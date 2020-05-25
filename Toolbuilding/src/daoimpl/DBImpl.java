package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import daten.Adresse;
import daten.Gebiet;
import daten.Gebiete;
import daten.Geodaten;
import daten.Kontaktdaten;
import daten.Schule;
import daten.Schulen;
import daten.Schulleitung;
import daten.Schultypen;
import datenbank.DBKommu;
import interfaces.DB_Interface;
import interfaces.SchulenSuche;

/**
 * Die Klasse DBImpl implementiert das DB_Interface und das Interface SchulenSuche
 * @author Sarah, Jessica
 *
 */
public class DBImpl extends DBKommu implements DB_Interface, SchulenSuche
{
	private Connection con;
	
	private PreparedStatement createAdresse;
	private PreparedStatement createSchulleitung;
	private PreparedStatement createKontaktdaten;
	private PreparedStatement createSchultypen;
	private PreparedStatement createSchule;
	
	private PreparedStatement insertSchule;
	private PreparedStatement insertAdresse;
	private PreparedStatement insertSchulleitung;
	private PreparedStatement insertKontaktdaten;
	private PreparedStatement insertSchultypen;
	
	private PreparedStatement selectAdressId;
	private PreparedStatement selectSchulleitungsId;
	private PreparedStatement selectSchultypenId;
	private PreparedStatement selectKontaktdatenId;
	
	private PreparedStatement selectSchultypen;
	private PreparedStatement selectKontaktdaten; 
	private PreparedStatement selectAdresse; 
	private PreparedStatement selectSchulleitung;
	private PreparedStatement updateAdresseGeo; 
	
	private PreparedStatement selectSchulen;
	private PreparedStatement selectGebiet;
	private PreparedStatement selectSchulenGebiet;
	
	private PreparedStatement selectGanztagsschulen;
	
	
	public DBImpl(Connection con)
	{
		super(); 
		this.con = con;
		createStatements();
	}
	
