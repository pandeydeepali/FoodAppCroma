package com.croma.app.foodApp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by suppi on 21/07/16.
 */
public class InfoAdapter extends BaseAdapter {
    Context context;
    List<InfoItem> rowItems;


    public InfoAdapter(Context context, List<InfoItem> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView txtTitle;

    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.inforow, null);
            holder = new ViewHolder();

            holder.txtTitle = (TextView) convertView.findViewById(R.id.infoTextView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        InfoItem rowItem = (InfoItem) getItem(position);


        holder.txtTitle.setText(rowItem.getTitle());
        return convertView;
    }
}
