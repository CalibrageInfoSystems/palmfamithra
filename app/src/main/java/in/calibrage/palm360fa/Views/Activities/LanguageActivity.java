package in.calibrage.palm360fa.Views.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.Constants;
import in.calibrage.palm360fa.localData.SharedPrefsData;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;
public class LanguageActivity extends BaseActivity {

    private LinearLayout rbEng, rbTelugu, rbKannada;
    private Button btnNext;
    private boolean isLanguageSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        rbEng = findViewById(R.id.english);
        rbTelugu = findViewById(R.id.telugu);
        rbKannada = findViewById(R.id.kannada);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setEnabled(false); // disable by default
        btnNext.setAlpha(0.5f);    // faded look when disabled

        SharedPrefsData.putBool(this, Constants.WELCOME, true);

        rbEng.setOnClickListener(v -> {
            highlightSelectedLanguage(rbEng);
            updateResources(LanguageActivity.this, "en-US");
            SharedPrefsData.getInstance(LanguageActivity.this).updateIntValue(LanguageActivity.this, "lang", 1);
            enableNextButton();
        });

        rbTelugu.setOnClickListener(v -> {
            highlightSelectedLanguage(rbTelugu);
            updateResources(LanguageActivity.this, "te");
            SharedPrefsData.getInstance(LanguageActivity.this).updateIntValue(LanguageActivity.this, "lang", 2);
            enableNextButton();
        });

        rbKannada.setOnClickListener(v -> {
            highlightSelectedLanguage(rbKannada);
            updateResources(LanguageActivity.this, "kan");
            SharedPrefsData.getInstance(LanguageActivity.this).updateIntValue(LanguageActivity.this, "lang", 3);
            enableNextButton();
        });

        btnNext.setOnClickListener(v -> {
            if (isLanguageSelected) {
                navigateToLogin();
            }
        });
    }

    private void highlightSelectedLanguage(LinearLayout selectedLayout) {
        rbEng.setBackgroundResource(R.drawable.border_gray);
        rbTelugu.setBackgroundResource(R.drawable.border_gray);
        rbKannada.setBackgroundResource(R.drawable.border_gray);

        selectedLayout.setBackgroundResource(R.drawable.border_green);
        isLanguageSelected = true;
    }

    private void enableNextButton() {
        btnNext.setEnabled(true);
        btnNext.setAlpha(1f); // fully visible
    }

    private void navigateToLogin() {
        Intent refresh = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(refresh);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
