package com.epul.ergosum.metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

			entity.setAnnee(Integer.parseInt(map.get("annee")));
        	entity.setQuantiteDistribuee(Integer.parseInt(map.get("quantitedistribuee")));

        	return entity;
		}).collect(Collectors.toList());
	}
	private static List<Trancheage> getTrancheages(List<Map<String, String>> result)
	{
		return result.stream().map(map ->
		{
			Trancheage entity = new Trancheage();

			entity.setCodetranche(map.get("codetranche"));
        	entity.setAgemin(Integer.parseInt(map.get("agemin")));
        	entity.setAgemax(Integer.parseInt(map.get("agemax")));

        	return entity;
		}).collect(Collectors.toList());
	}
	private static List<CatalogueQuantites> getCatalogueQuantites(List<Map<String, String>> result)
	{
		return result.stream().map(map ->
		{
			CatalogueQuantites entity = new CatalogueQuantites();

			entity.setId(map.get("annee"));
        	entity.setQuantite(map.get("quantite"));
        	entity.setQuantiteDistribuee(map.get("quantitedistribuee"));

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
		return getTrancheages(DialogueBd.lecture("SELECT * FROM trancheage"));
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

	public static Trancheage rechercherTrancheage(String codetrancheage) throws MonException
	{
		return getOne(getTrancheages(DialogueBd.lecture("SELECT * FROM trancheage WHERE codetranche = " + codetrancheage)));
	}

	public static void modifier(Jouet unJouet) throws MonException
	{
		DialogueBd.insertionBD("UPDATE jouet SET "
				+ "  codecateg     = \"" + unJouet.getCategorie().getCodecateg()
				+ "\", codetranche = \"" + unJouet.getTrancheage().getCodetranche()
				+ "\", libelle     = \"" + unJouet.getLibelle()
				+ "\" WHERE numero = " + unJouet.getNumero());
	}

	public static Catalogue rechercherCatalogue(String annee) throws MonException
	{
		return getOne(getCatalogues(DialogueBd.lecture("SELECT * FROM catalogue WHERE annee = " + annee + " LIMIT 0, 1")));
	}

	public static void modifierCatalogue(Catalogue leCatalogue) throws MonException
	{
		DialogueBd.insertionBD("UPDATE catalogue SET "
				+ " quantiteDistribuee = \"" + leCatalogue.getQuantiteDistribuee()
				+ "\" WHERE annee = " + leCatalogue.getAnnee());
	}

	public static void ajouter(Jouet unJouet) throws MonException
	{
		DialogueBd.insertionBD("INSERT INTO jouet (numero, codecateg, codetranche, libelle) VALUES( "
				+ " \"" + unJouet.getNumero()
				+ "\", \"" + unJouet.getCategorie().getCodecateg()
				+ "\", \"" + unJouet.getTrancheage().getCodetranche()
				+ "\", \"" + unJouet.getLibelle()
				+ "\" )");
	}

	public static void effacer(String[] ids) throws MonException
	{
		StringBuilder sb = new StringBuilder();
		Stream.of(ids).forEach(s -> 
		{
			if(sb.length() > 0)
				sb.append("\", \"");
			sb.append(s);
		});

		DialogueBd.insertionBD("DELETE FROM comporte "
				+ " WHERE numero IN (\"" + sb.toString() + "\")");
		DialogueBd.insertionBD("DELETE FROM jouet "
				+ " WHERE numero IN (\"" + sb.toString() + "\")");
	}

	public static Object listerCatalogueQuantites(int anneeDebut, int nbCatalogues) throws MonException
	{
		return getCatalogueQuantites(DialogueBd.lecture("SELECT c.annee AS annee, co.quantite AS quantite, c.quantiteDistribuee AS quantitedistribuee FROM catalogue c INNER JOIN comporte co ON c.annee = co.annee WHERE c.annee >= " + anneeDebut + " AND c.annee < " + (anneeDebut + nbCatalogues)));
	}

	public static Map<Categorie, Integer> rechercherDictionnaire(String annee) throws MonException
	{
		return DialogueBd.lecture("SELECT ca.codecateg, ca.libcateg, SUM(c.quantite) AS quantite "
				+ "FROM comporte c "
				+ "INNER JOIN jouet j ON j.numero = c.numero "
				+ "INNER JOIN categorie ca ON ca.codecateg = j.codecateg "
				+ "WHERE c.annee = " + annee + " "
				+ "GROUP BY ca.codecateg")
				.stream()
				.collect(Collectors.toMap(
						e -> new Categorie(e.get("codecateg"), e.get("libcateg"), null),
						e -> Integer.parseInt(e.get("quantite"))));
	}
	
	
}
