package be.epsmarche.poo.ben.projetMenu.Model.DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


/**
 * Classe permettant la connexion à la Base de données
 * @author ben
 * @since V4
 */
//Todo: créer le fichier XML de connection à la dataBase
public class ConnectDAO {
	/**
	 * URL data Base
	 */
	private final String URL ="localhost:33020/ben_MenuV4";
	/**
	 * Password Data Base
	 */
	private final String PASS ="menuPass";
	/**
	 * User data Base
	 */
	private final String USER = "pooMenu";
	
	/**
	 * Controleur
	 */
	private ConnectDAO() {
		
	}
	
	/**
	 * fournisseur d'instance unique
	 * @return une instance de la classe connectDAO
	 */
	public static ConnectDAO getInstance() {
		return ConnectHolder.INSTANCE;
	}
	
	/**
	 * Classe interne  permettant d'obtenir une instance de la classe connectDAO
	 * @author ben
	 *
	 */
	private static class ConnectHolder{
		/**
		 * Attribut permettant de stocker une instance de la classe mère(ConnectDAO)
		 */
		private static final ConnectDAO INSTANCE = new ConnectDAO(); 
	}
	
	public Connection connection()throws SQLException{
		Connection con;
		
		try {
			// Chargement du driver de mysql
			Class.forName("com.mysql.cj.jdbc.driver");
			// Connexion à la BD
			con = (Connection) DriverManager.getConnection("jdbc:mysql://"+this.URL,this.URL,this.PASS);
			return con;
		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Erreur sur le driver mySql: "+ex);
			return null;
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"Echec de connexion à la bade donnés" +ex);
			return null;
		}
		
	}
	
//ToDo: A effacer ligne c-dessou Just Fort Test
//	public static void main(String[] args) {
//		
//	
//	System.out.println("Bonjour 1");
//	try {
//	//1- charger le driver mySql
//	Class.forName("com.mysql.jdbc.Driver");
//	//2- Créer la connexion
//	//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestDeJava","root","");
//	
//	// ToDo: remettre le projet la ligne ci-dessous
//	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33020/ben_MenuV4","pooMenu","menuPass");
//	System.out.println("La connexion est OK");
//	
	
	// On peut mettre ceci dans une autre classe appelée commandeDAO
//	//3 - Créer un état de connexion
//	Statement st = (Statement) con.createStatement();
//	System.out.println("Etat de connexion OK");
//	
//	//4- Créer une requete de sélection
//	ResultSet res = st.executeQuery("select* from commandes");
//	
//	System.out.println("Requete de selection Ok");
//	
//	//5- Parcours des données
//	while(res.next()) {
//		System.out.println(res.getString(1)+" |"+res.getString(2)+" |"+res.getString(3)+" |"+res.getString(4)+" |"+res.getString(5)+" |"+res.getString(6));
//	
//	}
//	
//	System.out.println("Parcours des données OK");
//	
//	con.close();
//	
//	} catch ( Exception e) {
//		e.printStackTrace();
//		System.out.println("Erreur");
//		System.exit(0);
//	}
//	
//	
//}
}
