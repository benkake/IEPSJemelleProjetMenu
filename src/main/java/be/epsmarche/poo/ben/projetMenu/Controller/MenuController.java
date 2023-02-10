package be.epsmarche.poo.ben.projetMenu.Controller;

import java.awt.EventQueue;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Commandes.CollectionMenus;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import be.epsmarche.poo.ben.projetMenu.View.ViewAccueil;

public class MenuController {

	// Attributs du controleur
	private ViewAccueil accueil;
//	private ViewAfficheTable afficheTable;
//	private ViewAjoutFormulaire formulaireAjout;
//	private ViewModifFormulaire modifFormulaire;
	private CollectionMenus myCollection;
	private ArrayList collection;

	// Rafraichisseur de vues: un écouteur spécial
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public MenuController() {
		System.out.println("Je suis dans le controleur");
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				accueil = new ViewAccueil();
				accueil.setVisible(true);
			}
		});

		this.myCollection = new CollectionMenus("table1");

		try {
			loadCollection();
		} catch (IOException | ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Problème de sérialisation des menus", "Erreur loading",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Methode getInstance()
	 * 
	 * @return une instance unique du controleur
	 */
	public static MenuController getControInstance() {
		return ControllerHolder.INSTANCE;
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

	// TODO : Revoir comment changer le nom en displayChoixDeMenu()
	// au lieu de displayModel
	// TODO: compléter la méthode
	public void displayModel() {
		this.collection = new ArrayList<>(myCollection.getListeMenu());
	}

	public void creationChoixMenu(String id, String categorie, String type, Double prix, String description)
			throws IOException, ErrorMenuDoublon {
		Choix choixDeMenu = new Choix();
		choixDeMenu.setId(id);
		choixDeMenu.setCategorie(categorie);
		choixDeMenu.setType(type);
		choixDeMenu.setPrix(prix);
		choixDeMenu.setDescription(description);

		this.myCollection.addMenu(choixDeMenu);
		saveCollection();
	}

	/**
	 * La méthode loadCollection permet de charger la collection de menu. Elle
	 * serialise ladite collection via le fichier de données binaires menus.dat crée
	 * par le programme
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void loadCollection() throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream fichier = new FileInputStream("menus.dat");
		
		try (ObjectInputStream entree = new ObjectInputStream(fichier)) {
			this.myCollection = (CollectionMenus) entree.readObject();
		}
	}

	/**
	 * Sauvegarde des menus dans la collection
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void saveCollection() throws FileNotFoundException, IOException {
		try (ObjectOutputStream sortie = new ObjectOutputStream(new FileOutputStream("menus.dat"))) {
			sortie.writeObject(this.myCollection);
		}
	}

	
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
}
