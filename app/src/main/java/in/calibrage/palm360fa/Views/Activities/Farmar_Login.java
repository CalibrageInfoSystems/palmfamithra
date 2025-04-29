package in.calibrage.palm360fa.Views.Activities;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Model.FarmerOtpResponceModel;
import in.calibrage.palm360fa.Model.FarmerResponceModel;
import in.calibrage.palm360fa.Model.GetServicesByStateCode;
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

public class Farmar_Login extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private Button loginBtn, Qr_scan;
    public static EditText farmerId;
    private String Farmer_code;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    String mobile_number;
    private Button ok_btn, cancel_btn;
    boolean doubleBackToExitPressedOnce = false;
    private int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    TextView fa_logout;
    String Request_Type,statecode,Statename;
    TextView dialogMessage;
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
        setContentView(R.layout.activity_farmar__login);
        init();
        setview();
    }
    private void init() {

        loginBtn = (Button) findViewById(R.id.btn_login);
        Qr_scan = (Button) findViewById(R.id.btn_qrscan1);
        farmerId = findViewById(R.id.farmer_id_edittxt);
        fa_logout =findViewById(R.id.fa_logout);

        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        validationPopShow();
    }
    private void setview() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Request_Type = extras.getString("RequestType");
            Log.e("Request_Type===", Request_Type);
        }
            loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (farmerId.getText() != null & farmerId.getText().toString().trim() != "" & !TextUtils.isEmpty(farmerId.getText())) {
                    Farmer_code = farmerId.getText().toString().replaceAll(" ", "");
                    Log.e("former==id", Farmer_code);

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("FARMER", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("farmerid", Farmer_code);  // Saving string data of your editext
                    editor.commit();
                    if (isOnline()) {
                        if (Request_Type.equalsIgnoreCase("special") || Request_Type.equalsIgnoreCase("Labour Request") ||
                                Request_Type.equalsIgnoreCase("Collection") || Request_Type.equalsIgnoreCase("Fertilizer") || Request_Type.equalsIgnoreCase("edibleoil") ||
                                Request_Type.equalsIgnoreCase("Equipment")|| Request_Type.equalsIgnoreCase("BioLab")
                        ) {

                            specialmethod();

                        } else {
                            GetLogin();
                        }

                    } else {
                        showDialog(Farmar_Login.this, getResources().getString(R.string.Internet));
                    }
                }
                 else {
                    showDialog(Farmar_Login.this, getResources().getString(R.string.farmar_id));
                }
            }
        });


        fa_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               logoutpoup();
            }
            });

                Qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(Farmar_Login.this,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(Farmar_Login.this, QRScannerActivity.class));

                } else {
                    ActivityCompat.requestPermissions((Farmar_Login) Farmar_Login.this,
                            new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }

        });
    }

    private void logoutpoup() {

        final Dialog dialog = new Dialog(Farmar_Login.this, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_logout);
        dialogMessage = dialog.findViewById(R.id.dialogMessage);
        dialogMessage.setText(getString(R.string.alert_logout));
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        ok_btn = dialog.findViewById(R.id.ok_btn);
/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // updateResources(getApplicationContext(), "en-US");
                //  SharedPrefsData.getInstance(getApplicationContext()).ClearData(getApplicationContext());
                SharedPrefsData.putBool(Farmar_Login.this, Constants.IS_LOGIN, false);
                Intent intent = new Intent(Farmar_Login.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

/**
 * @param OnClickListner
 */
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void GetLogin() {
        if (null != mdilogue)
            mdilogue.show();
        else {

            mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                    .setContext(this)
                    .setTheme(R.style.Custom)
                    .build();
            mdilogue.show();
        }
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getFormerOTP(APIConstantURL.Farmer_ID_CHECK + farmerId.getText().toString()+"/"+Request_Type)
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
                        showDialog(Farmar_Login.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(FarmerResponceModel farmerResponceModel) {

                        Log.d(TAG, "onNext: " + farmerResponceModel);
                        if (farmerResponceModel.getIsSuccess()) {


                                if (farmerResponceModel.getResult() != null) {
                                    mobile_number = farmerResponceModel.getResult();

                                    Log.d("mobile_number===", mobile_number);
                                } else {
                                    showDialog(Farmar_Login.this, "No Register Mobile Number for Send Otp");
                                }
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        /* Create an Intent that will start the Menu-Activity. */
                                        Intent i = new Intent(Farmar_Login.this, OtpActivity.class);
                                        i.putExtra("mobile", mobile_number);
                                        i.putExtra("request_type", Request_Type);
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(i);
                                        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

                                        finish();
                                    }
                                }, 300);

                            } else{
                                showDialog(Farmar_Login.this, getResources().getString(R.string.Invalid));
                            }

                    }
                });


    }

    private void specialmethod() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getFormerdetails(APIConstantURL.Farmer_otp +  farmerId.getText().toString() + "/" + null+"/"+true)
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
                        showDialog(Farmar_Login.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final FarmerOtpResponceModel farmerOtpResponceModel) {

                        if (farmerOtpResponceModel.getIsSuccess()) {


                            loginBtn.setEnabled(false);
                            SharedPrefsData.putBool(Farmar_Login.this, Constants.IS_Former_LOGIN, true);
                            SharedPrefsData.saveFormerDetails(Farmar_Login.this, farmerOtpResponceModel);
                              SharedPrefsData.getInstance(Farmar_Login.this).updateStringValue(Farmar_Login.this, Constants.USER_ID, farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getCode());
                            Log.e("Formarcode==", farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getCode());
                            SharedPrefsData.getInstance(Farmar_Login.this).updateStringValue(Farmar_Login.this, "statecode", farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getStateCode());

                        statecode =    farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getStateCode();
                            Statename =farmerOtpResponceModel.getResult().getFarmerDetails().get(0).getStateName();
                            Log.e("Formarcode==", statecode);

                            GetServicesByStateCode(statecode,Statename);

                            if (Request_Type.equalsIgnoreCase("Collection")) {
                                Intent intent = new Intent(getApplicationContext(), CollectionsActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                                finish();
                            }

                        }
                        else{
                            showDialog(Farmar_Login.this, getResources().getString(R.string.Invalid));
                        }

                    }});}
    private void GetServicesByStateCode(String statecode, final String statename) {

        Log.e("state===",statecode);
        final ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getservices(APIConstantURL.GetServicesByStateCode+statecode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetServicesByStateCode>() {
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
                        showDialog(Farmar_Login.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetServicesByStateCode getServicesByStateCode) {
                        mdilogue.cancel();
//                        if (getServicesByStateCode.getListResult() != null && getServicesByStateCode.getListResult().size()!= 0 ) {
//
//                            for (int i = 0; i < getServicesByStateCode.getListResult().size(); i++) {
//
//                                if (Request_Type.equalsIgnoreCase("Labour Request") && getServicesByStateCode.getListResult().get(i).getServiceTypeId() == 11) {
//
//                                    Intent intent = new Intent(getApplicationContext(), LabourRecommendationsActivity.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                                    startActivity(intent);
//                                    finish();
//
//                                }
//                                else if (Request_Type.equalsIgnoreCase("Fertilizer")&& getServicesByStateCode.getListResult().get(i).getServiceTypeId() == 12) {
//                                    Intent intent = new Intent(getApplicationContext(), Godown_list.class);
//                                    intent.putExtra("godown", "fert");
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                                else if (Request_Type.equalsIgnoreCase("Equipment")&& getServicesByStateCode.getListResult().get(i).getServiceTypeId() == 10) {
//                                Intent intent = new Intent(getApplicationContext(), Godown_list.class);
//                                intent.putExtra("godown","pole");
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                                startActivity(intent);
//                                finish();
//                            }
//                                else if (Request_Type.equalsIgnoreCase("BioLab")&& getServicesByStateCode.getListResult().get(i).getServiceTypeId() == 107) {
//                                    Intent intent = new Intent(getApplicationContext(), Godown_list.class);
//                                    intent.putExtra("godown","bioLab");
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                                else if (Request_Type.equalsIgnoreCase("edibleoil")&& getServicesByStateCode.getListResult().get(i).getServiceTypeId() == 116) {
//                                    Intent intent = new Intent(getApplicationContext(), Godown_list.class);
//                                    intent.putExtra("godown", "edible_oil");
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                                    startActivity(intent);
//                                    finish();
//                                }
////
//
////                                if(Request_Type.equalsIgnoreCase("special")&& getServicesByStateCode.getListResult().get(i).getServiceTypeId() == 13) {
////                                    Intent intent = new Intent(getApplicationContext(), Godown_list.class);
////                                    intent.putExtra("godown","pole");
////                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
////                                    startActivity(intent);
////                                    finish();
////                                }
//                                else{
//                                    showDialog(Farmar_Login.this, Request_Type+" service was not available for the" + statename);
//                                }
////}
//                            }
//                    } else{
//                            showDialog(Farmar_Login.this, Request_Type+" service was not available for the" + statename);
//                        }
                        boolean isRequestTypeAvailable = false;

                        if (getServicesByStateCode.getListResult() != null && !getServicesByStateCode.getListResult().isEmpty()) {
                            for (int i = 0; i < getServicesByStateCode.getListResult().size(); i++) {
                                int serviceTypeId = getServicesByStateCode.getListResult().get(i).getServiceTypeId();

                                if (Request_Type.equalsIgnoreCase("Labour Request") && serviceTypeId == 11) {
                                    navigateToActivity(LabourRecommendationsActivity.class);
                                    isRequestTypeAvailable = true;
                                    break;
                                } else if (Request_Type.equalsIgnoreCase("Fertilizer") && serviceTypeId == 12) {
                                    navigateToGodown("fert");
                                    isRequestTypeAvailable = true;
                                    break;
                                } else if (Request_Type.equalsIgnoreCase("Equipment") && serviceTypeId == 10) {
                                    navigateToGodown("pole");
                                    isRequestTypeAvailable = true;
                                    break;
                                } else if (Request_Type.equalsIgnoreCase("BioLab") && serviceTypeId == 107) {
                                    navigateToGodown("bioLab");
                                    isRequestTypeAvailable = true;
                                    break;
                                } else if (Request_Type.equalsIgnoreCase("edibleoil") && serviceTypeId == 116) {
                                    navigateToGodown("edible_oil");
                                    isRequestTypeAvailable = true;
                                    break;
                                }
                            }
                        }

                        if (!isRequestTypeAvailable) {
                            showDialog(Farmar_Login.this, Request_Type+" service was not available for the " + statename);
                        }
                    }
                });
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(getApplicationContext(), activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    private void navigateToGodown(String godownType) {
        Intent intent = new Intent(getApplicationContext(), Godown_list.class);
        intent.putExtra("godown", godownType);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            startActivity(new Intent(Farmar_Login.this, QRScannerActivity.class));
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finish();
      //  finishAffinity(); //
    }

}


