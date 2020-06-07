package daten;

public class User 
{
	private String vorname;
	private String nachname;
	private String username;
	private String passwort;
	private String geburtstag;
	private Adresse adresse;
	
	
	public String getVorname() 
	{
		return vorname;
	}
	
	public void setVorname(String vorname) 
	{
		this.vorname = vorname;
	}
	
	public String getNachname() 
	{
		return nachname;
	}
	
	public void setNachname(String nachname) 
	{
		this.nachname = nachname;
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getGeburtstag() 
	{
		return geburtstag;
	}
	
	public void setGeburtstag(String geburtstag) 
	{
		this.geburtstag = geburtstag;
	}
	
	public Adresse getAdresse() 
	{
		return adresse;
	}
	
	public void setAdresse(Adresse adresse) 
	{
		this.adresse = adresse;
	}

	public String getPasswort() 
	{
		return passwort;
	}

	public void setPasswort(String passwort) 
	{
		this.passwort = passwort;
	}

}
