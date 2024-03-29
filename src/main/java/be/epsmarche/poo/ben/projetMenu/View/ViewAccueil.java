package be.epsmarche.poo.ben.projetMenu.View;

import be.epsmarche.poo.ben.projetMenu.Model.Commandes.Commande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe définissant la fenetre d'accueil. Cette fenetre contient la barre de
 * menu et le message d'accueil
 *
 * @author ben
 * @version V.3
 */
public class ViewAccueil extends JFrame implements ActionListener {

	private static final long serialVersionUID = -3579165151123262311L;
	/**
	 * Attributs de l'interface graphique
	 */
	private final JMenuBar barreDeMenu = new JMenuBar();

	private final JMenu demarrer = new JMenu("Demarrer");
	private final JMenuItem quitter = new JMenuItem("Sortir du Programme");

	private final JMenu commander = new JMenu("Commander");
	private final JMenuItem composerCde = new JMenuItem("Composer la commande");

	private final JMenu etatsDeGest = new JMenu("Gestion");

	/**
	 * Création d'un panel
	 */
	private final JPanel panel = new JPanel();
	/**
	 * Création d'un label pour l'affichage du texte
	 */
	private final JLabel label = new JLabel();

	/**
	 * Création d'un conteneur
	 */
	protected Container contenu = new Container();

	// private static ViewTable afficheTable;

	/**
	 * Le constructeur. Il prend en paramètre le controleur
	 */

	public ViewAccueil() {
		this.initComponent();
	}

	public void initComponent() {

		// Configutation de la fenêtre
		setTitle("Gestionnaire de Commandes");
		this.setSize(1000, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contenu = this.getContentPane();

		// Configuration ddu background
		contenu.add(panel);
		panel.setBackground(Color.BLUE);

		// Creation et configuration du label
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 230));
		Font font = new Font("Comfortaa", Font.BOLD, 30);
		label.setFont(font);
		label.setText(" Bienvenu dans le gestionnaire de commandes !");
		label.setForeground(Color.WHITE);
		panel.add(label);

		// Insertion de la barre de menu dans le frame
		setJMenuBar(createBarreDeMenu());

	}

	protected JMenuBar createBarreDeMenu() {
		// dimensionnement du menu demarrer
		demarrer.setPreferredSize(new Dimension(100, 40));
		demarrer.setFont(new Font("Comforta", Font.ITALIC, 18));
		barreDeMenu.add(demarrer);

		// ajout sous-menu quitter
		demarrer.add(quitter);
		quitter.addActionListener(this);

		// dimensionnement du menu commander
		commander.setPreferredSize(new Dimension(120, 40));
		commander.setFont(new Font("Comforta", Font.ITALIC, 18));
		barreDeMenu.add(commander);

		// ajout du sous-menu "composer commande"
		commander.add(composerCde);
		composerCde.addActionListener(this);

		// dimensionnement du menu etatsDeGestion
		etatsDeGest.setPreferredSize(new Dimension(120, 40));
		etatsDeGest.setFont(new Font("Comforta", Font.ITALIC, 18));

		return barreDeMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == quitter) {
			int rep = JOptionPane.showConfirmDialog(null, "Sortir du Programme ?");
			if (rep == 0) {
				this.setVisible(false);
				System.exit(0);
			}
		}
		if (source == composerCde) {
			ViewCreerCommandeForm ouvrirCreerCde = new ViewCreerCommandeForm();
			ouvrirCreerCde.setVisible(true);
			this.dispose(); // fermer la fenêtre courante
		}

	}

}
