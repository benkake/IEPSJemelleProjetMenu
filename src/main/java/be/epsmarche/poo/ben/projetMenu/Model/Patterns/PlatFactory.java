package be.epsmarche.poo.ben.projetMenu.Model.Patterns;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Poisson;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Viande;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Volaille;

/**
 * Classe permettant de sélectionner les catégories de plat
 *
 * @author ben
 */

public class PlatFactory {

	/**
	 * Constructeur par defaut
	 */
	public PlatFactory() {

	}

	/**
	 * @param categorieDePlat
	 * @param typePlat
	 * @param prixPlat
	 * @return un plat en fonction de la catégorie de plat choisie (soient à base de
	 *         viande, de volaille ou de poisson)
	 */

	public Iplat getPlat(String categorieDePlat, String typePlat, Double prixPlat) {
		if (categorieDePlat == null)
			return null;
		if (categorieDePlat.equalsIgnoreCase("viande"))
			return new Viande(typePlat, prixPlat);
		if (categorieDePlat.equalsIgnoreCase("poisson"))
			return new Poisson(typePlat, prixPlat);
		if (categorieDePlat.equalsIgnoreCase("volaille"))
			return new Volaille(typePlat, prixPlat);
		return null;
	}
}
