package com.example.jawahir.mymovie_p0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jawahir.mymovie_p0.Utilities.MovieData;
import com.squareup.picasso.Picasso;

public class ChildActivity extends AppCompatActivity {
    public static String TAG = ChildActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        MovieData movieData;
        if (getIntent() != null && getIntent().getExtras() != null) {
            movieData = (MovieData) getIntent().getExtras().getParcelable("Movieobject");
        } else {
            return;
        }

        /*View view;
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_child,null);*/
        ImageView imageView = (ImageView) findViewById(R.id.img_child);
        TextView textViewDesc = (TextView) findViewById(R.id.tv_desc);
        TextView textViewTitle = (TextView) findViewById(R.id.tv_title);
        TextView textViewReview = (TextView) findViewById(R.id.tv_review);
        TextView textViewReleaseDate = (TextView) findViewById(R.id.tv_releaseDate);
        Picasso.with(this).load(movieData.getMoviePath()).into(imageView);
        textViewTitle.setText(movieData.getMovieName());
        textViewDesc.setText(movieData.getMovieDescription());
        textViewReview.setText("Rating : " + String.valueOf(movieData.getRating()));
        textViewReleaseDate.setText(movieData.getReleaseDate());


        Log.d(TAG,"Child Class "+movieData.getMovieDescription());
    }

}
