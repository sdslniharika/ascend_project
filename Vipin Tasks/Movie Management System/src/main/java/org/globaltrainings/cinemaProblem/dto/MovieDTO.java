package org.globaltrainings.cinemaProblem.dto;

import org.globaltrainings.cinemaProblem.entity.Movie;

import java.util.Objects;

public class MovieDTO {
    private String movieId;
    private String movieName;
    private String language;
    private int releasedIn;
    private int revenueInDollars;

    public MovieDTO(String movieId, String movieName, String language, int releasedIn, int revenueInDollars){
        this.movieId = movieId;
        this.movieName = movieName;
        this.language = language;
        this.releasedIn = releasedIn;
        this.revenueInDollars = revenueInDollars;
    }

    public MovieDTO() {

    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getReleasedIn() {
        return releasedIn;
    }

    public void setReleasedIn(int releasedIn) {
        this.releasedIn = releasedIn;
    }

    public int getRevenueInDollars() {
        return revenueInDollars;
    }

    public void setRevenueInDollars(int revenueInDollars) {
        this.revenueInDollars = revenueInDollars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return this.movieId.equalsIgnoreCase(movie.getMovieId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", language='" + language + '\'' +
                ", releasedIn=" + releasedIn +
                ", revenueInDollars=" + revenueInDollars +
                '}';
    }
}
