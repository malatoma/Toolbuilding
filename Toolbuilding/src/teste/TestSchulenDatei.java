package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import daoimpl.SchulenDatei;
import daten.Adresse;
import daten.Kontaktdaten;
import daten.Schule;
import daten.Schulen;
import daten.Schulleitung;
import daten.Schultypen;

public class TestSchulenDatei {

	SchulenDatei sd;
	
	@Before
	public void setUp() throws Exception 
	{
		sd= new SchulenDatei();
	}

	/**
	 * testes ob schulen nicht null ist und ob die länge 218 beträgt, dazu wird noch testDetails aufgerufen die auch teste beinhaltet
	 */
	@Test
	public void test() 
	{
		Schulen schulen = null;
		try 
		{
			schulen = sd.einlesenSchulen();
		}
		catch (Exception  e) 
		{
			e.printStackTrace();
		}
		assertNotNull(schulen);
		assertTrue(schulen.size()==218);
		testDetails(schulen);
	}

	/**
	 * testen ob die alle Attribute ob sie nicht null sind 
	 * @param schulen
	 */
	private void testDetails(Schulen schulen) 
	{
		for (Schule schule: schulen) 
		{
			assertNotNull(schule.getSchulnr());
			assertNotNull(schule.getName());
			assertNotNull(schule.getZusatz());
			assertNotNull(schule.getAdress());
			testAdresse(schule.getAdress());
			assertNotNull(schule.getKontaktdaten());
			testKontaktdaten(schule.getKontaktdaten());
			assertNotNull(schule.getSchulleitung());
			textSchulleitung(schule.getSchulleitung());
			assertNotNull(schule.getSchultypen());
			testSchultypen(schule.getSchultypen());
		}
		
	}

	/**
	 * testet ob die Attribute von Adresse nicht null sind
	 * @param adress
	 */
	private void testAdresse(Adresse adress) 
	{
		assertNotNull(adress.getStrasse());
		assertNotNull(adress.getHausnr());
		assertNotNull(adress.getPlz());
		assertNotNull(adress.getOrt());
		//assertNotNull(adress.getGeodaten());
	}

	/**
	 * testet ob die Attribute von Schultypen nicht null sind
	 * @param schultypen
	 */
	private void testSchultypen(Schultypen schultypen) 
	{
		assertNotNull(schultypen.getSchulform());
		assertNotNull(schultypen.getGanztagsschule());
		assertNotNull(schultypen.getGanztagsform());
	}

	/**
	 * testet ob die Attribute von Schulleitung nicht null sind
	 * @param schulleitung
	 */
	private void textSchulleitung(Schulleitung schulleitung) 
	{
		assertNotNull(schulleitung.getTitel());
		assertNotNull(schulleitung.getVname());
		assertNotNull(schulleitung.getNname());
	}

	/**
	 * testet ob die Attribute von Kontaktdaten nicht null sind
	 * @param kontaktdaten
	 */
	private void testKontaktdaten(Kontaktdaten kontaktdaten) 
	{
		assertNotNull(kontaktdaten.getVorwahl());
		assertNotNull(kontaktdaten.getTelefonnr());
		assertNotNull(kontaktdaten.getFax());
		assertNotNull(kontaktdaten.getEmail());
		assertNotNull(kontaktdaten.getInternet());
		
	}

}
