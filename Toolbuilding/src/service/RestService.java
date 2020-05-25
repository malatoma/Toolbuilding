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
import daten.Schulen;
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
	 * um alle Schulen anzeigen zu lassen
	 * @return
	 */
	@Path("alle") 
	@GET 
	@Produces("application/json")
	public Response alle() 
	{ 
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getAlleSchulen()) {}; 
		return Response.status(200).entity(myEntity).build(); 
	}
	
	/**
	 * um alle Grundschulen anzeigen zu lassen
	 * @return
	 */
	@Path("grundschule")
	@GET
	@Produces("application/json")
	public Response grundschulen() 
	{
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getGrundschulen()) {};
		return Response.status(200).entity(myEntity).build();
	}
	
	/**
	 * um alle Privatschulen anzeigen zu lassen
	 * @return
	 */
	@Path("privatschule")
	@GET
	@Produces("application/json")
	public Response privatschulen() 
	{
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getPrivatschulen()) {};
		return Response.status(200).entity(myEntity).build();
	}

	/**
	 * um alle Oberschulen anzeigen zu lassen
	 * @return
	 */
	@Path("oberschule")
	@GET
	@Produces("application/json")
	public Response oberschulen() 
	{
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getOberschulen()) {};
		return Response.status(200).entity(myEntity).build();
	}

	/**
	 * um alle Abendschulen anzeigen zu lassen
	 * @return
	 */
	@Path("abendschule")
	@GET
	@Produces("application/json")
	public Response abendschule() 
	{
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getAbendErwachsenenSchule()) {};
		return Response.status(200).entity(myEntity).build();
	}

	/**
	 * um alle Berufsschulen anzeigen zu lassen
	 * @return
	 */
	@Path("berufsschule")
	@GET
	@Produces("application/json")
	public Response berufsschule() 
	{
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getBerufsbildeneSchule()) {};
		return Response.status(200).entity(myEntity).build();
	}

	/**
	 * um alle durchgängigen Gymnasien anzeigen zu lassen
	 * @return
	 */
	@Path("durchgaengigesGym")
	@GET
	@Produces("application/json")
	public Response durchgaengigesGym() 
	{
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getDurchgaengigesGymnasium()) {};
		return Response.status(200).entity(myEntity).build();
	}

	/**
	 * um alle Förderzentren anzeigen zu lassen
	 * @return
	 */
	@Path("foerderzentrum")
	@GET
	@Produces("application/json")
	public Response foerderzentrum() 
	{
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getFoerderzentrum()) {};
		return Response.status(200).entity(myEntity).build();
	}

	/**
	 * um alle Gesundheitsschulen anzeigen zu lassen
	 * @return
	 */
	@Path("gesundheitsschule")
	@GET
	@Produces("application/json")
	public Response gesundheitsschule() 
	{
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getGesundheitsschule()) {};
		return Response.status(200).entity(myEntity).build();
	}

	/**
	 * um alle Schulzentren anzeigen zu lassen
	 * @return
	 */
	@Path("schulzentrum")
	@GET
	@Produces("application/json")
	public Response schulzentrum() 
	{
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getSchulzentrum()) {};
		return Response.status(200).entity(myEntity).build();
	}

	/**
	 * um alle Verwaltungschulen anzeigen zu lassen
	 * @return
	 */
	@Path("verwaltungsschule")
	@GET
	@Produces("application/json")
	public Response verwaltungsschulen() 
	{
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(dbi.getVerwaltungsschule()) {};
		return Response.status(200).entity(myEntity).build();
	}

	/**
	 * um alle Geodaten anzeigen zu lassen
	 * @return
	 */
	@Path("geom/{id}")
	@GET
	@Produces("application/json")
	public Response geometrie(@PathParam("id")int id) 
	{
		String result = dbi.getGeom(id);
		return Response.status(200).entity(result).build();
	}
	
	@Path("gebiet/{idOrt}/{idSchule}") 
	@GET 
	@Produces("application/json") 
	public Response schulenImGebiet(@PathParam("idOrt")int idOrt, @PathParam("idSchule")String idSchule) 
	{ 
		
		Schulen schulen = dbi.getSchulenImGebiet(idOrt, idSchule); 
		

		
		if (schulen == null)  schulen = new Schulen(); 
		GenericEntity<Schulen> myEntity = new GenericEntity<Schulen>(schulen) {}; 
		return Response.status(200).entity(myEntity).build(); 
	}
	
	@Override
	public Set<Class<?>> getClasses() 
	{
		return null;
	}
	
}
