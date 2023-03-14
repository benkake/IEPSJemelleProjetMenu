package be.epsmarche.poo.ben.projetMenu.Controller;

import java.awt.EventQueue;
import java.beans.PropertyChangeSupport;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import be.epsmarche.poo.ben.projetMenu.Model.Carte.Carte;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Loader;

import be.epsmarche.poo.ben.projetMenu.Model.Patterns.AccompagnementFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.DessertFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatDecorator;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import be.epsmarche.poo.ben.projetMenu.View.ViewAccueil;
import be.epsmarche.poo.ben.projetMenu.View.ViewCreerCommandeForm;

/**
 * Controleur: Classe permettant servant d'intermédiaire entre le modèle et la vue
 * @since V4
 */
public class MenuController {

	// Attributs du controleur
	private static ViewAccueil accueil;
	private static ViewCreerCommandeForm commander;

	/**
	 * Attribut de chargement
	 */
	private static Loader load = null;
//	private ViewTable afficheTable;
//	private ViewAjoutFormulaire formulaireAjout;
//	private ViewModifFormulaire modifFormulaire;
	
	private static Carte carte = new Carte();
	private static PlatFactory fabricPlat = new PlatFactory();
	private Choix platChoisi, accompChoisi, dessertChoisi;

	// Rafraichisseur de vues: un écouteur spécial
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public MenuController() {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}

			}
		});

	}
	
	public void start() {
		accueil = new ViewAccueil();
		accueil.setVisible(true);
	}

	/**
	 * Methode getInstance()
	 * 
	 * @return une instance unique du controleur
	 */
	public static MenuController getControInstance() {
		return ControllerHolder.INSTANCE;
	}

	public static Loader loadDataBase() throws ParserConfigurationException, SAXException, IOException {
		load = new Loader("menu.xml");
		return load;
	}

	/**
	 * Classe interne ControllerHolder permet de créer un singleton du controleur
	 * MenuConroller. Ce singleton es appelé INSTANCE
	 * 
	 * @author ben
	 */
	private static class ControllerHolder {
		private static final MenuController INSTANCE = new MenuController();
	}

	public void modifMenu(Choix menuAmodifier) throws Exception {

	}

	public void supprimeMenu(Choix menuAsupprimer) throws Exception {

	}


	/**
	 * Affichage d'un plat du menu
	 * @param plat
	 * @return
	 */
	public static String showMenu(Iplat plat) {
		return (plat.toString());
	}

	public static Double subTotal(Iplat menu) {
		return menu.getPrix();
	}
	
	/**
	 * methode permettant l'instanciation de la classe Loader
	 * @param l
	 * @return
	 */

	public static Loader getLoadObject(Loader l) {
		try {

			l = loadDataBase();
		} catch (ParserConfigurationException | SAXException | IOException e1) {
		
			e1.printStackTrace();
		}
		return l;
	}
}
