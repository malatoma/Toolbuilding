package bean;

import java.io.Serializable;

//import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
//import javax.faces.context.PartialResponseWriter;
import javax.faces.context.PartialResponseWriter;

import com.google.gson.Gson;

import daoimpl.DBImpl;
import daten.Gebiete;
//import daten.Gebiete;
import daten.User;
import datenbank.DBKommu;

/**
 * 
 * @author Jessica, Marco
 */

@ManagedBean
@RequestScoped
public class ToolBean extends DBImpl implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String host;
	private String port;
	private String dbName;
	private String user;
	private String password;
	
	private DBKommu dbk;
	public DBImpl dbi;

	private int auswahlGebiete;
	private Gebiete gebiete;
	
	//TODO: soll nur einmal beim Start der Webseite auf false gesetzt werden, danach nur noch über set geändert
	private boolean login;
	
	private User user1= new User();
	
	private Gson gson = new Gson();
	
	public ToolBean()
	{  
		super();
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
	 * Beispiel für Marker
	 */
/*	public void displaySchulen()
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
	}*/
	
	/**
	 * hier werden die jeweiligen Schulen auf der Karte dargestellt	
	 * @param markerclass
	 * @param schulen
	 */
/*	private void senden(String markerclass, Schulen schulen) 
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
	}*/
	
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
	
	public void registrieren()
	{
		System.out.println("registrieren()");
		if(user1.getVorname() == null || user1.getNachname() == null || user1.getGeburtstag() == null || user1.getStrasse() == null ||
				user1.getHausnr() == 0 || user1.getPlz() == null || user1.getOrt() == null || user1.getUsername() == null || 
				user1.getPasswort() == null)
		{
			setFehlermeldungReg("Bitte gebe in allen Felder etwas an.");
			System.out.println(dbi.getFehlermeldungReg());
		}
		else
		{
			dbi.checkUsername(user1);
			System.out.println(dbi.getFehlermeldungReg());
			if(dbi.getFehlermeldungReg() == null)
			{
				insertUser(user1);
			}
		}
	}
	
	public void anmelden()
	{
		dbi.anmelden(user1);
	}
	

	public boolean isLogin() 
	{
		return login;
	}

	public void setLogin(boolean login) 
	{
		this.login = login;
	}

	public Gson getGson() 
	{
		return gson;
	}

	public void setGson(Gson gson) 
	{
		this.gson = gson;
	}

	public int getAuswahlGebiete() 
	{
		return auswahlGebiete;
	}

	public void setAuswahlGebiete(int auswahlGebiete) 
	{
		this.auswahlGebiete = auswahlGebiete;
	}

	public User getUser1() 
	{
		return user1;
	}

	public void setUser1(User user1) 
	{
		this.user1 = user1;
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
