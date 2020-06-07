package com.chen.myapplication;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.chen.myapplication.db.CarDataBase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity2 extends AppCompatActivity {

    private CarDataBase mCarDateBaseHelper;
    public SQLiteDatabase mSQLiteDatabase;
    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        mCarDateBaseHelper = CarDataBase.getInstance(this, "CarDetail.db");
        mSQLiteDatabase = mCarDateBaseHelper.getReadableDatabase();
        mSharedPreferences = this.getSharedPreferences("app", MODE_PRIVATE);
        initData();
    }

    private void initData() {
        Boolean isFirstStart = mSharedPreferences.getBoolean("is_first_start", true);
        //插入几条数据
        if (isFirstStart) {
            SharedPreferences.Editor mEditor = mSharedPreferences.edit();
            mEditor.putBoolean("is_first_start", false);
            mEditor.apply();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "Aci");
            contentValues.put("date", DateUtil.getCurrentDate());
            contentValues.put("grade", "和几个玩摄影朋友决定自驾去广东省隔壁的湖南省拍衡山，" +
                    "经过600公里的自驾，终于到达衡阳，衡山它是我国五岳之一，位于国家历史文化名城、湖南省第二大城市——衡阳市南岳区，" +
                    "海拔1300.2米。由于气候条件较其他四岳为好，处处是茂林修竹，终年翠绿；奇花异草，四时飘香，自然景色十分秀…");
            mSQLiteDatabase.insert("car", null, contentValues);
            contentValues.clear();
            contentValues.put("name", "Zoyp晨");
            contentValues.put("date", DateUtil.getCurrentDate());
            contentValues.put("grade", "911 Carrera RS是73年为了参加国际汽车运动联合会的GT大赛第四组而开发的，" +
                    "以911 S为原型基础，“RS”表示Rennsport，德文中的赛车竞速 。911 Carrera RS 搭载与" +
                    "911 S相同的2.7L发动机，但最大马力从167 Ps升级到210 Ps，最高时速可以达到245km/h；同时在车身" +
                    "重量上，911 Carrera RS也…");
            mSQLiteDatabase.insert("car", null, contentValues);
            contentValues.clear();
            contentValues.put("name", "Bob");
            contentValues.put("date", DateUtil.getCurrentDate());
            contentValues.put("grade", "请问30w以内买什么车好啊？我姐姐下个月7号结婚，带回来了30w彩礼，" +
                    "我爸准备用这钱给我买辆车，因为我年底估计也要结婚了，算是我的婚车，我目前看中了宝马3系和奥迪A4l，" +
                    "一直在纠结，请问哪辆车开出去有面子一点？");
            mSQLiteDatabase.insert("car", null, contentValues);
            contentValues.clear();
            contentValues.put("name", "乌拉乌拉乌拉");
            contentValues.put("date", DateUtil.getCurrentDate());
            contentValues.put("grade", "买完才醒悟，卡罗拉和速疼，根本比不了，速疼非常的有高级感，像十七八万的车，" +
                    "看起来很大气，卡罗拉很小气，难看，像几万块的车。");
            mSQLiteDatabase.insert("car", null, contentValues);
            contentValues.clear();
            contentValues.put("name", "Zoyp晨");
            contentValues.put("date", DateUtil.getCurrentDate());
            contentValues.put("grade", "911 Carrera RS是73年为了参加国际汽车运动联合会的GT大赛第四组而开发的，" +
                    "以911 S为原型基础，“RS”表示Rennsport，德文中的赛车竞速 。911 Carrera RS 搭载与" +
                    "911 S相同的2.7L发动机，但最大马力从167 Ps升级到210 Ps，最高时速可以达到245km/h；同时在车身" +
                    "重量上，911 Carrera RS也…");
            mSQLiteDatabase.insert("car", null, contentValues);
            contentValues.clear();
            contentValues.put("name", "Bob");
            contentValues.put("date", DateUtil.getCurrentDate());
            contentValues.put("grade", "请问30w以内买什么车好啊？我姐姐下个月7号结婚，带回来了30w彩礼，" +
                    "我爸准备用这钱给我买辆车，因为我年底估计也要结婚了，算是我的婚车，我目前看中了宝马3系和奥迪A4l，" +
                    "一直在纠结，请问哪辆车开出去有面子一点？");
            mSQLiteDatabase.insert("car", null, contentValues);
            contentValues.clear();
            contentValues.put("name", "乌拉乌拉乌拉");
            contentValues.put("date", DateUtil.getCurrentDate());
            contentValues.put("grade", "买完才醒悟，卡罗拉和速疼，根本比不了，速疼非常的有高级感，像十七八万的车，" +
                    "看起来很大气，卡罗拉很小气，难看，像几万块的车。");
            mSQLiteDatabase.insert("car", null, contentValues);
        }
    }

    private void initVIew() {

    }

}