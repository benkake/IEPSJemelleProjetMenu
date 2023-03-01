package be.epsmarche.poo.ben.projetMenu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

//import be.epsmarche.poo.ben.projetMenu.Controller.IPlat;
import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Carte;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Loader;
import be.epsmarche.poo.ben.projetMenu.Model.Commandes.Commande;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant la fenetre principale. Cette fenetre sera ouverte en
 * permanance pour effectuer les opérations de commande et accéder aux autres
 * options et sous-options du menu
 * @version V.3
 * @author ben
 */
public class ViewCreerCommandeForm extends JFrame implements ItemListener, ActionListener {

	private Carte carte = null;
	private Choix platChoisi, accompChoisi, dessChoisi;
	private static final long serialVersionUID = -8859500785480111589L;
//***-------------------------------------------
	private final JMenuBar barreDeMenu = new JMenuBar();

	private final JMenu demarrer = new JMenu("Demarrer");

	private JMenuItem quitter = new JMenuItem("Sortir du Programme ?");

	private final JMenu etatsDeGest = new JMenu("Gestion");
	private final JMenu Statistiques = new JMenu("Statistiques");

	private final JMenuItem CommandeDuJour = new JMenuItem("Commande du Jour");
	private final JMenuItem ToutesLesComandes = new JMenuItem("Toutes les commandes");
	
	/**
	 * Création d'un panel
	 */
	private final JPanel panel = new JPanel();
	/**
	 * Création d'un label pour l'affichage du texte
	 */
	private final JLabel label = new JLabel("Bienvenu dans le gestionnaire de commandes !");
	/**
	 * Création d'un conteneur
	 */
	private JTextArea editeurDeText = new JTextArea();
	private Container contenu = new Container();

	private String message = "";
	private JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE,
			JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);
	private javax.swing.Timer timer = new javax.swing.Timer(1000, e -> optionPane.setValue(JOptionPane.CLOSED_OPTION));

	// Création des labels
	private static JLabel labelQuestPlat, labelPlatType, labelQuestAcc, labelAccomType, labelQuestDess, labelDessType,
			labelEntete, labelEntete2, labelEnteteTab;

	private static JComboBox<String> comboBox1, comboBox2, comboBox3;

	private String platItemSelected, accompItemSelected, dessItemSelected;
	private Iplat menu;
