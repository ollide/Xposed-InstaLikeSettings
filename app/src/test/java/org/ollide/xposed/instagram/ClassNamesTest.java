package org.ollide.xposed.instagram;


import org.junit.Assert;
import org.junit.Test;

public class ClassNamesTest {

    private static final int VERSION_6_10_1 = 5257472;
    private static final int VERSION_6_16_0 = 7274492;
    private static final int VERSION_6_16_1 = 7369808;
    private static final int VERSION_6_18_0 = 8031086;
    private static final int VERSION_6_19_0 = 8582325;
    private static final int VERSION_6_20_0 = 9204850;

    private static final int VERSION_PAST_UNKNOWN = 100;

    @Test
    public void getBaseActivityNameTest() {
        Assert.assertEquals("com.instagram.base.activity.e", ClassNames.getBaseActivityName());

        ClassNames.initWithVersion(VERSION_6_16_1);
        Assert.assertEquals("com.instagram.base.activity.e", ClassNames.getBaseActivityName());

        ClassNames.initWithVersion(VERSION_PAST_UNKNOWN);
        Assert.assertEquals("com.instagram.base.activity.e", ClassNames.getBaseActivityName());
    }

    // TODO: find a better way to test different versions
    // @Test
    public void getDoubleTapListenerNameTest() {
        ClassNames.initWithVersion(VERSION_6_20_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.bc", ClassNames.getDoubleTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_6_19_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.aw", ClassNames.getDoubleTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_6_18_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.at", ClassNames.getDoubleTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_6_16_1);
        Assert.assertEquals("com.instagram.android.feed.a.b.ac", ClassNames.getDoubleTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_6_16_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.ac", ClassNames.getDoubleTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_6_10_1);
        Assert.assertEquals("com.instagram.android.feed.a.b.z", ClassNames.getDoubleTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_PAST_UNKNOWN);
        Assert.assertEquals("com.instagram.android.feed.a.b.z", ClassNames.getDoubleTapListenerCandidates());
    }

    // TODO: find a better way to test different versions
    //@Test
    public void getHeartIconTapListenerNameTest() {
        ClassNames.initWithVersion(VERSION_6_20_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.ai", ClassNames.getHeartIconTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_6_19_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.ai", ClassNames.getHeartIconTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_6_18_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.af", ClassNames.getHeartIconTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_6_16_1);
        Assert.assertEquals("com.instagram.android.feed.a.b.o", ClassNames.getHeartIconTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_6_16_0);
        Assert.assertEquals("com.instagram.android.feed.a.b.o", ClassNames.getHeartIconTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_6_10_1);
        Assert.assertEquals("com.instagram.android.feed.a.b.b", ClassNames.getHeartIconTapListenerCandidates());

        ClassNames.initWithVersion(VERSION_PAST_UNKNOWN);
        Assert.assertEquals("com.instagram.android.feed.a.b.b", ClassNames.getHeartIconTapListenerCandidates());
    }

}
