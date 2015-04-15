package com.epul.ergosum.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.epul.ergosum.controle.meserreurs.MonException;
import com.epul.ergosum.persistance.DialogueBd;

public class GestionErgosum
{
	private GestionErgosum() {}
	
	private static <T> T getOne(List<T> list)
	{
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}
	
	private static List<Jouet> getJouets(List<Map<String, String>> result)
	{
		return result.stream().map(map ->
		{
        	Jouet entity = new Jouet();

        	entity.setNumero(map.get("numero"));
        	entity.setCategorie(new Categorie(map.get("codecateg")));
        	entity.setTrancheage(new Trancheage(map.get("codetranche")));
        	entity.setLibelle(map.get("libelle"));

        	return entity;
		}).collect(Collectors.toList());
	}
	private static List<Categorie> getCategories(List<Map<String, String>> result)
	{
		return result.stream().map(map ->
		{
			Categorie entity = new Categorie(map.get("codecateg"));

        	entity.setLibcateg(map.get("libcateg"));

        	return entity;
		}).collect(Collectors.toList());
	}
	private static List<Catalogue> getCatalogues(List<Map<String, String>> result)
	{
		return result.stream().map(map ->
		{
			Catalogue entity = new Catalogue();

			entity.setAnnee(Integer.getInteger(map.get("annee")));
        	entity.setQuantiteDistribuee(Integer.getInteger(map.get("quantitedistribuee")));

        	return entity;
		}).collect(Collectors.toList());
	}
	private static List<Trancheage> getTrancheages(List<Map<String, String>> result)
	{
		return result.stream().map(map ->
		{
			Trancheage entity = new Trancheage();

			entity.setCodetranche(map.get("codetranche"));
        	entity.setAgemin(Integer.getInteger(map.get("agemin")));
        	entity.setAgemax(Integer.getInteger(map.get("agemax")));

        	return entity;
		}).collect(Collectors.toList());
	}
	
	
	
	public static Object listerTousLesJouets() throws MonException
	{
		return listerTousLesJouets(0, 0);
	}
	public static Object listerTousLesJouets(int categorieCode, int trancheCode) throws MonException
	{
		if(categorieCode == 0)
			if(trancheCode == 0)
				return getJouets(DialogueBd.lecture("SELECT * FROM jouet"));
			else
				return getJouets(DialogueBd.lecture("SELECT * FROM jouet WHERE codetranche = " + trancheCode));
		else
			if(trancheCode == 0)
				return getJouets(DialogueBd.lecture("SELECT * FROM jouet WHERE codetranche = " + trancheCode));
			else
				return getJouets(DialogueBd.lecture("SELECT * FROM jouet WHERE codecateg = " + categorieCode + " AND codetranche = " + trancheCode));
	}

	public static Object listerToutesLesCategories() throws MonException
	{
		return getCategories(DialogueBd.lecture("SELECT * FROM categorie"));
	}

	public static Object listerToutesLesTranches() throws MonException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public static Object listerTousLesCatalogues() throws MonException
	{
		return getCatalogues(DialogueBd.lecture("SELECT * FROM catalogue"));
	}

	public static Jouet rechercherJouet(String id) throws MonException
	{
		return getOne(getJouets(DialogueBd.lecture("SELECT * FROM jouet WHERE numero = " + id + " LIMIT 0, 1")));
	}

	public static Categorie rechercherCategorie(String codecateg) throws MonException
	{
		return getOne(getCategories(DialogueBd.lecture("SELECT * FROM categorie WHERE codecateg = " + codecateg + " LIMIT 0, 1")));
	}

	public static Trancheage rechercherTrancheage(String parameter) throws MonException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public static void modifier(Jouet unJouet) throws MonException
	{
		// TODO Auto-generated method stub
	}

	public static Catalogue rechercherCatalogue(String annee) throws MonException
	{
		return getOne(getCatalogues(DialogueBd.lecture("SELECT * FROM catalogue WHERE annee = " + annee + " LIMIT 0, 1")));
	}

	public static void modifierCatalogue(Catalogue leCatalogue) throws MonException
	{
		// TODO Auto-generated method stub
		
	}

	public static void ajouter(Jouet unJouet) throws MonException
	{
		// TODO Auto-generated method stub
		
	}

	public static void effacer(String[] ids) throws MonException
	{
		// TODO Auto-generated method stub
	}

	public static Object listerCatalogueQuantites(int anneeDebut, int nbCatalogues) throws MonException
	{
		return getCatalogues(DialogueBd.lecture("SELECT * FROM catalogue WHERE annee >= " + anneeDebut + " LIMIT 0, " + nbCatalogues));
	}

	public static HashMap<Categorie, Integer> rechercherDictionnaire(String parameter) throws MonException
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
