package com.foncier;

import java.util.List;

import com.gestionterre.Coordonnee;
import com.gestionterre.Terre;
import com.modele.Coordonnerepository;
import com.modele.Terrerepository;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       Coordonnerepository terre = new Coordonnerepository();
        //String listeCoordonee = terre.listeCoordonneJson("terre131");
        //System.out.println(listeCoordonee);
        List<Coordonnee> liste = terre.getCoordonneByIdterre("terre131");
        double a= terre.calculPerimetre("terre131");
        System.out.println(a);
    }
}
