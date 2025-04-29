package in.calibrage.palm360fa.Views.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;

import android.text.TextUtils;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import dmax.dialog.SpotsDialog;

import in.calibrage.palm360fa.Model.GetIssueModel;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.MSGmodel;
import in.calibrage.palm360fa.Model.VisitRequestModel;
import in.calibrage.palm360fa.Model.VisitresponseModel;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.CommonUtil;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class Visit_request_Activity extends BaseActivity implements View.OnClickListener {
    String location, landmarkCode, plot_id, Farmer_code,date_of_plandation;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    ImageView backImg, home_btn;
    private TextView Age, id_plot, area, landMark,yop,cluster_name;
    List<String> Issue_type = new ArrayList<String>();
    private static final String TAG = Visit_request_Activity.class.getSimpleName();
    List<Integer> Issue_Id = new ArrayList<Integer>();
    Spinner Select_Issue;
    String selected_issue;
    ImageButton btn_addIMG;
    private Button btn;
    private ImageView imageview, imageview2, imageview3;
    private ImageView img_delete1, img_delete2, img_delete3;
    private RelativeLayout lyt_img, lyt_img2, lyt_img3;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    Integer cluster_Id;
    private List<Bitmap> images = new ArrayList<>();
    DecimalFormat dec = new DecimalFormat("####0.00");
    String currentDate;
    Button submit;
    Button buttonStart, buttonStop, buttonPlayLastRecordAudio, buttonStopPlayingRecording;
    String AudioSavePathInDevice = "";
    MediaRecorder mediaRecorder;
    Random random;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer;
String clustername;
    EditText comments;
    boolean flag = false;
    int pos;
    private Chronometer chronometer;
    private ImageView imageViewRecord, imageViewPlay, imageViewStop;
    private SeekBar seekBar;
    private LinearLayout linearLayoutRecorder, linearLayoutPlay;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private String fileName = null;
    private int lastProgress = 0;
    private Handler mHandler = new Handler();
    private int RECORD_AUDIO_REQUEST_CODE = 123;
    private boolean isPlaying = false;
    double plot_Age,plotarea;
    LoginResponse created_user;
    private Integer User_id;
    String statename,statecode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int langID = SharedPrefsData.getInstance(this).getIntFromSharedPrefs("lang");
        if (langID == 2)
            updateResources(this, "te");
        else if (langID == 3)
            updateResources(this, "kan");
        else
            updateResources(this, "en-US");
        setContentView(R.layout.activity_visit_request_);
        requestMultiplePermissions();
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            plot_id = extras.getString("plotid");
            plot_Age = extras.getDouble("plotAge", 0.00);
            plotarea = extras.getDouble("plotarea",0.00);
            location = extras.getString("plotVillage");
            landmarkCode = extras.getString("landMark");
            date_of_plandation = extras.getString("date_of_plandation");
            clustername = extras.getString("cluster_name");
            cluster_Id = extras.getInt("cluster_Id");
            statename = extras.getString("statename");
            statecode = extras.getString("statecode");

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPermissionToRecordAudio();
        }
        intview();
        setViews();



    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissionToRecordAudio() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    RECORD_AUDIO_REQUEST_CODE);

        }
    }

    private void intview() {
        //   requestMultiplePermissions();
        backImg = (ImageView) findViewById(R.id.back);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        btn_addIMG = findViewById(R.id.btn_addIMG);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();

        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");       // Saving string data of your editext
        Age = findViewById(R.id.age_plot);
        id_plot = findViewById(R.id.plot);
        area = findViewById(R.id.palmArea);
        landMark = findViewById(R.id.landmark);
        cluster_name =findViewById(R.id.cluster_officer);
        yop =findViewById(R.id.yop);
        Select_Issue = findViewById(R.id.issue_type);
        comments = findViewById(R.id.comments);
        imageview = (ImageView) findViewById(R.id.iv);
        imageview2 = (ImageView) findViewById(R.id.iv2);
        imageview3 = (ImageView) findViewById(R.id.iv3);

        imageview.setVisibility(View.GONE);
        imageview2.setVisibility(View.GONE);
        imageview2.setVisibility(View.GONE);
        btn_addIMG.setVisibility(View.VISIBLE);

//        buttonStart = (Button) findViewById(R.id.button);
//        buttonStop = (Button) findViewById(R.id.button2);
//        buttonPlayLastRecordAudio = (Button) findViewById(R.id.button3);
        //   buttonStopPlayingRecording = (Button) findViewById(R.id.button4);
        submit = (Button) findViewById(R.id.req_loan);
//        buttonStop.setEnabled(false);
//        buttonPlayLastRecordAudio.setEnabled(false);


        /*
         * Images disable enable when select from Camera
         * */
        // buttonStopPlayingRecording.setEnabled(false);
        lyt_img = findViewById(R.id.lyt_img1);
        lyt_img2 = findViewById(R.id.lyt_img2);
        lyt_img3 = findViewById(R.id.lyt_img3);

        img_delete1 = findViewById(R.id.img_delete1);
        img_delete2 = findViewById(R.id.img_delete2);
        img_delete3 = findViewById(R.id.img_delete3);

        /*Initially Disbale images */
        lyt_img.setVisibility(View.GONE);
        lyt_img2.setVisibility(View.GONE);
        lyt_img3.setVisibility(View.GONE);

        linearLayoutRecorder = (LinearLayout) findViewById(R.id.linearLayoutRecorder);
        chronometer = (Chronometer) findViewById(R.id.chronometerTimer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        imageViewRecord = (ImageView) findViewById(R.id.imageViewRecord);
        imageViewStop = (ImageView) findViewById(R.id.imageViewStop);
        imageViewPlay = (ImageView) findViewById(R.id.imageViewPlay);
        linearLayoutPlay = (LinearLayout) findViewById(R.id.linearLayoutPlay);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        imageViewRecord.setOnClickListener(this);
        imageViewStop.setOnClickListener(this);
        imageViewPlay.setOnClickListener(this);
        random = new Random();

    }


    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //Toast.makeText(getApplicationContext(), getResources().getString(R.string.userPermission), Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();

        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    private void setViews() {
        img_delete1.setOnClickListener(this);
        img_delete2.setOnClickListener(this);
        img_delete3.setOnClickListener(this);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(Visit_request_Activity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        Age.setText(dec.format(plot_Age )+" Ha (" +dec.format(plotarea)+" Acre)"  );
        area.setText(location);
        id_plot.setText(plot_id);
        landMark.setText(landmarkCode);
        yop.setText(date_of_plandation);
        cluster_name.setText(clustername);
        GetIssue_type();
        Select_Issue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_issue = Select_Issue.getItemAtPosition(Select_Issue.getSelectedItemPosition()).toString();

                //  Log.e("seleced_period===", seleced_Duration);
//                durationId = period_id.get(labourSpinner.getSelectedItemPosition());
                //Log.e("duration======", String.valueOf(durationId));
//
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


        btn_addIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (images.size() > 2) {
                    Toast.makeText(Visit_request_Activity.this, "max Images 3", Toast.LENGTH_SHORT).show();
                } else {
                    showPictureDialog();
                }


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validations()) {
                    stoppRecording();
                    try {
                        mPlayer.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mPlayer = null;
                    if (isOnline())
                        AddVisitRequest();
                    else {
                        showDialog(Visit_request_Activity.this, getResources().getString(R.string.Internet));
                        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void stoppRecording() {
        try {
            mRecorder.stop();
            mRecorder.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRecorder = null;
        //starting the chronometer
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
    }


    private boolean validations() {
        if (Select_Issue.getSelectedItemPosition() == 0) {

            showDialog(Visit_request_Activity.this, getResources().getString(R.string.valid_issue_type));
            return false;
        }
        if (selected_issue.contains("Others")) {
            if (comments.getText().toString().trim().length() == 0) {
                showDialog(Visit_request_Activity.this, getResources().getString(R.string.comments_valid));
                return false;
            }
        }
        if (images.size() == 0 &&  fileName == null) {

            Log.d(TAG, "---- analysis ---->> base64 :" + images.size() + fileName);
            showDialog(Visit_request_Activity.this, getResources().getString(R.string.select_image));
            return false;
        }


        return true;
    }


    private void AddVisitRequest() {
        mdilogue.show();
        JsonObject object = visitReuestobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.postvisit(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VisitresponseModel>() {
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
                        mdilogue.dismiss();
                    }

                    @Override
                    public void onNext(VisitresponseModel visitresponseModel) {


                        if (visitresponseModel.getIsSuccess()) {
                            mdilogue.dismiss();
                            submit.setEnabled(false);
                            if (visitresponseModel.getAffectedRecords() == 0) {
                                showDialog(Visit_request_Activity.this, visitresponseModel.getEndUserMessage());
                            } else {
                                new Handler().postDelayed(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.M)
                                    @Override
                                    public void run() {
//                                        String selected_name = arrayyTOstring(selected_labour);
//                                        String Amount = amount.getText().toString();
//                                        String date = edittext.getText().toString();
                                        List<MSGmodel> displayList = new ArrayList<>();

                                        // displayList.add(new MSGmodel(getString(R.string.select_labour_type), selected_name));
                                        displayList.add(new MSGmodel(getResources().getString(R.string.issue_type), selected_issue));
                                        String Comments = comments.getText().toString();
                                        if (Comments != null && !Comments.isEmpty() && !Comments.equals("null")) {
                                            displayList.add(new MSGmodel(getResources().getString(R.string.comments), comments.getText().toString()));
                                        }


                                        showvisitSuccessDialog(displayList, getResources().getString(R.string.visit_success));
                                    }
                                }, 300);
                            }
                        } else {
                            showDialog(Visit_request_Activity.this, visitresponseModel.getEndUserMessage());
                        }
                    }


                });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void showvisitSuccessDialog(List<MSGmodel> displayList, String summary) {

        final Button play;
        final ImageView iv1, iv2, iv3, imageView_Play;
        LinearLayout voice_layout;
        final SeekBar seek_Bar;
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.visit_dialog, viewGroup, false);
        TextView summary_text = dialogView.findViewById(R.id.summary_text);
        summary_text.setText(summary);

        iv1 = dialogView.findViewById(R.id.iv);
        iv2 = dialogView.findViewById(R.id.iv2);
        iv3 = dialogView.findViewById(R.id.iv3);
        imageView_Play = dialogView.findViewById(R.id.imageView_Play);
        seek_Bar = dialogView.findViewById(R.id.seek_Bar);
        voice_layout = dialogView.findViewById(R.id.linearLayout_Play);
        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.GONE);
        iv3.setVisibility(View.GONE);

        if (null != fileName || !TextUtils.isEmpty(fileName) ){
//            File file = new File(fileName);
//        if (file.exists()) {
            voice_layout.setVisibility(View.VISIBLE);
        }
        else {
            voice_layout.setVisibility(View.GONE);
        }

        for (int i = 0; i < images.size(); i++) {
            if (i == 0) {
                iv1.setVisibility(View.VISIBLE);
                iv1.setImageBitmap(images.get(i));
            }
            if (i == 1) {
                iv2.setVisibility(View.VISIBLE);
                iv2.setImageBitmap(images.get(i));
            }

            if (i == 2) {
                iv3.setVisibility(View.VISIBLE);
                iv3.setImageBitmap(images.get(i));
            }
        }
//
        imageView_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException, SecurityException, IllegalStateException {
                // if (mPlayer != null) {
                lastProgress = 0;
//                    chronometer.setBase(SystemClock.elapsedRealtime());
//                    startPlaying();
//                } else {
//                    startPlaying();
//                }
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(fileName);
                    mPlayer.prepare();
                    mPlayer.start();
                } catch (IOException e) {
                    Log.e("LOG_TAG", "prepare() failed");
                }
                //making the imageview pause button
                imageView_Play.setImageResource(R.drawable.ic_pause);

                seek_Bar.setProgress(lastProgress);
                mPlayer.seekTo(lastProgress);
                seek_Bar.setMax(mPlayer.getDuration());
                seek_Updation();
                chronometer.start();


                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        imageView_Play.setImageResource(R.drawable.ic_play);
                        isPlaying = false;
                        chronometer.stop();
                    }
                });


                seek_Bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (mPlayer != null && fromUser) {
                            mPlayer.seekTo(progress);
                            chronometer.setBase(SystemClock.elapsedRealtime() - mPlayer.getCurrentPosition());
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
                    seek_Updation();
                }
            };

            //
            private void seek_Updation() {
                if (mPlayer != null) {
                    int mCurrentPosition = mPlayer.getCurrentPosition();
                    seek_Bar.setProgress(mCurrentPosition);
                    lastProgress = mCurrentPosition;
                }
                mHandler.postDelayed(runnable, 100);
            }
        });


        LinearLayout layout = dialogView.findViewById(R.id.linear_text);
        final ImageView img = dialogView.findViewById(R.id.img);


        for (int i = 0; i < displayList.size(); i++) {

            LinearLayout lty = new LinearLayout(this);
            lty.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            lty.setWeightSum(1);
            lty.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTitle = new TextView(this);
            txtTitle.setText(displayList.get(i).getKey());
            txtTitle.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            txtTitle.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
            txtTitle.setTextColor(getColor(R.color.red));
            lty.addView(txtTitle);

            TextView txtitem = new TextView(this);
            txtitem.setText(displayList.get(i).getValue());
            txtitem.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            txtitem.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));


            lty.addView(txtitem);
            //  lty.setGravity(View.FOCUS_LEFT);

            layout.addView(lty);
            displayImages();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button dialogButton = (Button) alertDialog.findViewById(R.id.buttonOk);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                try {
                    mPlayer.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mPlayer = null;

                Intent intent = new Intent(Visit_request_Activity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });


    }


    private JsonObject visitReuestobject() {
//        String statecode = SharedPrefsData.getInstance(this).getStringFromSharedPrefs("statecode");
//        Log.e("state===",statecode);
        created_user = SharedPrefsData.getCreatedUser(Visit_request_Activity.this);

        User_id= created_user.getResult().getUserInfos().getId();

        VisitRequestModel.RequestHeader header = new VisitRequestModel.RequestHeader();

        header.setId(0);
        header.setRequestCode(null);
        header.setRequestTypeId(14);
        header.setFarmerCode(Farmer_code);
        header.setFarmerName(SharedPrefsData.getusername(this));
        header.setPlotCode(plot_id);
        header.setReqCreatedDate(currentDate);
        header.setStatusTypeId(15);
        header.setIsFarmerRequest(false);
        header.setCreatedByUserId(User_id);
        header.setStateCode(statecode);
        header.setStateName(statename);
        header.setCreatedDate(currentDate);
        header.setUpdatedByUserId(User_id);
        header.setUpdatedDate(currentDate);
        header.setTotalCost(null);
        header.setPalmArea(plot_Age + "");
        header.setPlotVillage(location);
        header.setClusterId(cluster_Id);
        header.setComments(comments.getText().toString());
        header.setCropMaintainceDate(null);
        header.setIssueTypeId(Issue_Id.get(Select_Issue.getSelectedItemPosition() - 1));

        List<VisitRequestModel.VisitRepo> visitRepo = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {

            VisitRequestModel.VisitRepo visitRepo1 = new VisitRequestModel.VisitRepo();
            visitRepo1.setId(1);
            visitRepo1.setRequestCode(null);
            visitRepo1.setFileLocation(null);
            visitRepo1.setFileName(CommonUtil.bitMaptoBase64(images.get(i)));
            visitRepo1.setFileExtension(".jpg");
            visitRepo1.setIsActive(true);
            visitRepo1.setCreatedByUserId(User_id);
            visitRepo1.setCreatedDate(currentDate);
            visitRepo1.setFileTypeId(36);


            visitRepo.add(visitRepo1);
        }


        if (null != fileName || !TextUtils.isEmpty(fileName)) {

            VisitRequestModel.VisitRepo visitRepo1audio = new VisitRequestModel.VisitRepo();
            visitRepo1audio.setId(1);

            visitRepo1audio.setFileName(doFileUpload(new File(fileName)));
            visitRepo1audio.setFileExtension(".mp3");
            visitRepo1audio.setIsActive(true);
            visitRepo1audio.setCreatedByUserId(User_id);
            visitRepo1audio.setCreatedDate(currentDate);
            visitRepo1audio.setFileTypeId(37);

            visitRepo1audio.setRequestCode(null);
            visitRepo1audio.setFileLocation(null);
            visitRepo.add(visitRepo1audio);
        }

        VisitRequestModel requestModel = new VisitRequestModel(header, visitRepo);

        requestModel.setReason(selected_issue);
        Log.d(TAG, "---- analysis ---->> base64 514:" + images.size() + fileName);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {

                    boolean StoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {

                        Toast.makeText(Visit_request_Activity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Visit_request_Activity.this, "Permission Denied", Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }
    }

    public boolean checkPermissionn() {

        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Log.e("path===", path);
                    //   Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    //imageview.setImageBitmap(bitmap);
                    images.add(bitmap);
                    displayImages();
                } catch (IOException e) {
                    e.printStackTrace();
                    //  Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            images.add(thumbnail);
            //  saveImage(thumbnail);
            displayImages();
            //Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayImages() {

        lyt_img.setVisibility(View.GONE);
        lyt_img2.setVisibility(View.GONE);
        lyt_img3.setVisibility(View.GONE);
        btn_addIMG.setVisibility(View.VISIBLE);
        if (images.size() > 0) {
            if (images.size() > 0 && images.get(0) != null) {
                imageview.setImageBitmap(images.get(0));
                imageview.setVisibility(View.VISIBLE);
                lyt_img.setVisibility(View.VISIBLE);

            }
            if (images.size() > 1 && images.get(1) != null) {
                imageview2.setImageBitmap(images.get(1));
                imageview2.setVisibility(View.VISIBLE);
                lyt_img2.setVisibility(View.VISIBLE);
            }
            if (images.size() > 2 && images.get(2) != null) {
                imageview3.setImageBitmap(images.get(2));
                imageview3.setVisibility(View.VISIBLE);
                lyt_img3.setVisibility(View.VISIBLE);
                btn_addIMG.setVisibility(View.GONE);
            }
        }

    }



    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void GetIssue_type() {


        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getIssuestypes(APIConstantURL.GetIssue)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetIssueModel>() {
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
                        showDialog(Visit_request_Activity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetIssueModel getIssueModel) {


                        if (getIssueModel.getListResult() != null) {
                            Issue_type.add("Select");
                            for (GetIssueModel.ListResult data : getIssueModel.getListResult()
                            ) {
                                Issue_type.add(data.getDesc());
                                Issue_Id.add(data.getTypeCdId());
                            }
                            Log.d(TAG, "RESPONSE======" + Issue_type);

//

                            ArrayAdapter aa = new ArrayAdapter(Visit_request_Activity.this, R.layout.spinner_item, Issue_type);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            Select_Issue.setAdapter(aa);
                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //  mediaRecorder.stop();
        this.finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_delete1:
                images.remove(0);
                displayImages();
                break;
            case R.id.img_delete2:
                images.remove(1);
                displayImages();
                break;
            case R.id.img_delete3:
                images.remove(2);
                displayImages();
                break;

            case R.id.imageViewRecord:
                prepareforRecording();
                startRecording();

                break;
            case R.id.imageViewStop:
                prepareforStop();
                stopRecording();

                break;
            case R.id.imageViewPlay:
                if (!isPlaying && fileName != null) {
                    isPlaying = true;
                    if (mPlayer != null) {
                          lastProgress = 0;
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        startPlaying();
                    } else {
                        startPlaying();
                    }

                } else {
                    isPlaying = false;
                    stopPlaying();
                }
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void prepareforStop() {
        TransitionManager.beginDelayedTransition(linearLayoutRecorder);
        imageViewRecord.setVisibility(View.VISIBLE);
        imageViewStop.setVisibility(View.GONE);
        linearLayoutPlay.setVisibility(View.VISIBLE);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void prepareforRecording() {
        TransitionManager.beginDelayedTransition(linearLayoutRecorder);
        imageViewRecord.setVisibility(View.GONE);
        imageViewStop.setVisibility(View.VISIBLE);
        linearLayoutPlay.setVisibility(View.GONE);
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
        chronometer.stop();

    }


    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.AudioEncoder.AMR_NB);

        File root = android.os.Environment.getExternalStorageDirectory();
        File file = new File(root.getAbsolutePath() + "/3FAkshaya/Audios");
        if (!file.exists()) {
            file.mkdirs();
        }

        fileName = root.getAbsolutePath() + "/3FAkshaya/Audios/" + String.valueOf(System.currentTimeMillis() + ".mp3");
        Log.d("filename===1049", fileName);
        mRecorder.setOutputFile(fileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastProgress = 0;
        seekBar.setProgress(0);
        stopPlaying();
        // making the imageview a stop button
        //starting the chronometer
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }


    private void stopRecording() {

        try {
            mRecorder.stop();
            mRecorder.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRecorder = null;
        //starting the chronometer
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        //showing the play button
      //  Toast.makeText(this, "Recording saved successfully.", Toast.LENGTH_SHORT).show();
    }


    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(fileName);
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
        chronometer.start();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {imageViewPlay.setImageResource(R.drawable.ic_play);
                seekBar.setVisibility(View.GONE);
                isPlaying = false;
                chronometer.stop();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mPlayer != null && fromUser) {
                    mPlayer.seekTo(progress);
                    chronometer.setBase(SystemClock.elapsedRealtime() - mPlayer.getCurrentPosition());
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


    private String doFileUpload(File f){


        byte[] bytes = new byte[0];
        try {
            bytes = FileUtils.readFileToByteArray(f);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //String encoded = Base64.encodeToString(bytes, 0);
        String finalString =android.util.Base64.encodeToString(bytes,0);



        return finalString;

// Receiving side

    }


}
