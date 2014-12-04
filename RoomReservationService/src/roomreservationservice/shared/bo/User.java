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

/**
 * Vorname des Nutzers.
 */
private String firstName = "";

/**
 * Nachname des Nutzers.
 */
private String lastName = "";

/**
 * E-Mailadresse des Nutzers.
 */
private String email = "";

/**
 * Access Token des Nutzers.
 */
private String accessToken = "";

/**
 * Access Token Secret des Nutzers.
 */
private String accessTokenSecret = "";


//Methoden

/**
 * Gibt den Vornamen aus. 
 */
public String getFirstName() {
	return firstName;
}

/**
 * Setzt den Vornamen auf einen neuen Wert.
 * @param firstName	Neuer Vorname.
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

/**
 * Ausgeben des Nachnamens.
 */
public String getLastName() {
	return lastName;
}

/**
 * Setzt den Nachnamen auf einen neuen Wert.
 * @param lastName	Neuer Nachname.
 */
public void setLastName(String lastName) {
	this.lastName = lastName;
}

/**
 * Ausgeben der E-Mailadresse.
 */
public String getEmail() {
	return email;
}

/**
 * Setzt die Mailadersse auf einen neuen Wert.
 * @param email	Neue E-Mailadresse.
 */
public void setEmail(String email) {
	this.email = email;
}

/**
 * Ausgeben des AccessTokens.
 */
public String getAccessToken() {
	return accessToken;
}

/**
 * Setzen des AccessTokens.
 * @param accessToken	Neues Access Token.
 */
public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
}

/**
 * Ausgeben des Accesstoken Secrets.
 */
public String getAccessTokenSecret() {
	return accessTokenSecret;
}

/**
 * Setzen des Accesstoken Secrets.
 * @param accessTokenSecret	Neues Access Token Secret.
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
  return super.toString() + "Vorname: " + this.getFirstName() + "Nachname: "+ this.getLastName() + "Email: " + getEmail() + "Accesstoken: " + getAccessToken() + "Accesstoken Secret: " + getAccessTokenSecret();

	}

/**
 * Feststellen der <em>inhaltlichen</em> Gleichheit zweier
 * <code>User</code>-Objekte.
 */
@Override
public boolean equals(Object o) {
  /*
   * Abfragen, ob ein Objekt ungl. NULL ist und ob ein Objekt gecastet werden
   * kann, sind immer wichtig!
   */
  if (o != null && o instanceof User) {
  	User c = (User) o;
    try {
      return super.equals(c);
    }
    catch (IllegalArgumentException e) {
      return false;
    }
  }
  return false;
}


}