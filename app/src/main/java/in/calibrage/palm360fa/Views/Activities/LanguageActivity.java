package in.calibrage.palm360fa.Views.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.Constants;
import in.calibrage.palm360fa.localData.SharedPrefsData;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class LanguageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        final TextView rbEng = findViewById(R.id.english);
        final TextView rbTelugu = findViewById(R.id.telugu);
        final TextView rbkannda = findViewById(R.id.kannada);
        SharedPrefsData.putBool(this, Constants.WELCOME,true);
        rbEng.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                /**
                 * "en" is the localization code for our default English language.
                 */
                rbEng.setBackgroundColor(Color.rgb(60, 180, 110));
                updateResources(LanguageActivity.this, "en-US");
                SharedPrefsData.getInstance(LanguageActivity.this).updateIntValue(LanguageActivity.this, "lang", 1);
                Intent refresh = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(refresh);
                finish();
                //Toast.makeText(getApplicationContext(), R.string.language_notification, Toast.LENGTH_SHORT).show();

            }
        });

/**
 * @param OnClickListner
 */
        rbTelugu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                /**
                 * "te" is the localization code for our default Telugu language.
                 */

                rbTelugu.setBackgroundColor(Color.rgb(60, 180, 110));
                updateResources(LanguageActivity.this, "te");
                SharedPrefsData.getInstance(LanguageActivity.this).updateIntValue(LanguageActivity.this, "lang", 2);
                Intent refresh = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(refresh);
                finish();
                //  Toast.makeText(getApplicationContext(), R.string.language_notification, Toast.LENGTH_SHORT).show();



            }
        });
        rbkannda.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                /**
                 * "te" is the localization code for our default Telugu language.
                 */

                rbkannda.setBackgroundColor(Color.rgb(60, 180, 110));
                updateResources(LanguageActivity.this, "kan");
                SharedPrefsData.getInstance(LanguageActivity.this).updateIntValue(LanguageActivity.this, "lang", 3);
                SharedPrefsData.putBool(LanguageActivity.this, Constants.WELCOME,true);
                Intent refresh = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(refresh);
                finish();
                //  Toast.makeText(getApplicationContext(), R.string.language_notification, Toast.LENGTH_SHORT).show();



            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        //  moveTaskToBack(true);

    }
}
