package com.croma.app.foodApp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
/**
 * @author supriya.pandey
 * OrderActivityFragment class
 */
public class OrderActivityFragment extends Fragment implements GlobalInterFace,View.OnClickListener {

    private static final String TAG = OrderActivityFragment.class.getSimpleName();
    private View mView;
    private TextView orderTextView;
    private Button clickMeButton;


    public OrderActivityFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_order, container, false);
        findViewById();
        setOnClickListener();
        return mView;
    }

    @Override
    public void findViewById() {

        orderTextView   =   (TextView)mView.findViewById(R.id.orderTextView);
        clickMeButton   =   (Button)mView.findViewById(R.id.clickMeButton);
        orderTextView.setText(((OrderActivity)getActivity()).demoString);

    }

    @Override
    public void setOnClickListener() {
        clickMeButton.setOnClickListener(this);

    }

    @Override
    public void applyFont() {

    }

    @Override
    public void OnitemSelect() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.clickMeButton:{
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment,new OrderActivityFragment2()).addToBackStack(null).commit();
                break;
            }
        }
    }
}
