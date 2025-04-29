package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.calibrage.palm360fa.Model.farmer;
import in.calibrage.palm360fa.R;

public class AutoCompleteUserAdapter extends ArrayAdapter<farmer> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<farmer> arrayList = new ArrayList<>();

    public AutoCompleteUserAdapter(Context context, int layoutResourceId, ArrayList<farmer> arrayList) {
        super(context, layoutResourceId, arrayList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);
            }

            farmer model = arrayList.get(position);

          TextView tvUserName = convertView.findViewById(R.id.farmer_Code);
            tvUserName.setText(model.getFarmername() + " ("+model.getFarmercode() +")");
            TextView mobile = convertView.findViewById(R.id.mobilenumber);
            mobile.setText(model.getMobilenumber());
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }
    public String getItemfarmercodewithnameAtPosition(int position) {
        return arrayList.get(position).getFarmecodewithname();
    }
    public String getItemNameAtPosition(int position) {
        return arrayList.get(position).getFarmercode();
    }
    public String getItemfarmerNameAtPosition(int position) {
        return arrayList.get(position).getFarmername();
    }
    public String getItemfarmerMobileNumberAtPosition(int position) {
        return arrayList.get(position).getMobilenumber();
    }
    public int getItemfarmerVillageAtPosition(int position) {
        return arrayList.get(position).getVillageId();
    }


//    public String getItemIDAtPosition(int position) {
//        return arrayList.get(position).getId();
//    }
}