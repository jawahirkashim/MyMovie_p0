package com.example.jawahir.mymovie_p0;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by jawahir on 2/3/17.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;
    String[] data;
    public ImageAdapter(String[] strings) {
        this.context = context;
        data = strings;
    }
    @Override
    public int getCount() {
        return data.length;
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
        return null;
    }
}
