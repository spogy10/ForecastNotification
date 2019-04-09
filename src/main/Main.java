package main;

import JavaFXHelper.FXHelper;
import controller.WeatherViewController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Day;
import model.WeatherForecast;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class Main extends Application { //todo: delete json.txt

    public static Map<Day, LinkedList<WeatherForecast>> KINGSTON;
    public static Map<Day, LinkedList<WeatherForecast>> MOBAY;
    private static boolean dataReceived = false;

    public static void main(String[] args) {
        Weather w = new Weather("Kingston", "jm");
        KINGSTON = w.parseJSON();
        w = new Weather("Montego Bay", "jm");
        MOBAY = w.parseJSON();

        dataReceived = (KINGSTON.size() > 0 && MOBAY.size() > 0);
        if(dataReceived){
            System.out.println("Received weather");
        }else{
            System.out.println("Error receiving weather");
        }
        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        if(dataReceived) {
            Parent root = FXMLLoader.load(getClass().getResource(WeatherViewController.FXML));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);

            primaryStage.setTitle(WeatherViewController.TITLE);

            primaryStage.show();

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    event.consume();
                    try {
                        FXHelper.closeProgram(this, primaryStage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            SendEmail sendEmail = new SendEmail();
            sendEmail.sendMessages();
        }else{
            FXHelper.alertPopup(this, "Error", "Error receiving weather update");
        }
    }
}
