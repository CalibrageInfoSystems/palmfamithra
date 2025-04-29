package in.calibrage.palm360fa.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import in.calibrage.palm360fa.Model.LabourRecommendationsModel;
import in.calibrage.palm360fa.R;

public class TransportplotAdapter extends SelectableAdapter<TransportplotAdapter.ViewHolder>{

    List<LabourRecommendationsModel.ListResult> recomm_Set;
    public Context mContext;
    private ClickListener clickListener;
    String transporttype;
    int StatustypeId;
    DecimalFormat dec = new DecimalFormat("####0.00");
    public TransportplotAdapter(  List<LabourRecommendationsModel.ListResult> recomm_Set, Context context,String selected_transport) {
        this.recomm_Set = recomm_Set;
        this.mContext=context;
        this.transporttype = selected_transport;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.transportplot_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        holder.textViewplotId.setText(recomm_Set.get(position).getPlotcode());
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        holder.textViewLocation.setText(recomm_Set.get(position).getVillageName());
        holder.landmark.setText(recomm_Set.get(position).getLandMark() );
        holder.textViewstatus.setText(recomm_Set.get(position).getStatusType() );
        holder.cluster_name.setText(recomm_Set.get(position).getClusterName());
        if(recomm_Set.get(position).getDateOfPlanting() != null){
            holder.yop.setText(recomm_Set.get(position).getDateOfPlanting() );
            holder.textViewpalmArea.setText(df.format(recomm_Set.get(position).getPalmArea())+" Ha ("+ dec.format(recomm_Set.get(position).getPalmAreainAcres() ) + " Acre)");
        }else{
            holder.yop.setVisibility(View.GONE);
            holder.date_plantation.setVisibility(View.GONE);
            holder.textViewpalmArea.setText(df.format(recomm_Set.get(position).getPlotArea())+" Ha ("+ dec.format(recomm_Set.get(position).getPlotArea() ) + " Acre)");
        }


        StatustypeId =recomm_Set.get(position).getStatusTypeId() ;
        final double selected_palm =recomm_Set.get(position).getPalmAreainAcres() ;
        final String interCrops =recomm_Set.get(position).getInterCrops() ;
        holder.selectionChk.setChecked(isSelected(position));


        holder.card_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                clickListener.onItemClicked(position);




            }

        });

       // AnimationUtil.animate(holder, true);
    }



    @Override
    public int getItemCount() {
        return recomm_Set.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewplotId;
        public TextView textViewpalmArea;
        public TextView textViewLocation;
        public TextView textViewstatus,date_plantation,landmark,yop,cluster_name;
        public CardView card_view;
        public TextView farmer_Code,plot_Mandal,plot_State,plot_District;
        public CheckBox selectionChk;
        LinearLayout linear;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewplotId = (TextView) itemView.findViewById(R.id.plotId);
            this.textViewpalmArea = (TextView) itemView.findViewById(R.id.palmArea);
            this.textViewLocation = (TextView) itemView.findViewById(R.id.location);
            this.textViewstatus = (TextView) itemView.findViewById(R.id.status);
         this.linear = (LinearLayout) itemView.findViewById(R.id.linear);
//            this.plot_Mandal = (TextView) itemView.findViewById(R.id.plot_Mandal);
//            this.plot_State = (TextView) itemView.findViewById(R.id.plot_State);
//            this.plot_District = (TextView) itemView.findViewById(R.id.plot_District);
            this.date_plantation = (TextView) itemView.findViewById(R.id.date_plantation);
            this.landmark = (TextView) itemView.findViewById(R.id.landmark);
            this.yop = (TextView) itemView.findViewById(R.id.yop);
            this.card_view =  (CardView) itemView.findViewById(R.id.card_view);
            this.cluster_name=(TextView)itemView.findViewById(R.id.status);
            this.selectionChk = (CheckBox) itemView.findViewById(R.id.chkSelected);
        }


    }
    public void setOnClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {

        void onItemClicked(int position);
    }
}
