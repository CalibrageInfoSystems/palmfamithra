package in.calibrage.palm360fa.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import in.calibrage.palm360fa.Model.ModelFert;
import in.calibrage.palm360fa.Model.SelectedProducts;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.CommonUtil;
import in.calibrage.palm360fa.common.CustomVolleyRequest;
import in.calibrage.palm360fa.localData.SharedPrefsData;


public class ModelFertAdapterNew extends RecyclerView.Adapter<ModelFertAdapterNew.ViewHolder> {

    private ArrayList<SelectedProducts> myProducts = new ArrayList<>();
    private ImageLoader imageLoader;
    public Context mContext;
    PopupWindow popUp;
    LinearLayout layout;
    TextView  Product_Name,product_price, discountprice,productsize,gst_price,cancel,instock;
    Double discount_cost, itemcost;
    WindowManager.LayoutParams params;
    LinearLayout mainLayout;
    Button but;
    boolean click = true;
    LinearLayout des_linear;



    //List of superHeroes
    List<ModelFert> list_products = new ArrayList<>();
    LayoutInflater mInflater;
    public ImageView new_image;
    private OnClickAck onClickAck1;
    String Description, ProductName,image_url,product_size,Product_uom ,final_amount;
    double onlygst, gst,discountgst ,discount_price,price;
    String gstprice;
    int Available_quantity;
    private listner listner;

