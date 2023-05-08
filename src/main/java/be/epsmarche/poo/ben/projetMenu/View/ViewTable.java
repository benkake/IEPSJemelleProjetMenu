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
 * @author ben
 * @since V4
 */
public class ViewTable extends JDialog implements PropertyChangeListener {
	private MenuController contr = new MenuController();
	private JTable tableDesCommandes;

	private ArrayList<Commande> list;
	//Nouveau
	private int rowSelected; // Ligne selectionnée
	private Commande commandeSelected = new Commande(); // Commande selectionnée
	/**
	 * constructeur
	 */
	public ViewTable() {
	}
	/**
	 * constructeur
	 * @param list
	 */
	public ViewTable(ArrayList<Commande> list) {
		this.list = list;
		this.contr = MenuController.getControInstance();
		init();
		/**
		 * Ajout d'un écouteur de changement de propriétésde ma table.
		 En effet, via la méthode add PropertyChangeListener,
		 J'ajoute un écouteur de changement de propriété
		 sur l'instance de ma table appelée tableDesCommandes.
		 Cet ecouteur est bien l'objet Jtable courant, c'est-à-dire celui qui
		 execute cette instruction. Cet objet est remplacé par this, pui passé en paramètre
		 */
		tableDesCommandes.addPropertyChangeListener(this);
	}
	private void init() {
		// configuration de la fenêtre
		setTitle("Liste des commandes");
		setSize(800, 500);
		setModal(true); // rend la fenêtre modale
		setLocationRelativeTo(null); // place la fenêtre au centre de l'écran en fonction de la taille de l'écran
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // dispose_on_close permet de détruire l'instance de la fenêtre
		Container contenu = getContentPane(); // renvoi un conteneur de contenu
		// configuration de la table
		JPanel panel = new JPanel(); // création d'un panel dans le conteneur
		this.tableDesCommandes = new JTable();
		// donner à la table une possibilité de tri par colonne
		this.tableDesCommandes.setAutoCreateRowSorter(true);
		// définition d'un model de table
		this.tableDesCommandes.setModel(new TableModele(this.list));
		// activation des lignes horizontales de mon tableau (paramétrage à true)
		this.tableDesCommandes.setShowHorizontalLines(true);
		this.tableDesCommandes.setShowVerticalLines(true);
		// définition de la couleur des grilles d tableau
		this.tableDesCommandes.setGridColor(Color.BLACK);
		// Placement de la table dans un scrollPane pour pouvoir le parcourir totalement
		JScrollPane tableContainer = new JScrollPane(tableDesCommandes);
		// Application d'un rendu à la table
		setRenderTable(this.list);

		// configuration des headers
		this.tableDesCommandes.getTableHeader().setForeground(Color.black); // couleur des caractères
		this.tableDesCommandes.setBackground(new java.awt.Color(204, 248, 255)); // couleur de fond
		this.tableDesCommandes.getTableHeader().setFont(new Font("comfortaa", Font.BOLD, 14)); // type de caractère

		// ajout de la table dans le panel
		panel.setLayout(new BorderLayout()); // disposition de la table sur l'écran
		panel.add(tableContainer, BorderLayout.CENTER); // Centrer l'affichage de la table
		contenu.add(panel); // Ajout de la table au contenu

		/**
		 * Lancement d'un rendu après une tri via le header.
		 Et ajout d'un écouteur de tri.
		 Pour ce faire on ajoute d'abord à la table un trieur de données(getRowSorter).
		 Ensuite on y ajoute un écouteur d'événement de tri (addRowSorterListenner)permettant
		 de détecter les changements subvenus lors des tris dans la table.
 		NB: La classe RowSorterListener est une interface qui hérite de la Classe EventListener de java
 		 	Du coup, pour éviter de créer une nouvelle classe concrète qui l'implémente,
		 	nous pouvons créer une instance lambda ou une instance anonyme de cette interface.
		 	Etant donné que le but n'est que d'écouter un seul événement.
		 */
		tableDesCommandes.getRowSorter().addRowSorterListener(new RowSorterListener() {
			@Override
			public void sorterChanged(RowSorterEvent e) {
				setRenderTable(list);
			}
		});

		/**
		 Ajout d'un écouteur de souris( MouseListener)sur l'objet tableDesCommandes.
		 Pour ce faire nous ferons appelle à sa classe abstraite MouseAdapter,
		 or MouseAdapter étant abstraite, nous ne pouvons obtenir son instance que via
		 une classe anonyme ou une instance lambda.
		 Donc, nous passerons en paramètre une instance anonyme de
		 de la classe abstraite MouseAdapter() qui implémente aussi la classe MouseListener
		 */
		tableDesCommandes.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(evt.getClickCount() == 2) {
					int rowSelected = tableDesCommandes.getSelectedRow(); // renvoie l'index de la ligne selectionnée
					commandeSelected.setIdCommande((int) tableDesCommandes.getValueAt(rowSelected, 0));
					commandeSelected.setPayee((Boolean) tableDesCommandes.getValueAt(rowSelected, 5));
					commandeSelected.setNumTab((String) tableDesCommandes.getValueAt(rowSelected,1));
					// Création d'une instance du formulaire en lui passant la table et la commande à modifier
					ViewModifFormulaire modifForm = new ViewModifFormulaire(tableDesCommandes,commandeSelected);
					modifForm.fillFields(commandeSelected); // appel de la méthode
					modifForm.setVisible(true);
				}
			}
		});
	}
	/**
	 * Methode permettant d'appliquer le render à un composant de la table
	 * @param list
	 */
	public void setRenderTable(ArrayList<Commande> list) {
		if (!(list.isEmpty())) {
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
	 * déclenchement du changement en fonction de l'etat du change Listenner.
	 *  En effet,
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		// Selection d'un listener par son nom
		if (evt.getPropertyName().equals("Listener commande modif")) {
			Commande commande = list
					.stream()
					.filter(n-> n.getIdCommande().equals(commandeSelected.getIdCommande()))
					.findFirst().orElse(null);
			commande.setPayee((Boolean)evt.getNewValue());
			var models = (TableModele)tableDesCommandes.getModel();
			models.fireTableDataChanged();
			setRenderTable(list);
		}
		if(evt.getPropertyName().equals("Listener commande delete")) { //vérifie si le nom de la propriété de l'événement (evt.getPropertyName()) est égal à la chaîne de caractères "Listener commande delete". Cela permet de vérifier si l'événement traité concerne la suppression d'une commande.
			// Plaçons un flux stream sur la liste des commandes pour la filtrer et retrouver l'id correspondant à celle selectionnée. en commençant la recherhe par le premier id
			Commande commande = list.stream().filter(n -> n.getIdCommande().equals(commandeSelected.getIdCommande())).findFirst().orElse(null);
			list.remove(commande); // supprimer la commande retrouvée si elle elle existe
			var model = (TableModele)tableDesCommandes.getModel(); // on obtient l'objet modele table
			model.fireTableRowsDeleted(rowSelected, rowSelected); // on l'informe de la supression por qu'il puisse supprimer de la vue la ligne selectionnéee
			setRenderTable(list); // et on met à jour l'affichage
		}
	}
	public void setModele(TableModele table) {
		this.setModele(table);
	}
	/**
	 * Classe interne qui crée le model de table
	 * @author ben
	 * @since V4
	 */
	public class TableModele extends AbstractTableModel {
		private final ArrayList list;
		private final String[] columnName = { "idCommande", "NumeroTable", "Menus", "Prix total", "date", "Facturee" };

		/**
		 * Constructeur
		 * @param list
		 */
		public TableModele(ArrayList list) {
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
		 * @return int (nombre de colonnes)
		 */
		@Override
		public int getColumnCount() {
			return columnName.length;
		}
		/**
		 * Pacours toutes les cellues de la table pour retourner chacune des valeurs
		 * @param rowIndex    the row whose value is to be queried
		 * @param columnIndex the column whose value is to be queried
		 * @return
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// Objet d'une ligne
			Commande ligne = (Commande) list.get(rowIndex);
			// récupération des cellules de la ligne
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

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if(columnIndex == 5){
				Commande commande = (Commande) list.get(rowIndex);
				boolean newPayeeValue = (boolean) aValue;

			}
		}
	}

	/**
	 * Classe interne permettant de varier les couleurs de toutes les cellules de la
	 * table
	 */

	public class RenderTable extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			// selectionne la cellule sur laquelle on travaille
			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			// Selectionne la valeur de la colonne sur laquelle on trvaille
			Boolean payee = (Boolean) table.getValueAt(row, 5);

			if (!payee) {
				cell.setBackground(Color.RED);
			} else if (row % 2 == 0) {
				cell.setBackground(Color.YELLOW);
			} else {
				cell.setBackground(Color.WHITE);
			}
			return cell;
		}

	}

}
