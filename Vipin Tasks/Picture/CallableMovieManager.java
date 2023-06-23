package com.example.demo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CallableMovieManager {
    private static Connection connection = null;
    public void start() throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/globallogicsql";
            String username = "rutwik", password = "Coconut@764#";
            connection = DriverManager.getConnection(url, username, password);
    }

    public boolean addMovie (Picture movie) throws SQLException {
        try {
            start();
            CallableStatement statement = connection.prepareCall("CALL InsertPicture(?,?,?,?,?);");
            statement.setString(1, movie.getMovieId());
            statement.setString(2, movie.getMovieName());
            statement.setString(3, movie.getLanguage());
            statement.setInt(4, movie.getReleasedIn());
            statement.setInt(5, movie.getRevenueInDollars());
           // System.out.println(sql);
            boolean changed = statement.execute();
            return changed;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public List<Picture> getAllMovies(){
        List<Picture> allMovies = new ArrayList<>();
        try {
            start();
            Statement statement = connection.createStatement();
            String sql = "select * from picture";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Picture movie = new Picture();
                movie.setMovieId(resultSet.getString("movieId"));
                movie.setMovieName(resultSet.getString("movieName"));
                movie.setLanguage(resultSet.getString("language"));
                movie.setReleasedIn(resultSet.getInt("releasedIn"));
                movie.setRevenueInDollars(resultSet.getInt("revenueInDollars"));
                allMovies.add(movie);
            }
            return allMovies;
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return allMovies;
    }

    public int updateMovie(String movieId, int amount){
        try {
            start();
            Statement statement = connection.createStatement();
            String sql1 = String.format("UPDATE picture SET revenueInDollars = '%d' WHERE movieId = '%s'", amount, movieId);
            int changed = statement.executeUpdate(sql1);

            String sql2 = String.format("select * from picture where movieId = '%s'", movieId);
            ResultSet resultSet = statement.executeQuery(sql2);
            while (resultSet.next()) {
                Picture movie = new Picture();
                movie.setMovieId(resultSet.getString("movieId"));
                movie.setMovieName(resultSet.getString("movieName"));
                movie.setLanguage(resultSet.getString("language"));
                movie.setReleasedIn(resultSet.getInt("releasedIn"));
                movie.setRevenueInDollars(resultSet.getInt("revenueInDollars"));

                System.out.println("The Update Movie is : " + movie);
            }
            return changed;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public String deleteMovie(String movieId){
        try {
            start();
            Statement statement = connection.createStatement();
            String sql1 = String.format("DELETE FROM picture WHERE movieId = '%s'", movieId);
            int a = statement.executeUpdate(sql1);

            if (a > 0) {
                return "The Movie with id : " + movieId + " has been deleted successfully";
            } else {
                return "Unable to delete the mentioned movie with id : " + movieId + " Please Try again";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Unable to delete the mentioned movie with id : " + movieId + " Please Try again";

    }



}
