package com.croma.app.foodApp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by suppi on 03/07/16.
 */
public class ListViewAdapter extends BaseAdapter {

    private View.OnClickListener onClickListener;
    private ArrayList<geometry > arrayList;
    private Activity context;


    //-----Constructor
    public ListViewAdapter(View.OnClickListener onClickListener, ArrayList<geometry> arrayList,Activity context) {
        this.onClickListener = onClickListener;
        this.arrayList = arrayList;
        this.context = context;
    }

    public int getCount() {
         return arrayList.size();

    }

    public geometry getItem(int position)
    {
         return arrayList.get(position);
    }

    public long getItemId(int position) {
        return  0;
    }



    private class ViewHolder {
        TextView txtViewTitle;
        TextView txtViewDescription;
        ImageView listImage;
        ImageView leftImage;
        TextView delieverItemTime;
        RatingBar ratingBarRestaurant;
//        TextView delieverAddress;
        RelativeLayout rlayout;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();

            convertView = inflater.inflate(R.layout.activity_list, null);
            holder = new ViewHolder();
            holder.txtViewTitle = (TextView) convertView.findViewById(R.id.listDescription);
            holder.txtViewDescription = (TextView) convertView.findViewById(R.id.listTitle);
            holder.listImage = (ImageView) convertView.findViewById(R.id.listImage);
            holder.leftImage = (ImageView) convertView.findViewById(R.id.homeleftlogoImage);
            holder.delieverItemTime = (TextView) convertView.findViewById(R.id.listDeliever);
            holder.ratingBarRestaurant=(RatingBar)convertView.findViewById(R.id.ratingBar);
          //  holder.delieverAddress = (TextView) convertView.findViewById(R.id.homeRestAddress);
            holder.rlayout = (RelativeLayout) convertView.findViewById(R.id._relativeLayout);
            holder.txtViewTitle.setText(arrayList.get(position).name);
            holder.txtViewDescription.setText(arrayList.get(position).vicinity);
            holder.ratingBarRestaurant.setNumStars(arrayList.get(position).rating);

            holder.listImage.setImageResource(R.drawable.back);
        //    holder.leftImage.setImageResource(arrayList.get(position).icon);
//            holder.delieverItemTime.setText(arrayList.get(position).itemdeliever);
//            holder.delieverAddress.setText(arrayList.get(position).delieverAddress);
            holder.rlayout.setOnClickListener(onClickListener);
            holder.rlayout.setTag(position);


        return convertView;
    }

}


