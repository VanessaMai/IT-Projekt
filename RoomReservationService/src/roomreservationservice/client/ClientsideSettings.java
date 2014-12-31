//test test neuer test iWoz
package roomreservationservice.client;

import java.util.logging.Logger;
import com.google.gwt.core.client.GWT;
import roomreservationservice.shared.CommonSettings;
import roomreservationservice.shared.*;

/**
 * Konfiguration der für den Client zur Kommunikation mit dem Server grundlegenden Attribute und Methoden. Außerdem wird
 * hier eine Insanz des Loggers erstellt.
 * 
 * @author Julius Renner
 *
 */
public class ClientsideSettings extends CommonSettings {

	private static RoomReservationServiceAdministrationAsync roomReservationServiceAdministration = null;
	private static ReportGeneratorAsync reportGenerator = null;

	/**
	 * Rückgabe der eindeutigen Instanz, die die RPC-Calls zum RoomReservationAdministrationServlet durchführt. Um diese
	 * im zum Beispiel im Client zu verwenden:
	 * 
	 * <pre>
	 * <code>RoomReservationServiceAdministrationAsync RoomReservationServiceAdministration = ClientSideSettings.getRoomReservationServiceAdministration()</code>
	 * 
	 * <pre>
	 * @return Die Instanz, mit der die RPC-Calls durchgeführt werden können
	 */
	public static RoomReservationServiceAdministrationAsync getRoomReservationServiceAdministration() {
		// Wenn bisher noch keine Instanz der RoomReservationServiceAdministration erstellt wurde, wird dies hier getan
		if (roomReservationServiceAdministration == null) {
			// roomReservationServiceAdministration instanziieren
			roomReservationServiceAdministration = GWT.create(RoomReservationServiceAdministration.class);
		}

		// Rückgabe der roomReservationServiceAdministration-Instanz
		return roomReservationServiceAdministration;
	}

	/**
	 * Rückgabe der eindeutigen Instanz, die die RPC-Calls zum ReportGeneratorServlet durchführt. Um diese im zum
	 * Beispiel im Client zu verwenden:
	 * 
	 * <pre>
	 * <code>ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator()</code>
	 * 
	 * <pre>
	 * @return Die Instanz, mit der die RPC-Calls durchgeführt werden können
	 */
	public static ReportGeneratorAsync getReportGenerator() {
		// Wenn bisher noch keine Instanz des ReportGenerators erstellt wurde, wird dies hier getan
		if (reportGenerator == null) {
			// Zunächst instantiieren wir ReportGenerator
			reportGenerator = GWT.create(ReportGenerator.class);
			// Rückgabe der ReportGenerator-Instanz
		}
		return reportGenerator;

	}

	/**
	 * Clientseitigen Loggerinstanz erstellen
	 */
	static Logger logger = Logger.getLogger("RRS-Client");

	/**
	 * Instanz des serverseitigen Loggers holen.
	 *
	 * Die Logging-Level sind in der <code>java.util.logging</code> Doku zu finden. Für diese Anwendung sollen nur die
	 * folgenden 3 verwendet werden:
	 * <ul>
	 * <li>SEVERE (höchste Stufe, wird immer gezeigt)</li>
	 * <li>WARNING</li>
	 * <li>INFO</li>
	 * </ul>
	 *
	 *
	 * <h2>Anwendungsbeispiel:</h2> Logger verwenden:
	 * 
	 * <pre>
	 * Logger logger = ClientSideSettings.getLogger();
	 * </pre>
	 * 
	 * Meldung ins Log schreiben und dabei Log-Level festlegen:
	 * 
	 * <pre>
	 * logger.info(&quot;Belegung erfolgreich erstellt.&quot;);
	 * </pre>
	 * 
	 * @return Instanz des clientseitigen Loggers
	 */
	public static Logger getLogger() {
		return logger;
	}

}
