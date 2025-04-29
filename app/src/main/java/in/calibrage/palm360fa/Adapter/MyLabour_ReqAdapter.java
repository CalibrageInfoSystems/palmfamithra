package in.calibrage.palm360fa.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;

import in.calibrage.palm360fa.Model.DeleteObject;
import in.calibrage.palm360fa.Model.GetLabourPackageDiscount;
import in.calibrage.palm360fa.Model.Resdelete;
import in.calibrage.palm360fa.Model.labour_req_response;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.AnimationUtil;
import in.calibrage.palm360fa.common.CommonUtil;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MyLabour_ReqAdapter extends RecyclerView.Adapter<MyLabour_ReqAdapter.ViewHolder> {
    TextView request_id, plot_code, plot_size, village, leader_name, pref_date, service_type, status, prun_amount, harv_amount, pruning_intercrop, harvest_intercrop, pack_name, collectionid, netweight,
            Discount_percentage, txt_assign_date,amount,txt_pin, Discount_amount, comments, intercrop_tresscount, intercrop_amount, intercrop_netweight, intercrop_harv_amount, job_done, trees_count, service_charge, service_amount, total_prunning_amount, total_harvesting_amount;
    Button cancel_btn, ok_btn;
    private List<labour_req_response.ListResult> labourlist_Set = new ArrayList<>();
    public Context mContext;
    String request_date, prefferdate, currentDate, jobdone, prefferdate_popup,assigndate;
    private SpotsDialog mdilogue;
    private Subscription mSubscription;
    DecimalFormat df = new DecimalFormat("####0.00");
    DecimalFormat dff = new DecimalFormat("####0.000");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
    String seleced_Duration;
    int discount;
    // double total_amount = Double.parseDouble(df.format(finalwithGST));
    RelativeLayout new_data, coll_label, label_netweight, label_amount, lin_comments, trees_lable, label_prunning, label_harv, label_amount_service,date_label;
    private Reqlister reqlister;
    RelativeLayout prunning_label, Harvesting_label, prunning_intercrop_label, harvesting_intercrop_label;
    // RecyclerView recyclerView;
    String selectedItemID;
    int selectedPO;
    DecimalFormat dec = new DecimalFormat("####0.00");
    double total_prunning, total_hav, Total_amount, intercrop_prunning, intercrop_harvesting;
    LinearLayout linear;

    public MyLabour_ReqAdapter(List<labour_req_response.ListResult> labourlist_Set, Context ctx) {
        this.labourlist_Set = labourlist_Set;
        this.mContext = ctx;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.my_req_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.txtPlotId.setText(labourlist_Set.get(position).getPlotCode());

        try {

            Date prefferdatee = input.parse(labourlist_Set.get(position).getStartDate());

            prefferdate = output.format(prefferdatee);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.txtDate.setText(labourlist_Set.get(position).getLeader() + "");
        //   holder.txtApproveDate.setText(labourlist_Set.get(position).getLeader()+"");
        if ((holder.txtDate.getText().equals("null")) || holder.txtDate.getText() == "null") {
            holder.txtDate.setVisibility(View.GONE);
            holder.line_labour.setVisibility(View.GONE);
        } else {
            holder.txtDate.setVisibility(View.VISIBLE);
            holder.line_labour.setVisibility(View.VISIBLE);
        }
        //   holder.txtTime.setText(superHero.getTime() 0.6);
        holder.txtDateNTime.setText(dec.format(labourlist_Set.get(position).getPalmArea()) + " " + "Ha (" + dec.format(labourlist_Set.get(position).getPalmArea() * 2.5) + " Acre)");
        //  holder.txtDateNTime.setText("0.6" + " " + "Ha");
        holder.txtReqDate.setText(labourlist_Set.get(position).getPlotVillage());
        holder.txtApproveDate.setText(prefferdate);

        holder.req_code.setText(labourlist_Set.get(position).getRequestCode());
        holder.txtStatus.setText(labourlist_Set.get(position).getStatusType());

//        if(labourlist_Set.get(position).getStatusType().equals("Closed") && labourlist_Set.get(position).getStatusType().equalsIgnoreCase("Closed")){
//            holder.cancel.setVisibility(View.GONE);
//            Log.e("staus====",labourlist_Set.get(position).getStatusType());
//        }
//        else {
//            Log.e("cancel====",labourlist_Set.get(position).getStatusType());
//            holder.cancel.setVisibility(View.VISIBLE);
//        }
//        if (!"Closed".equals(holder.txtStatus.getText())) {
//            holder.cancel.setVisibility(View.VISIBLE);
//
//        } else {
//            holder.cancel.setVisibility(View.GONE);
//        }
//        if (!"Cancelled".equals(holder.txtStatus.getText())) {
//            holder.cancel.setVisibility(View.VISIBLE);
//
//        } else {
//            holder.cancel.setVisibility(View.GONE);
//        }
        //todo
        Log.e("statustype++++id", labourlist_Set.get(position).getStatusTypeId() + "");
//        if(labourlist_Set.get(position).getStatusTypeId()!= 18 && labourlist_Set.get(position).getStatusTypeId()!=32  && labourlist_Set.get(position).getStatusTypeId()!=39){
//            holder.cancel.setVisibility(View.VISIBLE);
//
//        } else {
//            holder.cancel.setVisibility(View.GONE);
//
//        }
        holder.txtname.setText(labourlist_Set.get(position).getServiceTypes());


        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
            holder.details.setBackgroundColor(mContext.getColor(R.color.white2));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));
            holder.details.setBackgroundColor(mContext.getColor(R.color.light_gray2));

        }
        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        holder.cancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                selectedItemID = labourlist_Set.get(position).getRequestCode();
