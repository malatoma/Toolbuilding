package daoimpl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.http.Part;

import org.apache.tomcat.jni.File;

import com.sun.faces.component.visit.PartialVisitContext;

import daten.Gebiet;
import daten.Gebiete;
import daten.Projekt;
import daten.User;
import datenbank.DBKommu;
import interfaces.DB_Interface;

/**
 * Die Klasse DBImpl implementiert das DB_Interface und das Interface SchulenSuche
 * @author Jessica, Marco
 *
 */
public class DBImpl extends DBKommu implements DB_Interface
{
	private String fehlermeldungReg;
	private Connection con;
	
	private PreparedStatement createUser;
	private PreparedStatement createGegenstaende;
	private PreparedStatement createKarten;
	private PreparedStatement createPopups;
	private PreparedStatement createProjekte;
	
	private PreparedStatement insertUser;
	private PreparedStatement insertGegenstaende;
	private PreparedStatement insertKarten;
	private PreparedStatement insertPopups;
	private PreparedStatement insertProjekte;
	
	private PreparedStatement selectGebiet;
	private PreparedStatement selectProjekt;
	private PreparedStatement selectUser;
	private PreparedStatement selectUsername;
	
//	private PreparedStatement selectAdressId;
//	private PreparedStatement selectAdresse; 
//	private PreparedStatement updateAdresseGeo; 
	
//	private PreparedStatement selectGebiet;
	
	public DBImpl()
	{
		
	}
	
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
			
			//TODO: muss noch an die aktuellen Tabellen angepasst werden
			createUser = con.prepareStatement("CREATE TABLE IF NOT EXISTS adresse(AdressNr serial NOT NULL, Strasse varchar(80), HausNr varchar(30), PLZ varchar(30), Ort varchar(30), Geodaten geometry(Point,4326), PRIMARY KEY(AdressNr))");
			createGegenstaende = con.prepareStatement("CREATE TABLE IF NOT EXISTS schulleitung(SchulleitungNr serial NOT NULL, Titel varchar(10), Vorname varchar(30), Nachname varchar(30), PRIMARY KEY(SchulleitungNr))");
			createKarten = con.prepareStatement("CREATE TABLE IF NOT EXISTS kontaktdaten(KontaktNr serial NOT NULL, Vorwahl varchar(20), TelefonNr varchar(30), FAX varchar(30), Email varchar(100), Internet varchar(100), PRIMARY KEY(KontaktNr))");
			createPopups = con.prepareStatement("CREATE TABLE IF NOT EXISTS schultypen(SchultypNr serial NOT NULL, Schulform varchar(100), Ganztagsschule varchar(1), Ganztagsform varchar(100), PRIMARY KEY(SchultypNr))");
			createProjekte = con.prepareStatement("CREATE TABLE IF NOT EXISTS schule(SchulNr varchar(20), Name varchar(300), Zusatz varchar(300), AdressNr int, SchulleitungNr int, KontaktNr int, SchultypNr int, "
															+ "PRIMARY KEY(SchulNr), FOREIGN KEY(AdressNr) REFERENCES Adresse, FOREIGN KEY(SchulleitungNr) REFERENCES Schulleitung, FOREIGN KEY(KontaktNr) REFERENCES Kontaktdaten, FOREIGN KEY(SchultypNr) REFERENCES Schultypen)");
			
			createUser.executeUpdate();
			createGegenstaende.executeUpdate();
			createKarten.executeUpdate();
			createPopups.executeUpdate();
			createProjekte.executeUpdate();
			
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
			
