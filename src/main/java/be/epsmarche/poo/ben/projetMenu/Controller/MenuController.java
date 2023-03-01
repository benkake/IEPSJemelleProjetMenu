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
import be.epsmarche.poo.ben.projetMenu.Model.Commandes.CollectionMenus;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.AccompagnementFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.DessertFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatDecorator;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import be.epsmarche.poo.ben.projetMenu.View.ViewAccueil;
import be.epsmarche.poo.ben.projetMenu.View.ViewCreerCommandeForm;

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
	public CollectionMenus Cd1 = new CollectionMenus("Table1");
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

	/**
	 * getMenuSelected intervient dans l'action perform. Elle prend en paramètre le
	 * plat, l'accompagnement et le dessert choisis via le formulaire,
	 * 
	 * @param plat
	 * @param accomp
	 * @param dess
	 * @return
	 */

//	menu1 = new PlatFactory().getPlat("viande", "Steak de 200g de crocodile", 10.);
//	menu1 = new Riz(menu1, "risotto aux champignons", 7.5);
//	menu1 = new Fruit(menu1, "Salade de fruits tropicaux", 2.5);
//	
	// TODO : Revoir comment changer le nom en displayChoixDeMenu()
	// au lieu de displayModel
	// TODO: compléter la méthode
//	public void displayModel() {
//		this.collection = new ArrayList<>(myCollection.getListeMenu());
//	}

	/**
	 * La méthode loadCollection permet de charger la collection de menu. Elle
	 * serialise ladite collection via le fichier de données binaires menus.ser crée
	 * par le programme
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
//	private void loadCollection() throws FileNotFoundException, IOException, ClassNotFoundException {
//		FileInputStream fichier = new FileInputStream("menus.ser");
//
//		try (ObjectInputStream entree = new ObjectInputStream(fichier)) {
//			this.myCollection = (CollectionMenus) entree.readObject();
//		}
//	}

	/**
	 * Sauvegarde des menus dans la collection
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
//	private void saveCollection() throws FileNotFoundException, IOException {
//		try (ObjectOutputStream sortie = new ObjectOutputStream(new FileOutputStream("menus.ser"))) {
//			sortie.writeObject(this.myCollection);
//		}
//	}

	// ToDo à develpper
	public void modifMenu(Choix menuAmodifier) throws Exception {

	}

	public void supprimeMenu(Choix menuAsupprimer) throws Exception {

	}

//	public void saveCollection()throws FileNotFoundException, IOException{
//		
//	}

	/**
	 * 
	 * @param plat
	 * @return
	 */
	public static String showMenu(Iplat plat) {
		return (plat.toString());
	}

	public static Double subTotal(Iplat menu) {
		return menu.getPrix();
	}

	public static Loader getLoadObject(Loader l) {
		try {

			l = loadDataBase();
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return l;
	}
}
