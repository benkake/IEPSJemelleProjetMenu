package be.epsmarche.poo.ben.projetMenu.Model.Patterns;

import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Dessert.Fruit;
import be.epsmarche.poo.ben.projetMenu.Model.Dessert.Glace;
import be.epsmarche.poo.ben.projetMenu.Model.Dessert.Patisserie;
import be.epsmarche.poo.ben.projetMenu.Model.Dessert.PousseCafe;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

public class DessertFactory {

	/**
	 * Constructeur par defaut
	 */
	public DessertFactory() {

	}

	public Iplat getDess(Iplat plat, Choix dess) {

		if (dess.getType() == null)
			return null;
		if (dess.getType().equalsIgnoreCase("glace"))
			return new Glace(plat, dess.getDescription(), dess.getPrix());
		if (dess.getType().equalsIgnoreCase("fruit"))
			return new Fruit(plat, dess.getDescription(), dess.getPrix());
		if (dess.getType().equalsIgnoreCase("patisserie"))
			return new Patisserie(plat, dess.getDescription(), dess.getPrix());
		if (dess.getType().equalsIgnoreCase("pousseCafe"))
			return new PousseCafe(plat, dess.getDescription(), dess.getPrix());

		return null;
	}
}
