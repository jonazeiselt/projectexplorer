package app.util.timepicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import project.Project;

/**
 * This class extends VBox and it is used to receive the exact time of day.
 * (Seconds is not included since it does not fit the theme of project.)
 *
 * @author Project Group 3
 * @since 2015-05-11
 * 
 * @see TimeSpinner.java
 * @see http://code.makery.ch/blog/javafx-8-date-picker/
 */
public class TimePicker extends VBox 
{
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	private Date date;
	private DatePicker datePicker;

	private TimeSpinner timeSpinner;

	private Date projectStart;
	private Date projectEnd;

	public TimePicker(Project project, String startOrEnd)
	{
		Locale.setDefault(Locale.ENGLISH);

		projectStart = project.getStartDate();
		projectEnd = project.getEndDate();

		datePicker = new DatePicker();
		datePicker.setPrefWidth(130);
		datePicker.setShowWeekNumbers(true);

		timeSpinner = new TimeSpinner();

		// Factory to create Cell of DatePicker
		Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
		datePicker.setDayCellFactory(dayCellFactory);

		String msg = null;
		int value = 0;
		if (startOrEnd.equals("START"))
		{
			msg = "Start Date:";
			value = 0;
		}
		else if (startOrEnd.equals("END"))
		{
			msg = "End Date: ";
			value = 20;
		}

		Label msgLbl = new Label(msg);
		msgLbl.setStyle("-fx-font-size: 10pt; -fx-font-family: Segoe UI Semibold;");
		msgLbl.setPadding(new Insets(25,0,0,0));

		HBox hbox = new HBox();
		hbox.getChildren().addAll(datePicker);
		hbox.setPadding(new Insets(20,0,0,0));

		HBox root = new HBox(10);
		root.getChildren().addAll(msgLbl, hbox, timeSpinner);
		root.setPadding(new Insets(0,0,value,0));

		this.setSpacing(10);
		this.getChildren().add(root);
	}

	// Factory to create Cell of DatePicker
	private Callback<DatePicker, DateCell> getDayCellFactory() 
	{
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() 
		{
			@Override
			public DateCell call(final DatePicker datePicker) 
			{
				return new DateCell() 
				{
					@SuppressWarnings("deprecation")
					@Override
					public void updateItem(LocalDate item, boolean empty) 
					{
						super.updateItem(item, empty);


						if (item.isBefore(LocalDate.of(projectStart.getYear()+1900, projectStart.getMonth()+1, projectStart.getDate()))
								|| item.isAfter(LocalDate.of(projectEnd.getYear()+1900, projectEnd.getMonth()+1, projectEnd.getDate())))
						{
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		return dayCellFactory;
	}

	//Get methods for editing
	public DatePicker getDatePicker() 
	{
		return datePicker;
	}

	/**
	 * This is a public method that returns the exact time and date.
	 * @return date
	 */
	@SuppressWarnings("deprecation")
	public Date getDate()
	{
		String date0 = datePicker.getValue().toString();

		try 
		{
			if (timeSpinner.isDisable())
			{
				date = df.parse(date0);
			}
			else 
			{
				date = df.parse(date0);
				date.setHours(timeSpinner.getHour());
				date.setMinutes(timeSpinner.getMinute());
			}	
			return date;

		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * This is a public method that returns the date from the DatePicker
	 * and not from the TimeSpinner.
	 * 
	 * @return date
	 * @throws ParseException
	 */
	public Date getDateOnly() throws ParseException
	{
		String date0 = datePicker.getValue().toString();
		date = df.parse(date0);

		return date;
	}

	/**
	 * This is a public method that disables or enables the HourSpinner in TimeSpinner.
	 * @param b
	 */
	public void disableHourSpinner(boolean b)
	{
		timeSpinner.disableHourSpinner(b);
	}

	/**
	 * This is a public method that disables or enables the MinuteSpinner in TimeSpinner.
	 * @param b
	 */
	public void disableMinuteSpinner(boolean b)
	{
		timeSpinner.disableMinuteSpinner(b);
	}

	/**
	 * @return hour
	 */
	public int getHour()
	{
		return timeSpinner.getHour();
	}

	/**
	 * @return minute
	 */
	public int getMinute() 
	{
		return timeSpinner.getMinute();	
	}

	public void setDate(Date date) 
	{
		LocalDate localeDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		this.datePicker.setValue(localeDate);
	}
}
