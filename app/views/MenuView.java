package app.views;

import java.util.ArrayList;

import app.controller.TaskViewController;
import app.util.MenuBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Jonas Eiselt
 * @since 5 okt. 2016
 */
public class MenuView extends HBox
{
	private int btnSize = 64;

	private Button addBtn;
	private Button editBtn;
	private Button deleteBtn;

	private TaskViewController taskViewController;

	public MenuView(TaskViewController taskViewController) 
	{
		this.taskViewController = taskViewController;
		
		ArrayList<Button> _btnArr = new ArrayList<Button>();
		ArrayList<String> _strArr = new ArrayList<String>();

		// Button 4
		Button btn4 = new Button();
		ImageView im4 = new ImageView(new Image(getClass().getResourceAsStream("/images/zoom.png")));
		im4.setFitHeight(btnSize);
		im4.setFitWidth(btnSize);
		im4.setPreserveRatio(true);

		btn4.setGraphic(im4);

		// Mouse clicked.. 
		btn4.setOnMouseClicked(e -> {
			taskViewController.zoom();
		});

		_btnArr.add(btn4);
		_strArr.add("Zoom");

		// MenuBox box3 = new MenuBox("Zoom", _btnArr, _strArr);

		MenuBox box1 = prepareMenuBox("Project");
		MenuBox box2 = prepareMenuBox("Task");

		this.getChildren().addAll(box1, box2);
		this.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #EFEFEF); " // #f2f2f2, #d4d4d4
				+ "-fx-border-width: 1 1 1 1; "
				+ "-fx-border-color: #dcdcdc #dcdcdc #dcdcdc #dcdcdc; "
				+ "-fx-border-style: solid solid solid solid;");

		this.setSpacing(10);
		this.setPadding(new Insets(10,10,10,10));
		this.setMinWidth(1900);
	}

	private MenuBox prepareMenuBox(String title) 
	{
		ArrayList<Button> btnArr = new ArrayList<Button>();
		ArrayList<String> strArr = new ArrayList<String>();

		String append = null;
		String key = null;
		if (title.equalsIgnoreCase("Project"))
		{
			append = "p";
			key = "Project";
		}
		else if (title.equalsIgnoreCase("Task")) 
		{
			append = "t";
			key = "Task";
		}

		// Button 1
		addBtn = new Button();
		ImageView im1 = new ImageView(new Image(getClass().getResourceAsStream("/images/add_" + append + ".png")));
		im1.setFitHeight(btnSize);
		// im1.setFitWidth(btnSize);
		im1.setPreserveRatio(true);
		addBtn.setGraphic(im1);

		btnArr.add(addBtn);
		strArr.add("New " + key);

		// Button 2
		editBtn = new Button();
		ImageView im2 = new ImageView(new Image(getClass().getResourceAsStream("/images/edit_" + append + ".png")));
		im2.setFitHeight(btnSize);
		im2.setFitWidth(btnSize);
		editBtn.setGraphic(im2);

		btnArr.add(editBtn);
		strArr.add("Edit");

		// Button 3
		deleteBtn = new Button();
		ImageView im3 = new ImageView(new Image(getClass().getResourceAsStream("/images/delete_" + append + ".png")));
		im3.setFitHeight(btnSize);
		im3.setFitWidth(btnSize);
		deleteBtn.setGraphic(im3);

		btnArr.add(deleteBtn);
		strArr.add("Delete");

		this.addActionListeners(append);

		return new MenuBox(title, btnArr, strArr);
	}

	private void addActionListeners(String append) 
	{
		if (append.equals("p"))
		{
			addBtn.setOnMouseClicked(e -> {
				taskViewController.addProject();
			});

			editBtn.setOnMouseClicked(e -> {
				taskViewController.editProject();
			});

			deleteBtn.setOnMouseClicked(e -> {
				taskViewController.deleteProject();
			});
		}
		else 
		{
			addBtn.setOnMouseClicked(e -> {
				taskViewController.addTask();
			});

			editBtn.setOnMouseClicked(e -> {
				taskViewController.editTask(null);
			});

			deleteBtn.setOnMouseClicked(e -> {
				taskViewController.deleteTask(null);
			});
		}
	}
}