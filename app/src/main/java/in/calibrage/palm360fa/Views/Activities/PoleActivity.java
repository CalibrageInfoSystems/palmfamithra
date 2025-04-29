package in.calibrage.palm360fa.Views.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import in.calibrage.palm360fa.Adapter.ModelpoleAdapter;
import in.calibrage.palm360fa.Model.ModelFert;
import in.calibrage.palm360fa.Model.Product_new;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.CommonUtil;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.APIConstantURL;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class PoleActivity extends BaseActivity implements  ModelpoleAdapter.listner {
    private RecyclerView recyclerView;
    private ModelpoleAdapter adapter;
    static ArrayList<Product_new> myProductsList = new ArrayList<>();
    String dis_price, Farmer_code;
    final Context context = this;
    Button button, btn_next;
    TextView mealTotalText, txt_count,no_data;
    private String TAG = "PoleActivity";
    List<ModelFert> product_list = new ArrayList<>();
    private ProgressDialog dialog;
    private ImageView cartButtonIV;
    Integer  total_amount;

    LinearLayout lyt_cart;
    int Godown_id;
    private Toolbar toolbar;
    String Godown_code,Godown_name;
    DecimalFormat dec = new DecimalFormat("####0.00");
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        setContentView(R.layout.activity_pole);
        SharedPrefsData.saveCartitems(this,myProductsList);
        dialog = new ProgressDialog(this);
        txt_count = findViewById(R.id.txt_count);
        btn_next = findViewById(R.id.btn_next);

        lyt_cart = findViewById(R.id.lyt_cart);
        cartButtonIV = findViewById(R.id.cartButtonIV);
        no_data =findViewById(R.id.no_data);
        ImageView Home_btn = (ImageView) findViewById(R.id.home_btn);
        Home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PoleActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        settoolbar();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Godown_code = extras.getString("code_godown");
            Godown_id= extras.getInt("id_godown");
            Godown_name = extras.getString("name_godown");



        }

        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");       // Saving string data of your editext

        recyclerView = (RecyclerView) findViewById(R.id.fer_recycler_view);
        mealTotalText = (TextView) findViewById(R.id.meal_total);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (isOnline()){
            Getstate();}
        else {
            showDialog(PoleActivity.this,getResources().getString(R.string.Internet));
            btn_next.setBackground(this.getDrawable(R.drawable.button_bg_disable));
            btn_next.setEnabled(false);
        }



        cartButtonIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (myProductsList.size() > 0 & !TextUtils.isEmpty(mealTotalText.getText()) & mealTotalText.getText()!= "" ) {

                        Log.e("myProductsList===",myProductsList.toString());
                        Intent i = new Intent(PoleActivity.this, pole_godown_list.class);
                        i.putExtra("Total_amount", mealTotalText.getText());
                        i.putExtra("godown_id",Godown_id);
                        i.putExtra("godown_code",Godown_code);
                        i.putExtra("godown_name",Godown_name);
                        startActivity(i);
                        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                    }
                    else{
                        showDialog(PoleActivity.this, getResources().getString(R.string.select_product_toast));
                    }
                } catch (Resources.NotFoundException e) {
                    Log.e("error==",e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }

        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (myProductsList.size() > 0 & !TextUtils.isEmpty(mealTotalText.getText()) & mealTotalText.getText()!= "" ) {

                        Log.e("myProductsList===",myProductsList.toString());
                        Intent i = new Intent(PoleActivity.this, pole_godown_list.class);
                        i.putExtra("Total_amount", mealTotalText.getText());
                        i.putExtra("godown_id",Godown_id);
                        i.putExtra("godown_code",Godown_code);
                        i.putExtra("godown_name",Godown_name);
                        startActivity(i);
                        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                    }
                    else{
                        showDialog(PoleActivity.this, getResources().getString(R.string.select_product_toast));
                    }
                } catch (Resources.NotFoundException e) {
                    Log.e("error==",e.getLocalizedMessage());
                    e.printStackTrace();
                }

            }

        });
