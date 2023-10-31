package com.gestionEjb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.interfaces.Coordonneeinterface;
import com.interfaces.Terreinterface;
import com.interfaces.Transactioninterface;

public class Transfert {
    

    /**
     *generalisation maka zavatra ejb (banque ) 
     * 
     * return interface
     * 
     * interface.fonction que tu veux recuperer
     * 
     * 
     * ex : Transfert transfert = new Transfert()
     *       Transactioninterface transactioninterface  = transfert.transfertBanque();
     *      boolean response = transactioninterface.fonction() 
     * 
     * @return
     * @throws NamingException
     */
    public Transactioninterface transfertBanque() throws NamingException{
            Properties jndiProps = new Properties();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            Context context = new InitialContext(jndiProps);
            Transactioninterface ejb = (Transactioninterface) context.lookup("ejb:/banque-1.0-SNAPSHOT/Transactionrepository!com.interfaces.Transactioninterface");
            context.close();
            return ejb;
    }



    /*
     * 
     * maka zavatra avy any amn projet foncier (terrerepository)
     * 
     * 
     */

     public Terreinterface transfertFoncier() throws NamingException{
            Properties jndiProps = new Properties();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            Context context = new InitialContext(jndiProps);
            Terreinterface ejb = (Terreinterface) context.lookup("ejb:/foncier-1.0-SNAPSHOT/Terrerepository!com.interfaces.Terreinterface");
            context.close();
            return ejb;
        }


        /**
         * 
         * maka fonction avy any amn ny coordonneerepository
         * 
         * 
         * 
         */
        public Coordonneeinterface transfertFoncierCoordonnee() throws NamingException{
            Properties jndiProps = new Properties();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            Context context = new InitialContext(jndiProps);
            Coordonneeinterface ejb = (Coordonneeinterface) context.lookup("ejb:/foncier-1.0-SNAPSHOT/Coordonnerepository!com.interfaces.Coordonneeinterface");
            context.close();
            return ejb;
        }
}
