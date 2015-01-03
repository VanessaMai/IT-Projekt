package roomreservationservice.client.gui;

import com.google.gwt.user.client.ui.*;
/**
 * Diese Klasse ist die Basisklasse aller Reackionen. Jede Reaction ist ein
 * VerticalPanel und laesst sich somit unter GWT entsprechend anordnen.
 * 
 * @author thies, mazurkiewicz
 * @version 1.1
 * 
 */

public abstract class Reaction extends VerticalPanel {

	  /**
	   * Jedes GWT Widget muss diese Methode implementieren. Sie gibt an, was
	   * geschehen soll, wenn eine Widget-Instanz zur Anzeige gebracht wird.
	   */
	  public void onLoad() {
	    /*
	     * Bevor wir unsere eigene Formatierung veranslassen, ueberlassen wir es der
	     * Superklasse eine Initialisierung vorzunehmen.
	     */
	    super.onLoad();

	    /*
	     * Als erstes geben wir stets die Headline des Showcase aus. Da
	     * getHeadlineText() als abstrakte Methode bzw. als Einschubmethode
	     * realisiert wird, obliegt es den Subklassen, für eine Ausgestaltung also
	     * Implementierung zu sorgen.
	     */
	    this.add(this.createHeadline(this.getHeadlineText()));

	    /*
	     * Wenn alles vorbereitet ist, stoßen wir die run()-Methode an. Auch run()
	     * ist als abstrakte Methode bzw. als Einschubmethode realisiert. Auch hier
	     * ist es Aufgabe der Subklassen, für deren Implementierung zu sorgen.
	     */
	    this.run();
	  }

	  /**
	   * Mit Hilfe dieser Methode erstellen wir aus einem String ein mittels CSS
	   * formatierbares HTML-Element. 
	   * 
	   * @param text der String, den wir als andernorts HTML setzen wollen.
	   * @return GWT HTML Widget.
	   */
	  protected HTML createHeadline(String text) {
	    HTML content = new HTML(text);
	    return content;
	  }

	  /**
	   * Mit Hilfe dieser Methode erstellen wir aus einem Strinng ein mittels CSS
	   * formatierbares HTML-Element, das an das Ende der bisherigen Ausgabe dieser
	   * Reaktion angehaengt wird. 
	   * 
	   * @param text der String, den wir als HTML an die bisherige Showcase-Ausgabe
	   *          anhängen wollen.
	   */
	  protected void append(String text) {
	    HTML content = new HTML(text);
	    this.add(content);
	  }

	  /**
	   * Abstrakte Einschubmethode, die in den Subklassen zu realisieren ist.
	   * @return der Text, den wir als Headline setzen wollen. 
	   */
	  protected abstract String getHeadlineText();

	  /**
	   * Abstrakte Einschubmethode, die in den Subklassen zu realisieren ist.
	   */
	  protected abstract void run();
	}
