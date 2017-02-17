package com.fireball.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.fireball.demo.Listener.ResponseListener;
import com.fireball.demo.R;
import com.fireball.demo.model.BasicResponse;
import com.fireball.demo.model.Token;
import com.fireball.demo.utils.AsyncHttpRequest;
import com.fireball.demo.utils.BaseActivity;
import com.fireball.demo.utils.Constants;
import com.fireball.demo.utils.RequestParamsUtils;
import com.fireball.demo.utils.ResponseHandler;
import com.fireball.demo.utils.URLs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;


public class SplashActivity extends BaseActivity {
    private final int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {


        if (!getPreferences().getString(RequestParamsUtils.TOKEN, "").equals("") &&
                !getPreferences().getString(RequestParamsUtils.TOKEN_TYPE, "").equals("")) {

        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME);
        }


    }





}
