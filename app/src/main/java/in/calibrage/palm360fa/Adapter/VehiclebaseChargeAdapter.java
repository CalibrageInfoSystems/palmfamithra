package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Model.GetTypeCdDmt;
import in.calibrage.palm360fa.Model.Getdestinations;
import in.calibrage.palm360fa.Model.Hirecharges;
import in.calibrage.palm360fa.Model.Hirecharges_new;
import in.calibrage.palm360fa.Model.QuickPayModel;
import in.calibrage.palm360fa.Model.VehicleTypes;
import in.calibrage.palm360fa.Model.spinneritemmodel;
import in.calibrage.palm360fa.R;
import rx.Subscription;

public class VehiclebaseChargeAdapter extends RecyclerView.Adapter<VehiclebaseChargeAdapter.ViewHolder> {
    public Context mContext;
    //private List<GetPlotVillagesByFarmerCode.ListResult>villagesList = new ArrayList<>();
    private VehiclebaseChargeAdapterListener listener;
    String Destination1,Destionation2;
    Double price1,price2;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    public Spinner destination1,destination2,hiring_basics,hiring_basics2;
    String datetimevaluereq;
    List<String> get_ccs= new ArrayList<String>();
    List<Integer>get_ccsid= new ArrayList<Integer>();
    Getdestinations CCList;
   int Village_id;
   int mainposition;
int hirebasis_id;
int CC1,CC2,HirebaseID1,HirebaseID2;
    private  ArrayList<Hirecharges_new> hirechargesmain = new ArrayList<>();
    private  ArrayList<Hirecharges> hirecharges = new ArrayList<>();
    private ArrayList<VehicleTypes> vehicletypelist = new ArrayList<>();
    List<String> get_hiringbasis = new ArrayList<String>();
    List<Integer> get_hiringbasisid = new ArrayList<Integer>();
    ArrayList<spinneritemmodel> cclistArray = new ArrayList<spinneritemmodel>();
    ArrayList<spinneritemmodel> newcclistArray = new ArrayList<spinneritemmodel>();
    ArrayList<spinneritemmodel> hirebasisdataArry = new ArrayList<spinneritemmodel>();
    GetTypeCdDmt gethirechargesdata;
    SpinnerArrayAdapter adapter;
    String name;
    public VehiclebaseChargeAdapter(Context mContext,int mainposition, ArrayList<VehicleTypes> vehicletypelist, Getdestinations ccList, int village_id,GetTypeCdDmt gethirechargesdata,ArrayList<Hirecharges_new> hirechargesmain,VehiclebaseChargeAdapterListener listener) {
        this.Village_id = village_id;
        this.mainposition = mainposition;
        this.mContext = mContext;
        this.CCList= ccList;
        this.listener =listener;
        this.hirechargesmain = hirechargesmain;
        this.gethirechargesdata = gethirechargesdata;
        hirecharges= hirechargesmain.get(mainposition).getHirecharge_details();
        this.vehicletypelist =vehicletypelist;


        cclistArray.add(new spinneritemmodel(0,"Select"));
        for (Getdestinations.CollectionCenter data : CCList.getResult().getImportantPlaces().getCollectionCenters()) {
            cclistArray.add(new spinneritemmodel(data.getId(),data.getCollectionCenter()));
        }
        for (Getdestinations.Mill data : CCList.getResult().getImportantPlaces().getMills()) {
            cclistArray.add(new spinneritemmodel(data.getId(),data.getCollectionCenter()));
        }
        hirebasisdataArry.add(new spinneritemmodel(0,"Select"));
        for (GetTypeCdDmt.ListResult data : gethirechargesdata.getListResult()
        ) {
            hirebasisdataArry.add(new spinneritemmodel(data.getTypeCdId(),data.getDesc()));
        }


    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {


        View rowView;
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView=inflater.inflate(R.layout.vehiclehirecharges, parent,false);
        // create a new view
        //  View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hirecharges, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(rowView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {


        viewHolder.vehiclename.setText(vehicletypelist.get(position).getVehiclename());
    //    viewHolder.vehiclename2.setText(vehicletypelist.get(position).getVehiclename());

        adapter = new SpinnerArrayAdapter(mContext, cclistArray);
        adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        destination1.setAdapter(adapter);


        //  spinner.setAdapter(spinnerArrayAdapter);
        destination1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int i, long id)
            {
                spinneritemmodel clickedItem = (spinneritemmodel)
                        parent.getItemAtPosition(i);
                 name = clickedItem.getName();
                CC1 =clickedItem.getId();

                Hirecharges currentHireChage1 = hirecharges.get(position);
                currentHireChage1.setCcid(CC1);

                hirecharges.set(position,currentHireChage1);
                listener.onSelected(position,hirecharges,mainposition);
//                ArrayList<spinneritemmodel> secondarray = new ArrayList<>();
//                for (spinneritemmodel item : cclistArray) {
//                    if(!TextUtils.isEmpty(name) &&  !name.equalsIgnoreCase("Select") && item.getName().equalsIgnoreCase(name))
//                    {
//
//                    }else{
//                        secondarray.add(item);
//                    }
//                }


              //  Toast.makeText(mContext, name +"   ID"+ CC1 + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        adapter = new SpinnerArrayAdapter(mContext,  cclistArray);
        adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        destination2.setAdapter(adapter);

        //  spinner.setAdapter(spinnerArrayAdapter);
        destination2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int i, long id)
            {
                spinneritemmodel clickedItem = (spinneritemmodel)
                        parent.getItemAtPosition(i);
                String name = clickedItem.getName();
                CC2 =clickedItem.getId();
                Hirecharges currentHireChage1 = hirecharges.get(position);
                currentHireChage1.setOptccid(CC2);

                hirecharges.set(position,currentHireChage1);
                listener.onSelected(position,hirecharges,mainposition);
                //Toast.makeText(mContext, name +"   ID"+ CC2 + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        adapter = new SpinnerArrayAdapter(mContext, hirebasisdataArry);
        adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        hiring_basics2.setAdapter(adapter);


        //  spinner.setAdapter(spinnerArrayAdapter);
        hiring_basics2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int i, long id)
            {
                spinneritemmodel clickedItem = (spinneritemmodel)
                        parent.getItemAtPosition(i);
                String name = clickedItem.getName();
                HirebaseID2 =clickedItem.getId();

                Hirecharges currentHireChage1 = hirecharges.get(position);
                currentHireChage1.setOpthirebasisid(HirebaseID2);

                hirecharges.set(position,currentHireChage1);
                listener.onSelected(position,hirecharges,mainposition);
                //Toast.makeText(mContext, name +"   ID"+ HirebaseID2 + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


        adapter = new SpinnerArrayAdapter(mContext, hirebasisdataArry);
        adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
        hiring_basics.setAdapter(adapter);


        //  spinner.setAdapter(spinnerArrayAdapter);
        hiring_basics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int i, long id)
            {
                spinneritemmodel clickedItem = (spinneritemmodel)
                        parent.getItemAtPosition(i);
                String name = clickedItem.getName();
                HirebaseID1 =clickedItem.getId();

                Hirecharges currentHireChage = hirecharges.get(position);
                currentHireChage.setHirebasisid(HirebaseID1);

                hirecharges.set(position,currentHireChage);



                listener.onSelected(position,hirecharges,mainposition);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });




//    hirecharges.add(new Hirecharges(get_ccsid.get(destination1.getSelectedItemPosition()),Double.valueOf(viewHolder.price1.getText().toString()),position,get_ccsid.get(destination2.getSelectedItemPosition()),Double.valueOf(viewHolder.price2.getText().toString())));

