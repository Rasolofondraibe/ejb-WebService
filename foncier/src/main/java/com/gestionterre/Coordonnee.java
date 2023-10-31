package com.gestionterre;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "coordonnee")
public class Coordonnee implements Serializable{

    @Id
    @Column(name = "idcoordonnee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String idcoordonnee;

    @Column(name = "idterre")
    String idterre;

    @Column(name = "coordonneex")
    String coordonneex;

    @Column(name = "coordonneey")
    String coordonneey;

    public Coordonnee() {
    }

    public Coordonnee(String idcoordonnee, String idterre, String coordonneex,String coordonneey) {
        this.idcoordonnee = idcoordonnee;
        this.idterre = idterre;
        this.coordonneex = coordonneex;
        this.coordonneey = coordonneey;
    }
    
    public String getIdcoordonnee() {
        return idcoordonnee;
    }


    public void setIdcoordonnee(String idcoordonnee) {
        this.idcoordonnee = idcoordonnee;
    }


    public String getIdterre() {
        return idterre;
    }


    public void setIdterre(String idterre) {
        this.idterre = idterre;
    }


    public String getCoordonneex() {
        return coordonneex;
    }


    public void setCoordonneex(String coordonneex) {
        this.coordonneex = coordonneex;
    }

    public String getCoordonneey() {
        return coordonneey;
    }


    public void setCoordonneey(String coordonneey) {
        this.coordonneey = coordonneey;
    }


}
