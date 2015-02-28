package org.ollide.xposed.instagram;

public final class ClassNames {

    public static final String TARGET_PACKAGE = "com.instagram.android";

    private static final int VERSION_6_10_1 = 5257472;
    private static final int VERSION_6_15_0 = 6891295;
    private static final int VERSION_6_16_0_BETA = 7097676;
    // last reviewed version
    private static final int VERSION_6_16_1 = 7369808;

    /**
     * The BaseActivity's obfuscated class name.
     * Has never changed so far (since 6.10.1 | 5257472).
     */
    private static String sBaseActivityName = "com.instagram.base.activity.e";

    /**
     * Last changed with version 6.16.0_Beta, first checked with version 6.10.1.
     */
    private static String sDoubleTabListenerName = "com.instagram.android.feed.a.b.ac";
    /**
     * Last changed with version 6.16.0_Beta, first checked with version 6.10.1.
     */
    private static String sHeartIconTapListenerName = "com.instagram.android.feed.a.b.o";

    public static void initWithVersion(int versionCode) {
        if (versionCode >= VERSION_6_16_0_BETA) {
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
