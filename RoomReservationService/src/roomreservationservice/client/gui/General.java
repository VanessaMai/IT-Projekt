package roomreservationservice.client.gui;


import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

/*
 *  GUI-Klasse für die folgenden "Seiten": Anmeldungs- & Registrierungsbutton, Anmeldung mit Textfeldern,
 *  Registrierung mit Textfeldern, Startseite, Abmelden. (Siehe GUI-Prototyp 2.0)
 *  @author: Steven Gertz, Nicholas Gertz, Elisabeth Mazurkiewicz
 */

public class General {


	protected String getHeadlineText() {
		return "Anmelden";
	}
	
	//Neues Eingabefeld fuer E-Mail und Passwort.
	final TextBox email = new TextBox();	
	final PasswordTextBox password = new PasswordTextBox();
	
	//neuer Button fuer die Anmeldung.
	final Button submit = new Button ("Anmelden");
	
	//Hinzufuegen der Widgets zum contentPanel
	contentPanel.add(email);
	contentPanel.add(password);
	contentPanel.add(submit);


		
	registrationButton2.addClickHandler(new ClickHandler();
	
	}
	/*TODO: ClickHandler implementieren
	 */

	