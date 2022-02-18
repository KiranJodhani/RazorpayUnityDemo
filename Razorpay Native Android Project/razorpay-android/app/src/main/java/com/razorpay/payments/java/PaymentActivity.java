package com.razorpay.payments.java;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.content.Intent;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.razorpay.payments.R;

import org.json.JSONObject;
import org.json.JSONException;

import com.unity3d.player.UnityPlayer;



public class PaymentActivity extends Activity implements PaymentResultListener {
    private static final String TAG = PaymentActivity.class.getSimpleName();
    private String key;
    private String name;
    private String description;
    private String image;
    private String currency;
    private String amount;
    private String email;
    private String contact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Checkout.preload(getApplicationContext());
        startPayment();
    }

    public void LetThePaymentBegins(Activity uActivity, String dataFromUnity) {
//        Log.i(TAG, "#### DataFromUnity: " + dataFromUnity);
        Intent i = new Intent(uActivity, PaymentActivity.class);
        i.putExtra("value", dataFromUnity);
        uActivity.startActivity((i));
    }

    public void startPayment() {
//        getIntent().getStringExtra("value");
        try {
            JSONObject obj = null;
            obj = new JSONObject(getIntent().getStringExtra("value"));
            key = obj.getString("key");
            name = obj.getString("name");
            description = obj.getString("description");
            image = obj.getString("image");
            currency = obj.getString("currency");
            amount = obj.getString("amount");
            email = obj.getString("email");
            contact = obj.getString("contact");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        Log.i(TAG, "#### name: " + name);
//        Log.i(TAG, "#### description: " + description);
//        Log.i(TAG, "#### image: " + image);
//        Log.i(TAG, "#### currency: " + currency);
//        Log.i(TAG, "#### amount: " + amount);
//        Log.i(TAG, "#### email: " + email);
//        Log.i(TAG, "#### contact: " + contact);

        final Activity activity = this;
        final Checkout checkout = new Checkout();
        checkout.setKeyID(key);

        try {
            JSONObject options = new JSONObject();
            options.put("name", name);
            options.put("description", description);
            options.put("image", image); //You can omit the image option to fetch the image from dashboard
            options.put("currency", currency);
            options.put("amount", amount);

            JSONObject preFill = new JSONObject();
            preFill.put("email", email);
            preFill.put("contact", contact);

            options.put("prefill", preFill);

            checkout.open(activity, options);

        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
//            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            UnityPlayer.UnitySendMessage("RazorpayManager", "OnPaymentSuccess", razorpayPaymentID);
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }

    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
//            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
            UnityPlayer.UnitySendMessage("RazorpayManager", "OnPaymentError", response);
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}