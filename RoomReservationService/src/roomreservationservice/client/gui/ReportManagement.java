package roomreservationservice.client.gui;

import java.util.Date;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.datepicker.client.DatePicker;


/*
 *  GUI-Klasse fuerr die folgenden "Seiten": Datum mit Raum ODER Nutzer waehlen
 *  (Siehe GUI-Prototyp 2.0)
 *  @author: Mazurkiewicz, Elisabeth
 */
public class ReportManagement extends Reaction{

	@Override
	protected String getHeadlineText() {
			return "Report erstellen";
	}
	/**
	   * Jede Reaction muss die <code>run()</code>-Methode implementieren. Sie ist
	   * eine "Einschubmethode", die von einer Methode der Basisklasse
	   * <code>Reaction</code> aufgerufen wird, wenn die Reaction aktiviert wird.
	   */
	@Override
	protected void run() {
	/*Erstellen eines DatePickers und zwei Listen. So kann der User Das Datum und entweder einen
	 * Raum oder einen Nutzer, fuer den er den Report erhalten will, auswaehlen.
	 */
	    DatePicker datePicker = new DatePicker();
	    ListBox roomList = new ListBox();
	    ListBox userList = new ListBox();
	    
	/*Anlegen einer 3x2 Matrix (Grid). Diese wird Mit Namen der Widgets und den Widgets mit ihren
	 * Inhalten gefuellt.
	 */
		Grid ReportManagementGrid = new Grid(3, 2);
		this.add(ReportManagementGrid);

		Label datePickerLabel = new Label("Datum auswaehlen");
		ReportManagementGrid.setWidget(0, 0, datePickerLabel);
		ReportManagementGrid.setWidget(0, 1, datePicker);
		
	/*TODO: DatePicker muss sich Asugewaehltes Datum durch User merken um es spaeter mit der
	 * DB abzugleichen.
	 */

	// Set the default value
		datePicker.setValue(new Date(), true);
					    
		Label roomListLabel = new Label("Raum auswaehlen");
		ReportManagementGrid.setWidget(1, 0, roomListLabel);
		ReportManagementGrid.setWidget(1, 1, roomList);
	/*TODO: Liste soll die Raeume aus der Datenbank beinhalten. 
	 * Wenn ein Raum ausgewaehlt wird soll es nicht mehr moeglich sein, einen Nutzer auszuwaehlen.
	 * Raum den User ausgewaehlt hat muss gemerkt werden.
	 */
					
		roomList.addItem("Raum02");
		roomList.addItem("Raum03");
		roomList.addItem("Raum04");
		roomList.addItem("Raum05");
		roomList.addItem("Raum06");
					    

	// setting this value to 1 turns it into a drop-down list.
		roomList.setVisibleItemCount(1);
					    
		Label userListLabel = new Label("User auswaehlen");
		ReportManagementGrid.setWidget(2, 0, userListLabel);
		ReportManagementGrid.setWidget(2, 1, userList);
		userList.addItem("Mueller, Hans");
		userList.addItem("Kress, Peter");
		userList.addItem("Meyer, Lisa");
					    			    
	// setting this value to 1 turns it into a drop-down list.
		userList.setVisibleItemCount(1);
		
		/*ReportManagementGrid.add(datePicker);
		ReportManagementGrid.add(roomList);
		ReportManagementGrid.add(userList);*/
		
	/*TODO: Liste soll die User aus der Datenbank beinhalten. 
	 * Wenn ein User ausgewaehlt wird soll es nicht mehr moeglich sein, einen Raum auszuwaehlen.
	 * User den User ausgewaehlt hat muss gemerkt werden.
	 */
		
	/*Button fuer "Report erstellen" erstellen.
		Button showReportButton = new Button ("Report erstellen", new ClickHandler (){
			   
		public void onClick(ClickEvent event){
	/*TODO wenn Zeit und Raum gewaehlt wurde. Idee zwei buttons zu machen: Einen
	 * fuer allEventsByRoomForPeriodOfTime und einen fuer AllEventsByUserForPeriodOfTime.
			   reportGenerator
				.createAllEventsByRoomForPeriodOfTimeReport(new createAllEventsByRoomForPeriodOfTimeReportCallback());
	//todo wenn Zeit und Nutzer gewaehlt wurde
			   reportGenerator
				.createAllEventsByUserForPeriodOfTimeReport(new createAllEventsByUserForPeriodOfTimeReportCallback());
			    }
			   });

			  }
	
	/*TODO: Das gleiche fuer createAllEventsByUserForPeriodOfTimeReportCallback. Verkuepfen.
	*
	 * Diese Nested Class wird als Callback fuerr das Erzeugen des
	 * createAllEventsByRoomForPeriodOfTimeReport benoetigt.
	 * 
	 * @author rathke, Mazurkiewicz
	 * @version 1.1
	 *
	class createAllEventsByRoomForPeriodOfTimeReportCallback implements
			AsyncCallback<createAllEventsByRoomForPeriodOfTimeReport> {
		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message
			 * aus.
			 *
			ClientsideSettings.getLogger().severe(
					"Erzeugen des Reports fehlgeschlagen!");

		}

		@Override
		public void onSuccess(createAllEventsByRoomForPeriodOfTimeReport report) {
			if (report != null) {
				HTMLReportWriter writer = new HTMLReportWriter();
				writer.process(report);
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new HTML(writer.getReportText()));
			}
			}
		}*/
			}}