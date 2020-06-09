package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Diese Bean ist kontrolliert die ToolBean
 * @author Jessica, Marco
 *
 */

@ManagedBean
@RequestScoped
public class ToolBeanController implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{toolBean}")
	private ToolBean toolBean;
	
	
	public String anmelden()
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
		return "Registrierung";
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
