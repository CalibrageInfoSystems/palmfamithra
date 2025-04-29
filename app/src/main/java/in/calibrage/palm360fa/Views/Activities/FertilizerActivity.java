package in.calibrage.palm360fa.Views.Activities;

import android.annotation.SuppressLint;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import dmax.dialog.SpotsDialog;
import in.calibrage.palm360fa.Adapter.ModelFertAdapterNew;
import in.calibrage.palm360fa.Model.FertilizerSubCategories;
import in.calibrage.palm360fa.Model.ModelFert;
import in.calibrage.palm360fa.Model.SelectedProducts;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.BaseActivity;
import in.calibrage.palm360fa.common.CommonUtil;
import in.calibrage.palm360fa.localData.SharedPrefsData;
import in.calibrage.palm360fa.service.APIConstantURL;
import in.calibrage.palm360fa.service.ApiService;
import in.calibrage.palm360fa.service.ServiceFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static in.calibrage.palm360fa.common.CommonUtil.updateResources;

public class FertilizerActivity extends BaseActivity implements  ModelFertAdapterNew.listner  {
    private RecyclerView recyclerView;
    private ModelFertAdapterNew adapter;
    Double total_amount,Transport_amount;
    String amount;
    static ArrayList<SelectedProducts> myProductsList = new ArrayList<>();
    String dis_price, Farmer_code;
    final Context context = this;
    Button button, btn_next;
    TextView mealTotalText, txt_recomandations, txt_count,no_data;
    private String TAG = "FertilizerActivity";
    private List<ModelFert> product_list = new ArrayList<>();
    private ProgressDialog dialog;
    int SPLASH_DISPLAY_DURATION = 500;
    private Toolbar toolbar;
    private ImageView cartButtonIV;
    Integer Id, quantity;
    int price_final;
    int Count=0;
    int Godown_id;
    LinearLayout lyt_cart;
    String Godown_code,Godown_name;
    Spinner categorySpinner;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    DecimalFormat dec = new DecimalFormat("####0.00");
    int categoryid;
    //TODO
    FertilizerSubCategories categoryTypes;
    List<String> listdata = new ArrayList<>();
    List<Integer> category_id = new ArrayList<Integer>();

