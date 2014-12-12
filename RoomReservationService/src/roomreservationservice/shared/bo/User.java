package roomreservationservice.shared.bo;

import java.sql.Timestamp;

/**
 * Klasse, die einen User im Raumplanungssystem abbilden soll.
 */
public class User extends BusinessObject {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 6111824407973818207L;

	/**
	 * Der Konstruktor der Klasse User. Dieser soll verwendet werden, wenn ein komplett neues Objekt erstellt werden
	 * soll, für das der Erstellungszeitpunkt der Aufruf dieses Konstruktors sein soll. Wenn das Objekt schon existiert
	 * und nur aus wiederhergestellt werden soll (zum Beispiel aus der DB), dann bitte den Konstruktor verwenden, der
	 * zusätzlich den originalen Erstellugnszeitpunkt entgegen nimmt.
	 * 
	 * @param firstName
	 *            Der Vorname des Nutzers.
	 * @param lastName
	 *            Der Nachname des Nutzers.
	 * @param email
	 *            Die E-Mailadresse des Nutzers.
	 * @param accessToken
	 *            Das Accesstoken des Nutzers.
	 * @param accessTokenSecret
	 *            Das Accesstoken Secret des Nutzers.
	 */
	public User(String firstName, String lastName, String email, String accessToken, String accessTokenSecret) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
		setCreationDate();
	}

	/**
	 * Ein zweiter Konstruktor für die Klasse User. Dieser soll verwendet werden, wenn ein Objekt bereits ein
	 * Erstellungsdatum und eine ID besitzt und diese nicht neu erstellt werden müssen. Dies der Fall, wenn ein Objekt
	 * aus einem DB-Eintrag wiederhergestellt werden soll.
	 * 
	 * @param firstName
	 *            Der Vorname des Nutzers.
	 * @param lastName
	 *            Der Nachname des Nutzers.
	 * @param email
	 *            Die E-Mailadresse des Nutzers.
	 * @param accessToken
	 *            Das Accesstoken des Nutzers.
	 * @param accessTokenSecret
	 *            Das Accesstoken Secret des Nutzers.
	 * @param creationDate
	 *            Der originale Erstellnugszeitpunkt des Objekts.
	 * @param userID
	 *            Die ID des Objekts aus der DB.
	 */
	public User(String firstName, String lastName, String email, String accessToken, String accessTokenSecret,
			Timestamp creationDate, int userID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
		setCreationDate(creationDate);
		this.id = userID;
	}

	// Attribute des Users

	/**
	 * Vorname des Nutzers.
	 */
	private String firstName = "unknown";

	/**
	 * Nachname des Nutzers.
	 */
	private String lastName = "unknown";

	/**
	 * E-Mailadresse des Nutzers.
	 */
	private String email = "unknown";

	/**
	 * Access Token des Nutzers.
	 */
	private String accessToken = "unknown";

	/**
	 * Access Token Secret des Nutzers.
	 */
	private String accessTokenSecret = "unknown";

	// Methoden

	/**
	 * Gibt den Vornamen aus.
	 * 
	 * @return firstName Der Vorname des Nutzers.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setzt den Vornamen auf einen neuen Wert.
	 * 
	 * @param firstName
	 *            Der neue Vorname des Nutzers.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Ausgeben des Nachnamens.
	 * 
	 * @return lastName Der Nachname des Nutzers.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setzt den Nachnamen auf einen neuen Wert.
	 * 
	 * @param lastName
	 *            Der neue Nachname des Nutzers.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Ausgeben der E-Mailadresse.
	 * 
	 * @return email Die E-Mailadresse des Nutzers.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setzt die Mailadersse auf einen neuen Wert.
	 * 
	 * @param email
	 *            Die neue E-Mailadresse des Nutzers.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Ausgeben des AccessTokens.
	 * 
	 * @return accessToken Das Accesstoken des Nutzers.
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Setzen des AccessTokens.
	 * 
	 * @param accessToken
	 *            Das neue Accesstoken des Nutzers.
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Ausgeben des Accesstoken Secrets
	 * 
	 * @return accessTokenScret Das Accesstoken Scret des Nutzers.
	 */
	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	/**
	 * Setzen des Accesstoken Secrets.
	 * 
	 * @param accessTokenSecret
	 *            Das neue Access Token Secret des Nutzers.
	 */
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	/**
	 * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die
	 * <code>toString()</code>-Methode der Superklasse erzeugt wird und um den Vor- und Nachnamen des Nutzers, sowie der
	 * Mailadresse und dem AccessToken und dem dazugehörtigen Secret des Nutzers erweitert wird.
	 */
	@Override
	public String toString() {
		return super.toString() + "Vorname: " + getFirstName() + lineBreak + "Nachname: " + getLastName() + lineBreak
				+ "Email: " + getEmail() + lineBreak + "Accesstoken: " + getAccessToken() + lineBreak
				+ "Accesstoken Secret: " + getAccessTokenSecret() + lineBreak;

	}

	
	/**
	 * Feststellen der <em>inhaltlichen</em> Gleichheit zweier <code>User</code> -Objekte.
	 */
	@Override
	public boolean equals(Object o) {
		/*
		 * Abfragen, ob ein Objekt ungl. NULL ist und ob ein Objekt gecastet werden kann, sind immer wichtig!
		 */
		if (o != null && o instanceof User) {
			User c = (User) o;
			try {
				return super.equals(c);
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return false;
	}

}