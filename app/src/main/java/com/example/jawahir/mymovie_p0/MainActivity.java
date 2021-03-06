package com.example.jawahir.mymovie_p0;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jawahir.mymovie_p0.utilities.MovieData;
import com.example.jawahir.mymovie_p0.utilities.MovieJsonUtil;
import com.example.jawahir.mymovie_p0.utilities.NetworkUtil;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    GridView gridView;
    ProgressBar progressBar;
    TextView textViewErrorMsg;
    public  enum movieType {
        TOP,
        POPULAR
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the id of all the view
        gridView = (GridView) findViewById(R.id.gv_movie);
        progressBar = (ProgressBar) findViewById(R.id.pb_movie);
        textViewErrorMsg = (TextView) findViewById(R.id.tv_error_msg);
        progressBar.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.INVISIBLE);
        textViewErrorMsg.setVisibility(View.INVISIBLE);
        loadData(movieType.POPULAR);
    }

    private void loadData(movieType type) {
        URL url = NetworkUtil.buildUrl(type);
        if (isOnline()) {
            new MoviAsyncTask().execute(url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int iteamThatwasClicked = item.getItemId();

        if (iteamThatwasClicked == R.id.m_popular) {
            loadData(movieType.POPULAR);
        } else if (iteamThatwasClicked == R.id.m_top) {
            loadData(movieType.TOP);
        }
        return super.onOptionsItemSelected(item);
    }
    private void showData() {
        progressBar.setVisibility(View.INVISIBLE);
        textViewErrorMsg.setVisibility(View.INVISIBLE);
        gridView.setVisibility(View.VISIBLE);
    }

    private void showError () {
        progressBar.setVisibility(View.INVISIBLE);
        gridView.setVisibility(View.INVISIBLE);
        textViewErrorMsg.setVisibility(View.VISIBLE);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
    class MoviAsyncTask extends AsyncTask<URL,Void,MovieData[]> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected MovieData[] doInBackground(URL... params) {
            URL url = params[0];
            MovieData data[] = null;
            String jsonString = null;

            try {
                jsonString = NetworkUtil.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (jsonString != null) {
                data = MovieJsonUtil.getSimpleMoviListFromJson(jsonString);
            }


            return data;
        }

        @Override
        protected void onPostExecute(MovieData[] movieDatas) {
            progressBar.setVisibility(View.INVISIBLE);
            if (movieDatas != null ) {
                showData();
                //set movie data and images to each view
                ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this,movieDatas);
                gridView.setAdapter(imageAdapter);
                for (int i=0; i<movieDatas.length;i++) {
                    Log.v(TAG,"Movie Name:  "+ movieDatas[i].getMovieName());
                }
            } else {
                showError();
                Log.v(TAG,"Data is not available");
            }


        }
    }


}

