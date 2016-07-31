package com.croma.app.foodApp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
/**
 * author supriya.pandey
 *
 */
public class PagerActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public ViewPager mViewPager;
    public static final String TAG = "main-activity";
    public static final String ADDRESS_REQUESTED_KEY = "address-request-pending";
    public static final String LOCATION_ADDRESS_KEY = "location-address";
    private ProgressDialog progressDialog=null;

    /**
     * Provides the entry point to Google Play services.
     */
    public GoogleApiClient mGoogleApiClient;

    /**
     * Represents a geographical location.
     */
    public Location mLastLocation;
    /**
     * Tracks whether the user has requested an address. Becomes true when the user requests an
     * address and false when the address (or an error message) is delivered.
     * The user requests an address by pressing the Fetch Address button. This may happen
     * before GoogleApiClient connects. This activity uses this boolean to keep track of the
     * user's intent. If the value is true, the activity tries to fetch the address as soon as
     * GoogleApiClient connects.
     */
    public boolean mAddressRequested;

    /**
     * The formatted location address.
     */
    public String mAddressOutput;

    /**
     * Receiver registered with this activity to get the response from FetchAddressIntentService.
     */
    public AddressResultReceiver mResultReceiver;

    /**
     * Displays the location address.
     */
    //public TextView mLocationAddressTextView;

    /**
     * Visible while the address is being fetched.
     */
    ProgressBar mProgressBar;

    /**
     * Kicks off the request to fetch an address when pressed.
     */
    //Button mFetchAddressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(0);
        mResultReceiver = new AddressResultReceiver(new Handler());
        // Set defaults, then update using values stored in the Bundle.
        mAddressRequested = false;
        mAddressOutput = "";
        updateValuesFromBundle(savedInstanceState);
        buildGoogleApiClient();

    }

    /**
     * Updates fields based on data stored in the bundle.
     */
    public void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // Check savedInstanceState to see if the address was previously requested.
            if (savedInstanceState.keySet().contains(ADDRESS_REQUESTED_KEY)) {
                mAddressRequested = savedInstanceState.getBoolean(ADDRESS_REQUESTED_KEY);

            }
            // Check savedInstanceState to see if the location address string was previously found
            // and stored in the Bundle. If it was found, display the address string in the UI.
            if (savedInstanceState.keySet().contains(LOCATION_ADDRESS_KEY)) {
                mAddressOutput = savedInstanceState.getString(LOCATION_ADDRESS_KEY);
                displayAddressOutput();
            }
            Log.e("saved Instanced", mAddressOutput);
        }
    }

    /**
     * Builds a GoogleApiClient. Uses {@code #addApi} to request the LocationServices API.
     */
    public synchronized void buildGoogleApiClient() {
        Log.e("Build Client", "Builder client");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onResume(){
        super.onResume();

    }


    /**
     * Runs when user clicks the Fetch Address button. Starts the service to fetch the address if
     * GoogleApiClient is connected.
     */
    public void fetchAddressButtonHandler() {
        // We only start the service to fetch the address if GoogleApiClient is connected.
        if (mGoogleApiClient.isConnected() && mLastLocation != null) {
            startIntentService();
        }
        // If GoogleApiClient isn't connected, we process the user's request by setting
        // mAddressRequested to true. Later, when GoogleApiClient connects, we launch the service to
        // fetch the address. As far as the user is concerned, pressing the Fetch Address button
        // immediately kicks off the process of getting the address.
        mAddressRequested = true;

    }


    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        Log.e("Client Connected", "Connected");
        // Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
       // SharedPrefUtil.putFloat("CurrentLatitude", (float) mLastLocation.getLatitude(), PagerActivity.this);
        //SharedPrefUtil.putFloat("CurrentLongitude", (float) mLastLocation.getLongitude(), PagerActivity.this);
         SharedPrefUtil.putLong("CurrentLatitude", Double.doubleToLongBits(mLastLocation.getLatitude()), PagerActivity.this);
         SharedPrefUtil.putLong("CurrentLongitude", Double.doubleToLongBits(mLastLocation.getLongitude()), PagerActivity.this);


        if (mLastLocation != null) {
            // Determine whether a Geocoder is available.
            if (!Geocoder.isPresent()) {
                Toast.makeText(this, R.string.no_geocoder_available, Toast.LENGTH_LONG).show();
                return;
            }
            // It is possible that the user presses the button to get the address before the
            // GoogleApiClient object successfully connects. In such a case, mAddressRequested
            // is set to true, but no attempt is made to fetch the address (see
            // fetchAddressButtonHandler()) . Instead, we start the intent service here if the
            // user has requested an address, since we now have a connection to GoogleApiClient.
            if (mAddressRequested) {
                startIntentService();
            }
        }
    }


    /**
     * Creates an intent, adds location data to it as an extra, and starts the intent service for
     * fetching an address.
     */
    public void startIntentService() {
        Log.e("Intent", "Start Intent");
        // Create an intent for passing to the intent service responsible for fetching the address.
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        // Pass the result receiver as an extra to the service.
        intent.putExtra(Constants.RECEIVER, mResultReceiver);

        // Pass the location data as an extra to the service.
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);

        // Start the service. If the service isn't already running, it is instantiated and started
        // (creating a process for it if needed); if it is running then it remains running. The
        // service kills itself automatically once all intents are processed.
        startService(intent);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }


    /**
     * Updates the address in the UI.
     */
    public void displayAddressOutput() {

        final Handler handler = new Handler();
        progressDialog=ProgressDialog.show(this, "Please wait","Retriving Location...", true );
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PagerActivity.this, SignUpActivity.class);
                intent.putExtra("my_address",mAddressOutput);
                startActivity(intent);
                progressDialog.dismiss();
            }
        }, 5000);
    }


    /**
     * Shows a toast with the given text.
     */
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save whether the address has been requested.
        savedInstanceState.putBoolean(ADDRESS_REQUESTED_KEY, mAddressRequested);

        // Save the address string.
        savedInstanceState.putString(LOCATION_ADDRESS_KEY, mAddressOutput);
        super.onSaveInstanceState(savedInstanceState);
    }


    /**
     * Receiver for data sent from FetchAddressIntentService.
     */
    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        /**
         *  Receives data sent from FetchAddressIntentService and updates the UI in MainActivity.
         */
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string or an error message sent from the intent service.
            mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            displayAddressOutput();

            // Show a toast message if an address was found.
            if (resultCode == Constants.SUCCESS_RESULT) {
                showToast(getString(R.string.address_found));
            }

            // Reset. Enable the Fetch Address button and stop showing the progress bar.
            mAddressRequested = false;

        }
    }
    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(0);
        }
        // super.onBackPressed();
    }


}



