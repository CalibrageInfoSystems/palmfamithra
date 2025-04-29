package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.os.Build;

import android.text.TextUtils;
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

import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.AnimationUtil;


public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {
    String datetimevaluereq;
    public Context mContext;
    private List<PaymentResponseModel.PaymentResponce> payment_Set = new ArrayList<>();
    DecimalFormat  dff,  df;
    public PaymentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void updateData(List<PaymentResponseModel.PaymentResponce> viewModels) {
        payment_Set.clear();
        Log.d("PaymentAdapter","----- analysis --- Size :"+payment_Set.size());
        payment_Set= viewModels;
      //  notifyDataSetChanged();
    }

    public PaymentAdapter(Context context, List<PaymentResponseModel.PaymentResponce> payment_Set) {

        this.mContext = context;
        this.payment_Set = payment_Set;
    }

    public void clearAllDataa() {
        payment_Set.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_history_list_item2, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(PaymentAdapter.ViewHolder holder, int position) {



        double Quanitity = payment_Set.get(position).getQuantity();

        df = new DecimalFormat("#,###,##0.000");
        dff = new DecimalFormat("#,###,##0.00");
        Log.e("===============", payment_Set.get(position).getQuantity() + "");
        ((ViewHolder) holder).memo_text.setText("" + payment_Set.get(position).getMemo());
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date oneWayTripDate = input.parse(payment_Set.get(position).getRefDate());
            datetimevaluereq = output.format(oneWayTripDate);
            //datetimevalute.setText(output.format(oneWayTripDate));

            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((ViewHolder) holder).date.setText("" + datetimevaluereq);
        ((ViewHolder) holder).dateLabel.setText(datetimevaluereq);



        ((ViewHolder) holder).quantity_ffb.setText("" + df.format(Quanitity));
        int amt = (int) Math.round(payment_Set.get(position).getAmount());
        int AdhocRate = (int) Math.round(payment_Set.get(position).getAdhocRate());
        int InvoiceRate = (int) Math.round(payment_Set.get(position).getInvoiceRate());
        int GRAmount = (int) Math.round(payment_Set.get(position).getGRAmount());
        int Adjusted = (int) Math.round(payment_Set.get(position).getAdjusted());

        ((ViewHolder) holder).adhoc_value.setText("" + AdhocRate);
        ((ViewHolder) holder).txt_invoice.setText("" +InvoiceRate);
        ((ViewHolder) holder).txt_gr_rate.setText("" + GRAmount);
        ((ViewHolder) holder).adjustTxt.setText("" + Adjusted);
        ((ViewHolder) holder).finalAmount.setText("" + amt);


        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }
        int a = (int) Math.round(payment_Set.get(position).getBalance());

        Log.e("valueRounded===",a+"");

        if ((payment_Set.get(position).getBalance()) < 0) {
            if(a!=0) {
                String balance1 = (a) + "" + ")";
                ((ViewHolder) holder).balance.setText("" + balance1.toString().replace("-", "("));
            }
            else
                ((ViewHolder) holder).balance.setText("" + a);
        } else {
            ((ViewHolder) holder).balance.setText("" + a);
        }


        // holder.textViewPowers.setText(powers);*/
        if (payment_Set.get(position).getAdhocRate() == 0.0) {
            //   Log.e("bbbbb",superHero.getmAmount());
            holder.adhoc_value.setVisibility(View.GONE);
            holder.text_two.setVisibility(View.GONE);
            holder.dot5.setVisibility(View.GONE);

        } else {
            holder.adhoc_value.setVisibility(View.VISIBLE);
            holder.text_two.setVisibility(View.VISIBLE);
            holder.dot5.setVisibility(View.VISIBLE);
        }
        if (payment_Set.get(position).getQuantity() == 0.0) {
            //   Log.e("bbbbb",superHero.getmAmount());
            holder.text_one.setVisibility(View.GONE);
            holder.quantity_ffb.setVisibility(View.GONE);
            holder.dot4.setVisibility(View.GONE);
        } else {
            holder.text_one.setVisibility(View.VISIBLE);
            holder.quantity_ffb.setVisibility(View.VISIBLE);
            holder.dot4.setVisibility(View.VISIBLE);
        }
        if (payment_Set.get(position).getInvoiceRate() == 0.0) {
            //   Log.e("bbbbb",superHero.getmAmount());
            holder.txt_invoice.setVisibility(View.GONE);
            holder.text_thee.setVisibility(View.GONE);
            holder.dot6.setVisibility(View.GONE);

        } else {
            holder.txt_invoice.setVisibility(View.VISIBLE);
            holder.text_thee.setVisibility(View.VISIBLE);
            holder.dot6.setVisibility(View.VISIBLE);
        }
        if (payment_Set.get(position).getGRAmount() == 0.0) {
            //   Log.e("bbbbb",superHero.getmAmount());
            holder.txt_gr_rate.setVisibility(View.GONE);
            holder.text_four.setVisibility(View.GONE);
            holder.dot3.setVisibility(View.GONE);

        } else {
            holder.txt_gr_rate.setVisibility(View.VISIBLE);
            holder.text_four.setVisibility(View.VISIBLE);
            holder.dot3.setVisibility(View.VISIBLE);
        }
//
//
        if (payment_Set.get(position).getAdjusted() == 0.0) {
            //   Log.e("bbbbb",superHero.getmAmount());
            holder.adjustTxt.setVisibility(View.GONE);
            holder.text_five.setVisibility(View.GONE);
            holder.dot2.setVisibility(View.GONE);

        } else {
            holder.adjustTxt.setVisibility(View.VISIBLE);
            holder.text_five.setVisibility(View.VISIBLE);
            holder.dot2.setVisibility(View.VISIBLE);
        }
        if (payment_Set.get(position).getMemo() == null) {
            Log.e("Roja===", "memo==nulll");
            holder.memo_text.setVisibility(View.GONE);
            holder.text_six.setVisibility(View.GONE);
            holder.dot7.setVisibility(View.GONE);

        } else {
            holder.memo_text.setVisibility(View.VISIBLE);
            holder.text_six.setVisibility(View.VISIBLE);
            holder.dot7.setVisibility(View.VISIBLE);
        }
//

        if (amt == 0) {
            //   Log.e("bbbbb",superHero.getmAmount());
            holder.finalAmount.setVisibility(View.GONE);
            holder.text_seven.setVisibility(View.GONE);
            holder.dot1.setVisibility(View.GONE);
        } else {
            holder.finalAmount.setVisibility(View.VISIBLE);
            holder.text_seven.setVisibility(View.VISIBLE);
            holder.dot1.setVisibility(View.VISIBLE);
        }

        if (holder.memo_text.getVisibility() == View.VISIBLE) {
            if (TextUtils.isEmpty(holder.memo_text.getText()) || holder.memo_text.getText() == "null") {
                holder.memo_text.setVisibility(View.GONE);
                holder.text_six.setVisibility(View.GONE);
                holder.dot7.setVisibility(View.GONE);
            }
        }


        AnimationUtil.animate(holder, true);

    }




    @Override
    public int getItemCount() {
        if (payment_Set != null)
            return payment_Set.size();
        else
            return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtLedgerItem;
        public TextView quantity_ffb;
        public TextView adhoc_value;
        public TextView txt_invoice;
        public TextView txt_gr_rate;
        public TextView memo_text;
        public TextView showMore;
        public TextView finalAmount;
        protected CardView card_view;
        public TextView remarks, textViewPowers, date, balance, adjustTxt, dateLabel;
        public TextView text_one, text_two, text_thee, text_four, text_five, text_six, text_seven, text_eight;
        public TextView dot1,dot2,dot3,dot4,dot5,dot6,dot7,dot8;
        public ViewHolder(View itemView) {
            super(itemView);


            quantity_ffb = itemView.findViewById(R.id.quantity_ffb);
            adhoc_value = itemView.findViewById(R.id.adhoc_value);
            txt_invoice = itemView.findViewById(R.id.invoice);
            txt_gr_rate = itemView.findViewById(R.id.gr_rate);
            memo_text = itemView.findViewById(R.id.memo_text);
            finalAmount = itemView.findViewById(R.id.amount);
            adjustTxt = itemView.findViewById(R.id.adjusted);


            date = (TextView) itemView.findViewById(R.id.date);
            dateLabel = (TextView) itemView.findViewById(R.id.dateLabel);
            balance = (TextView) itemView.findViewById(R.id.balance);
            textViewPowers = (TextView) itemView.findViewById(R.id.textViewPowers);

            text_one = (TextView) itemView.findViewById(R.id.ffb_quantity);
            text_two = (TextView) itemView.findViewById(R.id.adhocLabel);
            text_thee = (TextView) itemView.findViewById(R.id.invoice_label);
            text_four = (TextView) itemView.findViewById(R.id.gr_amount_label);
            text_five = (TextView) itemView.findViewById(R.id.adjustedLabel);
            text_six = (TextView) itemView.findViewById(R.id.memo_label);
            text_seven = (TextView) itemView.findViewById(R.id.amount_label);
            text_eight = (TextView) itemView.findViewById(R.id.balanceLabel);
            dot1 = (TextView) itemView.findViewById(R.id.dot1);
            dot2 = (TextView) itemView.findViewById(R.id.dot2);
            dot3 = (TextView) itemView.findViewById(R.id.dot3);
            dot4 = (TextView) itemView.findViewById(R.id.dot4);
            dot5 = (TextView) itemView.findViewById(R.id.dot5);
            dot6 = (TextView) itemView.findViewById(R.id.dot6);
            dot7 = (TextView) itemView.findViewById(R.id.dot7);
            dot8 = (TextView) itemView.findViewById(R.id.dot8);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
