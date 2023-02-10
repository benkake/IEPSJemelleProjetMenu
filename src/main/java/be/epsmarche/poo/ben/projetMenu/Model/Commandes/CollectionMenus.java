package be.epsmarche.poo.ben.projetMenu.Model.Commandes;

import java.util.HashSet;
import java.util.Iterator;

import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.AccompagnementFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.DessertFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant les commandes
 * @author ben
 */
public class CollectionMenus {
	/**
	 *numTab = le numéro de table
	 */
	private String numTab;
	
	private Iplat platChoisi, accompChoisi, dessertChoisi; 
	/**
	 *lesCommandes: la liste des menus
	 */
	private HashSet<Iplat> lesCommandes; 

	/**
	 * Constructeur par défaut
	 */
	public CollectionMenus() {
		super();
	}
	

	/**
	 * Constructeur à 4 params
	 * @param numTab
	 */
	public CollectionMenus(String numTab) {
		super();
		this.numTab = numTab;
		this.lesCommandes = new HashSet<>();
	}
	
	
	public String getNumTab() {
		return numTab;
	}

	public void setNumTab(String numTab) {
		this.numTab = numTab;
	}

	public HashSet<Iplat> getListeMenu() {
		return lesCommandes;
	}

	
	public void setListeCde(HashSet<Iplat> lesCommandes) {
		this.lesCommandes = lesCommandes;
	}
	
	/**
	 * getMenuSelected intervient dans l'action perform.
	 * Elle prend en paramètre le plat, l'accompagnement et le dessert choisis via le formulaire, 
	 * @param plat
	 * @param accomp
	 * @param dess
	 * @return
	 */
	public Iplat getMenuSelected(Choix plat, Choix accomp, Choix dess) {
		
		PlatFactory fabricPlat = new PlatFactory(); 
		Iplat menuChoisi = fabricPlat.getPlat(plat.getCategorie(), plat.getType(), plat.getPrix());
		AccompagnementFactory fabricAccomp = new AccompagnementFactory();
		menuChoisi = fabricAccomp.getAccomp(accomp.getCategorie(), menuChoisi, accomp.getType(), accomp.getPrix());
		DessertFactory fabricDess = new DessertFactory();
		menuChoisi = fabricDess.getDess(dess.getCategorie(), menuChoisi, dess.getType(), dess.getPrix());	
		return menuChoisi;
	}
	
	/**
	 * Cette mehode ajoue au HashSet lesCommandes les différents menus selectionnés
	 * @param menuSelected
	 */
	
	public void addMenuSelected(Iplat menuSelected) {
		if(menuSelected != null)
			lesCommandes.add(menuSelected);
		else
			System.out.println("Vous n'avez pas proposé dé menu à ajouter..");
	}
	
	public void removeMenuSelected(Iplat menuSelected) {
		if(lesCommandes.isEmpty())
			System.out.println("La liste des menus ne contenait aucun menu !");
		else if(lesCommandes.contains(menuSelected))
			lesCommandes.remove(menuSelected);
		else
			System.out.println("La liste des menu ne contient pas le menu demandée !");
	}

	/**
	 * @return l'ensemble de la commande sous forme de chaîne de caractères
	 */
	
	public String afficheCommande(){
		Choix tmp;
		String text ="***********Commande de la table "+getNumTab()+" ********************\n\n";
		Iterator<Iplat> it = getListeMenu().iterator();
		while(it.hasNext()) {
			tmp = (Choix)it.next();
			text += tmp.toString()+"-----------------------\n";
		}
		return text +"Prix total: €"+getPrixTotal();	
	}
	
	/**
	 * @return le prix total de toute la commande
	 */
	public Double getPrixTotal() {
		Double prixTot =0.;
		Iterator<Iplat> it = getListeMenu().iterator();
		
		Choix tmp2 = (Choix)it.next();
		while(it.hasNext()) {
			prixTot += tmp2.getPrix();
		
		}
		return prixTot;
	}
}
