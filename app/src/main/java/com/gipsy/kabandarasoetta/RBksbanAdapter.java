package com.gipsy.kabandarasoetta;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

public class RBksbanAdapter extends RecyclerView.Adapter<RBksbanAdapter.ViewHolder> {

    private List<RBksban> rBksbanList;
    private Context context;
    private RBksbanAdapter.OnNoteListener mOneNoteListener;
    public TimelineView mTimelineView;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView Stopanbksban;
        RBksbanAdapter.OnNoteListener onNoteListener;

        public ViewHolder(View view, RBksbanAdapter.OnNoteListener onNoteListener, int viewType) {
            super(view);
            Stopanbksban = (TextView) view.findViewById(R.id.stopanbksban);
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
    public RBksbanAdapter(Context context, List<RBksban> rBksbanList, RBksbanAdapter.OnNoteListener onNoteListener) {
        this.rBksbanList = rBksbanList;
        this.context = context;
        this.mOneNoteListener = onNoteListener;
    }

    @Override
    public RBksbanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rbksban_list, parent,false);
        return new RBksbanAdapter.ViewHolder(itemView, mOneNoteListener, viewType);
    }
    @Override
    public void onBindViewHolder(RBksbanAdapter.ViewHolder holder, int position) {
        RBksban rBksban = rBksbanList.get(position);
        holder.Stopanbksban.setText(rBksban.getStopanbksban());

    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return rBksbanList.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
