package in.calibrage.palm360fa.Views.Activities;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Adapter.LabourTermsNCondtionsAdapter;
import in.calibrage.palm360fa.Model.AddLabourRequestHeader;
import in.calibrage.palm360fa.Model.AmountRequest;
import in.calibrage.palm360fa.Model.Costconfigres;
import in.calibrage.palm360fa.Model.GetAmount;
import in.calibrage.palm360fa.Model.GetLabourPackageDiscount;
import in.calibrage.palm360fa.Model.LabourDuration;
import in.calibrage.palm360fa.Model.LabourTermsNCondtionsModel;
import in.calibrage.palm360fa.Model.Labourservicetype;
import in.calibrage.palm360fa.Model.LobourResponse;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.MSGmodel;
import in.calibrage.palm360fa.Model.PostHoliday;
import in.calibrage.palm360fa.Model.RespHoliday;
import in.calibrage.palm360fa.Model.ServiceType;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.CommonUtil;
import in.calibrage.palm360fa.common.MultiSelectionSpinner;
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

public class LabourActivity extends BaseActivity implements MultiSelectionSpinner.OnMultipleItemsSelectedListener,DatePickerDialog.OnDateSetListener {
    Calendar myCalendar;
    EditText edittext;
    int day, year, month;
    String Farmer_code;
    Integer durationId;
    List<String> list = new ArrayList<String>();
    String val;
    List<String> service_name = new ArrayList<String>();
    List<Integer> labour_uID = new ArrayList<Integer>();
    List<Integer> ids_list = new ArrayList<Integer>();
    List<String> selected_labour = new ArrayList<String>();
    boolean result;
    List<Integer> period_id = new ArrayList<Integer>();
    Spinner labourSpinner;
    private ProgressDialog dialog;
    public static String TAG = "LabourActivity";
    ArrayList<String> listdata = new ArrayList<String>();
    MultiSelectionSpinner multiSelectionSpinner;
    Dialog myDialog;
    boolean isSelectable=true;
    DecimalFormat df = new DecimalFormat("######0.00");
    LabourTermsNCondtionsAdapter Tadapter;
    LabourdiscountAdapter dis_adapter;
    String selected_name, selected_ids;
    TextView terms, status_text, prun_amount, harv_amount, un_amount, un2_amount, yop;
    String seleced_Duration;
    int discount;
    RelativeLayout amount_Label, harv_amount_label, un_amount_label, un2_amount_label;
    private Subscription mSubscription;
    TextView ok, getTerms, head_text, service_charge;
    TextView Age,InterCrop, id_plot, area, landMark,cluster_name;
    RecyclerView terms_recycle, discount_recycle;
    Button button_submit;
    private SpotsDialog mdilogue;
    CheckBox checkbox,check_box;
    RelativeLayout checkpole;
    double harvesting_amount, harvesting_d_amount, prunning_amount, prunning_d_amount,intercrop_prun,intercrop_harv;
    String total_amount, serviceTypeId, Seleted_date, farmated_date, isSuccess, register, currentDate;
    String plot_id, interCrops,location,clustername, farmerCode, plotMandal, plotState, plotDistrict, landmarkCode, reformattedDate, status, plantationdate,
            finalAmount, plantation_date;
    EditText commentsTxt;
    Integer statusTypeId;
    double plot_Age,plotarea;
    DecimalFormat dec = new DecimalFormat("####0.00");
    ImageView backImg, home_btn;
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
    LinearLayout discount_label;
    TextView discount_percentage, harv_d_amount, pru_d_amount;
    DatePickerDialog datePickerDialog ;
    LoginResponse created_user;
    private Integer User_id;
    String Farmer_name,statecode,statename;
    int Year, Month, Day, Hour, Minute,clusterId;
    Calendar calendar ;
    boolean ownPole = false;
    int diff;
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
        setContentView(R.layout.activity_labour);


