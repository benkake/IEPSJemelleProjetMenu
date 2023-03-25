package be.epsmarche.poo.ben.projetMenu.Model.Accompagnements;

import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatDecorator;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant un accompagnement à base de Frites
 *
 * @author ben
 */
public class Frites extends PlatDecorator {

    /**
     * Prix de l'accompagnement à base de frites
     */
    protected Double prixFrites;

    /**
     * Type d'accompagnement à base de frites
     */
    protected String typeFrites;
    /**
     * Catégorie Accompagnement
     */
    protected String categorie;

    /**
     * Constructeur à 1 paramètre
     *
     * @param platDecore
     */
    public Frites(Iplat platDecore) {
        super(platDecore);
        this.platDecore = platDecore;
    }

    /**
     * Constructeur à 3 paramètres
     *
     * @param menu
     * @param typeFrites
     * @param prixFrites
     */
    public Frites(Iplat menu, String typeFrites, Double prixFrites) {
        super(menu);
        this.typeFrites = typeFrites;
        this.prixFrites = prixFrites;
    }

    /**
     * @return un plat avec un accompagnement à base de Frites
     */
    @Override
    public String getPeparation() {
        return platDecore.getPeparation() + " \n >Accompagnement: " + this.getType();
    }

    /**
     * @return le prix du plat + le prix de l'accompagnement à base de frites
     */
    @Override
    public Double getPrix() {
        return (platDecore.getPrix() + prixFrites);
    }

    @Override
    public void setPrix(Double prix) {
        this.prixFrites = prix;
    }

    @Override
    public String getType() {
        return typeFrites;
    }

    @Override
    public void setType(String type) {
        this.typeFrites = type;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return sous de String, un plat avec un accompagnement à base de Frites + la
     * somme des prix du plat et de cet accompagnement
     */
    @Override
    public String toString() {
        return "- " + getPeparation() + " [Prix = " + getPrix() + " euros]\n";
    }

}
