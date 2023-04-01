package projetMenu;

import be.epsmarche.poo.ben.projetMenu.Model.Carte.Carte;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Loader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class Test03LesCartes {
	/**
	 * Attribut de chargement
	 */
	private static Loader load = null;

	/**
	 * Constructeur
	 */
	public Test03LesCartes() {
	}

	/**
	 * Chargement du menu
	 *
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@BeforeAll
	public static void setUpClass() throws ParserConfigurationException, SAXException, IOException {
		load = new Loader("menu.xml");
	}

	@Order(1)
	@Test
	public void testPlatLoading() {
		/**
		 * Chargement des 3 plats disponibles
		 */
		load.creationPlats();
		ArrayList<Choix> listeDesPlats = Carte.getSingleInstance().getListeDesPlats();
		assertThat(listeDesPlats.size()).isEqualTo(3);
		System.out.println("Test loading liste des plats OK!");
		/**
		 * Test du 2e plat du tableau des plats: le steak de poisson
		 */
		Choix platTest = listeDesPlats.get(1);
		assertThat(platTest.getCategorie()).isEqualTo("plat");
		assertThat(platTest.getType()).isEqualTo("poisson");
		assertThat(platTest.getId()).isEqualTo("steakPoisson");
		assertThat(platTest.getDescription()).isEqualTo("Steak de 240g de requin");
		assertThat(platTest.getPrix()).isEqualTo(30.5);
		System.out.println("Test contenu d'un plat OK!\n");
	}

	@Order(2)
	@Test
	public void testAccompagnementsLoading() {
		/**
		 * Chargement des 4 accompagnements disponibles
		 */
		load.creationAccompagnements();
		ArrayList<Choix> listeAccompagnements = Carte.getSingleInstance().getListeDesAccompagnements();
		assertThat(listeAccompagnements.size()).isEqualTo(4);
		System.out.println("Test loading liste des accompagnements OK!");
		/**
		 * Test du 2e accompagnement du tableau des accompagnements: les pates
		 */
		Choix accompTest = listeAccompagnements.get(1);
		assertThat(accompTest.getCategorie()).isEqualTo("accompagnement");
		assertThat(accompTest.getType()).isEqualTo("pates");
		assertThat(accompTest.getId()).isEqualTo("macaroni");
		assertThat(accompTest.getDescription()).isEqualTo("Macaroni de Sicile");
		assertThat(accompTest.getPrix()).isEqualTo(6.5);
		System.out.println("Test contenu d'un accompagnement OK!\n");
	}

	@Order(3)
	@Test
	public void testDessertLoading() {
		/**
		 * Chargement des 4 desserts disponibles
		 */
		load.creationDessert();

		ArrayList<Choix> listeDesDesserts = Carte.getSingleInstance().getListeDesDesserts();
		assertThat(listeDesDesserts.size()).isEqualTo(4);
		System.out.println("Test loading liste des desserts OK!");
		/**
		 * Test du 2e dessert du tableau des desserts: patisserie
		 */
		Choix dessertTest = listeDesDesserts.get(1);
		assertThat(dessertTest.getCategorie()).isEqualTo("dessert");
		assertThat(dessertTest.getType()).isEqualTo("patisserie");
		assertThat(dessertTest.getId()).isEqualTo("crepe");
		assertThat(dessertTest.getDescription()).isEqualTo("Crêpe à la crème au chocolat");
		assertThat(dessertTest.getPrix()).isEqualTo(5.0);
		System.out.println("Test contenu d'un dessert OK!");
	}
}
