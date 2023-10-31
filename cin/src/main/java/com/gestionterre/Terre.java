package com.gestionterre;

import java.io.Serializable;
import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionEjb.Transfert;
import com.interfaces.Coordonneeinterface;
import com.interfaces.Terreinterface;

public class Terre implements Serializable{

    String  idterre;

    String numerocin;

    String nomterre;

    //interface pour les transfert de fonction 
    Terreinterface terreinterface;
    Coordonneeinterface coordonneeinterface;
    
    public Terre()throws NamingException{
        Transfert transfert = new Transfert();
        this.terreinterface= transfert.transfertFoncier();
        this.coordonneeinterface = transfert.transfertFoncierCoordonnee();
    }
    
    public Terre(String idterre, String numerocin,String nomterre) {
        this.idterre = idterre;
        this.numerocin = numerocin;
        this.nomterre = nomterre;
    }
    
    public String getIdterre(){
        return idterre;
    }

    public void setIdterre(String idterre) {
        this.idterre = idterre;
    }

    public String getNumerocin() {
        return numerocin;
    }

    public void setNumerocin(String numerocin) {
        this.numerocin = numerocin;
    }

    public String getNomterre(){
        return this.nomterre;
    }

    public void setNomterre(String nomterre){
        this.nomterre = nomterre;
    }


    /*
     * 
     * projet origine  : foncier (terrerepository)
     * 
     * insertion dans la table terre et coordonnee
     */

    public void achatterre(String numerocin,String nomterre,String[] listecoordonneex,String[] listecoordonneey){
        this.terreinterface.achatterre(numerocin, nomterre, listecoordonneex, listecoordonneey);
    }


    /*
     * 
     * liste de tous les terres d une personne
     * 
     * projet origine : foncier (terrerepository)
     * 
     *
     */
    public List<Terre> getAllTerre(String numerocin){
        return this.terreinterface.getAllTerre(numerocin);
    }


    /*
     * 
     * liste Coordonnee d une terre
     * 
     * projet origine : foncier (coordonnee repository)
     * 
     */

     public List<Coordonnee> getTerrePersonne(String idterre){
        return this.coordonneeinterface.getCoordonneByIdterre(idterre);
     }

     public String listeCoordonneJson(String idterre){
        List<Coordonnee> listeTerre = this.getTerrePersonne(idterre);
         ObjectMapper objectMapper = new ObjectMapper();
            try {
                // Convertissez la liste en JSON
                String json = objectMapper.writeValueAsString(listeTerre);

                return json;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return "Erreur lors de la conversion en JSON";
            }
     }

     public double perimetre(String idterre){
         return this.coordonneeinterface.calculPerimetre(idterre);
     }

     public double perimetreDegree(String idterre){
        return this.perimetreDegree(idterre);
     }
}
