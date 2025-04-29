package in.calibrage.palm360fa.Adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import in.calibrage.palm360fa.Model.Resproduct;
import in.calibrage.palm360fa.R;


public class Req_producut_Adapter extends RecyclerView.Adapter<Req_producut_Adapter.MyViewHolder> {
    private Context context;

    private List<Resproduct.ListResult> product_Listitems = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("####0.00");
    String holiday_id, name;
    String Enduser, IsSuccess;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView product_name, quantity, amount, gst, item_cost,cgst,sgst;
        CardView card_view;

        public MyViewHolder(View view) {
            super(view);
            product_name = view.findViewById(R.id.name);
            quantity = view.findViewById(R.id.qun_tity);
            amount = view.findViewById(R.id.Value);
            // gst = view.findViewById(R.id.per_gst);
            item_cost = view.findViewById(R.id.item_cost);
            card_view = view.findViewById(R.id.card_view);
            cgst =view.findViewById(R.id.cgst);
            // sgst =view.findViewById(R.id.sgst);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }


    public Req_producut_Adapter(Context context, List<Resproduct.ListResult> product_Listitems) {
        this.context = context;

        this.product_Listitems = product_Listitems;

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

        holder.product_name.setText(product_Listitems.get(position).getName());
        holder.quantity.setText(product_Listitems.get(position).getQuantity() + "");



        double totalamount = product_Listitems.get(position).getTotalAmount();
        int amount_total =(int)Math.round(totalamount);
        holder.amount.setText(df.format(amount_total));
        // holder.gst.setText(product_Listitems.get(position).getGstPercentage()+"");
        holder.cgst.setText(product_Listitems.get(position).getGstPercentage()+"");
        // holder.sgst.setText(product_Listitems.get(position).getSgstPercentage()+"");
        //  holder.item_cost.setText(df.format(product_Listitems.get(position).getAmount()));
        double amount = Double.valueOf(product_Listitems.get(position).getAmount());
        Log.e("amount===",amount+"");
        Integer quantity = Integer.parseInt(holder.quantity.getText().toString());
        double value = amount / quantity;
        holder.item_cost.setText(df.format(value));
    }


    @Override
    public int getItemCount() {
        if (product_Listitems != null)
            return product_Listitems.size();
        else
            return 0;
    }


}


