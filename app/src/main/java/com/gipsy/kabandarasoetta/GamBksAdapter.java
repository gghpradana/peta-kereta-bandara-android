package com.gipsy.kabandarasoetta;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GamBksAdapter extends PagerAdapter {

    private Context context;

        public GamBksAdapter(Context context) {
            this.context = context;
        }

        /*
    This callback is responsible for creating a page. We inflate the layout and set the drawable
    to the ImageView based on the position. In the end we add the inflated layout to the parent
    container .This method returns an object key to identify the page view, but in this example page view
    itself acts as the object key
    */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.gambar_pager, null);
            ImageView imageView = view.findViewById(R.id.gambar);
            imageView.setImageDrawable(context.getResources().getDrawable(getImageAt(position)));
            container.addView(view);
            return view;
        }

        /*
    This callback is responsible for destroying a page. Since we are using view only as the
    object key we just directly remove the view from parent container
    */
        @Override
        public void destroyItem(ViewGroup container, int position, Object view) {
            container.removeView((View) view);
        }

        /*
        Returns the count of the total pages
        */
        @Override
        public int getCount() {
            return 8;
        }

        /*
    Used to determine whether the page view is associated with object key returned by instantiateItem.
    Since here view only is the key we return view==object
    */
        @Override
        public boolean isViewFromObject( View view, Object o) {
            return o == view;
        }

        private int getImageAt(int position) {
            switch (position) {
                case 0:
                    return R.drawable.bks1;
                case 1:
                    return R.drawable.bks2;
                case 2:
                    return R.drawable.bks3;
                case 3:
                    return R.drawable.bks4;
                case 4:
                    return R.drawable.bks5;
                case 5:
                    return R.drawable.bks6;
                case 6:
                    return R.drawable.bks7;
                case 7:
                    return R.drawable.bks8;
                default:
                    return R.drawable.bks1;
            }
        }
}
