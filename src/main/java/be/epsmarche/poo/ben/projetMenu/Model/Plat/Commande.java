package be.epsmarche.poo.ben.projetMenu.Model.Plat;

import java.util.ArrayList;

/**
 * Classe définissant les commandes
 * @author ben
 */
public class Commande {
	/**
	 * @param numTab = le numéro de table
	 */
	private String numTab;
	
	/**
	 * @param listeCde = la liste des commandes qui sera composée de menus
	 */
	private ArrayList <Iplat> listeMenu = new ArrayList<>(); 

	/**
	 * Constructeur par défaut
	 */
	public Commande() {
		super();
	}
	
	/**
	 * Constructeur à 1 param
	 * @param 
	 */
	public Commande(String numTab) {
		super();
		this.numTab = numTab;
	}

	public String getNumTab() {
		return numTab;
	}

	public void setNumTab(String numTab) {
		this.numTab = numTab;
	}

	public ArrayList<Iplat> getListeMenu() {
		return listeMenu;
	}

	public void setListeCde(ArrayList<Iplat> listeMenu) {
		this.listeMenu = listeMenu;
	}
	
	/**
	 * @param menu
	 * ajoute les menus dans la liste des menus
	 */
	public void addMenu(Iplat menu) {
		listeMenu.add(menu);
	}
	
	/**
	 * @return le prix total de toute la commande
	 */
	public Double getPrixTotal() {
		Double prixTot =0.;
		for(int i = 0; i < listeMenu.size();++i) {
			prixTot += listeMenu.get(i).getPrix();
		}
		return prixTot;
	}
	
	
}
