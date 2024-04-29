package com.example.financing;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.romainpiel.titanic.library.Titanic;
import com.romainpiel.titanic.library.TitanicTextView;
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
//        获取浮动文字
        TitanicTextView titanicTextView = findViewById(R.id.titanic_tv);
//        获取SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("my_data", MODE_PRIVATE);
//        获取SharedPreferences的编辑对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
//      使用浮动文字
        Titanic titanic = new Titanic();
        titanicTextView.setTypeface(Typefaces.get(this,"Satisfy-Regular.ttf"));
        titanic.start(titanicTextView);

        //        测试
        //                        defValue在读取失败的情况下返回
        String text = sharedPreferences.getString("text", "default");
        Log.e("TAG",text);


        btn_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 在按钮点击事件中执行你想要的操作
                // 获取文本框中的内容
                String editTextContent = edit_text.getText().toString();
                // 获取 TextView 中的文本
                String textViewContent = text_num.getText().toString();
                // 获取要添加的组件的容器
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

//                        转换为小数
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
//                        保存数据
                        editor.putString("text",editTextContent);
                        editor.apply();
//                        调用数据
//                        BUG:读取失败
                        //        测试
                        //                        defValue在读取失败的情况下返回
                        String text = sharedPreferences.getString("text", "default");
                        Log.e("TAG",text);
                        // 设置点击按钮后要添加的内容
//                        textView.setText(String.format("%s   %s", editTextContent, sort));
                        textView.setText(text);
                        textView.setTextSize(30);
                        container.addView(textView);
                        // 创建一个新的 TextView 来显示额外的数据（时间日期）
                        TextView extraTextView = new TextView(MainActivity.this);
                        extraTextView.setText(displayText); // 设置额外数据为时间日期
                        extraTextView.setTextSize(20); // 设置文本大小
                        container.addView(extraTextView); // 将 TextView 添加到容器中
                    }else {
                        Log.d("TAG", "报错: 输入包含非法字符");
                    // 目前存在很多代码复用问题，之后写成工具类然后方便调用保持整洁和高效
                    // 弹窗
                        alertDialog1.show();
                        // 清空文本框内容
                        edit_text.setText("");
                    }
                }
            }
        });
    }



}