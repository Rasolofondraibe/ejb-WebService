package com.gestionsante;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionwebservice.Transfertwebservice;


public class Personne {

    @JsonProperty("numeroCin")
    String numerocin;

    @JsonProperty("nom")
    String nom;

    @JsonProperty("prenom")
    String prenom;

    @JsonProperty("datedenaissance")
    String datedenaissance;

    @JsonProperty("age")
    double age;

    @JsonProperty("adresse")
    String adresse;

    public Personne() {
    }

    public Personne(String numerocin, String nom, String prenom, String datedenaissance, double age, String adresse) {
        this.numerocin = numerocin;
        this.nom = nom;
        this.prenom = prenom;
        this.datedenaissance = datedenaissance;
        this.age = age;
        this.adresse = adresse;
    }

    public String getNumerocin() {
        return numerocin;
    }

    public void setNumerocin(String numerocin) {
        this.numerocin = numerocin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(String datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    /**
     * 
     * source fonction : web service du pojet sante
     * 
     * return json transfrome => List<Personne>
     * 
     */

    public List<Personne> getAllPersonne(){
        try{
            Transfertwebservice transfert = new Transfertwebservice();
            transfert.setUrl("http://localhost:5146/api/Personne/getAllPersonne");
            transfert.setMethode("GET");
            String json =  transfert.getTransfertWebService();      //reponse json

            //transformation du json en liste de personne
                ObjectMapper objectMapper = new ObjectMapper();
                List<Personne> listePersonne = objectMapper.readValue(json,new TypeReference<List<Personne>>(){});
            return listePersonne;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
