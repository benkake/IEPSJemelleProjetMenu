package be.epsmarche.poo.ben.projetMenu.Model.Patterns;

import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.Frites;
import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.Pates;
import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.PommesDeTerre;
import be.epsmarche.poo.ben.projetMenu.Model.Accompagnements.Riz;
import be.epsmarche.poo.ben.projetMenu.Model.Carte.Choix;
import be.epsmarche.poo.ben.projetMenu.Model.Plat.Iplat;

public class AccompagnementFactory {

    /**
     * Constructeur par defaut
     */
    public AccompagnementFactory() {

    }

    public Iplat getAccomp(Iplat plat, Choix accomp) {

        if (accomp.getType() == null)
            return null;
        if (accomp.getType().equalsIgnoreCase("frites"))
            return new Frites(plat, accomp.getDescription(), accomp.getPrix());
        if (accomp.getType().equalsIgnoreCase("pates"))
            return new Pates(plat, accomp.getDescription(), accomp.getPrix());
        if (accomp.getType().equalsIgnoreCase("PommeDT"))
            return new PommesDeTerre(plat, accomp.getDescription(), accomp.getPrix());
        if (accomp.getType().equalsIgnoreCase("riz"))
            return new Riz(plat, accomp.getDescription(), accomp.getPrix());

        return null;
    }

}
