package be.epsmarche.poo.ben.projetMenu.Model.Accompagnements;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.*;
import be.epsmarche.poo.ben.projetMenu.Patterns.*;

/**
 * Classe définissant un accompagnement à base de type pomme de terre
 * @author ben
 */
public class PommesDeTerre extends PlatDecorator {

	/**
	 * @param Prix de l'accompagnement à base de pomme de terre
	 */
	protected Double prixPommesDT;
	/**
	 * @param Type d'accompagnement à base de pomme de terre
	 */
	protected String typePommesDT;
	
	/**
	 * Constructeur à 1 paramètre
	 * @param platDecore
	 */
	public PommesDeTerre(Iplat platDecore) {
		super(platDecore);
		this.platDecore = platDecore;
	}
	
	public PommesDeTerre(Iplat platDecore, String typePommesDT,Double prixPommesDT) {
		super(platDecore);
		this.typePommesDT = typePommesDT;
		this.prixPommesDT = prixPommesDT;
		
		
	}
	
	/**
	 *@return un plat avec un accompagnement à base de pomme de terre
	 */
	@Override
	public String getPeparation() {
		return platDecore.getPeparation() + " \n >Accompagnement: "+getType();
	}
	
//	public String getPeparation() {
//		return platDecore.getPeparation() + " + pomme de terre cuite au four";
//	}

	@Override
	public void setPrix(Double prix) {
		this.prixPommesDT = prix;
	}

	/**
	 * @return le prix du plat + le prix de l'accompagnement à base de pomme de terre
	 */
	@Override
	public Double getPrix() {
		return (platDecore.getPrix() + prixPommesDT);
	}

	@Override
	public void setType(String type) {
		this.typePommesDT= type;

	}

	@Override
	public String getType() {
		return this.typePommesDT;
	}
	
	/**
	 *@return sous la forme lisible, 
	 *un plat avec un accompagnement à base de Pomme de terre + la somme des prix du plat et de cet accompagnement
	 */
	@Override
	public String toString() {
		return "- " + getPeparation() + " [Prix = " + getPrix() + " euros]\n";
	}

}
