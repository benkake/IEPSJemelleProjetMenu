package be.epsmarche.poo.ben.projetMenu.Model.Accompagnements;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.*;
import be.epsmarche.poo.ben.projetMenu.Patterns.*;

public class Riz extends PlatDecorator {

	protected Double prixRiz = 7.5;

	public Riz(Iplat platDecore) {
		super(platDecore);
		this.platDecore = platDecore;
	}

	@Override
	public String getPeparation() {
		return platDecore.getPeparation() + " + riz cantonais";
	}

	@Override
	public void setPrix(Double prix) {

	}

	/*
	 * getPrix returne le prix du riz (attribut local) plus le prix de la viande
	 * (attribut hérité obtenu via la classe platDecore)
	 */
	@Override
	public Double getPrix() {
		return (platDecore.getPrix() + prixRiz);
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "- " + getPeparation() + " [Prix = " + getPrix() + " euros]\n";
	}

}
