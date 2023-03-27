package be.epsmarche.poo.ben.projetMenu.Model.DBconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;
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

    MenuController contr = new MenuController();

    /**
     * constructeur
     */
    public CommandesDAO() {
    }

    //ToDo toutes ces méthodes doivent être appellees d'abord par le controleur qui les passera à la vue
    public List getAllCommandes() {

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

            // parcours toute la table afin de lire toutes les lignes
            while (res.next()) { // fonctionne comme un itérateur

                Commande commande = new Commande();
                commande.setIdCommande(res.getInt("id"));
                commande.setNumTab(res.getString("numtable"));
                commande.setMenus(res.getString("menus"));
                commande.setPrixTotalFromDB(res.getDouble("prix"));
                commande.setDateCommande(res.getDate("dateCommande"));
                commande.setPayee(res.getBoolean("payee"));
                list.add(commande);

                System.out.println("Voici le prix(dans CommandeDAO): "+res.getDouble("prix"));


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
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pStmt != null) {
                try {
                    pStmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return list;

    }

    /**
     * permet d'afficher les articles en fonction des id
     *
     * @param idToSelect
     * @return
     */

    public List getCommandeById(int idToSelect) {   // ToDo : voir si interssant commande par table
        // ToDo : voir comment changer en Map;
        ArrayList list = new ArrayList();
        java.sql.Connection con = null;
        java.sql.PreparedStatement pStmt = null;
        ResultSet res = null;
        try {

            // Crétion d'un état de connexion et stockage dans la variable con
            con = ConnectDAO.getInstance().connection();
            // Préparation d'une requête de selection
            String query = "SELECT * FROM commandes WHERE id = " + idToSelect; //ToDo: Revoir pour une meilleur méthode pour éviter les injections de code: trouver la bonne méthode
            // Stockage de la requête préparée dans l'etat de connexion
            pStmt = con.prepareStatement(query);
            // Stockage de la demande d'execution de la requête préparée, dans la variable res
            // qui permet de lire une ligne de la table des données
            res = pStmt.executeQuery();

            // parcours de toute la table afin de lire toutes les lignes
            while (res.next()) { // fonctionne comme un itérateur
                Commande commande = new Commande();
                commande.setIdCommande(res.getInt("id"));
                commande.setNumTab(res.getString("numtable"));
                commande.setMenus(res.getString("menus"));
                ;
                commande.setPrixTotalFromDB(res.getDouble("prix"));
                commande.setDateCommande(res.getDate("dateCommande"));
                commande.setPayee(res.getBoolean("payee"));
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
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pStmt != null) {
                try {
                    pStmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return list;

    }

    /**
     * Permet d'afficher les commandes payées
     *
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
            //	String query = "SELECT * FROM commandes Where payee = 1";
            String query = "SELECT * FROM commandes";
            // Stockage de la requête préparée dans l'etat de connexion
            pStmt = con.prepareStatement(query);
            // Stockage de la demande d'execution de la requête préparée, dans la variable res
            // qui permet de lire une ligne de la table des données
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
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pStmt != null) {
                try {
                    pStmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return list;
    }

    /**
     * Permet d'ajouter dans la BD les commandes préalablement crées ==> listeMenu avec Numéro de table dans notre cas
     *
     * @return
     */
    public boolean addCommandeDAO(Map<String, ArrayList<Iplat>> listeDesCommandes) throws SQLException {  // passer en paramètre un objet commande

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

                pStmt.setString(1, numTable);
                System.out.println(" Numero de table pour la BD = " + numTable);

                Double prixTotal = 0.;
                for (Iplat menus : uneCommande) {
                    pStmt.setString(2, menus.toString()); // attention voir si menu ne prends pas la listeMenu réadaptée pour les besoins d'affichage
                    System.out.println(" commande pour la BD =\n " + menus.toString());
                    prixTotal += menus.getPrix();
                    System.out.println("=== prix du menu pour la BD = " + menus.getPrix());
                    pStmt.setDouble(3, prixTotal); // voir si prix total  stocke la méthode get prix total
                    System.out.println("=== prix total pour la BD= " + prixTotal);
                }

            }
            pStmt.executeUpdate();
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


    // ToDo: Refelexion: Pour ne pas avoir plusieurs commandes pour une même table en journée
    // on peut à chaque fois que un numéro de table existe dans la BD, update plutôt cette
    // ligne avec les données correspondantes et non insérer.

    /**
     * Met à jour le combre de menu dans une commande et le prix total la date d'aujourd'hui
     * @param numTb
     * @param listeDesCommandes
     * @return
     * @throws SQLException
     */
    public boolean updateCommandeDAO(String numTb, Map<String, ArrayList<Iplat>> listeDesCommandes) throws SQLException {

        java.sql.Connection con = null;
        java.sql.PreparedStatement pStmt = null;

        try {
            // Création d'un état de connexion et stockage dans la variable con
            con = (Connection) ConnectDAO.getInstance().connection();
            // desactivation de la validation automatique des transaction par le pilote JDBC
            con.setAutoCommit(false); // methode de la clsse Connection
            // Préparation d'une requête de mise à jour
            String query = "UPDATE commandes SET menus = ?, prix = ? WHERE numtable = ? AND CAST(dateCommande AS DATE)= DATE(now()) ";

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
                // Fournir les valeurs à la requête préparée
                pStmt.setString(1, menusConcat); // attention voir si menu ne prends pas la listeMenu réadaptée pour les besoins d'affichage
                System.out.println("----------Commande mise à jour dans la DB =\n " + menusConcat); // Todo : effacer
                pStmt.setDouble(2, prixTotal); // voir si prix total  stocke la méthode get prix total
                System.out.println("=== prix total mis à jour dans la DB = " + prixTotal); // Todo : effacer
                pStmt.setString(3, numTable);

            }
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
                    // ToDo: A quoi sert le Logger dans une exception ?
                    Logger.getLogger(CommandesDAO.class.getName()).log(Level.SEVERE, null, ex);
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

}
