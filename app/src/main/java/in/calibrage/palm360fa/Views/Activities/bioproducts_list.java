package in.calibrage.palm360fa.Views.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import in.calibrage.palm360fa.Adapter.producut_Adapter;
import in.calibrage.palm360fa.Model.ActiveGodownsModel;
import in.calibrage.palm360fa.Model.FarmerOtpResponceModel;
import in.calibrage.palm360fa.Model.FarmerResponceModel;
import in.calibrage.palm360fa.Model.FertRequest;
import in.calibrage.palm360fa.Model.FertResponse;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.MSGmodel;
import in.calibrage.palm360fa.Model.PaymentsType;
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

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class bioproducts_list extends BaseActivity {

    private Integer User_id;
    public static final String TAG = pole_godown_list.class.getSimpleName();
    /*
     *
     * this is shows godows and payment process also
     * */
    LoginResponse created_user;
    Spinner paymentspin;
    List<Integer> payment_id = new ArrayList<Integer>();
    private Context ctx;
    private Button btn_submit, button;
    private RecyclerView lst_godown_list;
    private LinearLayoutManager linearLayoutManager;

    private TextView txt_select_godown, txt_Payment_mode, text_amount, Final_amount, gst_amount,sgst_amount,cgst_amount;
    private BottomSheetBehavior behavior;
    double products_amount;
    private Toolbar toolbar;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    private GodownListAdapter adapter;
    Integer RequestType,Totalgst;

    ArrayList<Double> gstvalues = new ArrayList<Double>();

    String product_name,selected_name;
    String Farmer_code, formattedDate, Godown_name;
    ImageView home_btn;
    Integer GodownId,quantity;

    DecimalFormat dec = new DecimalFormat("####0.00");

    List<String> listdata = new ArrayList<>();


    private ActiveGodownsModel.ListResult selectedGodown;
    private ArrayList<product> product_List = new ArrayList<>();
    private String final_amount, only_amount;
    int mealTotal = 0;
    String Gst_sum, Amount_, include_gst_amount,Godowncode;
    Integer Paymode, Statusid;
    private producut_Adapter mAdapter;
    RecyclerView recycler_view_products;
    PaymentsType paymentsTypes;
    double payble_amount;
    double Subsidy_amount, subsidy_amountt;
    double Gst_total;
    private FarmerOtpResponceModel catagoriesList;
    Integer Cluster_id;
    String mobile_number,Statename;
    private PinEntryEditText pinEntry;
    Button dialogButton;
    //endregion
    private List<String> selected_list = new ArrayList<String>();

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
        setContentView(R.layout.activity_bioproducts_list);
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
        sgst_amount =(TextView) findViewById(R.id.sgst_amount);
        cgst_amount =(TextView) findViewById(R.id.cgst_amount);
        //  txt_select_godown = findViewById(R.id.txt_select_godown);
        txt_Payment_mode = findViewById(R.id.txt_Payment_mode);
        // lst_godown_list = findViewById(R.id.lst_godown_list);

        //     sw_paymentMode = findViewById(R.id.sw_paymentMode);
//        linearLayoutManager = new LinearLayoutManager(ctx);
//        lst_godown_list.setLayoutManager(linearLayoutManager);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
//        txt_select_godown.setText(CommonUtil.getMultiColourString(getString(R.string.select_godown)));
//        txt_Payment_mode.setText(CommonUtil.getMultiColourString(getString(R.string.payment_mode)));
        text_amount = (TextView) findViewById(R.id.amount);
        Final_amount = (TextView) findViewById(R.id.final_amount_gst);
        gst_amount = (TextView) findViewById(R.id.gst_amount);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "---------- Finish ----------------");
                Intent intent = new Intent(bioproducts_list.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");
        if (isOnline()) {
            getPaymentMods();

            //   getActiveGodowns();
        } else {
            showDialog(bioproducts_list.this, getResources().getString(R.string.Internet));

        }

    }
    //region API Requests
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
                        showDialog(bioproducts_list.this, getString(R.string.server_error));
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

                            ArrayAdapter aa = new ArrayAdapter(bioproducts_list.this, R.layout.spinner_itemm, listdata);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            paymentspin.setAdapter(aa);
                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }
                });
    }

    private void setviews() {
        catagoriesList = SharedPrefsData.getCatagories(this);

        if (null != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId() && 0 != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId())
            Cluster_id =  catagoriesList.getResult().getFarmerDetails().get(0).getClusterId();
        Log.e("Cluster_id===",Cluster_id+"");
        Statename = catagoriesList.getResult().getFarmerDetails().get(0).getStateName();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formattedDate = df.format(c.getTime());
        for (int i = 0; i < SharedPrefsData.getCartData(this).size(); i++) {
            double gst = SharedPrefsData.getCartData(this).get(i).getGst();

            Double amount_product = SharedPrefsData.getCartData(this).get(i).getAmount();
            int quantity = SharedPrefsData.getCartData(this).get(i).getQuandity();
            String quan = String.valueOf(quantity);
            //  mealTotal = amount_product * quantity;
            String product_amount = String.valueOf(mealTotal);

            double percentage = quantity * gst;

            Log.e("percentage_value===", String.valueOf(percentage));
            //  int k = (int)(product_amount*(percentage/100.0f));
            double totalPrice =quantity * amount_product;
            Log.e("totalPrice===",totalPrice+"");
            double gstPrice = totalPrice - totalPrice / (1 + (gst/ 100));
            Log.e("gstPrice===",gstPrice+"");
            double BasePrice = totalPrice - gstPrice;
            Log.e("BasePrice===",BasePrice+"");

            gstvalues.add(gstPrice);

            Log.e("percentage_value===", String.valueOf(gstvalues));
            Gst_total = CommonUtil.sum(gstvalues);

            // Totalgst =( int)Math.round(Gst_total);
//            gst_amount.setText(dec.format(Totalgst));
//            Log.e("Totalgst===",Totalgst+"");
            double cgst = (double) Gst_total/2;


            sgst_amount.setText(dec.format(cgst));
            cgst_amount.setText(dec.format(cgst));
        }
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            include_gst_amount = extras.getString("Total_amount");

            GodownId = extras.getInt("godown_id");
            Godowncode = extras.getString("godown_code");
            Godown_name = extras.getString("godown_name");
        }
        Final_amount.setText(include_gst_amount + "");
        DecimalFormat dff = new DecimalFormat("####0.00");
        DecimalFormat form = new DecimalFormat("0.00");


        products_amount = Double.parseDouble(include_gst_amount) - Double.parseDouble(String.valueOf(Gst_total));
        Log.e("products_amount===", String.valueOf(products_amount));
        text_amount.setText("" + dff.format(products_amount));


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();


            }
        });
        mAdapter = new producut_Adapter(this, SharedPrefsData.getCartData(this));
        recycler_view_products.setAdapter(mAdapter);
