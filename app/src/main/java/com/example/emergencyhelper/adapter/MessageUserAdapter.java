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

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.message.CommunicateActivity;
import com.example.emergencyhelper.entity.MessageUserEntity;

import java.util.ArrayList;
import java.util.List;

public class MessageUserAdapter extends RecyclerView.Adapter<MessageUserAdapter.ViewHolder> {

    private List<MessageUserEntity> mMessagesList = new ArrayList<>();
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
    public MessageUserAdapter(Context context,List<MessageUserEntity> messages){
        this.mMessagesList=messages;
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
        MessageUserEntity me = mMessagesList.get(position);
        holder.header.setImageResource(me.getHeader());
        holder.content.setText(me.getContent());
        holder.name.setText(me.getName());
        holder.time.setText(me.getTime());
        holder.communicateBoxLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CommunicateActivity.class);
                intent.putExtra("communicateName",holder.name.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }

}
