package in.calibrage.palm360fa.Views.Activities;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Adapter.GodownListAdapter;
import in.calibrage.palm360fa.Adapter.fert_producut_Adapter;
import in.calibrage.palm360fa.Model.FarmerOtpResponceModel;
import in.calibrage.palm360fa.Model.FarmerResponceModel;
import in.calibrage.palm360fa.Model.FertRequest;
import in.calibrage.palm360fa.Model.FertResponse;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.MSGmodel;
import in.calibrage.palm360fa.Model.PaymentsType;
import in.calibrage.palm360fa.Model.SelectedProducts;
import in.calibrage.palm360fa.Model.SubsidyResponse;
import in.calibrage.palm360fa.Model.product;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.CommonUtil;
import in.calibrage.palm360fa.common.Constants;
import in.calibrage.palm360fa.common.PinEntryEditText;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EdibleOilsubmitscreen extends BaseActivity {
    //region variables
    public static final String TAG = EdibleOilsubmitscreen.class.getSimpleName();
    /*
     *
     * this is shows godows and payment process also
     * */
    Spinner paymentspin;
    List<Integer> payment_id = new ArrayList<Integer>();
    private Context ctx;
    private Button btn_submit, button;
    private RecyclerView lst_godown_list;
    private LinearLayoutManager linearLayoutManager;

    private TextView txt_select_godown, txt_Payment_mode, text_amount, Final_amount, gst_amount, subsidy_amount,
            paybleamount,sgst_amount,cgst_amount,transamount,tsgst_amount,tcgst_amount,totaltransportamount;
    private BottomSheetBehavior behavior;
    double products_amount,transport_AmountwithoutGst;
    private Toolbar toolbar;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    private GodownListAdapter adapter;
    Integer RequestType;
    int productspay_amount,TransportAmount;
    ArrayList<Double> gstvalues = new ArrayList<Double>();
    ArrayList<Double> transgstvalues = new ArrayList<Double>();

    String product_name, selected_name;
    String Farmer_code, formattedDate, Godown_name,Godowncode;
    ImageView home_btn;
    Integer GodownId, quantity;
    String mobile_number;
    DecimalFormat dec = new DecimalFormat("####0.00");
    List<String> listdata = new ArrayList<>();
    LoginResponse created_user;
    private Integer User_id;
    private FarmerOtpResponceModel catagoriesList;
    Integer Cluster_id;
    int productsamount,   Totalgst;
    private ArrayList<product> product_List = new ArrayList<>();
    private String final_amount, only_amount;
    int mealTotal = 0;
    String Gst_sum, Amount_, include_gst_amount,Transport_amount;
    Integer Paymode, Statusid;
    private fert_producut_Adapter mAdapter;
    RecyclerView recycler_view_products;
    PaymentsType paymentsTypes;
    double payble_amount, remaining_subsidy_amountt;
    double Subsidy_amount, subsidy_amountt;
    double Gst_total,TransGsttotal;
    //  double transportamount;
    //endregion
    private List<String> selected_list = new ArrayList<String>();
    String state_name;
    private PinEntryEditText pinEntry;
    Button dialogButton;
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
        setContentView(R.layout.activity_edible_oilsubmitscreen);
        init();
        setviews();
        settoolbar();

    }

    private void init() {
        ctx = this;
        paymentspin = (Spinner) findViewById(R.id.paymentSpinner);
        recycler_view_products = (RecyclerView) findViewById(R.id.products_recy);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view_products.setLayoutManager(mLayoutManager);
        btn_submit = findViewById(R.id.btn_submit);
        //txt_select_godown = findViewById(R.id.txt_select_godown);
        txt_Payment_mode = findViewById(R.id.txt_Payment_mode);

        subsidy_amount = findViewById(R.id.subcdamount);
        paybleamount = findViewById(R.id.paybleamount);
        //         sw_paymentMode = findViewById(R.id.sw_paymentMode);

        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
//        txt_select_godown.setText(CommonUtil.getMultiColourString(getString(R.string.select_godown)));
//        txt_Payment_mode.setText(CommonUtil.getMultiColourString(getString(R.string.payment_mode)));
        text_amount = (TextView) findViewById(R.id.amount);
        transamount = (TextView) findViewById(R.id.transamount);
        Final_amount = (TextView) findViewById(R.id.final_amount_gst);
        gst_amount = (TextView) findViewById(R.id.gst_amount);
        sgst_amount =(TextView) findViewById(R.id.sgst_amount);
        tcgst_amount =(TextView) findViewById(R.id.tcgst_amount);
        tsgst_amount =(TextView) findViewById(R.id.tsgst_amount);
        cgst_amount =(TextView) findViewById(R.id.cgst_amount);
        totaltransportamount= (TextView)findViewById(R.id.totaltransportamount);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "---------- Finish ----------------");
                Intent intent = new Intent(EdibleOilsubmitscreen.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "").trim();
        if (isOnline()) {
            getPaymentMods();
            getFertilizerSubsidies();

        } else {
            showDialog(EdibleOilsubmitscreen.this, getResources().getString(R.string.Internet));

        }

