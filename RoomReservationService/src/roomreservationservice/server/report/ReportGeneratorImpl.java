package roomreservationservice.server.report;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Logger;

import org.omg.CORBA.PRIVATE_MEMBER;

import roomreservationservice.server.RoomReservationServiceAdministrationImpl;
import roomreservationservice.server.ServersideSettings;
import roomreservationservice.shared.ReportGenerator;
import roomreservationservice.shared.bo.Room;
import roomreservationservice.shared.bo.Event;
import roomreservationservice.shared.bo.User;
import roomreservationservice.shared.report.*;

import com.google.api.server.spi.response.ForbiddenException;
import com.google.gwt.logging.client.FirebugLogHandler;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Implementierung des Report Generators.
 *
 * @author Julius Renner
 *
 */
@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {
	/**
	 * Logger vorbereiten.
	 */
	Logger logger;

	/**
	 * Ein ReportGenerator benötigt Zugriff auf den RoomReservationService, da dort die Methoden zum Auslesen der in der
	 * DB gespeicherten Objekte liegen. Die Instanz wird hier vorbereitet und beim Aufruf der init()-Methoden
	 * instanziiert.
	 */
	private RoomReservationServiceAdministrationImpl roomReservationServiceAdministration = null;

	/**
	 * <p>
	 * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels <code>GWT.create(Klassenname.class)</code>
	 * Client-seitig erzeugt. Hierzu ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
	 * Konstruktors ist durch die Client-seitige Instantiierung durch <code>GWT.create(Klassenname.class)</code> nach
	 * derzeitigem Stand nicht möglich.
	 * </p>
	 * <p>
	 * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die Client-seitig dirkt nach
	 * <code>GWT.create(Klassenname.class)</code> aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	 * </p>
	 */
	public ReportGeneratorImpl() throws IllegalArgumentException {
	}

	/**
	 * Initialsierungsmethode. Siehe dazu Anmerkungen zum No-Argument-Konstruktor.
	 * 
	 * @see #ReportGeneratorImpl()
	 */
	@Override
	public void init() throws IllegalArgumentException {
		/**
		 * Instanz des serverseitigen Loggers holen.
		 */
		try {
			this.logger = ServersideSettings.getLogger();
			logger.info("Logger erfolgreich instanziiert");
		} catch (Exception e) {
			logger.warning("Fehler bei der Instanziierung des Loggers: " + e.getMessage());

		}

		/*
		 * Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf eine RoomReservationService-Instanz.
		 */
		try {
			RoomReservationServiceAdministrationImpl roomReservationServiceAdministration = new RoomReservationServiceAdministrationImpl();
			roomReservationServiceAdministration.init();
			this.roomReservationServiceAdministration = roomReservationServiceAdministration;
		} catch (Exception e) {
			logger.warning("Fehler bei der Instanziierung der RoomReservationServiceAdministration: " + e.getMessage());

		}
	}

	/**
	 * Hinzufügen des Report-Impressums. Diese Methode ist aus den <code>create...</code>-Methoden ausgegliedert, da
	 * jede dieser Methoden diese Tätigkeiten redundant auszuführen hätte. Stattdessen rufen die <code>create...</code>
	 * -Methoden diese Methode auf.
	 * 
	 * @param r
	 *            der um das Impressum zu erweiternde Report.
	 */
	protected void addImprint(Report r) {

		/*
		 * Das Imressum soll mehrzeilig sein.
		 */
		CompositeParagraph imprint = new CompositeParagraph();

		imprint.addSubParagraph(new SimpleParagraph("Raumbelegungssystem"));
		imprint.addSubParagraph(new SimpleParagraph("HdM Stuttgart WI7, Gruppe 8, IT-Projekt, WS2014/15"));

		// Das eigentliche Hinzufügen des Impressums zum Report.
		r.setImprint(imprint);

	}

	/**
	 * Erstellen von <code>AllEventsForRoomInPeriodOfTimeReport</code>-Objekten.
	 * 
	 * @param room
	 *            Der Raum, für den der Report erstellt werden soll
	 * @param startDate
	 *            Der Startzeitpunkt des Zeitraums für den die Belegungen angezeigt werden sollen
	 * @param endDate
	 *            Der Startzeitpunkt des Zeitraums für den die Belegungen angezeigt werden sollen
	 * 
	 * @return der fertige Report
	 */
	@Override
	public AllEventsForRoomInPeriodOfTimeReport createAllEventsForRoomInPeriodOfTimeReport(Room room,
			Timestamp startDate, Timestamp endDate) throws IllegalArgumentException {

		/*
		 * Leeren Report vorbereiten.
		 */
		AllEventsForRoomInPeriodOfTimeReport result = new AllEventsForRoomInPeriodOfTimeReport();

		// Überschrift des Reports setzten.
		result.setTitle("Alle Belegungen in einem Raum für einen bestimmten Zeitraum");

		// Imressum hinzufügen
		this.addImprint(result);

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen "Timestamp" des Zeitpunkts der
		 * Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben auf dem Report stehen) des Reports.
		 * Die Kopfdaten sind mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();

		// Name und Vorname des Kunden aufnehmen
		header.addSubParagraph(new SimpleParagraph("<b>Raum:</b> " + room.getRoomName()));

		// Kundennummer aufnehmen
		header.addSubParagraph(new SimpleParagraph("<b>Zeitraum:</b> " + startDate.toString().substring(0, 16)
				+ " bis " + endDate.toString().substring(0, 16)));

		header.addSubParagraph(new SimpleParagraph(
				"Der Teilnahmestatus der Eingeladenen wird farblich dargestellt. Grün bedeutet, dass eine Zusage vorliegt, Rot, dass keine vorliegt, oder abgesagt wurde"));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		/*
		 * Ab hier erfolgt ein zeilenweises Hinzufügen von Belegungs-Informationen.
		 */

		/*
		 * Zunächst legen wir eine Kopfzeile für die Tabelle an.
		 */
		Row headline = new Row();

		/*
		 * Es wird eine Tabelle mit 6 Spalten erzeugt. In der ersten Spalte stehen die Überschriften der Spalten
		 */
		headline.addColumn(new Column("Belegungstitel"));
		headline.addColumn(new Column("Beginn"));
		headline.addColumn(new Column("Ende"));
		headline.addColumn(new Column("Organisator"));
		headline.addColumn(new Column("Erstellsdatum"));
		headline.addColumn(new Column("Teilnehmer"));

		// Hinzufügen der Kopfzeile
		result.addRow(headline);

		/*
		 * Alle Belegungen des angebenen Zeitraums holen.
		 */
		Vector<Event> events = this.roomReservationServiceAdministration.getEventsByRoomForPeriodOfTime(room,
				startDate, endDate);
		/*
		 * Hier werden nun die Belegungen nacheinander in die Tabelle eingefügt.
		 */

		for (Event event : events) {
			// Eine leere Zeile anlegen.
			Row eventRow = new Row();

			// Erste Spalte: Kontonummer hinzufügen
			eventRow.addColumn(new Column(event.getTopic()));
			eventRow.addColumn(new Column(event.getStartDate().toString().substring(0, 16)));
			eventRow.addColumn(new Column(event.getEndDate().toString().substring(0, 16)));

			eventRow.addColumn(new Column(roomReservationServiceAdministration.getUserByID(event.getOrganizerId())
					.getLastName()));
			eventRow.addColumn(new Column(event.getCreationDate().toString()));
			eventRow.addColumn(new Column(roomReservationServiceAdministration.getInviteesOfEvent(event).toString()));

			// und schließlich die Zeile dem Report hinzufügen.
			result.addRow(eventRow);
		}

		/*
		 * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
		 */
		logger.severe("Report erstellt: \n" + result.toString());

		return result;
	}

	@Override
	public AllEventsForUserInPeriodOfTimeReport createAllEventsForUserInPeriodOfTimeReport(User user,
			Timestamp startDate, Timestamp endDate) throws IllegalArgumentException {
		return null;
	}

	// private String getInviteeStringRepresentation(Vector events) {
	// StringBuffer inviteesString = new StringBuffer();
	//
	// for (Event event : events) {
	//
	// Vector<User> attendees = roomReservationServiceAdministration.getUsersByParticipationStatusForEvent(event, true);
	// for (User user : attendees) {
	// inviteesString.append("<span style=\"color = green;\">" + user.getFirstName() + " " + user.getLastName() +
	// "</span><br>");
	// }
	//
	// Vector<User> invitees = roomReservationServiceAdministration.getUsersByParticipationStatusForEvent(event, false);
	// for (User user : invitees) {
	// inviteesString.append("<span style=\"color = red;\">" + user.getFirstName() + " " + user.getLastName() +
	// "</span><br>");
	// }
	// }
	//
	// return inviteesString;
	// }

}