	/**
	 * Die Methode createTable() erstellt im Datenbankserver die jeweiligen Tabellen 
	 */
	public void createTable()
	{
		try
		{
			if(con == null)
			{
				con = getConnection();
			}
			
			createAdresse = con.prepareStatement("CREATE TABLE IF NOT EXISTS adresse(AdressNr serial NOT NULL, Strasse varchar(80), HausNr varchar(30), PLZ varchar(30), Ort varchar(30), Geodaten geometry(Point,4326), PRIMARY KEY(AdressNr))");
			createSchulleitung = con.prepareStatement("CREATE TABLE IF NOT EXISTS schulleitung(SchulleitungNr serial NOT NULL, Titel varchar(10), Vorname varchar(30), Nachname varchar(30), PRIMARY KEY(SchulleitungNr))");
			createKontaktdaten = con.prepareStatement("CREATE TABLE IF NOT EXISTS kontaktdaten(KontaktNr serial NOT NULL, Vorwahl varchar(20), TelefonNr varchar(30), FAX varchar(30), Email varchar(100), Internet varchar(100), PRIMARY KEY(KontaktNr))");
			createSchultypen = con.prepareStatement("CREATE TABLE IF NOT EXISTS schultypen(SchultypNr serial NOT NULL, Schulform varchar(100), Ganztagsschule varchar(1), Ganztagsform varchar(100), PRIMARY KEY(SchultypNr))");
			createSchule = con.prepareStatement("CREATE TABLE IF NOT EXISTS schule(SchulNr varchar(20), Name varchar(300), Zusatz varchar(300), AdressNr int, SchulleitungNr int, KontaktNr int, SchultypNr int, "
															+ "PRIMARY KEY(SchulNr), FOREIGN KEY(AdressNr) REFERENCES Adresse, FOREIGN KEY(SchulleitungNr) REFERENCES Schulleitung, FOREIGN KEY(KontaktNr) REFERENCES Kontaktdaten, FOREIGN KEY(SchultypNr) REFERENCES Schultypen)");
			
			createAdresse.executeUpdate();
			createSchulleitung.executeUpdate();
			createKontaktdaten.executeUpdate();
			createSchultypen.executeUpdate();
			createSchule.executeUpdate();
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		finally
		{
			System.out.println("Function complete.");
		}
	}
	
	

	
	/**
	 * Die Methode insertTableSchule() fügt in der Tabelle Schulen in dem Datenbankserver Daten ein
	 */
	public void createStatements() 
	{
		try
		{
			if(con == null)
			{
				con = getConnection();
			}
			
			insertSchule = con.prepareStatement("INSERT INTO schule (SchulNr, Name, Zusatz, AdressNr,SchulleitungNr, KontaktNr, SchultypNr) Values (?,?,?,?,?,?,?)");
			insertAdresse = con.prepareStatement("INSERT INTO adresse (Strasse , HausNr , PLZ , Ort) Values (?,?,?,?)");
			insertSchulleitung = con.prepareStatement("INSERT INTO schulleitung (Titel, Vorname, Nachname) VALUES (?,?,?)");
			insertKontaktdaten = con.prepareStatement("INSERT INTO kontaktdaten (Vorwahl, TelefonNr, FAX, Email, Internet) Values (?,?,?,?,?)");
			insertSchultypen = con.prepareStatement("INSERT INTO schultypen (Schulform, Ganztagsschule, Ganztagsform) Values (?,?,?)");
			
			selectAdressId = con.prepareStatement("SELECT adressnr FROM adresse WHERE strasse=? AND hausnr =? AND plz=? AND ort=?");
			selectSchulleitungsId = con.prepareStatement("SELECT schulleitungnr FROM schulleitung WHERE titel = ? AND vorname=? AND nachname=?");
			selectSchultypenId = con.prepareStatement("SELECT schultypnr FROM schultypen WHERE schulform =? AND ganztagsschule=? AND ganztagsform =?");
			selectKontaktdatenId = con.prepareStatement("SELECT kontaktnr FROM kontaktdaten WHERE vorwahl=? AND telefonnr=? AND fax =? AND email =? AND internet=?");
			
			selectSchultypen = con.prepareStatement("SELECT schulform, ganztagsschule, ganztagsform FROM schultypen WHERE schultypen.schultypNr = ?");
			selectKontaktdaten = con.prepareStatement("SELECT vorwahl, telefonNr, fax, email, internet FROM kontaktdaten WHERE kontaktdaten.kontaktNr = ?");
			selectAdresse = con.prepareStatement("SELECT adressNr, strasse, hausNr, plz, ort, ST_X(geodaten), ST_Y(geodaten) FROM adresse WHERE adresse.adressNr = ?");
			selectSchulleitung = con.prepareStatement("SELECT titel, vorname, nachname FROM schulleitung WHERE schulleitung.schulleitungNr = ?");
			updateAdresseGeo = con.prepareStatement("UPDATE adresse set Geodaten = ST_SetSRID(ST_MakePoint(?,?),4326) WHERE adresse.adressNr = ?");
			
			selectSchulen = con.prepareStatement("SELECT schulnr, name, zusatz, adressnr, schulleitungnr, kontaktnr, schule.schultypnr, schultypen.schulform \r\n"
												+ "FROM schule, schultypen \r\n"
												+ "WHERE schule.schultypnr = schultypen.schultypnr and schultypen.schulform like ?");
			selectGebiet = con.prepareStatement("SELECT ST_asGeoJSON(polygon) FROM gebiete WHERE gebiete.id = ?");
			selectSchulenGebiet = con.prepareStatement("SELECT DISTINCT schulnr, schule.name, zusatz, schule.adressnr, schulleitungnr, kontaktnr, schule.schultypnr "
												+ "FROM schule "
												+ "INNER JOIN gebiete ON gebiete.id = ? "
												+ "INNER JOIN schultypen ON schule.schultypnr = schultypen.schultypnr and schultypen.schulform like ? "
												+ "INNER JOIN adresse ON schule.adressnr = adresse.adressnr "
												+ "WHERE ST_DWithin(gebiete.polygon, adresse.geodaten, 0.002) ");
			
			selectGanztagsschulen = con.prepareStatement("SELECT schulnr, name, zusatz, adressnr, schulleitungnr, kontaktnr, schule.schultypnr\r\n" + 
													"FROM schule, schultypen\r\n" + 
													"WHERE schule.schultypnr = schultypen.schultypnr AND schultypen.ganztagsschule = ? AND schultypen.schulform like ?");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * diese Methode ist für das suchen der Gebiete in der Datenbank zuständig
	 * @return gebiete 
	 */
	public Gebiete suchenGebiete()
	{
		Gebiete gebiete = new Gebiete();
		
		if(con == null)
		{
			con = getConnection();
		}
		
		Statement stmt = null;
		String query = "SELECT id, name FROM gebiete";
		
		try
		{
			stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while(result.next())
			{
				Gebiet gebiet = new Gebiet();
				gebiet.setId(result.getInt(1));
				gebiet.setName(result.getString(2));
				gebiete.add(gebiet);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return gebiete;
	}
	
	
	/**
	 * Methode um die Geokoordinaten zu suchen
	 * @param schulen
	 * @return geo
	 */
	public ArrayList<Geodaten> sucheGeokoordinaten(Schulen schulen)
	{
		ArrayList<Geodaten> geo = new ArrayList<Geodaten>(); 
		try 
		{
			for(Schule schule : schulen)
			{
				Geodaten geodaten= new Geodaten(schule.getAdress());
				geodaten.geodatenErmitteln();
				geo.add(geodaten);
				
				updateAdresseGeo.setDouble(1, geodaten.getLon());
				updateAdresseGeo.setDouble(2, geodaten.getLat());
				updateAdresseGeo.setInt(3, schule.getAdress().getId());
				updateAdresseGeo.executeUpdate();
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return geo; 
	}
	
	/**
	 * Die Methode deleteTable() ist für das löschen von Daten aus dem Datenbankserver zuständig
	 */
	public void deleteTable(String tabellenname, String whereBefehl) throws Exception
	{		
		try
		{
			if(con == null)
			{
				con = getConnection();
			}
			PreparedStatement insert = con.prepareStatement("DELETE FROM " + tabellenname + " WHERE " + whereBefehl);
			insert.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Die Methode ist für die Schließung, von der vorher erstellten Verbindung, zum Datenbankserver
	 */
	public void verbindungSchließenDB() 
	{
		try 
		{
			if(con != null)
			{
				con.close();
				System.out.println("Disconnected");
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * fügt die Werte in die Tabelle Schule in der Datenbank ein
	 */
	public void insertTableSchule(Schule schule) 
	{
		
		try 
		{
			insertSchule.setString(1, schule.getSchulnr());
			insertSchule.setString(2, schule.getName());
			insertSchule.setString(3, schule.getZusatz());
			
			insertSchule.setInt(4, sucheAdresse(schule));
			insertSchule.setInt(5, sucheSchulleitung(schule));
			insertSchule.setInt(6, sucheKontaktdaten(schule));
			insertSchule.setInt(7, sucheSchultypen(schule));
			
			insertSchule.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	
	/**
	 * holt die Schultypen aus der Datenbank per Id
	 * @param id
	 * @return schultypen
	 */
	private Schultypen getSchultypen(int id) 
	{
		ResultSet result;
		Schultypen schultypen = null;
		try 
		{
			selectSchultypen.setInt(1, id);
			result = selectSchultypen.executeQuery();
			result.next();
			schultypen = new Schultypen(result.getString(1), result.getString(2), result.getString(3));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schultypen;
	
	}

	/**
	 * holt die Kontaktdaten aus der Datenbank per Id
	 * @param id
	 * @return kontaktdaten
	 */
	private Kontaktdaten getKontaktdaten(int id) 
	{
		ResultSet result;
		Kontaktdaten kontaktdaten = null;
		try 
		{
			selectKontaktdaten.setInt(1, id);
			result = selectKontaktdaten.executeQuery();
			result.next();
			kontaktdaten = new Kontaktdaten(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return kontaktdaten;
	
	}

	/**
	 * holt die Adresse aus der Datenbank per Id
	 * @param id
	 * @return adresse
	 */
	private Adresse getAdresse(int id)
	{
		ResultSet result;
		Adresse adresse = null;
		try 
		{
			selectAdresse.setInt(1, id); 
			result = selectAdresse.executeQuery();
			result.next();
			adresse = new Adresse(result.getString(2), result.getString(3), result.getString(4), result.getString(5));
			adresse.setId(result.getInt(1));
			adresse.setGeodaten(new Geodaten(result.getDouble(6), result.getDouble(7)));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return adresse;
	}
	
	/**
	 * holt die Schulleitung aus der Datenbank per Id
	 * @param id
	 * @return schulleitung
	 */
	private Schulleitung getSchulleitung(int id)
	{
		ResultSet result;
		Schulleitung schulleitung = null;
		try 
		{
			selectSchulleitung.setInt(1, id);
			result = selectSchulleitung.executeQuery();
			result.next();
			schulleitung = new Schulleitung(result.getString(1), result.getString(2), result.getString(3));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulleitung;
	}
	
	/**
	 * setzt die Schultypen in die DB, falls diese noch nicht vorhanden sind, die Vorhandenheit 
	 * wird mit der Methode sucheSchultypenID geprüft. Falls diese nämlich einen Wert größer 0 zurückgibt 
	 * sind die Werte schon vorhanden.
	 * @param schule
	 * @return i
	 */
	public int sucheSchultypen(Schule schule)
	{
		int i = -1;
		try 
		{
			i = sucheSchultypenID(schule);
			if(i < 0)
			{
				insertSchultypen.setString(1, schule.getSchultypen().getSchulform());
				insertSchultypen.setString(2, schule.getSchultypen().getGanztagsschule());
				insertSchultypen.setString(3, schule.getSchultypen().getGanztagsform());
				insertSchultypen.executeUpdate();
				i = sucheSchultypenID(schule);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * setzt die Schultypen in die DB
	 * @param schule
	 * @return i
	 */
	public int sucheSchultypenID(Schule schule) 
	{
		int i = -1;
		try
		{
			selectSchultypenId.setString(1, schule.getSchultypen().getSchulform());
			selectSchultypenId.setString(2, schule.getSchultypen().getGanztagsschule());
			selectSchultypenId.setString(3, schule.getSchultypen().getGanztagsform());
			ResultSet rs= selectSchultypenId.executeQuery();
				
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * setzt die Kontaktdaten in die DB, falls diese noch nicht vorhanden sind, die Vorhandenheit 
	 * wird mit der Methode sucheKontaktdatenID geprüft. Falls diese nämlich einen Wert größer 0 zurückgibt 
	 * sind die Werte schon vorhanden.
	 * @param schule
	 * @return i
	 */
	public int sucheKontaktdaten(Schule schule)
	{
		int i = -1;
		try 
		{
			i = sucheKontaktdatenID(schule);
			if(i<0)
			{
				insertKontaktdaten.setString(1, schule.getKontaktdaten().getVorwahl());
				insertKontaktdaten.setString(2, schule.getKontaktdaten().getTelefonnr());
				insertKontaktdaten.setString(3, schule.getKontaktdaten().getFax());
				insertKontaktdaten.setString(4, schule.getKontaktdaten().getEmail());
				insertKontaktdaten.setString(5, schule.getKontaktdaten().getInternet());
				insertKontaktdaten.executeUpdate();
				i=sucheKontaktdatenID(schule);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * setzt die Kontaktdaten in die DB
	 * @param schule
	 * @return i
	 */
	public int sucheKontaktdatenID(Schule schule) 
	{
		int i = -1;
		try
		{
			selectKontaktdatenId.setString(1, schule.getKontaktdaten().getVorwahl());
			selectKontaktdatenId.setString(2, schule.getKontaktdaten().getTelefonnr());
			selectKontaktdatenId.setString(3, schule.getKontaktdaten().getFax());
			selectKontaktdatenId.setString(4, schule.getKontaktdaten().getEmail());
			selectKontaktdatenId.setString(5, schule.getKontaktdaten().getInternet());
			ResultSet rs= selectKontaktdatenId.executeQuery();
				
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * setzt die Schulleitung in die DB, falls diese noch nicht vorhanden sind, die Vorhandenheit 
	 * wird mit der Methode sucheSchulleitungID geprüft. Falls diese nämlich einen Wert größer 0 zurückgibt 
	 * sind die Werte schon vorhanden.
	 * @param schule
	 * @return i
	 */
	public int sucheSchulleitung(Schule schule)
	{
		int i = -1;
		try 
		{
			i = sucheSchulleitungsID(schule);
			if(i<0)
			{
				insertSchulleitung.setString(1, schule.getSchulleitung().getTitel());
				insertSchulleitung.setString(2, schule.getSchulleitung().getVname());
				insertSchulleitung.setString(3, schule.getSchulleitung().getNname());
				insertSchulleitung.executeUpdate();
				i = sucheSchulleitungsID(schule);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * setzt die Schulleitung in die DB
	 * @param schule
	 * @return i
	 */
	public int sucheSchulleitungsID(Schule schule) 
	{
		int i = -1;
		try
		{
			selectSchulleitungsId.setString(1, schule.getSchulleitung().getTitel());
			selectSchulleitungsId.setString(2, schule.getSchulleitung().getVname());
			selectSchulleitungsId.setString(3, schule.getSchulleitung().getNname());
			ResultSet rs= selectSchulleitungsId.executeQuery();
			
			while(rs.next())
			{
				i=rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * setzt die Adresse in die DB, falls diese noch nicht vorhanden sind, die Vorhandenheit 
	 * wird mit der Methode sucheAdressID geprüft. Falls diese nämlich einen Wert größer 0 zurückgibt 
	 * sind die Werte schon vorhanden.
	 * @param schule
	 * @return
	 */
	public int sucheAdresse(Schule schule)
	{
		int i = -1;
		try 
		{
			i = sucheAdressID(schule);
			if(i < 0)
			{
				insertAdresse.setString(1, schule.getAdress().getStrasse());
				insertAdresse.setString(2, schule.getAdress().getHausnr());
				insertAdresse.setString(3, schule.getAdress().getPlz());
				insertAdresse.setString(4, schule.getAdress().getOrt());
				
				insertAdresse.executeUpdate();
				i=sucheAdressID(schule);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * setzt die Adresse in die DB
	 * @param schule
	 * @return i
	 */
	public int sucheAdressID(Schule schule) 
	{
		int i = -1;
		try
		{
			selectAdressId.setString(1, schule.getAdress().getStrasse());
			selectAdressId.setString(2, schule.getAdress().getHausnr());
			selectAdressId.setString(3, schule.getAdress().getPlz());
			selectAdressId.setString(4, schule.getAdress().getOrt());
			
			ResultSet rs = selectAdressId.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * holt die Geodaten aus der Datenbank per Id
	 * @param id
	 * @return geo
	 */
	public String getGeom(int id) 
	{
		String geo = "";
		
		ResultSet result;
		try 
		{
			selectGebiet.setInt(1, id);
			result = selectGebiet.executeQuery();
			result.next();
			geo = result.getString(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return geo;
	}

	/**
	 * die Methode soll die Schulen in den jeweiligen Gebieten wieder zurückgeben
	 * @param idOrt
	 * @param idSchule
	 * @return schulen
	 */
	public Schulen getSchulenImGebiet(int idOrt, String idSchule) 
	{
		if(con == null)
		{
			con = getConnection();
		}
	
		Schulen schulen = new Schulen();
		String sql = "";
		
		if(idSchule.equals("alle"))
		{
			sql = "%e%";
		}
		else if(idSchule.equals("privatschule"))
		{
			sql = "Privat%";
		}
		else if(idSchule.equals("grundschule"))
		{
			sql = "Grundschule";
		}
		else if(idSchule.equals("oberschule"))
		{
			sql = "Oberschule";
		}
		else if(idSchule.equals("abendschule"))
		{
			sql = "Abend%";
		}
		else if(idSchule.equals("berufsschule"))
		{
			sql = "Berufsbildende%";
		}
		else if(idSchule.equals("durchgaengigesGym"))
		{
			sql = "Durchgängiges%";
		}
		else if(idSchule.equals("foerderzentrum"))
		{
			sql = "Förder%";
		}
		else if(idSchule.equals("gesundheitsschule"))
		{
			sql = "Schule für Gesundheit%";
		}
		else if(idSchule.equals("schulzentrum"))
		{
			sql = "Schulzentrum%";
		}
		else if(idSchule.equals("verwaltungsschule"))
		{
			sql = "Verwaltungsschule";
		}
		
		ResultSet result;
		try 
		{
			selectSchulenGebiet.setInt(1, idOrt);
			selectSchulenGebiet.setString(2, sql);

			result = selectSchulenGebiet.executeQuery();
			
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return schulen;
	}
	
	/**
	 * Methode um alle Schulen aus der Datenbank zu holen
	 * @return schulen
	 */
	public Schulen getAlleSchulen()
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		
		try 
		{
			PreparedStatement selectSchulen = con.prepareStatement("SELECT * FROM schule");
			ResultSet result = selectSchulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}

	/**
	 * holt die Foederzentren aus der Datenbank
	 */
	public Schulen getFoerderzentrum() 
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		
		try 
		{
			selectSchulen.setString(1, "Förder%");
			ResultSet result = selectSchulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}

	/**
	 * holt die Abend/Erwachsenenschulen aus der Datenbank
	 */
	public Schulen getAbendErwachsenenSchule() 
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		
		try 
		{
			selectSchulen.setString(1, "Abend%");
			ResultSet result = selectSchulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}

	/**
	 * holt die Durchgaengige Gymnasien aus der Datenbank
	 */
	public Schulen getDurchgaengigesGymnasium() 
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		
		try 
		{
			selectSchulen.setString(1, "Durchgängiges%");
			ResultSet result = selectSchulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}

	/**
	 * holt die Schulzentren aus der Datenbank
	 */
	public Schulen getSchulzentrum() 
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		
		try 
		{
			selectSchulen.setString(1, "Schulzentrum%");
			ResultSet result = selectSchulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}

	/**
	 * holt die Berufsbildeneschulen aus der Datenbank
	 */
	public Schulen getBerufsbildeneSchule() 
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		
		try 
		{
			selectSchulen.setString(1, "Berufsbildende%");
			ResultSet result = selectSchulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}

	/**
	 * holt die Gesundheitsschulen aus der Datenbank
	 */
	public Schulen getGesundheitsschule() 
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		
		try 
		{
			selectSchulen.setString(1, "Schule für Gesundheit%");
			ResultSet result = selectSchulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}

	/**
	 * holt die Verwaltungsschulen aus der Datenbank
	 */
	public Schulen getVerwaltungsschule() 
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		
		try 
		{
			selectSchulen.setString(1, "Verwaltungsschule");
			ResultSet result = selectSchulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}

	
	
	/**
	 * diese Methode gibt alle Ganztagsschulen zurück
	 * @param id
	 * @param idSchule
	 * @return schulen
	 */
	public Schulen getGanztagschulen(String id, String idSchule)
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		String sql = "";
		
		if(idSchule.equals("alle"))
		{
			sql = "%e%";
		}
		else if(idSchule.equals("privat"))
		{
			sql = "Privat%";
		}
		else if(idSchule.equals("grund"))
		{
			sql = "Grundschule";
		}
		else if(idSchule.equals("ober"))
		{
			sql = "Oberschule";
		}
		else if(idSchule.equals("abend"))
		{
			sql = "Abend%";
		}
		else if(idSchule.equals("beruf"))
		{
			sql = "Berufsbildende%";
		}
		else if(idSchule.equals("gym"))
		{
			sql = "Durchgängiges%";
		}
		else if(idSchule.equals("foerderzentrum"))
		{
			sql = "Förder%";
		}
		else if(idSchule.equals("gesundheit"))
		{
			sql = "Schule für Gesundheit%";
		}
		else if(idSchule.equals("schulzentrum"))
		{
			sql = "Schulzentrum%";
		}
		else if(idSchule.equals("verwaltung"))
		{
			sql = "Verwaltungsschule";
		}
		
		
		try 
		{
			selectGanztagsschulen.setString(1, id);
			selectGanztagsschulen.setString(2, sql);

			ResultSet result = selectGanztagsschulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}
	
	/**
	 * holt alle Grundschulen aus der Datenbank
	 */
	public Schulen getGrundschulen() 
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		
		try 
		{
			PreparedStatement selectSchulen = con.prepareStatement("SELECT schulnr, name, zusatz, adressnr, schulleitungnr, kontaktnr, schule.schultypnr, schultypen.schulform \r\n" + 
					"FROM schule, schultypen \r\n" + 
					"WHERE schule.schultypnr = schultypen.schultypnr and schultypen.schulform = 'Grundschule'");
			ResultSet result = selectSchulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}

	/**
	 * holt alle Privatschulen aus der Datenbank
	 */
	public Schulen getPrivatschulen()
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		
		try 
		{
			PreparedStatement selectSchulen = con.prepareStatement("SELECT schulnr, name, zusatz, adressnr, schulleitungnr, kontaktnr, schule.schultypnr, schultypen.schulform \r\n" + 
					"FROM schule, schultypen \r\n" + 
					"WHERE schule.schultypnr = schultypen.schultypnr and schultypen.schulform like 'Privat%'");
			ResultSet result = selectSchulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}

	/**
	 * holt alle Oberschulen aus der Datenbank
	 */
	public Schulen getOberschulen() 
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		Schulen schulen = new Schulen();
		
		try 
		{
			PreparedStatement selectSchulen = con.prepareStatement("SELECT schulnr, name, zusatz, adressnr, schulleitungnr, kontaktnr, schule.schultypnr, schultypen.schulform \r\n" + 
					"FROM schule, schultypen \r\n" + 
					"WHERE schule.schultypnr = schultypen.schultypnr and schultypen.schulform = 'Oberschule'");
			ResultSet result = selectSchulen.executeQuery();
			while(result.next())
			{
				Schule schule = new Schule();
				schule.setSchulnr(result.getString(1));
				schule.setName(result.getString(2));
				schule.setZusatz(result.getString(3));
				schule.setAdress(getAdresse(result.getInt(4)));
				schule.setSchulleitung(getSchulleitung(result.getInt(5)));
				schule.setKontaktdaten(getKontaktdaten(result.getInt(6)));
				schule.setSchultypen(getSchultypen(result.getInt(7)));
				schulen.add(schule);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return schulen;
	}
	
}
