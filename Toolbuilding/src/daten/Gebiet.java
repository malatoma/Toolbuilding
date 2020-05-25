package daten;

/**
 * Klasse für die Attribute von einem Gebiet
 * @author Sarah, Jessica
 *
 */
public class Gebiet 
{
	private int id;
	private String name;
	
	public String toString()
	{
		return "Gebiet - id: " + id + " - name: " + name;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
}
