package be.epsmarche.poo.ben.projetMenu.Model.Carte;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Classe permettant de lire fichier des données menuForTest.xml
 * @author ben
 */
public class Loader {
	
	/**
	 * Création d'un objet Document
	 */
	Document document;
	/**
	 * Création élément racine
	 */
	Element racine;
	/**
	 * Contructeur 
	 * + initialisation connexion au fichier xml 
	 * + création document et racine
	 */
	public Loader(String nomFichierXML) {
		// création d'un objet SAXBuilder 
		//pour analyser le fichier XML avec un parseur SAX 
		//et créer une arborescence JDOM
		SAXBuilder saxB = new SAXBuilder();
		try {
			//Création d'un objet fichier dont le nom est donné en paramètre
			File fichierXML = new File(nomFichierXML);
			
			//construction d'un document JDOM via la methode build() de SAXBuilder
			//avec en argument le fichier xml
			document = saxB.build(fichierXML);
			
		} catch (JDOMException|IOException e) {
			System.err.println("Une erreur s'est produite: "+e);
		}
		
		// construction de l'élément racine à partir du document construit c-dessus
		racine = document.getRootElement();
	}
	
	/**
	 * Création d'une liste d'objet de type Choix (des catégories de plat)
	 */
	public void creationPlats() {
		// Creation d'une liste de plats
		ArrayList<Choix> listeDePlats = new ArrayList<>();
		// Liste du noeud plats 
		//qui se situe au rang zero du tableau menu
		Element elementPlats = racine.getChildren("plats").get(0);
		// recuperation des noeuds enfants du noeud plats
		List listeTypesPlats = elementPlats.getChildren();
		//Création d'un iterateur pour parcourir la liste des types de plat
		Iterator it1 = listeTypesPlats.iterator();
		
		// parcours du tableau listeTypesPlats pour
		// récupérer les éléments catégories de plat
		while(it1.hasNext()){
			Element elementCategoriePlat = (Element) it1.next(); 
			String attrCategorieValue = elementCategoriePlat.getAttributeValue("id");
			// récupération des noeuds enfants de catégorie
			List listeCategoriePlat = elementCategoriePlat.getChildren();
			Iterator it2 = listeCategoriePlat.iterator();
			// parcours du tableau listeCategorielat pour
			// récupérer les elements plat
			while(it2.hasNext()) {
				Element elementPlat = (Element) it2.next();
				String elementDeLaCategorie = elementPlat.getName();
			}
		}
		
		
		
	}

	

}
