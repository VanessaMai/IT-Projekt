package roomreservationservice.shared.bo;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * Die Klasse BusinessObject stellt die Basisklasse aller in diesem Projekt für 
 * die Umsetzung des Fachkonzepts relevanten Klassen dar. Jedes “BusinessObject” besitzt eine Nummer, 
 * die in der relationalen Datenbank als Primärschlüssel bezeichnet wird. 
 * Jedes BusinessObject ist als Serializable gekennzeichnet, so dass es automatisch in eine textuelle Form überführt wird und 
 * z.B. zwischen Client und Server transporTert wird. Auch besitzt jedes BusinessObject einen Zeitpunkt, 
 * zu dem ein Objekt erstellt wird.
 * @author AnhDuc
 *
 */

public abstract class BusinessObject implements Serializable {
	
	/**
	 * Eine eindeutige ID die sich ändert, wenn an dem serialisierten Objekt Änderungen durchgeführt wurden.
	 * Somit kann überprüft werden, ob ein Objekt eine Zustandsänderung erfahren hat.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Die eindeutige Identifikationsnummer einer Instanz dieser Klasse.
	 */
	private int id = 0;
	/**
	 * Der Zeitpunkt, zu dem das jeweilige Objekt erstellt wurde.
	 */
	private Date creationDate;
	
	/**
	 * Auslesen der ID.
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Setzen der ID
	 */
	public void setId(int newId){
		this.id = newId;
	}
	
	/**
	 * Auslesen des Erstellungszeitpunktes.
	 */
	public Date getCreationDate(){
		return this.creationDate;
	}
	
	/**
	 * Setzen des Erstellungszeitpunktes.
	 */
	public void setCreationDate(Date newCreationDate){
		this.creationDate = newCreationDate;
	}
	
	/**
	 * Erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz.
	 * Dies kann in Subklassen überschrieben werden.
	 */
	public String toString(){
		/*
	     * Wir geben den Klassennamen gefolgt von der ID des Objekts zurück.
	     */
		return this.getClass().getName() + " #" + this.getId();
	}
	
	/**
	 * Feststellen der inhaltlichen Gleichheit zweier BusinessObject,Objekte. 
	 * Die Gleichheit beschränkt sich auf die identische ID.
	 */
	public boolean equals(Object o){
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
	 * Erzeugen einer Zahl, die für das BusinessObject charakteristisch ist.
	 */
	public int hashCode(){
		int hash = id;
		return hash;
	}
}

