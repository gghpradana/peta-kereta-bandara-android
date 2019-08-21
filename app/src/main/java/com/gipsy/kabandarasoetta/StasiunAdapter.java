package com.gipsy.kabandarasoetta;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StasiunAdapter extends RecyclerView.Adapter<StasiunAdapter.MyViewHolder> {

    private List<Stasiun> stasiunList;
    private OnNoteListener mOneNoteListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView NamaStasiun;
        OnNoteListener onNoteListener;

        public MyViewHolder(View view, OnNoteListener onNoteListener) {
            super(view);
            NamaStasiun = (TextView) view.findViewById(R.id.NamaStasiun);
            this.onNoteListener = onNoteListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public StasiunAdapter(List<Stasiun> stasiunList, OnNoteListener onNoteListener) {
        this.stasiunList =stasiunList;
        this.mOneNoteListener = onNoteListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stasiun_list, parent,false);
        return new MyViewHolder(itemView, mOneNoteListener);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Stasiun stasiun = stasiunList.get(position);
        holder.NamaStasiun.setText(stasiun.getNamaStasiun());
    }
    @Override
    public int getItemCount() {
        return stasiunList.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
