package roomreservationservice.shared.bo;
/**
 * Klasse, die einen User im Raumplanungssystem abbilden soll.
 */
public class User extends BusinessObject {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 6111824407973818207L;
	


// Attribute des Users
private String firstName = "";
private String lastName = "";
private String email = "";
private String accessToken = "";
private String accessTokenSecret = "";


//Methoden

/**
 * Gibt den Vornamen aus. 
 * @return fistName
 */
public String getFirstName() {
	return firstName;
}

/**
 * Setzt den Vornamen auf einen neuen Wert.
 * @param firstName
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

/**
 * Ausgeben des Nachnamens.
 * @return lastName
 */
public String getLastName() {
	return lastName;
}

/**
 * Setzt den Nachnamen auf einen neuen Wert.
 * @param lastName
 */
public void setLastName(String lastName) {
	this.lastName = lastName;
}

/**
 * Ausgeben der E-Mailadresse.
 * @return email
 */
public String getEmail() {
	return email;
}

/**
 * Setzt die Mailadersse auf einen neuen Wert.
 * @param email
 */
public void setEmail(String email) {
	this.email = email;
}

/**
 * Ausgeben des AccessTokens.
 * @return accessToken
 */
public String getAccessToken() {
	return accessToken;
}

/**
 * Setzen des AccessTokens.
 * @param accessToken
 */
public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
}

/**
 * Ausgeben des Accesstoken Secrets.
 * @return accessTokenSecret
 */
public String getAccessTokenSecret() {
	return accessTokenSecret;
}

/**
 * Setzen des Accesstoken Secrets.
 * @param accessTokenSecret
 */
public void setAccessTokenSecret(String accessTokenSecret) {
	this.accessTokenSecret = accessTokenSecret;
}

/**
 * Erzeugung der textuellen Darstellung der jeweiligen Instanz und Erweiterung des Textes, der durch die <code>toString()</code>-Methode
 * der Superklasse erzeugt wird und um den Vor- und Nachnamen des Nutzers, sowie der Mailadresse und dem AccessToken und dem dazugehörtigen Secret des Nutzers erweitert wird.
 */
@Override
public String toString() {
  return this.getClass().getName() + " #" + this.id + " Erstellungszeitpunkt: " + this.getCreationDate() + "Vorname: " + this.getFirstName() + "Nachname: "+ this.getLastName() + "Email: " + getEmail() + "Accesstoken: " + getAccessToken() + "Accesstoken Secret: " + getAccessTokenSecret();

}


}