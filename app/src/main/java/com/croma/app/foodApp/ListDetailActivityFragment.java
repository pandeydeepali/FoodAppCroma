package com.croma.app.foodApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
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
    SharedPreferences prefs;


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

    public void fillArrayList() {
//        arrayList.add(new FoodItem("Veg Pasta", "Order Minimum 200", R.drawable.back, R.drawable.list, "Delievered in 60min", "C-77, Noida sector 27"));
//        arrayList.add(new FoodItem("Non Veg One", "Order Minimum 200", R.drawable.back, R.drawable.img5, "Delievered in 60min", "C-78, Noida sector 27"));
//        arrayList.add(new FoodItem("Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img2, "Delievered in 60min", "C-79, Noida sector 27"));
//        arrayList.add(new FoodItem("Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img3, "Delievered in 60min", "C-77, Noida sector 27"));
//        arrayList.add(new FoodItem("Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img4, "Delievered in 60min", "C-77, Noida sector 27"));
//        arrayList.add(new FoodItem("Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img5, "Delievered in 60min", "C-77, Noida sector 27"));
//        arrayList.add(new FoodItem("Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img1, "Delievered in 60min", "C-79, Noida sector 27"));
//        arrayList.add(new FoodItem("Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img4, "Delievered in 60min", "C-77, Noida sector 27"));
//        arrayList.add(new FoodItem("Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img3, "Delievered in 60min", "C-77, Noida sector 27"));
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
                    // b.putString("");
                    // Intent intent=new Intent(getActivity(), ListDetailActivity.class);

                    b.putString("ItemSubItem", foodItem.name);
                    b.putString("ItemAddress", foodItem.vicinity);
                    b.putInt("ItemImage", Integer.valueOf(foodItem.icon));
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
