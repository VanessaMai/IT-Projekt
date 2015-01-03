package roomreservationservice.client.gui;

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
		// TODO Auto-generated method stub
		
	}
	/*TODO Belegung erstellen : 
	 * Ueberschrift festlegen
	 * DatePicker: Datum, 
	 * 1. ListBox: Ort, 
	 * 2. ListBox: Zeit, 
	 * 1. CellList: Teilnehmeruebersicht, 
	 * 2. CellList: Nutzer einladen, 
	*1. Button+ClickHandler: Belegung uebernehmen
	*/
	/*TODO Belegungsuebersicht
	 * Ueberschrift festlegen
	 * Kalender darstellen durch Tabelle mit Spalten= Wochentag + Zeilen = Uhrzeit
	 * 1.Button+ClickHandler: Belegung erstellen
	 */
	/*TODO Belegung modifizieren und loeschen
	 * Ueberschrift festlegen
	 * DatePicker: Datum, 
	 * 1. ListBox: Ort, 
	 * 2. ListBox: Zeit, 
	 * 1. CellList: Teilnehmeruebersicht, 
	 * 2. CellList: Nutzer einladen, 
	 *1. Button+ClickHandler: Aenderung uebernehmen
	 *2. Button+ClickHandler: Belegung loeschen
	 */
}
