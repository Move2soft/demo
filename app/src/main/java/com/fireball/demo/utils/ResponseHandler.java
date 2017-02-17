package com.fireball.demo.utils;

import android.app.Activity;

import com.fireball.demo.Listener.ResponseListener;


/**
 * Created by UV on 26-Dec-16.
 */
public class ResponseHandler extends AsyncResponseHandler {

    private ResponseListener responseListener;

    public ResponseHandler(Activity context, ResponseListener responseListener) {
        super(context);
        this.responseListener = responseListener;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onFinish() {
        super.onFinish();
        try {
            responseListener.onResponse("");
        } catch (Exception e) {
            Debug.e("ResponseHandler onFinish Exception", e.getMessage());
        }
    }

    @Override
    public void onSuccess(String response) {

        try {
            responseListener.onResponse(response);


        } catch (Exception e) {
            Debug.e("ResponseHandler onSuccess Exception", e.getMessage());
        }
    }

    @Override
    public void onFailure(Throwable e, String content) {
        responseListener.onResponse("");
        Debug.e("Response ", "onFailure" + e.getMessage());
    }
}
