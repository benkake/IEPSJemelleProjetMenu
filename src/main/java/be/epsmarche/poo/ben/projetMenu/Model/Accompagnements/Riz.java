package be.epsmarche.poo.ben.projetMenu.Model.Accompagnements;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.*;
import be.epsmarche.poo.ben.projetMenu.Patterns.*;

/**
 * Classe définissant un accompagnement à base de type Riz
 * @author ben
 */
public class Riz extends PlatDecorator {
	/**
	 *@param prix de l'accompagnement à base de riz
	 */
	protected Double prixRiz;
	/**
	 *@param type d'accompagnement à base de riz
	 */
	protected String typeRiz;

	/**
	 * Constructeur à 1 paramètre
	 * @param platDecore
	 */
	public Riz(Iplat platDecore) {
		super(platDecore);
		this.platDecore = platDecore;
	}
	
	/**
	 * Constructeur à 2 paramètres
	 * @param platDecore
	 * @param prixRiz
	 * @param typeRiz
	 */
	public Riz(Iplat platDecore, String typeRiz,Double prixRiz) {
		super(platDecore);
		this.typeRiz = typeRiz;
		this.prixRiz = prixRiz;
		
	}

	/**
	 *@return un plat avec un accompagnement à base de Riz
	 */
	@Override
	public String getPeparation() {
		return platDecore.getPeparation() +" \n >Accompagnement: "+ getType();
	}
	
	@Override
	public void setPrix(Double prix) {
		this.prixRiz = prix;
	}

	/**
	 * @return le prix du plat + le prix de l'accompagnement à base de riz
	 */
	@Override
	public Double getPrix() {
		return (platDecore.getPrix() + prixRiz);
	}

	@Override
	public void setType(String type) {
		this.typeRiz = type;

	}

	@Override
	public String getType() {
		return this.typeRiz;
	}

	/**
	 *@return sous la forme lisible, 
	 *un plat avec un accompagnement à base de Riz + la somme des prix du plat et de cet accompagnement
	 */
	@Override
	public String toString() {
		return "- " + getPeparation() + " [Prix = " + getPrix() + " euros]\n";
	}

}
