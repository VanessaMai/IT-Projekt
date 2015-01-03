package roomreservationservice.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

/*
 *  GUI-Klasse für die folgenden "Seiten": Belegung erstellen, Belegungsübersicht,
 *  Belegung editieren/löschen (Siehe GUI-Prototyp 2.0)
 *  @author: Gertz, Steven
 */

public class EventManagement extends Reaction{

	@Override
	protected String getHeadlineText() {
		return "EventManagement";
	}

	@Override
	protected void run() {
		
		/*
		 * erforderliche Buttons fuer die Belegungsverwaltung
		 */
		
		// Belegung erstellen
		Button createEventButton = new Button ("Belegung erstellen", new ClickHandler() {
			public void onClick(ClickEvent event) {
				//Loescht vorherige Anzeige auf contentPanel.
				RootPanel.get("Content").clear ();
				
				/*
				 * erforderliche Widgets um eine Belegung zu erstellen
				 */
				
				// Kalender um Tag zu waehlen
				DatePicker datePicker = new DatePicker();
				
				/*
				 TODO funktioniert nur mit DB-Connect oder?
				 * Wert im Kalender setzen wenn der Nutzer ein Dateum waehlt

			    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			      public void onValueChange(ValueChangeEvent<Date> event) {
			        Date date = event.getValue();
			        String dateString = DateTimeFormat.getMediumDateFormat().format(date);
			        text.setText(dateString);
			      }
			    });

			    Setzen des Default-Wertes
			    
			    datePicker.setValue(new Date(), true);
				*/
				
				// Liste um Raum zu waehlen
				ListBox roomNumber = new ListBox();
				roomNumber.addItem("Raum 01");
				roomNumber.addItem("Raum 02");
				roomNumber.addItem("Raum 03");
				roomNumber.addItem("Raum 04");
				roomNumber.addItem("Raum 05");
				//TODO DB-Connect
				
				// Liste um Zeitslot zu waehlen
				ListBox timeSlot = new ListBox();
				timeSlot.addItem("09 bis 11 Uhr");
				timeSlot.addItem("11 bis 13 Uhr");
				timeSlot.addItem("13 bis 15 Uhr");
				timeSlot.addItem("15 bis 17 Uhr");
				//TODO DB-Connect
				
				// Liste um Nutzer einzuladen
				ListBox userInvitation = new ListBox();
				userInvitation.addItem("User 01");
				userInvitation.addItem("User 02");
				userInvitation.addItem("User 03");
				//TODO DB-Connect

				// Button um die gewaehlten Werte zu uebernehmen & den Vorgang abzuschließen
				Button confirmButton = new Button ("Erstellen", new ClickHandler() {
					public void onClick(ClickEvent event) {
						//Loescht vorherige Anzeige auf contentPanel.
						RootPanel.get("Content").clear ();
						//TODO DB-Connect mit Erfolgsmeldung	
					}
				});
					
				//Hinzufuegen der Widgets zum contentPanel
				RootPanel.get("Content").add(datePicker);
				RootPanel.get("Content").add(roomNumber);
				RootPanel.get("Content").add(timeSlot);
				RootPanel.get("Content").add(userInvitation);
				RootPanel.get("Content").add(confirmButton);
			}
		});
		
		//Belegungsübersicht
		Button eventOverviewButton = new Button ("Belegungsübersicht", new ClickHandler() {
			public void onClick(ClickEvent event) {
				//Loescht vorherige Anzeige auf contentPanel.
				RootPanel.get("Content").clear ();
				
				/*
				 * erforderliche Widgets um eine Belegungsübersicht zu erstellen
				 */
				
				// Liste um Raum zu waehlen
				ListBox roomNumber = new ListBox();
				roomNumber.addItem("Raum 01");
				roomNumber.addItem("Raum 02");
				roomNumber.addItem("Raum 03");
				roomNumber.addItem("Raum 04");
				roomNumber.addItem("Raum 05");
				//TODO DB-Connect
				
				// Liste um Kalenderwoche oder aehnliches. zu waehlen
				ListBox calendarWeek = new ListBox();
				calendarWeek.addItem("11");
				calendarWeek.addItem("21");
				calendarWeek.addItem("31");
				calendarWeek.addItem("42");
				calendarWeek.addItem("51");
				//TODO DB-Connect
				
				// Button um die gewaehlten Werte zu uebernehmen & den Vorgang abzuschließen
				Button confirmButton = new Button ("Übersicht anzeigen", new ClickHandler() {
					public void onClick(ClickEvent event) {
						//Loescht vorherige Anzeige auf contentPanel.
						RootPanel.get("Content").clear ();
						//TODO DB-Connect mit Erfolgsmeldung
						
						//TODO Uebersichtstabelle mit , Vanessa fragen.	
					}
				});
				
				//Hinzufuegen der Widgets zum contentPanel
				RootPanel.get("Content").add(roomNumber);
				RootPanel.get("Content").add(calendarWeek);
				RootPanel.get("Content").add(confirmButton);
			}
		});
		
		//Belegung bearbeiten und loeschen
		Button editEventButton = new Button ("Belegung bearbeiten", new ClickHandler() {
			public void onClick(ClickEvent event) {
				//Loescht vorherige Anzeige auf contentPanel.
				RootPanel.get("Content").clear ();
				
				/*
				 * erforderliche Widgets um eine Belegung zu bearbeiten oder zu loeschen
				 */
				
				// Kalender um Tag zu waehlen
				DatePicker datePicker = new DatePicker();
				
				/*
				 TODO funktioniert nur mit DB-Connect oder?
				 * Wert im Kalender setzen wenn der Nutzer ein Dateum waehlt

			    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			      public void onValueChange(ValueChangeEvent<Date> event) {
			        Date date = event.getValue();
			        String dateString = DateTimeFormat.getMediumDateFormat().format(date);
			        text.setText(dateString);
			      }
			    });

			    Setzen des Default-Wertes
			    
			    datePicker.setValue(new Date(), true);
				*/
				
				// Liste um Raum zu waehlen
				ListBox roomNumber = new ListBox();
				roomNumber.addItem("Raum 01");
				roomNumber.addItem("Raum 02");
				roomNumber.addItem("Raum 03");
				roomNumber.addItem("Raum 04");
				roomNumber.addItem("Raum 05");
				//TODO DB-Connect
				
				// Liste um Zeitslot zu waehlen
				ListBox timeSlot = new ListBox();
				timeSlot.addItem("09 bis 11 Uhr");
				timeSlot.addItem("11 bis 13 Uhr");
				timeSlot.addItem("13 bis 15 Uhr");
				timeSlot.addItem("15 bis 17 Uhr");
				//TODO DB-Connect
				
				// Liste um Nutzer einzuladen
				ListBox userInvitation = new ListBox();
				userInvitation.addItem("User 01");
				userInvitation.addItem("User 02");
				userInvitation.addItem("User 03");
				//TODO DB-Connect
				
				// Liste um eine Teilnehmeruebersicht zu erhalten
				ListBox userAcceptance = new ListBox();
				userAcceptance.addItem("User 01");
				userAcceptance.addItem("User 02");
				userAcceptance.addItem("User 03");
				//TODO DB-Connect
				
				// Button um die gewaehlten Werte zu uebernehmen & den Vorgang abzuschließen
				Button confirmButton = new Button ("Änderung übernehmen", new ClickHandler() {
					public void onClick(ClickEvent event) {
						//Loescht vorherige Anzeige auf contentPanel.
						RootPanel.get("Content").clear ();
						//TODO DB-Connect mit Erfolgsmeldung	
					}
				});
				
				// Button um die Belegung zu löschen
				Button deleteButton = new Button ("Belegung löschen", new ClickHandler() {
					public void onClick(ClickEvent event) {
						//Loescht vorherige Anzeige auf contentPanel.
						RootPanel.get("Content").clear ();
						//TODO DB-Connect mit Erfolgsmeldung	
					}
				});
					
				//Hinzufuegen der Widgets zum contentPanel
				RootPanel.get("Content").add(datePicker);
				RootPanel.get("Content").add(roomNumber);
				RootPanel.get("Content").add(timeSlot);
				RootPanel.get("Content").add(userInvitation);
				RootPanel.get("Content").add(userAcceptance);
				RootPanel.get("Content").add(confirmButton);
				RootPanel.get("Content").add(deleteButton);
			}
		});
		
		//Hinzufuegen der Buttons zum contentPanel
		RootPanel.get("Content").add(createEventButton);
		RootPanel.get("Content").add(eventOverviewButton);
		RootPanel.get("Content").add(editEventButton);
	
	}
	
	/*TODO Belegungsuebersicht
	 * Kalender darstellen durch Tabelle mit Spalten= Wochentag + Zeilen = Uhrzeit
	 */
	
}
