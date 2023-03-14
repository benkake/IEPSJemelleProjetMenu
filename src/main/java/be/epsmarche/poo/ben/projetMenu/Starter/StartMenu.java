package be.epsmarche.poo.ben.projetMenu.Starter;

import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;

/**
 * Classe permettant de lancer le programme
 * @author ben 
 */
public class StartMenu {
	public static void main(String[] args) {
		MenuController contr = new MenuController();
		contr.start();
	}
}
