package be.epsmarche.poo.ben.projetMenu.Model.Carte;

import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe définissant la carte du restaurant
 */
public class Carte {
    static MenuController contr = new MenuController();
    private static Loader load;
    private static final Loader loaded = MenuController.getLoadObject(load);
    /**
     * Listes des choix de plats diponibles
     */
    private ArrayList<Choix> listeDesPlats;
    /**
     * Listes des choix d'accompagnements diponibles
     */
    private ArrayList<Choix> listeDesAccompagnements;
    /**
     * Listes des choix de desserts diponibles
     */
    private ArrayList<Choix> listeDesDesserts;

    /**
     * constructeur par defaut
     */
    public Carte() {
    }

    /**
     * Constructeur singleton NB: Permet d'obtenir un objet unique de type Carte.
     *
     * @return une instance de la Classe Carte.
     */
    public static Carte getSingleInstance() { // permet de rendre accesible l'objet carte
        return SingleCarteGetter.INSTANCE;
    }

    public ArrayList<Choix> getListeDesPlats() {
        return this.listeDesPlats;
    }

    /**
     * Getters and setters
     */

    public void setListeDesPlats(ArrayList<Choix> listeDesPlats) {

        this.listeDesPlats = listeDesPlats;
    }

    public ArrayList<Choix> getListeDesAccompagnements() {
        return this.listeDesAccompagnements;
    }

    public void setListeDesAccompagnements(ArrayList<Choix> listeDesAccompagnements) {
        this.listeDesAccompagnements = listeDesAccompagnements;
    }

    public ArrayList<Choix> getListeDesDesserts() {
        return listeDesDesserts;
    }

    public void setListeDesDesserts(ArrayList<Choix> listeDesDesserts) {
        this.listeDesDesserts = listeDesDesserts;
    }

    /**
     * Permet de choisir un plat
     *
     * @param idPlat
     * @return un plat choisi
     */
    public Choix getPlatChoisi(String idPlat) {
        Choix platChoisi = null;
        Iterator<Choix> it = getListeDesPlats().iterator();
        while (it.hasNext()) {
            Choix choix = it.next();
            if (choix.getId().equals(idPlat)) {
                platChoisi = choix;
            }
        }
        return platChoisi;
    }

    /**
     * Permet de choisir un accompagnement
     *
     * @param idAccomp
     * @return un choix d'accompagnement
     */
    public Choix getAccompagnementChoisi(String idAccomp) {
        Choix accompagnementChoisi = null;
        Iterator<Choix> it = listeDesAccompagnements.iterator();
        while (it.hasNext()) {
            Choix choix = it.next();
            if (choix.getId().equals(idAccomp)) {
                accompagnementChoisi = choix;
            }
        }
        return accompagnementChoisi;
    }

    /**
     * Permet de choisir un dessert
     *
     * @param idDessert
     * @return
     */
    public Choix getDessertChoisi(String idDessert) {
        Choix dessertChoisi = null;
        Iterator<Choix> it = listeDesDesserts.iterator();
        while (it.hasNext()) {
            Choix choix = it.next();
            if (choix.getId().equals(idDessert)) {
                dessertChoisi = choix;
            }
        }
        return dessertChoisi;
    }

    public String[] castListeDesId(List<String> listeDesId) {

        int taille = listeDesId.size();
        String[] comboItems = new String[taille];

        for (int i = 0; i < taille; ++i) {
            comboItems[i] = listeDesId.get(i);
        }
        // return Arrays.toString(comboItems);
        return comboItems;
    }

    public List<String> getListeDesIds(ArrayList<Choix> elementMenu) {
        ArrayList<String> listeDesIds = new ArrayList<>();

        for (int i = 0; i < elementMenu.size(); ++i) {
            Choix element = elementMenu.get(i);
            listeDesIds.add(element.getId());
        }
        return listeDesIds;
    }

    public ArrayList<Choix> getPlatList() {
        try {
            loaded.creationPlats();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ArrayList<Choix> listeDesElements = getSingleInstance().getListeDesPlats();
        return listeDesElements;
    }

    public ArrayList<Choix> getAccompList() {
        try {
            loaded.creationAccompagnements();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ArrayList<Choix> listeDesAccomp = getSingleInstance().getListeDesAccompagnements();
        return listeDesAccomp;
    }

    public ArrayList<Choix> getDessList() {
        try {
            loaded.creationDessert();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ArrayList<Choix> listeDesDess = getSingleInstance().getListeDesDesserts();
        return listeDesDess;
    }

    /**
     * classe interne Singleton Permet de créer un unique objet carte
     */
    private static class SingleCarteGetter {
        // nouvelle carte est stockée dans une constante (INSTANCE) de type carte
        private static final Carte INSTANCE = new Carte();
    }

}
