package roomreservationservice.client.gui;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.datepicker.client.DatePicker;

/*
 * Klasse fuer Methoden zur Erstellung der benoetigten Widgets
 * @author: Gertz, Steven, Mazurkiewicz, Elisabeth und Gertz, Nicholas
 */

public class Widgets {

	// Methode zur Erstellung eines Kalender-Widgets
	public static DatePicker createDatePicker() {
		DatePicker datePicker = new DatePicker();
		final Label text = new Label();
		
		// Wert setzen, wenn der Nutzer ein Datum waehlt
	    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
	      public void onValueChange(ValueChangeEvent<Date> event) {
	        Date date = event.getValue();
	        String dateString = DateTimeFormat.getMediumDateFormat().format(date);
	        text.setText(dateString);
	      }
	    });

	    //Setzen des Default-Wertes
	    datePicker.setValue(new Date(), true);
		//TODO DB-Connect
		
		return datePicker;
	}
	
	// Methode zur Erstellung eines ListBox-Widgets fuer Raeume
	public static ListBox createRoomList() {
		ListBox roomList = new ListBox();
		
		//TODO DB-Connect
		
		return roomList;
	}
	
	// Methode zur Erstellung eines ListBox-Widgets fuer Raeume
	public static ListBox createTimeSlotList() {
		ListBox timeSlotList = new ListBox();
			
		//TODO DB-Connect
			
		return timeSlotList;
	}

}
