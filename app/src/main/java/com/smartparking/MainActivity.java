package com.smartparking;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener
{
    Button paybtn;
    TextView paytext;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Checkout.preload(getApplicationContext());

        paytext=(TextView)findViewById(R.id.paytext);
        paybtn=(Button)findViewById(R.id.paybtn);

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makepayment();
            }
        });

    }

    private void makepayment()
    {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_lihA5B4cUsSNdf");

        checkout.setImage(R.drawable.logo_no_name);
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Smart Parking payment");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "USD");
            options.put("amount", "30000");//300 X 100
            options.put("prefill.email", "lazarouderedza99@gmail.com");
            options.put("prefill.contact","+263774780043");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s)
    {
        paytext.setText("Successful payment ID :"+s);
        Log.e("Payment Result : = ",s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        paytext.setText("Failed and cause is :"+s);
        Log.e("Payment Result : = ",s);

    }
}