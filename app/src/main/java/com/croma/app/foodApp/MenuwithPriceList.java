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
    int quantity=1;
    private TextView headText, horizontalFoodPrice, horizontalFoodText, QuantityText, AddBasketBtn;
    private ImageView backbtn;
    private Button minusBtn, plusBtn;



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
        minusBtn=(Button)findViewById(R.id.minusQuan);
        plusBtn=(Button)findViewById(R.id.plusQuan);
        QuantityText=(TextView)findViewById(R.id.quantity);
        AddBasketBtn=(Button)findViewById(R.id.basketBtn);
        AddBasketBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        plusBtn.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.menupriceBack: {
                finish();
                break;
            }

            case R.id.plusQuan: {
                increaseQuan();
                break;
            }


            case R.id.minusQuan: {
                decreaseQuan();
                break;
            }


        }
    }


    public void increaseQuan(){
        Log.e("increase count", "IncreaseCount");

    }


    public void decreaseQuan(){
        Log.e("increase count", "DecreseCount");

    }



}



