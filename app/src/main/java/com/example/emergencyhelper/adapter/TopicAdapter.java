package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.MainActivity;
import com.example.emergencyhelper.activity.topic.CommentActivity;
import com.example.emergencyhelper.entity.TopicEntity;
import com.example.emergencyhelper.fragment.near.TopicFragment;

import java.util.ArrayList;
import java.util.List;

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
        holder.detail.setText(te.getDetail());
        holder.title.setText(te.getTitle());
        holder.comment_num.setText(te.getComment_num()+"");
        holder.see_num.setText(te.getSee_num()+"");
        holder.name.setText(te.getName());
        holder.header.setImageResource(te.getImg_id());
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
            topic_wra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(context, CommentActivity.class);
                    context.startActivity(in);
                }
            });
        }
    }
}