        if (isOnline()) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                plot_id = extras.getString("plotid");
                plot_Age = extras.getDouble("plotAge", 0.00);
                plotarea = extras.getDouble("plotarea", 0.00);
                Log.e("plot_Age===", plot_Age + "");
                location = extras.getString("plotVillage");
                farmerCode = extras.getString("farmerCode");
                landmarkCode = extras.getString("landMark");
                plotMandal = extras.getString("plotMandal");
                plotState = extras.getString("plotState");
                plotDistrict = extras.getString("plotDistrict");
                clustername = extras.getString("cluster_name");
                plantationdate = extras.getString("date_of_plandation");
                status = extras.getString("status");
                interCrops = extras.getString("interCrop");
                statusTypeId =extras.getInt("statusTypeId");
                clusterId =  extras.getInt("cluster_Id");
                statename = extras.getString("statename");
                statecode = extras.getString("statecode");
            }
            //  GetInterCropByPlotCode();
            intview();
            setViews();
            SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
            Farmer_code = pref.getString("farmerid", "");       // Saving string data of your editext
        }
        else {
            showDialog(LabourActivity.this, getResources().getString(R.string.Internet));
            //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }

        // GetCropMaintenanceHistoryDetailsByCode();


    }

    private void intview() {
        created_user = SharedPrefsData.getCreatedUser(LabourActivity.this);

        User_id= created_user.getResult().getUserInfos().getId();

        String middlename = created_user.getResult().getUserInfos().getMiddleName();
        String finalmiddlename = "";
        if ((middlename != null && !middlename.isEmpty() && !middlename.equals("null"))) {
            finalmiddlename = middlename + " ";
        }
        Farmer_name =created_user.getResult().getUserInfos().getFirstName()  + " " + finalmiddlename + created_user.getResult().getUserInfos().getLastName();

        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Log.i("LOG_RESPONSE date ", currentDate);
        Age = findViewById(R.id.age_plot);
        id_plot = findViewById(R.id.plot);
        InterCrop =findViewById(R.id.intercrop);
        area = findViewById(R.id.palmArea);
        yop = findViewById(R.id.yop);
        landMark = findViewById(R.id.landmark);
        commentsTxt = findViewById(R.id.commentTxt);
        cluster_name =findViewById(R.id.cluster_officer);
//        status_text=findViewById(R.id.status);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        backImg = (ImageView) findViewById(R.id.back);
        multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.spinner_labour_type);
        terms = (TextView) findViewById(R.id.terms);
        labourSpinner = (Spinner) findViewById(R.id.labour_duration);
        edittext = (EditText) findViewById(R.id.date_display);
        checkbox = (CheckBox) findViewById(R.id.checkBox);
        check_box = (CheckBox) findViewById(R.id.check_Box);
        button_submit = findViewById(R.id.buttonSubmit);
        amount_Label = findViewById(R.id.pruning_amount_label);
        harv_amount_label = findViewById(R.id.harv_amount_label);
      //  harv_d_amount = findViewById(R.id.harv_d_amount);
        pru_d_amount = findViewById(R.id.pru_d_amount);
        discount_percentage = findViewById(R.id.discount_percentage);
        discount_label = findViewById(R.id.discount_label);
        un_amount_label = findViewById(R.id.un_amount_label);
        un2_amount_label = findViewById(R.id.un2_amount_label);
        prun_amount = findViewById(R.id.pruning_amount);
        harv_amount = findViewById(R.id.harv_amount);
        un_amount = findViewById(R.id.un_amount);
        un2_amount = findViewById(R.id.un2_amount);
        calendar = Calendar.getInstance();
        checkpole =findViewById(R.id.check_pole);
        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        Hour = calendar.get(Calendar.HOUR_OF_DAY);
        Minute = calendar.get(Calendar.MINUTE);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        if (isOnline()) {
            // CostConfig();
            Getterms_conditions();

        } else {
            showDialog(LabourActivity.this, getResources().getString(R.string.Internet));
            //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }

    }

    private void setViews() {

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(LabourActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

//        try {
//            Date oneWayTripDate = input.parse(plantationdate);
//
//            plantation_date = output.format(oneWayTripDate);
//
//
//            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        Age.setText(dec.format(plot_Age )+" Ha (" +dec.format(plotarea )+" Acre)"  );
        area.setText(location);
        id_plot.setText(plot_id);
        landMark.setText(landmarkCode);
        cluster_name.setText(clustername);
        yop.setText(plantationdate);
        InterCrop.setText(interCrops);
        myCalendar = Calendar.getInstance();
        plantation_date = yop.getText().toString() ;
        int year = calendar.get(Calendar.YEAR);
        diff =year - Integer.valueOf(plantation_date);
        Log.e("diff===",diff+"");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //  edittext.setText(dateFormat.format(new Date()));


        try {
            Date oneWayTripDate = input.parse(dateFormat.format(new Date()));

            reformattedDate = output.format(oneWayTripDate);
            //  GetAll_tokens_closed();
            Log.e("===============", "======current===========" + reformattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog = DatePickerDialog.newInstance(LabourActivity.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setTitle("Select Date");


                // Setting Min Date to today date
                // Setting Min Date to today date
                Calendar min_date_c = Calendar.getInstance();
                min_date_c.set(Calendar.DATE, Day+3);
                datePickerDialog.setMinDate(min_date_c);

                // Setting Max Date to next 2 years
                Calendar max_date_c = Calendar.getInstance();
                max_date_c.set(Calendar.YEAR, Year+10);
                datePickerDialog.setMaxDate(max_date_c);



                //Disable all SUNDAYS and SATURDAYS between Min and Max Dates
                for (Calendar loopdate = min_date_c; min_date_c.before(max_date_c); min_date_c.add(Calendar.DATE, 1), loopdate = min_date_c) {
                    int dayOfWeek = loopdate.get(Calendar.DAY_OF_WEEK);
                    if (dayOfWeek == Calendar.SUNDAY ) {
                        Calendar[] disabledDays =  new Calendar[1];
                        disabledDays[0] = loopdate;
                        datePickerDialog.setDisabledDays(disabledDays);
                    }
                }



                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialogInterface) {

                        //  Toast.makeText(LabourActivity.this, "Datepicker Canceled", Toast.LENGTH_SHORT).show();
                    }
                });

                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
            }
        });

