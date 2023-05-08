package be.epsmarche.poo.ben.projetMenu.Model.Commandes;

import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.AccompagnementFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.DessertFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * Classe définissant les commandes
 *
 * @author ben
 */
public class Commande {

	/**
	 * Liste des menus par table. En effet chaque serie de menu par table est une
	 * commande
	 */
	private final ArrayList<Iplat> listeMenu1 = new ArrayList<>(); // commande table 1
	private final ArrayList<Iplat> listeMenu2 = new ArrayList<>(); // commande table 2
	private final ArrayList<Iplat> listeMenu3 = new ArrayList<>(); // commande table 3
	private final ArrayList<Iplat> listeMenu4 = new ArrayList<>(); // commande table 4
	private final ArrayList<Iplat> listeMenu5 = new ArrayList<>(); // commande table 5
	private final ArrayList<Iplat> listeMenu6 = new ArrayList<>(); // commande table 6
	private final Map<String, ArrayList<Iplat>> listeDesCommandes = new HashMap<>();
	private Integer idCommande;
	private String numTab;
	private String menus;
	private Double prixTotalFromDB;
	private Date dateCommande;
	private Boolean payee;
	/**
	 * listeMenu: la liste de menus correspondant à une commande quelconque
	 */
	private ArrayList<Iplat> listeMenu = new ArrayList<>();
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
		ArrayList<String> lesTables = new ArrayList<>();
		lesTables.add(" 1");
		lesTables.add(" 2");
		lesTables.add(" 3");
		lesTables.add(" 4");
		lesTables.add(" 5");
		lesTables.add(" 6");
	}

	public String getNumTab() {
		return this.numTab;
	}

	public void setNumTab(String tabNum) {
		this.numTab = tabNum;
	}

	public Integer getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(Integer id) {
		this.idCommande = id;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Boolean getPayee() {
		return payee;
	}

	public void setPayee(Boolean payee) {
		this.payee = payee;
	}

	public Double getPrixTotalFromDB() {
		return prixTotalFromDB;
	}

	public void setPrixTotalFromDB(Double prixTotalFromDB) {
		this.prixTotalFromDB = prixTotalFromDB;
	}

	public ArrayList<Iplat> getListeMenu() {
		if (getNumTab().equals(" 1"))
			listeMenu = listeMenu1;
		if (getNumTab().equals(" 2"))
			listeMenu = listeMenu2;
		if (getNumTab().equals(" 3"))
			listeMenu = listeMenu3;
		if (getNumTab().equals(" 4"))
			listeMenu = listeMenu4;
		if (getNumTab().equals(" 5"))
			listeMenu = listeMenu5;
		if (getNumTab().equals(" 6"))
			listeMenu = listeMenu6;
		return listeMenu;
	}

	public Map<String, ArrayList<Iplat>> getListeDesCommandes() {
		return listeDesCommandes;
	}

	public void addCommande(String numTabl, ArrayList<Iplat> listeDeMenu) {
		listeDesCommandes.put(numTabl, listeDeMenu);
	}

	public void addMenu(Iplat menu, String tabNum) {
		if (tabNum.equals(" 1"))
			listeMenu1.add(menu);
		if (tabNum.equals(" 2"))
			listeMenu2.add(menu);
		if (tabNum.equals(" 3"))
			listeMenu3.add(menu);
		if (tabNum.equals(" 4"))
			listeMenu4.add(menu);
		if (tabNum.equals(" 5"))
			listeMenu5.add(menu);
		if (tabNum.equals(" 6"))
			listeMenu6.add(menu);
	}

	/**
	 * @param menu ajoute les menus dans la liste des menus( la commande)
	 */
	public void addMenu(Iplat menu) {
		getListeMenu().add(menu);
	}

	public String displayCommandForDB() {
		String commandMenus = null;
		for (int i = 0; i < getListeMenu().size(); ++i) {
			commandMenus += getListeMenu().get(i);
		}
		return commandMenus;
	}

	/**
	 * @return l'ensemble de la commande sous forme de chaîne de caractères
	 */
	public String displayCommand() {
		Iplat tmp;
		StringBuilder text; // le StringBuilder permet de concaténer des String avec des valeurs changeantes
		text = new StringBuilder("***********Commande de la table " + getNumTab() + " ********************\n\n");
		for (int i = 0; i < getListeMenu().size(); ++i) {
			tmp = getListeMenu().get(i);
			text.append(tmp.toString()).append("-----------------------Prix unitaire: €")
					.append(getListeMenu().get(i).getPrix()).append("\n");
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
	// méthode qui retourne l'état du paymement
	public boolean isPaid() {
		return Boolean.TRUE.equals(payee); // cette forme evite le cas ou le boolean payee est null
	}
}
