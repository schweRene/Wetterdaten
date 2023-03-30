package com.example.wetterstation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




public class MainController {
    @FXML
    private Button berlinBtn;

    @FXML
    private TextField blnTxt;

    @FXML
    private Button closeBtn;

    @FXML
    private TextField fraTxt;

    @FXML
    private Button frankfurtBtn;

    @FXML
    private Button hhBtn;

    @FXML
    private TextField hhTxt;

    @FXML
    private TextField koTxt;

    @FXML
    private Button koelnBtn;

    @FXML
    private TextField muTxt;

    @FXML
    private Button munichBtn;

    @FXML
    private ImageView sunImg;

    @FXML
    private ImageView thunderImg;


    public void berlin(ActionEvent event){
        String city = "Berlin";
        String weatherdata = getWeatherData(city);
        blnTxt.setText(weatherdata);
    }

    public void hamburg(ActionEvent event){
        String city = "Hamburg";
        String weatherdata = getWeatherData(city);
        hhTxt.setText(weatherdata);
    }

    public void muenchen(ActionEvent event){
        String city = "München";
        String weatherdata = getWeatherData(city);
        muTxt.setText(weatherdata);
    }
    public void koeln(ActionEvent event){
        String city = "Köln";
        String weatherdata = getWeatherData(city);
        koTxt.setText(weatherdata);
    }
    public void frankfurt(ActionEvent event){
        String city = "Frankfurt/Main";
        String weatherdata = getWeatherData(city);
        fraTxt.setText(weatherdata);
    }

    private String getWeatherData(String city){
        try {
            String apiUrl = "https://dwd.api.bund.dev/api/v1/weather/report/" + city;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            //Parsen der JSON-Antwort von der API
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject temperatureObject = jsonResponse.getJSONObject("temperatures");
            double temperature = temperatureObject.getDouble("airTemperature");
            double feelsLike = temperatureObject.getDouble(feelsLike);
            double humidity = jsonResponse.getDouble("humidity");

            //Anzeigen der Wetterdaten im entsprechendem Textfeld
            switch (city){
                case "Berlin":
                    blnTxt.setText("Temperatur: " + temperature +
                                " K\nGefühlte Temperatur: " + feelsLike +
                                " K\n Luftfeuchtigkeit: " + humidity + "%");
                    break;
                case "Hamburg":
                    hhTxt.setText("Temperatur: " + temperature +
                            " K\nGefühlte Temperatur: " + feelsLike +
                            " K\n Luftfeuchtigkeit: " + humidity + "%");
                    break;
                case "München":
                    muTxt.setText("Temperatur: " + temperature +
                            " K\nGefühlte Temperatur: " + feelsLike +
                            " K\n Luftfeuchtigkeit: " + humidity + "%");
                    break;
                case "Köln":
                    koTxt.setText("Temperatur: " + temperature +
                            " K\nGefühlte Temperatur: " + feelsLike +
                            " K\n Luftfeuchtigkeit: " + humidity + "%");
                    break;
                case "Frankfurt/Main":
                    fraTxt.setText("Temperatur: " + temperature +
                            " K\nGefühlte Temperatur: " + feelsLike +
                            " K\n Luftfeuchtigkeit: " + humidity + "%");
                    break;



        }
        }catch (IOException | JSONExeption e){                         //| JSONExeption e
            e.printStackTrace();
        }


    }



    public void close(){
        System.exit(0);
    }
}
