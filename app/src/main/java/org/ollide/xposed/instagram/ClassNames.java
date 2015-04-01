package org.ollide.xposed.instagram;

public final class ClassNames {

    public static final String TARGET_PACKAGE = "com.instagram.android";

    private static final int VERSION_6_10_1 = 5257472;
    private static final int VERSION_6_15_0 = 6891295;
    private static final int VERSION_6_16_0_BETA = 7097676;
    private static final int VERSION_6_16_1 = 7369808;
    private static final int VERSION_6_17_0 = 7483428;
    private static final int VERSION_6_18_0 = 8031086;
    // last reviewed version
    private static final int VERSION_6_19_0 = 8582325;

    /**
     * The BaseActivity's obfuscated class name.
     * Has never changed so far (since 6.10.1 | 5257472).
     */
    private static String sBaseActivityName = "com.instagram.base.activity.e";

    /**
     * Last changed with version 6.19.0, first checked with version 6.10.1.
     */
    private static String sDoubleTabListenerName = "com.instagram.android.feed.a.b.aw";
    /**
     * Last changed with version 6.19.0, first checked with version 6.10.1.
     */
    private static String sHeartIconTapListenerName = "com.instagram.android.feed.a.b.ah";

    public static void initWithVersion(int versionCode) {
        if (versionCode >= VERSION_6_19_0) {
            sDoubleTabListenerName = "com.instagram.android.feed.a.b.aw";
            sHeartIconTapListenerName = "com.instagram.android.feed.a.b.ah";
        } else if (versionCode >= VERSION_6_18_0) {
            sDoubleTabListenerName = "com.instagram.android.feed.a.b.at";
            sHeartIconTapListenerName = "com.instagram.android.feed.a.b.af";
        } else if (versionCode >= VERSION_6_17_0) {
            sDoubleTabListenerName = "com.instagram.android.feed.a.b.ao";
            sHeartIconTapListenerName = "com.instagram.android.feed.a.b.aa";
        } else if (versionCode >= VERSION_6_16_0_BETA) {
            sDoubleTabListenerName = "com.instagram.android.feed.a.b.ac";
            sHeartIconTapListenerName = "com.instagram.android.feed.a.b.o";
        } else {
            sDoubleTabListenerName = "com.instagram.android.feed.a.b.z";
            sHeartIconTapListenerName = "com.instagram.android.feed.a.b.b";
        }
    }

    public static String getBaseActivityName() {
        return sBaseActivityName;
    }

    public static String getDoubleTapListenerName() {
        return sDoubleTabListenerName;
    }

    public static String getHeartIconTapListenerName() {
        return sHeartIconTapListenerName;
    }
}
