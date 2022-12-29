package be.epsmarche.poo.ben.projetMenu.Patterns;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.*;

public class PlatFactory {

	public PlatFactory() {

	}

	/*
	 * Types de plat: Poisson, viande, Valaille
	 */

	public Iplat getPlat(String typeDePlat) {

		if (typeDePlat == null)
			return null;
		if (typeDePlat.equalsIgnoreCase("viande"))
			return new Viande("viande");
		if (typeDePlat.equalsIgnoreCase("poisson"))
			return new Poisson("poisson");
		if (typeDePlat.equalsIgnoreCase("volaille"))
			return new Volaille("volaille");

		return null;
	}

}
