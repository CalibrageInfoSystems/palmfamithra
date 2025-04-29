package in.calibrage.palm360fa.Adapter;

import android.app.Dialog;
import android.content.Context;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import in.calibrage.palm360fa.Model.DeleteObject;
import in.calibrage.palm360fa.Model.ResPole;
import in.calibrage.palm360fa.Model.Resdelete;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.AnimationUtil;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;




public class GetPoleAdapter extends RecyclerView.Adapter<GetPoleAdapter.ViewHolder> {

    List<ResPole.ListResult> list;
    public Context mContext;
    private Animation animationUp, animationDown;
    private final int COUNTDOWN_RUNNING_TIME = 500;
    private GetPoleAdapterListener1 listener;
    public CardView card_view;
    String datetimevaluereq,currentDate;
    DecimalFormat df = new DecimalFormat("####0.00");
    String selectedItemID;
    int selectedPO;
    private Subscription mSubscription;
    Button cancel_btn,ok_btn;

    public GetPoleAdapter(List<ResPole.ListResult> list, Context ctx, GetPoleAdapterListener1 listener) {
        this.mContext = ctx;
        this.listener = listener;
        this.list = list;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {


        public LinearLayout contentLayout;
        public TextView requestCode;
        public TextView req_date;
        public TextView statusType;
        public TextView paymentMode, amount;
        public ImageView showMore;
        public TextView cancel,godown_name;
        LinearLayout details;
        public CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);

            showMore = (ImageView)itemView.findViewById(R.id.show_more);
            contentLayout = (LinearLayout)itemView.findViewById(R.id.linear1);
            requestCode = itemView.findViewById(R.id.requestCode);
            req_date = itemView.findViewById(R.id.reqCreatedDate);
            statusType = itemView.findViewById(R.id.statusType);
            paymentMode = itemView.findViewById(R.id.paymentMode);
            card_view =   itemView.findViewById(R.id.card_view);
            amount=itemView.findViewById(R.id.amount);
            cancel = itemView.findViewById(R.id.cancel);
            godown_name=itemView.findViewById(R.id.godown_name);
            details = itemView.findViewById(R.id.details);
            card_view = itemView.findViewById(R.id.card_view);


        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.pole_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date oneWayTripDate = input.parse(list.get(position).getReqCreatedDate());

            datetimevaluereq = output.format(oneWayTripDate);


            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.requestCode.setText(list.get(position).getRequestCode());
        holder.godown_name.setText(list.get(position).getGoDownName());
        holder.req_date.setText(datetimevaluereq);
        holder.statusType.setText(list.get(position).getStatus());
        holder.paymentMode.setText(list.get(position).getPaymentMode());
        if(null !=list.get(position).getPaubleAmount() )
        {
            holder.amount.setText(df.format(list.get(position).getPaubleAmount()));
        }

        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        if (!"Closed".equals(holder.statusType.getText()))
//        {
//            holder.cancel.setVisibility(View.VISIBLE);
//
//        }
//        else {
//            holder.cancel.setVisibility(View.GONE);
//        }
//        if (!"Closed".equals(holder.statusType.getText()) && !"Cancelled".equals(holder.statusType.getText())) {
//            holder.cancel.setVisibility(View.VISIBLE);
//
//        } else {
//            holder.cancel.setVisibility(View.GONE);
//        }
//
//        holder.cancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                selectedItemID = list.get(position).getRequestCode();
//                selectedPO = position;
//                showConformationDialog(selectedPO);
//
//            }
//
//        });

        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
            holder.details.setBackgroundColor(mContext.getColor(R.color.white2));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));
            holder.details.setBackgroundColor(mContext.getColor(R.color.light_gray2));

        }
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onContactSelected1(list.get(position).getRequestCode().toString());

            }
        });
        AnimationUtil.animate(holder, true);
    }

    private void showConformationDialog(final int selectedPO) {
        TextView dialogMessage;
        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_cancel);
        dialogMessage = dialog.findViewById(R.id.dialogMessage);
        dialogMessage.setText(mContext.getString(R.string.alert_msg));
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        ok_btn = dialog.findViewById(R.id.ok_btn);
/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    delete_request();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
            }
        });

/**
 * @param OnClickListner
 */
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void delete_request()  throws JSONException {

        JsonObject object = Requestobject();
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.postdelete(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Resdelete>() {
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

                    }

                    @Override
                    public void onNext(Resdelete resdelete) {


                        ResPole.ListResult item =list.get(selectedPO);
                        item.setStatus("Cancelled");
                        list.set(selectedPO,item);
                        Toast.makeText(mContext, mContext.getString(R.string.cancel_success), Toast.LENGTH_LONG).show();
                        notifyItemChanged(selectedPO);


                    }



                });
    }

    private JsonObject Requestobject() {
        DeleteObject requestModel = new DeleteObject();
        requestModel.setRequestCode(selectedItemID);
        requestModel.setStatusTypeId(32);
        requestModel.setUpdatedByUserId(null);
        requestModel.setUpdatedDate(currentDate);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();


    }

    @Override
    public int getItemCount() {
        if (null != list)
            return list.size();
        else
            return 0;
    }


    public interface GetPoleAdapterListener1 {
        void onContactSelected1(String products);
    }

}


