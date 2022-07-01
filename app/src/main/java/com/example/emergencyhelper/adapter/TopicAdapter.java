package com.example.emergencyhelper.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.message.CommunicateActivity;
import com.example.emergencyhelper.activity.topic.CommentActivity;
import com.example.emergencyhelper.bean.TopicEntity;
import com.example.emergencyhelper.requests.TopicRequest;
import com.example.emergencyhelper.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import okhttp3.Response;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
    private List<TopicEntity> topics = new ArrayList<>();
    private static Context context;
    public TopicAdapter(List<TopicEntity> topics, Context context1){
        context = context1;
        this.topics=topics;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_topic,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopicEntity te = topics.get(position);
        holder.detail.setText(te.getTitle());
        holder.title.setText(te.getTitle());
        holder.comment_num.setText(te.getCommentNum()+"");
        holder.see_num.setText(te.getViewNum()+"");
        holder.name.setText(te.getPostUserName());
        Glide.with(context).load(te.getPostImgUrl()).into(holder.header);
        holder.topic_wra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddViewNumTask().execute(te.getTopicId());
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("topic",te);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return topics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView header;
        private TextView see_num;
        private TextView comment_num;
        private TextView title;
        private TextView detail;
        private LinearLayout topic_wra;
        public ViewHolder(View v){
            super(v);
            name = v.findViewById(R.id.name);
            header = v.findViewById(R.id.header);
            see_num = v.findViewById(R.id.browser_num);
            comment_num = v.findViewById(R.id.comment_num);
            title = v.findViewById(R.id.desc);
            detail = v.findViewById(R.id.detail);
            topic_wra = v.findViewById(R.id.topic_wra);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class AddViewNumTask extends AsyncTask<Integer,Integer,Integer>{
        @SneakyThrows
        @Override
        protected Integer doInBackground(Integer... integers) {
            Response response = new TopicRequest().updateViewNum(integers[0]);
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
                    //发送广播更新topics列表
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }
}
