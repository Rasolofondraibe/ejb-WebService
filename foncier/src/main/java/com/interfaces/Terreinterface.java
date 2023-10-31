package com.interfaces;

import java.util.List;

import com.gestionterre.Terre;

import jakarta.ejb.Remote;

@Remote
public interface Terreinterface {
    public void achatterre(String numerocin,String nomterre,String[] listecoordonneex,String[] listecoordonneey);
    public List<Terre> getAllTerre(String numerocin);
}
