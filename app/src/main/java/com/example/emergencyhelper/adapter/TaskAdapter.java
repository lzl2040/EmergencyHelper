package com.example.emergencyhelper.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.bean.Communicate;
import com.example.emergencyhelper.bean.TaskEntity;
import com.example.emergencyhelper.bean.User;
import com.example.emergencyhelper.requests.TaskRequest;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import okhttp3.Response;

/**
 * 别人发布的任务的适配器
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private String TAG = "TaskAdapter";
    private List<TaskEntity> tasks = new ArrayList<>();
    private Context context;
    private int categoryId,taskId;
    private int receivePosition;

    public TaskAdapter(List<TaskEntity> tasks, Context context){
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
        //TaskEntity te = tasks.get(position);
        TaskEntity task = tasks.get(position);
        holder.getPos(position);

        Glide.with(context).load(task.getReleaseImgUrl()).into(holder.header);
        //holder.header.setImageResource(task.getPostUser().getHeaderId());
        holder.desc.setText(task.getTaskContent());
        holder.name.setText(task.getReleaseUsername());
        holder.task_site.setText(task.getTaskSite());

        String deadline[] = task.getTaskDeadline().split(":");
        holder.task_time.setText(deadline[0]+":"+deadline[1]);

        holder.task_reward.setText(task.getTaskReward()+"");
        holder.get_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryId = task.getCategoryId();
                taskId = task.getTaskId();
                Log.e(TAG,"分类ID为:"+categoryId);
                receivePosition = position;
                new ReceiveTaskTask().execute(StaticData.getCurUser().getPhone());
            }
        });

        //点击聊一聊
        holder.contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void removeData(int pos){
        tasks.remove(pos);
        notifyItemRemoved(pos);
        notifyDataSetChanged();
    }

    public void updateData(List<TaskEntity> taskList){
        tasks.clear();
        tasks.addAll(taskList);
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
        Button contactBtn;
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
            contactBtn = v.findViewById(R.id.contact);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ReceiveTaskTask extends AsyncTask<String,Integer,Integer>{
        @SneakyThrows
        @Override
        protected Integer doInBackground(String... strings) {
            Response response = new TaskRequest().receiveTask(StaticData.getCurUser().getPhone(),taskId,categoryId);
            if(response == null){
                return -1;
            }
            return response.code();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            String msg = "";
            switch (integer){
                case -1:
                    msg = "请检查网络状态后重新尝试！";
                    ViewUtil.showErrorToast(msg,context);
                    break;
                case 401:
                    msg = "领取失败!";
                    ViewUtil.showErrorToast(msg,context);
                    break;
                case 200:
                    tasks.remove(receivePosition);
                    notifyDataSetChanged();
                    ViewUtil.showNotice("领取成功",context);
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }
}
