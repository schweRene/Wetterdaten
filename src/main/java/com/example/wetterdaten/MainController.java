package com.example.wetterdaten;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainController {
    @FXML
    private Button berlinBtn;



    @FXML
    private Button closeBtn;



    @FXML
    private Button frankfurtBtn;

    @FXML
    private Button hhBtn;



    @FXML
    private BubbleChart<?, ?> incomeChart;



    @FXML
    private Button koelnBtn;

    @FXML
    private AnchorPane leftForm;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField weatherTxt;

    @FXML
    private Button munichBtn;

    @FXML
    private AnchorPane rightForm;

    public void close(){
        System.exit(0);
    }


    // API-Schlüssel für OpenWeatherMap
    private static final String API_KEY = "your_api_key_here";

    // URLs für API-Aufrufe
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String CITY_PARAM = "?q=";
    private static final String API_KEY_PARAM = "&appid=" + API_KEY;

    public void berlin(ActionEvent event){
        String city = "Berlin";
        String weatherData = getWeatherData(city);
        weatherTxt.setText(weatherData);
    }

    public void hamburg(ActionEvent event){
        String city = "Hamburg";
        String weatherData = getWeatherData(city);
        weatherTxt.setText(weatherData);
    }

    public void muenchen(ActionEvent event){
        String city = "München";
        String weatherData = getWeatherData(city);
        weatherTxt.setText(weatherData);
    }
    public void koeln(ActionEvent event){
        String city = "Köln";
        String weatherData = getWeatherData(city);
        weatherTxt.setText(weatherData);
    }
    public void frankfurt(ActionEvent event){
        String city = "Frankfurt/Main";
        String weatherData = getWeatherData(city);
        weatherTxt.setText(weatherData);
    }

    private String getWeatherData(String city){
        try {
            URL url = new URL(BASE_URL + CITY_PARAM + city + API_KEY_PARAM );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer reponse = new StringBuffer();

            while((inputLine = in.readLine()) != null){
                reponse.append(inputLine);
            }
            in.close();

            return reponse.toString();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return "";
    }


}
