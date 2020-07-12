package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import daoimpl.DBImpl;

/**
 * Diese Bean ist kontrolliert die ToolBean
 * @author Jessica, Marco
 *
 */

@ManagedBean
@RequestScoped
public class ToolBeanController extends DBImpl implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{toolBean}")
	private ToolBean toolBean;
	
	public String anmelden()
	{
		toolBean.setLogin(true);
		toolBean.anmelden();
		return "Home";
	}
	
	public String logout()
	{
		toolBean.setLogin(false);
		return "Home";
	}
	
	public String registrieren1()
	{
		return "Registrierung";
	}
	
	public String registrieren2()
	{
		System.out.println("registrieren2()");
		toolBean.registrieren();
		if(toolBean.dbi.getFehlermeldungReg() == null)
		{
			toolBean.setLogin(true);
			return "Home";
		}
		else
		{
			return "Registrierung";
		}
	}
	
	public String newProject()
	{
		toolBean.project();
		return "Projektuebersicht";
	}
	
	public String showKarte()
	{
		return "Karte";
	}
	
	public String showProjekte()
	{
		return "Projektuebersicht";
	}

	
	public ToolBean getToolBean() 
	{
		return toolBean;
	}

	public void setToolBean(ToolBean toolBean) 
	{
		this.toolBean = toolBean;
	}
	public void refreshProject() {
		toolBean.ProjektAufrufen();
	}
	
}
