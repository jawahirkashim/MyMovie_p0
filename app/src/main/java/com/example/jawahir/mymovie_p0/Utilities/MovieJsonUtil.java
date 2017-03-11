package com.example.jawahir.mymovie_p0.Utilities;

import android.nfc.Tag;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.Date;

/**
 * Created by jawahir on 1/3/17.
 */

public final class MovieJsonUtil {

    static String TAG = MovieJsonUtil.class.getSimpleName();
    static String imagebase = "http://image.tmdb.org/t/p/w185/";
    final static  String MOVIE_MESSAGE_CODE = "status_code";
    final static int HTTP_OK = 1;
    final static int HTTP_NOT_FOUND = 6;
    final static String RESULT = "results";

    public static MovieData[] getSimpleMoviListFromJson (String json) {
        String data[] = null;
        MovieData movieData[] = null;
        try {
            JSONObject jsonObject = new JSONObject(json);

            if(jsonObject.has(MOVIE_MESSAGE_CODE)) {
                int error_code = jsonObject.getInt(MOVIE_MESSAGE_CODE);
                switch (error_code) {
                    case HTTP_OK:
                        break;
                    case HTTP_NOT_FOUND:
                        return null;
                    default:
                        return null;
                }

            }
            JSONArray jsonArray = jsonObject.getJSONArray(RESULT);
            data = new String[jsonArray.length()];
            movieData = new MovieData[data.length];
            for (int i = 0; i <data.length ; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String poster_path = imagebase + jsonObj.getString("poster_path");
                String title = jsonObj.getString("title");
                String backdrop_path = jsonObj.getString("backdrop_path");
                double vote_average = jsonObj.getDouble("vote_average");
                String overview = jsonObj.getString("overview");
                data[i] = title+"\n "+overview;
                String releaseDate = jsonObj.getString("release_date");
                MovieData mdata = new MovieData(title,overview,poster_path, vote_average,releaseDate);
                movieData[i] = mdata;
                Log.v(TAG,"msg "+mdata.movieDescription+ " \n path  "+mdata.moviePath);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieData;
    }
}
