package com.gestionargent;

import javax.naming.NamingException;

import com.gestionEjb.Transfert;
import com.interfaces.Transactioninterface;

public class Transaction{

    String idtransaction;
    String datetransaction;
    String numerocin;
    double entree;
    double sortie;
    
    //interface pour le transfert des fonctions
    Transactioninterface transactioninterface;

    public Transaction(String idtransaction, String datetransaction, String numerocin, double entree, double sortie){
        this.idtransaction = idtransaction;
        this.datetransaction = datetransaction;
        this.numerocin = numerocin;
        this.entree = entree;
        this.sortie = sortie;
    }   

    public Transaction() throws NamingException{
        Transfert transfert = new Transfert();
        this.transactioninterface = transfert.transfertBanque();
    }

    public String getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(String idtransaction) {
        this.idtransaction = idtransaction;
    }

    public String getDatetransaction() {
        return datetransaction;
    }

    public void setDatetransaction(String datetransaction) {
        this.datetransaction = datetransaction;
    }

    public String getNumerocin() {
        return numerocin;
    }

    public void setNumerocin(String numerocin) {
        this.numerocin = numerocin;
    }

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    /**
     * 
     * projet origine fonction : banque 
     * 
     * personneEntree = personne recepeteur
     * personneSortie = personne qui envoie
     * 
     */

    public void toDoTransaction(String personneEntree,String personneSortie,double valeur){
        //recuperer le transfert//
        transactioninterface.toDoTransaction(personneEntree,personneSortie,valeur);
    } 
}
