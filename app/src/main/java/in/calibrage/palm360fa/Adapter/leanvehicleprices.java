package in.calibrage.palm360fa.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.calibrage.palm360fa.Model.Vehicletype;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.Views.transport.Model.Vehicledata;

public class leanvehicleprices extends RecyclerView.Adapter<leanvehicleprices.ViewHolder>{

    private ArrayList<Vehicletype> vehicletypelist = new ArrayList<>();
    public Context mContext;
    private leanvehiclepricesListener listener;
    int _villageId,_destination1,_destination2;
    private ArrayList<Vehicledata> _leanVechileData = new ArrayList<>();
    int _currentPo;


    public leanvehicleprices(int _currentPo, Context context,  ArrayList<Vehicletype> vehicletypelist,int villageId, leanvehiclepricesListener listener ) {
        this.vehicletypelist = vehicletypelist;
        this.mContext = context;
        this.listener =listener;
        this._villageId = villageId;
        this._destination1 = 0;
        this._destination2 = 0;
        this._currentPo = _currentPo;
        Log.d("MAHESH #######", "###################  VILLAGE ID   leanvehicleprices :"+villageId);
        for(int i =0; i < vehicletypelist.size(); i ++)
        {
            _leanVechileData.add(new Vehicledata(101,0,_villageId,_destination1,0,_destination2,0));
        }

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView;
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView=inflater.inflate(R.layout.vehiclepriceslyt, parent,false);
        // create a new view
        //  View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hirecharges, null);

        // create ViewHolder

       ViewHolder viewHolder = new ViewHolder(rowView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Vehicledata currentitem  = _leanVechileData.get(position);
        currentitem.setVillageId(_villageId);

        holder.vehicleName.setText(vehicletypelist.get(position).getVehicleName() + "");
        holder.destination1.addTextChangedListener(new vehicleprices.TextChangedListener<EditText>(holder.destination1) {

            @Override
            public void onTextChanged(EditText target, Editable s) {
                currentitem.setPrice(TextUtils.isEmpty(target.getText().toString()) == true ? 0 : Double.parseDouble(target.getText().toString()));
                currentitem.setVehicleTypeId(vehicletypelist.get(position).getVehicleId());
                _leanVechileData.set(position,currentitem);
                listener.onLeakUpdated(_currentPo,_leanVechileData);
            }
        });
        holder.destination2.addTextChangedListener(new vehicleprices.TextChangedListener<EditText>(holder.destination2) {

            @Override
            public void onTextChanged(EditText target, Editable s) {
                currentitem.setPrice2(TextUtils.isEmpty(target.getText().toString()) == true ? 0 : Double.parseDouble(target.getText().toString()));
                currentitem.setVehicleTypeId(vehicletypelist.get(position).getVehicleId());
                _leanVechileData.set(position,currentitem);
                listener.onLeakUpdated(_currentPo,_leanVechileData);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (vehicletypelist != null)
            return vehicletypelist.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView vehicleName;
        EditText destination1, destination2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vehicleName = itemView.findViewById(R.id.vehicleName);
            destination1 = itemView.findViewById(R.id.destination1_et);
            destination2 = itemView.findViewById(R.id.destination2_et);
        }
    }

    private void showDialog(Context mContext, String msg) {
        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        final ImageView img = dialog.findViewById(R.id.img_cross);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);
        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Animatable) img.getDrawable()).start();
            }
        }, 500);
    }

    public interface leanvehiclepricesListener {

        void onLeakUpdated(int po, ArrayList<Vehicledata> leanvehiclepricesList);
    }

    public abstract static class TextChangedListener<T> implements TextWatcher {
        private T target;

        public TextChangedListener(T target) {
            this.target = target;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            this.onTextChanged(target, s);
        }

        public abstract void onTextChanged(T target, Editable s);
    }
}
