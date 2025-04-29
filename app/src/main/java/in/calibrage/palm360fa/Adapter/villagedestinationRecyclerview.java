package in.calibrage.palm360fa.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.calibrage.palm360fa.Model.GetPlotVillagesByFarmerCode;
import in.calibrage.palm360fa.Model.Getdestinations;
import in.calibrage.palm360fa.R;

public class villagedestinationRecyclerview extends RecyclerView.Adapter<villagedestinationRecyclerview.ViewHolder> {
    Getdestinations CCList;
    private List<GetPlotVillagesByFarmerCode.ListResult>villagesList = new ArrayList<>();
    public Context mContext;
    private villagedestinationRecyclerviewrListener listener;



    public villagedestinationRecyclerview(Context context, List<GetPlotVillagesByFarmerCode.ListResult> villages, Getdestinations cclist, villagedestinationRecyclerviewrListener listener) {
        this.villagesList = villages;
        this.mContext = context;
        this.CCList= cclist;
        this.listener =listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View rowView;
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView=inflater.inflate(R.layout.villagedestinationrecyclerview, parent,false);

        ViewHolder viewHolder = new ViewHolder(rowView);

        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    private void showDialog(Context mContext, String msg) {
        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
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

    @Override
    public int getItemCount() {

        if (villagesList != null)
            return villagesList.size();
        else
            return 0;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        EditText villageName;
        Spinner destination1, destination2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            villageName = itemView.findViewById(R.id.peak_plotvillagename);
            destination1 = itemView.findViewById(R.id.destination1spinner);
            destination2 = itemView.findViewById(R.id.destination2spinner);
        }
    }

    public interface villagedestinationRecyclerviewrListener {

        void onSelected(int po, ArrayList<villagedestinationRecyclerview> villagedestinations);
    }

    public abstract static class TextChangedListener<T> implements TextWatcher {
        private T target;

        public TextChangedListener(T target) {
            this.target = target;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            this.onTextChanged(target, s);
        }

        public abstract void onTextChanged(T target, Editable s);
    }
}
