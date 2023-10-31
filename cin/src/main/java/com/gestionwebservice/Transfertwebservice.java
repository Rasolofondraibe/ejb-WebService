package com.gestionwebservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Transfertwebservice {
    String url;
    String methode;

    public Transfertwebservice(){}
    
    
    public Transfertwebservice(String url,String methode){
        this.url = url;
        this.methode = methode;
    }

    public String getUrl(){
        return this.url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getMethode(){
        return this.methode;
    }

    public void setMethode(String methode){
        this.methode = methode;
    }


    /**
     * 
     * generalisation reception de donne web service 
     * 
     * type de retour : json
     * 
     */

    public String getTransfertWebService(){
        String jsonResponse = "";
        try{
            URL url = new URL(this.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(this.getMethode());
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            jsonResponse = response.toString();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

}