package in.calibrage.palm360fa.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.calibrage.palm360fa.Model.InterCrop;
import in.calibrage.palm360fa.R;


public class InterCrop_Adapter extends RecyclerView.Adapter<InterCrop_Adapter.MyViewHolder> {


    private Context context;
    private List<InterCrop> intercrop_List;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView crop, phone;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            crop = view.findViewById(R.id.crop);

        }
    }


    public InterCrop_Adapter(Context context, List<InterCrop> intercrop_List) {
        this.context = context;

        this.intercrop_List = intercrop_List;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inter_crop_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final InterCrop settings = intercrop_List.get(position);
        holder.crop.setText(settings.getCrop());



    }

    @Override
    public int getItemCount() {
        return intercrop_List.size();
    }

}