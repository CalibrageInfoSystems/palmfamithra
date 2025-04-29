package in.calibrage.palm360fa.Views.Activities;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Adapter.GetLoanAdapter;
import in.calibrage.palm360fa.Adapter.GetPoleAdapter;
import in.calibrage.palm360fa.Adapter.GetfertAdapter;
import in.calibrage.palm360fa.Adapter.GetvisitAdapter;
import in.calibrage.palm360fa.Adapter.MyLabour_ReqAdapter;
import in.calibrage.palm360fa.Adapter.MyQuickPayDataAdapter;
import in.calibrage.palm360fa.Model.Getvisit;
import in.calibrage.palm360fa.Model.ReqPole;
import in.calibrage.palm360fa.Model.ResLoan;
import in.calibrage.palm360fa.Model.ResPole;
import in.calibrage.palm360fa.Model.Resfert;
import in.calibrage.palm360fa.Model.Resquickpay;
import in.calibrage.palm360fa.Model.labour_req_response;
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

public class RequestListctivity extends BaseActivity implements GetPoleAdapter.GetPoleAdapterListener1, GetfertAdapter.GetPoleAdapterListener{

public static final String TAG = RequestListctivity.class.getSimpleName();

private Context ctx;
private LinearLayoutManager lytManager;
private RecyclerView rcv_requests;
private SpotsDialog mdilogue;
private Subscription mSubscription;
        String name;
        String  Farmer_code;
        TextView no_data;
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
        setContentView(R.layout.activity_request_listctivity);
        name = getIntent().getStringExtra("key");

        setuptoolbar();
        init();

        if (name.equalsIgnoreCase(getResources().getString(R.string.lab_req))) {
        if (isOnline())
        GetLabourRequestDetails();
        else {
        showDialog(RequestListctivity.this, getResources().getString(R.string.Internet));
        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }



        } else if (name.equalsIgnoreCase(getResources().getString(R.string.pole_req))) {
        if (isOnline())
        getPole();
        else {
        showDialog(RequestListctivity.this, getResources().getString(R.string.Internet));
        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }


        } else if (name.equalsIgnoreCase(getResources().getString(R.string.fert_req))) {
        if (isOnline())
        getfertilizer();
        else {
        showDialog(RequestListctivity.this, getResources().getString(R.string.Internet));
        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }


        } else if (name.equalsIgnoreCase(getResources().getString(R.string.quick_req))) {
        if (isOnline())
        getquickpay();
        else {
        showDialog(RequestListctivity.this, getResources().getString(R.string.Internet));
        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }



        } else if (name.equalsIgnoreCase(getResources().getString(R.string.visit_req))) {
        if (isOnline())
        getvisit();
        else {
        showDialog(RequestListctivity.this, getResources().getString(R.string.Internet));
        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }


        } else if (name.equalsIgnoreCase( getResources().getString(R.string.Loan_req))) {
        if (isOnline())
        getLoan();
        else {
        showDialog(RequestListctivity.this, getResources().getString(R.string.Internet));
        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }



        }


        }



private void GetLabourRequestDetails() {

        mdilogue.show();
        JsonObject object = getPoleobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.postLabour_request(object)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<labour_req_response>() {
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
        mdilogue.cancel();
        no_data.setVisibility(View.VISIBLE);
        showDialog(RequestListctivity.this, getString(R.string.server_error));
        }

@Override
public void onNext(labour_req_response labour_req_response) {
        if ( labour_req_response.getListResult().size() != 0) {

        no_data.setVisibility(View.VISIBLE);
        Log.e("labourdata===", "Data");
        rcv_requests.setVisibility(View.VISIBLE);
        MyLabour_ReqAdapter adapter = new MyLabour_ReqAdapter(labour_req_response.getListResult(), ctx);
        rcv_requests.setAdapter(adapter);


        } else {
        no_data.setVisibility(View.VISIBLE);
        Log.e("labourdata===","No===Data");
        //  no_data.setText("No " + name + " Found");
        rcv_requests.setVisibility(View.GONE);
        }

        }
        });
        }


private void init() {
        ctx = this;
        lytManager = new LinearLayoutManager(this);
        rcv_requests = findViewById(R.id.rcv_requests);
        no_data=findViewById(R.id.no_data);
        rcv_requests.setLayoutManager(lytManager);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
        .setContext(this)
        .setTheme(R.style.Custom)
        .build();
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");

        }

private void setuptoolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title =(TextView) findViewById(R.id.txt_name);
        title.setText(name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        finish();
        }
        });
        }

private void getPole() {
        mdilogue.show();
        JsonObject object = getPoleobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.GetPoleRequestDetails(object)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<ResPole>() {
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
        mdilogue.cancel();
        no_data.setVisibility(View.VISIBLE);
        showDialog(RequestListctivity.this, getString(R.string.server_error));
        }

@Override
public void onNext(ResPole poleResponce) {
      //  if(poleResponce.getListResult() != null)

                if ( poleResponce.getListResult().size() != 0) {

        no_data.setVisibility(View.GONE);
        rcv_requests.setVisibility(View.VISIBLE);
        GetPoleAdapter adapter = new GetPoleAdapter(poleResponce.getListResult(), ctx, RequestListctivity.this);
        rcv_requests.setAdapter(adapter);

        }
        else{
        no_data.setVisibility(View.VISIBLE);
        // no_data.setText("No " + name + " Found");
        rcv_requests.setVisibility(View.GONE);
        }

        }


        });


        }
