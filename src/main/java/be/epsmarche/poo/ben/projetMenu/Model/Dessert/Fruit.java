
package be.epsmarche.poo.ben.projetMenu.Model.Dessert;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import be.epsmarche.poo.ben.projetMenu.Patterns.*;

/**
 * Classe définissant le dessert de type fruit
 * @author ben
 */
public class Fruit extends PlatDecorator {
	/*
	 * Prix du dessert au fruit
	 */
	protected Double prixDessFruit;
	/*
	 * Type du dessert au fruit
	 */
	protected String typeDessFruit;
	/**
	 * @param platDecore
	 * @param prixDessFruit
	 * @param typeFruit
	 */
	public Fruit(Iplat platDecore, Double prix, String type) {
		super(platDecore);
		this.prixDessFruit = prix;
		this.typeDessFruit = type;
	}
	
	@Override
	public String getPeparation() {
		return platDecore.getPeparation()+ "\nDessert: "+getType();
	}

	@Override
	public void setPrix(Double prix) {
		this.prixDessFruit = prix;
	}

	@Override
	public Double getPrix() {
		return  platDecore.getPrix() + this.prixDessFruit;
	}

	@Override
	public void setType(String type) {
		this.typeDessFruit = type;	
	}

	@Override
	public String getType() {
		return this.typeDessFruit;
	}

}
