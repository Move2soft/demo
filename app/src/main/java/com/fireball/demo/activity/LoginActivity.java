package com.fireball.demo.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.fireball.demo.Listener.ResponseListener;
import com.fireball.demo.R;
import com.fireball.demo.adapter.SpinnerAdapter;
import com.fireball.demo.model.Spinner;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity implements ResponseListener {
    @Bind(R.id.etEmailId)
    EditText etEmailId;
    @Bind(R.id.etMobileNo)
    EditText etMobileNo;
    @Bind(R.id.etPassword)
    EditText etPassword;
    private String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public String methodName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ttLogin)
    public void ttLoginClick() {
        isValidData();
    }

    @OnClick(R.id.ttRegister)
    public void ttRegisterClick() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void isValidData() {
        String email_id = etEmailId.getText().toString();
        etEmailId.getText().toString();

        if (etMobileNo.length() == 0) {
            etMobileNo.setError("Enter Mobile No");
            etMobileNo.requestFocus();

        } else if (etMobileNo.getText().length() != 10) {
            etMobileNo.setError("Enter Valid  10 digit Mobile No!");
            etMobileNo.requestFocus();
        } else if (etEmailId.length() == 0) {
            etEmailId.setError("Enter Email");
            etEmailId.requestFocus();

        } else if (!email_id.matches(EMAIL_PATTERN)) {
            etEmailId.setError("Invalid Email ID!");
            etEmailId.requestFocus();
        } else if (etPassword.length() == 0) {
            etPassword.setError("Enter Password");
            etPassword.requestFocus();

        } else {
            if (!getPreferences().getString(RequestParamsUtils.TOKEN, "").equals("") &&
                    !getPreferences().getString(RequestParamsUtils.TOKEN_TYPE, "").equals("")) {
                doLogin();
            } else {
                getToken(etMobileNo.getText().toString(), etPassword.getText().toString());
            }

        }
    }

    private void doLogin() {
        try {
            showProgress("");
            methodName="login";
            RequestParams params = new RequestParams();
            params.put(RequestParamsUtils.REG_MOBILE_NO, etMobileNo.getText().toString());
            params.put(RequestParamsUtils.REG_EMAIL, etEmailId.getText().toString());
            params.put(RequestParamsUtils.ISACTIVE, "true");
            params.put(RequestParamsUtils.ISDELETE, "false");
            params.put(RequestParamsUtils.ISVARIFY, "true");
            Log.e("doLogin", params.toString());
            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();
            clientPhotos.addHeader("Content-Type", "application/json");
//            clientPhotos.addHeader(RequestParamsUtils.AUTHENTICATION_KEY, Constants.API_KEY_VALUE);

            clientPhotos.addHeader(RequestParamsUtils.AUTHENTICATION_KEY, getPreferences().getString(RequestParamsUtils.TOKEN_TYPE, "") + " " + getPreferences().getString(RequestParamsUtils.TOKEN, ""));
            clientPhotos.get(new URLs().LOGIN_URL, params, new ResponseHandler(LoginActivity.this, this));

        } catch (Exception e) {
            Log.e("getCategory Exception", e.getMessage());
        }

    }

    @Override
    public void onResponse(String response) {

        if (methodName.equals("Get Token")) {

            try {
                dismissProgress();

                Log.e("Response Get Token ", "onSuccess" + response);

                if (response != null && response.length() > 0) {


                    Token tokenRider = new Gson().fromJson(
                            response, new TypeToken<Token>() {
                            }.getType());
                    Constants.TOKEN = tokenRider.accessToken;
                    Constants.TOKEN_TYPE = tokenRider.tokenType;
                    SharedPreferences.Editor editor = getPreferences().edit();
                    editor.putString(RequestParamsUtils.TOKEN, tokenRider.accessToken + "");
                    editor.putString(RequestParamsUtils.TOKEN_TYPE, tokenRider.tokenType + "");
                    editor.commit();
                    doLogin();
                }
            } catch (Exception e) {
                Log.e("Response Exception", e.getMessage());
            }

        } else {
            try {
                dismissProgress();

                Log.e("ResponseHandler", "onSuccess" + response);

                if (response != null && response.length() > 0) {

//                CategoryList categoryListRider = new Gson().fromJson(
//                        response, new TypeToken<CategoryList>() {
//                        }.getType());
//
//                if (categoryListRider.st == 1) {
//                    mainActivityAdapter.addAll(categoryListRider.result);
//                    dismissProgress();
//
//                } else {
//
//                }

                }
            } catch (Exception e) {
                Log.e("Response Exception", e.getMessage());
            }
        }

    }

    private void getToken(String userName, String password) {
        try {
            showProgress("");
            methodName = "Get Token";
            RequestParams params = new RequestParams();
            params.put(RequestParamsUtils.CLIENT_ID, "consoleApp");
            params.put(RequestParamsUtils.GRANT_TYPE, "password");
            params.put(RequestParamsUtils.USERNAME, userName);
            params.put(RequestParamsUtils.PASSWORD, password);
            Log.e("getToken", params.toString());
            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();
            clientPhotos.addHeader("Content-Type", "application/x-www-form-urlencoded");
            clientPhotos.addHeader(RequestParamsUtils.AUTHENTICATION_KEY, "Basic Y29uc29sZUFwcDphZG1pbkBhZ3JvdHJhZGVzZXJ2aWNlLmNvbQ==");
            clientPhotos.post(new URLs().GET_TOKEN, params, new ResponseHandler(LoginActivity.this, this));

        } catch (Exception e) {
            Log.e("RegigetToken Exception", e.getMessage());
        }

    }

}
