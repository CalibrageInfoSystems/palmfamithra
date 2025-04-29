package in.calibrage.palm360fa.Fragments;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;


import in.calibrage.palm360fa.Adapter.PaymentAdapter;
import in.calibrage.palm360fa.Adapter.PaymentRequestModel;
import in.calibrage.palm360fa.Adapter.PaymentResponseModel;
import in.calibrage.palm360fa.BuildConfig;
import in.calibrage.palm360fa.Model.ExportPayments;
import in.calibrage.palm360fa.Model.GetBankDetailsByFarmerCode;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseFragment;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;
import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class PaymentFragment extends BaseFragment {
    public static String TAG = PaymentFragment.class.getSimpleName();
    String to_date, from_date;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    private RecyclerView Payment_recycle;
    TextView noRecords, ffb, totalBalance;
    PaymentAdapter pay_adapter;
    String Farmer_code;
    LinearLayout linear1;
    private LinearLayout btnDownload,Downloaded_files;
    private PaymentResponseModel paymentResponseModelEXCEl;
    public static final String PROGRESS_UPDATE = "progress_update";
    private static final int PERMISSION_REQUEST_CODE = 1;

    DecimalFormat dff = new DecimalFormat("######0");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final int langID = SharedPrefsData.getInstance(getContext()).getIntFromSharedPrefs("lang");
        if (langID == 2)
            updateResources(getContext(), "te");
        else if (langID == 3)
            updateResources(getContext(), "kan");
        else
            updateResources(getContext(), "en-US");
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_payment, container, false);


        SharedPreferences pref = getActivity().getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(getContext())
                .setTheme(R.style.Custom)
                .build();

        Payment_recycle = (RecyclerView) rootView.findViewById(R.id.payment_recycler_view);
        noRecords = (TextView) rootView.findViewById(R.id.text);
        ffb = (TextView) rootView.findViewById(R.id.ffb_total);
        btnDownload = (LinearLayout) rootView.findViewById(R.id.btnDownload);
        linear1 = (LinearLayout) rootView.findViewById(R.id.linear1);
        totalBalance = (TextView) rootView.findViewById(R.id.totalBalance);
        Downloaded_files=(LinearLayout) rootView.findViewById(R.id.btnDownloaded);
        Payment_recycle.setHasFixedSize(true);
        Payment_recycle.setLayoutManager(new LinearLayoutManager(getContext()));

        // recyclerView.setAdapter(adapter);
//        if (isOnline(getContext()))
//            getPaymentDetails();
//        else {
//            showDialog(getActivity(), getResources().getString(R.string.Internet));
//
//        }
        pay_adapter = new PaymentAdapter(getContext());
        Payment_recycle.setAdapter(pay_adapter);


//
//        nameFragTxt.setText("NAME : "+name);
//        yearFragTxt.setText("YEAR : "+String.valueOf(year));
        return rootView;
    }

    private void ExportPayments() {
        mdilogue.show();
        JsonObject object = ExportObject();
        ApiService service = ServiceFactory.createRetrofitService(getContext(), ApiService.class);
        mSubscription = service.postexport(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
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

                               @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                               @Override
                               public void onNext(String base64String) {
                                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                       isStoragePermissionGranted();
                                   }

                                   if(base64String!= null) {
                                       Log.e("base64String===", base64String);
                                       // String base64String = "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";
                                       //byte[] decoded = Base64.decode(base64String, 0);
                                       // Log.e("~~~~~~~~ Decoded: ", Arrays.toString(decoded));
//
                                       byte[] inData = Base64.decode(base64String, 0);
//                                   for (KanboardTaskFile f: files) {
//                                       if (f.getId() == id) {
                                       try {
                                           Date date = new Date();
                                           SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
                                           System.out.println(formatter.format(date));
                                           File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), String.valueOf("3F_"+formatter.format(date) + ".xlsx"));
                                           Log.e("file ==",file.getAbsolutePath());
                                           FileOutputStream outData = new FileOutputStream(file);
                                           outData.write(inData);
                                           outData.close();
                                           String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString()));
                                           if (mime == null) {
                                               mime = "application/octet-stream";
                                           }
                                           if (BuildConfig.DEBUG) {
                                               Log.d("Roja==", Uri.fromFile(file).toString());
                                               Log.d("Roja==", mime);
                                           }
                                           DownloadManager dm = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);

                                           dm.addCompletedDownload( file.getName(), "3F Akshaya", false, mime, file.getPath(), file.length(), true);


                                           Toast.makeText(getContext(), getResources().getString(R.string.downloaded), Toast.LENGTH_SHORT).show();
                                       } catch (IOException e) {
                                           Log.w("Roja==", "IOError writing file");
                                           e.printStackTrace();
                                       }

                                   }


                                  // Snackbar.make(findViewById(R.id.root_layout), "Unable to download file", Snackbar.LENGTH_LONG).show();
                               }

