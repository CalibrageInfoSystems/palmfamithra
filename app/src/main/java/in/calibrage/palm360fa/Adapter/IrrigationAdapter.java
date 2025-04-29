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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import in.calibrage.palm360fa.Model.Irrigation_Model;
import in.calibrage.palm360fa.R;


public class IrrigationAdapter extends RecyclerView.Adapter<IrrigationAdapter.ViewHolder> {

    public Context mContext;
    List<Irrigation_Model> Irr_List;
    String datetimevaluereq;

    public IrrigationAdapter(Context context, List<Irrigation_Model> Irr_List) {
        this.Irr_List = Irr_List;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.irrigation_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Irrigation_Model data = Irr_List.get(position);
        holder.name.setText(data.getName());
        holder.updatedBy.setText(data.getUpdatedBy());



        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date oneWayTripDate = input.parse(data.getUpdatedbyDate());

            datetimevaluereq = output.format(oneWayTripDate);


            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.updatedbyDate.setText(datetimevaluereq);

//
    }

    @Override
    public int getItemCount() {
        if (Irr_List != null)
            return Irr_List.size();
        else
            return 0;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView  plotCode,name,updatedBy,updatedbyDate ;
        public TextView UOMName, dosage_label, Comments, txtDriverName;
        protected CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.irrigation_name);
            updatedBy = itemView.findViewById(R.id.updated_by);
            updatedbyDate = itemView.findViewById(R.id.updated_date);

        }
    }
}
