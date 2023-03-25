package be.epsmarche.poo.ben.projetMenu.Model.Patterns;

import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

/**
 * Classe permettant d'ajouter un décorateur à un plat.
 *
 * @author ben
 */
public abstract class PlatDecorator implements Iplat {

    protected Iplat platDecore;

    public PlatDecorator(Iplat platDecore) {
        super();
        this.platDecore = platDecore;
    }

}
