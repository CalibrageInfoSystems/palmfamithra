package in.calibrage.palm360fa.Adapter;


import android.content.Context;
import android.os.Build;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.calibrage.palm360fa.Model.QuickPayModel;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.AnimationUtil;


public class QuickPayDataAdapter extends RecyclerView.Adapter<QuickPayDataAdapter.ViewHolder> {
    public Context mContext;
    private List<QuickPayModel.ListResult> stList = new ArrayList<>();

    String datetimevaluereq;

    private quick_paylistener listener;

    public QuickPayDataAdapter(Context context, List<QuickPayModel.ListResult> superHeroes, quick_paylistener listener) {
        this.stList = superHeroes;
        this.mContext = context;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.quickpaycardview_row, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final int pos = position;

        if (stList.get(position).getCollectionBlocked() == true){

            viewHolder.blockedCollection.setVisibility(View.VISIBLE);
        }else {

            viewHolder.blockedCollection.setVisibility(View.GONE);
        }

        viewHolder.collection_id.setText(stList.get(position).getUColnid());

        viewHolder.tvNetWeight.setText(stList.get(position).getQuantity() + " " + "MT");

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date oneWayTripDate = input.parse(stList.get(position).getDocDate());

            datetimevaluereq = output.format(oneWayTripDate);
            //datetimevalute.setText(output.format(oneWayTripDate));

            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewHolder.tvDate.setText(datetimevaluereq);

        viewHolder.tvCc.setText(stList.get(position).getWhsName());


        if (position % 2 == 0) {
            viewHolder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            viewHolder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }
        AnimationUtil.animate(viewHolder, true);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setOnClickAckListener(stList.get(position));
            }
        });

    }

    // Return the size arraylist
    @Override
    public int getItemCount() {


        if (stList != null)
            return stList.size();
        else
            return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView collection_id;
        public TextView tvNetWeight;
        public TextView tvCc;
        public TextView tvDate;
        public CheckBox chkSelected;
        public CardView card_view;
        public QuickPayModel singlestudent;
        TextView blockedCollection;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            collection_id = (TextView) itemLayoutView.findViewById(R.id.collection_id);
            tvNetWeight = (TextView) itemLayoutView.findViewById(R.id.tvNetWeight);
            tvDate = (TextView) itemLayoutView.findViewById(R.id.tvDate);
            tvCc = (TextView) itemLayoutView.findViewById(R.id.tvCc);
            card_view = (CardView) itemLayoutView.findViewById(R.id.card_view);
            blockedCollection = itemLayoutView.findViewById(R.id.blockedCollection);

        }

    }

    // method to access in activity after updating selection
    public List<QuickPayModel.ListResult> getStudentist() {
        return stList;
    }


    public interface quick_paylistener {
        void setOnClickAckListener(QuickPayModel.ListResult item);
    }


}
