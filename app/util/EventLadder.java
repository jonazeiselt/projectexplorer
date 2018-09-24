package app.util;

import java.util.ArrayList;

import javafx.scene.layout.VBox;
import project.Task;

/**
 * 
 * @author Jonas Eiselt
 * @since 2 okt. 2016
 * @param <K>
 */
public class EventLadder<K> extends VBox 
{
	private ArrayList<K> events;
	
	public EventLadder(ArrayList<Task> tasks) 
	{
		this.getChildren().add(new EventUnit<K>("#BOX"));
		this.getChildren().add(new EventUnit<K>("Task description"));

		if (tasks.size() != 0)
		{
			for (int i = 0; i < tasks.size(); i++)
			{
				Task task = tasks.get(i);
				EventUnit<Task> eu = new EventUnit<Task>(task);
				this.getChildren().add(eu);
			}
		}

		int diff = 20 - tasks.size();
		if (diff > 0)
		{
			for (int i = 0; i < diff; i++)
			{
				this.getChildren().add(new EventUnit<K>());			
			}
		}
		this.setStyle("-fx-border-color: #dcdcdc;");
	}

	public ArrayList<K> getArray()
	{
		return events;
	}
}
