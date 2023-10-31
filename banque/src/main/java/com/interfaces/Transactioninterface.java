package com.interfaces;

import jakarta.ejb.Remote;

@Remote
public interface Transactioninterface {
    public boolean ifSoldeSufficient(String numerocin,double solderetirer);
    public void toDoTransaction(String personneEntree,String personneSortie,double valeur);
}
