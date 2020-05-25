package bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialResponseWriter;

import com.google.gson.Gson;

import daoimpl.DBImpl;
import daten.Gebiete;
import daten.Schulen;
import datenbank.DBKommu;

/**
 * 
 * @author Sarah, Jesica
 *	Diese Klasse ist da, um die Schulen zu verwalten. Mit der Klasse können wir die jeweiligen Schulen ausgeben lassen.
 */

@ManagedBean
@RequestScoped
public class SchulenBean 
{
	private Schulen schulen = null;
	
	private String host;
	private String port;
	private String dbName;
	private String user;
	private String password;
	
	private DBKommu dbk;
	private DBImpl dbi;
	
	private String gruppe;
	private String schulenform;
	private ArrayList<String> angaben;
	private boolean anschrift = false;
	private boolean telefon = false;
	private boolean email = false;
	private boolean webseite = false;
	private boolean schulleitung = false;
	
	private String auswahlSchulenform;
	private int auswahlGebiete;
	private Gebiete gebiete;
	
	private Gson gson = new Gson();
	
	private String ganztagsschule;
	private boolean ganztag = false;
	
	public SchulenBean()
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
		
		gebiete = dbi.suchenGebiete();
	}
	
	/**
	 * In dieser Methode wird geguckt, welcher String ausgewählt wurde um dann die richtige Methode ausführen zu können.
	 */
	public void schulenAngeben()
	{
		if(schulenform.equals("Alle Schulen"))
		{
			schulen = dbi.getAlleSchulen();
		}
		else if(schulenform.equals("privat"))
		{
			schulen = dbi.getPrivatschulen();
		}
		else if(schulenform.equals("ober"))
		{
			schulen = dbi.getOberschulen();
		}
		else if(schulenform.equals("grund"))
		{
			schulen = dbi.getGrundschulen();
		}
		else if(schulenform.equals("foerderzentrum"))
		{
			schulen = dbi.getFoerderzentrum();
		}
		else if(schulenform.equals("abend"))
		{
			schulen = dbi.getAbendErwachsenenSchule();
		}
		else if(schulenform.equals("gym"))
		{
			schulen = dbi.getDurchgaengigesGymnasium();
		}
		else if(schulenform.equals("schulzentrum"))
		{
			schulen = dbi.getSchulzentrum();
		}
		else if(schulenform.equals("beruf"))
		{
			schulen = dbi.getBerufsbildeneSchule();
		}
		else if(schulenform.equals("gesundheit"))
		{
			schulen = dbi.getGesundheitsschule();
		}
		else if(schulenform.equals("verwaltung"))
		{
			schulen = dbi.getVerwaltungsschule();
		}
	}
	
	/**
	 * diese Methode setzt alle Angaben auf false
	 */
	public void angabenFalse()
	{
		anschrift = false;
		telefon = false;
		email = false;
		webseite = false;
		schulleitung = false;
	}
	
	/**
	 * diese Methode setzt alle Angaben auf true
	 */
	public void angabenTrue()
	{
		anschrift = true;
		telefon = true;
		email = true;
		webseite = true;
		schulleitung = true;
	}
	
	/**
	 * diese Methode guckt welche Strings ausgewählt wurden um den die Angaben auf true zu setzen, somit werden nur
	 * die ausgewählten Angaben ausgegeben.
	 */
	public void angabenAngeben()
	{
		angabenFalse();
		
		for(int i = 0; i < angaben.size(); i++)
		{
			if(angaben.get(i).equals("Anschrift"))
			{
				anschrift = true;
			}
			else if(angaben.get(i).equals("Telefon"))
			{
				telefon = true;
			}
			else if(angaben.get(i).equals("E-Mail"))
			{
				email = true;
			}
			else if(angaben.get(i).equals("Webseite"))
			{
				webseite = true;
			}
			else if(angaben.get(i).equals("Schulleitung"))
			{
				schulleitung = true;
			}
		}
	}
	
	/**
	 * diese Methode ruft die Methoden für die ausgewählte Schule und für die ausgewählten Angaben auf
	 */
	public void sucheSchulen()
	{
		schulenAngeben();
		angabenAngeben();
	}
	
	/**
	 * diese Methode testet ob Ganztagsschule auf ja oder nein gesetzt wurde und ruft den jeweils die Methode
	 * getGanztagschulen() mit den jeweiligen Attributen auf. Danach wird den die Methode mit den ausgewählten Angaben
	 * aufgerufen.
	 */
	public void sucheSchulen1()
	{
		ganztag = false;
		
		if(ganztagsschule.equals("ja"))
		{
			ganztag = true;
			schulen = dbi.getGanztagschulen("J", schulenform);
		}
		else
		{
			ganztag = false;
			schulen = dbi.getGanztagschulen("N", schulenform);
		}
		
		angabenAngeben();
	}
	
	/**
	 * hier werden die Marker von der Karte für die jeweilige Schule gesetzt, nachdem abgefragt wurde welche Schule 
	 * ausgewählt wurde. Dazu werden dann noch die jeweiligen get-Methoden von den Schulen aufgerufen.
	 */
	public void displaySchulen()
	{
		String markerclass = "";
		
		if (auswahlSchulenform.equals("alle")) 
		{
			markerclass = "marker-red";
			schulen = dbi.getAlleSchulen();
		}
		else if (auswahlSchulenform.equals("privatschule"))
		{
			markerclass = "marker-green";
			schulen = dbi.getPrivatschulen();
		}
		else if (auswahlSchulenform.equals("oberschule"))
		{
			markerclass = "marker-darkred";
			schulen = dbi.getOberschulen();
		}
		else if (auswahlSchulenform.equals("grundschule"))
		{
			markerclass = "marker-purple";
			schulen = dbi.getGrundschulen();
		}
		else if (auswahlSchulenform.equals("abendschule"))
		{
			markerclass = "marker-darkgreen";
			schulen = dbi.getAbendErwachsenenSchule();
		}
		else if (auswahlSchulenform.equals("berufsschule"))
		{
			markerclass = "marker-darkblue";
			schulen = dbi.getBerufsbildeneSchule();
		}
		else if (auswahlSchulenform.equals("durchgaengigesGym"))
		{
			markerclass = "marker-black";
			schulen = dbi.getDurchgaengigesGymnasium();
		}
		else if (auswahlSchulenform.equals("foerderzentrum"))
		{
			markerclass = "marker-orange";
			schulen = dbi.getFoerderzentrum();
		}
		else if (auswahlSchulenform.equals("gesundheitsschule"))
		{
			markerclass = "marker-darkorange";
			schulen = dbi.getGesundheitsschule();
		}
		else if (auswahlSchulenform.equals("schulzentrum"))
		{
			markerclass = "marker-brown";
			schulen = dbi.getSchulzentrum();
		}
		else if (auswahlSchulenform.equals("verwaltungsschule"))
		{
			markerclass = "marker-darkbrown";
			schulen = dbi.getVerwaltungsschule();
		}
		senden(markerclass, schulen);
	}
	
	/**
	 * hier werden die jeweiligen Schulen auf der Karte dargestellt	
	 * @param markerclass
	 * @param schulen
	 */
	private void senden(String markerclass, Schulen schulen) 
	{
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extContext = ctx.getExternalContext();
		
		if (ctx.getPartialViewContext().isAjaxRequest()) 
		{
			try 
			{
				extContext.setResponseContentType("text/xml");
				extContext.addResponseHeader("cache-Control","no-cache");
				PartialResponseWriter writer = ctx.getPartialViewContext().getPartialResponseWriter();
				writer.startDocument();
				writer.startEval();
				writer.write("showSchulen("+ gson.toJson(schulen) + ",'" + markerclass + "')");
				writer.endEval();
				writer.endDocument();
				writer.flush();
				ctx.responseComplete();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}		
		}
	}
	
	/**
	 * hier werden die jeweiligen Gebiete auf der Karte dargestellt
	 */
	public void displayGebiete()
	{
		String daten = dbi.getGeom(auswahlGebiete);
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extContext = ctx.getExternalContext();
		
		if (ctx.getPartialViewContext().isAjaxRequest()) 
		{
			try 
			{
				extContext.setResponseContentType("text/xml");
				extContext.addResponseHeader("cache-Control","no-cache");
				PartialResponseWriter writer = ctx.getPartialViewContext().getPartialResponseWriter();
				writer.startDocument();
				writer.startEval();
				writer.write("showGebiet("+ daten + ")");
				writer.endEval();
				writer.endDocument();
				writer.flush();
				ctx.responseComplete();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}		
		}
	}
	
	/**
	 * hier werden die jeweiligen Schulen eines Gebietes dargestellt
	 */
	public void displaySchulenGebiete()
	{
		String markerclass = "";
		
		if (auswahlSchulenform.equals("alle")) 
		{
			markerclass = "marker-red";
		}
		else if (auswahlSchulenform.equals("privatschule"))
		{
			markerclass = "marker-green";
		}
		else if (auswahlSchulenform.equals("oberschule"))
		{
			markerclass = "marker-darkred";
		}
		else if (auswahlSchulenform.equals("grundschule"))
		{
			markerclass = "marker-purple";
		}
		else if (auswahlSchulenform.equals("abendschule"))
		{
			markerclass = "marker-darkgreen";
		}
		else if (auswahlSchulenform.equals("berufsschule"))
		{
			markerclass = "marker-darkblue";
		}
		else if (auswahlSchulenform.equals("durchgaengigesGym"))
		{
			markerclass = "marker-black";
		}
		else if (auswahlSchulenform.equals("foerderzentrum"))
		{
			markerclass = "marker-orange";
		}
		else if (auswahlSchulenform.equals("gesundheitsschule"))
		{
			markerclass = "marker-darkorange";
		}
		else if (auswahlSchulenform.equals("schulzentrum"))
		{
			markerclass = "marker-brown";
		}
		else if (auswahlSchulenform.equals("verwaltungsschule"))
		{
			markerclass = "marker-darkbrown";
		}
		
		schulen = dbi.getSchulenImGebiet(auswahlGebiete, auswahlSchulenform);
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extContext = ctx.getExternalContext();
		
		if (ctx.getPartialViewContext().isAjaxRequest()) 
		{
			try 
			{
				extContext.setResponseContentType("text/xml");
				extContext.addResponseHeader("cache-Control","no-cache");
				PartialResponseWriter writer = ctx.getPartialViewContext().getPartialResponseWriter();
				writer.startDocument();
				writer.startEval();
				writer.write("showSchulen("+ gson.toJson(schulen) + ",'" + markerclass + "')");
				writer.endEval();
				writer.endDocument();
				writer.flush();
				ctx.responseComplete();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}		
		}
	}
	
	

	public Schulen getSchulen() 
	{
		return schulen;
	}

	public void setSchulen(Schulen schulen) 
	{
		this.schulen = schulen;
	}

	public String getGruppe() 
	{
		return gruppe;
	}

	public void setGruppe(String gruppe) 
	{
		this.gruppe = gruppe;
	}

	public String getSchulenform() 
	{
		return schulenform;
	}

	public void setSchulenform(String schulenform) 
	{
		this.schulenform = schulenform;
	}

	public ArrayList<String> getAngaben() 
	{
		return angaben;
	}

	public void setAngaben(ArrayList<String> angaben) 
	{
		this.angaben = angaben;
	}

	public boolean isAnschrift() 
	{
		return anschrift;
	}

	public void setAnschrift(boolean anschrift) 
	{
		this.anschrift = anschrift;
	}

	public boolean isTelefon() 
	{
		return telefon;
	}

	public void setTelefon(boolean telefon) 
	{
		this.telefon = telefon;
	}

	public boolean isEmail() 
	{
		return email;
	}

	public void setEmail(boolean email) 
	{
		this.email = email;
	}

	public boolean isWebseite() 
	{
		return webseite;
	}

	public void setWebseite(boolean webseite) 
	{
		this.webseite = webseite;
	}

	public boolean isSchulleitung() 
	{
		return schulleitung;
	}

	public void setSchulleitung(boolean schulleitung) 
	{
		this.schulleitung = schulleitung;
	}

	public String getAuswahlSchulenform() 
	{
		return auswahlSchulenform;
	}

	public void setAuswahlSchulenform(String auswahlSchulenform) 
	{
		this.auswahlSchulenform = auswahlSchulenform;
	}

	public int getAuswahlGebiete() 
	{
		return auswahlGebiete;
	}

	public void setAuswahlGebiete(int auswahlGebiete) 
	{
		this.auswahlGebiete = auswahlGebiete;
	}

	public String getGanztagsschule() 
	{
		return ganztagsschule;
	}

	public void setGanztagsschule(String ganztagsschule) 
	{
		this.ganztagsschule = ganztagsschule;
	}

	public boolean isGanztag() 
	{
		return ganztag;
	}

	public void setGanztag(boolean ganztag) 
	{
		this.ganztag = ganztag;
	}

	public Gebiete getGebiete() 
	{
		return gebiete;
	}

	public void setGebiete(Gebiete gebiete) 
	{
		this.gebiete = gebiete;
	}
}
