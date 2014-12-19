package roomreservationservice.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>. 
 */

public class RoomReservationService implements EntryPoint {

	 /**
	   * "Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benï¿½tigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen." Bankprojekt
	   */
	public void onModuleLoad() {
		
		//Erste Seite. Hauptseite. User hat wahlt zwischen Registrieren und Authentifizieren
		final VerticalPanel mainPanel = new VerticalPanel();
		
		//neue Widgets erzeugen. Jeweils ein Button fuer Registrieren und Authentifizieren.
		Button registrationButton = new Button ("Registrieren");
		Button authentificationButton = new Button ("Authentifizieren");
		
		//Hinzufuegen der Buttons zum VerticalPanel "mainPanel"
		mainPanel.add(registrationButton);
		mainPanel.add(authentificationButton);
		
		/*
	     * Unter welchem Namen können wir den Button durch die CSS-Datei des
	     * Projekts formatieren?
	     * @author:thies
	     */
		//registrationButton.setStylePrimaryName("")
		//authentificationButton.setStylePrimaryName("")
		
		/* Verhalten, wenn auf Button geklickt wird.
		 * Hierzu registrieren wir einen ClickHandler, dessen
	     * onClick()-Methode beim Mausklick auf den zugehörigen Button aufgerufen
	     * wird.
	     */
		
		registrationButton.addClickHandler(new ClickHandler() {
		      
			public void onClick(ClickEvent event) {
				
				//Loescht vorherige Anzeige auf mainPanel.
				mainPanel.clear ();
				//TODO Englische Bezeichnungen fuer Textboxen??
				
				//Neues Eingabefeld fuer Vorname, Nachname, Email.
				final TextBox forename = new TextBox();
				final TextBox surename = new TextBox();
				final TextBox email = new TextBox();				
				
				//Neues Eingabefeld fuer Passwort.
				PasswordTextBox password = new PasswordTextBox();
				
				//neue Buttons fuer weitere Handlungen
				final Button registrationButton2 = new Button ("Registrieren");
				final Button cancelButton = new Button ("Abbrechen");
				
				//Hinzufuegen der Widgets zum mainPanel
				mainPanel.add(forename);
				mainPanel.add(surename);
				mainPanel.add(email);
				mainPanel.add(password);
				mainPanel.add(registrationButton2);
				mainPanel.add(cancelButton);
				
				registrationButton2.addClickHandler(new ClickHandler() {
				      
					public void onClick(ClickEvent event) {
						mainPanel.clear ();
						//TODO als Klasse auslagern kann fuer Login auch verwendet werden!
						/*TODO unterscheiden zwischen Registration und Anmeldung, 
						 * da bei der Anmeldung Email und Passwort ueberprueft werden muessen!
						 */
						
						//Neue Buttons fuer die Startseite nach der Registrierung oder Login
						final Button roomAdministration= new Button ("Raumverwaltung");
						final Button userAdministration= new Button ("Nutzerverwaltung");
						final Button reportBuild= new Button ("Raumverwaltung");		
						
						//Hinzufuegen der Widgets zum mainPanel
						mainPanel.add(roomAdministration);
						mainPanel.add(userAdministration);
						mainPanel.add(reportBuild);
								
			}
		});
		
		// Main panel mit der HTML Hostpage "RoomReservationService.html" verlinken.
		RootPanel.get("roomReservationService").add(mainPanel);
};
		});}}