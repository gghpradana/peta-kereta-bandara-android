package com.gipsy.kabandarasoetta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RBanBksAdapter extends RecyclerView.Adapter<RBanBksAdapter.ViewHolder>{

    private List<RBanBks> rBanBksList;
    private Context context;
    private RBanBksAdapter.OnNoteListener mOneNoteListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Stopanbanbks;
        RBanBksAdapter.OnNoteListener onNoteListener;

        public ViewHolder(View view, RBanBksAdapter.OnNoteListener onNoteListener) {
            super(view);
            Stopanbanbks = (TextView) view.findViewById(R.id.stopanbanbks);

            this.onNoteListener = onNoteListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public RBanBksAdapter(Context context, List<RBanBks> rBanBksList, RBanBksAdapter.OnNoteListener onNoteListener) {
        this.rBanBksList = rBanBksList;
        this.context = context;
        this.mOneNoteListener = onNoteListener;
    }

    @Override
    public RBanBksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rbanbks_list, parent,false);
        return new RBanBksAdapter.ViewHolder(itemView, mOneNoteListener);
    }

    @Override
    public void onBindViewHolder(RBanBksAdapter.ViewHolder holder, int position) {
        RBanBks rBanBks = rBanBksList.get(position);
        holder.Stopanbanbks.setText(rBanBks.getStopanbanbks());
    }

    @Override
    public int getItemCount() {
        return rBanBksList.size();
    }

    public interface OnNoteListener {
        void onNoteClick(int position);

    }
}
