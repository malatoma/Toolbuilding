package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import daoimpl.SchulenDatei;
import daten.Schulen;

public class TestSchultyp {
	private SchulenDatei sd;
	@Before
	public void setUp() throws Exception {
		sd= new SchulenDatei();
	}

	/**
	 *
	 * testes ob schulen nicht null ist und ob die länge 218 beträgt, dazu wird noch testGnaztagsschule aufgerufen
	 */
	@Test
	public void test() {
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
		testGanztagsschule(schulen);

	}

	/**
	 * testet ob Ganztagsschule nicht null ist und am ende gibt er uns anzahl, wert und index aus
	 * @param schulen
	 */
	private void testGanztagsschule(Schulen schulen) {
		int index = 0; 
		int wert = 0; 
		int anzahl = 0; 
		for (int i = 0; i < schulen.size(); i++) {
			if (schulen.get(i).getSchultypen() != null) {
				if (schulen.get(i).getSchultypen().getGanztagsschule()!=null) {
					anzahl++; 
					if (schulen.get(i).getSchultypen().getGanztagsschule().length() > wert) {
						index = i; 
						wert = schulen.get(i).getSchultypen().getGanztagsschule().length();					
					}
				}
			}			
		}
		System.out.println("Anzahl der Ganztagsschulen: " + anzahl + 
							" Längste Angabe: " + wert + " Index der längsten Angabe: " + index);		
	}

}
