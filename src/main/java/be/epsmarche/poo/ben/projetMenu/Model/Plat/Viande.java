package be.epsmarche.poo.ben.projetMenu.Model.Plat;

/**
 * Cette classe représente un plat à base de viande
 */
public class Viande implements Iplat {
	
	//protected Double prix = 10.5;
	protected Double prix;
	//protected String type = "Steak de 200g de crocodile";
	protected String type;
	
	//public String preparation = getPeparation();

	/**
	 * Constructeurs de nouvels objets Viande
	 */
	
	public Viande() {
		super();
	}

	/**
	 * @param type de viande
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
	 * @return au format lisible le prix et le type de viande
	 */
	@Override
	public String toString() {
		return "Viande [prix=" + getPrix() + ", type=" + getType() + "]";
	}
	
	@Override
	public String getPeparation() {
		return getType() + " mariné au piment africain";
	}

}
