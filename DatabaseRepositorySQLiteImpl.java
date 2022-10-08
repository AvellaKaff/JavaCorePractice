package main.java.lesson8;

import main.java.lesson7.ApplicationGlobalState;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class DatabaseRepositorySQLiteImpl implements DatabaseRepository{
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

     String filename = null;
     String createTableQuery = "CREATE TABLE IF NOT EXISTS weather " +
             "(  \n" +
             "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
             "city TEXT NOT NULL, \n" +
             "date TEXT NOT NULL, \n" +
             "weather_text TEXT NOT NULL, \n" +
             "temperature REAL NOT NULL \n" +
            "); ";
     String insertWeatherQuery = "INSERT INTO weather (city, date, weather_text, temperature) VALUES (?,?,?,?)";
     public DatabaseRepositorySQLiteImpl() { filename = ApplicationGlobalState.getInstance().getDBFileName();}

     private Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:"+filename);
                return connection;
     }

     private void createTableIfNotExists() {
         try ( Connection connection = getConnection()){
                connection.createStatement().execute(createTableQuery);
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
     }


    @Override
    public boolean saveWeatherData(WeatherData weatherData) throws SQLException {
         createTableIfNotExists();
     try ( Connection connection = getConnection();
       PreparedStatement saveWeather = connection.prepareStatement(insertWeatherQuery)) {
        saveWeather.setString(1, weatherData.getCity());
        saveWeather.setString(2, weatherData.getLocalDate());
        saveWeather.setString(3, weatherData.getText());
        saveWeather.setDouble(4, weatherData.getTemperature());
        return saveWeather.execute();
     }catch (SQLException ex){
         ex.printStackTrace();
     } throw new SQLException("Failure on saving weather object");
    }

    @Override
    public List<WeatherData> getAllSavedData() throws IOException, SQLException {
         Statement statement = getConnection().createStatement();
         ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM weather");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<WeatherData> weatherDataList = new ArrayList<WeatherData>();
        while (resultSet.next()){
            System.out.println(
                resultSet.getInt(1) + " - " +
                        resultSet.getString(2)+ " - " +
                        resultSet.getString(3)+ " - " +
                        resultSet.getString(4)+ " - " +
                        resultSet.getDouble(5)
            );
            weatherDataList.add(new WeatherData(resultSet.getString(2),resultSet.getString(3), resultSet.getString(4),resultSet.getDouble(5)));
        }
        System.out.println("");
        return weatherDataList;
    }
}


