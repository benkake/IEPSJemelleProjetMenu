package projetMenu;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.Frites;
import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.Pates;
import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.PommesDeTerre;
import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.Riz;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import be.epsmarche.poo.ben.projetMenu.Patterns.PlatDecorator;
import be.epsmarche.poo.ben.projetMenu.Patterns.PlatFactory;
import be.epsmarhe.poo.ben.projetMenu.controller.MenuController;

public class TestDesPlatsGarnis {
	/* Création d'un attribut de stockage de l'objet factory */
	public static PlatFactory fact;

	/* Instanciation du Factory avant tous les tests */
	@BeforeAll
	public static void factInstance() {
		fact = new PlatFactory();
	}

	/* Instanciation du controleur */
	public static MenuController contr = new MenuController();

	/* Test plat de viande accompagné de frites */
//ToDo Corriger le premier test avant de cloturer le projet

//	
//	@Order(1)
//	@Test
//	public void testViandeAuxFrites() {
//		/* Création d'un objet viande */
//		Iplat plat1 = fact.getPlat("Viande");
//
//		plat1.setPrix(10.5);
//		plat1.setType("Steak de 200 g de crocodile");
//		assertThat(plat1.getPrix()).isEqualTo(10.5);
//		assertThat(plat1.getPeparation()).isEqualTo("Steak de 200 g de crocodile mariné au piment africain");
//		System.out.println("Test Plat factory Steak de viande: OK !");
//
//		/* Création d'un objet viande garnie de frites */
//		PlatDecorator platGarni1 = new Frites(plat1);
//		assertThat(platGarni1.getPrix()).isEqualTo(15.0);
//		assertThat(platGarni1.getPeparation())
//				.isEqualTo("Steak de 200 g de crocodile mariné au piment africain + frites de patate douce");
//		assertThat(contr.showOrder(platGarni1)).isEqualTo(
//				"- Steak de 200 g de crocodile mariné au piment africain + frites de patate douce [Prix = 15.0 euros]\n");
//		System.out.println("Test Steak de viande garni de frites: OK !");
//	}
//
//	/*
//	 * Test plat de volaille accompagné de pomme de terre
//	 */
//	@Order(2)
//	@Test
//	public void testVolailleAuxPommes2Ter() {
//		/* Création d'un objet volaille */
//		Iplat plat2 = fact.getPlat("Volaille");
//
//		plat2.setPrix(12.0);
//		plat2.setType("Steak de 300 g de Dindon");
//		assertThat(plat2.getPrix()).isEqualTo(12.0);
//		assertThat(plat2.getPeparation()).isEqualTo("Steak de 300 g de Dindon mariné à la moutarde de Dijon");
//		System.out.println("Test Plat factory Steak de dindon: OK !");
//
//		/* Création d'un objet objet volaille garni de pomme */
//		PlatDecorator platGarni2 = new PommesDeTerre(plat2);
//
//		assertThat(platGarni2.getPrix()).isEqualTo(15.5);
//		assertThat(platGarni2.getPeparation())
//				.isEqualTo("Steak de 300 g de Dindon mariné à la moutarde de Dijon + pomme de terre cuite au four");
//		assertThat(contr.showOrder(platGarni2)).isEqualTo(
//				"- Steak de 300 g de Dindon mariné à la moutarde de Dijon + pomme de terre cuite au four [Prix = 15.5 euros]\n");
//		System.out.println("Test Steak de volaille garni de pommes de terre: OK !");
//
//	}
//
//	/* Test plat de poisson accompagné de riz */
//	@Order(3)
//	@Test
//	public void testPoissonAuxRiz() {
//		/* Création d'un objet poisson */
//		Iplat plat3 = fact.getPlat("Poisson");
//
//		plat3.setPrix(13.0);
//		plat3.setType("Steak de 250 g de Saumon");
//		assertThat(plat3.getPrix()).isEqualTo(13.0);
//		assertThat(plat3.getPeparation()).isEqualTo("Steak de 250 g de Saumon mariné à la farine de blé");
//		System.out.println("Test Plat factory Steak de poisson: OK !");
//
//		/* Création d'un objet poisson garni de riz */
//		PlatDecorator platGarni3 = new Riz(plat3);
//
//		assertThat(platGarni3.getPrix()).isEqualTo(20.5);
//		assertThat(platGarni3.getPeparation())
//				.isEqualTo("Steak de 250 g de Saumon mariné à la farine de blé + riz cantonais");
//		assertThat(contr.showOrder(platGarni3)).isEqualTo(
//				"- Steak de 250 g de Saumon mariné à la farine de blé + riz cantonais [Prix = 20.5 euros]\n");
//		System.out.println("Test Steak de poisson garni de riz: OK !");
//
//	}
//
//	/* Test plat de viande accompagné de pates */
//	@Order(4)
//	@Test
//	public void testViandeAuxPates() {
//		/* Création d'un objet viande */
//		Iplat plat1 = fact.getPlat("Viande");
//
//		plat1.setPrix(10.5);
//		plat1.setType("Steak de 200 g de lapin");
//		assertThat(plat1.getPrix()).isEqualTo(10.5);
//		assertThat(plat1.getPeparation()).isEqualTo("Steak de 200 g de lapin mariné au piment africain");
//		System.out.println("Test Plat factory Steak de viande: OK !");
//
//		/* Création d'un objet viande garnie de pates */
//		PlatDecorator platGarni1 = new Pates(plat1);
//
//		assertThat(platGarni1.getPrix()).isEqualTo(13.0);
//		assertThat(platGarni1.getPeparation())
//				.isEqualTo("Steak de 200 g de lapin mariné au piment africain + spaguetti à la Bolognaise");
//		assertThat(contr.showOrder(platGarni1)).isEqualTo(
//				"- Steak de 200 g de lapin mariné au piment africain + spaguetti à la Bolognaise [Prix = 13.0 euros]\n");
//		System.out.println("Test Steak de viande garni de pates: OK !");
//	}

}
