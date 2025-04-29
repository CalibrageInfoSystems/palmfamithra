package in.calibrage.palm360fa.Views.transport;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Adapter.AutoCompleteUserAdapter;
import in.calibrage.palm360fa.Adapter.MoreVehiclesAdapter;
import in.calibrage.palm360fa.Adapter.Vehicle;
import in.calibrage.palm360fa.Adapter.VehicleHireChargesAdapter;
import in.calibrage.palm360fa.Model.AddFarmerTransportationRequest;
import in.calibrage.palm360fa.Model.AddFarmerTransportationResponse;
import in.calibrage.palm360fa.Model.Farmersearchobject;
import in.calibrage.palm360fa.Model.Farmersearchresponse;
import in.calibrage.palm360fa.Model.GetCompanyTransport;
import in.calibrage.palm360fa.Model.GetDriverforTransportationTypes;
import in.calibrage.palm360fa.Model.GetDurationTypes;
import in.calibrage.palm360fa.Model.GetLabourTypes;
import in.calibrage.palm360fa.Model.GetPaymentModeTypes;
import in.calibrage.palm360fa.Model.GetPlotVillagesByFarmerCode;
import in.calibrage.palm360fa.Model.GetTypeofServices;
import in.calibrage.palm360fa.Model.Getdestinations;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.MSGmodel;
import in.calibrage.palm360fa.Model.VehicleTypeResponse;
import in.calibrage.palm360fa.Model.Vehicletype;
import in.calibrage.palm360fa.Model.farmer;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.Views.Activities.HomeActivity;
import in.calibrage.palm360fa.Views.Activities.QRScanner_Activity;
import in.calibrage.palm360fa.Views.transport.Model.Item;
import in.calibrage.palm360fa.Views.transport.Model.Transporthirebasis;
import in.calibrage.palm360fa.Views.transport.Model.TypeItem;
import in.calibrage.palm360fa.Views.transport.Model.Vehicledata;
import in.calibrage.palm360fa.Views.transport.Model.VillageWithData;
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

public class FarmerTransportServiceActivity extends BaseActivity implements MoreVehiclesAdapter.VechileListListner, VehicleHireChargesAdapter.VehicleHireChargesAdapterListener,MultiSelectionSpinner.OnMultipleItemSelectedListener {
    public static String TAG = FarmerTransportServiceActivity.class.getSimpleName();
    private int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private AutoCompleteUserAdapter _farmerDataAutoCompleteAdapter;
    private ArrayList<farmer> _farmerDataArrayList = new ArrayList<>();
    private ArrayList<Vehicle> ownvehicles_arrayList = new ArrayList<Vehicle>();
    private ArrayList<Item> typeOfServiceArray =new ArrayList<>();
    private ArrayList<TypeItem> _vechileTypeArray, _driverTypeArray, _driverpaymanetdurationTypeArr, _labourTypeArray,_labourDurationTypeArr, _labourpaymentTypeArr, _companyTransportTypeArr, _knowaboutAkshayaArr ;

    private ArrayList<String> get_vehicleType, get_driverType, get_driverdurationType;
    private ArrayList<Integer> get_vehicleType_Id = new ArrayList<Integer>();


    private ArrayList<Integer> get_driverType_Id = new ArrayList<Integer>();


    private ArrayList<Integer> get_driverdurationType_Id = new ArrayList<Integer>();


    ImageView backImg, home_btn;
    private SpotsDialog mdilogue;
    RecyclerView addvehicleData, donthaveownVehicleRecyclerview;
    private Spinner _spinnerOwnVechile, _spinnertypeofLabour, _spinnerlabourchargespaymentduation, _spinnerlabourchargespaymentmode, _spinnerhireTractors,_spinnerinterestedinCompanyTransport,_spinnerknowaboutAkshaya;
    MultiSelectionSpinner _multispinnertypeofservice;
    LinearLayout haveownvehiclelyt, donthaveownvehiclelyt, hiredriverlyt, contractLabour,typeofserivelyt;
    EditText et_driverpaymentamount, et_problem, et_labourchargespaymentamount, et_othertypelabourchargepaymentmode, et_suggestions, et_needotherservices, et_otherTypeofservice, et_othertypevehicle;
    Button btn_addmorevehicles, submit;
    ImageButton qrscan;
    private Dialog _dialog;
    private Subscription mSubscription;
    String  _farmercode,
            _OnlyFarmerCode,
            _contactnumber,
            _farmerName,
            _farmerNamewithCode,


            selectedvehicleType,
            selecteddriverfortransportation,
            selecteddriverpaymentamountper,
            selectedvehiclecurrentlyonRent,
            selectedwillingtorent;


    int     _farmer_villageId,
            vehicletypeid,
            driverforTransportationid,
            driverpaymentperduationId;

    Double driverpaymentamount,_driveramount;
    LinearLayoutManager linearLayoutManager;
    MoreVehiclesAdapter adapter;


    AutoCompleteTextView _farmerAutoComplete;
    SpinnerTypeArrayAdapter _labourTypeAdapter, _labourpaymentDuationAdapter,_labourpaymentTypeAdapter,_companyTransportAdapter, _knowaboutAkshayaAdapter;


     Spinner _spinnervehicleType, _spinnerDriverforTransportation, _spinnerDriverpaymentperduation, _spinnervehicleonRent, _spinnerwillingtoRent;
    // User Data
    LoginResponse created_user;
    Double _labourAmount;
    boolean isOwnVechile = false,ishireTractor = false, isknowAkshaya = false,isvehicleonrent = false, iswillingtorent = false;
    String _hiringProblems,_otherLabourPaymentMode,_suggestions,_needothertypeofservice;
    int _labourTypeId;
    TypeItem labourTypeItem,paymentDurationTypeItem,paymentModeTypeItem, companyTransportTypeItem,driverforTransportationTypeItem,driverpaymentdurationTypeItem;
    ArrayList<Item> selectedServices = new ArrayList<>();
    ArrayList<Item> selected_Services = new ArrayList<>();

    Getdestinations getdestination;
    VehicleHireChargesAdapter vehicleHireChargesAdapter;
    private ArrayList<Vehicletype> vehicletypelist = new ArrayList<>();
    ArrayList<VillageWithData> finaldatalistfromActivity = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_transport_service);

        init();
        bind();
        noinit();
        nobind();
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
    }


    private void init() {

        home_btn = (ImageView) findViewById(R.id.home_btn);
        backImg = (ImageView) findViewById(R.id.back);
        _farmerAutoComplete = findViewById(R.id.farmerAutoComplete);
        _spinnerOwnVechile = findViewById(R.id.spinnerOwnVechile);
        haveownvehiclelyt = findViewById(R.id.haveownvehicle);
        donthaveownvehiclelyt = findViewById(R.id.donthaveownvehiclee);
        hiredriverlyt = findViewById(R.id.hiredriverlyt);

        btn_addmorevehicles = findViewById(R.id.btn_addmorevehicles);
        et_problem = findViewById(R.id.et_problem);
        _spinnertypeofLabour = findViewById(R.id.typeofLabour);
        et_labourchargespaymentamount = findViewById(R.id.et_labourchargespaymentamount);
        _spinnerlabourchargespaymentduation = findViewById(R.id.labourchargespaymentduation);
        _spinnerlabourchargespaymentmode = findViewById(R.id.labourchargespaymentmode);
        et_othertypelabourchargepaymentmode = findViewById(R.id.et_othertypelabourchargepaymentmode);
        _spinnerhireTractors =  findViewById(R.id.spinnerhireTractors);
        _multispinnertypeofservice = findViewById(R.id.multiSpinnertypeofservice);
        _multispinnertypeofservice.setListener(this);
        _spinnerinterestedinCompanyTransport = findViewById(R.id.spinnercompanytransportinterest);
        _spinnerknowaboutAkshaya = findViewById(R.id.spinnerknowaboutAkshaya);
        typeofserivelyt = findViewById(R.id.typeofserivelyt);
        et_suggestions = findViewById(R.id.suggestions_et);
        et_needotherservices = findViewById(R.id.needanyotherservices_et);
        contractLabour = findViewById(R.id.clabourchargeslayout);
        et_otherTypeofservice = findViewById(R.id.et_othertypeofservice);
        submit = findViewById(R.id.btn_submit);
        qrscan = findViewById(R.id.btn_qrscan);

        addvehicleData = findViewById(R.id.addvehicledatarecycleview);
        linearLayoutManager = new LinearLayoutManager(FarmerTransportServiceActivity.this);
        addvehicleData.setLayoutManager(linearLayoutManager);
        btn_addmorevehicles.setVisibility(View.GONE);


        _spinnerOwnVechile.setEnabled(false);
    }

    private void noinit() {





    }


    private void bind() {


        created_user = SharedPrefsData.getCreatedUser(FarmerTransportServiceActivity.this);
        _farmerAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                _farmerAutoComplete.setText(_farmerDataAutoCompleteAdapter.getItemfarmercodewithnameAtPosition(position));
                _farmerAutoComplete.setSelection(_farmerAutoComplete.getText().toString().trim().length());

                // Binding Farmer Data
                _farmercode = _farmerAutoComplete.getText().toString().trim();
                _OnlyFarmerCode =  _farmerDataAutoCompleteAdapter.getItem(position).getFarmercode();
                _farmerName = _farmerDataAutoCompleteAdapter.getItemfarmerNameAtPosition(position);
                _contactnumber = _farmerDataAutoCompleteAdapter.getItemfarmerMobileNumberAtPosition(position);
                _farmer_villageId = _farmerDataAutoCompleteAdapter.getItemfarmerVillageAtPosition(position);

//                Log.d(TAG, "======> Analysis ================( _farmerAutoComplete.setOnItemClickListener())============================");
//                Log.d(TAG,"====> Analysis ==== > Farmer Code : "+_farmercode);

            }
        });

        _farmerAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if (_farmerAutoComplete.isPerformingCompletion()) {
                    // An item has been selected from the list. Ignore.
                } else {
                    if (s.toString().toUpperCase().trim().length() > 4 && s.toString().toUpperCase().trim().length() < 17) {
                        resetSpinnerOwnVechile();
                        populateFarmerData(s.toString().trim());
                    } else {
                        _farmercode = null;
                        resetSpinnerOwnVechile();

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        _spinnerOwnVechile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (_farmercode == null || TextUtils.isEmpty(_farmercode)) {
                    resetSpinnerOwnVechile();
                    if (i != 0) {
                        showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.pleaseselectfarmer));
                    }

                } else if (i > 0) {

                    if (i == 1) {
                        btn_addmorevehicles.setVisibility(View.VISIBLE);
                        addvehicleData.setVisibility(View.VISIBLE);
                        haveownvehicle();


                    } else {
                        btn_addmorevehicles.setVisibility(View.GONE);
                        ownvehicles_arrayList.clear();
                        addvehicleData.setVisibility(View.GONE);
                        donthaveownvehicle();
                        Log.d(TAG,"================> FARMER CODE :"+_OnlyFarmerCode);
                        get3finfo(_OnlyFarmerCode);
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

//                resetSpinnerOwnVechile();
//                showDialog(FarmerTransportServiceActivity.this,"Please Select ");
            }
        });

        _spinnerhireTractors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _multispinnertypeofservice.setItems(typeOfServiceArray);
               // _multispinnertypeofservice.set
               if (i == 1) {

                   typeofserivelyt.setVisibility(View.VISIBLE);

                }else{

                   typeofserivelyt.setVisibility(View.GONE);

               }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

