package roomreservationservice.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
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
				 * Wert im Kalender setzen wenn der Nutzer ein Datum waehlt

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
					
				/*Anlegen einer 5x2 Matrix (Grid). Diese wird Mit Namen der Widgets und den Widgets mit ihren
				 * Inhalten gefuellt.
				 */
				Grid createEventGrid = new Grid(5, 2);
				RootPanel.get("Content").add(createEventGrid);
					
				Label datePickerLabel = new Label("Datum wählen");
				createEventGrid.setWidget(0, 0, datePickerLabel);
				createEventGrid.setWidget(0, 1, datePicker);
					
				Label roomNumberLabel = new Label("Raum wählen");
				createEventGrid.setWidget(1, 0, roomNumberLabel);
				createEventGrid.setWidget(1, 1, roomNumber);
					
				Label timeSlotLabel = new Label("Zeitslot wählen");
				createEventGrid.setWidget(2, 0, timeSlotLabel);
				createEventGrid.setWidget(2, 1, timeSlot);	
				
				Label userInvitationLabel = new Label("Nutzer einladen");
				createEventGrid.setWidget(3, 0, userInvitationLabel);
				createEventGrid.setWidget(3, 1, userInvitation);	

				Label confirmButtonLabel = new Label("");
				createEventGrid.setWidget(4, 0, confirmButtonLabel);
				createEventGrid.setWidget(4, 1, confirmButton);
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
				
				/*Anlegen einer 3x2 Matrix (Grid). Diese wird Mit Namen der Widgets und den Widgets mit ihren
				 * Inhalten gefuellt.
				 */
				Grid eventOverviewGrid = new Grid(3, 2);
				RootPanel.get("Content").add(eventOverviewGrid);
					
				Label calendarWeekLabel = new Label("Kalenderwoche wählen");
				eventOverviewGrid.setWidget(0, 0, calendarWeekLabel);
				eventOverviewGrid.setWidget(0, 1, calendarWeek);
					
				Label roomNumberLabel = new Label("Raum wählen");
				eventOverviewGrid.setWidget(1, 0, roomNumberLabel);
				eventOverviewGrid.setWidget(1, 1, roomNumber);
				
				Label confirmButtonLabel = new Label("");
				eventOverviewGrid.setWidget(2, 0, confirmButtonLabel);
				eventOverviewGrid.setWidget(2, 1, confirmButton);
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
				
				// Liste um Belegung zu waehlen
				ListBox eventName = new ListBox();
				eventName.addItem("Medienmärkte");
				eventName.addItem("ADS");
				eventName.addItem("Software Engineering");
				eventName.addItem("Datenbanken");
				eventName.addItem("Organisation");
				//TODO DB-Connect
				
				// Button um die gewaehlten Werte zu uebernehmen & den Vorgang abzuschließen
				Button confirmButton = new Button ("bearbeiten", new ClickHandler() {
					public void onClick(ClickEvent event) {
						//Loescht vorherige Anzeige auf contentPanel.
						RootPanel.get("Content").clear ();
				
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
				
				/*Anlegen einer 7x2 Matrix (Grid). Diese wird Mit Namen der Widgets und den Widgets mit ihren
				 * Inhalten gefuellt.
				 */
				Grid editEventButtonGrid = new Grid(7, 2);
				RootPanel.get("Content").add(editEventButtonGrid);
					
				Label datePickerLabel = new Label("Datum wählen");
				editEventButtonGrid.setWidget(0, 0, datePickerLabel);
				editEventButtonGrid.setWidget(0, 1, datePicker);
					
				Label roomNumberLabel = new Label("Raum wählen");
				editEventButtonGrid.setWidget(1, 0, roomNumberLabel);
				editEventButtonGrid.setWidget(1, 1, roomNumber);
					
				Label timeSlotLabel = new Label("Zeitslot wählen");
				editEventButtonGrid.setWidget(2, 0, timeSlotLabel);
				editEventButtonGrid.setWidget(2, 1, timeSlot);	
				
				Label userInvitationLabel = new Label("Nutzer einladen");
				editEventButtonGrid.setWidget(3, 0, userInvitationLabel);
				editEventButtonGrid.setWidget(3, 1, userInvitation);
				
				Label userAcceptanceLabel = new Label("Zusagen");
				editEventButtonGrid.setWidget(4, 0, userAcceptanceLabel);
				editEventButtonGrid.setWidget(4, 1, userAcceptance);

				Label confirmButtonLabel = new Label("");
				editEventButtonGrid.setWidget(5, 0, confirmButtonLabel);
				editEventButtonGrid.setWidget(5, 1, confirmButton);
				
				Label deleteButtonLabel = new Label("");
				editEventButtonGrid.setWidget(6, 0, deleteButtonLabel);
				editEventButtonGrid.setWidget(6, 1, deleteButton);
				
				}
			});
				
				/*Anlegen einer 2x2 Matrix (Grid). Diese wird Mit Namen der Widgets und den Widgets mit ihren
				 * Inhalten gefuellt.
				 */
				Grid chooseEventButtonGrid = new Grid(2, 2);
				RootPanel.get("Content").add(chooseEventButtonGrid);
					
				Label eventNameLabel = new Label("Belegung wählen");
				chooseEventButtonGrid.setWidget(0, 0, eventNameLabel);
				chooseEventButtonGrid.setWidget(0, 1, eventName);
				
				Label confirmButtonLabel = new Label("");
				chooseEventButtonGrid.setWidget(1, 0, confirmButtonLabel);
				chooseEventButtonGrid.setWidget(1, 1, confirmButton);
				
			}
		});
		
		/*Anlegen einer 2x3 Matrix (Grid). Diese wird Mit Namen der Widgets und den Widgets mit ihren
		 * Inhalten gefuellt.
		 */
		Grid EventManagementGrid = new Grid(3, 2);
		this.add(EventManagementGrid);
			
		Label createEventLabel = new Label("Erstellen Sie Ihre eigene Belegung");
		EventManagementGrid.setWidget(0, 0, createEventLabel);
		EventManagementGrid.setWidget(0, 1, createEventButton);
			
		Label eventOverviewLabel = new Label("Erstellen Sie eine Übersicht über Belegungen");
		EventManagementGrid.setWidget(1, 0, eventOverviewLabel);
		EventManagementGrid.setWidget(1, 1, eventOverviewButton);
			
		Label editEventLabel = new Label("Bearbeiten oder Löschen Sie eine Belegung");
		EventManagementGrid.setWidget(2, 0, editEventLabel);
		EventManagementGrid.setWidget(2, 1, editEventButton);					
	}
	
	/*TODO Belegungsuebersicht
	 * Kalender darstellen durch Tabelle mit Spalten= Wochentag + Zeilen = Uhrzeit
	 */
	
}
