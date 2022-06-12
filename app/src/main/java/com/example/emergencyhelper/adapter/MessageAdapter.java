package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.emergencyhelper.bean.Message;

import java.util.List;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.ui.RoundImage;

/**
 * author ： yxm521
 * time    ： 2022/3/29
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private Context context;
    private List<Message> messages;

    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == 0){
            //发送消息
            view = LayoutInflater.from(context).inflate(R.layout.item_communicate_send_message,parent,false);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.item_communicate_receive_message,parent,false);
        }
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.contentTxt.setText(message.getContent());
        Glide.with(context).load(message.getSendUser().getImgUrl()).into(holder.headerImg);
        //holder.headerImg.setImageResource(message.getSendUser().getImgUrl());
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getMsgType();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        RoundImage headerImg;
        TextView contentTxt;
        public ViewHolder(View v){
            super(v);
            headerImg = v.findViewById(R.id.user_header);
            contentTxt = v.findViewById(R.id.message_content);
        }
    }

    public void update(Message message){
        messages.add(message);
        notifyDataSetChanged();
    }
}
