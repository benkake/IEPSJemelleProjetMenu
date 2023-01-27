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
	private final JMenuItem exit = new JMenuItem();
	
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
	 * A cet effet, il donne accès aux sous-menus suivants: 
	 * @see commandeCourante
	 * @see etatsEncaissements
	 * @see listeCommandes
	 * @see statistiques
	 */
	private final JMenuItem afficher = new JMenuItem();
	/**
	 * L'objet StringBuilder txt permet de concaténer des chaines de caractères modifiables.
	 * Comparativement au StringBuffer, cet objet txt n'est pas thread-safe(donc ne peut-etre executé simultannéement). 
	 * Mais il a l'avantage d'être rapide du point de vue de l'iplémentation. 
	 */
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
		
		// instanciation de la barre de menus
		JMenuBar barreDeMenu = new JMenuBar();
		
		// Insertion de la barre de menu dans le frame
		setJMenuBar(barreDeMenu);
		
		// Création menu 
		JMenu demarrer = new JMenu("Demarrer");
		demarrer.setPreferredSize(new Dimension(100,40));
		barreDeMenu.add(demarrer);
		JMenuItem exit = new JMenuItem("Exit");
		demarrer.add(exit);
		exit.addActionListener(this);
		
		JMenu commander = new JMenu("Commander");
		commander.setPreferredSize(new Dimension(120,40));
		barreDeMenu.add(commander);
		
		JMenu etatsDeGest = new JMenu("Etats de gestion");
		etatsDeGest.setPreferredSize(new Dimension(120,40));
		barreDeMenu.add(etatsDeGest);
		JMenuItem afficher = new JMenuItem("Afficher");
		etatsDeGest.add(afficher);
		afficher.addActionListener(this);
		
		contenu.add(createLeftPanel(),BorderLayout.WEST);
		contenu.add(createRightPanel(),BorderLayout.EAST);
		contenu.add(createDownPanel(),BorderLayout.SOUTH);
	}
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
	// TODO A configurer pour la sortie du program(tuto prof)
	// Voir le rôle du controleur dans le tuto prof
//		StringBuilder txt = new StringBuilder();
//		txt.append("Action relative à la commande");
//		
//		if(source == exit) {
//			txt.append("Le sous-menu exit s'affiche");
//		}
////		if(source == afficher) {
////			txt.append("Le sous-menu affichage s'affiche");
////		}
//		label.setText(txt.toString());
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
