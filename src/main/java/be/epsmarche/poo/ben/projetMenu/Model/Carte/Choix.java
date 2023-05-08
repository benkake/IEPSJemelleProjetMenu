package be.epsmarche.poo.ben.projetMenu.Model.Carte;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

public class Choix implements Iplat {

	/**
	 * id d'un composant du menu
	 */
	private String id;

	/**
	 * categorie de produit composant le menu
	 */
	private String categorie;

	/**
	 * type de produit composant le plat ou l'accompagnement ou le dessert
	 */
	private String type;

	/**
	 * prix du produit
	 */
	private Double prix;

	/**
	 * description du produit
	 */
	private String description;

	/**
	 * Constructeur sans param
	 *
	 * @since version 3.0
	 */
	public Choix() {
	}

	/**
	 * Constructeur
	 *
	 * @param id
	 * @param categorie
	 * @param type
	 * @param prix
	 * @param description
	 * @return une instance de la Classe choix
	 */
	public Choix(String id, String categorie, String type, Double prix, String description) {
		this.id = id;
		this.categorie = categorie;
		this.type = type;
		this.prix = prix;
		this.description = description;
	}

	/**
	 * Getters et Setters
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getPeparation() {
		return null;
	}
}