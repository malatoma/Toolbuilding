package bean;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Diese Bean ist f�r das umschalten der Sprachen zust�ndig
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
