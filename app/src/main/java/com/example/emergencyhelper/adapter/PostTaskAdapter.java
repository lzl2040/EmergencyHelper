package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.DingdanActivity;
import com.example.emergencyhelper.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 我发布的任务的适配器
 */
public class PostTaskAdapter extends RecyclerView.Adapter<PostTaskAdapter.ViewHolder> {
    public Context context;
    public List<TaskEntity> taskes = new ArrayList<>();
    public PostTaskAdapter(Context context, List<TaskEntity> tasks){
        this.context=context;
        this.taskes=tasks;
    }@NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_post_task,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskEntity te = taskes.get(position);
        holder.getPos(position);
        holder.desc.setText(te.getDesc());
        holder.task_site.setText(te.getSite());
        holder.task_time.setText(te.getDeadline());
        holder.pay.setText(te.getReward()+"元/次");
    }

    @Override
    public int getItemCount() {
        return taskes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView desc;
        TextView task_time;
        TextView task_site;
        Button get_task;
        TextView pay;
        int pos;

        public void getPos(int position){
            this.pos = position;
        }

        public ViewHolder(View v){
            super(v);
            desc = v.findViewById(R.id.task_desc);
            task_time = v.findViewById(R.id.task_time);
            task_site = v.findViewById(R.id.task_site);
            pay = v.findViewById(R.id.task_reward);
        }
    }

}
