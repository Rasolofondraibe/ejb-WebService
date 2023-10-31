package com.interfaces;

import java.util.List;

import com.gestionterre.Coordonnee;


public interface Coordonneeinterface {
    public List<Coordonnee> getCoordonneByIdterre(String idterre);
    public double calculPerimetre(String idterre);
    public double perimetreDegree(String idterre);
}
