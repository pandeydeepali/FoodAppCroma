package com.croma.app.foodApp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by suppi on 07/08/16.
 */
public class DetailAdapter extends BaseAdapter {
    public ArrayList<Fooddetail> detailList;
    public Activity context;


    public DetailAdapter(DetailFragment detailFragment, ArrayList<Fooddetail> detailList, Activity context){
        this.detailList=detailList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return detailList.size();
    }

    @Override
    public Object getItem(int position) {
        return detailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView foodName;
        ImageView forwardImage;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.detail_list, null);
        holder=new ViewHolder();
        holder.foodName = (TextView) convertView.findViewById(R.id.detailTitle);
        holder.forwardImage = (ImageView) convertView.findViewById(R.id.detailImg);
        holder.foodName.setText(detailList.get(position).foodName);
        holder.forwardImage.setImageResource(detailList.get(position).forwardImg);



        return convertView;
    }


}
