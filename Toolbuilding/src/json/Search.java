package json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import daoimpl.DBImpl;
import datenbank.DBKommu;

public class Search extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private String host;
	private String port;
	private String dbName;
	private String user;
	private String password;
	
	private DBKommu dbk;
	private DBImpl dbi;
	
	private Gson gson = new Gson();

	public void init() throws ServletException 
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
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String pathInfo = request.getPathInfo();
		String[] splits = pathInfo.split("/");
		String json = "noch nicht implementiert";
		
		switch (splits[1]) 
		{
			case "alle":
				json = gson.toJson(dbi.getAlleSchulen());
			break;
		}
		
		out.print(json);
		out.flush();
	}
	
	
}