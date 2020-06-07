package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Diese Bean ist kontrolliert die ToolBean
 * @author Sarah, Jessica
 *
 */

@ManagedBean
@RequestScoped
public class ToolBeanController implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{toolBean}")
	private ToolBean toolBean;
	
	/**
	 * diese Methode ist dafür da die jeweiligen Gruppen auf unserer Webseite zu unterscheiden undn zu setzen
	 * @return webseite
	 */
/*	public String gruppenSuchen()
	{
		String webseite = "";
		
		if(toolBean.getGruppe().equals("Schüler"))
		{
			webseite = "Schueler";
		}
		else if(toolBean.getGruppe().equals("Eltern"))
		{
			webseite = "Eltern";
		}
		else if(toolBean.getGruppe().equals("Austauschschüler"))
		{
			webseite = "Austauschschueler";
		}
		else if(toolBean.getGruppe().equals("Förderverein"))
		{
			webseite = "Foerderverein";
		}
		else if(toolBean.getGruppe().equals("Immigranten"))
		{
			webseite = "Immigranten";
		}
		
		return webseite;
	}*/
	
	/**
	 * wird diese Methode aufgerufen, wird sucheSchulen() aufgerufen und der String "Schueler" wird zurückgegeben"
	 * @return "Schueler"
	 */
/*	public String suchen1()
	{
		toolBean.sucheSchulen();
		
		return "Schueler";
	}*/
	
	/**
	 * wird diese Methode aufgerufen, wird sucheSchulen() aufgerufen und der String "Austauschschueler" wird zurückgegeben"
	 * @return "Austauschschueler"
	 */
/*	public String suchen2()
	{
		toolBean.sucheSchulen();
		
		return "Austauschschueler";
	}*/
	
	/**
	 * wird diese Methode aufgerufen, wird sucheSchulen1() aufgerufen und der String "Eltern" wird zurückgegeben"
	 * @return "Eltern"
	 */
/*	public String suchen3()
	{
		toolBean.sucheSchulen1();
		
		return "Eltern";
	}*/
	
	/**
	 * wird diese Methode aufgerufen, wird schulenAngeben() und angabenTrue() aufgerufen und 
	 * der String "Foerderverein" wird zurückgegeben"
	 * @return "Foerderverein"
	 */
/*	public String suchen4()
	{
		toolBean.schulenAngeben();
		toolBean.angabenTrue();
		
		return "Foerderverein";
	}*/
	
	/**
	 * wird diese Methode aufgerufen, wird sucheSchulen() aufgerufen und der String "Immigranten" zurückgegeben"
	 * @return "Immigranten"
	 */
/*	public String suchen5()
	{
		toolBean.sucheSchulen();
		
		return "Immigranten";
	}*/
	
	public String login()
	{
		toolBean.setLogin(true);
		return "Home";
	}
	
	public String logout()
	{
		toolBean.setLogin(false);
		return "Home";
	}
	
	public String registrieren1()
	{
		return "Registrieren";
	}
	
	public String registrieren2()
	{
		toolBean.registrieren();
		toolBean.setLogin(true);
		return "Home";
	}
	

	public ToolBean getToolBean() 
	{
		return toolBean;
	}

	public void setToolBean(ToolBean toolBean) 
	{
		this.toolBean = toolBean;
	}
	
}
