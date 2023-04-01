package be.epsmarche.poo.ben.projetMenu.Model.Dessert;

import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatDecorator;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant un dessert à base de glace
 *
 * @author ben
 */
public class Glace extends PlatDecorator {

	/**
	 * prixDstGlace = prix du dessert à base de glace
	 */
	protected Double prixDstGlace;

	/**
	 * TypeDstGlace = type de dessert à base de Glace
	 */
	protected String typeDstGlace;

	/**
	 * Categorie dessert
	 */
	protected String categorie;

	/**
	 * Constructeur de dessert à base de Glace
	 *
	 * @param menu
	 * @param prix
	 * @param type
	 */
	public Glace(Iplat menu, String type, Double prix) {
		super(menu);
		this.prixDstGlace = prix;
		this.typeDstGlace = type;
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
	 *         dessert à base de glace
	 */
	@Override
	public Double getPrix() {
		return platDecore.getPrix() + this.prixDstGlace;
	}

	/**
	 * Getters et setters
	 */
	@Override
	public void setPrix(Double prix) {
		this.prixDstGlace = prix;
	}

	@Override
	public String getType() {
		return this.typeDstGlace;
	}

	@Override
	public void setType(String type) {
		this.typeDstGlace = type;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * Affiche (lisiblement)le menu comprenant: le plat + accompagnement + dessert à
	 * base de glace
	 */
	@Override
	public String toString() {
		return "<> " + getPeparation() + "\n";
	}

}
