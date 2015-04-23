package org.ollide.xposed.instagram;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class MethodHooks implements IXposedHookLoadPackage {

    public static final String MY_PACKAGE_NAME = "org.ollide.xposed.instagram";

    private static Context mContext;

    private static int mDtMode = Prefs.MODE_DIALOG;
    private static int mHiMode = Prefs.MODE_LIKE;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals(ClassNames.TARGET_PACKAGE)) {
            return;
        }

        // we are in com.instagram.android - hook methods!

        // load module preferences
        refreshPreferences();

        // hook BaseActivity's onResume to gain access on a valid Context object
        findAndHookMethod(ClassNames.getBaseActivityName(), loadPackageParam.classLoader, "onResume", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                mContext = (Context) param.thisObject;
            }
        });

        // hook application start to init version code without reflection
        findAndHookMethod(ClassNames.getAppClassName(), loadPackageParam.classLoader, "onCreate", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Context context = (Context) param.thisObject;
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                // initialize obfuscated class names
                ClassNames.initWithVersion(packageInfo.versionCode);

                hookDoubleTap(loadPackageParam.classLoader);
                hookHeartIcon(loadPackageParam.classLoader);
            }
        });
    }

    private void hookDoubleTap(ClassLoader classLoader) {
        for (String candidate : ClassNames.getDoubleTapListenerCandidates()) {
            if (hookDoubleTap(classLoader, candidate)) {
                break;
            }
        }
    }

    private boolean hookDoubleTap(ClassLoader classLoader, String className) {
        try {
            findAndHookMethod(className, classLoader, "onDoubleTap", MotionEvent.class, new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(final MethodHookParam param) throws Throwable {
                    refreshPreferences();

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
            return true;
        } catch (XposedHelpers.ClassNotFoundError | NoSuchMethodError error) {
            return false;
        }
    }

    private void hookHeartIcon(ClassLoader classLoader) {
        for (String candidate : ClassNames.getHeartIconTapListenerCandidates()) {
            if (hookHeartIcon(classLoader, candidate)) {
                break;
            }
        }
    }

    private boolean hookHeartIcon(ClassLoader classLoader, String className) {
        try {
            findAndHookMethod(className, classLoader, "onClick", View.class, new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    refreshPreferences();

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
            return true;
        } catch (XposedHelpers.ClassNotFoundError | NoSuchMethodError error) {
            return false;
        }
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
