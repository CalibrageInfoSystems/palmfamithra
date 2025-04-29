package in.calibrage.palm360fa.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.calibrage.palm360fa.Model.Nutrient_Model;
import in.calibrage.palm360fa.R;


public class NutrientDataAdapter extends RecyclerView.Adapter<NutrientDataAdapter.ViewHolder> {

    public Context mContext;
    private List<Nutrient_Model> nut_List;

    // RecyclerView recyclerView;
    public NutrientDataAdapter(Context ctx, List<Nutrient_Model> nut_List) {
        this.nut_List = nut_List;
        this.mContext = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.nuet_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Nutrient_Model dataa = nut_List.get(position);

        holder.NutrientDeficiencyName.setText(dataa.getNutrientDeficiencyName());
        holder.Nameofchemicalapplied.setText(dataa.getNameofchemicalapplied());
        holder.RecommendedFertilizer.setText(dataa.getRecommendedFertilizer());
        holder.UOMName.setText(dataa.getUOMName());
        holder.Dosage.setText(dataa.getDosage());
        holder.RegisteredDate.setText(dataa.getRegisteredDate());

        holder.comments.setText(dataa.getComments());

        if (dataa.getDosage().equalsIgnoreCase("0.0")) {

            holder.Dosage.setVisibility(View.GONE);
            holder.dosage_label.setVisibility(View.GONE);
            holder.UOMName.setVisibility(View.GONE);
        }
        else{
            holder.Dosage.setVisibility(View.VISIBLE);
            holder.dosage_label.setVisibility(View.VISIBLE);
            holder.UOMName.setVisibility(View.VISIBLE);
        }
        if (dataa.getComments().contains("null")) {
            //   Log.e("bbbbb",superHero.getmAmount());
            holder.comments.setVisibility(View.GONE);
            holder.comment_label.setVisibility(View.GONE);

        } else {
            holder.comments.setVisibility(View.VISIBLE);
            holder.comment_label.setVisibility(View.VISIBLE);
        }
        if (dataa.getRecommendedFertilizer().contains("null")) {
            //   Log.e("bbbbb",superHero.getmAmount());
            holder.RecommendedFertilizer.setVisibility(View.GONE);
            holder.RecommendedFertilizer_label.setVisibility(View.GONE);

        } else {
            holder.RecommendedFertilizer.setVisibility(View.VISIBLE);
            holder.RecommendedFertilizer_label.setVisibility(View.VISIBLE);
        }
        //      holder.imageView.setImageResource(listdata[position].getImgId());

    }


    @Override
    public int getItemCount() {

        if (nut_List != null)
            return nut_List.size();
        else
            return 0;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView NutrientDeficiencyName, Nameofchemicalapplied, RecommendedFertilizer, UOMName, Dosage, RegisteredDate, comments, dosage_label,
                RecommendedFertilizer_label;

        // ImageView thumbnail;
        public TextView comment_label, reason_label;

        public ViewHolder(View itemView) {
            super(itemView);

            //   txtPlotCode = itemView.findViewById(R.id.plot_code);
            NutrientDeficiencyName = itemView.findViewById(R.id.NutrientDeficiencyName);
            Nameofchemicalapplied = itemView.findViewById(R.id.Nameofchemicalapplied);
            RecommendedFertilizer = itemView.findViewById(R.id.RecommendedFertilizer);
            UOMName = itemView.findViewById(R.id.UOMName);
            Dosage = itemView.findViewById(R.id.Dosage);

            RegisteredDate = itemView.findViewById(R.id.RegisteredDate);
            dosage_label = itemView.findViewById(R.id.DosageLabel);
            comments = itemView.findViewById(R.id.Comments);
            comment_label = itemView.findViewById(R.id.commentsLabel);
            RecommendedFertilizer_label = itemView.findViewById(R.id.RecommendedFertilizerLabel);

        }


    }
}


