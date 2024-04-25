package com.example.financing;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TextViewNum  extends TextView {

    public TextViewNum(Context context) {
        super(context);
        init();
    }

    public TextViewNum(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewNum(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        // 设置TextView为获取焦点状态
        setFocusable(true);
        setFocusableInTouchMode(true);
    }






}
