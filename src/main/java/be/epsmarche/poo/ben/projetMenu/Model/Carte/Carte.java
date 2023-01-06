package be.epsmarche.poo.ben.projetMenu.Model.Carte;

import java.util.ArrayList;

/**
 * Classe définissant l'ensemble des cartes selectionnables dans le restaurant
 */
public class Carte {
    /**
     * @param Listes des choix de plats diponibles
     */
    private ArrayList<Choix> listeDesPlats;

    /**
     *@param Listes des choix d'accompagnements diponibles
     */
    private ArrayList<Choix> listeDesAccompagnements;

    /**
     *@param Listes des choix de desserts diponibles
     */
    private ArrayList<Choix> listeDesDesserts;

    /**
     * constructeur par defaut
     */
    public Carte() {}

    /**
     * constructeur singleton
     * Nota: Permet d'obtenir un objet unique de type Carte
     * @return une instance de la Classe Carte
     */
    public static Carte getInstance(){
       return  CarteGetter.INSTANCE;
    }

    /**
     *sous-classe  Singleton
     * qui permet d'avoir une carte unique dans un restaurant
     */
    private static class CarteGetter{
        private static final Carte INSTANCE = new Carte();
    }

}
