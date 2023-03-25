package be.epsmarche.poo.ben.projetMenu.Model.Accompagnements;

import be.epsmarche.poo.ben.projetMenu.Model.Patterns.PlatDecorator;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe définissant un accompagnement à base de pomme de terre
 *
 * @author ben
 */
public class PommesDeTerre extends PlatDecorator {

    protected String categorie;
    /**
     * Prix de l'accompagnement à base de pomme de terre
     */
    protected Double prixPommesDT;
    /**
     * Type d'accompagnement à base de pomme de terre
     */
    protected String typePommesDT;

    /**
     * Constructeur à 1 paramètre
     *
     * @param platDecore
     */
    public PommesDeTerre(Iplat platDecore) {
        super(platDecore);
        this.platDecore = platDecore;
    }

    /**
     * Constructeur à 3 paramètres
     *
     * @param platDecore
     * @param typePommesDT
     * @param prixPommesDT
     */
    public PommesDeTerre(Iplat platDecore, String typePommesDT, Double prixPommesDT) {
        super(platDecore);
        this.typePommesDT = typePommesDT;
        this.prixPommesDT = prixPommesDT;
    }

    /**
     * @return un plat avec un accompagnement à base de pomme de terre
     */
    @Override
    public String getPeparation() {
        return platDecore.getPeparation() + " \n >Accompagnement: " + getType();
    }

    /**
     * @return le prix du plat + le prix de l'accompagnement à base de pomme de
     * terre
     */
    @Override
    public Double getPrix() {
        return (platDecore.getPrix() + prixPommesDT);
    }

    @Override
    public void setPrix(Double prix) {
        this.prixPommesDT = prix;
    }

    @Override
    public String getType() {
        return this.typePommesDT;
    }

    @Override
    public void setType(String type) {
        this.typePommesDT = type;

    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return sous forme de String, un plat avec un accompagnement à base de Pomme
     * de terre + la somme des prix du plat et de cet accompagnement
     */
    @Override
    public String toString() {
        return "- " + getPeparation() + " [Prix = " + getPrix() + " euros]\n";
    }
}
