package be.epsmarche.poo.ben.projetMenu.Patterns;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

public abstract class PlatDecorator implements Iplat {

	protected Iplat platDecore;

	public PlatDecorator(Iplat platDecore) {
		super();
		this.platDecore = platDecore;
	}

}
