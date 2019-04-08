package main;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Weather {
    private String city = "";
    private String countryCode = "";
    private final String baseUrl = "http://api.openweathermap.org/data/2.5/forecast?APPID=e3a4361d5129cb5aab34242bb4617c53&units=metric";

    public Weather(String city, String countryCode){
        this.city = city;
        this.countryCode = countryCode;
    }

    public String getJSON(){
        String json = "";
        StringBuilder buildUrl = new StringBuilder(baseUrl);
        buildUrl.append("&q=");
        buildUrl.append(city);
        buildUrl.append(",");
        buildUrl.append(countryCode);
        InputStream is = null;
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
}
