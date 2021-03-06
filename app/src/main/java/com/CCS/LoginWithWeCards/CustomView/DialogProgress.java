package com.CCS.LoginWithWeCards.CustomView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;

import com.CCS.LoginWithWeCards.R;
import com.CCS.LoginWithWeCards.Utils.AppTypeface;
import com.CCS.LoginWithWeCards.Utils.TypefaceUtils;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;


/**
 * Created by bhavika on 5/9/16.
 */

public class DialogProgress extends ProgressDialog {
    private AppTypeface typeface;

    public DialogProgress(Activity context) {
        super(context, getStyle());

        setCancelable(false);

        String message = "Please Wait ...";
        SpannableString spannableString = new SpannableString(message);
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(TypefaceUtils.getInstance().getRegularTypeface(context));
        spannableString.setSpan(typefaceSpan, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        setMessage(spannableString);
    }

    private static int getStyle() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            return R.style.AppAlertTheme;
        } else {
            return R.style.AppAlertTheme19;
        }
    }
}
