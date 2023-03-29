package be.epsmarche.poo.ben.projetMenu.View;

import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Carte;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Loader;
import be.epsmarche.poo.ben.projetMenu.Model.Commandes.Commande;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe définissant la fenetre principale. Cette fenetre sera ouverte en
 * permanance pour effectuer les opérations de commande et accéder aux autres
 * options et sous-options du menu
 *
 * @author ben
 * @version V.3
 */
public class ViewCreerCommandeForm extends JFrame implements ItemListener, ActionListener {

    private static final long serialVersionUID = -8859500785480111589L;
    private static JComboBox<String> comboBox1, comboBox2, comboBox3;
    //***---------------------------------------------------------
    private static Loader loadedObjects;
    private final Carte carte;
    private static MenuController contr = new MenuController();

    private ViewTable table;
    //***-------------------------------------------
    private final JMenuBar barreDeMenu = new JMenuBar();
    private final JMenu demarrer = new JMenu("Demarrer");
    private final JMenuItem quitter = new JMenuItem("Sortir du Programme ?");
    private final JMenu etatsDeGest = new JMenu("Gestion");
    private final JMenu Statistiques = new JMenu("Statistiques");
    private final JMenuItem CommandeDuJour = new JMenuItem("Commandes du Jour");
    private final JMenuItem ToutesLesComandes = new JMenuItem("Toutes les commandes");
    private final Container contenu;
    private final String message = "";
    private final JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE,
            JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
    private final javax.swing.Timer timer = new javax.swing.Timer(1000, e -> optionPane.setValue(JOptionPane.CLOSED_OPTION));
    private final Commande laCommande = new Commande();
    JButton tab1 = new JButton();
    JButton tab2 = new JButton();
    JButton tab3 = new JButton();
    JButton tab4 = new JButton();
    JButton tab5 = new JButton();
    JButton tab6 = new JButton();
    /**
     * Création d'un conteneur
     */
    private JTextArea editeurDeText = new JTextArea();
    // Création des labels
    private JLabel labelQuestPlat;
    private JLabel labelPlatType;
    private JLabel labelQuestAcc;
    private JLabel labelAccomType;
    private JLabel labelQuestDess;
    private JLabel labelDessType;
    private JLabel labelEntete;
    private JLabel labelEntete2;
    private String platItemSelected, accompItemSelected, dessItemSelected;
    //private ViewTable table;
    private int menuSizeBefAdd, menuSizeAfterAdd, commandSiseBefAdd, commandSizeAfterAdd;


    private String numeroDeTable;

    /**
     * Le constructeur
     */
    public ViewCreerCommandeForm() {
        super("Composer la commande");
        this.carte = new Carte();

        // Configutation de la fenêtre

        this.setSize(1100, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // ***--------------------------------------------------------
        // récupération dans le controleur des objets chargés par la classe Loader
        loadedObjects = MenuController.getLoadObject(loadedObjects);
        /*
         * récupération de la liste des plats chargés
         */
        ArrayList<Choix> listePlats = carte.getPlatList(); // !!!!!!OK OK OK les plats sont récupérés de la carte

        // Mise à jour de la liste des plats sur la carte

        carte.setListeDesPlats(listePlats);

        /*
         * récupération de la liste des accompagnements chargés
         */
        ArrayList<Choix> listeAccomp = carte.getAccompList(); // !!!!!!OK OK OK les plats sont récupérés de la carte

        // Mise à jour de la liste des accompagnements sur la carte

        carte.setListeDesAccompagnements(listeAccomp);
        /*
         * récupération de la liste des desserts chargés
         */
        ArrayList<Choix> listeDess = carte.getDessList();

        // Mise à jour de la liste des accompagnements sur la carte

        carte.setListeDesDesserts(listeDess);

        // ***-----------------------------------------------------------

        contenu = this.getContentPane();


        //Création d'un panel

        JPanel panel = new JPanel();
        contenu.add(panel);
        panel.setBackground(Color.green);

        // Creation et configuration du label
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 230));
        Font font = new Font("Comfortaa", Font.BOLD, 30);

        // Création d'un label pour l'affichage du texte

