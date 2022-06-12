package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.message.CommunicateActivity;
import com.example.emergencyhelper.bean.Communicate;
import com.example.emergencyhelper.bean.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageUserAdapter extends RecyclerView.Adapter<MessageUserAdapter.ViewHolder> {
    private List<Communicate> communicates = new ArrayList<>();
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView content;
        ImageView header;
        TextView time;
        LinearLayout communicateBoxLinear;
        public ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
            content = view.findViewById(R.id.content);
            header = view.findViewById(R.id.header);
            time = view.findViewById(R.id.message_time);
            communicateBoxLinear = view.findViewById(R.id.communicate_box);
        }
    }

    public MessageUserAdapter(Context context,List<Communicate> communicates){
        this.communicates = communicates;
        this.context = context;
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
        Communicate communicate = communicates.get(position);
        Glide.with(context).load(communicate.getAcceptUser().getImgUrl()).into(holder.header);
        //holder.header.setImageResource(communicate.getAcceptUser().getImgUrl());
        holder.name.setText(communicate.getAcceptUser().getName());
        holder.time.setText(communicate.getCommunicateDate());
        List<Message> messages = communicate.getMessages();
        if(messages.size() == 0){
            holder.content.setText("");
        }else{
            holder.content.setText(messages.get(messages.size() - 1).getContent());
        }

        holder.communicateBoxLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CommunicateActivity.class);
                intent.putExtra("index",position);
                intent.putExtra("communicate",communicate);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return communicates.size();
    }

    public void update(List<Communicate> communicateList){
        communicates.clear();
        communicates.addAll(communicateList);
        notifyDataSetChanged();
    }
}
