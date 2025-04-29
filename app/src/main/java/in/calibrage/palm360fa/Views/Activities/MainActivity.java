package in.calibrage.palm360fa.Views.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.kal.rackmonthpicker.RackMonthPicker;
import com.kal.rackmonthpicker.listener.DateMonthDialogListener;
import com.kal.rackmonthpicker.listener.OnCancelMonthDialogListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import in.calibrage.palm360fa.Fragments.PaymentFragment;
import in.calibrage.palm360fa.Fragments.TransFragment;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.CommonUtil;
import in.calibrage.palm360fa.localData.SharedPrefsData;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;


public class MainActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabChats;
    private TabItem tabStatus;
    String financiyalYearFrom ;
    String financiyalYearTo ;
    String selected_item;
    private Button submit;
    private String fromString, toString;

    private String farmatted_fromdate, farmated_todate;
    String[] selection ;
    Spinner spin;
    PaymentFragment myFragment;
    private String currentDate,last_30day,month_lastdate,firstdate_threemonths,dateInString;
    LinearLayout custom_linear;
    private EditText fromText, toText;
    private DatePickerDialog picker;
    private Calendar calendar;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private ImageView backImg, home_btn;
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
        setContentView(R.layout.activity_main);

        String[]  selection2=  {
                getString(R.string.last_month), getString(R.string.last_threemonth),getString(R.string.currentfinicialP), getString(R.string.selectedP)};
        selection= selection2;

//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle(getResources().getString(R.string.app_name));
//        setSupportActionBar(toolbar);
        spin = (Spinner) findViewById(R.id.spinner);
        tabLayout = findViewById(R.id.tablayout);
        tabChats = findViewById(R.id.tabpayments);
        tabStatus = findViewById(R.id.tabtrans);
        custom_linear =(LinearLayout) findViewById(R.id.linear2);
        fromText = (EditText) findViewById(R.id.from_date);
        fromText.setInputType(InputType.TYPE_NULL);
        toText = (EditText) findViewById(R.id.to_date);
        submit = (Button) findViewById(R.id.btn__sub);
        backImg = (ImageView) findViewById(R.id.back);

        home_btn = (ImageView) findViewById(R.id.home_btn);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.spinner_item, selection);
        aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(aa);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        SharedPrefsData.getInstance(this).updateStringValue(this,"sitem","Last 30 Days");
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Log.i("LOG_RESPONSE date ", currentDate);


        Calendar aCalendar = Calendar.getInstance();
        aCalendar.set(Calendar.DATE, 1);
        aCalendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDateOfPreviousMonth = aCalendar.getTime();
        aCalendar.set(Calendar.DATE, 1);
        Date firstDateOfPreviousMonth = aCalendar.getTime();


      //  Calendar aCalendar = Calendar.getInstance();
        aCalendar.set(Calendar.DATE, 1);
        aCalendar.add(Calendar.MONTH, -2);
