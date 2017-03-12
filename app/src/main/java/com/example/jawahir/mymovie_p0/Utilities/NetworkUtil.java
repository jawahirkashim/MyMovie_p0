package com.example.jawahir.mymovie_p0.Utilities;

import android.net.Uri;

import com.example.jawahir.mymovie_p0.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by jawahir on 8/3/17.
 */

public class NetworkUtil {
    private static String BASE_URL = "http://api.themoviedb.org/3/movie";
    private static String Key_param = "api_key";
    private static String sortPopular = "popular";
    private static String sortTop = "top_rated";
    private static String key = "PLEASE collect an auth key from https://www.themoviedb.org/ ";//SensitiveData.Key;


    public static URL buildUrl( MainActivity.movieType type) {
        String sorttype = null;
        switch (type) {
            case POPULAR:
                sorttype = sortPopular;
                break;
            case TOP:
                sorttype = sortTop;
                break;

        }
        Uri builtUri = Uri.parse(BASE_URL).buildUpon().appendPath(sorttype).
                appendQueryParameter(Key_param,key).
                build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
