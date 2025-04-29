package in.calibrage.palm360fa.Views.Activities;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import in.calibrage.palm360fa.Model.GetLabourPackageDiscount;
import in.calibrage.palm360fa.R;


public class LabourdiscountAdapter extends RecyclerView.Adapter<LabourdiscountAdapter.ViewHolder> {

    public Context mContext;
    private GetLabourPackageDiscount listdata;

    public LabourdiscountAdapter(Context ctx, GetLabourPackageDiscount listdata) {
        this.listdata = listdata;
        this.mContext = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.discount_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        ( (ViewHolder) holder).pack.setText( listdata.getListResult().get(position).getDesc());
        ( (ViewHolder) holder).discount.setText(listdata.getListResult().get(position).getDiscountPercentage()+"");


    }


    @Override
    public int getItemCount() {

        if (listdata != null)
            return listdata.getListResult().size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView pack, discount, unknown1, unknow2, age;

        public CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.pack = (TextView) itemView.findViewById(R.id.pack);
            this.discount = (TextView) itemView.findViewById(R.id.discount);
//            this.unknown1 = (TextView) itemView.findViewById(R.id.un1);
//            this.unknow2 = (TextView) itemView.findViewById(R.id.un2);
//            this.age = (TextView) itemView.findViewById(R.id.age);

            // this.card_view =  (CardView) itemView.findViewById(R.id.card_view);

        }


    }

}
