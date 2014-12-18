package roomreservationservice.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>. 
 */
public class RoomReservationService implements EntryPoint {
	
 /*
  * Wir bereiten nun die Erstellung eines Navigators vor, der
  * zwei Schaltflächen (Buttons) für die Ausführung von Unterprogrammen
  * enthalten soll. Auswahl der Optionen Registrieren oder Authentifizieren fuer den Nutzer
  * 
  * Beide zur Auswahl stehenden Optionen loesen durch das Anklicken des jeweiligen Buttons Unterprogramme aus. 
  * Die Use-Cases des Systems darstellen. 
  *
  * Da unsere MainPage hauptsaechlich aus aneinander gereihten Buttons und Textfeldern
  * bestehen wird, bietet es sich an einen Vertical Pannel als Container zu verwenden.
  * Wir instanziieren nun einen VerticalPanel "mainPanel".
  */
	
	private VerticalPanel mainPanel = new VerticalPanel ();
	private HorizontalPanel hPanel = new HorizontalPanel ();
	
/*
 * "Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
 * zusichert, benötigen wir eine Methode
 * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
 * <code>main()</code>-Methode normaler Java-Applikationen." Bankprojekt
 */
	public void onModuleLoad() {
		
// MainPanel mit der HTML Hostpage "RoomReservationService.html" verlinken.
		RootPanel.get("roomReservationService").add(mainPanel);
		
		//Button der zur Registrierung fuehrt.
		Button registrierungsWeiterLeitungsButton = new Button ("Registrieren");
		
		//Button der zur Authentifizierung fuehrt.
		Button authentifizierungsWeiterLeitungsButton = new Button ("Authentifizieren");
		
//Formatierung der Buttons in der CSS-Datei
		registrierungsWeiterLeitungsButton.setStylePrimaryName("roomReservationService-button");
		authentifizierungsWeiterLeitungsButton.setStylePrimaryName("roomReservationService-button");
		
//Ausbau des Navigators "mainPanel" mit den beiden Buttons "Registrierung" und "Authentifizierung"
		mainPanel.add(registrierungsWeiterLeitungsButton);
		mainPanel.add(authentifizierungsWeiterLeitungsButton);
		
/*
 * Clickhandler werden festgelegt, damit den Buttons durch seine onClick()-Methode ein Verhalten zugeordnet wird, wenn 
 * mit der Maus auf sie geklickt wird.
 */
		
/*Ausbau des "mainPanel" mit den beiden Buttons "Registrierung" und "Authentifizierung"
 * mainPanel.add(registrierungsWeiterLeitungsButton);
 * mainPanel.add(authentifizierungsWeiterLeitungsButton);
 * 
 *Durch das Klicken auf Authentifizieren wird die Seite neu geladen. 
 *Es erscheinen drei Textfelder, ein PasswortTextfelt und zwei Buttons.
 * und ein Button.
 * @author: Mazurkiewicz
 */
		
		registrierungsWeiterLeitungsButton.addClickHandler(new ClickHandler (){
			public void onClick(ClickEvent event){
				
				mainPanel.clear();
									
				TextBox Vorname = new TextBox();
				TextBox Nachname = new TextBox();
				TextBox Email = new TextBox();
				PasswordTextBox Passwort = new PasswordTextBox();
				
				mainPanel.add(Vorname);
				mainPanel.add(Nachname);
				mainPanel.add(Email);
				mainPanel.add(Passwort);
				
				Button registrierungsButton = new Button("Registrieren");
				Button abbruchButton = new Button("Abbrechen");
				
				registrierungsButton.setStylePrimaryName("roomReservationService-button");
				abbruchButton.setStylePrimaryName("roomReservationService-button");
				
				mainPanel.add(hPanel);
				
				hPanel.add(abbruchButton);
				hPanel.add(registrierungsButton);
		
				registrierungsButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						
						mainPanel.clear();
						HorizontalPanel hPanel = new HorizontalPanel();
						mainPanel.add(hPanel);
			
				
						Button raumVerwaltungsButton = new Button("Raumverwaltung");
						Button nutzerVerwaltungsButton = new Button("Nutzerverwaltung");
						Button reportButton = new Button("Report erstellen");
						Button logoutButton = new Button("Logout");
						
						raumVerwaltungsButton.setStylePrimaryName("roomReservationService-button");
						nutzerVerwaltungsButton.setStylePrimaryName("roomReservationService-button");
						reportButton.setStylePrimaryName("roomReservationService-button");
						logoutButton.setStylePrimaryName("roomReservationService-button");

						hPanel.add(raumVerwaltungsButton);
						hPanel.add(nutzerVerwaltungsButton);
						hPanel.add(reportButton);
						hPanel.add(logoutButton);

						}
					});
							
		      		  
		abbruchButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
			mainPanel.clear();
			}
		});
		    }
	    });
	

				
/*Durch das Klicken auf Registrieren wird die Seite neu geladen. 
 * Es erscheinen zwei Textfelder und zwei Buttons.
 * und ein Button.
 * @author: Mazurkiewicz
 */
		
		authentifizierungsWeiterLeitungsButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				
				mainPanel.clear();
				
				VerticalPanel vPanel = new VerticalPanel ();
				
				mainPanel.add(vPanel);
				
				TextBox Email = new TextBox();
				PasswordTextBox Passwort = new PasswordTextBox();
				
				vPanel.add(Email);
				vPanel.add(Passwort);
				
				Button anmeldungsButton = new Button("Anmelden");
				anmeldungsButton.setStylePrimaryName("roomReservationService-button");
				
				vPanel.add(anmeldungsButton);
				
				anmeldungsButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						
					mainPanel.clear();
					HorizontalPanel hPanel = new HorizontalPanel();
					mainPanel.add(hPanel);
		
			
					Button raumVerwaltungsButton = new Button("Raumverwaltung");
					Button nutzerVerwaltungsButton = new Button("Nutzerverwaltung");
					Button reportButton = new Button("Report erstellen");
					Button logoutButton = new Button("Logout");
					
					raumVerwaltungsButton.setStylePrimaryName("roomReservationService-button");
					nutzerVerwaltungsButton.setStylePrimaryName("roomReservationService-button");
					reportButton.setStylePrimaryName("roomReservationService-button");
					logoutButton.setStylePrimaryName("roomReservationService-button");

					hPanel.add(raumVerwaltungsButton);
					hPanel.add(nutzerVerwaltungsButton);
					hPanel.add(reportButton);
					hPanel.add(logoutButton);

					}
				});

							
		      }
		    });
		
	}
}
