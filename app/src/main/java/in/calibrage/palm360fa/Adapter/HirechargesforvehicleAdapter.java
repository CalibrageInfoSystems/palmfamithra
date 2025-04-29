package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Model.GetHiringBasisTypes;
import in.calibrage.palm360fa.Model.GetPlotVillagesByFarmerCode;
import in.calibrage.palm360fa.Model.GetSourceofTransportType;
import in.calibrage.palm360fa.Model.Getdestinations;
import in.calibrage.palm360fa.Model.Hirecharges;
import in.calibrage.palm360fa.Model.Hirecharges_new;
import in.calibrage.palm360fa.Model.Vehicletype;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.Views.transport.Model.Transporthirebasis;
import in.calibrage.palm360fa.Views.transport.Model.TypeItem;
import in.calibrage.palm360fa.Views.transport.Model.Vehicledata;
import in.calibrage.palm360fa.Views.transport.SpinnerTypeArrayAdapter;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HirechargesforvehicleAdapter extends RecyclerView.Adapter<HirechargesforvehicleAdapter.ViewHolder> implements vehicleprices.vehiclepricesListener, leanvehicleprices.leanvehiclepricesListener {

    public static  String TAG = HirechargesforvehicleAdapter.class.getSimpleName();

    List<String> get_sourceTransportType = new ArrayList<String>();
    List<Integer> get_sourceTransportType_Id = new ArrayList<Integer>();

    List<String>get_hiringbasisType = new ArrayList<String>();
    List<Integer> get_hiringbasisType_Id = new ArrayList<Integer>();

    private ArrayList<TypeItem> _howdriverTransportoFFBArray, _hirebasisArr, _destinationsArr;


    SpinnerTypeArrayAdapter _howfarmertransportfromffbtofacotryAdapter, _hiringbasisAdapter, _destinations1Adapter,_destinations2Adapter, _leandestinations1Adapter,_leandestinations2Adapter;

    public Context mContext;
    //private List<GetPlotVillagesByFarmerCode.ListResult>villagesList = new ArrayList<>();
    private HirechargesforvehicleAdapterlister listener;
    String Destination1,Destionation2;
    String leanDestination1, leanDestination2;
    Double price1,price2;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    public Spinner destination1,destination2;
    public Spinner leandestination1,leandestination2;

    String datetimevaluereq;
    List<String> get_ccs= new ArrayList<String>();
    List<Integer>get_ccsid= new ArrayList<Integer>();
    Getdestinations CCList;
    int Village_id;
    int mainposition;
    List<GetPlotVillagesByFarmerCode.ListResult> villagesList;
    private  ArrayList<Hirecharges_new> hirechargesmain = new ArrayList<>();
    private  ArrayList<Hirecharges> hirecharges = new ArrayList<>();
    private ArrayList<Vehicletype> vehicletypelist = new ArrayList<>();


    //  private List<Getdestinations.Mill>MCCList = new ArrayList<>();
    ArrayList<Vehicledata> PeakPricesFromAdapter = new ArrayList<>();
    ArrayList<Vehicledata> LeanPricesFromAdapter = new ArrayList<>();
    private ArrayList<Transporthirebasis> mainList = new ArrayList<>();
    public HirechargesforvehicleAdapter(Context mContext, int mainposition, Getdestinations ccList, List<GetPlotVillagesByFarmerCode.ListResult> villagesList, int village_id, ArrayList<Hirecharges_new> hirechargesmain, ArrayList<Vehicletype> vehicletypelist, HirechargesforvehicleAdapterlister listener) {
        this.Village_id = village_id;
        this.villagesList = villagesList;
        this.mainposition = mainposition;
        this.mContext = mContext;
        this.CCList= ccList;
        this.listener =listener;
        this.hirechargesmain = hirechargesmain;
        hirecharges= hirechargesmain.get(mainposition).getHirecharge_details();
        this.vehicletypelist = vehicletypelist;

        Log.d("vehicletypelistadapeter", vehicletypelist.get(0).getVehicleName() + "");

//        for(int i = 0; i < villagesList.size(); i ++)
//        {
            mainList.add(new Transporthirebasis(0,null,null,null,null,null,null,0));
//        }

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View rowView;
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView=inflater.inflate(R.layout.donthavevehicleadapter, parent,false);
        // create a new view
        //  View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hirecharges, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(rowView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
       final Transporthirebasis transporthirebasis = mainList.get(position);
        viewHolder.pvillagename.setText(villagesList.get(position).getVillage());
        viewHolder.lvillagename.setText(villagesList.get(position).getVillage());



//        viewHolder.vehiclename.setText(vehicletypelist.get(position).getVehiclename());
//        viewHolder.vehiclename2.setText(vehicletypelist.get(position).getVehiclename());


        LinearLayoutManager layoutManager = new LinearLayoutManager(
                viewHolder.peakseasonvehicleprices.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(
                viewHolder.leanseasonvehicleprices.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        if (CCList.getResult().getImportantPlaces().getCollectionCenters() != null) {
            _destinationsArr = new ArrayList<>();
            _destinationsArr.add(new TypeItem(0, "Select"));
            for (Getdestinations.CollectionCenter data : CCList.getResult().getImportantPlaces().getCollectionCenters()) {
                _destinationsArr.add(new TypeItem(data.getId(), data.getCollectionCenter()));
            }
            for (Getdestinations.Mill data : CCList.getResult().getImportantPlaces().getMills()) {
                _destinationsArr.add(new TypeItem(data.getId(), data.getCollectionCenter()));

            }


            _destinations1Adapter = new SpinnerTypeArrayAdapter(mContext, _destinationsArr);
            _destinations1Adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
            destination1.setAdapter(_destinations1Adapter);

            _destinations2Adapter = new SpinnerTypeArrayAdapter(mContext, _destinationsArr);
            _destinations2Adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
            destination2.setAdapter(_destinations2Adapter);

            _leandestinations1Adapter = new SpinnerTypeArrayAdapter(mContext, _destinationsArr);
            _leandestinations1Adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
            leandestination1.setAdapter(_leandestinations1Adapter);
            _leandestinations2Adapter = new SpinnerTypeArrayAdapter(mContext, _destinationsArr);
            _leandestinations2Adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
            leandestination2.setAdapter(_leandestinations2Adapter);


        }


            vehicleprices subItemAdapter = new vehicleprices(position, mContext, vehicletypelist, villagesList.get(position).getVillageId(),HirechargesforvehicleAdapter.this);
            viewHolder.peakseasonvehicleprices.setLayoutManager(layoutManager);
            viewHolder.peakseasonvehicleprices.setAdapter(subItemAdapter);
            viewHolder.peakseasonvehicleprices.setRecycledViewPool(viewPool);

           leanvehicleprices leanvehiclepricesadapter = new leanvehicleprices( position,mContext,vehicletypelist,villagesList.get(position).getVillageId(),HirechargesforvehicleAdapter.this);

            viewHolder.leanseasonvehicleprices.setLayoutManager(layoutManager1);
            viewHolder.leanseasonvehicleprices.setAdapter(leanvehiclepricesadapter);
            viewHolder.leanseasonvehicleprices.setRecycledViewPool(viewPool);


        viewHolder.otherTypeTransport.addTextChangedListener(new vehicleprices.TextChangedListener<EditText>(viewHolder.otherTypeTransport) {

            @Override
            public void onTextChanged(EditText target, Editable s) {
                transporthirebasis.setTransportFFBDesc(target.getText().toString());
                mainList.set(position,transporthirebasis);
                listener.onUpdatedLeanData(mainposition,mainList);
            }
        });

        destination1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i1, long l) {

                TypeItem peakDest1 = (TypeItem) destination1.getSelectedItem();

                for(int i =0; i <PeakPricesFromAdapter.size();i ++)
                {
                    Vehicledata curent = PeakPricesFromAdapter.get(i);
                    curent.setDestinationId(peakDest1.getId());


                    PeakPricesFromAdapter.set(i,curent);
                }

                transporthirebasis.setPeakdata(PeakPricesFromAdapter);
                mainList.set(position,transporthirebasis);

                listener.onUpdatedLeanData(mainposition,mainList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        destination2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i1, long l) {

                TypeItem peakDest2 = (TypeItem) destination2.getSelectedItem();
                for(int i =0; i <PeakPricesFromAdapter.size();i ++)
                {
                    Vehicledata curent = PeakPricesFromAdapter.get(i);

                    curent.setDestinationId2(peakDest2.getId());

                    PeakPricesFromAdapter.set(i,curent);
                }

                transporthirebasis.setPeakdata(PeakPricesFromAdapter);
                mainList.set(position,transporthirebasis);

                listener.onUpdatedLeanData(mainposition,mainList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        leandestination1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i1, long l) {
                TypeItem leanDest1 = (TypeItem) leandestination1.getSelectedItem();

                for(int i =0; i <LeanPricesFromAdapter.size();i ++)
                {
                    Vehicledata curent = LeanPricesFromAdapter.get(i);
                    curent.setDestinationId(leanDest1.getId());

                    LeanPricesFromAdapter.set(i,curent);
                }

                transporthirebasis.setLeanData(LeanPricesFromAdapter);
                mainList.set(position,transporthirebasis);

                listener.onUpdatedLeanData(mainposition,mainList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        leandestination2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i1, long l) {

                TypeItem leanDest2 = (TypeItem) leandestination2.getSelectedItem();
                for(int i =0; i <LeanPricesFromAdapter.size();i ++)
                {
                    Vehicledata curent = LeanPricesFromAdapter.get(i);

                    curent.setDestinationId2(leanDest2.getId());

                    LeanPricesFromAdapter.set(i,curent);
                }

                transporthirebasis.setLeanData(LeanPricesFromAdapter);
                mainList.set(position,transporthirebasis);

                listener.onUpdatedLeanData(mainposition,mainList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


       viewHolder.howfarmertransportfromffbtofacotry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                    TypeItem sourceofTransport = (TypeItem) viewHolder.howfarmertransportfromffbtofacotry.getSelectedItem();

                    if(sourceofTransport.getName().equalsIgnoreCase("Others"))
                    {
                        viewHolder.otherTypeTransport.setVisibility(View.VISIBLE);

                    }else{
                        viewHolder.otherTypeTransport.setVisibility(View.GONE);
                        viewHolder.otherTypeTransport.setText("");

                    }

                    transporthirebasis.setTransportFFBTypeId(sourceofTransport.getId());
                    Log.d("TransportFFBTypeId", sourceofTransport.getId() + "");

                    mainList.set(position,transporthirebasis);
                    listener.onUpdatedLeanData(mainposition,mainList);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
       viewHolder.hiringbasis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TypeItem hiringbasisvalue =     (TypeItem) viewHolder.hiringbasis.getSelectedItem();
                transporthirebasis.setHirebasisId(hiringbasisvalue.getId());
                mainList.set(position,transporthirebasis);
                listener.onUpdatedLeanData(mainposition,mainList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
          });
        viewHolder.ownername.addTextChangedListener(new vehicleprices.TextChangedListener<EditText>(viewHolder.ownername) {

            @Override
            public void onTextChanged(EditText target, Editable s) {

                transporthirebasis.setOwnerName(target.getText().toString());
                mainList.set(position,transporthirebasis);
                listener.onUpdatedLeanData(mainposition,mainList);
            }
        });

        viewHolder.owneraddress.addTextChangedListener(new vehicleprices.TextChangedListener<EditText>(viewHolder.owneraddress) {

            @Override
            public void onTextChanged(EditText target, Editable s) {

                transporthirebasis.setOwneraddress(target.getText().toString());
                mainList.set(position,transporthirebasis);
                listener.onUpdatedLeanData(mainposition,mainList);
            }
        });

        viewHolder.ownermobileNumber.addTextChangedListener(new vehicleprices.TextChangedListener<EditText>(viewHolder.ownermobileNumber) {

            @Override
            public void onTextChanged(EditText target, Editable s) {

                transporthirebasis.setOwnerMobileNumber(target.getText().toString());
                mainList.set(position,transporthirebasis);
                listener.onUpdatedLeanData(mainposition,mainList);
            }
        });

    }


    @Override
    public int getItemCount() {

            return 1;
    }



    @Override
    public void onPeakDataupdated(int po, ArrayList<Vehicledata> vehiclepricesList) {
        this.PeakPricesFromAdapter = vehiclepricesList;


        TypeItem Dest1 = (TypeItem) destination1.getSelectedItem();
        TypeItem Dest2 = (TypeItem) destination2.getSelectedItem();



        for(int i =0; i <PeakPricesFromAdapter.size();i ++)
        {

            Log.d("Dest1selected", Dest1.getId() + "");
            Log.d("Dest2selected", Dest2.getId() + "");


            Vehicledata curent = PeakPricesFromAdapter.get(i);
            curent.setDestinationId(Dest1.getId());
            curent.setDestinationId2(Dest2.getId());

            PeakPricesFromAdapter.set(i,curent);
        }
        final Transporthirebasis transporthirebasis = mainList.get(po);
        transporthirebasis.setPeakdata(PeakPricesFromAdapter);
        mainList.set(po,transporthirebasis);
        listener.onUpdatedLeanData(mainposition,mainList);
    }


    @Override
    public void onLeakUpdated(int po, ArrayList<Vehicledata> leanvehiclepricesList) {
        this.LeanPricesFromAdapter = leanvehiclepricesList;
        TypeItem leanDest1 = (TypeItem) leandestination1.getSelectedItem();
        TypeItem leanDest2 = (TypeItem) leandestination2.getSelectedItem();
        for(int i =0; i <LeanPricesFromAdapter.size();i ++)
        {
            Vehicledata curent = LeanPricesFromAdapter.get(i);
            curent.setDestinationId(leanDest1.getId());
            curent.setDestinationId2(leanDest2.getId());

            LeanPricesFromAdapter.set(i,curent);
        }
        final Transporthirebasis transporthirebasis = mainList.get(po);
        transporthirebasis.setLeanData(LeanPricesFromAdapter);
        mainList.set(po,transporthirebasis);

        listener.onUpdatedLeanData(mainposition,mainList);

    }




    class ViewHolder extends RecyclerView.ViewHolder{

        EditText pvillagename, lvillagename, ownername, owneraddress, ownermobileNumber, otherTypeTransport;

        public ImageView thumbnail;

        RecyclerView peakseasonvehicleprices, leanseasonvehicleprices;
        Spinner howfarmertransportfromffbtofacotry,hiringbasis;

        String selectedhowfarmertransportfromffbtofacotry, selectedhiringbasis;

        int sourceTransportTypeid, hiringbasisid;
        private LinearLayoutManager linearLayoutManager;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            howfarmertransportfromffbtofacotry = itemLayoutView.findViewById(R.id.farmertransport);
            hiringbasis = itemLayoutView.findViewById(R.id.hiringbasis);
            destination1 = itemLayoutView.findViewById(R.id.destination1spinner);
            destination2 = itemLayoutView.findViewById(R.id.destination2spinner);
            otherTypeTransport = itemLayoutView.findViewById(R.id.no_othertypevehicle);

            leandestination1 = itemLayoutView.findViewById(R.id.leandestination1spinner);
            leandestination2 = itemLayoutView.findViewById(R.id.leandestination2spinner);

            ownername = itemLayoutView.findViewById(R.id.hirevehicleownername);
            owneraddress = itemLayoutView.findViewById(R.id.hirevehicleowneraddress);
            ownermobileNumber = itemLayoutView.findViewById(R.id.hirevehicleownermobileNumber);




            pvillagename = itemLayoutView.findViewById(R.id.peak_plotvillagename);

            lvillagename = itemLayoutView.findViewById(R.id.lean_plotvillagename);


           peakseasonvehicleprices = itemLayoutView.findViewById(R.id.peakseasonvehicleprices);
           leanseasonvehicleprices = itemLayoutView.findViewById(R.id.leanseasonvehicleprices);


            hiringbasis(hiringbasis);
            howfarmertransportfromffbtofacotry(howfarmertransportfromffbtofacotry);

        }

    }

    private void howfarmertransportfromffbtofacotry(final Spinner howfarmertransportfromffbtofacotry) {
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.getsourceofTransportTypes(APIConstantURL.sourceofTransportType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetSourceofTransportType>() {
                    @Override
                    public void onCompleted() {
                     //   mdilogue.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ((HttpException) e).code();
                            ((HttpException) e).message();
                            ((HttpException) e).response().errorBody();
                            try {
                                ((HttpException) e).response().errorBody().string();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                      //  mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetSourceofTransportType getSourceofTransportType) {

                        if (getSourceofTransportType.getListResult() != null) {
                            _howdriverTransportoFFBArray = new ArrayList<>();
                            _howdriverTransportoFFBArray.add(new TypeItem(0, "Please Select"));
                            for (GetSourceofTransportType.ListResult data : getSourceofTransportType.getListResult()
                            ) {
                                _howdriverTransportoFFBArray.add(new TypeItem(data.getTypeCdId(), data.getDesc()));
                            }


                            _howfarmertransportfromffbtofacotryAdapter = new SpinnerTypeArrayAdapter(mContext, _howdriverTransportoFFBArray);
                            _howfarmertransportfromffbtofacotryAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            howfarmertransportfromffbtofacotry.setAdapter(_howfarmertransportfromffbtofacotryAdapter);



                        }

                    }

                });
    }

    private void hiringbasis(final Spinner _hiringbasis) {

        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.gethiringbasisTypes(APIConstantURL.hiringbasis)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetHiringBasisTypes>() {
                    @Override
                    public void onCompleted() {
                    //    mdilogue.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ((HttpException) e).code();
                            ((HttpException) e).message();
                            ((HttpException) e).response().errorBody();
                            try {
                                ((HttpException) e).response().errorBody().string();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                      //  mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetHiringBasisTypes getHiringBasisTypes) {

                        if (getHiringBasisTypes.getListResult() != null) {
                            _hirebasisArr = new ArrayList<>();
                            _hirebasisArr.add(new TypeItem(0, "Please Select"));
                            for (GetHiringBasisTypes.ListResult data : getHiringBasisTypes.getListResult()
                            ) {
                                _hirebasisArr.add(new TypeItem(data.getTypeCdId(), data.getDesc()));
                            }


                            _hiringbasisAdapter = new SpinnerTypeArrayAdapter(mContext, _hirebasisArr);
                            _hiringbasisAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            _hiringbasis.setAdapter(_hiringbasisAdapter);

                        }


                    }

                });
    }



    public interface HirechargesforvehicleAdapterlister {

        void onUpdatedLeanData(int mainpo, ArrayList<Transporthirebasis> maindata);

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









