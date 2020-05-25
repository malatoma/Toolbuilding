package daten;

/**
 * Die Klasse Kontaktdaten enthält alle Attribute die man brauch um Kontakt aufzubauen.
 * Dazu die jeweiligen get- und set-Methoden und die toString() Methode
 * @author Sarah,Jessica
 *
 */
public class Kontaktdaten 
{
	private String vorwahl;
	private String telefonnr;
	private String fax;
	private String email;
	private String internet;
	
	/**
	 * Konstruktor der Klasse Kontaktdaten.
	 * Er bekommt alle Attribute übergeben und setzt diese.
	 * @param vorwahl
	 * @param telefonnr
	 * @param fax
	 * @param email
	 */
	public Kontaktdaten(String vorwahl, String telefonnr, String fax, String email, String internet)
	{
		this.vorwahl = vorwahl;
		this.telefonnr = telefonnr;
		this.fax = fax;
		this.email = email;
		this.internet = internet;
		
	}

	
	/**
	 * get-Methode von dem Attribute telefonnr
	 * @return telefonnr
	 */
	public String getTelefonnr() 
	{
		return telefonnr;
	}
	
	/**
	 * set-Methode von dem Attribute telefonnr
	 * @param telefonnr
	 */
	public void setTelefonnr(String telefonnr) 
	{
		this.telefonnr = telefonnr;
	}
	
	/**
	 * get-Methode von dem Attribute vorwahl
	 * @return vorwahl
	 */
	public String getVorwahl() 
	{
		return vorwahl;
	}
	
	/**
	 * set-Methode von dem Attribute vorwahl
	 * Wenn der Ort Bremen ist soll die Vowahl 0421 sein sonst ist sie 0471
	 * @return vorwahl ="0421" oder vowahl= "0471"
	 */
	public void setVorwahl(String vorwahl) 
	{
		this.vorwahl = vorwahl;
	}
	
	/**
	 * get-Methode von dem Attribute fax
	 * @return fax
	 */
	public String getFax() 
	{
		return fax;
	}
	/**
	 * set-Methode von dem Attribute fax
	 * @param fax
	 */
	public void setFax(String fax) 
	{
		this.fax = fax;
	}
	
	/**
	 * get-Methode von dem Attribute email
	 * @return email
	 */
	public String getEmail() 
	{
		return email;
	}
	
	/**
	 * set-Methode von dem Attribute email
	 * @param email
	 */
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	/**
	 * get-Methode von dem Attribut internet
	 * @return internet
	 */
	public String getInternet() 
	{
		return internet;
	}
	
	/**
	 * set-Methode von dem Attribut internet
	 * @param internet
	 */
	public void setInternet(String internet) 
	{
		this.internet = internet;
	}
	
	/**
	 * toString() Methode zum Ausgeben von den Attributen dieser Klasse
	 */
	public String toString()
	{
		return "Vorwahl: " +vorwahl+"\n"+ "Telefonnummer: " +telefonnr+"\n"+ "Fax: " +fax+"\n"+ "Email " +email+"\n"+ "Internet: " +internet; 
	}

	
	
}
