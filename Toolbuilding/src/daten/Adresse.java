package daten;

/**
 * Die Klasse Adresse beinhaltet alle Attribute die zu einer Adresse gehören.
 * Dazu die jeweiligen Get- und Set-Methoden und eine toString() Methode
 * @author Sarah, Jessica
 *
 */

public class Adresse 
{
	private int id;
	private String strasse;
	private String hausnr;
	private String plz;
	private String ort;
	private Geodaten geodaten;
	
	
	/**
	 * Konstruktor der Klasse Adresse
	 * @param strasse
	 * @param hausnr
	 * @param plz
	 * @param ort
	 */
	public Adresse(String strasse, String hausnr, String plz, String ort)
	{
		this.strasse=strasse;
		this.hausnr=hausnr;
		this.plz=plz;
		this.ort=ort;
	}
	
	
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}
	/**
	 * get-Methode von dem Attribut strasse
	 * @return strasse
	 */
	public String getStrasse() 
	{
		return strasse;
	}
	
	/**
	 * set-Methode von dem Attribut strasse
	 * @param strasse
	 */
	public void setStrasse(String strasse) 
	{
		this.strasse = strasse;
	}
	
	/**
	 * get-Methode von dem Attribut hausnr
	 * @return hausnr
	 */
	public String getHausnr() 
	{
		return hausnr;
	}
	
	/**
	 * set-Methode von dem Attribut hausnr
	 * @param hausnr
	 */
	public void setHausnr(String hausnr) 
	{
		this.hausnr = hausnr;
	}
	
	/**
	 * get-Methode von dem Attribute plz
	 * @return plz
	 */
	public String getPlz() 
	{
		return plz;
	}
	
	/**
	 * set-Methode von dem Attribute plz
	 * @param plz
	 */
	public void setPlz(String plz) 
	{
		this.plz = plz;
	}
	
	/**
	 * get-Methode von dem Attribute ort
	 * @return ort
	 */
	public String getOrt() 
	{
		return ort;
	}
	
	/**
	 * set-Methode von dem Attribut ort
	 * @param ort
	 */
	public void setOrt(String ort) 
	{
		this.ort = ort;
	}
	
	/**
	 * get-Methode von dem Attribut geodaten
	 * @return
	 */
	public Geodaten getGeodaten() 
	{
		return geodaten;
	}

	/**
	 * set-Methode von dem Attribut geodaten
	 * @param geodaten
	 */
	public void setGeodaten(Geodaten geodaten) 
	{
		this.geodaten = geodaten;
	}

	/**
	 * toString() Methode zum Ausgeben von den Attributen dieser Klasse
	 */
	public String toString()
	{
		return strasse + " " + hausnr + ", " + plz + " " + ort; 
	}

}
