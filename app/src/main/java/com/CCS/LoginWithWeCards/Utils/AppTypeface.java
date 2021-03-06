package com.CCS.LoginWithWeCards.Utils;

import android.app.Activity;
import android.graphics.Typeface;

/**
 * Created by mauliksantoki on 6/9/16.
 */

public class AppTypeface {

    private Typeface regularFont;


    private static final String REGULAR_FONT = "Muli.ttf";


    private final Activity activity;

    public AppTypeface(Activity act) {
        activity = act;
        inicontrol();
    }

    private void inicontrol() {
        regularFont = Typeface.createFromAsset(activity.getAssets(), REGULAR_FONT);
    }


    public Typeface getRegularFont() {
        return regularFont;
    }


}
