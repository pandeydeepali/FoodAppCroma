package com.croma.app.foodApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MenuwithPriceList extends AppCompatActivity implements GlobalInterFace {
    private TextView headText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuwith_price_list);
        findViewById();
        setOnClickListener();
        applyFont();



    }

    @Override
    public void findViewById() {
        headText=(TextView)findViewById(R.id.menuheaderrext);
        Bundle b=new Bundle();
        headText.setText(b.getString("fName"));

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
}
