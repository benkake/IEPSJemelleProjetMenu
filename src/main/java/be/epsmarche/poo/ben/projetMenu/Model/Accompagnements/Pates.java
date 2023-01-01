package be.epsmarche.poo.ben.projetMenu.Model.Accompagnements;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.*;
import be.epsmarche.poo.ben.projetMenu.Patterns.*;

/**
 * Classe définissant un accompagnement à base de type Pates
 * @author ben
 */
public class Pates extends PlatDecorator {

	/**
	 * Prix de l'accompagnement à base de pates
	 */
	protected Double prixPates;
	/**
	 * Type d'accompagnement à base de pates
	 */
	protected String typePates;

	/**
	 * Constructeur à 1 paramètre
	 * @param platDecore
	 */
	public Pates(Iplat platDecore) {
		super(platDecore);
		this.platDecore = platDecore;
	}
	
	
	/**
	 * Constructeur à 2 paramètres
	 * @param platDecore
	 * @param prixPates
	 * @param typePates
	 */
	public Pates(Iplat platDecore, Double prixPates, String typePates) {
		super(platDecore);
		this.prixPates = prixPates;
		this.typePates = typePates;
	}
	
	/**
	 *@return un plat avec un accompagnement à base de pates
	 */
	@Override
	public String getPeparation() {
		return platDecore.getPeparation() + " \n >Accompagnement: "+getType();
	}
//	public String getPeparation() {
//		return platDecore.getPeparation() + " + spaguetti à la Bolognaise";
//	}

	@Override
	public void setPrix(Double prix) {
		this.prixPates = prix;
	}

	/**
	 * @return le prix du plat + le prix de l'accompagnement à base de Pates
	 */
	@Override
	public Double getPrix() {
		return (platDecore.getPrix() + prixPates);
	}

	@Override
	public void setType(String type) {
		this.typePates = type;

	}

	@Override
	public String getType() {
		return this.typePates;
	}
	
	/**
	 *@return sous la forme lisible, 
	 *un plat avec un accompagnement à base de pates + la somme des prix du plat et de cet accompagnement
	 */
	@Override
	public String toString() {
		return "- " + getPeparation() + " [Prix = " + getPrix() + " euros]\n";
	}

}
