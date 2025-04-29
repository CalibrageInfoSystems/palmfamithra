package in.calibrage.palm360fa.Views.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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
import in.calibrage.palm360fa.Model.VehicleTypeResponse;
import in.calibrage.palm360fa.Model.Vehicletype;
import in.calibrage.palm360fa.Model.farmer;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.Views.transport.Model.VillageWithData;
import in.calibrage.palm360fa.common.TypeofServiceMultiSpinner;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Transport_service_questioner_farmer extends AppCompatActivity implements TypeofServiceMultiSpinner.OnMultipleItemSelectedListener,VehicleHireChargesAdapter.VehicleHireChargesAdapterListener {

    // GetPlotVillagesByFarmerCode getPlotVillagesByFarmerCode;
    public static String TAG = "Transport_service_questioner_farmer";
    private SpotsDialog mdilogue;
    private Subscription mSubscription;
    double amount;
    private AutoCompleteUserAdapter userAdapter;
    String FarmerCode;
    boolean isslected = false;
    boolean ownvehiclebool = false;
    boolean knowaboutAkshaya = false;
    boolean hireTractorBool = false;


    LoginResponse created_user;
    private Integer User_id;
    String currentDate;
    RecyclerView donthavevehiclerecycleview;
    VehicleHireChargesAdapter vehicleHireChargesAdapter;

    String FARMER_NAME, farmer_mobilenumber;
    int farmervillageId;

    int vehicletypeid, driverforTransportationid, driverdurationTypeid, labourdurationTypeid, sourceTransportTypeid, hiringbasisid, labourTypeid, labourpaymentmodeid, companyTransportid;

    List<String> get_farmerById = new ArrayList<String>();

    List<String> get_vehicleType = new ArrayList<String>();
    List<Integer> get_vehicleType_Id = new ArrayList<Integer>();
    List<String> get_driverType = new ArrayList<String>();
    List<Integer> get_driverType_Id = new ArrayList<Integer>();


    List<String> get_driverdurationType = new ArrayList<String>();
    List<Integer> get_driverdurationType_Id = new ArrayList<Integer>();

    List<String> get_labourdurationType = new ArrayList<String>();
    List<Integer> get_labourdurationType_Id = new ArrayList<Integer>();


    List<String> get_sourceTransportType = new ArrayList<String>();
    List<Integer> get_sourceTransportType_Id = new ArrayList<Integer>();

    List<String> get_hiringbasisType = new ArrayList<String>();
    List<Integer> get_hiringbasisType_Id = new ArrayList<Integer>();

    List<String> get_labourType = new ArrayList<String>();
    List<Integer> get_labourType_Id = new ArrayList<Integer>();

    List<String> get_labourpaymentType = new ArrayList<String>();
    List<Integer> get_labourpaymentType_Id = new ArrayList<Integer>();

    List<String> get_comapanyTransportType = new ArrayList<String>();
    List<Integer> get_comapanyTransportType_Id = new ArrayList<Integer>();

    List<String> get_typeofService = new ArrayList<String>();
    List<Integer> get_typeofService_Id = new ArrayList<Integer>();
    List<String> selected_servicestype = new ArrayList<String>();


    private ArrayList<farmer> arrayList = new ArrayList<>();

    String contactName, phoneNumber, Farmername, Farmernamewithcode;


    AutoCompleteTextView et_search;
    Getdestinations getdestination;
    //EditText et_search, othervehicletype, othersourceoftransport, vehicleownerName, vehicleowneraddress, vehicleownermobilenumber, facinganyproblem, suggestions, otherservice, otherlabourpaymentmode;
    EditText paymentamount, othervehicletype, othersourceoftransport, vehicleownerName, vehicleowneraddress, vehicleownermobilenumber, facinganyproblem, suggestions, otherservice, otherlabourpaymentmode,
            labourchargespaymentamount, puddlingPayment;
    private ImageView searchclear;
    Button Qr_scan, addmorevehicle, submit;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    Spinner ownvehicle, vehicleType, driverfortransportation, driverpaymentamountper, vehiclecurrentlyonRent, willingtorent, howfarmertransportfromffbtofacotry, hiringbasis, labourType,
            labouramountper, labourChargepaymentmode, hireTractors, puddlingpaymentper, intrestedintakingcompanytransport, aboutakshyaapp;

    TypeofServiceMultiSpinner typeofservice;
    LinearLayout typeofserivelyt;
    private ArrayList<Vehicletype> vehicletypelist = new ArrayList<>();


    //    labouramountper, intrestedintakingcompanytransport, aboutakshyaapp;
    List<String> list = new ArrayList<String>();
    String selectedownvehicle, selectedvehicleType, selecteddriverfortransportation, selecteddriverpaymentamountper, selectedvehiclecurrentlyonRent, selectedwillingtorent, selectedhowfarmertransportfromffbtofacotry,
            selectedhiringbasis, selectedlabourType, selectedlabouramountper, selectedlabourChargepaymentmode, selectedhireTractors, selectedpuddlingpaymentper, selectedintrestedintakingcompanytransport, selectedaboutakshyaapp,
            selectedtypeofservice;

    LinearLayout haveownvehiclelyt, donthaveownvehiclelyt, contractLabour;

    /* EditText pvillagename, p2wdestination1, p2wdestination2, p4wdestination1, p4wdestination2, pvandestination1, pvandestination2, pautodestination1,pautodestination2, pbullockdestination1,
             pbullockdestination2, pothersdestination1, pothersdestination2;

     EditText lvillagename, l2wdestination1, l2wdestination2, l4wdestination1, l4wdestination2, lvandestination1, lvandestination2, lautodestination1,lautodestination2, lbullockdestination1,
             lbullockdestination2, lothersdestination1, lothersdestination2;*/
    ArrayList<Vehicle> vehicle_arrayList = new ArrayList<Vehicle>();
    RecyclerView adddatarecycleview;

    private LinearLayoutManager linearLayoutManager;

//    private TextWatcher mTextWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            doSearch(s.toString());
//            if (s.toString().length() > 0)
//                searchclear.setVisibility(View.VISIBLE);
//            else
//                searchclear.setVisibility(View.GONE);
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_service_questioner_farmer);

        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();

        searchclear = findViewById(R.id.iv_clear);
        et_search = findViewById(R.id.et_search);
        Qr_scan = (Button) findViewById(R.id.btn_qrscan1);
        othervehicletype = findViewById(R.id.othertypevehicle);
        othersourceoftransport = findViewById(R.id.no_othertypevehicle);
        vehicleownerName = findViewById(R.id.hirevehicleownername);
        vehicleowneraddress = findViewById(R.id.hirevehicleowneraddress);
        vehicleownermobilenumber = findViewById(R.id.hirevehicleownermobileNumber);
        facinganyproblem = findViewById(R.id.problemfacing);
        suggestions = findViewById(R.id.suggestions_et);
        otherservice = findViewById(R.id.needanyotherservices_et);
        otherlabourpaymentmode = findViewById(R.id.othertypelabourchargepaymentmode);
        addmorevehicle = findViewById(R.id.addmorevehicles);
        paymentamount = findViewById(R.id.paymentamount);
        labourchargespaymentamount = findViewById(R.id.labourchargespaymentamount);
        submit = findViewById(R.id.submit);

        ownvehicle = findViewById(R.id.own_vehicle);
        vehicleType = findViewById(R.id.typeofvehicle);
        driverfortransportation = findViewById(R.id.driving);
        driverpaymentamountper = findViewById(R.id.paymentper);
        vehiclecurrentlyonRent = findViewById(R.id.vehiclestatus);
        willingtorent = findViewById(R.id.willingtoRent);
        howfarmertransportfromffbtofacotry = findViewById(R.id.farmertransport);
        //hiringbasis = findViewById(R.id.hiringbasis);
        labourType = findViewById(R.id.typeofLabour);
        labouramountper = findViewById(R.id.labourchargespaymentper);
        labourChargepaymentmode = findViewById(R.id.labourchargespaymentmode);
        hireTractors = findViewById(R.id.hireTractors);
        intrestedintakingcompanytransport = findViewById(R.id.companytransportinterest);
        aboutakshyaapp = findViewById(R.id.knowaboutAkshaya);
        typeofservice = findViewById(R.id.typeofservice);

        haveownvehiclelyt = findViewById(R.id.haveownvehicle);
        donthaveownvehiclelyt = findViewById(R.id.donthaveownvehicle);
        contractLabour = findViewById(R.id.clabourchargeslayout);
        typeofserivelyt = findViewById(R.id.typeofserivelyt);


