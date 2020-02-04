package com.mapmyindia.intouchsdkdemo.binding;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class BindingAdapterUtils {

    @BindingAdapter("isEnable")
    public static void enabled(View view, Boolean show) {
        view.setEnabled(show);
    }


    @BindingAdapter("isVisible")
    public static void showHide(View view, Boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("bind:text")
    public static void text(TextView view, String str) {
        view.setText(str);
    }


    @BindingAdapter("android:drawableLeft")
    public static void setDrawableLeft(TextView textView, Drawable resourceId) {
        textView.setCompoundDrawablesWithIntrinsicBounds(resourceId, null, null, null);
    }

    @BindingAdapter("bind:buttonDrawableLeft")
    public static void setButtonDrawableLeft(Button button, Drawable resourceId) {
        button.setCompoundDrawablesWithIntrinsicBounds(resourceId, null, null, null);
    }

}
