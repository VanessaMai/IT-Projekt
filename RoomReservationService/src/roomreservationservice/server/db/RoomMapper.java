package roomreservationservice.server.db;

import java.sql.*;
import java.util.Vector;
import roomreservationservice.shared.bo.Room;


/**
 * Mapper-Klasse, die <code>Room</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierfür gibt es veschiedene Methoden zum Auslesen,
 * Ändern und Löschen aus der Datenbank.
 * 
 */

public class RoomMapper {
	
	/**
	   * Die Klasse RoomMapper wird nur einmal instantiiert (Singleton).
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   */
	private static RoomMapper roomMapper = null;

	/**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	  protected RoomMapper() {
	  }


	 	  
	  /**
	   * Diese Methode liefert die zu verwendende Instanz des RoomMappers zurück.
	   * <p>
	   * Wenn der RoomMapper gebraucht wird, muss er über diese Methode <code>RoomMapper.roomMapper</code>
	   * aufgerufen werden. Das erzeugen einer neuen Instanz wäre falsch. Es soll immer nur die hier erstellte benutzt werden.
	   * Die Methode prüft, ob bereits eine Instanz vorhanden ist. Falls ja, gibt sie diese zurück, falls nicht, wird eine neue erstellt.
	   * 
	   * 
	   * @return Das <code>RoomMapper</code>-Objekt.
	   */
	  public static RoomMapper roomMapper() {
	    if (roomMapper == null) {
	    	roomMapper = new RoomMapper();
	    }
	    return roomMapper;
	  }
	  
	  
	  /**
	   * Suchen eines Raums anhand seiner ID. Zurückgegebe wird genau der eine Raum,
	   * dem die ID zugewiesen ist.
	   * 
	   * @param id Primärschlüsselattribut (->DB)
	   * @return Raum-Objekt, das dem übergebenen Schlüssel entspricht, null bei
	   *         nicht vorhandenem DB-Tupel.
	   */
	  public Room findByKey(int id) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet resultSet = stmt.executeQuery("SELECT * FROM rooms "
	          + "WHERE id=" + id);

	      /*
	       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
	       * werden. Prüfe, ob ein Ergebnis vorliegt.
	       */
	      if (resultSet.next()) {
	        // Ergebnis-Tupel in Objekt umwandeln
	        Room room = new Room();
	        room.setId(resultSet.getInt("id"));
	        room.setRoomCapacity(resultSet.getInt("capacity"));
	        room.setRoomName(resultSet.getString("name"));
	        return room;
	      }
	    }
	    // SQL Exception abfangen, sollte etwas schiefgehen.
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    }

	    return null;
	  }
	  
	  
	  /**
	   * Auslesen aller Räume.
	   * 
	   * @return Ein Vektor mit Room-Objekten, die sämtliche Konten
	   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
	   *         oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<Room> findAll() {
	    Connection con = DBConnection.connection();

	    // Ergebnisvektor vorbereiten
	    Vector<Room> result = new Vector<Room>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet resultSet = stmt.executeQuery("SELECT * FROM rooms "
	          + " ORDER BY id");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
	      while (resultSet.next()) {
	        Room room = new Room();
	        room.setId(resultSet.getInt("id"));
	        room.setRoomCapacity(resultSet.getInt("capacity"));
	        room.setRoomName(resultSet.getString("name"));
	        
	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(room);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }
	  
}
