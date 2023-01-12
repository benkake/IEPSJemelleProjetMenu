
package be.epsmarche.poo.ben.projetMenu.Model.Dessert;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import be.epsmarche.poo.ben.projetMenu.Patterns.*;

/**
 * Classe définissant un dessert à base de fruit
 * @author ben
 */
public class Fruit extends PlatDecorator {
	
	/**
	 * @param prixDstFruit = prix du dessert au fruit
	 */
	protected Double prixDstFruit;
	
	/**
	 * @param TypeDstFruit = type de dessert au fruit
	 */
	protected String typeDstFruit;
	
	/**
	 * Constructeur de dessert à base de fruit
	 * @param menu
	 * @param prix
	 * @param type
	 */
	public Fruit(Iplat menu, String type,Double prix ) {
		super(menu);
		this.prixDstFruit = prix;
		this.typeDstFruit = type;
	}
	
	/**
	 * @return le plat garni avec le type de dessert à base de fruit
	 */
	@Override
	public String getPeparation() {
		return platDecore.getPeparation()+ "\n >Dessert: "+getType();
	}
	
	/**
	 * Getters et setters
	 */
	
	@Override
	public void setPrix(Double prix) {
		this.prixDstFruit = prix;
	}

	/**
	 * @return le prix le prix du plat 
	 * + le prix de l'accompagnement 
	 * + le prix du dessert à base de fruit
	 */
	@Override
	public Double getPrix() {
		return  platDecore.getPrix() + this.prixDstFruit;
	}

	@Override
	public void setType(String type) {
		this.typeDstFruit = type;	
	}

	@Override
	public String getType() {
		return this.typeDstFruit;
	}
	
	/**
	 * Affiche (lisiblement)le menu comprenant:
	 * le plat + accompagnement + dessert à base de fruit
	 */
	@Override
	public String toString() {
		return "<> " + getPeparation()+"\n";
	}

}
