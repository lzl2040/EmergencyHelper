package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.entity.TaskEntity;

import java.util.List;

/**
 * 领取任务后任务的适配器
 * author ： yxm521
 * time    ： 2022/3/28
 */
public class GetTaskAdapter extends RecyclerView.Adapter<GetTaskAdapter.ViewHolder> {
    private Context context;
    private List<TaskEntity> tasks;
    //实现倒计时
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    public GetTaskAdapter(Context context, List<TaskEntity> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_get_task,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskEntity task = tasks.get(position);
        holder.nameTxt.setText(task.getName());
        holder.headerImg.setImageResource(task.getHeader());
        holder.taskDeadLineTxt.setText(task.getDeadline());
        holder.taskRewardTxt.setText(task.getReward());
        holder.descTxt.setText(task.getDesc());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView countDownTxt;
        TextView nameTxt;
        TextView descTxt;
        ImageView headerImg;
        TextView taskDeadLineTxt;
        TextView taskSiteTxt;
        TextView taskRewardTxt;
        public ViewHolder(View view){
            super(view);
            countDownTxt = view.findViewById(R.id.countdown);
            nameTxt = view.findViewById(R.id.name);
            descTxt = view.findViewById(R.id.task_desc);
            headerImg = view.findViewById(R.id.header);
            taskDeadLineTxt = view.findViewById(R.id.task_time);
            taskSiteTxt = view.findViewById(R.id.task_site);
            taskRewardTxt = view.findViewById(R.id.task_reward);
        }
    }
}
