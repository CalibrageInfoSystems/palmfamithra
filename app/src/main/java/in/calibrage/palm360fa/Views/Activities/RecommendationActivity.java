package in.calibrage.palm360fa.Views.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

import dmax.dialog.SpotsDialog;

import in.calibrage.palm360fa.Adapter.RecommendationAdapter;
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

public class RecommendationActivity extends BaseActivity {
    private RecyclerView recom_recyclerView;
    private RecommendationAdapter rec_adapter;
    private ProgressDialog dialog;

    String Farmer_code;
    private Subscription mSubscription;
    TextView no_plots;
    ImageView backImg,home_btn;
    private SpotsDialog mdilogue ;
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
        setContentView(R.layout.activity_recommendation);
        init();
        setViews();
    }
    private void init() {
        no_plots=(TextView)findViewById(R.id.text);
        home_btn=(ImageView)findViewById(R.id.home_btn);

        backImg=(ImageView)findViewById(R.id.back);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        recom_recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");
    }
    private void setViews() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                //  finish();
            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(RecommendationActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        recom_recyclerView.setHasFixedSize(true);
        recom_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (isOnline())
            Getplots();
        else {
            showDialog(RecommendationActivity.this, getResources().getString(R.string.Internet));
        }


    }






    private void Getplots() {

        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getplots(APIConstantURL.Recommede_plots+Farmer_code)
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
                        showDialog(RecommendationActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(RecomPlotcodes recomPlotcodes) {
                        Log.d("", "onNext: " + recomPlotcodes);
                        mdilogue.dismiss();
                        if(recomPlotcodes.getIsSuccess())
                        {

                            if (recomPlotcodes.getListResult().isEmpty()) {
                                no_plots.setVisibility(View.VISIBLE);

                            } else {
                                no_plots.setVisibility(View.GONE);
                            }
                        }
                        RecommendationAdapter rec_adapter= new RecommendationAdapter(RecommendationActivity.this, recomPlotcodes.getListResult());
                        recom_recyclerView.setAdapter(rec_adapter);
                    }


                });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}

