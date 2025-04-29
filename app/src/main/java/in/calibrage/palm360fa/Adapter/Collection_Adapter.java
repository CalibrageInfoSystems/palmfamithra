package in.calibrage.palm360fa.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;

import in.calibrage.palm360fa.Model.CollectionResponceModel;
import in.calibrage.palm360fa.Model.GetCollectionInfoById;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.AnimationUtil;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class Collection_Adapter extends RecyclerView.Adapter<Collection_Adapter.ViewHolder> {
    String datetimevaluereq,info_date;
    public Context mContext;
    int pos;
    LinearLayout img_profile;
    TextView driverName,vehicleNumber,collectionCenter,grossWeight,tareWeight,totalBunches,acceptedBunches,rejectedBunches,operatorname,net_weight,collectionid,date,comments;
    Button cancel_btn,ok_btn;
    String collection_id;
    RelativeLayout comments_layout;
    private SpotsDialog mdilogue;
    private Subscription mSubscription;
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
    private List<CollectionResponceModel.CollectioDatum> collection_Set = new ArrayList<>();
    LayoutInflater mInflater;
    public Collection_Adapter(Context mContext) {
        this.mContext = mContext;
    }

    public void updateData(List<CollectionResponceModel.CollectioDatum> viewModels) {
        collection_Set.clear();
        collection_Set.addAll(viewModels);
        notifyDataSetChanged();
    }
    public void clearAllDataa() {
        collection_Set.clear();
        notifyDataSetChanged();
    }
    public Collection_Adapter(Context context, List<CollectionResponceModel.CollectioDatum> collection_Set) {

        this.mContext = context;
        this.collection_Set = collection_Set;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.collection_data_xml, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        ((ViewHolder) holder).txtCollectionId.setText(collection_Set.get(position).getUColnid());



        try {
            Date oneWayTripDate = input.parse(collection_Set.get(position).getDocDate());

            datetimevaluereq = output.format(oneWayTripDate);


            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((ViewHolder) holder).txtDate.setText(" : "+datetimevaluereq);
        ((ViewHolder) holder).txtWeight.setText(" : " + collection_Set.get(position).getQuantity() + "" + "0 Kg");
        ((ViewHolder) holder).txtCc.setText(" : "+collection_Set.get(position).getWhsName());
        ((ViewHolder) holder).txtStatus.setText(collection_Set.get(position).getUApaystat().toString().replace("Payment", ""));
        if (collection_Set.get(position).getUApaystat().toString().endsWith("Paid"))
            ((ViewHolder) holder).txtStatus.setTextColor(mContext.getColor(R.color.green));
        else
            ((ViewHolder) holder).txtStatus.setTextColor(mContext.getColor(R.color.red));


        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }


        holder.info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                collection_id = holder.txtCollectionId.getText().toString();
                pos = position;
                showConformationDialog(pos);


            }

        });
        holder.card_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                collection_id = holder.txtCollectionId.getText().toString();
                pos = position;
                showConformationDialog(pos);


            }

        });
        AnimationUtil.animate(holder, true);
    }

    private void showConformationDialog(final int pos) {
        CollectionInfoById();

        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_info);
       // grossWeight,tareWeight,totalBunches,acceptedBunches,rejectedBunches,operatorname;
        driverName=dialog.findViewById(R.id.driverName);
        vehicleNumber=dialog.findViewById(R.id.vehicle_number);
        collectionCenter=dialog.findViewById(R.id.collection_center);
        img_profile=dialog.findViewById(R.id.lin_rept);
       grossWeight = dialog.findViewById(R.id.gross_weight);
        tareWeight = dialog.findViewById(R.id.tare_weight);
//        totalBunches = dialog.findViewById(R.id.total_Bunches);
//        acceptedBunches = dialog.findViewById(R.id.accepted_Bunches);
//        rejectedBunches = dialog.findViewById(R.id.rejected_Bunches);
        operatorname = dialog.findViewById(R.id.operator_Name);
        net_weight= dialog.findViewById(R.id.net_weight);
        collectionid= dialog.findViewById(R.id.collection_id);
        date= dialog.findViewById(R.id.date_id);
        comments= dialog.findViewById(R.id.comtstext);
        comments_layout = dialog.findViewById(R.id.lin_comments);
       ok_btn = dialog.findViewById(R.id.btn_dialog);


