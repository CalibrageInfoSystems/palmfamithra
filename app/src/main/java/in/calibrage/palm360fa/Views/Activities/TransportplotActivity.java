package in.calibrage.palm360fa.Views.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;


import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Adapter.TransportplotAdapter;
import in.calibrage.palm360fa.Model.GetIssueModel;
import in.calibrage.palm360fa.Model.LabourRecommendationsModel;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.CommonUtil;
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

public class TransportplotActivity extends BaseActivity implements TransportplotAdapter.ClickListener{
    private static final String TAG = TransportplotActivity.class.getSimpleName();

    private RecyclerView recyclerView,Approverecycler_view;
    private TransportplotAdapter adapter;
    private Subscription mSubscription;
    String Farmer_code;
    LinearLayout noRecords,newtextlinear;
    ImageView backImg,home_btn;
    private SpotsDialog mdilogue ;
    Date dateObj;
    int diff;
    private List<LabourRecommendationsModel.ListResult> plotdetailslistObj = new ArrayList<>();
    private LabourRecommendationsModel selectedplots;
    private  boolean isstatustype_89 = false;
    Spinner Transporttype;
    List<Integer> transport_Id = new ArrayList<Integer>();
    List<String> transport_type = new ArrayList<String>();
    Button nextButton;
    String selected_plots;
    String selected_transport;
    boolean isLessthan3years = false;
    int Statustypeid;
    // List plotCodesToDisplay = new ArrayList();
    List<String> plotCodesToDisplay = new ArrayList<>();
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
        setContentView(R.layout.activity_transportplot);
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
        Transporttype = findViewById(R.id.Transport_type);
        nextButton = findViewById(R.id.nextButton);
        newtextlinear = findViewById(R.id.newtextlinear);
    }

    private void setViews() {
        nextButton.setVisibility(View.VISIBLE);
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

                Intent intent = new Intent(TransportplotActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        if (isOnline()){
            //   GetLabourRequestDetails();
            gettransporttype();}
        else {
            showDialog(TransportplotActivity.this, getResources().getString(R.string.Internet));

        }

        Transporttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_transport = Transporttype.getItemAtPosition(Transporttype.getSelectedItemPosition()).toString();

                if(selected_transport.equals("Sapling")){
                    GetApproveplots();

                }
                else if(selected_transport.equals("Fertilizer")){
                    GetAllplots();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        // listSuperHeroes = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        Approverecycler_view = (RecyclerView) findViewById(R.id.Approverecycler_view);
        Approverecycler_view.setHasFixedSize(true);
        Approverecycler_view.setLayoutManager(new LinearLayoutManager(this));
        Approverecycler_view.setAdapter(adapter);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validations()){
                    Log.e("============>165",transport_type.get(Transporttype.getSelectedItemPosition()) +"=="+
                            transport_Id.get(Transporttype.getSelectedItemPosition() - 1) + "==>plots"+selected_plots);
                    Intent intent = new Intent(getApplicationContext(), TransportActivity.class);
                    intent.putExtra("plotcode",selected_plots);
                    intent.putExtra("transporttype", transport_type.get(Transporttype.getSelectedItemPosition()));
                    intent.putExtra("transporttypeId", transport_Id.get(Transporttype.getSelectedItemPosition() - 1));
                    intent.putExtra("yielding", isLessthan3years);
                    intent.putExtra("plotslist",(Serializable) plotCodesToDisplay);
                    startActivity(intent);
                }
            }
        });

    }

    private void GetApproveplots() {

            mdilogue.show();
            ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
            mSubscription = service.getrecommdetails(APIConstantURL.GetActivePlotsByFarmerCode +Farmer_code +"/true")
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
                            showDialog(TransportplotActivity.this, getString(R.string.server_error));
                        }

                        @Override
                        public void onNext(LabourRecommendationsModel labourRecommendationsModel) {
                            mdilogue.dismiss();
                            Log.d(TAG, "onNext:lobour " + labourRecommendationsModel);
                            plotdetailslistObj = labourRecommendationsModel.getListResult();

                            if (labourRecommendationsModel.getListResult() != null) {
                                isLessthan3years =false;
                                newtextlinear.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                                noRecords.setVisibility(View.GONE);
                                Approverecycler_view.setVisibility(View.VISIBLE);
                                nextButton.setVisibility(View.VISIBLE);
                                adapter = new TransportplotAdapter(labourRecommendationsModel.getListResult(), TransportplotActivity.this,selected_transport);
                                // adapter = new TransportplotAdapter(this, plotdetailslistObj, R.layout.adapter_plotdetails);
                                adapter.setOnClickListener(TransportplotActivity.this);

                                Approverecycler_view.setAdapter(adapter);
                                adapter.clearSelection();
                                adapter.selectedItems.clear();



                            } else {
                                nextButton.setVisibility(View.GONE);
                                noRecords.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                                Approverecycler_view.setVisibility(View.GONE);
                                newtextlinear.setVisibility(View.GONE);
                            }
                        }

                    });}


    private boolean validations() {
        if (Transporttype.getSelectedItemPosition() == 0) {

            showDialog(TransportplotActivity.this, getResources().getString(R.string.Validtransport));
            return false;
        }

        if (TextUtils.isEmpty(selected_plots)){
            showDialog(TransportplotActivity.this, getResources().getString(R.string.Validlist));
            return false;
        }

        return true;
    }

    private void gettransporttype() {

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getIssuestypes(APIConstantURL.GetTransporttype)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetIssueModel>() {
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
                        showDialog(TransportplotActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetIssueModel getIssueModel) {


                        if (getIssueModel.getListResult() != null) {
                            transport_type.add("Select");
                            for (GetIssueModel.ListResult data : getIssueModel.getListResult()
                            ) {
                                transport_type.add(data.getDesc());
                                transport_Id.add(data.getTypeCdId());
                            }
                            Log.d(TAG, "RESPONSE======" + transport_type);

//

                            ArrayAdapter aa = new ArrayAdapter(TransportplotActivity.this, R.layout.spinner_item, transport_type);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            Transporttype.setAdapter(aa);
                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });
    }

    private void GetAllplots()
    {
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
                        showDialog(TransportplotActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(LabourRecommendationsModel labourRecommendationsModel) {
                        mdilogue.dismiss();
                        Log.d(TAG, "onNext:lobour " + labourRecommendationsModel);
                        plotdetailslistObj = labourRecommendationsModel.getListResult();
// plotdetailslistObj = ccDataAccessHandler.getPlotDetails(basicFarmerDetails.getFarmerCode().trim());

                        if (labourRecommendationsModel.getListResult() != null) {
                            // we have check wether 89 id exist or mot
                            for (LabourRecommendationsModel.ListResult item : labourRecommendationsModel.getListResult()) {

                                Log.d("Mahesh", "id :" + item.getStatusTypeId());
                                String date = item.getDateOfPlanting();

                                SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    dateObj = curFormater.parse(date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                                Date currentDate = new Date();
                                Calendar a = getCalendar(dateObj);
                                Calendar b = getCalendar(currentDate);
                                diff = b.get(YEAR) - a.get(YEAR);
                                Log.e("diff====", diff + "");


                                if(diff >= 3)
                                    isLessthan3years =true;

                            }
//                            if (!isLessthan3years) {
//                                showDialogf(TransportplotActivity.this, getResources().getString(R.string.no_yeild));
//
//                            }else
//                            {
//
//                            }

                            newtextlinear.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                            nextButton.setVisibility(View.VISIBLE);
                            Approverecycler_view.setVisibility(View.GONE);
                            noRecords.setVisibility(View.GONE);
                            adapter = new TransportplotAdapter(labourRecommendationsModel.getListResult(), TransportplotActivity.this,selected_transport);
                            // adapter = new TransportplotAdapter(this, plotdetailslistObj, R.layout.adapter_plotdetails);
                            adapter.setOnClickListener(TransportplotActivity.this);

                            recyclerView.setAdapter(adapter);
                            adapter.clearSelection();
                            adapter.selectedItems.clear();



                        } else {
                            nextButton.setVisibility(View.GONE);
                            noRecords.setVisibility(View.VISIBLE);
                            Approverecycler_view.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.GONE);
                            newtextlinear.setVisibility(View.GONE);
                        }
                    }


                });
    }


    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onItemClicked( int position) {

        toggleSelection(position);

        selected_plots = getPlotCodesToDisplay();
        Log.e("plotCodes====>375", selected_plots + "");

    }

    private void toggleSelection(int position) {
        Log.v(TAG, "@@@ item clicked and the position is 111 check " + position);
        adapter.toggleSelection(position);

        selected_plots = getPlotCodesToDisplay();
        Log.e("plotCodes====>384", selected_plots + "");

    }

    public String getPlotCodesToDisplay() {
        plotCodesToDisplay.clear();

        if (null != adapter.getSelectedItems() && !adapter.getSelectedItems().isEmpty()) {
            List<Integer> selectedPos = adapter.getSelectedItems();
            for (int i = 0; i < selectedPos.size(); i++) {
                Log.v(TAG, "@@@ let's go next " + selectedPos.get(i) + "..." + plotdetailslistObj.get(i).getPlotcode());
                plotCodesToDisplay.add(plotdetailslistObj.get(selectedPos.get(i)).getPlotcode());
            }
        }
        plotCodesToDisplay = CommonUtil.ignoreDuplicatedInArrayList(plotCodesToDisplay);
        Log.e("plotToDisplay=>422", plotCodesToDisplay + "");
        Log.e("plotToDisplay=>423", plotCodesToDisplay.size() + "");
        return TextUtils.join(", ", plotCodesToDisplay);
    }


}
