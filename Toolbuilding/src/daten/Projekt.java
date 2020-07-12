package daten;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;

import javax.servlet.http.Part;
import javax.websocket.Decoder.BinaryStream;

import org.apache.tomcat.jni.File;

public class Projekt {

	private String projektname;
	private String projektbeschreibung;
	private boolean projektstatus;
	private Date erstelldatum;
	private String besitzer;
	private Part bilderpart;
	private InputStream bilder;
	
	public String getProjektname() {
		return projektname;
	}
	public void setProjektname(String projektname) {
		this.projektname = projektname;
	}
	public InputStream getBilder() {
		return bilder;
	}
	public void setBilder(InputStream bilder) {
		this.bilder = bilder;
	}
	public String getProjektbeschreibung() {
		return projektbeschreibung;
	}
	public void setProjektbeschreibung(String projektbeschreibung) {
		this.projektbeschreibung = projektbeschreibung;
	}
	public boolean isProjektstatus() {
		return projektstatus;
	}
	public void setProjektstatus(boolean projektstatus) {
		this.projektstatus = projektstatus;
	}
	public Date getErstelldatum() {
		return erstelldatum;
	}
	public void setErstelldatum(Date erstelldatum) {
		this.erstelldatum = erstelldatum;
	}
	public String getBesitzer() {
		return besitzer;
	}
	public void setBesitzer(String besitzer) {
		this.besitzer = besitzer;
	}
	public void currentDate() {
		erstelldatum = new Date(System.currentTimeMillis());
	}
	public Part getBilderpart() {
		return bilderpart;
	}
	public void setBilderpart(Part bilderpart) {
		this.bilderpart = bilderpart;
	}
}