/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void CollectionInfoById() {
      //  mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.getinfo(APIConstantURL.CollectionInfoById +collection_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCollectionInfoById>() {
                    @Override
                    public void onCompleted() {

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
                       // mdilogue.dismiss();
                        //showDialog(LabourActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final GetCollectionInfoById getCollectionInfoById) {

                        if (getCollectionInfoById.getResult() != null) {


                            driverName.setText(getCollectionInfoById.getResult().getDriverName());
                            vehicleNumber.setText(getCollectionInfoById.getResult().getVehicleNumber());
                            collectionCenter.setText(getCollectionInfoById.getResult().getCollectionCenter());
                            operatorname.setText(getCollectionInfoById.getResult().getOperatorName());
//                            totalBunches.setText(getCollectionInfoById.getResult().getTotalBunches()+"");
//                            acceptedBunches.setText(getCollectionInfoById.getResult().getAcceptedBunches()+"");
//                            rejectedBunches.setText(getCollectionInfoById.getResult().getRejectedBunches()+"");

                            grossWeight.setText(getCollectionInfoById.getResult().getGrossWeight()+"0 Kg");
                            tareWeight.setText(getCollectionInfoById.getResult().getTareWeight()+"0 Kg");
                            net_weight.setText(getCollectionInfoById.getResult().getNetWeight()+"0 Kg");
                            try {
                                Date oneWayTripDate = input.parse(getCollectionInfoById.getResult().getReceiptGeneratedDate());

                                info_date = output.format(oneWayTripDate);


                                Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            date.setText(info_date);
                            collectionid.setText(getCollectionInfoById.getResult().getCode());
                            if (getCollectionInfoById.getResult().getComments() != null && !getCollectionInfoById.getResult().getComments().isEmpty()){
                                comments_layout.setVisibility(View.VISIBLE);
                              //  comments.setText(getCollectionInfoById.getResult().getComments());
                            }else {
                                comments_layout.setVisibility(View.GONE);

                            }
                        }
                        img_profile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Context context=mContext.getApplicationContext();
                                mInflater = LayoutInflater.from(context);
                                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                                View mView =mInflater.inflate(R.layout.dialog_custom_layout, null);
                              //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                                PhotoView photoView = mView.findViewById(R.id.imageView);
                                TextView cancel =mView.findViewById(R.id.cancel);
                                if(getCollectionInfoById.getResult().getReceiptImg()!=null)
                                    Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).placeholder( R.drawable.progress_animation).into(photoView);
                              //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                                else
                                    Picasso.with(mContext).load(R.drawable.logo).error(R.drawable.ic_user).placeholder( R.drawable.progress_animation).into(photoView);
                                //photoView.setImageResource(Integer.parseInt(getCollectionInfoById.getResult().getReceiptImg()));
                                mBuilder.setView(mView);


                                final AlertDialog mDialog = mBuilder.create();
                                cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mDialog.dismiss();
                                    }
                                });
                                mDialog.show();
                            }
                        });

                    }


                });
    }


    @Override
    public int getItemCount() {
        return collection_Set.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtCollectionId;
        public TextView txtDate;
        public TextView txtWeight;
        public TextView txtCc;
        public TextView txtStatus,info;
        protected CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);
            txtCollectionId = itemView.findViewById(R.id.collection_id);
            txtDate = itemView.findViewById(R.id.date);
            txtWeight = itemView.findViewById(R.id.weight);
            txtCc = itemView.findViewById(R.id.cc);
            txtStatus = itemView.findViewById(R.id.status);
            info = itemView.findViewById(R.id.info);
            card_view = itemView.findViewById(R.id.card_view);
//
        }


    }

    public void clear() {
        try {
            int size = collection_Set.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    collection_Set.remove(0);
                }

                notifyItemRangeRemoved(0, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
