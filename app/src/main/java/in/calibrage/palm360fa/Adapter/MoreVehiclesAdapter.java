package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.calibrage.palm360fa.R;

public class MoreVehiclesAdapter  extends RecyclerView.Adapter<MoreVehiclesAdapter.MyViewHolder> {


    private Context context;
    ArrayList<Vehicle> vehicle_arrayList=new ArrayList<Vehicle>();
    private MoreVehiclesAdapter.VechileListListner listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView vehicletype, drivertype,driverpayment,amountper,currentlyvehicle,willingtorent,txtDirver,othertext,othervehicletext;
        public ImageView thumbnail;
        public ImageButton imgdelete;
        public MyViewHolder(View view) {
            super(view);
            vehicletype = view.findViewById(R.id.vehicletype);
            drivertype = view.findViewById(R.id.drivertype);
            driverpayment = view.findViewById(R.id.driverpayment);
            amountper = view.findViewById(R.id.amountper);
            currentlyvehicle = view.findViewById(R.id.currentlyvehicle);
            willingtorent = view.findViewById(R.id.willingtorent);
            imgdelete = view.findViewById(R.id.imgdelete);
            txtDirver = view.findViewById(R.id.txtDirver);
            othertext = view.findViewById(R.id.othertext);
            othervehicletext = view.findViewById(R.id.othervehicletext);

        }

    }


    public MoreVehiclesAdapter(Context context, ArrayList<Vehicle> vehicle_arrayList,MoreVehiclesAdapter.VechileListListner _listener) {
        this.context = context;

        this.vehicle_arrayList = vehicle_arrayList;
        this.listener =_listener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehicledata, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

       holder.vehicletype.setText(vehicle_arrayList.get(position).getVehicletype());
        holder.drivertype.setText(vehicle_arrayList.get(position).getDriver());
        holder.driverpayment.setText( vehicle_arrayList.get(position).getAmount()+"/"+vehicle_arrayList.get(position).getTime());
        holder.othertext.setText(vehicle_arrayList.get(position).getOthervehicletext());
        holder.currentlyvehicle.setText(vehicle_arrayList.get(position).getCurrentlyrent());
        holder.willingtorent.setText(vehicle_arrayList.get(position).getWillingtorent());

        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete Specific Image
                listener.onItemDelete(position);

            }
        });

        if(vehicle_arrayList.get(position).getAmount() == 0)
        {
            holder.driverpayment.setVisibility(View.GONE);
            holder.txtDirver.setVisibility(View.GONE);
        }
     if( TextUtils.isEmpty(vehicle_arrayList.get(position).getOthervehicletext())){
         holder.othertext.setVisibility(View.GONE);
         holder.othervehicletext.setVisibility(View.GONE);
        }
//
//
//        Picasso.with(context).load(settings.getImage()).error(R.drawable.ic_user).transform(new CircleTransform()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return vehicle_arrayList.size();
    }


    public interface VechileListListner {
        void onItemDelete(int po);
    }
}
