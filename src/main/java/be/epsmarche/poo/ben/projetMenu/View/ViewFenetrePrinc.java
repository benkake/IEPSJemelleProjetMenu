package be.epsmarche.poo.ben.projetMenu.View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
public class ViewFenetrePrinc extends JFrame implements ActionListener {
	
	private final JMenuBar barreDeMenu = new JMenuBar();
	
	/** menu Demarrer
	 * Donner accès au sous-menu exit.
	 * @see JMenuItem exit
	 */
	private final JMenu demarrer = new JMenu();
	/**
	 *  Sous-menu du menu démarrer: exit.
	 *  il permet de sortir du prgramme après confirmation.
	 */
	private JMenuItem quitter = new JMenuItem("Sortir du Programme");
	
	/** menu Commander.
	 * Affiche la page principale où s'effectue les commandes
	 */
	
	private final JMenu commander = new JMenu();
	
	/** menu etatsDeGestion.
	 * Permet d'avoir une vue globale sur la gestion des commandes.
	 */
	
	private final JMenu etatsDeGestion = new JMenu();
	/**
	 * Sous-menu afficher
	 * Permet d'acceder aux affichages des diférents relavifs à la gestion des commandes.
	 * A cet effet, il donne accès aux menus items suivants: 
	 * @see commandeCourante
	 * @see etatsEncaissements
	 * @see listeCommandes
	 * @see statistiques
	 */
	
	/**
	 * L'objet StringBuilder txt permet de concaténer des chaines de caractères modifiables.
	 * Comparativement au StringBuffer, cet objet txt n'est pas thread-safe(donc ne peut-etre executé simultannéement). 
	 * Mais il a l'avantage d'être rapide du point de vue de l'iplémentation. 
	 */
//	private final JMenuItem commandeCourante = new JMenuItem();
//	private final JMenuItem listeDesCommandes = new JMenuItem();
//	private final JMenuItem etatDesEncaiss = new JMenuItem();
//	private final JMenuItem statistiques = new JMenuItem();

	private final StringBuilder txt = new StringBuilder();
	/**
	 * Création d'un conteneur de type panel 
	 */
	private final JPanel panel = new JPanel();
	/**
	 * Création d'un label pour l'affichage du texte
	 */
	private final JLabel label = new JLabel();
	/**
	 * Création d'un 2e conteneur
	 */
	private Container contenu = new Container();
	
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
		
		/**
		 * Configiration de l'affichage
		 */
		JPanel panel = new JPanel();
		contenu.add(panel, BorderLayout.SOUTH);
		// Creation label
		JLabel label = new JLabel();
		panel.add(label);
		
		// Insertion de la barre de menu dans le frame
		setJMenuBar(createBarreDeMenu());
		
		// Positionnement des panels
		PanelsPositions();
		
	}
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
	// TODO A configurer pour la sortie du program(tuto prof)
	// Voir le rôle du controleur dans le tuto prof

	}
	
	private JMenuBar createBarreDeMenu() {
		
		JMenuBar barreDeMenu = new JMenuBar();
		
		JMenu demarrer = new JMenu("Demarrer");
		demarrer.setPreferredSize(new Dimension(100,40));
		barreDeMenu.add(demarrer);
		//exit = new JMenuItem("Exit");
		quitter.addActionListener(this::exitConfirmation);
		demarrer.add(quitter);
		quitter.addActionListener(this);
		
		JMenu commander = new JMenu("Commander");
		commander.setPreferredSize(new Dimension(120,40));
		barreDeMenu.add(commander);
		
		JMenu etatsDeGest = new JMenu("Etats de gestion");
		etatsDeGest.setPreferredSize(new Dimension(120,40));
		barreDeMenu.add(etatsDeGest);
		
		JMenu afficher = new JMenu("Afficher");
		etatsDeGest.add(afficher);
		afficher.addActionListener(this);
		
		JMenuItem commandeCourante = new JMenuItem("Commande courante");
		afficher.add(commandeCourante);
		commandeCourante.addActionListener(this);
		
		JMenuItem listeDesCommandes = new JMenuItem("Liste des commandes");
		afficher.add(listeDesCommandes);
		listeDesCommandes.addActionListener(this);
		
		JMenuItem etatDesEncaiss = new JMenuItem("Etat des encaissements");
		afficher.add(etatDesEncaiss);
		etatDesEncaiss.addActionListener(this);
		
		JMenuItem statistiques = new JMenuItem("Statistiques");
		afficher.add(statistiques);
		statistiques.addActionListener(this);
		
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
		//editeurDeText.setPreferredSize(new Dimension(250, 0));
		JScrollPane scrollEdit = new JScrollPane(editeurDeText);
		rightPanel.add(scrollEdit);
		return rightPanel;
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
