package in.calibrage.palm360fa.Views.Activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;

import in.calibrage.palm360fa.Model.AddQuickPayOTP;
import in.calibrage.palm360fa.Model.FarmerOtpResponceModel;
import in.calibrage.palm360fa.Model.GetQuickpayDetails;
import in.calibrage.palm360fa.Model.GetquickpayDetailsModel;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.MSGmodel;
import in.calibrage.palm360fa.Model.PostQuickpaymodel;
import in.calibrage.palm360fa.Model.QuickPayOTPres;
import in.calibrage.palm360fa.Model.QuickPayResponce;
import in.calibrage.palm360fa.Model.ResQuickPayOTP;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.CommonUtil;
import in.calibrage.palm360fa.common.PinEntryEditText;
import in.calibrage.palm360fa.common.SignatureView;
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

public class Quickpay_SummaryActivity extends BaseActivity {
    CheckBox checkbox;
    private static final String TAG = Quickpay_SummaryActivity.class.getSimpleName();
    private ProgressDialog dialog;
    String currentDate;
    String total;
    TextView terms;
    TextView ok, getTerms, Click_here ;
    //TextView ffbCostTxt, convenienceChargeTxt, closingBalanceTxt, totalAmount, text_flat_charge, text_quntity, text_quickpay_fee;

    TextView  totalclosingBalanceTxt,totalffbcost, totalTransactionFee, totalQuickpayFee,totalpay;

    String Farmer_code;
    private Subscription mSubscription;
    ImageView backImg, home_btn;
    Button submit;
    private SpotsDialog mdilogue;
    Bitmap dest;
    Button save;
    String whs_Code, ccstateName, ccstateCode, ccdistrictName;
    int ccdistrictId;
    List<GetquickpayDetailsModel> quickpaydetailslist = new ArrayList<>();
    private Quickpaydetailsadapter adapter;
    RecyclerView detailsrecyclerview;
    List<Double> ffbflatchargelist = new ArrayList<>();
    double totalflatcharge = 0;
    double totalFFBcost = 0;
    double totaltransactionfee = 0;
    double totalquickfee = 0;
    double totaldue = 0;
    double totalamounttopay = 0;
    double sumoftotalamounttopay = 0;


    private boolean isSignatured = false;
    SignatureView signatureView;
    String path;
    List<String> ids_list = new ArrayList<>();
    List<String> dates_list = new ArrayList<>();
    List<Double> netweight_list = new ArrayList<>();
    List<String> post_ids = new ArrayList<>();
    List<String> post_codes = new ArrayList<>();
    private static final String IMAGE_DIRECTORY = "/signdemo";
    String result;
    double total_weight = 0.0;
    DecimalFormat df = new DecimalFormat("####0.00");
    DecimalFormat dff = new DecimalFormat("####0.000");;
    LoginResponse created_user;
    String statecode,statename;
    String districtName;
    Integer districtId;
    private Integer User_id,Cluster_id;
    private PinEntryEditText pinEntry;
    Button dialogButton;
    boolean QuickpayinProgress = false;
    boolean QuickpaySuccess = true;
    final int langID = SharedPrefsData.getInstance(this).getIntFromSharedPrefs("lang");
    private FarmerOtpResponceModel catagoriesList;
    String farmer_full_name,Mobile_number,Farmername,farmername_base64;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
        setContentView(R.layout.quickpaysummaryactivity);


        init();
        setViews();

        // Saving string data of your e

    }

    private void init() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // include_gst_amount = extras.getString("Total_amount");

            ids_list = (ArrayList<String>) getIntent().getSerializableExtra("collection_ids");
            dates_list = (ArrayList<String>) getIntent().getSerializableExtra("collection_dates");
            netweight_list = (ArrayList<Double>) getIntent().getSerializableExtra("collection_weight");
            whs_Code =extras.getString("whsCode");

            ccstateName = extras.getString("ccstatename");
            ccstateCode = extras.getString("ccstatecode");
            ccdistrictName = extras.getString("ccdistrictname");
            ccdistrictId = extras.getInt("ccdistrictid");

//            Log.e("ccstatename==",ccstateName);
//            Log.e("ccstatecode==",ccstateCode);
//            Log.e("ccdistrictName==",ccdistrictName);
//            Log.e("ccdistrictId==",ccdistrictId + "");
            Log.e("whs_Code==",whs_Code);
        }
        for (int i = 0; i < ids_list.size(); i++) {

            String id = ids_list.get(i);
            String date = dates_list.get(i);
            double weight = netweight_list.get(i);
            post_codes.add(id + "(" + weight +" MT"+ ")" );


            total_weight = total_weight + weight;
            Log.e("total_weight===",total_weight+"");

            Log.e("post_ids==", String.valueOf(post_ids));
        }


        backImg = (ImageView) findViewById(R.id.back);
        signatureView = (SignatureView) findViewById(R.id.signature_view);
        Click_here = (TextView) findViewById(R.id.clear);
        save = (Button) findViewById(R.id.save);

        totalffbcost = (TextView) findViewById(R.id.totalffbcost);
        totalclosingBalanceTxt = (TextView) findViewById(R.id.totaldues);
        totalQuickpayFee = (TextView) findViewById(R.id.totalquickpayfee);
        totalTransactionFee = (TextView) findViewById(R.id.totaltransactioncost);
        totalpay = (TextView) findViewById(R.id.totalpay);
