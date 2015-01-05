package roomreservationservice.client.gui;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

/*
 *  GUI-Klasse fuer die Startseite.
 *  (Siehe GUI-Prototyp 2.0)
 *  @author: Nicholas Gertz
 */

public class Homepage extends Reaction{

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
		
	/*Anlegen einer 3x2 Matrix (Grid). Diese wird mit einem String als Textausgabe gefuehlt.
	 */	
		
		Grid HomepageGrid = new Grid(3, 2);
		this.add(HomepageGrid);
		
		Label HomepageLabel = new Label("Willkommen auf der Startseite. Bitte nutzen Sie die Navigationsleiste, um zu dem gewünschten Bereich zu gelangen.");
		HomepageGrid.setWidget(0, 0, HomepageLabel);
		
		
		
		
	}
	/*TODO Aufrufen der Startseite
	 * wird auch in General nach anmeldung aufgerufen werden.
	 */

}
