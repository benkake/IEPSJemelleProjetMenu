package be.epsmarche.poo.ben.projetMenu.Model.DBconnection;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 * Classe permettant la connexion à la Base de données
 *
 * @author ben
 * @since V4
 */
public class ConnectDAO {
	private  String DRIVER = null;
	private  String URL = null;
	private  String PASS = null;
	private  String USER = null;

	/**
	 * Constructeur
	 */
	public ConnectDAO() {
		LoadXMLfileConnector connect = new LoadXMLfileConnector();
		DRIVER = connect.getDriver();
		URL = connect.getUrl();
		PASS = connect.getPass();
		USER = connect.getUser();
	}
	
	
	/**
	 * fournisseur d'instance unique
	 * @return une instance de la classe connectDAO
	 */
	public static ConnectDAO getInstance() {
		return ConnectHolder.INSTANCE;
	}

	/**
	 * Classe interne permettant d'obtenir une instance de la classe connectDAO
	 *
	 * @author ben
	 */
	private static class ConnectHolder {
		/**
		 * Attribut permettant de stocker une instance de la classe mère(ConnectDAO)
		 */
		private static final ConnectDAO INSTANCE = new ConnectDAO();
	}

	public Connection connection() throws SQLException {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			// Connexion à la BD
			con = (Connection) DriverManager.getConnection("jdbc:mysql://" + this.URL, this.USER, this.PASS);
			return con;
		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Erreur sur le driver mySql: " + ex);
			return null;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Echec de connexion à la bade donnés" + ex);
			return null;
		}
	}
}
