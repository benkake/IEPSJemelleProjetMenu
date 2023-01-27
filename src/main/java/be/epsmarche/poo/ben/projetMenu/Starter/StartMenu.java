package be.epsmarche.poo.ben.projetMenu.Starter;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import be.epsmarche.poo.ben.projetMenu.View.ViewFenetrePrinc;

/**
 * @author ben
 * Classe permettant de lancer le programme
 */
public class StartMenu {
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		ViewFenetrePrinc wind = new ViewFenetrePrinc();
		wind.setVisible(true);
	}
}
