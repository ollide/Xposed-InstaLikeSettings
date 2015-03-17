package org.ollide.xposed.instagram;


import org.junit.Assert;
import org.junit.Test;

public class ClassNamesTest {

    private static final int VERSION_6_10_1 = 5257472;
    private static final int VERSION_6_16_0 = 7274492;
    private static final int VERSION_6_16_1 = 7369808;
    private static final int VERSION_6_18_0 = 8031086;

    private static final int VERSION_PAST_UNKNOWN = 100;

    @Test
    public void getBaseActivityNameTest() {
        Assert.assertEquals("com.instagram.base.activity.e", ClassNames.getBaseActivityName());

        ClassNames.initWithVersion(VERSION_6_16_1);
        Assert.assertEquals("com.instagram.base.activity.e", ClassNames.getBaseActivityName());

        ClassNames.initWithVersion(VERSION_PAST_UNKNOWN);
        Assert.assertEquals("com.instagram.base.activity.e", ClassNames.getBaseActivityName());
    }

    @Test
    public void getDoubleTapListenerNameTest() {
        ClassNames.initWithVersion(VERSION_6_18_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.at", ClassNames.getDoubleTapListenerName());    ClassNames.initWithVersion(VERSION_6_16_1);

        ClassNames.initWithVersion(VERSION_6_16_1);
        Assert.assertEquals("com.instagram.android.feed.a.b.ac", ClassNames.getDoubleTapListenerName());

        ClassNames.initWithVersion(VERSION_6_16_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.ac", ClassNames.getDoubleTapListenerName());

        ClassNames.initWithVersion(VERSION_6_10_1);
        Assert.assertEquals("com.instagram.android.feed.a.b.z", ClassNames.getDoubleTapListenerName());

        ClassNames.initWithVersion(VERSION_PAST_UNKNOWN);
        Assert.assertEquals("com.instagram.android.feed.a.b.z", ClassNames.getDoubleTapListenerName());
    }

    @Test
    public void getHeartIconTapListenerNameTest() {
        ClassNames.initWithVersion(VERSION_6_18_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.af", ClassNames.getHeartIconTapListenerName());

        ClassNames.initWithVersion(VERSION_6_16_1);
        Assert.assertEquals("com.instagram.android.feed.a.b.o", ClassNames.getHeartIconTapListenerName());

        ClassNames.initWithVersion(VERSION_6_16_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.o", ClassNames.getHeartIconTapListenerName());

        ClassNames.initWithVersion(VERSION_6_10_1);
        Assert.assertEquals("com.instagram.android.feed.a.b.b", ClassNames.getHeartIconTapListenerName());

        ClassNames.initWithVersion(VERSION_PAST_UNKNOWN);
        Assert.assertEquals("com.instagram.android.feed.a.b.b", ClassNames.getHeartIconTapListenerName());
    }

}
