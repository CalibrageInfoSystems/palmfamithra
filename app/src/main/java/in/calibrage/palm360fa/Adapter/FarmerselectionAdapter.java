//package in.calibrage.AkshayaFA.Adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Filter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import in.calibrage.AkshayaFA.Model.Farmersearchresponse;
//
//import in.calibrage.AkshayaFA.R;
//import in.calibrage.AkshayaFA.common.CircleTransform;
//
//public class FarmerselectionAdapter extends ArrayAdapter<Farmersearchresponse.ListResult> {
//
//
//    private Context context;
//
//    //
//    private int resourceId;
//    List<Farmersearchresponse.ListResult> items, tempItems, suggestions;
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView farmer_Code, phone;
//        public ImageView thumbnail;
//
//        public MyViewHolder(View view) {
//            super(view);
//            farmer_Code = view.findViewById(R.id.farmer_Code);
//            phone = view.findViewById(R.id.mobilenumber);
//
//
//        }
//    }
//
//    public FarmerselectionAdapter(@NonNull Context context, int resourceId, ArrayList<String> items) {
//        super(context, resourceId, items);
//        this.items = items;
//        this.context = context;
//        this.resourceId = resourceId;
//
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View view = convertView;
//        try {
//            if (convertView == null) {
//                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//                view = inflater.inflate(resourceId, parent, false);
//            }
//            Farmersearchresponse.ListResult farmer = getItem(position);
//            TextView name = (TextView) view.findViewById(R.id.farmer_Code);
//            TextView phone = view.findViewById(R.id.mobilenumber);
//
//            name.setText(farmer.getFarmerCode());
//            phone.setText(farmer.getContactNumber());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return view;
//    }
//
//    @Nullable
//    @Override
//    public Farmersearchresponse.ListResult getItem(int position) {
//        return items.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return items.size();
//    }
//}