//



                });

//


    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }


    private JsonObject ExportObject() {
//
        ExportPayments.BankDetails requestModel = new ExportPayments.BankDetails() ;
        //TODO need to save in shared pref

        GetBankDetailsByFarmerCode bankdetails;
        bankdetails = SharedPrefsData.getbankdetails(getContext());
Log.e("bankdetails==", String.valueOf(bankdetails));
        Log.e("bankdetails==name", bankdetails.getListResult().get(0).getAccountHolderName());
        requestModel.setAccountHolderName(bankdetails.getListResult().get(0).getAccountHolderName());
        requestModel.setFarmerCode(bankdetails.getListResult().get(0).getFarmerCode());
        requestModel.setAccountNumber(bankdetails.getListResult().get(0).getAccountNumber());
        requestModel.setBankName(bankdetails.getListResult().get(0).getBankName());
        requestModel.setBranchName(bankdetails.getListResult().get(0).getBranchName());

        requestModel.setIfscCode(bankdetails.getListResult().get(0).getIfscCode());
        requestModel.setGuardianName(bankdetails.getListResult().get(0).getGuardianName());
        requestModel.setState(bankdetails.getListResult().get(0).getState());
        requestModel.setDistrict(bankdetails.getListResult().get(0).getDistrict());
        requestModel.setMandal(bankdetails.getListResult().get(0).getMandal());
        requestModel.setVillage(bankdetails.getListResult().get(0).getVillage());

        List<ExportPayments.PaymentResponce> paymentinfo = new ArrayList<>();

        for (int i = 0; i < paymentResponseModelEXCEl.getResult().getPaymentResponce().size(); i++) {

            ExportPayments.PaymentResponce payment = new ExportPayments.PaymentResponce();
            payment.setCardCode(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getCardCode());
            payment.setCardName(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getCardName());
            payment.setUVillage(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getUVillage());
            payment.setUMandal(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getUMandal());
            payment.setUFHname(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getUFHname());
            payment.setBankName(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getBankName());
            payment.setBankAccount(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getBankAccount());
            payment.setBranch(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getBranch());
            payment.setRefDate(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getRefDate());
            payment.setQuantity(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getQuantity());
            if(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getMemo()!=null) {
                payment.setMemo(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getMemo() + "");
            }
            else {
                payment.setMemo(" ");
            }
            payment.setObAmount(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getObAmount());

            int amt = (int) Math.round(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getAmount());
            int AdhocRate = (int) Math.round(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getAdhocRate());
            int InvoiceRate = (int) Math.round(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getInvoiceRate());
            int GRAmount = (int) Math.round(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getGRAmount());
            int Adjusted = (int) Math.round(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getAdjusted());
            payment.setAmount(amt);
            payment.setAdhocRate(AdhocRate);
            payment.setInvoiceRate(InvoiceRate);
            payment.setOb(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getOb());
            payment.setCb(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getCb());
            payment.setGRAmount( GRAmount);
            payment.setAdjusted(Adjusted);
            int bal = (int) Math.round(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getBalance());
            if (paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getBalance() < 0) {
                String balance1 = bal + "" + ")";
                //   String balance1 = dff.format(paymentResponseModelEXCEl.getResult().getPaymentResponce().get(i).getBalance())+ "";
                payment.setBalance(balance1.replace("-", "(") + " ");
            } else {
                payment.setBalance(bal + "  ");
            }



            paymentinfo.add(payment);
        }
        ExportPayments requestModel1 = new ExportPayments(requestModel,
                paymentResponseModelEXCEl.getResult().getTotalQuanitity(), paymentResponseModelEXCEl.getResult().getTotalGRAmount(),
                paymentResponseModelEXCEl.getResult().getTotalAdjusted(), paymentResponseModelEXCEl.getResult().getTotalAmount(),
                paymentResponseModelEXCEl.getResult().getTotalBalance(), paymentinfo);


        //ExportPayments payments =new ExportPayments(bankdetails,)
        return new Gson().toJsonTree(requestModel1).getAsJsonObject();
    }

    private BroadcastReceiver mNotificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            to_date = intent.getStringExtra("todate");
            from_date = intent.getStringExtra("fromdate");
            Payment_recycle.setVisibility(View.GONE);

            if (to_date.equalsIgnoreCase("clear")) {
                pay_adapter.clearAllDataa();
                linear1.setVisibility(View.GONE);
            } else {
                linear1.setVisibility(View.VISIBLE);
                if (isOnline(getContext()))
                    getPaymentDetails();
                else {
                    showDialog(getActivity(), getResources().getString(R.string.Internet));
                }
            }
            Log.e("roja=====", to_date + "=====" + from_date);

            if(  linear1.getVisibility() == View.GONE) {
                btnDownload.setVisibility(View.GONE);
                Downloaded_files.setVisibility(View.GONE);
            }else {
                btnDownload.setVisibility(View.VISIBLE);
                Downloaded_files.setVisibility(View.VISIBLE);
            }
            btnDownload.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

