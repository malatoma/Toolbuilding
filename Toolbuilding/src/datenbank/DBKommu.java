package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBKommu 
{
	private String host;
	private String port;
	private String dbName;
	private String user;
	private String password;
	
	static Connection connection;
	
	/**
	 * Die Methode getConnection() ist für den Aufbau der Verbindung zum Datenbankserver zuständig
	 * @return con oder null
	 */
	public Connection getConnection()
	{
		try
		{
			String driver = "org.postgresql.Driver";
			String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
			
			Class.forName(driver);
			
			connection = DriverManager.getConnection(url, user, password);
			
			System.out.println("Connected");
			
			return connection;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public String getHost() 
	{
		return host;
	}

	public void setHost(String host) 
	{
		this.host = host;
	}

	public String getPort() 
	{
		return port;
	}

	public void setPort(String port) 
	{
		this.port = port;
	}

	public String getDbName() 
	{
		return dbName;
	}

	public void setDbName(String dbName) 
	{
		this.dbName = dbName;
	}

	public String getUser() 
	{
		return user;
	}

	public void setUser(String user) 
	{
		this.user = user;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public static void setConnection(Connection connection) 
	{
		DBKommu.connection = connection;
	}
	
}
