package com.gestionargent;

import jakarta.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "transaction")
public class Transaction implements Serializable{

    @Id
    @Column(name = "idtransaction")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pour generer automatiquement l id
    String idtransaction;

    @Column(name = "datetransaction", insertable = false, updatable = false)   //insertable :  ne peut pas etre inserer (donc creer automatique une default lors de l'insertion)
    String datetransaction;

    @Column(name = "numerocin")
    String numerocin;

    @Column(name = "entree")
    double entree;

    @Column(name = "sortie")
    double sortie;

    public Transaction(){}

    public Transaction(String idtransaction, String datetransaction, String numerocin, double entree, double sortie) {
        this.idtransaction = idtransaction;
        this.datetransaction = datetransaction;
        this.numerocin = numerocin;
        this.entree = entree;
        this.sortie = sortie;
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
}
