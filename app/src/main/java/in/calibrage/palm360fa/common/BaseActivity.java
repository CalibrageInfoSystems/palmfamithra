package in.calibrage.palm360fa.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import in.calibrage.palm360fa.Model.MSGmodel;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.Views.Activities.HomeActivity;
import in.calibrage.palm360fa.localData.SharedPrefsData;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;


/*
 * This class base For all Activitys , here we declared all common activity related Methods
 * */
public class BaseActivity extends AppCompatActivity {
    Button showPopupBtn, closePopupBtn;
    PopupWindow popupWindow;
    LinearLayout linearLayout1;
    private ProgressDialog mProgressDialog;

    //region Check user Online or not
    public boolean isOnline() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }
    //endregion
    //region Validation Check Dialog
    public void validationPopShow() {

        LayoutInflater layoutInflater = (LayoutInflater) BaseActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup, null);

        closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popup_window_animation);

        //display the popup window
        // popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

        //close the popup window on button click
        closePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }
    //endregion
    //region Error Dialog
    public void showDialog(Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity, R.style.DialogSlideAnim);
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
    //endregion
    //region KeyBorad Show & Hide
    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            inputMethodManager.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);


    }
    //endregion
    //region Common Dialog For Sucess Message
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void showSuccessDialog(List<MSGmodel> msg, String summary) {
        final int langID = SharedPrefsData.getInstance(this).getIntFromSharedPrefs("lang");
        if (langID == 2)
            updateResources(this, "te");
        else
            updateResources(this, "en-US");
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);


        //TextView txtmsg = dialogView.findViewById(R.id.txtmsg);
        LinearLayout layout = dialogView.findViewById(R.id.linear_text);
        TextView summary_text = dialogView.findViewById(R.id.summary_text);
        summary_text.setText(summary);
        final ImageView img = dialogView.findViewById(R.id.img);


        for (int i = 0; i < msg.size(); i++) {

            LinearLayout lty = new LinearLayout(this);
            lty.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            lty.setWeightSum(1);
            lty.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTitle = new TextView(this);
            txtTitle.setText(msg.get(i).getKey());
            txtTitle.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            txtTitle.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
            txtTitle.setTextColor(getColor(R.color.red));
            lty.addView(txtTitle);

            TextView txtitem = new TextView(this);
            txtitem.setText(msg.get(i).getValue());
            txtitem.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            txtitem.setPadding(10, 0, 0, 0);
            txtitem.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));


            lty.addView(txtitem);
            //  lty.setGravity(View.FOCUS_LEFT);

            layout.addView(lty);
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


             //   updateResources(getApplicationContext(), "en-US");
                SharedPrefsData.putBool(getApplicationContext(), Constants.IS_Former_LOGIN, false);
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                finish();

            }
        });

    }
    //endregion

}





