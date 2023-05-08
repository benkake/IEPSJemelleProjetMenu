package be.epsmarche.poo.ben.projetMenu.Controller;

import java.awt.EventQueue;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import be.epsmarche.poo.ben.projetMenu.Model.Carte.Loader;
import be.epsmarche.poo.ben.projetMenu.Model.DBconnection.CommandesDAO;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;
import be.epsmarche.poo.ben.projetMenu.View.ViewAccueil;

/**
 * Controleur: Classe permettant servant d'intermédiaire entre le modèle et la
 * vue
 *
 * @since V4
 */
public class MenuController {

	private static CommandesDAO comDAO = new CommandesDAO();

	// Rafraichisseur de vues: un écouteur spécial
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final String message = "";
	@SuppressWarnings("unused")
	private final JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE,
			JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);
	private final javax.swing.Timer timer = new javax.swing.Timer(1000,
			e -> optionPane.setValue(JOptionPane.CLOSED_OPTION));

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
		// Attributs du controleur
		ViewAccueil accueil = new ViewAccueil();
		accueil.setVisible(true);
	}

	/**
	 * Methode getInstance()
	 * @return une instance unique du controleur
	 */
	public static MenuController getControInstance() {
		return ControllerHolder.INSTANCE;
	}
	public static Loader loadDataBase() throws ParserConfigurationException, SAXException, IOException {
		/**
		 * Attribut de chargement
		 */
		Loader load = new Loader("menu.xml");
		return load;
	}

	/**
	 * Classe interne ControllerHolder permet de créer un singleton du controleur
	 * MenuConroller. Ce singleton es appelé INSTANCE
	 * @author ben
	 */
	private static class ControllerHolder {
		private static final MenuController INSTANCE = new MenuController();
	}

	/**
	 * Affichage d'un plat du menu
	 *
	 * @param plat
	 * @return
	 */
	public static String showMenu(Iplat plat) {
		return (plat.toString());
	}
	/**
	 * methode permettant l'instanciation de la classe Loader
	 *
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

	public void deleteCommandeInMap(HashMap<String, ArrayList<Iplat>> listDesCommandes, String table)
			throws IOException, SQLException {
		int rep = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette commande de liste ?");
		if (rep == 0) {
			if (listDesCommandes.containsKey(table)) {
				// Supprime la commande correspondante du hashMap
				listDesCommandes.remove(table);
				saveCollection(listDesCommandes);
				timer.setRepeats(false);
				timer.start();
				optionPane.createDialog(null, "La commande de la "+table+" a été supprimée").setVisible(true);
			} else {
				timer.setRepeats(false);
				timer.start();
				optionPane.createDialog(null, "Vous n'avez selectionné aucune commande").setVisible(true);
			}
		}
	}
	public void saveCollection(HashMap<String, ArrayList<Iplat>> commandes) throws SQLException {
		try {
			callAddCommandeDAO(commandes);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public boolean callUpdateCommandeDAO(String numt, Map<String, ArrayList<Iplat>> listeDesCommandes)
			throws SQLException {
		this.pcs.firePropertyChange("Listener commande", null, listeDesCommandes);
		return comDAO.updateCommandeDAO(numt, listeDesCommandes);
	}
	public boolean callAddCommandeDAO(Map<String, ArrayList<Iplat>> listeCommandes) throws SQLException {
		return comDAO.addCommandeDAO(listeCommandes);
	}
	public ArrayList callGetAllCammandes() {
		return (ArrayList) comDAO.getAllCommandes();
	}

	public ArrayList callGetCommandeDuJour() {
		return (ArrayList) comDAO.getCommandeDuJour();
	}

	public ArrayList CallGetTableOccupee() {
		return (ArrayList) comDAO.getTableOccupee();
	}

	public void callDeleteCommandeByTabl(String numeroDeTable) {
		comDAO.deleteCommandeByTable(numeroDeTable);
	}

	public boolean callModifCommande(int selectedId, Boolean isPaid) {
		try {
			comDAO.modifCommandeDAO(selectedId, isPaid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public Map getCommandeMapping() {
		return comDAO.commandeMapping();
	}
	public Map getGainMapping() {
		return comDAO.gainMapping();
	}
}
