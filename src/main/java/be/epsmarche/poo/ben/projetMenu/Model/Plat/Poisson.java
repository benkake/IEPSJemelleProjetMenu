package be.epsmarche.poo.ben.projetMenu.Model.Plat;

public class Poisson implements Iplat {

	protected Double prix = 11.5;
	protected String type = "Steak de thon rouge";

	public Poisson() {
		super();
	}

	public Poisson(String type) {
		super();
		this.type = getType();
	}

	@Override
	public Double getPrix() {
		return this.prix;
	}

	@Override
	public void setPrix(Double prix) {
		this.prix = prix;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getPeparation() {
		return getType() + " mariné à la farine de blé";
	}

	@Override
	public String toString() {
		return "Poisson [prix=" + prix + ", type=" + type + "]";
	}

}
