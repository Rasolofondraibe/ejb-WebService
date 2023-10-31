package com.cin;



import java.util.List;

import javax.naming.NamingException;

import com.gestionterre.Coordonnee;
import com.gestionterre.Terre;


public class App 
{
    public static void main( String[] args ) throws NamingException
    {
        Terre terre = new Terre();
        String listeCoordonee = terre.listeCoordonneJson("terre131");
        System.out.println(listeCoordonee);
        
        
    }
}
