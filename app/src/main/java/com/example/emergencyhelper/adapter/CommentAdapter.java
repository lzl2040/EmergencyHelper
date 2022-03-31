package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.entity.CommentEntity;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static Context context;
    private List<CommentEntity> comments = new ArrayList<>();

    public CommentAdapter(Context context1, List<CommentEntity> comments){
        context =context1;
        this.comments=comments;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==1){
            View v = LayoutInflater.from(context).inflate(R.layout.item_comment_top,parent,false);
            ViewHolder2 vh = new ViewHolder2(v);
            return vh;
        }
        else if(viewType==2){
            View v = LayoutInflater.from(context).inflate(R.layout.item_topic_comment,parent,false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CommentEntity ce = comments.get(position);
        if (ce.getType()==2){
            ((ViewHolder) holder).header.setImageResource(ce.getImg_id());
            ((ViewHolder) holder).content.setText(ce.getContent());
            ((ViewHolder) holder).time.setText(ce.getTime());
            ((ViewHolder) holder).name.setText(ce.getName());
        }
        else if (ce.getType()==1){
            ((ViewHolder2) holder).header.setImageResource(ce.getPoster_header());
            ((ViewHolder2) holder).name.setText(ce.getPoster_name());
            ((ViewHolder2) holder).time.setText(ce.getPage_post_time());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return comments.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView time;
        private TextView content;
        private ImageView header;
        public ViewHolder(View v){
            super(v);
            name =v.findViewById(R.id.name);
            time = v.findViewById(R.id.time);
            content = v.findViewById(R.id.content);
            header = v.findViewById(R.id.header);
        }
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView content;
        private ImageView header;
        private TextView time;
        public ViewHolder2(View v){
            super(v);
            name = v.findViewById(R.id.post_name);
            content = v.findViewById(R.id.content);
            header = v.findViewById(R.id.post_header);
            time = v.findViewById(R.id.time);
        }
    }
}
