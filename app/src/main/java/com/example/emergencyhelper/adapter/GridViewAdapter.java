package com.example.emergencyhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emergencyhelper.entity.SortItemEntity;

import java.util.List;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.util.ViewUtil;

/**
 * author ： yxm521
 * time    ： 2022/4/3
 */
public class GridViewAdapter extends BaseAdapter {
    private List<SortItemEntity> data;
    private Context context;
    private int pageIndex;
    private int pageMaxSize;
    private List<Class> cls;

    public GridViewAdapter(List<SortItemEntity> data, Context context, int pageIndex, int pageMaxSize,List<Class> cls) {
        this.data = data;
        this.context = context;
        this.pageIndex = pageIndex;
        this.pageMaxSize = pageMaxSize;
        this.cls = cls;
    }

    @Override
    public int getCount() {
        return data.size() > (pageIndex + 1) * pageMaxSize ? pageMaxSize : (data.size() - pageIndex * pageMaxSize);
    }

    @Override
    public Object getItem(int i) {
        return data.get(i + pageIndex * pageMaxSize);
    }

    @Override
    public long getItemId(int i) {
        return pageIndex * pageMaxSize;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_sort,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        int position = i + pageIndex * pageMaxSize;
        SortItemEntity se = data.get(position);
        holder.sortImg.setImageResource(se.getImgId());
        holder.sortNameTxt.setText(context.getString(se.getNameId()));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context,cls.get(position));
            }
        });
        return view;
    }

    class ViewHolder{
        TextView sortNameTxt;
        ImageView sortImg;
        public ViewHolder(View view){
            sortNameTxt = view.findViewById(R.id.sort_name);
            sortImg = view.findViewById(R.id.img_url);
        }
    }
}
