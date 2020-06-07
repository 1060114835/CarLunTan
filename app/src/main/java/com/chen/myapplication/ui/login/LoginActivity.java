package com.chen.myapplication.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.myapplication.EditActivity;
import com.chen.myapplication.MainActivity2;
import com.chen.myapplication.MyApplication;
import com.chen.myapplication.R;
import com.chen.myapplication.data.Result;
import com.chen.myapplication.data.model.LoggedInUser;
import com.chen.myapplication.db.CarDataBase;
import com.chen.myapplication.ui.home.UserBean;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        final EditText usernameEditText = findViewById(R.id.username);
        usernameEditText.setText("admin");
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.register);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                List<UserBean> data = EditActivity.data;
                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getAccount().equals(username) &&
                                data.get(i).getPassword().equals(password)) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity2.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            CurrentUser.account = data.get(i).getAccount();
                            CurrentUser.password = data.get(i).getPassword();
                            CurrentUser.name = data.get(i).getName();
                            CurrentUser.age = data.get(i).getAge();
                            CurrentUser.school = data.get(i).getSchool();
                            CurrentUser.interist = data.get(i).getInterist();
                            CurrentUser.introduction = data.get(i).getIntroduction();
                            CurrentUser.location = data.get(i).getLocation();
                            finish();
                            return;
                        }
                    }
                    Toast.makeText(LoginActivity.this, "登录失败，输入错误", Toast.LENGTH_SHORT).show();
                } else {
                    data = query(new CarDataBase(LoginActivity.this, "CarDetail.db", null, 1).getReadableDatabase());
                    EditActivity.data = data;
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getAccount().equals(username) &&
                                data.get(i).getPassword().equals(password)) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity2.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            CurrentUser.account = data.get(i).getAccount();
                            CurrentUser.password = data.get(i).getPassword();
                            CurrentUser.name = data.get(i).getName();
                            CurrentUser.age = data.get(i).getAge();
                            CurrentUser.school = data.get(i).getSchool();
                            CurrentUser.interist = data.get(i).getInterist();
                            CurrentUser.introduction = data.get(i).getIntroduction();
                            CurrentUser.location = data.get(i).getLocation();
                            finish();
                            return;
                        }
                    }
                    Toast.makeText(LoginActivity.this, "登录失败，输入错误", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private List<UserBean> query(SQLiteDatabase database) {
        List<UserBean> data = new ArrayList<>();
        Cursor cursor = database.query("user", null,
                null, null, null, null, null);
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