    String seleced_category;
    //code_godown
    @SuppressLint("MissingInflatedId")
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
        setContentView(R.layout.activity_fertilizer);
        dialog = new ProgressDialog(this);
        txt_recomandations = findViewById(R.id.txt_recomandations);
        txt_count = findViewById(R.id.txt_count);
        btn_next = findViewById(R.id.btn_next);
        no_data =findViewById(R.id.no_data);
        lyt_cart =findViewById(R.id.lyt_cart);
        categorySpinner =  findViewById(R.id.categorySpinner);
        cartButtonIV = findViewById(R.id.cartButtonIV);

        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        settoolbar();
        saveEmptyCartItems();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Godown_code = extras.getString("code_godown");
            Log.e("Godown_code===",Godown_code);
            Godown_id= extras.getInt("id_godown");
            Godown_name = extras.getString("name_godown");



        }

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                product_list.clear();
                seleced_category = categorySpinner.getItemAtPosition(categorySpinner.getSelectedItemPosition()).toString();
                Log.e("seleced_category==", seleced_category);
                if (!seleced_category.equalsIgnoreCase("Select")) {
                    categoryid = category_id.get(categorySpinner.getSelectedItemPosition() - 1);
                    Log.d("categoryid", categoryid + "");

                    // Check if there are items in the cart
                    if (txt_count.getText().toString().equals("0")) {
                        // If cart is empty, fetch products for the selected category
                        Getstate(categoryid);
                    } else {
                        // Cart is not empty, save cart data and fetch products for the selected category
                        saveCartData();
                        Getstate(categoryid);
                    }
                } else {
                    // "Select" category chosen, fetch all products
                    GetAllproducts();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing here
            }
        });

        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "").trim();       // Saving string data of your editext

        recyclerView = (RecyclerView) findViewById(R.id.fer_recycler_view);
        mealTotalText = (TextView) findViewById(R.id.meal_total);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (isOnline()){
            getgcategorydata();
        }
        else {
            showDialog(FertilizerActivity.this,getResources().getString(R.string.Internet));
            btn_next.setBackground(this.getDrawable(R.drawable.button_bg_disable));
            btn_next.setEnabled(false);
            //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
        }
        // Getstate();
        cartButtonIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (myProductsList.size() > 0 & !TextUtils.isEmpty(mealTotalText.getText()) & mealTotalText.getText()!= "" ) {

                        Intent i = new Intent(FertilizerActivity.this, Fert_godown_list.class);
                        i.putExtra("Total_amount", mealTotalText.getText());
                        i.putExtra("Transport_amount",dec.format(Transport_amount) );
                        i.putExtra("godown_id",Godown_id);
                        i.putExtra("godown_code",Godown_code);
                        i.putExtra("godown_name",Godown_name);

                        startActivity(i);
                        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                    }
                    else{
                        showDialog(FertilizerActivity.this, getResources().getString(R.string.select_product_toast));
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

                        Intent i = new Intent(FertilizerActivity.this, Fert_godown_list.class);
                        i.putExtra("Total_amount", mealTotalText.getText());
                        i.putExtra("Transport_amount",dec.format(Transport_amount) );
                        i.putExtra("godown_id",Godown_id);
                        i.putExtra("godown_code",Godown_code);
                        i.putExtra("godown_name",Godown_name);

                        startActivity(i);
                        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                    }
                    else{
                        showDialog(FertilizerActivity.this, getResources().getString(R.string.select_product_toast));
                    }
                } catch (Resources.NotFoundException e) {
                    Log.e("error==",e.getLocalizedMessage());
                    e.printStackTrace();
                }

            }

        });
        txt_recomandations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FertilizerActivity.this, RecommendationActivity.class));
            }
        });
    }

    private void saveCartData() {
        // Save the current state of the cart data (e.g., to SharedPreferences or a database)
        List<SelectedProducts> cartData = new ArrayList<>(myProductsList);
        SharedPrefsData.saveFertCartitems(context, (ArrayList<SelectedProducts>) cartData);
    }


    private void GetAllproducts() {
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        String url = APIConstantURL.LOCAL_URL + "Products/GetProductsByGodown/1/"+ Godown_code;
        Log.e("url==Godown_code===",url);
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
                        //Log.d("kl==============", seleced_category);
                        parseData(kl);
                        if (myProductsList.size() > 0 & !TextUtils.isEmpty(mealTotalText.getText()) & mealTotalText.getText()!= "" ) {
                            updateCartQuantities();
                        }
                        else {
                            displayProductList(product_list);
                        }
                        recyclerView.setVisibility(View.VISIBLE);
                        no_data.setVisibility(View.GONE);
                        Log.e("no==data==208","No data");



                        String affectedRecords = jsonObject.getString("affectedRecords");
                        Log.d(TAG, "GetProductsByCategoryId======" + affectedRecords);
                    }else {
                        no_data.setVisibility(View.VISIBLE);
                        lyt_cart.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        Log.d(TAG,"------ analysis ------ "+"Iam NUll");
                    }


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


    private void getgcategorydata() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getfertilizercategory(APIConstantURL.GetFertilizerSubCategories) //static 1
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FertilizerSubCategories>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.cancel();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mdilogue.cancel();
                        showDialog(FertilizerActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(FertilizerSubCategories fertilizerSubCategories) {


                        categoryTypes = fertilizerSubCategories;
                        mdilogue.cancel();
                        if (fertilizerSubCategories.getListResult() != null) {
                            listdata.add("Select");
                            for (FertilizerSubCategories.SubCategory string : fertilizerSubCategories.getListResult()
                            ) {
                                listdata.add(string.getName());
                                category_id.add(string.getCategoryId());
                            }


                            Log.d(TAG, "RESPONSE======" + listdata);

//

                            ArrayAdapter aa = new ArrayAdapter(FertilizerActivity.this, R.layout.spinner_itemm, listdata);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            categorySpinner.setAdapter(aa);

                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }


                    }
                });
    }

    private void settoolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Select Godown");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveEmptyCartItems();
                finish();
            }
        });
    }

    private void saveEmptyCartItems() {
        // Create an empty list of SelectedProducts
        ArrayList<SelectedProducts> emptyList = new ArrayList<>();

        // Save the empty list to SharedPreferences to clear the cart items
        SharedPrefsData.saveFertCartitems(context, emptyList);

        // Update myProductsList and CommonUtil.FertProductitems
        myProductsList = emptyList;
        CommonUtil.FertProductitems = emptyList;
    }



    private void Getstate(final int categoryid) {
        product_list.clear();
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        String url = APIConstantURL.LOCAL_URL + "Products/GetProductsByGodown/1/"+ Godown_code;
        Log.e("url==Godown_code===",url);
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
                    Log.d(TAG, "success======" + jsonObject.getString("listResult"));
                    if(!jsonObject.getString("listResult").equals("null")) {

                        JSONArray kl = jsonObject.getJSONArray("listResult");
                        Log.d("categoryid=======", categoryid+"");
                        parseData(kl,categoryid);

//
                        Log.d("productsize=======", txt_count.getText().toString()+"");
                        if (myProductsList.size() > 0 & !TextUtils.isEmpty(mealTotalText.getText()) & mealTotalText.getText()!= "" ) {
                            updateCartQuantities();
                        }
                        else {
                            displayProductList(product_list);
                        }

                        String affectedRecords = jsonObject.getString("affectedRecords");
                        Log.d(TAG, "GetProductsByCategoryId======" + affectedRecords);
                    }else {
                        no_data.setVisibility(View.VISIBLE);
                        lyt_cart.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        Log.d(TAG,"------ analysis ------ "+"Iam NUll");
                    }


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

    // Method to compare and update quantities of products in the cart

    // Update the cart quantities and update the RecyclerView
    private void updateCartQuantities() {
        for (ModelFert product : product_list) {
            for (SelectedProducts cartProduct : myProductsList) {
                //    Log.e("====>",product.getProduct_code());
                Log.e("====>cart",cartProduct.getProduct_code());
                if (product.getProduct_code() != null && cartProduct.getProduct_code() != null) {
//                    if (product.getProduct_code().equals(cartProduct.getProduct_code())) {
//                        product.setmQuantity(cartProduct.getQuandity());
//                        break;
//                    }
                    if (product.getId() == cartProduct.getProductID()) {
                        product.setmQuantity(cartProduct.getQuandity());
                        break;
                    }
                }
            }
        }
        adapter.updateDataset(product_list); // Update the adapter with the modified product list
    }

    private void displayProductList(List<ModelFert> productList) {
        adapter = new ModelFertAdapterNew(productList, this, this);
        recyclerView.setAdapter(adapter);
    }

    private void parseData(JSONArray array, int categoryId) {
        product_list.clear();
        Log.d(TAG, "product_list======categoryId" + array.length());
        Log.d(TAG, "categoryId===?" + categoryId+"");
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject json = array.getJSONObject(i);
                Log.d(TAG, "product_list======categoryId1" +json.getInt("categoryId"));
                // Check if the categoryId matches
                if (json.has("categoryId") && json.getInt("categoryId") == categoryId) {
                    Log.d(TAG, "product_list======categoryId2" +categoryId);
                    Log.d(TAG, "Adding item with categoryId: " + categoryId + ", Name: " + json.getString("name"));

                    ModelFert Fertdetails = new ModelFert();
                    Fertdetails.setName(json.getString("name"));
                    Fertdetails.setDiscountedPrice(json.getDouble("actualPriceInclGST"));
                    Fertdetails.setmAmount(json.getString("discountedPriceInclGST"));
                    Fertdetails.setPrice(json.getInt("priceInclGST"));
                    Fertdetails.setImageUrl(json.getString("imageUrl"));
                    Fertdetails.setDescription(json.getString("description"));
                    Fertdetails.setTransPortActualPriceInclGST(json.getDouble("transPortActualPriceInclGST"));
                    Fertdetails.setTransportGSTPercentage(json.getDouble("transportGSTPercentage"));
                    double size = json.getDouble("size");
                    Log.d(TAG, "--- Size ----" + size);
                    Fertdetails.setSize(size);
                    Fertdetails.setId(json.getInt("id"));
                    Fertdetails.setUomType(json.getString("uomType"));
                    if (!json.isNull("availableQuantity")) {
                        Fertdetails.setAvail_quantity(json.getInt("availableQuantity"));
                    } else {
                        Fertdetails.setAvail_quantity(0);
                    }
                    Fertdetails.setProduct_code(json.getString("code"));
                    Log.e("uom===", json.getString("uomType"));
                    int price_finall = json.getInt("price");
                    Log.e("price_final====", String.valueOf(price_finall));
                    String final_price = Integer.toString(price_finall);
                    Log.e("final_price====", String.valueOf(final_price));
                    dis_price = json.getString("discountedPrice");
                    Log.e("dis_price====", dis_price);
                    String gst = json.getString("gstPercentage");
                    Log.e("gst====", String.valueOf(gst));
                    if (String.valueOf(gst) != null) {
                        Fertdetails.setgst(gst);
                    }
                    // Add data to product_list only if categoryId matches
                    product_list.add(Fertdetails);
                } else {
                    Log.d(TAG, "Skipping item with categoryId: " + json.getInt("categoryId") + ", Name: " + json.getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, "product_list: " + product_list.size());
        if ( product_list.size() == 0) {
            // Display a message indicating that no data is available
            no_data.setVisibility(View.VISIBLE);

            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
            Log.e("no==data==208","No data");
            adapter = new ModelFertAdapterNew(product_list, this, this);
            recyclerView.setAdapter(adapter);
        }
    }




    private void parseData(JSONArray array) {
        Log.d(TAG, "product_list======All" + array.length());
        for (int i = 0; i < array.length(); i++) {

            ModelFert Fertdetails = new ModelFert();
            JSONObject json = null;

            try {
                json = array.getJSONObject(i);
                Fertdetails.setName(json.getString("name"));
                Fertdetails.setDiscountedPrice(json.getDouble("actualPriceInclGST"));
                Fertdetails.setmAmount(json.getString("discountedPriceInclGST"));
                Fertdetails.setPrice(json.getInt("priceInclGST"));
                Fertdetails.setImageUrl(json.getString("imageUrl"));
                Fertdetails.setDescription(json.getString("description"));
                Fertdetails.setTransPortActualPriceInclGST(json.getDouble("transPortActualPriceInclGST"));
                Fertdetails.setTransportGSTPercentage(json.getDouble("transportGSTPercentage"));
                double size = json.getDouble("size");
                //   Log.d(TAG, "--- Size ----" + size);
                Fertdetails.setSize(size);
                Fertdetails.setId(json.getInt("id"));
                Fertdetails.setUomType(json.getString("uomType"));
                if (!json.isNull("availableQuantity")) {
                    Fertdetails.setAvail_quantity(json.getInt("availableQuantity"));
                } else {
                    Fertdetails.setAvail_quantity(0);
                }

                Fertdetails.setProduct_code(json.getString("code"));
                Log.e("uom===", json.getString("uomType"));
                int price_finall = json.getInt("price");
                Log.e("price_final====", String.valueOf(price_finall));
                String final_price = Integer.toString(price_finall);
                Log.e("final_price====", String.valueOf(final_price));
                dis_price = json.getString("discountedPrice");
                Log.e("dis_price====", dis_price);
                String gst =json.getString("gstPercentage");
                Log.e("gst====", String.valueOf(gst));

                if( String.valueOf(gst)!= null) {
                    Fertdetails.setgst(gst);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            product_list.add(Fertdetails);

            adapter = new ModelFertAdapterNew(product_list, this, this);


            recyclerView.setAdapter(adapter);

        }
    }

    @Override
    public void updated(int po, ArrayList<SelectedProducts> myProducts) {
        SharedPrefsData.saveFertCartitems(context, myProducts);
        myProductsList = myProducts;
        CommonUtil.FertProductitems = myProductsList;
        Double allitemscost = 0.0;
        Double totaltransportcost = 0.0;
        int allproducts = 0;

        // Convert SelectedProducts to ModelFert
        List<ModelFert> convertedProducts = new ArrayList<>();
        for (SelectedProducts selectedProduct : myProducts) {
            ModelFert modelFert = new ModelFert();
            // Populate modelFert with relevant data from selectedProduct
            // Assuming you have a method to convert SelectedProducts to ModelFert
            // modelFert = convertSelectedProductToModelFert(selectedProduct);
            convertedProducts.add(modelFert);

            Double oneitem = selectedProduct.getQuandity() * (selectedProduct.getWithGSTamount());
            allitemscost = oneitem + allitemscost;
            Log.d("Product", "total Proce :" + allitemscost);
            int onitem = selectedProduct.getQuandity();
            allproducts = allproducts + onitem;
            Log.d("Product", "totalitems :" + allproducts);
            Double onetransfortitem = selectedProduct.getQuandity() * (selectedProduct.getTranportPrice());
            totaltransportcost = onetransfortitem + totaltransportcost;
        }
        txt_count.setText(allproducts + "");
        total_amount = (allitemscost * 100) / 100;
        Transport_amount = (totaltransportcost * 100) / 100;

        mealTotalText.setText(dec.format(total_amount));

        // Update the quantities of products in the RecyclerView
        adapter.updateDataset(convertedProducts);
    }

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

    @Override
    public void onBackPressed() {
        // Save an empty list to clear the cart items
        saveEmptyCartItems();

        // Call super to handle back button press as usual
        super.onBackPressed();
    }




}




