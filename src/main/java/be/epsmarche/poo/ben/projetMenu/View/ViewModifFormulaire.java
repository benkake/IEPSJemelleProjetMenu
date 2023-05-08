package be.epsmarche.poo.ben.projetMenu.View;

import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;
import be.epsmarche.poo.ben.projetMenu.Model.Commandes.Commande;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe permettant de modifier via un formulaire les données de la table
 */
public class ViewModifFormulaire extends JDialog implements ActionListener {
	/**
	 * Attributs
	 */
	private final MenuController contr;
	private JTextField fieldId;
	private JCheckBox checkPayee;
	private JTable tableDesCommandes;
	private Commande commandeChoisie;


	// constructeur qui prend en paramètre la table et la commande à modifer
	public ViewModifFormulaire(JTable tableCommandes,Commande commandeChoisie) {
		this.contr = MenuController.getControInstance();
		this.tableDesCommandes = tableCommandes;
		this.commandeChoisie = commandeChoisie;
		this.initComponent();

	}

	public void initComponent() {
		// Configuration fenêtre
		setTitle("Modification d'une commande");
		setSize(200, 150);
		setModal(true); // rend la fenêtre modale
		setLocationRelativeTo(null); //
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container contenu = getContentPane();
		contenu.setLayout(new GridLayout(3, 1, 20, 10));

		// 1-formulaire: l'ID
		JPanel panelId = new JPanel();
		panelId.setLayout(new FlowLayout(FlowLayout.LEFT)); // positionnement du form à gauche
		JLabel labelId = new JLabel("Id commande");
		labelId.setPreferredSize(new Dimension(120, 30));
		panelId.add(labelId);
		fieldId = new JTextField();
		fieldId.setEditable(false); // rendre le numéro de commande non éditable
		panelId.add(fieldId);
		contenu.add(panelId);

		// 3-formulaire: payee
		JPanel panelPayee = new JPanel();
		panelPayee.setLayout(new FlowLayout(FlowLayout.LEFT)); // positionnement du form à gauche
		JLabel labelPayee = new JLabel("Payee ?");
		labelPayee.setPreferredSize(new Dimension(120, 30));
		panelPayee.add(labelPayee);
		checkPayee = new JCheckBox();
		panelPayee.add(checkPayee);
		contenu.add(panelPayee);

		// 4-formualire: modification ou suppression
		JPanel panelModSuppr = new JPanel();
		panelModSuppr.setLayout(new FlowLayout(FlowLayout.LEFT)); // positionnement du form à gauche
		JButton boutonMod = new JButton("Modifier");
		boutonMod.addActionListener(this);
		panelModSuppr.add(boutonMod);
		JButton boutonSuppr = new JButton("Supprimer");
		boutonSuppr.addActionListener(this);
		panelModSuppr.add(boutonSuppr);
		contenu.add(panelModSuppr);
	}
	public void fillFields(Commande uneCommande) {
		this.fieldId.setText(Integer.toString(uneCommande.getIdCommande()));
		if (uneCommande.isPaid()) { // si une commande est payée
			this.checkPayee.setSelected(true); // passe la valeur true au checkBox
		} else { // sinon
			this.checkPayee.setSelected(false); // passe la valeur false
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// stockage de la source de l'événement
		String event = e.getActionCommand();
		Commande commande = new Commande();
		int idCommande = Integer.parseInt(fieldId.getText());
		commande.setIdCommande(idCommande);

		if (checkPayee.isSelected()) {
			commande.setPayee(true);
		} else {
			commande.setPayee(false);
		}
		// Action de modification dans la BD
		if (event.equals("Modifier")) {
			int idSelected = commande.getIdCommande();
			boolean isItPaid = commande.getPayee();
			// création d'un booleen vérificateur d'erreur hasNotError
			boolean hasError = contr.callModifCommande(idSelected,isItPaid);
			if(!hasError){ // si la modification de la commande n'a pas d'erreur
			tableDesCommandes.firePropertyChange("Listener commande modif",commandeChoisie.getPayee(), commande.getPayee());
			}
		}

		// Action de suppression dans le Map et la BD
		if (event.equals("Supprimer")) {
			String tablCde = commande.getNumTab();
			try {
				contr.deleteCommandeInMap((HashMap<String, ArrayList<Iplat>>) commande.getListeDesCommandes(), tablCde);
			} catch (IOException | SQLException ex) {
				throw new RuntimeException(ex);
			}
			contr.callDeleteCommandeByTabl(tablCde);
			tableDesCommandes.firePropertyChange("Listener commande delete", false, true);
		}
		this.setVisible(false);
		dispose();
	}
}
