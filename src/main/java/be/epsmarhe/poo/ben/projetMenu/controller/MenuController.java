package be.epsmarhe.poo.ben.projetMenu.controller;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;


public class MenuController {

	public MenuController() {}
	
	public static String showMenu(Iplat plat) {
		return (plat.toString());
	}
	
	public static Double subTotal(Iplat menu) {
		return menu.getPrix();
	}
	
	
}
