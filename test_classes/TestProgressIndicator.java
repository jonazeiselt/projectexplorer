package test_classes;

import app.util.ProgressIndicator;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestProgressIndicator extends Application
{
	public static void main(String[] args) 
	{
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) 
    {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 250);
  
        ProgressIndicator pi = new ProgressIndicator(50,50,300,30,100);
        root.getChildren().add(pi); 
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
