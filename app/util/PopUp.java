package app.util;

import org.controlsfx.control.PopOver;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Jonas Eiselt
 * @since 21 okt. 2016
 */
public class PopUp extends PopOver 
{
	private Label headerLbl = new Label();
	private Label contentLbl = new Label();
	private Label nameLbl;

	public PopUp()
	{
		VBox root = new VBox(5);
		HBox hbox = new HBox();
		
		Button btn = new Button();
		ImageView im4 = new ImageView(new Image(getClass().getResourceAsStream("/images/close_pop.png")));
		im4.setFitHeight(17);
		im4.setFitWidth(17);
		im4.setPreserveRatio(true);

		btn.setGraphic(im4);
		btn.setStyle("-fx-background-color: transparent;");

		// Mouse clicked.. 
		btn.setOnMouseClicked(b -> {
			this.hide();
		});
		
		hbox.setPadding(new Insets(0,20,0,0));
		hbox.getChildren().addAll(btn, headerLbl);

		VBox textBox0 = new VBox();
		Label lbl0 = new Label("Name");
		lbl0.setStyle("-fx-font-family: Segoe UI; -fx-font-style: italic; -fx-font-size: 17px; -fx-text-fill: #5bd75b; -fx-font-weight: normal;");
		
		nameLbl = new Label();
		textBox0.getChildren().addAll(lbl0, nameLbl);
		textBox0.setPadding(new Insets(10,20,10,15));

		VBox textBox = new VBox();
		Label lbl1 = new Label("Description");
		lbl1.setStyle("-fx-font-family: Segoe UI; -fx-font-style: italic; -fx-font-size: 17px; -fx-text-fill: #5bd75b; -fx-font-weight: normal;");
		
		textBox.getChildren().addAll(lbl1, contentLbl);
		textBox.setPadding(new Insets(10,20,10,15));

		root.getChildren().addAll(hbox, textBox0, textBox);
		root.setPrefSize(200,200);
		this.setContentNode(root);

		this.setDetachable(false);
	}

	public void setHeaderText(String title)
	{
		headerLbl.setText(title);
		headerLbl.setPadding(new Insets(5,0,0,0));
		headerLbl.setStyle("-fx-text-fill: #666666;");
		
		nameLbl.setText(title);
		nameLbl.setWrapText(true);
	}

	public void setContentText(String content)
	{
		contentLbl.setText(content);
		contentLbl.setWrapText(true);
	}
}
