package com.example.think.upcomingmoviesdemo.ui.MovieDetails;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.think.upcomingmoviesdemo.R;
import com.example.think.upcomingmoviesdemo.data.Images.Backdrop;
import com.example.think.upcomingmoviesdemo.data.local.PreferencesHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Think on 27-Feb-18.
 */

class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private PreferencesHelper mPreferenceHelper;
    private ArrayList<Backdrop> mListBackdrop = new ArrayList<>();

    public ViewPagerAdapter(Context context, ArrayList<Backdrop> mListBackdrops, PreferencesHelper mPreferenceHelper) {
        this.mContext = context;
        this.mListBackdrop = mListBackdrops;
        this.mPreferenceHelper = mPreferenceHelper;
    }

    @Override
    public int getCount() {
        return mListBackdrop.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_viewpager,
                container, false);
        Log.e("backdrop size", String.valueOf(mListBackdrop.size()));

        final ImageView imageBackdrop = itemView.findViewById(R.id.image_backdrops);

        String imageUrl = mPreferenceHelper.getMediumBaseImageUrl() + mListBackdrop.get(position).getFilePath();

        Log.e("baseurlcheck",imageUrl);

        Picasso.with(mContext)
                .load(imageUrl)
                .fit()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(imageBackdrop);

        container.addView(itemView);
        return itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