//***---------------------------------------------------------
	private static Loader loadedObjects;
	private ArrayList<Choix> listePlats = new ArrayList<>();
	private ArrayList<Choix> listeAccomp = new ArrayList<>();
	private ArrayList<Choix> listeDess = new ArrayList<>();
	private Commande laCommande = new Commande();
	private String numeroDeTable;
	private String affichage = "";
	
	public String getNumeroDeTable() {
		return numeroDeTable;
	}

	public void setNumeroDeTable(String numBoutonTable) {
		this.numeroDeTable = numBoutonTable;
	}

	/**
	 * Le constructeur
	 */
	public ViewCreerCommandeForm() {
		super("Composer la commande");
		this.carte = new Carte();
		/**
		 * Configutation de la fenêtre
		 */

		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// ***--------------------------------------------------------
		// récupération dans le controleur des objets chargés par la classe Loader
		loadedObjects = MenuController.getLoadObject(loadedObjects);
		/*
		 * récupération de la liste des plats chargés
		 */
		listePlats = carte.getPlatList(); // !!!!!!OK OK OK les plats sont récupérés de la carte
		/**
		 * Mise à jour de la liste des plats sur la carte
		 */
		carte.setListeDesPlats(listePlats);

		/*
		 * récupération de la liste des accompagnements chargés
		 */
		listeAccomp = carte.getAccompList(); // !!!!!!OK OK OK les plats sont récupérés de la carte
		/**
		 * Mise à jour de la liste des accompagnements sur la carte
		 */
		carte.setListeDesAccompagnements(listeAccomp);

		/*
		 * récupération de la liste des desserts chargés
		 */
		listeDess = carte.getDessList(); 
		/**
		 * Mise à jour de la liste des accompagnements sur la carte
		 */
		carte.setListeDesDesserts(listeDess);

		// ***-----------------------------------------------------------

		contenu = (JPanel) this.getContentPane();

		contenu.add(panel);
		panel.setBackground(Color.green);

		// Creation et configuration du label
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 230));
		Font font = new Font("Comfortaa", Font.BOLD, 30);
		label.setFont(font);
		panel.add(label);

		// Insertion de la barre de menu dans le frame
		setJMenuBar(createBarreDeMenu());

		// positionnement des panels
		PanelsPositions();

	}

	public String getPlatItemSelected() {
		return platItemSelected;
	}

	public void setPlatItemSelected(String platItemSelected) {
		this.platItemSelected = platItemSelected;
	}

	public String getAccompItemSelected() {
		return accompItemSelected;
	}

	public void setAccompItemSelected(String accompItemSelected) {
		this.accompItemSelected = accompItemSelected;
	}

	public String getDessItemSelected() {
		return dessItemSelected;
	}

	public void setDessItemSelected(String dessItemSelected) {
		this.dessItemSelected = dessItemSelected;
	}

	private JMenuBar createBarreDeMenu() {

		demarrer.setPreferredSize(new Dimension(100, 40));
		demarrer.setFont(new Font("Comforta", Font.ITALIC, 18));
		barreDeMenu.add(demarrer);
		quitter.addActionListener(this::actionPerformed);
		demarrer.add(quitter);

		etatsDeGest.setPreferredSize(new Dimension(80, 40));
		etatsDeGest.setFont(new Font("Comforta", Font.ITALIC, 18));
		barreDeMenu.add(etatsDeGest);
		
		Statistiques.setPreferredSize(new Dimension(120, 40));
		Statistiques.setFont(new Font("Comforta", Font.ITALIC, 18));
		barreDeMenu.add(Statistiques);

		JMenu afficher = new JMenu("Afficher");
		etatsDeGest.add(afficher);
		// afficher.addActionListener(this);

//		JMenuItem ToutesLesComandes = new JMenuItem("Liste des commandes");
		afficher.add(ToutesLesComandes);
		// ToutesLesComandes.addActionListener(this);

//		JMenuItem CommandeDuJour = new JMenuItem("Etat des encaissements");
		afficher.add(CommandeDuJour);
		// CommandeDuJour.addActionListener(this);


		return barreDeMenu;
	}

	private void PanelsPositions() {
		contenu.add(createLeftPanel(), BorderLayout.WEST);
		contenu.add(createRightPanel(), BorderLayout.EAST);
		contenu.add(createDownPanel(), BorderLayout.SOUTH);
		contenu.add(createCentralPanel1(), BorderLayout.CENTER);
	}

	private JPanel createLeftPanel() {
		JPanel leftPanel = new JPanel(new GridLayout(7, 1));
		labelEnteteTab = new JLabel("");
		labelEnteteTab.setFont(new Font("Comforta", Font.BOLD, 20));
		leftPanel.add(labelEnteteTab);

		JButton tab1 = new JButton();
		tab1.setFont(new Font("Comforta", Font.ITALIC, 20));
		tab1.setText("Table1");
		tab1.addActionListener(this);
		leftPanel.add(tab1);

		JButton tab2 = new JButton();
		tab2.setFont(new Font("Comforta", Font.ITALIC, 20));
		tab2.setText("Table2");
		tab2.addActionListener(this);
		leftPanel.add(tab2);

		JButton tab3 = new JButton();
		tab3.setFont(new Font("Comforta", Font.ITALIC, 20));
		tab3.setText("Table3");
		tab3.addActionListener(this);
		leftPanel.add(tab3);

		JButton tab4 = new JButton();
		tab4.setFont(new Font("Comforta", Font.ITALIC, 20));
		tab4.setText("Table4");
		tab4.addActionListener(this);
		leftPanel.add(tab4);

		JButton tab5 = new JButton();
		tab5.setFont(new Font("Comforta", Font.ITALIC, 20));
		tab5.setText("Table5");
		tab5.addActionListener(this);
		leftPanel.add(tab5);

		JButton tab6 = new JButton();
		tab6.setFont(new Font("Comforta", Font.ITALIC, 20));
		tab6.setText("Table6");
		tab6.addActionListener(this);
		leftPanel.add(tab6);
		return leftPanel;
	}

	private JPanel createRightPanel() {
		editeurDeText = new JTextArea(80, 30);
		editeurDeText.getText();
		editeurDeText.setBackground(Color.darkGray);
		editeurDeText.setForeground(Color.WHITE);
		editeurDeText.setFont(new Font("Comforta", Font.PLAIN, 13));

		JScrollPane scrollEdit = new JScrollPane(editeurDeText);
		scrollEdit.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollEdit.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JPanel rightPanel = new JPanel(new GridLayout(1, 1));
		rightPanel.add(scrollEdit);

		scrollEdit.revalidate();
		return rightPanel;
	}

	private JPanel createCentralPanel1() {
		JPanel centralPanel1 = new JPanel(new GridLayout(5, 0));

		// récupération des id des plats et leur chargement sur la combobox en tant que
		// itemm
		List<String> listeDesIdPlats = new ArrayList<>();
		listeDesIdPlats = carte.getListeDesIds(carte.getListeDesPlats()); // !!!!!!OK OK OOK
		comboBox1 = new JComboBox<String>(carte.castListeDesId(listeDesIdPlats));

		comboBox1.setPreferredSize(new Dimension(200, 30));
		comboBox1.setFont(new Font("comfortaa", Font.ITALIC, 32));
		// comboBox1.setForeground();
		comboBox1.addItemListener(this);

		// Labelle d'entete
		labelEntete = new JLabel("Choisir une table et composer..", labelEntete.CENTER);
		labelEntete.setFont(new Font("comfortaa", Font.ITALIC, 20));
		labelEntete.setForeground(Color.BLUE);
		labelEntete.setHorizontalAlignment(SwingConstants.RIGHT);

		labelEntete2 = new JLabel("la commande en 3 étapes", labelEntete2.CENTER);
		labelEntete2.setFont(new Font("comfortaa", Font.ITALIC, 20));
		labelEntete2.setForeground(Color.BLUE);
		labelEntete.setHorizontalAlignment(SwingConstants.RIGHT);

		centralPanel1.add(labelEntete);
		labelQuestPlat = new JLabel("Etape 1/3: CHOISISSEZ UN PLAT ", labelQuestPlat.RIGHT);
		labelQuestPlat.setFont(new Font("comfortaa", Font.BOLD, 15));
		labelQuestPlat.setForeground(Color.BLUE);
		labelPlatType = new JLabel(listeDesIdPlats.get(0), labelPlatType.RIGHT);
		// Couleur du texte
		labelPlatType.setForeground(Color.RED);

		centralPanel1.add(labelEntete);
		centralPanel1.add(labelEntete2);
		centralPanel1.add(labelQuestPlat);

		centralPanel1.add(comboBox1);
		// centralPanel1.add(labelPlatType);

		// les ACCOMPAGNEMENTS

		// récupération des id des accompagnement et leur chargement sur la combobox
		// comme item
		List<String> listeDesIdAccomp = new ArrayList<>();
		listeDesIdAccomp = carte.getListeDesIds(carte.getListeDesAccompagnements()); // !!!!!!OK OK OOK
		comboBox2 = new JComboBox<String>(carte.castListeDesId(listeDesIdAccomp));
		comboBox2.setPreferredSize(new Dimension(200, 30));
		comboBox2.setFont(new Font("comfortaa", Font.ITALIC, 32));
		comboBox2.addItemListener(this);

//		
		// Création des étiquettes
		labelQuestAcc = new JLabel("Etape 2/3: CHOISISSEZ UN ACCOMPAGNEMENT ", labelQuestAcc.RIGHT);
		labelQuestAcc.setFont(new Font("comfortaa", Font.BOLD, 15));
		labelQuestAcc.setForeground(Color.BLUE);
		labelAccomType = new JLabel(listeDesIdAccomp.get(0), labelAccomType.LEFT);
		// Couleur du texte
		labelAccomType.setForeground(Color.RED);
		// Ajouter comboBox et Label au panel
		centralPanel1.add(labelQuestAcc);
		centralPanel1.add(comboBox2);
		// Affiche le résultat du choix
		// centralPanel1.add(labelAccomType);

		// Les DESSERTS
		// récupération des id des accompagnement et leur chargement sur la combobox
		// comme item
		List<String> listeDesIdDess = new ArrayList<>();
		listeDesIdDess = carte.getListeDesIds(carte.getListeDesDesserts()); // !!!!!!OK OK OOK
		comboBox3 = new JComboBox<String>(carte.castListeDesId(listeDesIdDess));
		comboBox3.setPreferredSize(new Dimension(200, 30));
		comboBox3.setFont(new Font("comfortaa", Font.ITALIC, 32));
		comboBox3.addItemListener(this);
		// comboBox3.setBounds(10,50,50,50);
		// Création des étiquettes
		labelQuestDess = new JLabel("Etape 3/3: CHOISISSEZ UN DESSERT ", labelQuestDess.RIGHT);
		labelQuestDess.setFont(new Font("comfortaa", Font.BOLD, 15));
		labelQuestDess.setForeground(Color.BLUE);
		labelDessType = new JLabel(listeDesIdDess.get(0), labelDessType.LEFT);
		// Couleur du texte
		labelDessType.setForeground(Color.RED);
		// Ajouter comboBox et Label au panel
		centralPanel1.add(labelQuestDess);
		centralPanel1.add(comboBox3);
		// centralPanel1.add(labelDessType);

		return centralPanel1;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == comboBox1)
			labelPlatType.setText("[" + comboBox1.getSelectedItem() + "]");
		if (e.getSource() == comboBox2)
			labelAccomType.setText("[" + comboBox2.getSelectedItem() + "]");
		if (e.getSource() == comboBox3)
			labelDessType.setText("[" + comboBox3.getSelectedItem() + "]");
	}

	private JPanel createDownPanel() {
		JPanel downPanel = new JPanel(new GridLayout(1, 3));
		JButton ajouterMenu = new JButton("Ajouter Menu");
		// ajouterMenu.addActionListener(e->{ new AtauCloseConfirmationMessage(this,
		// "Menu ajouté avec succès", 3);});
		ajouterMenu.addActionListener(this);

		ajouterMenu.setPreferredSize(new Dimension(20, 40));
		ajouterMenu.setFont(new Font("comfortaa", Font.BOLD, 20));
		downPanel.add(ajouterMenu);

		JButton enregCommand = new JButton("Afficher detail commande");
		enregCommand.setPreferredSize(new Dimension(20, 40));
		enregCommand.addActionListener(this);
		enregCommand.setFont(new Font("comfortaa", Font.BOLD, 20));
		downPanel.add(enregCommand);

		JButton supprimCommande = new JButton("");
		supprimCommande.setPreferredSize(new Dimension(20, 40));
		supprimCommande.addActionListener(this);
		supprimCommande.setFont(new Font("comfortaa", Font.BOLD, 20));
		downPanel.add(supprimCommande);
		return downPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String event = e.getActionCommand();

		if (source == quitter) {
			exitConfirmation(source);
		}

		if (event.equals("Table1")) {
			setNumeroDeTable(" 1");
		}
		if (event.equals("Table2")) {
			setNumeroDeTable(" 2");
		}
		if (event.equals("Table3")) {
			setNumeroDeTable(" 3");
		}
		if (event.equals("Table4")) {
			setNumeroDeTable(" 4");
		}
		if (event.equals("Table5")) {
			setNumeroDeTable(" 5");
		}
		if (event.equals("Table6")) {
			setNumeroDeTable(" 6");
		}
		
		//ToDo: Switcher la suite des tables

		if (event.equals("Ajouter Menu")) {

			setPlatItemSelected(comboBox1.getSelectedItem().toString());
			platChoisi = carte.getPlatChoisi(getPlatItemSelected());

			setAccompItemSelected(comboBox2.getSelectedItem().toString());
			accompChoisi = carte.getAccompagnementChoisi(getAccompItemSelected());

			setDessItemSelected(comboBox3.getSelectedItem().toString());
			dessChoisi = carte.getDessertChoisi(getDessItemSelected());

			// ToDo: remplacer le numeroDeTable par le item selected des tables avec une
			// méthode qui switch en fonction de la table choisie

			if (JOptionPane.showConfirmDialog(null,
					"Numéro de la table choisie: " + getNumeroDeTable() + "\nPlat choisi: "
							+ comboBox1.getSelectedItem().toString().toUpperCase() + "\nAccompagnement chosi: "
							+ comboBox2.getSelectedItem().toString().toUpperCase() + "\nDessert choisi: "
							+ comboBox3.getSelectedItem().toString().toUpperCase()
							+ "\n\nCliquez sur:\n'Yes' pour confirmer\n'No' pour modifier",
					"Avant l'ajout, confirmez vos choix ci-dessous",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				if (getNumeroDeTable() == null) {
					timer.setRepeats(false);
					timer.start();
					optionPane.createDialog(null, "Veuillez choisir une table").setVisible(true);

				} else {
					
					laCommande.setNumTab(getNumeroDeTable());

					menu = laCommande.getMenuConcocted(platChoisi, accompChoisi, dessChoisi);
					laCommande.addMenu(menu);
					
					ArrayList<Iplat> commandeSelected = laCommande.selectCommande(getNumeroDeTable());
					laCommande.addCommande(getNumeroDeTable(), commandeSelected);
					//laCommande.setNumTab(event);
					
					//ToDo: Juste pour le test du mapping des commandes
					System.out.println("Nous avons : "+laCommande.getListeDesCommandes().size()+" commande(s)");
					
					timer.setRepeats(false);
					timer.start();
					optionPane.createDialog(null, "Menu ajouté avec succès").setVisible(true);

					setPlatItemSelected(null);
					setAccompItemSelected(null);
					setDessItemSelected(null);
				}
			}
			
			
		}

		if (event.equals("Afficher detail commande")) {
			
			// ici il faudrait voir comment faire une méthode qui affiche les détails en fonction d'un sous-menu item
			// commande table1, commande table2 etc...
			if (laCommande.getPrixTotal() == 0) {
				timer.setRepeats(false);
				timer.start();
				optionPane.createDialog(null, "Ajouter un menu à la commande!").setVisible(true);
			} else {
				
				affichage = laCommande.displayCommand();
				editeurDeText.setText(affichage);
			}
		}

	}

	/**
	 * Confirme la sortie du programme si la réponse à la question est oui. Le
	 * programme continue si la réponse est non!
	 * 
	 * @param source
	 */
	private void exitConfirmation(Object source) {
		if (JOptionPane.showConfirmDialog(null, "Voulez-vous quitter le programme ?", "Gestionnaire de commandes",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(ABORT);

	}
	
	

}
