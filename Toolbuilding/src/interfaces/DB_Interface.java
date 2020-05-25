package interfaces;

import daten.Schule;
import daten.Schulen;

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
	 * insertTableSchule(Schule schule) fügt die ausgelesenden Werte in der Datenbank in die jeweiligen Tabellen
	 * @param schule
	 */
	public void insertTableSchule(Schule schule);
	
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
	public Schulen getAlleSchulen();
	public Schulen getAbendErwachsenenSchule();
	public Schulen getBerufsbildeneSchule();
	public Schulen getDurchgaengigesGymnasium();
	public Schulen getFoerderzentrum();
	public Schulen getGesundheitsschule();
	public Schulen getGrundschulen();
	public Schulen getOberschulen();
	public Schulen getPrivatschulen();
	public Schulen getSchulzentrum();
	public Schulen getVerwaltungsschule();
	public Schulen getSchulenImGebiet(int gebietId, String schultyp);
	public String getGeom(int gebietId);
	
	/**
	 * verbindungSchließenDB() schließt die Verbindung zur Datenbank
	 */
	public void verbindungSchließenDB();
}
