package test_classes;

import java.util.Date;

import app.util.DateLine;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestDateLine extends Application
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
        Scene scene = new Scene(root, 600, 250);

        DateLine dl = new DateLine(startDate, endDate);
        root.getChildren().add(dl); 
        
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}