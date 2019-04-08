package model;

import java.util.Calendar;
import java.util.Date;

public class WeatherForecast {
    private Day day;
    private Date date;
    private String mainWeather;
    private String description;
    private String iconFileName;

    public WeatherForecast(long epoch, String mainWeather, String description, String iconFileName){
        date = new Date(epoch*1000L);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        day = Day.getDayFromIndex(c.get(Calendar.DAY_OF_WEEK));
        this.mainWeather = mainWeather;
        this.description = description;
        this.iconFileName = iconFileName+".png";
    }

    public boolean isRaining(){
        return (mainWeather.equals("Thunderstorm") || mainWeather.equals("Drizzle") || mainWeather.equals("Rain"));
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        day = Day.getDayFromIndex(c.get(Calendar.DAY_OF_WEEK));
    }

    public Day getDay() {
        return day;
    }

    public String getMainWeather() {
        return mainWeather;
    }

    public void setMainWeather(String mainWeather) {
        this.mainWeather = mainWeather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconFileName() {
        return iconFileName;
    }

    public void setIconFileName(String iconFileName) {
        this.iconFileName = iconFileName;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "day=" + day +
                ", date=" + date +
                ", mainWeather='" + mainWeather + '\'' +
                ", description='" + description + '\'' +
                ", iconFileName='" + iconFileName + '\'' +
                '}';
    }
}