//        pvillagename = findViewById(R.id.peak_plotvillagename);
//        p2wdestination1 = findViewById(R.id.twowheeldestination1_et);
//        p2wdestination2 = findViewById(R.id.twowheeldestination2_et);
//        p4wdestination1 = findViewById(R.id.fourwheeldestination1_et);
//        p4wdestination2 = findViewById(R.id.fourwheeldestination2_et);
//        pvandestination1 = findViewById(R.id.pickupVandestination1_et);
//        pvandestination2 = findViewById(R.id.pickupVandestination2_et);
//        pautodestination1 = findViewById(R.id.autodestination1_et);
//        pautodestination2 = findViewById(R.id.autodestination2_et);
//        pbullockdestination1 = findViewById(R.id.bullockdestination1_et);
//        pbullockdestination2 = findViewById(R.id.bullockdestination2_et);
//        pothersdestination1 = findViewById(R.id.otherdestination1_et);
//        pothersdestination2 = findViewById(R.id.otherdestination1_et);
//
//        lvillagename = findViewById(R.id.lean_plotvillagename);
//        l2wdestination1 = findViewById(R.id.leantwowheeldestination1_et);
//        l2wdestination2 = findViewById(R.id.leantwowheeldestination2_et);
//        l4wdestination1 = findViewById(R.id.leanfourwheeldestination1_et);
//        l4wdestination2 = findViewById(R.id.leanfourwheeldestination2_et);
//        lvandestination1 = findViewById(R.id.leanpickupVandestination1_et);
//        lvandestination2 = findViewById(R.id.leanpickupVandestination2_et);
//        lautodestination1 = findViewById(R.id.leanautodestination1_et);
//        lautodestination2 = findViewById(R.id.leanautodestination2_et);
//        lbullockdestination1 = findViewById(R.id.leanbullockdestination1_et);
//        lbullockdestination2 = findViewById(R.id.leanbullockdestination2_et);
//        lothersdestination1 = findViewById(R.id.leanotherdestination1_et);
//        lothersdestination2 = findViewById(R.id.leanotherdestination2_et);

        adddatarecycleview = findViewById(R.id.adddatarecycleview);
        linearLayoutManager = new LinearLayoutManager(this);
        adddatarecycleview.setLayoutManager(linearLayoutManager);

        donthavevehiclerecycleview = findViewById(R.id.donthavevehiclerecycleview);
        linearLayoutManager = new LinearLayoutManager(this);
        donthavevehiclerecycleview.setLayoutManager(linearLayoutManager);

//        MoreVehiclesAdapter adapter = new MoreVehiclesAdapter(Transport_service_questioner_farmer.this,vehicle_arrayList);
//        adddatarecycleview.setAdapter(adapter);
//        fetchContacts();


        et_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et_search.setText(userAdapter.getItemNameAtPosition(position));
                et_search.setSelection(et_search.getText().toString().trim().length());

                FarmerCode = et_search.getText().toString().trim();
                FARMER_NAME = userAdapter.getItemfarmerNameAtPosition(position);
                FARMER_NAME = userAdapter.getItemfarmerNameAtPosition(position);
                farmer_mobilenumber = userAdapter.getItemfarmerMobileNumberAtPosition(position);
                farmervillageId = userAdapter.getItemfarmerVillageAtPosition(position);


                get3finfo(FarmerCode);

                Log.e("===>farmercode1999===", FarmerCode);
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if (et_search.isPerformingCompletion()) {
                    // An item has been selected from the list. Ignore.
                } else {
                    if (s.toString().toLowerCase().trim().length() >= 3 && s.toString().toLowerCase().trim().length() <= 5) {
                        PopulatePeopleList(s.toString().trim());
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        Qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Transport_service_questioner_farmer.this,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    // Permission already granted, start QR Scanner activity
                    startActivity(new Intent(Transport_service_questioner_farmer.this, QRScanner_Activity.class));
                } else {
                    // Request camera permission
                    ActivityCompat.requestPermissions(Transport_service_questioner_farmer.this,
                            new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }
        });
    }

    // Handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start QR Scanner
                startActivity(new Intent(Transport_service_questioner_farmer.this, QRScanner_Activity.class));
            } else {
                // Permission denied, check if user selected "Don't ask again"
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    // User selected "Don't ask again", show settings dialog
                    showSettingsDialog();
                } else {
                    // User just denied, show a message
                    Toast.makeText(this, "Camera permission is required to scan QR codes.", Toast.LENGTH_LONG).show();
                }
            }
        }
    

