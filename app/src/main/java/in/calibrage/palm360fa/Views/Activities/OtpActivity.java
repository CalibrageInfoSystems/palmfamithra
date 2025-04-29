package in.calibrage.palm360fa.Views.Activities;

import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Model.FarmerOtpResponceModel;
import in.calibrage.palm360fa.Model.FarmerResponceModel;
import in.calibrage.palm360fa.Model.GetServicesByStateCode;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.Constants;
import in.calibrage.palm360fa.common.PinEntryEditText;
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

public class OtpActivity extends BaseActivity {
    public static final String TAG = OtpActivity.class.getSimpleName();
    private Subscription mSubscription;
    private Button sub_Btn;
    private String currentDate,Farmer_code;
    private PinEntryEditText pinEntry;
    private ImageView backImg;
    private SpotsDialog mdilogue;
    TextView otp_desc;
    String Reg_mobilenumber,Requested_type;
    TextView resend;
    String F_number,S_number;
    String Request_Type,statecode,Statename;
    @RequiresApi(api = Build.VERSION_CODES.M)
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp);

        init();
        setview();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {

        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String  Device_id= Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.e("deviece==id", Device_id);
        sub_Btn = (Button) findViewById(R.id.btn_otp_login);
        backImg = (ImageView) findViewById(R.id.back);
        pinEntry = findViewById(R.id.txt_pin_entry);
        otp_desc =(TextView)findViewById(R.id.otp_desc);
        resend =findViewById(R.id.resend_otp);
        pinEntry.requestFocus();
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");

    }

    private void setview() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            Reg_mobilenumber = extras.getString("mobile");
            Requested_type = extras.getString("request_type");
            if (Reg_mobilenumber.contains(",")) {
                String[] separated = Reg_mobilenumber.split(",");
                F_number = separated[0].replaceAll("\\d(?=(?:\\D*\\d){4})", "*");
                S_number = separated[1].replaceAll("\\d(?=(?:\\D*\\d){4})", "*");
                otp_desc.setText(getString(R.string.otp_desc) + " " + F_number + "," + S_number);
            } else {
                String number = Reg_mobilenumber.replaceAll("\\d(?=(?:\\D*\\d){4})", "*");
                otp_desc.setText(getString(R.string.otp_desc) + " " + number);
            }

        }
        sub_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pinEntry.getText() != null & pinEntry.getText().toString().trim() != "" & !TextUtils.isEmpty(pinEntry.getText())) {
                    if (isOnline())
                        GetOtp();
                    else {
                        showDialog(OtpActivity.this, getResources().getString(R.string.Internet));
                    }
                } else {
                    showDialog(OtpActivity.this, getResources().getString(R.string.ente_pin));
                    //pinEntry.setError("Please Enter Pin");
                }
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendotp();
            }

            private void resendotp() {
                mdilogue.show();
                ApiService service = ServiceFactory.createRetrofitService(OtpActivity.this, ApiService.class);
                mSubscription = service.getFormerOTP(APIConstantURL.Farmer_ID_CHECK + Farmer_code+"/"+Requested_type)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FarmerResponceModel>() {
                            @Override
                            public void onCompleted() {
                                mdilogue.cancel();
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
                                showDialog(OtpActivity.this, getString(R.string.server_error));
                            }

                            @Override
                            public void onNext(FarmerResponceModel farmerResponceModel) {
                                mdilogue.cancel();
                                Log.d(TAG, "onNext: " + farmerResponceModel);
                                if (farmerResponceModel.getIsSuccess()) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.otpsuccess), Toast.LENGTH_LONG).show();
                                    //  showDialog(OtpActivity.this, "Otp sent to Your Register Mobile Number(s)");
//                            if (farmerResponceModel.getResult()!=null) {
//                                mobile_number = farmerResponceModel.getResult();
//
//                                Log.d("mobile_number===", mobile_number);
//                            }
//                            else {
//                                showDialog(LoginActivity.this, "No Register Mobile Number for Send Otp");
//                            }

                                } else {
                                    showDialog(OtpActivity.this, getResources().getString(R.string.Invalid));
                                }
                            }
                        });

            }
        });

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void GetOtp() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getFormerdetails(APIConstantURL.Farmer_otp + Farmer_code + "/" + pinEntry.getText().toString()+"/"+false)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FarmerOtpResponceModel>() {
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
                        showDialog(OtpActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final FarmerOtpResponceModel farmerOtpResponceModel) {
                        mdilogue.dismiss();
                        if (farmerOtpResponceModel.getIsSuccess()) {


                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /* Create an Intent that will start the Menu-Activity. */
                                    SharedPrefsData.putBool(OtpActivity.this, Constants.IS_Former_LOGIN, true);
                                    SharedPrefsData.saveFormerDetails(OtpActivity.this, farmerOtpResponceModel);
                                    SharedPrefsData.getInstance(OtpActivity.this).updateStringValue(OtpActivity.this, Constants.USER_ID, farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getCode());
                                    Log.e("Formarcode==", farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getCode());
                                    SharedPrefsData.getInstance(OtpActivity.this).updateStringValue(OtpActivity.this, "statecode", farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getStateCode());
                                    SharedPrefsData.getInstance(OtpActivity.this).updateIntValue(OtpActivity.this, "districtId", farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getDistrictId());
                                    SharedPrefsData.getInstance(OtpActivity.this).updateStringValue(OtpActivity.this, "districtName", farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getDistrictName());

                                    if (Requested_type.contains("Payment")) {
                                        Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);
                                        finish();
                                    }
                                    if (Requested_type.contains("crop")) {
                                        Intent intent = new Intent(getApplicationContext(), RecommendationActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);
                                        finish();
                                    }

//                                    if (Requested_type.contains("Labour Request")) {
//                                        Intent intent = new Intent(getApplicationContext(), LabourRecommendationsActivity.class);
//                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                                        startActivity(intent);
//                                        finish();
//                                    }
                                    statecode =    farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getStateCode();
                                    Statename =farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getStateName();
                                    Log.e("Formarcode==", statecode);

                                    GetServicesByStateCode(statecode,Statename);


                                }

                            }, 000);

                        } else {
                            showDialog(OtpActivity.this, farmerOtpResponceModel.getEndUserMessage());
                        }
                    }


                });


    }

    private void GetServicesByStateCode(String statecode, final String statename) {
        Log.e("state===", statecode);
        final ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);

        mSubscription = service.getservices(APIConstantURL.GetServicesByStateCode + statecode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetServicesByStateCode>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mdilogue.dismiss();
                        if (e instanceof HttpException) {
                            try {
                                Log.e("API Error", ((HttpException) e).response().errorBody().string());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        showDialog(OtpActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetServicesByStateCode getServicesByStateCode) {
                        mdilogue.cancel();
                        boolean isRequestTypeAvailable = false;

                        if (getServicesByStateCode.getListResult() != null && !getServicesByStateCode.getListResult().isEmpty()) {
                            for (int i = 0; i < getServicesByStateCode.getListResult().size(); i++) {
                                int serviceTypeId = getServicesByStateCode.getListResult().get(i).getServiceTypeId();

                                if (Requested_type.contains("QuickPay") && serviceTypeId == 13) {
                                    navigateToActivity(QuickPayActivity.class);
                                    isRequestTypeAvailable = true;
                                    break;
                                } else if (Requested_type.contains("Visit") && serviceTypeId == 14) {
                                    navigateToActivity(RequestVisitActivity.class);
                                    isRequestTypeAvailable = true;
                                    break;
                                } else if (Requested_type.contains("Loan") && serviceTypeId == 28) {
                                    navigateToActivity(LoanActivity.class);
                                    isRequestTypeAvailable = true;
                                    break;
                                } else if (Requested_type.contains("Transport") && serviceTypeId == 108) {
                                    navigateToActivity(TransportplotActivity.class);
                                    isRequestTypeAvailable = true;
                                    break;
                                }
                            }
                        }

                        // If no matching request type is found, show an error message
                        if (!isRequestTypeAvailable) {
                            showDialog(OtpActivity.this, Requested_type+" service was not available for the " + statename);
                        }
                    }
                });
    }

    // Helper function to launch an activity
    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(getApplicationContext(), activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }




    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
    }

}
