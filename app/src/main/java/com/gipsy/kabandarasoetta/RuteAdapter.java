package com.gipsy.kabandarasoetta;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RuteAdapter extends RecyclerView.Adapter<RuteAdapter.ViewHolder> {

    private List<Rute> ruteList;
    private OnNoteListener mOnNoteListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Ruteapa;
        OnNoteListener onNoteListener;

        public ViewHolder(View view, OnNoteListener onNoteListener){
            super(view);
            Ruteapa = (TextView) view.findViewById(R.id.Ruteapa);
            this.onNoteListener = onNoteListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());

        }
    }
    public RuteAdapter (List<Rute> ruteList, OnNoteListener onNoteListener) {
        this.ruteList = ruteList;
        this.mOnNoteListener = onNoteListener;
    }
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewtype) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rute_list, parent, false);
        return new ViewHolder(itemView, mOnNoteListener);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rute rute = ruteList.get(position);
        holder.Ruteapa.setText(rute.getRuteapa());
    }
    @Override
    public int getItemCount(){
        return ruteList.size();
    }
    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
