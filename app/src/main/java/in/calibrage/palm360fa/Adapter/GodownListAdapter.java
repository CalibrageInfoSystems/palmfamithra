package in.calibrage.palm360fa.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.calibrage.palm360fa.Model.ActiveGodownsModel;
import in.calibrage.palm360fa.R;


public class GodownListAdapter extends RecyclerView.Adapter<GodownListAdapter.viewHolder> {
    private int selectedpo= -1;
    private List<ActiveGodownsModel.ListResult> godownlistResults = new ArrayList<>();
    private LayoutInflater inflater;
    OnItemClickListener listener;
    public GodownListAdapter(List<ActiveGodownsModel.ListResult> godownlistResults, Context ctx, OnItemClickListener listener) {
        this.godownlistResults = godownlistResults;
        inflater = (LayoutInflater.from(ctx));
        this.listener =listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lst_item_godown, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        if(position == selectedpo)
            holder.cardView.setBackgroundResource(R.drawable.button_bg3);
        else
            holder.cardView.setBackgroundResource(R.drawable.button_bg2);



        holder.txt_name.setText(""+godownlistResults.get(position).getName());
        holder.txt_address.setText(""+godownlistResults.get(position).getAddress());
        holder.txt_Location.setText(""+godownlistResults.get(position).getLocation());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                notifyItemChanged(selectedpo);
                selectedpo = position;
                notifyItemChanged(selectedpo);
                listener.onItemClick(godownlistResults.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        if (godownlistResults != null)
            return godownlistResults.size();
        else
            return 0;
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView txt_name, txt_Location, txt_address;
        CardView cardView;

        public viewHolder(View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);
            txt_Location = itemView.findViewById(R.id.txt_Location);
            txt_address = itemView.findViewById(R.id.txt_address);
            cardView = itemView.findViewById(R.id.cardView);
        }

    }


    public interface OnItemClickListener {
        void onItemClick(ActiveGodownsModel.ListResult item);
    }
}
