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

import javax.swing.BoxLayout;
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
/**
 * Classe définissant la fenetre principale.
 * Cette fenetre sera ouverte en permanance 
 * pour effectuer les opérations de commande 
 * et accéder aux autres options et sous-options du menu 
 * @version V.3
 * @author ben	
 */
public class ViewFenetrePrinc extends JFrame implements ItemListener {
	
	private static final long serialVersionUID = -8859500785480111589L;
	private final JMenuBar barreDeMenu = new JMenuBar();
	/** menu Demarrer
	 * Donner accès au sous-menu exit.
	 * @see JMenuItem exit
	 */
	private final JMenu demarrer = new JMenu("Demarrer");
	/**
	 *  Sous-menu du menu démarrer: exit.
	 *  il permet de sortir du prgramme après confirmation.
	 */
	private JMenuItem quitter = new JMenuItem("Sortir du Programme");
	/** menu Commander.
	 * Affiche la page principale où s'effectue les commandes
	 */
	private final JMenu commander = new JMenu("Commander");
	/** menu etatsDeGestion.
	 * Permet d'avoir une vue globale sur la gestion des commandes.
	 */
	private final JMenu etatsDeGest = new JMenu("Etats de gestion");
	/**
	 * Sous-menu afficher
	 * Permet d'acceder aux affichages des diférents relavifs à la gestion des commandes.
	 * A cet effet, il donne accès aux menus items suivants: 
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
	private static JLabel labelQuestPlat, labelPlatType, labelQuestAcc, labelAccomType, labelQuestDess, labelDessType;
	private static JComboBox comboBox1, comboBox2, comboBox3;
	
	
	
	
	/**
	 * Le constructeur
	 */
	public  ViewFenetrePrinc() {
		/**
		 * Configutation de la fenêtre
		 */
		super("Gestionnaire de Commandes");
		this.setSize(1000,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		contenu = (JPanel) this.getContentPane();
		
		
		contenu.add(panel);
		panel.setBackground(Color.green);
		
		// Creation et configuration du label
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 230));
		Font font = new Font("Comfortaa",Font.BOLD,30);
		label.setFont(font);
		panel.add(label);
	
		
		// Insertion de la barre de menu dans le frame
		setJMenuBar(createBarreDeMenu());
		
		// **********Positionnement des panels
		// TODO En cliquant sur le menu Commander l'on doit
		//accéder à la page créée par la fonction ci-dessous
		
		PanelsPositions();
		
	}

	
	private JMenuBar createBarreDeMenu() {
		
		//JMenuBar barreDeMenu = new JMenuBar();
		
//		JMenu demarrer = new JMenu("Demarrer");
		demarrer.setPreferredSize(new Dimension(100,40));
		barreDeMenu.add(demarrer);
		//exit = new JMenuItem("Exit");
		quitter.addActionListener(this::exitConfirmation);
		demarrer.add(quitter);
		//quitter.addActionListener(this);
		
//		JMenu commander = new JMenu("Commander");
		commander.setPreferredSize(new Dimension(120,40));
		commander.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuCommanderListener(e);
				
			}

			private void menuCommanderListener(ActionEvent e) {
				System.out.println(" Menu commander a été cliqué");
				
			}
		});
		barreDeMenu.add(commander);
	
//		JMenu etatsDeGest = new JMenu("Etats de gestion");
		etatsDeGest.setPreferredSize(new Dimension(120,40));
		barreDeMenu.add(etatsDeGest);
		
		JMenu afficher = new JMenu("Afficher");
		etatsDeGest.add(afficher);
		//afficher.addActionListener(this);
		
//		JMenuItem commandeCourante = new JMenuItem("Commande courante");
		afficher.add(commandeCourante);
		//commandeCourante.addActionListener(this);
		
//		JMenuItem listeDesCommandes = new JMenuItem("Liste des commandes");
		afficher.add(listeDesCommandes);
		//listeDesCommandes.addActionListener(this);
		
//		JMenuItem etatDesEncaiss = new JMenuItem("Etat des encaissements");
		afficher.add(etatDesEncaiss);
		//etatDesEncaiss.addActionListener(this);
		