//                resetSpinnerOwnVechile();
//                showDialog(FarmerTransportServiceActivity.this,"Please Select ");
            }
        });

        btn_addmorevehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("============>", "Clicked");

                haveownvehicle();
                selectvehicleType();
                selecteddriverforTransportation();
                driverpaymentperduration();
            }


        });



        if(isOnline()){
        selecttypeofLabour();
        selectlabourpaymentper();
        selectlabourPaymentMode();
        intrestedintakingcompanytransport();
        typeofservice();}
        else{
            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.Internet));
        }
        _spinnertypeofLabour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    contractLabour.setVisibility(View.VISIBLE);
                } else {
                    contractLabour.setVisibility(View.GONE);
                    et_labourchargespaymentamount.setText("");
                    _spinnerlabourchargespaymentduation.setSelection(0);
                    _spinnerlabourchargespaymentmode.setSelection(0);
                    et_othertypelabourchargepaymentmode.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        _spinnerlabourchargespaymentmode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 3) {
                    et_othertypelabourchargepaymentmode.setVisibility(View.VISIBLE);
                } else {
                    et_othertypelabourchargepaymentmode.setVisibility(View.GONE);
                    et_othertypelabourchargepaymentmode.setText("");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        qrscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(FarmerTransportServiceActivity.this,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(FarmerTransportServiceActivity.this, QRScanner_Activity.class);
                    startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                    //  startActivity(new Intent(Transport_service_questioner_vendor.this, QRScanner_Activity.class));

                } else {
                    ActivityCompat.requestPermissions((FarmerTransportServiceActivity) FarmerTransportServiceActivity.this,
                            new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }

        });

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
                Intent intent = new Intent(FarmerTransportServiceActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_Services = _multispinnertypeofservice.getSelectedItems();
                if(_farmercode == null ||  TextUtils.isEmpty(_farmercode)) {
                    showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.pleaseselectfarmer));

                }
               else  if(_spinnerOwnVechile == null ||_spinnerOwnVechile.getSelectedItemPosition() == 0) {
                    showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validateownvehicle));

                }else if(_spinnerOwnVechile.getSelectedItemPosition() == 1)
                {
                    if(ownVechileValidation()){

                        AddFarmerTransportation(addfarmerTransportationRequest());
                    }
                }else if(_spinnerOwnVechile.getSelectedItemPosition() == 2)
                {
                    if(NoOwnVechileValidation()){

                        AddFarmerTransportation(addfarmerTransportationRequestforNoOwnVehicle());
                    }
                }


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
               String QRstring = data.getStringExtra("keyName");
                Log.e("===========> QRstring ",QRstring);

                _farmerAutoComplete.setText(QRstring);
            }
        }}

    private boolean NoOwnVechileValidation() {


        boolean isValid = true;


        if (validateList()) {
            _labourAmount = TextUtils.isEmpty( et_labourchargespaymentamount.getText().toString()) == true ? 0 : Double.parseDouble(et_labourchargespaymentamount.getText().toString());
            if( _spinnerOwnVechile.getSelectedItemPosition() != 0 && _spinnerOwnVechile.getSelectedItemPosition() ==1) {
                isOwnVechile = true;
            }
            if( _spinnerhireTractors.getSelectedItemPosition() ==1) {
                ishireTractor = true;
            }
            if( _spinnerknowaboutAkshaya.getSelectedItemPosition() ==1) {
                isknowAkshaya = true;
            }
            if(_spinnervehicleonRent != null && _spinnervehicleonRent.getSelectedItemPosition() ==1) {
                isvehicleonrent = true;
            }
            if(_spinnerwillingtoRent != null && _spinnerwillingtoRent.getSelectedItemPosition() ==1) {
                iswillingtorent = true;
            }

            _hiringProblems = et_problem.getText().toString();
            _suggestions = et_suggestions.getText().toString();
            _needothertypeofservice = et_needotherservices.getText().toString();

            labourTypeItem = (TypeItem) _spinnertypeofLabour.getSelectedItem();
            paymentDurationTypeItem = (TypeItem) _spinnerlabourchargespaymentduation.getSelectedItem();
            paymentModeTypeItem = (TypeItem) _spinnerlabourchargespaymentmode.getSelectedItem();
            companyTransportTypeItem = (TypeItem) _spinnerinterestedinCompanyTransport.getSelectedItem();

            _otherLabourPaymentMode = et_othertypelabourchargepaymentmode.getText().toString();

            selectedServices =  _multispinnertypeofservice.getSelectedItems();


            if(_spinnertypeofLabour.getSelectedItemPosition() == 0)
           {
               showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectlabourtype));
               isValid = false;
               return  isValid;
           }else if(_spinnertypeofLabour.getSelectedItemPosition() == 2 && (_labourAmount == 0 || _spinnerlabourchargespaymentduation.getSelectedItemPosition() == 0)) {
               showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.pleaseenteramountdetails));
                isValid = false;
                return  isValid;
           }else if(_spinnertypeofLabour.getSelectedItemPosition() == 2 && _spinnerlabourchargespaymentmode.getSelectedItemPosition() == 0) {
               showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validatepaymentmode));
                isValid = false;
                return  isValid;
           }else if(_spinnertypeofLabour.getSelectedItemPosition() == 2 && paymentModeTypeItem.getName().equalsIgnoreCase("Others") && TextUtils.isEmpty(_otherLabourPaymentMode))
           {
               showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validateotherpaymentmode));
               isValid = false;
               return  isValid;
           }
           else if(_spinnerhireTractors.getSelectedItemPosition() == 0)
           {
               showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectdoyouhiretractors));
               isValid = false;
               return  isValid;
           }
           else if(_spinnerhireTractors.getSelectedItemPosition() == 1 && selectedServices.size() == 0)
           {
               showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validatetypeofservices));
               isValid = false;
               return  isValid;
           }
           else if (Validateother() == false)
            {
                showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validatespecify));
                isValid = false;
                return  isValid;
            }
           else if(_spinnerinterestedinCompanyTransport.getSelectedItemPosition() == 0)
           {
               showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectinterestedintakingcompanytransport));
               isValid = false;
               return  isValid;
           }
           else if(_spinnerknowaboutAkshaya.getSelectedItemPosition() == 0)
           {
               showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectaboutakshaya));
               isValid = false;
               return  isValid;
           }
        }else{
            isValid = false;

        }


        return  isValid;
    }

    private boolean Validateother() {
        boolean isvalid = true;
        for(int i =0; i <selected_Services.size() ; i ++)
        {
            if((selected_Services.get(i).getId()== 86)  && (TextUtils.isEmpty(et_otherTypeofservice.getText().toString())))
        {
        //    showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validatespecify));
            isvalid = false;


        }

        }

        return  isvalid;
    }

    public  boolean validateList()
    {   boolean isValid = true;
        for (VillageWithData  maindata:finaldatalistfromActivity) {

            if (null != maindata) {
                for (int i = 0; i < maindata.getAllListinfo().size(); i++) {
                    Log.d("FFBTypeIdfinal", maindata.getAllListinfo().get(i).getTransportFFBTypeId() + "");
                    if (maindata.getAllListinfo().get(i).getTransportFFBTypeId() == 0) {
                        showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selecthowFarmertransportFFBtoFactory));
                        isValid = false;
                        return  isValid;

                    }

                        else if (maindata.getAllListinfo().get(i).getHirebasisId() == 0) {
                        showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validatehiringbasis));
                        isValid = false;

                        return  isValid;

                    }

                    if ( maindata.getAllListinfo().get(i).getPeakdata() != null  && maindata.getAllListinfo().get(i).getPeakdata().size() > 0) {
                        for (int j = 0; j < maindata.getAllListinfo().get(i).getPeakdata().size(); j++) {
                            if (maindata.getAllListinfo().get(i).getPeakdata().get(j).getDestinationId() == 0) {
                                showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectdestination1));
                                isValid = false;
                                return  isValid;
                            } else if (Validatevehiclepeakprices() == false)
                            {
                                showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.enterprice));
                                isValid = false;
                                return  isValid;

                            }
