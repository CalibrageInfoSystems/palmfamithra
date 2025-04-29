package in.calibrage.palm360fa.Views.Activities;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

import in.calibrage.palm360fa.Adapter.Req_producutt_Adapter;
import in.calibrage.palm360fa.Adapter.producut_Adapter;
import in.calibrage.palm360fa.Model.Product_new;
import in.calibrage.palm360fa.Model.Resproduct;
import in.calibrage.palm360fa.R;
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

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;


public class product_list extends BaseActivity {
    String id_holder;
    private SpotsDialog mdilogue;
    RecyclerView recycler_view_products;
    private producut_Adapter mAdapter;
    TextView requst_code;
    //LinearLayout noRecords;
    private Subscription mSubscription;
    private List<Product_new> product_List = new ArrayList<>();
    private TextView text_amount, Final_amount, gst_amount, subsidy_amount, paybleamount,sgst_amount,cgst_amount;
    double valueRounded;
    DecimalFormat df = new DecimalFormat("####0.00");
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
        setContentView(R.layout.activity_product_list);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        ImageView backImg = (ImageView) findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageView home_btn = (ImageView) findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        requst_code =(TextView) findViewById(R.id.requst_code);
        id_holder = getIntent().getStringExtra("Name");
        requst_code.setText(": " + id_holder);
        Log.e("id_holder===", id_holder);
        recycler_view_products = (RecyclerView) findViewById(R.id.products_recy);
        text_amount = (TextView) findViewById(R.id.amount);
        sgst_amount =(TextView) findViewById(R.id.sgst_amount);
        cgst_amount =(TextView) findViewById(R.id.cgst_amount);
        Final_amount = (TextView) findViewById(R.id.final_amount_gst);
        gst_amount = (TextView) findViewById(R.id.gst_amount);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view_products.setLayoutManager(mLayoutManager);
        recycler_view_products.setItemAnimator(new DefaultItemAnimator());
        if (isOnline())
            GetProductDetailsByRequestCode();
        else {
            showDialog(product_list.this, getResources().getString(R.string.Internet));
        }
    }

    private void GetProductDetailsByRequestCode() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getLoan(APIConstantURL.GetProductDetailsByRequestCode + id_holder)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Resproduct>() {
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
                        showDialog(product_list.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(Resproduct resproduct) {
                        if (resproduct.getListResult() != null) {
                            Req_producutt_Adapter adapter = new Req_producutt_Adapter(product_list.this, resproduct.getListResult());
                            recycler_view_products.setAdapter(adapter);

                            Double amount_total = 0.00;
                            Double total_amount = 0.00;
                            Double gst_amountt =0.0;
                            for (int i = 0; i < resproduct.getListResult().size(); i++) {
                                if (null != resproduct.getListResult().get(i).getAmount()) {
                                    amount_total = amount_total + resproduct.getListResult().get(i).getBasePrice();

                                    total_amount = total_amount + resproduct.getListResult().get(i).getAmount();


                                    gst_amountt=total_amount-amount_total;


                                    valueRounded =(gst_amountt * 100D) / 100D;
                                    Log.e("valueRounded===",valueRounded+"");
                                }
//                                Log.e("amount_total====127", amount_total + "");
//                                if (null != resproduct.getListResult().get(i).getCgst() && null != resproduct.getListResult().get(i).getSgst()){
//                                    gst_amountt = gst_amountt + resproduct.getListResult().get(i).getCgst() + resproduct.getListResult().get(i).getSgst();
                                // }
                                // text_amount.setText(resproduct.getListResult().get(i).getAmount()+"");

                            }

                            text_amount.setText(df.format(amount_total));
                            Final_amount.setText(df.format(total_amount));
                            gst_amount.setText(df.format(valueRounded));
                            double cgst =(double) gst_amountt/2;

                            sgst_amount.setText(df.format(cgst));
                            cgst_amount.setText(df.format(cgst));
                        } else {
                            Log.e("data", "No==have");
                        }
                    }


                });
    }


}
