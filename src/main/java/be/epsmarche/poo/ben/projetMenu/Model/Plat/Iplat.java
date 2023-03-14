package be.epsmarche.poo.ben.projetMenu.Model.Plat;

/**
 * Interface définissant le plat
 * 
 * @author ben
 */
public interface Iplat {
	public String getPeparation();

	public void setPrix(Double prix);

	public Double getPrix();

	public void setType(String type);

	public String getType();
}
