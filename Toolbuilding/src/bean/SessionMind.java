package bean;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Diese Bean ist für das umschalten der Sprachen zuständig
 * @author Sarah, Jessica
 *
 */
@ManagedBean
@SessionScoped
public class SessionMind 
{
	   
	   public String switchLanguage(String langCode)
	    {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale (langCode));
			
			return "Startseite";
	    }

	   
}