//		JMenuItem statistiques = new JMenuItem("Statistiques");
		afficher.add(statistiques);
		//statistiques.addActionListener(this);
		
		return barreDeMenu;
	}
	
	/**
	 * Confirme la sortie du programme si la réponse à la question est oui.
	 * Le programme continue si la réponse est non!
	 * @param event
	 */
	private void exitConfirmation(ActionEvent event) {
	    if(JOptionPane.showConfirmDialog(quitter,"Voulez-vous quitter le programme ?","Gestionnaire de commandes",
	    		JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
	    System.exit(ABORT);  
	}
	
	
	private void PanelsPositions() {
		contenu.add(createLeftPanel(),BorderLayout.WEST);
		contenu.add(createRightPanel(),BorderLayout.EAST);
		contenu.add(createDownPanel(),BorderLayout.SOUTH);
		contenu.add(createCentralPanel1(),BorderLayout.CENTER);
	}
	
	
	private JPanel createLeftPanel() {
		JPanel leftPanel = new JPanel(new GridLayout(6,1));
		leftPanel.add(new JButton("Table1"));
		leftPanel.add(new JButton("Table2"));
		leftPanel.add(new JButton("Table3"));
		leftPanel.add(new JButton("Table4"));
		leftPanel.add(new JButton("Table5"));
		leftPanel.add(new JButton("Table6"));
		return leftPanel;
	}
	
	private JPanel createRightPanel() {
		JPanel rightPanel = new JPanel(new GridLayout(1,1));
		JTextArea editeurDeText = new JTextArea("Affichage texte ici");
		//editeurDeText.setPreferredSize(new Dimension(750, 0));
		JScrollPane scrollEdit = new JScrollPane(editeurDeText);
		rightPanel.add(scrollEdit);
		return rightPanel;
	}
	
	private JPanel createCentralPanel1() {
		JPanel centralPanel1 = new JPanel(new GridLayout(3,3));
		// Les des plats
		String listeDesPlats[]= {"Steak de mouton","Steak de poisson","Steak de volaille"};
		// Liste des desserts
		String listeDesAccomp[]= {"Frite de Manioc","Macaroni de Sicile","Croquettes de pomme de Terre","Risotto"};
		// Liste des accompagnemenst
		String listeDesDesserts[]= {"Salade de fruits tropicaux","Crêpe à la crème au chocolat","Milkshake à la vanille","Cognac de bourgogne"};
		comboBox1 = new JComboBox<String>(listeDesPlats);
		comboBox1.addItemListener(this);
		comboBox1.setBounds(10,50,50,50);
		//comboBox1.setBounds(50, 50, 90,20);
		// Création des étiquettes
		labelQuestPlat = new JLabel("PLAT");
		labelPlatType = new JLabel(listeDesPlats[0]);
		// Couleur du texte
		labelPlatType.setForeground(Color.RED);
		// Ajouter comboBox et Label au panel
		centralPanel1.add(labelQuestPlat);
		centralPanel1.add(comboBox1);
		centralPanel1.add(labelPlatType);
		
		
		// Les accompagnements
		comboBox2 = new JComboBox<String>(listeDesAccomp);
		comboBox2.addItemListener(this);
		comboBox2.setBounds(10,50,50,50);
		// Création des étiquettes
		labelQuestAcc = new JLabel("ACCOMPAGNEMENT");
		labelAccomType = new JLabel(listeDesAccomp[0]);
		// Couleur du texte
		labelAccomType.setForeground(Color.BLUE);
		// Ajouter comboBox et Label au panel
		centralPanel1.add(labelQuestAcc);
		centralPanel1.add(comboBox2);
		centralPanel1.add(labelAccomType);
		

		// Les Desserts
		comboBox3 = new JComboBox<String>(listeDesDesserts);
		comboBox3.addItemListener(this);
		comboBox3.setBounds(10,50,50,50);
		// Création des étiquettes
		labelQuestDess = new JLabel("DESSERT");
		labelDessType = new JLabel(listeDesDesserts[0]);
		// Couleur du texte
		labelDessType.setForeground(Color.GRAY);
		// Ajouter comboBox et Label au panel
		centralPanel1.add(labelQuestDess);
		centralPanel1.add(comboBox3);
		centralPanel1.add(labelDessType);
		
		return centralPanel1;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == comboBox1) 
			labelPlatType.setText("["+comboBox1.getSelectedItem() +"]");
		if(e.getSource() == comboBox2) 
			labelAccomType.setText("["+comboBox2.getSelectedItem() +"]");
		if(e.getSource() == comboBox3) 
			labelDessType.setText("["+comboBox3.getSelectedItem() +"]");

	}
	
	
	private JPanel createDownPanel() {
		JPanel downPanel = new JPanel(new GridLayout(1,3));
		JButton annulerCommande = new JButton("Annuler la Commande");
		annulerCommande.setPreferredSize(new Dimension(20,40));
		downPanel.add(annulerCommande);
		
		JButton validerCommande = new JButton("Valider la Commande");
		annulerCommande.setPreferredSize(new Dimension(20,40));
		downPanel.add(validerCommande);
		
		JButton modifierCommande = new JButton("Modifier la Commande");
		modifierCommande.setPreferredSize(new Dimension(20,40));
		downPanel.add(modifierCommande);
		
		return downPanel;
	}


	
	
}