//        ffbCostTxt = (TextView) findViewById(R.id.tvtext_item_five);
//        convenienceChargeTxt = (TextView) findViewById(R.id.tvtext_item_seven);
//        closingBalanceTxt = (TextView) findViewById(R.id.tvtext_item_nine);
//        totalAmount = (TextView) findViewById(R.id.tvtext_item_fifteen);
//        text_flat_charge = (TextView) findViewById(R.id.text_flat_charge);
//        text_quntity = (TextView) findViewById(R.id.text_quntity);
//        text_quickpay_fee = (TextView) findViewById(R.id.text_quickpay_fee);
        detailsrecyclerview = findViewById(R.id.details_recyclerview);

        Button confirmBtn = (Button) findViewById(R.id.buttonConfirm);
        checkbox = (CheckBox) findViewById(R.id.checkBox);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        submit = (Button) findViewById(R.id.buttonConfirm);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");
        catagoriesList = SharedPrefsData.getCatagories(this);// Saving string data of your editext
        farmer_full_name = catagoriesList.getResult().getFarmerDetails().get(0).getFirstName() + " " + catagoriesList.getResult().getFarmerDetails().get(0).getMiddleName() + " " + catagoriesList.getResult().getFarmerDetails().get(0).getLastName();
        created_user = SharedPrefsData.getCreatedUser(Quickpay_SummaryActivity.this);
        if (null != catagoriesList.getResult().getFarmerDetails().get(0).getMobileNumber()) {

            Mobile_number =catagoriesList.getResult().getFarmerDetails().get(0).getContactNumber()+"," +catagoriesList.getResult().getFarmerDetails().get(0).getMobileNumber();
        }else {
            Mobile_number = catagoriesList.getResult().getFarmerDetails().get(0).getContactNumber();
        }
        Farmername =farmer_full_name.replace("null", "");
        Log.e("Farmername===", Farmername+"");