//        sw_paymentMode.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
//            @Override
//            public void onSwitch(int position, String tabText) {
//                Paymode = paymentsTypes.getListResult().get(position).getTypeCdId();
//                String name = paymentsTypes.getListResult().get(position).getDesc();
//                if (name.contains("Cash")) {
//                    Statusid = 16;
//                }
//                if (name.contains("Against FFB")) {
//                    Statusid = 15;
//                }
//
//                Log.d(TAG, "------------ analysis -------- >> Mahesh :" + Paymode);
//                Log.d(TAG, "------------ analysis -------- >> Mahesh :" + name);
//
//            }
//        });

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
                        showDialog(EdibleOilsubmitscreen.this, getString(R.string.server_error));
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

                            ArrayAdapter aa = new ArrayAdapter(EdibleOilsubmitscreen.this, R.layout.spinner_itemm, listdata);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            paymentspin.setAdapter(aa);
                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }
                });
    }

    @SuppressLint("LongLogTag")
    private void setviews() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            include_gst_amount = extras.getString("Total_amount");
            Transport_amount = extras.getString("Transport_amount");
            GodownId = extras.getInt("godown_id");
            Godowncode = extras.getString("godown_code");
            Godown_name = extras.getString("godown_name");
        }
        catagoriesList = SharedPrefsData.getCatagories(this);
        if (null != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId() && 0 != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId())
            Cluster_id =  catagoriesList.getResult().getFarmerDetails().get(0).getClusterId();

        state_name = catagoriesList.getResult().getFarmerDetails().get(0).getStateName();
        Log.e("Cluster_id===",Cluster_id+"");
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formattedDate = df.format(c.getTime());
        for (int i = 0; i < SharedPrefsData.getFertCartData(this).size(); i++) {
            double gst = SharedPrefsData.getFertCartData(this).get(i).getGst();
            double tgst = SharedPrefsData.getFertCartData(this).get(i).getTransgst();
            Double amount_product = SharedPrefsData.getFertCartData(this).get(i).getAmount();
            int quantity = SharedPrefsData.getFertCartData(this).get(i).getQuandity();
            Double transportamountbyproduct = SharedPrefsData.getFertCartData(this).get(i).getTranportPrice();
            double totalPrice =quantity * amount_product;
            Log.e("totalPrice===",totalPrice+"");
            double gstPrice = totalPrice - totalPrice / (1 + (gst/ 100));
            Log.e("gstPrice===",gstPrice+"");
            double BasePrice = totalPrice - gstPrice;
            Log.e("BasePrice===",BasePrice+"");

            gstvalues.add(gstPrice);
            Gst_total = CommonUtil.sum(gstvalues);

            gst_amount.setText(dec.format(Gst_total));
            Log.e("Totalgst===",Gst_total+"");
            double cgst = (double) Gst_total/2;
            Log.e("Totalgst===",cgst+"");
            sgst_amount.setText(dec.format(cgst));
            cgst_amount.setText(dec.format(cgst));

            Log.e("percentage_value===", String.valueOf(gstvalues));
            double   transportamount =quantity * transportamountbyproduct;
            Log.e("transportamount===",transportamount+"");
            double transgstPrice = transportamount - transportamount / (1 + (tgst/ 100));
            Log.e("transgstPrice===",transgstPrice+"");
            double  transportpricewithoutgst = transportamount - transgstPrice;
            Log.e("transportpricewithoutgst===",transportpricewithoutgst+"");
            transgstvalues.add(transgstPrice);
            TransGsttotal = CommonUtil.sum(transgstvalues);
            Log.e("transgst_Sum===", String.valueOf(TransGsttotal));


            double tcgst = (double) TransGsttotal/2;
            Log.e("Totalgst===",cgst+"");
            tsgst_amount.setText(dec.format(tcgst));
            tcgst_amount.setText(dec.format(tcgst));
            //    transamount.setText(dec.format(transportpricewithoutgst));
            //    totaltransportamount.setText(dec.format(transportamount));
            // include_gst_amount = Gst_sum + Amount_;
            Log.e("gst_Sum===", String.valueOf(Gst_total));
            //  Totalgst =( int)Math.round(Gst_total);


        }

        Final_amount.setText(include_gst_amount);
        DecimalFormat dff = new DecimalFormat("####0.00");
        DecimalFormat form = new DecimalFormat("0.00");

        // Final_amount.setText(""+form.format( include_gst_amount));

