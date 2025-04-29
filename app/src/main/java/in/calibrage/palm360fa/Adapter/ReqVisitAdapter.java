package in.calibrage.palm360fa.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable;
import android.os.Build;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import in.calibrage.palm360fa.Model.RaiseRequest;
import in.calibrage.palm360fa.Model.RecomPlotcodes;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.Views.Activities.Visit_request_Activity;
import in.calibrage.palm360fa.common.AnimationUtil;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;


public class ReqVisitAdapter extends RecyclerView.Adapter<ReqVisitAdapter.ViewHolder> {

    public Context mContext;
    private List<RecomPlotcodes.ListResult> plot_Set;
    DecimalFormat dec = new DecimalFormat("######0.00");
    private Subscription mSubscription;
    double plot_area,selected_plot;
    Integer cluster_id;
    String plot_code,plot_village,plot_landmark,plot_cluster_name,date_platation,Farmer_code,statename,statecode;
    public ReqVisitAdapter(Context context, List<RecomPlotcodes.ListResult> plot_Set) {

        this.mContext = context;
        this.plot_Set = plot_Set;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recommendation_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        SharedPreferences pref = mContext.getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");
        DecimalFormat df = new DecimalFormat("#,###,##0.00");

        holder.textViewpalmArea.setText(df.format(plot_Set.get(position).getPalmArea())+" Ha ("+  dec.format(plot_Set.get(position).getPalmAreainAcres() ) + " Acre)");
        ((ViewHolder) holder).textViewplotId.setText(plot_Set.get(position).getPlotcode());
        //   ((ViewHolder) holder).textViewpalmArea.setText(plot_Set.get(position).getPalmArea() + " " + "Ha");
        ((ViewHolder) holder).textViewLocation.setText(plot_Set.get(position).getVillageName());
        ((ViewHolder) holder).textViewstatus.setText(plot_Set.get(position).getLandMark());
        holder.yop.setText(plot_Set.get(position).getDateOfPlanting() );
        holder.cluster_name.setText(plot_Set.get(position).getClusterName());
        selected_plot =plot_Set.get(position).getPalmArea();
        plot_area = plot_Set.get(position).getPalmAreainAcres();

        Log.e("selected_plot===",plot_area+"");
        ((ViewHolder) holder).card_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                plot_code= holder.textViewplotId.getText()+"";
                plot_village = holder.textViewLocation.getText()+"";
                plot_landmark = holder.textViewstatus.getText()+"";
                plot_cluster_name =holder.cluster_name.getText()+"";
                cluster_id = plot_Set.get(position).getClusterId();
                date_platation =  plot_Set.get(position).getDateOfPlanting()+"";
                statename= plot_Set.get(position).getStateName()+"";
                statecode = plot_Set.get(position).getStateCode()+"";
                visit_CanRaiseRequest();



            }

        });
        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }

        AnimationUtil.animate(holder, true);
    }

    private void visit_CanRaiseRequest() {
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.getRaiseRequest(APIConstantURL.CanRaiseRequest + Farmer_code + "/" + plot_code + "/" + 14)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RaiseRequest>() {
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

                        //   showDialog(QuickPayActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(RaiseRequest raiseRequest) {

                        if (raiseRequest.getIsSuccess()) {
                            Log.e("test=== ", raiseRequest.getIsSuccess() + "");

                            Intent intent = new Intent(mContext,  Visit_request_Activity.class);
                            intent.putExtra("plotid",plot_code);
                            intent.putExtra("plotAge", selected_plot);
                            intent.putExtra("plotarea", plot_area);
                            intent.putExtra("plotVillage", plot_village);
                            intent.putExtra("landMark", plot_landmark);
                            intent.putExtra("cluster_name",plot_cluster_name);
                            intent.putExtra("cluster_Id",cluster_id);
                            intent.putExtra("date_of_plandation", date_platation  );
                            intent.putExtra("statename",  statename);
                            intent.putExtra("statecode",  statecode);

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            mContext.startActivity(intent);
                        } else {
                            // edittext.getText().clear();Quick pay has already been requested, another quick pay request cannot be requested until the 1st one is completed.
                            showDialog(mContext, mContext.getResources().getString(R.string.visit_reqst));

                        }
                    }
                });}

    public void showDialog(Context context, String msg) {
        final Dialog dialog = new Dialog(context, R.style.DialogSlideAnim);
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

    @Override
    public int getItemCount() {

        if (plot_Set != null)
            return plot_Set.size();
        else
            return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewplotId;
        public TextView textViewpalmArea;
        public TextView textViewLocation,cluster_name;
        public TextView textViewstatus,yop;
        public CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewplotId = (TextView) itemView.findViewById(R.id.plotId);
            this.textViewpalmArea = (TextView) itemView.findViewById(R.id.palmArea);
            this.textViewLocation = (TextView) itemView.findViewById(R.id.location);
            this.textViewstatus = (TextView) itemView.findViewById(R.id.landmark);
            this.yop =(TextView) itemView.findViewById(R.id.yop);
            this.card_view = (CardView) itemView.findViewById(R.id.card_view);
this.cluster_name =(TextView)itemView.findViewById(R.id.status);
        }


    }
}
