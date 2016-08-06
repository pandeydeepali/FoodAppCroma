package com.croma.app.foodApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListDetailActivityFragment extends Fragment implements GlobalInterFace, View.OnClickListener {
    public static final String TAG = ListDetailActivityFragment.class.getSimpleName();
    public ArrayList<geometry> fragmentArrayList;
    public ListViewAdapter listAdapter;
    private View mView;
    private ListView listView;

    //-----from navigationActivity-----//

    public ListDetailActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.content_list_detail, container, false);
        findViewById();
        setOnClickListener();
        applyFont();
        OnitemSelect();
        return mView;
    }

    @Override
    public void findViewById() {
        listView = (ListView) mView.findViewById(R.id.detail_listView);
        fragmentArrayList = new ArrayList<>();
        //  listAdapter = new ListViewAdapter((((NavigationActivity)getActivity())), arrayList, ListDetailActivityFragment.this);
        listAdapter = new ListViewAdapter(ListDetailActivityFragment.this, fragmentArrayList, (((NavigationActivity) getActivity())));
        listView.setAdapter(listAdapter);
        fillArrayList();
    }

    @Override
    public void setOnClickListener() {

    }

    @Override
    public void applyFont() {

    }

    @Override
    public void OnitemSelect() {

    }

    @Override
    public void onResume() {
        Log.e("OnResume", "OnResue");
        super.onResume();
        //jsonRequestWithGet();
    }

    public void fillArrayList() {
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._relativeLayout: {
                if (v.getTag() != null) {
                    Integer integer = Integer.parseInt(v.getTag().toString());
                    geometry foodItem = fragmentArrayList.get(integer);
                    Bundle b = new Bundle();
                    b.putString("ItemName", foodItem.name);
                    b.putString("ItemSubItem", foodItem.name);
                    b.putString("ItemAddress", foodItem.vicinity);
                    b.putString("ItemImage", foodItem.icon);
                    b.putString("PlaceItemID", foodItem.place_id);
                    DetailFragment detailFragment = new DetailFragment();
                    detailFragment.setArguments(b);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment, detailFragment).addToBackStack(null).commit();
                    // startActivity(b);
                    break;
                }
            }
        }
    }
}
