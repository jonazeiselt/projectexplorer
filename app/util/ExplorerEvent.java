package app.util;

import java.util.Date;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Jonas Eiselt
 * @since 8 okt. 2016
 */
public class ExplorerEvent extends Group 
{
	private Date eventStart;
	private Date eventEnd;

	private Date projectStart;
	private Date projectEnd;

	private double timeSpan;
	private double eventWidth;
	
	private double xPos;
	private double yPos;
	
	private int index;

	public ExplorerEvent(int index, Date eventStart, Date eventEnd, Date projectStart, Date projectEnd)
	{
		this.index = index;
		
		this.setEventStart(eventStart);
		this.setEventEnd(eventEnd);

		this.setProjectStart(projectStart);
		this.setProjectEnd(projectEnd);

		this.getTimeSpan();
		this.setEvent();

		//		Rectangle r = new Rectangle(x, y, width, height);
		//		r.setFill(Color.GREENYELLOW);
		//		r.setArcHeight(15);
		//		r.setArcWidth(15);
	}

	/**
	 * This is a private method that calculates the time between start date and end date, 
	 * which then is used to calculate the length of each Event/Task.
	 *  
	 * @return timeSpan
	 */
	private double getTimeSpan()
	{
		long diff = eventEnd.getTime() - eventStart.getTime();
		timeSpan = (double) diff/(24*60*60*1000);

		return timeSpan;
	}

	/**
	 * This is a private method with help of the private method "positionEvent" that calculates 
	 * the position of the durational event.
	 */
	private void setEvent() 
	{
		eventWidth = timeSpan*LayoutSettings.getDateWidth();
		Rectangle rect = new Rectangle((double) eventWidth, 20, Color.GREENYELLOW);

		xPos = positionEvent(projectStart, eventStart);
		xPos *= LayoutSettings.getDateWidth();

		yPos = positionEvent();
		
		rect.setLayoutX(xPos+LayoutSettings.getDescriptionWidth()+1);
		rect.setLayoutY(yPos);
		
		this.getChildren().add(rect);
	}

	private double positionEvent() 
	{
		int height = LayoutSettings.getDateHeight();
		return ((index*height) + (height/4)) + ((LayoutSettings.getDateHeight()*2)-4);
	}

	private double positionEvent(Date projectStart, Date eventStart) 
	{
		long diff = eventStart.getTime() - projectStart.getTime();
		double diffDays = (double) diff/(24*60*60*1000);

		return diffDays;
	}

	public Date getEventStart() 
	{
		return eventStart;
	}

	public void setEventStart(Date eventStart) 
	{
		this.eventStart = eventStart;
	}

	public Date getEventEnd() 
	{
		return eventEnd;
	}

	public void setEventEnd(Date eventEnd) 
	{
		this.eventEnd = eventEnd;
	}

	public Date getProjectStart() 
	{
		return projectStart;
	}

	public void setProjectStart(Date projectStart) 
	{
		this.projectStart = projectStart;
	}

	public Date getProjectEnd() 
	{
		return projectEnd;
	}

	public void setProjectEnd(Date projectEnd) 
	{
		this.projectEnd = projectEnd;
	}
}