package in.calibrage.palm360fa.Fragments;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

import in.calibrage.palm360fa.Adapter.PaymentRequestModel;
import in.calibrage.palm360fa.Adapter.TransactionRatesAdapter;
import in.calibrage.palm360fa.Adapter.TransportationAdapter;
import in.calibrage.palm360fa.Model.GetTranspotationCharges;
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


public class TransFragment extends BaseFragment {
    public static String TAG = TransFragment.class.getSimpleName();
    String to_date, from_date;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    private RecyclerView trans_recycle;
    TextView noRecords,ratetext;
    TransportationAdapter pay_adapter;
    String Farmer_code;
    Button transportationratesbtn;
    LinearLayout linear1;
    RecyclerView trans_rates;
    TransactionRatesAdapter ratesAdapter;
    private List<GetTranspotationCharges.TrasportRate> ratelist = new ArrayList<>();
    Button ok_btn;

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

        View rootView = inflater.inflate(R.layout.fragment_trans, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "").trim();
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(getContext())
                .setTheme(R.style.Custom)
                .build();

        trans_recycle = (RecyclerView) rootView.findViewById(R.id.trans_recycler_view);
        noRecords = (TextView) rootView.findViewById(R.id.text);
        trans_recycle.setHasFixedSize(true);
        trans_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        transportationratesbtn = (Button) rootView.findViewById(R.id.transportationratesbtn);

        pay_adapter = new TransportationAdapter(getContext());
        trans_recycle.setAdapter(pay_adapter);




//
        return rootView;
    }

    private void Showtransactionrates() {
        final Dialog dialog = new Dialog(getContext(), R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.transactionrates);
        trans_rates = (RecyclerView) dialog.findViewById(R.id.trans_rates);
        ok_btn = dialog.findViewById(R.id.btn_dialog);
        ratetext = dialog.findViewById(R.id.ratetext);
        trans_rates.setHasFixedSize(true);
        trans_rates.setLayoutManager(new LinearLayoutManager(getContext()));
        ratesAdapter = new TransactionRatesAdapter(getContext(),ratelist);
        trans_rates.setAdapter(ratesAdapter);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog.dismiss();

            }
        });
        dialog.show();

    }

    private BroadcastReceiver mNotificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            to_date = intent.getStringExtra("todate");
            from_date = intent.getStringExtra("fromdate");
            trans_recycle.setVisibility(View.GONE);

            if (to_date.equalsIgnoreCase("clear")) {
                noRecords.setVisibility(View.GONE);
                pay_adapter.clearAllDataa();

            } else {
                noRecords.setVisibility(View.VISIBLE);
                if (isOnline(getContext()))
                    GetTranspotationChargesByFarmerCode();
                else {
                    showDialog(getActivity(), getResources().getString(R.string.Internet));
                }
            }
            Log.e("roja=====", to_date + "=====" + from_date);

            transportationratesbtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (isOnline(getContext())) {
                        Showtransactionrates();

                    } else {
                        showDialog(getActivity(), getResources().getString(R.string.Internet));
                    }
                }
            });
        }
    };

    private void GetTranspotationChargesByFarmerCode() {
        pay_adapter.clearAllDataa();

        mdilogue.show();
        JsonObject object = paymenObject();
        ApiService service = ServiceFactory.createRetrofitService(getContext(), ApiService.class);
        mSubscription = service.posttrans(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetTranspotationCharges>() {
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
                        Log.e("error==",e.getLocalizedMessage());
                        mdilogue.dismiss();
                        pay_adapter.clearAllDataa();

                        showDialog(getActivity(), getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final GetTranspotationCharges getTranspotationCharges) {
                        mdilogue.dismiss();

                        Log.d(TAG, "onNext:payment " + getTranspotationCharges);

                        try {
                            if (getTranspotationCharges.getTranspotationCharges().size() != 0) {
                                trans_recycle.setVisibility(View.VISIBLE);
                                noRecords.setVisibility(View.GONE);

                                pay_adapter.updateData(getTranspotationCharges.getTranspotationCharges());


                                if(getTranspotationCharges.getTranspotationCharges().size()== 0 && getTranspotationCharges.getTranspotationCharges() == null){
                                    noRecords.setVisibility(View.VISIBLE);

                                    trans_recycle.setVisibility(View.GONE);
                                }

                            } else {
                                noRecords.setVisibility(View.VISIBLE);
                                //
                                trans_recycle.setVisibility(View.GONE);
                            }

                            if (getTranspotationCharges.getTrasportRates() != null) {
                                ratelist = getTranspotationCharges.getTrasportRates();

                            }


//                                transportationratesbtn.setOnClickListener(new View.OnClickListener() {
//
//                                    @Override
//                                    public void onClick(View view) {
//
//                                        if (isOnline(getContext())) {

//
//                                        } else {
//                                            showDialog(getActivity(), getResources().getString(R.string.Internet));
//                                        }
//                                    }
//                                });

                        } catch (Exception e) {
                            Log.e("Exception.==",e.getLocalizedMessage());
                            e.printStackTrace();
                        }



                    }


                });
    }

    private JsonObject paymenObject() {
        Log.e("roja=====176",to_date+"=====" + from_date);
        PaymentRequestModel requestModel = new PaymentRequestModel();
        //TODO need to save in shared pref
        /*
         * remove fist 2 letters from former code and add v
         * */


        requestModel.setVendorCode(Farmer_code);
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
}
