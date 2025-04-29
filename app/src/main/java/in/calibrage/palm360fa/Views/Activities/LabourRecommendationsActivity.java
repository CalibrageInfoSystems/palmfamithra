package in.calibrage.palm360fa.Views.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Adapter.LabourRecommendationAdapter;
import in.calibrage.palm360fa.Model.LabourRecommendationsModel;
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
import static java.util.Calendar.YEAR;

public class LabourRecommendationsActivity extends BaseActivity {
    private static final String TAG = LabourRecommendationsActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private LabourRecommendationAdapter adapter;
    private Subscription mSubscription;
    String Farmer_code;
    LinearLayout noRecords;
    ImageView backImg,home_btn;
    private SpotsDialog mdilogue ;
    Date dateObj;
    int diff;
    private  boolean isstatustype_89 = false;
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
        setContentView(R.layout.activity_labour_recommendations);
        init();
        setViews();
    }

    private void init() {
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");
        noRecords = (LinearLayout) findViewById(R.id.text);
        backImg = (ImageView) findViewById(R.id.back);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
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

                Intent intent = new Intent(LabourRecommendationsActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


        // listSuperHeroes = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        if (isOnline())
            GetLabourRequestDetails();
        else {
            showDialog(LabourRecommendationsActivity.this, getResources().getString(R.string.Internet));

        }


    }

    private void GetLabourRequestDetails() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getrecommdetails(APIConstantURL.GetActivePlotsByFarmerCode +Farmer_code)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LabourRecommendationsModel>() {
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
                        showDialog(LabourRecommendationsActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(LabourRecommendationsModel labourRecommendationsModel) {
                        mdilogue.dismiss();
                        Log.d(TAG, "onNext:lobour " + labourRecommendationsModel);
//
                        boolean isLessthan3years = false;
                        if (labourRecommendationsModel.getListResult() != null) {
                            // we have check wether 89 id exist or mot
                            for (LabourRecommendationsModel.ListResult item : labourRecommendationsModel.getListResult()) {

                                Log.d("Mahesh", "--- Analysis ----- ");
                                Log.d("Mahesh", "id :" + item.getStatusTypeId());
                                String date = item.getDateOfPlanting();


                                SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    dateObj = curFormater.parse(date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date currentDate = new Date();
                                Calendar a = getCalendar(dateObj);
                                Calendar b = getCalendar(currentDate);
                                diff = b.get(YEAR) - a.get(YEAR);
                                Log.e("diff====", diff + "");


                                if(diff >= 3)
                                    isLessthan3years =true;

                            }


                            if (!isLessthan3years) {
                                showDialogf(LabourRecommendationsActivity.this, getResources().getString(R.string.no_yeild));

                            }else
                            {
                                noRecords.setVisibility(View.GONE);
                                adapter = new LabourRecommendationAdapter(labourRecommendationsModel.getListResult(), LabourRecommendationsActivity.this);
                                recyclerView.setAdapter(adapter);
                            }



                        } else {
                            noRecords.setVisibility(View.VISIBLE);

                        }
                    }


                });
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
