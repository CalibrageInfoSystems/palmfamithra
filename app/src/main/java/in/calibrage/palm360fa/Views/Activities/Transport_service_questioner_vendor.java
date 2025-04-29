package in.calibrage.palm360fa.Views.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

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
import in.calibrage.palm360fa.Adapter.HirechargesAdapter;
import in.calibrage.palm360fa.Adapter.PlougherAdapter;
import in.calibrage.palm360fa.Adapter.PuddlerAdapter;
import in.calibrage.palm360fa.Adapter.SpinnerArrayAdapter;
import in.calibrage.palm360fa.Adapter.VehicleTypesWithCount;
import in.calibrage.palm360fa.Adapter.vehicltypeadapter;
import in.calibrage.palm360fa.Model.Addvendorobject;
import in.calibrage.palm360fa.Model.Attachments;
import in.calibrage.palm360fa.Model.Farmersearchobject;
import in.calibrage.palm360fa.Model.Farmersearchresponse;
import in.calibrage.palm360fa.Model.GetDistricts;
import in.calibrage.palm360fa.Model.GetMandals;
import in.calibrage.palm360fa.Model.GetStates;
import in.calibrage.palm360fa.Model.GetTypeCdDmt;
import in.calibrage.palm360fa.Model.GetVillages;
import in.calibrage.palm360fa.Model.Getdestinations;
import in.calibrage.palm360fa.Model.Hirecharges;
import in.calibrage.palm360fa.Model.Hirecharges_new;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.MSGmodel;

import in.calibrage.palm360fa.Model.VehicleTypes;
import in.calibrage.palm360fa.Model.VendorTransportationResponse;
import in.calibrage.palm360fa.Model.VillagesData;
import in.calibrage.palm360fa.Model.farmer;
import in.calibrage.palm360fa.Model.spinneritemmodel;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.Views.transport.Model.TypeItem;
import in.calibrage.palm360fa.Views.transport.SpinnerTypeArrayAdapter;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.CommonUtil;
import in.calibrage.palm360fa.common.MultiSelectionSpinnerr;
import in.calibrage.palm360fa.common.MultiSelection_Spinner;
import in.calibrage.palm360fa.common.MultiSelection_VASpinner;
import in.calibrage.palm360fa.common.MultiSelection_VSpinner;
import in.calibrage.palm360fa.common.MultiSelectionnSpinner;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static in.calibrage.palm360fa.service.APIConstantURL.Getmandals;
import static in.calibrage.palm360fa.service.APIConstantURL.Getvillages;

public class Transport_service_questioner_vendor extends BaseActivity implements MultiSelection_Spinner.OnMultipleItemSelectedListener,
        MultiSelectionnSpinner.OnMultipleItemsSelectedListener, MultiSelectionSpinnerr.OnMultipleItemsSelectedListener, MultiSelection_VSpinner.OnMultipleItemSelectedListener,
        MultiSelection_VASpinner.OnMultipleItemSelectedListener, AdapterView.OnItemClickListener, HirechargesAdapter.HirechargesAdapterListener, PlougherAdapter.PlougherAdapterListener, PuddlerAdapter.PuddleAdapterListener, vehicltypeadapter.vehicltypeadapterListener {
    // variables

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    String farmer_mobilenumber;
    String currentDate;
    public static String TAG = "Transport_service_questioner_vendor";
    public Spinner haveownfactor, ownership, hiringservice, hiring_basics, getadvace, paymentperiod, payment_mode, delaypayment, haveinterest, spin_state, spin_attachment,uomtype,uomtypeother,uomtyspear;
    EditText Name, address, mobile, no_vehicles, price,othercharges, problem, pleasespecipy, chargetails, commentTxt, Attachvehiclecharges, Attachvehiclecharges2, pleasespecipyvehicle, pleasespecipypaymentmode;
    MultiSelection_Spinner villages;
    MultiSelectionSpinnerr districts;
    MultiSelectionnSpinner mandal;
    MultiSelection_VASpinner Vehicle_attachment;
    MultiSelection_VSpinner vehicle_type;
    private Integer User_id;
    private RecyclerView.LayoutManager layoutManager, layoutManager2;
    private List<VillagesData> villagesList = new ArrayList<>();
    LoginResponse created_user;
    String selected_factorzone, selectedownership, SELECTEDhiringservice, selecteduomype, selecteduomypeother,selecteduomyespare, Selected_getadvace, selected_state, attachment_have,
            selected_paymentperiod, selected_paymentmode, selected_waitingcharge, selectAppinterest;
    ImageView backImg, home_btn;
    static ArrayList<Hirecharges_new> hirechargeslist = new ArrayList<>();
    static ArrayList<Attachments> attachmentlist = new ArrayList<>();
    static ArrayList<Attachments> Attachment_List = new ArrayList<>();
    LinearLayout factorzonesyes, factorzonesno, otherlinear, chargedetails, attachment;
    List<String> get_mandal = new ArrayList<String>();
    List<Integer> get_mandal_Id = new ArrayList<Integer>();
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    //  List<String> get_state = new ArrayList<String>();
    List<String> get_state_code = new ArrayList<String>();
    List<Integer> get_state_Id = new ArrayList<Integer>();
    String contactName, phoneNumber, Farmername, Farmernamewithcode;
    int Villageid;
    List<String> get_district = new ArrayList<String>();
    List<Integer> get_district_Id = new ArrayList<Integer>();
    List<String> get_vehicleattachment = new ArrayList<String>();
    List<Integer> get_vehicleattachment_Id = new ArrayList<Integer>();
    private ArrayList<String> mPeopleList = new ArrayList<>();

    List<String> get_villages = new ArrayList<String>();
    List<Integer> get_villages_Id = new ArrayList<Integer>();
    public static int mandal_id, village_id, state_id;
    String Statecode;
    ImageButton Qr_scan;
    private int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    List<String> selected_districts = new ArrayList<String>();
    List<String> selected_Mandals = new ArrayList<String>();
    List<String> selected_villages = new ArrayList<String>();
    List<String> selected_Vehicles = new ArrayList<String>();
    List<Integer> selected_districtsids = new ArrayList<Integer>();
    List<Integer> selected_Mandalsids = new ArrayList<Integer>();
    List<Integer> selected_villagesids = new ArrayList<Integer>();
    List<Integer> selected_vehicleattachmentids = new ArrayList<Integer>();
    List<String> selected_vehicleattachment = new ArrayList<String>();
    List<Integer> selected_vehicleids = new ArrayList<Integer>();
//    List<String> get_hiringservice = new ArrayList<String>();
//    List<Integer> get_hiringservice_Id = new ArrayList<Integer>();

    List<String> get_vehicletype = new ArrayList<String>();
    List<Integer> get_vehicletypeid = new ArrayList<Integer>();

    List<String> get_hiringbasis = new ArrayList<String>();
    List<Integer> get_hiringbasisid = new ArrayList<Integer>();

    TypeItem paymentperiodTypeItem,hiringservicesTypeItem,paymentModeTypeItem, ownershipTypeItem,selectedfactorzone,Selecteduomtype,SelectedUomtypeother,Selecteduomtypespare;
    private ArrayList<TypeItem> get_paymentperiod, get_paymenttype,get_ownership,get_state,get_hiringservice,AttachmentChargesType;
    int Attachment_ID;
    private AutoCompleteTextView autoTextView;

    private AutoCompleteUserAdapter userAdapter;
    private HirechargesAdapter hirechargesAdapter;
    Button buttonSubmit;
    private vehicltypeadapter vehicletypeadapter;
    private PlougherAdapter plougherAdapter;
    private PuddlerAdapter puddlerAdapter;
    public static AutoCompleteTextView farmerId;
    private ArrayList<farmer> arrayList = new ArrayList<>();
    RecyclerView hirecharges;
    String FarmerCode, FARMER_NAME;
    LinearLayout chargeslinear;
    RecyclerView Plougherrecycle, Puddlerrecycle;
    String selected_distname, selected_distids, selected_mandalname, selected_mandalids, selected_villageids, selected_sttached_ids;
    int CC_id, Attachedis, selected_Vehicleid;
    Double Price, Amount;
    int Vehiclecount;
    boolean isslected = false;
    boolean isattached = false;
    boolean getAdvaced = false;
    boolean havewaitcharges = false;
    boolean appinstalled = false;

    private List<Getdestinations.CollectionCenter> CCList = new ArrayList<>();
    private List<Getdestinations.Mill> MCCList = new ArrayList<>();

    RecyclerView vehicle_recycle;
    private ArrayList<VehicleTypes> vehicletype_list = new ArrayList<>();
    private ArrayList<VehicleTypesWithCount> vehicletypewithcount_list = new ArrayList<>();
    Getdestinations getdestinations;
    GetTypeCdDmt gethirechargesdata;
    GetTypeCdDmt AttachmentChargesdata;
    LinearLayout Sprayerchargeslinear, Puddlerchargeslinear, Ploughechargeslinear, othervepaymentmodelinear, othervehiclelinear,attachmentsublinear,yesadvancelinear,otherattachments;
    Spinner spinner;
    SpinnerTypeArrayAdapter paymentperiodAdapter,hiringserviceTypeAdapter,paymenttypeAdapter, ownershipAdapter,stateAdapter,AttachmentChargesTypeAdapter;

    private ArrayList<spinneritemmodel> algorithmItems;
    SpinnerArrayAdapter adapter;
    String QRstring;

    EditText pleasespecipyattachment;

    // TypeItem labourTypeItem,paymentDurationTypeItem,paymentModeTypeItem, companyTransportTypeItem,driverforTransportationTypeItem,driverpaymentdurationTypeItem;
    // variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_service_questioner_vendor);

        intviews();
        setviews();
    }

    private void intviews() {

        //  spinner = (Spinner)findViewById(R.id.spinner);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        backImg = (ImageView) findViewById(R.id.back);
        spin_state = findViewById(R.id.spin_state);
        villages = (MultiSelection_Spinner) findViewById(R.id.villages);
        districts = (MultiSelectionSpinnerr) findViewById(R.id.distct);
        haveownfactor = (Spinner) findViewById(R.id.haveownfactor);
        ownership = (Spinner) findViewById(R.id.ownership);
        hiringservice = (Spinner) findViewById(R.id.hiringservice);
        mandal = (MultiSelectionnSpinner) findViewById(R.id.mandalspin);
        vehicle_type = (MultiSelection_VSpinner) findViewById(R.id.vehicle_type);
        hiring_basics = (Spinner) findViewById(R.id.hiring_basics);
        spin_attachment = (Spinner) findViewById(R.id.attachment);
        getadvace = (Spinner) findViewById(R.id.getadvace);
        paymentperiod = (Spinner) findViewById(R.id.paymentperiod);
        payment_mode = (Spinner) findViewById(R.id.payment_mode);
        delaypayment = (Spinner) findViewById(R.id.delaypayment);
        haveinterest = (Spinner) findViewById(R.id.haveinterest);
        commentTxt = findViewById(R.id.commentTxt);
        Vehicle_attachment = (MultiSelection_VASpinner) findViewById(R.id.Vehicle_attachment);
        Name = findViewById(R.id.Name);
        address = findViewById(R.id.address);
        mobile = findViewById(R.id.mobile);
        vehicle_recycle = findViewById(R.id.vehicle_recycle);
        Attachvehiclecharges = findViewById(R.id.charges);
        Attachvehiclecharges2 = findViewById(R.id.Sprayercharges);
        problem = findViewById(R.id.problem);
        pleasespecipy = findViewById(R.id.pleasespecipy);
        chargetails = findViewById(R.id.chargetails);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        factorzonesyes = findViewById(R.id.factorzonesyes);
        chargedetails = findViewById(R.id.chargedetails);
        factorzonesno = findViewById(R.id.factorzonesno);
        otherlinear = findViewById(R.id.otherlinear);
        Qr_scan = (ImageButton) findViewById(R.id.btn_qrscan1);
        hirecharges = (RecyclerView) findViewById(R.id.hirecharges);
        attachment = (LinearLayout) findViewById(R.id.attachmentlinear);
        chargeslinear = (LinearLayout) findViewById(R.id.Chaffchargeslinear);
        Ploughechargeslinear = (LinearLayout) findViewById(R.id.Ploughechargeslinear);
        Puddlerchargeslinear = (LinearLayout) findViewById(R.id.Puddlerchargeslinear);
        Sprayerchargeslinear = (LinearLayout) findViewById(R.id.Sprayerchargeslinear);
        Puddlerrecycle = (RecyclerView) findViewById(R.id.Puddlerrecycle);
        Plougherrecycle = (RecyclerView) findViewById(R.id.Plougherrecycle);
        othercharges =(EditText)findViewById(R.id.othercharges);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        farmerId = (AutoCompleteTextView) findViewById(R.id.autoTextView);
        othervehiclelinear = findViewById(R.id.othervehiclelinear);
        pleasespecipyvehicle = findViewById(R.id.pleasespecipyvehicle);
        othervepaymentmodelinear = findViewById(R.id.othervepaymentmodelinear);
        pleasespecipypaymentmode = findViewById(R.id.pleasespecipypaymentmode);
        attachmentsublinear = findViewById(R.id.attachmentsublinear);
        yesadvancelinear =findViewById(R.id.yesadvancelinear);
        pleasespecipyattachment = findViewById(R.id.pleasespecipyattachment);
        otherattachments = findViewById(R.id.otherattachments);

        uomtype = findViewById(R.id.uomtype);
        uomtypeother = findViewById(R.id.uomtypeother);
        uomtyspear = findViewById(R.id.uom_spinner);
    }



    private void setviews() {
//
        if (isOnline()) {
            yesorno();
            FFBtransporthiringservice();
            Attachment_ChargesType();
            villages.setListener(this);
            districts.setListener(this);
            mandal.setListener(this);
            vehicle_type.setListener(this);
            Vehicle_attachment.setListener(this);
            GetStates();

            // vehicletype();
            hiringbasis();
            getadvanceyesorno();
            payment_period();
            paymentmode();
            waitingchargesyesorno();
            haveinterestyesorno();
        } else {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.Internet));
            //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }

        hirecharges.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager2 = new LinearLayoutManager(this);
        hirecharges.setLayoutManager(new LinearLayoutManager(this));


        Puddlerrecycle.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setAutoMeasureEnabled(false);
        Puddlerrecycle.setLayoutManager(llm);
        Plougherrecycle.setHasFixedSize(true);
        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        llm2.setAutoMeasureEnabled(false);
        Plougherrecycle.setLayoutManager(llm2);

        vehicle_recycle.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        vehicle_recycle.setLayoutManager(mLayoutManager);
