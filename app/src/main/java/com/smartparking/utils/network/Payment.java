package com.smartparking.utils.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.smartparking.R;
import com.smartparking.utils.AppConstants;

import org.json.JSONObject;

import java.util.Map;
import java.util.Objects;

public class Payment extends AppCompatActivity implements PaymentResultListener {
    public Boolean paymentFuntion(String amount, String email, String name, String note, Context context) {
        /*Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", email)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();
        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        // check if intent resolves
        if(null != chooser.resolveActivity(context.getPackageManager())){
            ((Activity) context).startActivityForResult(chooser, AppConstants.UPI_PAYMENT);
            return false;
        }else{
            Toast.makeText(context,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
            return false;
        }
*/


        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_lihA5B4cUsSNdf");

        checkout.setImage(R.drawable.logo_no_name);
        final Payment activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Smart Parking payment");
            options.put("description", note);
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "USD");
            options.put("amount", amount);//300 X 100
            options.put("prefill.email", email);
            options.put("prefill.contact", "+263774780043");
            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }


        return true;
    }



    @Override
    public void onPaymentSuccess(String s)
    {
      //  paytext.setText("Successful payment ID :"+s);
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
        Log.e("Payment Result : = ",s);
    }

    @Override
    public void onPaymentError(int i, String s) {
       // paytext.setText("Failed and cause is :"+s);
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
        Log.e("Payment Result : = ",s);

    }





    public Boolean upiPaymentDataOperation(Map<String, String> myMap, Context context) {
//        https://stackoverflow.com/a/10514517
        if (isConnectionAvailable(context)) {
            String approvalRefNo = "";
            if (Objects.equals(myMap.get("status"), "success")) {
                //Code to handle successful transaction here.
                if (myMap.get("approvalrefno") != null) {
                    approvalRefNo = myMap.get("approvalrefno");
                } else if (myMap.get("txnref") != null) {
                    approvalRefNo = myMap.get("txnref");
                } else {
                    approvalRefNo = "";
                }
                Toast.makeText(context, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.d("UPIPay", "responseStr: " + approvalRefNo);
                return true;
            } else {
                Toast.makeText(context, "Transaction failed. Please try again!", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(context, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
