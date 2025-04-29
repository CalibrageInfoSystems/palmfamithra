package in.calibrage.palm360fa.Views.Activities;

import androidx.annotation.RequiresApi;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Model.FarmerOtpResponceModel;
import in.calibrage.palm360fa.Model.GetQuickpayDetails;
import in.calibrage.palm360fa.Model.GetquickpayDetailsModel;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.MSGmodel;
import in.calibrage.palm360fa.Model.PostQuickpaymodel;
import in.calibrage.palm360fa.Model.QuickPayResponce;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.PinEntryEditText;
import in.calibrage.palm360fa.common.SignatureView;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class Special_Fee extends BaseActivity {
    CheckBox checkbox;
    private static final String TAG = Quickpay_SummaryActivity.class.getSimpleName();
    private ProgressDialog dialog;
    String currentDate;
    String total;
    TextView terms;
    TextView ok, getTerms, Click_here ;
    TextView ffbCostTxt, convenienceChargeTxt, closingBalanceTxt, totalAmount, text_flat_charge, text_quntity, text_quickpay_fee;
    String Farmer_code;
    private Subscription mSubscription;
    ImageView backImg, home_btn;
    Button submit;
    private SpotsDialog mdilogue;
    Bitmap dest;
    Button save;
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
    String whs_Code;
    double total_weight = 0.0;
    String statecode,statename;
    DecimalFormat df = new DecimalFormat("####0.00");
    DecimalFormat dff = new DecimalFormat("####0.000");;
    LoginResponse created_user;
    private Integer User_id,Cluster_id;
    private PinEntryEditText pinEntry;
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
        setContentView(R.layout.activity_special__fee);


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
            Log.e("whs_Code==",whs_Code);
        }
        for (int i = 0; i < ids_list.size(); i++) {

            String id = ids_list.get(i);
            String date = dates_list.get(i);
            double weight = netweight_list.get(i);
            post_ids.add(id + "|" + weight + "|" + date + "");
            post_codes.add(id + "(" + weight +" MT"+ ")" );


            total_weight = total_weight + weight;
            Log.e("total_weight===",total_weight+"");

            Log.e("post_ids==", String.valueOf(post_ids));
        }


        backImg = (ImageView) findViewById(R.id.back);
        signatureView = (SignatureView) findViewById(R.id.signature_view);
        Click_here = (TextView) findViewById(R.id.clear);
        save = (Button) findViewById(R.id.save);
        ffbCostTxt = (TextView) findViewById(R.id.tvtext_item_five);
        convenienceChargeTxt = (TextView) findViewById(R.id.tvtext_item_seven);
        closingBalanceTxt = (TextView) findViewById(R.id.tvtext_item_nine);
        totalAmount = (TextView) findViewById(R.id.tvtext_item_fifteen);
        text_flat_charge = (TextView) findViewById(R.id.text_flat_charge);
        text_quntity = (TextView) findViewById(R.id.text_quntity);
        text_quickpay_fee = (TextView) findViewById(R.id.text_quickpay_fee);
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


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setViews() {
//
//

        catagoriesList = SharedPrefsData.getCatagories(this);
        if (null != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId() && 0 != catagoriesList.getResult().getFarmerDetails().get(0).getClusterId())
            Cluster_id =  catagoriesList.getResult().getFarmerDetails().get(0).getClusterId();
        Log.e("Cluster_id===",Cluster_id+"");

        statecode = catagoriesList.getResult().getFarmerDetails().get(0).getStateCode();
        statename =catagoriesList.getResult().getFarmerDetails().get(0).getStateName();

        created_user = SharedPrefsData.getCreatedUser(Special_Fee.this);

        User_id= created_user.getResult().getUserInfos().getId();

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


        GetQuckPaySummary();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitReq();
            }
        });


    }








    private void GetQuckPaySummary() {
        mdilogue.show();
        JsonObject object = GetReuestobject();
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
                        showDialog(Special_Fee.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetquickpayDetailsModel getquickpayDetailsModel) {
                        if (getquickpayDetailsModel.getListResult() != null) {

                            Log.e("nodada====", "nodata===custom2");
                            text_quntity.setText(" "+dff.format(getquickpayDetailsModel.getListResult().get(0).getQuantity()));

                            if (getquickpayDetailsModel.getListResult().get(0).getFfbFlatCharge() == null) {
                                text_flat_charge.setText(" 0.00");

                            } else {
                                text_flat_charge.setText(" "+df.format(getquickpayDetailsModel.getListResult().get(0).getFfbFlatCharge()));
                            }
                            ffbCostTxt.setText(" "+df.format(getquickpayDetailsModel.getListResult().get(0).getFfbCost()));

                            if (getquickpayDetailsModel.getListResult().get(0).getConvenienceCharge() == null) {
                                convenienceChargeTxt.setText(" 0.00");

                            } else {

                                convenienceChargeTxt.setText("-" + df.format(getquickpayDetailsModel.getListResult().get(0).getConvenienceCharge()));
                            }
                            if(getquickpayDetailsModel.getListResult().get(0).getClosingBalance() > 0.0){
                                closingBalanceTxt.setText(" "+df.format(getquickpayDetailsModel.getListResult().get(0).getClosingBalance()));
                            }else{
                                closingBalanceTxt.setText(" 0.00");
                            }
                            //   totalAmount.setText(df.format(getquickpayDetailsModel.getListResult().get(0).getTotal()));
                            if (getquickpayDetailsModel.getListResult().get(0).getTotal() > 0.0 ) {
                                totalAmount.setText(" "+df.format(getquickpayDetailsModel.getListResult().get(0).getTotal()));
                                // totalAmount.setText("0");

                            } else {
                                totalAmount.setText(" 0.00");
                                // totalAmount.setText(String.valueOf(getquickpayDetailsModel.getListResult().get(0).getTotal()));
                            }
                            text_quickpay_fee.setText("-" + df.format(getquickpayDetailsModel.getListResult().get(0).getQuickPay()));

                        } else {

                        }


                    }


                });
    }

    private JsonObject GetReuestobject() {
        GetQuickpayDetails requestModel = new GetQuickpayDetails();
        requestModel.setFarmerCode(Farmer_code);
        requestModel.setQuantity(total_weight);
        requestModel.setIsSpecialPay(true);
        requestModel.setStateCode(statecode);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();


    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void submitReq() {

        Double d1 = Double.valueOf(totalAmount.getText().toString());

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

                            if (quickPayResponce.getIsSuccess()) {
                                mdilogue.dismiss();
                                submit.setEnabled(false);
                                result = quickPayResponce.getResult();
                                Log.e("result===",result);
                                showSuccesspdf(result);

                            } else {
                                mdilogue.dismiss();
                                showDialog(Special_Fee.this, quickPayResponce.getEndUserMessage());
                            }

                        }


                    });
        } else {
            showDialog(Special_Fee.this,getString(R.string.enter_loan_amount));
        }

    }
    private void showSuccesspdf(String result) {

        mdilogue.show();
        final Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        if (langID == 2)
            updateResources(this, "te");
        else
            updateResources(this, "en-US");
        dialog.setContentView(R.layout.pdf_dialog);
        final WebView webView = dialog.findViewById(R.id.webView);
        TextView request_head = dialog.findViewById(R.id.request_head);
        request_head.setText(getResources().getString(R.string.spcial_pdf));
        Button btn_dialog = dialog.findViewById(R.id.btn_dialog);
        // WebSettings settings = webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);




        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                webView.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
                mdilogue.show();
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
                mdilogue.dismiss();
            }
        });

  PdfWebViewClient pdfWebViewClient = new PdfWebViewClient(Special_Fee.this, webView);
        pdfWebViewClient.loadPdfUrl(
                "https://www.google.co.in/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0ahUKEwjgwIfp3KXSAhXrhFQKHQqEDHYQFggZMAA&url=http%3A%2F%2Fwww.orimi.com%2Fpdf-test.pdf&usg=AFQjCNERYYcSfMLS5ukBcT2Qy11YxEhXqw&cad=rja");
