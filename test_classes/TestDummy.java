package test_classes;

import java.util.ArrayList;
import java.util.Date;

import app.controller.PEAppController;
import app.controller.TaskViewController;
import app.util.LayoutSettings;
import app.views.MenuView;
import app.views.TaskView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.Project;
import project.Task;

/**
 * 
 * @author Jonas Eiselt
 * @since 5 okt. 2016
 */
public class TestDummy extends Application
{
	public static void main(String[] args) 
	{
        launch(args);
    }
    
	@SuppressWarnings("deprecation")
	@Override
    public void start(Stage stage) 
    {
		// PREPARE..
		LayoutSettings.init();
		TaskViewController twc = new TaskViewController(new PEAppController());
		
		// GO..
    	VBox nRoot = new VBox(10);
        nRoot.getChildren().add(new MenuView(twc));
        
        Date startDate = new Date(2016-1900, 10-1, 2);
    	Date endDate = new Date(2016-1900, 11-1, 30);
    	
    	Date ds1 = new Date(2016-1900, 10-1, 4);
    	Date de1 = new Date(2016-1900, 10-1, 20);
  
    	Date ds2 = new Date(2016-1900, 10-1, 3);
    	Date de2 = new Date(2016-1900, 10-1, 18);
    	
    	Date ds3 = new Date(2016-1900, 10-1, 30);
    	Date de3 = new Date(2016-1900, 11-1, 18);
    	
    	// ADD TASKS
    	Task t1 = new Task("Start building database", "Helloooo", ds1, de1);
    	Task t2 = new Task("Math", "Helloooo", startDate, endDate);
    	Task t3 = new Task("Report in BIO", "Helloooo", startDate, endDate);
    	Task t4 = new Task("Programming", "Helloooo", ds2, de2);
    	Task t5 = new Task("Field Trip", "Helloooo", startDate, endDate);
    	Task t6 = new Task("Layout fixes, button mechanisms, and project", "Helloooo", startDate, endDate);
    	Task t7 = new Task("Month bar, e.g. it says October, 2016 above dates", "Helloooo", startDate, endDate);
    	Task t8 = new Task("App Logic", "Helloooo", ds3, de3);

        ArrayList<Task> al = new ArrayList<Task>();
        al.add(t1);
        al.add(t2);
        al.add(t3);
        al.add(t4);
        al.add(t5);
        al.add(t6);
        al.add(t7);
        al.add(t8);

        // TASKVIEW
        TaskView tw = new TaskView(twc, new Project("Studies", "My academic year", startDate, endDate, al));
        tw.addTask(al.indexOf(t1), t1);
        tw.addTask(al.indexOf(t4), t4);
        tw.addTask(al.indexOf(t8), t8);
        
        ScrollPane sp = new ScrollPane(tw);
        sp.setPrefHeight(1000);
        sp.setStyle("-fx-pressed: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background: white;");
        
        nRoot.getChildren().add(sp);
        
        nRoot.setStyle("-fx-background-color: #F5F5F5;"); // #dcdcdc -> gainsboro
        nRoot.setPadding(new Insets(10,10,10,10)); 
        
        stage.setTitle("Project - Studies");
        
        stage.setMaximized(true);
        stage.setScene(new Scene(nRoot, 600, 600));
        stage.show();
    }
}