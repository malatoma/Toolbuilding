package daoimpl;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import daten.Schulen;
import datenbank.DBKommu;

/**
 * Klasse um alle Daten in die Datenbank zu importieren
 * @author Sarah, Jessica
 *
 */
public class DBImport 
{
	private SchulenDatei sd;

	private String host;
	private String port;
	private String dbName;
	private String user;
	private String password;
	
	private DBKommu dbk;
	private DBImpl dbi;

	/**
	 * Methode um die Daten einzulesen und in die DB zu importieren
	 */
	public void lesenImportieren()
	{
		sd = new SchulenDatei();
		try 
		{
			sd.einlesenSchulen();
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		Schulen schulen = sd.getAlleSchulen();
		
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
		dbi.createTable();
		for(int i= 0; i<schulen.size();i++)
		{
			dbi.insertTableSchule(schulen.get(i));
		}
		dbi.verbindungSchließenDB();
	}
	 
}