//
    }

    private void settoolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Select ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void Getstate() {
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        String url = APIConstantURL.LOCAL_URL + "Products/GetProductsByGodown/2/"+ Godown_code;

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "RESPONSE======" + response);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d(TAG, "GetProductsByCategoryId ======" + jsonObject);
                    String success = jsonObject.getString("isSuccess");
                    Log.d(TAG, "success======" + success);

                    if(!jsonObject.getString("listResult").equals("null")) {

                        JSONArray kl = jsonObject.getJSONArray("listResult");
                        Log.d("kl==============", String.valueOf(kl));
                        parseData(kl);
                        recyclerView.setVisibility(View.VISIBLE);
                        no_data.setVisibility(View.GONE);
                        Log.e("no==data==208","No data");

                        // parseData(alsoKnownAsArray);


                        String affectedRecords = jsonObject.getString("affectedRecords");
                        Log.d(TAG, "GetProductsByCategoryId======" + affectedRecords);
                    }else {
                        no_data.setVisibility(View.VISIBLE);
                        lyt_cart.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        Log.d(TAG,"------ analysis ------ "+"Iam NUll");
                    }
                    // JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                    // parseData(alsoKnownAsArray);


                    String affectedRecords = jsonObject.getString("affectedRecords");
                    Log.d(TAG, "GetProductsByCategoryId======" + affectedRecords);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if (error instanceof NetworkError) {
                    Log.i("one:" + TAG, error.toString());

                } else if (error instanceof ServerError) {
                    Log.i("two:" + TAG, error.toString());

                } else if (error instanceof AuthFailureError) {
                    Log.i("three:" + TAG, error.toString());

                } else if (error instanceof ParseError) {
                    Log.i("four::" + TAG, error.toString());

                } else if (error instanceof NoConnectionError) {
                    Log.i("five::" + TAG, error.toString());

                } else if (error instanceof TimeoutError) {
                    Log.i("six::" + TAG, error.toString());

                } else {
                    System.out.println("Checking error in else");
                }
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
    private void parseData(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            ModelFert superHero = new ModelFert();
            JSONObject json = null;

            try {
                json = array.getJSONObject(i);
                superHero.setName(json.getString("name"));
//                superHero.setDiscountedPrice(json.getDouble("actualPrice"));
//                superHero.setmAmount(json.getString("discountedPrice"));
//                superHero.setPrice(json.getInt("price"));

                superHero.setDiscountedPrice(json.getDouble("actualPriceInclGST"));
                superHero.setmAmount(json.getString("discountedPriceInclGST"));
                superHero.setPrice(json.getInt("priceInclGST"));
                superHero.setImageUrl(json.getString("imageUrl"));
                superHero.setDescription(json.getString("description"));

                superHero.setId(json.getInt("id"));

                superHero.setAvail_quantity(json.getInt("availableQuantity"));
                superHero.setProduct_code(json.getString("code"));

                Log.e("uom===", json.getString("uomType"));
                int price_finall = json.getInt("price");
                Log.e("price_final====", String.valueOf(price_finall));
                String final_price = Integer.toString(price_finall);
                Log.e("final_price====", String.valueOf(final_price));
                dis_price = json.getString("discountedPrice");
                Log.e("dis_price====", dis_price);

//                String gst =json.getString("gstPercentage");
//                Log.e("gst====", String.valueOf(gst));
//
//
                String gst =json.getString("gstPercentage");
                Log.e("gst====", String.valueOf(gst));

                if( String.valueOf(gst)!= null) {
                    superHero.setgst(gst);
                }
                String uoms = json.getString("uomType");
                // superHero.setUomType(json.getString("uomType"));

                if (String.valueOf(uoms) != null) {
                    superHero.setUomType(uoms);
                }
                int size = json.getInt("size");
                if (String.valueOf(size) != null){

                    Log.d(TAG, "--- Size ----" + size);
//}
                    superHero.setSize(size);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            product_list.add(superHero);

            adapter = new ModelpoleAdapter(product_list, this, this);
            Log.d(TAG, "listSuperHeroes======" + product_list);

            recyclerView.setAdapter(adapter);

        }
    }
//    @Override
//    public void setOnClickAckListener(String status, int position, Boolean ischecked, NetworkImageView img) {
//    }
    @Override
    public void updated(int po, ArrayList<Product_new> myProducts) {
        SharedPrefsData.saveCartitems(context,myProducts);


        myProductsList = myProducts;
        CommonUtil.Productitems =myProductsList;
        Double allitemscost = 0.0;
        int allproducts = 0;

        for (Product_new product : myProducts) {
            Double oneitem = product.getQuandity() * (product.getAmount());
            allitemscost = oneitem + allitemscost;
            Log.d("Product", "total Proce :" + allitemscost);
            int onitem = product.getQuandity();
            allproducts = allproducts + onitem;
            Log.d("Product", "totalitems :" + allproducts);


        }
        txt_count.setText(allproducts + "");
        total_amount = (int)(allitemscost * 100) / 100;
        Log.e("valueRounded===",total_amount+"");
        mealTotalText.setText(dec.format(total_amount)); }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static ArrayList<Product_new> getProducts(){
        return myProductsList;
    }
}
