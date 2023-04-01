package be.epsmarche.poo.ben.projetMenu.Model.Dessert;

import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatDecorator;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant un dessert à base de fruit
 *
 * @author ben
 */
public class Fruit extends PlatDecorator {
	/**
	 * prixDstFruit = prix du dessert au fruit
	 */
	protected Double prixDstFruit;
	/**
	 * TypeDstFruit = type de dessert au fruit
	 */
	protected String typeDstFruit;
	/**
	 * Categorie de dessert
	 */
	protected String categorie;

	/**
	 * Constructeur de dessert à base de fruit
	 *
	 * @param menu
	 * @param prix
	 * @param type
	 */
	public Fruit(Iplat menu, String type, Double prix) {
		super(menu);
		this.prixDstFruit = prix;
		this.typeDstFruit = type;
	}

	/**
	 * @return le plat garni avec le type de dessert à base de fruit
	 */
	@Override
	public String getPeparation() {
		return platDecore.getPeparation() + "\n >Dessert: " + getType();
	}

	/**
	 * @return le prix le prix du plat + le prix de l'accompagnement + le prix du
	 *         dessert à base de fruit
	 */
	@Override
	public Double getPrix() {
		return platDecore.getPrix() + this.prixDstFruit;
	}

	/**
	 * Getters et setters
	 */

	@Override
	public void setPrix(Double prix) {
		this.prixDstFruit = prix;
	}

	@Override
	public String getType() {
		return this.typeDstFruit;
	}

	@Override
	public void setType(String type) {
		this.typeDstFruit = type;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * Affiche (sous forme de String)le menu comprenant: le plat + accompagnement +
	 * dessert à base de fruit
	 */
	@Override
	public String toString() {
		return "<> " + getPeparation() + "\n";
	}

}
