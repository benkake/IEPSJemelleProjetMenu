package be.epsmarhe.poo.ben.projetMenu.controller;

import be.epsmarche.poo.ben.projetMenu.Patterns.*;

public class MenuController {

	public MenuController() {
	}

	public String showOrder(PlatDecorator plat) {
		return (plat.toString());
	}
}
