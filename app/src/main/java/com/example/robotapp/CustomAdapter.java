package com.example.robotapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList student_id, student_name, student_data;

    CustomAdapter(Activity activity, Context context, ArrayList student_id, ArrayList student_name,
                  ArrayList student_data){
        this.activity = activity;
        this.context = context;
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_data = student_data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.student_data_txt.setText(String.valueOf(student_id.get(position)));
        holder.student_name_txt.setText(String.valueOf(student_name.get(position)));
        holder.student_data_txt.setText(String.valueOf(student_data.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateData.class);
                intent.putExtra("id", String.valueOf(student_id.get(position)));
                intent.putExtra("name", String.valueOf(student_name.get(position)));
                intent.putExtra("data", String.valueOf(student_data.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return student_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView student_id_txt, student_name_txt, student_data_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id_txt = itemView.findViewById(R.id.student_id_txt);
            student_name_txt = itemView.findViewById(R.id.student_name_txt);
            student_data_txt = itemView.findViewById(R.id.student_data_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}