package in.calibrage.palm360fa.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Model.DeleteObject;
import in.calibrage.palm360fa.Model.GetVisitRequestRepository;
import in.calibrage.palm360fa.Model.Getvisit;
import in.calibrage.palm360fa.Model.Resdelete;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetvisitAdapter extends RecyclerView.Adapter<GetvisitAdapter.ViewHolder> {

    List<Getvisit.ListResult> list_visit;
    public Context mContext;
    String datetimevaluereq, currentDate;
    String selectedItemID;
    int selectedPO;
    Button ok_btn;
    String AudioURL, imageur1;
    LinearLayout linearLayout_Play;
    //	private Chronometer chronometer;
    private List<String> image_list = new ArrayList<String>();
    ImageView iv1, iv2, iv3, imageViewPlay;
    LinearLayout voice_layout;
    SeekBar seekBar;
    private MediaPlayer mPlayer;
    private int lastProgress = 0;
    private Handler mHandler = new Handler();
    private int RECORD_AUDIO_REQUEST_CODE = 123;
    private boolean isPlaying = false;
    DecimalFormat df = new DecimalFormat("####0.00");
    private Subscription mSubscription;
    LayoutInflater mInflater;
    private SpotsDialog mdilogue;
    // RecyclerView recyclerView;
    public GetvisitAdapter(List<Getvisit.ListResult> list_loan, Context ctx) {
        this.list_visit = list_loan;
        this.mContext = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.visit_req_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date oneWayTripDate = input.parse(list_visit.get(position).getReqCreatedDate());

            datetimevaluereq = output.format(oneWayTripDate);


            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(mContext)
                .setTheme(R.style.Custom)
                .build();
        holder.requestCode.setText(list_visit.get(position).getRequestCode());
        holder.req_date.setText(datetimevaluereq);
        holder.PlotId.setText(list_visit.get(position).getPlotCode());
        if(list_visit.get(position).getPalmArea()!=null) {
            holder.plot_size.setText(df.format(list_visit.get(position).getPalmArea()) + " Ha");
        }
        holder.location.setText(list_visit.get(position).getPlotVillage());
        holder.statusType.setText(list_visit.get(position).getStatusType());
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
//                selectedItemID = list_visit.get(position).getRequestCode();
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
                selectedItemID = list_visit.get(position).getRequestCode();
                selectedPO = position;
                showCondetailsDialog(selectedPO);


            }

        });
    }

    private void showCondetailsDialog(int selectedPO) {
        GetVisitRequestRepository();
        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_images);
        imageViewPlay = (ImageView) dialog.findViewById(R.id.imageViewPlay);
        seekBar = (SeekBar) dialog.findViewById(R.id.seekBar);
        ok_btn = (Button) dialog.findViewById(R.id.buttonOk);
        iv1 = (ImageView) dialog.findViewById(R.id.iv);
        iv2 = (ImageView) dialog.findViewById(R.id.iv2);
        iv3 = (ImageView) dialog.findViewById(R.id.iv3);
        //linearLayout_Play=(LinearLayout)dialog.findViewById(R.id.linearLayout_Play)
        voice_layout = dialog.findViewById(R.id.linearLayout_Play);
        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.GONE);
        iv3.setVisibility(View.GONE);

//        String imageUrl = "https://via.placeholder.com/500";
//        Picasso.with(mContext).load(imageUrl).error(R.drawable.ic_user).into(iv1);
        //Loading image using Picasso
        // Picasso.get().load(imageUrl).into(iv1);
//
//

//        }
//

        imageViewPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!isPlaying && AudioURL != null) {
                    isPlaying = true;
                    if (mPlayer != null) {
                        lastProgress = 0;
                        //chronometer.setBase(SystemClock.elapsedRealtime());
                        startPlaying();
                    } else {
                        startPlaying();
                    }

                } else {
                    isPlaying = false;
                    stopPlaying();
                }

            }
        });
        //  ok_btn = dialog.findViewById(R.id.btn_dialog);