//
//// Setting Min Date
//              Calendar min_date_c = Calendar.getInstance();
//                datePickerDialog.getDatePicker().setMinDate(min_date_c);
//                //datePickerDialog.setMinDate(min_date_c);
//
//
//// Setting Max Date
//                Calendar max_date_c = Calendar.getInstance();
//                max_date_c.set(Calendar.YEAR, 10);
//                datePickerDialog.getDatePicker().setMaxDate(max_date_c);
//                //  datePickerDialog.setMaxDate(max_date_c);
//
////Disable all SUNDAYS and SATURDAYS between Min and Max Dates
//                for (Calendar loopdate = min_date_c; min_date_c.before(max_date_c); min_date_c.add(Calendar.DATE, 1), loopdate = min_date_c) {
//                    int dayOfWeek = loopdate.get(Calendar.DAY_OF_WEEK);
//                    if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
//                        Calendar[] disabledDays = new Calendar[1];
//                        disabledDays[0] = loopdate;
//                        datePickerDialog.getDatePicker().setEnabled(false);
//                    }
//                    datePickerDialog.show();
//                }

        if (isOnline()) {

            //   CostConfig();
            Getterms_conditions();
        }
        //  GetLabourPackageDiscount();}

        else {
            showDialog(LabourActivity.this, getResources().getString(R.string.Internet));
            //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // CostConfig();
                    Getterms_conditions();

                    myDialog = new Dialog(LabourActivity.this);

                    ShowPopup();

                }

            }
        });
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    // CostConfig();
                    ownPole = true;

                }
                else {
                    ownPole = false;
                }

            }
        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//
                if (validations()) {

                    if (isOnline())
                        AddLabourRequestHeader();
                    else {
                        showDialog(LabourActivity.this, getResources().getString(R.string.Internet));
                        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
                    }

                    //
                }

            }


        });
        // Getterms_conditions();
        terms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (isOnline()) {
                    //  CostConfig();
                    Getterms_conditions();
                } else {

                    showDialog(LabourActivity.this, getResources().getString(R.string.Internet));
                    //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();

                }
                myDialog = new Dialog(LabourActivity.this);

                ShowPopup();

            }
        });
//        if (isOnline())
//            Getlabour_duration();
//        else {
//            showDialog(LabourActivity.this, getResources().getString(R.string.Internet));
//            //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
//        }

        labourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seleced_Duration = labourSpinner.getItemAtPosition(labourSpinner.getSelectedItemPosition()).toString();
                if(seleced_Duration!= "Select") {
                    discount_label.setVisibility(View.VISIBLE);
                }
                else {
                    discount_label.setVisibility(View.GONE);
                }