			//TODO: statements müssen umgeschrieben werden
			insertUser = con.prepareStatement("INSERT INTO users (vorname, nachname, geburtstag, strasse, hausnummer, plz, ort, username, passwort) Values (?,?,?,?,?,?,?,?,?)");
			insertProjekte = con.prepareStatement("INSERT INTO projekte (name, beschreibung, erstellt_von, file, status) Values (?,?,?,?,?)");
			/*insertSchule = con.prepareStatement("INSERT INTO schule (SchulNr, Name, Zusatz, AdressNr,SchulleitungNr, KontaktNr, SchultypNr) Values (?,?,?,?,?,?,?)");
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
												+ "WHERE schule.schultypnr = schultypen.schultypnr and schultypen.schulform like ?");*/
			selectGebiet = con.prepareStatement("SELECT ST_asGeoJSON(karten) FROM karten WHERE karten.kartenid = ?");
			selectProjekt = con.prepareStatement("SELECT *,encode(file,'base64') AS efile FROM projekte");
			selectUser = con.prepareStatement("SELECT username FROM users WHERE username = ? AND passwort = ?");
			selectUsername = con.prepareStatement("SELECT username FROM users WHERE username = ?");
			/*selectSchulenGebiet = con.prepareStatement("SELECT DISTINCT schulnr, schule.name, zusatz, schule.adressnr, schulleitungnr, kontaktnr, schule.schultypnr "
												+ "FROM schule "
												+ "INNER JOIN gebiete ON gebiete.id = ? "
												+ "INNER JOIN schultypen ON schule.schultypnr = schultypen.schultypnr and schultypen.schulform like ? "
												+ "INNER JOIN adresse ON schule.adressnr = adresse.adressnr "
												+ "WHERE ST_DWithin(gebiete.polygon, adresse.geodaten, 0.002) ");
			
			selectGanztagsschulen = con.prepareStatement("SELECT schulnr, name, zusatz, adressnr, schulleitungnr, kontaktnr, schule.schultypnr\r\n" + 
													"FROM schule, schultypen\r\n" + 
													"WHERE schule.schultypnr = schultypen.schultypnr AND schultypen.ganztagsschule = ? AND schultypen.schulform like ?");*/
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
		String query = "SELECT kartenid, name FROM karten";
		
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
	 * checkt, ob der Username bereits vergeben ist
	 * 
	 * Hinweis: Fehlermeldung bei result = null kann ignoriert werden; User wird abgespeichert
	 * @param user
	 */
	public void checkUsername(User user)
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		System.out.println("checkUsername()");
		
		ResultSet result;
		try 
		{
			System.out.println("username: " + user.getUsername());
			selectUsername.setString(1, user.getUsername());
			System.out.println("selectUsername");
			result = selectUsername.executeQuery();
			if(result.next())
			{
				setFehlermeldungReg("Der Username ist bereits vergeben.");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
		System.out.println(getFehlermeldungReg());
	}
	
	public void insertUser(User user)
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		System.out.println("insertUser()");
		try 
		{
			insertUser.setString(1, user.getVorname());
			insertUser.setString(2, user.getNachname());
			insertUser.setString(3, user.getGeburtstag());
			insertUser.setString(4, user.getStrasse());
			insertUser.setInt(5, user.getHausnr());
			insertUser.setString(6, user.getPlz());
			insertUser.setString(7, user.getOrt());
			insertUser.setString(8, user.getUsername());
			insertUser.setString(9, user.getPasswort());
			
			insertUser.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void insertProjekt(Projekt projekt)
	{
		try 
		{
			InputStream fis=projekt.getBilderpart().getInputStream();
			insertProjekte.setString(1, projekt.getProjektname());
			insertProjekte.setString(2, projekt.getProjektbeschreibung());
			insertProjekte.setDate(3, projekt.getErstelldatum());
			insertProjekte.setBinaryStream(4, fis);
			insertProjekte.setBoolean(5, true);
			insertProjekte.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public LinkedList<Projekt> selectAllProjekt(){
		LinkedList<Projekt> sProjekt = new LinkedList<Projekt>();
		
		ResultSet result;
		Projekt tempProjekt = new Projekt();
		try {
			result = selectProjekt.executeQuery();
			while(result.next()) {
				tempProjekt.setBesitzer(result.getString("name"));
				tempProjekt.setBilder( result.getString("efile"));
				tempProjekt.setErstelldatum(result.getDate("erstellt_von"));
				tempProjekt.setProjektbeschreibung(result.getString("beschreibung"));
				tempProjekt.setProjektname(result.getString("name"));
				tempProjekt.setProjektstatus(result.getBoolean("status"));
				sProjekt.add(tempProjekt);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sProjekt;
		
	}

	/**
	 * holt die Geodaten aus der Datenbank per Id
	 * @param id
	 * @return geo
	 */
	public String getGeom(int id) 
	{
		if(con == null)
		{
			con = getConnection();
		}
		
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
	
	public String anmelden(User user)
	{
		if(con == null)
		{
			con = getConnection();
		}
		
		String username = "";
		String fehlermeldung = "Der Username oder das Passwort ist falsch.";
		
		ResultSet result;
		try 
		{
			selectUser.setString(1, user.getUsername());
			selectUser.setString(2, user.getPasswort());
			result = selectUser.executeQuery();
			result.next();
			username = result.getString(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		if(username != null)
		{
			return username;
		}
		else
		{
			return fehlermeldung;
		}
		
	}


	public String getFehlermeldungReg() 
	{
		return fehlermeldungReg;
	}

	public void setFehlermeldungReg(String fehlermeldungReg) 
	{
		this.fehlermeldungReg = fehlermeldungReg;
	}

}
