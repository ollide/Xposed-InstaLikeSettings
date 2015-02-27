package org.ollide.xposed.instagram;

import de.robv.android.xposed.XposedBridge;

public final class ClassNames {

    public static final String TARGET_PACKAGE = "com.instagram.android";

    public static String getBaseActivityName(int versionCode) {
        String className = "com.instagram.base.activity.e";
        switch (versionCode) {
            case 7369808: // 6.16.1
            case 7274492: // 6.16.0
            case 6891295: // 6.15.0
            case 6809797:
            case 6648672:
            case 6527593: // 6.14.1
            case 6456473: // 6.14.0
            case 6280649: // 6.13.3
            case 6208041: // 6.13.1
            case 6189164: // 6.13.0
            case 5914807: // 6.12.2
            case 5547416: // 6.11.2
            case 5257472: // 6.10.1
                break;
            default:
                XposedBridge.log("App version " + versionCode + " is not supported. Class name may have changed!");
        }
        return className;
    }

    public static String getDoubleTapListenerName(int versionCode) {
        String className = "com.instagram.android.feed.a.b.ac";
        switch (versionCode) {
            case 7369808: // 6.16.1
            case 7274492: // 6.16.0
            case 7097676: // 6.16.0 beta
                break;
            case 6891295: // 6.15.0
            case 6809797:
            case 6648672:
            case 6527593: // 6.14.1
            case 6456473: // 6.14.0
            case 6280649: // 6.13.3
            case 6208041: // 6.13.1
            case 6189164: // 6.13.0
            case 5914807: // 6.12.2
            case 5547416: // 6.11.2
            case 5257472: // 6.10.1
                className = "com.instagram.android.feed.a.b.z";
                break;
            default:
                XposedBridge.log("App version " + versionCode + " is not supported. Class name may have changed!");
        }
        return className;
    }

    public static String getHeartIconTapListenerName(int versionCode) {
        String className = "com.instagram.android.feed.a.b.o";
        switch (versionCode) {
            case 7369808: // 6.16.1
            case 7274492: // 6.16.0
            case 7097676: // 6.16.0 beta
                break;
            case 6891295: // 6.15.0
            case 6809797:
            case 6648672:
            case 6527593: // 6.14.1
            case 6456473: // 6.14.0
            case 6280649: // 6.13.3
            case 6208041: // 6.13.1
            case 6189164: // 6.13.0
            case 5914807: // 6.12.2
            case 5547416: // 6.11.2
            case 5257472: // 6.10.1
                className = "com.instagram.android.feed.a.b.b";
                break;
            default:
                XposedBridge.log("App version " + versionCode + " is not supported. Class name may have changed!");
        }
        return className;
    }
}
