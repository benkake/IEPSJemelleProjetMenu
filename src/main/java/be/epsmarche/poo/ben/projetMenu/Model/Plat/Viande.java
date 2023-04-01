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

	@Override
	public String getType() {
		return this.type;
	}

	/*
	 * getters et setters
	 */
	@Override
	public void setType(String type) {
		this.type = type;
	}

	public Double getPrix() {
		return this.prix;
	}

	@Override
	public void setPrix(Double prix) {
		this.prix = prix;
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
