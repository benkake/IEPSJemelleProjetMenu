package be.epsmarche.poo.ben.projetMenu.Model.Plat;

public class Viande implements Iplat {

	protected Double prix = 10.5;
	protected String type = "Steak de 200g de crocodile";

	public Viande() {
		super();
	}

	public Viande(String type) {
		super();
		this.type = getType();
	}
	
	

	@Override
	public String getPeparation() {
		return getType() + " mariné au piment africain";
	}

	@Override
	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Double getPrix() {
		return this.prix;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "Viande [prix=" + getPrix() + ", type=" + getType() + "]";
	}

}