//
        User_id= created_user.getResult().getUserInfos().getId();
        Log.e("created_user===", String.valueOf(User_id));
        statecode = catagoriesList.getResult().getFarmerDetails().get(0).getStateCode();
        statename =catagoriesList.getResult().getFarmerDetails().get(0).getStateName();

        districtId = SharedPrefsData.getInstance(this).getIntFromSharedPrefs("districtId");
        districtName = SharedPrefsData.getInstance(this).getStringFromSharedPrefs("districtName");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setViews() {

        catagoriesList = SharedPrefsData.getCatagories(this);
        if (null != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId() && 0 != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId())
            Cluster_id =  catagoriesList.getResult().getFarmerDetails().get(0).getClusterId();
        Log.e("Cluster_id===",Cluster_id+"");


        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validations()) {
                    signature_popup();
//                    if (isOnline()) {
//                        //submitReq();
//                    } else {
//                        showDialog(Quickpay_SummaryActivity.this, getResources().getString(R.string.Internet));
//                    } }
                }
            }
        });

        detailsrecyclerview.setHasFixedSize(true);
        detailsrecyclerview.setLayoutManager(new LinearLayoutManager(this));

        if(isOnline()) {

//            for (int i=0; i<dates_list.size(); i++){
//
//                Log.d("CollectionDates", dates_list.get(i) + "");
//                collectiondate = dates_list.get(i);
//                collectionweight = netweight_list.get(i);
//                GetQuckPaySummary(collectiondate,collectionweight);
//
//            }
//
            // Got all responces
            QuickpayinProgress =true;
            GetQuckPaySummary(dates_list,netweight_list,0,dates_list.size());
        }
        else {
            showDialog(Quickpay_SummaryActivity.this, getResources().getString(R.string.Internet));
        }

    }

    private void AddQuickPayOTP() {
        mdilogue.show();;
        JsonObject object = Getotpreq();
        Log.e("object==",object+"");
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.post_quickpayotp(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QuickPayOTPres>() {

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
                        showDialog(Quickpay_SummaryActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(QuickPayOTPres quickPayOTPres) {

                        if (quickPayOTPres.getIsSuccess()) {
                            Toast.makeText(Quickpay_SummaryActivity.this, getString(R.string.otp_sent), Toast.LENGTH_SHORT).show();
                        } else {
                            showDialog(Quickpay_SummaryActivity.this, getResources().getString(R.string.Invalid_details));
                        }



                    }});}

    private JsonObject Getotpreq() {
        AddQuickPayOTP requestModel = new AddQuickPayOTP();
        requestModel.setFarmerCode(Farmer_code);
        requestModel.setCreatedByUserId(User_id);
        requestModel.setFarmerName(Farmername);
        requestModel.setOtp(4);
        requestModel.setPhoneNumbers(Mobile_number);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }


    private void signature_popup() {
        AddQuickPayOTP();
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.signature_view);
        //dialog.setCancelable(false);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView farmer_name = (TextView) dialog.findViewById(R.id.farmer_Code);
        pinEntry =  dialog.findViewById(R.id.txt_pin_entry);

        farmer_name.setText("" + farmer_full_name.replace("null", ""));
        //  ImageView image = (ImageView) dialog.findViewById(R.id.image);
        // image.setImageResource(R.drawable.ic_launcher);

        dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pinEntry.getText() != null & pinEntry.getText().toString().trim() != "" & !TextUtils.isEmpty(pinEntry.getText())) {
                    if (isOnline())
                        IsQuickPayValid();
                    else {
                        showDialog(Quickpay_SummaryActivity.this, getResources().getString(R.string.Internet));
                    }
                } else {
                    showDialog(Quickpay_SummaryActivity.this, getResources().getString(R.string.ente_pin));
                    //pinEntry.setError("Please Enter Pin");
                }

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void IsQuickPayValid() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getquickpayotp(APIConstantURL.quickpay_otp + Farmer_code + "/" + pinEntry.getText().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResQuickPayOTP>() {
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
                        //showDialog(Quickpay_SummaryActivity.this, getString(R.string.server_error));
                    }

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onNext(ResQuickPayOTP resQuickPayOTP) {

                        if (resQuickPayOTP.getIsSuccess()) {
                            mdilogue.dismiss();
                            dialogButton.setEnabled(false);
                            Log.e("success==",resQuickPayOTP.getEndUserMessage());

                            submitReq();
                        }
                        else {
                            showDialog(Quickpay_SummaryActivity.this, resQuickPayOTP.getEndUserMessage());
                        }

                    }});}


    private boolean validations() {
        if (checkbox.isChecked()) {
            checkbox.setChecked(true);
        } else {
            showDialog(Quickpay_SummaryActivity.this, getResources().getString(R.string.terms_agree));
            return false;
        }



        return true;
    }

    public String saveImage(Bitmap myBitmap) {


        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.bg_button_normal); // the original file yourimage.jpg i added in resources
        dest = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);

        String yourText = "My custom Text adding to Image";

        Canvas cs = new Canvas(dest);
        Paint tPaint = new Paint();
        tPaint.setTextSize(24);
        tPaint.setColor(Color.BLUE);
        tPaint.setStyle(Paint.Style.FILL);
        cs.drawBitmap(src, 0f, 0f, null);
        float height = tPaint.measureText("yY");
        float width = tPaint.measureText(Farmername);
        float x_coord = (src.getWidth() - width)/2;
        cs.drawText(Farmername, x_coord, height+15f, tPaint); // 15f is to put space between top edge and the text, if you want to change it, you can
        try {
            dest.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File("/sdcard/ImageAfterAddingText.jpg")));

            Log.e("dest=",dest+"");
            // dest is Bitmap, if you want to preview the final image, you can display it on screen also before saving
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";

    }

    private void GetQuckPaySummary(List<String> Cdate, List<Double> Cweightx, final int curentIndex, int totalIndex) {
        mdilogue.show();

        // cdate[index],


        if (curentIndex < dates_list.size()){


            JsonObject object = GetReuestobject(Cdate.get(curentIndex), Cweightx.get(curentIndex));
            quickpayProcess(curentIndex, object);
        }else{

            QuickpaySuccess = true;
            QuickpayinProgress = false;
            Log.d(TAG, "All API's Completed");
            Log.d(TAG, "DetailsListSize" + quickpaydetailslist.size() + "");
            adapter = new Quickpaydetailsadapter(Quickpay_SummaryActivity.this, quickpaydetailslist, ids_list,dates_list);
            detailsrecyclerview.setAdapter(adapter);
            totalFFBcost =0;

            for(int i =0; i < quickpaydetailslist.size(); i ++)
            {
                totalFFBcost = totalFFBcost+ quickpaydetailslist.get(i).getListResult().get(0).getFfbCost();
                totaltransactionfee = quickpaydetailslist.get(i).getListResult().get(0).getConvenienceCharge();
                totalquickfee = totalquickfee+ quickpaydetailslist.get(i).getListResult().get(0).getQuickPay();
                totaldue = quickpaydetailslist.get(i).getListResult().get(0).getClosingBalance();

                //totaldue = -450;
                totalflatcharge = totalflatcharge+ quickpaydetailslist.get(i).getListResult().get(0).getFfbFlatCharge();
                totalamounttopay = totalamounttopay + quickpaydetailslist.get(i).getListResult().get(0).getTotal();
                ffbflatchargelist.add(quickpaydetailslist.get(i).getListResult().get(0).getFfbFlatCharge());

            }

            Log.d(TAG, "totalFFBcostis:"+totalFFBcost);
            Log.d(TAG, "totaltransactionfeeis:"+totaltransactionfee);
            Log.d(TAG, "totalquickfeeis:"+totalquickfee);
            Log.d(TAG, "totaldueis:"+totaldue);
            Log.d(TAG, "totalflatchargeis:"+totalflatcharge);
            Log.d(TAG, "totalamounttopayis:"+totalamounttopay);
            Log.d(TAG, "ffbflatchargelistis:"+ffbflatchargelist);

            if (totalFFBcost > 0.0){
                totalffbcost.setText(df.format(totalFFBcost));;
            }else{
                totalffbcost.setText(" 0.00");
            }

            if (totaltransactionfee > 0.0){
                totalTransactionFee.setText("-" + df.format(totaltransactionfee));;
            }else{
                totalTransactionFee.setText(" 0.00");
            }

            if (totalquickfee > 0.0){
                totalQuickpayFee.setText("-" + df.format(totalquickfee));;
            }else{
                totalQuickpayFee.setText(" 0.00");
            }

            if (totaldue > 0.0){
                totalclosingBalanceTxt.setText("-" +df.format(totaldue));
            }else{
                totalclosingBalanceTxt.setText(" 0.00");
            }
//
//            double due = 0.0;
//            try{
//                due =  Double.parseDouble(totalclosingBalanceTxt.getText().toString());
//                Log.d("DueAmount", "is" + due);
//
//            }catch (Exception ex){
//
//                Log.e(TAG, "due amount expection is" + ex);
//            }

            //We are not adding negative due amount ##CIS

            double totalDueamount = 0.0;
            if(totaldue > 0.0)
            {
                totalDueamount  = totaldue;

            }else{
                totalDueamount = 0.0;
            }
//             formula  total = (ffbCost -transaction Fee   -quickpay fee )  - due
            sumoftotalamounttopay = (totalFFBcost - totaltransactionfee - totalquickfee) - totalDueamount;
            // sumoftotalamounttopay = totalFFBcost - totaltransactionfee - totalquickfee - -44;
            Log.d("sumtotalamounttopay", sumoftotalamounttopay + "");

            totalpay.setText(df.format(sumoftotalamounttopay));

        }




    }


    private void quickpayProcess(final int curentIndex, JsonObject object) {
        //        GetQuickpayDetails requestModel = new GetQuickpayDetails();
//
//        requestModel.setFarmerCode(Farmer_code);
//        requestModel.setQuantity(Cweight);
//        requestModel.setIsSpecialPay(false);
//        requestModel.setStateCode(statecode);
//        requestModel.setDistrictId(districtId);
//        requestModel.setDocDate(Cdate);
//
//
//        object = new Gson().toJsonTree(requestModel).getAsJsonObject();

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.post_details(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetquickpayDetailsModel>() {
                    //        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
//        mSubscription = service.getquickpaydetails(APIConstantURL.GetQuickpayDetails + Farmer_code)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<GetquickpayDetailsModel>() {
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
                        showDialog(Quickpay_SummaryActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetquickpayDetailsModel getquickpayDetailsModel) {
                        if (getquickpayDetailsModel.getListResult() != null) {

                            // Check Quick pay Deatils Valid or not

                            quickpaydetailslist.add(getquickpayDetailsModel);
                            Log.d("getquickpayDetailsModel", quickpaydetailslist.size() + "");

//                            double sum = 0;
//                            for(Double d : ffbflatchargelist) {
//                                sum += d;
//                            }
//
//                            Log.d("statecode========", statecode);
//
//                            Log.d(TAG, "Sum is: " +sum);
//

//                            ffbflatchargelist = new ArrayList<>();
//
//                            for (int i=0; i<dates_list.size())
//
//                            ffbflatchargelist.add(getquickpayDetailsModel.getListResult().get(0).getFfbFlatCharge());
//
//                            Log.e("ffbflatchargelist====", ffbflatchargelist + "");

                            Log.e("nodada====", "nodata===custom2");
//                            text_quntity.setText(" "+dff.format(getquickpayDetailsModel.getListResult().get(0).getQuantity()));
//
//                            if (getquickpayDetailsModel.getListResult().get(0).getFfbFlatCharge() == null) {
//                                text_flat_charge.setText(" 0.00");
//
//                            } else
//                            ffbCostTxt.setText(" "+df.format(getquickpayDetailsModel.getListResult().get(0).getFfbCost()));
//
//                            if (getquickpayDetailsModel.getListResult().get(0).getConvenienceCharge() == null) {
//                                convenienceChargeTxt.setText(" 0.00");
//
//                            } else {
//
//                                convenienceChargeTxt.setText("-" + df.format(getquickpayDetailsModel.getListResult().get(0).getConvenienceCharge()));
//                            }
//
//                            //   totalAmount.setText(df.format(getquickpayDetailsModel.getListResult().get(0).getTotal()));
//
//
//                            if(getquickpayDetailsModel.getListResult().get(0).getClosingBalance() > 0.0){
//                                closingBalanceTxt.setText(" "+df.format(getquickpayDetailsModel.getListResult().get(0).getClosingBalance()));
//                            }else{
//                                closingBalanceTxt.setText(" 0.00");
//                            }
//                            if (getquickpayDetailsModel.getListResult().get(0).getTotal() > 0.0 ) {
//                                totalAmount.setText(" "+df.format(getquickpayDetailsModel.getListResult().get(0).getTotal()));
//                                // totalAmount.setText("0");
//
//                            } else {
//                                totalAmount.setText(" 0.00");
//                                // totalAmount.setText(String.valueOf(getquickpayDetailsModel.getListResult().get(0).getTotal()));
//                            }
//                            text_quickpay_fee.setText("-" + df.format(getquickpayDetailsModel.getListResult().get(0).getQuickPay()));
                            if (curentIndex < dates_list.size()){

                                GetQuckPaySummary(dates_list,netweight_list,curentIndex+1,dates_list.size());
                            }else{

                                QuickpaySuccess = true;
                                QuickpayinProgress = false;
                                Log.d(TAG, "All API's Completed");
                                Log.d(TAG, "DetailsListSize" + quickpaydetailslist.size() + "");
                                adapter = new Quickpaydetailsadapter(Quickpay_SummaryActivity.this, quickpaydetailslist,ids_list, dates_list);
                                detailsrecyclerview.setAdapter(adapter);
                            }

                        } else {
                            Log.d(TAG, "QuickPay  Deatis #### Error or No Data" );

                            QuickpaySuccess = false;
                            QuickpayinProgress = false;
                            Spanned test = Html.fromHtml(getString(R.string.ffbratenorthere));
                            showwDialog(Quickpay_SummaryActivity.this,test);
                        }

                    }


                });
    }

    public void showwDialog(Activity activity, Spanned msg) {
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


//    private void GetQuckPaySummary() {
//        mdilogue.show();
//        JsonObject object = GetReuestobject();
//        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
//        mSubscription = service.post_details(object)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<GetquickpayDetailsModel>() {
//                    //        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
////        mSubscription = service.getquickpaydetails(APIConstantURL.GetQuickpayDetails + Farmer_code)
////                .subscribeOn(Schedulers.newThread())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Subscriber<GetquickpayDetailsModel>() {
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
//                        mdilogue.dismiss();
//                        showDialog(Quickpay_SummaryActivity.this, getString(R.string.server_error));
//                    }
//
//                    @Override
//                    public void onNext(GetquickpayDetailsModel getquickpayDetailsModel) {
//                        if (getquickpayDetailsModel.getListResult() != null) {
//
//                            Log.e("nodada====", "nodata===custom2");
//                            text_quntity.setText(" "+dff.format(getquickpayDetailsModel.getListResult().get(0).getQuantity()));
//
//                            if (getquickpayDetailsModel.getListResult().get(0).getFfbFlatCharge() == null) {
//                                text_flat_charge.setText(" 0.00");
//
//                            } else {
//                                text_flat_charge.setText(" "+df.format(getquickpayDetailsModel.getListResult().get(0).getFfbFlatCharge()));
//                            }
//                            ffbCostTxt.setText(" "+df.format(getquickpayDetailsModel.getListResult().get(0).getFfbCost()));
//
//                            if (getquickpayDetailsModel.getListResult().get(0).getConvenienceCharge() == null) {
//                                convenienceChargeTxt.setText(" 0.00");
//
//                            } else {
//
//                                convenienceChargeTxt.setText("-" + df.format(getquickpayDetailsModel.getListResult().get(0).getConvenienceCharge()));
//                            }
//                            if(getquickpayDetailsModel.getListResult().get(0).getClosingBalance() > 0.0){
//                                closingBalanceTxt.setText(" "+df.format(getquickpayDetailsModel.getListResult().get(0).getClosingBalance()));
//                            }else{
//                                closingBalanceTxt.setText(" 0.00");
//                            }
//                            //   totalAmount.setText(df.format(getquickpayDetailsModel.getListResult().get(0).getTotal()));
//                            if (getquickpayDetailsModel.getListResult().get(0).getTotal() > 0.0 ) {
//                                totalAmount.setText(" "+df.format(getquickpayDetailsModel.getListResult().get(0).getTotal()));
//                                // totalAmount.setText("0");
//
//                            } else {
//                                totalAmount.setText(" 0.00");
//                                // totalAmount.setText(String.valueOf(getquickpayDetailsModel.getListResult().get(0).getTotal()));
//                            }
//                            text_quickpay_fee.setText("-" + df.format(getquickpayDetailsModel.getListResult().get(0).getQuickPay()));
//
//                        } else {
//
//                        }
//
//
//                    }
//
//
//                });
//    }

    private JsonObject GetReuestobject(String Cdate, Double Cweight) {


        GetQuickpayDetails requestModel = new GetQuickpayDetails();

        requestModel.setFarmerCode(Farmer_code);
        requestModel.setQuantity(Cweight);
        requestModel.setIsSpecialPay(false);
        requestModel.setDocDate(Cdate);

        if (ccstateCode != null && !ccstateCode.isEmpty() && !ccstateCode.equals("null")) {
            requestModel.setStateCode(ccstateCode);

            if (!ccstateCode.startsWith("AP") ) {

                requestModel.setDistrictId(0);

            }else{
                requestModel.setDistrictId(ccdistrictId);
            }

        }else{

            requestModel.setStateCode(statecode);

            if (!statecode.startsWith("AP") ) {

                requestModel.setDistrictId(0);

            }else{
                requestModel.setDistrictId(districtId);
            }

        }

//        if (ccstateCode != null && !ccstateCode.isEmpty() && !ccstateCode.equals("null")) {
//
//            if (!ccstateCode.startsWith("AP") ) {
//
//                requestModel.setDistrictId(0);
//
//            }else{
//                requestModel.setDistrictId(ccdistrictId);
//            }
//
//        }else{
//
//            if (!statecode.startsWith("AP") ) {
//
//                requestModel.setDistrictId(0);
//
//            }else{
//                requestModel.setDistrictId(districtId);
//            }
//
//        }


        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void submitReq() {

        Double d1 = totalamounttopay;

        if (d1 > 0.0) {
            mdilogue.show();
            JsonObject object = quickReuestobject();

            ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
            mSubscription = service.postquickpay(object)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<QuickPayResponce>() {
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
                        public void onNext(final QuickPayResponce quickPayResponce) {
                            mdilogue.dismiss();
                            if (quickPayResponce.getIsSuccess()) {
                                result = quickPayResponce.getResult();
                                Log.e("result===",result);
                                showSuccesspdf(result);

                            } else {
                                showDialog(Quickpay_SummaryActivity.this, quickPayResponce.getEndUserMessage());
                            }

                        }


                    });
        } else {
            showDialog(Quickpay_SummaryActivity.this,getString(R.string.enter_loan_amount));
        }

    }

    private void showSuccesspdf(final String result) {
        mdilogue.show();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.pdf_dialog);
        final WebView webView = dialog.findViewById(R.id.webView);
        final ProgressBar progressBar = dialog.findViewById(R.id.progressBar);
        Button btn_dialog = dialog.findViewById(R.id.btn_dialog);

        progressBar.setVisibility(View.VISIBLE); // Show the progress bar initially
        //  String pdfurl = "https://3FAkshaya.com/3FAkshayaRepo/FileRepository/2023/07/28/QuickpayPdf/20230728111114410.pdf";

        String doc = "https://docs.google.com/gview?embedded=true&url=" + result;

        //   String doc = "https://docs.google.com/gview?embedded=true&url=" + pdfurl;

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.loadUrl(doc);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE); // Hide the progress bar when page is loaded
            }
        });

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                List<MSGmodel> displayList = new ArrayList<>();

                showSuccessDialog(displayList, getResources().getString(R.string.qucick_success));
