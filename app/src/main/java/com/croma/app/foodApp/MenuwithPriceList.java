package com.croma.app.foodApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        Intent intent = getIntent();
        String nameVal = intent.getStringExtra("listFoodName");
       // String lName = intent.getStringExtra("lname");
        headText.setText(nameVal);

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
