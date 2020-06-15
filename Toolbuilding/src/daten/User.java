package daten;

public class User 
{
	private String vorname;
	private String nachname;
	private String username;
	private String passwort;
	private String geburtstag;
	private String strasse;
	private int hausnr;
	private String plz;
	private String ort;
	
	
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
	

	public String getPasswort() 
	{
		return passwort;
	}

	public void setPasswort(String passwort) 
	{
		this.passwort = passwort;
	}

	public String getStrasse() 
	{
		return strasse;
	}

	public void setStrasse(String strasse) 
	{
		this.strasse = strasse;
	}

	public int getHausnr() 
	{
		return hausnr;
	}

	public void setHausnr(int hausnr) 
	{
		this.hausnr = hausnr;
	}

	public String getPlz() 
	{
		return plz;
	}

	public void setPlz(String plz) 
	{
		this.plz = plz;
	}

	public String getOrt() 
	{
		return ort;
	}

	public void setOrt(String ort) 
	{
		this.ort = ort;
	}

}
