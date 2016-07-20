package com.croma.app.foodApp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by suppi on 16/07/16.
 */
public class Welcome extends Fragment implements GlobalInterFace, View.OnClickListener{
   private Button welcomeBtn;
    private View v;
    private Bundle savedInstanceState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_welcome, container, false);
        welcomeBtn=(Button)v.findViewById(R.id.welcomeBtn);
        findViewById();
        setOnClickListener();
        return v;
    }


    @Override
    public void findViewById() {


    }

    @Override
    public void setOnClickListener() {
        welcomeBtn.setOnClickListener(this);


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
            case R.id.welcomeBtn:{
                Log.e("click", "click");
                Fragment fragment = new Locate();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
               // fragmentManager.executePendingTransactions();

//                if(savedInstanceState==null) {
//                    getActivity().getSupportFragmentManager().beginTransaction().
//                            replace(R.id.fragment_place, new Locate()).addToBackStack(null).commit();
                    break;

        }
        }
    }
}
