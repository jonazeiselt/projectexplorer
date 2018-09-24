package app;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Date;

import app.controller.PEAppController;
import app.controller.TaskViewController;
import app.interfaces.TaskViewInterface;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import project.Task;

public class PEApp extends Application
{
	public ServerSocket serverSocket;

	public static void main(String[] args) 
	{
		launch(args);
	}

	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public void start(Stage stage) 
	{
		try
		{
			// Make sure that only one instance of the app is running..
			serverSocket = new ServerSocket(15486);
		}
		catch (BindException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Look, an Error Dialog");
			alert.setContentText("Ooops, application is already running!");

			alert.showAndWait();
			System.exit(0);
		}
		catch (IOException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Look, an Error Dialog");
			alert.setContentText("Ooops, application is already running!");

			alert.showAndWait();
			System.exit(0);
		}

		try 
		{
			// PREPARE..
			PEAppController pc = new PEAppController();

			// ERASE LATER
			Date startDate = new Date(2016-1900, 10-1, 2);
			Date endDate = new Date(2016-1900, 11-1, 30);

			//			Date ds1 = new Date(2016-1900, 10-1, 4);
			//			Date de1 = new Date(2016-1900, 10-1, 20);
			//
			//			Date ds2 = new Date(2016-1900, 10-1, 3);
			//			Date de2 = new Date(2016-1900, 10-1, 18);
			//
			//			Date ds3 = new Date(2016-1900, 10-1, 30);
			//			Date de3 = new Date(2016-1900, 11-1, 18);

			// ADD TASKS
			//			Task t1 = new Task("Start building database", "Helloooo", ds1, de1);
			//			Task t2 = new Task("Math", "Helloooo", startDate, endDate);
			//			Task t3 = new Task("Report in BIO", "Helloooo", startDate, endDate);
			//			Task t4 = new Task("Programming", "Helloooo", ds2, de2);
			//			Task t5 = new Task("Field Trip", "Helloooo", startDate, endDate);
			//			Task t6 = new Task("Layout fixes, button mechanisms, and project", "Helloooo", startDate, endDate);
			//			Task t7 = new Task("Month bar, e.g. it says October, 2016 above dates", "Helloooo", startDate, endDate);
			//			Task t8 = new Task("App Logic", "Helloooo", ds3, de3);

			ArrayList<Task> al = new ArrayList<Task>();
			//			al.add(t1);
			//			al.add(t2);
			//			al.add(t3);
			//			al.add(t4);
			//			al.add(t5);
			//			al.add(t6);
			//			al.add(t7);
			//			al.add(t8);

			// Project project = new Project("Studies", "My academic year", startDate, endDate, al);
			// pc.addProject(project);

			pc.setActiveProject(1);

			TaskViewInterface twi = new TaskViewInterface(new TaskViewController(pc));
			twi.getIcons().add(new Image("/images/app_icon.png"));
			twi.show();

		} catch (NullPointerException ex) {
			// Do nothing...
		}
	}
}