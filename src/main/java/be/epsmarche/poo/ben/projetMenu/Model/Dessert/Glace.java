
package be.epsmarche.poo.ben.projetMenu.Model.Dessert;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import be.epsmarche.poo.ben.projetMenu.Patterns.*;

/**
 * Classe définissant un dessert à base de glace
 * @author ben
 */
public class Glace extends PlatDecorator {
	
	/**
	 * @param prixDstGlace = prix du dessert à base de glace
	 */
	protected Double prixDstGlace;
	
	/**
	 * @param TypeDstGlace = type de dessert à base de Glace
	 */
	protected String typeDstGlace;
	
	/**
	 * Constructeur de dessert
	 * @param menu
	 * @param prix
	 * @param type
	 */
	public Glace(Iplat menu, String type,Double prix ) {
		super(menu);
		this.prixDstGlace = prix;
		this.typeDstGlace = type;
	}
	
	/**
	 * @return le plat garni avec le type de dessert 
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
		this.prixDstGlace = prix;
	}

	/**
	 * @return le prix le prix du plat + le prix de l'accompagnement + le prix du dessert
	 */
	@Override
	public Double getPrix() {
		return  platDecore.getPrix() + this.prixDstGlace;
	}

	@Override
	public void setType(String type) {
		this.typeDstGlace = type;	
	}

	@Override
	public String getType() {
		return this.typeDstGlace;
	}
	
	/**
	 * Affiche (lisiblement)le menu comprenant:
	 * le plat + accompagnement + dessert
	 */
	@Override
	public String toString() {
		return "<> " + getPeparation() +"\n";
	}

}
