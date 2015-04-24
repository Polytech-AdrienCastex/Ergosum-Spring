package com.epul.ergosum.persistance;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.epul.ergosum.controle.meserreurs.MonException;

import java.sql.Connection;
import java.sql.SQLException;

public class Connexion {

	
	private static Connexion instance =null;
	
	 // On utilise un singleton  
	 public static Connexion getInstance()
     {
         if (instance ==null)
             instance = new Connexion();

         return instance;

     }
	 // on rend le constructeur privé
	 // pour empêcher toute création d'instance
	 private Connexion ()
	 {}
	 
	public Connection  getConnexion( )throws MonException 
	  {   
	    Connection conn = null;
		try 
	       {   
			   Context ctxt =  new InitialContext();
			   // On récupère l'environnement
			   Context envCtx = (Context) ctxt.lookup("java:comp/env");
			   // On recherche la data source
	           DataSource ds = (DataSource) envCtx.lookup("jdbc/ergosum");
	           conn= ds.getConnection();
	       
	       }
	    catch( SQLException e )  {
	          throw new MonException(e.getMessage());
	       }
	     catch ( Exception e )      {
	          throw new MonException(e.getMessage());
	       }
		return conn;
	} 
	
	

}