        JLabel label = new JLabel("Bienvenu dans le gestionnaire de commandes !");
        label.setFont(font);
        panel.add(label);

        // Insertion de la barre de menu dans le frame
        setJMenuBar(createBarreDeMenu());

        // positionnement des panels
        PanelsPositions();

    }

    public String getNumeroDeTable() {
        return numeroDeTable;
    }

    public void setNumeroDeTable(String numBoutonTable) {
        this.numeroDeTable = numBoutonTable;
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

        quitter.addActionListener(this);
        demarrer.add(quitter);

        etatsDeGest.setPreferredSize(new Dimension(80, 40));
        etatsDeGest.setFont(new Font("Comforta", Font.ITALIC, 18));
        barreDeMenu.add(etatsDeGest);

        Statistiques.setPreferredSize(new Dimension(120, 40));
        Statistiques.setFont(new Font("Comforta", Font.ITALIC, 18));
        barreDeMenu.add(Statistiques);

        JMenu afficher = new JMenu("Afficher");
        etatsDeGest.add(afficher);
        afficher.add(ToutesLesComandes);
        ToutesLesComandes.addActionListener(this);


        afficher.add(CommandeDuJour);
        CommandeDuJour.addActionListener(this);


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
        JLabel labelEnteteTab = new JLabel("");
        labelEnteteTab.setFont(new Font("Comforta", Font.BOLD, 20));
        leftPanel.add(labelEnteteTab);

        //	JButton tab1 = new JButton();
        tab1.setFont(new Font("Comforta", Font.ITALIC, 20));
        tab1.setText("Table1");
        tab1.addActionListener(this);
        leftPanel.add(tab1);

        //	JButton tab2 = new JButton();
        tab2.setFont(new Font("Comforta", Font.ITALIC, 20));
        tab2.setText("Table2");
        tab2.addActionListener(this);
        leftPanel.add(tab2);

        //	JButton tab3 = new JButton();
        tab3.setFont(new Font("Comforta", Font.ITALIC, 20));
        tab3.setText("Table3");
        tab3.addActionListener(this);
        leftPanel.add(tab3);

        //	JButton tab4 = new JButton();
        tab4.setFont(new Font("Comforta", Font.ITALIC, 20));
        tab4.setText("Table4");
        tab4.addActionListener(this);
        leftPanel.add(tab4);

        //	JButton tab5 = new JButton();
        tab5.setFont(new Font("Comforta", Font.ITALIC, 20));
        tab5.setText("Table5");
        tab5.addActionListener(this);
        leftPanel.add(tab5);

        //	JButton tab6 = new JButton();
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
        editeurDeText.setEditable(false);

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
        List<String> listeDesIdPlats;
        listeDesIdPlats = carte.getListeDesIds(carte.getListeDesPlats()); // !!!!!!OK OK OOK
        comboBox1 = new JComboBox<>(carte.castListeDesId(listeDesIdPlats));

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
        List<String> listeDesIdAccomp;
        listeDesIdAccomp = carte.getListeDesIds(carte.getListeDesAccompagnements()); // !!!!!!OK OK OOK
        comboBox2 = new JComboBox<>(carte.castListeDesId(listeDesIdAccomp));
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
        List<String> listeDesIdDess;
        listeDesIdDess = carte.getListeDesIds(carte.getListeDesDesserts()); // !!!!!!OK OK OOK
        comboBox3 = new JComboBox<>(carte.castListeDesId(listeDesIdDess));
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
        ajouterMenu.addActionListener(this);
        ajouterMenu.setPreferredSize(new Dimension(20, 40));
        ajouterMenu.setFont(new Font("comfortaa", Font.BOLD, 20));
        downPanel.add(ajouterMenu);

        JButton enregCommand = new JButton("Detail commande courante");
        enregCommand.setPreferredSize(new Dimension(20, 40));
        enregCommand.addActionListener(this);
        enregCommand.setFont(new Font("comfortaa", Font.BOLD, 20));
        downPanel.add(enregCommand);

        JButton enregAllCommand = new JButton("Update or Save Commande");
        enregAllCommand.setPreferredSize(new Dimension(20, 40));
        enregAllCommand.addActionListener(this);
        enregAllCommand.setFont(new Font("comfortaa", Font.BOLD, 20));
        downPanel.add(enregAllCommand);
        return downPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String event = e.getActionCommand();

        if (source == quitter) {
            exitConfirmation();
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

        if (event.equals("Ajouter Menu")) {

            setPlatItemSelected(Objects.requireNonNull(comboBox1.getSelectedItem()).toString());
            Choix platChoisi = carte.getPlatChoisi(getPlatItemSelected());

            setAccompItemSelected(Objects.requireNonNull(comboBox2.getSelectedItem()).toString());
            Choix accompChoisi = carte.getAccompagnementChoisi(getAccompItemSelected());

            setDessItemSelected(Objects.requireNonNull(comboBox3.getSelectedItem()).toString());
            Choix dessChoisi = carte.getDessertChoisi(getDessItemSelected());

            commandSiseBefAdd = laCommande.getListeDesCommandes().size();
            System.out.println("Nombre de commande avant création : " + commandSiseBefAdd);
            laCommande.setNumTab(this.getNumeroDeTable());
            if (JOptionPane.showConfirmDialog(null,
                    "Numéro de la table choisie: " + this.getNumeroDeTable() + "\nPlat choisi: "
                            + comboBox1.getSelectedItem().toString().toUpperCase() + "\nAccompagnement chosi: "
                            + comboBox2.getSelectedItem().toString().toUpperCase() + "\nDessert choisi: "
                            + comboBox3.getSelectedItem().toString().toUpperCase()
                            + "\n\nCliquez sur:\n'Yes' pour confirmer\n'No' pour modifier",
                    "Avant l'ajout, confirmez vos choix ci-dessous",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                if (this.getNumeroDeTable() == null) {
                    timer.setRepeats(false);
                    timer.start();
                    optionPane.createDialog(null, "Veuillez choisir une table").setVisible(true);

                } else {
                    menuSizeBefAdd = laCommande.getListeMenu().size();

                    Iplat menu = laCommande.getMenuConcocted(platChoisi, accompChoisi, dessChoisi);
                    laCommande.addMenu(menu, this.getNumeroDeTable());
                    menuSizeAfterAdd = laCommande.getListeMenu().size();
                    laCommande.addCommande(this.getNumeroDeTable(), laCommande.getListeMenu());
                    commandSizeAfterAdd = laCommande.getListeDesCommandes().size();


                    //ToDo: A effacer: Juste pour le test du mapping des commandes
                    System.out.println(" Nombre commandes avant l'ajout: " + commandSiseBefAdd);
                    System.out.println("Table No." + this.getNumeroDeTable());
                    System.out.println("Nombre de menu avant l'ajout: " + menuSizeBefAdd);
                    System.out.println("Nombre de menu après l'ajout: " + menuSizeAfterAdd + "\n");


                    System.out.println("Nombre de commande créees après ajout :" + commandSizeAfterAdd);

                    if (menuSizeAfterAdd > 0) {
                        getButtonSource(this.getNumeroDeTable()).setBackground(new Color(34, 81, 222));
                    }
                    timer.setRepeats(false);
                    timer.start();
                    optionPane.createDialog(null, "Menu ajouté avec succès").setVisible(true);


                    setPlatItemSelected(null);
                    setAccompItemSelected(null);
                    setDessItemSelected(null);
                }
            }

        }

        if (event.equals("Detail commande courante")) {

            if (this.getNumeroDeTable() == null || menuSizeAfterAdd == 0) {
                timer.setRepeats(false);
                timer.start();
                optionPane.createDialog(null, "Aucun menu n'a été composé !").setVisible(true);
            } else {
                laCommande.setNumTab(this.getNumeroDeTable());
                if (laCommande.getPrixTotal() == 0) {
                    timer.setRepeats(false);
                    timer.start();
                    optionPane.createDialog(null, "La Table " + this.getNumeroDeTable() + " n'a pas de commande!").setVisible(true);
                }
                if (menuSizeAfterAdd == menuSizeBefAdd) {
                    timer.setRepeats(false);
                    timer.start();
                    optionPane.createDialog(null, "Ajoutez un menu à la commande!").setVisible(true);
                } else {
                    // ToDo: voir dans Commande comment switcher listecommandes avec la methode selectCommande

                    laCommande.setNumTab(this.getNumeroDeTable());
                    String affichage = laCommande.displayCommand();
                    editeurDeText.setText(affichage);

                    setPlatItemSelected(null);
                    setAccompItemSelected(null);
                    setDessItemSelected(null);

                }
            }
        }


        if (event.equals("Update or Save Commande")) {
            Commande cde = new Commande();
            cde.setNumTab(this.getNumeroDeTable());
            ArrayList <String> tableOQP = new ArrayList<>();
            if (cde.getNumTab() == null || laCommande.getListeDesCommandes().isEmpty()) {
                timer.setRepeats(false);
                timer.start();
                optionPane.createDialog(null, "Aucune commande effectuée !").setVisible(true);
            } else {
                tableOQP = contr.CallGetTableOccupee();
                // Chargement des données du menu crée
                String numtbl = this.getNumeroDeTable();
                laCommande.setNumTab(numtbl);
                laCommande.setMenus(laCommande.displayCommandForDB());
                laCommande.setPrixTotalFromDB(laCommande.getPrixTotal());

                System.out.println("************ View Créer Cde: ligne 541 numtab = "+numtbl);
                if (!(tableOQP.contains(numtbl))) {
                    try {
                        //Ajoute la nouvelle table
                        System.out.println("************ View Créer Cde: ligne 545 numtab avant insertion = "+numtbl);
                        contr.callAddCommandeDAO(laCommande.getListeDesCommandes());
                        timer.setRepeats(false);
                        timer.start();
                        optionPane.createDialog(null, "1: Commande ajoutée avec succes !").setVisible(true);

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("************ View Créer Cde: après insertion: ligne 554 numtab  = "+numtbl);
                } else {
                    System.out.println("************ View Créer Cde: avant update: ligne 557 numtab  = "+numtbl);
                    try {
                        contr.callUpdateCommandeDAO(numtbl, laCommande.getListeDesCommandes());
                        timer.setRepeats(false);
                        timer.start();
                        optionPane.createDialog(null, "0: Commande updatée avec succes !").setVisible(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("************ View Créer Cde: après update: ligne 567 numtab  = "+numtbl);

                }
                laCommande.setNumTab(null); // Nettoyage du stckage de numéro de table
                System.out.println("************ View Créer Cde: avant update: ligne 569 lacommNumtab  = "+laCommande.getNumTab());
            }
        }

        if(event.equals("Toutes les commandes")){
        table = new ViewTable(contr.callGetAllCammandes()); // paramètre est l'arralist constitué de données de la bd
        table.setVisible(true);
         }else if(event.equals("Commandes du Jour")){
        table = new ViewTable();
        table.setTitle("Les commandes du jour");
        if (contr.callGetCommandeDuJour().isEmpty()) {
            timer.setRepeats(false);
            timer.start();
            optionPane.createDialog(null, "Pas encore de Commande!").setVisible(true);
        } else {
            table = new ViewTable(contr.callGetCommandeDuJour()); // paramètre est l'arraylist constitué de données de la bd
            table.setVisible(true);
        }
    }

}



        //ToDo : prévoir un bouton pour effacer dernier menu de la commande courante affichée en détail


    /**
     * Confirme la sortie du programme si la réponse à la question est oui. Le
     * programme continue si la réponse est non!
     */
    private void exitConfirmation() {
        if (JOptionPane.showConfirmDialog(null, "Voulez-vous quitter le programme ?", "Gestionnaire de commandes",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(ABORT);

    }

    public JButton getButtonSource(String numTab) {
        JButton bouton = new JButton();
        if (numTab.equals(" 1"))
            bouton = tab1;
        if (numTab.equals(" 2"))
            bouton = tab2;
        if (numTab.equals(" 3"))
            bouton = tab3;
        if (numTab.equals(" 4"))
            bouton = tab4;
        if (numTab.equals(" 5"))
            bouton = tab5;
        if (numTab.equals(" 6"))
            bouton = tab6;
        return bouton;
    }
}

