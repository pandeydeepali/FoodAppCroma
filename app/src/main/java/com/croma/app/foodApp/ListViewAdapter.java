package com.croma.app.foodApp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
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

import com.android.volley.Response;
import com.android.volley.cache.plus.ImageLoader;
import com.android.volley.cache.plus.ImageRequest;
import com.android.volley.error.VolleyError;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
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
        final ViewHolder holder;
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
            holder.listImage.setImageResource(R.drawable.back);
            //holder.leftImage.setImageURI(Uri.parse(arrayList.get(position).icon));
            holder.leftImage.setImageResource(Integer.parseInt(arrayList.get(position).icon));
            holder.ratingBarRestaurant.setRating(arrayList.get(position).rating);
            holder.rlayout.setOnClickListener(onClickListener);
            holder.rlayout.setTag(position);


        return convertView;
    }

}


