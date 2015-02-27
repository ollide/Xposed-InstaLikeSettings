package org.ollide.xposed.instagram;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class MethodHooks implements IXposedHookLoadPackage {

    public static final String MY_PACKAGE_NAME = "org.ollide.xposed.instagram";

    private static Context mContext;
    private static int mVersionCode = 0;

    private static int mDtMode = Prefs.MODE_DIALOG;
    private static int mHiMode = Prefs.MODE_LIKE;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals(ClassNames.TARGET_PACKAGE)) {
            return;
        }

        // we are in com.instagram.android - hook methods!

        // load module preferences
        refreshPreferences();

        // retrieve app's versionCode via reflection
        ApplicationInfo info = loadPackageParam.appInfo;
        try {
            Class c = Class.forName("android.content.pm.ApplicationInfo");
            Field versionCodeField = c.getField("versionCode");
            mVersionCode = (int) versionCodeField.get(info);
        } catch (Exception e) {
            // hidden API use may fail
        }

        // hook BaseActivity's onCreate to gain access on a valid Context object
        findAndHookMethod(ClassNames.getBaseActivityName(mVersionCode), loadPackageParam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                mContext = (Context) param.thisObject;
            }
        });

        hookDoubleTap(loadPackageParam.classLoader);
        hookHeartIcon(loadPackageParam.classLoader);
    }

    private void hookDoubleTap(ClassLoader classLoader) {
        // replace image's onDoubleTap
        findAndHookMethod(ClassNames.getDoubleTapListenerName(mVersionCode), classLoader, "onDoubleTap", MotionEvent.class, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(final MethodHookParam param) throws Throwable {

                if (mDtMode == Prefs.MODE_NONE) {
                    return true;
                } else if (mDtMode == Prefs.MODE_LIKE) {
                    return XposedBridge.invokeOriginalMethod(param.method, param.thisObject, param.args);
                } else {
                    showLikeDialog(param);
                    return true;
                }
            }
        });
    }

    private void hookHeartIcon(ClassLoader classLoader) {
        // replace the heart icon's onClick
        findAndHookMethod(ClassNames.getHeartIconTapListenerName(mVersionCode), classLoader, "onClick", View.class, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {

                if (mHiMode == Prefs.MODE_NONE) {
                    return null;
                } else if (mHiMode == Prefs.MODE_LIKE) {
                    return XposedBridge.invokeOriginalMethod(param.method, param.thisObject, param.args);
                } else {
                    showLikeDialog(param);
                    return null;
                }
            }
        });
    }

    private void showLikeDialog(final XC_MethodHook.MethodHookParam param) {
        if (mContext != null) {
            AlertDialog dialog = new AlertDialog.Builder(mContext)
                    .setTitle("Like photo?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                XposedBridge.invokeOriginalMethod(param.method, param.thisObject, param.args);
                            } catch (Exception e) {
                                // ignore
                            }
                        }
                    })
                    .setNegativeButton("No", null)
                    .create();
            dialog.show();
        }
    }

    private static void refreshPreferences() {
        XSharedPreferences prefs = new XSharedPreferences(new File(
                Environment.getDataDirectory(), "data/"
                + MY_PACKAGE_NAME + "/shared_prefs/" + MY_PACKAGE_NAME
                + "_preferences" + ".xml"));
        prefs.reload();

        mDtMode = Integer.parseInt(prefs.getString(Prefs.DT_MODE, Integer.toString(Prefs.MODE_DIALOG)));
        mHiMode = Integer.parseInt(prefs.getString(Prefs.HI_MODE, Integer.toString(Prefs.MODE_LIKE)));
    }
}
