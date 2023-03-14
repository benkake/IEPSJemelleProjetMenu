package be.epsmarche.poo.ben.projetMenu.Model.Plat;

/**
 * Classe definissant un plat à base de viande
 * 
 * @author ben
 */
public class Viande implements Iplat {
	/**
	 * @Prix du plat
	 */
	protected Double prix;
	/**
	 * Type de plat
	 */
	protected String type;

	/**
	 * Catégorie de plat
	 */
	protected String categorie;

	/**
	 * Constructeurs de nouvels objets plat à base de Viande
	 */
	public Viande() {
		super();
	}

	/**
	 * type de viande
	 */
	public Viande(String type) {
		super();
		this.type = getType();
	}

	/**
	 * @param type
	 * @param prix de la viande
	 */
	public Viande(String type, Double prix) {
		super();
		this.type = type;
		this.prix = prix;
	}

	/*
	 * getters et setters
	 */
	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Double getPrix() {
		return this.prix;
	}

	/**
	 * @return le type de plat à base de viande + la description de sa préparation
	 */
	@Override
	public String getPeparation() {
		return getType() + " mariné au piment africain";
	}

	/**
	 * @return au format String la méthode getPreparation()
	 */
	@Override
	public String toString() {
		return getPeparation();
	}
}
