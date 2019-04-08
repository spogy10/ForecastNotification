package main;

import database.WorkerDatabaseManager;
import model.Worker;

import java.util.LinkedList;
import java.util.List;

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

    public void sendToKingstonWorkers(String message){ //TODO: add code to send emails

    }

    public void sendToKingstonITWorkers(String message){

    }

    public void sendToMontegoBayWorkers(String message){

    }

    public void sendToMontegoBayITWorkers(String message){

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
