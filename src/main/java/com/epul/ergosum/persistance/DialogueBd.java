package com.epul.ergosum.persistance;
import java.sql.*;
import java.util.*;
import com.epul.ergosum.controle.meserreurs.MonException;


public class DialogueBd {
	
	private static Connexion uneconnexion = null;
	private static DialogueBd instance =null;

	public static DialogueBd getInstance()
	{
		if (instance ==null)
		{
			instance = new DialogueBd();
		}
		return instance;
	}

	private  DialogueBd() {
		super();
	}

	public  static void insertionBD (String mysql) throws MonException 
	{   
		Connection cnx=null;
		try 
		{ 

			cnx = Connexion.getInstance().getConnexion();
			Statement unstatement = cnx.createStatement();
			unstatement.execute(mysql);
			// on ferme la connexion
			cnx.close();
		}
		catch(SQLException e)
		{
			System.out.println("Erreur :" +  e.getMessage () );
			System.out.println(mysql);
			throw new MonException(e.getMessage());
		}       
	} 


	public static List<Map<String, String>> lecture(String req)  throws  MonException
	{
		Connection cnx = null;
		
		try
		{
			List<Map<String, String>> result = new ArrayList<>();

			cnx = Connexion.getInstance().getConnexion();
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(req);
			ResultSetMetaData rsmd = rs.getMetaData();
			int nbCols = rsmd.getColumnCount();

			while(rs.next())
			{
				Map<String, String> temp = new HashMap<>();
				
				for(int i = 1; i <= nbCols; i++)
					temp.put(rsmd.getColumnName(i).toLowerCase(), rs.getObject(i).toString());
				
				result.add(temp);
			}
			
			cnx.close();
			return result;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			throw new MonException(e.getMessage());
		}
		finally
		{
			if (cnx != null)
			{
				try
				{ 
					cnx.close();
				}
				catch (SQLException e)
				{ }
			}
		}
	}
	 /*
 public static List<Object> lecture(String req )  throws  MonException
	  {    
		 Connection cnx = null;
		  Statement stmt;
	      ResultSet  rs;
	      List<Object> mesRes = new ArrayList<Object>();
	      int i;
	      int nbCols;
	       
	            try
	            {
	            	
	            	cnx = Connexion.getInstance().getConnexion();
	            	 stmt = cnx.createStatement(); 
	                 // Execution de la requete 
	                rs= stmt.executeQuery(req);
	                // on retrouve le nombre de colonnes de la requête 
	                ResultSetMetaData rsmd = rs.getMetaData();
	                nbCols = rsmd.getColumnCount();
	                i=1;
	                // on balaie toutes les lignes
	               while ( rs.next())
	               {  
	            	   
	                   // On balaie les colonnes
	                   i=1;
	                    while (i <= nbCols)
	                     {
	                         mesRes.add(rs.getObject(i));
	                         i++;
	                     }
	               }
	                cnx.close();
	                // Retourner la table
	                return (mesRes);
	            }
	           catch (SQLException e)
	            {
	        	   System.out.println(e.getMessage());
	        	   throw new MonException(e.getMessage());
	            }
	            finally
	            {
	                // S'il y a eu un problème, la connexion
	                // peut être encore ouverte, dans ce cas
	                // il faut la fermer.                 
	               
	                if (cnx != null)
	                  try { 
	                     cnx.close();
	                    }
	                   catch (SQLException e)
	                     {
	                        }
	             }
	  }*/
}

