# Toolbuilding

**Schritt für Schritt - Anleitung:**

1) Installieren einer [SQL-Datenbank](https://www.postgresql.org/download/)
2) Herunterladen des Projekts
3) Erstellen einer Datenbank

![image](https://user-images.githubusercontent.com/46625416/82147798-8686ac80-9850-11ea-8a90-6645a76c11c8.png)

 4) Erweitern der Datenbank um PostGIS
 - in einem Query Tool Fenster folgenden Code eingeben
 >CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;
 5) Die Datein im Ordner **Datenbank** in Datenbank importieren
 Beispiel PostgreSQL:
 - Query Tool unter Tools öffnen
 
 ![image](https://user-images.githubusercontent.com/46625416/82147853-fc8b1380-9850-11ea-8317-bef941d526f0.png)
 
 - Datei importieren/öffnen
     - Reihenfolge:
         - create_tables.sql
         - bremen_gesamt.sql
         - stadtteile_bremen.sql
 
 ![image](https://user-images.githubusercontent.com/46625416/82147960-9652c080-9851-11ea-8164-7e66727e1c65.png)
 
 - Starten
 
 ![image](https://user-images.githubusercontent.com/46625416/82148025-1842e980-9852-11ea-8cb2-5f275119b3f6.png)
 
 6)
