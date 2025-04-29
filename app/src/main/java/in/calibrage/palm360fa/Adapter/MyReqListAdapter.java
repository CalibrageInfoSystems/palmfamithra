package in.calibrage.palm360fa.Adapter;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.calibrage.palm360fa.Model.Request_settings;
import in.calibrage.palm360fa.R;
import in.calibrage.palm360fa.common.CircleTransform;


public class MyReqListAdapter extends RecyclerView.Adapter<MyReqListAdapter.MyViewHolder> {


    private Context context;
    private List<Request_settings> request_List;
    private RequestAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.textView);
            thumbnail = view.findViewById(R.id.imageView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onContactSelected(request_List.get(getAdapterPosition()));
                }
            });
        }
    }


    public MyReqListAdapter(Context context, List<Request_settings> request_List, RequestAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.request_List = request_List;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_req_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Request_settings settings = request_List.get(position);
        holder.name.setText(settings.getName());


        Picasso.with(context).load(settings.getImage()).error(R.drawable.ic_user).transform(new CircleTransform()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return request_List.size();
    }




    public interface RequestAdapterListener {
        void onContactSelected(Request_settings request);
    }
}