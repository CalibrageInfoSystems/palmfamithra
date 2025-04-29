package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.os.Build;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.calibrage.palm360fa.Model.GetTranspotationCharges;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.AnimationUtil;


public class TransportationAdapter extends RecyclerView.Adapter<TransportationAdapter.ViewHolder> {
    String datetimevaluereq;
    public Context mContext;
    private List<GetTranspotationCharges.TranspotationCharge> trans_Set = new ArrayList<>();
    DecimalFormat dff,  df;
    public TransportationAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void updateData(List<GetTranspotationCharges.TranspotationCharge> viewModels) {
        trans_Set.clear();
        Log.d("PaymentAdapter","----- analysis --- Size :"+trans_Set.size());
        trans_Set= viewModels;
        //  notifyDataSetChanged();
    }

    public TransportationAdapter(Context context, List<GetTranspotationCharges.TranspotationCharge> trans_Set) {

        this.mContext = context;
        this.trans_Set = trans_Set;
    }

    public void clearAllDataa() {
        trans_Set.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trans_history_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {




        df = new DecimalFormat("#,###,##0.000");
        dff = new DecimalFormat("#,###,##0.00");

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date oneWayTripDate = input.parse(trans_Set.get(position).getReceiptGeneratedDate());
            datetimevaluereq = output.format(oneWayTripDate);
            //datetimevalute.setText(output.format(oneWayTripDate));

            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((ViewHolder) holder).date.setText(": " + datetimevaluereq);
        ((ViewHolder) holder).dateLabel.setText(datetimevaluereq);
        int amt = (int) Math.round(trans_Set.get(position).getRate());
        ((ViewHolder) holder).collection_code.setText("" + trans_Set.get(position).getCollectionCode());
        ((ViewHolder) holder).total_amount.setText(": " +dff.format(trans_Set.get(position).getRate()));
        ((ViewHolder) holder).trans_charges.setText(": " +trans_Set.get(position).getTonnageCost());
        //((ViewHolder) holder).status.setText(": " + trans_Set.get(position).getStatus());
        ((ViewHolder) holder).net_weight.setText(": " + df.format(trans_Set.get(position).getQty()));

        //(ViewHolder) holder).collection_code.setText(": " + df.format(Quanitity));


        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }




        AnimationUtil.animate(holder, true);

    }


    @Override
    public int getItemCount() {
        if (trans_Set != null)
            return trans_Set.size();
        else
            return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView collection_code;
        public TextView total_amount;
        public TextView trans_charges;
        public TextView status;
        public TextView memo_text;
        public TextView showMore;
        public TextView net_weight;
        protected CardView card_view;
        public TextView  date,  dateLabel;
        public TextView text_one, text_two, text_thee, text_four, text_five, text_six, text_seven, text_eight;

        public ViewHolder(View itemView) {
            super(itemView);

            collection_code = itemView.findViewById(R.id.collection_code);
            date = (TextView) itemView.findViewById(R.id.date);
            dateLabel = (TextView) itemView.findViewById(R.id.dateLabel);
            total_amount = (TextView) itemView.findViewById(R.id.amount_total);
            trans_charges = (TextView) itemView.findViewById(R.id.trans_charge);
            status =(TextView) itemView.findViewById(R.id.statuss);
            net_weight = (TextView) itemView.findViewById(R.id.net_weight);
//            text_two = (TextView) itemView.findViewById(R.id.adhocLabel);
//            text_thee = (TextView) itemView.findViewById(R.id.invoice_label);
//            text_four = (TextView) itemView.findViewById(R.id.gr_amount_label);
//            text_five = (TextView) itemView.findViewById(R.id.adjustedLabel);
//            text_six = (TextView) itemView.findViewById(R.id.memo_label);
//            text_seven = (TextView) itemView.findViewById(R.id.amount_label);
//            text_eight = (TextView) itemView.findViewById(R.id.balanceLabel);

            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
