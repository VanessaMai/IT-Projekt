package roomreservationservice.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/*
 *  GUI-Klasse für die folgenden "Seiten": Einladung die per Mail an User versendet wird.
 *  (Siehe GUI-Prototyp 2.0)
 **  @author: Elisabeth Mazurkiewicz
 */

public class InvitationManagement extends Reaction{

	@Override
	protected String getHeadlineText() {
		return "Einladungsmanagement";
	}

	@Override
	protected void run() {
		Button reject = new Button ("Ablehnen");
		Button accept = new Button ("Annehmen");
		

	    // Add the widgets to the page
		RootPanel.get("Content").add(reject);
	 	RootPanel.get("Content").add(accept);
		
		
	}
	//TODO Wie werden Informationen eingeleitet ClickHanlder in Einladungsverwaltung anlegen: Zusagen, Absagen

}