/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog.dismiss();
                try {
                    mPlayer.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mPlayer = null;
            }
        });
        dialog.show();

    }

    private void GetVisitRequestRepository() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.getimages(APIConstantURL.GetVisitRequestRepository + selectedItemID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetVisitRequestRepository>() {
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
                        mdilogue.dismiss();
                        //showDialog(LabourActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetVisitRequestRepository getVisitRequestRepository) {
                        mdilogue.dismiss();
                        final List<String> img = new ArrayList<>();
                        if (getVisitRequestRepository.getListResult() != null) {

                            for (int i = 0; i < getVisitRequestRepository.getListResult().size(); i++) {
                                if (getVisitRequestRepository.getListResult().get(i).getFileTypeId() == 37) {
                                    voice_layout.setVisibility(View.VISIBLE);
                                    AudioURL = getVisitRequestRepository.getListResult().get(i).getFileLocation();
                                } else {
                                    voice_layout.setVisibility(View.GONE);
                                    img.add(getVisitRequestRepository.getListResult().get(i).getFileLocation());
                                    Log.d("GETVisit", getVisitRequestRepository.getListResult().get(i).getFileLocation());
//
                                }

                                for (int j = 0; j < img.size(); j++) {
                                  final   int finalJ =j;
                                    if (j == 0) {
                                        iv1.setVisibility(View.VISIBLE);
                                        Picasso.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv1);


                                    iv1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Context context = mContext.getApplicationContext();
                                            mInflater = LayoutInflater.from(context);
                                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                                            View mView = mInflater.inflate(R.layout.dialog_custom_layout, null);
                                            TextView cancel =mView.findViewById(R.id.cancel);
                                            //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                                            PhotoView photoView = mView.findViewById(R.id.imageView);

                                            Picasso.with(mContext).load(img.get(finalJ)).placeholder(R.drawable.progress_animation).error(R.drawable.ic_user).into(photoView);
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
                                    if (j == 1) {
                                        iv2.setVisibility(View.VISIBLE);
                                        Picasso.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv2);


                                        iv2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Context context = mContext.getApplicationContext();
                                            mInflater = LayoutInflater.from(context);
                                           AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                                            View mView = mInflater.inflate(R.layout.dialog_custom_layout, null);
                                            TextView cancel =mView.findViewById(R.id.cancel);
                                            //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                                            PhotoView photoView = mView.findViewById(R.id.imageView);
                                            Picasso.with(mContext).load(img.get(finalJ)).placeholder(R.drawable.progress_animation).error(R.drawable.ic_user).into(photoView);
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
                                    if (j == 2) {
                                        iv3.setVisibility(View.VISIBLE);
                                        Picasso.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv3);

                                        iv3.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Context context = mContext.getApplicationContext();
                                                mInflater = LayoutInflater.from(context);
                                                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                                                View mView = mInflater.inflate(R.layout.dialog_custom_layout, null);
                                                TextView cancel =mView.findViewById(R.id.cancel);
                                                //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                                                PhotoView photoView = mView.findViewById(R.id.imageView);
                                                Picasso.with(mContext).load(img.get(finalJ)).placeholder(R.drawable.progress_animation).error(R.drawable.ic_user).into(photoView);

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
                                }


                            }

                        }

                    }


                });
    }


    private void stopPlaying() {
        try {
            mPlayer.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mPlayer = null;
        //showing the play button
        imageViewPlay.setImageResource(R.drawable.ic_play);
        //chronometer.stop();

    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(AudioURL);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }
        //making the imageview pause button
        imageViewPlay.setImageResource(R.drawable.ic_pause);
        seekBar.setVisibility(View.VISIBLE);
        seekBar.setProgress(lastProgress);
        mPlayer.seekTo(lastProgress);
        seekBar.setMax(mPlayer.getDuration());
        seekUpdation();
        //chronometer.start();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                imageViewPlay.setImageResource(R.drawable.ic_play);
                seekBar.setVisibility(View.GONE);
                isPlaying = false;
                //	chronometer.stop();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mPlayer != null && fromUser) {
                    mPlayer.seekTo(progress);
                    //	chronometer.setBase(SystemClock.elapsedRealtime() - mPlayer.getCurrentPosition());
                    lastProgress = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
        }
    };

    private void seekUpdation() {
        if (mPlayer != null) {
            int mCurrentPosition = mPlayer.getCurrentPosition();
            seekBar.setProgress(mCurrentPosition);
            lastProgress = mCurrentPosition;
        }
        mHandler.postDelayed(runnable, 100);
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

                        Getvisit.ListResult item = list_visit.get(selectedPO);
                        item.setStatusType("Cancelled");
                        list_visit.set(selectedPO, item);
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

        if (list_visit != null)
            return list_visit.size();
        else
            return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView requestCode;
        public TextView req_date, amount;
        public TextView statusType, cancel;
        public TextView PlotId, plot_size, location;
        LinearLayout details;

        public ViewHolder(View itemView) {
            super(itemView);


            requestCode = itemView.findViewById(R.id.requestCode);
            PlotId = itemView.findViewById(R.id.plotId);
            plot_size = itemView.findViewById(R.id.plot_size);
            location = itemView.findViewById(R.id.village_name);
            req_date = itemView.findViewById(R.id.reqCreatedDate);
            statusType = itemView.findViewById(R.id.statusType);

            cancel = itemView.findViewById(R.id.cancel);
            details = itemView.findViewById(R.id.details);


        }


    }
}