//                selectedPO = position;
//                showConformationDialog(selectedPO);
////                selectedItemID = labourlist_Set.get(position).getRequestCode();
////                selectedPO = position;
////                try {
////                    delete_request();
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
//
//            }
//
//        });
        holder.details.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                selectedItemID = labourlist_Set.get(position).getRequestCode();
                selectedPO = position;
                Log.d("selectedPO==", selectedPO + "");
               // GetLabourPackageDiscount(selectedPO);
                showCondetailsDialog(selectedPO);


            }

        });
        AnimationUtil.animate(holder, true);
    }

    private void GetLabourPackageDiscount(final int selectedPO) {
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.getdiscount(APIConstantURL.GetLabourPackageDiscount)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetLabourPackageDiscount>() {
                    @Override
                    public void onCompleted() {

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

                        //showDialog(LabourActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetLabourPackageDiscount getLabourPackageDiscount) {


//                        for (int j = 0; j < getLabourPackageDiscount.getListResult().size(); j++) {
//
//                            String Second=getLabourPackageDiscount.getListResult().get(j).getDesc();
//                            //Log.e("seleced_period===249", getLabourPackageDiscount.getListResult().get(j).getDesc());
//                            Log.d("MAHESH:","ONE :"+seleced_Duration );
//                            Log.d("MAHESH:","TWO :"+Second );
//                            //  seleced_Duration = getLabourPackageDiscount.getListResult().get(j).getDesc();
//                            if (seleced_Duration.equalsIgnoreCase(Second)) {
//                                discount = getLabourPackageDiscount.getListResult().get(j).getDiscountPercentage();
//                                Log.e("discount===253", discount + "");
////
//
//                            }
//                        }

                        if (getLabourPackageDiscount.getIsSuccess()) {
                            seleced_Duration = labourlist_Set.get(selectedPO).getDuration();
                            Log.e("seleced_Duration===246", seleced_Duration);
                            // discount=0;
                            for (int i = 0; i < getLabourPackageDiscount.getListResult().size(); i++) {
                                String Data = getLabourPackageDiscount.getListResult().get(i).getDesc();
                                Log.d("MAHESH ", "discount Name :" + Data);
                                if (Data.equalsIgnoreCase(seleced_Duration)) {
                                    discount = getLabourPackageDiscount.getListResult().get(i).getDiscountPercentage();
                                    Log.d("MAHESH ", "discount :" + discount);
                                }
                            }

                        }

                        showCondetailsDialog(selectedPO);
                    }
                });
    }

    private void showConformationDialog(final int selectedPO) {
        TextView dialogMessage;
        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_cancel);
        dialogMessage = dialog.findViewById(R.id.dialogMessage);
        dialogMessage.setText(mContext.getString(R.string.alert_msg));
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        ok_btn = dialog.findViewById(R.id.ok_btn);
/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    delete_request();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
            }
        });

