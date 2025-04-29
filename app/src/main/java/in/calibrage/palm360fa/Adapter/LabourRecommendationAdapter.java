package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import in.calibrage.palm360fa.Model.LabourRecommendationsModel;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.Views.Activities.LabourActivity;
import in.calibrage.palm360fa.common.AnimationUtil;


public class LabourRecommendationAdapter extends RecyclerView.Adapter<LabourRecommendationAdapter.ViewHolder>{

    List<LabourRecommendationsModel.ListResult> recomm_Set;
    public Context mContext;
    DecimalFormat dec = new DecimalFormat("####0.00");
    public LabourRecommendationAdapter(    List<LabourRecommendationsModel.ListResult> recomm_Set, Context context) {
        this.recomm_Set = recomm_Set;
        this.mContext=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recommendation_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.textViewplotId.setText(recomm_Set.get(position).getPlotcode());
        DecimalFormat df = new DecimalFormat("#,###,##0.00");

        holder.textViewpalmArea.setText(df.format(recomm_Set.get(position).getPalmArea())+" Ha ("+ dec.format(recomm_Set.get(position).getPalmAreainAcres() ) + " Acre)");
        holder.textViewLocation.setText(recomm_Set.get(position).getVillageName());
        holder.landmark.setText(recomm_Set.get(position).getLandMark() );
        holder.textViewstatus.setText(recomm_Set.get(position).getStatusType() );
        holder.farmer_Code.setText(recomm_Set.get(position).getFarmerCode());
        holder.plot_Mandal.setText(recomm_Set.get(position).getMandalName());
        holder.plot_State.setText(recomm_Set.get(position).getStateName());
        holder.plot_District.setText(recomm_Set.get(position).getDistrictName());
        holder.cluster_name.setText(recomm_Set.get(position).getClusterName());
        holder.yop.setText(recomm_Set.get(position).getDateOfPlanting() );
        Log.e("date",recomm_Set.get(position).getDateOfPlanting() );

        final double selected_plot =recomm_Set.get(position).getPalmArea() ;
        final double selected_palm =recomm_Set.get(position).getPalmAreainAcres() ;
        final String interCrops =recomm_Set.get(position).getInterCrops() ;
        holder.card_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LabourActivity.class);
                intent.putExtra("plotid",    holder.textViewplotId.getText());
                intent.putExtra("plotAge",    selected_plot);
                intent.putExtra("plotarea",    selected_palm);
                intent.putExtra("plotVillage",    holder.textViewLocation.getText());
                intent.putExtra("farmerCode",    holder.farmer_Code.getText());
                intent.putExtra("landMark",    holder.landmark.getText());
                intent.putExtra("status",    holder.textViewstatus.getText());
                intent.putExtra("plotMandal",    holder.plot_Mandal.getText());
                intent.putExtra("plotState",    holder.plot_State.getText());
                intent.putExtra("plotDistrict",    holder.plot_District.getText());
                intent.putExtra("cluster_name",    holder.cluster_name.getText());
                intent.putExtra("interCrop",   interCrops);
                intent.putExtra("cluster_Id",    recomm_Set.get(position).getClusterId());
                intent.putExtra("statusTypeId",   recomm_Set.get(position).getStatusTypeId());
                intent.putExtra("date_of_plandation",   recomm_Set.get(position).getDateOfPlanting());
                intent.putExtra("statename",   recomm_Set.get(position).getStateName());
                intent.putExtra("statecode",   recomm_Set.get(position).getStateCode());


                Log.e("plotDistrict====", (String) holder.plot_Mandal.getText());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(intent);



            }

        });

        AnimationUtil.animate(holder, true);
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
        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewplotId = (TextView) itemView.findViewById(R.id.plotId);
            this.textViewpalmArea = (TextView) itemView.findViewById(R.id.palmArea);
            this.textViewLocation = (TextView) itemView.findViewById(R.id.location);
            this.textViewstatus = (TextView) itemView.findViewById(R.id.status);
            this.farmer_Code = (TextView) itemView.findViewById(R.id.farmer_Code);
            this.plot_Mandal = (TextView) itemView.findViewById(R.id.plot_Mandal);
            this.plot_State = (TextView) itemView.findViewById(R.id.plot_State);
            this.plot_District = (TextView) itemView.findViewById(R.id.plot_District);
            this.date_plantation = (TextView) itemView.findViewById(R.id.date_plantation);
            this.landmark = (TextView) itemView.findViewById(R.id.landmark);
            this.yop = (TextView) itemView.findViewById(R.id.yop);
            this.card_view =  (CardView) itemView.findViewById(R.id.card_view);
            this.cluster_name=(TextView)itemView.findViewById(R.id.status);
        }


    }
}