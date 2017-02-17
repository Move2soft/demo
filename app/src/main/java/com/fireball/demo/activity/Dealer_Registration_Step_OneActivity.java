package com.fireball.demo.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fireball.demo.Listener.ResponseListener;
import com.fireball.demo.R;
import com.fireball.demo.adapter.BlockAdapter;
import com.fireball.demo.adapter.DistrictAdapter;
import com.fireball.demo.adapter.SpinnerAdapter;
import com.fireball.demo.adapter.StateAdapter;
import com.fireball.demo.model.Block;
import com.fireball.demo.model.District;
import com.fireball.demo.model.Spinner;
import com.fireball.demo.model.State;
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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dealer_Registration_Step_OneActivity extends BaseActivity implements ResponseListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tvState)
    TextView tvState;
    @Bind(R.id.tvDistrict)
    TextView tvDistrict;
    @Bind(R.id.tvBlock)
    TextView tvBlock;
    @Bind(R.id.etFullName)
    EditText etFullName;
    @Bind(R.id.etHouseNo)
    EditText etHouseNo;
    @Bind(R.id.etStreetName)
    EditText etStreetName;
    @Bind(R.id.etLandMark)
    EditText etLandMark;
    @Bind(R.id.etPincode)
    EditText etPincode;
    @Bind(R.id.etPlace)
    EditText etPlace;
    @Bind(R.id.etEmailId)
    EditText etEmailId;
    @Bind(R.id.etSTdCode)
    EditText etSTdCode;
    @Bind(R.id.etPhoneNo)
    EditText etPhoneNo;
    @Bind(R.id.etKeyContactPerson)
    EditText etKeyContactPerson;
    @Bind(R.id.etMobileNo)
    EditText etMobileNo;


    private String methodName;
    private List<State.Output> stateList;
    private List<District.Output> districtList;
    private List<Block.Output> blockList;
    private int stateId;
    private String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer);
        ButterKnife.bind(this);
        getState();
    }

    @OnClick(R.id.ivDrawer)
    public void ivDrawerClick() {
        onBackPressed();
    }

    @OnClick(R.id.tvNext)
    public void tvNextClick() {

        isValidData();
    }

    @OnClick(R.id.tvState)
    public void tvStateClick() {
        showDealerDialog("Select State", stateList, tvState);
    }

    @OnClick(R.id.tvBlock)
    public void tvBlockClick() {
        showBlockDialog("Select Block", blockList, tvBlock);
    }


    @OnClick(R.id.tvDistrict)
    public void tvDistrictClick() {
        showDistrictDialog("Select District", districtList, tvDistrict);
    }

    private void isValidData() {

        String varemail = etEmailId.getText().toString();


        if (etFullName.getText().toString().trim().length() == 0) {
            etFullName.setError("Enter Full Name");
            etFullName.requestFocus();

        } else if (etHouseNo.getText().toString().trim().length() == 0) {
            etHouseNo.setError("Enter House No");
            etHouseNo.requestFocus();

        } else if (etStreetName.getText().toString().trim().length() == 0) {
            etStreetName.setError("Enter Stree Name");
            etStreetName.requestFocus();

        } else if (etLandMark.getText().toString().trim().length() == 0) {
            etLandMark.setError("Enter LandMark");
            etLandMark.requestFocus();

        } else if (etPincode.getText().toString().trim().length() == 0) {
            etPincode.setError("Enter Pincode");
            etPincode.requestFocus();

        } else if (etPincode.getText().toString().trim().length() != 6) {
            etPincode.setError("Invalid Pincode");
            etPincode.requestFocus();

        } else if (etPlace.getText().toString().trim().length() == 0) {
            etPlace.setError("Enter Place");
            etPlace.requestFocus();

        } else if (tvState.getText().toString().trim().length() == 0) {
            tvState.setError("Select State");
            tvState.requestFocus();

        } else if (tvDistrict.getText().toString().trim().length() == 0) {
            tvDistrict.setError("Select District");
            tvDistrict.requestFocus();

        } else if (tvBlock.getText().toString().trim().length() == 0) {
            tvBlock.setError("Select Block");
            tvBlock.requestFocus();

        } else if (etEmailId.getText().toString().trim().length() == 0) {
            etEmailId.setError("Enter Email id");
            etEmailId.requestFocus();

        } else if (!varemail.matches(EMAIL_PATTERN)) {
            etEmailId.setError("Invalid Email");
            etEmailId.requestFocus();
        } else if (etSTdCode.getText().toString().trim().length() == 0) {
            etSTdCode.setError("Enter STD Code");
            etSTdCode.requestFocus();
        } else if (etPhoneNo.getText().toString().trim().length() == 0) {
            etPhoneNo.setError("Enter Phone No");
            etPhoneNo.requestFocus();

        } else if (etKeyContactPerson.getText().toString().trim().length() == 0) {
            etKeyContactPerson.setError("Enter Key Contact Person");
            etKeyContactPerson.requestFocus();

        } else if (etMobileNo.getText().toString().trim().length() == 0) {
            etMobileNo.setError("Enter Mobile Number");
            etMobileNo.requestFocus();
        } else {
            Intent intent = new Intent(Dealer_Registration_Step_OneActivity.this, Dealer_Registration_Step_TwoActivity.class);
            startActivity(intent);
        }


    }

    private void showDealerDialog(final String title,
                                  final List<State.Output> data, final TextView tv) {

        final Dialog a = new Dialog(this);
        Window w = a.getWindow();
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.setContentView(R.layout.spinner_listview);
        w.setBackgroundDrawableResource(android.R.color.transparent);

        TextView lblselect = (TextView) w.findViewById(R.id.dialogtitle);
        lblselect.setText(title.replace("*", "").trim());

        StateAdapter adapter = new StateAdapter(this);
        ListView lv = (ListView) w.findViewById(R.id.lvSpinner);
        lv.setAdapter(adapter);
        adapter.addAll(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterview, View view,
                                    int position, long l) {
                tv.setText(data.get(position).name);
                stateId = data.get(position).id;
                getDistrict(stateId);
                a.dismiss();
            }
        });
        a.show();
    }


    private void showDistrictDialog(final String title,
                                    final List<District.Output> data, final TextView tv) {

        final Dialog a = new Dialog(this);
        Window w = a.getWindow();
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.setContentView(R.layout.spinner_listview);
        w.setBackgroundDrawableResource(android.R.color.transparent);

        TextView lblselect = (TextView) w.findViewById(R.id.dialogtitle);
        lblselect.setText(title.replace("*", "").trim());

        DistrictAdapter adapter = new DistrictAdapter(this);
        ListView lv = (ListView) w.findViewById(R.id.lvSpinner);
        lv.setAdapter(adapter);
        adapter.addAll(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterview, View view,
                                    int position, long l) {
                tv.setText(data.get(position).name);
                getBlock(data.get(position).stateId, data.get(position).id);
                a.dismiss();
            }
        });
        a.show();
    }


    private void showBlockDialog(final String title,
                                 final List<Block.Output> data, final TextView tv) {

        final Dialog a = new Dialog(this);
        Window w = a.getWindow();
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.setContentView(R.layout.spinner_listview);
        w.setBackgroundDrawableResource(android.R.color.transparent);

        TextView lblselect = (TextView) w.findViewById(R.id.dialogtitle);
        lblselect.setText(title.replace("*", "").trim());

        BlockAdapter adapter = new BlockAdapter(this);
        ListView lv = (ListView) w.findViewById(R.id.lvSpinner);
        lv.setAdapter(adapter);
        adapter.addAll(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterview, View view,
                                    int position, long l) {
                tv.setText(data.get(position).name);
                a.dismiss();
            }
        });
        a.show();
    }

    private void getState() {
        try {
            showProgress("");
            methodName = "getState";
            RequestParams params = new RequestParams();
            Log.e("getState", params.toString());
            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();
            clientPhotos.addHeader("Content-Type", "application/json");
//            clientPhotos.addHeader(RequestParamsUtils.AUTHENTICATION_KEY, Constants.API_KEY_VALUE);

            clientPhotos.addHeader(RequestParamsUtils.AUTHENTICATION_KEY, getPreferences().getString(RequestParamsUtils.TOKEN_TYPE, "") + " " + getPreferences().getString(RequestParamsUtils.TOKEN, ""));
            clientPhotos.get(new URLs().GET_STATE, params, new ResponseHandler(Dealer_Registration_Step_OneActivity.this, this));

        } catch (Exception e) {
            Log.e("getCategory Exception", e.getMessage());
        }

    }


    private void getDistrict(int stateId) {
        try {
            showProgress("");
            methodName = "getDistrict";
            RequestParams params = new RequestParams();
            params.put(RequestParamsUtils.STATE_ID, stateId + "");
            Log.e("getDistrict", params.toString());
            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();
            clientPhotos.addHeader("Content-Type", "application/json");
            clientPhotos.addHeader(RequestParamsUtils.AUTHENTICATION_KEY, getPreferences().getString(RequestParamsUtils.TOKEN_TYPE, "") + " " + getPreferences().getString(RequestParamsUtils.TOKEN, ""));
            clientPhotos.get(new URLs().GET_DISTRICT, params, new ResponseHandler(Dealer_Registration_Step_OneActivity.this, this));

        } catch (Exception e) {
            Log.e("getCategory Exception", e.getMessage());
        }

    }


    private void getBlock(int stateId, int DistrictId) {
        try {
            showProgress("");
            methodName = "getBlock";
            RequestParams params = new RequestParams();
            params.put(RequestParamsUtils.STATE_ID, stateId + "");
            params.put(RequestParamsUtils.DISTRICT_ID, DistrictId + "");
            Log.e("getDistrict", params.toString());
            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();
            clientPhotos.addHeader("Content-Type", "application/json");
            clientPhotos.addHeader(RequestParamsUtils.AUTHENTICATION_KEY, getPreferences().getString(RequestParamsUtils.TOKEN_TYPE, "") + " " + getPreferences().getString(RequestParamsUtils.TOKEN, ""));
            clientPhotos.get(new URLs().GET_BLOCK, params, new ResponseHandler(Dealer_Registration_Step_OneActivity.this, this));

        } catch (Exception e) {
            Log.e("getCategory Exception", e.getMessage());
        }

    }


    @Override
    public void onResponse(String response) {
        dismissProgress();


        if (methodName.equals("getState")) {

            try {
                Log.e("Response getState", "onSuccess" + response);

                if (response != null && response.length() > 0) {


                    State stateRider = new Gson().fromJson(
                            response, new TypeToken<State>() {
                            }.getType());

                    if (stateRider.status) {
                        stateList = stateRider.output;
                    }
                }
            } catch (Exception e) {
                Log.e("Response Exception", e.getMessage());
            }

        } else if (methodName.equals("getDistrict")) {
            try {

                Log.e("ResponseHandler", "getDistrict" + response);

                if (response != null && response.length() > 0) {

                    District districtRider = new Gson().fromJson(
                            response, new TypeToken<District>() {
                            }.getType());

                    if (districtRider.status) {
                        districtList = districtRider.output;
                    } else {

                    }

                }
            } catch (Exception e) {
                Log.e("Response Exception", e.getMessage());
            }
        } else if (methodName.equals("getBlock")) {
            try {

                Log.e("ResponseHandler", "getDistrict" + response);

                if (response != null && response.length() > 0) {

                    Block blockRider = new Gson().fromJson(
                            response, new TypeToken<Block>() {
                            }.getType());

                    if (blockRider.status) {
                        blockList = blockRider.output;
                    } else {

                    }

                }
            } catch (Exception e) {
                Log.e("Response Exception", e.getMessage());
            }
        }

    }
}
