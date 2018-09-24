package app.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javafx.scene.layout.HBox;

/**
 * 
 * @author Jonas Eiselt
 * @since 6 okt. 2016
 */
public class MonthLine extends HBox 
{	
	private int nbrDates = 0;
	private SimpleDateFormat df = new SimpleDateFormat("MMMM", Locale.US);
	
	public MonthLine(Date startDate, Date endDate) 
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		
		ArrayList<String> array = new ArrayList<String>();
		while (calendar.getTime().before(endDate))
		{
			Date result = calendar.getTime();
			calendar.add(Calendar.DATE, 1);
			
			String month = df.format(result);
			if (array.size() < 1) 
			{
				array.add(month);
				nbrDates++;
			}
			else if (month.equals(array.get(nbrDates-1)))
			{
				array.add(month);
				nbrDates++;
			}
			else 
			{
				this.getChildren().add(new MonthUnit(nbrDates*LayoutSettings.getDateWidth(), array.get(nbrDates-1), 2016));
				nbrDates = 0;
				array.clear();
				
				array.add(month);
				nbrDates++;
			}
		}
		// Add remaining segment..
		if (array.size() != 0) 
		{
			this.getChildren().add(new MonthUnit(nbrDates*LayoutSettings.getDateWidth(), array.get(nbrDates-1), 2016));
		}

		this.setPrefHeight(LayoutSettings.getDateHeight()-5);
		this.setStyle("-fx-border-width: 1 1 1 1; -fx-border-color: #dcdcdc; "
				+ "-fx-border-style: solid hidden solid hidden;");
	}
}