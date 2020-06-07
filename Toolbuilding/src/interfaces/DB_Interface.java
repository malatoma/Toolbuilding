package interfaces;

public interface DB_Interface 
{
	/**
	 * createTable() erstellt die Tabellen in der Datenbank
	 */
	public void createTable();
	
	/**
	 * createStatements() erzeugt Statements, die im weiteren Verlauf in den Klassen benötigt werden
	 */
	public void createStatements();
	
	/**
	 * deleteTable() löscht Daten aus der Datenbank
	 * @param tabellenname
	 * @param whereBefehl
	 * @throws Exception
	 */
	public void deleteTable(String tabellenname, String whereBefehl) throws Exception;
	
	/**
	 * folgende Methoden sind für das Lesen aus der Datenbank zuständig
	 */
//	public String getGeom(int gebietId);
	
	/**
	 * verbindungSchließenDB() schließt die Verbindung zur Datenbank
	 */
	public void verbindungSchließenDB();
}
