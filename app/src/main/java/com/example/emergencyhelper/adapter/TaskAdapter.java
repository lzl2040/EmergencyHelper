package com.example.emergencyhelper.adapter;

import android.content.Context;
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
import com.example.emergencyhelper.activity.my.DingdanActivity;
import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.entity.MessageUserEntity;
import com.example.emergencyhelper.entity.TaskEntity;
import com.example.emergencyhelper.fragment.message.MessageFragment;
import com.example.emergencyhelper.util.DateUtils;
import com.example.emergencyhelper.util.StaticData;

import java.util.ArrayList;
import java.util.List;

/**
 * 别人发布的任务的适配器
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    //public static List<TaskEntity>tasks = new ArrayList<>();
    public static List<Task> tasks = new ArrayList<>();
    public static Context context;

    public TaskAdapter(List<Task> tasks,Context context){
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
        Task task = tasks.get(position);
        holder.getPos(position);
        holder.header.setImageResource(task.getPostUser().getHeaderId());
        holder.desc.setText(task.getContent());
        holder.name.setText(task.getPostUser().getUsername());
        holder.task_site.setText(task.getSite());
        holder.task_time.setText(task.getDeadline());
        holder.task_reward.setText(task.getReward()+"");
        holder.get_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setReceiveUser(StaticData.getCurUser());
                DingdanActivity.tasks.add(task);
                removeData(position);
                Toast.makeText(context,"领取成功",Toast.LENGTH_LONG).show();
            }
        });
        //点击聊一聊
//        holder.contactBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //需要头像,用户名,以及时间
//                int headerId = te.getHeader();
//                String name = te.getName();
//                //时间先暂时这样算
//                String timeStr = DateUtils.timeNum2String(System.currentTimeMillis());
//                String strs[] = timeStr.split(" ");
//                String time = strs[1];
//                MessageUserEntity entity = new MessageUserEntity(headerId,name,"",time);
//                MessageFragment.adapter.update(entity);
//            }
//        });
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
}
