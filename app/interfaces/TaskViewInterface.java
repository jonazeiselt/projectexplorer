package app.interfaces;

import java.util.ArrayList;

import app.controller.PEAppController;
import app.controller.TaskViewController;
import app.util.LayoutSettings;
import app.views.MenuView;
import app.views.TaskView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.Project;
import project.Task;

/**
 * 
 * @author Jonas Eiselt
 * @since 8 okt. 2016
 */
public class TaskViewInterface extends Stage 
{
	private PEAppController mainController;
	private TaskViewController taskViewController;

	private TaskView tw;
	private ScrollPane sp;
	private VBox nRoot;

	public TaskViewInterface(TaskViewController taskViewController)
	{
		this.taskViewController = taskViewController;
		this.mainController = taskViewController.getMainController();

		taskViewController.setLink(this);
		mainController.setLink(this);

		this.showProject();
	}

	public void refresh() 
	{
		this.showProject();
	}

	private void showProject() 
	{
		// GO..
		nRoot = new VBox(10);
		nRoot.getChildren().add(this.getMenuBar());
		// nRoot.getChildren().add();

		Project project = taskViewController.getMainController().getActiveProject();
		ArrayList<Task> list = project.getTasks();

		// TASKVIEW
		tw = new TaskView(taskViewController, taskViewController.getMainController().getActiveProject());
		for (int i = 0; i < list.size(); i++)
		{
			tw.addTask(i, list.get(i));
		}

		VBox spBox = new VBox(10);
		sp = new ScrollPane(tw);
		sp.setPrefHeight(1000);
		sp.setStyle("-fx-pressed: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background: white;");

		spBox.getChildren().addAll(new MenuView(taskViewController), sp);
		spBox.setPadding(new Insets(0,10,10,10));

		nRoot.getChildren().add(spBox);
		// nRoot.getChildren().add(new DirectoryView());

		nRoot.setStyle("-fx-background-color: #F5F5F5;"); // #dcdcdc -> gainsboro
		// nRoot.setPadding(new Insets(10,10,10,10)); 

		this.setTitle("Project Explorer - " + project.getName());

		this.setMaximized(true);
		this.setScene(new Scene(nRoot, LayoutSettings.getScreenWidth(), LayoutSettings.getScreenHeight()-80));

		this.setOnCloseRequest(e -> {
			tw.closePopUp();
			System.exit(0);
		});
	}

	private MenuBar getMenuBar() 
	{
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(this.widthProperty());

		// File menu - new, open, exit
		Menu fileMenu = new Menu("File");

		Menu newMenuItem = new Menu("New"); 
		MenuItem newProjectItem = new MenuItem("Project");
		newProjectItem.setOnAction(e -> taskViewController.addProject());

		MenuItem newTaskItem = new MenuItem("Task");
		newTaskItem.setOnAction(e -> taskViewController.addTask());

		newMenuItem.getItems().addAll(newProjectItem, newTaskItem);

		MenuItem openMenuItem = new MenuItem("Open");
		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setOnAction(e -> System.exit(0));

		fileMenu.getItems().addAll(newMenuItem, openMenuItem, new SeparatorMenuItem(), exitMenuItem);

		Menu helpMenu = new Menu("Help");

		MenuItem tipsMenuItem = new MenuItem("Tips...");
		MenuItem aboutMenuItem = new MenuItem("About");

		helpMenu.getItems().addAll(tipsMenuItem, new SeparatorMenuItem(), aboutMenuItem);

		menuBar.getMenus().addAll(fileMenu, helpMenu);
		return menuBar;
	}
}