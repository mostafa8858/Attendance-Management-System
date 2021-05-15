package com.example.attendance.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.attendance.Domin.Room;
import com.example.attendance.R;

import java.util.ArrayList;

public class AdapterForRooms extends RecyclerView.Adapter<AdapterForRooms.RoomHolder> {

    private ArrayList<Room> rooms;

    public AdapterForRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void updateData(ArrayList<Room> rooms) {
      this.  rooms.clear();
        this.rooms=rooms;
    }

    @Override
    public RoomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_room_view, null, false);
        RoomHolder roomHolder = new RoomHolder(view);
        return roomHolder;
    }

    @Override
    public void onBindViewHolder(AdapterForRooms.RoomHolder holder, int position) {

        Room room = rooms.get(position);

        if (room.getRoomImageUri() != null) {
            holder.imageView.setImageURI(room.getRoomImageUri());
        }

        holder.textView.setText(room.getRoomTitle());


    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public class RoomHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public RoomHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_in_custom_room);
            imageView = itemView.findViewById(R.id.image_in_custom_room);
        }
    }
}
