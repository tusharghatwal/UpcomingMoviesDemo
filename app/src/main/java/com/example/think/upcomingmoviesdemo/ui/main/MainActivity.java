package com.example.think.upcomingmoviesdemo.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.think.upcomingmoviesdemo.R;
import com.example.think.upcomingmoviesdemo.data.local.DbHelper;
import com.example.think.upcomingmoviesdemo.data.local.MoviesTable;
import com.example.think.upcomingmoviesdemo.data.local.PreferencesHelper;
import com.example.think.upcomingmoviesdemo.data.model.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private MainActivityPresenter mPresenter;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerMovies;
    private PreferencesHelper mPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressbar);
        mRecyclerMovies = findViewById(R.id.recyclerMovies);

        mPreferenceHelper = new PreferencesHelper(this);
        mPresenter = new MainActivityPresenter(this, mPreferenceHelper);

        mPresenter.getConfigurationAndLoadMovies();
//        moviesTable.getAllMovies();

    }

    @Override
    public void setActivityView(List<Movie> movies) {
        mProgressBar.setVisibility(View.GONE);

        MoviesAdapter moviesAdapter = new MoviesAdapter(this, movies, mPreferenceHelper);
        mRecyclerMovies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerMovies.setAdapter(moviesAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribeSubscription();
    }
}
