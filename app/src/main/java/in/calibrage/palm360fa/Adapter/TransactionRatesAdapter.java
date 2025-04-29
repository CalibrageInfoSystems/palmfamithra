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
import java.util.ArrayList;
import java.util.List;

import in.calibrage.palm360fa.Model.GetTranspotationCharges;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.AnimationUtil;


public class TransactionRatesAdapter extends RecyclerView.Adapter<TransactionRatesAdapter.ViewHolder> {
    String datetimevaluereq;
    public Context mContext;
    private List<GetTranspotationCharges.TrasportRate> trans_Set = new ArrayList<>();
    DecimalFormat dff,  df;
//    public TransactionRatesAdapter(Context mContext) {
//        this.mContext = mContext;
//    }

    public void updateData(List<GetTranspotationCharges.TrasportRate> viewModels) {
        trans_Set.clear();
        Log.d("PaymentAdapter","----- analysis --- Size :"+trans_Set.size());
        trans_Set= viewModels;
        //  notifyDataSetChanged();
    }

    public TransactionRatesAdapter(Context context, List<GetTranspotationCharges.TrasportRate> trans_Set) {

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
                .inflate(R.layout.trans_rate_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {




        df = new DecimalFormat("#,###,##0.000");
        dff = new DecimalFormat("#,###,##0.00");
        ((ViewHolder) holder).village.setText(": " +trans_Set.get(position).getVillage());

        ((ViewHolder) holder).mandal.setText(": " +trans_Set.get(position).getMandal());
        ((ViewHolder) holder).rate.setText(": " +dff.format(trans_Set.get(position).getRate()));


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

        public TextView rate;
        public TextView mandal;
        public TextView village;

        CardView card_view;
        public ViewHolder(View itemView) {
            super(itemView);

            rate = itemView.findViewById(R.id.rate);

            mandal = (TextView) itemView.findViewById(R.id.mandal);
            village = (TextView) itemView.findViewById(R.id.village);


            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}

