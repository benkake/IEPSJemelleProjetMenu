package be.epsmarche.poo.ben.projetMenu.Model.Commandes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.AccompagnementFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.DessertFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant les commandes
 * 
 * @author ben
 */
public class Commande {
	/**
	 * numTab = le numéro de table
	 */
	private String numTab;
	
	/**
	 * listeMenu: la liste des menus 
	 */
	private ArrayList<Iplat> listeMenu = new ArrayList<>();
	
	/**
	 * Liste des menus par table
	 */
	
	private ArrayList<Iplat> listeMenu1 = new ArrayList<>();
	private ArrayList<Iplat> listeMenu2 = new ArrayList<>();
	private ArrayList<Iplat> listeMenu3 = new ArrayList<>();
	private ArrayList<Iplat> listeMenu4 = new ArrayList<>();
	private ArrayList<Iplat> listeMenu5 = new ArrayList<>();
	private ArrayList<Iplat> listeMenu6 = new ArrayList<>();
	
	private ArrayList<String> lesTables = new ArrayList<>();
	
	private Map<String,ArrayList> listeDesCommandes = new HashMap<>();
	
	private ArrayList<Iplat> uneCommande = new ArrayList<>();
	
	
	/**
	 * Constructeur par défaut
	 */
	public Commande() {
		super();
	}

	/**
	 * Constructeur à 1 param
	 * 
	 * @param numTab
	 */
	public Commande(String numTab) {
		super();
		this.numTab = numTab;
		this.listeMenu = new ArrayList<>();
		
		// Insertion des 6 tables du restaurant
		lesTables.add(" 1");
		lesTables.add(" 2");
		lesTables.add(" 3");
		lesTables.add(" 4");
		lesTables.add(" 5");
		lesTables.add(" 6");
	}

	public String getNumTab() {
		return numTab;
	}

	public void setNumTab(String tabNum) {
		selectTableNum( tabNum);	
	}

	public ArrayList<Iplat> getListeMenu() {
		return listeMenu;
	}

	public void setListeMenu(ArrayList<Iplat> listeMenu) {
		this.listeMenu = listeMenu;
	}
	
	
	
	public Map<String, ArrayList > getListeDesCommandes() {
		return listeDesCommandes;
	}

	public void addCommande(String numTabl, ArrayList<Iplat> listeDeMenu) {
		listeDesCommandes.put(numTabl,listeDeMenu);	
	}
	

	private void selectTableNum( String tabNum) {
		
		if(tabNum == " 1")
			this.numTab = " 1";
		if(numTab == " 2")
			this.numTab = " 2";
		if(numTab == " 3")
			this.numTab = " 3";
		if(numTab == " 4")
			this.numTab = " 4";
		if(numTab == " 5")
			this.numTab = " 5";
		if(numTab == " 6")
			this.numTab = " 6";
	}
	
	public ArrayList<Iplat> selectCommande(String tabNum) {
		
		ArrayList<Iplat> uneCommande = new ArrayList<>();
		if(tabNum == " 1")
			uneCommande = listeMenu1;
		if(numTab == " 2")
			uneCommande = listeMenu2;
		if(numTab == " 3")
			uneCommande = listeMenu3;
		if(numTab == " 4")
			uneCommande = listeMenu4;
		if(numTab == " 5")
			uneCommande = listeMenu5;
		if(numTab == " 6")
			uneCommande = listeMenu6;
		
		return uneCommande;
		
	}
	/**
	 * @param menu ajoute les menus dans la liste des menus( la commande)
	 */
	public void addMenu(Iplat menu) {
		getListeMenu().add(menu);
	}

	/**
	 * @return l'ensemble de la commande sous forme de chaîne de caractères
	 */

	public String displayCommand() {
		Iplat tmp = null;
		String text = null;
		text = "***********Commande de la table " + getNumTab() + " ********************\n\n";
		for (int i = 0; i < getListeMenu().size(); ++i) {
			tmp = (Iplat) (getListeMenu().get(i));
			text += tmp.toString() + "-----------------------\n";
		}
		return text + "Prix total: €" + getPrixTotal();

	}

	/**
	 * @return le prix total de toute la commande
	 */
	public Double getPrixTotal() {
		Double prixTot = 0.;
		for (int i = 0; i < getListeMenu().size(); ++i) {
			prixTot += getListeMenu().get(i).getPrix();
		}
		return prixTot;
	}

	/**
	 * Methode permettant de créer un objet menu constitué de 3 éléments(plat,
	 * accompagnement et dessert)
	 * 
	 * @param plat
	 * @param accomp
	 * @param dess
	 * @return un objet menuConcocted
	 * @since V3
	 */
	public Iplat getMenuConcocted(Choix plat, Choix accomp, Choix dess) {

		AccompagnementFactory accompChoisi = new AccompagnementFactory();
		DessertFactory dessChoisi = new DessertFactory();
		PlatFactory platChoisi = new PlatFactory();

		Iplat menuConcocted = platChoisi.getPlat(plat.getType(), plat.getDescription(), plat.getPrix());
		menuConcocted = accompChoisi.getAccomp(menuConcocted, accomp);
		menuConcocted = dessChoisi.getDess(menuConcocted, dess);

		return menuConcocted;
	}

}
