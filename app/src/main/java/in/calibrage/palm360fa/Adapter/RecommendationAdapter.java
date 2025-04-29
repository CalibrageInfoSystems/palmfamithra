package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import in.calibrage.palm360fa.Model.RecomPlotcodes;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.Views.Activities.CropMaintanceVisitActivity;
import in.calibrage.palm360fa.common.AnimationUtil;


public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ViewHolder> {

    public Context mContext;
    private List<RecomPlotcodes.ListResult> plot_Set;

    DecimalFormat dec = new DecimalFormat("####0.00");
    public RecommendationAdapter(Context context, List<RecomPlotcodes.ListResult> plot_Set) {

        this.mContext = context;
        this.plot_Set = plot_Set;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recommendation_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final double selected_plot =plot_Set.get(position).getPalmArea();
        final double plot_area = plot_Set.get(position).getPalmAreainAcres();

        ((ViewHolder) holder).textViewplotId.setText(plot_Set.get(position).getPlotcode());
        holder.textViewpalmArea.setText(dec.format(plot_Set.get(position).getPalmArea())+" Ha ("+  dec.format(plot_Set.get(position).getPalmAreainAcres() ) + " Acre)");
        ((ViewHolder) holder).textViewLocation.setText(plot_Set.get(position).getVillageName());
        ((ViewHolder) holder).textViewstatus.setText(plot_Set.get(position).getLandMark());
        ((ViewHolder) holder).yop.setText(plot_Set.get(position).getDateOfPlanting() );
        holder.cluster_name.setText(plot_Set.get(position).getClusterName());
        ((ViewHolder) holder).card_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CropMaintanceVisitActivity.class);
                intent.putExtra("plotid", holder.textViewplotId.getText());
                intent.putExtra("plotAge", selected_plot);
                intent.putExtra("plotVillage", holder.textViewLocation.getText());
                intent.putExtra("landMark", holder.textViewstatus.getText());
                intent.putExtra("plotarea",plot_area);
                intent.putExtra("cluster_name",holder.cluster_name.getText());
                intent.putExtra("date_of_plandation",   plot_Set.get(position).getDateOfPlanting());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(intent);


            }

        });
        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }

        AnimationUtil.animate(holder, true);
    }

    @Override
    public int getItemCount() {
        if (plot_Set != null)
            return plot_Set.size();
        else
            return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewplotId;
        public TextView textViewpalmArea;
        public TextView textViewLocation;
        public TextView textViewstatus,yop,cluster_name;
        public CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewplotId = (TextView) itemView.findViewById(R.id.plotId);
            this.textViewpalmArea = (TextView) itemView.findViewById(R.id.palmArea);
            this.textViewLocation = (TextView) itemView.findViewById(R.id.location);
            this.textViewstatus = (TextView) itemView.findViewById(R.id.landmark);
            this.card_view = (CardView) itemView.findViewById(R.id.card_view);
            this.yop=(TextView)itemView.findViewById(R.id.yop);
            this.cluster_name=(TextView)itemView.findViewById(R.id.status);

        }


    }
}
