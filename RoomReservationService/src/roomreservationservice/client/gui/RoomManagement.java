package roomreservationservice.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/*
 *  GUI-Klasse für die folgenden "Seiten": Raum wählen, Raum modifizieren/löschen,
 *  Raum erstellen. (Siehe GUI-Prototyp 2.0)
 *  @author: Gertz, Steven
 */

public class RoomManagement extends Reaction{

	@Override
	protected String getHeadlineText() {
		return "Raumverwaltung";
	}

	@Override
	protected void run() {
	
	/*Anlegen einer 5x2 Matrix (Grid). Diese wird Buttons gefühlt.
	 */
	Grid RoomManagementGrid = new Grid(3, 2);
	RootPanel.get("Content").add(RoomManagementGrid);

	/*Raum erstellen
	 */
	Button editEventButton = new Button ("Raum erstellen", new ClickHandler() {
	public void onClick(ClickEvent event) {
	//Loescht vorherige Anzeige auf contentPanel.
	RootPanel.get("Content").clear ();
	
	/* Label zur Beschriftung der Textfelder erzeugen
	 */
	Label giveNameLabel = new Label("Name eingaben");
	Label giveCapacityLabel = new Label("Kapazität eingeben");
	
	/*Textboxen zur Eingabe der "Raum Modifizierung" und "Kapazitaet Aenderung" erzeugen. 
	 */
	TextBox giveName = new TextBox();
	TextBox giveCapacity = new TextBox();
	
	/*Widgets im Grid auf Positionen setzen
	 */
	RoomManagementGrid.setWidget(0, 0, giveNameLabel);
	RoomManagementGrid.setWidget(0, 1, giveName);
	RoomManagementGrid.setWidget(1, 0, giveCapacityLabel);
	RoomManagementGrid.setWidget(1, 1, giveCapacity);
	
	/* Button zur Uebermittlung der eingegeben Daten erzeugen.
	 */
	Button submit = new Button("Anwenden");
	
	/* Button im Grid auf Position setzen
	 */
	RoomManagementGrid.setWidget(2, 1, submit);
	
	/*Clickhandler für die den Button
	 */
	  submit.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		    	
		    //Loescht vorherige Anzeige auf contentPanel.
			RootPanel.get("Content").clear ();
		    }
		  });
	}
	
	
	/*Raum modifizieren
	 */
	MODIFIZIEREN
	
	Button editEventButton = new Button ("Raum modifizieren", new ClickHandler() {
		public void onClick(ClickEvent event) {
		//Loescht vorherige Anzeige auf contentPanel.
		RootPanel.get("Content").clear ();
		
	/*Raum auswählen
	 * Bereich leeren
	 */
	
	/* Label zur Beschriftung der Textfelder erzeugen
	 */
	Label changeNameLabel = new Label("Name ändern");
	Label changeCapacityLabel = new Label("Kapazität ändern");
	
	/*Textboxen zur Eingabe der "Raum Modifizierung" und "Kapazitaet Aenderung" erzeugen. 
	 */
	TextBox changeName = new TextBox();
	TextBox changeCapacity = new TextBox();
	
	/*Widgets im Grid auf Positionen setzen
	 */
	RoomManagementGrid.setWidget(0, 0, changeNameLabel);
	RoomManagementGrid.setWidget(0, 1, changeName);
	RoomManagementGrid.setWidget(1, 0, changeCapacityLabel);
	RoomManagementGrid.setWidget(1, 1, changeCapacity);
	
	/* Button zur Uebermittlung der eingegeben Daten erzeugen.
	 */
	Button submit = new Button("Anwenden");
	
	/* Button im Grid auf Position setzen
	 */
	RoomManagementGrid.setWidget(2, 1, submit);
	
	/*Clickhandler für die den Button
	 */
	  submit.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		    	
		    //Loescht vorherige Anzeige auf contentPanel.
			RootPanel.get("Content").clear ();
		    }
		  });
	}

	/*Erfolgsmeldung ausgeben
	 */


	
	
	
	/*TODO Raum erstellen:
	 * 1. TextBox: Namen eingeben
	 * 2. TextBox: Kapazitaet aendern
	 * 1. Button + ClickHandler: Raum erstellen
	 */


