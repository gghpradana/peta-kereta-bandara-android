package com.gipsy.kabandarasoetta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

public class RBanBniAdapter extends RecyclerView.Adapter<RBanBniAdapter.ViewHolder> {

    private List<RBanBni> rBanBniList;
    private Context context;
    private RBanBniAdapter.OnNoteListener mOneNoteListener;
    public TimelineView mTimelineView;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView Stopanbanbni;
        RBanBniAdapter.OnNoteListener onNoteListener;

        public ViewHolder(View view, RBanBniAdapter.OnNoteListener onNoteListener, int viewType) {
            super(view);
            Stopanbanbni = (TextView) view.findViewById(R.id.stopanbanbni);
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
    public RBanBniAdapter(Context context, List<RBanBni> rBanBniList, RBanBniAdapter.OnNoteListener onNoteListener) {
        this.rBanBniList = rBanBniList;
        this.context = context;
        this.mOneNoteListener = onNoteListener;
    }

    @Override
    public RBanBniAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rbanbni_list, parent,false);
        return new RBanBniAdapter.ViewHolder(itemView, mOneNoteListener, viewType);
    }
    @Override
    public void onBindViewHolder(RBanBniAdapter.ViewHolder holder, int position) {
        RBanBni rBanBni = rBanBniList.get(position);
        holder.Stopanbanbni.setText(rBanBni.getStopanbanbni());
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return rBanBniList.size();
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
