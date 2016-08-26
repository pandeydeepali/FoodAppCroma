package com.croma.app.foodApp;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by suppi on 07/08/16.
 */
public class DetailAdapter extends BaseAdapter {
    public ArrayList<Fooddetail> detailList;
    public Activity context;
    public DetailAdapter(ArrayList<Fooddetail> detailList, Activity context){
        this.detailList=detailList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return detailList.size();
       // return 0;
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
        RelativeLayout detailrelative;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.detail_list, null);
            holder = new ViewHolder();
            holder.foodName = (TextView) convertView.findViewById(R.id.detailTitle);
            holder.forwardImage = (ImageView) convertView.findViewById(R.id.detailImg);
            holder.detailrelative=(RelativeLayout)convertView.findViewById(R.id.detail_relativeLayout);
            holder.foodName.setText(detailList.get(position).foodName);
            holder.forwardImage.setImageResource(detailList.get(position).forwardImg);
            holder.detailrelative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("click", "click");


                }
            });

        return convertView;
    }

}