//



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

    private void FertilizerRequest() {


        mdilogue.show();
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
                        mdilogue.cancel();
                    }

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onNext(FertResponse fertResponse) {
                        if (fertResponse.getIsSuccess()) {
                            mdilogue.dismiss();
                            btn_submit.setEnabled(false);
                            new Handler().postDelayed(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.M)
                                @Override
                                public void run() {

                                    List<MSGmodel> displayList = new ArrayList<>();

                                    for (int i = 0; i < SharedPrefsData.getCartData(bioproducts_list.this).size(); i++) {



                                        product_name =SharedPrefsData.getCartData(getApplicationContext()).get(i).getProductname();
                                        quantity=SharedPrefsData.getCartData(getApplicationContext()).get(i).getQuandity();
                                        selected_list.add(product_name + "   :   " +quantity +" ");
                                        selected_name = arrayyTOstring(selected_list);
                                    }
                                    displayList.add(new MSGmodel(getString(R.string.Godown_name), Godown_name));
                                    displayList.add(new MSGmodel(getString(R.string.product_quantity), selected_name));

                                    displayList.add(new MSGmodel(getResources().getString(R.string.amount), text_amount.getText()+""));
                                    displayList.add(new MSGmodel(getResources().getString(R.string.gst_amount),dec.format(Gst_total)));

                                    displayList.add(new MSGmodel(getResources().getString(R.string.total_amt), Final_amount.getText()+""));

                                    showSuccessDialog(displayList, getString(R.string.success_lab));
                                }
                            }, 300);
                        } else {
                            showDialog(bioproducts_list.this, fertResponse.getEndUserMessage());
                        }


                    }


                });


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
        requestModel.setRequestTypeId(107);
        requestModel.setFarmerCode(Farmer_code);
        requestModel.setFarmerName(SharedPrefsData.getusername(this));
        requestModel.setPlotCode(null);
        requestModel.setRequestCreatedDate(formattedDate);

        requestModel.setIsFarmerRequest(false);
        requestModel.setCreatedByUserId(User_id);
        requestModel.setCreatedDate(formattedDate);
        requestModel.setUpdatedByUserId(User_id);
        requestModel.setUpdatedDate(formattedDate);
        requestModel.setGodownId(GodownId);
        requestModel.setPaymentModeType((payment_id.get(paymentspin.getSelectedItemPosition() - 1)));
        requestModel.setFileName(null);
        requestModel.setFileExtension(null);
        requestModel.setFileLocation(null);
        requestModel.setClusterId(Cluster_id);
        requestModel.setStateCode(statecode);
        requestModel.setStateName(Statename);
        requestModel.setTotalCost(Double.valueOf(include_gst_amount));
        requestModel.setSubcidyAmount(0.0);
        requestModel.setPaybleAmount(Double.valueOf(include_gst_amount));
        requestModel.setComments(null);
        requestModel.setCropMaintainceDate(null);
        requestModel.setIssueTypeId(null);
        requestModel.setGodownCode(Godowncode);
        List<FertRequest.RequestProductDetail> req_products = new ArrayList<>();


        for (int i = 0; i < SharedPrefsData.getCartData(this).size(); i++) {


            FertRequest.RequestProductDetail products = new FertRequest.RequestProductDetail();
            products.setBagCost(SharedPrefsData.getCartData(this).get(i).getAmount());
            products.setGstPersentage(SharedPrefsData.getCartData(this).get(i).getGst());
            products.setProductId(SharedPrefsData.getCartData(this).get(i).getProductID());
            products.setQuantity(SharedPrefsData.getCartData(this).get(i).getQuandity());
            products.setSize((SharedPrefsData.getCartData(this).get(i).getSize()));
            products.setProductCode((SharedPrefsData.getCartData(this).get(i).getProduct_code()));

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

//    private void getActiveGodowns() {
//
//        int typeid = SharedPrefsData.getInstance(this).getIntFromSharedPrefs("postTypeId");
//
//        String statecode = SharedPrefsData.getInstance(this).getStringFromSharedPrefs("statecode");
//        mdilogue.show();
//        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
//        mSubscription = service.getActiveGodowns(APIConstantURL.GetActiveGodowns + "/" + statecode)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ActiveGodownsModel>() {
//                    @Override
//                    public void onCompleted() {
//                        mdilogue.cancel();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mdilogue.cancel();
//                        Log.d(TAG, "---- analysis ---->GetActiveGodows -->> error -->> :" + e.getLocalizedMessage());
//                        showDialog(pole_godown_list.this, getString(R.string.server_error));
//                    }
//
//                    @Override
//                    public void onNext(ActiveGodownsModel activeGodownsModel) {
//                        mdilogue.cancel();
//                        Log.d(TAG, "---- analysis ---->GetActiveGodows-->> Responce size-->> :" + activeGodownsModel.getListResult().size());
//                        adapter = new GodownListAdapter(activeGodownsModel.getListResult(), ctx, pole_godown_list.this);
//                        lst_godown_list.setAdapter(adapter);
//
//
//                    }
//                });
//
//    }



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
                showDialog(bioproducts_list.this, getResources().getString(R.string.Internet));


            }
        }

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
                        showDialog(bioproducts_list.this, getResources().getString(R.string.Internet));
                    }
                } else {
                    showDialog(bioproducts_list.this, getResources().getString(R.string.ente_pin));
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
                        showDialog(bioproducts_list.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final FarmerOtpResponceModel farmerOtpResponceModel) {
                        mdilogue.dismiss();
                        if (farmerOtpResponceModel.getIsSuccess()) {

                            FertilizerRequest();
                        }



                        else {
                            showDialog(bioproducts_list.this, farmerOtpResponceModel.getEndUserMessage());
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
                            showDialog(bioproducts_list.this, getString(R.string.server_error));
                        }

                        @Override
                        public void onNext(FarmerResponceModel farmerResponceModel) {

                            Log.d(TAG, "onNext: " + farmerResponceModel);
                            if (farmerResponceModel.getIsSuccess()) {
                                mobile_number = farmerResponceModel.getResult();

                                Log.e("mobile_number======",mobile_number+"");
                            } else {
                                showDialog(bioproducts_list.this, getResources().getString(R.string.Invalid));
                            }

                        }
                    });


        }

    }

    private boolean validations() {


        if (paymentspin.getSelectedItemPosition() == 0) {

            showDialog(bioproducts_list.this, getResources().getString(R.string.paym_validation));
            return false;
        }
        return true;
    }

    //endregion
//    @Override
//    public void onItemClick(ActiveGodownsModel.ListResult item) {
//
//        selectedGodown = item;
//        GodownId = selectedGodown.getId();
//        Godown_name=selectedGodown.getName();
//        // Log.e("selectedGodown===",selectedGodown.getId().toString());
//    }
}