//
//                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        finish();



            }
        });

        dialog.show();
    }

//    private void showSuccesspdf(String result) {
//;
//        mdilogue.show();
//        final Dialog dialog = new Dialog(this);
//
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        if (langID == 2)
//            updateResources(this, "te");
//        else
//            updateResources(this, "en-US");
//        dialog.setContentView(R.layout.pdf_dialog);
//        final WebView webView = dialog.findViewById(R.id.webView);
//        Button btn_dialog = dialog.findViewById(R.id.btn_dialog);
//        TextView request_head = dialog.findViewById(R.id.request_head);
//        request_head.setText(getResources().getString(R.string.quick_pdf));
//       // WebSettings settings = webView.getSettings();
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//
//
//
//
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//
//                webView.loadUrl("javascript:(function() { " +
//                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
//                mdilogue.show();
//            }
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                webView.loadUrl("javascript:(function() { " +
//                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
//                mdilogue.dismiss();
//            }
//        });
//
//        PdfWebViewClient pdfWebViewClient = new PdfWebViewClient(Quickpay_SummaryActivity.this, webView);
//        pdfWebViewClient.loadPdfUrl(
//                "https://www.google.co.in/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0ahUKEwjgwIfp3KXSAhXrhFQKHQqEDHYQFggZMAA&url=http%3A%2F%2Fwww.orimi.com%2Fpdf-test.pdf&usg=AFQjCNERYYcSfMLS5ukBcT2Qy11YxEhXqw&cad=rja");
////
//        btn_dialog.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                List<MSGmodel> displayList = new ArrayList<>();
//
//                showSuccessDialog(displayList, getResources().getString(R.string.qucick_success));
////
//            }
//        });
//        dialog.show();
//
//    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private JsonObject quickReuestobject() {

//        double sum = 0;
//        for(Double d : ffbflatchargelist) {
//            sum += d;
//        }
//
//        Log.d("statecode========", statecode);
//
//        Log.d(TAG, "Sum is: " +sum);
//
//        Double avgffbcost = sum/dates_list.size();
//        Log.d(TAG, "Avg FFB Cost is: " + avgffbcost);

        Double avgffbcost = totalflatcharge/dates_list.size();
        Log.d(TAG, "Avg FFB Cost is: " + avgffbcost);


        PostQuickpaymodel requestModel = new PostQuickpaymodel();

        requestModel.setFarmerName(SharedPrefsData.getusername(this));
        requestModel.setFarmerCode(Farmer_code);
        requestModel.setIsFarmerRequest(false);
        requestModel.setCreatedByUserId(User_id);
        requestModel.setReqCreatedDate(currentDate);
        requestModel.setCreatedDate(currentDate);
        requestModel.setUpdatedByUserId(User_id);
        requestModel.setWhsCode(whs_Code);
        requestModel.setUpdatedDate(currentDate);
        requestModel.setClusterId(Cluster_id);
        requestModel.setFileLocation("");

        if (ccstateCode != null && !ccstateCode.isEmpty() && !ccstateCode.equals("null")) {
            requestModel.setStateCode(ccstateCode);

            if (!ccstateCode.startsWith("AP") ) {

                requestModel.setDistrictId(0);
                requestModel.setDistrictName("null");

            }else{
                requestModel.setDistrictId(ccdistrictId);
                requestModel.setDistrictName(ccdistrictName);
            }

        }else{

            requestModel.setStateCode(statecode);

            if (!statecode.startsWith("AP") ) {

                requestModel.setDistrictId(0);
                requestModel.setDistrictName("null");

            }else{
                requestModel.setDistrictId(districtId);
                requestModel.setDistrictName(districtName);
            }

        }

        if (ccstateName != null && !ccstateName.isEmpty() && !ccstateName.equals("null")) {
            requestModel.setStateName(ccstateName);
        }else{
            requestModel.setStateName(statename);
        }

//        if (!statecode.startsWith("AP") ) {
//            requestModel.setDistrictId(0);
//            requestModel.setDistrictName(null);
//        }else{
//            requestModel.setDistrictId(districtId);
//            requestModel.setDistrictName(districtName);
//        }

        requestModel.setFfbCost(avgffbcost);

        if(totaldue > 0.0){
            requestModel.setClosingBalance(totaldue);
        }else {

            requestModel.setClosingBalance(0.0);
        }



//        if(null != totalclosingBalanceTxt.getText() & !TextUtils.isEmpty(totalclosingBalanceTxt.getText()))
////            requestModel.setClosingBalance(Double.parseDouble(totalclosingBalanceTxt.getText().toString()));
////        else {
////            requestModel.setClosingBalance(0.0);
////        }
        //TODO make dynamic

        for (int i = 0; i < ids_list.size(); i++) {

            String id = ids_list.get(i);
            String date = dates_list.get(i);
            double weight = netweight_list.get(i);
            post_ids.add(id + "|" + weight + "|" + date + "|"+ ffbflatchargelist.get(i) + "");

        }

        String val = arrayTOstring(post_ids);
        Log.d(TAG, "------ analysis ------ >> get values in String(): " + val);
        String val_codes = arrayTOstring(post_codes);
        Log.d(TAG, "------ analysis ------ >> get values in String(): " + val_codes);
        requestModel.setCollectionIds(val);
        requestModel.setIsSpecialPay(false);
        requestModel.setCollectionCodes(val_codes);
        //  requestModel.setCost(Double.parseDouble(ffbCostTxt.getText().toString()));
        requestModel.setNetWeight(total_weight);
        requestModel.setSignatureExtension(".png");
//        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.rectangle_white); // the original file yourimage.jpg i added in resources
//        Bitmap dest = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
//
//        Log.e("dest=",dest+"");
//
//        Canvas cs = new Canvas(dest);
//        Paint tPaint = new Paint();
//        tPaint.setTextSize(30);
//        tPaint.setColor(Color.BLACK);
//
//
//        tPaint.setStyle(Paint.Style.FILL);
//        cs.drawBitmap(src, 0f, 0f, null);
//        float height = tPaint.measureText("yY");
//        float width = tPaint.measureText(Farmername);
//        float x_coord = (src.getWidth() - width)/2;
//        cs.drawText(Farmername, x_coord, height+160f, tPaint); // 15f is to put space between top edge and the text, if you want to change it, you can
//        try {
//            dest.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File("/sdcard/ImageAfterAddingText.jpg")));
//
//            Log.e("dest=",dest+"");
//            // dest is Bitmap, if you want to preview the final image, you can display it on screen also before saving
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.rectangle_white);
        if (src == null) {
            Log.e("Error", "Source Bitmap is null");
            //  return;
        }

