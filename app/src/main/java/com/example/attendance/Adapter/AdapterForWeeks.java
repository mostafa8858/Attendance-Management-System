package com.example.attendance.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendance.Domin.Week;
import com.example.attendance.Listener.RecyclerViewOnClickListenerForWeek;
import com.example.attendance.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterForWeeks extends RecyclerView.Adapter<AdapterForWeeks.ClassHolder> {
    private ArrayList<Week> weeks;
    private RecyclerViewOnClickListenerForWeek recyclerViewOnClickListenerForWeek;

    public AdapterForWeeks(ArrayList<Week> weeks, RecyclerViewOnClickListenerForWeek recyclerViewOnClickListenerForWeek) {
        this.weeks = weeks;
        this.recyclerViewOnClickListenerForWeek = recyclerViewOnClickListenerForWeek;
    }


    public void updateData(ArrayList<Week> weeks) {
        this.weeks.clear();
        this.weeks = weeks;
    }

    @NonNull
    @NotNull
    @Override

    public ClassHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customweeksforadpter, null, false);
        ClassHolder classHolder = new ClassHolder(view);
        return classHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterForWeeks.ClassHolder holder, int position) {
        Week week = this.weeks.get(position);
        holder.textViewWeeksAdapter.setText(week.getWeekName());
        holder.week = weeks.get(position);
    }

    @Override
    public int getItemCount() {
        return weeks.size();
    }

    public class ClassHolder extends RecyclerView.ViewHolder {
        ImageView imageViewWeeksAdapter;
        TextView textViewWeeksAdapter;
        Week week;

        public ClassHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageViewWeeksAdapter = itemView.findViewById(R.id.image_in_adatpterweeks);
            textViewWeeksAdapter = itemView.findViewById(R.id.text_in_custom_room);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    recyclerViewOnClickListenerForWeek.onClick(week);
                }
            });

        }
    }
}
