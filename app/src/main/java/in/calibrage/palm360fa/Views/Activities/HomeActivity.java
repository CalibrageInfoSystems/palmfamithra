package in.calibrage.palm360fa.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.Views.transport.FarmerTransportServiceActivity;
import in.calibrage.palm360fa.common.Constants;
import in.calibrage.palm360fa.localData.SharedPrefsData;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class HomeActivity extends AppCompatActivity {
CardView Labour,pole,fertilizer,loan,visit,quickpay,collections,payments,crop,special_button,vendorservy_button,farmerservey_button, bioLab,trans_button,edibleoil_button;
ImageView logout,ic_request;
TextView dialogMessage;
    boolean doubleBackToExitPressedOnce = false;
    private Button ok_btn, cancel_btn;
    LoginResponse created_user;
    String activityRights;
    LinearLayout special_payl,spe_border;
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
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /* intializing and assigning ID's */
        initViews();
        /* Navigation's and using the views */
        setViews();
    }

    private void initViews() {

        Labour=findViewById(R.id.labour_button);
        pole=findViewById(R.id.pole_button);
        fertilizer=findViewById(R.id.fertlizer_button);
        loan=findViewById(R.id.loan_button);
        visit=findViewById(R.id.visit_button);
        quickpay=findViewById(R.id.Quickpay_button);
        collections=findViewById(R.id.collections_button);
        payments=findViewById(R.id.payments_button);
        crop=findViewById(R.id.recommendations_button);
        special_button=findViewById( R.id.special_button);
        vendorservy_button=findViewById( R.id.vendorservy_button);
        farmerservey_button=findViewById( R.id.farmerservey_button);
        bioLab=findViewById(R.id.bioLab_button);
        trans_button=findViewById( R.id.trans_button);
        edibleoil_button=findViewById( R.id.edibleoil_button);
       // special_payl = findViewById(R.id.special_payl);
        //spe_border = findViewById(R.id.spe_border);

    }
    private void setViews() {
        created_user = SharedPrefsData.getCreatedUser(HomeActivity.this);

        activityRights= created_user.getResult().getUserInfos().getActivityRights();
      if(activityRights.contains("59")){
          //special_payl.setVisibility(View.VISIBLE);
         // spe_border.setVisibility(View.VISIBLE);
          //Toast.makeText(this, "Have Special Fee" , Toast.LENGTH_SHORT).show();
      }else{
          //special_payl.setVisibility(View.GONE);
          //spe_border.setVisibility(View.GONE);
        //  Toast.makeText(this, "haven't special fee: " , Toast.LENGTH_SHORT).show();
      }
        collections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","Collection");
                startActivity(intent);
            }
        });


        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","crop");
                startActivity(intent);
            }
        });
        payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","Payment");
                startActivity(intent);

            }
        });

        Labour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","Labour Request");
                startActivity(intent);

                
            }
        });


        pole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","Equipment");
                startActivity(intent);

            }
        });
        fertilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","Fertilizer");
                startActivity(intent);

            }
        });
        edibleoil_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","edibleoil");
                startActivity(intent);

            }
        });
        quickpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","QuickPay");
                startActivity(intent);
            }
        });
        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","Visit");
                startActivity(intent);

            }
        });
        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","Loan");
                startActivity(intent);

            }
        });

        bioLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","BioLab");
                startActivity(intent);

            }
        });


        special_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                intent.putExtra("RequestType","special");
                startActivity(intent);

            }
        });



        vendorservy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Transport_service_questioner_vendor.class);

                startActivity(intent);

            }
        });
        farmerservey_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(HomeActivity.this, Transport_service_questioner_farmer.class);
                Intent intent = new Intent(HomeActivity.this, FarmerTransportServiceActivity.class);

                startActivity(intent);

            }
        });

        trans_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
         intent.putExtra("RequestType","Transport");
                startActivity(intent);

            }
        });
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                logOutDialog();
//            }
//        });
//        ic_request.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomeActivity.this, myRequest_Activity.class);
//                startActivity(intent);
//
//            }
//        });
    }

    private void logOutDialog() {


        final Dialog dialog = new Dialog(HomeActivity.this, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_logout);
        dialogMessage = dialog.findViewById(R.id.dialogMessage);
        dialogMessage.setText(getString(R.string.alert_logout));
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        ok_btn = dialog.findViewById(R.id.ok_btn);
/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // updateResources(getApplicationContext(), "en-US");
               SharedPrefsData.getInstance(getApplicationContext()).ClearData(getApplicationContext());
                SharedPrefsData.putBool(HomeActivity.this, Constants.IS_LOGIN, false);
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.nav_logout) {
            logOutDialog();
            return true;
        } else if (itemId == R.id.My_request) {
            Intent intent = new Intent(HomeActivity.this, myRequest_Activity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.languageTitle) {
            selectLanguage();
            return true;
        } else if (itemId == R.id.nav_farmer_logout) {
            logOutDialog_farmer();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private void logOutDialog_farmer() {



            final Dialog dialog = new Dialog(HomeActivity.this, R.style.DialogSlideAnim);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_logout);
            dialogMessage = dialog.findViewById(R.id.dialogMessage);
            dialogMessage.setText(getString(R.string.alert_logout));
            cancel_btn = dialog.findViewById(R.id.cancel_btn);
            ok_btn = dialog.findViewById(R.id.ok_btn);
/**
 * @param OnClickListner
 */
            ok_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // updateResources(getApplicationContext(), "en-US");
                    //  SharedPrefsData.getInstance(getApplicationContext()).ClearData(getApplicationContext());


                    SharedPrefsData.putBool(HomeActivity.this, Constants.IS_Former_LOGIN, false);
                    Intent intent = new Intent(HomeActivity.this, Farmar_Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();


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


    private void selectLanguage() {
        String statecode = SharedPrefsData.getInstance(this).getStringFromSharedPrefs("statecode");
        Log.e("state===",statecode);
        final Dialog dialog = new Dialog(HomeActivity.this, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_language);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setTitle("");

        // set the custom forgotPasswordDialog components - text, image and button
        final TextView rbEng = dialog.findViewById(R.id.rbEng);
        final TextView rbTelugu = dialog.findViewById(R.id.rbTelugu);
        final TextView rbKannada = dialog.findViewById(R.id.rbkannada);
        View view =dialog.findViewById(R.id.view);
        View view2 =dialog.findViewById(R.id.view2);

//        if (statecode.equalsIgnoreCase("AP")){
//
//            rbTelugu.setVisibility(View.VISIBLE);
//            rbKannada.setVisibility(View.GONE);
//            view2.setVisibility(View.GONE);
//        }
//        else{
//            rbTelugu.setVisibility(View.GONE);
//            rbKannada.setVisibility(View.VISIBLE);
//            view2.setVisibility(View.GONE);
//        }

/**
 * @param OnClickListner
 */
        rbEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * "en" is the localization code for our default English language.
                 */


                updateResources(HomeActivity.this, "en-US");
                SharedPrefsData.getInstance(HomeActivity.this).updateIntValue(HomeActivity.this, "lang", 1);
                Intent refresh = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(refresh);
                finish();
                dialog.dismiss();
            }
        });

/**
 * @param OnClickListner
 */
        rbTelugu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * "te" is the localization code for our default Telugu language.
                 */
                updateResources(HomeActivity.this, "te");
                SharedPrefsData.getInstance(HomeActivity.this).updateIntValue(HomeActivity.this, "lang", 2);
                Intent refresh = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(refresh);
                finish();
                dialog.dismiss();
            }
        });
        rbKannada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * "te" is the localization code for our default Telugu language.
                 */
                updateResources(HomeActivity.this, "kan");
                SharedPrefsData.getInstance(HomeActivity.this).updateIntValue(HomeActivity.this, "lang", 3);
                Intent refresh = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(refresh);
                finish();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage( getResources().getString(R.string.logout_pop))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        updateResources(getApplicationContext(), "en-US");
//                        SharedPrefsData.getInstance(getApplicationContext()).ClearData(getApplicationContext());
                        SharedPrefsData.putBool(HomeActivity.this, Constants.IS_Former_LOGIN, false);
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton( getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
