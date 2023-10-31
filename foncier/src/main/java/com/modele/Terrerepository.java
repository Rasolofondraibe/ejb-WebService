package com.modele;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.gestionexception.ExceptionPersonnalise;
import com.gestionterre.Terre;
import com.interfaces.Terreinterface;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;


@Stateless
public class Terrerepository implements Serializable,Terreinterface{
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


    /*
     * 
     * 
     * liste de tous les terres d une personnee
     * 
     */

    @Override
    public List<Terre> getAllTerre(String numerocin){
        if(this.entityManagerFactory == null || !this.entityManagerFactory.isOpen()){
            this.ouvrirConnexion();
        }
        try{
            String jpql = "SELECT p FROM Terre p WHERE p.numerocin = :numerocin";
            TypedQuery<Terre> query = entityManager.createQuery(jpql, Terre.class);
            query.setParameter("numerocin", numerocin);
            List<Terre> terres = query.getResultList();
            return terres;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.closeConnexion(); 
        }
        return Collections.emptyList();
    }

    /*
     * 
     * 
     * insertion tany :  
     *      *insertion terre
     *      **insertion coordonnee (x et y) (dans repository terre)
     * retourn idterre du terre inserer pour inserer dans coordonnee
     */
    public String insertionterre(String numerocin,String nomterre){
        if(this.entityManagerFactory == null || !this.entityManagerFactory.isOpen()){
            this.ouvrirConnexion();
        }
        try{
            this.entityManager.getTransaction().begin();
            Terre terre = new Terre();
            terre.setNumerocin(numerocin);
            terre.setNomterre(nomterre);
            
            //insertion
                this.entityManager.persist(terre);
                this.entityManager.getTransaction().commit();

            return terre.getIdterre();
        }catch(Exception e){
            e.printStackTrace();
            this.entityManager.getTransaction().rollback(); 
        }finally{
            this.closeConnexion();
        }
        return "";
    }


    /**
     * 
     * insertion terre avec ses coordonne
     * table terre / table coordonne
     * 
     * fonction  : 
     *      insertionterre (terrerepository)
     *      insertioncoordonnee (coordonneerepository)
     * 
     * listecoordonneex => liste des coordonnees x 
     * listecoordonneey => liste des coordonnees y 
     * 
     * coordonnee (listecoordoneex[i],listecoordonneey[i])
     * 
     */

    @Override
    public void achatterre(String numerocin,String nomterre,String[] listecoordonneex,String[] listecoordonneey){
        try{

            if(listecoordonneex.length != listecoordonneey.length){
                throw(new ExceptionPersonnalise("******Coordonnee manquant soit x soit y ***********"));
            }

            //insertion dans la table terre (retourne id du terre inserer)
                String idterre = this.insertionterre(numerocin, nomterre);

            //insertion dans la table coordonnee 
                Coordonnerepository coordonnerepository = new Coordonnerepository();
                for(int i=0;i<listecoordonneex.length;i++){
                    coordonnerepository.insertioncoordonnee(idterre, listecoordonneex[i], listecoordonneey[i]);
                }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
