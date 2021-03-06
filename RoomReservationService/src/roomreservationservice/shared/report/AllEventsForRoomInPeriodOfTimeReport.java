package roomreservationservice.shared.report;

import java.io.Serializable;

/**
 * Report, der alle Belegungen eines Raums innerhalb einenes bestimmten Zeitraumm darstellt. Die Klasse trägt keine
 * weiteren Attribute- und Methoden-Implementierungen, da alles Notwendige schon in den Superklassen vorliegt. Ihre
 * Existenz ist dennoch wichtig, um bestimmte Typen von Reports deklarieren und mit ihnen objektorientiert umgehen zu
 * können.
 * 
 * @author Julius Renner
 */
public class AllEventsForRoomInPeriodOfTimeReport extends SimpleReport implements Serializable {

	private static final long serialVersionUID = 1L;

}