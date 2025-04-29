package in.calibrage.palm360fa.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.calibrage.palm360fa.Model.pest;
import in.calibrage.palm360fa.R;


public class pest_Adapter extends RecyclerView.Adapter<pest_Adapter.ViewHolder> {

    public Context mContext;
    private List<pest> pest_List;

    public pest_Adapter(Context ctx, List<pest> pest_List) {
        this.pest_List = pest_List;
        this.mContext = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.pest_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final pest dataa = pest_List.get(position);

        holder.Pest.setText(dataa.getPest());
        holder.PestChemicals.setText(dataa.getPestChemicals());
        holder.RecommendedChemical.setText(dataa.getRecommendedChemical());
        holder.Dosage.setText(dataa.getDosage());
        holder.UOMName.setText(dataa.getUOMName());
        holder.Comments.setText(dataa.getComments());

        if (dataa.getComments().contains("null")) {

            holder.Comments.setVisibility(View.GONE);
            holder.Comments_label.setVisibility(View.GONE);

        } else {
            holder.Comments.setVisibility(View.VISIBLE);
            holder.Comments_label.setVisibility(View.VISIBLE);
        }


        if (dataa.getUOMName().contains("null")) {

            holder.UOMName.setVisibility(View.GONE);


        } else {
            holder.UOMName.setVisibility(View.VISIBLE);

        }
        if (dataa.getDosage().contains("0.0")) {

            holder.Dosage.setVisibility(View.GONE);
            holder.Dosage_label.setVisibility(View.GONE);
            holder.UOMName.setVisibility(View.GONE);

        } else {
            holder.Dosage.setVisibility(View.VISIBLE);
            holder.Dosage_label.setVisibility(View.VISIBLE);
            holder.UOMName.setVisibility(View.VISIBLE);
        }
        if (dataa.getRecommendedChemical().contains("null")) {

            holder.RecommendedChemical.setVisibility(View.GONE);
            holder.RecommendedChemical_label.setVisibility(View.GONE);

        } else {
            holder.RecommendedChemical.setVisibility(View.VISIBLE);
            holder.RecommendedChemical_label.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public int getItemCount() {

        if (pest_List != null)
            return pest_List.size();
        else
            return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView Pest, PestChemicals, RecommendedChemical, Dosage, UOMName, Comments;
        public TextView RecommendedChemical_label, Dosage_label, Comments_label;


        public ViewHolder(View itemView) {
            super(itemView);

            //
            Pest = itemView.findViewById(R.id.Pest);
            PestChemicals = itemView.findViewById(R.id.PestChemicals);
            RecommendedChemical = itemView.findViewById(R.id.RecommendedChemical);
            Dosage = itemView.findViewById(R.id.Dosage);
            UOMName = itemView.findViewById(R.id.UOMName);
            Comments = itemView.findViewById(R.id.Comments);
            RecommendedChemical_label = itemView.findViewById(R.id.RecommendedChemicalLabel);
            Dosage_label = itemView.findViewById(R.id.DosageLabel);
            Comments_label = itemView.findViewById(R.id.CommentsLabel);

        }


    }
}


