package in.calibrage.palm360fa.Views.Activities;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.calibrage.palm360fa.Model.GetquickpayDetailsModel;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.AnimationUtil;

public class Quickpaydetailsadapter extends RecyclerView.Adapter<Quickpaydetailsadapter.ViewHolder> {


public Context mContext;
private List<GetquickpayDetailsModel> detailsList = new ArrayList<>();
        List<String> ids_list = new ArrayList<>();
        List<String> date_list = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("####0.00");
    DecimalFormat dff = new DecimalFormat("####0.000");
    String datetimevaluereq;



public Quickpaydetailsadapter(Context context, List<GetquickpayDetailsModel> quickpaydetails,List<String> ids_list,List<String> date_list ) {
        this.detailsList = quickpaydetails;
        this.mContext = context;
        this.ids_list = ids_list;
        this.date_list = date_list;
        }
@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quickpaydetailsadapter, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(lp);
        return new ViewHolder(rootView);


//        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
//                R.layout.quickpaydetailsadapter, null);
//
//        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
//
//        return viewHolder;
        }

@RequiresApi(api = Build.VERSION_CODES.M)
@Override
public void onBindViewHolder(@NonNull Quickpaydetailsadapter.ViewHolder viewHolder, int position) {

final int pos = position;

    AnimationUtil.animate(viewHolder, true);

    viewHolder.collectionid.setText(ids_list.get(position));

    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
    try {
        Date oneWayTripDate = input.parse(date_list.get(position));

        datetimevaluereq = output.format(oneWayTripDate);
        //datetimevalute.setText(output.format(oneWayTripDate));

        Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
    } catch (ParseException e) {
        e.printStackTrace();
    }

    viewHolder.collectiondate.setText(datetimevaluereq);

    if (detailsList.get(position).getListResult().get(0).getFfbCost() == null){

        viewHolder.ffbCostTxt.setText("0.00");
    }else{

        viewHolder.ffbCostTxt.setText(df.format(detailsList.get(position).getListResult().get(0).getFfbCost()));
    }


    if (detailsList.get(position).getListResult().get(0).getFfbFlatCharge() == null){

        viewHolder.text_flat_charge.setText("0.00");
    }else{

        viewHolder.text_flat_charge.setText(df.format(detailsList.get(position).getListResult().get(0).getFfbFlatCharge()));
    }

    if (detailsList.get(position).getListResult().get(0).getQuantity() == null){

        viewHolder.text_quntity.setText("0.00");
    }else{

        viewHolder.text_quntity.setText(dff.format(detailsList.get(position).getListResult().get(0).getQuantity()));
    }

        }

@Override
public int getItemCount() {
        return detailsList.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView ffbCostTxt, convenienceChargeTxt, closingBalanceTxt, totalAmount, text_flat_charge, text_quntity, text_quickpay_fee,collectionid,collectiondate;
    public CardView card_view;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        card_view = (CardView) itemView.findViewById(R.id.card_view);

        collectiondate = (TextView) itemView.findViewById(R.id.collectiondate);
        collectionid = (TextView) itemView.findViewById(R.id.collectionid);
        ffbCostTxt = (TextView) itemView.findViewById(R.id.tvtext_item_five);
        text_flat_charge = (TextView) itemView.findViewById(R.id.text_flat_charge);
        text_quntity = (TextView) itemView.findViewById(R.id.text_quntity);
    }
}
}
