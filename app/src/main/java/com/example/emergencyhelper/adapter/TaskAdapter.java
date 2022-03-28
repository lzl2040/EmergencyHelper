package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.DingdanActivity;
import com.example.emergencyhelper.entity.TaskEntity;
import com.example.emergencyhelper.fragment.HomePageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 别人发布的任务的适配器
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    public static List<TaskEntity>tasks = new ArrayList<>();
    public static Context context;

    public TaskAdapter(List<TaskEntity>tasks,Context context){
        this.context=context;
        this.tasks=tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_task,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskEntity te = tasks.get(position);
        holder.getPos(position);
        holder.header.setImageResource(te.getHeader());
        holder.desc.setText(te.getDesc());
        holder.name.setText(te.getName());
        holder.task_site.setText(te.getSite());
        holder.task_time.setText(te.getDeadline());
        holder.task_reward.setText(te.getReward());
        holder.get_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DingdanActivity.tasks.add(tasks.get(position));
                removeData(position);
                Toast.makeText(context,"领取成功",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void removeData(int pos){
        tasks.remove(pos);
        notifyItemRemoved(pos);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView desc;
        ImageView header;
        TextView task_time;
        TextView task_site;
        TextView task_reward;
        Button get_task;
        int pos;
        public void getPos(int position){
            this.pos = position;
        }
        public ViewHolder(View v){
            super(v);
            name = v.findViewById(R.id.name);
            desc = v.findViewById(R.id.task_desc);
            header = v.findViewById(R.id.header);
            get_task = v.findViewById(R.id.get_task);
            task_time = v.findViewById(R.id.task_time);
            task_site = v.findViewById(R.id.task_site);
            task_reward = v.findViewById(R.id.task_reward);
        }
    }
}
