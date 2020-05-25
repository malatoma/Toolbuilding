package teste;

import static org.junit.Assert.assertTrue;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;

import daoimpl.DBImpl;
import daten.Schulen;
import datenbank.DBKommu;

public class Schulen_Gebiet_Test 
{
	private String host;
	private String port;
	private String dbName;
	private String user;
	private String password;
	
	private DBKommu dbk;
	private DBImpl dbi;

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
	}

	/**
	 * gucken ob schulen nicht null ist nachdem man die Methode getSchulenImGebiet aufruft
	 */
	@Test
	public void test() 
	{
		Schulen schulen = dbi.getSchulenImGebiet(3, "grundschule");
		
		
		assertTrue(schulen != null);
	}

}
