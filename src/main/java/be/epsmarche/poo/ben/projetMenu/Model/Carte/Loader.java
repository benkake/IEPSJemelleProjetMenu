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
 * Classe permettant de lire le fichier des données "menuForTest.xml"
 * 
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
	 * Contructeur + initialisation connexion au fichier xml + création document et
	 * racine
	 */
	public Loader(String nomFichier) throws ParserConfigurationException, SAXException, IOException {

		try {

			// Création d'un objet fichier
			File inputFile = new File(nomFichier);
			// création d'un objet SAXBuilder
			SAXBuilder saxB = new SAXBuilder();

			// construction d'un document JDOM avec un fichier en paramètre
			document = saxB.build(inputFile);

			// construction de l'élément racine à partir du document construit
			racine = document.getRootElement();

			/**
			 * Chargement du tableau racine contenant les 3 goupes d'éléments(plat,
			 * accompagnement, dessert) qui composent les menus
			 */
			composantsDesMenus = racine.getChildren();

		} catch (JDOMException | IOException e) {
			System.err.println("Une erreur s'est produite pendant la connexion au fichier XML!!!!: " + e);
		}

	}

	/**
	 * Création d'une liste d'objet de type Choix des catégories de plat
	 */
	public void creationPlats() {
		// Creation d'une liste de plats
		ArrayList<Choix> listeDePlats = new ArrayList<>();
		// Liste du noeud plats
		Element elementPlats = racine.getChildren("plats").get(0);
		// recuperation des noeuds enfants du noeud "plats"
		List<Element> listeTypesPlats = elementPlats.getChildren();
		// Création d'un iterateur pour parcourir les enfants du noeud plats
		Iterator<Element> it1 = listeTypesPlats.iterator();

		// parcours du tableau listeTypesPlats pour
		// récupérer les éléments catégories de plat
		while (it1.hasNext()) {
			Element elementCategoriePlat = (Element) it1.next();
			String valeurAttrCategorie = elementCategoriePlat.getAttributeValue("id");
			// récupération des noeuds enfants de catégorie
			List<Element> listeCategoriePlat = elementCategoriePlat.getChildren();
			Iterator<Element> it2 = listeCategoriePlat.iterator();
			// parcours du tableau listeCategorielat pour
			// récupérer les elements plat
			while (it2.hasNext()) {
				Element elementPlat = (Element) it2.next();
				String categorieDePlat = elementPlat.getName();
				String id = elementPlat.getAttributeValue("id");
				String description = elementPlat.getChildText("type");
				Double prix = Double.parseDouble(elementPlat.getChildText("prix"));
				Choix choixDePlat = new Choix(id, categorieDePlat, valeurAttrCategorie, prix, description);
				listeDePlats.add(choixDePlat);
			}
		}

		Carte carte = Carte.getSingleInstance();
		carte.setListeDesPlats(listeDePlats);
	}

	/**
	 * Création d'une liste d'objet de type Choix des catégories d'accompagnement
	 */
	public void creationAccompagnements() {
		// Creation d'une liste d'accompagnements
		ArrayList<Choix> listeDaccompagnements = new ArrayList<>();
		// Liste du noeud accompagnements
		Element elementAccompagnements = racine.getChildren("accompagnements").get(0);
		// recuperation des noeuds enfants du noeud "accompagnements"
		List<Element> listeTypesAccompagnement = elementAccompagnements.getChildren();
		// Création d'un iterateur pour parcourir les enfants du noeud accompagnements
		Iterator<Element> it1 = listeTypesAccompagnement.iterator();

		// parcours du tableau listeTypesAccompagnement pour
		// récupérer les éléments catégories d'accompagnement
		while (it1.hasNext()) {
			Element elementCategorieAccomp = (Element) it1.next();
			String valeurAttrCategorie = elementCategorieAccomp.getAttributeValue("id");
			// récupération des noeuds enfants de catégorie

			List<Element> listeCategorieAccomp = elementCategorieAccomp.getChildren();
			Iterator<Element> it2 = listeCategorieAccomp.iterator();
			// parcours du tableau listeCategorieAccomp pour
			// récupérer les elements accompagnement
			while (it2.hasNext()) {
				Element elementAccomp = (Element) it2.next();
				String categorieAccomp = elementAccomp.getName();
				String id = elementAccomp.getAttributeValue("id");
				String description = elementAccomp.getChildText("type");
				Double prix = Double.parseDouble(elementAccomp.getChildText("prix"));
				Choix choixDaccompagnement = new Choix(id, categorieAccomp, valeurAttrCategorie, prix, description);
				listeDaccompagnements.add(choixDaccompagnement);
			}
		}
		Carte carte = Carte.getSingleInstance();
		carte.setListeDesAccompagnements(listeDaccompagnements);
	}

	/**
	 * Création d'une liste d'objet de type Choix des catégories de dessert
	 */
	public void creationDessert() {
		// Creation d'une liste de desserts
		ArrayList<Choix> listeDeDesserts = new ArrayList<>();
		// Liste du noeud Dessert
		Element elementDesserts = racine.getChildren("desserts").get(0);
		// recuperation des noeuds enfants du noeud "dessert"
		List<Element> listeTypesDessert = elementDesserts.getChildren();
		// Création d'un iterateur pour parcourir les enfants du noeud desserts
		Iterator<Element> it1 = listeTypesDessert.iterator();

		// parcours du tableau listeTypesDessert pour
		// récupérer les éléments catégories de dessert
		while (it1.hasNext()) {
			Element elementCategorieDessert = (Element) it1.next();
			String valeurAttrCategorie = elementCategorieDessert.getAttributeValue("id");
			// récupération des noeuds enfants de catégorie
			List<Element> listeCategorieDessert = elementCategorieDessert.getChildren();
			Iterator<Element> it2 = listeCategorieDessert.iterator();
			// parcours du tableau listeCategorieDessert pour
			// récupérer les elements dessert
			while (it2.hasNext()) {
				Element elementDess = (Element) it2.next();
				String categorieDessert = elementDess.getName();
				String id = elementDess.getAttributeValue("id");
				String description = elementDess.getChildText("type");
				Double prix = Double.parseDouble(elementDess.getChildText("prix"));
				Choix choixDeDessert = new Choix(id, categorieDessert, valeurAttrCategorie, prix, description);
				listeDeDesserts.add(choixDeDessert);
			}
		}
		Carte carte = Carte.getSingleInstance();
		carte.setListeDesDesserts(listeDeDesserts);
	}
}