        viewHolder.price1.addTextChangedListener(new HirechargesAdapter.TextChangedListener<EditText>( viewHolder.price1) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                //Do stuff


//  Updating Existing values from List

                Hirecharges currentHireChage = hirecharges.get(position);
                currentHireChage.setPrice(TextUtils.isEmpty(viewHolder.price1.getText()) == true ? 0 : Double.valueOf(viewHolder.price1.getText().toString()));
                currentHireChage.setVehicleid(vehicletypelist.get(position).getVehicleid());
                currentHireChage.setCcid(CC1);
                currentHireChage.setVillage_id(Village_id);

                hirecharges.set(position,currentHireChage);
//                hirecharges.set(position,new
//                        Hirecharges(
//                        CC1,
//                        TextUtils.isEmpty(viewHolder.price1.getText()) == true ? 0 : Double.valueOf(viewHolder.price1.getText().toString()),
//                        CC2,
//                        TextUtils.isEmpty( viewHolder.price2.getText().toString()) == true ? 0 : Double.valueOf(viewHolder.price2.getText().toString()),
//                        Village_id,
//                        vehicletypelist.get(position).getVehicleid(),
//                        HirebaseID1,HirebaseID2
//                ));



                listener.onSelected(position,hirecharges,mainposition);


            }
        });
        viewHolder.price2.addTextChangedListener(new HirechargesAdapter.TextChangedListener<EditText>( viewHolder.price2) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                //Do stuff
                Log.e("============>",target.getText().toString());
//  Updating Existing values from List

                Hirecharges currentHireChage = hirecharges.get(position);
                currentHireChage.setOptPrice(TextUtils.isEmpty(viewHolder.price2.getText()) == true ? 0 : Double.valueOf(viewHolder.price2.getText().toString()));
                currentHireChage.setVehicleid(vehicletypelist.get(position).getVehicleid());
                currentHireChage.setOptccid(CC2);
                currentHireChage.setVillage_id(Village_id);

                hirecharges.set(position,currentHireChage);
//                hirecharges.set(position,new
//                        Hirecharges(
//                        CC1,
//                        TextUtils.isEmpty(viewHolder.price1.getText()) == true ? 0 : Double.valueOf(viewHolder.price1.getText().toString()),
//                        CC2,
//                        TextUtils.isEmpty( viewHolder.price2.getText().toString()) == true ? 0 : Double.valueOf(viewHolder.price2.getText().toString()),
//                        Village_id,
//                        vehicletypelist.get(position).getVehicleid(), HirebaseID1,HirebaseID2
//                ));

                listener.onSelected(position,hirecharges,mainposition);


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


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView villagename,vehiclename,vehiclename2;

        public EditText price1,price2;
        public TextView tvDate;
        public CheckBox chkSelected;
       // Button add;
        public CardView card_view;
        public QuickPayModel singlestudent;
        private Subscription mSubscription;
        private SpotsDialog mdilogue;
        String datetimevaluereq;
        List<String>get_ccs= new ArrayList<String>();
        List<Integer>get_ccsid= new ArrayList<Integer>();

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            vehiclename = (TextView) itemLayoutView.findViewById(R.id.vehiclename);
           // vehiclename2 = (TextView) itemLayoutView.findViewById(R.id.vehiclename2);
            villagename = (TextView) itemLayoutView.findViewById(R.id.villagename);
            destination1 = (Spinner) itemLayoutView.findViewById(R.id.destination1);
            destination2 = (Spinner) itemLayoutView.findViewById(R.id.destination2);
            price1 = (EditText) itemLayoutView.findViewById(R.id.price);
            price2 = (EditText) itemLayoutView.findViewById(R.id.price2);
            //add = (Button) itemLayoutView.findViewById(R.id.add);
            hiring_basics =(Spinner)itemLayoutView.findViewById(R.id.hiring_basics) ;
            hiring_basics2=(Spinner)itemLayoutView.findViewById(R.id.hiring_basics2) ;


        }



//
    }



    public interface VehiclebaseChargeAdapterListener {

        void onSelected(int po, ArrayList<Hirecharges> hirecharges,int mainposition);
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









