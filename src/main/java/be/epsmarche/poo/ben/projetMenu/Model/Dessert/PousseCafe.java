
package be.epsmarche.poo.ben.projetMenu.Model.Dessert;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import be.epsmarche.poo.ben.projetMenu.Patterns.*;

/**
 * Classe définissant un dessert à base de Pousse-café
 * @author ben
 */
public class PousseCafe extends PlatDecorator {
	
	/**
	 * @param prixDstPousCaf = prix du dessert à base de pousse-café
	 */
	protected Double prixDstPousCaf;
	
	/**
	 * @param TypeDstFruit = type de dessert au fruit
	 */
	protected String typeDstPousCaf;
	
	/**
	 * Constructeur de dessert à base de pousse-café
	 * @param menu
	 * @param prix
	 * @param type
	 */
	public PousseCafe(Iplat menu, String type,Double prix ) {
		super(menu);
		this.prixDstPousCaf = prix;
		this.typeDstPousCaf = type;
	}
	
	/**
	 * @return le plat garni avec le type de dessert à base de pousse-café
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
		this.prixDstPousCaf = prix;
	}

	/**
	 * @return le prix le prix du plat 
	 * + le prix de l'accompagnement 
	 * + le prix du dessert à base de pousse-café
	 */
	@Override
	public Double getPrix() {
		return  platDecore.getPrix() + this.prixDstPousCaf;
	}

	@Override
	public void setType(String type) {
		this.typeDstPousCaf = type;	
	}

	@Override
	public String getType() {
		return this.typeDstPousCaf;
	}
	
	/**
	 * Affiche (lisiblement)le menu comprenant:
	 * le plat + accompagnement + dessert à base de pousse-café
	 */
	@Override
	public String toString() {
		return "<> " + getPeparation() +"\n";
	}

}
