package be.epsmarche.poo.ben.projetMenu.Model.Carte;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe définissant l'ensemble des cartes selectionnables dans le restaurant
 */
public class Carte {
    /**
     * Listes des choix de plats diponibles
     */
    private ArrayList<Choix> listeDesPlats;

    /**
     *Listes des choix d'accompagnements diponibles
     */
    private ArrayList<Choix> listeDesAccompagnements;

    /**
     * Listes des choix de desserts diponibles
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
     * qui permet d'avoir un composant unique du menu (plat ou accompagnement ou dessert)
     */
    private static class CarteGetter{
        private static final Carte INSTANCE = new Carte();
    }

    /**
     *
     * @param idPlat
     * @return un choix de plat
     */
    public Choix getPlatChoisi(String idPlat){
        Choix platChoisi = null;
        Iterator<Choix> it = listeDesPlats.iterator();
        while(it.hasNext()){
            Choix choix = it.next();
            if(choix.getId().equals(idPlat)){
                platChoisi = choix;
            }
        }
        return platChoisi;
    }

    /**
     * @param idAccomp
     * @return un choix d'accompagnement
     */
    public Choix getAccompagnementChoisi(String idAccomp){
        Choix accompagnementChoisi = null;
        Iterator<Choix> it = listeDesAccompagnements.listIterator();
        while(it.hasNext()){
            Choix choix = it.next();
            if(choix.getId().equals(idAccomp)){
                accompagnementChoisi = choix;
            }
        }
        return accompagnementChoisi;
    }

    public Choix getDessertChoisi(String idDessert){
        Choix dessertChoisi =null;
        Iterator<Choix> it = listeDesDesserts.iterator();
        while(it.hasNext()){
            Choix choix = it.next();
            if(choix.getId().equals(idDessert)){
                dessertChoisi = choix;
            }
        }
        return dessertChoisi;
    }

}
