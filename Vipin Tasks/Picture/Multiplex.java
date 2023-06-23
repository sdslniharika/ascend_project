package com.example.demo;

import java.util.List;

public class Multiplex {
    public void run(){
        MovieServiceImpl service = new MovieServiceImpl();

        PictureDTO dt1 = new PictureDTO("M104", "Terminator", "English", 1992, 10000);
        PictureDTO dt2 = new PictureDTO("M105", "Titanic", "English", 1996, 18900);
        PictureDTO dt3 = new PictureDTO("M106", "Gladiator", "English", 2001, 9100);
        PictureDTO dt4 = new PictureDTO("M107", "Avatar", "English", 2010, 20000);
        PictureDTO dt5 = new PictureDTO("M108", "Avengers", "English", 1992, 6800);
        PictureDTO dt6 = new PictureDTO("M109", "2012", "English", 2010, 4500);

        System.out.println("----------------------------------Add Movies --------------------------------------------");
        Picture m1 = service.addMovie(dt1);
        displayMovieAdded(m1);
        Picture m2 = service.addMovie(dt2);
        displayMovieAdded(m2);
        Picture m3 = service.addMovie(dt3);
        displayMovieAdded(m3);
        Picture m4 = service.addMovie(dt4);
        displayMovieAdded(m4);
        Picture m5 = service.addMovie(dt5);
        displayMovieAdded(m5);
        Picture m6 = service.addMovie(dt6);
        displayMovieAdded(m6);
        System.out.println();

        System.out.println("----------------------------------Get Movies by Year-------------------------------------");
        List<Picture> movies = service.getMovies(2010);
        displayListOfMovies(movies);
        System.out.println();

        System.out.println("----------------------------------Update Movies by ID-------------------------------------");
        String message = service.updateRevenue("M106", 5500);
        System.out.println(message);
        System.out.println();

        System.out.println("----------------------------------Delete Movies by ID-------------------------------------");
        String delete = service.deleteMovie("M103");
        System.out.println(delete);


    }

    public void displayMovieAdded(Picture movie){
        System.out.println();
        System.out.println("The Movie added is : "+movie);
    }

    public void displayListOfMovies(List<Picture> movieList){
        System.out.println();
        System.out.println("The List of movies with selected search criteria are : \n");
        for(Picture m: movieList){
            System.out.println("    "+m);
        }
    }



}
