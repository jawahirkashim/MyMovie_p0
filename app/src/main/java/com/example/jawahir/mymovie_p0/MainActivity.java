package com.example.jawahir.mymovie_p0;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.example.jawahir.mymovie_p0.Utilities.MovieData;
import com.example.jawahir.mymovie_p0.Utilities.MovieJsonUtil;
import com.example.jawahir.mymovie_p0.Utilities.NetworkUtil;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    GridView gridView ;
    int imageId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the id of all the view
        gridView = (GridView) findViewById(R.id.gv_movie);
        imageId = R.drawable.image_01;
        loadData();
    }

    private void loadData() {
        URL url = NetworkUtil.buildUrl("test");
        new MoviAsyncTask().execute(url);
    }

    class MoviAsyncTask extends AsyncTask<URL,Void,MovieData[]> {

        @Override
        protected MovieData[] doInBackground(URL... params) {
            URL url = params[0];
            String jsonString = null;
            try {
                jsonString = NetworkUtil.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            MovieData data[] = MovieJsonUtil.getSimpleMoviListFromJson(jsonString);

            return data;
        }

        @Override
        protected void onPostExecute(MovieData[] movieDatas) {
            //set movie data and images to each view
            ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this,movieDatas,imageId);
            gridView.setAdapter(imageAdapter);
            for (int i=0; i<movieDatas.length;i++) {
                Log.v(TAG,"Movie Name:  "+ movieDatas[i].movieName);
            }
        }
    }

}

