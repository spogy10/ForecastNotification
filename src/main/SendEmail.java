package main;

import JavaFXHelper.FXHelper;
import database.WorkerDatabaseManager;
import model.Day;
import model.WeatherForecast;
import model.Worker;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SendEmail {
    private List<Worker> workersList;
    private List<String> kingstonWorkersEmail, montegoBayWorkersEmail, kingstonITEmail, montegoBayITEmail;
    
    public SendEmail(){
        workersList = WorkerDatabaseManager.getInstance().retrieveAllWorkers();
        kingstonWorkersEmail = new LinkedList<>();
        montegoBayWorkersEmail = new LinkedList<>();
        kingstonITEmail = new LinkedList<>();
        montegoBayITEmail = new LinkedList<>();
        sortEmails();
    }
    
    private void sortEmails(){
        for(Worker w : workersList){
            if(w.getCity().getName().equals("Kingston")){
                kingstonWorkersEmail.add(w.getEmail());
                if(w.getRole().getName().equals("IT")){
                    kingstonITEmail.add(w.getEmail());
                }
            }else if(w.getCity().getName().equals("Montego Bay")){
                montegoBayWorkersEmail.add(w.getEmail());
                if(w.getRole().getName().equals("IT")){
                    montegoBayITEmail.add(w.getEmail());
                }
            }
        }
    }

    private void sendToKingstonWorkers(String message){ //TODO: add code to send emails
        System.out.println(message);
    }

    private void sendToKingstonITWorkers(String message){
        System.out.println(message);
    }

    private void sendToMontegoBayWorkers(String message){
        System.out.println(message);
    }

    private void sendToMontegoBayITWorkers(String message){
        System.out.println(message);
    }

    public void sendMessages(){

        sendMessages(Main.KINGSTON, true);
        sendMessages(Main.MOBAY, false);

        try {
            FXHelper.alertPopup(this, "Notifications", "Emails Sent.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendMessages(Map<Day, LinkedList<WeatherForecast>> weatherMap, boolean kingston){

        boolean rainAnyDay = false;
        boolean rainTomorrow = false;

        String sunnyMessage = "It's going to be sunny tomorrow, therefore an 8 hour work day.";
        String rainyMessage = "It's going to rain tomorrow, work will be cut to 4 hours.";
        String itMessage = "It's going to rain, so all IT personnel should not go on the streets.";

        int i = 0;
        for(Day day : weatherMap.keySet()){
            if( i == 1){
                for(WeatherForecast wf : weatherMap.get(day)){
                    if(wf.isRaining()){
                        rainAnyDay = true;
                        rainTomorrow = true;
                    }
                }
            }else{
                for(WeatherForecast wf : weatherMap.get(day)){
                    if(wf.isRaining()){
                        rainAnyDay = true;
                    }
                }
            }

            if( (i > 1) && rainAnyDay){
                break;
            }
            i++;
        }

        if(kingston){
            if(rainTomorrow){
                sendToKingstonWorkers(rainyMessage);
            }else{
                sendToKingstonWorkers(sunnyMessage);
            }

            if(rainAnyDay){
                sendToKingstonITWorkers(itMessage);
            }
        }else{
            if(rainTomorrow){
                sendToMontegoBayWorkers(rainyMessage);
            }else{
                sendToMontegoBayWorkers(sunnyMessage);
            }

            if(rainAnyDay){
                sendToMontegoBayITWorkers(itMessage);
            }
        }
    }

    public List<String> getKingstonWorkersEmail() {
        return kingstonWorkersEmail;
    }

    public List<String> getMontegoBayWorkersEmail() {
        return montegoBayWorkersEmail;
    }

    public List<String> getKingstonITEmail() {
        return kingstonITEmail;
    }

    public List<String> getMontegoBayITEmail() {
        return montegoBayITEmail;
    }
}
