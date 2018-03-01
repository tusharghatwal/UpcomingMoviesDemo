package com.example.think.upcomingmoviesdemo.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.think.upcomingmoviesdemo.R;
import com.example.think.upcomingmoviesdemo.data.local.PreferencesHelper;
import com.example.think.upcomingmoviesdemo.data.model.Movie;
import com.example.think.upcomingmoviesdemo.ui.MovieDetails.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 2/26/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolderMovies>{

    public static final String KEY = "movie";
    private Context mContext;
    private ArrayList<Movie> mListMovies = new ArrayList<>();
    private PreferencesHelper mPreferencesHelper;

    public MoviesAdapter(MainActivity mContext, List<Movie> movies, PreferencesHelper mPreferenceHelper) {
        this.mContext = mContext;
        this.mListMovies.addAll(movies);
        this.mPreferencesHelper = mPreferenceHelper;
    }

    class ViewHolderMovies extends RecyclerView.ViewHolder {

        public TextView mPlaceName = itemView.findViewById(R.id.txtMovieName);
        public ImageView mImageView = itemView.findViewById(R.id.imgMovie);

        public ViewHolderMovies(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {

        return mListMovies.size();
    }

    @Override
    public ViewHolderMovies onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_adapter_movies, null , false);
        return new ViewHolderMovies(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderMovies holder, final int position) {
        final Movie r = mListMovies.get(position);
        String imageUrl = mPreferencesHelper.getThumbnailBaseImageUrl() + r.getPosterPath();

        holder.mPlaceName.setText(r.getTitle());
        Picasso.with(mContext)
                .load(imageUrl)
                .fit()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.mImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra(MoviesAdapter.KEY, r);
                mContext.startActivity(intent);
            }
        });
    }
}
