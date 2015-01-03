package roomreservationservice.client.gui;

import ClickEvent;
import ClickHandler;
import CreateAccountDemo;
import Showcase;

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
	
	
		 /**
	   * "Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, ben�tigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen." Bankprojekt
	   */

	public void onModuleLoad() {
		/* Der Navigator ist als einspaltige Aneinanderreihung von Buttons
	     * realisiert. Daher bietet sich ein HorizontalPanel an.
	     */
		HorizontalPanel navPanel = new HorizontalPanel();
		
		/* Benoetigtes Panel fuer die weitere Darstellung:
		 * VerticalPanel als Container fuer alle weiteren Panels und Inhalte.
		 */
		 VerticalPanel contentPanel = new VerticalPanel();	
		
		/* mainPanel  mit der HTML Hostpage "RoomReservationService.html" verlinken 
		*/
		RootPanel.get("Content").add(contentPanel);
		
		/*navPanel mit der HTML Hostpage "RoomReservationService.html" verlinken 
		 */
		RootPanel.get("Navigator").add(navPanel);
		
	
		//neue Widgets erzeugen. Buttons fuer das HorizontalPanel "navPanel"
		Button homepageButton = new Button ("Startseite");
		Button userManagement = new Button ("Nutzerverwaltung");
		Button roomManagement = new Button("Raumverwaltung");
		Button reportMangement = new Button ("Reporterstellung");
		Button invitationManagement = new Button ("Einladungsverwaltung");
		Button logout = new Button ("Abmelden");
		
		//Hinzufuegen der Buttons zum HorizontalPanel "navPanel"
		navPanel.add(homepageButton);
		navPanel.add(userManagement);
		navPanel.add(roomManagement);
		navPanel.add(reportMangement);
		navPanel.add(invitationManagement);
		
		/*Clickhandler fuer die Buttons des navPanels erzeugen
		 * diese erstellen ein neues Objekt der jeweiligen SubKlasse von Reaction
		 * und rufen dieses auf. Analogie zum BankProjekt.java.
		 */
		    
		
		homepageButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Reaction reaction = new homepageButton ();
				
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(reaction);
			}
		});
		
		userManagement.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Reaction reaction = new userManagement ();
				
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(reaction);
			}
		});
		
		roomManagement.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Reaction reaction = new roomManagement ();
				
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(reaction);
			}
		});
		
		reportMangement.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Reaction reaction = new reportMangement ();
				
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(reaction);
			}
		});
		
		
		invitationManagement.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Reaction reaction = new invitationManagement ();
				
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(reaction);
			}
		});
		
		logout.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Reaction reaction = new logout ();
				
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(reaction);
			}
		});
				
		}}