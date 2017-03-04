package com.example.jawahir.mymovie_p0;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.example.jawahir.mymovie_p0.Utilities.MovieJsonUtil;
import com.example.jawahir.mymovie_p0.Utilities.NetworkUtil;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    GridView gridView = (GridView) findViewById(R.id.gv_id);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //get the id of all the view

        loadData();
    }

    private void loadData() {
        URL url = NetworkUtil.builtURL("test");
        new MoviAsyncTask().execute(url);
    }
    class MoviAsyncTask extends AsyncTask<URL,Void,String[]> {

        @Override
        protected String[] doInBackground(URL... params) {
            URL url = params[0];
            String jsonString = NetworkUtil.getResponseFromHttpURL(url);
            String data[] = MovieJsonUtil.getSimpleMoviListFromJson(jsonString);

            return data;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            //set movie data and images to each view
            gridView.setAdapter(new ImageAdapter( strings));
            for (int i=0; i<strings.length;i++) {
                Log.v(TAG,"msg "+ strings[i]);
            }
        }
    }
}

