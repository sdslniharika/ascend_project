package org.globaltrainings.cinemaProblem.service;

import org.globaltrainings.cinemaProblem.dto.MovieDTO;
import org.globaltrainings.cinemaProblem.entity.Movie;

import java.util.List;

public interface IMovieService {
    Movie addMovie(MovieDTO movieDTO);
    List<Movie> getMovies(int year);
    void updateRevenue(String movieId, int amount);
    String deleteMovie(String movieId);
}
