package in.calibrage.palm360fa.Views.Activities;

import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Model.FarmerOtpResponceModel;
import in.calibrage.palm360fa.Model.GetIssueModel;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.MSGmodel;
import in.calibrage.palm360fa.Model.PaymentsType;
import in.calibrage.palm360fa.Model.TransportResp;
import in.calibrage.palm360fa.Model.Transportobject;
import in.calibrage.palm360fa.Model.UserClusters;
import in.calibrage.palm360fa.Model.resGet3FInfo;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.Constants;
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

public class TransportActivity extends BaseActivity {
    private static final String TAG = TransportActivity.class.getSimpleName();
    private Subscription mSubscription;
    String Farmer_code;
    ImageView backImg,home_btn;
    private SpotsDialog mdilogue ;
    Spinner vehicle_type,from_location,Tolocation,paymentspin;
    String selected_vehicle_type,selectedF_location,selectedT_location,seleced_payment;
    List<Integer> vehicle_Id = new ArrayList<Integer>();
    List<String> vehicletype = new ArrayList<String>();

    List<Integer> fromlocation_Id = new ArrayList<Integer>();
    List<String> fromlocationname = new ArrayList<String>();


    List<Integer> tolocation_Id = new ArrayList<Integer>();
    List<String> tolocationname = new ArrayList<String>();
    Button savetransport;
    String statecode,UserID;
    List<String> listdata = new ArrayList<>();

    PaymentsType paymentsTypes;
    List<Integer> payment_id = new ArrayList<Integer>();
    EditText tranportprice,driverName,vehicle_number;
    String selectedplots;
    String selectedtransport;
    int selectedtransportid;
    TextView farmar_code,farmer_name,trans_type,cluster_name,select_plots;
    LoginResponse created_user;
    private Integer User_id;
    String Farmer_name,currentDate;
    private FarmerOtpResponceModel catagoriesList;
    Integer Cluster_id;
    String statename;
    List<String>plots_list = new ArrayList<>();
    boolean yielding;
    DecimalFormat dform = new DecimalFormat("#,###,##0.00");
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
        setContentView(R.layout.activity_transport);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            selectedplots = extras.getString("plotcode");
            selectedtransport = extras.getString("transporttype");
            selectedtransportid = extras.getInt("transporttypeId");
            yielding = extras.getBoolean("yielding");
            plots_list = (ArrayList<String>) getIntent().getSerializableExtra("plotslist");
            Log.e("============>165",selectedtransport +"=="+
                    selectedtransportid+ "==>plots"+selectedplots);

            Log.e("====>plots_list",plots_list.size()+"");
            Log.e("====>yielding125",yielding+"");


        }
        initview();
        setViews();
    }

    private void setViews() {


        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TransportplotActivity.class);
                startActivity(intent);
            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TransportActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        statecode = SharedPrefsData.getInstance(this).getStringFromSharedPrefs("statecode");
        UserID=SharedPrefsData.getInstance(this).getStringFromSharedPrefs("id");
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");
        farmar_code.setText(Farmer_code+"");

        created_user = SharedPrefsData.getCreatedUser(TransportActivity.this);

        User_id= created_user.getResult().getUserInfos().getId();

        String middlename = created_user.getResult().getUserInfos().getMiddleName();
        String finalmiddlename = "";
        if ((middlename != null && !middlename.isEmpty() && !middlename.equals("null"))) {
            finalmiddlename = middlename + " ";
        }
        Farmer_name =created_user.getResult().getUserInfos().getFirstName()  + " " + finalmiddlename + created_user.getResult().getUserInfos().getLastName();
        farmer_name.setText(SharedPrefsData.getusername(this)+"");
        trans_type.setText(selectedtransport+"");
        select_plots.setText(selectedplots+"");
        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Log.i("LOG_RESPONSE date ", currentDate);


        if (isOnline()){
            getPaymentMods();
            getvehicletype();
            getgowdownsnurseryies(statecode);
            gettolocations();


        }
        else {
            showDialog(TransportActivity.this, getResources().getString(R.string.Internet));

        }

        savetransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if (validations()) {

                    if (isOnline())
                        Addtransportreq();
                    else {
                        showDialog(TransportActivity.this, getResources().getString(R.string.Internet));
                        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
                    }

                    //
                }

            }


        });
        vehicle_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_vehicle_type = vehicle_type.getItemAtPosition(vehicle_type.getSelectedItemPosition()).toString();
                Log.e("selectedvehicletype==>",selected_vehicle_type);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        from_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedF_location = from_location.getItemAtPosition(from_location.getSelectedItemPosition()).toString();
                Log.e("selectedF_location==>",selectedF_location);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        Tolocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedT_location = Tolocation.getItemAtPosition(Tolocation.getSelectedItemPosition()).toString();
                Log.e("selectedT_location==>",selectedT_location);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });



        paymentspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 seleced_payment = paymentspin.getItemAtPosition(paymentspin.getSelectedItemPosition()).toString();
                Log.e("seleced_payment==", seleced_payment);
                if (!yielding) {
                    paymentspin.setSelection(1);
                }

