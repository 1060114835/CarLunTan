package com.chen.myapplication.ui.dashboard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.myapplication.ChangeActivity;
import com.chen.myapplication.MainActivity2;
import com.chen.myapplication.R;
import com.chen.myapplication.ui.home.Adapter;
import com.chen.myapplication.ui.home.InfoBean;
import com.chen.myapplication.ui.login.CurrentUser;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private TextView name;
    private TextView age;
    private TextView location;
    private TextView interest;
    private TextView school;
    private TextView introduction;
    private Button change;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        name = root.findViewById(R.id.tv_1);
        age = root.findViewById(R.id.tv_2);
        location = root.findViewById(R.id.tv_3);
        interest = root.findViewById(R.id.tv_4);
        school = root.findViewById(R.id.tv_5);
        introduction = root.findViewById(R.id.tv_6);
        change = root.findViewById(R.id.change);
        name.setText(CurrentUser.name);
        age.setText(CurrentUser.age);
        location.setText(CurrentUser.location);
        interest.setText(CurrentUser.interist);
        school.setText(CurrentUser.school);
        introduction.setText(CurrentUser.introduction);
        change = root.findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        name.setText(CurrentUser.name);
        age.setText(CurrentUser.age);
        location.setText(CurrentUser.location);
        interest.setText(CurrentUser.interist);
        school.setText(CurrentUser.school);
        introduction.setText(CurrentUser.introduction);
    }
}