//                if(seleced_Duration!= "Select") {
//                    GetLabourPackageDiscount();
//                    myDialog = new Dialog(LabourActivity.this);
//
//                    discount_popup();
//                }
                if (seleced_Duration.equalsIgnoreCase("1 Day") ){
                    discount_label.setVisibility(View.GONE);
//                    harv_d_amount.setVisibility(View.GONE);
//                    pru_d_amount.setVisibility(View.GONE);
//                    harv_amount.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
//                    prun_amount.setPaintFlags(Paint.ANTI_ALIAS_FLAG);

                }
                Log.e("seleced_period===", seleced_Duration);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        listdata.clear();
        listdata.add("Select");
        ArrayAdapter aa = new ArrayAdapter(LabourActivity.this, R.layout.spinner_item, listdata);
        aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        labourSpinner.setAdapter(aa);
        if (isOnline()) {
            GetSpinnerLabourType();
        } else {
            showDialog(LabourActivity.this, getResources().getString(R.string.Internet));
            //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }

        if (isOnline()) {
            multiSelectionSpinner.setListener(this);
        }
    }

    private void GetLabourPackageDiscount() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getdiscount(APIConstantURL.GetLabourPackageDiscount)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetLabourPackageDiscount>() {
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
                        //showDialog(LabourActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetLabourPackageDiscount getLabourPackageDiscount) {
                        mdilogue.dismiss();
                        if (getLabourPackageDiscount.getIsSuccess()) {

                            dis_adapter = new LabourdiscountAdapter(LabourActivity.this, getLabourPackageDiscount);
                            discount_recycle.setAdapter(dis_adapter);
                            Log.e("seleced_period===430", seleced_Duration);
                            for (int j = 0; j < getLabourPackageDiscount.getListResult().size(); j++) {
                                Log.e("seleced_period===432", seleced_Duration);
                                Log.e("seleced_period===432d", getLabourPackageDiscount.getListResult().get(j).getDesc());

                                if (seleced_Duration.equals(getLabourPackageDiscount.getListResult().get(j).getDesc())) {
                                    Log.e("seleced_period===432f", seleced_Duration);
                                    Log.e("seleced_period===432df", getLabourPackageDiscount.getListResult().get(j).getDesc());
                                    if (getLabourPackageDiscount.getListResult().get(j).getDesc() != null && seleced_Duration != null) {
                                        discount_label.setVisibility(View.VISIBLE);
                                        harv_d_amount.setVisibility(View.VISIBLE);
                                        pru_d_amount.setVisibility(View.VISIBLE);
                                        harv_amount.setPaintFlags(harv_amount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                        prun_amount.setPaintFlags(prun_amount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                    } else {
                                        discount_label.setVisibility(View.GONE);
                                        harv_d_amount.setVisibility(View.GONE);
                                        pru_d_amount.setVisibility(View.GONE);
                                    }

                                    discount = getLabourPackageDiscount.getListResult().get(j).getDiscountPercentage();
                                    discount_percentage.setText(discount + "%");
                                    Log.e("discount===430", discount + "");
                                }

                            }
                            harvesting_d_amount = harvesting_amount * discount / 100;
                            Log.e("call====864", discount + "discoutnt percentage" +
                                    harvesting_d_amount + "harvesting===with discount " + harvesting_amount);
                            double dis_amount = harvesting_amount - harvesting_d_amount;
                            harv_d_amount.setText(dis_amount + "");

                            prunning_d_amount = prunning_amount * discount / 100;
                            double pru_dis_amount = prunning_amount - prunning_d_amount;
                            pru_d_amount.setText(pru_dis_amount + "");
                        }
                    }


                });
    }

    private void discount_popup() {
        // GetLabourPackageDiscount();
        myDialog.setContentView(R.layout.discount_popup);
        ok = (TextView) myDialog.findViewById(R.id.ok);

        head_text = (TextView) myDialog.findViewById(R.id.head_text);
        getTerms = (TextView) myDialog.findViewById(R.id.txtclose);
        discount_recycle = (RecyclerView) myDialog.findViewById(R.id.recycler_discount);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(LabourActivity.this);
        discount_recycle.setLayoutManager(mLayoutManager);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        getTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        // add OK and Cancel buttons

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        myDialog.show();

    }

    private void ShowPopup() {
        myDialog.setContentView(R.layout.custompopup);
        ok = (TextView) myDialog.findViewById(R.id.ok);
        service_charge = (TextView) myDialog.findViewById(R.id.service_charge);
        head_text = (TextView) myDialog.findViewById(R.id.head_text);
        getTerms = (TextView) myDialog.findViewById(R.id.txtclose);
        terms_recycle = (RecyclerView) myDialog.findViewById(R.id.recycler_term);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(LabourActivity.this);
        terms_recycle.setLayoutManager(mLayoutManager);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        getTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        // add OK and Cancel buttons

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        myDialog.show();


    }

    private void Getterms_conditions() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getterms(APIConstantURL.GetLabourTermsandConditions)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LabourTermsNCondtionsModel>() {
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
                        //showDialog(LabourActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(LabourTermsNCondtionsModel labourTermsNCondtionsModel) {


                        List<ServiceType> serviceTypes = new ArrayList<>();
                        //if(labourTermsNCondtionsModel != null & labourTermsNCondtionsModel.getResult().ge)
                        Log.d("", "onNext: " + labourTermsNCondtionsModel);
                        mdilogue.dismiss();
                        if (labourTermsNCondtionsModel.getIsSuccess()) {

                            Tadapter = new LabourTermsNCondtionsAdapter(LabourActivity.this, labourTermsNCondtionsModel);
                            terms_recycle.setAdapter(Tadapter);

                        }
                    }


                });
    }

    private void CostConfig() {
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getcostconfi(APIConstantURL.CostConfig + 7)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Costconfigres>() {
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
                        //showDialog(LabourActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(Costconfigres costconfigres) {
                        if (costconfigres.getIsSuccess()) {
                            service_charge.setText(getResources().getString(R.string.service) + " " + costconfigres.getResult().getCost() + getResources().getString(R.string.charge));

                            Log.e("service_charge", costconfigres.getResult().getCost() + "");
                        }
                    }

                });
    }

    private boolean validations() {


        if (selected_labour.size() == 0) {
            showDialog(LabourActivity.this, getResources().getString(R.string.Valid_service));

            // showDialog(LabourActivity.this, "Please Select Service Type ");
            return false;
        }
        if (ids_list.size() == 0) {
            showDialog(LabourActivity.this, getResources().getString(R.string.Valid_service));

            // showDialog(LabourActivity.this, "Please Select Service Type ");
            return false;
        }

        if (edittext.getText().toString().matches("")) {
            // Toasty.error(LabourActivity.this, "Please Select the Date", Toast.LENGTH_SHORT).show();
            showDialog(LabourActivity.this, getResources().getString(R.string.date_selectiomn));
            return false;
        }
        if (selected_ids.contains("20") || selected_ids.contains("34") ) {
            if (labourSpinner.getSelectedItemPosition() == 0) {

                showDialog(LabourActivity.this, getResources().getString(R.string.valid_pack));
                return false;
            }
        }
        if (checkbox.isChecked()) {
            // showCustomDialog();

            checkbox.setChecked(true);
        } else {
            //Toasty.error(getApplicationContext(),R.string.terms_agree, Toast.LENGTH_LONG).show();
            showDialog(LabourActivity.this, getResources().getString(R.string.terms_agree));
            return false;
        }

        if (selected_ids.contains("20")) {
            if( harvesting_amount ==0.0){
                showDialog(LabourActivity.this, "You can't Raise the Request since Harvesting Amount is 0");
                return false;
            }

        }
        if (selected_ids.contains("19")) {
            if( prunning_amount ==0.0){
                showDialog(LabourActivity.this, getResources().getString(R.string.failmsg));
                return false;
            }


        }
        if (selected_ids.contains("33")) {
            if( intercrop_prun ==0.0){
                showDialog(LabourActivity.this, getResources().getString(R.string.failmsg));
                return false;
            }


        }
        if (selected_ids.contains("34")) {
            if( intercrop_harv ==0.0){
                showDialog(LabourActivity.this, getResources().getString(R.string.failmsg));
                return false;
            }


        }


        return true;
    }

    private void AddLabourRequestHeader() {
        mdilogue.show();
        JsonObject object = LabourReuestobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.postLabour(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LobourResponse>() {
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
                    public void onNext(LobourResponse lobourResponse) {
                        if (lobourResponse.getIsSuccess()) {
                            mdilogue.dismiss();
                            button_submit.setEnabled(false);
                            new Handler().postDelayed(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.M)
                                @Override
                                public void run() {
                                    //  String selected_name = arrayyTOstring(selected_labour);
                                    Log.e("selected_name===", selected_name);
                                    finalAmount = getAmount();
                                    String Amount = harv_amount.getText().toString();
                                    String date = edittext.getText().toString();
                                    List<MSGmodel> displayList = new ArrayList<>();


//                                        requestModel.setHarvestingAmount(Double.parseDouble((String) harv_amount.getText()));
//                                    } else {
//                                        requestModel.setHarvestingAmount(0.0);
//                                    }
//                                    if (discount_label.getVisibility() == View.VISIBLE) {
//                                        displayList.add(new MSGmodel(getString(R.string.select_labour_ltype), selected_name));
//                                        if (selected_ids.contains("20")) {
//
//                                            displayList.add(new MSGmodel(getResources().getString(R.string.harv_amount), df.format(Double.parseDouble(harv_d_amount.getText() + ""))));
//                                        }
//                                        if (selected_ids.contains("19")) {
//                                            displayList.add(new MSGmodel(getResources().getString(R.string.pru_amount), df.format(Double.parseDouble(pru_d_amount.getText() + ""))));
//                                        }
//                                        if (selected_ids.contains("33")) {
//                                            displayList.add(new MSGmodel(getResources().getString(R.string.intercrop_prunning), un_amount.getText().toString()));
//                                        }
//                                        if (selected_ids.contains("34")) {
//                                            displayList.add(new MSGmodel(getResources().getString(R.string.harv_intercrop), un2_amount.getText().toString()));
//                                        }
//                                        displayList.add(new MSGmodel(getResources().getString(R.string.labour_duration), seleced_Duration));
//
//                                        displayList.add(new MSGmodel(getResources().getString(R.string.starttDate), date));
//                                    } else {
                                        displayList.add(new MSGmodel(getString(R.string.select_labour_ltype), selected_name));
                                        if (selected_ids.contains("20")) {

                                            displayList.add(new MSGmodel(getResources().getString(R.string.harv_amount), df.format(Double.parseDouble(harv_amount.getText() + ""))));
                                        }
                                        if (selected_ids.contains("19")) {
                                            displayList.add(new MSGmodel(getResources().getString(R.string.pru_amount), df.format(Double.parseDouble(prun_amount.getText() + ""))));
                                        }
                                        if (selected_ids.contains("33")) {
                                            displayList.add(new MSGmodel(getResources().getString(R.string.intercrop_prunning), df.format(Double.parseDouble(un_amount.getText().toString()))));
                                        }
                                        if (selected_ids.contains("34")) {
                                            displayList.add(new MSGmodel(getResources().getString(R.string.harv_intercrop), df.format(Double.parseDouble(un2_amount.getText().toString()))));
                                        }

                                        displayList.add(new MSGmodel(getResources().getString(R.string.labour_duration), seleced_Duration));

                                        displayList.add(new MSGmodel(getResources().getString(R.string.slecteddate), date));


//
                                    Log.d(TAG, "------ analysis ------ >> get selected_name in String(): " + selected_name);

                                    showSuccessDialog(displayList, getResources().getString(R.string.success_labour));
                                }
                            }, 300);
                        } else {
                            showDialog(LabourActivity.this, getResources().getString(R.string.failmsg));
                        }

                    }


                });
    }


    private void GetSpinnerLabourType() {
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getLabourService(APIConstantURL.GetLabourServicetype + plot_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Labourservicetype>() {
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
                        // showDialog(LabourActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(Labourservicetype labourservicetype) {
                        if (labourservicetype.getResult() != null) {
                            if(diff>=3)

                            {
                                for (Labourservicetype.Result data : labourservicetype.getResult()
                                ) {

                                    service_name.add(data.getDesc());
                                    labour_uID.add(data.getTypeCdId());}


                            }
                            else{

                                service_name.add(labourservicetype.getResult().get(0).getDesc());
                                labour_uID.add(labourservicetype.getResult().get(0).getTypeCdId());
                            }

                            Log.d(TAG, "RESPONSE======" + service_name + labour_uID);

                            multiSelectionSpinner.setItems(service_name);



//

//
                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }

                });
    }


    private void Getlabour_duration() {

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getLabourduration(APIConstantURL.GetLabourDuration)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<LabourDuration>() {
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
                        // showDialog(LabourActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(LabourDuration labourDuration) {


                        if (labourDuration.getListResult() != null) {
                            listdata.add("Select");
                            for (LabourDuration.ListResult data : labourDuration.getListResult()
                            ) {
                                listdata.add(data.getDesc());
                                period_id.add(data.getTypeCdId());
                            }
                            Log.d(TAG, "RESPONSE======" + listdata);


//

                            ArrayAdapter aa = new ArrayAdapter(LabourActivity.this, R.layout.spinner_item, listdata);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            labourSpinner.setAdapter(aa);

                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });
    }



    @Override
    public void selectedIndices(List<Integer> indices) {
        check_box.setChecked(false);
        if (isOnline()) {
            ids_list.clear();

            for (Integer values : indices) {
                Log.d(TAG, "---- analysis ---- > get selected labour ID :" + labour_uID.get(values));
//if(result = true){
//    labour_uID.get(values).equals;
//    Log.d(TAG, "---- analysis ---- > get selected labour ID result true:" + labour_uID.get(values));
//}
//else {
//    Log.d(TAG, "---- analysis ---- > get selected labour ID result false:" + labour_uID.get(values));
//}
                ids_list.add(labour_uID.get(values));
                Log.d(TAG, "---- analysis ---- > get selected labour ID :" + ids_list);
            }
            if (isOnline()) {
                GetLabourServiceCostByAge();
            } else {
                showDialog(LabourActivity.this, getResources().getString(R.string.Internet));
                //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
            }
            selected_ids = arrayTOstring(ids_list);
            Log.d(TAG, "---- analysis ---- > get selected labour name922 :" + selected_ids);
            if (selected_ids.contains("20")) {
                harv_amount_label.setVisibility(View.VISIBLE);
                harvesting_d_amount = harvesting_amount * discount / 100;
                Log.e("call====864", discount + "discoutnt percentage" + harvesting_d_amount + "harvesting===with discount " + harvesting_amount);

                //double k = (int) (percentage * amount_product) / 100;
            } else {
                harv_amount_label.setVisibility(View.GONE);

            }
//
            if (selected_ids.contains("19")) {
                amount_Label.setVisibility(View.VISIBLE);

            } else {
                amount_Label.setVisibility(View.GONE);
            }
            if (selected_ids.contains("33")) {
                un_amount_label.setVisibility(View.VISIBLE);
            } else {
                un_amount_label.setVisibility(View.GONE);
            }
            if (selected_ids.contains("34")) {
                un2_amount_label.setVisibility(View.VISIBLE);
            } else {
                un2_amount_label.setVisibility(View.GONE);
            }


            if (selected_ids.contains("20") || selected_ids.contains("34") ) {
                listdata.clear();
                Getlabour_duration();
                //   checkpole.setVisibility(View.VISIBLE);
            }

            else if (selected_ids.contains("19") || selected_ids.contains("33") ) {
                listdata.clear();
                listdata.add("1 Day");
                ArrayAdapter aa = new ArrayAdapter(LabourActivity.this, R.layout.spinner_item, listdata);
                aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                labourSpinner.setAdapter(aa);
//                //   checkpole.setVisibility(View.VISIBLE);
            }
            else
            {
                // checkpole.setVisibility(View.VISIBLE);
                listdata.clear();
                listdata.add("Select");
                ArrayAdapter aa = new ArrayAdapter(LabourActivity.this, R.layout.spinner_item, listdata);
                aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                labourSpinner.setAdapter(aa);

            }
        }
    }

    @Override
    public JsonObject selectedStrings(List<String> strings)  {
        check_box.setChecked(false);
        for (int i = 0; i < strings.size(); i++) {
            String name = strings.get(i);
            Log.d(TAG, "---- analysis ---- > get selected labour name :" + name);
            if (!selected_labour.contains(name))
                selected_labour.add(name);

            Log.d(TAG, "---- analysis ---- > get selected labour name :" + selected_labour);

        }

        selected_name = CommonUtil.arrayToString(strings);
        Log.d(TAG, "---- analysis ---- > get selected labour name908 :" + selected_name);


//        if (selected_name.contains("UnKnown1")) {
//            un_amount_label.setVisibility(View.VISIBLE);
//        } else {
//            un_amount_label.setVisibility(View.GONE);
//        }
//
//        if (selected_name.contains("UnKnown2")) {
//            un2_amount_label.setVisibility(View.VISIBLE);
//        } else {
//            un2_amount_label.setVisibility(View.GONE);
//        }


        return null;
    }

    private void GetLabourServiceCostByAge() {

        mdilogue.show();
        JsonObject object = amountReuestobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.postservice_amount(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetAmount>() {
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
                    }

                    @Override
                    public void onNext(GetAmount getAmount) {


                        if (getAmount.getIsSuccess()) {

                            Integer percentage = getAmount.getResult().getservicePercentage();
                            //  amount_Label.setVisibility(View.VISIBLE);
//                            prun_amount.setText(getAmount.getResult().getPrunningCost().toString());
//                            harv_amount.setText(getAmount.getResult().getHarvestCost().toString());
//                            un_amount.setText(getAmount.getResult().getUnKnown1Cost().toString());
//                            un2_amount.setText(getAmount.getResult().getUnKnown2Cost().toString());

                            harvesting_amount = getAmount.getResult().getHarvestCost();
                            prunning_amount = getAmount.getResult().getPrunningCost();
                            intercrop_prun =getAmount.getResult().getUnKnown1Cost();
                            intercrop_harv =getAmount.getResult().getUnKnown2Cost();
                            int harv = (int) (percentage * harvesting_amount) / 100;
                            Log.e("K===1",harv+"");
                            double prun= (double) (percentage * prunning_amount) / 100;
                            Log.e("K===2",prun+"");
                            double prun_inter= (double) (percentage * intercrop_prun) / 100;
                            Log.e("K===3",prun_inter+"");
                            double harv_inter= (double) (percentage * intercrop_harv) / 100;
                            Log.e("K===4",harv_inter+"");



                            Log.e("prunning_amount===",(prunning_amount+prun)+"");
                            int selected_prunning_amount =( int)Math.round(prunning_amount+prun);
                            int selected_harvesting_amount =( int)Math.round(harvesting_amount+harv);
                            int selected_intercrop_prun =( int)Math.round(intercrop_prun+prun_inter);
                            int selected_intercrop_harv =( int)Math.round(intercrop_harv+harv_inter);
                            prun_amount.setText(df.format(selected_prunning_amount));
                            harv_amount.setText(df.format(selected_harvesting_amount));
                            un_amount.setText(df.format(selected_intercrop_prun));
                            un2_amount.setText(df.format(selected_intercrop_harv));
                            //  double  harv_amount =
                        } else {
                            //showDialog(LabourActivity.this, lobourResponse.getEndUserMessage());
                        }

                    }


                });
    }

    private JsonObject amountReuestobject() {
        AmountRequest requestModel = new AmountRequest();
        requestModel.setDateOfPlanting(plantationdate);


        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }


    private JsonObject LabourReuestobject()

    {
        finalAmount = getAmount();
//        Log.d(TAG, "----- analysis ----->> GetAmount -->> Amount :" + finalAmount);
//        String statecode = SharedPrefsData.getInstance(this).getStringFromSharedPrefs("statecode");
//        Log.e("state===",statecode);
        AddLabourRequestHeader requestModel = new AddLabourRequestHeader();
        requestModel.setFarmerCode(Farmer_code);
  requestModel.setFarmerName(SharedPrefsData.getusername(this));
        requestModel.setPlotCode(plot_id);
      //  requestModel.setFarmerName(Farmer_name);
        requestModel.setPalmArea(plot_Age);
        requestModel.setIsFarmerRequest(false);
        requestModel.setComments(commentsTxt.getText().toString());
        requestModel.setPreferredDate(reformattedDate);
        requestModel.setCreatedByUserId(User_id);
        requestModel.setUpdatedByUserId(User_id);
        if (selected_ids.contains("20") || selected_ids.contains("34") ) {
            requestModel.setDurationId(period_id.get(labourSpinner.getSelectedItemPosition() - 1));}
        else{
            requestModel.setDurationId(38);}
        requestModel.setPlotVillage(location);
        requestModel.setYearofPlanting(plantationdate);

        requestModel.setClusterId(clusterId);
        requestModel.setOwnPole(ownPole);
        requestModel.setServices(selected_name);
        requestModel.setPackage(seleced_Duration);
        requestModel.setStateCode(statecode);
        requestModel.setStateName(statename);
//        requestModel.setHarvestingAmount(0.00);
//        requestModel.setPruningAmount(0.00);
//        requestModel.setHarvestingAmount(harvesting_amount);
//        requestModel.setPruningAmount(prunning_amount);
//        requestModel.setUnKnown1Amount(intercrop_prun);
//        requestModel.setUnKnown2Amount(intercrop_harv);

//        requestModel.setPlotMandal(plotMandal);
//        requestModel.setPlotState(plotState);
//        requestModel.setPlotDistrict(plotDistrict);

        String val = arrayTOstring(ids_list);
        Log.d(TAG, "------ analysis ------ >> get values in String(): " + val);

        requestModel.setServiceTypes(val);

        requestModel.setCreatedDate(currentDate);

        requestModel.setUpdatedDate(currentDate);
        requestModel.setAmount(null);
        if (selected_ids.contains("20")) {

            requestModel.setHarvestingAmount(harvesting_amount);
        } else {
            requestModel.setHarvestingAmount(0.0);
        }
        if (selected_ids.contains("19")) {
            requestModel.setPruningAmount(prunning_amount);
        }
        else{
            requestModel.setPruningAmount(0.0);}

        if (selected_ids.contains("33")) {
            requestModel.setUnKnown1Amount(intercrop_prun);
        }
        else{
            requestModel.setUnKnown1Amount(0.0);}

        if (selected_ids.contains("34")) {
            requestModel.setUnKnown2Amount(intercrop_harv);
        }
        else{
            requestModel.setUnKnown2Amount(0.0);}


        String selected_name = arrayyTOstring(selected_labour);
//
//        if (selected_name.contains("Harvesting")) {
//            requestModel.setHarvestingAmount(Double.parseDouble((String) harv_amount.getText()));
//        }else {
//            requestModel.setHarvestingAmount(0.0);
//        }
//
//        if (selected_name.contains("Pruning")) {
//            requestModel.setPruningAmount(Double.parseDouble((String) prun_amount.getText()));}
//        else {
//            requestModel.setPruningAmount(0.0);
//        }
//
//        if (selected_name.contains("UnKnown1")) {
//            requestModel.setUnKnown1Amount(Double.parseDouble((String) un_amount.getText()));
//        }else {  requestModel.setUnKnown1Amount(0.0);}
//
//        if (selected_name.contains("UnKnown2")) {
//            requestModel.setUnKnown2Amount(Double.parseDouble((String) un2_amount.getText()));
//        }else { requestModel.setUnKnown2Amount(0.0);}
//


//      if (harv_amount.getVisibility() == View.VISIBLE) {
//          requestModel.setHarvestingAmount(harvesting_amount);
//        } else {
//            requestModel.setHarvestingAmount(0.0);
//        }
//
//
//        if (prun_amount.getVisibility() == View.VISIBLE) {
//            requestModel.setPruningAmount(prunning_amount);
//        }
//        else{
//            requestModel.setPruningAmount(0.0);}
//
//
//        if (un_amount.getVisibility() == View.VISIBLE) {
//            requestModel.setUnKnown1Amount(intercrop_prun);
//        }
//        else{
//            requestModel.setUnKnown1Amount(0.0);}
//
//
//        if (un2_amount.getVisibility() == View.VISIBLE) {
//            requestModel.setUnKnown2Amount(intercrop_harv);
//        }
//        else{
//            requestModel.setUnKnown2Amount(0.0);}


//        requestModel.setUnKnown1Amount(Double.parseDouble((String) un_amount.getText()));
//        requestModel.setUnKnown2Amount(Double.parseDouble((String) un2_amount.getText()));

        // TODO
        // clearalllists();

        return new Gson().toJsonTree(requestModel).getAsJsonObject();


    }

    private String getAmount() {

        Double finalAmount = 0.0;
        if (harv_amount.getVisibility() == View.VISIBLE && !TextUtils.isEmpty(harv_amount.getText())) {
            finalAmount = finalAmount + Double.parseDouble(String.valueOf(harv_amount.getText()));
        }
        if (prun_amount.getVisibility() == View.VISIBLE && !TextUtils.isEmpty(prun_amount.getText())) {
            finalAmount = finalAmount + Double.parseDouble(String.valueOf(prun_amount.getText()));
        }

        if (un_amount.getVisibility() == View.VISIBLE && !TextUtils.isEmpty(un_amount.getText())) {
            finalAmount = finalAmount + Double.parseDouble(String.valueOf(un_amount.getText()));
        }

        if (un2_amount.getVisibility() == View.VISIBLE && !TextUtils.isEmpty(un2_amount.getText())) {
            finalAmount = finalAmount + Double.parseDouble(String.valueOf(un2_amount.getText()));
        }

        return "" + finalAmount;

    }

    public String arrayTOstring(List<Integer> arrayList) {
        StringBuilder string = new StringBuilder();
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (i == 0)
                    string.append("" + arrayList.get(i));
                else
                    string.append("," + arrayList.get(i));
            }
        }
        return string.toString();
    }

    public String arrayyTOstring(List<String> arrayList) {
        StringBuilder string = new StringBuilder();
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (i == 0)
                    string.append("" + arrayList.get(i));
                else
                    string.append("," + arrayList.get(i));
            }
        }
        return string.toString();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override

    public void onDateSet(DatePickerDialog view, int year,
                          int monthOfYear, int dayOfMonth) {
        int month = monthOfYear + 1;
        String formattedMonth = "" + month;
        String formattedDayOfMonth = "" + dayOfMonth;

        if (month < 10) {

            formattedMonth = "0" + month;
        }
        if (dayOfMonth < 10) {

            formattedDayOfMonth = "0" + dayOfMonth;
        }
        String dateVal = formattedDayOfMonth + "/" + formattedMonth + "/" + year;


        edittext.setText(dateVal);


        SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat output = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date oneWayTripDate = input.parse(dateVal);

            reformattedDate = output.format(oneWayTripDate);
            //  GetAll_tokens_closed();
            Log.e("===============", "======sending_date===========" + reformattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        HolidayList();
    }

    private void HolidayList() {
        mdilogue.show();
        JsonObject object = holidayobject();
        ApiService service = ServiceFactory.createRetrofitService(LabourActivity.this, ApiService.class);
        mSubscription = service.postholiday(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RespHoliday>() {
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
                    }

                    @Override
                    public void onNext(RespHoliday respHoliday) {

                        Log.e("test=== ",respHoliday.getIsSuccess()+"");
                        if (respHoliday.getIsSuccess()){
                            Log.e("test=== ",respHoliday.getIsSuccess()+"");
                        }
                        else {
                            edittext.getText().clear();
                            showDialog(LabourActivity.this, "Please Select Another Date.It is Holiday.");

                        }
                    }




                });

    }

    private JsonObject holidayobject() {
        PostHoliday requestModel = new PostHoliday();
        requestModel.setPreferredDate(reformattedDate);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

}