//
                    if (isOnline(getContext())) {
                        ExportPayments();

                    } else {
                        showDialog(getActivity(), getResources().getString(R.string.Internet));
                    }
                }
            });
            Downloaded_files.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
//                    try {
//                        File   file= new File(Environment.DIRECTORY_DOWNLOADS);
//                        if (!file.exists()) {
//                            file.mkdirs();
//                        }
//
//                        MimeTypeMap map = MimeTypeMap.getSingleton();
//                        String ext = MimeTypeMap.getFileExtensionFromUrl(file.getName());
//                        String type = map.getMimeTypeFromExtension(ext);
//
//                        if (type == null)
//                            type = "*/*";
//
////
//                        Uri uri = Uri.parse(Environment.getDownloadCacheDirectory().getPath().toString());
////
//
//                        Intent intent = new Intent();
//                        intent.setAction(Intent.ACTION_VIEW);
//                     //   intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
//                        // intent.addCategory(Intent.CATEGORY_OPENABLE);
//                        intent.setDataAndType(uri, type);
//
//
//                        startActivityForResult(intent, 1);
//                    } catch (Exception e) {
//                        Log.e("error==",e.getLocalizedMessage());
//                        e.printStackTrace();
//                    }

                }
            });
          //  registerReceiver();
        }
    };




    private void getPaymentDetails() {
        pay_adapter.clearAllDataa();
        mdilogue.show();
        JsonObject object = paymenObject();
        ApiService service = ServiceFactory.createRetrofitService(getContext(), ApiService.class);
        mSubscription = service.postpayment(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PaymentResponseModel>() {
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
                        pay_adapter.clearAllDataa();
                        showDialog(getActivity(), getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(PaymentResponseModel paymentResponseModel) {
                        mdilogue.dismiss();

                        paymentResponseModelEXCEl = paymentResponseModel;
                        Log.d(TAG, "onNext:payment " + paymentResponseModel);
                        try {
                            if (paymentResponseModel.getResult().getPaymentResponce() != null &&paymentResponseModel.getResult().getPaymentResponce().size()!=0 ) {
                                Payment_recycle.setVisibility(View.VISIBLE);
                                noRecords.setVisibility(View.GONE);

                                pay_adapter.updateData(paymentResponseModel.getResult().getPaymentResponce());
                                Log.d("TotalQuanitity===", paymentResponseModel.getResult().getTotalQuanitity() + "");
                                double TotalQuanitity = paymentResponseModel.getResult().getTotalQuanitity();

                                DecimalFormat df = new DecimalFormat("#,###,##0.000");
                                System.out.println(df.format(TotalQuanitity));
                                //

                                ffb.setText((df.format(TotalQuanitity)));
                                if (paymentResponseModel.getResult().getTotalBalance() == null) {
                                    totalBalance.setText("0.00");

                                } else {
                                    totalBalance.setText(dff.format(paymentResponseModel.getResult().getTotalBalance()));
                                }

                                if (paymentResponseModel.getAffectedRecords() == 0) {
                                    noRecords.setVisibility(View.VISIBLE);
                                    linear1.setVisibility(View.GONE);
                                    Payment_recycle.setVisibility(View.GONE);
                                    btnDownload.setVisibility(View.GONE);
                                    Downloaded_files.setVisibility(View.GONE);
                                }


                            } else {
                                noRecords.setVisibility(View.VISIBLE);
                                linear1.setVisibility(View.GONE);
                                //
                                Payment_recycle.setVisibility(View.GONE);
                                btnDownload.setVisibility(View.GONE);
                                Downloaded_files.setVisibility(View.GONE);

                            }
                        } catch (Exception e) {
                            Log.e("Exception.==", e.getLocalizedMessage());
                            e.printStackTrace();
                        }
                    }


                });
    }

    private JsonObject paymenObject() {
        Log.e("roja=====176", to_date + "=====" + from_date);
        PaymentRequestModel requestModel = new PaymentRequestModel();
        //TODO need to save in shared pref
        /*
         * remove fist 2 letters from former code and add v
         * */

        String text;
        text = Farmer_code.substring(1);
        text = text.substring(1);


        String finalstring = "V" + text;

        Log.i("VendorCode", finalstring);
        requestModel.setVendorCode(finalstring);
        requestModel.setToDate(to_date);
        requestModel.setFromDate(from_date);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }


    @Override
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(mNotificationReceiver, new IntentFilter("KEY"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(mNotificationReceiver);
    }
//
}