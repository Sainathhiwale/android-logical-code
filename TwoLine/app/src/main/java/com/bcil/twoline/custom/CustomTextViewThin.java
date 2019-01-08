package com.bcil.twoline.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CustomTextViewThin extends AppCompatTextView {

    public CustomTextViewThin(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextViewThin(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewThin(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Gotham-Book.otf");
            setTypeface(tf);
        }

    }

}
