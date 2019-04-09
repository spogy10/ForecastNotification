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

    private static String getLocalJson(){ //todo: take out
        return "{\"cod\":\"200\",\"message\":0.0042,\"cnt\":38,\"list\":[{\"dt\":1554703200,\"main\":{\"temp\":24.65,\"temp_min\":21.23,\"temp_max\":24.65,\"pressure\":1015.47,\"sea_level\":1015.47,\"grnd_level\":989.21,\"humidity\":98,\"temp_kf\":3.42},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"clouds\":{\"all\":20},\"wind\":{\"speed\":1.97,\"deg\":70.007},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-08 06:00:00\"},{\"dt\":1554714000,\"main\":{\"temp\":23.62,\"temp_min\":21.06,\"temp_max\":23.62,\"pressure\":1014.96,\"sea_level\":1014.96,\"grnd_level\":988.67,\"humidity\":96,\"temp_kf\":2.57},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":24},\"wind\":{\"speed\":1.73,\"deg\":69.008},\"rain\":{\"3h\":0.025},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-08 09:00:00\"},{\"dt\":1554724800,\"main\":{\"temp\":23.8,\"temp_min\":22.09,\"temp_max\":23.8,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":989.68,\"humidity\":97,\"temp_kf\":1.71},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":44},\"wind\":{\"speed\":1.76,\"deg\":71.0009},\"rain\":{\"3h\":0.16},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-08 12:00:00\"},{\"dt\":1554735600,\"main\":{\"temp\":27.01,\"temp_min\":26.15,\"temp_max\":27.01,\"pressure\":1016.78,\"sea_level\":1016.78,\"grnd_level\":990.54,\"humidity\":80,\"temp_kf\":0.86},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":36},\"wind\":{\"speed\":2.42,\"deg\":90.0021},\"rain\":{\"3h\":0.15},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-08 15:00:00\"},{\"dt\":1554746400,\"main\":{\"temp\":28.39,\"temp_min\":28.39,\"temp_max\":28.39,\"pressure\":1015.34,\"sea_level\":1015.34,\"grnd_level\":989.12,\"humidity\":69,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":56},\"wind\":{\"speed\":3.21,\"deg\":106.009},\"rain\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-08 18:00:00\"},{\"dt\":1554757200,\"main\":{\"temp\":27.71,\"temp_min\":27.71,\"temp_max\":27.71,\"pressure\":1013.6,\"sea_level\":1013.6,\"grnd_level\":987.31,\"humidity\":69,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03d\"}],\"clouds\":{\"all\":32},\"wind\":{\"speed\":3.16,\"deg\":103.502},\"rain\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-08 21:00:00\"},{\"dt\":1554768000,\"main\":{\"temp\":25.21,\"temp_min\":25.21,\"temp_max\":25.21,\"pressure\":1014.19,\"sea_level\":1014.19,\"grnd_level\":987.99,\"humidity\":81,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.71,\"deg\":97.0028},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-09 00:00:00\"},{\"dt\":1554778800,\"main\":{\"temp\":22.36,\"temp_min\":22.36,\"temp_max\":22.36,\"pressure\":1015.59,\"sea_level\":1015.59,\"grnd_level\":989.37,\"humidity\":97,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":44},\"wind\":{\"speed\":2.52,\"deg\":86.5017},\"rain\":{\"3h\":0.005},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-09 03:00:00\"},{\"dt\":1554789600,\"main\":{\"temp\":22.5,\"temp_min\":22.5,\"temp_max\":22.5,\"pressure\":1014.54,\"sea_level\":1014.54,\"grnd_level\":988.29,\"humidity\":99,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":68},\"wind\":{\"speed\":2.29,\"deg\":83.5105},\"rain\":{\"3h\":0.05},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-09 06:00:00\"},{\"dt\":1554800400,\"main\":{\"temp\":22.35,\"temp_min\":22.35,\"temp_max\":22.35,\"pressure\":1013.48,\"sea_level\":1013.48,\"grnd_level\":987.32,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":80},\"wind\":{\"speed\":1.83,\"deg\":85.0063},\"rain\":{\"3h\":0.3},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-09 09:00:00\"},{\"dt\":1554811200,\"main\":{\"temp\":23.02,\"temp_min\":23.02,\"temp_max\":23.02,\"pressure\":1014.35,\"sea_level\":1014.35,\"grnd_level\":988.14,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":68},\"wind\":{\"speed\":1.92,\"deg\":90.0008},\"rain\":{\"3h\":0.16},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-09 12:00:00\"},{\"dt\":1554822000,\"main\":{\"temp\":25.96,\"temp_min\":25.96,\"temp_max\":25.96,\"pressure\":1014.75,\"sea_level\":1014.75,\"grnd_level\":988.59,\"humidity\":82,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":64},\"wind\":{\"speed\":2.66,\"deg\":111.504},\"rain\":{\"3h\":0.05},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-09 15:00:00\"},{\"dt\":1554832800,\"main\":{\"temp\":27.32,\"temp_min\":27.32,\"temp_max\":27.32,\"pressure\":1013.36,\"sea_level\":1013.36,\"grnd_level\":987.15,\"humidity\":73,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":68},\"wind\":{\"speed\":3.27,\"deg\":126},\"rain\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-09 18:00:00\"},{\"dt\":1554843600,\"main\":{\"temp\":26.67,\"temp_min\":26.67,\"temp_max\":26.67,\"pressure\":1011.67,\"sea_level\":1011.67,\"grnd_level\":985.61,\"humidity\":75,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":80},\"wind\":{\"speed\":2.81,\"deg\":131.5},\"rain\":{\"3h\":0.01},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-09 21:00:00\"},{\"dt\":1554854400,\"main\":{\"temp\":24.96,\"temp_min\":24.96,\"temp_max\":24.96,\"pressure\":1011.94,\"sea_level\":1011.94,\"grnd_level\":985.81,\"humidity\":82,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":36},\"wind\":{\"speed\":2.24,\"deg\":122.002},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-10 00:00:00\"},{\"dt\":1554865200,\"main\":{\"temp\":22.19,\"temp_min\":22.19,\"temp_max\":22.19,\"pressure\":1013.43,\"sea_level\":1013.43,\"grnd_level\":987.31,\"humidity\":96,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":36},\"wind\":{\"speed\":2.07,\"deg\":116.501},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-10 03:00:00\"},{\"dt\":1554876000,\"main\":{\"temp\":21.53,\"temp_min\":21.53,\"temp_max\":21.53,\"pressure\":1013.04,\"sea_level\":1013.04,\"grnd_level\":986.79,\"humidity\":96,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":56},\"wind\":{\"speed\":1.66,\"deg\":118.002},\"rain\":{\"3h\":0.01},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-10 06:00:00\"},{\"dt\":1554886800,\"main\":{\"temp\":21.02,\"temp_min\":21.02,\"temp_max\":21.02,\"pressure\":1012.29,\"sea_level\":1012.29,\"grnd_level\":986.16,\"humidity\":99,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":48},\"wind\":{\"speed\":1.42,\"deg\":106.002},\"rain\":{\"3h\":0.11},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-10 09:00:00\"},{\"dt\":1554897600,\"main\":{\"temp\":22.08,\"temp_min\":22.08,\"temp_max\":22.08,\"pressure\":1013.26,\"sea_level\":1013.26,\"grnd_level\":987.01,\"humidity\":98,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":56},\"wind\":{\"speed\":1.46,\"deg\":100.5},\"rain\":{\"3h\":0.06},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-10 12:00:00\"},{\"dt\":1554908400,\"main\":{\"temp\":26.57,\"temp_min\":26.57,\"temp_max\":26.57,\"pressure\":1014.19,\"sea_level\":1014.19,\"grnd_level\":987.97,\"humidity\":78,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":64},\"wind\":{\"speed\":2.01,\"deg\":120.506},\"rain\":{\"3h\":0.04},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-10 15:00:00\"},{\"dt\":1554919200,\"main\":{\"temp\":27.48,\"temp_min\":27.48,\"temp_max\":27.48,\"pressure\":1012.88,\"sea_level\":1012.88,\"grnd_level\":986.66,\"humidity\":71,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":64},\"wind\":{\"speed\":2.7,\"deg\":134},\"rain\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-10 18:00:00\"},{\"dt\":1554930000,\"main\":{\"temp\":26.98,\"temp_min\":26.98,\"temp_max\":26.98,\"pressure\":1011.2,\"sea_level\":1011.2,\"grnd_level\":985.06,\"humidity\":73,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":76},\"wind\":{\"speed\":2.46,\"deg\":139.003},\"rain\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-10 21:00:00\"},{\"dt\":1554940800,\"main\":{\"temp\":25.14,\"temp_min\":25.14,\"temp_max\":25.14,\"pressure\":1012.01,\"sea_level\":1012.01,\"grnd_level\":985.9,\"humidity\":82,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":80},\"wind\":{\"speed\":1.76,\"deg\":134.5},\"rain\":{\"3h\":0.01},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-11 00:00:00\"},{\"dt\":1554951600,\"main\":{\"temp\":23.86,\"temp_min\":23.86,\"temp_max\":23.86,\"pressure\":1013.5,\"sea_level\":1013.5,\"grnd_level\":987.31,\"humidity\":90,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":68},\"wind\":{\"speed\":1.71,\"deg\":120.005},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-11 03:00:00\"},{\"dt\":1554962400,\"main\":{\"temp\":22.84,\"temp_min\":22.84,\"temp_max\":22.84,\"pressure\":1012.9,\"sea_level\":1012.9,\"grnd_level\":986.69,\"humidity\":92,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":64},\"wind\":{\"speed\":1.66,\"deg\":118.001},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-11 06:00:00\"},{\"dt\":1554973200,\"main\":{\"temp\":21.85,\"temp_min\":21.85,\"temp_max\":21.85,\"pressure\":1012.24,\"sea_level\":1012.24,\"grnd_level\":986.08,\"humidity\":93,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":36},\"wind\":{\"speed\":1.52,\"deg\":108.003},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-11 09:00:00\"},{\"dt\":1554984000,\"main\":{\"temp\":22.18,\"temp_min\":22.18,\"temp_max\":22.18,\"pressure\":1013.54,\"sea_level\":1013.54,\"grnd_level\":987.31,\"humidity\":97,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":80},\"wind\":{\"speed\":1.66,\"deg\":96.0009},\"rain\":{\"3h\":0.76},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-11 12:00:00\"},{\"dt\":1554994800,\"main\":{\"temp\":24.23,\"temp_min\":24.23,\"temp_max\":24.23,\"pressure\":1015.12,\"sea_level\":1015.12,\"grnd_level\":988.96,\"humidity\":92,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":1.77,\"deg\":93.5013},\"rain\":{\"3h\":1.11},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-11 15:00:00\"},{\"dt\":1555005600,\"main\":{\"temp\":27.06,\"temp_min\":27.06,\"temp_max\":27.06,\"pressure\":1014.22,\"sea_level\":1014.22,\"grnd_level\":987.96,\"humidity\":76,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":88},\"wind\":{\"speed\":2.2,\"deg\":121.001},\"rain\":{\"3h\":0.06},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-11 18:00:00\"},{\"dt\":1555016400,\"main\":{\"temp\":26.63,\"temp_min\":26.63,\"temp_max\":26.63,\"pressure\":1012.79,\"sea_level\":1012.79,\"grnd_level\":986.57,\"humidity\":76,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":88},\"wind\":{\"speed\":2.46,\"deg\":122.001},\"rain\":{\"3h\":0.04},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-11 21:00:00\"},{\"dt\":1555027200,\"main\":{\"temp\":24.79,\"temp_min\":24.79,\"temp_max\":24.79,\"pressure\":1013.43,\"sea_level\":1013.43,\"grnd_level\":987.12,\"humidity\":83,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"clouds\":{\"all\":24},\"wind\":{\"speed\":2.11,\"deg\":109.003},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-12 00:00:00\"},{\"dt\":1555038000,\"main\":{\"temp\":22.07,\"temp_min\":22.07,\"temp_max\":22.07,\"pressure\":1014.9,\"sea_level\":1014.9,\"grnd_level\":988.66,\"humidity\":97,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"clouds\":{\"all\":24},\"wind\":{\"speed\":1.96,\"deg\":95.0029},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-12 03:00:00\"},{\"dt\":1555048800,\"main\":{\"temp\":21.13,\"temp_min\":21.13,\"temp_max\":21.13,\"pressure\":1014.36,\"sea_level\":1014.36,\"grnd_level\":988.14,\"humidity\":96,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":36},\"wind\":{\"speed\":2.05,\"deg\":81.0003},\"rain\":{\"3h\":0.03},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-12 06:00:00\"},{\"dt\":1555059600,\"main\":{\"temp\":20.53,\"temp_min\":20.53,\"temp_max\":20.53,\"pressure\":1013.57,\"sea_level\":1013.57,\"grnd_level\":987.36,\"humidity\":97,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"clouds\":{\"all\":20},\"wind\":{\"speed\":1.8,\"deg\":66.0018},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2019-04-12 09:00:00\"},{\"dt\":1555070400,\"main\":{\"temp\":21.91,\"temp_min\":21.91,\"temp_max\":21.91,\"pressure\":1014.68,\"sea_level\":1014.68,\"grnd_level\":988.45,\"humidity\":96,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03d\"}],\"clouds\":{\"all\":36},\"wind\":{\"speed\":1.76,\"deg\":61.5038},\"rain\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-12 12:00:00\"},{\"dt\":1555081200,\"main\":{\"temp\":27.38,\"temp_min\":27.38,\"temp_max\":27.38,\"pressure\":1015.68,\"sea_level\":1015.68,\"grnd_level\":989.43,\"humidity\":73,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":36},\"wind\":{\"speed\":2.17,\"deg\":87.5002},\"rain\":{\"3h\":0.0099999999999998},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-12 15:00:00\"},{\"dt\":1555092000,\"main\":{\"temp\":29.03,\"temp_min\":29.03,\"temp_max\":29.03,\"pressure\":1014.63,\"sea_level\":1014.63,\"grnd_level\":988.27,\"humidity\":66,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"clouds\":{\"all\":12},\"wind\":{\"speed\":2.66,\"deg\":98.0008},\"rain\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-12 18:00:00\"},{\"dt\":1555102800,\"main\":{\"temp\":29.24,\"temp_min\":29.24,\"temp_max\":29.24,\"pressure\":1012.94,\"sea_level\":1012.94,\"grnd_level\":986.46,\"humidity\":62,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"02d\"}],\"clouds\":{\"all\":8},\"wind\":{\"speed\":2.6,\"deg\":97.5122},\"rain\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-04-12 21:00:00\"}],\"city\":{\"id\":3489854,\"name\":\"Kingston\",\"coord\":{\"lat\":17.9712,\"lon\":-76.7929},\"country\":\"JM\",\"population\":937700}}";
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

    public Map<Day, LinkedList<WeatherForecast>> parseJSON(){
        Map<Day, LinkedList<WeatherForecast>> dayWeatherMap = new LinkedHashMap<>();
        WeatherForecast wf;
        //JSONObject jsonObject = new JSONObject(getJSON()); //todo: add this in
        JSONObject jsonObject = new JSONObject(getLocalJson()); //todo: take this out
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
