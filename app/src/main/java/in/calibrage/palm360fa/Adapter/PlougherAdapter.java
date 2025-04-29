package in.calibrage.palm360fa.Adapter;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.calibrage.palm360fa.Model.Attachments;
import in.calibrage.palm360fa.Model.GetTypeCdDmt;
import in.calibrage.palm360fa.Model.spinneritemmodel;
import in.calibrage.palm360fa.R;

public class PlougherAdapter extends RecyclerView.Adapter<PlougherAdapter.ViewHolder> {
public Context mContext;
private List<GetTypeCdDmt.ListResult>plousherList = new ArrayList<>();
    private PlougherAdapterListener listener;
    private ArrayList<Attachments> attachment = new ArrayList<>();
    double Price;
    int Plougher;
    GetTypeCdDmt Attachment_Chargesdata;
    public Spinner uom_spinner;
    int uomid;
    ArrayList<spinneritemmodel> uomdataArry = new ArrayList<spinneritemmodel>();
    SpinnerArrayAdapter adapter;
public PlougherAdapter(Context context, List<GetTypeCdDmt.ListResult> superHeroes,int Plougher,GetTypeCdDmt AttachmentChargesdata,PlougherAdapterListener listener) {
        this.plousherList = superHeroes;
        this.mContext = context;
        this.listener = listener;
        this.Plougher = Plougher;
    this.Attachment_Chargesdata =AttachmentChargesdata;
    uomdataArry.add(new spinneritemmodel(0,"Please Select"));
    for (GetTypeCdDmt.ListResult data : Attachment_Chargesdata.getListResult()
    ) {
        uomdataArry.add(new spinneritemmodel(data.getTypeCdId(),data.getDesc()));
    }
        }


@Override
public ViewHolder onCreateViewHolder(ViewGroup parent,
                                     int viewType) {
//        // create a new view
//        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
//        R.layout.plougherdata, null);
    View _v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plougherdata, parent, false );
        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(_v);

        return viewHolder;
        }

@RequiresApi(api = Build.VERSION_CODES.M)
@Override
public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.plougherName.setText(plousherList.get(position).getDesc());
    attachment.add(new Attachments("",Plougher,position,0.0,0,0));
  //  attachment.add(new Attachments(plousherList.get(position).getDesc(),Double.valueOf(viewHolder.amount.getText().toString()),plousherList.get(position).getTypeCdId()));
    attachment.add(new Attachments("",Plougher,position,0.0,0,0));
    //Price = Double.valueOf(viewHolder.amount.getText().toString());
    //  spinner.setAdapter(spinnerArrayAdapter);


    adapter = new SpinnerArrayAdapter(mContext, uomdataArry);
    adapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
    uom_spinner.setAdapter(adapter);
    uom_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent,
                                   View view, int i, long id)
        {
            spinneritemmodel clickedItem = (spinneritemmodel)
                    parent.getItemAtPosition(i);
            String name = clickedItem.getName();
            uomid =clickedItem.getId();

            Attachments currentuom = attachment.get(position);
            currentuom.setUOMID(uomid);

            attachment.set(position,currentuom);
            listener.onSelectedd(position,attachment,Plougher);

            //Toast.makeText(mContext, name +"   ID"+ HirebaseID2 + " selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // DO Nothing here
        }
    });

    viewHolder.amount.addTextChangedListener(new HirechargesAdapter.TextChangedListener<EditText>( viewHolder.amount) {
        @Override
        public void onTextChanged(EditText target, Editable s) {
            //Do stuff
            attachment.set(position,new Attachments(plousherList.get(position).getDesc(),Plougher,position,
                    TextUtils.isEmpty(viewHolder.amount.getText()) == true ? 0 : Double.valueOf(viewHolder.amount.getText().toString()),
                    plousherList.get(position).getTypeCdId(),uomid));

            listener.onSelectedd(position,attachment,Plougher);


        }
    });


        }





// Return the size arraylist
@Override
public int getItemCount() {


        if (plousherList != null)
        return plousherList.size();
        else
        return 0;
        }


public  class ViewHolder extends RecyclerView.ViewHolder {

    public TextView plougherName, villagename2;

    public EditText amount, price2;


    public ViewHolder(View itemLayoutView) {
        super(itemLayoutView);
        plougherName = (TextView) itemLayoutView.findViewById(R.id.plougherName);
        amount = (EditText) itemLayoutView.findViewById(R.id.price);

        uom_spinner = (Spinner)itemLayoutView.findViewById(R.id.uom_spinner);

    }
}


    public interface PlougherAdapterListener {
        void onSelectedd(int pos,ArrayList<Attachments> attachment,int Attachedid);
    }
}
