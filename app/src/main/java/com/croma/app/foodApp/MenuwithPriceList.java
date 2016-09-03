package com.croma.app.foodApp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MenuwithPriceList extends AppCompatActivity implements GlobalInterFace, View.OnClickListener {
    public int quantity = 1;

    private TextView headText, horizontalFoodPrice, horizontalFoodText, QuantityText, totalquan;
    private ImageView backbtn;
    private Button minusBtn, plusBtn, addBasketBtn;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuwith_price_list);
        findViewById();
        setOnClickListener();
        applyFont();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void findViewById() {
        headText = (TextView) findViewById(R.id.menuheaderrext);
        horizontalFoodText = (TextView) findViewById(R.id.MenufoodName);
        horizontalFoodPrice = (TextView) findViewById(R.id.Menufoodprice);
        totalquan = (TextView) findViewById(R.id.MENUPRICE);

        backbtn = (ImageView) findViewById(R.id.menupriceBack);
        minusBtn = (Button) findViewById(R.id.minusQuan);
        plusBtn = (Button) findViewById(R.id.plusQuan);
        QuantityText = (TextView) findViewById(R.id.quantity);
        addBasketBtn=(Button)findViewById(R.id.AddBasketBtn);
        addBasketBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        plusBtn.setOnClickListener(this);
        backbtn.setOnClickListener(this);
        Intent intent = getIntent();
        String nameVal = intent.getStringExtra("listFoodName");
        int price = intent.getIntExtra("listFoodPrice", 0);
        Log.e("priiii", "" + price);
        headText.setText(nameVal);
        horizontalFoodText.setText(nameVal);
        horizontalFoodPrice.setText(String.valueOf(price));
        horizontalFoodPrice.append(" Rs.");
        totalquan.setText(String.valueOf(price));
        totalquan.append(" Rs.");


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

            case R.id.AddBasketBtn: {
                addIteminBasket();
                break;
            }


        }
    }


    public void increaseQuan() {

        quantity++;
        if (quantity <= 0) {

        } else {
            QuantityText.setText("" + quantity);
           // String.valueOf(horizontalFoodPrice) * String.valueOf(quantity)=totalquan;
            String value = horizontalFoodPrice.getText().toString();
            value = value.replaceAll("\\D+",""); //REGEX
            String multiple = String.valueOf( Integer.parseInt(value)* quantity);
            totalquan.setText(multiple);
            totalquan.append(" Rs.");


        }

    }


    public void decreaseQuan() {
        quantity--;
        if (quantity <= 0) {

        } else {
            QuantityText.setText("" + quantity);
            String value = horizontalFoodPrice.getText().toString();
            value = value.replaceAll("\\D+",""); //REGEX
            String multiple = String.valueOf( Integer.parseInt(value)* quantity);
            totalquan.setText(multiple);
            totalquan.append(" Rs.");

        }
    }


    public void addIteminBasket(){
        String price=totalquan.getText().toString();







    }


}



