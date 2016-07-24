package com.croma.app.foodApp;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListDetailActivityFragment extends Fragment implements GlobalInterFace, View.OnClickListener {
    private static final String TAG = ListDetailActivityFragment.class.getSimpleName();
    private View mView;
    private ListView listView;
    public ArrayList<FoodItem> arrayList;
    public ListViewAdapter listAdapter;


    public ListDetailActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.content_list_detail, container, false);
        findViewById();
        setOnClickListener();
        applyFont();
        OnitemSelect();
        return mView;
    }

    @Override
    public void findViewById() {
        listView = (ListView)mView.findViewById(R.id.detail_listView);
        arrayList = new ArrayList<>();
      //  listAdapter = new ListViewAdapter((((NavigationActivity)getActivity())), arrayList, ListDetailActivityFragment.this);
        listAdapter = new ListViewAdapter(ListDetailActivityFragment.this, arrayList, (((NavigationActivity)getActivity())));
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

    public void fillArrayList(){
        arrayList.add(new FoodItem("Veg Pasta", "Order Minimum 200", R.drawable.back, R.drawable.list, "Delievered in 60min", "C-77, Noida sector 27"));
        arrayList.add(new FoodItem( "Non Veg One", "Order Minimum 200", R.drawable.back, R.drawable.img5, "Delievered in 60min", "C-78, Noida sector 27"));
        arrayList.add(new FoodItem("Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img2, "Delievered in 60min", "C-79, Noida sector 27"));
        arrayList.add(new FoodItem( "Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img3, "Delievered in 60min", "C-77, Noida sector 27"));
        arrayList.add(new FoodItem( "Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img4, "Delievered in 60min", "C-77, Noida sector 27"));
        arrayList.add(new FoodItem( "Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img5, "Delievered in 60min", "C-77, Noida sector 27"));
        arrayList.add(new FoodItem("Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img1, "Delievered in 60min", "C-79, Noida sector 27"));
        arrayList.add(new FoodItem( "Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img4, "Delievered in 60min", "C-77, Noida sector 27"));
        arrayList.add(new FoodItem( "Non Veg Veg", "Order Minimum 200", R.drawable.back, R.drawable.img3, "Delievered in 60min", "C-77, Noida sector 27"));
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._relativeLayout: {
                if (v.getTag() != null) {
                    Integer integer = Integer.parseInt(v.getTag().toString());
                    FoodItem foodItem=arrayList.get(integer);
                    Bundle b=new Bundle();
                   // b.putString("");
                   // Intent intent=new Intent(getActivity(), ListDetailActivity.class);
                    b.putString("ItemName", foodItem.itemName);
                    b.putString("ItemSubItem", foodItem.subItemName);
                    b.putString("ItemAddress", foodItem.delieverAddress);
                    b.putInt("ItemImage", foodItem.leftImage);

                    DetailFragment detailFragment=new DetailFragment();
                    detailFragment.setArguments(b);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment, detailFragment).addToBackStack(null).commit();
                   // startActivity(b);
                    break;

                }

            }
        }
    }
}
