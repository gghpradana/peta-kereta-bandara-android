package com.gipsy.kabandarasoetta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class JadKebAdapter extends RecyclerView.Adapter<JadKebAdapter.ViewHolder> {

    private List<JadKeb> jadKebList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView JadwalKeb;

        public ViewHolder(View itemView) {
            super(itemView);
            JadwalKeb = itemView.findViewById(R.id.jadwal_bks);
        }
    }
    public JadKebAdapter(Context context,List<JadKeb> jadKebList){
        this.jadKebList = jadKebList;
        this.context = context;
    }

    @Override
    public JadKebAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        View itemview = LayoutInflater.from(context)
                .inflate(R.layout.jadbks_list, parent, false);
        return new JadKebAdapter.ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(JadKebAdapter.ViewHolder holder, int position) {
        JadKeb jadKeb = jadKebList.get(position);
        holder.JadwalKeb.setText(jadKeb.getJadwalKeb());
    }

    @Override
    public int getItemCount() {
        return jadKebList.size();
    }
}
