package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Model.GetHiringBasisTypes;
import in.calibrage.palm360fa.Model.GetSourceofTransportType;
import in.calibrage.palm360fa.Model.Request_settings;
import in.calibrage.palm360fa.Model.VehicleTypeResponse;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DonthavevehicleAdapter extends RecyclerView.Adapter<DonthavevehicleAdapter.MyViewHolder> {

    private Context context;
    ArrayList<Vehicle> vehicle_arrayList = new ArrayList<Vehicle>();
    ArrayList<VehicleTypeResponse> vehicles_arrayList = new ArrayList<VehicleTypeResponse>();

    private RequestAdapterListener listener;

    private SpotsDialog mdilogue;
    private Subscription mSubscription;

    RecyclerView peakseasonvehicleprices, leanseasonvehicleprices;

    List<String> get_sourceTransportType = new ArrayList<String>();
    List<Integer> get_sourceTransportType_Id = new ArrayList<Integer>();

    List<String>get_hiringbasisType = new ArrayList<String>();
    List<Integer> get_hiringbasisType_Id = new ArrayList<Integer>();

    Spinner howfarmertransportfromffbtofacotry,hiringbasis;
    String selectedhowfarmertransportfromffbtofacotry, selectedhiringbasis;

    int sourceTransportTypeid, hiringbasisid;
    private LinearLayoutManager linearLayoutManager;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        EditText pvillagename, p2wdestination1, p2wdestination2, p4wdestination1, p4wdestination2, pvandestination1, pvandestination2, pautodestination1, pautodestination2, pbullockdestination1,
                pbullockdestination2, pothersdestination1, pothersdestination2;

        EditText lvillagename, l2wdestination1, l2wdestination2, l4wdestination1, l4wdestination2, lvandestination1, lvandestination2, lautodestination1, lautodestination2, lbullockdestination1,
                lbullockdestination2, lothersdestination1, lothersdestination2;

        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);


            mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                    .setContext(context)
                    .setTheme(R.style.Custom)
                    .build();

            howfarmertransportfromffbtofacotry = view.findViewById(R.id.farmertransport);
          //  hiringbasis = view.findViewById(R.id.hiringbasis);


            pvillagename = view.findViewById(R.id.peak_plotvillagename);
//            p2wdestination1 = view.findViewById(R.id.twowheeldestination1_et);
//            p2wdestination2 = view.findViewById(R.id.twowheeldestination2_et);
//            p4wdestination1 = view.findViewById(R.id.fourwheeldestination1_et);
//            p4wdestination2 = view.findViewById(R.id.fourwheeldestination2_et);
//            pvandestination1 = view.findViewById(R.id.pickupVandestination1_et);
//            pvandestination2 = view.findViewById(R.id.pickupVandestination2_et);
//            pautodestination1 = view.findViewById(R.id.autodestination1_et);
//            pautodestination2 = view.findViewById(R.id.autodestination2_et);
//            pbullockdestination1 = view.findViewById(R.id.bullockdestination1_et);
//            pbullockdestination2 = view.findViewById(R.id.bullockdestination2_et);
//            pothersdestination1 = view.findViewById(R.id.otherdestination1_et);
//            pothersdestination2 = view.findViewById(R.id.otherdestination1_et);

            lvillagename = view.findViewById(R.id.lean_plotvillagename);
