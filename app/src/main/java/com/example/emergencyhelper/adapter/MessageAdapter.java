package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.entity.MessageEntity;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<MessageEntity> mMessagesList = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView content;
        ImageView header;
        TextView time;
        public ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
            content = view.findViewById(R.id.content);
            header = view.findViewById(R.id.header);
            time = view.findViewById(R.id.message_time);
        }
    }
    public MessageAdapter(List<MessageEntity> messages){
        mMessagesList=messages;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessageEntity me = mMessagesList.get(position);
        holder.header.setImageResource(me.getHeader());
        holder.content.setText(me.getContent());
        holder.name.setText(me.getName());
        holder.time.setText(me.getTime());
    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }

}
