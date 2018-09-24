package app.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.scene.layout.HBox;

public class DateLine extends HBox 
{
	private int nbrDates = 0;
	
	public DateLine(Date startDate, Date endDate) 
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);

		while (calendar.getTime().before(endDate))
		{
			Date result = calendar.getTime();
			calendar.add(Calendar.DATE, 1);
			nbrDates++;
			
			this.getChildren().add(new DateUnit(result));
			this.setStyle("-fx-border-width: 1 1 1 1; "
					+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
					+ "-fx-border-style: hidden hidden solid hidden;");
		}
	}
	
	public int getNumberOfDates()
	{
		return nbrDates++;
	}
}