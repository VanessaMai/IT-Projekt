package roomreservationservice.shared.bo;
/**
 * Klasse, die eine Einladung im Raumplanungssystem abbilden soll.
 */
public class Invitation extends BusinessObject {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 2578942449203500945L;

	
	
	
	/**
	 * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die <code>toString()</code>-Methode
	 * der Superklasse erzeugt wird und um erweitert wird.
	 */
	@Override
	public String toString() {
	  return this.getClass().getName() + " #" + this.id + " Erstellungszeitpunkt: " + this.getCreationDate() + "Vorname: " + this.getFirstName() + "Nachname: "+ this.getLastName() + "Email: " + getEmail() + "Accesstoken: " + getAccessToken() + "Accesstoken Secret: " + getAccessTokenSecret();

}
