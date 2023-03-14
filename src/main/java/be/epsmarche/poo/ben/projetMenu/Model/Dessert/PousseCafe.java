
package be.epsmarche.poo.ben.projetMenu.Model.Dessert;

import be.epsmarche.poo.ben.projetMenu.Model.Patterns.*;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant un dessert à base de Pousse-café
 * 
 * @author ben
 */
public class PousseCafe extends PlatDecorator {
	/**
	 * prixDstPousCaf = prix du dessert à base de pousse-café
	 */
	protected Double prixDstPousCaf;
	/**
	 * TypeDstFruit = type de dessert au fruit
	 */
	protected String typeDstPousCaf;
	/**
	 * Categorie Dessert
	 */
	protected String categorie;

	/**
	 * Constructeur de dessert à base de pousse-café
	 * 
	 * @param menu
	 * @param prix
	 * @param type
	 */
	public PousseCafe(Iplat menu, String type, Double prix) {
		super(menu);
		this.prixDstPousCaf = prix;
		this.typeDstPousCaf = type;
	}

	/**
	 * @return le plat garni avec le type de dessert à base de pousse-café
	 */
	@Override
	public String getPeparation() {
		return platDecore.getPeparation() + "\n >Dessert: " + getType();
	}

	/**
	 * Getters et setters
	 */

	@Override
	public void setPrix(Double prix) {
		this.prixDstPousCaf = prix;
	}

	/**
	 * @return le prix le prix du plat + le prix de l'accompagnement + le prix du
	 *         dessert à base de pousse-café
	 */
	@Override
	public Double getPrix() {
		return platDecore.getPrix() + this.prixDstPousCaf;
	}

	@Override
	public void setType(String type) {
		this.typeDstPousCaf = type;
	}

	@Override
	public String getType() {
		return this.typeDstPousCaf;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * Affiche (lisiblement)le menu comprenant: le plat + accompagnement + dessert à
	 * base de pousse-café
	 */
	@Override
	public String toString() {
		return "<> " + getPeparation() + "\n";
	}
}
