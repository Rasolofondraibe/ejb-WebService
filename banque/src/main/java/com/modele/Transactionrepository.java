package com.modele;

import java.io.Serializable;

import com.gestionargent.Transaction;
import com.gestionexception.ExceptionPersonnalise;
import com.interfaces.Transactioninterface;
import jakarta.persistence.*;
import jakarta.ejb.Stateless;

@Stateless
public class Transactionrepository implements Transactioninterface,Serializable{
    
    private EntityManagerFactory entityManagerFactory; 
    
    private EntityManager entityManager; 

    public void ouvrirConnexion(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("banque_per");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public void closeConnexion(){
        this.entityManager.close();
        this.entityManagerFactory.close();
    }



    /*
     * numerocin : personne1        solde : 700
     * 
     * numerocin    sommeentree     sommesortie     solde
     * personne1    1500            500             1000
     * 
     * 1000>700   return true
     * 
     */

    @Override
    public boolean ifSoldeSufficient(String numerocin,double solderetirer){
        if(this.entityManagerFactory == null || !this.entityManagerFactory.isOpen()){
            this.ouvrirConnexion();
        }
        try{
            String sql = "SELECT * from solde_view WHERE numeroCin = :numerocin";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("numerocin",numerocin);
            Object[] result = (Object[]) query.getSingleResult();
            
            if(result!=null && (double)result[3] >= solderetirer){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.closeConnexion();
        }
        return false;
    }

    /**
     * 
     * faire une entree d argent 
     * ajout ligne dans la table transaction : 
     * 
     * idtransaction | datetransaction | numerocin | entree | sortie
     * personne1        2023-10-26       personne1   200       0
     * 
     * dateTransaction :  date aujourdhui (misy default any amn base)
     * 
     *les colonnes sans default a inserer : 
            numerocin
     *      entree
     *      
     * la sortie doit etre 0
     */

     public void toDoEntree(String numerocin,double entree){
        
        if(this.entityManagerFactory == null || !this.entityManagerFactory.isOpen()){
            this.ouvrirConnexion();
            
        }

        try{
            this.entityManager.getTransaction().begin();
            Transaction transaction = new Transaction();
            transaction.setNumerocin(numerocin);
            transaction.setEntree(entree);

            //insertion
                this.entityManager.persist(transaction);
                this.entityManager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            this.entityManager.getTransaction().rollback(); 
        }finally{
            this.closeConnexion();
        }
     }

     /*
        faire une sortie d argent 
      * 
        idtransaction | datetransaction | numerocin | entree | sortie
      * personne1        2023-10-26       personne1   0        200

        entree default 0 
        datetransaction default now()

     */

     public void toDoSortie(String numerocin,double sortie){
        if(this.entityManagerFactory == null || !this.entityManagerFactory.isOpen()){
            this.ouvrirConnexion();
            
        }

        try{
            this.entityManager.getTransaction().begin();
            Transaction transaction = new Transaction();
            transaction.setNumerocin(numerocin);
            transaction.setSortie(sortie);

            //insertion
                this.entityManager.persist(transaction);
                this.entityManager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            this.entityManager.getTransaction().rollback(); 
        }finally{
            this.closeConnexion();
        }
     }

     /***
      * faire une transaction  

        personneEntree  : numerocin personne qui recoit l argent 
        personneSortie  : numerocin personne qui envoie l argent 

        personneEntree.toDoEntree()     //recevoir l argent
        personneSortie.toDoSortie()     //sortie d argent 

        verification si le solde de personneSortie est suffisant avant de transferer
    */

    @Override
    public void toDoTransaction(String personneEntree,String personneSortie,double valeur){     //valeur : argent a envoyer
        try{
            if(this.ifSoldeSufficient(personneSortie, valeur)){  //verification si le solde est suffisant 
                this.toDoEntree(personneEntree,valeur);
                this.toDoSortie(personneSortie,valeur);
            }else{
                throw(new ExceptionPersonnalise("******SOLDE INSUFFISANT***********"));
            }

        }catch(Exception e){
            System.err.println("Erreur : " + e.getMessage());
            this.entityManager.getTransaction().rollback(); 
        }
    }
     
}
