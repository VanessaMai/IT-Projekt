package roomreservationservice.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/*
 *  GUI-Klasse für die folgenden "Seiten": Raum wählen, Raum modifizieren/löschen,
 *  Raum erstellen. (Siehe GUI-Prototyp 2.0)
 *  @author: Gertz, Steven
 */

public class RoomManagement extends Reaction {

	@Override
	protected String getHeadlineText() {
		return "Raumverwaltung";
	}

	@Override
	protected void run() {

		/*Raum erstellen
		 */

		// Button zum Erstellen eine neuen Raums
		Button createRoomButton = new Button("Raum erstellen",
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						// Loescht vorherige Anzeige auf contentPanel.
						RootPanel.get("Content").clear();

						TextBox giveName = new TextBox();
						TextBox giveCapacity = new TextBox();

						// Button um die gewaehlten Werte zu uebernehmen & den
						// Vorgang abzuschließen
						Button confirmButton = new Button("Raum erstellen",
								new ClickHandler() {
									public void onClick(ClickEvent event) {
										// Loescht vorherige Anzeige auf
										// contentPanel.
										RootPanel.get("Content").clear();
										// TODO DB-Connect mit Erfolgsmeldung
									}
								});
						/*
						 * Anlegen einer 5x2 Matrix (Grid). Diese wird Mit Namen
						 * der Widgets und den Widgets mit ihren Inhalten
						 * gefuellt.
						 */
						Grid createRoomGrid = new Grid(5, 2);
						RootPanel.get("Content").add(createRoomGrid);

						Label giveNameLabel = new Label("Namen eingeben");
						createRoomGrid.setWidget(0, 0, giveNameLabel);
						createRoomGrid.setWidget(0, 1, giveName);

						Label giveCapacityLabel = new Label("Kapazität eingeben");
						createRoomGrid.setWidget(1, 0, giveCapacityLabel);
						createRoomGrid.setWidget(1, 1, giveCapacity);

						Label confirmButtonLabel = new Label("");
						createRoomGrid.setWidget(2, 0, confirmButtonLabel);
						createRoomGrid.setWidget(2, 1, confirmButton);
					}
				});

		/*Raum modifizieren
		 */

		// Button zum Modifizieren eines neuen Raums
		Button modifyRoomButton = new Button("Raum modifizieren",
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						// Loescht vorherige Anzeige auf contentPanel.
						RootPanel.get("Content").clear();

						// Methodenaufruf um ListBox-Widget fuer Raeume zu erzeugen
						ListBox roomList = Widgets.createRoomList();
						
						//Erzeugen neuer Textboxen
						TextBox modifyName = new TextBox();
						TextBox modifyCapacity = new TextBox();

						// Button um die gewaehlten Werte zu uebernehmen & den
						// Vorgang abzuschließen
						Button confirmButton = new Button("Raum modifizieren",
								new ClickHandler() {
									public void onClick(ClickEvent event) {
										// Loescht vorherige Anzeige auf
										// contentPanel.
										RootPanel.get("Content").clear();
										// TODO DB-Connect mit Erfolgsmeldung
									}
								});
						
						/*
						 * Anlegen einer 5x2 Matrix (Grid). Diese wird Mit Namen
						 * der Widgets und den Widgets mit ihren Inhalten
						 * gefuellt.
						 */
						Grid modifyRoomGrid = new Grid(5, 2);
						RootPanel.get("Content").add(modifyRoomGrid);

						Label selectRoomLabel = new  Label ("Raum auswählen");
						modifyRoomGrid.setWidget(0, 0, selectRoomLabel);
						modifyRoomGrid.setWidget(0, 1, roomList);
						
						Label modifyNameLabel = new Label("Name ändern");
						modifyRoomGrid.setWidget(1, 0, modifyNameLabel);
						modifyRoomGrid.setWidget(1, 1, modifyName);

						Label modifyCapacityLabel = new Label("Kapazität ändern");
						modifyRoomGrid.setWidget(2, 0, modifyCapacityLabel);
						modifyRoomGrid.setWidget(2, 1, modifyCapacity);

						Label confirmButtonLabel = new Label("");
						modifyRoomGrid.setWidget(3, 0, confirmButtonLabel);
						modifyRoomGrid.setWidget(3, 1, confirmButton);
					}
				});
		/*
		 * Anlegen einer 2x3 Matrix (Grid). Diese wird Mit Namen der Widgets und
		 * den Widgets mit ihren Inhalten gefuellt.
		 */
		Grid RoomManagementGrid = new Grid(3, 2);
		this.add(RoomManagementGrid);

		Label createRoomLabel = new Label("Erstellen Sie Ihren eigenen Raum");
		RoomManagementGrid.setWidget(0, 0, createRoomLabel);
		RoomManagementGrid.setWidget(0, 1, createRoomButton);
		
		Label modifyRoomLabel = new Label("Modifizieren Sie ihren eigenen Raum");
		RoomManagementGrid.setWidget(1, 0, modifyRoomLabel);
		RoomManagementGrid.setWidget(1, 1, modifyRoomButton);

	}
}