    public ModelFertAdapterNew(List<ModelFert> list_products, Context context, listner listner) {
        super();
        //Getting all the superheroes
        this.list_products = list_products;
        this.mContext = context;
        this.listner = listner;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fert_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final ModelFert superHero = list_products.get(position);

        imageLoader = CustomVolleyRequest.getInstance(mContext).getImageLoader();

//        imageLoader.get(superHero.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        holder.imageView2.setImageUrl(superHero.getImageUrl(), imageLoader);
        // holder.imageView.setImageUrl(superHero.getImageUrl(), imageLoader);

        Picasso.with(mContext )
                .load(superHero.getImageUrl())
                .error(R.drawable.ic_applogo )
                .placeholder( R.drawable.progress_animation)
                .into(holder.imageView);

        holder.currentFoodName.setText(superHero.getName());

        if (superHero.getmAmount().equals("null")) {
            itemcost = Double.valueOf(superHero.getPrice());

        } else {
            itemcost = Double.valueOf(superHero.getmAmount());
        }
        if (superHero.getgst() != null && !superHero.getgst().equals("null") && !superHero.getgst().isEmpty()) {
            gst = Double.valueOf(superHero.getgst().trim());
            Log.d("PRODUCT ", "---- analysis -----(gst)  :" + gst);
            onlygst = (itemcost / 100.0f) * gst;
        } else {
            onlygst = 0.00;
        }

//        if (Double.valueOf(superHero.getgst()) != null && !superHero.getgst().equals("null") && superHero.getgst()!= null) {
//            gst = Double.valueOf(superHero.getgst());
//            Log.d("PRODUCT ", "---- analysis -----(gst)  :" + gst);
//            //Double onlygst = (gst / itemcost) * 100;
//            onlygst = (itemcost / 100.0f) * gst;
//        } else {
//            onlygst = 0.00;
//        }

        holder.quantityText.setText("" +superHero.getmQuantity());
        Log.d("PRODUCT ", "---- analysis -----(withgstitemcost)  :" + onlygst);
        Double finalwithGST = itemcost + onlygst;

        DecimalFormat df = new DecimalFormat("####0.00");
        //   String itemcost= df.format(itemcostt);

        String total_amount = df.format(finalwithGST);

        holder.currentCost.setText(mContext.getString(R.string.Rs) +df.format(itemcost));

        //  holder.disc.setText(superHero.getDescription());
        if (!TextUtils.isEmpty(superHero.getSize() +"")) {
            holder.size.setText(superHero.getSize() + " " + superHero.getUomType());
        } else {
            holder.size.setText("N/A");
        }
        if(superHero.getmQuantity() < superHero.getAvail_quantity()) {
            holder.quantityText.setText("" + superHero.getmQuantity());
            // holder.addMeal.setEnabled(true);

        }


        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Description = superHero.getDescription();
                image_url=superHero.getImageUrl();
                price =superHero.getPrice();
                ProductName = superHero.getName();
                discount_price=superHero.getDiscountedPrice();
                product_size=superHero.getSize()+"";
                Product_uom =superHero.getUomType();
                gstprice=superHero.getgst();
                final_amount =superHero.getmAmount();

                Available_quantity =superHero.getAvail_quantity();
                Log.e("Description==160",  discount_price +"   price"+price +"");
                displayPopupWindow(view);
            }
        });
        holder.disc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Description = superHero.getDescription();
                image_url=superHero.getImageUrl();
                price =superHero.getPrice();
                ProductName = superHero.getName();
                discount_price=superHero.getDiscountedPrice();
                product_size=superHero.getSize()+"";
                Product_uom =superHero.getUomType();
                gstprice=superHero.getgst();
                final_amount =superHero.getmAmount();

                Available_quantity =superHero.getAvail_quantity();
                Log.e("Description==160",  discount_price +"   price"+price +"");
                displayPopupWindow(view);
            }
        });
        holder.currentFoodName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductName = superHero.getName();
                //   displayPopupWindow2(view);
            }
        });


        discount_cost =superHero.getDiscountedPrice();
        Log.e("discount_cost==",discount_cost+"");
        discountgst = (discount_cost / 100.0f) * gst;
        Double finaldiscount= discount_cost + discountgst;

        holder.actual_amt.setText(mContext.getString(R.string.Rs) + df.format(discount_cost));
        Log.e("cost===191", superHero.getmAmount()+"===============110"+superHero.getDiscountedPrice());
        if (superHero.getmAmount().equals("null") || superHero.getmAmount().equalsIgnoreCase(superHero.getDiscountedPrice()+"") ) {
            Log.e("cost===193", superHero.getmAmount()+"===============110"+superHero.getDiscountedPrice());
            holder.actual_amt.setVisibility(View.INVISIBLE);
        } else {
            Log.e("cost===196", superHero.getmAmount()+"===============110"+superHero.getDiscountedPrice());
            holder.actual_amt.setVisibility(View.VISIBLE);
            holder.actual_amt.setPaintFlags(holder.actual_amt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
//        if (superHero.getmAmount().equals(superHero.getDiscountedPrice())) {
//            holder.actual_amt.setVisibility(View.INVISIBLE);
//        } else {
//            holder.actual_amt.setVisibility(View.VISIBLE);
//            holder.actual_amt.setPaintFlags(holder.actual_amt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//        }

        String powers = "";





        holder.addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModelFert product = list_products.get(position);
                if (CommonUtil.FertProductitems != null) {
                    myProducts = CommonUtil.FertProductitems;
                }
                if (myProducts != null && superHero != null) {
                    if (contains(myProducts, superHero.getId())) {
                        for (int i = 0; i < myProducts.size(); i++) {
                            if (myProducts.get(i).getProductID() == superHero.getId()) {
                                SelectedProducts product_new = myProducts.get(i);
                                Integer currentQTY = product_new.getQuandity();
                                if (currentQTY != null) {
                                    // Increment the current quantity by 1
                                    product_new.setQuandity(currentQTY + 1);
                                } else {
                                    // If current quantity is null, set it to 1
                                    product_new.setQuandity(1);
                                }
                                myProducts.set(i, product_new);
                                Log.d("PRODUCT ", "---- analysis -----(Update new)  " + product_new.getQuandity());
                                superHero.setmQuantity(product_new.getQuandity());
                                notifyItemChanged(position);
                                break; // Exit the loop once the item is found and updated
                            }
                        }
                    } else {
                        // Add the item with a quantity of 1
                        if (superHero.getmAmount().equals("null")) {
                            itemcost = Double.valueOf(superHero.getPrice());
                        } else {
                            itemcost = Double.valueOf(superHero.getmAmount());
                        }
                        Log.d("PRODUCT ", "---- analysis -----(itemcost)  :" + itemcost);
                        Double gst = Double.valueOf(superHero.getgst());
                        Log.d("PRODUCT ", "---- analysis -----(gst)  :" + gst);
                        double onlygst = (itemcost / 100.0f) * gst;
                        Log.d("PRODUCT ", "---- analysis -----(withgstitemcost)  :" + onlygst);
                        Double finalwithGST = itemcost + onlygst;
                        DecimalFormat df = new DecimalFormat("####0.00");
                        double Gst = Double.parseDouble(superHero.getgst());
                        double total_amount = Double.parseDouble(df.format(finalwithGST));
                        Log.d("PRODUCT ", "---- analysis -----  " + total_amount);

                        myProducts.add(new SelectedProducts(1, superHero.getName(),
                                itemcost, itemcost, Gst, itemcost, superHero.getSize(),
                                superHero.getProduct_code(),superHero.getId(), superHero.getTransPortActualPriceInclGST(), superHero.getTransportGSTPercentage()));
                        Log.d("PRODUCT ", "---- analysis -----(Add new)  ");
                        superHero.setmQuantity(1);
                        notifyItemChanged(position);
                    }
                    caliculateTotalAmount();
                    listner.updated(position, myProducts);
                } else {
                    Log.e("ERROR", "myProducts or superHero is null");
                }
            }
        });

        holder.subtractMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Roja====,", "clicked----");
                if (CommonUtil.FertProductitems != null) {
                    myProducts = CommonUtil.FertProductitems;
                }
                if (contains(myProducts, superHero.getId())) {
                    for (int i = 0; i < myProducts.size(); i++) {
                        if (myProducts.get(i).getProductID() == (superHero.getId())) {
                            SelectedProducts product_new = myProducts.get(i);
                            if (product_new.getQuandity() > 1) {
                                Integer currentQTY = product_new.getQuandity();
                                product_new.setQuandity(currentQTY - 1);
                                myProducts.set(i, product_new);
                                superHero.setmQuantity(product_new.getQuandity());

                                notifyItemChanged(position);
                            } else {
                                myProducts.remove(i);
                                superHero.setmQuantity(0);
                                notifyItemChanged(position);
                            }

                            Log.d("PRODUCT ", "---- analysis -----(Remove)  " + product_new.getQuandity());
                        }
                    }

                }


                caliculateTotalAmount();
                listner.updated(position, myProducts);

            }
        });

