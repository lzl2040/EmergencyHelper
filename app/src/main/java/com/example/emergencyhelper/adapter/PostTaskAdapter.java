package com.example.emergencyhelper.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.my.PostActivity;
import com.example.emergencyhelper.bean.TaskEntity;
import com.example.emergencyhelper.requests.TaskRequest;
import com.example.emergencyhelper.ui.RoundImage;
import com.example.emergencyhelper.util.CheckUtil;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.adapter.simple.XUISimpleAdapter;
import com.xuexiang.xui.widget.popupwindow.popup.XUISimplePopup;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import okhttp3.Response;

/**
 * 我发布的任务的适配器
 */
public class PostTaskAdapter extends RecyclerView.Adapter<PostTaskAdapter.ViewHolder> {
    private Context context;
    private List<TaskEntity> taskes = new ArrayList<>();

    public PostTaskAdapter(Context context, List<TaskEntity> tasks){
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
        TaskEntity task = taskes.get(position);
        holder.getPos(position);
        holder.desc.setText(task.getTaskContent());
        holder.task_site.setText(task.getTaskSite());
        String deadline[] = task.getTaskDeadline().split(":");
        holder.task_time.setText(deadline[0]+":"+deadline[1]);
        holder.pay.setText(task.getTaskReward()+"");
        if(task.getIsComplete() == 1){
            holder.moreImg.setVisibility(View.GONE);
            holder.completeImg.setVisibility(View.VISIBLE);
        }
        if(task.getReceivePhone() == null || task.getReceivePhone().length() == 0){
            holder.receiveUserNameTxt.setText(R.string.blank_string);
            holder.receiveUserImg.setVisibility(View.INVISIBLE);
        }else{
            Glide.with(context).load(task.getReceiveImgUrl()).into(holder.receiveUserImg);
            holder.receiveUserNameTxt.setText(task.getReceiveUsername());
        }
        int curPos = position;
        holder.moreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XUISimplePopup mMenuPopup = new XUISimplePopup(context, StaticData.menuItems)
                        .create(new XUISimplePopup.OnPopupItemClickListener() {
                            @Override
                            public void onItemClick(XUISimpleAdapter adapter, AdapterItem item, int position) {
                                if(position == 0){
                                    //确认完成任务
                                    if(!CheckUtil.checkStringNull(task.getReceivePhone()))
                                        new ConfirmTask().startExeute(holder,task);
                                    else {
                                        ViewUtil.showErrorToast("目前无人领取,无法确认完成",context);
                                    }
                                }else if(position == 1){
                                    //本地更新适配器
                                    taskes.remove(curPos);
                                    notifyDataSetChanged();
                                    Intent intent = new Intent("cancelTask");
                                    intent.putExtra("removePos",curPos);
                                    context.sendBroadcast(intent);
                                    //取消任务
                                    new CancelTask().execute(task.getTaskId());
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
    public void updateData(List<TaskEntity> entities){
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
        ImageView completeImg;
        RoundImage receiveUserImg;
        TextView receiveUserNameTxt;
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
            completeImg = v.findViewById(R.id.complete);
            receiveUserImg = v.findViewById(R.id.get_task_user_header);
            receiveUserNameTxt = v.findViewById(R.id.get_task_user_name);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ConfirmTask extends AsyncTask<TaskEntity,Integer,Integer>{
        private ViewHolder holder;
        public void startExeute(ViewHolder holder,TaskEntity taskEntity){
            this.holder = holder;
            execute(taskEntity);
        }

        @SneakyThrows
        @Override
        protected Integer doInBackground(TaskEntity... entities) {
            Response response = new TaskRequest().confirmComplete(entities[0].getTaskId(),entities[0].getReceivePhone(),entities[0].getTaskReward());
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
                    ViewUtil.showErrorToast(msg, context);
                    break;
                case 200:
                    msg = "确认完成!";
                    holder.completeImg.setVisibility(View.VISIBLE);
                    holder.moreImg.setVisibility(View.GONE);
                    ViewUtil.showNotice(msg,context);
                    break;
                case 401:
                    msg = "产生冲突!";
                    ViewUtil.showErrorToast(msg,context);
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class CancelTask extends AsyncTask<Integer,Integer,Integer>{

        @Override
        protected Integer doInBackground(Integer... integers) {
            Response response = new TaskRequest().cancelTask(integers[0]);
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
                    ViewUtil.showErrorToast(msg, context);
                    break;
                case 200:
                    msg = "取消成功!";
                    ViewUtil.showNotice(msg,context);
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }
}