//        double products_amount =Double.parseDouble(include_gst_amount)- Double.parseDouble(String.valueOf(Gst_total));
//        Log.e("products_amount===", String.valueOf(products_amount));
//        text_amount.setText("" + products_amount);

        products_amount = Double.parseDouble(include_gst_amount) - Double.parseDouble(String.valueOf(Gst_total));
        Log.e("products_amount===", String.valueOf(products_amount));
        transport_AmountwithoutGst =Double.parseDouble(Transport_amount) - Double.parseDouble(String.valueOf(TransGsttotal));
        // productsamount = ( int)Math.round(products_amount);
        text_amount.setText(dec.format(products_amount));
        transamount.setText(dec.format(transport_AmountwithoutGst));
        totaltransportamount.setText(Transport_amount);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();


            }
        });
        mAdapter = new fert_producut_Adapter(this, SharedPrefsData.getFertCartData(this));
        recycler_view_products.setAdapter(mAdapter);
//        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                // React to state change
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                // React to dragging events
//            }
//        });
        payble_amount =  Double.parseDouble(include_gst_amount) + Double.parseDouble(String.valueOf(Transport_amount));
//        productspay_amount= ( int)Math.round(payble_amount);
//        TransportAmount     =  ( int)Math.round(Double.parseDouble(Transport_amount));
//        Log.e("products_amount===", String.valueOf(productspay_amount));
//        Log.e("TransportAmount===", String.valueOf(TransportAmount));
        paybleamount.setText(dec.format(payble_amount));

        paymentspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleced_payment = paymentspin.getItemAtPosition(paymentspin.getSelectedItemPosition()).toString();
                Log.e("seleced_payment==", seleced_payment);


