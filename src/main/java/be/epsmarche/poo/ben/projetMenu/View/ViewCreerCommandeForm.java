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
import java.io.IOException;
import java.util.ArrayList;

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
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Carte;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Loader;
import be.epsmarche.poo.ben.projetMenu.Model.Commandes.Commande;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.AccompagnementFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.DessertFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatFactory;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant la fenetre principale. Cette fenetre sera ouverte en
 * permanance pour effectuer les opérations de commande et accéder aux autres
 * options et sous-options du menu
 * 
 * @version V.3
 * @author ben
 */
public class ViewCreerCommandeForm extends JFrame implements ItemListener, ActionListener {
	
	private Carte carte = null;
	private Choix elementChoisi, accompChoisi, dessChoisi;
	
	private static final long serialVersionUID = -8859500785480111589L;
	private static MenuController contr = null;
	private final JMenuBar barreDeMenu = new JMenuBar();
	/**
	 * menu Demarrer Donner accès au sous-menu exit.
	 * 
	 * @see JMenuItem exit
	 */
	private final JMenu demarrer = new JMenu("Demarrer");
	/**
	 * Sous-menu du menu démarrer: exit. il permet de sortir du prgramme après
	 * confirmation.
	 */
	private JMenuItem quitter = new JMenuItem("Sortir du Programme");
	/**
	 * menu Commander. Affiche la page principale où s'effectue les commandes
	 */
	// private final JMenu commander = new JMenu("Commander");
	/**
	 * menu etatsDeGestion. Permet d'avoir une vue globale sur la gestion des
	 * commandes.
	 */
	// private JMenuItem composerCde = new JMenuItem("Composer la commande");
	private final JMenu etatsDeGest = new JMenu("Etats de gestion");
	/**
	 * Sous-menu afficher Permet d'acceder aux affichages des diférents relavifs à
	 * la gestion des commandes. A cet effet, il donne accès aux menus items
	 * suivants:
	 * 
	 * @see commandeCourante
	 * @see etatsEncaissements
	 * @see listeCommandes
	 * @see statistiques
	 */
	private final JMenu afficher = new JMenu("Afficher");
	private final JMenuItem commandeCourante = new JMenuItem("Commande courante");
	private final JMenuItem etatDesEncaiss = new JMenuItem("Etat des encaissements");
	private final JMenuItem listeDesCommandes = new JMenuItem("Liste des commandes");
	private final JMenuItem statistiques = new JMenuItem("Statistiques");
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
	private Container contenu = new Container();

	// Création des labels
	private static JLabel labelQuestPlat, labelPlatType, labelQuestAcc, labelAccomType, labelQuestDess, labelDessType,
			labelEntete, labelEntete2, labelEnteteTab;

	private static JComboBox comboBox1, comboBox2, comboBox3;

	private String idPlat, idAccomp,idDess;
	private Iplat menu =null;
	private static Loader loading = null;
	private static Commande laCommandes = new Commande("Table1"); 
	

	/**
	 * Le constructeur
	 */
	public ViewCreerCommandeForm() {
		super("Composer la commande");
		this.contr = new MenuController();
		this.carte = new Carte();
		/**
		 * Configutation de la fenêtre
		 */

		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

		// **********Positionnement des panels
		// TODO En cliquant sur le menu Commander l'on doit
		// accéder à la page créée par la fonction ci-dessous

		PanelsPositions();

	}

	public String getIdPlat() {
		return idPlat;
	}

	public void setIdPlat(String idPlat) {
		this.idPlat = idPlat;
	}

	public String getIdAccomp() {
		return idAccomp;
	}

	public void setIdAccomp(String idAccomp) {
		this.idAccomp = idAccomp;
	}

	public String getIdDess() {
		return idDess;
	}

	public void setIdDess(String idDess) {
		this.idDess = idDess;
	}