// Handle permission result

// Show a dialog directing the user to app settings
      

//
//        Qr_scan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (ContextCompat.checkSelfPermission(Transport_service_questioner_farmer.this,
//                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                    startActivity(new Intent(Transport_service_questioner_farmer.this, QRScanner_Activity.class));
//
//                } else {
//                    ActivityCompat.requestPermissions((Transport_service_questioner_farmer) Transport_service_questioner_farmer.this,
//                            new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
//                }
//            }
//
//        });

        addmorevehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("============>","Clicked");

                amount = Double.valueOf(paymentamount.getText().toString());

                Log.d("Driveramount", amount + "");

                if (addmorevhiclevalidations()){

                    vehicleType.setSelection(0);
                    driverfortransportation.setSelection(0);
                    driverpaymentamountper.setSelection(0);
                    paymentamount.setText("");
                    vehiclecurrentlyonRent.setSelection(0);
                    willingtorent.setSelection(0);
//                    MoreVehiclesAdapter adapter = new MoreVehiclesAdapter(Transport_service_questioner_farmer.this,vehicle_arrayList);
//                    adddatarecycleview.setAdapter(adapter);
                    fetchContacts();
                }



            }

        });


        typeofservice.setListener(this);
        haveownvehicle();
        vehicleType();
        driverforTransportation();
        paymentper();
//        howfarmertransportfromffbtofacotry();
//        hiringbasis();
        typeofLabour();
        labourPaymentMode();
        intrestedintakingcompanytransport();
        typeofservice();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validations()){
                    AddFarmerTransportation();
                    Toast.makeText(Transport_service_questioner_farmer.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        vehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedvehicleType = vehicleType.getItemAtPosition(vehicleType.getSelectedItemPosition()).toString();
                if (i != 0) {

                    vehicletypeid = get_vehicleType_Id.get(vehicleType.getSelectedItemPosition() - 1);
                }
                vehicleType.setPrompt(selectedvehicleType);

                if (vehicleType.getSelectedItemPosition() == 6){

                    othervehicletype.setVisibility(View.VISIBLE);
                }else{
                    othervehicletype.setVisibility(View.GONE);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        driverfortransportation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selecteddriverfortransportation = driverfortransportation.getItemAtPosition(driverfortransportation.getSelectedItemPosition()).toString();
                if (i != 0) {
                    driverforTransportationid = get_driverType_Id.get(driverfortransportation.getSelectedItemPosition() - 1);
                }
                driverfortransportation.setPrompt(selecteddriverfortransportation);
//                if (driverfortransportation.getSelectedItem() == "Others"){
//
//                    othersourceoftransport.setVisibility(View.VISIBLE);
//                }else{
//                    othersourceoftransport.setVisibility(View.GONE);
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        driverpaymentamountper.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selecteddriverpaymentamountper = driverpaymentamountper.getItemAtPosition(driverpaymentamountper.getSelectedItemPosition()).toString();
                if (i != 0) {

                    driverdurationTypeid = get_driverdurationType_Id.get(driverpaymentamountper.getSelectedItemPosition() - 1);
                }
                driverpaymentamountper.setPrompt(selecteddriverpaymentamountper);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        labouramountper.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedlabouramountper = labouramountper.getItemAtPosition(labouramountper.getSelectedItemPosition()).toString();
                if (i != 0) {

                    labourdurationTypeid = get_labourdurationType_Id.get(labouramountper.getSelectedItemPosition() - 1);
                }
                labouramountper.setPrompt(selectedlabouramountper);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

//        howfarmertransportfromffbtofacotry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                selectedhowfarmertransportfromffbtofacotry = howfarmertransportfromffbtofacotry.getItemAtPosition(howfarmertransportfromffbtofacotry.getSelectedItemPosition()).toString();
//                if (i != 0) {
//
//                    sourceTransportTypeid = get_sourceTransportType_Id.get(howfarmertransportfromffbtofacotry.getSelectedItemPosition() - 1);
//                }
//                howfarmertransportfromffbtofacotry.setPrompt(selectedhowfarmertransportfromffbtofacotry);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                // DO Nothing here
//            }
//        });
//
//        hiringbasis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                selectedhiringbasis = hiringbasis.getItemAtPosition(hiringbasis.getSelectedItemPosition()).toString();
//                if (i != 0) {
//
//                    hiringbasisid = get_hiringbasisType_Id.get(hiringbasis.getSelectedItemPosition() - 1);
//                }
//                hiringbasis.setPrompt(selectedhiringbasis);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                // DO Nothing here
//            }
//        });

        labourType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedlabourType = labourType.getItemAtPosition(labourType.getSelectedItemPosition()).toString();
                if (i != 0) {

                    labourTypeid = get_labourType_Id.get(labourType.getSelectedItemPosition() - 1);
                }
                labourType.setPrompt(selectedlabourType);

                if (labourType.getSelectedItemPosition() == 2){

                    contractLabour.setVisibility(View.VISIBLE);
                }else{
                    contractLabour.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        labourChargepaymentmode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedlabourChargepaymentmode = labourChargepaymentmode.getItemAtPosition(labourChargepaymentmode.getSelectedItemPosition()).toString();
                if (i != 0) {

                    labourpaymentmodeid = get_labourpaymentType_Id.get(labourChargepaymentmode.getSelectedItemPosition() - 1);
                }
                labourChargepaymentmode.setPrompt(selectedlabourChargepaymentmode);

                if (labourChargepaymentmode.getSelectedItemPosition() == 3){

                    otherlabourpaymentmode.setVisibility(View.VISIBLE);
                }else{
                    otherlabourpaymentmode.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        intrestedintakingcompanytransport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedintrestedintakingcompanytransport = intrestedintakingcompanytransport.getItemAtPosition(intrestedintakingcompanytransport.getSelectedItemPosition()).toString();
                if (i != 0) {

                    companyTransportid = get_comapanyTransportType_Id.get(intrestedintakingcompanytransport.getSelectedItemPosition() - 1);
                }
                intrestedintakingcompanytransport.setPrompt(selectedintrestedintakingcompanytransport);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

    }

    // Show a dialog directing the user to app settings
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Required")
                .setMessage("Camera permission is needed to scan QR codes. Please enable it in Settings.")
                .setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void fetchContacts() {

        Vehicle a = new Vehicle( vehicletypeid,selectedvehicleType,selecteddriverfortransportation,driverforTransportationid,amount,selecteddriverpaymentamountper,driverdurationTypeid,selectedvehiclecurrentlyonRent,selectedwillingtorent,othervehicletype.getText().toString());
        vehicle_arrayList.add(a);

    }

    private boolean addmorevhiclevalidations() {

        if (vehicleType.getSelectedItemPosition() == 0){

            showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.validatevehicletype));
            return false;
        }

        if (vehicleType.getSelectedItemPosition() == 6) {

            if (othervehicletype.getText().toString().matches("")) {

                showDialog(Transport_service_questioner_farmer.this, getResources().getString(R.string.pleaseenterothervehicletype));
                return false;
            }
        }

        else if (driverfortransportation.getSelectedItemPosition() == 0){

            showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.pleaseselectdriverfortransportation));
            return false;
        }
        else if(paymentamount.getText().toString().matches("") ){
            showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.enterdriverpayment));
            return false;

        }else if (driverpaymentamountper.getSelectedItemPosition() == 0){

            showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectamountperdriverpayment));
            return false;
        }else if (vehiclecurrentlyonRent.getSelectedItemPosition() == 0){

            showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectvehiclecurrentlyonrent));
            return false;
        }else if (willingtorent.getSelectedItemPosition() == 0){

            showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectwillingtorent));
            return false;
        }

        return true;
    }


    private boolean validations() {

        if(et_search.getText().toString().matches("")){
            showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectfarmerid));
            return false;
        }
        if (ownvehicle.getSelectedItemPosition() == 0 ){
            showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selecthaveownvehicletotransport));
            return false;
        }

        if (ownvehicle.getSelectedItemPosition() == 1 ){

            if (vehicleType.getSelectedItemPosition() == 0){

                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.validatevehicletype));
                return false;
            }
            if (vehicleType.getSelectedItemPosition() == 6) {

                if (othervehicletype.getText().toString().matches("")) {

                    showDialog(Transport_service_questioner_farmer.this, getResources().getString(R.string.pleaseenterothervehicletype));
                    return false;
                }
            }

            else if (driverfortransportation.getSelectedItemPosition() == 0){

                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.pleaseselectdriverfortransportation));
                return false;
            }
            else if(paymentamount.getText().toString().matches("") ){
                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.enterdriverpayment));
                return false;

            }else if (driverpaymentamountper.getSelectedItemPosition() == 0){

                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectamountperdriverpayment));
                return false;
            }else if (vehiclecurrentlyonRent.getSelectedItemPosition() == 0){

                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectvehiclecurrentlyonrent));
                return false;
            }else if (willingtorent.getSelectedItemPosition() == 0){

                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectwillingtorent));
                return false;
            }

        }