//        for (int i = 0; i < SharedPrefsData.getFertCartData().size(); i++) {
//            double gst = SharedPrefsData.getFertCartData(this).get(i).getGst();
//            double tgst = SharedPrefsData.getFertCartData(this).get(i).getTransgst();
//            Double amount_product = SharedPrefsData.getFertCartData(this).get(i).getAmount();
//            int quantity = SharedPrefsData.getFertCartData(this).get(i).getQuandity();
//            Double transportamountbyproduct = SharedPrefsData.getFertCartData(this).get(i).getTranportPrice();
//            double totalPrice =quantity * amount_product;
//            Log.e("totalPrice===",totalPrice+"");
//            double gstPrice = totalPrice - totalPrice / (1 + (gst/ 100));
//            Log.e("gstPrice===",gstPrice+"");
//            double BasePrice = totalPrice - gstPrice;
//            Log.e("BasePrice===",BasePrice+"");
//
//
//
//
//        }

    }
    public void showDialog(Context context, String msg) {
        final Dialog dialog = new Dialog(context, R.style.DialogSlideAnim);
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
    private void displayPopupWindow(View anchorView) {
        //  View container;
        Log.e("Description==252", Description + "price=="+ price + discount_price +product_size + gstprice);
        //    final PopupWindow popup = new PopupWindow(context);

        final PopupWindow   popup = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);



        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_content, null);
        TextView text = layout.findViewById(R.id.product_description);
        new_image =layout.findViewById(R.id.product_image);
        Product_Name =layout.findViewById(R.id.product_title);
        des_linear=layout.findViewById(R.id.des_linear);
        product_price=layout.findViewById(R.id.product_price);
        instock =layout.findViewById(R.id.instock);
        discountprice =layout.findViewById(R.id.discount_price);
        productsize=layout.findViewById(R.id.product_size);
        cancel=layout.findViewById(R.id.cancel);
        gst_price=layout.findViewById(R.id.gst_price);
        if(Description != null && !Description .isEmpty()){
            des_linear.setVisibility(View.VISIBLE);
            Log.e("Description====336",Description);
        }
        else {
            Log.e("Description====339",Description);
            des_linear.setVisibility(View.GONE);
        }
        text.setText(Description);
        Picasso.with(mContext).load(image_url).error(R.drawable.ic_user).placeholder( R.drawable.progress_animation).into(new_image);

        new_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context  context=mContext.getApplicationContext();
                mInflater = LayoutInflater.from(context);
                androidx.appcompat.app.AlertDialog.Builder mBuilder = new androidx.appcompat.app.AlertDialog.Builder(mContext);
                View mView =mInflater.inflate(R.layout.dialog_custom_layout, null);
                TextView cancel =mView.findViewById(R.id.cancel);
                //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                Picasso.with(mContext).load(image_url).error(R.drawable.ic_user).placeholder( R.drawable.progress_animation).into(photoView);
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
        DecimalFormat df = new DecimalFormat("####0.00");

        // double total_amount = Double.parseDouble(df.format(finalwithGST));
        //  imageLoader.get(image_url, ImageLoader.getImageListener(new_image, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        Product_Name.setText(": "+ProductName);
        product_price.setText("  "+df.format(Math.round(discount_price)));
        discountprice.setText(": "+df.format(Math.round(price)));
        productsize.setText(": "+product_size + " "+ Product_uom);
        gst_price.setText(": "+gstprice);
        instock.setText(": "+Available_quantity);
        Log.e("finalamount ",final_amount);
        if(final_amount.equalsIgnoreCase("null") || final_amount.equalsIgnoreCase(discount_price +"")){
            Log.e("finalamount ",final_amount);
            Log.e("price====381",product_price.getText()+"====="+discountprice.getText() );
            product_price.setVisibility(View.GONE);
        }
        else {
            Log.e("price====385",product_price.getText()+"====="+discountprice.getText() );
            product_price.setVisibility(View.VISIBLE);
            product_price.setPaintFlags(product_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        // holder.actual_amt.setText(mContext.getString(R.string.Rs) + finaldiscount);
        Log.e("Description==273", Description + "price=="+ price + discount_price +product_size + gstprice);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        popup.setOutsideTouchable(false);

        View container = (View) popup.getContentView().getParent();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.3f;
        wm.updateViewLayout(container, p);
//

        // Closes the popup window when touch outside of it - when looses focus
//        popup.setOutsideTouchable(true);
        popup.setFocusable(true);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });

        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView);

    }

    @Override
    public int getItemCount() {
        return list_products.size();
    }

    public void updateDataset(List<ModelFert> product_list) {
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView; NetworkImageView imageView2;
        public TextView currentFoodName,
                currentCost,
                quantityText,
                actual_amt,

        remove_text;
        CardView card_view;
        public ImageView addMeal, subtractMeal;
        public ImageView thumbnail;
        public TextView disc, size;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
            imageView2 = (NetworkImageView) itemView.findViewById(R.id.thumbnail2);
            currentFoodName = (TextView) itemView.findViewById(R.id.selected_food_name);
            currentCost = (TextView) itemView.findViewById(R.id.selected_food_amount);
            subtractMeal = (ImageView) itemView.findViewById(R.id.minus_meal);
            quantityText = (TextView) itemView.findViewById(R.id.quantity);
            addMeal = (ImageView) itemView.findViewById(R.id.plus_meal);
            //  remove_text = (TextView) itemView.findViewById(R.id.ggd);
            actual_amt = (TextView) itemView.findViewById(R.id.actual_amt);
            disc = (TextView) itemView.findViewById(
                    R.id.desc);
            size = (TextView) itemView.findViewById(
                    R.id.size);
            card_view =(CardView) itemView.findViewById(
                    R.id.card_view);
        }
    }

    public void setOnListener(OnClickAck OListener) {
        this.onClickAck1 = OListener;
    }

    public interface OnClickAck {
        void setOnClickAckListener(String status, int position, Boolean ischecked, NetworkImageView img);
    }


    public void caliculateTotalAmount() {
        Double allitemscost = 0.0;
        int allproducts = 0;
        for (SelectedProducts product : myProducts) {
            Double oneitem = product.getQuandity() * (product.getWithGSTamount());
            allitemscost = oneitem + allitemscost;
            Log.d("Product", "total Proce :" + allitemscost);
            int onitem = product.getQuandity();
            allproducts = allproducts + onitem;
            Log.d("Product", "totalitems :" + allproducts);
        }


        SharedPrefsData.getInstance(mContext).updateStringValue(mContext, "amount", allitemscost + "");
    }

    boolean contains(ArrayList<SelectedProducts> list, int name) {
        for (SelectedProducts item : list) {
            if (item.getProductID() == name) {
                return true;
            }
        }
        return false;
    }

    public interface listner {
        void updated(int po, ArrayList<SelectedProducts> myProducts);
    }

}