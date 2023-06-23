package org.globaltrainings.cinemaProblem.frontEnd;

import org.globaltrainings.cinemaProblem.dto.MovieDTO;
import org.globaltrainings.cinemaProblem.entity.Movie;
import org.globaltrainings.cinemaProblem.service.MovieServiceImpl;

import java.util.List;

public class Multiplex {
    public void run(){
        MovieServiceImpl service = new MovieServiceImpl();

        MovieDTO dt1 = new MovieDTO("M104", "Terminator", "English", 1992, 10000);
        MovieDTO dt2 = new MovieDTO("M105", "Titanic", "English", 1996, 18900);
        MovieDTO dt3 = new MovieDTO("M106", "Gladiator", "English", 2001, 9100);
        MovieDTO dt4 = new MovieDTO("M107", "Avatar", "English", 2010, 20000);
        MovieDTO dt5 = new MovieDTO("M108", "Avengers", "English", 1992, 6800);
        MovieDTO dt6 = new MovieDTO("M109", "2012", "English", 2010, 4500);

        System.out.println("----------------------------------Add Movies --------------------------------------------");
        Movie m1 = service.addMovie(dt1);
        displayMovieAdded(m1);
        Movie m2 = service.addMovie(dt2);
        displayMovieAdded(m2);
        Movie m3 = service.addMovie(dt3);
        displayMovieAdded(m3);
        Movie m4 = service.addMovie(dt4);
        displayMovieAdded(m4);
        Movie m5 = service.addMovie(dt5);
        displayMovieAdded(m5);
        Movie m6 = service.addMovie(dt6);
        displayMovieAdded(m6);
        System.out.println();

        System.out.println("----------------------------------Get Movies by Year-------------------------------------");
        List<Movie> movies = service.getMovies(2010);
        displayListOfMovies(movies);
        System.out.println();

        System.out.println("----------------------------------Update Movies by ID-------------------------------------");
        service.updateRevenue("M106", 5500);
        System.out.println();

        System.out.println("----------------------------------Delete Movies by ID-------------------------------------");
        String delete = service.deleteMovie("M103");
        System.out.println(delete);


    }

    public void displayMovieAdded(Movie movie){
        System.out.println();
        System.out.println("The Movie added is : "+movie);
    }

    public void displayListOfMovies(List<Movie> movieList){
        System.out.println();
        System.out.println("The List of movies with selected search criteria are : \n");
        for(Movie m: movieList){
            System.out.println("    "+m);
        }
    }



}
