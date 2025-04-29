package in.calibrage.palm360fa.Views.Activities;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.calibrage.palm360fa.Adapter.MyReqListAdapter;
import in.calibrage.palm360fa.Model.Request_settings;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.localData.SharedPrefsData;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class myRequest_Activity extends BaseActivity implements MyReqListAdapter.RequestAdapterListener {

    private List<Request_settings> request_List = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_request);

        final int langID = SharedPrefsData.getInstance(this).getIntFromSharedPrefs("lang");
        if (langID == 2)
            updateResources(this, "te");
        else if (langID == 3)
            updateResources(this, "kan");
        else
            updateResources(this, "en-US");
        setuptoolbar();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyReqListAdapter adapter = new MyReqListAdapter(this, request_List, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        fetchContacts();
    }
    private void setuptoolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title =(TextView) findViewById(R.id.txt_name);
       // title.setText(name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void fetchContacts() {
        int[] covers = new int[]{
                R.drawable.labour,
                R.drawable.equipment,
                R.drawable.fertilizers,
                R.drawable.quick_pay,
                R.drawable.visit,
                R.drawable.loan,

        };
        Request_settings a = new Request_settings( getResources().getString(R.string.lab_req), covers[0]);
        request_List.add(a);
        a = new Request_settings( getResources().getString(R.string.pole_req), covers[1]);
        request_List.add(a);
        a = new Request_settings( getResources().getString(R.string.fert_req), covers[2]);
        request_List.add(a);
        a = new Request_settings(getResources().getString(R.string.quick_req), covers[3]);
        request_List.add(a);
        a = new Request_settings(getResources().getString(R.string.visit_req), covers[4]);
        request_List.add(a);
        a = new Request_settings(getResources().getString(R.string.Loan_req), covers[5]);
        request_List.add(a);
    }

    @Override

        public void onContactSelected(Request_settings request) {
            if (request.getName().contains( getResources().getString(R.string.lab_req))) {
                Intent intent = new Intent(this, RequestListctivity.class);
                intent.putExtra("key", getResources().getString(R.string.lab_req));
                startActivity(intent);
            }
            if (request.getName().contains( getResources().getString(R.string.pole_req))) {
                Intent intent = new Intent(this, RequestListctivity.class);
                intent.putExtra("key", getResources().getString(R.string.pole_req));
                startActivity(intent);
            }
            if (request.getName().contains( getResources().getString(R.string.fert_req))) {
                Intent intent = new Intent(this, RequestListctivity.class);
                intent.putExtra("key", getResources().getString(R.string.fert_req));
                startActivity(intent);
            }
            if (request.getName().contains(getResources().getString(R.string.quick_req))) {
                Intent intent = new Intent(this, RequestListctivity.class);
                intent.putExtra("key", getResources().getString(R.string.quick_req));
                startActivity(intent);
            }
            if (request.getName().contains(getResources().getString(R.string.visit_req))) {
                Intent intent = new Intent(this, RequestListctivity.class);
                intent.putExtra("key", getResources().getString(R.string.visit_req));
                startActivity(intent);
            }
            if (request.getName().contains(getResources().getString(R.string.Loan_req))) {
                Intent intent = new Intent(this, RequestListctivity.class);
                intent.putExtra("key", getResources().getString(R.string.Loan_req));
                startActivity(intent);
            }

        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
