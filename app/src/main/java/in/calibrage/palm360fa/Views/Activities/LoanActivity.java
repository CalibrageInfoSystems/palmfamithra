package in.calibrage.palm360fa.Views.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;

import in.calibrage.palm360fa.Model.FarmerOtpResponceModel;
import in.calibrage.palm360fa.Model.LoanRequest;
import in.calibrage.palm360fa.Model.LoanResponse;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.MSGmodel;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class LoanActivity extends BaseActivity {
    public static String TAG = LoanActivity.class.getSimpleName();
    CheckBox checkbox;
    String currentDate;
    TextView ok, getTerms;
    TextView terms;
    String Farmer_code;
    ImageView backImg, home_btn;
    Button loan_Btn;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    Dialog myDialog;
    EditText amount, reason;
LoginResponse created_user;
private Integer User_id;
String statename;
    private FarmerOtpResponceModel catagoriesList;
    Integer Cluster_id;
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
        setContentView(R.layout.activity_loan);
        init();
        setViews();
    }

    private void init() {

        checkbox = (CheckBox) findViewById(R.id.checkBox);

        backImg = (ImageView) findViewById(R.id.back);
        terms = (TextView) findViewById(R.id.terms);
        loan_Btn = (Button) findViewById(R.id.req_loan);
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");       // Saving string data of your editext
        home_btn = (ImageView) findViewById(R.id.home_btn);
        amount = (EditText) findViewById(R.id.loan_amount);
        reason = (EditText) findViewById(R.id.reason);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
    }

    private void setViews() {

        catagoriesList = SharedPrefsData.getCatagories(this);
        Log.e("Cluster_id===",catagoriesList.getResult().getFarmerDetails().get(0).getFirstName());
        if (null != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId() && 0 != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId())
            Cluster_id =  catagoriesList.getResult().getFarmerDetails().get(0).getClusterId();
        Log.e("Cluster_id===",Cluster_id+"");
        created_user = SharedPrefsData.getCreatedUser(LoanActivity.this);
        statename = catagoriesList.getResult().getFarmerDetails().get(0).getStateName();
     User_id= created_user.getResult().getUserInfos().getId();
       Log.e("created_user===", String.valueOf(User_id));
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myDialog = new Dialog(LoanActivity.this);
                    showCustomDialog();
                }

            }
        });


        loan_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amount.getText() != null & amount.getText().toString().trim() != "" & !TextUtils.isEmpty(amount.getText())) {
                    if (Double.parseDouble(amount.getText().toString()) != 0.0) {
                        if (checkbox.isChecked()) {
                            if (isOnline())
                                GetLoanDetails();
                            else {
                                showDialog(LoanActivity.this, getResources().getString(R.string.Internet));

                            }

                        } else {
                            showDialog(LoanActivity.this, getResources().getString(R.string.terms_agree));


                        }
                    } else {
                        showDialog(LoanActivity.this, getString(R.string.enter_loan));
                    }
                } else {
                    showDialog(LoanActivity.this, getString(R.string.str_enter_loan_amount));
                }


            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(LoanActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Log.i("LOG_RESPONSE date ", currentDate);
        terms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.e("Roja.....", "1==================");
                showCustomDialog();

            }

        });
    }

    private void GetLoanDetails() {
        mdilogue.show();
        JsonObject object = LoanReuestobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.postLoan(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoanResponse>() {
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
                    }

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onNext(LoanResponse loanResponse) {


                        if (loanResponse.getIsSuccess()) {
                            mdilogue.dismiss();
                            loan_Btn.setEnabled(false);
                            String Amount = amount.getText().toString();
                            String Reason = reason.getText().toString();
                            List<MSGmodel> displayList = new ArrayList<>();

                            displayList.add(new MSGmodel(getString(R.string.loan_amount), Amount));
                            if (Reason != null && !Reason.isEmpty() && !Reason.equals("null")) {

                                displayList.add(new MSGmodel(getResources().getString(R.string.reason_loan), Reason));
                            }

                            showSuccessDialog(displayList, getResources().getString(R.string.success_Loan));


                        } else {
                            showDialog(LoanActivity.this, loanResponse.getEndUserMessage());
                        }

                    }


                });
    }

    private JsonObject LoanReuestobject() {
        String statecode = SharedPrefsData.getInstance(this).getStringFromSharedPrefs("statecode");
        Log.e("state===",statecode);
        LoanRequest requestModel = new LoanRequest();
        requestModel.setFarmerCode(Farmer_code);
        requestModel.setPlotCode(null);
        requestModel.setIsFarmerRequest(false);
        requestModel.setComments(reason.getText().toString());
        requestModel.setFarmerName(SharedPrefsData.getusername(this));
        requestModel.setCreatedByUserId(User_id);
        requestModel.setUpdatedByUserId(User_id);
        requestModel.setCreatedDate(currentDate);
        requestModel.setStateCode(statecode);
        requestModel.setStateName(statename);
        requestModel.setClusterId(Cluster_id);
        requestModel.setUpdatedDate(currentDate);
        requestModel.setRequestCreatedDate(currentDate);
        requestModel.setCropMaintainceDate(null);
        requestModel.setStatusTypeId(15);
        requestModel.setRequestTypeId(28);
        requestModel.setTotalCost(Double.parseDouble(amount.getText().toString()));
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.loan_dialog);
        // Set dialog title
        dialog.setTitle("Custom Dialog");

        ok = (TextView) dialog.findViewById(R.id.ok);

        getTerms = (TextView) dialog.findViewById(R.id.txtclose);
        //  image.setImageResource(R.drawable.ic_action_duration);

        dialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        getTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }


}

