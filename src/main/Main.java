package main;

import model.WeatherForecast;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Weather w = new Weather("Kingston", "jm");
        List<WeatherForecast> wf = w.parseJSON();

        for(WeatherForecast f : wf){
            System.out.println(f);
        }

    }


}
