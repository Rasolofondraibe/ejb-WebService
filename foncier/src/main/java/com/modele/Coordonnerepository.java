package com.modele;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

import com.gestionterre.Coordonnee;
import com.interfaces.Coordonneeinterface;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import org.locationtech.jts.geom.Point;


@Stateless
public class Coordonnerepository implements Serializable,Coordonneeinterface{
    private EntityManagerFactory entityManagerFactory; 
    private EntityManager entityManager; 

    public void ouvrirConnexion(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("foncier_per");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public void closeConnexion(){
        this.entityManager.close();
        this.entityManagerFactory.close();
    }


    /**
     * 
     * 
     * 
     * 
     * prendre tous les listes des coordonnees d une terre
     * 
     * 
     * si il y a une erreur le retour sera une liste vide
     */

    @Override
    public List<Coordonnee> getCoordonneByIdterre(String idterre){
        if(this.entityManagerFactory == null || !this.entityManagerFactory.isOpen()){
            this.ouvrirConnexion();
        }
        try {
            String jpql = "SELECT p FROM Coordonnee p WHERE p.idterre = :idterre";
            TypedQuery<Coordonnee> query = entityManager.createQuery(jpql, Coordonnee.class);
            query.setParameter("idterre", idterre);
            List<Coordonnee> coordonnees = query.getResultList();
            return coordonnees;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnexion();
        }
        return Collections.emptyList();
    }

    /**
     * 
     * insertion des coordonnee d une terre apres avoir inserer une terre (dans terrerepository)
     * 
     * table coordonnee 
     *  idterre coordonneex coordonneey
     */

     public void insertioncoordonnee(String idterre,String coordonneex,String coordonneey){
        if(this.entityManagerFactory == null || !this.entityManagerFactory.isOpen()){
            this.ouvrirConnexion();
        }
        try{
            this.entityManager.getTransaction().begin();
            Coordonnee coordonnee = new Coordonnee();
            coordonnee.setIdterre(idterre);
            coordonnee.setCoordonneex(coordonneex);
            coordonnee.setCoordonneey(coordonneey);
            
            //insertion
                this.entityManager.persist(coordonnee);
                this.entityManager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            this.entityManager.getTransaction().rollback(); 
        }finally{
            this.closeConnexion();
        }
     }

     public double calculDistance(double latitude,double longitude,double latitude1,double longitude1){
        double lon1 = Math.toRadians(longitude);
        double lat1 = Math.toRadians(latitude);
        double lon2 = Math.toRadians(longitude1);
        double lat2 = Math.toRadians(latitude1);
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double radius = 6371000;
        double distance = radius * c;
        return distance;
    }
    

    public double calculEnDegree(double latitude,double longitude,double latitude1,double longitude1){
        GeometryFactory geometryFactory = new GeometryFactory();

        // Créez deux points
        Coordinate point1Coords = new Coordinate(13.4050, 52.5200); // Point 1 (Berlin)
        Coordinate point2Coords = new Coordinate(2.3522, 48.8566);  // Point 2 (Paris)

        Point point1 = geometryFactory.createPoint(point1Coords);
        Point point2 = geometryFactory.createPoint(point2Coords);

        // Calcul de la distance entre les deux points
        double distance = point1.distance(point2);

        return distance;
    }


     

     @Override
     public double calculPerimetre(String idterre){
        List<Coordonnee> liste = this.getCoordonneByIdterre(idterre);
        double perimetre =0;
        int i=0;
        
       while(i<(liste.size()-1)){
            perimetre = perimetre + this.calculDistance(Double.valueOf(liste.get(i).getCoordonneex()), Double.valueOf(liste.get(i).getCoordonneey()), Double.valueOf(liste.get(i+1).getCoordonneex()), Double.valueOf(liste.get(i+1).getCoordonneey())); 
            i++;
        }
        int j = liste.size()-1;
       perimetre = perimetre + this.calculDistance(Double.valueOf(liste.get(j).getCoordonneex()), Double.valueOf(liste.get(j).getCoordonneey()), Double.valueOf(liste.get(0).getCoordonneex()), Double.valueOf(liste.get(0).getCoordonneey()));
        return perimetre;
     }


    @Override
    public double perimetreDegree(String idterre){
        List<Coordonnee> liste = this.getCoordonneByIdterre(idterre);
        double perimetre =0;
        int i=0;
        
       while(i<(liste.size()-1)){
            perimetre = perimetre + this.calculEnDegree(Double.valueOf(liste.get(i).getCoordonneex()), Double.valueOf(liste.get(i).getCoordonneey()), Double.valueOf(liste.get(i+1).getCoordonneex()), Double.valueOf(liste.get(i+1).getCoordonneey())); 
            i++;
        }
        int j = liste.size()-1;
       perimetre = perimetre + this.calculEnDegree(Double.valueOf(liste.get(j).getCoordonneex()), Double.valueOf(liste.get(j).getCoordonneey()), Double.valueOf(liste.get(0).getCoordonneex()), Double.valueOf(liste.get(0).getCoordonneey()));
        return perimetre;
    }
}
