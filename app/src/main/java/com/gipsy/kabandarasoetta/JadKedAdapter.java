package com.gipsy.kabandarasoetta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class JadKedAdapter extends RecyclerView.Adapter<JadKedAdapter.ViewHolder> {
    private List<JadKed> jadKedList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView JadwalKed;

        public ViewHolder(View itemView) {
            super(itemView);
            JadwalKed = itemView.findViewById(R.id.jadwal_bks);
        }
    }
    public JadKedAdapter(Context context,List<JadKed> jadKedList){
        this.jadKedList = jadKedList;
        this.context = context;
    }

    @Override
    public JadKedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        View itemview = LayoutInflater.from(context)
                .inflate(R.layout.jadbks_list, parent, false);
        return new JadKedAdapter.ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(JadKedAdapter.ViewHolder holder, int position) {
        JadKed jadKed = jadKedList.get(position);
        holder.JadwalKed.setText(jadKed.getJadwalKed());
    }

    @Override
    public int getItemCount() {
        return jadKedList.size();
    }
}
