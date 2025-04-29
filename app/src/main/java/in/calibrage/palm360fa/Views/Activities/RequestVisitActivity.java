package in.calibrage.palm360fa.Views.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

import dmax.dialog.SpotsDialog;

import in.calibrage.palm360fa.Adapter.ReqVisitAdapter;
import in.calibrage.palm360fa.Model.RecomPlotcodes;
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

public class RequestVisitActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private static final String TAG = LabourRecommendationsActivity.class.getSimpleName();

    private ReqVisitAdapter adapter;
    private Subscription mSubscription;
    String Farmer_code;
    LinearLayout noRecords;
    ImageView backImg, home_btn;
    private SpotsDialog mdilogue;

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
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_request_visit);

        init();

        setViews();


    }

    private void init() {
        noRecords = (LinearLayout) findViewById(R.id.text);
        backImg = (ImageView) findViewById(R.id.back);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");       // Saving string data of your editext

    }

    private void setViews() {

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(RequestVisitActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        // listSuperHeroes = new ArrayList<>();


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        if (isOnline())
            GetLabourRequestDetails();
        else {
            showDialog(RequestVisitActivity.this,getResources().getString(R.string.Internet));
            //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }


    }

    private void GetLabourRequestDetails() {

        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getplots(APIConstantURL.Recommede_plots + Farmer_code)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecomPlotcodes>() {
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

                    @Override
                    public void onNext(RecomPlotcodes recomPlotcodes) {
                        Log.d("", "onNext: " + recomPlotcodes);
                        mdilogue.dismiss();
                        if (recomPlotcodes.getIsSuccess()) {

                            if (recomPlotcodes.getListResult().isEmpty()) {
                                noRecords.setVisibility(View.VISIBLE);

                            } else {
                                noRecords.setVisibility(View.GONE);
                            }
                        }
                        ReqVisitAdapter rec_adapter = new ReqVisitAdapter(RequestVisitActivity.this, recomPlotcodes.getListResult());
                        recyclerView.setAdapter(rec_adapter);
                    }

                });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}