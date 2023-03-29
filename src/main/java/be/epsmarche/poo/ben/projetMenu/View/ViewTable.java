package be.epsmarche.poo.ben.projetMenu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;
import be.epsmarche.poo.ben.projetMenu.Model.Commandes.Commande;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe pemettant d'afficher l'ensemble des commandes dans une table. Cette
 * table est dynamique et offre la possibilité de se faire modifier. Les données
 * qu'elle contient seront stockées plutard dans une Base de données SQL
 *
 * @author ben
 * @since V4
 */
public class ViewTable extends JDialog implements PropertyChangeListener {

    private MenuController contr = new MenuController();
    private JTable tableDesCommandes;

    private ArrayList list;

    /**
     * constructeur
     */
    public ViewTable(){
    }
    /**
     * constructeur
     *
     * @param list
     */
    public ViewTable(ArrayList list) {
        this.list = list;
        this.contr = MenuController.getControInstance();
        init();
    }

    private void init() {
        // configuration de la fenêtre
        setTitle("Liste des commandes");
        setSize(800, 500);
        setModal(true); // rend la fenêtre modale
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container contenu = getContentPane();

        //configuration de la table
        JPanel panel = new JPanel();
        this.tableDesCommandes = new JTable();
        // donner à la table une possibilité de tri par colonne
        this.tableDesCommandes.setAutoCreateRowSorter(true);
        //définition d'un model de table
        this.tableDesCommandes.setModel(new TableModel(this.list));
        // activation des lignes horizontales de mon tableau (paramétrage à true)
        this.tableDesCommandes.setShowHorizontalLines(true);
        this.tableDesCommandes.setShowVerticalLines(false);
        // définition de la couleur des grilles d tableau
        this.tableDesCommandes.setGridColor(Color.BLACK);
        // Placement de la table dans un scrollPane pour pouvoir le parcourir totalement
        JScrollPane tableContainer = new JScrollPane(tableDesCommandes);
        setRenderTable(this.list);

        // configuration des headers
        this.tableDesCommandes.getTableHeader().setForeground(Color.black); // couleur des caractères
        this.tableDesCommandes.setBackground(new java.awt.Color(204, 248, 255)); // couleur de fond
        this.tableDesCommandes.getTableHeader().setFont(new Font("comfortaa", Font.BOLD, 14)); // type de caractère

        // ajout de la table dans le panel
        panel.setLayout(new BorderLayout()); // disposition de la table sur l'écran
        panel.add(tableContainer, BorderLayout.CENTER); // Centrer l'affichage de la table
        contenu.add(panel); // Ajout de la table au contenu

        // Lancement d'un rendu après une tri via le header
        tableDesCommandes.getRowSorter().addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent e) {
                setRenderTable(list); 
            }
        });

        //Ecoute des événements sur la table
        tableDesCommandes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tableDesCommandes.getSelectedRow();
                Commande commandeSelected = new Commande(); // ne faut-il pas utiliser l'autre constructeur ?
                commandeSelected.setIdCommande((int) tableDesCommandes.getValueAt(row, 0));
                commandeSelected.setNumTab((String) tableDesCommandes.getValueAt(row, 1)); // Colonne Numero de table
                commandeSelected.setListeMenu((ArrayList<Iplat>) tableDesCommandes.getValueAt(row, 2));
                commandeSelected.setPrixTotalFromDB((Double) tableDesCommandes.getValueAt(row, 3));
                commandeSelected.setDateCommande((Date) tableDesCommandes.getValueAt(row, 4));
                commandeSelected.setPayee((Boolean) tableDesCommandes.getValueAt(row, 5));                
            }
        });
    }

    /**
     * Methode permettant d'appliquer le render à un composant de la table
     * @param list
     */
    public void setRenderTable(ArrayList list) {
        if (!(list.isEmpty())) {
            //DefaultTableCellRenderer rendu = new DefaultTableCellRenderer();
        	DefaultTableCellRenderer rendu = new RenderTable();
            // Extraction des données des colonnes
            for (int i = 0; i < tableDesCommandes.getColumnCount(); ++i) {
                // Création objet colonne
                TableColumn tableCol = tableDesCommandes.getColumnModel().getColumn(i);
                // Mise en forme de la colonne
                tableCol.setCellRenderer(rendu);
            }
        }

    }

    @Override
    /**
     *  déclenchement du changement en fonction de l'etat du change Listenner
     */
    public void propertyChange(PropertyChangeEvent evt) {
        // Selection d'un listener par son nom
        if (evt.getPropertyName().equals("Listener commande")) {
            // récupération d'un objet via le listenner
            Commande com = (Commande) evt.getNewValue();
            ArrayList listCom = com.getListeMenu();
            this.tableDesCommandes.setModel(new TableModel(listCom));
        }

    }

    //---------------------------------------------------------

    /**
     * Classe interne qui crée le model de table
     *
     * @author ben
     * @since V4
     */
    public class TableModel extends AbstractTableModel {
        private final ArrayList list;
        private final String[] columnName = {"idCommande", "NumeroTable", "Menus", "Prix total", "date", "Facturee"};

        /**
         * Constructeur
         * @param list
         */
        public TableModel(ArrayList list) {
            this.list = list;
        }

        /**
         * paramétrage des noms de colonne
         * @return nomDeColone
         */
        @Override
        public String getColumnName(int col) {
            String nomDeColone = "";

            switch (col) {
                case 0:
                    nomDeColone = columnName[0];
                    break;
                case 1:
                    nomDeColone = columnName[1];
                    break;
                case 2:
                    nomDeColone = columnName[2];
                    break;
                case 3:
                    nomDeColone = columnName[3];
                    break;
                case 4:
                    nomDeColone = columnName[4];
                    break;
                case 5:
                    nomDeColone = columnName[5];
                    break;
                default:
                    break;
            }

            return nomDeColone;
        }

        /**
         * nombre de lignes qui équivaut à la taille de la collection list à afficher
         *
         * @return int (nombre de lignes)
         */
        @Override
        public int getRowCount() {
            if (list == null)
                return 0;
            return list.size();
        }

        /**
         * nombre de colonnes
         *
         * @return int (nombre de colonnes)
         */
        @Override
        public int getColumnCount() {
            return columnName.length;
        }

        /**
         * Pacours toutes les cellues de la table pour retourner chacune des valeurs
         *
         * @param rowIndex    the row whose value is to be queried
         * @param columnIndex the column whose value is to be queried
         * @return
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            //Objet d'une ligne
            Commande ligne = (Commande) list.get(rowIndex);
            //récupération des cellules de la ligne
            switch (columnIndex) {
                case 0:
                    return ligne.getIdCommande();
                case 1:
                    return ligne.getNumTab();
                case 2:
                    return ligne.getMenus();
                case 3:
                    return ligne.getPrixTotalFromDB();
                case 4:
                    return ligne.getDateCommande();
                case 5:
                    return ligne.getPayee();
                default:
                    break;
            }
            return null;
        }

    }

    //------------------------------------

    /**
     * Classe interne permettant de varier les couleurs de toutes les cellules de la table
     */

    public class RenderTable extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {

            // selectionne la cellule sur laquelle on travaille
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Selectionne la valeur de la colonne sur laquelle on trvaille
            Boolean payee = (Boolean) table.getModel().getValueAt(row,5 );
            
            if(!payee) {
            	cell.setBackground(Color.RED);
            }else if (row % 2 == 0) {
            		cell.setBackground(Color.YELLOW);
            	}else {
            		cell.setBackground(Color.WHITE);
            }
            return cell;
        }

    }


}



