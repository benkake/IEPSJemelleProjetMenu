package be.epsmarche.poo.ben.projetMenu.Model.Accompagnements;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.*;
import be.epsmarche.poo.ben.projetMenu.Patterns.*;

/**
 * Classe définissant un accompagnement à base de type Frites
 * @author ben
 */
public class Frites extends PlatDecorator {
	
	/**
	 * Prix de l'accompagnement à base  de frites
	 */
	protected Double prixFrites;
	
	/**
	 * Type d'accompagnement à base de frites
	 */
	protected String typeFrites;
	
	/**
	 * Constructeur à 1 paramètre
	 * @param platDecore
	 */
	public Frites(Iplat platDecore) {
		super(platDecore);
		this.platDecore = platDecore;
	}
	
	/**
	 * Constructeur à 2 paramètres
	 * @param menu
	 * @param typeFrites
	 * @param prixFrites
	 */
	public Frites(Iplat menu, String typeFrites,Double prixFrites ) {
		super(menu);
		this.typeFrites = typeFrites;
		this.prixFrites = prixFrites;
	};
	
	/**
	 *@return un plat avec un accompagnement à base de Frites
	 */
	@Override
	public String getPeparation() {           
		return platDecore.getPeparation() +" \n >Accompagnement: "+ this.getType();
	}

	@Override
	public void setPrix(Double prix) {
		this.prixFrites = prix;
	}

	/**
	 * @return le prix du plat + le prix de l'accompagnement à base de frites
	 */
	@Override
	public Double getPrix() {
		return (platDecore.getPrix() + prixFrites);
	}

	@Override
	public void setType(String type) {  
		this.typeFrites = type;
	}

	@Override
	public String getType() { 
		return typeFrites;
	}
	
	/**
	 *@return sous la forme lisible, 
	 *un plat avec un accompagnement à base de Frites + la somme des prix du plat et de cet accompagnement
	 */
	@Override
	public String toString() {
		return "- " + getPeparation() + " [Prix = " + getPrix() + " euros]\n";
	}

}
