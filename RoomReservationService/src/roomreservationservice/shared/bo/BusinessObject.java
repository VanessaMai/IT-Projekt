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
     * Wir geben den Klassennamen gefolgt von der ID des Objekts zurück.
     */
    return this.getClass().getName() + " #" + this.id + " Erstellungszeitpunkt: " + this.getCreationDate();
  }

  /**
   * <p>
   * Feststellen der <em>inhaltlichen</em> Gleichheit zweier
   * <code>BusinessObject</code>-Objekte. Die Gleichheit wird in diesem Beispiel auf eine
   * identische ID beschränkt.
   * </p>
   * <p>
   * <b>ACHTUNG:</b> Die inhaltliche Gleichheit nicht mit dem Vergleich der
   * <em>Identität</em> eines Objekts mit einem anderen verwechseln!!! Dies
   * würde durch den Operator <code>==</code> bestimmt. Bei Unklarheit hierzu
   * können Sie nocheinmal in die Definition des Sprachkerns von Java schauen.
   * Die Methode <code>equals(...)</code> ist für jeden Referenzdatentyp
   * definiert, da sie bereits in der Klasse <code>Object</code> in einfachster
   * Form realisiert ist. Dort ist sie allerdings auf die simple Bestimmung der
   * Gleicheit der Java-internen Objekt-ID der verglichenen Objekte beschränkt.
   * In unseren eigenen Klassen können wir diese Methode überschreiben und ihr
   * mehr Intelligenz verleihen.
   * </p>
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
         * Wenn irgendetwas schief geht, dann geben wir sicherheitshalber false
         * zurück.
         */
        return false;
      }
    }
    /*
     * Wenn bislang keine Gleichheit bestimmt werden konnte, dann müssen
     * schließlich false zurückgeben.
     */
    return false;
  }
  
  /**
   * <p>
   * Erzeugen einer ganzen Zahl, die für das <code>BusinessObject</code> charakteristisch ist.
   * </p>
   * <p>
   * Zusammen mit <code>equals</code> sollte diese Methode immer definiert werden. Manche Java-Klassen
   * verwendenden <code>hashCode</code>, um initial ein Objekt (z.B. in einer Hashtable) zu identifizieren. Erst danach
   * würde mit <code>equals</code> festgestellt, ob es sich tatsächlich um das gesuchte Objekt handelt.
   */
  @Override
public int hashCode() {
	  return this.id;
  }

}
