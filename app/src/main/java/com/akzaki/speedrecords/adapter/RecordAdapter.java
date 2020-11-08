package com.akzaki.speedrecords.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akzaki.speedrecords.R;
import com.akzaki.speedrecords.model.Record;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {

    private Context context;
    private Record[] recordList;
    public RecordAdapter(Context cont, Record[] u) {
        this.recordList = u;
        this.context = cont;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return recordList.length;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.km_per_h.setText(String.format("%.1f KM/H",(recordList[position].distance /1000)/(recordList[position].second/3600)));
        holder.meter_second.setText(String.format("%.1f METERS, %.1f SECONDS",recordList[position].distance,recordList[position].second));
        //holder.km_per_h.setText("kdsf");
        //holder.meter_second.setText("dfgsdfg");
        if((recordList[position].distance /1000)/(recordList[position].second/3600) >= 80.0){
            holder.over_limit.setImageResource(R.drawable.red_cow);
        }
        //holder.single.setBackgroundColor((recordList[position]. ? Color.GREEN : Color.RED) );
        //holder.genderimage.setImageResource();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView km_per_h;
        TextView meter_second;
        ImageView over_limit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            km_per_h = itemView.findViewById(R.id.km_per_h);
            meter_second = itemView.findViewById(R.id.meter_second);
            over_limit = itemView.findViewById(R.id.over_image);
        }
    }

}
