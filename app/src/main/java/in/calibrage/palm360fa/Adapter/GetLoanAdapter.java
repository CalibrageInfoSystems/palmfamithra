package in.calibrage.palm360fa.Adapter;


import android.app.Dialog;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import in.calibrage.palm360fa.Model.ResLoan;
import in.calibrage.palm360fa.Model.Resdelete;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class GetLoanAdapter extends RecyclerView.Adapter<GetLoanAdapter.ViewHolder>{

    List<ResLoan.ListResult> list_loan;
    public Context mContext;
    String datetimevaluereq,currentDate;
    String selectedItemID;
    int selectedPO;
    Button ok_btn;
    TextView comments;
    LinearLayout lin_comments;
    TextView no_comments;
    DecimalFormat df = new DecimalFormat("####0.00");
    private Subscription mSubscription;
    // RecyclerView recyclerView;
    public GetLoanAdapter(  List<ResLoan.ListResult> list_loan, Context ctx) {
        this.list_loan = list_loan;
        this.mContext=ctx;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.loan_req_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date oneWayTripDate = input.parse(list_loan.get(position).getReqCreatedDate());

            datetimevaluereq = output.format(oneWayTripDate);


            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.requestCode.setText(list_loan.get(position).getRequestCode());
        holder.req_date.setText(datetimevaluereq);
        holder.amount.setText(df.format(list_loan.get(position).getTotalCost()));
        holder.statusType.setText(list_loan.get(position).getStatusType());
        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        if (!"Closed".equals(holder.statusType.getText()))
//        {
//            holder.cancel.setVisibility(View.VISIBLE);
//
//        }
//        else {
//            holder.cancel.setVisibility(View.GONE);
//        }
//        if (!"Cancelled".equals(holder.statusType.getText())) {
//            holder.cancel.setVisibility(View.VISIBLE);
//
//        } else {
//            holder.cancel.setVisibility(View.GONE);
//        }

        holder.details.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                selectedItemID = list_loan.get(position).getRequestCode();

                selectedPO = position;
                showCondetailsDialog(selectedPO);


            }

        });
//        holder.cancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                selectedItemID = list_loan.get(position).getRequestCode();
//                selectedPO = position;
//                try {
//                    delete_request();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        });


    }


    private void showCondetailsDialog(int selectedPO) {

        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_loan);
        comments=dialog.findViewById(R.id.comments_text);
        no_comments=dialog.findViewById(R.id.no_comments);
        lin_comments=dialog.findViewById(R.id.lin_comments);
        ok_btn=dialog.findViewById(R.id.buttonOk);
        if(list_loan.get(selectedPO).getComments()!= null && !list_loan.get(selectedPO).getComments().isEmpty()) {
            comments.setText(list_loan.get(selectedPO).getComments());
            no_comments.setVisibility(View.GONE);
        }
        else {
            lin_comments.setVisibility(View.GONE                               );
            no_comments.setVisibility(View.VISIBLE);
        }
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

                        ResLoan.ListResult item =list_loan.get(selectedPO);
                        item.setStatusType("Cancelled");
                        list_loan.set(selectedPO,item);
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

        if (list_loan != null)
            return list_loan.size();
        else
            return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {



        public TextView requestCode;
        public TextView req_date,amount;
        public TextView statusType,cancel;

        LinearLayout details;

        public ViewHolder(View itemView) {
            super(itemView);



            requestCode = itemView.findViewById(R.id.requestCode);
            req_date = itemView.findViewById(R.id.reqCreatedDate);
            statusType = itemView.findViewById(R.id.statusType);
            amount = itemView.findViewById(R.id.amount);
            details =itemView.findViewById(R.id.details);
            cancel = itemView.findViewById(R.id.cancel);



        }


    }
}


