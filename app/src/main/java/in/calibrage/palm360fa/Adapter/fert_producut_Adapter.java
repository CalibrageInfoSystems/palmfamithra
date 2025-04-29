package in.calibrage.palm360fa.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import in.calibrage.palm360fa.Model.SelectedProducts;
import in.calibrage.palm360fa.R;


public class fert_producut_Adapter extends RecyclerView.Adapter<fert_producut_Adapter.MyViewHolder> {
    private Context context;
    private ArrayList<SelectedProducts> product_Listitems = new ArrayList<>();
    int  valueRounded;
    double cgst;
    double totalAmount;
    float originalWeightSum;
    double GST;
    DecimalFormat dec = new DecimalFormat("####0.00");
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView product_name, quantity, amount, gst, item_cost,cgst,TranportPrice,Transgst,TotalTranportPrice,TotalAmount;
        CardView card_view;
        LinearLayout transportlayout,transtotallinear;
        public MyViewHolder(View view) {
            super(view);
            product_name = view.findViewById(R.id.name);
            quantity = view.findViewById(R.id.qun_tity);
            amount = view.findViewById(R.id.Value);
            TranportPrice = view.findViewById(R.id.TranportPrice);
            cgst =view.findViewById(R.id.cgst);
            Transgst =view.findViewById(R.id.Transgst);
            item_cost = view.findViewById(R.id.item_cost);
            TotalTranportPrice= view.findViewById(R.id.TotalTranportPrice);
            TotalAmount= view.findViewById(R.id.totalamount);
            transportlayout = view.findViewById(R.id.transportlayout);
            transtotallinear = view.findViewById(R.id.transtotallinear);
            card_view = view.findViewById(R.id.card_view);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }


    public fert_producut_Adapter(Context context, ArrayList<SelectedProducts> myProductsList) {
        this.context = context;

        this.product_Listitems = myProductsList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_fert, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.card_view.setBackgroundResource(R.drawable.button_bg2);
        final SelectedProducts dataa = product_Listitems.get(position);
        holder.product_name.setText(dataa.getProductname());
        holder.quantity.setText(dataa.getQuandity() + "");
        Double amountt =(dataa.getWithGSTamount()) * dataa.getQuandity() ;
        Double  transamount =(dataa.getTranportPrice()) * dataa.getQuandity() ;

        valueRounded = (int)(amountt * 100) / 100;
        holder.amount.setText(dec.format(amountt));
      // holder.gst.setText(dataa.getGst()+"");

        holder.item_cost.setText(dec.format(dataa.getAmount()));
        if(dataa.getTranportPrice()!=0.0){
            holder.transportlayout.setVisibility(View.VISIBLE);
           holder.transtotallinear.setVisibility(View.VISIBLE);
        holder.TranportPrice.setText(dataa.getTranportPrice() + "");
            holder.Transgst.setText(dataa.getTransgst() + "");
             transamount =(dataa.getTranportPrice()) * dataa.getQuandity() ;
            holder.TotalTranportPrice.setText(dec.format(transamount) + "");

        }
        else{
            holder.transportlayout.setVisibility(View.GONE);
          holder.transtotallinear.setVisibility(View.GONE);
        }

        if(" "+dataa.getGst()!=null){
             GST = dataa.getGst();
            holder.cgst.setText(GST+"");
            cgst =GST/2;
        }

        totalAmount = transamount+amountt;
        holder.TotalAmount.setText(dec.format(totalAmount) + "");

    }


    @Override
    public int getItemCount() {
        if (product_Listitems != null)
            return product_Listitems.size();
        else
            return 0;
    }


}

