package projetMenu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.*;
import be.epsmarche.poo.ben.projetMenu.Model.Dessert.*;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.*;
import be.epsmarche.poo.ben.projetMenu.Patterns.PlatFactory;
/**
 * Classe permettant de tester les commandes
 * @author ben
 */
public class Test02LesCommandes {
	/**
	 * @param menu = représentant 4 types de menus
	 */
	private static Iplat menu1, menu2, menu3, menu4;
	/**
	 * constructeur par defaut
	 */
	public Test02LesCommandes() {}
	/**
	 * Création de 4 menus distincts
	 */
	@BeforeAll
	public static void settingTest() {
		
		// Création menu1
		menu1 = new PlatFactory().getPlat("viande","Steak de 200g de crocodile",10.);
		menu1 = new Riz(menu1,"riz cantonnais",7.5);
		menu1 = new Fruit(menu1,"Salade de fruit tropicaux",2.5);
		
		// Création menu2
		menu2 = new PlatFactory().getPlat("volaille","Steak de 240g de dinde",16.5);
		menu2 = new Frites(menu2,"frites de manioc ",3.5);
		menu2 = new PousseCafe(menu2,"Cognac de Bourgogne",5.);
		
		//Création menu3
		menu3 = new PlatFactory().getPlat("Viande","Steak de 150g de mouton",18.5);
		menu3 = new PommesDeTerre(menu3,"Pomme de terre cuite à la vapeur ",4.5);
		menu3 = new Glace(menu3,"Milkshake à la vanille ",2.0);
		
		// Création menu4
		menu4 = new PlatFactory().getPlat("Poisson","Steak de 240g de requin",30.5);
		menu4 = new Pates(menu4,"Macaroni de Sicile ",6.5);
		menu4 = new Patisserie(menu4,"Crêpe à la crème au chocolat",5.);
		
	}
	
	@Test
	public void TestAddMenu() {
		Commande Cd1 = new Commande("tabl1");
		Cd1.addMenu(menu1);
		assertThat(Cd1.getListeMenu().size()).isEqualTo(1);
		System.out.println("Test add menu1 Ok!");
		Cd1.addMenu(menu2);
		assertThat(Cd1.getListeMenu().size()).isEqualTo(2);
		System.out.println("Test add menu2 Ok!");
		Cd1.addMenu(menu3);
		assertThat(Cd1.getListeMenu().size()).isEqualTo(3);
		System.out.println("Test add menu3 Ok!");
		Cd1.addMenu(menu4);
		assertThat(Cd1.getListeMenu().size()).isEqualTo(4);
		System.out.println("Test add menu4 Ok!");
		
		Cd1.getPrixTotal();
		assertThat(Cd1.getPrixTotal()).isEqualTo(112.0);
		System.out.println("Test calcul pix total Ok!");
		//String
		
		
	}
	
	
	
}