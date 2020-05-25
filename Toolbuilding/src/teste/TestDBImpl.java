package teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;

import daoimpl.DBImpl;
import daten.Schule;
import datenbank.DBKommu;

public class TestDBImpl 
{
	private String host;
	private String port;
	private String dbName;
	private String user;
	private String password;
	
	private DBImpl dbi;
	private DBKommu dbk;
	private Schule schule;
	
	@Before
	public void setUp() throws Exception
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext exContext = context.getExternalContext();
		
		host = exContext.getInitParameter("host");
		port = exContext.getInitParameter("port");
		dbName = exContext.getInitParameter("dbname");
		user = exContext.getInitParameter("benutzername");
		password = exContext.getInitParameter("passwort");
		
		dbk = new DBKommu();
		dbk.setHost(host);
		dbk.setPort(port);
		dbk.setDbName(dbName);
		dbk.setUser(user);
		dbk.setPassword(password);
		
		dbi = new DBImpl(dbk.getConnection());
		schule = new Schule();		
	}
	
	/**
	 * testet die DB Connection
	 */
	@Test
	public void testenConnection()
	{
		assertNotNull(dbi.getConnection());
	}
	
	/**
	 * testes ob Schulleitung nicht null ist
	 */
	@Test
	public void testenSchulleitung()
	{
		int ergebnis = dbi.sucheSchulleitungsID(schule);
		assertTrue(ergebnis == -1);
		assertNotNull(dbi.sucheSchulleitung(schule));
	}
	
	/**
	 * testes ob Schultypen nicht null ist
	 */
	@Test
	public void testenSchultypen()
	{
		int ergebnis = dbi.sucheSchultypenID(schule);
		assertTrue(ergebnis == -1);
		assertNotNull(dbi.sucheSchultypen(schule));		
	}
	
	/**
	 * testes ob Kontaktdaten nicht null ist
	 */
	@Test
	public void testenKontaktdaten()
	{
		int ergebnis = dbi.sucheKontaktdatenID(schule);
		assertTrue(ergebnis == -1);
		assertNotNull(dbi.sucheKontaktdaten(schule));		
	}
	
	
}
