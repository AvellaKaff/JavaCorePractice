package main.java.lesson7;



import main.java.lesson7.enums.Periods;
import main.java.lesson8.WeatherData;

import java.io.IOException;
import java.util.List;

public interface WeatherProvider {

    WeatherData getWeather(Periods periods, String city) throws IOException;
    List<WeatherData> getAllFromDb() throws IOException;

}