//                            else if  (maindata.getAllListinfo().get(i).getPeakdata().get(j).getDestinationId2() != 0) {
//
//                                if (Validatevehiclepeakdestination() == false){
//
//                                    showDialog(FarmerTransportServiceActivity.this, "Please Enter Price2");
//                                    isValid = false;
//                                    return  isValid;
//
//                                }
//
//                            }
//
                        }
                    }else{
                        showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.peakseasondata));
                        isValid = false;
                        return  isValid;
                    }


//
                    if (maindata.getAllListinfo().get(i).getLeanData() != null  && maindata.getAllListinfo().get(i).getLeanData().size() >0) {
                        for (int j = 0; j < maindata.getAllListinfo().get(i).getLeanData().size(); j++) {
                            if (maindata.getAllListinfo().get(i).getLeanData().get(j).getDestinationId() == 0) {
                                showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectdestination1));
                                isValid = false;
                                return  isValid;
                            } else if (Validatevehicleleanprices() == false)
                            {
                                showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.enterprice));
                                isValid = false;
                                return  isValid;
                            }
//                            else if  (maindata.getAllListinfo().get(i).getLeanData().get(j).getDestinationId2() != 0) {
//
//                                if (Validatevehicleleandestination() == false){
//
//                                    showDialog(FarmerTransportServiceActivity.this, "Please Enter Price2");
//                                    isValid = false;
//                                    return  isValid;
//
//                                }
//
//                            }
//                             if  (maindata.getAllListinfo().get(i).getLeanData().get(j).getDestinationId2() == 0) {
//                                return  true;
//                            }else if  (maindata.getAllListinfo().get(i).getLeanData().get(j).getDestinationId2() != 0) {
//                                showDialog(FarmerTransportServiceActivity.this, "Please Enter Price2");
//                                return  false;
//                            }
                        }
                    }else{
                        showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.leanseasondata));
                        isValid = false;
                        return  isValid;
                    }

                    if(TextUtils.isEmpty(maindata.getAllListinfo().get(i).getOwnerName())){

                        showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.entervehicleownername));
                        isValid = false;
                        return  isValid;
                    }else if (TextUtils.isEmpty(maindata.getAllListinfo().get(i).getOwneraddress())){

                        showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.entervehicleowneraddress));
                        isValid = false;
                        return  isValid;
                    }
                    else if (TextUtils.isEmpty(maindata.getAllListinfo().get(i).getOwnerMobileNumber())){

                        showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.entervehicleownermobilenumber));
                        isValid = false;
                        return  isValid;
                    }


                }
            }else{
                Toast.makeText(this, "Data is Null", Toast.LENGTH_SHORT).show();
            }
        }
       return isValid;
    }

    private void nobind() {



    }

    public  boolean Validatevehiclepeakprices()
    {
        boolean isvalid = false;
        for(int i =0; i <finaldatalistfromActivity.size() ; i ++)
        {
            if (null != finaldatalistfromActivity.get(i).getAllListinfo() && finaldatalistfromActivity.get(i).getAllListinfo().size() > 0) {
                for (int k=0; k<finaldatalistfromActivity.get(i).getAllListinfo().size(); k++ ){
                    if ( null != finaldatalistfromActivity.get(i).getAllListinfo().get(k).getPeakdata()  && finaldatalistfromActivity.get(i).getAllListinfo().get(k).getPeakdata().size() >0) {
                        for ( int j=0; j<finaldatalistfromActivity.get(i).getAllListinfo().get(k).getPeakdata().size(); j++){

                            if(finaldatalistfromActivity.get(i).getAllListinfo().get(k).getPeakdata().get(j).getPrice() > 0)
                            {
                                isvalid= true;
                            }
                        }
                    }
                }
            }

        }

        return  isvalid;

    }

    public  boolean Validatevehicleleandestination()
    {
        boolean isvalid = false;
        for(int i =0; i <finaldatalistfromActivity.size() ; i ++)
        {
            for (int k=0; k<finaldatalistfromActivity.get(i).getAllListinfo().size(); k++ ){
                for (int j=0; j<finaldatalistfromActivity.get(i).getAllListinfo().get(k).getLeanData().size(); j++){

                    if(finaldatalistfromActivity.get(i).getAllListinfo().get(k).getLeanData().get(j).getPrice2() > 0)
                    {
                        isvalid= true;
                    }
                }
            }

        }

        return  isvalid;

    }

    public  boolean Validatevehiclepeakdestination()
    {
        boolean isvalid = false;
        for(int i =0; i <finaldatalistfromActivity.size() ; i ++)
        {
            for (int k=0; k<finaldatalistfromActivity.get(i).getAllListinfo().size(); k++ ){
                for (int j=0; j<finaldatalistfromActivity.get(i).getAllListinfo().get(k).getPeakdata().size(); j++){

                    if(finaldatalistfromActivity.get(i).getAllListinfo().get(k).getPeakdata().get(j).getPrice2() > 0)
                    {
                        isvalid= true;
                    }
                }
            }

        }

        return  isvalid;

    }


    public  boolean Validatevehicleleanprices()
    {
        boolean isvalid = false;
        for(int i =0; i <finaldatalistfromActivity.size() ; i ++)
        {
            if (null != finaldatalistfromActivity.get(i).getAllListinfo() && finaldatalistfromActivity.get(i).getAllListinfo().size() > 0) {
                for (int k=0; k<finaldatalistfromActivity.get(i).getAllListinfo().size(); k++ ){
                    if (null != finaldatalistfromActivity.get(i).getAllListinfo().get(k).getLeanData() && finaldatalistfromActivity.get(i).getAllListinfo().get(k).getLeanData().size() >0) {
                        for (int j=0; j<finaldatalistfromActivity.get(i).getAllListinfo().get(k).getLeanData().size(); j++){

                            if(finaldatalistfromActivity.get(i).getAllListinfo().get(k).getLeanData().get(j).getPrice() > 0)
                            {
                                isvalid= true;
                            }
                        }
                    }
                }
            }

        }

        return  isvalid;

    }


    private void get3finfo(final String farmerCode) {
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getcc(APIConstantURL.Get3FInfo+farmerCode+"/"+"AP")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Getdestinations>() {
                    @Override
                    public void onCompleted() {
                        //   mdilogue.dismiss();
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
                        //  mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(Getdestinations getdestinations) {

                        // TransportationService(FarmerCode,getdestinations);
                        if (getdestinations.getResult() != null) {

                            getdestination = getdestinations;
                            if(isOnline()) {
                                TransportationService(_OnlyFarmerCode, getdestinations);
                            }else{
                                showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.Internet));
                            }
                            Log.e("=====>destination", getdestinations + "");
                        }
                    }
                });}


    private void TransportationService(String farmecode, final Getdestinations getdestinations) {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getvillagesbyfarmercode(APIConstantURL.TransportationService+farmecode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetPlotVillagesByFarmerCode>() {
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
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetPlotVillagesByFarmerCode getPlotVillagesByFarmerCode) {

                        if (getPlotVillagesByFarmerCode != null && getPlotVillagesByFarmerCode.getListResult().size() > 0) {

                            if (isOnline()) {

                                selectvehicleTypeforNoOwnVehicle(getPlotVillagesByFarmerCode, getdestinations);
                            }else
                            {
                                showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.Internet));
                            }


                        }else
                        {
                            Toast.makeText(FarmerTransportServiceActivity.this, getResources().getString(R.string.novillagesfound), Toast.LENGTH_SHORT).show();
                        }
                    }


                });}
