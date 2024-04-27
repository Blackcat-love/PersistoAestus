package com.example.financing;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
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
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        创建弹窗
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setTitle("报错")//标题
                .setMessage("输入内容非法")//内容
                .setIcon(R.mipmap.ic_launcher)//图标
                .create();
//        弹窗2
        AlertDialog alertDialog2 = new AlertDialog.Builder(this)
                .setTitle("报错")//标题
                .setMessage("输入不能为空")//内容
                .setIcon(R.mipmap.ic_launcher)//图标
                .create();




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
                    alertDialog2.show();

                }else {
                    // 使用正则表达式检查输入是否只包含数字和小数点
                    if (editTextContent.matches("[0-9.]+")) {
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


                        // 获取当前时间日期
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String currentDateAndTime = sdf.format(new Date());

                        // 构建要显示的文本内容，包括计算结果和时间日期
                        String displayText = "\n时间日期：" + currentDateAndTime;


                        // 分类显示
                        String sort = "分类: 待添加 ";

                        // 将结果显示在 TextView 中
                        text_num.setText(String.valueOf(sum));

                        // 清空文本框内容
                        edit_text.setText("");

                        TextView textView = new TextView(MainActivity.this);

                        //设置点击按钮后要添加的内容
                        textView.setText(String.format("%s   %s", editTextContent, sort));
                        textView.setTextSize(30);

//                    textView.setLayoutParams(new LinearLayout.LayoutParams(
//                            ViewGroup.LayoutParams.WRAP_CONTENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT));

                        container.addView(textView);

                        // 创建一个新的 TextView 来显示额外的数据（时间日期）
                        TextView extraTextView = new TextView(MainActivity.this);
                        extraTextView.setText(displayText); // 设置额外数据为时间日期
                        extraTextView.setTextSize(20); // 设置文本大小
                        container.addView(extraTextView); // 将 TextView 添加到容器中
                    }else {
                        Log.d("TAG", "报错: 输入包含非法字符");
//                        目前存在很多代码复用问题，之后写成工具类然后方便调用保持整洁和高效

//                        弹窗
                        alertDialog1.show();


                        // 清空文本框内容
                        edit_text.setText("");

                    }

                }


            }
        });

    }






}