package com.example.financing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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