//        if (ownvehicle.getSelectedItemPosition() == 2 ) {
//
//            if (howfarmertransportfromffbtofacotry.getSelectedItemPosition() == 0){
//
//                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectHowFarmerTransportFFBtoFactory));
//                return false;
//            }else if (othersourceoftransport.getText().toString().matches("")){
//
//                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectothersourceofTransport));
//                return false;
//            }
//
//            else if (hiringbasis.getSelectedItemPosition() == 0) {
//
//                showDialog(Transport_service_questioner_farmer.this, getResources().getString(R.string.validatehiringbasis));
//                return false;
//            }
////            }else if (pvillagename.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakentervillageName));
////                return false;
////            }
////            else if (p2wdestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenter2wdestination1));
////                return false;
////            }else if (p2wdestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenter2wdestination2));
////                return false;
////            }else if (p4wdestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenter4wdestination1));
////                return false;
////            }else if (p4wdestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenter4wdestination2));
////                return false;
////            }else if (pvandestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenterpickupvandestination1));
////                return false;
////            }else if (pvandestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenterpickupvandestination2));
////                return false;
////            }else if (pautodestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenterautodestination1));
////                return false;
////            }else if (pautodestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenterautodestination2));
////                return false;
////            }else if (pbullockdestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenterbullockcartdestination1));
////                return false;
////            }else if (pbullockdestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenterbullockcartdestination2));
////                return false;
////            }else if (pothersdestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenterotherdestination1));
////                return false;
////            }else if (pothersdestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.peakenterotherdestination2));
////                return false;
////            }
////
////            else if (lvillagename.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanentervillageName));
////                return false;
////            }
////
////            else if (l2wdestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenter2wdestination1));
////                return false;
////            }else if (l2wdestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenter2wdestination2));
////                return false;
////            }else if (l4wdestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenter4wdestination1));
////                return false;
////            }else if (l4wdestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenter4wdestination2));
////                return false;
////            }else if (lvandestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenterpickupvandestination1));
////                return false;
////            }else if (lvandestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenterpickupvandestination2));
////                return false;
////            }else if (lautodestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenterautodestination1));
////                return false;
////            }else if (lautodestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenterautodestination2));
////                return false;
////            }else if (lbullockdestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenterbullockcartdestination1));
////                return false;
////            }else if (lbullockdestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenterbullockcartdestination2));
////                return false;
////            }else if (lothersdestination1.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenterotherdestination1));
////                return false;
////            }else if (lothersdestination2.getText().toString().matches("")){
////
////                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.leanenterotherdestination2));
////                return false;
////            }
//
//            else if (vehicleownerName.getText().toString().matches("")){
//
//                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.entervehicleownername));
//                return false;
//            }else if (vehicleowneraddress.getText().toString().matches("")){
//
//                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.entervehicleowneraddress));
//                return false;
//            }else if (vehicleownermobilenumber.getText().toString().matches("")){
//
//                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.entervehicleownermobilenumber));
//                return false;
//            }
//        }

       if (labourType.getSelectedItemPosition() == 0){

           showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectlabourtype));
           return false;

       }

        if (labourType.getSelectedItemPosition() == 2){

            if (labourchargespaymentamount.getText().toString().matches("")){

                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.enterlabourcharableamount));
                return false;
            }else if(labouramountper.getSelectedItemPosition() == 0){

                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectlabouramountper));
                return false;
            }else if (labourChargepaymentmode.getSelectedItemPosition() == 0){

                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectlabourpaymentmode));
                return false;
            }

        }

        if (labourChargepaymentmode.getSelectedItemPosition() == 3){

            if (otherlabourpaymentmode.getText().toString().matches("")){

                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.specifyotherpaymentmodeforlabour));
                return false;
            }

        }


        if (hireTractors.getSelectedItemPosition() == 0){

            showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectdoyouhiretractors));
            return false;

        }

