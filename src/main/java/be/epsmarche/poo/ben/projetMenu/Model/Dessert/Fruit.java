
package be.epsmarche.poo.ben.projetMenu.Model.Dessert;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import be.epsmarche.poo.ben.projetMenu.Patterns.*;

/**
 * Classe définissant le dessert de type fruit
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
	 * Constructeur de dessert
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
	 * @return le plat garni avec le type de dessert
	 */
	@Override
	public String getPeparation() {
		return platDecore.getPeparation()+ "\nDessert: "+getType();
	}
	
	/**
	 * Getters et setters
	 */
	
	@Override
	public void setPrix(Double prix) {
		this.prixDstFruit = prix;
	}

	/**
	 * @return le prix le prix du plat + le prix de l'accompagnement + le prix du dessert
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
	 * Affiche le menu comprenant le plat + accompagnement + dessert
	 */
	@Override
	public String toString() {
		return "<> " + getPeparation() +"\n";
	}

}
