package main;

import JavaFXHelper.FXHelper;
import com.sendgrid.*;
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

    private void sendToKingstonWorkers(String message){
        System.out.println("Email to Kingston Workers: "+message);

        String sender = "boss@boss.com";
        String subject = "Attention Kingston Workers";
        for(String email : kingstonWorkersEmail){
            sendEmail(sender, subject, email, message);
        }
    }

    private void sendToKingstonITWorkers(String message){
        System.out.println("Email to Kingston IT Workers: "+message);

        String sender = "boss@boss.com";
        String subject = "Attention Kingston IT Workers";
        for(String email : kingstonITEmail){
            sendEmail(sender, subject, email, message);
        }
    }

    private void sendToMontegoBayWorkers(String message){
        System.out.println("Email to Montego Bay Workers: "+message);

        String sender = "boss@boss.com";
        String subject = "Attention Montego Bay Workers";
        for(String email : montegoBayWorkersEmail){
            sendEmail(sender, subject, email, message);
        }
    }

    private void sendToMontegoBayITWorkers(String message){
        System.out.println("Email to Montego Bay IT Workers: "+message);

        String sender = "boss@boss.com";
        String subject = "Attention Montego Bay IT Workers";
        for(String email : montegoBayITEmail){
            sendEmail(sender, subject, email, message);
        }
    }

    void sendMessages(){

        sendMessages(Main.KINGSTON, true);
        sendMessages(Main.MOBAY, false);

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

    private static Response sendEmail(String sender, String subject, String recipient, String message){
        Response response = new Response();
        Email from = new Email(sender);
        Email to = new Email(recipient);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = sg.api(request);
            System.out.println("Email to "+recipient+" has status code: "+response.getStatusCode());
            System.out.println(response.getBody());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return response;
    }
}
