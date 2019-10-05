package com.gipsy.kabandarasoetta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

public class RBniBanAdapter extends RecyclerView.Adapter<RBniBanAdapter.ViewHolder> {

    private List<RBniBan> rBniBanList;
    private Context context;
    private RBniBanAdapter.OnNoteListener mOneNoteListener;
    public TimelineView mTimelineView;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView Stopanbniban;
        RBniBanAdapter.OnNoteListener onNoteListener;

        public ViewHolder(View view, RBniBanAdapter.OnNoteListener onNoteListener, int viewType) {
            super(view);
            Stopanbniban = (TextView) view.findViewById(R.id.stopanbniban);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.timeline);
            mTimelineView.initLine(viewType);

            this.onNoteListener = onNoteListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public RBniBanAdapter(Context context, List<RBniBan> rBniBanList, RBniBanAdapter.OnNoteListener onNoteListener) {
        this.rBniBanList = rBniBanList;
        this.context = context;
        this.mOneNoteListener = onNoteListener;
    }

    @Override
    public RBniBanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rbniban_list, parent,false);
        return new RBniBanAdapter.ViewHolder(itemView, mOneNoteListener, viewType);
    }
    @Override
    public void onBindViewHolder(RBniBanAdapter.ViewHolder holder, int position) {
        RBniBan rBniBan = rBniBanList.get(position);
        holder.Stopanbniban.setText(rBniBan.getStopanbniban());

    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return rBniBanList.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
