package be.epsmarche.poo.ben.projetMenu.Model.Accompagnements;

import be.epsmarche.poo.ben.projetMenu.Model.Patterns.*;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.*;

/**
 * Classe définissant un accompagnement à base Riz
 * 
 * @author ben
 */
public class Riz extends PlatDecorator {

	/**
	 * categorie d'accompagnement
	 */
	protected String categorie;
	/**
	 * prix de l'accompagnement à base de riz
	 */
	protected Double prixRiz;
	/**
	 * type d'accompagnement à base de riz
	 */
	protected String typeRiz;

	/**
	 * Constructeur à 1 paramètre
	 * 
	 * @param platDecore
	 */
	public Riz(Iplat platDecore) {
		super(platDecore);
		this.platDecore = platDecore;
	}

	/**
	 * Constructeur à 3 paramètres
	 * 
	 * @param platDecore
	 * @param prixRiz
	 * @param typeRiz
	 */
	public Riz(Iplat platDecore, String typeRiz, Double prixRiz) {
		super(platDecore);
		this.typeRiz = typeRiz;
		this.prixRiz = prixRiz;
	}

//	public Riz(String categorieAcc,Iplat platDecore, String typeRiz,Double prixRiz) {
//		super(platDecore);
//		this.typeRiz = typeRiz;
//		this.prixRiz = prixRiz;
//	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;

	}

	public String getCategorie() {
		return this.categorie;
	}

	/**
	 * @return un plat avec un accompagnement à base de Riz
	 */
	@Override
	public String getPeparation() {
		return platDecore.getPeparation() + " \n >Accompagnement: " + getType();
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
	 * @return sous forme de String, un plat avec un accompagnement à base de Riz +
	 *         la somme des prix du plat et de cet accompagnement
	 */
	@Override
	public String toString() {
		return "- " + getPeparation() + " [Prix = " + getPrix() + " euros]\n";
	}

}
