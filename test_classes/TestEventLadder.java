package test_classes;

import java.util.ArrayList;
import java.util.Date;

import app.util.EventLadder;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.Task;

public class TestEventLadder extends Application
{
	public static void main(String[] args) 
	{
        launch(args);
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public void start(Stage stage) 
    {
    	Date startDate = new Date(2016, 10-1, 2);
    	Date endDate = new Date(2016, 10-1, 30);
    	
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);
        
        ArrayList<Task> al = new ArrayList<Task>();
        al.add(new Task("School 1", "Helloooo", startDate, endDate));
        al.add(new Task("School 2", "Helloooo", startDate, endDate));
        al.add(new Task("School 3", "Helloooo", startDate, endDate));
        al.add(new Task("School 4", "Helloooo", startDate, endDate));
        al.add(new Task("School 5", "Helloooo", startDate, endDate));

        EventLadder<Task> el = new EventLadder<Task>(al);
        root.getChildren().add(el); 
        
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}