//        if (hireTractors.getSelectedItemPosition() == 1){
//
//            if (typeofservice.getSelectedItemPosition() == 0){
//
//                showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selecttypeofservice));
//                return false;
//            }
//
//        }

        if (intrestedintakingcompanytransport.getSelectedItemPosition() == 0){

            showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectinterestedintakingcompanytransport));
            return false;

        }

        if (aboutakshyaapp.getSelectedItemPosition() == 0){

            showDialog(Transport_service_questioner_farmer.this,getResources().getString(R.string.selectaboutakshaya));
            return false;

        }


        return true;
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

                            if (getPlotVillagesByFarmerCode != null) {


                            //arrayList.add(new  farmer(item.getFarmerCode(),item.getContactNumber()));

                                Log.e("=====getdestination",getdestination.getResult().getImportantPlaces().getCollectionCenters().size()+"");
                                Log.e("======ehicleTypeList",vehicletypelist.get(0).getVehicleName() + "");

                                vehicleHireChargesAdapter = new VehicleHireChargesAdapter(Transport_service_questioner_farmer.this, getPlotVillagesByFarmerCode.getListResult(),getdestinations,vehicletypelist, Transport_service_questioner_farmer.this);
                                donthavevehiclerecycleview.setAdapter(vehicleHireChargesAdapter);

                        }
                    }


                });}

    private void PopulatePeopleList(String s) {
        if(arrayList!=null)
            arrayList.clear();
        mdilogue.show();
        JsonObject object = postsearchrequest(s);
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
                        showDialog(Transport_service_questioner_farmer.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(Farmersearchresponse farmersearchresponse) {


                        for (Farmersearchresponse.ListResult item : farmersearchresponse.getListResult()
                        ) {
                            contactName = item.getFarmerCode();
                            phoneNumber = item.getContactNumber();
                            Farmername = item.getFarmerName();
                            Farmernamewithcode = item.getDisplayName();
                            int Villageid = item.getVillageId();
                            // arrayList = new ArrayList<>();
                            arrayList.add(new farmer(Villageid,contactName,phoneNumber,Farmernamewithcode,farmer_mobilenumber));
//                            contactName = item.getFarmerCode();
//                            phoneNumber = item.getContactNumber();
//                            // arrayList = new ArrayList<>();
//                            arrayList.add(new farmer(contactName,phoneNumber));



                            //     arrayList.add(new  farmer(item.getFarmerCode(),item.getContactNumber()));
                            userAdapter = new AutoCompleteUserAdapter(Transport_service_questioner_farmer.this, R.layout.farmerselection, arrayList);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    et_search.setAdapter(userAdapter);
                                    userAdapter.notifyDataSetChanged();
                                    et_search.showDropDown();
                                }
                            });
                        }

                        FarmerCode   =  et_search.getText().toString().trim();
                        Log.e("===>farmercode470",FarmerCode);

