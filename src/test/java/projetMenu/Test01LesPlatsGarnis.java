package projetMenu;

import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;
import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.Frites;
import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.Pates;
import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.PommesDeTerre;
import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.Riz;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatDecorator;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Test01LesPlatsGarnis {
	/* Création d'un attribut de stockage de l'objet factory */
	public static PlatFactory fact;

	/* Instanciation du Factory avant tous les tests */
	@BeforeAll
	public static void factInstance() {
		fact = new PlatFactory();
	}

	/* Test plat de viande accompagné de frites */
	@Order(1)
	@Test
	public void testViandeAuxFrites() {
		/* Création d'un objet viande */
		Iplat plat1 = fact.getPlat("Viande", "Steak de 200 g de crocodile", 10.0);
		assertThat(plat1.getPrix()).isEqualTo(10.0);
		assertThat(plat1.getPeparation()).isEqualTo("Steak de 200 g de crocodile mariné au piment africain");
		System.out.println("Test Plat factory Steak de viande: OK !");
		/* Création d'un objet viande garnie de frites */
		PlatDecorator platGarni1 = new Frites(plat1);
		platGarni1.setPrix(4.5);
		platGarni1.setType("frites de patate douce");
		assertThat(platGarni1.getPrix()).isEqualTo(14.5);
		assertThat(platGarni1.getPeparation()).isEqualTo("Steak de 200 g de crocodile mariné au piment africain \n"
				+ " >Accompagnement: " + "frites de patate douce");
		assertThat(MenuController.showMenu(platGarni1))
				.isEqualTo("- Steak de 200 g de crocodile mariné au piment africain \n" + " >Accompagnement: "
						+ "frites de patate douce [Prix = 14.5 euros]\n");
		System.out.println("Test Steak de viande garni de frites: OK !\n");
	}

	/*
	 * Test plat de volaille accompagné de pomme de terre
	 */
	@Order(2)
	@Test
	public void testVolailleAuxPommes2Ter() {
		/* Création d'un objet volaille */
		Iplat plat2 = fact.getPlat("Volaille", "Steak de 300 g de Dinde", 12.0);
		assertThat(plat2.getPrix()).isEqualTo(12.0);
		assertThat(plat2.getPeparation()).isEqualTo("Steak de 300 g de Dinde mariné à la moutarde de Dijon");
		System.out.println("Test Plat factory Steak de dinde: OK !");
		PlatDecorator platGarni2 = new PommesDeTerre(plat2);
		platGarni2.setPrix(3.5);
		platGarni2.setType("pomme de terre cuite au four");
		assertThat(platGarni2.getPrix()).isEqualTo(15.5);
		assertThat(platGarni2.getPeparation()).isEqualTo("Steak de 300 g de Dinde mariné à la moutarde de Dijon \n"
				+ " >Accompagnement: " + "pomme de terre cuite au four");
		assertThat(MenuController.showMenu(platGarni2))
				.isEqualTo("- Steak de 300 g de Dinde mariné à la moutarde de Dijon \n" + " >Accompagnement: "
						+ "pomme de terre cuite au four [Prix = 15.5 euros]\n");
		System.out.println("Test Steak de volaille garni de pommes de terre: OK !\n");
	}

	/* Test plat de poisson accompagné de riz */
	@Order(3)
	@Test
	public void testPoissonAuxRiz() {
		/* Création d'un objet poisson */
		Iplat plat3 = fact.getPlat("Poisson", "Steak de 250 g de Saumon", 13.0);
		assertThat(plat3.getPrix()).isEqualTo(13.0);
		assertThat(plat3.getPeparation()).isEqualTo("Steak de 250 g de Saumon mariné à la farine de blé");
		System.out.println("Test Plat factory Steak de poisson: OK !");
		/* Création d'un objet poisson garni de riz */
		PlatDecorator platGarni3 = new Riz(plat3);
		platGarni3.setPrix(7.5);
		platGarni3.setType("riz cantonais");
		assertThat(platGarni3.getPrix()).isEqualTo(20.5);
		assertThat(platGarni3.getPeparation()).isEqualTo(
				"Steak de 250 g de Saumon mariné à la farine de blé \n" + " >Accompagnement: " + "riz cantonais");
		assertThat(MenuController.showMenu(platGarni3))
				.isEqualTo("- Steak de 250 g de Saumon mariné à la farine de blé \n" + " >Accompagnement: "
						+ "riz cantonais [Prix = 20.5 euros]\n");
		System.out.println("Test Steak de poisson garni de riz: OK !\n");
	}

	/* Test plat de viande accompagné de pates */
	@Order(4)
	@Test
	public void testViandeAuxPates() {
		/* Création d'un objet viande */
		Iplat plat1 = fact.getPlat("Viande", "Steak de 200 g de lapin", 10.5);
		assertThat(plat1.getPrix()).isEqualTo(10.5);
		assertThat(plat1.getPeparation()).isEqualTo("Steak de 200 g de lapin mariné au piment africain");
		System.out.println("Test Plat factory Steak de viande: OK !");
		/* Création d'un objet viande garnie de pates */
		PlatDecorator platGarni1 = new Pates(plat1);
		platGarni1.setPrix(3.5);
		platGarni1.setType("spaguetti à la Bolognaise");
		assertThat(platGarni1.getPrix()).isEqualTo(14.0);
		assertThat(platGarni1.getPeparation()).isEqualTo("Steak de 200 g de lapin mariné au piment africain \n"
				+ " >Accompagnement: " + "spaguetti à la Bolognaise");
		assertThat(MenuController.showMenu(platGarni1))
				.isEqualTo("- Steak de 200 g de lapin mariné au piment africain \n" + " >Accompagnement: "
						+ "spaguetti à la Bolognaise [Prix = 14.0 euros]\n");
		System.out.println("Test Steak de viande garni de pates: OK !");
	}
}
