package app.views;

import java.util.Locale;

import app.controller.TaskViewController;
import app.util.DateLine;
import app.util.EventLadder;
import app.util.ExplorerEvent;
import app.util.MonthLine;
import app.util.PopUp;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import project.Project;
import project.Task;

/**
 * 
 * @author Jonas Eiselt
 * @since 4 okt. 2016
 */
public class TaskView extends Group 
{
	private TaskViewController taskViewController;
	private Project project;

	private PopUp pop;
	private ContextMenu contextMenu;

	public TaskView(TaskViewController taskViewController, Project project)
	{
		Locale.setDefault(new Locale("en", "US"));

		this.taskViewController = taskViewController;
		this.project = project;

		EventLadder<Task> el = new EventLadder<Task>(project.getTasks());
		this.getChildren().add(el); 

		VBox box = new VBox();
		MonthLine ml = new MonthLine(project.getStartDate(), project.getEndDate());
		DateLine dl = new DateLine(project.getStartDate(), project.getEndDate());

		box.getChildren().addAll(ml, dl);
		box.setLayoutX(201);

		this.getChildren().add(box);
	}

	public void addTask(int index, Task task)
	{
		ExplorerEvent ee = new ExplorerEvent(index, task.getStartDate(), task.getEndDate(), project.getStartDate(), project.getEndDate());
		this.getChildren().add(ee);

		ee.setOnMouseClicked(e -> 
		{
			if (e.getButton() == MouseButton.PRIMARY) 
			{
				if (null == pop)
				{
					if (null != contextMenu) 
					{
						contextMenu.hide();
					}
					pop = new PopUp();
					pop.setHeaderText(task.getName());
					pop.setContentText(task.getDescription());

					pop.show(ee);

					pop.setOnHidden(p -> {
						pop = null;
					});
				}	
			}
			else 
			{
				if (null == contextMenu)
				{
					if (null != pop) 
					{
						pop.hide();
					}
					contextMenu = new ContextMenu();

					MenuItem newMI = new MenuItem("New task");
					newMI.setOnAction(nMI -> {
						taskViewController.addTask();
					});

					MenuItem editMI = new MenuItem("Edit task");
					editMI.setOnAction(eMI -> {
						taskViewController.editTask(task);
					});

					MenuItem deleteMI = new MenuItem("Delete task");
					deleteMI.setOnAction(dMI -> 
					{
						taskViewController.deleteTask(task);
					});

					contextMenu.getItems().addAll(newMI, new SeparatorMenuItem(), editMI, deleteMI);

					contextMenu.show(ee, e.getSceneX(), e.getSceneY()+20);

					contextMenu.setOnHidden(c -> {
						contextMenu = null;
					});
				}
			}
		});
	}

	public void closePopUp() 
	{
		if (pop != null) 
		{
			pop.hide();
		}
	}
}
