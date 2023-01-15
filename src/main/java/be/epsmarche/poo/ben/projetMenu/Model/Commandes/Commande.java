package be.epsmarche.poo.ben.projetMenu.Model.Commandes;

import java.util.ArrayList;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant les commandes
 * @author ben
 */
public class Commande {
	/**
	 *numTab = le numéro de table
	 */
	private String numTab;
	
	/**
	 *listeMenu: la liste des menus
	 */
	private ArrayList <Iplat> listeMenu; 

	/**
	 * Constructeur par défaut
	 */
	public Commande() {
		super();
	}

	/**
	 * Constructeur à 1 param
	 * @param numTab
	 */
	public Commande(String numTab) {
		super();
		this.numTab = numTab;
		this.listeMenu = new ArrayList<>();
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
	 * ajoute les menus dans la liste des menus( la commande)
	 */
	public void addMenu(Iplat menu) {
		listeMenu.add(menu);
	}
	
	/**
	 * @return l'ensemble de la commande sous forme de chaîne de caractères
	 */
	
	public String displayCommand(){
		Iplat tmp;
		String text ="***********Commande de la table "+getNumTab()+" ********************\n\n";
		for(int i = 0; i < getListeMenu().size(); ++i) { 
			 tmp = (Iplat) (getListeMenu().get(i)); 
			  text += tmp.toString()+"-----------------------\n";
		}
		return text +"Prix total: €"+getPrixTotal();	
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
