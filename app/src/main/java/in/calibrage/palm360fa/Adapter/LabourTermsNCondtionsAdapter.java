package in.calibrage.palm360fa.Adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import in.calibrage.palm360fa.Model.LabourTermsNCondtionsModel;
import in.calibrage.palm360fa.R;


public class LabourTermsNCondtionsAdapter extends RecyclerView.Adapter<LabourTermsNCondtionsAdapter.ViewHolder> {

    public Context mContext;
    private LabourTermsNCondtionsModel listdata;

    public LabourTermsNCondtionsAdapter(Context ctx, LabourTermsNCondtionsModel listdata) {
        this.listdata = listdata;
        this.mContext = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recommendation_terms_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("3==years",listdata.getListResult().get(0).getC3()+"");
        if (position == 0 & listdata.getListResult().size() >0) {
            holder.age.setText( " <= 1 Year");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC1()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC1()));
            holder.unknown1.setText(""+listdata.getListResult().get(2).getC1());
            holder.unknow2.setText(""+listdata.getListResult().get(3).getC1());
        } else if (position == 1 & listdata.getListResult().size() >1) {

            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC2()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC2()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC2()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC2()));
            Log.e("3==years",listdata.getListResult().get(0).getC3()+"");
        }

        else if (position == 2  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC3()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC3()));
            Log.e("3==years",listdata.getListResult().get(0).getC3()+"");
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC3()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC3()));
        }
//        else if (position == 3 & listdata.getListResult().size() >1) {
//            holder.age.setText(position + 1 + " Years");
//            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC3()));
//            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC3()));
//          /*  holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC3()));
//            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC3()));*/
//        }
        else if (position == 3  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC4()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC4()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC4()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC4()));
        }
        else if (position == 4  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC5()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC5()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC5()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC5()));
        }
        else if (position == 5  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC6()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC6()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC6()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC6()));
        }
        else if (position == 6  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC7()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC7()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC7()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC7()));
        }
        else if (position == 7  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1+ " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC8()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC8()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC8()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC8()));
        }
        else if (position == 8  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1+ " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC9()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC9()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC9()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC9()));
        }
        else if (position == 9  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1+ " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC10()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC10()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC10()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC10()));
        }  else if (position == 10  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC11()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC11()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC11()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC11()));
        }

        else if (position == 11  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC12()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC12()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC12()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC12()));

        }
        else if (position == 12  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC13()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC13()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC13()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC13()));

        }
        else if (position == 13  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC14()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC14()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC14()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC14()));

        }
        else if (position == 14  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC15()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC15()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC15()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC15()));

        }
        else if (position == 15  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC16()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC16()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC16()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC16()));

        }
        else if (position == 16  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1+ " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC17()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC17()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC17()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC17()));

        }
        else if (position == 17  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC18()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC18()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC18()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC18()));

        }
        else if (position == 18  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC19()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC19()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC19()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC19()));

        }
        else if (position == 19  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC20()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC20()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC20()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC20()));

        }
        else if (position == 20  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC21()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC21()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC21()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC21()));

        }   else if (position == 21  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC22()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC22()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC22()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC22()));

        }
        else if (position == 22  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC23()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC23()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC23()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC23()));

        }
        else if (position == 23  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC24()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC24()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC24()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC24()));

        }
        else if (position == 24  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC25()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC25()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC25()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC25()));

        }
        else if (position == 25  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC26()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC26()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC26()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC26()));

        }
        else if (position == 26  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC27()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC27()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC27()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC27()));

        }
        else if (position == 27  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC28()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC28()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC28()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC28()));

        }
        else if (position == 28  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC29()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC29()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC29()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC29()));

        }
        else if (position == 29  & listdata.getListResult().size() >1) {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText(String.valueOf(listdata.getListResult().get(0).getC30()));
            holder.harv.setText(String.valueOf(listdata.getListResult().get(1).getC30()));
            holder.unknown1.setText(String.valueOf(listdata.getListResult().get(2).getC30()));
            holder.unknow2.setText(String.valueOf(listdata.getListResult().get(3).getC30()));

        }


        else {
            holder.age.setText(position + 1 + " Years");
            holder.purning.setText("NA");
            holder.harv.setText("NA");
            holder.unknown1.setText("NA");
            holder.unknow2.setText("NA");
        }




    }


    @Override
    public int getItemCount() {

        return 30;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView harv, purning, unknown1, unknow2, age;

        public CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.harv = (TextView) itemView.findViewById(R.id.harv);
            this.purning = (TextView) itemView.findViewById(R.id.prunning);
            this.unknown1 = (TextView) itemView.findViewById(R.id.un1);
            this.unknow2 = (TextView) itemView.findViewById(R.id.un2);
            this.age = (TextView) itemView.findViewById(R.id.age);

            // this.card_view =  (CardView) itemView.findViewById(R.id.card_view);

        }


    }
}