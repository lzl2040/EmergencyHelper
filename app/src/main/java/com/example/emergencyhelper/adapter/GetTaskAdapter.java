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

import com.bumptech.glide.Glide;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.bean.TaskEntity;
import com.example.emergencyhelper.util.DateUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 领取任务后任务的适配器
 * author ： yxm521
 * time    ： 2022/3/28
 */
public class GetTaskAdapter extends RecyclerView.Adapter<GetTaskAdapter.ViewHolder> {
    private Context context;
    //private List<TaskEntity> tasks;
    private List<TaskEntity> tasks;

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
        //TaskEntity task = tasks.get(position);
        TaskEntity task = tasks.get(position);
        holder.nameTxt.setText(task.getReleaseUsername());
        Glide.with(context).load(task.getReleaseImgUrl()).into(holder.headerImg);
        holder.taskRewardTxt.setText(task.getTaskReward()+"");
        holder.descTxt.setText(task.getTaskContent());
        holder.taskSiteTxt.setText(task.getTaskSite());
        String deadline[] = task.getTaskDeadline().split(":");
        holder.taskDeadLineTxt.setText(deadline[0]+":"+deadline[1]);
        holder.setTimer(task.getTaskDeadline());
    }

    public void updateData(List<TaskEntity> taskEntities){
        tasks.clear();
        tasks.addAll(taskEntities);
        notifyDataSetChanged();
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
        //用于倒计时的
        private Timer timer;
        //消息队列接收
        private Handler handler = new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 100:
                        long interval = (Long) msg.obj;
                        if(interval < 0){
                            handler.removeMessages(100);
                            countDownTxt.setText(R.string.task_have_stop);
                            break;
                        }
                        String intervalStr = DateUtils.calInterval(interval);
                        countDownTxt.setText(intervalStr);
                        break;
                    default:
                        break;
                }
            }
        };
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

        /**
         * 设计每个item的倒计时
         */
        public void setTimer(String deadline){
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Message message = Message.obtain();
                    message.what = 100;
                    long nowTime = System.currentTimeMillis();
                    long endTime = DateUtils.string2TimeNum(deadline);
                    message.obj = endTime - nowTime;
                    handler.sendMessage(message);
                }
            };
            timer.schedule(timerTask,1,1000);
        }
    }
}
