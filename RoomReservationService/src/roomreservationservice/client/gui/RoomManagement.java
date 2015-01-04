package roomreservationservice.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

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
		// TODO Auto-generated method stub
		
		/*TODO RaumVerwaltun Hauptseite:
		 * CellList: Raum waehlen
		 * 1. Button + ClickHandler: Raum modifizieren
		 * 2. Button + ClickHandler: Raum erstellen
		
	/*Anlegen einer 3x2 Matrix (Grid). Diese wird Buttons gefühlt.
	 */
	Grid RoomManagementGrid = new Grid(3, 2);
	this.add(RoomManagementGrid);
	
	/* Buttons um auf die Seiten "Raum modifizieren" und "Raum erstellen" zu gelangen
	 */
	Button modify = new Button ("Raum modifizieren");
	Button setup = new Button ("Raum erstellen");
	
	/*Buttons im Grid platzieren
	 */
	RoomManagementGrid.setWidget(0, 0, modify);
	RoomManagementGrid.setWidget(0, 1, setup);

	/*Clickhandler für die Buttons
	 */
	
	  modify.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		      // handle the click event
		    }
		  });
	
	setup.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			// handle the click event
			}
		});
	}
}

	
	
	

	/*TODO Raum modifizieren und loeschen:
	 * 1. TextBox: Namen aendern
	 * 2. TextBox: Kapazitaet aendern
	 * 1. Button + ClickHandler: Raum modifizieren
	 * 2. Button + ClickHandler: Raum loeschen
	 */
	
	
	
	/*TODO Raum erstellen:
	 * 1. TextBox: Namen eingeben
	 * 2. TextBox: Kapazitaet aendern
	 * 1. Button + ClickHandler: Raum erstellen
	 */

