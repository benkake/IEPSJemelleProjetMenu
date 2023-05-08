package be.epsmarche.poo.ben.projetMenu.Model.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.epsmarche.poo.ben.projetMenu.Model.Commandes.Commande;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe permettant d'accéder à la BD pour obtenir des objets de type commande
 * Elle contient toutes les méthodes du CRUD
 *
 * @author ben
 * @since V4
 */
public class CommandesDAO {
	/**
	 * constructeur
	 */
	public CommandesDAO() {

	}

	/**
	 * Permet de calculer les montants de gain ar jour.
	 * Ces montants sont stockés dans un TreeMap.
	 * @return
	 */
	public Map<Date, Double> gainMapping() {
		Map<Date, Double> gainParJour = new TreeMap<>();
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		ResultSet res = null;
		try {
			// Crétion d'un état de connexion à la BD
			con = ConnectDAO.getInstance().connection();
			// Préparation d'une requête de selection
			String query = "SELECT dateCommande, SUM(prix) AS total_prix FROM commandes WHERE payee = true GROUP BY dateCommande";
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			res = pStmt.executeQuery();
			while (res.next()) {
				Date date = res.getDate("dateCommande");
				Double total_prix = res.getDouble("total_prix");
				gainParJour.put(date, total_prix);
			}

		} catch (SQLException ex) {
			// La classe Logger permet de créer des journaux d'événements ou encore les logs,
			// dont le rôle est de suivre l'execution d'un programme
			// en l'instanciant avec sa méthode getLogger, l'on peut créer ce journal en lorsque
			// lors de l'execution du programme dans la classe qui lui est passé en paramètre.
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			executeFinally(con,pStmt);

		}
		return gainParJour;
	}

	/**
	 * permet d'obtenir le nombre de commandes par jour.
	 * Ces commandes sont stockées dans un TreeMap afin de classer
	 * selon l'ordre naturel des dates
	 * @return
	 */
	public Map<Date, Integer> commandeMapping() {

		Map<Date, Integer> commandesParJour = new TreeMap<>();
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		ResultSet res = null;
		try {

			// Crétion d'un état de connexion à la BD
			con = ConnectDAO.getInstance().connection();
			// Préparation d'une requête de selection
			String query = "SELECT DATE(dateCommande) AS date, COUNT(*) AS nombre_de_commandes FROM commandes GROUP BY dateCommande";

			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			res = pStmt.executeQuery();
			while (res.next()) {
				Date date = res.getDate("date");
				int nombreDeCommandes = res.getInt("nombre_de_commandes");
				commandesParJour.put(date, nombreDeCommandes);
			}

		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			executeFinally(con,pStmt);

		}
		return commandesParJour;
	}

	/**
	 * Permet d'obtenir toutes les commandes de la Base de données.
	 * Ces commandes seront stockées dans un arrayList.
	 * @return
	 */
	public List getAllCommandes() {
		// déclaration des objets utiles pour l'exécution d'un select dans la BD
		ArrayList list = new ArrayList();
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		ResultSet res = null;
		try {
			// Création d'un état de connexion à la BD
			con = ConnectDAO.getInstance().connection();
			// Préparation d'une requête de selection
			String query = "SELECT * FROM commandes";
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			//  lire une ligne de la table des données
			res = pStmt.executeQuery();

			// parcours toute la table afin de lire toutes les lignes.
			// En effet, resultSet place le curseur avant la première ligne.
			// Lorsqu'on y attache le next(), il passe à la ligne suivant tant qu'une
			// ligne suivante existe. donc next() est son itérateur
			while (res.next()) { // fonctionne comme un itérateur
				Commande commande = new Commande();
				commande.setIdCommande(res.getInt("id"));
				commande.setNumTab(res.getString("numtable"));
				commande.setMenus(res.getString("menus"));
				commande.setPrixTotalFromDB(res.getDouble("prix"));
				commande.setDateCommande(res.getDate("dateCommande"));
				commande.setPayee(res.getBoolean("payee"));
				list.add(commande);

			}

		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			executeFinally(con,pStmt);
		}
		return list;
	}

