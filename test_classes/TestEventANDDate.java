package test_classes;

import java.util.ArrayList;
import java.util.Date;

import app.util.DateLine;
import app.util.EventLadder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.Task;

public class TestEventANDDate extends Application
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
    	
    	VBox nRoot = new VBox();
        Group root = new Group();
        
        ArrayList<Task> al = new ArrayList<Task>();
        al.add(new Task("School 1", "Helloooo", startDate, endDate));
        al.add(new Task("School 2", "Helloooo", startDate, endDate));
        al.add(new Task("School 3", "Helloooo", startDate, endDate));
        al.add(new Task("School 4", "Helloooo", startDate, endDate));
        al.add(new Task("School 5", "Helloooo", startDate, endDate));

        EventLadder<Task> el = new EventLadder<Task>(al);
        root.getChildren().add(el); 
        
        DateLine dl = new DateLine(startDate, endDate);
        dl.setLayoutX(201);
        root.getChildren().add(dl);
        
        nRoot.getChildren().add(root);
        nRoot.setStyle("-fx-background-color: #F5F5F5;"); // #dcdcdc -> gainsboro
        nRoot.setPadding(new Insets(10,10,10,10));
        
        stage.setMaximized(true);
        stage.setScene(new Scene(nRoot, 600, 600));
        stage.show();
    }
}