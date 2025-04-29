package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.calibrage.palm360fa.Model.VehicleTypes;
import in.calibrage.palm360fa.R;

public class vehicltypeadapter extends RecyclerView.Adapter<vehicltypeadapter.ViewHolder> {
    public Context mContext;

    private vehicltypeadapterListener listener;

    double Price;
    private ArrayList<VehicleTypes> vehicletype_list = new ArrayList<>();
    private ArrayList<VehicleTypesWithCount> vehiclecount_list = new ArrayList<>();
    int Plougher;
    public vehicltypeadapter(Context context,ArrayList<VehicleTypes> vehicletype_list,vehicltypeadapterListener listener) {
        this.vehicletype_list = vehicletype_list;
        this.mContext = context;
        this.listener = listener;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View _v = LayoutInflater.from(parent.getContext()).inflate(R.layout.selectedvehicles, parent, false );
        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(_v);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
      //  viewHolder.Vehiclecount.setText("1");
        viewHolder.vehiclename.setText(vehicletype_list.get(position).getVehiclename());
        vehiclecount_list.add(new VehicleTypesWithCount(position,0,"",0));
        //  attachment.add(new Attachments(plousherList.get(position).getDesc(),Double.valueOf(viewHolder.amount.getText().toString()),plousherList.get(position).getTypeCdId()));

        viewHolder.Vehiclecount.addTextChangedListener(new HirechargesAdapter.TextChangedListener<EditText>( viewHolder.Vehiclecount) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                //Do stuff
                vehiclecount_list.set(position,
                        new VehicleTypesWithCount(position,
                                vehicletype_list.get(position).getVehicleid(),
                                vehicletype_list.get(position).getVehiclename(),
                        TextUtils.isEmpty(viewHolder.Vehiclecount.getText()) == true ? 0 : Integer.valueOf(viewHolder.Vehiclecount.getText().toString())));

                listener.OnVehiclecount(position,vehiclecount_list);


            }
        });


    }





    // Return the size arraylist
    @Override
    public int getItemCount() {


        if (vehicletype_list != null)
            return vehicletype_list.size();
        else
            return 0;
    }


    public  class ViewHolder extends RecyclerView.ViewHolder {

        public TextView vehiclename, villagename2;

        public EditText Vehiclecount;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            vehiclename = (TextView) itemLayoutView.findViewById(R.id.vehiclename);
            Vehiclecount = (EditText) itemLayoutView.findViewById(R.id.Vehiclecount);



        }
    }


    public interface vehicltypeadapterListener {
        void OnVehiclecount(int pos,ArrayList<VehicleTypesWithCount> vehiclecountlist);
    }
}
