package com.example.demo;

import java.util.List;

public interface IMovieService {
    Picture addMovie(PictureDTO movieDTO);
    List<Picture> getMovies(int year);
    String updateRevenue(String movieId, int amount);
    String deleteMovie(String movieId);
}
