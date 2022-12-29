package be.epsmarche.poo.ben.projetMenu.Model.Accompagnements;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.*;
import be.epsmarche.poo.ben.projetMenu.Patterns.*;

public class PommesDeTerre extends PlatDecorator {

	protected Double prixPommes = 3.5;

	public PommesDeTerre(Iplat platDecore) {
		super(platDecore);
		this.platDecore = platDecore;
	}

	@Override
	public String getPeparation() {
		return platDecore.getPeparation() + " + pomme de terre cuite au four";
	}

	@Override
	public void setPrix(Double prix) {

	}

	/*
	 * getPrix returne le prix des Pomme de terre (attribut local) plus le prix de
	 * la viande (attribut hérité obtenu via la classe platDecore)
	 */
	@Override
	public Double getPrix() {
		return (platDecore.getPrix() + prixPommes);
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public String toString() {
		return "- " + getPeparation() + " [Prix = " + getPrix() + " euros]\n";
	}

}
