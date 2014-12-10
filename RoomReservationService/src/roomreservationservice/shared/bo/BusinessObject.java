package roomreservationservice.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Abstrakte Superklasse aller Businessobjects.
 */
public abstract class BusinessObject implements Serializable {
	/**
	 * Automatisch generierte UID.
	 */
	private static final long serialVersionUID = -2101367372041454677L;

	/**
	 * Eine Instanz des Kalenders wird in der Variable <code>calendar</code> gespeichert. Mithilfe dessen, kann die
	 * aktuelle Zeit ausgelesen werden und für den Erstellungszeitpunkt verwendet werden.
	 */
	Calendar calendar = Calendar.getInstance();

	/**
	 * In diesem Timestamp wird das Erstellungsdatum gespeichert. Der Grund, warum dies in einem Timestamp und nicht in
	 * einer Instanz von <code>java.util.Date</code> geschieht ist, dass in einem Timestamp neben des Datums auch
	 * Uhrzeiten geschrieben werden können. Zudem kann ein Timestamp leicht in eine MySQL Datenbank geschrieben und von
	 * dort wieder ausgelesen werden.
	 */
	private Timestamp creationDate;

	/**
	 * Auslesen des Erstellungszeitpunkts.
	 * 
	 * @return der Erstellungszeitpunkt.
	 */
	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	/**
	 * Setzen der jetzigen Zeit und des jetzigen Datums als Erstellungszeitpunktes der Instanz. Hierfür wird der
	 * aktuelle
	 */
	public void setCreationDate() {
		creationDate = new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * Setzen des übergebenen Timestamps als Erstellungszeitpunktes der Instanz.
	 * 
	 * @param timestamp
	 *            Der Timestamp, der als Erstellungszeitpunkt gesetzt werden soll. Zum Beispiel ausgelesen aus der DB.
	 */
	public void setCreationDate(Timestamp timestamp) {
		creationDate = timestamp;
	}

	/**
	 * Die eindeutige Identifikationsnummer einer Instanz dieser Klasse.
	 */
	protected int id = 0;

	/**
	 * Auslesen der ID.
	 * 
	 * @return Die ID des Objekts.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Setzen der ID
	 * 
	 * @param id
	 *            Die neue ID des Objekts.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Textuelle Darstellung jeweiligen Klasseninstanz erzeugen (Klasse, Name und ID). Diese wird durch die Unterklassen
	 * noch erweitert.
	 * 
	 * @return String mit den Infos der Klasseninstanz.
	 */
	@Override
	public String toString() {
		/*
		 * Rückgabe des Klassennamens, der ID und des Erstellungszeitpunkts des Objekts.
		 */
		return this.getClass().getName() + " #" + this.id + " Erstellungszeitpunkt: " + this.getCreationDate();
	}

	/**
	 * Feststellen der <em>inhaltlichen</em> Gleichheit zweier <code>BusinessObject</code>-Objekte. Die Gleichheit ist
	 * hier auf eine identische ID beschränkt.
	 */
	@Override
	public boolean equals(Object o) {
		/*
		 * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet werden kann, sind immer wichtig!
		 */
		if (o != null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			try {
				if (bo.getId() == this.id)
					return true;
			} catch (IllegalArgumentException e) {
				/*
				 * Falls eine Exception geworfen wird, wird zur Sicherheit false zurückgegeben.
				 */
				return false;
			}
		}
		/*
		 * Sind die beiden Objekt nicht gleicht, wird false zurückgeben.
		 */
		return false;
	}

	/**
	 * Erzeugen einer ganzen Zahl, die für das <code>BusinessObject</code> charakteristisch ist.
	 */
	@Override
	public int hashCode() {
		return this.id;
	}
}