//            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }

    private void Addtransportreq()
    {

        catagoriesList = SharedPrefsData.getCatagories(this);
        if (null != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId() && 0 != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId())
            Cluster_id =  catagoriesList.getResult().getFarmerDetails().get(0).getClusterId();
        Log.e("Cluster_id===",Cluster_id+"");

        statename =catagoriesList.getResult().getFarmerDetails().get(0).getStateName();
        mdilogue.show();
        JsonObject object = TransReuestobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.postTransportRequest(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TransportResp>() {
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
                    public void onNext(TransportResp transportResp) {
                        Log.e("transportResp===281",transportResp+"");

                        if (transportResp.getIsSuccess()) {
                            mdilogue.dismiss();
                            savetransport.setEnabled(false);
                            // Toast.makeText(getApplicationContext(), "sucess", Toast.LENGTH_SHORT).show();

                            new Handler().postDelayed(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.M)
                                @Override
                                public void run() {

                                    List<MSGmodel> displayList = new ArrayList<>();
                                    displayList.add(new MSGmodel(getString(R.string.farmer_namewithcode), SharedPrefsData.getusername(TransportActivity.this)+" ("+ Farmer_code +")"));
                                    displayList.add(new MSGmodel(getString(R.string.str_selected_plots), selectedplots));

                                    displayList.add(new MSGmodel(getString(R.string.trans_type), selectedtransport));
                                    displayList.add(new MSGmodel(getString(R.string.vehicle_type), selected_vehicle_type));

                                    displayList.add(new MSGmodel(getResources().getString(R.string.vehicle_Number), vehicle_number.getText()+"" ));
                                    displayList.add(new MSGmodel(getResources().getString(R.string.driver_name),driverName.getText()+"" ));

                                    displayList.add(new MSGmodel(getResources().getString(R.string.fromlocation), selectedF_location));

                                    displayList.add(new MSGmodel(getResources().getString(R.string.tolocation), selectedT_location));
                                    //Double subAmount = subsidy_amountt- include_gst_amount dec.format(Gst_total)

                                    displayList.add(new MSGmodel(getResources().getString(R.string.paymentMode),seleced_payment));
                                    displayList.add(new MSGmodel(getResources().getString(R.string.transportprice),dform.format(Double.parseDouble(tranportprice.getText().toString()))));


                                    showSuccessDialog(displayList, getString(R.string.success_transport));
                                }
                            }, 300);
                        } else {
                            showDialog(TransportActivity.this, transportResp.getEndUserMessage());
                        }
                    }



                });

    }

    private JsonObject TransReuestobject()
        {

            Transportobject requestModel = new Transportobject();
            requestModel.setId(0);
            requestModel.setRequestTypeId(108);
            requestModel.setTransportTypeId(selectedtransportid);
            requestModel.setVehicleTypeId(vehicle_Id.get(vehicle_type.getSelectedItemPosition() - 1));
            requestModel.setVehicleNumber(vehicle_number.getText()+"");
            requestModel.setDriverName(driverName.getText()+"");
            if(selectedtransportid == 109) {
            requestModel.setGodownId(fromlocation_Id.get(from_location.getSelectedItemPosition() - 1)+"");
                requestModel.setNurseryId(null);}
            else{
                requestModel.setGodownId(null);
                requestModel.setNurseryId(fromlocation_Id.get(from_location.getSelectedItemPosition() - 1)+"");
            }

            requestModel.setToLocation(tolocation_Id.get(Tolocation.getSelectedItemPosition() - 1));
            requestModel.setPaymentTypeId((payment_id.get(paymentspin.getSelectedItemPosition() - 1)));
            requestModel.setRequestCode(null);
            requestModel.setFarmerCode(Farmer_code);
            requestModel.setPlotCode(null);
            requestModel.setReqCreatedDate(currentDate);
            requestModel.setStatusTypeId(15);
            requestModel.setIsFarmerRequest(false);
            requestModel.setCreatedByUserId(User_id);
            requestModel.setUpdatedByUserId(User_id);
            requestModel.setCreatedDate(currentDate);
            requestModel.setUpdatedDate(currentDate);
            requestModel.setTotalCost(Double.parseDouble(tranportprice.getText().toString()));
            requestModel.setComments(null);
            requestModel.setCropMaintainceDate(null);
            requestModel.setIssueTypeId(null);
            requestModel.setFarmerName(SharedPrefsData.getusername(this));
            requestModel.setPalmArea(null);
            requestModel.setPlotVillage(null);
            requestModel.setYearofPlanting(null);
            requestModel.setTotalCostWithoutServiceCharge(null);
            requestModel.setServiceChargeAmount(null);
            requestModel.setParentRequestCode(null);
            requestModel.setRecoveryFarmerCode(null);
            requestModel.setClusterId(Cluster_id);
            requestModel.setStateCode(statecode);
            requestModel.setStateName(statename);
            requestModel.setServerUpdatedStatus(null);
            List<Transportobject.PlotTransportDetail> plotRepo = new ArrayList<>();

            for (int i = 0; i < plots_list.size(); i++) {
Log.e("====>plotcode3444",plots_list.get(i));
                Transportobject.PlotTransportDetail plotdetails = new Transportobject.PlotTransportDetail();
                plotdetails.setId(0);
                plotdetails.setRequestCode(null);
                plotdetails.setPlotCode(plots_list.get(i));


                plotRepo.add(plotdetails);
            }
            requestModel.setRequestplotDetails(plotRepo);

            return new Gson().toJsonTree(requestModel).getAsJsonObject();



    }

    private boolean validations() {
        Log.e("=======>422",vehicle_number.getText().toString().trim());

        if (vehicle_type.getSelectedItemPosition() == 0) {

            showDialog(TransportActivity.this, getResources().getString(R.string.validatevehicletype));
            return false;
        }

        if (vehicle_number.getText().toString().matches("")) {
            // Toasty.error(LabourActivity.this, "Please Select the Date", Toast.LENGTH_SHORT).show();
            showDialog(TransportActivity.this, getResources().getString(R.string.validatevehiclenumber));
            return false;
        }

        if(isValidvehicleNo(vehicle_number.getText().toString().trim()))
        {
          //  Toast.makeText(getApplicationContext(), "Valid Email Address.", Toast.LENGTH_SHORT).show();
        }else{
          //  Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
            showDialog(TransportActivity.this, getResources().getString(R.string.validatevehiclenumber1));
            return false;
        }
//        if( isValidvehicleNo(vehicle_number.getText().toString())){
//            showDialog(TransportActivity.this, getResources().getString(R.string.validatevehiclenumber));
//           Please Enter Valid Vehicle Number ;
//        }
        if (driverName.getText().toString().matches("")) {
            // Toasty.error(LabourActivity.this, "Please Select the Date", Toast.LENGTH_SHORT).show();
            showDialog(TransportActivity.this, getResources().getString(R.string.validatedrivername));
            return false;
        }
        if (from_location.getSelectedItemPosition() == 0) {

            showDialog(TransportActivity.this, getResources().getString(R.string.validatefromlocation));
            return false;
        }

        if (Tolocation.getSelectedItemPosition() == 0) {

            showDialog(TransportActivity.this, getResources().getString(R.string.validatetolocation));
            return false;
        }
        if (paymentspin.getSelectedItemPosition() == 0) {

            showDialog(TransportActivity.this, getResources().getString(R.string.paym_validation));
            return false;
        }
        if (tranportprice.getText().toString().matches("")) {
            // Toasty.error(LabourActivity.this, "Please Select the Date", Toast.LENGTH_SHORT).show();
            showDialog(TransportActivity.this, getResources().getString(R.string.tranportprice));
            return false;
        }



        return true;
    }

    public static boolean isValidvehicleNo(String str)
    {


        return Pattern.compile("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$").matcher(str).matches();
    }


    //Payment mode
    private void getPaymentMods() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getpaymentModes(APIConstantURL.GetPaymentsTypeByFarmerCode + SharedPrefsData.getInstance(this).getStringFromSharedPrefs(Constants.USER_ID))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<PaymentsType>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.cancel();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mdilogue.cancel();
                        showDialog(TransportActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(PaymentsType paymentsType) {
                        paymentsTypes = paymentsType;
                        mdilogue.cancel();
                        if (paymentsType.getListResult() != null) {
                            listdata.add("Select");
                            for (PaymentsType.ListResult string : paymentsType.getListResult()
                            ) {
                                listdata.add(string.getDesc());
                                payment_id.add(string.getTypeCdId());
                            }


                            Log.d(TAG, "RESPONSE======" + listdata);

//

                            ArrayAdapter aa = new ArrayAdapter(TransportActivity.this, R.layout.spinner_item, listdata);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            paymentspin.setAdapter(aa);
                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }
                });
    }


    private void gettolocations() {
          mdilogue.show();  
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getclusters(APIConstantURL.GetClusters + UserID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<UserClusters>() {
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
                        showDialog(TransportActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(UserClusters userclusters) {


                        if (userclusters.getListResult() != null) {
                            tolocationname.add("Select");
                            for (UserClusters.ListResult data : userclusters.getListResult()
                            ) {
                                tolocationname.add(data.getClusterName());
                                tolocation_Id.add(data.getClusterId());
                            }
                            Log.d(TAG, "RESPONSE======" + tolocationname);

//

                            ArrayAdapter aa = new ArrayAdapter(TransportActivity.this, R.layout.spinner_item, tolocationname);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            Tolocation.setAdapter(aa);
                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });

    }

    private void getgowdownsnurseryies(String code) {
        SharedPreferences pref = this.getSharedPreferences("FARMER", MODE_PRIVATE);
        String Farmer_code = pref.getString("farmerid", "").trim();
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.get3finfo(APIConstantURL.Get3FInfo + Farmer_code + "/" + code)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<resGet3FInfo>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.cancel();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mdilogue.cancel();
                        Log.d(TAG, "---- analysis ---->GetContactInfo -->> error -->> :" + e.getLocalizedMessage());
                        showDialog(TransportActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final resGet3FInfo resGet3FInfo) {

                      if(selectedtransportid == 109) {

                            if (resGet3FInfo.getResult().getImportantPlaces().getGodowns() != null && resGet3FInfo.getResult().getImportantPlaces().getGodowns().size() != 0) {
                                fromlocationname.add("Select");
                                for (resGet3FInfo.Godown data : resGet3FInfo.getResult().getImportantPlaces().getGodowns()
                                ) {
                                    fromlocationname.add(data.getGodown());
                                    fromlocation_Id.add(data.getGodownId());
                                }
                                Log.d(TAG, "RESPONSE======" + vehicletype);

//

                                ArrayAdapter aa = new ArrayAdapter(TransportActivity.this, R.layout.spinner_item, fromlocationname);
                                aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                                from_location.setAdapter(aa);
                            } else {
                                Log.e("nodada====", "nodata===custom2");

                            }

                        }
                        else{
                        if (resGet3FInfo.getResult().getImportantPlaces().getNurseries() != null
                                && resGet3FInfo.getResult().getImportantPlaces().getNurseries().size()!= 0) {
                            fromlocationname.add("Select");
                            for (resGet3FInfo.Nurseries data : resGet3FInfo.getResult().getImportantPlaces().getNurseries()
                            ) {
                                fromlocationname.add(data.getNurseryName());
                                fromlocation_Id.add(data.getNurseryId());
                            }
                            Log.d(TAG, "RESPONSE======" + vehicletype);

//

                            ArrayAdapter aa = new ArrayAdapter(TransportActivity.this, R.layout.spinner_item, fromlocationname);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            from_location.setAdapter(aa);
                        } else {
                            Log.e("nodada====", "nodata===custom2");

                      }

                    }
                    }
    });

    }

    private void getvehicletype() {
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getIssuestypes(APIConstantURL.vehicleType)
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
                        showDialog(TransportActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetIssueModel getIssueModel) {


                        if (getIssueModel.getListResult() != null) {
                            vehicletype.add("Select");

                            for (int i = 0; i < getIssueModel.getListResult().size(); i++) {
                                vehicletype.add(getIssueModel.getListResult().get(i).getDesc());
                                vehicle_Id.add(getIssueModel.getListResult().get(i).getTypeCdId());
                                if(getIssueModel.getListResult().get(i).getTypeCdId() == 48 || getIssueModel.getListResult().get(i).getTypeCdId() == 50  || getIssueModel.getListResult().get(i).getTypeCdId() == 51 ){
                                    vehicletype.remove(getIssueModel.getListResult().get(i).getDesc());
                                    vehicle_Id.remove(getIssueModel.getListResult().get(i).getTypeCdId());
                                }

                            }
//                            for (GetIssueModel.ListResult data : getIssueModel.getListResult()
//                            ) {
//                                vehicletype.add(data.getDesc());
//                                vehicle_Id.add(data.getTypeCdId());
//
//                            }
//

                            Log.d(TAG, "RESPONSE======" + vehicletype);

//

                            ArrayAdapter aa = new ArrayAdapter(TransportActivity.this, R.layout.spinner_item, vehicletype);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            vehicle_type.setAdapter(aa);

                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });
    }



    private void initview() {

            backImg = (ImageView) findViewById(R.id.back);
            home_btn = (ImageView) findViewById(R.id.home_btn);
            mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                    .setContext(this)
                    .setTheme(R.style.Custom)
                    .build();
        vehicle_type = findViewById(R.id.vehicle_type);
        savetransport = findViewById(R.id.savetransport);
        from_location = findViewById(R.id.fromlocation);
        Tolocation = findViewById(R.id.Tolocation);
        paymentspin = (Spinner) findViewById(R.id.paymentSpinner);
        vehicle_number = findViewById(R.id.vehicle_number);
        driverName = findViewById(R.id.driverName);
        tranportprice = findViewById(R.id.tranportprice);
        farmar_code = findViewById(R.id.farmar_code);
        farmer_name = findViewById(R.id.farmer_name);
        trans_type = findViewById(R.id.trans_type);
        cluster_name = findViewById(R.id.cluster_name);
        select_plots = findViewById(R.id.select_plots);

    }
}