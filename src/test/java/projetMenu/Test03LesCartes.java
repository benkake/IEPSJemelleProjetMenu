package projetMenu;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import be.epsmarche.poo.ben.projetMenu.Model.Carte.Carte;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Loader;

public class Test03LesCartes {
	/**
	 * Attribut de chargement
	 */
	private static Loader load = null;;
	/**
	 * Constructeur
	 */
	public Test03LesCartes() {}
	/**
	 * Chargement du menu
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@BeforeAll
	public static void setUpClass() throws ParserConfigurationException, SAXException, IOException {
		load = new Loader("menuForTest.xml");
	}
	
	@Order(1)
	@Test
	public void testPlatLoading() {
		/**
		 * Chargement des 3 plats disponibles
		 */
		load.creationPlats(); 
		ArrayList<Choix> listeDesPlats = Carte.getInstance().getListeDesPlats();
		assertThat(listeDesPlats.size()).isEqualTo(3);
		System.out.println("Test loading liste des plats OK!");
		/**
		 * Test du 2e plat du tableau des plats: le steak de poisson
		 */
		Choix platTest = (Choix)listeDesPlats.get(1);
		assertThat(platTest.getCategorie()).isEqualTo("plat");
		assertThat(platTest.getType()).isEqualTo("poisson");
		assertThat(platTest.getId()).isEqualTo("steakPoisson");
		assertThat(platTest.getDescription()).isEqualTo("Steak de 240g de requin");
		assertThat(platTest.getPrix()).isEqualTo(30.5);
		System.out.println("Test contenu d'un plat OK!");
	}

}
