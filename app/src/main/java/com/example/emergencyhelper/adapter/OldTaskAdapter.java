package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.emergencyhelper.activity.message.CommunicateActivity;
import com.example.emergencyhelper.activity.my.DingdanActivity;
import com.example.emergencyhelper.bean.Communicate;
import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.bean.User;
import com.example.emergencyhelper.bean.UserAndTaskCategory;
import com.example.emergencyhelper.fragment.home.HomePageFragment;
import com.example.emergencyhelper.util.DateUtils;
import com.example.emergencyhelper.util.StaticData;

import java.util.ArrayList;
import java.util.List;

/**
 * author ： yxm521
 * time    ： 2022/4/25
 */
public class OldTaskAdapter extends RecyclerView.Adapter<OldTaskAdapter.Holder> {
    private String TAG = "OldTaskAdapter";
    public static List<Task> tasks = new ArrayList<>();
    public static Context context;

    public OldTaskAdapter(List<Task> tasks,Context context) {
        this.context=context;
        this.tasks=tasks;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.old_item_task,parent,false);
        Holder viewHolder = new Holder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Task task = tasks.get(position);
        holder.getPos(position);
        holder.header.setImageResource(task.getPostUser().getHeaderId());
        holder.desc.setText(task.getContent());
        holder.name.setText(task.getPostUser().getName());
        holder.task_site.setText(task.getSite());
        holder.task_time.setText(task.getDeadline());
        holder.task_reward.setText(task.getReward()+"");
        holder.get_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更新用户对于该分类的领取次数
                int index = task.getCategory().getCategoryId() - 1;
                UserAndTaskCategory userAndTaskCategory = StaticData.getUserAndTaskCategories().get(index);
                int num = userAndTaskCategory.getNum();
                num = num + 1;
                userAndTaskCategory.setNum(num);
                List<UserAndTaskCategory> categories = StaticData.getUserAndTaskCategories();
                categories.set(index,userAndTaskCategory);
                StaticData.setUserAndTaskCategories(categories);
                task.setReceiveUser(StaticData.getCurUser());
                DingdanActivity.tasks.add(task);
                removeData(position);
                HomePageFragment.dragBtn.setImageResource(R.mipmap.stop_record);
                Toast.makeText(context,"领取成功",Toast.LENGTH_LONG).show();
            }
        });

        //点击聊一聊
        holder.contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = checkIsSameCommunication(StaticData.getCurUser(),task.getPostUser());
                List<Communicate> communicates = StaticData.getCommunicates();
                StaticData.setJumpClass(context.getClass());
                if(index != -1){
                    //说明之前聊过天
                    Intent intent = new Intent(context, CommunicateActivity.class);
                    intent.putExtra("index",index);
                    intent.putExtra("communicate",communicates.get(index));
                    context.startActivity(intent);
                }else{
                    //之前没有聊过天
                    Log.e(TAG,"之前没有聊过");
                    //时间只取时和分
                    String timeStr = DateUtils.timeNum2String(System.currentTimeMillis());
                    String strs[] = timeStr.split(" ");
                    String time = strs[1];
                    //MessageUserEntity entity = new MessageUserEntity(headerId,name,"",time);
                    //存储这个新的聊天信息
                    Communicate data = new Communicate(StaticData.getCurUser(),task.getPostUser(),time);
                    communicates.add(data);
                    StaticData.setCommunicates(communicates);
                    Intent intent = new Intent(context, CommunicateActivity.class);
                    intent.putExtra("index",communicates.size()-1);
                    intent.putExtra("communicate",data);
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void removeData(int pos){
        tasks.remove(pos);
        notifyItemRemoved(pos);
        notifyDataSetChanged();
    }

    /**
     * 检查是否是同一个交流
     * @param start
     * @param receive
     * @return 返回下标
     */
    public int checkIsSameCommunication(User start, User receive){
        int counts = 0;
        List<Communicate> communicates = StaticData.getCommunicates();
        for(Communicate communicate:communicates){
            String phone1 = communicate.getStartUser().getPhone();
            String phone2 = communicate.getAcceptUser().getPhone();
            if(start.getPhone().equals(phone1) && receive.getPhone().equals(phone2)){
                return counts;
            }
            counts++;
        }
        return -1;
    }

    static class Holder extends RecyclerView.ViewHolder{
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
        public Holder(View v){
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
