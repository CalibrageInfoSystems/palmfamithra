package in.calibrage.palm360fa.Views.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;

import in.calibrage.palm360fa.Adapter.DiseaseDataAdapter;
import in.calibrage.palm360fa.Adapter.InterCrop_Adapter;
import in.calibrage.palm360fa.Adapter.IrrigationAdapter;
import in.calibrage.palm360fa.Adapter.NutrientDataAdapter;
import in.calibrage.palm360fa.Adapter.fertilizerRecommendation_Adapter;
import in.calibrage.palm360fa.Adapter.fertilizer_Adapter;
import in.calibrage.palm360fa.Adapter.pest_Adapter;
import in.calibrage.palm360fa.Model.DiseaseDataModel;
import in.calibrage.palm360fa.Model.InterCrop;
import in.calibrage.palm360fa.Model.Irrigation_Model;
import in.calibrage.palm360fa.Model.Nutrient_Model;
import in.calibrage.palm360fa.Model.fertilizer;
import in.calibrage.palm360fa.Model.fertilizerRecommendation;
import in.calibrage.palm360fa.Model.pest;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.APIConstantURL;
import rx.Subscription;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class CropMaintanceVisitActivity extends BaseActivity {
    //region variables
    private RecyclerView recyclerView;

    private Subscription mSubscription;
    private static final String TAG = CropMaintanceVisitActivity.class.getSimpleName();
    String Farmer_code,plot_id,datevaluereq,timevaluereq;
    RecyclerView recycler_view_InterCrop,recycler_view_irrigation ,
            recycler_view_fert_rec_Details,recycler_view_pest,recycler_view_desease,
            recyclerView_nut,recycler_view_fertilizer;
    ImageView thumbnail;


    private fertilizer_Adapter fAdapter;
    private TextView Age, id_plot, area,landMark,yop;
    //
   private fertilizerRecommendation_Adapter fertadapter;
//
    private pest_Adapter pestadapter;
    //
    private DiseaseDataAdapter disease_adapter;
    //
    private InterCrop_Adapter interadapter;
    private NutrientDataAdapter nut_adapter;

    private IrrigationAdapter irrigation_adapter;


    //
    private List<fertilizerRecommendation> fert_rec_List = new ArrayList<>();
//    private List<wood>wood_List = new ArrayList<>();
    private List<pest> pestt_List = new ArrayList<>();
    //
    private List<DiseaseDataModel> disease_List = new ArrayList<>();
    //
    private List<Nutrient_Model>nut_List = new ArrayList<>();
    private List<Irrigation_Model>Irr_List = new ArrayList<>();
    private List<InterCrop> intercrop_List = new ArrayList<>();
    private List<fertilizer> fertilizer_List = new ArrayList<>();

    private SpotsDialog mdilogue ;
    String datetimevalute,datetimevaluereq;
    RelativeLayout irrigation_text,uprootment_text,fert_rec_text,pest_text,diase_text,nut_text,Fert_text,InterCrop_text;
    public TextView treesAppearance,last_visit_date,freq_harvest,fruitColor,fruitSize,fruitHyegiene,plantationType;
    public TextView seedsPlanted,prevPalmsCount,plamsCount,isTreesMissing,missingTreesCount,reasonType,expectedPlamsCount,comments;
    // ImageView thumbnail;
    public TextView comment_label,reason_label,cluster_name;
    String location,landmarkCode,date_of_plandation,clustter;
    ImageView backImg,home_btn;
    double plot_Age,plotarea;
    DecimalFormat dec = new DecimalFormat("####0.00");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
    //endregion
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
        setContentView(R.layout.activity_crop_maintance_visit);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            plot_id = extras.getString("plotid");
            plot_Age = extras.getDouble("plotAge", 0.00);
            plotarea = extras.getDouble("plotarea", 0.00);
            location = extras.getString("plotVillage");
            landmarkCode = extras.getString("landMark");
            clustter = extras.getString("cluster_name");
            date_of_plandation=extras.getString("date_of_plandation");
        }
        intview();
        setViews();
        if (isOnline())
            GetCropMaintenanceHistoryDetailsByCode();
        else {
            showDialog(CropMaintanceVisitActivity.this,getResources().getString(R.string.Internet));
            //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }


    }

    private void intview() {
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");
        backImg = (ImageView) findViewById(R.id.back);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        recycler_view_pest =(RecyclerView)findViewById(R.id.recyclerView_pest);
        pest_text =(RelativeLayout) findViewById(R.id.pest_text);

        recycler_view_desease =(RecyclerView)findViewById(R.id.recyclerView_dease);
        diase_text =(RelativeLayout) findViewById(R.id.dease_text);

        recyclerView_nut =(RecyclerView)findViewById(R.id.recyclerView_nut);
        nut_text =(RelativeLayout) findViewById(R.id.nut_text);

        recycler_view_fert_rec_Details =(RecyclerView)findViewById(R.id.recyclerView_fert_rec);
        fert_rec_text =(RelativeLayout) findViewById(R.id.fert_rec_text);
        recycler_view_fertilizer =(RecyclerView)findViewById(R.id.recycler_fertilizer);
        Fert_text =(RelativeLayout) findViewById(R.id.fertlizer);
        cluster_name =(TextView)findViewById(R.id.cluster_officer) ;
        recycler_view_irrigation =(RecyclerView)findViewById(R.id.recyclerView_irrigation);
        irrigation_text=(RelativeLayout) findViewById(R.id.irr_rec_text);
        treesAppearance = findViewById(R.id.treesAppearance);
        last_visit_date  = findViewById(R.id.last_visit_date);
        freq_harvest=findViewById(R.id.freq_harvest);


        plamsCount = findViewById(R.id.plamsCount);

        recycler_view_InterCrop =(RecyclerView)findViewById(R.id.recyclerView_InterCrop);
        InterCrop_text =(RelativeLayout) findViewById(R.id.InterCrop_text);


        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();

        Age = findViewById(R.id.age_plot);
        id_plot = findViewById(R.id.plot);
        area = findViewById(R.id.palmArea);
        landMark = findViewById(R.id.landmark);
        yop =findViewById(R.id.yop);
    }

    private void  setViews() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        RecyclerView.LayoutManager mLayoutManager_pest = new LinearLayoutManager(getApplicationContext());
        recycler_view_pest.setLayoutManager(mLayoutManager_pest);
        recycler_view_pest.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManager_dease = new LinearLayoutManager(getApplicationContext());
        recycler_view_desease.setLayoutManager(mLayoutManager_dease);
        recycler_view_desease.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManager_nut = new LinearLayoutManager(getApplicationContext());
        recyclerView_nut.setLayoutManager(mLayoutManager_nut);
        recyclerView_nut.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManager_fert_rec = new LinearLayoutManager(getApplicationContext());
        recycler_view_fert_rec_Details.setLayoutManager(mLayoutManager_fert_rec);
        recycler_view_fert_rec_Details.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManager_irrigation = new LinearLayoutManager(getApplicationContext());
        recycler_view_irrigation.setLayoutManager(mLayoutManager_irrigation);
        recycler_view_irrigation.setItemAnimator(new DefaultItemAnimator());


        RecyclerView.LayoutManager mLayoutManager_inter = new LinearLayoutManager(getApplicationContext());
        recycler_view_InterCrop.setLayoutManager(mLayoutManager_inter);
        recycler_view_InterCrop.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view_fertilizer.setLayoutManager(mLayoutManager);
        recycler_view_fertilizer.setItemAnimator(new DefaultItemAnimator());

        Age.setText(dec.format(plot_Age )+" Ha (" +dec.format(plotarea )+" Acre)"  );
        area.setText(location);
        id_plot.setText(plot_id);
        landMark.setText(landmarkCode);
        yop.setText(date_of_plandation);
        cluster_name.setText(clustter);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(CropMaintanceVisitActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }



    private void GetCropMaintenanceHistoryDetailsByCode() {
        mdilogue.show();
      String url = APIConstantURL.LOCAL_URL + "GetCropMaintenanceHistoryDetailsByPlotCode/" + plot_id + "/"+ Farmer_code;
      //String url = APIConstantURL.LOCAL_URL + "GetCropMaintenanceHistoryDetailsByPlotCode/" + "APDC0003000187" + "/"+ "APWGNJDC00030098";
        Log.e("url====APDC0003000187",url);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mdilogue.dismiss();
                Log.d(TAG, "RESPONSE======" + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d(TAG, "GetCrop ======" + jsonObject);




                    JSONObject healthPlantation_Data = jsonObject.getJSONObject("healthPlantationData");
                    int frequencyOfHarvest = jsonObject.getInt("frequencyOfHarvest");
                    freq_harvest.setText(frequencyOfHarvest+" Days");
                    Log.e("data===",healthPlantation_Data.getString("treesAppearance"));
                    treesAppearance.setText(healthPlantation_Data.getString("treesAppearance"));

                    try {
                        Date oneWayTripDate = input.parse(healthPlantation_Data.getString("updatedDate"));

                        datetimevaluereq = output.format(oneWayTripDate);


                        Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    last_visit_date.setText(datetimevaluereq);
                    JSONArray nutrient_Data = jsonObject.getJSONArray("nutrientData");
                    if(nutrient_Data.length()==0){
                        recyclerView_nut.setVisibility(View.GONE);
                        nut_text.setVisibility(View.GONE);

                    }
                    else {
                        recyclerView_nut.setVisibility(View.VISIBLE);
                        nut_text.setVisibility(View.VISIBLE);

                    }
                    for (int i = 0; i < nutrient_Data.length(); i++) {

                        Nutrient_Model nutrient_List = new Nutrient_Model("","","",
                                "","","","");
                        JSONObject json = null;

                        try {
                            json = nutrient_Data.getJSONObject(i);

                            nutrient_List.setNutrientDeficiencyName(json.getString("nutrient"));
                            nutrient_List.setNameofchemicalapplied(json.getString("chemical"));
                            nutrient_List.setRecommendedFertilizer(json.getString("recommendedFertilizer"));

                            nutrient_List.setUOMName(json.getString("uomName"));
                            nutrient_List.setDosage(json.getString("dosage"));
                            //   nutrient_List.setRegisteredDate(json.getString("registeredDate"));

                            String date=json.getString("registeredDate");
                            //  String amount_total=json.getString("totalCost");
                            String  datee = date.substring(0, 10);
                            Log.e("datee===",datee);

                            try {
                                Date oneWayTripDate = input.parse(datee);

                                datetimevalute=output.format(oneWayTripDate);
                                //datetimevalute.setText(output.format(oneWayTripDate));

                                Log.e("===============","======currentData======"+output.format(oneWayTripDate));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            nutrient_List.setRegisteredDate(datetimevalute);

                            nutrient_List.setComments(json.getString("comments"));

                            Log.e("ourput===6",json.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        nut_List.add(nutrient_List);


                    }

                    nut_adapter = new NutrientDataAdapter( CropMaintanceVisitActivity.this,nut_List);

                    //Adding adapter to recyclerview
                    recyclerView_nut.setAdapter(nut_adapter);



                    JSONObject uprootment_Data = jsonObject.getJSONObject("uprootmentData");
                  Log.e("uprootment_Data======", String.valueOf(uprootment_Data));

                    plamsCount.setText(uprootment_Data.getString("plamsCount"));
//                    isTreesMissing.setText(uprootment_Data.getString("isTreesMissing"));
//                    missingTreesCount.setText(uprootment_Data.getString("missingTreesCount"));
//                    reasonType.setText(uprootment_Data.getString("reasonType"));
//                    expectedPlamsCount.setText(uprootment_Data.getString("expectedPlamsCount"));
//                    comments.setText(uprootment_Data.getString("comments"));
//
//                    if (uprootment_Data.getString("reasonType").contains("null"))
//                    {
//                        //   Log.e("bbbbb",superHero.getmAmount());
//                        reasonType.setVisibility(View.GONE);
//                        reason_label.setVisibility(View.GONE);
//
//                    }
//                    else {
//                        reasonType.setVisibility(View.VISIBLE);
//                        reason_label.setVisibility(View.VISIBLE);
//                    }
//                    if (uprootment_Data.getString("comments").contains("null"))
//                    {
//                        //   Log.e("bbbbb",superHero.getmAmount());
//                        comments.setVisibility(View.GONE);
//                        comment_label.setVisibility(View.GONE);
//
//                    }
//                    else {
//                        comments.setVisibility(View.VISIBLE);
//                        comment_label.setVisibility(View.VISIBLE);
//                    }


                    JSONArray pest_Data = jsonObject.getJSONArray("pestData");
                    if(pest_Data.length()==0){
                        recycler_view_pest.setVisibility(View.GONE);
                        pest_text.setVisibility(View.GONE);

                    }
                    else {
                        recycler_view_pest.setVisibility(View.VISIBLE);
                        pest_text.setVisibility(View.VISIBLE);

                    }
                    for (int i = 0; i < pest_Data.length(); i++) {

                        pest pest_List = new pest("","","","","","");
                        JSONObject json = null;

                        try {
                            json = pest_Data.getJSONObject(i);

                            pest_List.setPest(json.getString("pest"));
                            pest_List.setPestChemicals(json.getString("pestChemicals"));
                            pest_List.setRecommendedChemical(json.getString("recommendedChemical"));

                            pest_List.setDosage(json.getString("dosage"));
                            pest_List.setUOMName(json.getString("uomName"));
                            pest_List.setComments(json.getString("comments"));




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pestt_List.add(pest_List);


                    }
                    pestadapter = new pest_Adapter( CropMaintanceVisitActivity.this,pestt_List);

                    //Adding adapter to recyclerview
                    recycler_view_pest.setAdapter(pestadapter);




                    JSONArray disease_Data = jsonObject.getJSONArray("diseaseData");

                    if(disease_Data.length()==0){
                        recycler_view_desease.setVisibility(View.GONE);
                        diase_text.setVisibility(View.GONE);

                    }
                    else {
                        recycler_view_desease.setVisibility(View.VISIBLE);
                        diase_text.setVisibility(View.VISIBLE);

                    }

                    for (int i = 0; i < disease_Data.length(); i++) {


                        DiseaseDataModel dis_List = new DiseaseDataModel();
                        JSONObject json = null;

                        try {
                            json = disease_Data.getJSONObject(i);

                            dis_List.setDisease(json.getString("disease"));
                            dis_List.setDosage(json.getString("dosage"));
                            dis_List.setUOMName(json.getString("uomName"));
                            dis_List.setChemical(json.getString("chemical"));
                            dis_List.setComments(json.getString("comments"));
                            dis_List.setRec_Chemical(json.getString("recommendedChemical"));




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        disease_List.add(dis_List);


                    }
                    disease_adapter = new DiseaseDataAdapter( CropMaintanceVisitActivity.this,disease_List);
                    recycler_view_desease.setAdapter(disease_adapter);



                    JSONArray fertilizerRecommendation_Details = jsonObject.getJSONArray("fertilizerRecommendationDetails");

                    Log.e("fert_rec_Data===", String.valueOf(fertilizerRecommendation_Details));
                    Log.e("fert_rec_Data===", String.valueOf(fertilizerRecommendation_Details.length()));
                    if(fertilizerRecommendation_Details.length()==0){
                        recycler_view_fert_rec_Details.setVisibility(View.GONE);
                        fert_rec_text.setVisibility(View.GONE);

                    }
                    else {
                        recycler_view_fert_rec_Details.setVisibility(View.VISIBLE);
                        fert_rec_text.setVisibility(View.VISIBLE);

                    }
                    for (int i = 0; i < fertilizerRecommendation_Details.length(); i++) {

                        fertilizerRecommendation fert_List = new fertilizerRecommendation();
                        JSONObject json = null;

                        try {
                            json = fertilizerRecommendation_Details.getJSONObject(i);

                            fert_List.setLastUpdatedDate(json.getString("updatedDate"));
                            fert_List.setComments(json.getString("comments"));
                            fert_List.setDosage(json.getString("dosage"));

                            fert_List.setUOMame(json.getString("uomName"));
                            fert_List.setRecommended_fertilizer(json.getString("recommendedFertilizerName"));




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        fert_rec_List.add(fert_List);


                    }
                    fertadapter = new fertilizerRecommendation_Adapter( CropMaintanceVisitActivity.this,fert_rec_List);

                    //Adding adapter to recyclerview
                    recycler_view_fert_rec_Details.setAdapter(fertadapter);


                    JSONArray Irrigation_Data = jsonObject.getJSONArray("plotIrrigation");

                    if(Irrigation_Data.length()==0){
                        recycler_view_irrigation.setVisibility(View.GONE);
                        irrigation_text.setVisibility(View.GONE);

                    }
                    else {
                        recycler_view_irrigation.setVisibility(View.VISIBLE);
                        irrigation_text.setVisibility(View.VISIBLE);

                    }

                    for (int i = 0; i < Irrigation_Data.length(); i++) {


                        Irrigation_Model Irrigation_List = new Irrigation_Model();
                        JSONObject json = null;

                        try {
                            json = Irrigation_Data.getJSONObject(i);


                            //Irrigation_List.setPlotCode(json.getString("plotCode"));

                            Irrigation_List.setName(json.getString("name"));

                            Irrigation_List.setUpdatedBy(json.getString("updatedBy"));

                            Irrigation_List.setUpdatedbyDate(json.getString("updatedbyDate"));





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Irr_List.add(Irrigation_List);


                    }
                    irrigation_adapter = new IrrigationAdapter( CropMaintanceVisitActivity.this,Irr_List);
                    recycler_view_irrigation.setAdapter(irrigation_adapter);

                    JSONArray interCropPlantationXref_Data = jsonObject.getJSONArray("interCropPlantationXrefData");

                    if(interCropPlantationXref_Data.length()==0){
                        recycler_view_InterCrop.setVisibility(View.GONE);
                        InterCrop_text.setVisibility(View.GONE);

                    }
                    else {
                        recycler_view_InterCrop.setVisibility(View.VISIBLE);
                        InterCrop_text.setVisibility(View.VISIBLE);

                    }
                    for (int i = 0; i < interCropPlantationXref_Data.length(); i++) {

                        InterCrop Icrop_List = new InterCrop();
                        JSONObject json = null;

                        try {
                            json = interCropPlantationXref_Data.getJSONObject(i);
                            Icrop_List.setCrop(json.getString("crop"));
                            Log.e("ourput===5",json.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }  intercrop_List.add(Icrop_List);


                    }
                    interadapter = new InterCrop_Adapter( CropMaintanceVisitActivity.this,intercrop_List);

                    //Adding adapter to recyclerview
                    recycler_view_InterCrop.setAdapter(interadapter);
                    JSONArray fertilizer_Data = jsonObject.getJSONArray("fertilizerData");


                    if(fertilizer_Data.length()==0){
                        recycler_view_fertilizer.setVisibility(View.GONE);
                        Fert_text.setVisibility(View.GONE);

                    }
                    else {
                        recycler_view_fertilizer.setVisibility(View.VISIBLE);
                        Fert_text.setVisibility(View.VISIBLE);

                    }
                    Log.e("alsoKnownAsArray==76", String.valueOf(fertilizer_Data));
                    for (int i = 0; i < fertilizer_Data.length(); i++) {

                        fertilizer fert_List = new fertilizer();
                        JSONObject json = null;

                        try {
                            json = fertilizer_Data.getJSONObject(i);

                            // fert_List.setPloteCode(json.getString("plotCode"));
                            fert_List.setfertilizer_sourse(json.getString("fertilizerSourceType"));
                            fert_List.setfertilizer_name(json.getString("fertilizer"));
                            fert_List.setDosage(json.getString("dosage")+" "+"kgs");

                            fert_List.setfrequency(json.getString("applyFertilizerFrequencyType"));

                            String date=json.getString("updatedDate");
                            //  String amount_total=json.getString("totalCost");
                            String  datee = date.substring(0, 10);
                            Log.e("datee===",datee);

                            try {
                                Date oneWayTripDate = input.parse(datee);

                                datetimevalute=output.format(oneWayTripDate);
                                //datetimevalute.setText(output.format(oneWayTripDate));

                                Log.e("===============","======currentData======"+output.format(oneWayTripDate));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            fert_List.setdate(datetimevalute);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        fertilizer_List.add(fert_List);


                    }
                    fAdapter = new fertilizer_Adapter( CropMaintanceVisitActivity.this,fertilizer_List);

                    //Adding adapter to recyclerview
                    recycler_view_fertilizer.setAdapter(fAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                mdilogue.dismiss();
                if (error instanceof NetworkError) {
                    Log.i("one:" + TAG, error.toString());

                } else if (error instanceof ServerError) {
                    Log.i("two:" + TAG, error.toString());

                } else if (error instanceof AuthFailureError) {
                    Log.i("three:" + TAG, error.toString());

                } else if (error instanceof ParseError) {
                    Log.i("four::" + TAG, error.toString());

                } else if (error instanceof NoConnectionError) {
                    Log.i("five::" + TAG, error.toString());

                } else if (error instanceof TimeoutError) {
                    Log.i("six::" + TAG, error.toString());

                } else {
                    System.out.println("Checking error in else");
                }
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
