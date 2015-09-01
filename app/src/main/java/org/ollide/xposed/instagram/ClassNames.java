package org.ollide.xposed.instagram;

import java.util.Arrays;

public final class ClassNames {

    public static final String TARGET_PACKAGE = "com.instagram.android";

    private static MinSdk appMinSdk = MinSdk.UNKNOWN;

    // minSdk9 codes
    private static final int V9_6_10_1 = 5257472; // Nov 5 '14
    private static final int V9_6_15_0 = 6891295; // Feb 10 '15

    private static final int V9_6_16_0 = 7097676;

    private static final int V9_6_17_0 = 7483428; // Mar 3 '15

    private static final int V9_6_18_0 = 8031086; // Mar 17 '15
    private static final int V9_6_18_0_2 = 8317903; // Mar 24 '15

    private static final int V9_6_19_0 = 8582325; // Apr 1 '15
    private static final int V9_6_19_0_2 = 8886221; // Apr 8 '15
    private static final int V9_6_19_0_3 = 9350372; // Apr 20 '15

    private static final Integer[] V9_CODES = {V9_6_10_1, V9_6_15_0, V9_6_16_0, V9_6_17_0, V9_6_18_0, V9_6_18_0_2,
            V9_6_19_0, V9_6_19_0_2, V9_6_19_0_3};

    // minSdk14 codes
    private static final int V14_6_20_0 = 9204850; // Apr 17 '15
    private static final int V14_6_20_1 = 9409530; // Apr 23 '15
    private static final int V14_6_20_1_2 = 9476507; // Apr 24 '15
    private static final int V14_6_20_2 = 9494173; // Apr 25 '15

    private static final int V14_7_3_0 = 13211814; // Jul 21 '15

    private static final int V14_7_4_0 = 13688692; // Aug 06 '15
    private static final int V14_7_5_0 = 14047999; // Aug 18 '15
    private static final int V14_7_5_1 = 14510518; // Sep 01 '15

    private static final Integer[] V14_CODES = {V14_6_20_0, V14_6_20_1, V14_6_20_1_2, V14_6_20_2,
            V14_7_3_0, V14_7_4_0, V14_7_5_0, V14_7_5_1};

    /**
     * Last changed with version 6.19.0 (8582325) last checked with 9350372
     */
    private static String v9DoubleTabListenerName = "com.instagram.android.feed.a.b.aw";
    /**
     * Last changed with version 6.19.0 (8582325) last checked with 9350372
     */
    private static String v9HeartIconTapListenerName = "com.instagram.android.feed.a.b.ai";

    /**
     * Last changed with version 7.4.0 (13688692) last checked with 14510518
     */
    private static String v14DoubleTabListenerName = "com.instagram.android.feed.a.b.au";
    /**
     * Last changed with version 7.4.0 (13688692) last checked with 14510518
     */
    private static String v14HeartIconTapListenerName = "com.instagram.android.feed.a.b.w";

    public static void initWithVersion(int versionCode) {
        // minSdk9 class names
        if (versionCode >= V9_6_19_0) {
            v9DoubleTabListenerName = "com.instagram.android.feed.a.b.aw";
            v9HeartIconTapListenerName = "com.instagram.android.feed.a.b.ai";
        } else if (versionCode >= V9_6_18_0) {
            v9DoubleTabListenerName = "com.instagram.android.feed.a.b.at";
            v9HeartIconTapListenerName = "com.instagram.android.feed.a.b.af";
        } else if (versionCode >= V9_6_17_0) {
            v9DoubleTabListenerName = "com.instagram.android.feed.a.b.ao";
            v9HeartIconTapListenerName = "com.instagram.android.feed.a.b.aa";
        } else if (versionCode >= V9_6_16_0) {
            v9DoubleTabListenerName = "com.instagram.android.feed.a.b.ac";
            v9HeartIconTapListenerName = "com.instagram.android.feed.a.b.o";
        } else {
            v9DoubleTabListenerName = "com.instagram.android.feed.a.b.z";
            v9HeartIconTapListenerName = "com.instagram.android.feed.a.b.b";
        }

        // minSdk14 class names
        if (versionCode >= V14_7_4_0) {
            v14DoubleTabListenerName = "com.instagram.android.feed.a.b.au";
            v14HeartIconTapListenerName = "com.instagram.android.feed.a.b.w";
        } else if (versionCode >= V14_7_3_0) {
            v14DoubleTabListenerName = "com.instagram.android.feed.a.b.at";
            v14HeartIconTapListenerName = "com.instagram.android.feed.a.b.w";
        } else if (versionCode >= V14_6_20_0) {
            v14DoubleTabListenerName = "com.instagram.android.feed.a.b.bc";
            v14HeartIconTapListenerName = "com.instagram.android.feed.a.b.ai";
        }

        // check exact version match
        if (Arrays.asList(V9_CODES).contains(versionCode)) {
            appMinSdk = MinSdk.V9;
        } else if (Arrays.asList(V14_CODES).contains(versionCode)) {
            appMinSdk = MinSdk.V14;
        } else {
            appMinSdk = MinSdk.UNKNOWN;
        }
    }

    public static String getBaseActivityName() {
        return "com.instagram.base.activity.e";
    }

    public static String getAppClassName() {
        return "com.instagram.app.InstagramAppShell";
    }

    public static String[] getDoubleTapListenerCandidates() {
        switch (appMinSdk) {
            case V9:
                return new String[]{v9DoubleTabListenerName};
            case V14:
                return new String[]{v14DoubleTabListenerName};
            default:
                return new String[]{v9DoubleTabListenerName, v14DoubleTabListenerName};
        }
    }

    public static String[] getHeartIconTapListenerCandidates() {
        switch (appMinSdk) {
            case V9:
                return new String[]{v9HeartIconTapListenerName};
            case V14:
                return new String[]{v14HeartIconTapListenerName};
            default:
                return new String[]{v9HeartIconTapListenerName, v14HeartIconTapListenerName};
        }
    }

    enum MinSdk {
        UNKNOWN, V9, V14
    }
}
