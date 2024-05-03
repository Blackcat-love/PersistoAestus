package com.example.financing;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        获取jump按钮
        ImageView junp = findViewById(R.id.btn_jump);
//        获得insert按钮
        Button btn_insert = findViewById(R.id.btn_insert);
//        获得updata按钮
        Button btn_updata = findViewById(R.id.btn_updata);
//        获得select按钮
        Button btn_select = findViewById(R.id.btn_select);
//        获得del按钮
        Button btn_del = findViewById(R.id.btn_del);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBOpenHelper dbOpenHelper = new DBOpenHelper(MainActivity.this, "expenditure.db", null, 1);
                SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
//                创建存放数据的ContenValues对象
                ContentValues values = new ContentValues();
                values.put("amount",123);
                values.put("category","hello Sqlite");
                db.insert("expenditure",null,values);


            }
        });


        junp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JUMP other Layout
                Log.e("TAG","JUMP被点击了");
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


    }





}