/**
 * @param OnClickListner
 */
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private void showCondetailsDialog(int selectedPO) {
        //myDialog.setContentView(R.layout.custompopup);
        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  dialog.setCancelable(false);
        //    dialog.getWindow().setLayout(((getWidth(mContext) / 100) * 100), LinearLayout.LayoutParams.MATCH_PARENT);
//        dialog.getWindow().setGravity(Gravity.LEFT);
//        dialog.show();
        dialog.setContentView(R.layout.dialog_ditails);
        // grossWeight,tareWeight,totalBunches,acceptedBunches,rejectedBunches,operatorname;
        request_id = dialog.findViewById(R.id.request_id);
        linear = dialog.findViewById(R.id.linear);
        plot_code = dialog.findViewById(R.id.plot_code);
        plot_size = dialog.findViewById(R.id.plot_size);
        village = dialog.findViewById(R.id.village);
        leader_name = dialog.findViewById(R.id.leader_name);
        pref_date = dialog.findViewById(R.id.pref_date);
        service_type = dialog.findViewById(R.id.service_type);
        status = dialog.findViewById(R.id.status);
        prun_amount = dialog.findViewById(R.id.prun_amount);
        harv_amount = dialog.findViewById(R.id.harv_amount);
        pruning_intercrop = dialog.findViewById(R.id.pruning_intercrop);
        harvest_intercrop = dialog.findViewById(R.id.harvest_intercrop);
        pack_name = dialog.findViewById(R.id.pack_name);
        Discount_amount = dialog.findViewById(R.id.discount_amount);
        Discount_percentage = dialog.findViewById(R.id.discount_percentage);
        intercrop_tresscount = dialog.findViewById(R.id.prun_intercrop_num);
        intercrop_amount = dialog.findViewById(R.id.intercrop_amount);
        intercrop_netweight = dialog.findViewById(R.id.harv_weight_intercrop);
        intercrop_harv_amount = dialog.findViewById(R.id.harv_amt_intercrop);
        // collectionid= dialog.findViewById(R.id.ids_collection);
        netweight = dialog.findViewById(R.id.netweight);
        amount = dialog.findViewById(R.id.amount);
        job_done = dialog.findViewById(R.id.job_done);
        new_data = dialog.findViewById(R.id.new_data);
        coll_label = dialog.findViewById(R.id.lin_collection_ids);
        label_netweight = dialog.findViewById(R.id.label_netweight);
        date_label =dialog.findViewById(R.id.date_label);
        label_amount = dialog.findViewById(R.id.label_amount);
        lin_comments = dialog.findViewById(R.id.lin_comments);
        trees_lable = dialog.findViewById(R.id.lin_new1);
        label_prunning = dialog.findViewById(R.id.lin_new2);
        label_harv = dialog.findViewById(R.id.label_harv_amount);
        trees_count = dialog.findViewById(R.id.trees_count);
        service_charge = dialog.findViewById(R.id.service_charge);
        label_amount_service = dialog.findViewById(R.id.amount_service);
        service_amount = dialog.findViewById(R.id.service_amount);
        total_prunning_amount = dialog.findViewById(R.id.prun_total_amount);
        total_harvesting_amount = dialog.findViewById(R.id.harv_amountt);
        ok_btn = dialog.findViewById(R.id.btn_dialog);
        txt_pin= dialog.findViewById(R.id.txt_pin);
        txt_assign_date=dialog.findViewById(R.id.txt_assign_date);

        prunning_label = dialog.findViewById(R.id.prunning_label);
        Harvesting_label = dialog.findViewById(R.id.harvest_label);
        prunning_intercrop_label = dialog.findViewById(R.id.prunning_intercrop_label);
        harvesting_intercrop_label = dialog.findViewById(R.id.harvest_inter_label);
        request_id.setText(labourlist_Set.get(selectedPO).getRequestCode());

        plot_code.setText(labourlist_Set.get(selectedPO).getPlotCode());
        txt_pin.setText(labourlist_Set.get(selectedPO).getPin()+"");

        plot_size.setText((dec.format(labourlist_Set.get(selectedPO).getPalmArea()) + " " + "Ha (" + dec.format(labourlist_Set.get(selectedPO).getPalmArea() * 2.5) + " Acre)"));
        village.setText(labourlist_Set.get(selectedPO).getPlotVillage());
        leader_name.setText(labourlist_Set.get(selectedPO).getLeader() + "");
        Date prefferdatee = null;
        try {
            prefferdatee = input.parse(labourlist_Set.get(selectedPO).getStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        prefferdate_popup = output.format(prefferdatee);
        pref_date.setText(prefferdate_popup);

        //  Discount_percentage.setText(discount + "");
        service_type.setText(labourlist_Set.get(selectedPO).getServiceTypes());
        status.setText(labourlist_Set.get(selectedPO).getStatusType());
        if (labourlist_Set.get(selectedPO).getPruningAmount() != 0.0) {
            prunning_label.setVisibility(View.VISIBLE);
            prun_amount.setText(df.format(labourlist_Set.get(selectedPO).getPruningAmountWithChange()));
        } else {
            prunning_label.setVisibility(View.GONE);
        }
        if (labourlist_Set.get(selectedPO).getHarvestingAmount() != 0.0 ) {
            Harvesting_label.setVisibility(View.VISIBLE);
            harv_amount.setText(df.format(labourlist_Set.get(selectedPO).getHarvestingAmountWithCharge()));
        } else {
            Harvesting_label.setVisibility(View.GONE);
        }

        pack_name.setText(labourlist_Set.get(selectedPO).getDuration() + "");
        if (labourlist_Set.get(selectedPO).getPruningWithIntercropAmount() != 0.0) {
            prunning_intercrop_label.setVisibility(View.VISIBLE);
            pruning_intercrop.setText(df.format(labourlist_Set.get(selectedPO).getPruningWithIntercropAmountAndCharge()));
        } else {
            prunning_intercrop_label.setVisibility(View.GONE);
        }
        if (labourlist_Set.get(selectedPO).getHarvestingWithIntercropAmount() != 0.0) {
            harvesting_intercrop_label.setVisibility(View.VISIBLE);
            harvest_intercrop.setText(df.format(labourlist_Set.get(selectedPO).getHarvestingWithIntercropAmountAndCharge()));
        } else {
            harvesting_intercrop_label.setVisibility(View.GONE);
        }
        try {



            if(labourlist_Set.get(selectedPO).getAssignedDate()!=null){

                Date assign = input.parse(labourlist_Set.get(selectedPO).getAssignedDate());
                assigndate = output.format(assign);
                txt_assign_date.setText(assigndate);
            }

            else{
                date_label.setVisibility(View.GONE);
            }


            if (labourlist_Set.get(selectedPO).getJobDoneDate() != null) {
                Date job_done = input.parse(labourlist_Set.get(selectedPO).getJobDoneDate() + "");
                jobdone = output.format(job_done);

            }else{
                lin_comments.setVisibility(View.GONE);
            }
            Log.e("job_donedatee=======1",jobdone+"=="+labourlist_Set.get(selectedPO).getRequestCode());
            Log.e("assigndatee=======1",assigndate+"=="+labourlist_Set.get(selectedPO).getRequestCode());
            Log.e("prefferdatee=======1",prefferdate+"=="+labourlist_Set.get(selectedPO).getRequestCode());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        job_done.setText(jobdone);
        //   collectionid.setText(labourlist_Set.get(selectedPO).getCollectionIds()+"");
        //  netweight.setText(labourlist_Set.get(selectedPO).getNetWeight()+"");


        //service_charge.setText(labourlist_Set.get(selectedPO).getServiceChargePercentage()+"");


        int percentages = (int) Math.round(Float.parseFloat(labourlist_Set.get(selectedPO).getServiceChargePercentage() + ""));
        Log.e("tress====234", String.valueOf(percentages));
        service_charge.setText(percentages + "");




        if (prun_amount.getText() != null && labourlist_Set.get(selectedPO).getTreesCount() != null && !prun_amount.getText().toString().isEmpty()&& labourlist_Set.get(selectedPO).getPruningAmount() != 0.0) {

            double prunning = Double.parseDouble(prun_amount.getText() + "");
            double tress = Double.parseDouble(labourlist_Set.get(selectedPO).getTreesCount() + "");
            total_prunning = tress * prunning;
            total_prunning_amount.setText(df.format(total_prunning));
            int value = (int) Math.round((Double) labourlist_Set.get(selectedPO).getTreesCount());
            trees_count.setText(value + "");
            Log.e("tress====236", String.valueOf(total_prunning));
        } else {
            trees_count.setText("0");
            total_prunning_amount.setText("0.00");
        }
        if (pruning_intercrop.getText() != null && labourlist_Set.get(selectedPO).getTreesCountWithIntercrop() != null && !pruning_intercrop.getText().toString().isEmpty()&& labourlist_Set.get(selectedPO).getPruningWithIntercropAmount() != 0.0) {
            //Code to perform calculations

            double prunning_intercrop = Double.parseDouble(pruning_intercrop.getText() + "");
            double tress_inter = Double.parseDouble(labourlist_Set.get(selectedPO).getTreesCountWithIntercrop() + "");
            intercrop_prunning = tress_inter * prunning_intercrop;
            intercrop_amount.setText(df.format(intercrop_prunning));
            int value = (int) Math.round((Double) labourlist_Set.get(selectedPO).getTreesCountWithIntercrop());
            intercrop_tresscount.setText(value + "");
            Log.e("tress====424", String.valueOf(intercrop_prunning));
        } else {
            intercrop_tresscount.setText("0");
            intercrop_amount.setText("0.00");
        }

        if (harvest_intercrop.getText() != null && labourlist_Set.get(selectedPO).getNetWeightIntercrop() != null&& !harvest_intercrop.getText().toString().isEmpty() && labourlist_Set.get(selectedPO).getHarvestingWithIntercropAmount() != 0.0) {

            double harv_intercrop = Double.parseDouble(harvest_intercrop.getText() + "");
            double intercrop_net_weight = Double.parseDouble(labourlist_Set.get(selectedPO).getNetWeightIntercrop() + "");
            intercrop_harvesting = intercrop_net_weight * harv_intercrop;
            intercrop_harv_amount.setText(df.format(intercrop_harvesting));
            intercrop_netweight.setText(dff.format(intercrop_net_weight));
            Log.e("tress====424", String.valueOf(intercrop_prunning));
        } else {
            intercrop_harv_amount.setText("0.00");
            intercrop_netweight.setText("0.000");
        }
        if (harv_amount.getText() != null && labourlist_Set.get(selectedPO).getNetWeight() != null && labourlist_Set.get(selectedPO).getServiceChargePercentage() != null && labourlist_Set.get(selectedPO).getHarvestingAmount() != 0.0 ) {
            double harvesting = Double.parseDouble(harv_amount.getText() + "");
            double net_weight = Double.parseDouble(labourlist_Set.get(selectedPO).getNetWeight() + "");
            netweight.setText(dff.format(net_weight));
            total_hav = net_weight * harvesting;
            total_harvesting_amount.setText(df.format(total_hav));
//            Total_amount = total_prunning + total_hav+intercrop_prunning +intercrop_harvesting;
//            Log.e("tress====247", String.valueOf(Total_amount));
//            double percentage = Double.parseDouble(labourlist_Set.get(selectedPO).getServiceChargePercentage());
//            Log.e("percentage====252", String.valueOf(percentage));
//            double Service_amount = (Total_amount * percentage) / 100;
//            Log.e("percentage====254", String.valueOf(Service_amount));
//            service_amount.setText(dff.format(Service_amount));
//            double discount_amount =(Total_amount * discount)/100;
//            Log.e("discount_amount==",discount_amount+"");
//            Discount_amount.setText(discount_amount+"");

        } else {
            netweight.setText("0.000");
            total_harvesting_amount.setText("0.00");
            //   service_amount.setText("0.00");
            //  Discount_amount.setText(("0.00"));
            //  label_netweight.setVisibility(View.GONE);
//            label_harv.setVisibility(View.GONE);
//            label_amount_service.setVisibility(View.GONE);

        }
        /*if (total_prunning != 0.0 || total_hav != 0.0 || intercrop_prunning != 0.0 || intercrop_harvesting != 0.0) {
            Total_amount = total_prunning + total_hav + intercrop_prunning + intercrop_harvesting;
            amount.setText( labourlist_Set.get(selectedPO).getNetWeightIntercrop() );
        } else {
            amount.setText("0.00");
        }*/

        if
        (CommonUtil.isDoubleNullorEmpty(total_prunning) && CommonUtil.isDoubleNullorEmpty(intercrop_prunning) && CommonUtil.isDoubleNullorEmpty(intercrop_harvesting) && CommonUtil.isDoubleNullorEmpty(total_hav)) {
            Total_amount=0;

            amount.setText( df.format(labourlist_Set.get(selectedPO).getTotalCost()));
            // amount.setText("0.00");
            Log.e("mahesh","Total_amount=======in if================"+Total_amount);
        } else {

            amount.setText(df.format(labourlist_Set.get(selectedPO).getTotalCost()));
            Total_amount = total_prunning + total_hav + intercrop_prunning + intercrop_harvesting;
            Log.e("mahesh","Total_amount===========in else============"+Total_amount);
            //   amount.setText(Total_amount+"");
            Total_amount=0;total_prunning=0;total_hav=0;intercrop_prunning=0;intercrop_harvesting=0;
        }
        // amount.setText(Total_amount + "");
        //  amount.setText(Total_amount+"");
        Log.e("tress====247", String.valueOf(Total_amount));
        double percentage = Double.parseDouble(labourlist_Set.get(selectedPO).getServiceChargePercentage() + "");
        Log.e("percentage====252", String.valueOf(percentage));
        double Service_amount = (Total_amount * percentage) / 100;
        Log.e("percentage====254", String.valueOf(Service_amount));
        service_amount.setText(dff.format(Service_amount));
        double discount_amount = (Total_amount * discount) / 100;
        Log.e("discount_amount==", discount_amount + "");
        Discount_amount.setText(discount_amount + "");
        //
        //amount.setText(labourlist_Set.get(selectedPO).getTotalCost() + "");
//        if (labourlist_Set.get(selectedPO).getTotalCost() != null) {
//
//            amount.setText(labourlist_Set.get(selectedPO).getTotalCost() + "");
//        } else {
//           // amount.setText(Total_amount+"");
//
//          amount.setText("0.00");
//        }

//        if (job_done.getText().equals("null")|| job_done.getText() == "null") {
//            lin_comments.setVisibility(View.GONE);
//        }
        if (leader_name.getText().equals("null") || leader_name.getText() == "null") {
            new_data.setVisibility(View.GONE);
            date_label.setVisibility(View.GONE);
        }
//        if (collectionid.getText().equals("null")|| collectionid.getText() == "null") {
//            coll_label.setVisibility(View.GONE);
//        }
        if (netweight.getText().equals("null") || netweight.getText() == "null") {
            label_netweight.setVisibility(View.GONE);
        }
//        if (amount.getText().equals("null")|| amount.getText() == "null") {
//
//        }


/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //amount.setText("0.00");
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    public static int getWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }


    private void delete_request() throws JSONException {

        JsonObject object = Requestobject();
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.postdelete(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Resdelete>() {
                    @Override
                    public void onCompleted() {

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

                    }

                    @Override
                    public void onNext(Resdelete resdelete) {
                        labour_req_response.ListResult item = labourlist_Set.get(selectedPO);
                        item.setStatusType("Cancelled");
                        labourlist_Set.set(selectedPO, item);
                        Toast.makeText(mContext, mContext.getString(R.string.cancel_success), Toast.LENGTH_LONG).show();
                        notifyItemChanged(selectedPO);

                    }


                });
    }

    private JsonObject Requestobject() {
        DeleteObject requestModel = new DeleteObject();
        requestModel.setRequestCode(selectedItemID);
        requestModel.setStatusTypeId(32);
        requestModel.setUpdatedByUserId(null);
        requestModel.setUpdatedDate(currentDate);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();


    }

    @Override
    public int getItemCount() {
        if (null != labourlist_Set)
            return labourlist_Set.size();
        else
            return 0;
    }
    // return labourlist_Set.size();


    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView txtPlotId;
        public TextView txtDate;
        public TextView txtTime;
        public TextView txtComments;
        public TextView txtDateNTime;
        public TextView txtReqDate;
        public TextView txtApproveDate;
        public TextView txtStatus;
        public TextView txtname, line_labour;
        public TextView cancel;
        public TextView req_code;
        public CardView card_view;
        LinearLayout details;
//"requestCode":"REQVWGBDAB00010001L127"

        public ViewHolder(View itemView) {
            super(itemView);


            txtPlotId = itemView.findViewById(R.id.plotId);
            txtDate = itemView.findViewById(R.id.req_date);
            req_code = itemView.findViewById(R.id.requestCode);
            cancel = itemView.findViewById(R.id.cancel);
            txtDateNTime = itemView.findViewById(R.id.dateNTime);
            txtReqDate = itemView.findViewById(R.id.village_name);
            txtApproveDate = itemView.findViewById(R.id.status_type);
            txtStatus = itemView.findViewById(R.id.status);
            txtname = itemView.findViewById(R.id.name);
            line_labour = itemView.findViewById(R.id.approveDateLabel);
            card_view = itemView.findViewById(R.id.card_view);
            details = itemView.findViewById(R.id.details);


        }


    }

    public interface Reqlister {
        void onContactSelected(labour_req_response.ListResult selectedItem);
    }


    @SuppressLint("NewApi")
    public static final void recreateActivityCompat(final Activity a) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            a.recreate();
        } else {
            final Intent intent = a.getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            a.finish();
            a.overridePendingTransition(0, 0);
            a.startActivity(intent);
            a.overridePendingTransition(0, 0);
        }
    }
}




