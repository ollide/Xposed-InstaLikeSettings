package org.ollide.xposed.instagram;

public class Prefs {

    public static final String DT_MODE = "doubleTapPreference";
    public static final String HI_MODE = "likeIconPreference";

    public static final int MODE_DIALOG = 0;
    public static final int MODE_NONE = 1;
    public static final int MODE_LIKE = 2;

    private Prefs() {
        // do not instantiate
    }
}
