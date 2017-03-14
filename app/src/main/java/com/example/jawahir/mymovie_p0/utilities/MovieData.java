package com.example.jawahir.mymovie_p0.utilities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jawahir on 5/3/17.
 */

public class MovieData implements Parcelable{

    private String moviePath;
    private String movieDescription;
    private String movieName;
    private String rating;
    private String releaseDate;
    public MovieData (String movieName, String movieDescription, String moviePath, String rating, String releaseDate) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.moviePath = moviePath;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public MovieData (Parcel in) {
        String data[] = new String[5];
        in.readStringArray(data);
        this.movieName = data[0];
        this.movieDescription = data[1];
        this.moviePath = data[2];
        this.rating = data[3];
        this.releaseDate = data[4];
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.movieName,
                this.movieDescription,
                this.moviePath,
                this.rating,
                this.releaseDate});
    }
    static final Parcelable.Creator<MovieData> CREATOR = new Parcelable.Creator<MovieData>() {
                                                            public  MovieData createFromParcel(Parcel in) {
                                                                return new MovieData(in);
                                                            }
                                                            public MovieData[] newArray(int size) {
                                                                return new MovieData[size];
                                                            }
                                                        };

    public String getMoviePath() {
        return moviePath;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}