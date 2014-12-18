package roomreservationservice.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>. 
 */

// test für eli

public class RoomReservationService implements EntryPoint {
	
	private VerticalPanel mainPanel = new VerticalPanel ();
	private Button registrierungsButton = new Button ("Registrieren");
	private Button authentifizierungsButton = new Button ("Authentifizieren");
	
	 /**
	   * "Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, ben�tigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen." Bankprojekt
	   */
	public void onModuleLoad() {
		
		
		//Assemble Main Panel
		mainPanel.add(registrierungsButton);
		mainPanel.add(authentifizierungsButton);
		
		// Main panel mit der HTML Hostpage "RoomReservationService.html" verlinken.
		RootPanel.get("roomReservationService").add(mainPanel);
	}
}
