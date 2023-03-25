package be.epsmarche.poo.ben.projetMenu.Model.Plat;

/**
 * Classe definissant un plat à base de volaille
 *
 * @author ben
 */
public class Volaille implements Iplat {
    /**
     * Prix du plat
     */
    // protected Double prix = 12.5;
    protected Double prix;
    /**
     * Type de plat
     */
    protected String type;
    /**
     * Catégorie plat
     */
    protected String categorie;

    /**
     * Constructeurs de nouvels objets plat à base de volaille
     */
    public Volaille() {
        super();
    }

    public Volaille(String type) {
        super();
        this.type = getType();
    }

    /**
     * @param type
     * @param prix
     */
    public Volaille(String type, Double prix) {
        super();
        this.type = type;
        this.prix = prix;
    }

    /**
     * Getters et Setters
     */
    @Override
    public Double getPrix() {
        return this.prix;
    }

    @Override
    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return le type de plat à base de volaille + la description de sa préparation
     */
    @Override
    public String getPeparation() {
        return getType() + " mariné à la moutarde de Dijon";
    }

    /**
     * @return au format String la méthode getPreparation()
     */
    @Override
    public String toString() {
        return getPeparation();
    }
}