// Create a mutable copy
        Bitmap dest = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas cs = new Canvas(dest);
        Paint tPaint = new Paint();
        tPaint.setTextSize(30);
        tPaint.setColor(Color.BLACK);
        tPaint.setStyle(Paint.Style.FILL);
        tPaint.setAntiAlias(true);

// Draw original image
        cs.drawBitmap(src, 0f, 0f, null);

// Measure text size correctly
        Rect textBounds = new Rect();
        String text = Farmername != null ? Farmername : "Default Name";
        tPaint.getTextBounds(text, 0, text.length(), textBounds);

        float x_coord = (src.getWidth() - textBounds.width()) / 2f;
        float y_coord = (src.getHeight() / 2f) + (textBounds.height() / 2f);

// Draw text
        cs.drawText(text, x_coord, y_coord, tPaint);

// Save the image
        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ImageAfterAddingText.jpg");
            FileOutputStream out = new FileOutputStream(file);
            dest.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Log.e("Saved Image Path", file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

// Convert Bitmap to Base64
        requestModel.setSignatureName(CommonUtil.bitMaptoBase64(dest));

//// Recycle bitmaps to free memory
//        src.recycle();
//        dest.recycle();
//
//        requestModel.setSignatureName(CommonUtil.bitMaptoBase64(dest));
        //

        //    requestModel.setSignatureName(CommonUtil.bitMaptoBase64(decodedByte));
//requestModel.setSignatureName(farmername_base64);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

    public String arrayTOstring(List<String> arrayList) {
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

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.loan_dialog);
        // Set dialog title
        dialog.setTitle("Custom Dialog");

        ok = (TextView) dialog.findViewById(R.id.ok);

        getTerms = (TextView) dialog.findViewById(R.id.txtclose);
        //  image.setImageResource(R.drawable.ic_action_duration);
        dialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        getTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }


    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return (false);
        }
    }

    private class PdfWebViewClient extends WebViewClient
    {
        private static final String TAG = "PdfWebViewClient";
        private static final String PDF_EXTENSION = ".pdf";
        private static final String PDF_VIEWER_URL = "http://docs.google.com/gview?embedded=true&url=";

        private Context mContext;
        private WebView mWebView;
        private ProgressDialog mProgressDialog;
        private boolean isLoadingPdfUrl;

        public PdfWebViewClient(Context context, WebView webView)
        {
            mContext = context;
            mWebView = webView;
            mWebView.setWebViewClient(this);
        }

        public void loadPdfUrl(String url)
        {
            mWebView.stopLoading();

            if (!TextUtils.isEmpty(url))
            {
                isLoadingPdfUrl = isPdfUrl(url);
                if (isLoadingPdfUrl)
                {
                    mWebView.clearHistory();
                }

                showProgressDialog();
            }

            mWebView.loadUrl(url);
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url)
        {
            return shouldOverrideUrlLoading(result);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl)
        {
            handleError(errorCode, description.toString(), failingUrl);
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request)
        {
            final Uri uri = request.getUrl();
            return shouldOverrideUrlLoading(webView, uri.toString());
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public void onReceivedError(final WebView webView, final WebResourceRequest request, final WebResourceError error)
        {
            final Uri uri = request.getUrl();
            handleError(error.getErrorCode(), error.getDescription().toString(), uri.toString());
        }

        @Override
        public void onPageFinished(final WebView view, final String url)
        {
            Log.i(TAG, "Finished loading. URL : " + url);
            dismissProgressDialog();
        }

        private boolean shouldOverrideUrlLoading(final String result)
        {
            Log.i(TAG, "shouldOverrideUrlLoading() URL : " + result);

            if (!isLoadingPdfUrl && isPdfUrl(result))
            {
                mWebView.stopLoading();

                final String pdfUrl = PDF_VIEWER_URL + result;

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        loadPdfUrl(pdfUrl);
                    }
                }, 300);

                return true;
            }

            return false; // Load url in the webView itself
        }

        private void handleError(final int errorCode, final String description, final String failingUrl)
        {
            Log.e(TAG, "Error : " + errorCode + ", " + description + " URL : " + failingUrl);
        }

        private void showProgressDialog()
        {
            dismissProgressDialog();
            mProgressDialog = ProgressDialog.show(mContext, "", "Loading...");
        }

        private void dismissProgressDialog()
        {
            if (mProgressDialog != null && mProgressDialog.isShowing())
            {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        }

        private boolean isPdfUrl(String url)
        {
            if (!TextUtils.isEmpty(url))
            {
                url = url.trim();
                int lastIndex = url.toLowerCase().lastIndexOf(PDF_EXTENSION);
                if (lastIndex != -1)
                {
                    return url.substring(lastIndex).equalsIgnoreCase(PDF_EXTENSION);
                }
            }
            return false;
        }
    }
}


