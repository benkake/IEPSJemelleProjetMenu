package be.epsmarche.poo.ben.projetMenu.Starter;

import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;

/**
 * @author ben Classe permettant de lancer le programme
 */
public class StartMenu {
	public static void main(String[] args) {
		MenuController contr = new MenuController();
		contr.start();
	}
}