//
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                List<MSGmodel> displayList = new ArrayList<>();

                showSuccessDialog(displayList, getResources().getString(R.string.specialpaysuccess));
//
            }
        });
        dialog.show();

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private JsonObject quickReuestobject() {

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
        requestModel.setIsSpecialPay(true);
        requestModel.setStateName(statename);
        requestModel.setStateCode(statecode);
        requestModel.setFileLocation("");
        if(null != closingBalanceTxt.getText() & !TextUtils.isEmpty(closingBalanceTxt.getText()))
            requestModel.setClosingBalance(Double.parseDouble(closingBalanceTxt.getText().toString()));
        else {
            requestModel.setClosingBalance(0.0);
        }
        //TODO make dynamic

        String val = arrayTOstring(post_ids);
        Log.d(TAG, "------ analysis ------ >> get values in String(): " + val);
        String val_codes = arrayTOstring(post_codes);
        Log.d(TAG, "------ analysis ------ >> get values in String(): " + val_codes);
        requestModel.setCollectionIds(val);

        requestModel.setCollectionCodes(val_codes);
        //  requestModel.setCost(Double.parseDouble(ffbCostTxt.getText().toString()));
        requestModel.setNetWeight(Double.parseDouble(text_quntity.getText().toString()));
        requestModel.setSignatureExtension("");

        requestModel.setSignatureName("");
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