//
//    private void fetchVehicleData() {
//
//        Vehicle a = new Vehicle(vehicletypeid, selectedvehicleType, selecteddriverfortransportation, driverforTransportationid, driverpaymentamount, selecteddriverpaymentamountper, driverpaymentperduationId, selectedvehiclecurrentlyonRent, selectedwillingtorent,othervehicletype.getText().toString());
//        ownvehicles_arrayList.add(a);
//
//    }

    private void haveownvehicle() {

        haveownvehiclelyt.setVisibility(View.VISIBLE);
        donthaveownvehiclelyt.setVisibility(View.GONE);
    }

    private void donthaveownvehicle() {

        haveownvehiclelyt.setVisibility(View.GONE);
        donthaveownvehiclelyt.setVisibility(View.VISIBLE);
    }

    private void resetSpinnerOwnVechile() {
        _spinnerOwnVechile.setSelection(0);
        _spinnerOwnVechile.setEnabled(true);
//        _spinnerOwnVechile.setAdapter(_farmerDataAutoCompleteAdapter);
//        _farmerDataAutoCompleteAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
    }


    private void populateFarmerData(String s) {
        _farmerDataArrayList = new ArrayList<>();
        mdilogue.show();
        JsonObject object = populateFarmerDataRequest(s);
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.farmersearch(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Farmersearchresponse>() {

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
                        showDialog(FarmerTransportServiceActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(Farmersearchresponse farmersearchresponse) {
                        if (farmersearchresponse != null && farmersearchresponse.getAffectedRecords() != 0) {

                            for (Farmersearchresponse.ListResult item : farmersearchresponse.getListResult()) {
                                _farmerDataArrayList.add(new farmer(item.getVillageId(), item.getFarmerCode(), item.getContactNumber(), item.getFarmerName(), item.getDisplayName()));
                            }

                            _farmerDataAutoCompleteAdapter = new AutoCompleteUserAdapter(FarmerTransportServiceActivity.this, R.layout.farmerselection, _farmerDataArrayList);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    _farmerAutoComplete.setAdapter(_farmerDataAutoCompleteAdapter);
                                    _farmerDataAutoCompleteAdapter.notifyDataSetChanged();
                                    _farmerAutoComplete.showDropDown();
                                }
                            });

                        } else {
                            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.nofarmer));
                            resetSpinnerOwnVechile();
                            _spinnerOwnVechile.setEnabled(false);
                            _farmercode = null;
                        }
                    }
                });
    }

    private JsonObject populateFarmerDataRequest(String s) {
        Farmersearchobject requestModel = new Farmersearchobject();
        requestModel.setSearchKey(s);
        requestModel.setId(null);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private void selecttypeofLabour() {

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getLabourTypes(APIConstantURL.labourType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetLabourTypes>() {
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
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetLabourTypes getLabourTypes) {

                        if (getLabourTypes.getListResult() != null) {
                            _labourTypeArray = new ArrayList<>();
                            _labourTypeArray.add(new TypeItem(0, "Please Select"));
                            for (GetLabourTypes.ListResult data : getLabourTypes.getListResult()
                            ) {
                                _labourTypeArray.add(new TypeItem(data.getTypeCdId(), data.getDesc()));
                            }
                            _labourTypeAdapter = new SpinnerTypeArrayAdapter(FarmerTransportServiceActivity.this, _labourTypeArray);
                            _labourTypeAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            _spinnertypeofLabour.setAdapter(_labourTypeAdapter);

                        }

                    }

                });

    }

    private void selectlabourpaymentper() {

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getDurationTypes(APIConstantURL.durationType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetDurationTypes>() {
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
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetDurationTypes getDurationTypes) {

                        if (getDurationTypes.getListResult() != null) {
                            _labourDurationTypeArr = new ArrayList<>();
                            _labourDurationTypeArr.add(new TypeItem(0, "Please Select"));
                            for (GetDurationTypes.ListResult data : getDurationTypes.getListResult()
                            ) {
                                _labourDurationTypeArr.add(new TypeItem(data.getTypeCdId(), data.getDesc()));
                            }
                            _labourpaymentDuationAdapter = new SpinnerTypeArrayAdapter(FarmerTransportServiceActivity.this, _labourDurationTypeArr);
                            _labourpaymentDuationAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            _spinnerlabourchargespaymentduation.setAdapter(_labourpaymentDuationAdapter);




                        }


                    }

                });
    }

    private void selectlabourPaymentMode() {
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getpaymentModeTypes(APIConstantURL.paymentMode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetPaymentModeTypes>() {
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
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetPaymentModeTypes getPaymentModeTypes) {

                        if (getPaymentModeTypes.getListResult() != null) {
                            _labourpaymentTypeArr = new ArrayList<>();
                            _labourpaymentTypeArr.add(new TypeItem(0, "Please Select"));
                            for (GetPaymentModeTypes.ListResult data : getPaymentModeTypes.getListResult()
                            ) {
                                _labourpaymentTypeArr.add(new TypeItem(data.getTypeCdId(), data.getDesc()));
                            }
                            _labourpaymentTypeAdapter = new SpinnerTypeArrayAdapter(FarmerTransportServiceActivity.this, _labourpaymentTypeArr);
                            _labourpaymentTypeAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            _spinnerlabourchargespaymentmode.setAdapter(_labourpaymentTypeAdapter);

                        }


                    }

                });
    }

    private void intrestedintakingcompanytransport() {
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getcompanyTransport(APIConstantURL.companyTrnasport)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetCompanyTransport>() {
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
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetCompanyTransport getCompanyTransport) {

                        if (getCompanyTransport.getListResult() != null) {
                            _companyTransportTypeArr = new ArrayList<>();
                            _companyTransportTypeArr.add(new TypeItem(0, "Please Select"));
                            for (GetCompanyTransport.ListResult data : getCompanyTransport.getListResult()
                            ) {
                                _companyTransportTypeArr.add(new TypeItem(data.getTypeCdId(), data.getDesc()));
                            }
                            _companyTransportAdapter = new SpinnerTypeArrayAdapter(FarmerTransportServiceActivity.this, _companyTransportTypeArr);
                            _companyTransportAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            _spinnerinterestedinCompanyTransport.setAdapter(_companyTransportAdapter);


                        }


                    }

                });
    }



    private void typeofservice() {

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getservivceeeTypes(APIConstantURL.typeofServicesfarmer)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTypeofServices>() {
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
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetTypeofServices getTypeofServices) {
                        typeOfServiceArray = new ArrayList<>();

                        if (getTypeofServices.getListResult() != null) {

                            for (GetTypeofServices.ListResult data : getTypeofServices.getListResult()
                            ) {
                                typeOfServiceArray.add(new Item(data.getTypeCdId(),data.getDesc(),false));
                            }

                            _multispinnertypeofservice.setItems(typeOfServiceArray);


                        } else {

                        }


                    }

                });

    }

    private void selectvehicleType() {

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getvehicleTypes(APIConstantURL.vehicleType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<VehicleTypeResponse>() {
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
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(VehicleTypeResponse vehicleTypeResponse) {
                        _vechileTypeArray = new ArrayList<>();
                        _vechileTypeArray.add(new TypeItem(0, "Please Select"));
                        if (vehicleTypeResponse.getListResult() != null) {

                            for (VehicleTypeResponse.ListResult data : vehicleTypeResponse.getListResult()
                            ) {
                                _vechileTypeArray.add(new TypeItem(data.getTypeCdId(), data.getDesc()));


                            }

                            selecteddriverforTransportation();

//                            showHaveOwnVehicleDialog(FarmerTransportServiceActivity.this,_vechileTypeArray, );
                        } else {
//                            get_vehicleType.add("No Vehicle Type Available");

                        }


                    }

                });

    }

    private void selectvehicleTypeforNoOwnVehicle( final GetPlotVillagesByFarmerCode getPlotVillagesByFarmerCode, final Getdestinations getdestinations) {

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getvehicleTypes(APIConstantURL.vehicleType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<VehicleTypeResponse>() {
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
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(VehicleTypeResponse vehicleTypeResponse) {
                        vehicletypelist = new ArrayList<>();
                        if (vehicleTypeResponse.getListResult() != null) {

//                            for (VehicleTypeResponse.ListResult data : vehicleTypeResponse.getListResult()
//                            ) {
//                                _vechileTypeArray.add(new TypeItem(data.getTypeCdId(), data.getDesc()));
                                for (int i = 0; i < vehicleTypeResponse.getListResult().size(); i++) {
                                vehicletypelist.add(new Vehicletype(i,  vehicleTypeResponse.getListResult().get(i).getDesc(),vehicleTypeResponse.getListResult().get(i).getTypeCdId()));}



//                            }
                            donthaveownVehicleRecyclerview = findViewById(R.id.donthavevehiclerecycleview);
                            linearLayoutManager = new LinearLayoutManager(FarmerTransportServiceActivity.this);
                            donthaveownVehicleRecyclerview.setLayoutManager(linearLayoutManager);
                            vehicleHireChargesAdapter = new VehicleHireChargesAdapter(FarmerTransportServiceActivity.this,getPlotVillagesByFarmerCode.getListResult(),getdestinations,vehicletypelist, FarmerTransportServiceActivity.this);
                            donthaveownVehicleRecyclerview.setAdapter(vehicleHireChargesAdapter);

                        } else {
//                            get_vehicleType.add("No Vehicle Type Available");

                        }


                    }

                });

    }


    private void selecteddriverforTransportation() {

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getdriverforTransportationTypes(APIConstantURL.driverforTransportationType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetDriverforTransportationTypes>() {
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
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetDriverforTransportationTypes driverforTransportationTypes) {

                        if (driverforTransportationTypes.getListResult() != null) {

                            _driverTypeArray = new ArrayList<>();
                            _driverTypeArray.add(new TypeItem(0, "Please Select"));
                            for (GetDriverforTransportationTypes.ListResult data : driverforTransportationTypes.getListResult()
                            ) {
                                {
                                    _driverTypeArray.add(new TypeItem(data.getTypeCdId(), data.getDesc()));
                                }
                            }

                            driverpaymentperduration();

                        }


                    }

                });

    }

    private void driverpaymentperduration() {

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getDurationTypes(APIConstantURL.durationType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetDurationTypes>() {
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
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetDurationTypes getDurationTypes) {

                        if (getDurationTypes.getListResult() != null) {
                            _driverpaymanetdurationTypeArr = new ArrayList<>();
                            _driverpaymanetdurationTypeArr.add(new TypeItem(0, "Please Select"));

                            for (GetDurationTypes.ListResult data : getDurationTypes.getListResult()
                            ) {
                                _driverpaymanetdurationTypeArr.add(new TypeItem(data.getTypeCdId(), data.getDesc()));

                            }
                            if(_dialog == null ||  !_dialog.isShowing())
                            {
                                showHaveOwnVehicleDialog(FarmerTransportServiceActivity.this, _vechileTypeArray, _driverTypeArray, _driverpaymanetdurationTypeArr);
                            }
                        }
                    }

                });
    }


    public void showDialog(Activity activity, String msg) {
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

    public void showHaveOwnVehicleDialog(Activity activity, ArrayList<TypeItem> _vechileTypeArray, ArrayList<TypeItem> _driverTypeArray, ArrayList<TypeItem> _driverpaymanetdurationTypeArr) {
//        _dialog = new Dialog(activity, R.style.Common_textView_title);
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        if (_dialog != null && _dialog.isShowing()) {
            _dialog.cancel();
        }
        _dialog = new Dialog(activity);
        _dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        _dialog.setCancelable(false);
        _dialog.setContentView(R.layout.haveownvehicle);


        final SpinnerTypeArrayAdapter _vechileTypeAdapter, _driverTypeAdapter, _driverpaymentTypeAdapter;


        Button addvehicleBtn, cancelBtn;
        final LinearLayout hiredriverlyt = _dialog.findViewById(R.id.hiredriverlyt);
        hiredriverlyt.setVisibility(View.GONE);

        final EditText driverpaymentamount_et = _dialog.findViewById(R.id.driverpaymentamount_et);
        et_othertypevehicle = _dialog.findViewById(R.id.et_othertypevehicle);
        et_othertypevehicle.setVisibility(View.GONE);

        addvehicleBtn = _dialog.findViewById(R.id.addvehicleBtn);
        cancelBtn = _dialog.findViewById(R.id.cancelBtn);

        _spinnervehicleonRent = _dialog.findViewById(R.id.isvehicleonRent);
        _spinnerwillingtoRent = _dialog.findViewById(R.id.vwillingtoRent);

        _spinnervehicleType = _dialog.findViewById(R.id.typeofvehicle);
        _spinnerDriverforTransportation = _dialog.findViewById(R.id.driverforTransportation);
        _spinnerDriverpaymentperduation = _dialog.findViewById(R.id.driverpaymentper);

        _vechileTypeAdapter = new SpinnerTypeArrayAdapter(activity, _vechileTypeArray);
        _vechileTypeAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        _spinnervehicleType.setAdapter(_vechileTypeAdapter);

        _driverTypeAdapter = new SpinnerTypeArrayAdapter(activity, _driverTypeArray);
        _driverTypeAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        _spinnerDriverforTransportation.setAdapter(_driverTypeAdapter);

        _driverpaymentTypeAdapter = new SpinnerTypeArrayAdapter(activity, _driverpaymanetdurationTypeArr);
        _driverpaymentTypeAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        _spinnerDriverpaymentperduation.setAdapter(_driverpaymentTypeAdapter);
        if (_dialog != null && !_dialog.isShowing()) {
            _dialog.show();
        }

        _spinnervehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TypeItem vechiletype = (TypeItem) _spinnervehicleType.getSelectedItem();
                if (vechiletype.getName().equals("Others")) {

                    et_othertypevehicle.setVisibility(View.VISIBLE);
                } else {
                    et_othertypevehicle.setVisibility(View.GONE);
                    et_othertypevehicle.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        _spinnerDriverforTransportation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    hiredriverlyt.setVisibility(View.VISIBLE);

                } else {
                    hiredriverlyt.setVisibility(View.GONE);
                    driverpaymentamount_et.setText("");
                    _spinnerDriverpaymentperduation.setSelection(0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        _spinnerDriverpaymentperduation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                selecteddriverpaymentamountper = _spinnerDriverpaymentperduation.getItemAtPosition(_spinnerDriverpaymentperduation.getSelectedItemPosition()).toString();
//                if (i != 0) {
//
//                    driverpaymentperduationId = get_driverdurationType_Id.get(_spinnerDriverpaymentperduation.getSelectedItemPosition() - 1);
//                }
//                _spinnerDriverpaymentperduation.setPrompt(selecteddriverpaymentamountper);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        addvehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TypeItem vechiletype = (TypeItem) _spinnervehicleType.getSelectedItem();
                TypeItem driverType = (TypeItem) _spinnerDriverforTransportation.getSelectedItem();
                TypeItem driverpaymentdurationType = (TypeItem) _spinnerDriverpaymentperduation.getSelectedItem();
                String vehicleonRentType = (String) _spinnervehicleonRent.getSelectedItem();
                String willingtoRentType = (String) _spinnerwillingtoRent.getSelectedItem();
                Log.d(TAG, "onClickVehicleType: " + vechiletype.getName());
                Log.d(TAG, "onClickdriverType: " + driverType.getName());
                Log.d(TAG, "onClickdriverpaymentdurationType: " + driverpaymentdurationType.getName());
                Log.d(TAG, "onClickvehicleonRentType: " + vehicleonRentType);
                Log.d(TAG, "onClickwillingtoRentType: " + willingtoRentType);

                if (vechiletype == null || _spinnervehicleType.getSelectedItemPosition() == 0) {
                    showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validatevehicletype));
                } else if (vechiletype.getName().equals("Others") && TextUtils.isEmpty(et_othertypevehicle.getText().toString())) {
                    showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.specifyvehicleType));
                } else if (driverType == null || _spinnerDriverforTransportation.getSelectedItemPosition() == 0) {
                    showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.pleaseselectdriverfortransportation));
                } else if (_spinnerDriverforTransportation.getSelectedItemPosition() == 2 && TextUtils.isEmpty(driverpaymentamount_et.getText().toString())) {
                    showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.enterdriverpayment));
                } else if (_spinnerDriverforTransportation.getSelectedItemPosition() == 2 && (driverpaymentdurationType == null || driverpaymentdurationType.getId() == 0)) {
                    showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectamountperdriverpayment));
                } else if (_spinnervehicleonRent.getSelectedItemPosition() == 0) {
                    showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectvehiclecurrentlyonrent));
                } else if (_spinnerwillingtoRent.getSelectedItemPosition() == 0) {
                    showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectwillingtorent));
                } else {
                    Vehicle a = new Vehicle(
                            vechiletype.getId(),
                            vechiletype.getName(),
                            driverType.getName(),
                            driverType.getId(),
                            TextUtils.isEmpty(driverpaymentamount_et.getText().toString()) == true ? 0 : Double.parseDouble(driverpaymentamount_et.getText().toString()),
                            driverpaymentdurationType.getName(),
                            driverpaymentdurationType.getId(),
                            vehicleonRentType,
                            willingtoRentType,et_othertypevehicle.getText().toString());

                    ownvehicles_arrayList.add(a);
                    adapter = new MoreVehiclesAdapter(FarmerTransportServiceActivity.this, ownvehicles_arrayList, FarmerTransportServiceActivity.this);
                    addvehicleData.setAdapter(adapter);
                    _dialog.dismiss();
                }

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "===Cancel Clicked===");
                if (_dialog != null && _dialog.isShowing()) {
                    _dialog.dismiss();
                }
            }
        });


    }

    @Override
    public void onItemDelete(int po) {
        ownvehicles_arrayList.remove(po);
        adapter = new MoreVehiclesAdapter(FarmerTransportServiceActivity.this, ownvehicles_arrayList, FarmerTransportServiceActivity.this);
        addvehicleData.setAdapter(adapter);
    }
    public boolean ownVechileValidation()
    {
        _labourAmount = TextUtils.isEmpty( et_labourchargespaymentamount.getText().toString()) == true ? 0 : Double.parseDouble(et_labourchargespaymentamount.getText().toString());
        if( _spinnerOwnVechile.getSelectedItemPosition() != 0 && _spinnerOwnVechile.getSelectedItemPosition() ==1) {
            isOwnVechile = true;
        }
        if( _spinnerhireTractors.getSelectedItemPosition() ==1) {
            ishireTractor = true;
        }
        if( _spinnerknowaboutAkshaya.getSelectedItemPosition() ==1) {
            isknowAkshaya = true;
        }
        if(_spinnervehicleonRent != null && _spinnervehicleonRent.getSelectedItemPosition() ==1) {
            isvehicleonrent = true;
        }
        if(_spinnerwillingtoRent != null && _spinnerwillingtoRent.getSelectedItemPosition() ==1) {
            iswillingtorent = true;
        }

        _hiringProblems = et_problem.getText().toString();
        _suggestions = et_suggestions.getText().toString();
        _needothertypeofservice = et_needotherservices.getText().toString();

        labourTypeItem = (TypeItem) _spinnertypeofLabour.getSelectedItem();
        paymentDurationTypeItem = (TypeItem) _spinnerlabourchargespaymentduation.getSelectedItem();
        paymentModeTypeItem = (TypeItem) _spinnerlabourchargespaymentmode.getSelectedItem();
        companyTransportTypeItem = (TypeItem) _spinnerinterestedinCompanyTransport.getSelectedItem();

        _otherLabourPaymentMode = et_othertypelabourchargepaymentmode.getText().toString();

        selectedServices =  _multispinnertypeofservice.getSelectedItems();

//       if(_spinnerDriverforTransportation != null && _spinnerDriverpaymentperduation != null)
//       {
//           driverforTransportationTypeItem = (TypeItem) _spinnerDriverforTransportation.getSelectedItem();
//           driverpaymentdurationTypeItem = (TypeItem) _spinnerDriverpaymentperduation.getSelectedItem();
//           _driveramount =TextUtils.isEmpty(et_driverpaymentamount.getText())== true ? 0: Double.parseDouble(et_driverpaymentamount.getText().toString());
//       }







        Log.d(TAG,"===> Analysis ==  ownVechileValidation() =========");
        Log.d(TAG,"===> Farmer Code :"+ _farmercode);
//        Log.d(TAG,"===> labourAmount :"+ _labourAmount);
//        Log.d(TAG,"===> farmerName    :"+ _farmerName);
//        Log.d(TAG,"===> ownvehicle    :"+ isOwnVechile);
//        Log.d(TAG,"===> HiringProblems :"+ _hiringProblems);
//        Log.d(TAG,"===> LabourTypeId :"+ labourTypeItem.getId());
//        Log.d(TAG,"===> LabourTypeName :"+ labourTypeItem.getName());
//        Log.d(TAG,"===> paymentDuration Id :"+ paymentDurationTypeItem.getId());
//        Log.d(TAG,"===> paymentDurationName :"+ paymentDurationTypeItem.getName());
        Log.d(TAG,"===> paymentMode Id :"+ paymentModeTypeItem.getId());
        Log.d(TAG,"===> paymentMode Name :"+ paymentModeTypeItem.getName());
//        Log.d(TAG,"===> otherLabourPaymentMode :"+ _otherLabourPaymentMode);
//        Log.d(TAG,"===> companyTransport Id :"+ companyTransportTypeItem.getId());
//        Log.d(TAG,"===> companyTransport Name :"+ companyTransportTypeItem.getName());
//        Log.d(TAG,"===> suggestions :"+ _suggestions);
//        Log.d(TAG,"===> ishireTractor :"+ ishireTractor);
//        Log.d(TAG,"===> isknowAkshaya :"+ isknowAkshaya);
//        Log.d(TAG,"===> selectedServices size :"+ selectedServices.size());
//        Log.d(TAG,"===> ownvehicles_arrayList size :"+ ownvehicles_arrayList.size());  // must atlest one
////        Log.d(TAG,"===> driverforTransportationTypeItem Id :"+ driverforTransportationTypeItem == null ? "" : driverforTransportationTypeItem.getId());  // must atlest one
//        Log.d(TAG,"===> driverforTransportationTypeItem name :"+ driverforTransportationTypeItem.getId());
//        Log.d(TAG,"===> _driveramount :"+_driveramount);
//        Log.d(TAG,"===> driverpaymentdurationTypeItemId :"+driverpaymentdurationTypeItem.getId());
//        Log.d(TAG,"===> driverpaymentdurationTypeItemName :"+driverpaymentdurationTypeItem.getName());
//        Log.d(TAG,"===> isvehicleonrent :"+isvehicleonrent);
//        Log.d(TAG,"===> iswillingtorent :"+iswillingtorent);

        if(_spinnerOwnVechile.getSelectedItemPosition() ==1 && ownvehicles_arrayList.size() == 0)
        {
            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.addatleastonevehicle));
            return false;
        }else if(_spinnertypeofLabour.getSelectedItemPosition() == 0)
        {
            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectlabourtype));
            return false;
        }else if(_spinnertypeofLabour.getSelectedItemPosition() == 2 && ( _labourAmount == 0 || _spinnerlabourchargespaymentduation.getSelectedItemPosition() == 0)) {
            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.pleaseenteramountdetails));
            return false;
        }else if(_spinnertypeofLabour.getSelectedItemPosition() == 2 && _spinnerlabourchargespaymentmode.getSelectedItemPosition() == 0) {
            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validatepaymentmode));
            return false;
        }else if(_spinnertypeofLabour.getSelectedItemPosition() == 2 && paymentModeTypeItem.getName().equalsIgnoreCase("Others") && TextUtils.isEmpty(_otherLabourPaymentMode))
        {
            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validateotherpaymentmode));
            return false;
        }
        else if(_spinnerhireTractors.getSelectedItemPosition() == 0)
        {
            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectdoyouhiretractors));
            return false;
        }
        else if(_spinnerhireTractors.getSelectedItemPosition() == 1 && selectedServices.size() == 0)
        {
            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validatetypeofservices));
            return false;
        } else if (Validateother() == false)
        {
            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.validatespecify));
            return false;

        }
        else if(_spinnerinterestedinCompanyTransport.getSelectedItemPosition() == 0)
        {
            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectinterestedintakingcompanytransport));
            return false;
        }
        else if(_spinnerknowaboutAkshaya.getSelectedItemPosition() == 0)
        {
            showDialog(FarmerTransportServiceActivity.this, getResources().getString(R.string.selectaboutakshaya));
            return false;
        }else {

            return true;


        }




    }


    private void AddFarmerTransportation(JsonObject inputjson) {
        mdilogue.show();
        JsonObject object = inputjson;
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.addFarmerTransportationpage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddFarmerTransportationResponse>() {

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
                       // showDialog(FarmerTransportServiceActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(AddFarmerTransportationResponse farmerTransportationResponse) {

                        if (farmerTransportationResponse.getIsSuccess()){

                        List<MSGmodel> displayList = new ArrayList<>();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showSuccessDialog(displayList, getResources().getString(R.string.farmertransportservice));
                        }

                    }else{
                            Toast.makeText(FarmerTransportServiceActivity.this, farmerTransportationResponse.getEndUserMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });}

    private JsonObject addfarmerTransportationRequest() {

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        AddFarmerTransportationRequest.FarmerTrasportationDetails farmerTrasportationDetails = new AddFarmerTransportationRequest.FarmerTrasportationDetails();



        farmerTrasportationDetails.setId(null);
        farmerTrasportationDetails.setFarmerCode(_OnlyFarmerCode);
        farmerTrasportationDetails.setFarmerName(_farmerName);
        farmerTrasportationDetails.setOwnVehicle(isOwnVechile);
        farmerTrasportationDetails.setHiringProblems(_hiringProblems);
        farmerTrasportationDetails.setLabourTypeId(labourTypeItem.getId());

        if (labourTypeItem.getName().equalsIgnoreCase("Contract Labour")) {

            farmerTrasportationDetails.setLabourCharges(_labourAmount);
            farmerTrasportationDetails.setLabourChargesTypeId(paymentDurationTypeItem.getId());
            farmerTrasportationDetails.setPaymentTypeId(paymentModeTypeItem.getId());

            if (paymentModeTypeItem.getName().equalsIgnoreCase( "Others")) {

                farmerTrasportationDetails.setPaymentTypeDesc(_otherLabourPaymentMode);
            }
        }
        farmerTrasportationDetails.setTransportServiceFrom3F(companyTransportTypeItem.getId());
        farmerTrasportationDetails.setSuggestions(_suggestions);
        farmerTrasportationDetails.setHireTransportOtherThanTransport(ishireTractor);
        farmerTrasportationDetails.setOtherServices(_needothertypeofservice);
        farmerTrasportationDetails.setKnownAkshaya(isknowAkshaya);
        farmerTrasportationDetails.setCreatedByUserId(created_user.getResult().getUserInfos().getId());
        farmerTrasportationDetails.setCreatedDate(currentDate);
        farmerTrasportationDetails.setUpdatedByUserId(created_user.getResult().getUserInfos().getId());
        farmerTrasportationDetails.setUpdatedDate(currentDate);
        farmerTrasportationDetails.setFarmerMobileNumber(_contactnumber);
        farmerTrasportationDetails.setVillageId(_farmer_villageId);

        List<AddFarmerTransportationRequest.FarmerService> farmerServices = new ArrayList<>();

        //  for (int i = 0; i < selected_villagesids.size(); i++) {
        if(ishireTractor) {
            for (int i = 0; i < selectedServices.size(); i++) {


                AddFarmerTransportationRequest.FarmerService farmerServicemodel = new AddFarmerTransportationRequest.FarmerService();
                farmerServicemodel.setId(null);
                farmerServicemodel.setFarmerTransportationServiceId(null);  // Todo Need to discuss
                farmerServicemodel.setServiceTypeId(selectedServices.get(i).getId());
                if (selectedServices.get(i).getId() == 86) {
                    farmerServicemodel.setServiceDesc(et_otherTypeofservice.getText().toString());
                } else {
                    farmerServicemodel.setServiceDesc(null);
                }
                farmerServicemodel.setCreatedByUserId(created_user.getResult().getUserInfos().getId());
                farmerServicemodel.setCreatedDate(currentDate);
                farmerServices.add(farmerServicemodel);
            }
        }

//        List<AddFarmerTransportationRequest.FarmerTransportHireCharge> farmerTransportHireCharges = new ArrayList<>();
        ////
        ////        //  for (int i = 0; i < selected_villagesids.size(); i++) {
        ////
        ////
        ////        AddFarmerTransportationRequest.FarmerTransportHireCharge farmerTransportHireChargemodel = new AddFarmerTransportationRequest.FarmerTransportHireCharge();
        ////        farmerTransportHireChargemodel.setId(1);
        ////        farmerTransportHireChargemodel.setFarmerTransportationServiceId(1);
        ////        farmerTransportHireChargemodel.setSeasonTypeId(1);
        ////        farmerTransportHireChargemodel.setVehicleTypeId(1);
        ////        farmerTransportHireChargemodel.setVillageId(1);
        ////        farmerTransportHireChargemodel.setDestinationId(1);
        ////        farmerTransportHireChargemodel.setPrice(1.1);
        ////        farmerTransportHireChargemodel.setCreatedByUserId(1);
        ////        farmerTransportHireChargemodel.setCreatedDate("");
        ////        farmerTransportHireCharges.add(farmerTransportHireChargemodel);
        //
        //        //}

        List<AddFarmerTransportationRequest.FarmerVehicleDetail> farmerVehicleDetails = new ArrayList<>();
        for(int i =0; i < ownvehicles_arrayList.size(); i ++) {


            AddFarmerTransportationRequest.FarmerVehicleDetail farmerVehicle_Detail = new AddFarmerTransportationRequest.FarmerVehicleDetail();
            farmerVehicle_Detail.setId(null);
            farmerVehicle_Detail.setFarmerTransportationServiceId(null);
            farmerVehicle_Detail.setVehicleTypeId(ownvehicles_arrayList.get(i).getVehicletype_id());

            if(ownvehicles_arrayList.get(i).getVehicletype_id() == 51){

                farmerVehicle_Detail.setVehicleTypeDesc(et_othertypevehicle.getText().toString());
            }

            farmerVehicle_Detail.setDriverTypeId(ownvehicles_arrayList.get(i).getDriver_id());

            if(ownvehicles_arrayList.get(i).getDriver().equalsIgnoreCase("Hire Driver"))
            {
                farmerVehicle_Detail.setDriverPrice(ownvehicles_arrayList.get(i).getAmount());
                farmerVehicle_Detail.setDriverPaymentTypeId(ownvehicles_arrayList.get(i).getPaymenttypeid());
            }else
            {
                farmerVehicle_Detail.setDriverPrice(0.0);
                farmerVehicle_Detail.setDriverPaymentTypeId(null);
            }


            farmerVehicle_Detail.setIsVehicleRented(isvehicleonrent);
            farmerVehicle_Detail.setWillingToRentVehicle(iswillingtorent);
            farmerVehicle_Detail.setCreatedByUserId(created_user.getResult().getUserInfos().getId());
            farmerVehicle_Detail.setCreatedDate(currentDate);
            farmerVehicleDetails.add(farmerVehicle_Detail);
        }

//        for (int i = 0; i < vehicle_arrayList.size(); i++) {
//
//            AddFarmerTransportationRequest.FarmerVehicleDetail farmerVehicleDetailmodel = new AddFarmerTransportationRequest.FarmerVehicleDetail();
//            farmerVehicleDetailmodel.setId(null);
//            farmerVehicleDetailmodel.setFarmerTransportationServiceId(null);
//            farmerVehicleDetailmodel.setVehicleTypeId(vehicle_arrayList.get(i).getVehicletype_id());
//            farmerVehicleDetailmodel.setVehicleTypeDesc("");
//            farmerVehicleDetailmodel.setDriverTypeId(vehicle_arrayList.get(i).getDriver_id());
//            farmerVehicleDetailmodel.setDriverPrice(vehicle_arrayList.get(i).getAmount());
//            farmerVehicleDetailmodel.setDriverPaymentTypeId(vehicle_arrayList.get(i).getPaymenttypeid());
//            farmerVehicleDetailmodel.setIsVehicleRented(true);
//            farmerVehicleDetailmodel.setWillingToRentVehicle(true);
//            farmerVehicleDetailmodel.setCreatedByUserId(User_id);
//            farmerVehicleDetailmodel.setCreatedDate(currentDate);
//            farmerVehicleDetails.add(farmerVehicleDetailmodel);
//
//        }


//        List<AddFarmerTransportationRequest.FarmerTransportHireBasi> farmerTransportHireBasis = new ArrayList<>();
//
//        // for (int i = 0; i < vehicle_arrayList.size(); i++) {
//
//        AddFarmerTransportationRequest.FarmerTransportHireBasi farmerTransportHireBasismodel = new AddFarmerTransportationRequest.FarmerTransportHireBasi();
//        farmerTransportHireBasismodel.setId(1);
//        farmerTransportHireBasismodel.setFarmerTransportationServiceId(1);
//        farmerTransportHireBasismodel.setTransportFFBTypeId(1);
//        farmerTransportHireBasismodel.setTransportFFBDesc("");
//        farmerTransportHireBasismodel.setHiringTypeId(1);
//        farmerTransportHireBasismodel.setOwnerName("");
//        farmerTransportHireBasismodel.setOwnerAddress("");
//        farmerTransportHireBasismodel.setOwnerMobileNumber("");
//        farmerTransportHireBasismodel.setCreatedByUserId(1);
//        farmerTransportHireBasismodel.setCreatedDate("");
//        farmerTransportHireBasis.add(farmerTransportHireBasismodel);

        // }



        AddFarmerTransportationRequest requestModel = new AddFarmerTransportationRequest(farmerTrasportationDetails,farmerVehicleDetails,null,null, farmerServices);




        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private JsonObject addfarmerTransportationRequestforNoOwnVehicle() {

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        AddFarmerTransportationRequest.FarmerTrasportationDetails farmerTrasportationDetails = new AddFarmerTransportationRequest.FarmerTrasportationDetails();

        farmerTrasportationDetails.setId(null);
        farmerTrasportationDetails.setFarmerCode(_OnlyFarmerCode);
        farmerTrasportationDetails.setFarmerName(_farmerName);
        farmerTrasportationDetails.setOwnVehicle(isOwnVechile);
        farmerTrasportationDetails.setHiringProblems(_hiringProblems);
        farmerTrasportationDetails.setLabourTypeId(labourTypeItem.getId());

        if (labourTypeItem.getName().equalsIgnoreCase("Contract Labour")) {

            farmerTrasportationDetails.setLabourCharges(_labourAmount);
            farmerTrasportationDetails.setLabourChargesTypeId(paymentDurationTypeItem.getId());
            farmerTrasportationDetails.setPaymentTypeId(paymentModeTypeItem.getId());

            if (paymentModeTypeItem.getName().equalsIgnoreCase( "Others")) {

                farmerTrasportationDetails.setPaymentTypeDesc(_otherLabourPaymentMode);
            }
        }
        farmerTrasportationDetails.setTransportServiceFrom3F(companyTransportTypeItem.getId());
        farmerTrasportationDetails.setSuggestions(_suggestions);
        farmerTrasportationDetails.setHireTransportOtherThanTransport(ishireTractor);
        farmerTrasportationDetails.setOtherServices(_needothertypeofservice);
        farmerTrasportationDetails.setKnownAkshaya(isknowAkshaya);
        farmerTrasportationDetails.setCreatedByUserId(created_user.getResult().getUserInfos().getId());
        farmerTrasportationDetails.setCreatedDate(currentDate);
        farmerTrasportationDetails.setUpdatedByUserId(created_user.getResult().getUserInfos().getId());
        farmerTrasportationDetails.setUpdatedDate(currentDate);
        farmerTrasportationDetails.setFarmerMobileNumber(_contactnumber);
        farmerTrasportationDetails.setVillageId(_farmer_villageId);

        List<AddFarmerTransportationRequest.FarmerService> farmerServices = new ArrayList<>();

        //  for (int i = 0; i < selected_villagesids.size(); i++) {
        if(ishireTractor)
        {
            for(int i =0; i <selectedServices.size(); i ++) {


                AddFarmerTransportationRequest.FarmerService farmerServicemodel = new AddFarmerTransportationRequest.FarmerService();
                farmerServicemodel.setId(null);
                farmerServicemodel.setFarmerTransportationServiceId(null);  // Todo Need to discuss
                farmerServicemodel.setServiceTypeId(selectedServices.get(i).getId());
                if(selectedServices.get(i).getId() == 86 ){
                    farmerServicemodel.setServiceDesc(et_otherTypeofservice.getText().toString());}
                else{
                    farmerServicemodel.setServiceDesc(null) ;
                }
               // farmerServicemodel.setServiceDesc(selectedServices.get(i).getName());
                farmerServicemodel.setCreatedByUserId(created_user.getResult().getUserInfos().getId());
                farmerServicemodel.setCreatedDate(currentDate);
                farmerServices.add(farmerServicemodel);
            }
        }


             List<AddFarmerTransportationRequest.FarmerTransportHireCharge> farmerTransportHireCharges = new ArrayList<>();

                 for (int i = 0; i < finaldatalistfromActivity.size(); i++) {

                     for (int j=0; j< finaldatalistfromActivity.get(i).getAllListinfo().size(); j ++){

                         for (int k=0; k<finaldatalistfromActivity.get(i).getAllListinfo().get(j).getPeakdata().size(); k++){

                             if (finaldatalistfromActivity.get(i).getAllListinfo().get(j).getPeakdata().get(k).getPrice() > 0) {

                                 AddFarmerTransportationRequest.FarmerTransportHireCharge farmerTransportHireChargemodel = new AddFarmerTransportationRequest.FarmerTransportHireCharge();
                                 farmerTransportHireChargemodel.setId(null);
                                 farmerTransportHireChargemodel.setFarmerTransportationServiceId(null);
                                 farmerTransportHireChargemodel.setSeasonTypeId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getPeakdata().get(k).getSeasonTypeId());
                                 farmerTransportHireChargemodel.setVehicleTypeId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getPeakdata().get(k).getVehicleTypeId());
                                 farmerTransportHireChargemodel.setVillageId(finaldatalistfromActivity.get(i).getVillageId());
                                 farmerTransportHireChargemodel.setDestinationId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getPeakdata().get(k).getDestinationId());
                                 farmerTransportHireChargemodel.setPrice(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getPeakdata().get(k).getPrice());
                                 farmerTransportHireChargemodel.setCreatedByUserId(created_user.getResult().getUserInfos().getId());
                                 farmerTransportHireChargemodel.setCreatedDate(currentDate);
                                 farmerTransportHireCharges.add(farmerTransportHireChargemodel);
                             }
                             if (finaldatalistfromActivity.get(i).getAllListinfo().get(j).getPeakdata().get(k).getPrice2() > 0){

                                 AddFarmerTransportationRequest.FarmerTransportHireCharge farmerTransportHireChargemodeldest2= new AddFarmerTransportationRequest.FarmerTransportHireCharge();
                                 farmerTransportHireChargemodeldest2.setId(null);
                                 farmerTransportHireChargemodeldest2.setFarmerTransportationServiceId(null);
                                 farmerTransportHireChargemodeldest2.setSeasonTypeId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getPeakdata().get(k).getSeasonTypeId());
                                 farmerTransportHireChargemodeldest2.setVehicleTypeId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getPeakdata().get(k).getVehicleTypeId());
                                 farmerTransportHireChargemodeldest2.setVillageId(finaldatalistfromActivity.get(i).getVillageId());
                                 farmerTransportHireChargemodeldest2.setDestinationId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getPeakdata().get(k).getDestinationId2());
                                 farmerTransportHireChargemodeldest2.setPrice(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getPeakdata().get(k).getPrice2());
                                 farmerTransportHireChargemodeldest2.setCreatedByUserId(created_user.getResult().getUserInfos().getId());
                                 farmerTransportHireChargemodeldest2.setCreatedDate(currentDate);
                                 farmerTransportHireCharges.add(farmerTransportHireChargemodeldest2);
                             }
                         }



                         for (int k=0; k<finaldatalistfromActivity.get(i).getAllListinfo().get(j).getLeanData().size(); k++){

                             if (finaldatalistfromActivity.get(i).getAllListinfo().get(j).getLeanData().get(k).getPrice() > 0) {

                                 AddFarmerTransportationRequest.FarmerTransportHireCharge farmerTransportHireChargemodel = new AddFarmerTransportationRequest.FarmerTransportHireCharge();
                                 farmerTransportHireChargemodel.setId(null);
                                 farmerTransportHireChargemodel.setFarmerTransportationServiceId(null);
                                 farmerTransportHireChargemodel.setSeasonTypeId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getLeanData().get(k).getSeasonTypeId());
                                 farmerTransportHireChargemodel.setVehicleTypeId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getLeanData().get(k).getVehicleTypeId());
                                 farmerTransportHireChargemodel.setVillageId(finaldatalistfromActivity.get(i).getVillageId());
                                 farmerTransportHireChargemodel.setDestinationId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getLeanData().get(k).getDestinationId());
                                 farmerTransportHireChargemodel.setPrice(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getLeanData().get(k).getPrice());
                                 farmerTransportHireChargemodel.setCreatedByUserId(created_user.getResult().getUserInfos().getId());
                                 farmerTransportHireChargemodel.setCreatedDate(currentDate);
                                 farmerTransportHireCharges.add(farmerTransportHireChargemodel);
                             }

                             if (finaldatalistfromActivity.get(i).getAllListinfo().get(j).getLeanData().get(k).getPrice2() > 0){

                                 AddFarmerTransportationRequest.FarmerTransportHireCharge farmerTransportHireChargemodeldest2= new AddFarmerTransportationRequest.FarmerTransportHireCharge();
                                 farmerTransportHireChargemodeldest2.setId(null);
                                 farmerTransportHireChargemodeldest2.setFarmerTransportationServiceId(null);
                                 farmerTransportHireChargemodeldest2.setSeasonTypeId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getLeanData().get(k).getSeasonTypeId());
                                 farmerTransportHireChargemodeldest2.setVehicleTypeId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getLeanData().get(k).getVehicleTypeId());
                                 farmerTransportHireChargemodeldest2.setVillageId(finaldatalistfromActivity.get(i).getVillageId());
                                 farmerTransportHireChargemodeldest2.setDestinationId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getLeanData().get(k).getDestinationId2());
                                 farmerTransportHireChargemodeldest2.setPrice(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getLeanData().get(k).getPrice2());
                                 farmerTransportHireChargemodeldest2.setCreatedByUserId(created_user.getResult().getUserInfos().getId());
                                 farmerTransportHireChargemodeldest2.setCreatedDate(currentDate);
                                 farmerTransportHireCharges.add(farmerTransportHireChargemodeldest2);
                             }
                         }
                     }

                }




        List<AddFarmerTransportationRequest.FarmerTransportHireBasi> farmerTransportHireBasis = new ArrayList<>();

        for (int i = 0; i < finaldatalistfromActivity.size(); i++) {
            for (int j=0; j< finaldatalistfromActivity.get(i).getAllListinfo().size(); j ++) {

                AddFarmerTransportationRequest.FarmerTransportHireBasi farmerTransportHireBasismodel = new AddFarmerTransportationRequest.FarmerTransportHireBasi();
                farmerTransportHireBasismodel.setId(null);
                farmerTransportHireBasismodel.setFarmerTransportationServiceId(null);
                farmerTransportHireBasismodel.setTransportFFBTypeId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getTransportFFBTypeId());

                if(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getTransportFFBTypeId() == 64){

                    farmerTransportHireBasismodel.setTransportFFBDesc(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getTransportFFBDesc());
                }

                farmerTransportHireBasismodel.setHiringTypeId(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getHirebasisId());
                farmerTransportHireBasismodel.setOwnerName(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getOwnerName());
                farmerTransportHireBasismodel.setOwnerAddress(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getOwneraddress());
                farmerTransportHireBasismodel.setOwnerMobileNumber(finaldatalistfromActivity.get(i).getAllListinfo().get(j).getOwnerMobileNumber());
                farmerTransportHireBasismodel.setCreatedByUserId(created_user.getResult().getUserInfos().getId());
                farmerTransportHireBasismodel.setCreatedDate(currentDate);
                farmerTransportHireBasismodel.setCreatedDate(currentDate);
                farmerTransportHireBasismodel.setVillageId(finaldatalistfromActivity.get(i).getVillageId());
                farmerTransportHireBasis.add(farmerTransportHireBasismodel);
            }

         }



        AddFarmerTransportationRequest requestModel = new AddFarmerTransportationRequest(farmerTrasportationDetails,null,farmerTransportHireCharges,farmerTransportHireBasis, farmerServices);


        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }


    @Override
    public void onUpdatedFinalData(int po, ArrayList<VillageWithData> finaldatalist) {
       this.finaldatalistfromActivity = finaldatalist;
//        for (VillageWithData maindatalist:finaldatalist) {
        for (int i=0; i < finaldatalist.size(); i ++){

            Log.d("finaldatalist", finaldatalist.size() + "");
            Log.d("finaldatalist", "final data Current INDEX :"+i);

            if(finaldatalist.get(i).getAllListinfo() != null){

                for (Transporthirebasis data : finaldatalist.get(i).getAllListinfo()) {

                    Log.d("AllListInfo", finaldatalist.get(i).getAllListinfo().size() + "");

                    Log.d("Main Adapter", "------------------------------ Transporthirebasis --------------------START-----------------------------");
                    Log.d("MainAdapter", "Dest1 :" + data.getOwneraddress());
                    Log.d("MainAdapter", "Dest2 :" + data.getOwnerMobileNumber());
                    Log.d("MainAdapter", "FFBId123 :" + data.getTransportFFBTypeId());
                    Log.d("MainAdapter", "Price1 :" + data.getHirebasisId());
                    Log.d("MainAdapter", "Price2 :" + data.getOwnerName());
                    Log.d("Main Adapter", "------------------------------ leandata --------------------START-----------------------------");
                    if (data.getLeanData() != null) {
                        for (Vehicledata leandata :
                                data.getLeanData()) {

                            Log.d("MainAdapter", "Dest1 :" + leandata.getDestinationId());
                            Log.d("MainAdapter", "Dest2 :" + leandata.getDestinationId2());
                            Log.d("MainAdapter", "Price1 :" + leandata.getPrice());
                            Log.d("MainAdapter", "Price2 :" + leandata.getPrice2());
                            Log.d("MainAdapter", "VechileTypeId :" + leandata.getVehicleTypeId());
                            Log.d("MainAdapter", "VillageId :" + leandata.getVillageId());
                        }
                        Log.d("Main Adapter", "------------------------------ leandata --------------------END-----------------------------");
                    }
                    if (data.getPeakdata() != null) {
                        Log.d("Main Adapter", "------------------------------ peakdata --------------------START-----------------------------");
                        for (Vehicledata peakdata :
                                data.getPeakdata()) {

                            Log.d("MainAdapter", "Dest1 :" + peakdata.getDestinationId());
                            Log.d("MainAdapter", "Dest2==== :" + peakdata.getDestinationId2());
                            Log.d("MainAdapter", "Price1 :" + peakdata.getPrice());
                            Log.d("MainAdapter", "Price2 :" + peakdata.getPrice2());
                            Log.d("MainAdapter", "VechileTypeId :" + peakdata.getVehicleTypeId());
                            Log.d("MainAdapter", "VillageId :" + peakdata.getVillageId());
                        }
                        Log.d("Main Adapter", "------------------------------ peakdata --------------------END-----------------------------");
                    }
                    Log.d("Main Adapter", "------------------------------ Transporthirebasis -------------------END------------------------------");

                }
            }
        }
    }

    @Override
    public void selecteditems(ArrayList<Item> items) {
     //   selectedServices.clear();
        selected_Services = items;
      //  Log.e(" selected_Serviceslist==",selected_Services.size()+"");
     //   selectedServices =  _multispinnertypeofservice.getSelectedItems();
        for(int i =0; i <selected_Services.size() ; i ++)
        {
            if(selected_Services.get(i).getId() == 86)
            {
                et_otherTypeofservice.setVisibility(View.VISIBLE);

            }
            else{
                et_otherTypeofservice.setVisibility(View.GONE);
                et_otherTypeofservice.setText("");
            }

        }
    }
}
