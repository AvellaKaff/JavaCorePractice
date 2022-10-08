package main.java.lesson7;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.lesson7.currentconditions.WeatherResponse;
import main.java.lesson7.enums.Periods;
import main.java.lesson7.forecast5days.Forecasts;
import main.java.lesson8.DatabaseRepository;
import main.java.lesson8.DatabaseRepositorySQLiteImpl;
import main.java.lesson8.WeatherData;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccuWeatherProvider implements WeatherProvider  {
    private static AccuWeatherProvider INSTANCE;

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST_ENDPOINT = "forecasts";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String DAYS = "5day";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();
    private static final String ACCEPT_LANGUAGE = "ru";
    private static final String METRIC = "true";

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    DatabaseRepository databaseRepository = new DatabaseRepositorySQLiteImpl();

    public static AccuWeatherProvider getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AccuWeatherProvider();
        }
        return INSTANCE;
    }

    @Override
    public WeatherData getWeather(Periods periods, String city) throws IOException {
        String cityKey = detectCityKey();
        WeatherData weatherData = new WeatherData();
        if (periods.equals(Periods.NOW)) {
            HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(CURRENT_CONDITIONS_ENDPOINT)
                .addPathSegment(API_VERSION)
                .addPathSegment(cityKey)
                .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", ACCEPT_LANGUAGE)
                .build();

            Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();

            Response response = client.newCall(request).execute();

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            String json = response.body().string();

            List<WeatherResponse> dailyList = objectMapper.readValue(json, new TypeReference<List<WeatherResponse>>() {});
            System.out.println(dailyList);

            weatherData.setLocalDate(dailyList.get(0).getLocalObservationDateTime());
            weatherData.setText(dailyList.get(0).getWeatherText());
            weatherData.setTemperature(dailyList.get(0).getTemperature().getMetric().getValue());
            weatherData.setCity(city);
            try {
                databaseRepository.saveWeatherData(weatherData);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if(periods.equals(Periods.FIVE_DAYS)){
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST_ENDPOINT)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(DAILY)
                    .addPathSegment(DAYS)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", ACCEPT_LANGUAGE)
                    .addQueryParameter("metric", METRIC)
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String json = response.body().string();

            Forecasts forecasts = objectMapper.readValue(json, Forecasts.class);
            String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();
            System.out.print(" В городе " + selectedCity);
            System.out.println(forecasts);
            for (int i = 0; i < 5; i++) {
                weatherData.setLocalDate(forecasts.getDailyForecasts().get(i).getDate());
                weatherData.setText(forecasts.getDailyForecasts().get(i).getDay().getIconPhrase());
                weatherData.setTemperature(forecasts.getDailyForecasts().get(i).getTemperature().getMaximum().getValue().doubleValue());
                weatherData.setCity(city);
                try {
                    databaseRepository.saveWeatherData(weatherData);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return weatherData;
    }

    @Override
    public List<WeatherData> getAllFromDb() throws IOException {
        try {
            return databaseRepository.getAllSavedData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String detectCityKey() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationURL = new HttpUrl.Builder()
            .scheme("http")
            .host(BASE_HOST)
            .addPathSegment("locations")
            .addPathSegment(API_VERSION)
            .addPathSegment("cities")
            .addPathSegment("autocomplete")
            .addQueryParameter("apikey", API_KEY)
            .addQueryParameter("q", selectedCity)
            .build();

        Request request = new Request.Builder()
            .addHeader("accept", "application/json")
            .url(detectLocationURL)
            .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("Произвожу поиск города " + selectedCity);

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            System.out.println("Найден город " + cityName + " в стране " + countryName);
        } else throw new IOException("Server returns 0 cities");

        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }

    public String detectCity() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationURL = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("locations")
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationURL)
                .build();
        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();
        String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
        return cityName;
    }
}
