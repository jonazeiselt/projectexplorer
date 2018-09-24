package app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;

/**
 * 
 * @author Jonas Eiselt
 * @since 2 okt. 2016
 */
public class DateUnit extends StackPane
{
	private SimpleDateFormat df = new SimpleDateFormat("dd", Locale.US);
	private SimpleDateFormat eeeeDf = new SimpleDateFormat("EEEE", Locale.US); 
	private SimpleDateFormat nDf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
	
	public DateUnit(Date date) 
	{
		GregorianCalendar cal = new GregorianCalendar();
		Date today = cal.getTime();
		
		String s = df.format(date);
		
		Label l = new Label(s);
		if (nDf.format(today).equals(nDf.format(date)))
		{
			l.setStyle("-fx-font-family: Segoe UI; -fx-font-size: 13px; -fx-text-fill: #666666; -fx-font-weight: normal;");
			this.setStyle("-fx-background-color: #D6F5D6; "
					+ "-fx-border-width: 1 1 1 1; "
					+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
					+ "-fx-border-style: hidden dashed hidden hidden;");
		}
		else
		{
			l.setStyle("-fx-font-family: Segoe UI; -fx-font-size: 13px; -fx-text-fill: #666666; -fx-font-weight: normal;");
			this.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #EFEFEF); " // white color
					+ "-fx-border-width: 1 1 1 1; "
					+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
					+ "-fx-border-style: hidden dashed hidden hidden;");
		}
		
		l.setTooltip(new Tooltip(eeeeDf.format(date)));
		l.setPadding(new Insets(10,10,10,10));
		
		this.getChildren().add(l);
		this.setPrefSize(LayoutSettings.getDateWidth(), LayoutSettings.getDateHeight());

		this.setOnMouseEntered(event -> 
		{
			this.setStyle("-fx-background-color: #FFFF99;");
		});
		
		this.setOnMouseExited(event -> 
		{
			if(nDf.format(today).equals(nDf.format(date)))
			{
				this.setStyle("-fx-background-color: #D6F5D6; "
						+ "-fx-border-width: 1 1 1 1; "
						+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
						+ "-fx-border-style: hidden dashed hidden hidden;");
			}
			else 
			{
				this.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #EFEFEF); "
					+ "-fx-border-width: 1 1 1 1; "
					+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
					+ "-fx-border-style: hidden dashed hidden hidden;");
			}
		});
	}
}
