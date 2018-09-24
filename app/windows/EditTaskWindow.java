package app.windows;

import java.util.Date;

import app.util.timepicker.TimePicker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.Project;
import project.Task;

public class EditTaskWindow extends Stage 
{
	private TextField nameField;
	private TextArea descriptionArea;

	private Button addBtn;

	private Project project;

	private TimePicker startTP;
	private TimePicker endTP;

	private Date taskStart;
	private Date taskEnd;

	private String description;
	private String taskTitle;

	public EditTaskWindow(Project project, Task task) 
	{
		this.project = project;
		this.initStyle(StageStyle.UTILITY);

		Label headerlabel = new Label("Event Manager");
		headerlabel.setStyle("-fx-font-size: 15pt; -fx-font-family: Segoe UI;");
		headerlabel.setPadding(new Insets(10,0,20,0));

		Label nameLabel = new Label("Title:" );
		nameLabel.setStyle("-fx-font-size: 11pt; -fx-font-family: Segoe UI Semibold;");

		Label descriptionLabel = new Label("Description: ");
		descriptionLabel.setStyle("-fx-font-size: 11pt; -fx-font-family: Segoe UI Semibold;");

		nameField = getLetterField();
		nameField.setText(task.getName());
		nameField.setMaxWidth(370);
		nameField.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
					if (nameField.getText().length() >= 50) 
					{
						nameField.setText(nameField.getText().substring(0, 50));
					}
				}
			}
		});
		nameField.setPromptText("50 characters or less");

		descriptionArea = new TextArea();
		descriptionArea.setText(task.getDescription());
		descriptionArea.setMaxWidth(370);
		descriptionArea.setPrefRowCount(3);
		descriptionArea.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
					if (descriptionArea.getText().length() >= 150) 
					{
						descriptionArea.setText(descriptionArea.getText().substring(0, 150));
					}
				}
			}
		});
		descriptionArea.setPromptText("150 characters or less");
		descriptionArea.setWrapText(true);

		addBtn = new Button("Edit Event");
		addBtn.setPrefWidth(150);
		addBtn.setOnAction(event -> {
			try 
			{
				this.checkInput();

			} catch (Exception e) {	
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Look, an Error Dialog");
				alert.setContentText("Ooops, make sure that dates have been added to both date fields if it is not a non-durational event!");

				// Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				// stage.getIcons().add(new Image(this.getClass().getResource(icon).toString()));

				alert.showAndWait();
			}
		});

		CheckBox cb = new CheckBox("Non-durational?");

		VBox nameBox = new VBox(10);
		nameBox.getChildren().addAll(nameLabel, nameField);
		nameBox.setPadding(new Insets(0,20,0,0));

		startTP = new TimePicker(project, "START");
		startTP.setDate(task.getStartDate());

		endTP = new TimePicker(project, "END");
		endTP.setDate(task.getEndDate());

		VBox tpBox = new VBox(20);
		tpBox.getChildren().addAll(startTP, endTP);
		tpBox.setPadding(new Insets(20,20,20,0));

		cb.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

			}
		});

		HBox root100 = new HBox();
		root100.getChildren().addAll(cb);

		VBox descriptionBox = new VBox(10);
		descriptionBox.getChildren().addAll(descriptionLabel, descriptionArea);
		descriptionBox.setPadding(new Insets(0,20,0,0));

		HBox root0 = new HBox(75);
		root0.getChildren().addAll(root100, addBtn);

		VBox vbox = new VBox();
		vbox.getChildren().add(headerlabel);

		VBox root99 = new VBox(20);
		root99.getChildren().addAll(vbox, nameBox, descriptionBox, tpBox, root0);
		root99.setPadding(new Insets(20,20,20,20));

		root99.setStyle("-fx-background-color: white;");

		Scene scene = new Scene(root99,425,600);

		this.setScene(scene);
		this.setResizable(false);
	}

	private void checkInput() 
	{
		Date date1 = startTP.getDate();
		Date date2 = endTP.getDate();	

		if (nameField.getText().isEmpty() || descriptionArea.getText().isEmpty()) 
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Look, an Error Dialog");
			alert.setContentText("Ooops, you cannot create an event with no title or description!");

			alert.showAndWait();
		}
		else if ((date1.after(project.getStartDate()) || date1.equals(project.getStartDate())) || (date1.before(project.getEndDate()) && date2.after(project.getStartDate())) || (date2.before(project.getEndDate()) || date2.equals(project.getEndDate())))			
		{
			taskStart = startTP.getDate();
			taskEnd = endTP.getDate();

			taskTitle = nameField.getText();
			description = descriptionArea.getText();

			this.close();	
		}
	}

	private TextField getLetterField() 
	{
		return new TextField() 
		{
			@Override 
			public void replaceText(int start, int end, String text) 
			{
				if (text.matches("[a-ö]*") || text.matches("[A-Ö]*") || text.matches(" ") || text.matches("[0-9]*")) 
				{
					super.replaceText(start, end, text);
				}
			}

			@Override 
			public void replaceSelection(String text) 
			{
				if (text.matches("[a-ö]*") || text.matches("[A-Ö]*") || text.matches(" ") || text.matches("[0-9]*")) 
				{
					super.replaceSelection(text);
				}
			}
		};
	}

	public String getTaskTitle() 
	{
		return taskTitle;
	}

	public String getDescription()
	{
		return description;
	}

	public Date getStartDate() 
	{
		return taskStart;
	}

	public Date getEndDate() 
	{
		return taskEnd;
	}
}