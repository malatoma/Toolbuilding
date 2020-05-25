package daten;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


/**
 * Klasse um die Geodaten zu verwalten
 * @author Sarah, Jessica
 *
 */
public class Geodaten 
{
	private Adresse adresse; 
	
	private double lat = 0;
	private double lon = 0;
	
	/**
	 * Mehrere Konstruktor dieser Klasse 
	 */
	
	public Geodaten() 
	{
		super(); 
		System.out.println("Geodaten-Objekt erzeugt");
	}
	
	
	public Geodaten(Adresse adresse)
	{
		this(); 
		this.adresse = adresse;
	}
	
	
	public Geodaten(String strasse, String hausNr, String plz, String ort)
	{
		adresse = new Adresse(strasse, hausNr, plz, ort); 
	}
	
	
	public Geodaten(double lon, double lat)
	{
		this.lon = lon;
		this.lat = lat;
	}

	
	public Adresse getAdresse() 
	{
		return adresse;
	}
	
	public void setAdresse(Adresse adresse) 
	{
		this.adresse = adresse;
	}
	
	public double getLat() 
	{
		return lat;
	}
	
	public double getLon() 
	{
		return lon;
	}
	
	public void setLat(double lat) 
	{
		this.lat = lat;
	}

	public void setLon(double lon) 
	{
		this.lon = lon;
	}

	/**
	 * Methode um die Geodaten zu ermitteln
	 */
	public void geodatenErmitteln()
	{
		try 
		{
			String urlParameter = "q=Germany%2C" + adresse.getPlz() +"%2C"+
					URLEncoder.encode(adresse.getOrt(),"UTF-8") +"%2C"+
					URLEncoder.encode(adresse.getStrasse(),"UTF-8") + "%2C" +
					URLEncoder.encode(adresse.getHausnr(),"UTF-8") +
					"&polygon_geojson=1&format=json";
			String urlNominatim = "https://nominatim.openstreetmap.org/search.php?";
			String request = urlNominatim + urlParameter;
			URL url = new URL(request);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			
			// Ergebnisse empfangen
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			Gson gson = new Gson();
			JsonArray js = gson.fromJson(bufferedReader, JsonArray.class);
			
			// Ergebnisse filtern
			JsonObject jo = (JsonObject) js.get(0);
			
			lon = jo.get("lon").getAsDouble();
			lat = jo.get("lat").getAsDouble();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * gibt einen String zurück
	 */
	public String toString() 
	{
		return "Geodaten [adresse = " + adresse.getOrt() + ", " + adresse.getStrasse()  + " " + adresse.getHausnr() + 
				", lat = " + lat + ", lon = " + lon + "]";
	}
}
