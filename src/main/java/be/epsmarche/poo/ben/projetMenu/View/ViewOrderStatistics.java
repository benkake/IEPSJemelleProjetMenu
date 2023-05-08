package be.epsmarche.poo.ben.projetMenu.View;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;

import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Classe permettant de générer des graphiques. Afin de la rendre modale elle
 * hériter la classe JDialog
 */
public class ViewOrderStatistics extends JDialog {
	MenuController contr = new MenuController();

	public ViewOrderStatistics(JFrame parent, Boolean modal) {
		super(parent, modal);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JFreeChart chart1 = createChart1("Statistique des commandes ", "Nombre de commandes par jour");
		ChartPanel chartPanel1 = new ChartPanel(chart1);
		chartPanel1.setPreferredSize(new java.awt.Dimension(700, 320));
		panel.add(chartPanel1);

		JFreeChart chart2 = createChart2("Statistique des commandes ", "Nombre de commandes par mois");
		ChartPanel chartPanel2 = new ChartPanel(chart2);
		chartPanel2.setPreferredSize(new java.awt.Dimension(700, 320));
		panel.add(chartPanel2);

//****new
		 JScrollPane scrollPane = new JScrollPane(panel);
		 scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // Définir la politique de défilement horizontal
		 getContentPane().add(scrollPane);

		//getContentPane().add(panel);
		setTitle("Etat des commandes");
		setModal(true);
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);

	}

	private JFreeChart createChart1(String appTitle, String chartTitle) {
		JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Jour", "Quantité", createDataset1(),
				PlotOrientation.VERTICAL, true, true, false);
		return barChart;
	}

	private JFreeChart createChart2(String appTitle, String chartTitle) {
		JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Mois", "Quantité", createDataset2(),
				PlotOrientation.VERTICAL, true, true, false);
		return barChart;
	}

	private CategoryDataset createDataset1() {
		final String nombreDeCommandes = "Nombre de Commandes par jour"; // returne le total des commandes en un mois
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Map<Date, Integer> daylyCommandeMap = contr.getCommandeMapping();
		if (daylyCommandeMap != null && !daylyCommandeMap.isEmpty()) {
			for (Entry<Date, Integer> entry : daylyCommandeMap.entrySet()) {
				Date dateCommande = entry.getKey();
				Integer nbreCommandes = entry.getValue();
				dataset.addValue(nbreCommandes, nombreDeCommandes, dateCommande);
			}
		}
		return dataset;
	}

	private CategoryDataset createDataset2() {
		final String nombreDeCommandes = "Nombre de Commandes par mois"; // returne le total des commandes en un mois
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Map<Date, Integer> mountlyCommandeMap = contr.getCommandeMapping();
		if (mountlyCommandeMap != null && !mountlyCommandeMap.isEmpty()) {
			for (Entry<Date, Integer> entry : mountlyCommandeMap.entrySet()) {
				Date dateCommande = entry.getKey();
				Integer nbreCommandes = entry.getValue();
				// formatage de date pour retourner le mois
				SimpleDateFormat sdf = new SimpleDateFormat("MM");
				String mois = sdf.format(dateCommande);
				dataset.addValue(nbreCommandes, nombreDeCommandes, mois);
			}
		}
		return dataset;
	}
}