//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Transport_service_questioner_vendor.this,android.R.layout.select_dialog_item,mPeopleList);
//                         mAdapter = new FarmerselectionAdapter(Transport_service_questioner_vendor.this, android.R.layout.select_dialog_item, mPeopleList);
//                            autoTextView.setThreshold(3);
//                            autoTextView.setAdapter(adapter);
//
//                        actvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                    FarmerCode   =  actvName.getText().toString();
//
//                                    Log.e("==========>farmercode",FarmerCode);
//                                  //  fruitDesc.setText(fruit.getDesc());
//                                }
//                            });
//                        }
                    }
                });}

    private JsonObject postsearchrequest(String s) {
        Farmersearchobject requestModel = new Farmersearchobject();
        requestModel.setSearchKey(s);
        requestModel.setId(null);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();



    }

    private void get3finfo(String farmerCode) {
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
                            TransportationService(FarmerCode,getdestinations);
                            Log.e("=====>destination", getdestinations + "");
                        }
                    }
                });}


    private void AddFarmerTransportation() {
        mdilogue.show();
        JsonObject object = addfarmerTransportationRequest();
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
                        showDialog(Transport_service_questioner_farmer.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(AddFarmerTransportationResponse farmerTransportationResponse) {

//
//                        for (AddFarmerTransportationResponse.ListResult item : farmerTransportationResponse.getListResult()
//                        ) {
//                            contactName = item.getFarmerCode();
//                            phoneNumber = item.getContactNumber();
//                            // arrayList = new ArrayList<>();
//                            arrayList.add(new farmer(contactName,phoneNumber));
//
//
//
//                            //     arrayList.add(new  farmer(item.getFarmerCode(),item.getContactNumber()));
//                            userAdapter = new AutoCompleteUserAdapter(Transport_service_questioner_farmer.this, R.layout.farmerselection, arrayList);
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    et_search.setAdapter(userAdapter);
//                                    userAdapter.notifyDataSetChanged();
//                                    et_search.showDropDown();
//                                }
//                            });
//                        }
//
//                        FarmerCode   =  et_search.getText().toString().trim();
//                        Log.e("===>farmercode470",FarmerCode);
//
////                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Transport_service_questioner_vendor.this,android.R.layout.select_dialog_item,mPeopleList);
////                         mAdapter = new FarmerselectionAdapter(Transport_service_questioner_vendor.this, android.R.layout.select_dialog_item, mPeopleList);
////                            autoTextView.setThreshold(3);
////                            autoTextView.setAdapter(adapter);
////
////                        actvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                                @Override
////                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                                    FarmerCode   =  actvName.getText().toString();
////
////                                    Log.e("==========>farmercode",FarmerCode);
////                                  //  fruitDesc.setText(fruit.getDesc());
////                                }
////                            });
////                        }
                    }
                });}



    private JsonObject addfarmerTransportationRequest() {

        created_user = SharedPrefsData.getCreatedUser(Transport_service_questioner_farmer.this);
        User_id = created_user.getResult().getUserInfos().getId();

        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        AddFarmerTransportationRequest.FarmerTrasportationDetails farmerTrasportationDetails = new AddFarmerTransportationRequest.FarmerTrasportationDetails();

        Double labouramount = Double.parseDouble(labourchargespaymentamount.getText().toString());

        farmerTrasportationDetails.setId(null);
        farmerTrasportationDetails.setFarmerCode(FarmerCode);
        farmerTrasportationDetails.setFarmerName(FARMER_NAME);
        farmerTrasportationDetails.setOwnVehicle(ownvehiclebool);
        farmerTrasportationDetails.setHiringProblems(facinganyproblem.getText().toString());
        farmerTrasportationDetails.setLabourTypeId(labourTypeid);

        if (labourType.getSelectedItem() ==  "Contract Labour") {

            farmerTrasportationDetails.setLabourCharges(labouramount);
            farmerTrasportationDetails.setLabourChargesTypeId(labourdurationTypeid);
            farmerTrasportationDetails.setPaymentTypeId(labourpaymentmodeid);

            if (labourChargepaymentmode.getSelectedItem() == "Others") {

                farmerTrasportationDetails.setPaymentTypeDesc(otherlabourpaymentmode.getText().toString() + "");
            }
        }
        farmerTrasportationDetails.setTransportServiceFrom3F(companyTransportid);
        farmerTrasportationDetails.setSuggestions(suggestions.getText().toString());
        farmerTrasportationDetails.setHireTransportOtherThanTransport(hireTractorBool);
        farmerTrasportationDetails.setOtherServices(otherservice.getText().toString() + "");
        farmerTrasportationDetails.setKnownAkshaya(knowaboutAkshaya);
        farmerTrasportationDetails.setCreatedByUserId(User_id);
        farmerTrasportationDetails.setCreatedDate(currentDate);
        farmerTrasportationDetails.setUpdatedByUserId(User_id);
        farmerTrasportationDetails.setUpdatedDate(currentDate);
        farmerTrasportationDetails.setFarmerMobileNumber(farmer_mobilenumber);
        farmerTrasportationDetails.setVillageId(farmervillageId);

        List<AddFarmerTransportationRequest.FarmerService> farmerServices = new ArrayList<>();

      //  for (int i = 0; i < selected_villagesids.size(); i++) {

        AddFarmerTransportationRequest.FarmerService farmerServicemodel = new AddFarmerTransportationRequest.FarmerService();
        farmerServicemodel.setId(1);
        farmerServicemodel.setFarmerTransportationServiceId(1);
        farmerServicemodel.setServiceTypeId(1);
        farmerServicemodel.setServiceDesc("");
        farmerServicemodel.setCreatedByUserId(1);
        farmerServicemodel.setCreatedDate("");
        farmerServices.add(farmerServicemodel);
       // }

        List<AddFarmerTransportationRequest.FarmerTransportHireCharge> farmerTransportHireCharges = new ArrayList<>();

        //  for (int i = 0; i < selected_villagesids.size(); i++) {


        AddFarmerTransportationRequest.FarmerTransportHireCharge farmerTransportHireChargemodel = new AddFarmerTransportationRequest.FarmerTransportHireCharge();
        farmerTransportHireChargemodel.setId(1);
        farmerTransportHireChargemodel.setFarmerTransportationServiceId(1);
        farmerTransportHireChargemodel.setSeasonTypeId(1);
        farmerTransportHireChargemodel.setVehicleTypeId(1);
        farmerTransportHireChargemodel.setVillageId(1);
        farmerTransportHireChargemodel.setDestinationId(1);
        farmerTransportHireChargemodel.setPrice(1.1);
        farmerTransportHireChargemodel.setCreatedByUserId(1);
        farmerTransportHireChargemodel.setCreatedDate("");
        farmerTransportHireCharges.add(farmerTransportHireChargemodel);

        //}
        Log.d(TAG, "----- Analysis ==> vehiclesize :" + vehicle_arrayList.size() );

        List<AddFarmerTransportationRequest.FarmerVehicleDetail> farmerVehicleDetails = new ArrayList<>();

        AddFarmerTransportationRequest.FarmerVehicleDetail farmerVehicle_Detail = new AddFarmerTransportationRequest.FarmerVehicleDetail();
        farmerVehicle_Detail.setId(null);
        farmerVehicle_Detail.setFarmerTransportationServiceId(null);
        farmerVehicle_Detail.setVehicleTypeId(vehicletypeid);
        farmerVehicle_Detail.setVehicleTypeDesc("");
        farmerVehicle_Detail.setDriverTypeId(driverforTransportationid);
        farmerVehicle_Detail.setDriverPrice(amount);
        farmerVehicle_Detail.setDriverPaymentTypeId(driverdurationTypeid);
        farmerVehicle_Detail.setIsVehicleRented(true);
        farmerVehicle_Detail.setWillingToRentVehicle(true);
        farmerVehicle_Detail.setCreatedByUserId(User_id);
        farmerVehicle_Detail.setCreatedDate(currentDate);
        farmerVehicleDetails.add(farmerVehicle_Detail);

                for (int i = 0; i < vehicle_arrayList.size(); i++) {

                    AddFarmerTransportationRequest.FarmerVehicleDetail farmerVehicleDetailmodel = new AddFarmerTransportationRequest.FarmerVehicleDetail();
                    farmerVehicleDetailmodel.setId(null);
                    farmerVehicleDetailmodel.setFarmerTransportationServiceId(null);
                    farmerVehicleDetailmodel.setVehicleTypeId(vehicle_arrayList.get(i).getVehicletype_id());
                    farmerVehicleDetailmodel.setVehicleTypeDesc("");
                    farmerVehicleDetailmodel.setDriverTypeId(vehicle_arrayList.get(i).getDriver_id());
                    farmerVehicleDetailmodel.setDriverPrice(vehicle_arrayList.get(i).getAmount());
                    farmerVehicleDetailmodel.setDriverPaymentTypeId(vehicle_arrayList.get(i).getPaymenttypeid());
                    farmerVehicleDetailmodel.setIsVehicleRented(true);
                    farmerVehicleDetailmodel.setWillingToRentVehicle(true);
                    farmerVehicleDetailmodel.setCreatedByUserId(User_id);
                    farmerVehicleDetailmodel.setCreatedDate(currentDate);
                    farmerVehicleDetails.add(farmerVehicleDetailmodel);

                }


        List<AddFarmerTransportationRequest.FarmerTransportHireBasi> farmerTransportHireBasis = new ArrayList<>();

       // for (int i = 0; i < vehicle_arrayList.size(); i++) {

            AddFarmerTransportationRequest.FarmerTransportHireBasi farmerTransportHireBasismodel = new AddFarmerTransportationRequest.FarmerTransportHireBasi();
                    farmerTransportHireBasismodel.setId(1);
                    farmerTransportHireBasismodel.setFarmerTransportationServiceId(1);
                    farmerTransportHireBasismodel.setTransportFFBTypeId(1);
                    farmerTransportHireBasismodel.setTransportFFBDesc("");
                    farmerTransportHireBasismodel.setHiringTypeId(1);
                    farmerTransportHireBasismodel.setOwnerName("");
                    farmerTransportHireBasismodel.setOwnerAddress("");
                    farmerTransportHireBasismodel.setOwnerMobileNumber("");
                    farmerTransportHireBasismodel.setCreatedByUserId(1);
                    farmerTransportHireBasismodel.setCreatedDate("");
                    farmerTransportHireBasis.add(farmerTransportHireBasismodel);

        // }



        AddFarmerTransportationRequest requestModel = new AddFarmerTransportationRequest(farmerTrasportationDetails,farmerVehicleDetails,farmerTransportHireCharges,farmerTransportHireBasis, farmerServices);




        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private void haveownvehicle() {
        List<String> list = new ArrayList<String>();
        list.add("Select");
        list.add("Yes");
        list.add("No");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinneritem, list);

        dataAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        ownvehicle.setAdapter(dataAdapter);
        ownvehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedownvehicle = ownvehicle.getItemAtPosition(ownvehicle.getSelectedItemPosition()).toString();

                Log.d("selectedownvehicle", selectedownvehicle + "");


                if (ownvehicle.getSelectedItem() == "Yes"){

                    isslected = true;
                    ownvehiclebool = true;
                    haveownvehiclelyt.setVisibility(View.VISIBLE);
                    donthaveownvehiclelyt.setVisibility(View.GONE);

                }

                if (ownvehicle.getSelectedItem() == "No"){
                    isslected = false;
                    ownvehiclebool = false;
                    donthaveownvehiclelyt.setVisibility(View.VISIBLE);
//                    vehicleHireChargesAdapter = new VehicleHireChargesAdapter(Transport_service_questioner_farmer.this, getPlotVillagesByFarmerCode.getListResult(),getdestinations, Transport_service_questioner_farmer.this);
//                    donthavevehiclerecycleview.setAdapter(vehicleHireChargesAdapter);
                    haveownvehiclelyt.setVisibility(View.GONE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        vehiclecurrentlyonRent.setAdapter(dataAdapter);
        vehiclecurrentlyonRent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedvehiclecurrentlyonRent = vehiclecurrentlyonRent.getItemAtPosition(vehiclecurrentlyonRent.getSelectedItemPosition()).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        willingtorent.setAdapter(dataAdapter);
        willingtorent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedwillingtorent = willingtorent.getItemAtPosition(willingtorent.getSelectedItemPosition()).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        hireTractors.setAdapter(dataAdapter);
        hireTractors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedhireTractors = hireTractors.getItemAtPosition(hireTractors.getSelectedItemPosition()).toString();

               if(hireTractors.getSelectedItem() == "Yes") {

                   hireTractorBool = true;
                   typeofserivelyt.setVisibility(View.VISIBLE);
               }else{
                   hireTractorBool = false;
                   typeofserivelyt.setVisibility(View.GONE);
               }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


        aboutakshyaapp.setAdapter(dataAdapter);
        aboutakshyaapp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedaboutakshyaapp = aboutakshyaapp.getItemAtPosition(aboutakshyaapp.getSelectedItemPosition()).toString();
                if (aboutakshyaapp.getSelectedItem() == "Yes"){

                    knowaboutAkshaya = true;

                }else {
                    knowaboutAkshaya = false;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

    }


    private void vehicleType() {

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

                        if (vehicleTypeResponse.getListResult() != null) {
                            get_vehicleType.add("Select Vehicle Type");
                            for (VehicleTypeResponse.ListResult data : vehicleTypeResponse.getListResult()
                            ) {
                                get_vehicleType.add(data.getDesc());
                                get_vehicleType_Id.add(data.getTypeCdId());
                            }
                            for (int i = 0; i < vehicleTypeResponse.getListResult().size(); i++) {
                            vehicletypelist.add(new Vehicletype(i,  vehicleTypeResponse.getListResult().get(i).getDesc(),vehicleTypeResponse.getListResult().get(i).getTypeCdId()));}
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Transport_service_questioner_farmer.this, R.layout.spinneritem, get_vehicleType);
                            dataAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            vehicleType.setAdapter(dataAdapter);

                        } else {
                            get_vehicleType.add("No Vehicle Type Available");
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }

                });

    }

    private void driverforTransportation()  {

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
                            get_driverType.add("Select Driver Type");
                            for (GetDriverforTransportationTypes.ListResult data : driverforTransportationTypes.getListResult()
                            ) {
                                get_driverType.add(data.getDesc());
                                get_driverType_Id.add(data.getTypeCdId());
                            }


                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Transport_service_questioner_farmer.this, R.layout.spinneritem, get_driverType);
                            dataAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            driverfortransportation.setAdapter(dataAdapter);

                        } else {
                            get_driverType.add("No Vehicle Type Available");
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }

                });

    }



    private void paymentper() {

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
                            get_driverdurationType.add("Select Duration Type");
                            get_labourdurationType.add("Select Duration Type");
                            for (GetDurationTypes.ListResult data : getDurationTypes.getListResult()
                            ) {
                                get_driverdurationType.add(data.getDesc());
                                get_driverdurationType_Id.add(data.getTypeCdId());
                                get_labourdurationType.add(data.getDesc());
                                get_labourdurationType_Id.add(data.getTypeCdId());
                            }


                            ArrayAdapter<String> driverdurationAdapter = new ArrayAdapter<String>(Transport_service_questioner_farmer.this, R.layout.spinneritem, get_driverdurationType);
                            driverdurationAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            driverpaymentamountper.setAdapter(driverdurationAdapter);

                            ArrayAdapter<String> labourdurationAdapter = new ArrayAdapter<String>(Transport_service_questioner_farmer.this, R.layout.spinneritem, get_labourdurationType);
                            labourdurationAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            labouramountper.setAdapter(labourdurationAdapter);

                        } else {
                            get_driverdurationType.add("No Vehicle Type Available");
                            get_labourdurationType.add("No Vehicle Type Available");
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }

                });
    }

