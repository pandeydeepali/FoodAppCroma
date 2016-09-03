package com.croma.app.foodApp;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.payu.india.Extras.PayUChecksum;
import com.payu.india.Extras.PayUSdkDetails;
import com.payu.india.Model.PaymentParams;
import com.payu.india.Model.PayuConfig;
import com.payu.india.Model.PostData;
import com.payu.india.Payu.PayuConstants;
import com.payu.payuui.PayUBaseActivity;
import com.utll.global.ActivitySwitcher;

public class MenuwithPriceList extends AppCompatActivity implements GlobalInterFace, View.OnClickListener {
    public int quantity = 1;

    private TextView headText, horizontalFoodPrice, horizontalFoodText, QuantityText, totalquan;
    private ImageView backbtn;
    private Button minusBtn, plusBtn, payBtn;
    private FloatingActionButton floatingCartBtn;

    private PayUChecksum checksum;
    private PostData postData;
    private String key;
    private String salt;
    private String var1;
    private Intent intent;
    //    private mPaymentParams mPaymentParams;
    private PaymentParams mPaymentParams;
    private PayuConfig payuConfig;
    private String cardBin;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     *
     * For Payment Integration
     * @param savedInstanceState
     */

    int merchantIndex = 0;
    //    int env = PayuConstants.MOBILE_STAGING_ENV;
    // in case of production make sure that merchantIndex is fixed as 0 (0MQaQP) for other key's payu server cant generate hash
    int env = PayuConstants.PRODUCTION_ENV;

    String merchantTestKeys[] = {"gtKFFx", "gtKFFx"};
    String merchantTestSalts[] = {"eCwWELxi", "eCwWELxi" };

    String merchantProductionKeys[] = {"0MQaQP", "smsplus"};
    String merchantProductionSalts[] = {"13p0PXZk", "1b1b0",};

    String offerKeys[] = {"test123@6622", "offer_test@ffer_t5172", "offerfranklin@6636"};

    String merchantKey = env == PayuConstants.PRODUCTION_ENV ? merchantProductionKeys[merchantIndex]:merchantTestKeys[merchantIndex];
    //    String merchantSalt = env == PayuConstants.PRODUCTION_ENV ? merchantProductionSalts[merchantIndex] : merchantTestSalts[merchantIndex];
    String mandatoryKeys[] = { PayuConstants.KEY, PayuConstants.AMOUNT, PayuConstants.PRODUCT_INFO, PayuConstants.FIRST_NAME, PayuConstants.EMAIL, PayuConstants.TXNID, PayuConstants.SURL, PayuConstants.FURL, PayuConstants.USER_CREDENTIALS, PayuConstants.UDF1, PayuConstants.UDF2, PayuConstants.UDF3, PayuConstants.UDF4, PayuConstants.UDF5, PayuConstants.ENV};
    String mandatoryValues[] = { merchantKey, "10.0", "myproduct", "firstname", "me@itsmeonly.com", ""+System.currentTimeMillis(), "https://payu.herokuapp.com/success", "https://payu.herokuapp.com/failure", merchantKey+":payutest@payu.in", "udf1", "udf2", "udf3", "udf4", "udf5", ""+env};

    String inputData = "";

    // lets tell the people what version of sdk we are using
    PayUSdkDetails payUSdkDetails = new PayUSdkDetails();


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
        payBtn=(Button)findViewById(R.id.PayBtn);

        payBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        plusBtn.setOnClickListener(this);
        backbtn.setOnClickListener(this);


        Intent intent = getIntent();
        String nameVal = intent.getStringExtra("listFoodName");
        int price = intent.getIntExtra("listFoodPrice", 0);
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

            case R.id.PayBtn: {
                paymentusingPayUApi();
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


    public void paymentusingPayUApi(){
        String totalAmount = totalquan.getText().toString();
        totalAmount = totalAmount.replaceAll("\\D+",""); //REGEX
        String paidAmount=String.valueOf(Integer.parseInt(totalAmount));
        Log.e("paidAmount", paidAmount);
        Bundle b = new Bundle();
        b.putString("itemAmount", paidAmount);
        ActivitySwitcher.switchActivity(MenuwithPriceList.this, MakePaymentActivity.class);

  }










}