//            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }
    //Fertilizer submit  API Requests
    private void FertilizerRequest() {
        mdilogue.show();
        btn_submit.setEnabled(false);
        btn_submit.setBackgroundResource(R.drawable.button_bg_disable);
        JsonObject object = fertReuestobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.postfert(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FertResponse>() {
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
                        //    showDialog(Fert_godown_list.this, getString(R.string.endusermsgnew));
                    }

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onNext(FertResponse fertResponse) {

                        if (fertResponse.getIsSuccess()) {
                            mdilogue.dismiss();
                            btn_submit.setEnabled(false);

                            // Toast.makeText(getApplicationContext(), "sucess", Toast.LENGTH_SHORT).show();

                            new Handler().postDelayed(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.M)
                                @Override
                                public void run() {

                                    List<MSGmodel> displayList = new ArrayList<>();

                                    List<FertRequest.RequestProductDetail> req_products = new ArrayList<>();

                                    for (int i = 0; i < SharedPrefsData.getFertCartData(EdibleOilsubmitscreen.this).size(); i++) {


                                        product_name = SharedPrefsData.getFertCartData(getApplicationContext()).get(i).getProductname();
                                        quantity = SharedPrefsData.getFertCartData(getApplicationContext()).get(i).getQuandity();
                                        selected_list.add(product_name + " : " + quantity + "");
                                        selected_name = arrayyTOstring(selected_list);
                                    }
                                    displayList.add(new MSGmodel(getString(R.string.Godown_name), Godown_name));
                                    displayList.add(new MSGmodel(getString(R.string.product_quantity), selected_name));

                                    displayList.add(new MSGmodel(getResources().getString(R.string.amount), dec.format(products_amount)));
                                    displayList.add(new MSGmodel(getResources().getString(R.string.gst_amount),dec.format(Gst_total)));

                                    displayList.add(new MSGmodel(getResources().getString(R.string.total_amt), include_gst_amount));
                                    displayList.add(new MSGmodel(getResources().getString(R.string.transamount),  dec.format(transport_AmountwithoutGst)));
                                    displayList.add(new MSGmodel(getResources().getString(R.string.transgst),dec.format(TransGsttotal)));

                                    displayList.add(new MSGmodel(getResources().getString(R.string.totaltransportcost), Transport_amount));
                                    //Double subAmount = subsidy_amountt- include_gst_amount dec.format(Gst_total)
                                    displayList.add(new MSGmodel(getResources().getString(R.string.subcd_amt), dec.format(Math.round(Subsidy_amount))));



                                    displayList.add(new MSGmodel(getResources().getString(R.string.amount_payble),paybleamount.getText().toString()));

//products_amount
//                                    Log.d(TAG, "------ analysis ------ >> get selected_name in String(): " + selected_name);

                                    showSuccessDialog(displayList, getString(R.string.success_edible));
                                    saveEmptyCartItems();
                                }
                            }, 300);
                        }
                        else {
                            //    showDialog(Fert_godown_list.this, getString(R.string.endusermsg));
                            showDialog(EdibleOilsubmitscreen.this, getString(R.string.endusermsgnew));
                        }


                    }


                });


    }

    private void saveEmptyCartItems() {
        // Create an empty list of SelectedProducts
        ArrayList<SelectedProducts> emptyList = new ArrayList<>();

        // Save the empty list to SharedPreferences to clear the cart items
        SharedPrefsData.saveFertCartitems(this, emptyList);

        // Update myProductsList and CommonUtil.FertProductitems

        CommonUtil.FertProductitems = emptyList;
    }

    public String arrayyTOstring(List<String> arrayList) {
        StringBuilder string = new StringBuilder();
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (i == 0)
                    string.append("" + arrayList.get(i));
                else
                    string.append(","+ "\n" + arrayList.get(i));
            }
        }
        return string.toString();
    }

    private JsonObject fertReuestobject() {

        created_user = SharedPrefsData.getCreatedUser(this);
        String statecode = SharedPrefsData.getInstance(this).getStringFromSharedPrefs("statecode");
        Log.e("state===",statecode);
        User_id= created_user.getResult().getUserInfos().getId();
        FertRequest requestModel = new FertRequest();

        requestModel.setId(0);
        requestModel.setRequestTypeId(116);
        requestModel.setFarmerCode(Farmer_code);
        requestModel.setFarmerName(SharedPrefsData.getusername(this));
        requestModel.setPlotCode(null);
        requestModel.setRequestCreatedDate(formattedDate);
        requestModel.setIsFarmerRequest(true);
        requestModel.setCreatedByUserId(User_id);
        requestModel.setCreatedDate(formattedDate);
        requestModel.setUpdatedByUserId(User_id);
        requestModel.setUpdatedDate(formattedDate);
        requestModel.setGodownId(GodownId);
        requestModel.setPaymentModeType((payment_id.get(paymentspin.getSelectedItemPosition() - 1)));
        requestModel.setFileName(null);
        requestModel.setFileExtension(null);
        requestModel.setFileLocation(null);
        requestModel.setTotalCost(Double.valueOf(include_gst_amount));
        requestModel.setStateCode(statecode);
        requestModel.setStateName(state_name);
        requestModel.setSubcidyAmount(Double.valueOf(Subsidy_amount));
        requestModel.setPaybleAmount(Double.valueOf(paybleamount.getText().toString()));
        requestModel.setTransportPayableAmount(Double.valueOf(totaltransportamount.getText().toString()));
        requestModel.setComments(null);
        requestModel.setGodownCode(Godowncode);
        requestModel.setCropMaintainceDate(null);
        requestModel.setIssueTypeId(null);
        requestModel.setClusterId(Cluster_id);
        List<FertRequest.RequestProductDetail> req_products = new ArrayList<>();

        for (int i = 0; i < SharedPrefsData.getFertCartData(this).size(); i++) {


            FertRequest.RequestProductDetail products = new FertRequest.RequestProductDetail();
            products.setBagCost(SharedPrefsData.getFertCartData(this).get(i).getAmount());
            products.setGstPersentage(SharedPrefsData.getFertCartData(this).get(i).getGst());
            products.setProductId(SharedPrefsData.getFertCartData(this).get(i).getProductID());
            products.setQuantity(SharedPrefsData.getFertCartData(this).get(i).getQuandity());
            products.setSize((SharedPrefsData.getFertCartData(this).get(i).getSize()));
            products.setProductCode((SharedPrefsData.getFertCartData(this).get(i).getProduct_code()));
            products.setTransGstPercentage((SharedPrefsData.getFertCartData(this).get(i).getTransgst()));
            products.setTransporCost((SharedPrefsData.getFertCartData(this).get(i).getTranportPrice()));

            req_products.add(products);
        }


        requestModel.setRequestProductDetails(req_products);


        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private void settoolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Product Request");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //get Fertilizer Subsidies  API Requests
    private void getFertilizerSubsidies() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getsubsidy(APIConstantURL.Getfarmersubsidy + SharedPrefsData.getInstance(this).getStringFromSharedPrefs(Constants.USER_ID))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<SubsidyResponse>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.cancel();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mdilogue.cancel();
                    }

                    @Override
                    public void onNext(SubsidyResponse subsidyResponse) {
                        mdilogue.cancel();
                        if (subsidyResponse.getIsSuccess()) {
                            // subsidy_amount.setText(dec.format(subsidyResponse.getResult().getRemainingAmount()));
                            subsidy_amountt = subsidyResponse.getResult().getRemainingAmount();
                            subsidy_amount.setText(dec.format(Math.round(subsidyResponse.getResult().getRemainingAmount())));
                            Log.d(TAG, "-----analysis----->> Subsidy Amount : " + subsidy_amountt);
                            if (subsidy_amountt > 0 ) {
                                Log.d(TAG, "-----analysis----->> >0 Subsidy Amount : " + subsidy_amount);
                                if (Double.parseDouble(include_gst_amount) < subsidy_amountt) {
                                    remaining_subsidy_amountt = subsidy_amountt - Double.parseDouble(include_gst_amount);
                                    /*
                                     * nothing to pay
                                     * */
                                    payble_amount = 0.0;
                                    paybleamount.setText(dec.format(payble_amount));
                                    Subsidy_amount = Double.parseDouble(include_gst_amount);
                                    subsidy_amount.setText(dec.format(Subsidy_amount));
                                } else if (subsidy_amountt < Double.parseDouble(include_gst_amount)) {
                                    Double remaining_Amoubt = Double.parseDouble(include_gst_amount) - subsidy_amountt;
                                    Log.e("remaining_Amoubt===",remaining_Amoubt+"");
                                    Subsidy_amount =subsidy_amountt;
                                    payble_amount =remaining_Amoubt;
                                    paybleamount.setText(dec.format(Math.round(payble_amount)));
                                } else if (subsidy_amountt == 0.0 || subsidy_amountt < 0) {
                                    payble_amount = Double.parseDouble(include_gst_amount);
                                    paybleamount.setText(dec.format(include_gst_amount));
                                    Log.d(TAG, "-----analysis----->> == 0.0 Subsidy Amount : " + payble_amount);
                                }
                            }
                            else  {
                                Log.d(TAG, "-----analysis----->> < 557 Subsidy Amount : " + subsidy_amountt);
                                subsidy_amount.setText("0.00");
                                payble_amount = Double.parseDouble(include_gst_amount);
                                Log.d(TAG, "-----analysis----->> < 560 payble_amount  : " + payble_amount);
                                paybleamount.setText(dec.format(Math.round(payble_amount) + dec.format(Transport_amount)));
                            }

                        }
                    }

                });
    }

    public void onSubmit() {
        /*
         * validate Fealds
         *
         * */

        if (validations()) {
            if (isOnline()) {
                if (payment_id.get(paymentspin.getSelectedItemPosition() - 1) == 26) {
                    FertilizerOTPrequest();
                    otppopup();

                }
                else{
                    FertilizerRequest();
                }
            }
            else{
                showDialog(EdibleOilsubmitscreen.this, getResources().getString(R.string.Internet));


            }
        }
//        if (validations()) {
//
//            if (isOnline())
//
//                FertilizerRequest();
//            else {
//                showDialog(EdibleOilsubmitscreen.this, getResources().getString(R.string.Internet));
//
//            }
//        }

    }

    private void otppopup() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fertilizer_popup);
        //dialog.setCancelable(false);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView farmer_name = (TextView) dialog.findViewById(R.id.farmer_Code);
        pinEntry =  dialog.findViewById(R.id.txt_pin_entry);
        //String number = mobile_number.replaceAll("\\d(?=(?:\\D*\\d){4})", "*");
        farmer_name.setText(getString(R.string.otp_desc) + " " );

        //  ImageView image = (ImageView) dialog.findViewById(R.id.image);
        // image.setImageResource(R.drawable.ic_launcher);

        dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pinEntry.getText() != null & pinEntry.getText().toString().trim() != "" & !TextUtils.isEmpty(pinEntry.getText())) {
                    if (isOnline())
                        validatefertotp();
                    else {
                        showDialog(EdibleOilsubmitscreen.this, getResources().getString(R.string.Internet));
                    }
                } else {
                    showDialog(EdibleOilsubmitscreen.this, getResources().getString(R.string.ente_pin));
                    //pinEntry.setError("Please Enter Pin");
                }

                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void validatefertotp() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getFormerdetails(APIConstantURL.Farmer_otp + Farmer_code + "/" + pinEntry.getText().toString()+"/"+false)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FarmerOtpResponceModel>() {
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
                        showDialog(EdibleOilsubmitscreen.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final FarmerOtpResponceModel farmerOtpResponceModel) {
                        mdilogue.dismiss();
                        if (farmerOtpResponceModel.getIsSuccess()) {

                            FertilizerRequest();
                        }



                        else {
                            showDialog(EdibleOilsubmitscreen.this, farmerOtpResponceModel.getEndUserMessage());
                        }
                    }


                });

    }
    private void FertilizerOTPrequest() {
        {
            if (null != mdilogue)
                mdilogue.show();
            else {

                mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                        .setContext(this)
                        .setTheme(R.style.Custom)
                        .build();
                mdilogue.show();
            }
            ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
            mSubscription = service.getFormerOTP(APIConstantURL.Farmer_ID_CHECK + Farmer_code + "/" +"Fertilizer")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FarmerResponceModel>() {
                        @Override
                        public void onCompleted() {
                            mdilogue.cancel();
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
                            showDialog(EdibleOilsubmitscreen.this, getString(R.string.server_error));
                        }

                        @Override
                        public void onNext(FarmerResponceModel farmerResponceModel) {

                            Log.d(TAG, "onNext: " + farmerResponceModel);
                            if (farmerResponceModel.getIsSuccess()) {
                                mobile_number = farmerResponceModel.getResult();

                                Log.e("mobile_number======",mobile_number+"");
                            } else {
                                showDialog(EdibleOilsubmitscreen.this, getResources().getString(R.string.Invalid));
                            }

                        }
                    });


        }

    }


    private boolean validations() {


        if (paymentspin.getSelectedItemPosition() == 0 ) {

            showDialog(EdibleOilsubmitscreen.this, getResources().getString(R.string.paym_validation));
            return false;
        }
//        else if(Subsidy_amount == 0.0 && paybleamount.getText().toString().equalsIgnoreCase("0.00")){
//            showDialog(Fert_godown_list.this,  getResources().getString(R.string.Internetpay));
//            return false;
//        }
        return true;
    }

    //endregion
    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }
}
