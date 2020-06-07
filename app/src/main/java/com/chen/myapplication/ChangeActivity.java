package com.chen.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.myapplication.db.CarDataBase;
import com.chen.myapplication.ui.login.CurrentUser;

public class ChangeActivity extends AppCompatActivity {

    private Button change;
    private TextView account;
    private EditText password;
    private EditText name;
    private EditText age;
    private EditText location;
    private EditText interest;
    private EditText school;
    private EditText introduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        change = findViewById(R.id.confirm_change);
        account = findViewById(R.id.tv10);
        account.setText(CurrentUser.account);
        password = findViewById(R.id.et_12);
        name = findViewById(R.id.et_13);
        age = findViewById(R.id.et_14);
        location = findViewById(R.id.et_15);
        interest = findViewById(R.id.et_16);
        school = findViewById(R.id.et_17);
        introduction = findViewById(R.id.et_18);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (account.getText().toString().isEmpty() &&
                        password.getText().toString().isEmpty() && name.getText().toString().isEmpty()) {
                    Toast.makeText(ChangeActivity.this, "请检查是否输入、密码、名字", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put("account", account.getText().toString());
                    values.put("password", password.getText().toString());
                    values.put("name", name.getText().toString());
                    values.put("age", age.getText().toString());
                    values.put("location", location.getText().toString());
                    values.put("interest", interest.getText().toString());
                    values.put("school", school.getText().toString());
                    values.put("intro", introduction.getText().toString());
                    SQLiteDatabase sqLiteDatabase =
                            CarDataBase.getInstance(getApplicationContext(), "CarDetail.db").getReadableDatabase();
                    sqLiteDatabase.update("user", values, "account = ?", new String[]{CurrentUser.account});
                    CurrentUser.account = account.getText().toString();
                    CurrentUser.password = password.getText().toString();
                    CurrentUser.name = name.getText().toString();
                    CurrentUser.age = age.getText().toString();
                    CurrentUser.school = school.getText().toString();
                    CurrentUser.interist = interest.getText().toString();
                    CurrentUser.introduction = introduction.getText().toString();
                    CurrentUser.location = location.getText().toString();
                    finish();
                    Toast.makeText(ChangeActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}