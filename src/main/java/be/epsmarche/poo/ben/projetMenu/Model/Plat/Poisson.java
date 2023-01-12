package be.epsmarche.poo.ben.projetMenu.Model.Plat;
/**
 * Classe definissant un plat à base de Poisson
 * @author ben
 */
public class Poisson implements Iplat {

	/**
	 *@param Prix du plat
	 */
	protected Double prix;
	/**
	 *@param Type de plat
	 */
	//protected String type = "Steak de thon rouge";
	protected String type;
	
	/**
	 * Constructeurs de nouvels objets plat à base de poisson
	 */
	public Poisson() {
		super();
	}
	/**
	 * @param type
	 */
	public Poisson(String type) {
		super();
		this.type = getType();
	}
	/**
	 * @param type
	 * @param prix
	 */
	public Poisson( String type, Double prix) {
		super();
		this.type = type;
		this.prix = prix;
	}
	
	/**
	 * Getters et Setters
	 */
	@Override
	public Double getPrix() {
		return this.prix;
	}

	@Override
	public void setPrix(Double prix) {
		this.prix = prix;
	}

	@Override
	public String getType() {
		return this.type;
	}
	@Override
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return le type de plat à base de poisson + la description de sa préparation
	 */
	@Override
	public String getPeparation() {
		return getType() + " mariné à la farine de blé";
	}
	/**
	 * @return au format lisible la méthode getPreparation()
	 */
	@Override
	public String toString() {
		return getPeparation();
	}

}
