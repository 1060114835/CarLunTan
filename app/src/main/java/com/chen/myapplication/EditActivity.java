package com.chen.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chen.myapplication.db.CarDataBase;
import com.chen.myapplication.ui.home.InfoBean;
import com.chen.myapplication.ui.home.UserBean;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    private Button register;
    private EditText account;
    private EditText password;
    private EditText name;
    private EditText age;
    private EditText location;
    private EditText interest;
    private EditText school;
    private EditText introduction;
    public static List<UserBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        account = findViewById(R.id.et1);
        password = findViewById(R.id.et_2);
        name = findViewById(R.id.et_3);
        age = findViewById(R.id.et_4);
        location = findViewById(R.id.et_5);
        interest = findViewById(R.id.et_6);
        school = findViewById(R.id.et_7);
        introduction = findViewById(R.id.et_8);
        register = findViewById(R.id.confirm_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (account.getText().toString().isEmpty() &&
                        password.getText().toString().isEmpty() && name.getText().toString().isEmpty()) {
                    Toast.makeText(EditActivity.this, "请检查是否输入账号、密码、名字", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase sqLiteDatabase =
                            CarDataBase.getInstance(getApplicationContext(), "CarDetail.db").getReadableDatabase();
                    data = query(sqLiteDatabase);
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getAccount().equals(account.getText().toString())) {
                            Toast.makeText(EditActivity.this, "您注册的账号已存在", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    ContentValues values = new ContentValues();
                    values.put("account", account.getText().toString());
                    values.put("password", password.getText().toString());
                    values.put("name", name.getText().toString());
                    values.put("age", age.getText().toString());
                    values.put("location", location.getText().toString());
                    values.put("interest", interest.getText().toString());
                    values.put("school", school.getText().toString());
                    values.put("intro", introduction.getText().toString());
                    sqLiteDatabase.insert("user", null, values);
                    Toast.makeText(EditActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private  List<UserBean> query(SQLiteDatabase database) {
        List<UserBean> data = new ArrayList<>();
        Cursor cursor = database.query("user", null,
                null,null,null,null,null);
        int size = cursor.getCount();
        for (int i = 0; i < size; i++) {
            cursor.moveToNext();
            String account = cursor.getString(cursor.getColumnIndex("account"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String age = cursor.getString(cursor.getColumnIndex("age"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            String interest = cursor.getString(cursor.getColumnIndex("interest"));
            String school = cursor.getString(cursor.getColumnIndex("school"));
            String intro = cursor.getString(cursor.getColumnIndex("intro"));
            data.add(new UserBean(account, password, name, age, location, interest, school, intro));
        }
        cursor.close();
        return data;
    }
}