private void getfertilizer() {
        mdilogue.show();
        JsonObject object = getPoleobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.GetfertRequestDetails(object)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Resfert>() {
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
        mdilogue.cancel();
        no_data.setVisibility(View.VISIBLE);
        showDialog(RequestListctivity.this, getString(R.string.server_error));
        }

@Override
public void onNext(Resfert fertResponce) {


        if(fertResponce.getListResult() .size() != 0)
        {
        no_data.setVisibility(View.GONE);
        rcv_requests.setVisibility(View.VISIBLE);
        GetfertAdapter adapter = new GetfertAdapter(fertResponce.getListResult(), ctx, RequestListctivity.this);
        rcv_requests.setAdapter(adapter);

        }
        else{
        no_data.setVisibility(View.VISIBLE);
        rcv_requests.setVisibility(View.GONE);
        }

        }

        });

        }

private void getquickpay() {
        mdilogue.show();
        JsonObject object = getheaderobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.GetRequestheaderDetails(object)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Resquickpay>() {
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
        mdilogue.cancel();
        showDialog(RequestListctivity.this, getString(R.string.server_error));
        }

@Override
public void onNext(Resquickpay resquickpay) {


        if(resquickpay.getListResult().size() != 0)
        {
        no_data.setVisibility(View.GONE);
        rcv_requests.setVisibility(View.VISIBLE);;
        MyQuickPayDataAdapter adapter = new MyQuickPayDataAdapter(resquickpay.getListResult(), ctx);
        rcv_requests.setAdapter(adapter);
        }
        else{
        no_data.setVisibility(View.VISIBLE);
        rcv_requests.setVisibility(View.GONE);;

        }

        }




        });

        }

private JsonObject getheadervisitobject() {
        ReqPole requestModel = new ReqPole();
        requestModel.setFarmerCode(Farmer_code);
        requestModel.setToDate(null);
        requestModel.setFromDate(null);
        requestModel.setRequestTypeId(14);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
        }

private void getLoan() {
        mdilogue.show();
        JsonObject object = getLoanheaderobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.GetRequestheaderLoanDetails(object)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<ResLoan>() {
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
        showDialog(RequestListctivity.this, getString(R.string.server_error));
        }

@Override
public void onNext(ResLoan resLoan) {
        if(resLoan.getListResult().size() != 0)
        {
        no_data.setVisibility(View.GONE);
        rcv_requests.setVisibility(View.VISIBLE);;
        GetLoanAdapter adapter = new GetLoanAdapter(resLoan.getListResult(), ctx);
        rcv_requests.setAdapter(adapter);
        }
        else{
        no_data.setVisibility(View.VISIBLE);
        rcv_requests.setVisibility(View.GONE);;

        }


        }




        });
        }
        private void getvisit() {

                mdilogue.show();
                JsonObject object = getheadervisitobject();
                ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
                mSubscription = service.GetRequestheadervistDetails(object)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Getvisit>() {
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
                                        showDialog(RequestListctivity.this, getString(R.string.server_error));
                                }

                                @Override
                                public void onNext(Getvisit getvisit) {
                                        if(getvisit.getListResult().size()!= 0)
                                        {
                                                no_data.setVisibility(View.GONE);
                                                rcv_requests.setVisibility(View.VISIBLE);;
                                                GetvisitAdapter adapter = new GetvisitAdapter(getvisit.getListResult(), ctx);
                                                rcv_requests.setAdapter(adapter);
                                        }
                                        else{
                                                no_data.setVisibility(View.VISIBLE);
                                                rcv_requests.setVisibility(View.GONE);;

                                        }
                                }


//                            GetLoanAdapter adapter = new GetLoanAdapter(resLoan.getListResult(), ctx);
//                            rcv_requests.setAdapter(adapter);





                        });
        }


private JsonObject getLoanheaderobject() {
        ReqPole requestModel = new ReqPole();
        requestModel.setFarmerCode(Farmer_code);
        requestModel.setToDate(null);
        requestModel.setFromDate(null);
        requestModel.setRequestTypeId(28);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
        }


private JsonObject getheaderobject() {
        ReqPole requestModel = new ReqPole();
        requestModel.setFarmerCode(Farmer_code);
        requestModel.setToDate(null);
        requestModel.setFromDate(null);
        requestModel.setRequestTypeId(13);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
        }

private JsonObject getPoleobject() {
        ReqPole requestModel = new ReqPole();
        requestModel.setFarmerCode(Farmer_code);
        requestModel.setToDate(null);
        requestModel.setFromDate(null);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
        }




@Override
public void onContactSelected1(String products) {
        Intent intent = new Intent(RequestListctivity.this, product_list.class);

//                    // Sending Student Id, Name, Number and Class to next UpdateActivity.
        intent.putExtra("Name",  products);

        startActivity(intent);
        }

        @Override
        public void onContactSelected(Resfert.ListResult list) {

                Intent intent = new Intent(RequestListctivity.this, product_list_fert.class);
                intent.putExtra("Name",  list.getRequestCode());
                intent.putExtra("subcidy",   list.getSubsidyAmount());
                intent.putExtra("pay",   list.getPaubleAmount());
                Log.e("540====","Selected: " +  list.getSubsidyAmount() + ", " +list.getPaubleAmount());

//                    // Sending Student Id, Name, Number and Class to next UpdateActivity.


                startActivity(intent);
        }
}