//        LinearLayoutManager llm3= new LinearLayoutManager(this);
//        llm3.setAutoMeasureEnabled(false);
//        vehicle_recycle.setLayoutManager(llm3);


        farmerId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                farmerId.setText(userAdapter.getItemfarmercodewithnameAtPosition(position));
                farmerId.setSelection(farmerId.getText().toString().trim().length());

                FarmerCode = userAdapter.getItemNameAtPosition(position);
                FARMER_NAME = userAdapter.getItemfarmerNameAtPosition(position);


                Log.e("===>farmercode1999===", FarmerCode + "========>farmname" + FARMER_NAME);
            }
        });

        farmerId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if (farmerId.isPerformingCompletion()) {
                    // An item has been selected from the list. Ignore.
                } else {
                    if (s.toString().toUpperCase().trim().length() >= 5 && s.toString().toUpperCase().trim().length() <= 17) {
                        PopulatePeopleList(s.toString().trim());
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


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
                Intent intent = new Intent(Transport_service_questioner_vendor.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        Qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(Transport_service_questioner_vendor.this,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Transport_service_questioner_vendor.this, QRScanner_Activity.class);
                    startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                    //  startActivity(new Intent(Transport_service_questioner_vendor.this, QRScanner_Activity.class));

                } else {
                    ActivityCompat.requestPermissions((Transport_service_questioner_vendor) Transport_service_questioner_vendor.this,
                            new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }

        });

        yesorno();
        FFBtransporthiringservice();

        villages.setListener(this);
        districts.setListener(this);
        mandal.setListener(this);
        GetStates();
//        Getmandal();
//        GetVillages();
        if(FarmerCode!=null && !FarmerCode.equalsIgnoreCase("")){
            get3finfo(FarmerCode);
        }
        else {
            get3finfo(null);
        }

        hiringbasis();
        getadvanceyesorno();
        payment_period();
        paymentmode();
        waitingchargesyesorno();
        haveinterestyesorno();


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (validations()) {

                    Log.e("=============>1", attachmentlist.size() + "");
                    Log.e("=============>2", Attachment_List.size() + "");


                    AddVendorTransportation();

                }

            }
        });

    }

    private void Attachment_ChargesType() {
        mdilogue.show();


        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.gettypecddmtdata(APIConstantURL.GetTypeCDdmt + 28)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTypeCdDmt>() {
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
                    public void onNext(GetTypeCdDmt getTypeCdDmt) {


                        if (getTypeCdDmt.getListResult() != null) {
                            AttachmentChargesType = new ArrayList<>();
                            AttachmentChargesType.add(new TypeItem(0, "Please Select"));

                            AttachmentChargesdata = getTypeCdDmt;
                            for (GetTypeCdDmt.ListResult data : getTypeCdDmt.getListResult()
                            ) { AttachmentChargesType.add(new TypeItem(data.getTypeCdId(), data.getDesc()));



                            }


                            AttachmentChargesTypeAdapter = new SpinnerTypeArrayAdapter(Transport_service_questioner_vendor.this, AttachmentChargesType);
                            AttachmentChargesTypeAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            uomtype.setAdapter(AttachmentChargesTypeAdapter);
                            uomtypeother.setAdapter(AttachmentChargesTypeAdapter);
                            uomtyspear.setAdapter(AttachmentChargesTypeAdapter);

                            uomtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    selecteduomype = uomtype.getItemAtPosition(uomtype.getSelectedItemPosition()).toString();
                                  //  hiringservicesTypeItem = (TypeItem) hiringservice.getSelectedItem();

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    // DO Nothing here
                                }
                            });
                            uomtypeother.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    selecteduomypeother = uomtypeother.getItemAtPosition(uomtypeother.getSelectedItemPosition()).toString();
                                    //  hiringservicesTypeItem = (TypeItem) hiringservice.getSelectedItem();

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    // DO Nothing here
                                }
                            });
                            uomtyspear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    selecteduomyespare = uomtyspear.getItemAtPosition(uomtyspear.getSelectedItemPosition()).toString();
                                    //  hiringservicesTypeItem = (TypeItem) hiringservice.getSelectedItem();

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    // DO Nothing here
                                }
                            });

                        } else {

                        }

                    }

                });


    }


    private void get3finfo(String farmerCode) {
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getcc(APIConstantURL.Get3FInfo + farmerCode + "/" + Statecode)
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
                    public void onNext(Getdestinations getdestinationss) {

                        if (getdestinationss.getResult() != null) {

                            getdestinations = getdestinationss;
                            Log.e("=====>destination", getdestinations + "");

                            algorithmItems = new ArrayList<>();



                        }

                        //TransportationService(FarmerCode,getdestinations);

                    }
                });
    }

    private void AddVendorTransportation() {
        mdilogue.show();
        JsonObject object = VendorTransportationObject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.VendorTransportation(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VendorTransportationResponse>() {

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
                        showDialog(Transport_service_questioner_vendor.this, getString(R.string.server_error));
                    }

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onNext(VendorTransportationResponse farmersearchresponse) {




                        if (farmersearchresponse.getIsSuccess()){

                            List<MSGmodel> displayList = new ArrayList<>();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                showSuccessDialog(displayList, getResources().getString(R.string.vendortransportservice));
                            }

                        }else{

                            showDialog(Transport_service_questioner_vendor.this, farmersearchresponse.getEndUserMessage());
                            //  Toast.makeText(Transport_service_questioner_vendor.this, farmersearchresponse.getEndUserMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

    private JsonObject VendorTransportationObject() {
        created_user = SharedPrefsData.getCreatedUser(Transport_service_questioner_vendor.this);
        User_id = created_user.getResult().getUserInfos().getId();

        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Addvendorobject.VendorTrasportationDetails headerModel = new Addvendorobject.VendorTrasportationDetails();
        headerModel.setId(null);
        headerModel.setOilPalmInFactoryZone(isslected);

        if (isslected == true) {
            headerModel.setFarmerCode(FarmerCode);
            headerModel.setFarmerName(FARMER_NAME);
            headerModel.setOwnershipTypeId(ownershipTypeItem.getId());
            headerModel.setOwnerName(null);
            headerModel.setOwnerMobileNumber(null);
            headerModel.setOwnerAddress(null);
        } else {
            headerModel.setOwnershipTypeId(null);
            headerModel.setFarmerCode(null);
            headerModel.setFarmerName(null);
            headerModel.setOwnerName(Name.getText().toString().trim());
            headerModel.setOwnerMobileNumber(mobile.getText().toString().trim());
            headerModel.setOwnerAddress(address.getText().toString().trim());
        }


        headerModel.setHiringServiceDurationTypeId(hiringservicesTypeItem.getId());

        headerModel.setHiringProblems(problem.getText().toString().trim());
        headerModel.setIsAdvanceGiven(getAdvaced);

        if(getAdvaced == true) {
            headerModel.setPaymentPeriodTypeId(paymentperiodTypeItem.getId());
            headerModel.setPaymentPeriodDesc(pleasespecipy.getText().toString());

            headerModel.setPaymentTypeId(paymentModeTypeItem.getId());
            headerModel.setPaymentTypeDesc(pleasespecipypaymentmode.getText().toString());
        }else{
            headerModel.setPaymentPeriodTypeId(null);
            headerModel.setPaymentPeriodDesc(null);

            headerModel.setPaymentTypeId(null);
            headerModel.setPaymentTypeDesc(null);
        }
        headerModel.setDoesWaitingFeeCharged(havewaitcharges);
        headerModel.setWaitingChargeDetails(chargetails.getText().toString().trim());
        headerModel.setInterestToApp(appinstalled);
        headerModel.setTractorAttachments(isattached);
        headerModel.setComments(commentTxt.getText().toString());
        headerModel.setCreatedByUserId(User_id);
        headerModel.setCreatedDate(currentDate);
        headerModel.setUpdatedByUserId(User_id);
        headerModel.setUpdatedDate(currentDate);



        List<Addvendorobject.VendorVillageDetail> villagedata = new ArrayList<>();
        for (int i = 0; i < selected_villagesids.size(); i++) {

            Addvendorobject.VendorVillageDetail villagemodel = new Addvendorobject.VendorVillageDetail();
            villagemodel.setId(null);
            villagemodel.setVendorTransportationServiceId(null);
            villagemodel.setVillageId(selected_villagesids.get(i));
            villagemodel.setCreatedByUserId(User_id);
            villagemodel.setCreatedDate(currentDate);
            villagedata.add(villagemodel);
        }
        List<Addvendorobject.VendorVehicleDetail> vehicledata = new ArrayList<>();

        for (int i = 0; i < vehicletypewithcount_list.size(); i++) {

            Addvendorobject.VendorVehicleDetail vehiclemodel = new Addvendorobject.VendorVehicleDetail();
            vehiclemodel.setId(null);
            vehiclemodel.setVendorTransportationServiceId(null);
            vehiclemodel.setVehicleCount(vehicletypewithcount_list.get(i).getNoofvehicles());
            vehiclemodel.setVehicleTypeId(vehicletypewithcount_list.get(i).getVehicleid());
            vehiclemodel.setCreatedByUserId(User_id);
            if(vehicletypewithcount_list.get(i).getVehicleid()== 51){
            vehiclemodel.setVehicleTypeDesc(pleasespecipyvehicle.getText().toString());}

            vehiclemodel.setCreatedDate(currentDate);
            vehicledata.add(vehiclemodel);
        }

        List<Addvendorobject.VendorTractorAttachment> Tractordata = new ArrayList<>();
        if (spin_attachment.getSelectedItemPosition() == 1) {
            for (int i = 0; i < selected_vehicleattachmentids.size(); i++) {
                if (selected_vehicleattachmentids.get(i) != 88 && selected_vehicleattachmentids.get(i) != 89) {

                    Addvendorobject.VendorTractorAttachment tractorattachmodel = new Addvendorobject.VendorTractorAttachment();
                    tractorattachmodel.setId(null);
                    tractorattachmodel.setVendorTransportationServiceId(null);
                    tractorattachmodel.setTractorAttachmentTypeId(selected_vehicleattachmentids.get(i));
                    tractorattachmodel.setTractorAttachmentSubTypeId(null);
                    tractorattachmodel.setTractorAttachmentTypeDesc(null);

                    if (selected_vehicleattachmentids.get(i) == 87) {
                        tractorattachmodel.setCharges(Double.valueOf(Attachvehiclecharges.getText().toString()));
                        tractorattachmodel.setTractorAttachmentUOMTypeId(Selecteduomtype.getId());
                    }
                    if (selected_vehicleattachmentids.get(i) == 90) {
                        tractorattachmodel.setCharges(Double.valueOf(Attachvehiclecharges2.getText().toString()));
                        tractorattachmodel.setTractorAttachmentUOMTypeId(Selecteduomtypespare.getId());
                    }
                    if (selected_vehicleattachmentids.get(i) == 102) {
                        tractorattachmodel.setTractorAttachmentTypeDesc(pleasespecipyattachment.getText().toString());
                        tractorattachmodel.setTractorAttachmentUOMTypeId(SelectedUomtypeother.getId());
                        tractorattachmodel.setCharges(Double.valueOf(othercharges.getText().toString()));
                    }
                    tractorattachmodel.setCreatedByUserId(User_id);
                    tractorattachmodel.setCreatedDate(currentDate);
                    Tractordata.add(tractorattachmodel);
                }
            }
        }
        for (int i = 0; i < attachmentlist.size(); i++) {

            if (attachmentlist.get(i).getPrice() > 0) {

                Addvendorobject.VendorTractorAttachment tractorattachmodel = new Addvendorobject.VendorTractorAttachment();
                tractorattachmodel.setId(null);
                tractorattachmodel.setVendorTransportationServiceId(null);
                tractorattachmodel.setTractorAttachmentTypeId(attachmentlist.get(i).getAttachmentID());
                tractorattachmodel.setTractorAttachmentSubTypeId(attachmentlist.get(i).getAttached_id());
                tractorattachmodel.setCharges(attachmentlist.get(i).getPrice());
                tractorattachmodel.setCreatedByUserId(User_id);
                tractorattachmodel.setCreatedDate(currentDate);
                tractorattachmodel.setTractorAttachmentUOMTypeId(attachmentlist.get(i).getUOMID());
                Tractordata.add(tractorattachmodel);
            }
        }

        for (int i = 0; i < Attachment_List.size(); i++) {

            if (Attachment_List.get(i).getPrice() > 0) {
                Addvendorobject.VendorTractorAttachment tractorattachmodel = new Addvendorobject.VendorTractorAttachment();
                tractorattachmodel.setId(null);
                tractorattachmodel.setVendorTransportationServiceId(null);
                tractorattachmodel.setTractorAttachmentTypeId(Attachment_List.get(i).getAttachmentID());
                tractorattachmodel.setTractorAttachmentSubTypeId(Attachment_List.get(i).getAttached_id());
                tractorattachmodel.setCharges(Attachment_List.get(i).getPrice());
                tractorattachmodel.setCreatedByUserId(User_id);
                tractorattachmodel.setTractorAttachmentUOMTypeId(Attachment_List.get(i).getUOMID());
                tractorattachmodel.setCreatedDate(currentDate);
                Tractordata.add(tractorattachmodel);
            }

        }


        List<Addvendorobject.VendorHireCharge> hirechargedataedata = new ArrayList<>();

        for (int i = 0; i < hirechargeslist.size(); i++) {

            for (int j = 0; j < hirechargeslist.get(i).getHirecharge_details().size(); j++) {
                Addvendorobject.VendorHireCharge chargesmodel = new Addvendorobject.VendorHireCharge();
                chargesmodel.setId(null);
                chargesmodel.setVendorTransportationServiceId(null);
                chargesmodel.setVillageId(hirechargeslist.get(i).getHirecharge_details().get(j).getVillage_id());
                chargesmodel.setDestinationId(hirechargeslist.get(i).getHirecharge_details().get(j).getCcid());
                chargesmodel.setPrice(hirechargeslist.get(i).getHirecharge_details().get(j).getPrice());
                chargesmodel.setVehicleTypeId(hirechargeslist.get(i).getHirecharge_details().get(j).getVehicleid());
                chargesmodel.setCreatedByUserId(User_id);
                chargesmodel.setCreatedDate(currentDate);
                chargesmodel.setHiringTypeId(hirechargeslist.get(i).getHirecharge_details().get(j).getHirebasisid());

                hirechargedataedata.add(chargesmodel);
                if (hirechargeslist.get(i).getHirecharge_details().get(j).getOptPrice() > 0) {
                    Addvendorobject.VendorHireCharge chargesmodell = new Addvendorobject.VendorHireCharge();
                    chargesmodell.setId(null);
                    chargesmodell.setVendorTransportationServiceId(null);
                    chargesmodell.setVillageId(hirechargeslist.get(i).getHirecharge_details().get(j).getVillage_id());
                    chargesmodell.setDestinationId(hirechargeslist.get(i).getHirecharge_details().get(j).getOptccid());
                    chargesmodell.setPrice(hirechargeslist.get(i).getHirecharge_details().get(j).getOptPrice());
                    chargesmodell.setVehicleTypeId(hirechargeslist.get(i).getHirecharge_details().get(j).getVehicleid());
                    chargesmodell.setCreatedByUserId(User_id);
                    chargesmodell.setCreatedDate(currentDate);
                    chargesmodell.setHiringTypeId(hirechargeslist.get(i).getHirecharge_details().get(j).getOpthirebasisid());

                    hirechargedataedata.add(chargesmodell);
                }


            }
        }

        Addvendorobject requestModel = new Addvendorobject(headerModel, villagedata, Tractordata, vehicledata, hirechargedataedata);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

    private void PopulatePeopleList(String s) {
        if (arrayList != null)
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
                        showDialog(Transport_service_questioner_vendor.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(Farmersearchresponse farmersearchresponse) {
                        if(farmersearchresponse.getListResult()!=null) {

                            for (Farmersearchresponse.ListResult item : farmersearchresponse.getListResult()
                            ) {
                                contactName = item.getFarmerCode();
                                phoneNumber = item.getContactNumber();
                                Farmername = item.getFarmerName();
                                Farmernamewithcode = item.getDisplayName();
                                Villageid = item.getVillageId();
                                // arrayList = new ArrayList<>();
                                arrayList.add(new farmer(Villageid, contactName, phoneNumber, Farmername, Farmernamewithcode));


                                //     arrayList.add(new  farmer(item.getFarmerCode(),item.getContactNumber()));
                                userAdapter = new AutoCompleteUserAdapter(Transport_service_questioner_vendor.this, R.layout.farmerselection, arrayList);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        farmerId.setAdapter(userAdapter);
                                        userAdapter.notifyDataSetChanged();
                                        farmerId.showDropDown();
                                    }
                                });
                            }

                            FarmerCode = farmerId.getText().toString().trim();
                            Log.e("===>farmercode470", FarmerCode);
                        }
                        else{
                            showDialog(Transport_service_questioner_vendor.this, getString(R.string.nofarmer));
                        }
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
                });
    }


    private boolean validations() {
        selectedfactorzone = (TypeItem) paymentperiod.getSelectedItem();
        paymentperiodTypeItem = (TypeItem) paymentperiod.getSelectedItem();
        hiringservicesTypeItem = (TypeItem) hiringservice.getSelectedItem();
        paymentModeTypeItem = (TypeItem) payment_mode.getSelectedItem();
        ownershipTypeItem = (TypeItem) ownership.getSelectedItem();
        Selecteduomtype =(TypeItem) uomtype.getSelectedItem();
        SelectedUomtypeother =(TypeItem) uomtypeother.getSelectedItem();
        Selecteduomtypespare =(TypeItem) uomtyspear.getSelectedItem();
        if (haveownfactor.getSelectedItemPosition() == 0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.Validatefactorzone));
            return false;
        }
        if (haveownfactor.getSelectedItemPosition() == 1) {
            if (farmerId.getText().toString().matches("")) {
                showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.farmar_id));
                return false;
            } else if (ownership.getSelectedItemPosition() == 0) {
                showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validateownership));
                return false;
            }

        }
        if (haveownfactor.getSelectedItemPosition() == 2) {
            if (Name.getText().toString().matches("")) {
                showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.entername));
                return false;
            } else if (address.getText().toString().matches("")) {
                showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.enteraddress));
                return false;
            } else if (mobile.getText().toString().matches("")) {
                showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.entermobile));
                return false;
            }

        }

        if (hiringservice.getSelectedItemPosition() == 0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.selecthiring));
            return false;
        } else if (spin_state.getSelectedItemPosition() == 0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.Valid_state));

            return false;
        } else if (selected_districts.size() == 0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.Valid_districts));

            return false;
        }
        else if(selected_districtsids == null ||  selected_districtsids.size() == 0){
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.Valid_districts));

            return false;
        } else if (selected_Mandals.size() == 0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.Valid_mandal));

            return false;
        }
        else if (selected_Mandalsids.size() == 0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.Valid_mandal));

            return false;
        }else if (selected_villagesids.size() == 0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.Valid_villages));

            return false;

        } else if(selected_Vehicles.size() == 0 ){
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatevehicletype));

            return false;}
        else if(selected_vehicleids.size() == 0 ){
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatevehicletype));

            return false;}

        else if (vehicletypewithcount_list.size() == 0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.enternumofvehicle));
            return false;
        }

        else if (Validatevehiclecount() == false)
        {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.enternumofvehicle));
            return  false;
        }else if (selected_vehicleids.contains("51")) {

            if (pleasespecipyvehicle.getText().toString().matches("")) {
                showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatespecify));
                return false;
            }
        }

        else  if (hirechargeslist.size() == 0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatehirecharges));
            return false;
        } else if (ValidateHireCharges() == false)
        {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatehirecharges));
            return  false;
        }
        else if(spin_attachment.getSelectedItemPosition() == 0){
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validateAttachments));
            return false;
        }
        if(spin_attachment.getSelectedItemPosition() == 1){
            if(selected_vehicleattachmentids.size() == 0 ){
                showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatevehicleAttachments));

                return false;}

            else if (selected_vehicleattachmentids.contains(87)){


                if (Attachvehiclecharges.getText().toString().matches("")) {
                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.entercharges));
                    return false;
                }
                else if (uomtype.getSelectedItemPosition() == 0) {
                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.ValidateUOM));
                    return false;
                }
            }

            if (selected_vehicleattachmentids.contains(88)){


                if ( Attachment_List.size() == 0 ){

                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.enterchargess));
                    return false;

                }
                else if (!IsVallidAttachment())
                {
                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.enterchargess));
                    return  false;
                }}
            if (selected_vehicleattachmentids.contains(89)) {
                if (attachmentlist.size() == 0) {

                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.enterchargespuddler));
                    return false;

                }
                else if (Validatepluder() == false)
                {
                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.enterchargespuddler));
                    return  false;
                }

            }
            if (selected_vehicleattachmentids.contains(90)){
                if (Attachvehiclecharges2.getText().toString().matches("")) {
                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.entercharges));
                    return false;
                }
                else if (uomtyspear.getSelectedItemPosition() == 0) {
                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.ValidateUOM));
                    return false;
                }
            }

            if (selected_vehicleattachmentids.contains(102)){
                if(pleasespecipyattachment.getText().toString().matches("")) {
                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.pleasespecify));
                    return false;
                }

             else if (othercharges.getText().toString().matches("")) {
                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.entercharges));
                    return false;
                }
            else if (uomtypeother.getSelectedItemPosition() == 0) {
                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.ValidateUOM));
                    return false;
                }

            }
        }
        if (getadvace.getSelectedItemPosition()==0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validateadvace));
            return false;
        }

        if (getadvace.getSelectedItemPosition()==1) {
            if (paymentperiod.getSelectedItemPosition()==0) {
                showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatepaymentperiod));
                return false;
            } else if (paymentperiod.getSelectedItemPosition()== 4) {
                if (pleasespecipy.getText().toString().matches("")) {
                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatespecify));
                    return false;
                }
            } else if( payment_mode.getSelectedItemPosition() == 0) {
                showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatepaymentmode));
                return false;
            } else if (payment_mode.getSelectedItemPosition() == 3) {
                if (pleasespecipypaymentmode.getText().toString().matches("")) {
                    showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatespecify));
                    return false;
                }
            }
        }
        if (delaypayment.getSelectedItemPosition()==0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatewaitingcharges));
            return false;
        } else if (delaypayment.getSelectedItemPosition()==1) {

            if (chargetails.getText().toString().matches("")) {
                showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validatechargedetails));
                return false;
            }

        }  if (haveinterest.getSelectedItemPosition() == 0) {
            showDialog(Transport_service_questioner_vendor.this, getResources().getString(R.string.validateinterest));
            return false;
        }



        return true;
    }

    private boolean Validatevehiclecount() {


            boolean isNotEmpty = true; ;

            for (VehicleTypesWithCount vehiclecount: vehicletypewithcount_list) {

                    if(vehiclecount.getNoofvehicles() == 0  )
                    {
                        isNotEmpty = false;
                    }


            }
            return  isNotEmpty;

    }
    private boolean IsVallidAttachment() {
        boolean isvalid = false;
        for(int i =0; i <Attachment_List.size() ; i ++)
        {
            if(Attachment_List.get(i).getPrice() > 0)
            {
                isvalid= true;
            }
            if(Attachment_List.get(i).getUOMID() > 0)
            {
                isvalid= true;
            }
        }

        return  isvalid;
    }

    public  boolean ValidateHireCharges()
    {
        boolean isNotEmpty = true; ;

        for (Hirecharges_new hirecharges: hirechargeslist) {
            for(Hirecharges hirevalues :hirecharges.getHirecharge_details())
            {
                if(hirevalues.getPrice() == 0  )
                {
                    isNotEmpty = false;
                }
                if(hirevalues.getHirebasisid() == 0  )
                {
                    isNotEmpty = false;
                }
                if(hirevalues.getCcid() == 0  )
                {
                    isNotEmpty = false;
                }
            }
        }
        return  isNotEmpty;
    }


    public  boolean Validatepluder()
    {
        boolean isvalid = false;
        for(int i =0; i <attachmentlist.size() ; i ++)
        {
            if(attachmentlist.get(i).getPrice() > 0)
            {
                isvalid= true;
            }
            if(attachmentlist.get(i).getUOMID() > 0)
            {
                isvalid= true;
            }
        }

        return  isvalid;

    }
    private void Getdistricts(int state_id) {
        mdilogue.show();
        get_district.clear();
        get_district_Id.clear();
        get_district = new ArrayList<String>();
        get_district_Id = new ArrayList<Integer>();
        selected_districts = new ArrayList<>();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getdistricts(APIConstantURL.GetDistrictsByStateId + state_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetDistricts>() {
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
                    public void onNext(GetDistricts getDistricts) {

                        if (getDistricts.getListResult() != null) {
                            // get_district.add("Select District");
                            for (GetDistricts.ListResult data : getDistricts.getListResult()
                            ) {
                                get_district.add(data.getName());
                                get_district_Id.add(data.getId());
                            }

//
                            districts.setItems(get_district);

                        } else {
                            Log.e("nodada====", "nodata===custom2");
                            get_district.add("No District Available");
                        }

                    }

                });
    }


    private void GetStates() {
        mdilogue.show();

        get_state_Id.clear();

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getstates(APIConstantURL.GetStatesByCountryId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetStates>() {
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
                    public void onNext(GetStates getStates) {

                        if (getStates.getListResult() != null) {
                            // Clearing state priority Data

                            get_state = new ArrayList<>();
                            get_state_Id = new ArrayList<Integer>();

                            get_state.add(new TypeItem(0, "Select State"));
                            for (GetStates.ListResult data : getStates.getListResult()
                            ) {
                                get_state.add(new TypeItem(data.getId(), data.getName()));
                                get_state_Id.add(data.getId());
                                get_state_code.add(data.getCode());
                            }

//

                            stateAdapter = new SpinnerTypeArrayAdapter(Transport_service_questioner_vendor.this, get_state);
                            stateAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            spin_state.setAdapter(stateAdapter);

                            spin_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                    selected_state = spin_state.getItemAtPosition(spin_state.getSelectedItemPosition()).toString();


                                    if (position != 0) {
                                        state_id = get_state_Id.get(spin_state.getSelectedItemPosition() - 1);

                                        Log.e("state_id=========>",state_id+"");
                                        Statecode= get_state_code.get(spin_state.getSelectedItemPosition() - 1);
                                        //   stateupdate();
                                        Getdistricts(state_id);

                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    // DO Nothing here
                                }
                            });

                        } else {
                            Log.e("nodada====", "nodata===custom2");
                            // get_state.add(0,"No State Available");

                        }

                    }

                });


    }

    private void haveinterestyesorno() {
        ArrayList<spinneritemmodel> list = new ArrayList<>();

        list.add(new spinneritemmodel(0, "Please Select"));
        list.add(new spinneritemmodel(1,"Yes"));
        list.add(new spinneritemmodel(2,"No"));
        adapter = new SpinnerArrayAdapter(Transport_service_questioner_vendor.this, list);
        adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        haveinterest.setAdapter(adapter);
        haveinterest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectAppinterest = haveinterest.getItemAtPosition(haveinterest.getSelectedItemPosition()).toString();

                if (haveinterest.getSelectedItemPosition() == 1) {

                    appinstalled = true;
//
                } else {
                    appinstalled = false;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }

    private void waitingchargesyesorno() {
        ArrayList<spinneritemmodel> list = new ArrayList<>();

        list.add(new spinneritemmodel(0, "Please Select"));
        list.add(new spinneritemmodel(1,"Yes"));
        list.add(new spinneritemmodel(2,"No"));
        adapter = new SpinnerArrayAdapter(Transport_service_questioner_vendor.this, list);
        adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        delaypayment.setAdapter(adapter);
        delaypayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_waitingcharge = delaypayment.getItemAtPosition(delaypayment.getSelectedItemPosition()).toString();
                if (delaypayment.getSelectedItemPosition()==1) {
                    chargedetails.setVisibility(View.VISIBLE);
                    havewaitcharges = true;
//
                } else {
                    havewaitcharges = false;
                    chargedetails.setVisibility(View.GONE);
                    chargetails.setText("");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }

    // Cash / Bank Transfer
    private void paymentmode() {



        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.gettypecddmtdata(APIConstantURL.GetTypeCDdmt + 18)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTypeCdDmt>() {
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
                    public void onNext(GetTypeCdDmt getTypeCdDmt) {


                        if (getTypeCdDmt.getListResult() != null) {
                            get_paymenttype = new ArrayList<>();
                            get_paymenttype.add(new TypeItem(0, "Please Select"));

                            for (GetTypeCdDmt.ListResult data : getTypeCdDmt.getListResult()
                            ) {
                                get_paymenttype.add(new TypeItem(data.getTypeCdId(), data.getDesc()));
                            }

                            paymenttypeAdapter = new SpinnerTypeArrayAdapter(Transport_service_questioner_vendor.this, get_paymenttype);
                            paymenttypeAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            payment_mode.setAdapter(paymenttypeAdapter);
                            payment_mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    selected_paymentmode = payment_mode.getItemAtPosition(payment_mode.getSelectedItemPosition()).toString();
                                    paymentModeTypeItem = (TypeItem) payment_mode.getSelectedItem();
                                    if (payment_mode.getSelectedItemPosition() == 3) {
                                        othervepaymentmodelinear.setVisibility(View.VISIBLE);
                                    } else {
                                        othervepaymentmodelinear.setVisibility(View.GONE);
                                        pleasespecipypaymentmode.setText("");
                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    // DO Nothing here
                                }
                            });

                        }

                    }


                });
    }


    private void payment_period() {


        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.gettypecddmtdata(APIConstantURL.GetTypeCDdmt + 20)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTypeCdDmt>() {
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
                    public void onNext(GetTypeCdDmt getTypeCdDmt) {


                        if (getTypeCdDmt.getListResult() != null) {


                            get_paymentperiod = new ArrayList<>();
                            get_paymentperiod.add(new TypeItem(0, "Please Select"));


                            for (GetTypeCdDmt.ListResult data : getTypeCdDmt.getListResult()
                            ) {
                                get_paymentperiod.add(new TypeItem(data.getTypeCdId(), data.getDesc()));
                            }

                            paymentperiodAdapter = new SpinnerTypeArrayAdapter(Transport_service_questioner_vendor.this, get_paymentperiod);
                            paymentperiodAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            paymentperiod.setAdapter(paymentperiodAdapter);
                            paymentperiod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                    paymentperiodTypeItem = (TypeItem) paymentperiod.getSelectedItem();

                                    selected_paymentperiod = paymentperiod.getItemAtPosition(paymentperiod.getSelectedItemPosition()).toString();

                                    if (paymentperiod.getSelectedItemPosition() == 4) {
                                        otherlinear.setVisibility(View.VISIBLE);
                                    } else {
                                        otherlinear.setVisibility(View.GONE);
                                        pleasespecipy.setText("");

                                    }


                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    // DO Nothing here
                                }
                            });


                        }
                    }


                });
    }

    private void getadvanceyesorno() {

        ArrayList<spinneritemmodel> list = new ArrayList<>();

        list.add(new spinneritemmodel(0, "Please Select"));
        list.add(new spinneritemmodel(1,"Yes"));
        list.add(new spinneritemmodel(2,"No"));
        adapter = new SpinnerArrayAdapter(Transport_service_questioner_vendor.this, list);
        adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        getadvace.setAdapter(adapter);
        getadvace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Selected_getadvace = getadvace.getItemAtPosition(getadvace.getSelectedItemPosition()).toString();
                if (getadvace.getSelectedItemPosition()==1) {
                    getAdvaced = true;
                    yesadvancelinear.setVisibility(View.VISIBLE);
                } else {
                    getAdvaced = false;
                    yesadvancelinear.setVisibility(View.GONE);
                    payment_mode.setSelection(0);
                    paymentperiod.setSelection(0);
                }

                //   Log.e("=============>",get_hiringbasisid.get(hiring_basics.getSelectedItemPosition()-1)+"");

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

    }

    //<  Drop Down - Per Trip / Per MT / Per KM >
    private void hiringbasis() {
        mdilogue.show();
        get_hiringbasis.clear();
        get_hiringbasisid.clear();

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.gettypecddmtdata(APIConstantURL.GetTypeCDdmt + 16)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTypeCdDmt>() {
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
                    public void onNext(GetTypeCdDmt getTypeCdDmt) {


                        if (getTypeCdDmt.getListResult() != null) {
                            get_hiringbasis = new ArrayList<String>();
                            get_hiringbasisid = new ArrayList<Integer>();

                            gethirechargesdata = getTypeCdDmt;
                            //   get_hiringbasis.add("Select");

                        }
                    }
                });
    }

    private void vehicletype() {
        mdilogue.show();
        get_vehicletype.clear();
        get_vehicletypeid.clear();
        selected_vehicleids = new ArrayList<>();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.gettypecddmtdata(APIConstantURL.GetTypeCDdmt + 12)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTypeCdDmt>() {
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
                    public void onNext(GetTypeCdDmt getTypeCdDmt) {


                        if (getTypeCdDmt.getListResult() != null) {
                            get_vehicletype = new ArrayList<String>();
                            get_vehicletypeid = new ArrayList<Integer>();
                            for (GetTypeCdDmt.ListResult data : getTypeCdDmt.getListResult()
                            ) {
                                get_vehicletype.add(data.getDesc());
                                get_vehicletypeid.add(data.getTypeCdId());
                            }
                            vehicle_type.setItems(get_vehicletype);


                        } else {

                        }

                    }

                });


    }

    private void GetVillages(String madalids) {
        mdilogue.show();
        get_villages.clear();
        get_villages_Id.clear();
        selected_villagesids =  new ArrayList<>();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getvillages(Getvillages + madalids)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetVillages>() {
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
                    public void onNext(GetVillages getVillages) {

                        if (getVillages.getListResult() != null) {
                            get_villages = new ArrayList<String>();
                            get_villages_Id = new ArrayList<Integer>();
                            for (GetVillages.ListResult data : getVillages.getListResult()
                            ) {
                                get_villages.add(data.getName());
                                get_villages_Id.add(data.getId());
                            }

//
//                            ArrayAdapter aa = new ArrayAdapter(Transport_service_questioner_vendor.this,android.R.layout.simple_spinner_item,get_villages);
//                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                            villages.setAdapter(aa);
                            villages.setItems(get_villages);

                        } else {
                            get_villages.add("No Villages Available");
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });

    }

    private void Getmandal(String selected_distids) {
        mdilogue.show();
        get_mandal.clear();
        get_mandal_Id.clear();
        selected_Mandalsids = new ArrayList<>();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getmandals(Getmandals + selected_distids)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetMandals>() {
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
                    public void onNext(GetMandals getMandals) {


                        if (getMandals.getListResult() != null) {
                            get_mandal = new ArrayList<String>();
                            get_mandal_Id = new ArrayList<Integer>();
                            for (GetMandals.ListResult data : getMandals.getListResult()
                            ) {
                                get_mandal.add(data.getName());
                                get_mandal_Id.add(data.getId());
                            }


                            mandal.setItems(get_mandal);

                        } else {
                            Log.e("nodada====", "nodata===custom2");
                            get_mandal.add("No Mandal Available");
                        }

                    }

                });
    }


    private void FFBtransporthiringservice() {
        mdilogue.show();


        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.gettypecddmtdata(APIConstantURL.GetTypeCDdmt + 14)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTypeCdDmt>() {
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
                    public void onNext(GetTypeCdDmt getTypeCdDmt) {


                        if (getTypeCdDmt.getListResult() != null) {
                            get_hiringservice = new ArrayList<>();
                            get_hiringservice.add(new TypeItem(0, "Please Select"));


                            for (GetTypeCdDmt.ListResult data : getTypeCdDmt.getListResult()
                            ) { get_hiringservice.add(new TypeItem(data.getTypeCdId(), data.getDesc()));



                            }


                            hiringserviceTypeAdapter = new SpinnerTypeArrayAdapter(Transport_service_questioner_vendor.this, get_hiringservice);
                            hiringserviceTypeAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            hiringservice.setAdapter(hiringserviceTypeAdapter);

                            hiringservice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    SELECTEDhiringservice = hiringservice.getItemAtPosition(hiringservice.getSelectedItemPosition()).toString();
                                    hiringservicesTypeItem = (TypeItem) hiringservice.getSelectedItem();

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    // DO Nothing here
                                }
                            });

                        } else {

                        }

                    }

                });


    }

    private void yesorno() {
        ArrayList<spinneritemmodel> list = new ArrayList<>();

        list.add(new spinneritemmodel(0, "Please Select"));
        list.add(new spinneritemmodel(1,"Yes"));
        list.add(new spinneritemmodel(2,"No"));
        adapter = new SpinnerArrayAdapter(Transport_service_questioner_vendor.this, list);
        adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        haveownfactor.setAdapter(adapter);

        haveownfactor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               selected_factorzone = haveownfactor.getItemAtPosition(haveownfactor.getSelectedItemPosition()).toString();

                selectedfactorzone = (TypeItem) paymentperiod.getSelectedItem();

                if (haveownfactor.getSelectedItemPosition() == 1) {
                    factorzonesyes.setVisibility(View.VISIBLE);
                    factorzonesno.setVisibility(View.GONE);
                    isslected = true;
                    Name.setText("");
                    mobile.setText("");
                    address.setText("");

                } else if (haveownfactor.getSelectedItemPosition() == 2) {
                    factorzonesyes.setVisibility(View.GONE);
                    factorzonesno.setVisibility(View.VISIBLE);
                    isslected = false;
                    farmerId.setText("");
                    ownership.setSelection(0);
                } else {
                    factorzonesyes.setVisibility(View.GONE);
                    factorzonesno.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        owner_ship();
        spin_attachment.setAdapter(adapter);
        spin_attachment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                attachment_have = spin_attachment.getItemAtPosition(spin_attachment.getSelectedItemPosition()).toString();


                if (spin_attachment.getSelectedItemPosition() == 1) {
                    attachment.setVisibility(View.VISIBLE);
                    VehicleAttachment();
                    isattached = true;
//
                } else {
                    isattached = false;
                    attachment.setVisibility(View.GONE);
                    attachmentsublinear.setVisibility(View.GONE);
                    Attachvehiclecharges2.setText("");
                    Attachvehiclecharges.setText("");

                }
                Log.e("seleced_period===", selected_factorzone);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


    }

    private void VehicleAttachment() {


        mdilogue.show();
        get_vehicleattachment.clear();
        get_vehicleattachment_Id.clear();

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.gettypecddmtdata(APIConstantURL.GetTypeCDdmt + 23)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTypeCdDmt>() {
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
                    public void onNext(GetTypeCdDmt getTypeCdDmt) {


                        if (getTypeCdDmt.getListResult() != null) {

                            for (GetTypeCdDmt.ListResult data : getTypeCdDmt.getListResult()
                            ) {
                                get_vehicleattachment.add(data.getDesc());
                                get_vehicleattachment_Id.add(data.getTypeCdId());
                            }


                            Vehicle_attachment.setItems(get_vehicleattachment);


                        } else {
                            Log.e("nodada====", "nodata===custom2");
                            get_vehicleattachment.add("No Data Available");
                        }

                    }

                });

    }

    private void owner_ship() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.gettypecddmtdata(APIConstantURL.GetTypeCDdmt + 19)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTypeCdDmt>() {
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
                    public void onNext(GetTypeCdDmt getTypeCdDmt) {


                        if (getTypeCdDmt.getListResult() != null) {
                            get_ownership = new ArrayList<>();
                            get_ownership.add(new TypeItem(0, "Please Select"));


                            for (GetTypeCdDmt.ListResult data : getTypeCdDmt.getListResult()
                            ) { get_ownership.add(new TypeItem(data.getTypeCdId(), data.getDesc()));



                            }
                            ownershipAdapter = new SpinnerTypeArrayAdapter(Transport_service_questioner_vendor.this, get_ownership);
                            ownershipAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            ownership.setAdapter(ownershipAdapter);

                            ownership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    selectedownership = ownership.getItemAtPosition(ownership.getSelectedItemPosition()).toString();
                                    ownershipTypeItem = (TypeItem) ownership.getSelectedItem();
                                    if (FarmerCode == null || TextUtils.isEmpty(FarmerCode)) {
                                        resetSpinnerOwnVechile();
                                        if (i != 0) {
                                            showDialog(Transport_service_questioner_vendor.this, "Please Select Farmer");
                                        }

                                    }
                                }
                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    // DO Nothing here
                                }
                            });

                        } else {

                        }

                    }

                });


    }


    private void resetSpinnerOwnVechile() {
        ownership.setSelection(0);
        ownership.setEnabled(true);
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

    @Override
    public void selectedIndices(List<Integer> indices) {



        selected_districtsids = new ArrayList<>();
        Log.d(TAG, "---- analysis ---- > get selected Dist ID :" + selected_distids);
if(indices != null && indices.size() >0)
{
    for (Integer values : indices) {
        Log.d(TAG, "---- analysis ---- > get selected labour ID :" + get_district_Id.get(values));

        selected_districtsids.add(get_district_Id.get(values));
        Log.d(TAG, "---- analysis ---- > get selected labour ID :" + selected_districtsids);
    }

    selected_distids = arrayTOstring(selected_districtsids);

    Getmandal(selected_distids);

}

    }

    @Override
    public JsonObject selectedStrings(List<String> strings) {

        selected_districts.clear();
        for (int i = 0; i < strings.size(); i++) {
            String name = strings.get(i);

            if (!selected_districts.contains(name))
                selected_districts.add(name);

            Log.d(TAG, "---- analysis ----764 > get selected labour name :" + selected_districts);

        }
        selected_distname = CommonUtil.arrayToString(strings);
        Log.d(TAG, "---- analysis ---- > get selected Dist names :" + selected_distname);
        return null;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            startActivity(new Intent(Transport_service_questioner_vendor.this, QRScanner_Activity.class));
        }
    }


    @Override
    public void selecteddIndices(List<Integer> indices) {


        selected_Mandalsids.clear();

        for (Integer values : indices) {
            Log.d(TAG, "---- analysis ---- > get selected mandal ID :" + get_mandal_Id.get(values));

            selected_Mandalsids.add(get_mandal_Id.get(values));
            Log.d(TAG, "---- analysis ---- > get selected mandal ID :" + selected_Mandalsids);
        }

        selected_mandalids = arrayTOstring(selected_Mandalsids);
        Log.d(TAG, "---- analysis ---- > get selected mandal ID :" + selected_mandalids);
        GetVillages(selected_mandalids);


    }


    @Override
    public JsonObject selecteddStrings(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            String name = strings.get(i);

            if (!selected_Mandals.contains(name))
                selected_Mandals.add(name);

            Log.d(TAG, "---- analysis ---- 794> get selected labour name :" + selected_Mandals);

        }
        return null;
    }

    @Override
    public void selectedIndicess(List<Integer> indices) {

        selected_villagesids.clear();

        for (Integer values : indices) {
            Log.d(TAG, "---- analysis ---- > get selected village ID :" + get_villages_Id.get(values));

            selected_villagesids.add(get_villages_Id.get(values));
            Log.d(TAG, "---- analysis ---- > get selected village ID :" + selected_villagesids);
        }

        selected_villageids = arrayTOstring(selected_villagesids);
        Log.d(TAG, "---- analysis ---- > get selected village size :" + selected_villagesids.size());

        Log.d(TAG, "---- analysis ---- > get selected Dvillageist ID :" + selected_villageids);

        vehicletype();
        vehicle_recycle.setVisibility(View.GONE);
        hirecharges.setVisibility(View.GONE);
    }

    @Override
    public JsonObject selectedStringss(List<String> strings) {
        // todo verify ids
        villagesList =new ArrayList<>();
        villagesList.clear();
        selected_villages.clear();
        for (int i = 0; i < strings.size(); i++) {
            String name = strings.get(i);
            if (!selected_villages.contains(name))
                selected_villages.add(name);

            Log.d(TAG, "---- analysis ---- 811> get selected village name :" + name);
            villagesList.add(new VillagesData(i, selected_villagesids.get(i),name));


        }

        if(hirechargesAdapter != null)
        {
            hirechargesAdapter = new HirechargesAdapter(Transport_service_questioner_vendor.this, villagesList, getdestinations, vehicletype_list,gethirechargesdata, Transport_service_questioner_vendor.this);
            hirecharges.setAdapter(hirechargesAdapter);

            hirechargesAdapter.notifyDataSetChanged();
        }

        // Log.d(TAG, "---- analysis ---- 811> get selected getdestinations name :"+ vehicletype_list.get(0).getVehicleid() );

        //     arrayList.add(new  farmer(item.getFarmerCode(),item.getContactNumber()));

        return null;
    }


    private JsonObject postsearchrequest(String s) {
        Farmersearchobject requestModel = new Farmersearchobject();
        requestModel.setSearchKey(s);
        requestModel.setId(null);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void selectedVAIndicess(List<Integer> indices) {
        selected_vehicleattachmentids.clear();

        for (Integer values : indices) {
            Log.d(TAG, "---- analysis ---- > get selected vehicleattachmentids :" + get_vehicleattachment_Id.get(values));

            selected_vehicleattachmentids.add(get_vehicleattachment_Id.get(values));
            Log.d(TAG, "---- analysis ---- > get selected vehicleattachmentids:" + selected_vehicleattachmentids);
        }

        selected_sttached_ids = arrayTOstring(selected_vehicleattachmentids);
//
        if(selected_vehicleattachmentids.size() != 0  && selected_vehicleattachmentids!= null){
            attachmentsublinear.setVisibility(View.VISIBLE);
        }
        else{
            attachmentsublinear.setVisibility(View.GONE);
            Attachvehiclecharges2.setText("");
            Attachvehiclecharges.setText("");
        }


        Log.d(TAG, "---- analysis ---- > get selected Dvillageist ID :" + selected_sttached_ids);
        if (selected_sttached_ids.contains("88")) {
            Ploughechargeslinear.setVisibility(View.VISIBLE);
            Plougherdata();
        } else {
            Ploughechargeslinear.setVisibility(View.GONE);
        }
        if (selected_sttached_ids.contains("89")) {

            Puddlerchargeslinear.setVisibility(View.VISIBLE);
            Puddlerdata();
        } else {
            Puddlerchargeslinear.setVisibility(View.GONE);
        }
        if (selected_sttached_ids.contains("87")) {
            chargeslinear.setVisibility(View.VISIBLE);

        } else {
            chargeslinear.setVisibility(View.GONE);
            Attachvehiclecharges.setText("");
        }
        if (selected_sttached_ids.contains("90")) {
            Sprayerchargeslinear.setVisibility(View.VISIBLE);
        } else {
            Sprayerchargeslinear.setVisibility(View.GONE);
            Attachvehiclecharges2.setText("");

        }

        if (selected_sttached_ids.contains("102")) {
            otherattachments.setVisibility(View.VISIBLE);
        } else {
            otherattachments.setVisibility(View.GONE);
            pleasespecipyattachment.setText("");
            othercharges.setText("");

        }

    }

    private void Puddlerdata() {
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.gettypecddmtdata(APIConstantURL.GetTypeCDdmt + 25)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTypeCdDmt>() {
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
                    public void onNext(GetTypeCdDmt getTypeCdDmt) {


                        int Plougher = 89;

                        if (getTypeCdDmt.getListResult() != null) {
                            puddlerAdapter = new PuddlerAdapter(Transport_service_questioner_vendor.this, getTypeCdDmt.getListResult(), Plougher, AttachmentChargesdata,Transport_service_questioner_vendor.this);
                            Puddlerrecycle.setAdapter(puddlerAdapter);

                        }
                    }

                });
    }


    private void Plougherdata() {
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.gettypecddmtdata(APIConstantURL.GetTypeCDdmt + 24)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetTypeCdDmt>() {
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
                    public void onNext(GetTypeCdDmt getTypeCdDmt) {


                        int Plougher = 88;

                        if (getTypeCdDmt.getListResult() != null) {
                            plougherAdapter = new PlougherAdapter(Transport_service_questioner_vendor.this, getTypeCdDmt.getListResult(), Plougher,AttachmentChargesdata, Transport_service_questioner_vendor.this);
                            Plougherrecycle.setAdapter(plougherAdapter);

                        }
                    }

                });
    }


    @Override
    public JsonObject selectedVAStringss(List<String> strings) {

        selected_vehicleattachment.clear();
        attachmentlist.clear();
        Attachment_List.clear();
        for (int i = 0; i < strings.size(); i++) {
            String name = strings.get(i);
            if (!selected_vehicleattachment.contains(name))
                selected_vehicleattachment.add(name);
            //  vehicletype_list.add(new VehicleTypes(i, selected_vehicleids.get(i), selected_Vehicles.get(i)));


        }
        if(selected_vehicleattachment.size()!=0&& selected_vehicleattachment!=null){
            attachmentsublinear.setVisibility(View.VISIBLE);
        }
        else{
            attachmentsublinear.setVisibility(View.GONE);


        }

        return null;
    }

    @Override
    public void selectedVIndicess(List<Integer> indices) {
        selected_vehicleids.clear();

        for (Integer values : indices) {
            Log.d(TAG, "---- analysis ---- > get selected vehicletype ID :" + get_vehicletypeid.get(values));

            selected_vehicleids.add(get_vehicletypeid.get(values));

        }
        if (selected_vehicleids.contains(51)) {
            othervehiclelinear.setVisibility(View.VISIBLE);
        } else {
            othervehiclelinear.setVisibility(View.GONE);
            pleasespecipyvehicle.setText("");
        }
        Log.d(TAG, "---- analysis ---- > get selected vehicle ID :" + selected_vehicleids);
        //   selected_sttached_ids = arrayTOstring(selected_vehicleattachmentids);


    }

    @Override
    public JsonObject selectedVStringss(List<String> strings) {
        vehicletype_list.clear();
        selected_Vehicles.clear();
        for (int i = 0; i < strings.size(); i++) {
            String name = strings.get(i);
            if (!selected_Vehicles.contains(name))
                selected_Vehicles.add(name);
            vehicletype_list.add(new VehicleTypes(i, selected_vehicleids.get(i), selected_Vehicles.get(i)));


        }
        if(selected_Vehicles.size()!=0&& selected_Vehicles!=null){
            vehicle_recycle.setVisibility(View.VISIBLE);
            hirecharges.setVisibility(View.VISIBLE);
        }

        vehicletypeadapter = new vehicltypeadapter(Transport_service_questioner_vendor.this, vehicletype_list, Transport_service_questioner_vendor.this);
        vehicle_recycle.setAdapter(vehicletypeadapter);
        // main recycleview
        hirechargesAdapter = new HirechargesAdapter(Transport_service_questioner_vendor.this, villagesList, getdestinations, vehicletype_list, gethirechargesdata,Transport_service_questioner_vendor.this);
        hirecharges.setAdapter(hirechargesAdapter);
        hirechargesAdapter.notifyDataSetChanged();


        return null;
    }


    @Override
    public void onsSelected(int po, ArrayList<Hirecharges_new> hirecharges) {
        hirechargeslist = hirecharges;

        for (int j =0; j <hirecharges.size(); j++) {

            for(int i =0; i <hirecharges.get(j).getHirecharge_details().size(); i++)
            {
                Log.d("---Analysis start--","------------------------------------------------------------------------------");
                Log.d("---Analysis--","===> Main Loop index :"+j +"===>  sub Loop index :"+i);
                Log.d("---Analysis--","===> price :"+hirecharges.get(j).getHirecharge_details().get(i).getPrice());
                Log.d("---Analysis--","===> CC_ID :"+hirecharges.get(j).getHirecharge_details().get(i).getCcid());
                Log.d("---Analysis--","===> vehicleid :"+hirecharges.get(j).getHirecharge_details().get(i).getVehicleid());
                Log.d("---Analysis--","===> villageid :"+hirecharges.get(j).getHirecharge_details().get(i).getVillage_id());
                Log.d("---Analysis--","===> optccd :"+hirecharges.get(j).getHirecharge_details().get(i).getOptccid());
                Log.d("---Analysis--","===>optprice :"+hirecharges.get(j).getHirecharge_details().get(i).getOptPrice());
                //     Log.d("---Analysis--","===> villageid :"+hirecharges.get(j).getHirecharge_details().get(i).getv());
                Log.d("---Analysis--end","------------------------------------------------------------------------------");

            }



        }




    }


    @Override
    public void onEnter(int pos, ArrayList<Attachments> attachment, int attached_id) {
        attachmentlist = attachment;
        Attachment_ID = attached_id;
        for (Attachments attached : attachment) {

            Attachedis = attached.getAttached_id();
            Amount = attached.getPrice();
            Log.d(TAG, "----- Analysis ==> onEnter() values attached_id :" + Attachedis + "Price :" + Amount);
        }
        for(int i =0; i <attachment.size(); i++)
        {
            Log.d("---Analysis start--","------------------------------------------------------------------------------");

            Log.d("---Analysis--","===> price Pluder :"+attachment.get(i).getPrice());
            Log.d("---Analysis--","===> id Pluder:"+attachment.get(i).getAttachmentID());

            Log.d("---Analysis--end","------------------------------------------------------------------------------");

        }
        Log.d(TAG, "----- Analysis ==> onEnter() values attached_id2080 :" + attachmentlist.size());
    }

    @Override
    public void onSelectedd(int pos, ArrayList<Attachments> attachment, int attached_id) {
        Attachment_List = attachment;
        Attachment_ID = attached_id;
        for (Attachments attached : attachment) {

            Attachedis = attached.getAttached_id();
            Amount = attached.getPrice();
            Log.d(TAG, "----- Analysis ==> onSelectedd() values attached_id :" + Attachedis + "Price :" + Amount);
        }

        for(int i =0; i <attachment.size(); i++)
        {
            Log.d("---Analysis start--","------------------------------------------------------------------------------");

            Log.d("---Analysis--","===> price plouser :"+attachment.get(i).getPrice());
            Log.d("---Analysis--","===> id plouser :"+attachment.get(i).getAttachmentID());

            Log.d("---Analysis--end","------------------------------------------------------------------------------");

        }
        Log.d(TAG, "----- Analysis ==> onEnter() values attached_id 2093:" + Attachment_List.size());
    }

    @Override
    public void OnVehiclecount(int pos, ArrayList<VehicleTypesWithCount> vehiclecountlist) {

        vehicletypewithcount_list = vehiclecountlist;

        for (VehicleTypesWithCount Vehicledata : vehiclecountlist) {

            selected_Vehicleid = Vehicledata.getVehicleid();
            Vehiclecount = Vehicledata.getNoofvehicles();
            Log.d(TAG, "----- Analysis ==> OnVehiclecount() values attached_id :" + selected_Vehicleid + "Price :" + Vehiclecount);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                QRstring = data.getStringExtra("keyName");
                Log.e("===========> QRstring ",QRstring);

                farmerId.setText(QRstring);
            }
        }}
    private  void  stateupdate(){
        get_district = new ArrayList<>();
        get_district_Id = new ArrayList<>();

        districts.resetSpinner();

        get_mandal = new ArrayList<>();
        get_mandal_Id = new ArrayList<>();

        mandal.resetSpinner();
    }
}
