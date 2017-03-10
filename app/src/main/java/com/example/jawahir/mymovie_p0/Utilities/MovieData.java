package com.example.jawahir.mymovie_p0.Utilities;

import java.io.Serializable;

/**
 * Created by jawahir on 5/3/17.
 */

public class MovieData implements Serializable{

    public String moviePath;
    public String movieDescription;
    public String movieName;
    public double rating;
    public MovieData (String movieName, String movieDescription, String moviePath, double rating) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.moviePath = moviePath;
        this.rating = rating;
    }
}