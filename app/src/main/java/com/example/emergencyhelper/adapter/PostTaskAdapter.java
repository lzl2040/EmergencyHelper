package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.entity.TaskEntity;
import com.example.emergencyhelper.util.StaticData;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.adapter.simple.XUISimpleAdapter;
import com.xuexiang.xui.widget.popupwindow.popup.XUISimplePopup;

import java.util.ArrayList;
import java.util.List;

/**
 * 我发布的任务的适配器
 */
public class PostTaskAdapter extends RecyclerView.Adapter<PostTaskAdapter.ViewHolder> {
    public Context context;
    public List<Task> taskes = new ArrayList<>();
    //public List<TaskEntity> taskes = new ArrayList<>();

    public PostTaskAdapter(Context context, List<Task> tasks){
        this.context=context;
        this.taskes=tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_post_task,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //TaskEntity te = taskes.get(position);
        Task task = taskes.get(position);
        holder.getPos(position);
        holder.desc.setText(task.getContent());
        holder.task_site.setText(task.getSite());
        holder.task_time.setText(task.getDeadline());
        holder.pay.setText(task.getReward()+"");
//        holder.desc.setText(te.getDesc());
//        holder.task_site.setText(te.getSite());
//        holder.task_time.setText(te.getDeadline());
//        holder.pay.setText(te.getReward()+"币/次");
        int curPos = position;
        holder.moreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XUISimplePopup mMenuPopup = new XUISimplePopup(context, StaticData.menuItems)
                        .create(new XUISimplePopup.OnPopupItemClickListener() {
                            @Override
                            public void onItemClick(XUISimpleAdapter adapter, AdapterItem item, int position) {
                                if(position == 0){
                                    //确认任务
                                }else if(position == 1){
                                    //取消任务
                                    taskes.remove(curPos);
                                    updateData(taskes);
                                }
                            }
                        });
                mMenuPopup.showDown(view);
            }
        });
    }

    /**
     * 更新适配器
     * @param entities
     */
    public void updateData(List<Task> entities){
        taskes.clear();
        taskes.addAll(entities);
        notifyDataSetChanged();
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
        ImageView moreImg;
        LinearLayout viewMore;
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
            moreImg = v.findViewById(R.id.more);
            viewMore = v.findViewById(R.id.view_more);
        }
    }

}
