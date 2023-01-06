package be.epsmarche.poo.ben.projetMenu.Model.Carte;

public class Choix {
    /**
     * @param id  d'une catégorie ou d'un plat
     */
    private String id;

    /**
     * @param categorie de produit composant le menu
     */
    private String categorie;

    /**
     * @param type de produit composant le plat ou l'accompagnement ou le dessert
     */
    private String type;

    /**
     * @param prix du produit
     */
    private  Double prix;

    /**
     * @param description du produit
     */
    private String description;

    /**
     * @return une instance de la Classe choix
     * @param id
     * @param categorie
     * @param type
     * @param prix
     * @param description
     */
    public Choix(String id, String categorie, String type, Double prix, String description) {
        this.id = id;
        this.categorie = categorie;
        this.type = type;
        this.prix = prix;
        this.description = description;
    }

    /**
     * Getters et Setters
     */
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Double getPrix() {
        return prix;
    }
    public void setPrix(Double prix) {
        this.prix = prix;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Choix{" +
                "id='" + id + '\'' +
                ", categorie='" + categorie + '\'' +
                ", type='" + type + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                '}';
    }
}