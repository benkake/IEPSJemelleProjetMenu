package be.epsmarche.poo.ben.projetMenu.Model.Carte;

import java.io.*;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

/**
 * Classe permettant de lire fichier des données menuForTest.xml
 * @author ben
 */
public class Loader {
	
	/**
	 * Création d'un objet Document
	 */
	public Document document;
	/**
	 * Création élément racine
	 */
	public Element racine;
	
	/**
	 * tableau de stockage des 3 groupe d'elements composant les menus
	 */
	public static List<Element> composantsDesMenus = null;
	/**
	 * Contructeur 
	 * + initialisation connexion au fichier xml 
	 * + création document et racine
	 */

	public Loader(String nomFichier)throws ParserConfigurationException,SAXException,IOException {
		//this.nom = nomFichier;
		
		try {
			
			//Création d'un objet fichier dont le nom est donné en paramètre
			File inputFile = new File(nomFichier);
			// création d'un objet SAXBuilder 
			SAXBuilder saxB = new SAXBuilder();
			
			//construction d'un document JDOM 
			document = saxB.build(inputFile);
			// je teste si l'élement racine est retrouvé
			System.out.println("element Racine..: "+document.getRootElement().getName());
			// construction de l'élément racine à partir du document construit c-dessus
			racine = document.getRootElement();
			
			/**
			 * Chargement du tableau des 3 goupes 
			 * d'éléments composant les menus(plat, accompagnement, dessert) 
			 */
			composantsDesMenus = racine.getChildren();
			System.out.println("-----------------------------------");
			
			
		} catch (JDOMException|IOException e) {
			System.err.println("Une erreur s'est produite!!!!: "+e);
		}

	}
	
	/**
	 * Création d'une liste d'objet de type Choix des catégories de plat
	 */
	public void creationPlats() {
		// Creation d'une liste de plats
		ArrayList<Choix> listeDePlats = new ArrayList<>();
		// Liste du noeud plats 
		//qui se situe au rang zero du tableau menu
		
		
		//Element elementPlats = racine.getChildren("plats").get(0);
		  Element elementPlats = document.getRootElement();
		// recuperation des noeuds enfants du noeud plats
		//  System.out.println(elementPlats.getName());
		  
		  
		List <Element>listeTypesPlats = elementPlats.getChildren();
		//Création d'un iterateur pour parcourir la liste des types de plat
		Iterator it1 = listeTypesPlats.iterator();
		
		// parcours du tableau listeTypesPlats pour
		// récupérer les éléments catégories de plat
		while(it1.hasNext()){
			Element elementCategoriePlat = (Element) it1.next(); 
			String valeurAttrCategorie = elementCategoriePlat.getAttributeValue("id");
			// récupération des noeuds enfants de catégorie
			//TODO voir si l'on peut donner Iplat en paramètre de la liste ci-dessous ?? 
			List listeCategoriePlat = elementCategoriePlat.getChildren();
			Iterator it2 = listeCategoriePlat.iterator();
			// parcours du tableau listeCategorielat pour
			// récupérer les elements plat
			while(it2.hasNext()) {
				Element elementPlat = (Element) it2.next();
				String categorieDePlat = elementPlat.getName();
				String id = elementPlat.getAttributeValue("id");
				String description = elementPlat.getChildText("type");
				Double prix = Double.parseDouble(elementPlat.getChildText("prix"));
				Choix choixDePlat = new Choix(id, categorieDePlat, valeurAttrCategorie, prix, description);
				listeDePlats.add(choixDePlat);
			}
		}
		Carte carte = Carte.getInstance();
		carte.setListeDesPlats(listeDePlats);
	}
	
	/**
	 * Création d'une liste d'objet de type Choix des catégories d'accompagnement
	 */
	public void creationAccompagnements() {
		// Creation d'une liste d'accompagnements
		ArrayList<Choix> listeAccompagnements = new ArrayList<>();
		// Liste du noeud accompagnement 
		//qui se situe au rang zero du tableau menu
		Element elementAccompagnement = racine.getChildren("accompagnements").get(0);
		// recuperation des noeuds enfants du noeud accompagnement
		List listeTypesAccompagnement = elementAccompagnement.getChildren();
		//Création d'un iterateur pour parcourir la liste des types d'accompagnement
		Iterator it1 = listeTypesAccompagnement.iterator();
		
		// parcours du tableau listeTypesDacompanemet pour
		// récupérer les éléments catégories d'accompagnement
		while(it1.hasNext()){
			Element elementCategorieAccomp = (Element) it1.next(); 
			String valeurAttrCategorie = elementCategorieAccomp.getAttributeValue("id");
			// récupération des noeuds enfants de catégorie
			List listeCategorieAccomp = elementCategorieAccomp.getChildren();
			Iterator it2 = listeCategorieAccomp.iterator();
			// parcours du tableau listeCategorieAccomp pour
			// récupérer les elements accompagnement
			while(it2.hasNext()) {
				Element elementAccomp = (Element) it2.next();
				String categorieAccomp = elementAccomp.getName();
				String id = elementAccomp.getAttributeValue("id");
				String description = elementAccomp.getChildText("type");
				Double prix = Double.parseDouble(elementAccomp.getChildText("prix"));
				Choix choixDacompagnement = new Choix(id, categorieAccomp, valeurAttrCategorie, prix, description);
				listeAccompagnements.add(choixDacompagnement);
			}
		}
		Carte carte = Carte.getInstance();
		carte.setListeDesAccompagnements(listeAccompagnements);
	}
	
	/**
	 * Création d'une liste d'objet de type Choix des catégories de dessert
	 */
	public void creationDessert() {
		// Creation d'une liste de desserts
		ArrayList<Choix> listeDeDesserts = new ArrayList<>();
		// Liste du noeud Dessert 
		//qui se situe au rang zero du tableau menu
		Element elementDessert = racine.getChildren("desserts").get(0);
		// recuperation des noeuds enfants du noeud dessert
		List listeTypesDessert = elementDessert.getChildren();
		//Création d'un iterateur pour parcourir la liste des types de dessert
		Iterator it1 = listeTypesDessert.iterator();
		
		// parcours du tableau listeTypesDessert pour
		// récupérer les éléments catégories de dessert
		while(it1.hasNext()){
			Element elementCategorieDessert = (Element) it1.next(); 
			String valeurAttrDessert = elementCategorieDessert.getAttributeValue("id");
			// récupération des noeuds enfants de catégorie
			List listeCategorieDessert = elementCategorieDessert.getChildren();
			Iterator it2 = listeCategorieDessert.iterator();
			// parcours du tableau listeCategorieDessert pour
			// récupérer les elements accompagnement
			while(it2.hasNext()) {
				Element elementDess = (Element) it2.next();
				String categorieDessert = elementDess .getName();
				String id = elementDess .getAttributeValue("id");
				String description = elementDess .getChildText("type");
				Double prix = Double.parseDouble(elementDess .getChildText("prix"));
				Choix choixDeDessert = new Choix(id, categorieDessert, valeurAttrDessert, prix, description);
				listeDeDesserts.add(choixDeDessert);
			}
		}
		Carte carte = Carte.getInstance();
		carte.setListeDesAccompagnements(listeDeDesserts);
	}

	@Override
	public String toString() {
		return "Loader [document=" + document + ", racine=" + racine + "]";
	}


	
}