	private JMenuBar createBarreDeMenu() {

		// JMenuBar barreDeMenu = new JMenuBar();

//		JMenu demarrer = new JMenu("Demarrer");
		demarrer.setPreferredSize(new Dimension(100, 40));
		barreDeMenu.add(demarrer);
		// exit = new JMenuItem("Exit");
		quitter.addActionListener(this::actionPerformed);
		demarrer.add(quitter);
//		//commander.setPreferredSize(new Dimension(120,40));
//		
//		barreDeMenu.add(commander);

//		JMenu etatsDeGest = new JMenu("Etats de gestion");
		etatsDeGest.setPreferredSize(new Dimension(120, 40));
		barreDeMenu.add(etatsDeGest);

		JMenu afficher = new JMenu("Afficher");
		etatsDeGest.add(afficher);
		// afficher.addActionListener(this);

//		JMenuItem commandeCourante = new JMenuItem("Commande courante");
		afficher.add(commandeCourante);
		// commandeCourante.addActionListener(this);

//		JMenuItem listeDesCommandes = new JMenuItem("Liste des commandes");
		afficher.add(listeDesCommandes);
		// listeDesCommandes.addActionListener(this);

//		JMenuItem etatDesEncaiss = new JMenuItem("Etat des encaissements");
		afficher.add(etatDesEncaiss);
		// etatDesEncaiss.addActionListener(this);

//		JMenuItem statistiques = new JMenuItem("Statistiques");
		afficher.add(statistiques);
		// statistiques.addActionListener(this);

		return barreDeMenu;
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

	private void PanelsPositions() {
		contenu.add(createLeftPanel(), BorderLayout.WEST);
		contenu.add(createRightPanel(), BorderLayout.EAST);
		contenu.add(createDownPanel(), BorderLayout.SOUTH);
		contenu.add(createCentralPanel1(), BorderLayout.CENTER);
	}

	private JPanel createLeftPanel() {
		JPanel leftPanel = new JPanel(new GridLayout(7, 1));
		labelEnteteTab = new JLabel("");
		labelEnteteTab.setFont(new Font("serif", Font.ITALIC, 20));
		leftPanel.add(labelEnteteTab);

		leftPanel.add(new JButton("Table1"));
		leftPanel.add(new JButton("Table2"));
		leftPanel.add(new JButton("Table3"));
		leftPanel.add(new JButton("Table4"));
		leftPanel.add(new JButton("Table5"));
		leftPanel.add(new JButton("Table6"));
		return leftPanel;
	}

	private JPanel createRightPanel() {
		JPanel rightPanel = new JPanel(new GridLayout(1, 1));
		JTextArea editeurDeText = new JTextArea("Affichage texte ici");
		// editeurDeText.setPreferredSize(new Dimension(750, 0));
		JScrollPane scrollEdit = new JScrollPane(editeurDeText);
		rightPanel.add(scrollEdit);
		return rightPanel;
	}

	// Compacter la methode ci-dessous

	private JPanel createCentralPanel1() {
		JPanel centralPanel1 = new JPanel(new GridLayout(5, 0));

		// Liste des plats
		String listeDesPlats[] = { "steakViande", "steakPoisson", "steakVolaille" };
		// Liste des desserts
		// Liste des accompagnemenst
		String listeDesAccomp[] = { "manioc", "macaroni", "croquettes","risotto" };
		// Liste des desserts
		String listeDesDesserts[] = { "multifruit", "crepe","milkshake", "cognac" };

		comboBox1 = new JComboBox<String>(listeDesPlats);
		comboBox1.addItemListener(this);
		// comboBox1.setBounds(10,50,50,50);
		// Création des étiquettes
		// TEST

		// Labelle d'entete
		labelEntete = new JLabel("Choisir une table et composer..", labelEntete.CENTER);
		labelEntete.setFont(new Font("comfortaa", Font.ITALIC, 25));
		labelEntete.setForeground(Color.BLUE);
		labelEntete.setHorizontalAlignment(SwingConstants.RIGHT);

		labelEntete2 = new JLabel("la commande en 3 étapes", labelEntete2.CENTER);
		labelEntete2.setFont(new Font("comfortaa", Font.ITALIC, 25));
		labelEntete2.setForeground(Color.BLUE);
		labelEntete.setHorizontalAlignment(SwingConstants.RIGHT);

		// centralPanel1.add(labelEntete);
		labelQuestPlat = new JLabel("Etape 1/3: CHOISISSEZ UN PLAT ", labelQuestPlat.RIGHT);
		labelQuestPlat.setFont(new Font("comfortaa", Font.BOLD, 15));
		labelQuestPlat.setForeground(Color.BLUE);
		labelPlatType = new JLabel(listeDesPlats[0], labelPlatType.LEFT);
		// Couleur du texte
		labelPlatType.setForeground(Color.RED);

		
		
		centralPanel1.add(labelEntete);
		centralPanel1.add(labelEntete2);
		centralPanel1.add(labelQuestPlat);

		centralPanel1.add(comboBox1);
		// Affiche les résultats du choix
		// centralPanel1.add(labelPlatType);

		// Les accompagnements
		comboBox2 = new JComboBox<String>(listeDesAccomp);
		comboBox2.addItemListener(this);
		// comboBox2.setBounds(10,50,50,50);
		// Création des étiquettes
		labelQuestAcc = new JLabel("Etape 2/3: CHOISISSEZ UN ACCOMPAGNEMENT ", labelQuestAcc.RIGHT);
		labelQuestAcc.setFont(new Font("comfortaa", Font.BOLD, 15));
		labelQuestAcc.setForeground(Color.BLUE);
		labelAccomType = new JLabel(listeDesAccomp[0], labelAccomType.LEFT);
		// Couleur du texte
		labelAccomType.setForeground(Color.BLUE);
		// Ajouter comboBox et Label au panel
		centralPanel1.add(labelQuestAcc);
		centralPanel1.add(comboBox2);
		// Affiche le résultat du choix
		// centralPanel1.add(labelAccomType);

		// Les Desserts
		comboBox3 = new JComboBox<String>(listeDesDesserts);
		comboBox3.addItemListener(this);
		// comboBox3.setBounds(10,50,50,50);
		// Création des étiquettes
		labelQuestDess = new JLabel("Etape 3/3: CHOISISSEZ UN DESSERT ", labelQuestDess.RIGHT);
		labelQuestDess.setFont(new Font("comfortaa", Font.BOLD, 15));
		labelQuestDess.setForeground(Color.BLUE);
		labelDessType = new JLabel(listeDesDesserts[0], labelDessType.LEFT);
		// Couleur du texte
		labelDessType.setForeground(Color.darkGray);
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
		ajouterMenu.addActionListener(this);
		ajouterMenu.setPreferredSize(new Dimension(20, 40));
		downPanel.add(ajouterMenu);

		JButton enregCommand = new JButton("Enregistrer la Commande");
		enregCommand.setPreferredSize(new Dimension(20, 40));
		enregCommand.addActionListener(this);
		downPanel.add(enregCommand);

		JButton supprimCommande = new JButton("Supprimer la Commande");
		supprimCommande.setPreferredSize(new Dimension(20, 40));
		supprimCommande.addActionListener(this);
		downPanel.add(supprimCommande);
		return downPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String event = e.getActionCommand();

		// Gestion des sources menuItems
		if (source == quitter) {
			exitConfirmation(source);
		}
		// Gestion des événements

		if (event.equals("Ajouter Menu")) {
			// Instanciation de la carte
	
			try {
				loading = new Loader("menu.xml");
			} catch (ParserConfigurationException | SAXException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			loading.creationPlats();
			ArrayList<Choix> listeDesPlats = Carte.getSingleInstance().getListeDesPlats();
			loading.creationAccompagnements();
			ArrayList<Choix> listeAccompagnements = Carte.getSingleInstance().getListeDesAccompagnements();
			loading.creationDessert();
			ArrayList<Choix> listeDesDesserts = Carte.getSingleInstance().getListeDesDesserts();
			
			System.out.println("Les catégories ont été chargées sur la carte");
			System.out.println(" Nombre de plats sur la carte: "+listeDesPlats.size());
			System.out.println(" Nombre d'accompagnements sur la carte: "+listeAccompagnements.size());
			System.out.println(" Nombre de dessert sur la carte: "+listeDesDesserts.size());
			
			
			// Choix des éléments du menu sur la carte
//			String selectedItem = (String) comboBox1.getSelectedItem();
//
//			
//			if(selectedItem.contentEquals(getIdPlat()))
//				setIdPlat(selectedItem);
//				System.out.println("Plat Selectionné: "+carte.getPlatChoisi(getIdPlat()));

			setIdPlat(comboBox1.getSelectedItem().toString());
			System.out.println("IdPlatSelectionné: "+getIdPlat());
			//Faire une boucle qui retourne le choix
			// En fonction des sorties des comboBox
			
			elementChoisi = listeDesPlats.get(0);
			System.out.println("Description du plat choisi: *******: "+elementChoisi.getDescription());
			System.out.println("Prix du plat choisi: *******: "+elementChoisi.getPrix());
			
			setIdAccomp(comboBox2.getSelectedItem().toString());
			System.out.println("IdAccompSelectionné: "+getIdAccomp());
			
			setIdDess(comboBox3.getSelectedItem().toString());
			System.out.println("IdDessertSelectionné: "+getIdDess());
			System.out.println("Les comboBox fonctionnent bien");
			
			
		///------------------------------------	
			Choix elementChoisi = carte.getPlatChoisi(getIdPlat());
			Choix accompChoisi = carte.getAccompagnementChoisi(getIdAccomp());
			Choix dessertChoisi = carte.getDessertChoisi(getIdDess());
			
			
	//--------------------------ça ne fonctionne pas à partir d'ici
			menu = contr.getMenuSelected(elementChoisi, accompChoisi, dessertChoisi);
			//menu = getMenuSelected(elementChoisi, accompChoisi, dessertChoisi);
			laCommandes.addMenu(menu);
			
			System.out.println("Nombre de menu dans la commande: "+laCommandes.getListeMenu().size());
			System.out.println("La commande\n");
			
		}

	}
	
	public Iplat getMenuSelected(Choix plat, Choix accomp, Choix dess) {

		PlatFactory fabricPlat = new PlatFactory();
		Iplat menuChoisi = fabricPlat.getPlat(plat.getCategorie(), plat.getType(), plat.getPrix());
		AccompagnementFactory fabricAccomp = new AccompagnementFactory();
		menuChoisi = fabricAccomp.getAccomp(accomp.getCategorie(), menuChoisi, accomp.getType(), accomp.getPrix());
		DessertFactory fabricDess = new DessertFactory();
		menuChoisi = fabricDess.getDess(dess.getCategorie(), menuChoisi, dess.getType(), dess.getPrix());
		return menuChoisi;
	}
	
	public boolean verif(Choix elementChoisi) {
		return elementChoisi == null;
	}
}
