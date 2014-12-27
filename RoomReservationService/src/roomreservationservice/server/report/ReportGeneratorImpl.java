package roomreservationservice.server.report;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import roomreservationservice.client.RoomReservationService;
import roomreservationservice.server.RoomReservationServiceAdministrationImpl;
import roomreservationservice.shared.ReportGenerator;
import roomreservationservice.shared.RoomReservationServiceAdministration;
import roomreservationservice.shared.bo.Room;
import roomreservationservice.shared.bo.Event;
import roomreservationservice.shared.bo.User;
import roomreservationservice.shared.report.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	/**
	 * Ein ReportGenerator benötigt Zugriff auf den RoomReservationService, da diese die essentiellen Methoden für die
	 * Koexistenz von Datenobjekten (vgl. bo-Package) bietet.
	 */
	private RoomReservationServiceAdministration roomReservationServiceAdministration = null;

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
		/*
		 * Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf eine BankVerwaltungImpl-Instanz.
		 */
		RoomReservationServiceAdministrationImpl rrsAdminImpl = new RoomReservationServiceAdministrationImpl();
		rrsAdminImpl.init();
		this.roomReservationServiceAdministration = rrsAdminImpl;
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

		imprint.addSubParagraph(new SimpleParagraph("RoomReservation Service"));
		imprint.addSubParagraph(new SimpleParagraph("Gruppe 8"));

		// Das eigentliche Hinzufügen des Impressums zum Report.
		r.setImprint(imprint);

	}

	/**
	 * Erstellen von <code>AllAccountsOfCustomerReport</code>-Objekten.
	 * 
	 * @param c
	 *            das Kundenobjekt bzgl. dessen der Report erstellt werden soll.
	 * @return der fertige Report
	 */
	@Override
	public AllEventsForRoomInPeriodOfTimeReport createAllEventsForRoomInPeriodOfTimeReport(Room room,
			Timestamp startDate, Timestamp endDate) throws IllegalArgumentException {

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		AllEventsForRoomInPeriodOfTimeReport result = new AllEventsForRoomInPeriodOfTimeReport();

		// Jeder Report hat einen Titel (Bezeichnung / Überschrift).
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
		header.addSubParagraph(new SimpleParagraph("Raum: " + room.getRoomName()));

		// Kundennummer aufnehmen
		header.addSubParagraph(new SimpleParagraph("Zeitraum: " + startDate.toString() + " bis " + endDate.toString()));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		/*
		 * Ab hier erfolgt ein zeilenweises Hinzufügen von Konto-Informationen.
		 */

		/*
		 * Zunächst legen wir eine Kopfzeile für die Konto-Tabelle an.
		 */
		Row headline = new Row();

		/*
		 * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste Spalte schreiben wir die jeweilige
		 * Kontonummer und in die zweite den aktuellen Kontostand. In der Kopfzeile legen wir also entsprechende
		 * Überschriften ab.
		 */
		headline.addColumn(new Column("Belegungsthema"));
		headline.addColumn(new Column("Beginn"));
		headline.addColumn(new Column("Ende"));
		headline.addColumn(new Column("Organisator"));
		headline.addColumn(new Column("Erstellsdatum"));

		// Hinzufügen der Kopfzeile
		result.addRow(headline);

		/*
		 * Nun werden sämtliche Konten des Kunden ausgelesen und deren Kto.-Nr. und Kontostand sukzessive in die Tabelle
		 * eingetragen.
		 */
		Vector<Event> events = this.roomReservationServiceAdministration.getEventsByRoomForPeriodOfTime(room,
				startDate, endDate);

		for (Event event : events) {
			// Eine leere Zeile anlegen.
			Row eventRow = new Row();

			// Erste Spalte: Kontonummer hinzufügen
			eventRow.addColumn(new Column(event.getTopic()));
			eventRow.addColumn(new Column(String.valueOf(event.getStartDate().toString())));
			eventRow.addColumn(new Column(String.valueOf(event.getEndDate().toString())));

			eventRow.addColumn(new Column(String.valueOf(event.getOrganizerId())));
			// TODO: Organisator Namen statt ID, dafür getUserByKey-Methode in RRS-Impl Klasse definieren

			eventRow.addColumn(new Column(event.getCreationDate().toString()));

			// und schließlich die Zeile dem Report hinzufügen.
			result.addRow(eventRow);
		}

		/*
		 * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
		 */
		return result;
	}

	@Override
	public AllEventsForUserInPeriodOfTimeReport createAllEventsForUserInPeriodOfTimeReport(User user,
			Timestamp startDate, Timestamp endDate) throws IllegalArgumentException {
		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		AllEventsForUserInPeriodOfTimeReport result = new AllEventsForUserInPeriodOfTimeReport();

		// Jeder Report hat einen Titel (Bezeichnung / Überschrift).
		result.setTitle("Alle Belegungen in einem Nutzer für einen bestimmten Zeitraum");

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
		header.addSubParagraph(new SimpleParagraph("Nutzer: " + user.getLastName() + " " +user.getFirstName()));

		// Kundennummer aufnehmen
		header.addSubParagraph(new SimpleParagraph("Zeitraum: " + startDate.toString() + " bis " + endDate.toString()));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		/*
		 * Ab hier erfolgt ein zeilenweises Hinzufügen von Konto-Informationen.
		 */

		/*
		 * Zunächst legen wir eine Kopfzeile für die Konto-Tabelle an.
		 */
		Row headline = new Row();

		/*
		 * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste Spalte schreiben wir die jeweilige
		 * Kontonummer und in die zweite den aktuellen Kontostand. In der Kopfzeile legen wir also entsprechende
		 * Überschriften ab.
		 */
		headline.addColumn(new Column("Belegungsthema"));
		headline.addColumn(new Column("Beginn"));
		headline.addColumn(new Column("Ende"));
		headline.addColumn(new Column("Organisator"));
		headline.addColumn(new Column("Erstellsdatum"));
		

		// Hinzufügen der Kopfzeile
		result.addRow(headline);

		/*
		 * Nun werden sämtliche Konten des Kunden ausgelesen und deren Kto.-Nr. und Kontostand sukzessive in die Tabelle
		 * eingetragen.
		 */
		Vector<Event> events = this.roomReservationServiceAdministration.getEventsByUserForPeriodOfTime(user, startDate, endDate);

		for (Event event : events) {
			// Eine leere Zeile anlegen.
			Row eventRow = new Row();

			// Erste Spalte: Kontonummer hinzufügen
			eventRow.addColumn(new Column(event.getTopic()));
			eventRow.addColumn(new Column(String.valueOf(event.getStartDate().toString())));
			eventRow.addColumn(new Column(String.valueOf(event.getEndDate().toString())));

			eventRow.addColumn(new Column(String.valueOf(event.getOrganizerId())));
			// TODO: Organisator Namen statt ID, dafür getUserByKey-Methode in RRS-Impl Klasse definieren

			eventRow.addColumn(new Column(event.getCreationDate().toString()));

			// und schließlich die Zeile dem Report hinzufügen.
			result.addRow(eventRow);
		}

		/*
		 * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
		 */
		return result;
	}
	

}
