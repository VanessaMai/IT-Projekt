package roomreservationservice.client.gui;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

/*
 *  GUI-Klasse fuer die Startseite.
 *  (Siehe GUI-Prototyp 2.0)
 *  @author: Nicholas Gertz
 */

public class Hompage extends Reaction{

	@Override

	protected String getHeadlineText() {
		return "Startseite";
	}

	/**
	   * Jede Reaction muss die <code>run()</code>-Methode implementieren. Sie ist
	   * eine "Einschubmethode", die von einer Methode der Basisklasse
	   * <code>Reaction</code> aufgerufen wird, wenn die Reaction aktiviert wird.
	   */
	
	@Override
	protected void run() {
		
	/*Anlegen einer 2x1 Matrix (Grid). Diese wird mit einem String als Textausgabe gefuehlt.
	 */	
		
		Grid HomepageGrid = new Grid(2, 1);
		this.add(HomepageGrid);
		
		Label HomepageLabel = new Label("Willkommen auf der Startseite. Bitte nutzen Sie die Navigationsleiste, um.... ");
	
		HomepageGrid.add(HomepageLabel);
		
		
	}
	/*TODO Aufrufen der Startseite
	 * wird auch in General nach anmeldung aufgerufen werden.
	 */

}
