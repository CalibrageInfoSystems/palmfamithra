package in.calibrage.palm360fa.Views.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Model.GetBankDetailsByFarmerCode;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.Constants;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;


public class PaymentActivity extends BaseActivity {
    public static String TAG = "PaymentActivity";
    private TextView accoontHolderName, accoontNumber, bankNamee, branchName, ifscCode;
    private ProgressDialog dialog;
    String Farmer_code;
    private SpotsDialog mdilogue;
    private Subscription mSubscription;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int langID = SharedPrefsData.getInstance(this).getIntFromSharedPrefs("lang");
        if (langID == 2)
            updateResources(this, "te");
        else if (langID == 3)
            updateResources(this, "kan");
        else
            updateResources(this, "en-US");
        setContentView(R.layout.activity_payment);


        dialog = new ProgressDialog(this);
        accoontHolderName = (TextView) findViewById(R.id.tvtext_item_three);
        accoontNumber = (TextView) findViewById(R.id.tvtext_item_five);
        bankNamee = (TextView) findViewById(R.id.tvtext_item_seven);
        branchName = (TextView) findViewById(R.id.tvtext_item_nine);
        ifscCode = (TextView) findViewById(R.id.tvtext_item_eleven);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        ImageView backImg = (ImageView) findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
        Farmer_code = SharedPrefsData.getInstance(this).getStringFromSharedPrefs(Constants.USER_ID);  // Saving string data of your editext
        ImageView home_btn = (ImageView) findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PaymentActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        Button submitBtn = (Button) findViewById(R.id.nextButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
        if (isOnline())
            getBankDetails();
        else {
            showDialog(PaymentActivity.this,getResources().getString(R.string.Internet));
            submitBtn.setBackground(this.getDrawable(R.drawable.button_bg_disable));
            submitBtn.setEnabled(false);
        }
    }

    private void getBankDetails() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getbankdetails(APIConstantURL.GetBankDetailsByFarmerCode + Farmer_code)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetBankDetailsByFarmerCode>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ((HttpException) e).code();
                            ((HttpException) e).message();
                            ((HttpException) e).response().errorBody();
                            try {
                                ((HttpException) e).response().errorBody().string();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                        mdilogue.dismiss();
                        showDialog(PaymentActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetBankDetailsByFarmerCode getBankDetailsByFarmerCode) {
                        SharedPrefsData.savebankdetails(PaymentActivity.this, getBankDetailsByFarmerCode);
                        Log.e("bankdetails==", String.valueOf(SharedPrefsData.getbankdetails(PaymentActivity.this)));
                        String cardName = getBankDetailsByFarmerCode.getListResult().get(0).getAccountHolderName();
                        accoontHolderName.setText(cardName);
                        String bank_Account =  getBankDetailsByFarmerCode.getListResult().get(0).getAccountNumber();
                        String bankName =  getBankDetailsByFarmerCode.getListResult().get(0).getBankName();
                        String branch =  getBankDetailsByFarmerCode.getListResult().get(0).getBranchName();
                        String ifscCode1 =  getBankDetailsByFarmerCode.getListResult().get(0).getIfscCode();
                        accoontNumber.setText(bank_Account);
                        bankNamee.setText(bankName);
                        branchName.setText(branch);
                        ifscCode.setText(ifscCode1);
                    }

                });

//
//        String url = APIConstantURL.LOCAL_URL + "Farmer/GetBankDetailsByFarmerCode/" + Farmer_code;
//        Log.e("url===", url);
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "RESPONSE======" + response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d(TAG, "RESPONSE======" + jsonObject);
//                    if (dialog.isShowing()) {
//                        dialog.dismiss();
//                    }
//                    JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
//                    for (int i = 0; i < alsoKnownAsArray.length(); i++) {
//                        JSONObject leagueData = alsoKnownAsArray.getJSONObject(i);
//                        String cardName = leagueData.getString("accountHolderName");
//                        String bank_Account = leagueData.getString("accountNumber");
//                        String bankName = leagueData.getString("bankName");
//                        String branch = leagueData.getString("branchName");
//                        String ifscCode1 = leagueData.getString("ifscCode");
//                        Log.d(TAG, "RESPONSE cardName======" + cardName);
//                        Log.d(TAG, "RESPONSE bank_Account======" + bank_Account);
//                        accoontHolderName.setText(cardName);
//                        accoontNumber.setText(bank_Account);
//                        bankNamee.setText(bankName);
//                        branchName.setText(branch);
//                        ifscCode.setText(ifscCode1);
//                    }
//
//                    String affectedRecords = jsonObject.getString("affectedRecords");
//                    Log.d(TAG, "RESPONSE getBankDetails======" + affectedRecords);
//                    String success = jsonObject.getString("isSuccess");
//                    Log.d(TAG, "success======" + success);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                if (error instanceof NetworkError) {
//                    Log.i("one:" + TAG, error.toString());
//
//                } else if (error instanceof ServerError) {
//                    Log.i("two:" + TAG, error.toString());
//
//                } else if (error instanceof AuthFailureError) {
//                    Log.i("three:" + TAG, error.toString());
//
//                } else if (error instanceof ParseError) {
//                    Log.i("four::" + TAG, error.toString());
//
//                } else if (error instanceof NoConnectionError) {
//                    Log.i("five::" + TAG, error.toString());
//
//                } else if (error instanceof TimeoutError) {
//                    Log.i("six::" + TAG, error.toString());
//
//                } else {
//                    System.out.println("Checking error in else");
//                }
//            }
//        });
//        int socketTimeout = 30000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(policy);
//        requestQueue.add(stringRequest);
//    }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}

