package com.chen.myapplication.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.myapplication.CommentActivity;
import com.chen.myapplication.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    Activity activity;
    List<InfoBean> data;
    int[] drawables;
    Random random = new Random();


    public Adapter(List<InfoBean> data, int[] drawables, Activity activity) {
        this.data = data;
        this.drawables = drawables;
        this.activity = activity;
        Log.d("chendata", "Adapter: " + Arrays.toString(data.toArray()));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final int temp = random.nextInt(5);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CommentActivity.class);
                intent.putExtra("name", data.get(position).getName());
                intent.putExtra("date", data.get(position).getDate());
                intent.putExtra("introduction", data.get(position).getIntroduction());
                intent.putExtra("image", drawables[temp]);
                activity.startActivity(intent);
            }
        });
        holder.name.setText(data.get(position).getName());
        holder.date.setText(data.get(position).getDate());
        holder.introduction.setText(data.get(position).getIntroduction());
        holder.imageView.setImageResource(drawables[temp]);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView date;
        TextView introduction;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            introduction = itemView.findViewById(R.id.introduction);
            imageView = itemView.findViewById(R.id.imageView2);
        }
    }
}
