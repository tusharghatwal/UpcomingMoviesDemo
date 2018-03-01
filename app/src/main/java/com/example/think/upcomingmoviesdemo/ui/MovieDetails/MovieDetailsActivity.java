package com.example.think.upcomingmoviesdemo.ui.MovieDetails;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.think.upcomingmoviesdemo.R;
import com.example.think.upcomingmoviesdemo.data.Images.Backdrop;
import com.example.think.upcomingmoviesdemo.data.local.PreferencesHelper;
import com.example.think.upcomingmoviesdemo.data.model.Movie;
import com.example.think.upcomingmoviesdemo.ui.main.MoviesAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsView {

    private MovieDetailsPresenter mMovieDetailsPresenter;
    private TextView mTextTitle, mTextDetails;
    private RatingBar mRatingBar;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private Movie mMovie;
    private CircleIndicator mIndicator;
    private PreferencesHelper mPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mTextTitle = findViewById(R.id.txt_title);
        mTextDetails = findViewById(R.id.txt_description);
        mRatingBar = findViewById(R.id.ratingbar);
        mViewPager = findViewById(R.id.viewpager);
        mIndicator = findViewById(R.id.indicator);

        mMovieDetailsPresenter = new MovieDetailsPresenter(this);
        mPreferenceHelper = new PreferencesHelper(this);

        mMovie = getIntent().getParcelableExtra(MoviesAdapter.KEY);

        mTextTitle.setText(mMovie.getTitle());
        mTextDetails.setText(mMovie.getOverview());
        mRatingBar.setRating(mMovie.getVoteAverage()/2);

        mMovieDetailsPresenter.getMovieImages(mMovie.getId());
    }

    @Override
    public void setViewPager(ArrayList<Backdrop> mListBackdrops) {
        Log.e("backdrop", String.valueOf(mListBackdrops.size()));
        mViewPagerAdapter = new ViewPagerAdapter(this, mListBackdrops, mPreferenceHelper);
        mViewPager.setAdapter(mViewPagerAdapter);
        mIndicator.setViewPager(mViewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMovieDetailsPresenter.unSubscribeSubscription();
    }
}
