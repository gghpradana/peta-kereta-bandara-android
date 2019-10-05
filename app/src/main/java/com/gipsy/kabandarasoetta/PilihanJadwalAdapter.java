package com.gipsy.kabandarasoetta;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PilihanJadwalAdapter extends RecyclerView.Adapter<PilihanJadwalAdapter.ViewHolder> {

    private List<PilihanJadwal> pilihanJadwalList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView JadwalBks;

        public ViewHolder(View itemView) {
            super(itemView);
            JadwalBks = itemView.findViewById(R.id.jadwal_bks);
        }
    }

    public PilihanJadwalAdapter(Context context, List<PilihanJadwal> pilihanJadwalList) {
        this.pilihanJadwalList = pilihanJadwalList;
        this.context = context;
    }

    @Override
    public PilihanJadwalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context)
                .inflate(R.layout.jadbks_list, parent, false);
        return new PilihanJadwalAdapter.ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(PilihanJadwalAdapter.ViewHolder Holder, int position) {
        PilihanJadwal pilihanJadwal = pilihanJadwalList.get(position);
        Holder.JadwalBks.setText(String.valueOf(pilihanJadwal.getPilihjadwal()));

    }

    @Override
    public int getItemCount() {
        return pilihanJadwalList.size();
    }
}
