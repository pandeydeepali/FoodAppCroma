package com.croma.app.foodApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuwithPriceList extends AppCompatActivity implements GlobalInterFace, View.OnClickListener {
    private TextView headText;
    private TextView horizontalFoodText;
    private TextView horizontalFoodPrice;
    private ImageView backbtn;


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
        horizontalFoodText=(TextView)findViewById(R.id.MenufoodName);
        horizontalFoodPrice=(TextView)findViewById(R.id.Menufoodprice);
        backbtn=(ImageView) findViewById(R.id.menupriceBack);
        backbtn.setOnClickListener(this);
        Intent intent = getIntent();
        String nameVal = intent.getStringExtra("listFoodName");
        int price = intent.getIntExtra("listFoodPrice", 0);
        Log.e("priiii", ""+price);
        headText.setText(nameVal);
        horizontalFoodText.setText(nameVal);
        horizontalFoodPrice.setText(String.valueOf(price));
        horizontalFoodPrice.append(" Rs.");

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
    public void onClick(View v) {

        

    }
}
