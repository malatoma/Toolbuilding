package teste;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import daoimpl.DBImpl;
import daten.Gebiete;
import datenbank.DBKommu;

class TestGebieteDB 
{
	DBImpl dbi;
	DBKommu dbk;
	
	String host;
	String port;
	String dbName;
	String user;
	String password;
	
	Gebiete gebiete;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		host = "localhost";
		port = "5432";
		dbName = "Schulenverzeichnis";
		user = "postgres";
		password = "dbjessica2018";
		
		dbk = new DBKommu();
		dbk.setHost(host);
		dbk.setPort(port);
		dbk.setDbName(dbName);
		dbk.setUser(user);
		dbk.setPassword(password);
		
		dbi = new DBImpl(dbk.getConnection());
		
		gebiete = new Gebiete();
	}

	@Test
	void testGebiete() 
	{
		gebiete = dbi.suchenGebiete();
		
		assertTrue(gebiete.size() != 0);
		
		
		for(int i = 0; i < gebiete.size(); i++)
		{
			assertTrue(gebiete.get(i).getId() != 0);
			//System.out.println("id " + i + ": " + gebiete.get(i).getId());
			assertTrue(gebiete.get(i).getName() != null);
			//System.out.println("name " + i + ": " + gebiete.get(i).getName());
		}
		
	}

}
