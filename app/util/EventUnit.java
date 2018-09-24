package app.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import project.Project;
import project.Task;

/**
 * 
 * @author Jonas Eiselt
 * @since 2 okt. 2016
 * @param <K>
 */
public class EventUnit<K> extends StackPane
{
	private Label l;

	private int prefWidth = 200;

	public EventUnit() 
	{
		this.setPrefSize(prefWidth, LayoutSettings.getDateHeight());

		this.setStyle("-fx-background-color: #FFFFFF; " // white color
				+ "-fx-border-width: 1 1 1 1; "
				+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
				+ "-fx-border-style: hidden hidden dashed hidden;");
	}
	
	public EventUnit(K k) 
	{
		if (k instanceof Project)
		{
			Project p = (Project) k;
			l = new Label(p.getName());
		}
		else 
		{
			Task t = (Task) k;
			l = new Label(t.getName());
		}

		l.setStyle("-fx-font-size: 10pt; -fx-font-family: Segoe UI Semibold; -fx-text-fill: #666666;");
		l.setPadding(new Insets(0,10,0,10));

		this.getChildren().add(l);
		this.setPrefSize(prefWidth, LayoutSettings.getDateHeight());

		this.setStyle("-fx-background-color: #FFFFFF; " // white color
				+ "-fx-border-width: 1 1 1 1; "
				+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
				+ "-fx-border-style: hidden hidden dashed hidden;");

		this.setAlignment(Pos.CENTER_LEFT);

		this.setOnMouseEntered(event -> 
		{
			this.setStyle("-fx-background-color: #FFFF99; " // white color
				+ "-fx-border-width: 1 1 1 1; "
				+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
				+ "-fx-border-style: hidden hidden dashed hidden;");
		});
		
		this.setOnMouseExited(event -> 
		{
			this.setStyle("-fx-background-color: #FFFFFF; " // white color
					+ "-fx-border-width: 1 1 1 1; "
					+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
					+ "-fx-border-style: hidden hidden dashed hidden;");
		});
	}

	public EventUnit(String string) 
	{
		int prefHeight = 0;
		if (!string.equals("#BOX"))
		{
			l = new Label(string);
			l.setStyle("-fx-font-size: 11pt; -fx-font-family: Segoe UI Semibold; -fx-text-fill: #666666;");

			this.getChildren().add(l);
			prefHeight = LayoutSettings.getDateHeight()+2; 
		}		
		else 
		{
			prefHeight = LayoutSettings.getDateHeight()-6;
		}		
		this.setPrefSize(prefWidth, prefHeight);

		// blue-ish: E3F3FF
		this.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #EFEFEF); " 
				+ "-fx-border-width: 1 1 1 1; "
				+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
				+ "-fx-border-style: hidden hidden dashed hidden;");
	}
}