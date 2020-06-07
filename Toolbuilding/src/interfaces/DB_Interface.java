package interfaces;

public interface DB_Interface 
{
	/**
	 * createTable() erstellt die Tabellen in der Datenbank
	 */
	public void createTable();
	
	/**
	 * createStatements() erzeugt Statements, die im weiteren Verlauf in den Klassen ben�tigt werden
	 */
	public void createStatements();
	
	/**
	 * deleteTable() l�scht Daten aus der Datenbank
	 * @param tabellenname
	 * @param whereBefehl
	 * @throws Exception
	 */
	public void deleteTable(String tabellenname, String whereBefehl) throws Exception;
	
	/**
	 * folgende Methoden sind f�r das Lesen aus der Datenbank zust�ndig
	 */
//	public String getGeom(int gebietId);
	
	/**
	 * verbindungSchlie�enDB() schlie�t die Verbindung zur Datenbank
	 */
	public void verbindungSchlie�enDB();
}
