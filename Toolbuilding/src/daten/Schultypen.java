package daten;
/**
 * Die Klasse Schultypen enthält alle Attribute die zu den Schultypen wissenwert sind
 * Dazu die jeweiligen get- und set-Methoden und die toString() Methode
 * @author Sarah,Jessica
 *
 */
public class Schultypen 
{
	private String schulform;
	private String ganztagsschule;
	private String ganztagsform;
	
	
	/**
	 * Konstruktor der Klasse Schultypen
	 * @param schulform
	 * @param ganztagsschule
	 * @param ganztagsform
	 */
	public Schultypen(String schulform, String ganztagsschule, String ganztagsform)
	{
		this.schulform = schulform;
		this.ganztagsschule = ganztagsschule;
		this.ganztagsform = ganztagsform;
	}
	
	
	/**
	 * get-Methode von dem Attribut schulart
	 * @return schulart
	 */
	public String getSchulform() 
	{
		return schulform;
	}
	
	/**
	 * set-Methode von dem Attribut schulart
	 * @param schulart
	 */
	public void setSchulform(String schulform) 
	{
		this.schulform = schulform;
	}
	
	
	/**
	 * set-Methode von dem Attribute ganztagsschule
	 * @param ganztagsschule
	 */
	public void setGanztagsschule(String ganztagsschule) 
	{
		this.ganztagsschule = ganztagsschule;
	}
	
	/**
	 * get-Methode vom Attribut ganztagsschule
	 * @return ganztagsschule
	 */
	public String getGanztagsschule()
	{
		return ganztagsschule;
	}
	

	/**
	 * set-Methode von dem Attribut ganztagsform
	 * @param ganztagsform
	 */
	public void setGanztagsform(String ganztagsform) 
	{
		this.ganztagsform = ganztagsform;
	}
	
	/**
	 * get-Methode vom Attribut ganztagsform
	 * @return ganztagsform
	 */
	public String getGanztagsform()
	{
		return ganztagsform;
	}
	
	/**
	 * toString() Methode zum Ausgeben von den Attributen dieser Klasse
	 */
	public String toString()
	{
		return ganztagsform; 
	}


	
}
