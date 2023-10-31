package com.banque;

import com.modele.Transactionrepository;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Transactionrepository transactionrepository = new Transactionrepository();
        transactionrepository.toDoTransaction("personne1","personne2",900);
    }
}
