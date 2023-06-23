package com.example.demo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PreparedMovieManager {
    private static Connection connection = null;
    public void start() throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/globallogicsql";
            String username = "rutwik", password = "Coconut@764#";
            connection = DriverManager.getConnection(url, username, password);
    }

    public int addMovie (Picture movie) throws SQLException {
        try {
            start();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO picture(movieId,movieName,language,releasedIn,revenueInDollars) values(?,?,?,?,?)");
            preparedStatement.setString(1, movie.getMovieId());
            preparedStatement.setString(2, movie.getMovieName());
            preparedStatement.setString(3, movie.getLanguage());
            preparedStatement.setInt(4, movie.getReleasedIn());
            preparedStatement.setInt(5, movie.getRevenueInDollars());
            int changed = preparedStatement.executeUpdate();
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

    public List<Picture> getAllMovies(){
        List<Picture> allMovies = new ArrayList<>();
        try {
            start();
            PreparedStatement statement = connection.prepareStatement("select * from picture");
            ResultSet resultSet = statement.executeQuery();

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
            PreparedStatement statement = connection.prepareStatement("UPDATE picture SET revenueInDollars = ? WHERE movieId = ?");
            statement.setInt(1, amount);
            statement.setString(2, movieId);
            int changed = statement.executeUpdate();
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM picture WHERE movieId = ?");
            statement.setString(1, movieId);
            int a = statement.executeUpdate();

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