	/**
	 * Permet d'afficher les commandes du jour
	 *
	 * @return
	 */
	public List getCommandeDuJour() {

		ArrayList list = new ArrayList();
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		ResultSet res = null;
		try {
			// Création d'un état de connexion et stockage dans la variable con
			con = ConnectDAO.getInstance().connection();
			// Préparation d'une requête de selection
			String query = "SELECT * FROM commandes WHERE CAST(dateCommande AS DATE)= DATE(now());";
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			res = pStmt.executeQuery();
			// parcours de toute la table afin de lire toutes les lignes
			while (res.next()) { // fonctionne comme un itérateur
				Commande commande = new Commande();
				commande.setIdCommande(res.getInt("id"));
				commande.setNumTab(res.getString("numtable"));
				commande.setMenus(res.getString("menus"));
				commande.setPrixTotalFromDB(res.getDouble("prix"));
				commande.setDateCommande(res.getDate("dateCommande"));
				commande.setPayee(res.getBoolean("payee"));
				list.add(commande);
			}
		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			executeFinally(con,pStmt);
		}
		return list;
	}

	/**
	 * Permet d'ajouter dans la BD les commandes préalablement crées ==> listeMenu
	 * avec Numéro de table dans notre cas
	 * @return
	 */
	public boolean addCommandeDAO(Map<String, ArrayList<Iplat>> listeDesCommandes) throws SQLException {

		Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		try {
			// Création d'un état de connexion et stockage dans la variable con
			con = (Connection) ConnectDAO.getInstance().connection();
			// desactivation de la validation automatique des transaction par le pilote JDBC
			con.setAutoCommit(false); // methode de la clsse Connection
			// Préparation d'une requête d'insertion
			String query = "INSERT INTO commandes(numtable, menus, prix) values(?,?,?)";
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			for (Map.Entry<String, ArrayList<Iplat>> commandes : listeDesCommandes.entrySet()) {
				String numTable = commandes.getKey();
				ArrayList<Iplat> uneCommande = commandes.getValue();
				//pStmt.setString(1, numTable); // numero de table
				Double prixTotal = 0.;
				for (Iplat menus : uneCommande) {
					pStmt.setString(1, numTable); // numero de table
					pStmt.setString(2, menus.toString()); // menu
					prixTotal += menus.getPrix();
					pStmt.setDouble(3, prixTotal); // voir si prix total stocke la méthode get prix total
					pStmt.executeUpdate();
				}
			}
			// valider les modification effectuées dans la table
			con.commit();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
			if (con != null) { // si la connexion echoue
				try {
					con.rollback(); // annule la modification
				} catch (SQLException e) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			return false;

		} finally {
			executeFinally(con,pStmt);
		}
	}

	public boolean modifCommandeDAO(int selectedId, Boolean isPaid) throws SQLException {
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;

		try {
			// Création d'un état de connexion et stockage dans la variable con
			con = (Connection) ConnectDAO.getInstance().connection();
			// desactivation de la validation automatique des transaction par le pilote JDBC
			con.setAutoCommit(false); // methode de la clsse Connection
			String query = "UPDATE commandes SET payee = ? WHERE id = ? ";
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			pStmt.setBoolean(1, isPaid);
			pStmt.setInt(2, selectedId);
			pStmt.executeUpdate();
			// valider les modification effectuées dans la table
			con.commit();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
			if (con != null) { // si la connexion echoue
				try {
					con.rollback(); // annule la modification
				} catch (SQLException ex1) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			return false;
		} finally {
			executeFinally(con,pStmt);
		}
	}

	/**
	 * Met à jour le combre de menu dans une commande et le prix total la date
	 * d'aujourd'hui
	 * @param numTb
	 * @param listeDesCommandes
	 * @return
	 * @throws SQLException
	 */
	public boolean updateCommandeDAO(String numTb, Map<String, ArrayList<Iplat>> listeDesCommandes)
			// rien
			throws SQLException {

		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		Date maintenant = new java.util.Date();
		Timestamp dateActuelle = new Timestamp(maintenant.getTime());

		try {
			// Création d'un état de connexion et stockage dans la variable con
			con = (Connection) ConnectDAO.getInstance().connection();
			// desactivation de la validation automatique des transaction par le pilote JDBC
			con.setAutoCommit(false); // methode de la clsse Connection
			// Préparation d'une requête de mise à jour
			String query = "UPDATE commandes SET menus = ?, prix = ?, dateCommande = ? WHERE numtable = ? AND CAST(dateCommande AS DATE)= DATE(now()) ";
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			String numTable = null;
			for (Map.Entry<String, ArrayList<Iplat>> commandes : listeDesCommandes.entrySet()) {
				
				numTable = commandes.getKey();
				ArrayList<Iplat> uneCommande = commandes.getValue();
				Double prixTotal = 0.;
				String menusConcat = "";
				for (Iplat menus : uneCommande) {
					menusConcat += menus.toString();
					prixTotal += menus.getPrix();
				}
//*********
				if(commandeExists(numTable, con)){ // si la comande existe après connection, mettre à jour
					pStmt.setString(1, menusConcat);
					pStmt.setDouble(2, prixTotal);
					pStmt.setTimestamp(3, dateActuelle);
					pStmt.setString(4, numTable);
					pStmt.executeUpdate();
				}else { // si la commande n'existe pas : ajouter
					addCommandeDAO(listeDesCommandes);
				}

			}
			// valider les modification effectuées dans la table
			con.commit();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);

			if (con != null) { // si la connexion echoue
				try {
					con.rollback(); // annule la modification
				} catch (SQLException ex1) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			return false;

		} finally {
            executeFinally(con,pStmt);
		}
	}

	private boolean commandeExists(String numTable, Connection con) throws SQLException {
	    //boolean exists = false;
	    PreparedStatement pStmt = null;
	    ResultSet rs = null;
	    try {
	        String query = "SELECT COUNT(*) FROM commandes WHERE numtable = ? AND CAST(dateCommande AS DATE) = DATE(NOW())";
	        pStmt = con.prepareStatement(query);
	        pStmt.setString(1, numTable);
	        rs = pStmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	        return true;
	        
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (pStmt != null) {
	            pStmt.close();
	        }
	    }
	}

	public boolean deleteCommandeByTable(String numTabl) {
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		ResultSet res = null;
		try {
			// Crétion d'un état de connexion et stockage dans la variable con
			con = ConnectDAO.getInstance().connection();
			// desactivation de la validation automatique des transaction par le pilote JDBC
			con.setAutoCommit(false); // methode de la clsse Connection
			// Préparation d'une requête de suppression
			String query = "DELETE FROM commandes WHERE numtable = ? AND CAST(dateCommande AS DATE)= DATE(now()) ";
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, numTabl);
			pStmt.executeUpdate();
			// valider les modification effectuées dans la table
			con.commit();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
			if (con != null) { // si la connexion echoue
				try {
					con.rollback(); // annule la modification
				} catch (SQLException ex1) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			return false;
		} finally {
            executeFinally(con,pStmt);
		}

	}

	/**
	 * Permet d'obtenir une liste de tables qui ont déja une commande
	 * @return
	 */
	public List getTableOccupee() {
		ArrayList<String> list = new ArrayList<>();
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		ResultSet res = null;
		try {
			// Création d'un état de connexion et stockage dans la variable con
			con = ConnectDAO.getInstance().connection();
			// Préparation d'une requête de selection
			String query = "SELECT numtable FROM commandes WHERE CAST(dateCommande AS DATE)= DATE(now());";
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			// Stockage de la demande d'execution de la requête préparée, dans la variable
			// res
			// qui permet de lire une ligne de la table des données
			res = pStmt.executeQuery();
			// parcours de toute la table afin de lire toutes les lignes
			while (res.next()) { // fonctionne comme un itérateur
				String numtbl = res.getString("numtable");
				list.add(numtbl);
			}
		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
            if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
            executeFinally(con,pStmt);
		}
		return list;
	}


    public void executeFinally(java.sql.Connection con, java.sql.PreparedStatement pStmt){
        if (con != null) { // si la connexion réussie
            try {
                con.setAutoCommit(true); // active la valisation automatique
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (pStmt != null) { // si la requête préparée est valide
            try {
                pStmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
