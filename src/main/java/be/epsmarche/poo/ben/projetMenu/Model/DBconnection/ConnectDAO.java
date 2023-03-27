package be.epsmarche.poo.ben.projetMenu.Model.DBconnection;

import java.sql.*;
//import java.sql.DriverManager;
//import java.sql.SQLException;

import javax.swing.JOptionPane;


/**
 * Classe permettant la connexion à la Base de données
 *
 * @author ben
 * @since V4
 */
//Todo: créer le fichier XML de connection à la dataBase
public class ConnectDAO {
    /**
     * URL data Base
     */
    private final String URL = "localhost:33020/ben_MenuV4";
    /**
     * Password Data Base
     */
    private final String PASS = "menuPass";
    /**
     * User data Base
     */
    private final String USER = "pooMenu";

    /**
     * Constructeur
     */
    public ConnectDAO() {
    }

    /**
     * fournisseur d'instance unique
     *
     * @return une instance de la classe connectDAO
     */
    public static ConnectDAO getInstance() {
        return ConnectHolder.INSTANCE;
    }

    /**
     * Classe interne  permettant d'obtenir une instance de la classe connectDAO
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
            // Chargement du driver de mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
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

    private PreparedStatement preparedStatement(String sqlQuery) throws SQLException {
        java.sql.Connection con = null;
        java.sql.PreparedStatement pStmt = null;
        con = (Connection) getInstance().connection();
        con.setAutoCommit(false);
        pStmt = con.prepareStatement(sqlQuery);
        return pStmt;
    }
}
