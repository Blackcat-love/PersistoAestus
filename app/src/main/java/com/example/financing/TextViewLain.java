package com.example.financing;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;

import androidx.annotation.Nullable;

public class TextViewLain extends TextView {
    public TextViewLain(Context context) {
        super(context);
        init();
    }

    public TextViewLain(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewLain(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 设置TextViewLain为获取焦点状态
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 在5秒后执行关闭或隐藏操作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 在这里添加关闭或隐藏组件的代码
                // 如果要隐藏组件，可以使用setVisibility(View.GONE);
                // 如果要关闭组件，可以使用setVisibility(View.INVISIBLE); 或者从父布局中移除组件
                // 例如：getParent().removeView(TextViewLain.this);
                // 这里仅提供示例代码，请根据您的需求进行相应修改
                TextViewLain.this.setVisibility(GONE);
            }
        }, 2000); // 5000毫秒 = 5秒
    }

    //    重写
    @Override
    public boolean isFocused() {
        return true;
    }

}
