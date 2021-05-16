package com.example.attendance.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendance.Domin.Room;
import com.example.attendance.Domin.WeeksModel;
import com.example.attendance.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterForWeeks extends RecyclerView.Adapter<AdapterForWeeks.ClassHolder> {
    private ArrayList<WeeksModel> week;

    public AdapterForWeeks(ArrayList<WeeksModel> week) {
        this.week = week;
    }

    @NonNull
    @NotNull
    @Override

    public ClassHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customweeksforadpter,null,false);
      ClassHolder classHolder=new ClassHolder(view);
      return classHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterForWeeks.ClassHolder holder, int position) {
              WeeksModel weeksModel=week.get(position);
              holder.textViewWeeksAdapter.setText(weeksModel.getWeekNumber());


    }

    @Override
    public int getItemCount() {
        return week.size();
    }

    public class  ClassHolder extends RecyclerView.ViewHolder{
        ImageView imageViewWeeksAdapter;
        TextView textViewWeeksAdapter;
        public ClassHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageViewWeeksAdapter=itemView.findViewById(R.id.image_in_adatpterweeks);
            textViewWeeksAdapter=itemView.findViewById(R.id.text_in_custom_room);
        }
    }
}
