package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Diese Bean ist kontrolliert die SchulenBean
 * @author Sarah, Jessica
 *
 */

@ManagedBean
@RequestScoped
public class SchulenBeanController 
{
	@ManagedProperty(value = "#{schulenBean}")
	private SchulenBean schulenBean;
	
	/**
	 * diese Methode ist daf�r da die jeweiligen Gruppen auf unserer Webseite zu unterscheiden undn zu setzen
	 * @return webseite
	 */
	public String gruppenSuchen()
	{
		String webseite = "";
		
		if(schulenBean.getGruppe().equals("Sch�ler"))
		{
			webseite = "Schueler";
		}
		else if(schulenBean.getGruppe().equals("Eltern"))
		{
			webseite = "Eltern";
		}
		else if(schulenBean.getGruppe().equals("Austauschsch�ler"))
		{
			webseite = "Austauschschueler";
		}
		else if(schulenBean.getGruppe().equals("F�rderverein"))
		{
			webseite = "Foerderverein";
		}
		else if(schulenBean.getGruppe().equals("Immigranten"))
		{
			webseite = "Immigranten";
		}
		
		return webseite;
	}
	
	/**
	 * wird diese Methode aufgerufen, wird sucheSchulen() aufgerufen und der String "Schueler" wird zur�ckgegeben"
	 * @return "Schueler"
	 */
	public String suchen1()
	{
		schulenBean.sucheSchulen();
		
		return "Schueler";
	}
	
	/**
	 * wird diese Methode aufgerufen, wird sucheSchulen() aufgerufen und der String "Austauschschueler" wird zur�ckgegeben"
	 * @return "Austauschschueler"
	 */
	public String suchen2()
	{
		schulenBean.sucheSchulen();
		
		return "Austauschschueler";
	}
	
	/**
	 * wird diese Methode aufgerufen, wird sucheSchulen1() aufgerufen und der String "Eltern" wird zur�ckgegeben"
	 * @return "Eltern"
	 */
	public String suchen3()
	{
		schulenBean.sucheSchulen1();
		
		return "Eltern";
	}
	
	/**
	 * wird diese Methode aufgerufen, wird schulenAngeben() und angabenTrue() aufgerufen und 
	 * der String "Foerderverein" wird zur�ckgegeben"
	 * @return "Foerderverein"
	 */
	public String suchen4()
	{
		schulenBean.schulenAngeben();
		schulenBean.angabenTrue();
		
		return "Foerderverein";
	}
	
	/**
	 * wird diese Methode aufgerufen, wird sucheSchulen() aufgerufen und der String "Immigranten" zur�ckgegeben"
	 * @return "Immigranten"
	 */
	public String suchen5()
	{
		schulenBean.sucheSchulen();
		
		return "Immigranten";
	}

	public SchulenBean getSchulenBean() 
	{
		return schulenBean;
	}

	public void setSchulenBean(SchulenBean schulenBean) 
	{
		this.schulenBean = schulenBean;
	}
	
	

}
