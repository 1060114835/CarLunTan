package com.chen.myapplication.ui.home;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.myapplication.DateUtil;
import com.chen.myapplication.MainActivity2;
import com.chen.myapplication.R;
import com.chen.myapplication.db.CarDataBase;
import com.chen.myapplication.ui.login.CurrentUser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private MainActivity2 activity2;
    private SQLiteDatabase database;
    private List<InfoBean> data = new ArrayList<>();
    private FloatingActionButton fab;
    private int[] drawables = new int[]{R.drawable.car1,
            R.drawable.car2, R.drawable.car3, R.drawable.car4, R.drawable.car5};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final EditText view = (EditText) inflater.inflate(R.layout.edittext, container, false);
        recyclerView = root.findViewById(R.id.rc_carInfo);
        activity2 = (MainActivity2) getActivity();
        if (activity2 != null) {
            database = activity2.mSQLiteDatabase;
        }
        initView();
        fab = root.findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final Adapter adapter = new Adapter(data, drawables, getActivity());
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("请输入您要发表的内容").setView(view).setNegativeButton(
                        "取消", null);
                builder.setPositiveButton("确认发布",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String inputName = view.getText().toString();
                                InfoBean infoBean = new InfoBean(CurrentUser.name, DateUtil.getCurrentDate(), inputName);
                                data.add(0, infoBean);
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("name", infoBean.getName());
                                contentValues.put("date", infoBean.getDate());
                                contentValues.put("grade", infoBean.getIntroduction());
                                SQLiteDatabase sqLiteDatabase = CarDataBase.getInstance(getActivity().
                                        getApplicationContext(), "CarDetail.db").getReadableDatabase();
                                sqLiteDatabase.insert("car", null, contentValues);
                                adapter.notifyDataSetChanged();
                            }

                        });
                builder.show();
            }
        });
        return root;
    }

    private void initView() {
        Cursor cursor = database.query("car", null,
                null, null, null, null, null);
        int size = cursor.getCount();
        cursor.moveToLast();
        for (int i = 0; i < size; i++) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String introduction = cursor.getString(cursor.getColumnIndex("grade"));
            data.add(new InfoBean(name, date, introduction));
            cursor.moveToPrevious();
        }
        cursor.close();
    }
}