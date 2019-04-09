package controller;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import main.Weather;
import model.WeatherForecast;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class WeatherViewItemController implements Initializable {
    public final static String FXML = "/view/weather_view_item.fxml";
    public static final String TITLE = "";

    @FXML private ImageView ivIcon;
    @FXML private Label lDescription;
    @FXML private VBox vbox;

    private WeatherForecast wf;

    public WeatherViewItemController(){

    }

    public WeatherViewItemController(WeatherForecast wf){
        setVariable(wf);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setVariable(WeatherForecast wf){
        this.wf = wf;
        ivIcon.setImage(Weather.getImage(wf.getIconFileName()));
        lDescription.setText(wf.getDescription());
    }

    public Node getVBcx(){
        return vbox;
    }
}
