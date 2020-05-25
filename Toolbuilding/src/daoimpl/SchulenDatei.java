package daoimpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import daten.Adresse;
import daten.Kontaktdaten;
import daten.Schule;
import daten.Schulen;
import daten.Schulleitung;
import daten.Schultypen;
import interfaces.SchulenSuche;

/**
 * Die Klasse SchulenDatei implemententiert die Klasse SchulenSuche.
 * In dieser Klasse sollen die Daten eingelesen und ausgegeben werden.
 * @author Sarah, Jessica
 *
 */
public class SchulenDatei implements SchulenSuche
{
	
	private Schulen schulen = null;
	private BufferedReader reader;
	
	/**
	 * Die Methode einlesenSchulen soll die Daten aus einer Excel Datei lesen
	 * und gibt zum Schluss eine ArrayList von Schulen zurück
	 * @return schule
	 * @throws IOException
	 */
	public Schulen einlesenSchulen() throws IOException
	{
		
		Schule schule = null;
		try 
		{
			reader = new BufferedReader(new FileReader("Quellen/BremenSchule.csv"));
			schulen = new Schulen();
			reader.readLine();
			do
			{
				schule = leseSchule();
				if(schule != null)
				{
					schulen.add(schule);
					
				}
				
			}
			while(schule!=null);
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Datei konnte nicht gefunden werden");
			e.printStackTrace();
		}
		reader.close();
		return schulen;
	}
	
	/**
	 * Die Methode leseSchule liest alles von der Excel Datei zeilenweise aus und setzt bestimmte Attribute
	 * @return schule
	 */
	public Schule leseSchule()
	{
		Schule schule = null;
		String text = null;
		
		try 
		{
			text= reader.readLine();
			
			if(text != null)
			{
				
				schule = new Schule();
				String[] texte = text.split(";");
				schule.setSchulnr(texte[0]); 
				schule.setName(texte[1]);
				schule.setZusatz(texte[2]);
				schule.setAdress(new Adresse(texte[3],texte[4],texte[5],texte[6]));
				schule.setSchulleitung(new Schulleitung(texte[7],texte[8],texte[9]));
				if(texte.length+1 > 29)
				{
					schule.setKontaktdaten(new Kontaktdaten(texte[10],texte[11],texte[12],texte[13], texte[28]));
				} 
				else
				{
					schule.setKontaktdaten(new Kontaktdaten(texte[10],texte[11],texte[12],texte[13], ""));
				}
				
				schule.setSchultypen(new Schultypen(texte[27],texte[14],texte[15]));
			}
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return schule;
		
	}
	
	/**
	 * Die Methode getAlleSchule() gibt alle Schulen wieder 
	 * @return schulen
	 */
	public Schulen getAlleSchulen() 
	{
		return schulen;
	}

	public Schulen getGrundschulen() 
	{
		return null;
	}

	public Schulen getPrivatschulen() 
	{
		return null;
	}

	
	public Schulen getOberschulen() 
	{
		
		return null;
	}

	public Schulen getFoerderzentrum() 
	{
		
		return null;
	}

	public Schulen getAbendErwachsenenSchule() 
	{
		return null;
	}

	public Schulen getDurchgaengigesGymnasium() 
	{
		return null;
	}

	
	public Schulen getSchulzentrum() 
	{
		return null;
	}

	
	public Schulen getBerufsbildeneSchule() 
	{
		return null;
	}

	
	public Schulen getGesundheitsschule()
	{
		return null;
	}

	
	public Schulen getVerwaltungsschule() 
	{
		return null;
	}
	
	

}
