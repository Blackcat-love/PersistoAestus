package com.example.financing;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DBOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

//    使用onCreate可以在初次使用数据库时，自动创建表，第二次启动则不会创建，自带了判断
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库sql语句并执行
        String sql = "CREATE TABLE expenditure (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "amount REAL," +
                "category TEXT," +
                "date DATE," +
                "transaction_method TEXT," +
                "account TEXT," +
                "payee TEXT," +
                "location TEXT," +
                "attachment TEXT," +
                "notes TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


}
