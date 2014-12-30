package roomreservationservice.client.gui;

import Showcase;

import com.google.gwt.event.dom.client.ClickEvent;
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

public class General extends RoomReservationService {

	/*Erste Seite.User hat Wahl zwischen Registrieren und 
	 * Authentifizieren.
	 */
	public General (){
	
	//neue Widgets erzeugen. Jeweils ein Button fuer Registrieren und Authentifizieren.
	Button registrationButton = new Button ("Registrieren");
	Button authentificationButton = new Button ("Authentifizieren");
		
	//Hinzufuegen der Buttons zum VerticalPanel "contentPanel"
	contentPanel.add(registrationButton);
	contentPanel.add(authentificationButton);
		
	registrationButton.addClickHandler(new ClickHandler() {
	      
		public void onClick(ClickEvent event) {
			
			//Loescht vorherige Anzeige auf contentPanel.
			contentPanel.clear ();
			//TODO Ueberschrift einfuegen
			
			//Neues Eingabefeld fuer Vorname, Nachname, Email.
			final TextBox forename = new TextBox();
			final TextBox surename = new TextBox();
			final TextBox email = new TextBox();				
			
			//Neues Eingabefeld fuer Passwort.
			PasswordTextBox password = new PasswordTextBox();
			
			//neue Buttons fuer weitere Handlungen
			final Button registrationButton2 = new Button ("Registrieren");
			final Button cancelButton = new Button ("Abbrechen");
			
			//Hinzufuegen der Widgets zum contentPanel
			contentPanel.add(forename);
			contentPanel.add(email);
			contentPanel.add(password);
			contentPanel.add(registrationButton2);
			contentPanel.add(cancelButton);	
		}
		});
		
				
			registrationButton2.addClickHandler(new ClickHandler() {
			      
				public void onClick(ClickEvent event) {
					contentPanel.clear ();
					//TODO als Klasse auslagern kann fuer Authentifizierung auch verwendet werden!
					/*TODO unterscheiden zwischen Registration und Anmeldung, 
					 * da bei der Anmeldung Email und Passwort ueberprueft werden muessen!
					 */
					//TODO Ueberschrift setzen
					
					/*TODO ClickHandler fuer InvitationManagement, EventManagement, ReportManagement,
					 * RoomManagement und UserManagement
					 * muessen in den jeweiligen Klassen festgelegt werden.								
					 */
							
		}
	});
	
			cancelButton.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event) {
					//TODO Leitung zur ersten Seite. Hauptseite. Zeile 17.
				
				}});
			
		;

	
	
	authentificationButton.addClickHandler(new ClickHandler() {
	      
		public void onClick(ClickEvent event) {
			contentPanel.clear ();
			//Neues Eingabefeld fuer Email.
			final TextBox email = new TextBox();
					
			//Neues Eingabefeld fuer Passwort.
			PasswordTextBox password = new PasswordTextBox();
			
			//neue Buttons fuer weitere Handlungen
			final Button subsriptionButton = new Button ("Anmelden");
			final Button cancelButton = new Button ("Abbrechen");
			
			//Hinzufuegen der Widgets zum contentPanel
			contentPanel.add(email);
			contentPanel.add(password);
			contentPanel.add(subsriptionButton);
			contentPanel.add(cancelButton);
			/*TODO Verwenden des Clickhandlers von registrationButton2 (z.85), 
			 * aber noch keine Ahnung wie.
			 */
			/*TODO Verwenden des Clickhandlers von cancelButton (z.115), 
			 * aber noch keine Ahnung wie.
			 */
			
			
		}
	});
	}}
	


		
