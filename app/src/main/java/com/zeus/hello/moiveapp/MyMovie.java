package com.zeus.hello.moiveapp;

/**
 * Created by zhou on 2017/1/17.
 */

public class MyMovie  {


    public String getMovieName() {
        return movieName;
    }

    public String getMovieType() {
        return movieType;
    }

    public String getLanguage() {
        return language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public String movieName;
    public String movieType;
    public String releaseDate;
    public String language;
    public String country;
    public String title;
    public String link;

    public MyMovie(String link, String title, String imageLink, String date, String info) {
        this.link = link;
        this.title = title;
        this.imageLink = imageLink;
        this.date = date;
        this.info = info;
    }

    public String imageLink;
    public String date;
    public String info;

    public int getRuntime() {
        return runtime;
    }



    private int runtime;
}
