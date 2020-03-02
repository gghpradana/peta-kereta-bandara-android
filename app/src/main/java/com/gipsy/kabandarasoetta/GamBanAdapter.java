package com.gipsy.kabandarasoetta;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GamBanAdapter extends PagerAdapter {
    private Context context;

    public GamBanAdapter (Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.gambar_pager, null);
        ImageView imageView = view.findViewById(R.id.gambar);
        imageView.setImageDrawable(context.getResources().getDrawable(getImageAt(position)));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 8;
    }


    @Override
    public boolean isViewFromObject( View view, Object o) {
        return o == view;
    }

    private int getImageAt(int position) {
        switch (position) {
            case 0:
                return R.drawable.ban1;
            case 1:
                return R.drawable.ban2;
            case 2:
                return R.drawable.ban3;
            case 3:
                return R.drawable.ban4;
            case 4:
                return R.drawable.ban5;
            case 5:
                return R.drawable.ban6;
            case 6:
                return R.drawable.ban7;
            case 7:
                return R.drawable.ban8;
            default:
                return R.drawable.ban1;
        }
    }
}
