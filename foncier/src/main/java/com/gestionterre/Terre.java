package com.gestionterre;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "terre")
public class Terre implements Serializable{
    
    @Id
    @Column(name = "idterre")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generer automatiquement un id
    String  idterre;

    @Column(name = "numerocin")
    String numerocin;

    @Column(name = "nomterre")
    String nomterre;
    
    public Terre() {
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

}
