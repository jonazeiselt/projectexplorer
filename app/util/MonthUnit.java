package app.util;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class MonthUnit extends StackPane
{
	public MonthUnit(int width, String month, int year) 
	{
		Label l = new Label(month + ", " + year);
		l.setStyle("-fx-font-family: Segoe UI; -fx-font-size: 13px; -fx-text-fill: #666666; -fx-font-weight: normal;");

		this.getChildren().add(l);
		this.setPrefWidth(width);

		this.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #EFEFEF); " // white color
				+ "-fx-border-width: 1 1 1 1; "
				+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
				+ "-fx-border-style: hidden dashed hidden hidden;");
	}
}