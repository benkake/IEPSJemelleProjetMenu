package be.epsmarche.poo.ben.projetMenu.Model.Plat;

/**
 * Interface définissant le plat
 *
 * @author ben
 */
public interface Iplat {
    String getPeparation();

    Double getPrix();

    void setPrix(Double prix);

    String getType();

    void setType(String type);
}