//    private void howfarmertransportfromffbtofacotry() {
//        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
//        mSubscription = service.getsourceofTransportTypes(APIConstantURL.sourceofTransportType)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetSourceofTransportType>() {
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
//                        mdilogue.cancel();
//                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
//                    }
//
//                    @Override
//                    public void onNext(GetSourceofTransportType getSourceofTransportType) {
//
//                        if (getSourceofTransportType.getListResult() != null) {
//                            get_sourceTransportType.add("Select Transportation Type");
//                            for (GetSourceofTransportType.ListResult data : getSourceofTransportType.getListResult()
//                            ) {
//                                get_sourceTransportType.add(data.getDesc());
//                                get_sourceTransportType_Id.add(data.getTypeCdId());
//                            }
//
//
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Transport_service_questioner_farmer.this, R.layout.spinneritem, get_sourceTransportType);
//                            dataAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
//                            howfarmertransportfromffbtofacotry.setAdapter(dataAdapter);
//
//                        } else {
//                            get_sourceTransportType.add("No Transportation Type Available");
//                            Log.e("nodada====", "nodata===custom2");
//
//                        }
//
//
//                    }
//
//                });
//    }
//
//    private void hiringbasis() {
//
//        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
//        mSubscription = service.gethiringbasisTypes(APIConstantURL.hiringbasis)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetHiringBasisTypes>() {
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
//                        mdilogue.cancel();
//                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
//                    }
//
//                    @Override
//                    public void onNext(GetHiringBasisTypes getHiringBasisTypes) {
//
//                        if (getHiringBasisTypes.getListResult() != null) {
//                            get_hiringbasisType.add("Select Hiring Basis Type");
//                            for (GetHiringBasisTypes.ListResult data : getHiringBasisTypes.getListResult()
//                            ) {
//                                get_hiringbasisType.add(data.getDesc());
//                                get_hiringbasisType_Id.add(data.getTypeCdId());
//                            }
//
//
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Transport_service_questioner_farmer.this, R.layout.spinneritem, get_hiringbasisType);
//                            dataAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
//                            hiringbasis.setAdapter(dataAdapter);
//
//                        } else {
//                            get_hiringbasisType.add("No Hiring Basis Type Available");
//                            Log.e("nodada====", "nodata===custom2");
//
//                        }
//
//
//                    }
//
//                });
//    }

    private void typeofLabour() {

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
                            get_labourType.add("Select Labour Type");
                            for (GetLabourTypes.ListResult data : getLabourTypes.getListResult()
                            ) {
                                get_labourType.add(data.getDesc());
                                get_labourType_Id.add(data.getTypeCdId());
                            }

                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Transport_service_questioner_farmer.this, R.layout.spinneritem, get_labourType);
                            dataAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            labourType.setAdapter(dataAdapter);

                        } else {
                            get_labourType.add("No Labour Type Available");
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }

                });

    }

    private void labourPaymentMode() {
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
                            get_labourpaymentType.add("Select Payment Type");
                            for (GetPaymentModeTypes.ListResult data : getPaymentModeTypes.getListResult()
                            ) {
                                get_labourpaymentType.add(data.getDesc());
                                get_labourpaymentType_Id.add(data.getTypeCdId());
                            }

                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Transport_service_questioner_farmer.this, R.layout.spinneritem, get_labourpaymentType);
                            dataAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            labourChargepaymentmode.setAdapter(dataAdapter);

                        } else {
                            get_labourpaymentType.add("No Payment Type Available");
                            Log.e("nodada====", "nodata===custom2");

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
                            get_comapanyTransportType.add("Please Select");
                            for (GetCompanyTransport.ListResult data : getCompanyTransport.getListResult()
                            ) {
                                get_comapanyTransportType.add(data.getDesc());
                                get_comapanyTransportType_Id.add(data.getTypeCdId());
                            }

                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Transport_service_questioner_farmer.this, R.layout.spinneritem, get_comapanyTransportType);
                            dataAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            intrestedintakingcompanytransport.setAdapter(dataAdapter);

                        } else {
                            get_comapanyTransportType.add("No Payment Type Available");
                            Log.e("nodada====", "nodata===custom2");

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

                        if (getTypeofServices.getListResult() != null) {
                            get_typeofService.add("Select Service Type");
                            for (GetTypeofServices.ListResult data : getTypeofServices.getListResult()
                            ) {
                                get_typeofService.add(data.getDesc());
                                get_typeofService_Id.add(data.getTypeCdId());
                            }

                            typeofservice.setItems(get_typeofService);


                        } else {
                            get_typeofService.add("No Service Type Available");
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }

                });

    }

    public void doSearch(String searchQuery) {

        Log.d("Search", "SearchClicked");
    }

    @Override
    public void selectedindexx(List<Integer> indices) {

    }

    @Override
    public JsonObject selctedString(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            String name = strings.get(i);

            if (!selected_servicestype.contains(name))
                selected_servicestype.add(name);

        }
        return null;
    }



    @Override
    public void onUpdatedFinalData(int po, ArrayList<VillageWithData> finaldatalist) {

    }
}
