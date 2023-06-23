package org.globaltrainings.cinemaProblem.service;

import org.globaltrainings.cinemaProblem.dbManager.MovieManager;
import org.globaltrainings.cinemaProblem.dto.MovieDTO;
import org.globaltrainings.cinemaProblem.entity.Movie;
import org.globaltrainings.cinemaProblem.exceptions.MovieNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieServiceImpl implements IMovieService{
    private MovieManager movieManager = new MovieManager();
    @Override
    public Movie addMovie(MovieDTO movieDTO) {
        try {
            Movie movie = new Movie(movieDTO.getMovieId(), movieDTO.getMovieName(), movieDTO.getLanguage(), movieDTO.getReleasedIn(), movieDTO.getRevenueInDollars());
            int yes = movieManager.addMovie(movie);
            if(yes<=0){
                throw new MovieNotFoundException("Unable to Add Movie. Please Try again");
            }
            return movie;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (MovieNotFoundException movieNotFoundException){
            System.out.println(movieNotFoundException.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new Movie();
    }

    @Override
    public List<Movie> getMovies(int year) {
        List<Movie> moviesInYear = new ArrayList<>();
        try {
            List<Movie> movies = movieManager.getAllMovies();
            if (movies.isEmpty()) {
                throw new MovieNotFoundException("There are no Movies available");
            }
            moviesInYear = movies.stream().filter(movie -> movie.getReleasedIn() == year).collect(Collectors.toList());
            if (moviesInYear.isEmpty()) {
                throw new MovieNotFoundException("No Movie was released in that year");
            }
            return moviesInYear;
        }catch(MovieNotFoundException movieNotFoundException){
            System.out.println(movieNotFoundException.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return moviesInYear;
    }

    @Override
    public void updateRevenue(String movieId, int amount) {
        try {
            int yes = movieManager.updateMovie(movieId, amount);
            if (yes <= 0) {
                throw new MovieNotFoundException("Unable to update, Please try again");
            }
        }catch (MovieNotFoundException movieNotFoundException){
            System.out.println(movieNotFoundException.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String deleteMovie(String movieId) {
        return movieManager.deleteMovie(movieId);
    }
}
