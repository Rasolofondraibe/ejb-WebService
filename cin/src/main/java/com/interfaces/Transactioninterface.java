package com.interfaces;

public interface Transactioninterface {
    public boolean ifSoldeSufficient(String numerocin,double solderetirer);
    public void toDoTransaction(String personneEntree,String personneSortie,double valeur);
}
