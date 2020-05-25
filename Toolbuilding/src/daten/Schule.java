package daten;

/**
 * Die Klasse Schule enthält alle Attribute die für eine Schule wissenswert sind
 * Dazu die jeweiligen get- und set-Methoden und die toString() Methode
 * @author Sarah,Jessica
 *
 */
public class Schule 
{
	private String schulnr;
	private String name;
	private String zusatz;
	private Adresse adress;
	private Schulleitung schulleitung;
	private Kontaktdaten kontaktdaten;
	private Schultypen schultypen;
	
	/**
	 * leerer Konstruktor der Klasse Schule
	 */
	public Schule()
	{
		
	}
	
	/**
	 * Konstruktor der Klasse Schule.
	 * Er bekommt alle Attribute übergeben und setzt diese.
	 * @param schulnr
	 * @param name
	 * @param zusatz
	 */
	public Schule(String schulnr,String name, String zusatz)
	{
		this.schulnr = schulnr;
		this.name = name;
		this.zusatz = zusatz;
	}
	
	/**
	 * get-Methode von dem Attribut schulnr
	 * @return schulnr
	 */
	public String getSchulnr() 
	{
		return schulnr;
	}

	/**
	 * set-Methode von dem Attribut schulnr
	 * @param schulnr
	 */
	public void setSchulnr(String schulnr) 
	{
		this.schulnr = schulnr;
	}
	
	/**
	 * get-Methode von dem Attribut name
	 * @return name
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * set-Methode von dem Attribut name
	 * @param name
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * get-Methode von dem Attribut zusatz
	 * @return zusatz
	 */
	public String getZusatz() 
	{
		return zusatz;
	}
	
	/**
	 * set-Methode von dem Attribute zusatz
	 * @param zusatz
	 */
	public void setZusatz(String zusatz) 
	{
		this.zusatz = zusatz;
	}
	
	/**
	 * get-Methode von dem Attribute adress
	 * @return adress
	 */
	public Adresse getAdress() 
	{
		return adress;
	}
	
	/**
	 * set-Methode von dem Attribute adress
	 * @param adress
	 */
	public void setAdress(Adresse adress) 
	{
		this.adress = adress;
	}
	
	/**
	 * get-Methode von dem Attribute schulleitung
	 * @return schulleitung
	 */
	public Schulleitung getSchulleitung() 
	{
		return schulleitung;
	}
	
	/**
	 * set-Methode von dem Attribute schulleitung
	 * @param schulleitung
	 */
	public void setSchulleitung(Schulleitung schulleitung) 
	{
		this.schulleitung = schulleitung;
	}
	
	/**
	 * get-Methode von dem Attribute kontaktdaten
	 * @return kontaktdaten
	 */
	public Kontaktdaten getKontaktdaten() 
	{
		return kontaktdaten;
	}
	
	/**
	 * set-Methode von dem Attribute kontaktdaten
	 * @param kontaktdaten
	 */
	public void setKontaktdaten(Kontaktdaten kontaktdaten) 
	{
		this.kontaktdaten = kontaktdaten;
	}
	
	/**
	 * get-Methode vom Attribut schultypen
	 * @return schultypen
	 */
	public Schultypen getSchultypen() 
	{
		return schultypen;
	}

	/**
	 * set-Methode vom Attribut schultypen
	 * @param schultypen
	 */
	public void setSchultypen(Schultypen schultypen) 
	{
		this.schultypen = schultypen;
	}

	/**
	 * toString() Methode zum Ausgeben von den Attributen dieser Klasse
	 */
	public String toString()
	{
		return "Schulnummer: "+schulnr+ "\n"+"Name: " +name+ "\n"+ "Zusatz: " +zusatz+ "\n" +adress+ "\n"+schulleitung+"\n"+ kontaktdaten +"\n"+ schultypen; 
	}
	
}
