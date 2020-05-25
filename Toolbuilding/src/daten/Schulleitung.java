package daten;

/**
 * Die Klasse Schulleitung enthält alle Attribute die zu einer Schulleitung zu wissen sind
 * Dazu die jeweiligen get- und set-Methoden und die toString() Methode
 * @author Sarah,Jessica
 *
 */
public class Schulleitung 
{
	private String titel;
	private String vname;
	private String nname;
	
	
	
	/**
	 * Konstruktor der Klasse Schulleitung
	 * @param titel
	 * @param vname
	 * @param nname
	 */
	public Schulleitung(String titel, String vname, String nname)
	{
		
		this.titel=titel;
		this.vname=vname;
		this.nname=nname;
	}
	
	
	/**
	 * get-Methode von dem Attribut titel
	 * @return titel
	 */
	public String getTitel() 
	{
		return titel;
	}
	
	/**
	 * set-Methode von dem Attribut titel
	 * @param titel
	 */
	public void setTitel(String titel) 
	{
		this.titel = titel;
	}
	
	/**
	 * get-Methode von dem Attribut vname
	 * @return vname
	 */
	public String getVname() 
	{
		return vname;
	}
	
	/**
	 * set-Methode von dem Attribut vname
	 * @param vname
	 */
	public void setVname(String vname) 
	{
		this.vname = vname;
	}
	
	/**
	 * get-Methode von dem Attribut nname
	 * @return nname
	 */
	public String getNname() 
	{
		return nname;
	}
	
	/**
	 * set-Methode von dem Attribut nname
	 * @param nname
	 */
	public void setNname(String nname) 
	{
		this.nname = nname;
	}
	
	/**
	 * toString() Methode zum Ausgeben von den Attributen dieser Klasse
	 */
	public String toString()
	{
		return titel + " " + vname + " " + nname; 
	}

	
	
}