//            l2wdestination1 = view.findViewById(R.id.leantwowheeldestination1_et);
//            l2wdestination2 = view.findViewById(R.id.leantwowheeldestination2_et);
//            l4wdestination1 = view.findViewById(R.id.leanfourwheeldestination1_et);
//            l4wdestination2 = view.findViewById(R.id.leanfourwheeldestination2_et);
//            lvandestination1 = view.findViewById(R.id.leanpickupVandestination1_et);
//            lvandestination2 = view.findViewById(R.id.leanpickupVandestination2_et);
//            lautodestination1 = view.findViewById(R.id.leanautodestination1_et);
//            lautodestination2 = view.findViewById(R.id.leanautodestination2_et);
//            lbullockdestination1 = view.findViewById(R.id.leanbullockdestination1_et);
//            lbullockdestination2 = view.findViewById(R.id.leanbullockdestination2_et);
//            lothersdestination1 = view.findViewById(R.id.leanotherdestination1_et);
//            lothersdestination2 = view.findViewById(R.id.leanotherdestination2_et);

            howfarmertransportfromffbtofacotry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedhowfarmertransportfromffbtofacotry = howfarmertransportfromffbtofacotry.getItemAtPosition(howfarmertransportfromffbtofacotry.getSelectedItemPosition()).toString();
                    if (i != 0) {

                        sourceTransportTypeid = get_sourceTransportType_Id.get(howfarmertransportfromffbtofacotry.getSelectedItemPosition() - 1);
                    }
                    howfarmertransportfromffbtofacotry.setPrompt(selectedhowfarmertransportfromffbtofacotry);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    // DO Nothing here
                }
            });

            hiringbasis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedhiringbasis = hiringbasis.getItemAtPosition(hiringbasis.getSelectedItemPosition()).toString();
                    if (i != 0) {

                        hiringbasisid = get_hiringbasisType_Id.get(hiringbasis.getSelectedItemPosition() - 1);
                    }
                    hiringbasis.setPrompt(selectedhiringbasis);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    // DO Nothing here
                }
            });

            peakseasonvehicleprices = view.findViewById(R.id.peakseasonvehicleprices);
            linearLayoutManager = new LinearLayoutManager(context);
            peakseasonvehicleprices.setLayoutManager(linearLayoutManager);

            leanseasonvehicleprices = view.findViewById(R.id.leanseasonvehicleprices);
            linearLayoutManager = new LinearLayoutManager(context);
            leanseasonvehicleprices.setLayoutManager(linearLayoutManager);

            hiringbasis();
            howfarmertransportfromffbtofacotry();

        }
    }

    public DonthavevehicleAdapter(Context context, ArrayList<Vehicle> vehicle_arrayList) {
        this.context = context;

        this.vehicle_arrayList = vehicle_arrayList;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.donthavevehicleadapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return vehicle_arrayList.size();
    }

    public interface RequestAdapterListener {
        void onContactSelected(Request_settings request);
    }

    private void howfarmertransportfromffbtofacotry() {
        ApiService service = ServiceFactory.createRetrofitService(context, ApiService.class);
        mSubscription = service.getsourceofTransportTypes(APIConstantURL.sourceofTransportType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetSourceofTransportType>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();
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
                        mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetSourceofTransportType getSourceofTransportType) {

                        if (getSourceofTransportType.getListResult() != null) {
                            get_sourceTransportType.add("Select Transportation Type");
                            for (GetSourceofTransportType.ListResult data : getSourceofTransportType.getListResult()
                            ) {
                                get_sourceTransportType.add(data.getDesc());
                                get_sourceTransportType_Id.add(data.getTypeCdId());
                            }


                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, R.layout.spinneritem, get_sourceTransportType);
                            dataAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            howfarmertransportfromffbtofacotry.setAdapter(dataAdapter);

                        } else {
                            get_sourceTransportType.add("No Transportation Type Available");
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }

                });
    }

    private void hiringbasis() {

        ApiService service = ServiceFactory.createRetrofitService(context, ApiService.class);
        mSubscription = service.gethiringbasisTypes(APIConstantURL.hiringbasis)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetHiringBasisTypes>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();
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
                        mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetHiringBasisTypes getHiringBasisTypes) {

                        if (getHiringBasisTypes.getListResult() != null) {
                            get_hiringbasisType.add("Select Hiring Basis Type");
                            for (GetHiringBasisTypes.ListResult data : getHiringBasisTypes.getListResult()
                            ) {
                                get_hiringbasisType.add(data.getDesc());
                                get_hiringbasisType_Id.add(data.getTypeCdId());
                            }


                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, R.layout.spinneritem, get_hiringbasisType);
                            dataAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                            hiringbasis.setAdapter(dataAdapter);

                        } else {
                            get_hiringbasisType.add("No Hiring Basis Type Available");
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }

                });
    }
}
