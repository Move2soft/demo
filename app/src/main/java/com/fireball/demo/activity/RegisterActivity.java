package com.fireball.demo.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.nsd.NsdManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fireball.demo.Listener.ResponseListener;
import com.fireball.demo.R;
import com.fireball.demo.adapter.SpinnerAdapter;
import com.fireball.demo.model.BasicResponse;
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

import java.util.ArrayList;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements ResponseListener {

    @Bind(R.id.etEmailId)
    EditText etEmailId;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.etMobileNo)
    EditText etMobileNo;
    @Bind(R.id.ttSelectDealer)
    TextView ttSelectDealer;
    private String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String methodName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

    }

    private void showDealerDialog(final String title,
                                  final ArrayList<Spinner> data, final TextView tv) {

        final Dialog a = new Dialog(this);
        Window w = a.getWindow();
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.setContentView(R.layout.spinner_listview);
        w.setBackgroundDrawableResource(android.R.color.transparent);

        TextView lblselect = (TextView) w.findViewById(R.id.dialogtitle);
        lblselect.setText(title.replace("*", "").trim());

        SpinnerAdapter adapter = new SpinnerAdapter(this);
        ListView lv = (ListView) w.findViewById(R.id.lvSpinner);
        lv.setAdapter(adapter);
        adapter.addAll(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterview, View view,
                                    int position, long l) {
                tv.setText(data.get(position).title);
//                timeZone = data.get(position).ID;
                a.dismiss();
            }
        });
        a.show();
    }


    @OnClick(R.id.ttSelectDealer)
    public void ttSelectDealerClick() {
        ArrayList<Spinner> list = new ArrayList<>();
        list.add(new Spinner("1", "Supplier"));
        list.add(new Spinner("2", "Dealer"));
        showDealerDialog("Select Dealer", list, ttSelectDealer);
    }

    public void isValidData() {
        String email_id = etEmailId.getText().toString();
        etEmailId.getText().toString();

        if (etMobileNo.length() == 0) {
            etMobileNo.setError("Enter Mobile No");
            etMobileNo.requestFocus();

        } else if (etMobileNo.getText().length() != 10) {
            etMobileNo.setError("Enter Valid 10 digit  Mobile No!");
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

        } else if (ttSelectDealer.getText().equals("") || ttSelectDealer.getText() == null) {
            ttSelectDealer.setError("Select Type Of  Dealer ");
            ttSelectDealer.requestFocus();
        } else {
            doRegistration();
        }
    }

    private void doRegistration() {
        try {
            showProgress("");
            methodName = "registration";
            RequestParams params = new RequestParams();
            params.put(RequestParamsUtils.USERNAME, etMobileNo.getText().toString());
            params.put(RequestParamsUtils.EMAIL_ID, etEmailId.getText().toString());
            params.put(RequestParamsUtils.PASSWORD, etPassword.getText().toString());
            params.put(RequestParamsUtils.GUID, generateGUID());
            Log.e("doRegistration", params.toString());
            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();
            clientPhotos.addHeader("Content-Type", "application/json");
            clientPhotos.get(new URLs().REGISTRATION_URL, params, new ResponseHandler(RegisterActivity.this, this));

        } catch (Exception e) {
            Log.e("Registration Exception", e.getMessage());
        }

    }


    private void getToken(String userName, String password) {
        try {
            showProgress("");
            methodName = "gettoken";
            RequestParams params = new RequestParams();
            params.put(RequestParamsUtils.CLIENT_ID, "consoleApp");
            params.put(RequestParamsUtils.GRANT_TYPE, "password");
            params.put(RequestParamsUtils.USERNAME, userName);
            params.put(RequestParamsUtils.PASSWORD, password);
            Log.e("getToken", params.toString());
            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();
            clientPhotos.addHeader("Content-Type", "application/x-www-form-urlencoded");
            clientPhotos.addHeader(RequestParamsUtils.AUTHENTICATION_KEY, "Basic Y29uc29sZUFwcDphZG1pbkBhZ3JvdHJhZGVzZXJ2aWNlLmNvbQ==");
            clientPhotos.post(new URLs().GET_TOKEN, params, new ResponseHandler(RegisterActivity.this, this));

        } catch (Exception e) {
            Log.e("RegigetToken Exception", e.getMessage());
        }

    }


    public String generateGUID() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }

    @Override
    public void onResponse(String response) {

        if (methodName.equals("registration")) {
            try {
                dismissProgress();

                Log.e("ResponseHandler", "onSuccess" + response);

                if (response != null && response.length() > 0) {


                    BasicResponse basicResponseRider = new Gson().fromJson(
                            response, new TypeToken<BasicResponse>() {
                            }.getType());

                    if (basicResponseRider.status) {

                        dismissProgress();
                        getToken(etMobileNo.getText().toString(), etPassword.getText().toString());


                    } else {

                    }

                }
            } catch (Exception e) {
                Log.e("Response Exception", e.getMessage());
            }
        } else {
            try {
                dismissProgress();

                Log.e("ResponseHandler", "onSuccess" + response);

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
                    Intent intent = new Intent(RegisterActivity.this, Dealer_Registration_Step_OneActivity.class);
                    startActivity(intent);
                }
            } catch (Exception e) {
                Log.e("Response Exception", e.getMessage());
            }
        }

    }


    @OnClick(R.id.ttRegister)
    public void ttRegisterClick() {
        isValidData();
    }
}
