package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.calibrage.palm360fa.Model.DiseaseDataModel;
import in.calibrage.palm360fa.R;


public class DiseaseDataAdapter extends RecyclerView.Adapter<DiseaseDataAdapter.ViewHolder>{

    public Context mContext;
    List<DiseaseDataModel> listdata;

    public DiseaseDataAdapter(Context context, List<DiseaseDataModel> listdata) {
        this.listdata = listdata;
        this.mContext=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.dease_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DiseaseDataModel superHero = listdata.get(position);
        holder.Disease.setText(superHero.getDisease());
        holder.Chemical.setText(superHero.getChemical());
        holder.Dosage.setText(superHero.getDosage());
        holder.RecommendedChemical.setText(superHero.getChemical());
        holder.UOMName.setText(superHero.getUOMName());
        holder.Comments.setText(superHero.getComments());


        if(position%2 == 0){
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }
        if (superHero.getDosage().contains("0.0") &&superHero.getDosage().contains("0") ) {

            holder.Dosage.setVisibility(View.GONE);
            holder.dosage_label.setVisibility(View.GONE);
            holder.UOMName.setVisibility(View.GONE);

        } else {
            holder.Dosage.setVisibility(View.VISIBLE);
            holder.dosage_label.setVisibility(View.VISIBLE);
            holder.UOMName.setVisibility(View.VISIBLE);
        }
         if (superHero.getRec_Chemical().contains("null")) {

     holder.rec_label.setVisibility(View.GONE);
            holder.RecommendedChemical.setVisibility(View.GONE);

        } else {
            holder.rec_label.setVisibility(View.VISIBLE);
            holder.RecommendedChemical.setVisibility(View.VISIBLE);
        }
        if (superHero.getChemical().equalsIgnoreCase("null")) {

            holder.chem_label.setVisibility(View.GONE);
            holder.Chemical.setVisibility(View.GONE);

        } else {
            holder.chem_label.setVisibility(View.VISIBLE);
            holder.Chemical.setVisibility(View.VISIBLE);
        }
        if (superHero.getComments().equalsIgnoreCase("null")) {
            //   Log.e("bbbbb",superHero.getmAmount());
            holder.Comments.setVisibility(View.GONE);
            holder.CommentsLabel.setVisibility(View.GONE);

        } else {
            holder.Comments.setVisibility(View.VISIBLE);
            holder.CommentsLabel.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public int getItemCount() {

        if (listdata != null)
            return listdata.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Disease;
        public TextView Chemical;
        public TextView RecommendedChemical;
        public TextView Dosage;
        public TextView UOMName,dosage_label,Comments,CommentsLabel,rec_label,chem_label;
        protected CardView card_view;
        public ViewHolder(View itemView) {
            super(itemView);
            Disease = itemView.findViewById(R.id.Disease);
            Chemical = itemView.findViewById(R.id.Chemical);
            RecommendedChemical = itemView.findViewById(R.id.RecommendedChemical);
            Dosage = itemView.findViewById(R.id.Dosage);
            UOMName = itemView.findViewById(R.id.UOMName);
            Comments = itemView.findViewById(R.id.Comments);
            dosage_label= itemView.findViewById(R.id.DosageLabel);
            CommentsLabel= itemView.findViewById(R.id.CommentsLabel);
            card_view=itemView.findViewById(R.id.card_view);
            rec_label =itemView.findViewById(R.id.RecommendedChemicalLabel);
            chem_label =itemView.findViewById(R.id.ChemicalLabel);
        }
    }
}