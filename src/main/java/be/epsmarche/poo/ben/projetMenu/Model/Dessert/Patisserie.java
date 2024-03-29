package be.epsmarche.poo.ben.projetMenu.Model.Dessert;

import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatDecorator;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant un dessert à base de patisserie
 *
 * @author ben
 */
public class Patisserie extends PlatDecorator {

	/**
	 * prixDstPatis = prix du dessert à base de patisserie
	 */
	protected Double prixDstPatiss;

	/**
	 * TypeDstPatiss = type de dessert à base de patisserie
	 */
	protected String typeDstPatiss;
	/**
	 * Categorie dessert
	 */
	protected String categorie;

	/**
	 * Constructeur de dessert à base de patisserie
	 *
	 * @param menu
	 * @param prix
	 * @param type
	 */
	public Patisserie(Iplat menu, String type, Double prix) {
		super(menu);
		this.prixDstPatiss = prix;
		this.typeDstPatiss = type;
	}

	/**
	 * @return le plat garni avec le type de dessert à base de glace
	 */
	@Override
	public String getPeparation() {
		return platDecore.getPeparation() + "\n >Dessert: " + getType();
	}

	/**
	 * @return le prix le prix du plat + le prix de l'accompagnement + le prix du
	 *         dessert à base de patisserie
	 */
	@Override
	public Double getPrix() {
		return platDecore.getPrix() + this.prixDstPatiss;
	}

	/**
	 * Getters et setters
	 */

	@Override
	public void setPrix(Double prix) {
		this.prixDstPatiss = prix;
	}

	@Override
	public String getType() {
		return this.typeDstPatiss;
	}

	@Override
	public void setType(String type) {
		this.typeDstPatiss = type;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * Affiche (lisiblement)le menu comprenant: le plat + accompagnement + dessert à
	 * base de patisserie
	 */
	@Override
	public String toString() {
		return "<> " + getPeparation() + "\n";
	}
}
