package roomreservationservice.shared.bo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Abstrakte Superklasse aller Businessobjects.
 */
public abstract class BusinessObject implements Serializable{

	
  private static final long serialVersionUID = 999999999999999L;

  /**
   * Eine Instanz des Kalenders wird in der Variable <code>calendar</code> gespeichert. Das ist nötig, um den Erstellungszeitpunkt setzen zu können.
   */
  Calendar calendar = Calendar.getInstance();
  // In <code>Date</code> wird der Erstellungszeitpunkt gespeichert.
  private Date creationDate;
  
  
  
  
  /**
   * Auslesen des Erstellungszeitpunkts.
   */
  public Date getCreationDate(){
	  return this.creationDate;
  }
  
  /**
   * Die eindeutige Identifikationsnummer einer Instanz dieser Klasse.
   */
  protected int id = 0;

  /**
   * Auslesen der ID.
   */
  public int getId() {
    return this.id;
  }

  /**
   * Setzen der ID
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Textuelle Darstellung jeweiligen Klasseninstanz erzeugen (Klasse, Name und ID).
   */
  @Override
public String toString() {
    /*
     * Rückgabe des Klassennamens, der ID und des Erstellungszeitpunkts des Objekts.
     */
    return this.getClass().getName() + " #" + this.id + " Erstellungszeitpunkt: " + this.getCreationDate();
  }

  /**
   * Feststellen der <em>inhaltlichen</em> Gleichheit zweier
   * <code>BusinessObject</code>-Objekte. Die Gleichheit ist hier auf eine
   * identische ID beschränkt.
   */
  @Override
public boolean equals(Object o) {
    /*
     * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet
     * werden kann, sind immer wichtig!
     */
    if (o != null && o instanceof BusinessObject) {
      BusinessObject bo = (BusinessObject) o;
      try {
        if (bo.getId() == this.id)
          return true;
      }
      catch (IllegalArgumentException e) {
        /*
         * Falls eine Exception geworfen wird, wird false zurückgegeben.
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
