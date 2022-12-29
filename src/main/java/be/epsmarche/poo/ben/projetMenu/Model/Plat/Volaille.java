package be.epsmarche.poo.ben.projetMenu.Model.Plat;

public class Volaille implements Iplat {

	protected Double prix = 12.5;
	protected String type = "Steak de dindon";

	public Volaille() {
		super();
	}

	public Volaille(String type) {
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
		return getType() + " mariné à la moutarde de Dijon";
	}

}
