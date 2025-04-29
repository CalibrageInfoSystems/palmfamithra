package in.calibrage.palm360fa.Views.Activities;

import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.BuildConfig;
import in.calibrage.palm360fa.Model.LoginRequest;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.Constants;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class LoginActivity extends BaseActivity {
    private EditText userNameEdt, passwordEdt;
    private Button  loginBtn ;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    AppCompatTextView app_version;
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
        setContentView(R.layout.activity_login);
        /* intializing and assigning ID's */
        initViews();
        /* Navigation's and using the views */
        setViews();
    }

    private void initViews() {
        app_version = findViewById(R.id.app_version);
        loginBtn =  findViewById(R.id.loginBtn);
        userNameEdt =  findViewById(R.id.username_edittxt);
        passwordEdt = findViewById(R.id.password_edittxt);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
    }
    private void setViews() {
        String versionName = BuildConfig.VERSION_NAME;
        app_version.setText(getResources().getString(R.string.App_version)+ " "+versionName);



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    if (validations()) {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                /* Create an Intent that will start the Menu-Activity. */
//                                Intent i = new Intent( LoginActivity.this, HomeActivity.class);
//
//                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                                startActivity(i);
//                                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
//
//                                finish();
//                            }
//                        }, 300);


                       FA_Login();
                    }
                }
                else {
                    showDialog(LoginActivity.this, getResources().getString(R.string.Internet));
                }


            }
        });
    }
    private boolean validations() {
        if (TextUtils.isEmpty(userNameEdt.getText().toString())) {
           // userNameEdt.setError(getString(R.string.err_please_enter_username));
            showDialog(LoginActivity.this, getResources().getString(R.string.err_please_enter_username));
            return false;
        } else if (TextUtils.isEmpty(passwordEdt.getText().toString().trim())) {
          //  passwordEdt.setError(getString(R.string.err_please_enter_password));
            showDialog(LoginActivity.this, getResources().getString(R.string.err_please_enter_password));
            return false;
        }
        return true;
    }

    private void FA_Login() {
        mdilogue.show();
        JsonObject object = loginPageObject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getLoginPage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
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
                        showDialog(LoginActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final LoginResponse loginResponse) {

                        if (loginResponse.getIsSuccess()) {
                            loginBtn.setEnabled(false);
                            mdilogue.dismiss();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /* Create an Intent that will start the Menu-Activity. */
                                    SharedPrefsData.putBool(LoginActivity.this, Constants.IS_LOGIN, true);
                                    SharedPrefsData.saveCreatedUser(LoginActivity.this, loginResponse);
                                    SharedPrefsData.getInstance(LoginActivity.this).updateStringValue(LoginActivity.this, Constants.createduser_ID, loginResponse.getResult().getUserInfos().getId()+"");
                                    Log.e("created_useid==", loginResponse.getResult().getUserInfos().getId()+"");
                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                    finish();
                                }
                            }, 300);

                        } else {
                            showDialog(LoginActivity.this, getResources().getString(R.string.Invalid_user));
                        }
                    }


                });}

    private JsonObject loginPageObject() {
        LoginRequest requestModel = new LoginRequest();
        requestModel.setUserName(userNameEdt.getText().toString().trim());
        requestModel.setPassword(passwordEdt.getText().toString().trim());
        requestModel.setRoleId(3);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }
    @Override
    public void onBackPressed() {
        Intent start = new Intent(Intent.ACTION_MAIN);
        start.addCategory(Intent.CATEGORY_HOME);
        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(start);
         //   super.onBackPressed();

           // return;
        }
//    private Boolean exit = false;
//    @Override
//    public void onBackPressed() {
//        if (exit) {
//            finish(); // finish activity
//        } else {
//         //   Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show();
//            exit = true;
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    exit = false;
//                }
//            }, 3 * 1000);
//
//        }
//
//    }
}

