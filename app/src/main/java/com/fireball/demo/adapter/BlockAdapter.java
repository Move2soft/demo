package com.fireball.demo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fireball.demo.R;
import com.fireball.demo.model.Block;
import com.fireball.demo.model.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MS on 2/8/2017.
 */
public class BlockAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater infalter;
    private List<Block.Output> data = new ArrayList<Block.Output>();

    public BlockAdapter(Context c) {
        infalter = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = c;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(List<Block.Output> files) {

        try {

            this.data.clear();
            this.data.addAll(files);

        } catch (Exception e) {
            Log.e("Exception is", e.getMessage());
        }

        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = infalter.inflate(R.layout.spinner_item, null);
            holder.tvMenuTitle = (TextView) convertView.findViewById(R.id.tvSPinner);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            holder.tvMenuTitle.setText(data.get(position).name);
        } catch (Exception e) {
            Log.e("spinner error", e.toString());
        }

        return convertView;
    }

    public class ViewHolder {
        TextView tvMenuTitle;
    }

    boolean isEnable = true;

    public void setParentCategEnabled(boolean isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
    }

}

