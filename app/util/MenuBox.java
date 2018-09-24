package app.util;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Jonas Eiselt
 * @since 4 okt. 2016
 */
public class MenuBox extends VBox
{
	public MenuBox(String description, ArrayList<Button> btns, ArrayList<String> btnDescrpts)
	{
		HBox btnBox = new HBox();
		
		VBox btnWTextBox = null;
		for (int i = 0; i < btns.size(); i++)
		{
			btnWTextBox = new VBox();

			Button btn = btns.get(i);
			btn.setStyle("-fx-background-color: transparent;");
			btn.setMinWidth(90);
			
			Label label = new Label(btnDescrpts.get(i));
			label.setStyle("-fx-font-family: Segoe UI; -fx-font-size: 14px; -fx-font-weight: normal;");

			btnWTextBox.getChildren().addAll(btn, label);
			btnWTextBox.setAlignment(Pos.CENTER);

			btnBox.getChildren().add(btnWTextBox);
		}
		
		this.setStyle("-fx-border-width: 1 1 1 1; "
				+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
				+ "-fx-border-style: hidden solid hidden hidden;");
		
		Label descLbl = new Label(description);
		descLbl.setStyle("-fx-font-family: Segoe UI; -fx-font-size: 13px; -fx-text-fill: #666666; -fx-font-weight: normal;");
		
		this.getChildren().addAll(btnBox, descLbl);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
	}
}
