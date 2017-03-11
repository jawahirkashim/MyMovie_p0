package com.example.jawahir.mymovie_p0;


import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jawahir.mymovie_p0.Utilities.MovieData;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jawahir on 2/3/17.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;
    MovieData[] mdata;
    static final String TAG = ImageAdapter.class.getSimpleName();

    public ImageAdapter(Context context,MovieData[] movieDatas) {
        this.context = context;
        mdata = movieDatas;
    }
    @Override
    public int getCount() {
        return mdata.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        //GridView gridView = (GridView) ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.movieposter_grid,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgv_movieposter);
        TextView textView = (TextView) view.findViewById(R.id.tv_moviposter);
        final String moviePath = mdata[position].moviePath;
        final String title = mdata[position].movieName;
        //textView.setText(title);
        Picasso.with(context).load(moviePath).into(imageView);
        Log.d(TAG,title);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"msg "+title,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,ChildActivity.class);
                MovieData movieData = new MovieData(title,mdata[position].movieDescription,moviePath,mdata[position].rating,mdata[position].releaseDate);
                intent.putExtra("Movieobject",movieData);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
