package com.example.financing;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        获取按钮
        Button btn_sum = findViewById(R.id.btn_sum);
//        获取文本框内容
        EditText edit_text = findViewById(R.id.edit_text);
//        获取保存的数据
        TextView text_num = findViewById(R.id.text_num);




        btn_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在按钮点击事件中执行你想要的操作
                // 获取文本框中的内容
                String editTextContent = edit_text.getText().toString();
                // 获取 TextView 中的文本
                String textViewContent = text_num.getText().toString();

//                获取要添加的组件的容器
                LinearLayout container = findViewById(R.id.main_line);

                if (editTextContent.isEmpty()){
                    Log.d("TAG","报错:输入为空");

                }else {
                    // 将字符串转换为 double 类型
                    double editTextValue = 0;
                    try {
                        editTextValue = Double.parseDouble(editTextContent);
                        // 在这里处理转换成功的情况
                    } catch (NumberFormatException e) {
                        // 在这里处理转换失败的情况，即输入不是有效的数字或小数
                        Log.d("TAG", "报错:输入不是有效的数字或小数");
                    }
                    double textViewValue = Double.parseDouble(textViewContent);

                    // 计算总和
                    double sum = editTextValue + textViewValue;

                    // 将结果显示在 TextView 中
                    text_num.setText(String.valueOf(sum));

                    // 清空文本框内容
                    edit_text.setText("");

                    TextView textView = new TextView(MainActivity.this);

                    //设置点击按钮后要添加的内容
                    textView.setText(editTextContent);
                    textView.setTextSize(30);

//                    textView.setLayoutParams(new LinearLayout.LayoutParams(
//                            ViewGroup.LayoutParams.WRAP_CONTENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    container.addView(textView);
                }


            }
        });

    }
}