package com.example.jawahir.mymovie_p0;


import android.content.Context;
import android.graphics.*;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
    int imageid;
    public ImageAdapter(Context context,MovieData[] movieDatas,int imageid) {
        this.context = context;
        mdata = movieDatas;
        this.imageid = imageid;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.movieposter_grid,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgv_movieposter);
        TextView textView = (TextView) view.findViewById(R.id.tv_moviposter);
        String moviePath = mdata[position].moviePath;
        String title = mdata[position].movieName;
        textView.setText(title);
        Picasso.with(context).load(moviePath).into(imageView);
        Log.d(TAG,title);
        return view;
    }
}
