package be.epsmarche.poo.ben.projetMenu.Model.Accompagnements;

import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatDecorator;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant un accompagnement à base de Pates
 * 
 * @author ben
 */
public class Pates extends PlatDecorator {

	/**
	 * Prix de l'accompagnement à base de pates
	 */
	protected Double prixPates;
	/**
	 * Type d'accompagnement à base de pates
	 */
	protected String typePates;
	/**
	 * Catégorie d'accompagnement
	 */
	protected String categorie;

	/**
	 * Constructeur à 1 paramètre
	 * 
	 * @param platDecore
	 */
	public Pates(Iplat platDecore) {
		super(platDecore);
		this.platDecore = platDecore;
	}

	/**
	 * Constructeur à 3 paramètres
	 * 
	 * @param platDecore
	 * @param prixPates
	 * @param typePates
	 */
	public Pates(Iplat platDecore, String typePates, Double prixPates) {
		super(platDecore);
		this.prixPates = prixPates;
		this.typePates = typePates;
	}

	/**
	 * @return un plat avec un accompagnement à base de pates
	 */
	@Override
	public String getPeparation() {
		return platDecore.getPeparation() + " \n >Accompagnement: " + getType();
	}

	@Override
	public void setPrix(Double prix) {
		this.prixPates = prix;
	}

	/**
	 * @return le prix du plat + le prix de l'accompagnement à base de Pates
	 */
	@Override
	public Double getPrix() {
		return (platDecore.getPrix() + prixPates);
	}

	@Override
	public void setType(String type) {
		this.typePates = type;

	}

	@Override
	public String getType() {
		return this.typePates;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getCategorie() {
		return categorie;
	}

	/**
	 * @return sous forme de String un plat avec un accompagnement à base de pates +
	 *         la somme des prix du plat et de cet accompagnement
	 */
	@Override
	public String toString() {
		return "- " + getPeparation() + " [Prix = " + getPrix() + " euros]\n";
	}

}