//        Date lastDateOfPreviousMonth = aCalendar.getTime();
        aCalendar.set(Calendar.DATE, 1);
        Date firstDateOfthreeMonth = aCalendar.getTime();


        //    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        last_30day = format.format(firstDateOfPreviousMonth);
        firstdate_threemonths =format.format(firstDateOfthreeMonth);
        month_lastdate = format.format(lastDateOfPreviousMonth);
        Log.i("last==1sdate ", last_30day);
        Log.i("last==30thdate ", month_lastdate);
        Log.i("last==3firstdate ", firstdate_threemonths);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();


        c.add(Calendar.DATE, 60);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        System.out.println("String date:"+dateInString);
        final int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
        final int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH) + 1);

        if (CurrentMonth < 4) {
            financiyalYearFrom = (CurrentYear - 1) + "-04-01";
            financiyalYearTo = (CurrentYear) + "-03-31";

        } else {
            financiyalYearFrom = (CurrentYear) + "-04-01";
            financiyalYearTo = (CurrentYear + 1) + "-03-31";

        }
        fromText.setHint(CommonUtil.getMultiColourString(getString(R.string.from_month)));
        toText.setHint(CommonUtil.getMultiColourString(getString(R.string.to_month)));


        fromText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RackMonthPicker(MainActivity.this)
                        .setLocale(Locale.getDefault())
                        .setPositiveButton(new DateMonthDialogListener() {
                            @Override
                            public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {
                                //fromText.setText(startDate + "/" + (month) + "/" + year);

                            if (CurrentYear == year){

                               if(month <= CurrentMonth){

                                   fromText.setText(startDate + "/" + (month) + "/" + year);
                               }
                                else{

                                   Toast.makeText(getApplicationContext(), getResources().getString(R.string.unableselect), Toast.LENGTH_LONG).show();
                               }
                            }
                            else if(CurrentYear < year) {

                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.unableselect), Toast.LENGTH_LONG).show();

                            }
                            else if(CurrentYear >year) {
                                fromText.setText(startDate + "/" + (month) + "/" + year);
                            }
                            }
                        })
                        .setNegativeButton(new OnCancelMonthDialogListener() {
                            @Override
                            public void onCancel(AlertDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();

            }
        });

        toText.setInputType(InputType.TYPE_NULL);
        toText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RackMonthPicker rackMonthPicker = new RackMonthPicker(MainActivity.this);
                rackMonthPicker.setLocale(Locale.getDefault());
                rackMonthPicker.setPositiveButton(new DateMonthDialogListener() {
                    @Override
                    public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {
                        GregorianCalendar cal = new GregorianCalendar();
                        if(cal.isLeapYear(year)){
                            if (month == 1) {
                                endDate = 31;
                            } else if (month == 2) {
                                endDate = 29;
                            } else if (month == 3) {
                                endDate = 31;
                            } else if (month == 4) {
                                endDate = 30;
                            } else if (month == 5) {
                                endDate = 31;
                            } else if (month == 6) {
                                endDate = 30;
                            } else if (month == 7) {
                                endDate = 31;
                            } else if (month == 8) {
                                endDate = 31;
                            } else if (month == 9) {
                                endDate = 30;
                            } else if (month == 10) {
                                endDate = 31;
                            } else if (month == 11) {
                                endDate = 30;
                            } else if (month == 12) {
                                endDate = 31;
                            }
                        } else {
                            if (month == 1) {
                                endDate = 31;
                            } else if (month == 2) {
                                endDate = 28;
                            } else if (month == 3) {
                                endDate = 31;
                            } else if (month == 4) {
                                endDate = 30;
                            } else if (month == 5) {
                                endDate = 31;
                            } else if (month == 6) {
                                endDate = 30;
                            } else if (month == 7) {
                                endDate = 31;
                            } else if (month == 8) {
                                endDate = 31;
                            } else if (month == 9) {
                                endDate = 30;
                            } else if (month == 10) {
                                endDate = 31;
                            } else if (month == 11) {
                                endDate = 30;
                            } else if (month == 12) {
                                endDate = 31;
                            }
                        }
                        if (CurrentYear == year) {

                            if (month <= CurrentMonth) {

                                toText.setText(endDate + "/" + (month) + "/" + year);
                            } else {

                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.unableselect), Toast.LENGTH_LONG).show();
                            }
                        } else if (CurrentYear < year) {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.unableselect), Toast.LENGTH_LONG).show();

                        } else if (CurrentYear > year) {
                            toText.setText(endDate + "/" + (month) + "/" + year);
                        }


                    }
                });
                rackMonthPicker.setNegativeButton(new OnCancelMonthDialogListener() {
                    @Override
                    public void onCancel(AlertDialog dialog) {
                        dialog.dismiss();
                    }
                });
                rackMonthPicker.show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                fromString = fromText.getText().toString().trim();
                toString = toText.getText().toString().trim();
                Log.d("fromString==", fromString);
                Log.d("toString==", toString);

                if (fromString.equalsIgnoreCase("") || toString.equalsIgnoreCase("")) {
                    showDialog(MainActivity.this, getResources().getString(R.string.enter_Date));
//                    pay_adapter.clearAllDataa();
//                    totalLinear.setVisibility(View.GONE);
//                    Payment_recycle.setVisibility(View.GONE);
                    Intent intent = new Intent("KEY");
                    intent.putExtra("todate", "clear");
                    intent.putExtra("fromdate", fromString);
                    sendBroadcast(intent);
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date1 = null;
                    try {
                        date1 = formatter.parse(fromString);

                        Date date2 = formatter.parse(toString);
                       String currentDatee = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        Date todate = formatter.parse(currentDatee);


                        if (date2.compareTo(date1) < 0) {

                            showDialog(MainActivity.this, getResources().getString(R.string.datevalidation));
                            Intent intent = new Intent("KEY");
                            intent.putExtra("todate", "clear");
                            intent.putExtra("fromdate", fromString);
                            sendBroadcast(intent);
//                            makeText(getApplicationContext(), "Please Enter From Date is less than To Date", Toast.LENGTH_LONG).show();
                        } else {

                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date d1 = null;
                            Date d2 = null;
                            try {
                                d1 = sdf.parse(fromString);

                                d2 = sdf.parse(toString);


                                System.out.println("1. " + sdf.format(d1).toUpperCase());
                                System.out.println("2. " + sdf.format(d2).toUpperCase());

//                                if (compareTo(d1, d2) < 0) {
//
//                                    System.out.println("proceed");
//                                } else if (compareTo(d1, d2) > 0) {
//                                    System.out.println("invalid");
//                                } else {
//                                    System.out.println("equal");
//                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            long diff = d2.getTime() - d1.getTime();

                            Log.e("diff===", String.valueOf(diff));

                            float dayCount = (float) diff / (24 * 60 * 60 * 1000);

                            if(date2.compareTo(todate) < 0){
                                Log.e("date2==1",date2+"");
                                farmated_todate= format.format(date2);
                            }
                            else{
                                farmated_todate= format.format(todate);
                                Log.e("date2==2",todate+"");
                            }
                            farmatted_fromdate = format.format(date1);

                            Log.e("dayCount===", String.valueOf(dayCount));
                            Intent intent = new Intent("KEY");
                            intent.putExtra("todate", farmated_todate);
                            intent.putExtra("fromdate", farmatted_fromdate);
                            sendBroadcast(intent);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i , long l) {
        selected_item = selection[i];
        fromText.getText().clear();
        toText.getText().clear();

        if(i == 0)
        {
            Intent intent = new Intent("KEY");
            intent.putExtra("todate", dateInString);
            intent.putExtra("fromdate", last_30day);
            sendBroadcast(intent);
        }else if(i== 1)
        {

            Intent intent = new Intent("KEY");
            intent.putExtra("todate", dateInString);
            intent.putExtra("fromdate", firstdate_threemonths);
            sendBroadcast(intent);
        }else if(i == 2)
        {
            Intent intent = new Intent("KEY");
            intent.putExtra("todate", dateInString);
            intent.putExtra("fromdate", financiyalYearFrom);
            sendBroadcast(intent);
        }
        else if(i == 3)
        {
            Intent intent = new Intent("KEY");
            intent.putExtra("todate", "clear");
            intent.putExtra("fromdate", financiyalYearFrom);
            sendBroadcast(intent);
        }

        if (spin.getSelectedItemPosition()== 3){


            custom_linear.setVisibility(View.VISIBLE);
        }
        else {
            custom_linear.setVisibility(View.GONE);
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }




    private class PageAdapter extends FragmentPagerAdapter {
        String spinner_item;
        public String getSpinner_item() {
            return spinner_item;
        }

        public void setSpinner_item(String spinner_item) {
            this.spinner_item = spinner_item;
        }


        private int numOfTabs;

        PageAdapter(FragmentManager fm, int numOfTabs) {
            super(fm);
            this.numOfTabs = numOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

         //  String selectedText= SharedPrefsData.getInstance(getApplicationContext()).getStringFromSharedPrefs("sitem");
            switch (position) {
                case 0:

                    return new PaymentFragment();


                case 1:
                    return new TransFragment();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return numOfTabs;
        }
    }
}
