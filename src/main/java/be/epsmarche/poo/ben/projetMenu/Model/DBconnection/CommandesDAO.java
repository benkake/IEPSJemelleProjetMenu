package be.epsmarche.poo.ben.projetMenu.Model.DBconnection;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.epsmarche.poo.ben.projetMenu.Model.Commandes.Commande;

/**
 * Classe permettant d'accéder à la BD pour obtenir des objets de type commande
 * Elle contient toutes les méthodes du CRUD
 * @author ben
 * @since V4
 *
 */
public class CommandesDAO implements Serializable{

	/**
	 * 
	 */
	public CommandesDAO() {
		
	}
	//ToDo toutes ces méthodes doivent être appellees d'abord par le controleur qui les passera à la vue
	public List getAllCommandes() {
		// ToDo : voir comment changer en Map;
		// déclaration des objets utiles pour l'exécution d'un select dans la BD
		ArrayList list = new ArrayList();
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		ResultSet res = null;
		try {
			
			// Crétion d'un état de connexion à la BD
			con = ConnectDAO.getInstance().connection();
			// Préparation d'une requête de selection
			String query = "SELECT * FROM commandes";
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			// Stockage de la demande d'execution de la requête préparée, dans la variable res
			// qui permet de lire une ligne de la table des données
			res = pStmt.executeQuery();
			
			// parcours de toute la table afin de lire toutes les lignes
			while(res.next()) { // fonctionne comme un itérateur
				Commande commande = new Commande();
				commande.setIdCommande(res.getInt("id"));
				commande.setNumTab(res.getString("numtable"));
				commande.setMenus(res.getString("menus"));;
				commande.setPrixTotal(res.getDouble("prix"));
				commande.setDateCommande(res.getDate("dateCommande"));
				commande.setPayee(res.getBoolean("payee"));
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
		} finally{
			if(res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			if(con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			if(pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
				
		}
		
		return list;
		
	}
	
	/**
	 * permet d'afficher les articles en fonction des id
	 * @param idToSelect
	 * @return
	 */
	
	public List getCommandeById(int idToSelect) {
		// ToDo : voir comment changer en Map;
		ArrayList list = new ArrayList();
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		ResultSet res = null;
		try {
			
			// Crétion d'un état de connexion et stockage dans la variable con
			con = ConnectDAO.getInstance().connection();
			// Préparation d'une requête de selection
			String query = "SELECT * FROM commandes WHERE id = "+idToSelect; //ToDo: Revoir pour une meilleur méthode pour éviter les injections de code: trouver la bonne méthode 
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			// Stockage de la demande d'execution de la requête préparée, dans la variable res
			// qui permet de lire une ligne de la table des données
			res = pStmt.executeQuery();
			
			// parcours de toute la table afin de lire toutes les lignes
			while(res.next()) { // fonctionne comme un itérateur
				Commande commande = new Commande();
				commande.setIdCommande(res.getInt("id"));
				commande.setNumTab(res.getString("numtable"));
				commande.setMenus(res.getString("menus"));;
				commande.setPrixTotal(res.getDouble("prix"));
				commande.setDateCommande(res.getDate("dateCommande"));
				commande.setPayee(res.getBoolean("payee"));
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
		} finally{
			if(res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			if(con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			if(pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
				
		}
		
		return list;
		
	}
	
	/**
	 * Permet d'afficher les commandes payées
	 * @return
	 */
	public List getCommandesPayees() {
		// ToDo : voir comment changer en Map;
		ArrayList list = new ArrayList();
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		ResultSet res = null;
		try {
			
			// Crétion d'un état de connexion et stockage dans la variable con
			con = ConnectDAO.getInstance().connection();
			// Préparation d'une requête de selection
			String query = "SELECT * FROM commandes Where payee = 1";
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			// Stockage de la demande d'execution de la requête préparée, dans la variable res
			// qui permet de lire une ligne de la table des données
			res = pStmt.executeQuery();
			
			// parcours de toute la table afin de lire toutes les lignes
			while(res.next()) { // fonctionne comme un itérateur
				Commande commande = new Commande();
				commande.setIdCommande(res.getInt("id"));
				commande.setNumTab(res.getString("numtable"));
				commande.setMenus(res.getString("menus"));;
				commande.setPrixTotal(res.getDouble("prix"));
				commande.setDateCommande(res.getDate("dateCommande"));
				commande.setPayee(res.getBoolean("payee"));
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
		} finally{
			if(res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			if(con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			if(pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
				
		}
		
		return list;
	}
	
	/**
	 * Permet d'ajouter dans la BD les commandes préalablement crées ==> listeMenu avec Numéro de table dans notre cas 
	 * @param commande
	 * @return
	 */
	public boolean addCommande(Commande commande) {  
		
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		
		try {
			
			// Crétion d'un état de connexion et stockage dans la variable con
			con = ConnectDAO.getInstance().connection();
			
			// desactivation de la validation automatique des transaction par le pilote JDBC
			con.setAutoCommit(false); // methode de la clsse Connection 
			// Préparation d'une requête d'insertion
			String query = "insert into commandes(numtable, menus, prix, dateCommande, payee) values(?,?,?,now(),0)" ;
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, commande.getNumTab());  // Voir si numTab ne prend pas le numéro de table selectionné
			pStmt.setString(2, commande.getMenus()); // attention voir si menu ne prends pas la listeMenu réadaptée pour les besoins d'affichage
			pStmt.setDouble(3, commande.getPrixTotal()); // voir si prix total  stocke la méthode get prix total
			pStmt.setBoolean(5,false );
			// mettre à jour le tableau
			pStmt.executeUpdate();
			// valider les modification effectuées dans la table
			con.commit();
			
			return true;
			
		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
			
			if(con != null) { // si la connexion echoue
				try {
					  	con.rollback(); // annule la modification
				    } catch (SQLException e) {
				    	Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			return false;
			
		}finally{
			
			if(con != null) { // si la connexion réussie
				try {
					con.setAutoCommit(true); // active la valisation automatique
					con.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			if(pStmt != null) { // si la requête préparée est valide
				try {
					pStmt.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
				
		}
		
	}
	
	// ToDo: Refelexion: Pour ne pas avoir plusieurs commandes pour une même table en journée
	// on peut à chaque fois que un numéro de table existe dans la BD, update plutôt cette 
	// ligne avec les données correspondantes et non insérer.
	
public boolean updateCommande(Commande commande) {
		
		java.sql.Connection con = null;
		java.sql.PreparedStatement pStmt = null;
		
		try {
			
			// Crétion d'un état de connexion et stockage dans la variable con
			con = ConnectDAO.getInstance().connection();
			
			// desactivation de la validation automatique des transaction par le pilote JDBC
			con.setAutoCommit(false); // methode de la clsse Connection
			// Préparation d'une requête de mise à jour
			String query = "update commandes"
							+"set numtable = ?,"
							+"menus = ?,"
							+"prix = ?,"
							+"payee = ?,"
							+"where id =?,";
					
			// Stockage de la requête préparée dans l'etat de connexion
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, commande.getNumTab());  // Voir si numTab ne prend pas le numéro de table selectionné
			pStmt.setString(2, commande.getMenus()); // attention voir si menu ne prends pas la listeMenu réadaptée pour les besoins d'affichage
			pStmt.setDouble(3, commande.getPrixTotal()); // voir si prix total  stocke la méthode get prix total
			pStmt.setBoolean(5,false );
			// mettre à jour le tableau
			pStmt.executeUpdate();
			// valider les modification effectuées dans la table
			con.commit();
			
			return true;
			
		} catch (SQLException ex) {
			Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
			
			if(con != null) { // si la connexion echoue
				try {
					  	con.rollback(); // annule la modification
				    } catch (SQLException ex1) {
				    	Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			return false;
			
		}finally{
			
			if(con != null) { // si la connexion réussie
				try {
					con.setAutoCommit(true); // active la valisation automatique
					con.close();
				} catch (SQLException ex) {
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			if(pStmt != null) { // si la requête préparée est valide
				try {
					pStmt.close();
				} catch (SQLException ex) {
					// ToDo: A quoi sert le Logger dans une exception ?
					Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
				
		}
		
	}
	
	
public boolean deleteCommande(int id) {
	
	java.sql.Connection con = null;
	java.sql.PreparedStatement pStmt = null;
	
	try {
		
		// Crétion d'un état de connexion et stockage dans la variable con
		con = ConnectDAO.getInstance().connection();
		
		// desactivation de la validation automatique des transaction par le pilote JDBC
		con.setAutoCommit(false); // methode de la clsse Connection
		// Préparation d'une requête de suppression
		String query = "delete from articles where id = ?";
		// Stockage de la requête préparée dans l'etat de connexion
		pStmt = con.prepareStatement(query);
		pStmt.setInt(1, id);  // Voir si numTab ne prend pas le numéro de table selectionné
		
		// mettre à jour le tableau
		pStmt.executeUpdate();
		// valider les modification effectuées dans la table
		con.commit();
		
		return true;
		
	} catch (SQLException ex) {
		Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
		
		if(con != null) { // si la connexion echoue
			try {
				  	con.rollback(); // annule la modification
			    } catch (SQLException ex1) {
			    	Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
		return false;
		
	}finally{
		
		if(con != null) { // si la connexion réussie
			try {
				con.setAutoCommit(true); // active la valisation automatique
				con.close();
			} catch (SQLException ex) {
				Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
		if(pStmt != null) { // si la requête préparée est valide
			try {
				pStmt.close();
			} catch (SQLException ex) {
				Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
			
	}
	
}
	
}
