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
import com.example.emergencyhelper.entity.HelperEntity;

import java.util.ArrayList;
import java.util.List;

public class HelperAdaper extends RecyclerView.Adapter<HelperAdaper.ViewHolder> {
    List<HelperEntity>helpers = new ArrayList<>();
    Context context;
    public HelperAdaper(List<HelperEntity>tasks, Context context){
        this.context=context;
        this.helpers=tasks;
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
        HelperEntity te = helpers.get(position);
        holder.header.setImageResource(te.getHeader());
        holder.desc.setText(te.getDesc());
        holder.name.setText(te.getName());
        holder.time.setText(te.getTime());
        holder.site.setText(te.getSite());
        holder.site.setText(te.getSite());
    }

    @Override
    public int getItemCount() {
        return helpers.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView desc;
        ImageView header;
        TextView time;
        TextView site;
        TextView reward;
        public ViewHolder(View v){
            super(v);
            name = v.findViewById(R.id.name);
            desc = v.findViewById(R.id.task_desc);
            header = v.findViewById(R.id.header);
            time=v.findViewById(R.id.task_time);
            site=v.findViewById(R.id.task_site);
            reward=v.findViewById(R.id.task_reward);
        }
    }
}
