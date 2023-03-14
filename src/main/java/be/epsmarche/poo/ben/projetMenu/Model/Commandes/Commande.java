package be.epsmarche.poo.ben.projetMenu.Model.Commandes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.AccompagnementFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.DessertFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant les commandes
 * @author ben
 */
public class Commande {
	private Date date;
	private Boolean facturee;
	/**
	 * numTab = le numéro de table
	 */
	private String numTab;
	
	/**
	 * listeMenu: la liste des menus 
	 */
	private ArrayList<Iplat> listeMenu = new ArrayList<>();
	
	/**
	 * Liste des menus par table.
	 * En effet chaque serie de menu par table est une commande 
	 */
	
	private ArrayList<Iplat> listeMenu1 = new ArrayList<>(); //commande table 1
	private ArrayList<Iplat> listeMenu2 = new ArrayList<>(); //commande table 2
	private ArrayList<Iplat> listeMenu3 = new ArrayList<>(); //commande table 3
	private ArrayList<Iplat> listeMenu4 = new ArrayList<>(); //commande table 4
	private ArrayList<Iplat> listeMenu5 = new ArrayList<>(); //commande table 5
	private ArrayList<Iplat> listeMenu6 = new ArrayList<>(); //commande table 6

	private ArrayList<String> lesTables = new ArrayList<>();
	
	private Map<String,ArrayList<Iplat>> listeDesCommandes = new HashMap<String,ArrayList<Iplat> >();

	
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
//		this.facturee = facturee;
//		this.date = date;
		// Insertion des 6 tables du restaurant
		lesTables.add(" 1");
		lesTables.add(" 2");
		lesTables.add(" 3");
		lesTables.add(" 4");
		lesTables.add(" 5");
		lesTables.add(" 6");
	}

	 
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getFacturee() {
		return facturee;
	}

	public void setFacturee(Boolean facturee) {
		this.facturee = facturee;
	}

	public String getNumTab() {
		return numTab;
	}


	public void setNumTab(String tabNum) {
		this.numTab = tabNum;
	}

	public ArrayList<Iplat> getListeMenu() {
		if(getNumTab().equals(" 1"))
			listeMenu = listeMenu1;
		if(getNumTab().equals(" 2"))
			listeMenu = listeMenu2;
		if(getNumTab().equals(" 3"))
			listeMenu = listeMenu3;
		if(getNumTab().equals(" 4"))
			listeMenu = listeMenu4;
		if(getNumTab().equals(" 5"))
			listeMenu = listeMenu5;
		if(getNumTab().equals(" 6"))
			listeMenu = listeMenu6;
		return listeMenu;
	}

//	public void setListeMenu(ArrayList<Iplat> listeMenu) {
//		this.listeMenu = listeMenu;
//	}
	
	
	public Map<String, ArrayList<Iplat>> getListeDesCommandes() {
		return listeDesCommandes;
	}

	public void addCommande(String numTabl, ArrayList<Iplat> listeDeMenu) {
		listeDesCommandes.put(numTabl,listeDeMenu);
	}

	
//	public String displayCurrentOrder(ArrayList<Iplat> currentOrder, String currentNumTab) {
//		Iplat tmp = null;
//		String text = null;
//		text = "***********Commande de la table " + currentNumTab + " ********************\n\n";
//		for (int i = 0; i < currentOrder.size(); ++i) {
//			tmp = (Iplat) (currentOrder.get(i));
//			text += tmp.toString() + "-----------------------\n";
//		}
//		return text + "Prix total: €" + getCurrentTotalPrice(currentOrder);
//	}
//
//	public Double getCurrentTotalPrice(ArrayList<Iplat> currentOrder) {
//		Double prixTot = 0.;
//		for (int i = 0; i < currentOrder.size(); ++i) {
//			prixTot += currentOrder.get(i).getPrix();
//		}
//		return prixTot;
//	}
//
	
//	public ArrayList<Iplat> selectCommande(String tabNum) { // Rappel: Une commande = une liste de menu
//
//		ArrayList<Iplat> uneCommande = new ArrayList<>();
//		if(tabNum == " 1")
//			uneCommande = listeMenu1;
//		if(tabNum == " 2")
//			uneCommande = listeMenu2;
//		if(tabNum == " 3")
//			uneCommande = listeMenu3;
//		if(tabNum == " 4")
//			uneCommande = listeMenu4;
//		if(tabNum == " 5")
//			uneCommande = listeMenu5;
//		if(tabNum == " 6")
//			uneCommande = listeMenu6;
//
//		return uneCommande;
//
//	}


	public void addMenu(Iplat menu, String tabNum){
		if(tabNum == " 1")
			listeMenu1.add(menu);
		if(tabNum == " 2")
			listeMenu2.add(menu);
		if(tabNum == " 3")
			listeMenu3.add(menu);;
		if(tabNum == " 4")
			listeMenu4.add(menu);
		if(tabNum == " 5")
			listeMenu5.add(menu);
		if(tabNum == " 6")
			listeMenu6.add(menu);
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
			text += tmp.toString() + "-----------------------Prix unitaire: €"+getListeMenu().get(i).getPrix()+"\n";
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

//	public double getPrixUnitaire(){
//		Double prixUnit = 0.;
//		for (int i = 0; i < getListeMenu().size(); ++i)
//			prixUnit = getListeMenu().get(i).getPrix();
//		return prixUnit;
//	}
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
