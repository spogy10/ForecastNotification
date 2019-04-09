package main;

import javafx.scene.image.Image;
import model.Day;
import model.WeatherForecast;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Weather {
    private String city;
    private String countryCode;
    private final String baseUrl = "http://api.openweathermap.org/data/2.5/forecast?APPID=e3a4361d5129cb5aab34242bb4617c53&units=metric";

    public Weather(String city, String countryCode){
        this.city = city;
        this.countryCode = countryCode;
    }

    private String getJSON(){
        String json = "";
        StringBuilder buildUrl = new StringBuilder(baseUrl);
        buildUrl.append("&q=");
        buildUrl.append(city);
        buildUrl.append(",");
        buildUrl.append(countryCode);
        InputStream is;
        try {
            URL url = new URL(buildUrl.toString());
            HttpURLConnection in = (HttpURLConnection) url.openConnection();
            in.addRequestProperty("User-Agent", "Foo?");

            in.setReadTimeout(0 /* milliseconds */);
            in.setConnectTimeout(0 /* milliseconds */);
            in.setRequestMethod("GET");
            in.setDoInput(true);
            in.connect();

            is = in.getInputStream();

            json = IOUtils.toString(is, "UTF-8");
        }catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static Image getImage(String fileName){
        Image image = null;
        try {
            image = new Image(new FileInputStream("images\\"+fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return image;
    }

    Map<Day, LinkedList<WeatherForecast>> parseJSON(){
        Map<Day, LinkedList<WeatherForecast>> dayWeatherMap = new LinkedHashMap<>();
        WeatherForecast wf;
        JSONObject jsonObject = new JSONObject(getJSON());
        JSONArray weatherList = jsonObject.getJSONArray("list");
        for(Object object : weatherList){
            JSONObject indexObject = (JSONObject) object;
            JSONArray weatherArray = indexObject.getJSONArray("weather");
            JSONObject weatherObject = weatherArray.getJSONObject(0);


            wf = new WeatherForecast(indexObject.getLong("dt"),
                    weatherObject.getString("main"),
                    weatherObject.getString("description"),
                    weatherObject.getString("icon"));

            dayWeatherMap.computeIfAbsent(wf.getDay(), l -> new LinkedList<>());
            dayWeatherMap.get(wf.getDay()).add(wf);
        }

        return dayWeatherMap;
    }
}
