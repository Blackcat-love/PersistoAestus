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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.romainpiel.titanic.library.Titanic;
import com.romainpiel.titanic.library.TitanicTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class AddActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        被创建时，所以只会调用一次
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        创建弹窗
        AlertDialog error_rule = new AlertDialog.Builder(this)
                .setTitle("报错")//标题
                .setMessage("输入内容非法")//内容
                .setIcon(R.mipmap.ic_launcher)//图标
                .create();
        AlertDialog error_null = new AlertDialog.Builder(this)
                .setTitle("报错")//标题
                .setMessage("输入不能为空")//内容
                .setIcon(R.mipmap.ic_launcher)//图标
                .create();

//        获取按钮
        Button btn_sum = findViewById(R.id.btn_sum);
//        clr测试用
        Button btn_clr = findViewById(R.id.btn_clr);
//        获取文本框内容
        EditText edit_text = findViewById(R.id.edit_text);
//        获取保存的数据
        TextView text_num = findViewById(R.id.text_num);
//        获取浮动文字
        TitanicTextView titanicTextView = findViewById(R.id.titanic_tv);
//      使用浮动文字
        Titanic titanic = new Titanic();
        titanicTextView.setTypeface(Typefaces.get(this,"Satisfy-Regular.ttf"));
        titanic.start(titanicTextView);

        text_num.setText(String.valueOf(calculateMapSum()));
        Log.e("总和",String.valueOf(calculateMapSum()));

        ShowContainer();
        btn_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取文本框中的内容
                String editTextContent = edit_text.getText().toString();

                if (editTextContent.isEmpty()){
                    Log.d("TAG","报错:输入为空");
                    error_null.show();
                }else {
                    // 使用正则表达式检查输入是否只包含数字和小数点
                    if (editTextContent.matches("[0-9.]+")) {
                        // 获取当前时间日期
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String currentDateAndTime = sdf.format(new Date());
                        // 构建要显示的文本内容，包括计算结果和时间日期
                        String displayText = "\n时间日期：" + currentDateAndTime;
                        // 清空文本框内容
                        edit_text.setText("");
                        //获取SharedPreferences对象
                        SharedPreferences sharedPreferences = getSharedPreferences("my_data", MODE_PRIVATE);
                        //获取SharedPreferences的编辑对象
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        int Data_size = sharedPreferences.getAll().size();
                        String key = String.valueOf(Data_size+1);
                        editor.putString(key,editTextContent);
                        editor.apply();
                        // 总和显示
                        text_num.setText(String.valueOf(calculateMapSum()));
                        ShowContainer();
                    }else {
                        Log.d("TAG", "报错: 输入包含非法字符");
                        // 弹窗
                        error_rule.show();
                        // 清空文本框内容
                        edit_text.setText("");
                    }
                }
            }
        });


        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        读取数据添加到容器里
                SharedPreferences sharedPreferences = getSharedPreferences("my_data", MODE_PRIVATE);
                sharedPreferences.edit().clear().apply();
                ShowContainer();
            }
        });
    }

    public static Map<String, String> parseStringToMap(String input) {
        // 去除字符串中的大括号和空格
        input = input.replaceAll("[{}\\s]", "");
        // 按逗号分割成键值对
        String[] pairs = input.split(",");

        Map<String, String> map = new HashMap<>();
        for (String pair : pairs) {
            // 再按等号分割键值对
            String[] keyValue = pair.split("=");
            Log.e("for循环输出:",pair);
            // 键值对应关系存入 Map
            if (keyValue.length == 2) {
                map.put(keyValue[0], keyValue[1]);
            }
        }
        return map;
    }

//    BUG 需要排序
    public void ShowContainer(){
        //        读取数据添加到容器里
        SharedPreferences sharedPreferences = getSharedPreferences("my_data", MODE_PRIVATE);
        //        获取SharedPreferences的编辑对象
        Map<String, String> dataMap = parseStringToMap(sharedPreferences.getAll().toString());
//                        DataMap只输出了Values
        System.out.println(dataMap.values() + "size:" + dataMap.size());
        // 获取要添加的组件的容器
        LinearLayout container = findViewById(R.id.main_line);
        container.removeAllViews();
        //        倒序
        for (int i = dataMap.size() - 1; i >= 0; i--) {
            //            创建TextView
            TextView textView = new TextView(AddActivity.this);
            textView.setText(dataMap.get(String.valueOf(i+1)));
            textView.setTextSize(30);
            container.addView(textView);
        }

    }

//虽然目前增加了SqLite来存储数据，但是目前先不用。
    //    计算总和
    public Double calculateMapSum(){
        Double sum = 0.0;
        //        读取数据添加到容器里
        SharedPreferences sharedPreferences = getSharedPreferences("my_data", MODE_PRIVATE);
        //        获取SharedPreferences的编辑对象
        Map<String, String> dataMap = parseStringToMap(sharedPreferences.getAll().toString());
        // 获取要添加的组件的容器
        LinearLayout container = findViewById(R.id.main_line);
        container.removeAllViews();
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            sum += Double.parseDouble(entry.getValue());
        }
        return sum;
    }




}