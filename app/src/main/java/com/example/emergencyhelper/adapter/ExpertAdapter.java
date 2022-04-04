package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.bean.Expert;

import java.util.List;

/**
 * author ： yxm521
 * time    ： 2022/4/4
 */
public class ExpertAdapter extends RecyclerView.Adapter<ExpertAdapter.ViewHolder> {
    private List<Expert> experts;
    private Context context;

    public ExpertAdapter(List<Expert> experts, Context context) {
        this.experts = experts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expert,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Expert expert = experts.get(position);
        holder.nameTxt.setText(expert.getName());
        holder.phoneTxt.setText(expert.getPhone());
        holder.areaTxt.setText(expert.getArea());
        holder.receiveNumTxt.setText(expert.getReceiveNum()+"");
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return experts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTxt;
        TextView phoneTxt;
        TextView areaTxt;
        TextView receiveNumTxt;
        public ViewHolder(View view){
            super(view);
            nameTxt = view.findViewById(R.id.name);
            phoneTxt = view.findViewById(R.id.phone);
            areaTxt = view.findViewById(R.id.area);
            receiveNumTxt = view.findViewById(R.id.receive_num);
        }
    }
}
