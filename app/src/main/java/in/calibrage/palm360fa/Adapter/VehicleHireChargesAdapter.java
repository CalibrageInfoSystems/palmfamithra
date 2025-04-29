package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Model.GetPlotVillagesByFarmerCode;
import in.calibrage.palm360fa.Model.Getdestinations;
import in.calibrage.palm360fa.Model.Hirecharges;
import in.calibrage.palm360fa.Model.Hirecharges_new;
import in.calibrage.palm360fa.Model.Vehicletype;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.Views.transport.Model.Transporthirebasis;
import in.calibrage.palm360fa.Views.transport.Model.VillageWithData;
import rx.Subscription;

public class VehicleHireChargesAdapter  extends RecyclerView.Adapter<VehicleHireChargesAdapter.ViewHolder> implements HirechargesforvehicleAdapter.HirechargesforvehicleAdapterlister {
    public Context mContext;
    //private List<GetPlotVillagesByFarmerCode.ListResult>villagesList = new ArrayList<>();
    private VehicleHireChargesAdapterListener listener;
    String Destination1, Destionation2;
    Double price1, price2;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    public Spinner destination1, destination2;
    private ArrayList<Hirecharges_new> hirechargesmain = new ArrayList<>();
    String datetimevaluereq;
    List<String> get_ccs = new ArrayList<String>();
    List<Integer> get_ccsid = new ArrayList<Integer>();
    Getdestinations CCList;
    List<GetPlotVillagesByFarmerCode.ListResult> villagesList;
    private ArrayList<Vehicletype> vehicletypelist = new ArrayList<>();
    ArrayList<VillageWithData> finalVillageData =new ArrayList<>();


    //  private List<Getdestinations.Mill>MCCList = new ArrayList<>();

    public VehicleHireChargesAdapter(Context context, List<GetPlotVillagesByFarmerCode.ListResult> villagesList, Getdestinations cclist,ArrayList<Vehicletype> vehicletypelist , VehicleHireChargesAdapterListener listener) {
        this.villagesList = villagesList;
        this.mContext = context;
        this.CCList = cclist;
        this.listener = listener;
        this.vehicletypelist = vehicletypelist;

        Log.d("vehicletypelist", vehicletypelist.get(0).getVehicleName() + "");
        Log.d("vehicletypelist", vehicletypelist.get(0).getVehicleName() + "");
        for(int i =0; i < villagesList.size(); i ++){
            Log.d("MAHESH #######", "###################  VILLAGE ID "+villagesList.get(i).getVillageId());
            ArrayList<Hirecharges> hirecharges = new ArrayList<>();

            hirechargesmain.add(new Hirecharges_new(0,hirecharges));
            finalVillageData.add(new VillageWithData(0,null,null));

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {


        View rowView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.hirecharges, parent, false);

        ViewHolder viewHolder = new ViewHolder(rowView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        VillageWithData vdata = finalVillageData.get(position);
       viewHolder.villagename.setText(villagesList.get(position).getVillage());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                viewHolder.rvSubItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

       // Log.d("CCLIST", CCList.getResult().getImportantPlaces().getCollectionCenters().size() + "");


        HirechargesforvehicleAdapter subItemAdapter = new HirechargesforvehicleAdapter(mContext,position, CCList, villagesList, villagesList.get(position).getVillageId(), hirechargesmain,vehicletypelist,VehicleHireChargesAdapter.this);

        viewHolder.rvSubItem.setLayoutManager(layoutManager);
        viewHolder.rvSubItem.setAdapter(subItemAdapter);
        viewHolder.rvSubItem.setRecycledViewPool(viewPool);

        vdata.setVillageId(villagesList.get(position).getVillageId());
        vdata.setVillageName(villagesList.get(position).getVillage());
        finalVillageData.set(position,vdata);


    }


    // Return the size arraylist
    @Override
    public int getItemCount() {

        if (villagesList != null)
            return villagesList.size();
        else
            return 1;
    }


    @Override
    public void onUpdatedLeanData(int mainpo ,ArrayList<Transporthirebasis> maindata) {
        VillageWithData vdata = finalVillageData.get(mainpo);

        vdata.setAllListinfo(maindata);
        finalVillageData.set(mainpo,vdata);
        listener.onUpdatedFinalData(mainpo,finalVillageData);

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView villagename, vehiclename, vehiclename2;

        public CardView card_view;
        private RecyclerView rvSubItem;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            villagename = (TextView) itemLayoutView.findViewById(R.id.villagename);

            rvSubItem = itemView.findViewById(R.id.rv_sub_item);

        }


//
    }


    public interface VehicleHireChargesAdapterListener {

        void onUpdatedFinalData(int po, ArrayList<VillageWithData> finaldatalist);
    }

    public abstract static class TextChangedListener<T> implements TextWatcher {
        private T target;

        public TextChangedListener(T target) {
            this.target = target;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            this.onTextChanged(target, s);
        }

        public abstract void onTextChanged(T target, Editable s);
    }
}
