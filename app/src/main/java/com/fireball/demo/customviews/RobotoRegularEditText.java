package com.fireball.demo.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Web on 4/23/2016.
 */
public class RobotoRegularEditText extends EditText {
    public RobotoRegularEditText(Context context) {
        super(context);
        setFontType(context);
    }

    public RobotoRegularEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFontType(context);
    }

    public RobotoRegularEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontType(context);
    }

    public void setFontType(Context context) {

        if (isInEditMode()) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "RobotoRegular.ttf");
            setTypeface(typeface);
        }

    }
}
