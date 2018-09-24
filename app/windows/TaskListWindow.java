package app.windows;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import app.util.PopUp;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.Project;
import project.Task;

public class TaskListWindow extends Stage 
{
	private Task selectedTask;
	private SimpleDateFormat nDf = new SimpleDateFormat("d MMM", Locale.US);
	
	private PopUp pop;

	public TaskListWindow(Project project) 
	{
		VBox root99 = new VBox(10);
		root99.setStyle("-fx-background-color: white;");

		Label headerlabel = new Label("Tasks");
		headerlabel.setStyle("-fx-font-size: 16pt; -fx-font-family: Segoe UI; -fx-font-weight: normal;");

		root99.getChildren().add(headerlabel);

		ArrayList<Task> tasks = project.getTasks();
		for (int i = 0; i < tasks.size(); i++) 
		{
			Task task = tasks.get(i);
			HBox box = getTaskItem(tasks.get(i));
			root99.getChildren().addAll(box);

			box.setOnMouseClicked(e -> {
				selectedTask = task;
				this.close();
			});
		}

		root99.setPadding(new Insets(20,20,20,20));

		Scene scene = new Scene(root99,425,600);

		this.setScene(scene);
		this.setResizable(false);
	}

	private HBox getTaskItem(Task task) 
	{
		Label nameLbl = new Label(task.getName().toUpperCase());
		nameLbl.setStyle("-fx-font-family: Segoe UI; -fx-font-size: 18px; -fx-font-weight: normal;");

		Label dateLbl = new Label(nDf.format(task.getStartDate()) + " to " + nDf.format(task.getEndDate()));
		dateLbl.setStyle("-fx-font-family: Segoe UI; -fx-font-size: 14px; -fx-text-fill: #666666; -fx-font-weight: normal;");

		HBox root = new HBox();

		ImageView im = new ImageView(new Image(getClass().getResourceAsStream("/images/task_red.png")));
		im.setFitHeight(80);
		im.setFitWidth(80);

		VBox infoBox = new VBox(5);
		infoBox.setPrefWidth(425);
		infoBox.getChildren().addAll(nameLbl, dateLbl);
		infoBox.setPadding(new Insets(10,10,10,10));

		ImageView optionsIm = new ImageView(new Image(getClass().getResourceAsStream("/images/options.png")));
		optionsIm.setFitHeight(20);
		optionsIm.setFitWidth(20);

		Button optBtn = new Button();
		optBtn.setStyle("-fx-background-color: transparent;");
		
		optBtn.setGraphic(optionsIm);
		
		HBox optBox = new HBox();
		optBox.setPadding(new Insets(40,0,0,0));
		optBox.getChildren().add(optBtn);
		
		root.getChildren().addAll(im, infoBox, optBox);

		// root.setPadding(new Insets(10,10,10,10));
		root.setStyle("-fx-background-color: white; "
				+ "-fx-border-width: 1 1 1 1; "
				+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
				+ "-fx-border-style: solid;");

		//		DropShadow dropShadow = new DropShadow();
		//		dropShadow.setColor(Color.GAINSBORO);
		//		dropShadow.setOffsetX(0.1);
		//		dropShadow.setOffsetY(0.1);
		//		root.setEffect(dropShadow);

		
		
		root.setOnMouseEntered(event -> 
		{
			root.setStyle("-fx-background-color: #FFFF99; "
					+ "-fx-border-width: 1 1 1 1; "
					+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
					+ "-fx-border-style: solid;");
		});

		root.setOnMouseExited(event -> 
		{
			root.setStyle("-fx-background-color: white; "
					+ "-fx-border-width: 1 1 1 1; "
					+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
					+ "-fx-border-style: solid;");
		});
		
		optBtn.setOnMouseClicked(e -> 
		{
			if (e.getButton() == MouseButton.PRIMARY) 
			{
				if (null == pop)
				{
					pop = new PopUp();
					pop.setHeaderText(task.getName());
					pop.setContentText(task.getDescription());

					pop.show(root);

					pop.setOnHidden(p -> {
						pop = null;
					});
				}	
			}
		});

		return root;
	}

	public Task getSelectedTask() 
	{
		return selectedTask;
	}
}
