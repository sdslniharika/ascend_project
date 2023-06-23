package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieServiceImpl implements IMovieService{
    private StatementMovieManager movieManager = new StatementMovieManager();
    private PreparedMovieManager preparedMovieManager = new PreparedMovieManager();
    private CallableMovieManager callableMovieManager = new CallableMovieManager();
    @Override
    public Picture addMovie(PictureDTO movieDTO) {
        try {
            Picture movie = new Picture(movieDTO.getMovieId(), movieDTO.getMovieName(), movieDTO.getLanguage(), movieDTO.getReleasedIn(), movieDTO.getRevenueInDollars());
           // int yes = movieManager.addMovie(movie);
            int yes = preparedMovieManager.addMovie(movie);
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
        return new Picture();
    }

    @Override
    public List<Picture> getMovies(int year) {
        List<Picture> moviesInYear = new ArrayList<>();
        try {
          //  List<Picture> movies = movieManager.getAllMovies();
            List<Picture> movies = preparedMovieManager.getAllMovies();
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
    public String updateRevenue(String movieId, int amount) {
        try {
            //int yes = movieManager.updateMovie(movieId, amount);
            int yes = preparedMovieManager.updateMovie(movieId, amount);
            if (yes <= 0) {
                throw new MovieNotFoundException("Unable to update, Please try again");
            }
            return "Movie revenvue has been updated for movie with id :  "+movieId;
        }catch (MovieNotFoundException movieNotFoundException){
            System.out.println(movieNotFoundException.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "Unable to update the movie";
    }

    @Override
    public String deleteMovie(String movieId) {
        //return movieManager.deleteMovie(movieId);
        return preparedMovieManager.deleteMovie(movieId);
    }
}
