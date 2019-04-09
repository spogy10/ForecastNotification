package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import main.Main;
import model.Day;
import model.WeatherForecast;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;

public class WeatherViewController implements Initializable {
    public static final String FXML = "/view/weather_view.fxml";
    public static final String TITLE = "Weather Forecast";

    private static Map<Day, LinkedList<WeatherForecast>> weatherMap;

    @FXML private GridPane gridPane;

    @FXML private Label lDay1, lDay2, lDay3, lDay4, lDay5, lDay6;

    @FXML private ComboBox<String> cbCity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] cities = new String[]{"Kingston", "Montego Bay"};
        cbCity.getItems().addAll(cities);
        cbCity.setValue("Kingston");
        weatherMap = Main.KINGSTON;
        setCells();
    }

    private void setCells() {
        int x = 1;
        Label[] labels = new Label[]{lDay1, lDay2, lDay3, lDay4, lDay5, lDay6};
        for(Day day : weatherMap.keySet()){
            labels[x - 1].setText(day.toString());
            for(WeatherForecast wf : weatherMap.get(day)){
                Calendar c = Calendar.getInstance();
                c.setTime(wf.getDate());
                switch (c.get(Calendar.HOUR_OF_DAY)){
                    case 1: setCell(x, 1, wf);
                    break;
                    case 4: setCell(x, 2, wf);
                        break;
                    case 7: setCell(x, 3, wf);
                        break;
                    case 10: setCell(x, 4, wf);
                        break;
                    case 13: setCell(x, 5, wf);
                        break;
                    case 16: setCell(x, 6, wf);
                        break;
                    case 19: setCell(x, 7, wf);
                        break;
                    case 22: setCell(x, 8, wf);
                        break;
                }
            }
            x++;
        }
    }

    private void setCell(int x, int y, WeatherForecast wf){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(WeatherViewItemController.FXML));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WeatherViewItemController item = loader.getController();

        item.setVariable(wf);
        gridPane.add(item.getVBcx(), x, y);
    }

    private void resetGrid(){
        gridPane.getChildren().removeIf(node -> node instanceof VBox);
    }

    @FXML
    private void cityChange() {
        resetGrid();
        if(cbCity.getValue().equals("Kingston")){
            weatherMap = Main.KINGSTON;
            setCells();
        }else if(cbCity.getValue().equals("Montego Bay")){
            weatherMap = Main.MOBAY;
            setCells();
        }
    }
}
