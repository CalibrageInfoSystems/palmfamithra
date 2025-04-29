package in.calibrage.palm360fa.Views.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;

import in.calibrage.palm360fa.Adapter.QuickPayDataAdapter;
import in.calibrage.palm360fa.Model.QuickPayModel;
import in.calibrage.palm360fa.Model.QuickpayBlockdateRequest;
import in.calibrage.palm360fa.Model.QuickpayBlockdateResponse;
import in.calibrage.palm360fa.Model.RaiseRequest;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
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

public class QuickPayActivity extends BaseActivity implements QuickPayDataAdapter.quick_paylistener {

    private RecyclerView recyclerView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private QuickPayDataAdapter adapter;
    private int checkedcount = 0;
    private Subscription mSubscription;
    private List<QuickPayModel> studentList;
    public static String TAG = "QuickPayActivity";
    private Button btnSelection;
    String datetimevaluereq, Farmer_code;
    TextView no_unpaid_collections;
    Button nextButton;
    TextView noRecords;
    String currentDate;
    private SpotsDialog mdilogue;
    ImageView backImg, home_btn;
    private List<QuickPayModel.ListResult> collection_list = new ArrayList<>();
    List<String> ids_list = new ArrayList<>();
    List<String> dates_list = new ArrayList<>();
    List<Double> weight_list = new ArrayList<>();
    int SPLASH_DISPLAY_DURATION = 500;
    String w_Code,statecode,districtName,stateName;
    int districtId;

    String cstatecode;
    int cdistrictId;
    private QuickPayModel quickPayModelData;

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
        setContentView(R.layout.activity_quick_pay);

        init();
        setViews();

    }
    private void init() {

        backImg = (ImageView) findViewById(R.id.back);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        recyclerView = (RecyclerView) findViewById(R.id.quick_recycler_view);

        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();

        noRecords = (TextView) findViewById(R.id.no_text);
        nextButton = (Button) findViewById(R.id.nextButton);
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");
    }
    private void setViews() {

        if (isOnline()){
            getQuckPay();}
        else {
            showDialog(QuickPayActivity.this, getResources().getString(R.string.Internet));
        }
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CanRaiseRequest();


                //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
//        if (isOnline()) {
//            getQuckPay();
//            nextButton.setVisibility(View.VISIBLE);
//        }
//        else {
//            showDialog(QuickPayActivity.this,getResources().getString(R.string.Internet));
//            nextButton.setVisibility(View.VISIBLE);
//        }



    }

