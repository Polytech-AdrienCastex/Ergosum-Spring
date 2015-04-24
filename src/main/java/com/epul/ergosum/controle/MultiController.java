package com.epul.ergosum.controle;

import com.epul.ergosum.controle.meserreurs.*;
import com.epul.ergosum.metier.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MultiController extends MultiActionController {

	private static final Logger logger = LoggerFactory.getLogger(MultiController.class);


	@RequestMapping(value = "Accueil", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) throws Exception
	//public String home(Locale locale, Model model)
	{/*
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);*/

		String destinationPage = "/Accueil";
		request.setAttribute("action", "Accueil");
		return new ModelAndView(destinationPage);
	}

	/**
	 * Affichage de tous les jouets
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "AfficherJouets")
	public ModelAndView afficherLesJouets(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String destinationPage = "";
		try
		{
			int categorieCode;
			int trancheCode;
			String categorie = request.getParameter("codecateg");
			String tranche = request.getParameter("codetranche");
			
			if(categorie == null)
				categorieCode = 0;
			else
				categorieCode = Integer.parseInt(categorie);
			
			if(tranche == null)
				trancheCode = 0;
			else
				trancheCode = Integer.parseInt(tranche);
			
			List<Jouet> jouets = (List<Jouet>)GestionErgosum.listerTousLesJouets(categorieCode, trancheCode);
			
			List<Categorie> categories = (List<Categorie>)GestionErgosum.listerToutesLesCategories();
			List<Trancheage> tranches = (List<Trancheage>)GestionErgosum.listerToutesLesTranches();

			for(Jouet j : jouets)
			{
				for(Categorie c : categories)
					if(c.getCodecateg().equals(j.getCategorie().getCodecateg()))
					{
						j.setCategorie(c);
						break;
					}

				for(Trancheage t : tranches)
					if(t.getCodetranche().equals(j.getTrancheage().getCodetranche()))
					{
						j.setTrancheage(t);
						break;
					}
			}
			
			request.setAttribute("mesJouets", jouets);
		}
		catch (MonException e)
		{
			request.setAttribute("MesErreurs", e.getMessage());
		}
		destinationPage = "/ListeJouets";

		request.setAttribute("action", "AfficherJouets");
		return new ModelAndView(destinationPage);
	}

	/**
	 * Ajout d'un jouet
	 */
	@RequestMapping(value = "AjouterJouet")
	public ModelAndView ajoutJouet(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String destinationPage;

		try
		{
			// on passe les numéros de client et de vendeur
			request.setAttribute("jouet", new Jouet());
			request.setAttribute("categories", GestionErgosum.listerToutesLesCategories());
			request.setAttribute("tranches", GestionErgosum.listerToutesLesTranches());
			request.setAttribute("catalogues", GestionErgosum.listerTousLesCatalogues());

			destinationPage = "/AjouterJouet";
		}
		catch (MonException e)
		{
			destinationPage = "/Erreur";
			request.setAttribute("MesErreurs", e.getMessage());
		}

		request.setAttribute("action", "AjouterJouet");
		return new ModelAndView(destinationPage);
	}

	/**
	 * Sélection d'une année Catalogue
	 */
	@RequestMapping(value = "ListerCatalogue")
	public ModelAndView choixCatalogue(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String destinationPage = "/Erreur";
		try
		{
			request.setAttribute("catalogues", GestionErgosum.listerTousLesCatalogues());
			destinationPage = "/ChoixCatalogue";
		}
		catch (MonException e)
		{
			request.setAttribute("MesErreurs", e.getMessage());
		}

		request.setAttribute("action", "ListerCatalogue");
		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "ListerDictionnaire")
	public ModelAndView choixDictionnaire(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String destinationPage = "/Erreur";
		try
		{
			request.setAttribute("catalogues", GestionErgosum.listerTousLesCatalogues());
			destinationPage = "/ChoixDictionnaire";
		}
		catch (MonException e)
		{
			request.setAttribute("MesErreurs", e.getMessage());
		}

		request.setAttribute("action", "ListerDictionnaire");
		return new ModelAndView(destinationPage);
	}

	/**
	 * Modifier Jouet
	 */
	@RequestMapping(value = "ModifierJouet")
	public ModelAndView modifierJouet(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String destinationPage = "/Erreur";
		try
		{
			String id = request.getParameter("id");

			Jouet unJouet = GestionErgosum.rechercherJouet(id);
			request.setAttribute("jouet", unJouet);
			request.setAttribute("categories", GestionErgosum.listerToutesLesCategories());
			request.setAttribute("tranches", GestionErgosum.listerToutesLesTranches());
			destinationPage = "/ModifierJouet";

		}
		catch (MonException e)
		{
			request.setAttribute("MesErreurs", e.getMessage());
		}

		request.setAttribute("action", "ModifierJouet");
		return new ModelAndView(destinationPage);
	}

	/**
	 * Sauver jouet
	 */
	@RequestMapping(value = "SauverJouet")
	public ModelAndView sauverJouet(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try
		{
			String id = request.getParameter("id");
			Jouet unJouet;

			// fabrication du jouet à partir des paramètres de la requête
			// Si le jouet n'est pas à créer, il faut le récupérer de la session
			// courante
			// Ensuite on peut modifier ses champs

			if (request.getParameter("type").equals("ajout"))
				unJouet = new Jouet();
			else
				unJouet = GestionErgosum.rechercherJouet(id);
			
			unJouet.setNumero(request.getParameter("id"));
			unJouet.setLibelle(request.getParameter("libelle"));
			Categorie uneCateg = GestionErgosum.rechercherCategorie(request.getParameter("codecateg"));
			unJouet.setCategorie(uneCateg);

			Trancheage uneTranche = GestionErgosum.rechercherTrancheage(request.getParameter("codetranche"));
			unJouet.setTrancheage(uneTranche);

			// sauvegarde du jouet
			switch(request.getParameter("type").toLowerCase())
			{
			case "ajout":
				Catalogue leCatalogue = GestionErgosum.rechercherCatalogue(request.getParameter("codecatalogue"));

				int quantiteDistribution = Integer.parseInt(request.getParameter("quantiteDistribution"));
				if (quantiteDistribution > 0)
				{
					leCatalogue.setQuantiteDistribuee(leCatalogue.getQuantiteDistribuee() + quantiteDistribution);
					GestionErgosum.modifierCatalogue(leCatalogue);
				}
				GestionErgosum.ajouter(unJouet);
				break;
			
			case "modif":
				GestionErgosum.modifier(unJouet);
				break;
			}

			return afficherLesJouets(request, response);
		}
		catch (Exception e)
		{
			String destinationPage = "/Erreur";
			request.setAttribute("MesErreurs", e.getMessage());
			request.setAttribute("action", "SauverJouet");
			return new ModelAndView(destinationPage);
		}
	}

	/**
	 * effacer jouet
	 */
	@RequestMapping(value = "EffacerJouet")
	public ModelAndView effacerJouet(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		try
		{
			String[] ids = request.getParameterValues("id[]");
			
			if (ids != null)
				GestionErgosum.effacer(ids);
		}
		catch (Exception e)
		{
			request.setAttribute("MesErreurs", e.getMessage());
		}

		return afficherLesJouets(request, response);
	}

	/**
	 * afficher Catalogue
	 */
	@RequestMapping(value = "AfficherCatalogues")
	public ModelAndView afficherCatalogue(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String destinationPage = "/Erreur";
		try
		{
			String id = request.getParameter("id");
			
			request.setAttribute("mesCataloguesQuantites", GestionErgosum
					.listerCatalogueQuantites(Integer.parseInt(request
							.getParameter("anneeDebut")), Integer
							.parseInt(request.getParameter("nbAnnees"))));
			destinationPage = "/AfficherCatalogues";
		}
		catch (MonException e)
		{
			request.setAttribute("MesErreurs", e.getMessage());
		}

		request.setAttribute("action", "AfficherCatalogues");
		return new ModelAndView(destinationPage);
	}

	/**
	 * afficher le Dictionnaire
	 */
	@RequestMapping(value = "AfficherDictionnaire")
	public ModelAndView afficherDictionnaire(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String destinationPage;
		
		try
		{
			String annee = request.getParameter("annee");

			Set<Entry<Categorie, Integer>> hashCatInt = GestionErgosum
					.rechercherDictionnaire(request.getParameter("annee")).entrySet();
			
			Integer total = 0;
			for(Entry<Categorie, Integer> e : hashCatInt)
				total += e.getValue();

			request.setAttribute("total", total);
			request.setAttribute("dictionnaire", hashCatInt);
			request.setAttribute("anneecatalogue", annee);
			destinationPage = "/AfficherDictionnaire";
		}
		catch (MonException e)
		{
			destinationPage = "/Erreur";
			request.setAttribute("MesErreurs", e.getMessage());
		}

		request.setAttribute("action", "AfficherDictionnaire");
		return new ModelAndView(destinationPage);
	}

}
