package service;

import java.util.Set;
import java.util.logging.Level; 
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.GET; 
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.Application; 
import javax.ws.rs.core.GenericEntity; 
import javax.ws.rs.core.Response; 
import daoimpl.DBImpl; 
import datenbank.DBKommu;

/**
 * Klasse RestService hilft dabei, dass die Methoden von den java-Klassen in der Web-Anwendung funktionieren
 * @author Sarah, Jessica
 *
 */
@Path("schulen") 
public class RestService extends Application
{
	
	private static final Logger log = Logger.getLogger(RestService.class.getName()); 
	
	private String host;
	private String port;
	private String dbName;
	private String user;
	private String password;
	
	private DBKommu dbk;
	private DBImpl dbi; 
	
	public RestService() 
	{ 
		log.log(Level.FINER,"RESTService erzeugt"); 
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
	 * Beispiel
	 */
/*	@Path("alle") 
	@GET 
	@Produces("application/json")
	public Response alle() 
	{ 
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getAlleSchulen()) {}; 
		return Response.status(200).entity(myEntity).build(); 
	}
*/
}
