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
		final Button registrationButton = new Button ("Registrieren");
		final Button authentificationButton = new Button ("Authentifizieren");
		
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
				TextBox forename = new TextBox();
				TextBox surename = new TextBox();
				TextBox email = new TextBox();				
				
				//Neues Eingabefeld fuer Passwort.
				PasswordTextBox password = new PasswordTextBox();
				
				//neue Buttons fuer weitere Handlungen
				Button registration = new Button ("Registrieren");
				Button cancel = new Button ("Abbrechen");
				
				//Hinzufuegen der Widgets zum mainPanel
				mainPanel.add(forename);
				mainPanel.add(surename);
				mainPanel.add(email);
				mainPanel.add(password);
				mainPanel.add(registration);
				mainPanel.add(cancel);
				
				
								
			}
		});
		
		// Main panel mit der HTML Hostpage "RoomReservationService.html" verlinken.
		RootPanel.get("roomReservationService").add(mainPanel);
	
}
}