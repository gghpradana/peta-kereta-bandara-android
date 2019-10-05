package com.gipsy.kabandarasoetta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class JadBksAdapter extends RecyclerView.Adapter<JadBksAdapter.ViewHolder> {

    private List<JadBks> jadBksList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView JadwalBks;

        public ViewHolder(View itemView) {
            super(itemView);
            JadwalBks = itemView.findViewById(R.id.jadwal_bks);
        }
    }
    public JadBksAdapter(Context context,List<JadBks> jadBksList){
        this.jadBksList = jadBksList;
        this.context = context;
    }

    @Override
    public JadBksAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewtype) {
        View itemview = LayoutInflater.from(context)
                .inflate(R.layout.jadbks_list, parent, false);
        return new JadBksAdapter.ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(JadBksAdapter.ViewHolder holder, int position) {
        JadBks jadBks = jadBksList.get(position);
        holder.JadwalBks.setText(String.valueOf(jadBks.getJadwalbks()));
    }

    @Override
    public int getItemCount() {
        return jadBksList.size();
    }
}
