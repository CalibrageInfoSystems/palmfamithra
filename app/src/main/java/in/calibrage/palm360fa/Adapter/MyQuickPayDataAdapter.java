package in.calibrage.palm360fa.Adapter;


import android.app.Dialog;
import android.content.Context;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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


import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Model.DeleteObject;
import in.calibrage.palm360fa.Model.Resdelete;
import in.calibrage.palm360fa.Model.Resquickpay;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;




public class MyQuickPayDataAdapter extends RecyclerView.Adapter<MyQuickPayDataAdapter.ViewHolder> {
    List<Resquickpay.ListResult> list;
    public Context mContext;
    String datetimevaluereq, currentDate;
    // RecyclerView recyclerView;
    String selectedItemID , pdf_url;
    int selectedPO;
    private SpotsDialog mdilogue;
    private Subscription mSubscription;
    DecimalFormat df = new DecimalFormat("####0.00");

    public MyQuickPayDataAdapter(List<Resquickpay.ListResult> list, Context ctx) {
        this.list = list;
        this.mContext = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.quick_req_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date oneWayTripDate = input.parse(list.get(position).getReqCreatedDate());

            datetimevaluereq = output.format(oneWayTripDate);


            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(mContext)
                .setTheme(R.style.Custom)
                .build();
        holder.requestCode.setText(list.get(position).getRequestCode());
        holder.req_date.setText(datetimevaluereq);
        holder.statusType.setText(list.get(position).getStatusType());
        holder.amount.setText(df.format(list.get(position).getTotalCost()));
        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        if (!"Closed".equals(holder.statusType.getText())) {
//            holder.cancel.setVisibility(View.VISIBLE);
//
//        } else {
//            holder.cancel.setVisibility(View.GONE);
//        }
//        if (!"Cancelled".equals(holder.statusType.getText())) {
//            holder.cancel.setVisibility(View.VISIBLE);
//
//        } else {
//            holder.cancel.setVisibility(View.GONE);
//        }
//        holder.cancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                selectedItemID = list.get(position).getRequestCode();
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

        holder.details.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                selectedItemID = list.get(position).getRequestCode();
                pdf_url =list.get(position).getFileUrl();
                Log.e("url===",pdf_url);
                selectedPO = position;
                showCondetailsDialog(selectedPO,pdf_url);


            }

        });
    }

    private void showCondetailsDialog(int selectedPO,String pdf_url) {

        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.pdf_dialog);
        final WebView webview = dialog.findViewById(R.id.webView);
        Button btn_dialog = dialog.findViewById(R.id.btn_dialog);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);




//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return false;
//            }
//        });
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                webview.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
                mdilogue.show();
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webview.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
                mdilogue.dismiss();
            }
        });
        webview.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf_url);
    //    webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url="  + pdf_url);
//        mdilogue.show();
//        final Dialog dialog = new Dialog(mContext);
////        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.pdf_dialog);
//        WebView webView = dialog.findViewById(R.id.webView);
//        Button btn_dialog = dialog.findViewById(R.id.btn_dialog);
//
//      webView.getSettings().setPluginState(WebSettings.PluginState.ON);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setUseWideViewPort(true);
//
//        //---you need this to prevent the webview from
//        // launching another browser when a url
//        // redirection occurs---
//        webView.setWebViewClient(new Callback());
//        Log.e("url===158",pdf_url);
//
//        webView.loadUrl(
//                "http://docs.google.com/gview?embedded=true&url=" + pdf_url);
//        mdilogue.dismiss();
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();

    }


    private void delete_request() throws JSONException {

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
                        Resquickpay.ListResult item =list.get(selectedPO);
                        item.setStatusType("Cancelled");
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
        if (list != null)
            return list.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView requestCode;
        public TextView req_date;
        public TextView statusType;
        public TextView amount, cancel;

        LinearLayout details;
        private SpotsDialog mdilogue;
        public ViewHolder(View itemView) {
            super(itemView);


            requestCode = itemView.findViewById(R.id.requestCode);
            req_date = itemView.findViewById(R.id.reqCreatedDate);
            statusType = itemView.findViewById(R.id.statusType);
            amount = itemView.findViewById(R.id.amount);
            cancel = itemView.findViewById(R.id.cancel);
            details =itemView.findViewById(R.id.details);



        }


    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return (false);
        }
    }
}

