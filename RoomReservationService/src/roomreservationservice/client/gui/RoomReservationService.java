package roomreservationservice.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>. 
 */


public class RoomReservationService implements EntryPoint {
	
	/* Um die Containerkomponentenstruktur unseres Interfaces darzustellen,
	 * benoetigen wir Container in welchen die Inhalte jeder Seiten dargestellt werden.
	 * 
	 * Dabei haben wir fuer die Navigationsleiste einen HorizontalPanel gewaehlt,
	 * der in der HTML unseres Projektes verlinkt ist und somit auf jeder Seite zu sehen
	 * ist.
	 * 
	 * danach haben wir einen VerticalPanel eingefuegt, der den jeweiligen Content,
	 * der Seite anzeigt und somit als Container dient.
	 * @author:Mazurkiewicz, Elisabeth und Gertz, Steven
	 */
	
	/* Der Navigator ist als einspaltige Aneinanderreihung von Buttons
     * realisiert. Daher bietet sich ein HorizontalPanel an.
     */
	private HorizontalPanel navPanel = new HorizontalPanel();
	
	/* Benoetigtes Panel fuer die weitere Darstellung:
	 * VerticalPanel als Container fuer alle weiteren Panels und Inhalte.
	 */
	private VerticalPanel mainPanel = new VerticalPanel();	
	
		 /**
	   * "Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, ben�tigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen." Bankprojekt
	   */
	@Override
	public void onModuleLoad() {
		
		/* navPanel mit der HTML Hostpage "RoomReservationService.html" verlinken
		*/
		RootPanel.get("navigator").add(navPanel);

		//neue Widgets erzeugen. Buttons fuer das HorizontalPanel "navPanel"
		Button homepageButton = new Button ("Startseite");
		Button userManagement = new Button ("Nutzerverwaltung");
		Button roomManagement = new Button("Raumverwaltung");
		Button reportMangement = new Button ("Reporterstellung");
		Button invitationManagement = new Button ("Einladungsverwaltung");
		
		//Hinzufuegen der Buttons zum HorizontalPanel "navPanel"
		navPanel.add(homepageButton);
		navPanel.add(userManagement);
		navPanel.add(roomManagement);
		navPanel.add(reportMangement);
		navPanel.add(invitationManagement);
		
		/* mainPanel  mit der HTML Hostpage "RoomReservationService.html" verlinken 
		*/
		RootPanel.get("roomReservationService").add(mainPanel);
		//TODO: Ueberschrift als Platzhalter anlegen
		//TODO: Zwei feste Buttons mit wechselnden Inhalten und ClickHandlern anlegen
		//@Mazurkiewicz
		
		
		}}