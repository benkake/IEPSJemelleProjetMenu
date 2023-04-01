package be.epsmarche.poo.ben.projetMenu.View;


import be.epsmarche.poo.ben.projetMenu.Controller.MenuController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Map.Entry;


/**
 * Classe permettant de générer des graphiques.
 * Afin de la rendre modale elle hériter la classe JDialog
 */
public class ViewGainStatistics extends JDialog {
	MenuController contr = new MenuController();

	public ViewGainStatistics(JFrame parent, Boolean modal) {
		super(parent, modal);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JFreeChart chart1 = createChart1("Statistique des gains ", "Gain journalier");
		ChartPanel chartPanel1 = new ChartPanel(chart1);
		chartPanel1.setPreferredSize(new java.awt.Dimension(700, 320)); // ToDo: à revoir
		panel.add(chartPanel1);

		JFreeChart chart2 = createChart2("Statistique des gains ", "gain mensuel");
		ChartPanel chartPanel2 = new ChartPanel(chart2);
		chartPanel2.setPreferredSize(new java.awt.Dimension(700, 320)); // ToDo: à revoir
		panel.add(chartPanel2);

		getContentPane().add(panel);
		setTitle("Etat des gains");
		setModal(true);
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);

	}
	private JFreeChart createChart1(String appTitle, String chartTitle) {
		JFreeChart barChart = ChartFactory.createBarChart(
				chartTitle, "Gain", "Montant",
				createDataset1(), PlotOrientation.VERTICAL,
				true, true, false);
		return barChart;
	}
	private JFreeChart createChart2(String appTitle, String chartTitle) {
		JFreeChart barChart = ChartFactory.createBarChart(
				chartTitle, "Gain", "Montant",
				createDataset2(), PlotOrientation.VERTICAL,
				true, true, false);
		return barChart;
	}


	private CategoryDataset createDataset1() {
		final String totalDesGain = "Total des gains jounaliers";  // returne le total des commandes en un mois
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Map<Date, Double> daylyGainMap = contr.getGainMapping();
		if (daylyGainMap != null && !daylyGainMap.isEmpty()) {
			for (Entry<Date, Double> entry : daylyGainMap.entrySet()) {
				Date dateDesgains = entry.getKey();
				Double volumeGain = entry.getValue();
				dataset.addValue(volumeGain, totalDesGain, dateDesgains);
			}
		}
		return dataset;
	}

	private CategoryDataset createDataset2() {
		final String totalDesGain = "Total des gains mensuels";  // returne le total des commandes en un mois
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<Date, Double> mountlyGainMap = contr.getGainMapping();
		if (mountlyGainMap != null && ! mountlyGainMap.isEmpty()) {
			for (Entry<Date, Double> entry : mountlyGainMap.entrySet()) {
				Date dateGain = entry.getKey();
				Double volumeGain = entry.getValue();
				//formatage de date pour retourner le mois
				SimpleDateFormat sdf = new SimpleDateFormat("MM");
				String mois = sdf.format(dateGain);
				dataset.addValue(volumeGain, totalDesGain , mois);
			}
		}
		return dataset;
	}
}




