package com.chen.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chen.myapplication.ui.login.CurrentUser;

public class CommentActivity extends AppCompatActivity {
    private Button commentBt;
    private EditText commentEdit;
    private TextView comment;
    private TextView name;
    private TextView date;
    private ImageView imageView;
    private TextView introduction;
    private TextView currentName;
    private TextView currentDate;
    private LinearLayout ly;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        comment = findViewById(R.id.commentText);
        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        imageView = findViewById(R.id.imageView2);
        introduction = findViewById(R.id.introduction);
        commentBt = findViewById(R.id.comment);
        commentEdit = findViewById(R.id.commentEdit);
        ly = findViewById(R.id.ly_text);
        currentName = findViewById(R.id.currentName);
        currentDate = findViewById(R.id.currentDate);
        currentName.setText(CurrentUser.name);
        currentDate.setText(DateUtil.getCurrentDate());
        name.setText(getIntent().getStringExtra("name"));
        date.setText(getIntent().getStringExtra("date"));
        introduction.setText(getIntent().getStringExtra("introduction"));
        imageView.setImageResource(getIntent().getIntExtra("image", R.drawable.car1));
        commentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment.setText(commentEdit.getText().toString());
                commentEdit.setVisibility(View.GONE);
                ly.setVisibility(View.VISIBLE);
                comment.setVisibility(View.VISIBLE);
            }
        });

    }
}