//    private void IsQuickPayBlockDate() {
//        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        mdilogue.show();
//        String statecode = SharedPrefsData.getInstance(this).getStringFromSharedPrefs("statecode");
//        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
//        mSubscription = service.getblockdate(APIConstantURL.IsQuickPayBlockDate +statecode+"/"+"true"+"/"+currentDate)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<IsQuickPayBlockDate>() {
//                    @Override
//                    public void onCompleted() {
//                        mdilogue.dismiss();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof HttpException) {
//                            ((HttpException) e).code();
//                            ((HttpException) e).message();
//                            ((HttpException) e).response().errorBody();
//                            try {
//                                ((HttpException) e).response().errorBody().string();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            }
//                            e.printStackTrace();
//                        }
//                        mdilogue.dismiss();
//                        //   showDialog(QuickPayActivity.this, getString(R.string.server_error));
//                    }
//
//                    @Override
//                    public void onNext(IsQuickPayBlockDate isQuickPayBlockDate) {
//
//
//                        if (isQuickPayBlockDate.getIsSuccess()){
//                            if(isQuickPayBlockDate.getResult().equals(true)) {
//                                if (isOnline()) {
//                                    getQuckPay();
//                                    nextButton.setVisibility(View.VISIBLE);
//                                } else {
//                                    showDialog(QuickPayActivity.this, getResources().getString(R.string.Internet));
//                                    nextButton.setVisibility(View.VISIBLE);
//                                }
//                            }
//
//                        }
//                        else {
//                            // edittext.getText().clear();Quick pay has already been requested, another quick pay request cannot be requested until the 1st one is completed.
//                            showDialogf(QuickPayActivity.this, isQuickPayBlockDate.getEndUserMessage());
//
//                        }
//                    }
//                });
//    }

    public void showDialogf(Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        final ImageView img = dialog.findViewById(R.id.img_cross);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);
        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Animatable) img.getDrawable()).start();
            }
        }, 500);
    }

    private void CanRaiseRequest() {
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getRaiseRequest(APIConstantURL.CanRaiseRequest + Farmer_code + "/" + null + "/" + 13)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RaiseRequest>() {
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
                        //   showDialog(QuickPayActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(RaiseRequest raiseRequest) {

                        if (raiseRequest.getIsSuccess()){
                            Log.e("test=== ",raiseRequest.getIsSuccess()+"");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    Intent intent = new Intent(getApplicationContext(), Quickpay_SummaryActivity.class);
                                    intent.putExtra("collection_ids", (Serializable) ids_list);

                                    intent.putExtra("collection_dates", (Serializable) dates_list);
                                    intent.putExtra("collection_weight", (Serializable) weight_list);
                                    intent.putExtra("whsCode",w_Code);

                                    intent.putExtra("ccstatename", stateName);
                                    intent.putExtra("ccstatecode",  statecode);
                                    intent.putExtra("ccdistrictname",districtName);
                                    intent.putExtra("ccdistrictid",districtId);

                                    startActivity(intent);

                                    Log.e("ids_list===", String.valueOf(ids_list));
                                    Log.e("dates_list===", String.valueOf(dates_list));
                                    Log.e("weight_list===", String.valueOf(weight_list));
                                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);


                                }
                            }, SPLASH_DISPLAY_DURATION);

                        }
                        else {
                            // edittext.getText().clear();Quick pay has already been requested, another quick pay request cannot be requested until the 1st one is completed.
                            showDialog(QuickPayActivity.this,  getResources().getString(R.string.quick_reqc));

                        }
                    }
                });
    }


    private void getQuckPay() {
        mdilogue.show();

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getquick(APIConstantURL.GetUnPayedCollectionsByFarmerCode + Farmer_code)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QuickPayModel>() {
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
                        //   showDialog(QuickPayActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(QuickPayModel quickPayModel) {
                        mdilogue.dismiss();

                        if (quickPayModel.getListResult() != null && quickPayModel.getListResult().size()!=0 ) {
                            quickPayModelData = quickPayModel;
                            // check can we raise quick pay or not

                            cstatecode = quickPayModelData.getListResult().get(0).getStateCode();
                            cdistrictId  = quickPayModelData.getListResult().get(0).getDistrictId();
                            Log.d("CollectionStateCode", cstatecode + "");
                            IsQuickPayBlockDate();


                        } else {
                            noRecords.setVisibility(View.VISIBLE);
                            // no_data.setText("No " + name + " Found");
                            recyclerView.setVisibility(View.GONE);
                            nextButton.setVisibility(View.GONE);
                        }

                        Log.d("", "onNext: " + quickPayModel);

                    }

                });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    @Override
    public void setOnClickAckListener(QuickPayModel.ListResult item) {

        if (ids_list.size() > 0) {

            if (ids_list.contains(item.getUColnid())) {
                ids_list.set(ids_list.indexOf(item.getUColnid()), item.getUColnid());

            }
        }
    }


    private void IsQuickPayBlockDate(){

        mdilogue.show();
        JsonObject object = quickpayblockdateReuestobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.quickpayBlockdate(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QuickpayBlockdateResponse>() {
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
                                ((HttpException) e).response(                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ).errorBody().string();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                        mdilogue.cancel();
                    }

                    @Override
                    public void onNext(final QuickpayBlockdateResponse quickPayblockdateresponse) {

                        if (quickPayblockdateresponse.getIsSuccess()){
                            if(quickPayblockdateresponse.getResult().equals(true)) {
                                if (isOnline()) {

                                    nextButton.setVisibility(View.VISIBLE);
                                    adapter = new QuickPayDataAdapter(QuickPayActivity.this, quickPayModelData.getListResult(), QuickPayActivity.this);
                                    recyclerView.setAdapter(adapter);
                                    w_Code = quickPayModelData.getListResult().get(0).getWhsCode();
                                    statecode = quickPayModelData.getListResult().get(0).getStateCode();
                                    stateName = quickPayModelData.getListResult().get(0).getStateName();
                                    districtName = quickPayModelData.getListResult().get(0).getDistrictName();
                                    districtId = quickPayModelData.getListResult().get(0).getDistrictId();


                                    for (QuickPayModel.ListResult item : quickPayModelData.getListResult()
                                    ) {

                                        if (item.getCollectionBlocked() == false){
                                            ids_list.add(item.getUColnid());
                                            dates_list.add(item.getDocDate());
                                            weight_list.add(item.getQuantity());
                                            Log.d("Collectionssize1", ids_list.size() + "");
                                        }

                                        if (ids_list.size() <= 0 ){

                                            nextButton.setVisibility(View.GONE);
                                            Log.d("Collectionssize2", ids_list.size() + "");
                                        }else{

                                            nextButton.setVisibility(View.VISIBLE);
                                        }

                                        Log.d("Collectionssize33", ids_list.size() + "");

                                    }
                                } else {
                                    showDialog(QuickPayActivity.this, getResources().getString(R.string.Internet));
                                    //nextButton.setVisibility(View.GONE);
                                }
                            }

                        }
                        else {
                            // edittext.getText().clear();Quick pay has already been requested, another quick pay request cannot be requested until the 1st one is completed.
                            showDialogf(QuickPayActivity.this, quickPayblockdateresponse.getEndUserMessage());

                        }

                    }


                });

    }
    private JsonObject quickpayblockdateReuestobject() {

        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        QuickpayBlockdateRequest requestModel = new QuickpayBlockdateRequest();

        requestModel.setIsQuickPayBlocked(true);
        requestModel.setDocDate(currentDate);
        requestModel.setStateCode(cstatecode);

        if (!cstatecode.startsWith("AP") ) {
            requestModel.setDistrictId(0);
        }else{
            requestModel.setDistrictId(cdistrictId);
        }

        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

}