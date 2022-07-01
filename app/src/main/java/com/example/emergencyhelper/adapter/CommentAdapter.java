package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.bean.TopicEntity;
import com.example.emergencyhelper.entity.CommentEntity;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static Context context;
    private List<CommentEntity> comments = new ArrayList<>();
    private TopicEntity topic;

    public CommentAdapter(Context context1, List<CommentEntity> comments,TopicEntity topic){
        context =context1;
        this.comments=comments;
        this.topic = topic;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){
            View v = LayoutInflater.from(context).inflate(R.layout.item_comment_top,parent,false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }
        else if(viewType==1){
            View v = LayoutInflater.from(context).inflate(R.layout.item_topic_comment,parent,false);
            ViewHolder2 vh = new ViewHolder2(v);
            return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0){
            Glide.with(context).load(topic.getPostImgUrl()).into(((ViewHolder)holder).header);
            ((ViewHolder) holder).content.setText(topic.getTitle());
            String time = topic.getPostTime();
//            String splits[] = time.split(".");
//            time = splits[0];
            ((ViewHolder) holder).time.setText(time);
            ((ViewHolder) holder).name.setText(topic.getPostUserName());
            ((ViewHolder) holder).commentNumTxt.setText(topic.getCommentNum() + "");
            ((ViewHolder) holder).viewNumTxt.setText(topic.getViewNum() + "");
        }
        else{
            CommentEntity ce = comments.get(position - 1);
            Glide.with(context).load(ce.getPostImgUrl()).into( ((ViewHolder2) holder).header);
            ((ViewHolder2) holder).name.setText(ce.getPostUserName());
            String time = ce.getPostTime();
            ((ViewHolder2) holder).time.setText(time);
            ((ViewHolder2) holder).content.setText(ce.getContent());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return position;
        }
        else{
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return comments.size() + 1;
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView time;
        private TextView content;
        private ImageView header;
        public ViewHolder2(View v){
            super(v);
            name =v.findViewById(R.id.name);
            time = v.findViewById(R.id.time);
            content = v.findViewById(R.id.content);
            header = v.findViewById(R.id.header);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView content;
        private ImageView header;
        private TextView time;
        private TextView viewNumTxt;
        private TextView commentNumTxt;
        public ViewHolder(View v){
            super(v);
            name = v.findViewById(R.id.post_name);
            content = v.findViewById(R.id.title);
            header = v.findViewById(R.id.post_header);
            time = v.findViewById(R.id.time);
            viewNumTxt = v.findViewById(R.id.browser_num);
            commentNumTxt = v.findViewById(R.id.comment_num);
        }
    }
}
