package app.controller;

import java.util.Optional;

import app.interfaces.TaskViewInterface;
import app.windows.AddTaskWindow;
import app.windows.EditTaskWindow;
import app.windows.TaskListWindow;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import project.Task;

/**
 * 
 * @author Jonas Eiselt
 * @since 22 okt. 2016
 */
public class TaskViewController 
{
	private PEAppController mainController;
	@SuppressWarnings("unused")
	private TaskViewInterface taskViewInterface;

	public TaskViewController(PEAppController mainController) 
	{
		this.mainController = mainController;
	}

	public PEAppController getMainController()
	{
		return mainController;
	}

	public void zoom() 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Look, an information dialog");
		alert.setContentText("Hold on, this feature will come soon..");
		alert.showAndWait();
	}

	public void addProject() 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Look, an information dialog");
		alert.setContentText("Hold on, this feature will come soon..");
		alert.showAndWait();
	}

	public void editProject() 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Look, an information dialog");
		alert.setContentText("Hold on, this feature will come soon..");
		alert.showAndWait();
	}

	public void deleteProject() 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Look, an information dialog");
		alert.setContentText("Hold on, this feature will come soon..");
		alert.showAndWait();
	}

	public void addTask() 
	{
		AddTaskWindow atw = new AddTaskWindow(mainController.getActiveProject());
		atw.showAndWait();

		try 
		{	
			if (atw.getTaskTitle() != null && atw.getDescription() != null && atw.getStartDate() != null && atw.getEndDate() != null)
			{
				Task task = new Task(atw.getTaskTitle(), atw.getDescription(), atw.getStartDate(), atw.getEndDate());
				mainController.addTask(task);
			}

		} catch (Exception e) {
			// Do nothing
		}
	}

	public void editTask(Task task) 
	{
		Task selectedTask = null;
		if (null == task)
		{
			TaskListWindow tlw = new TaskListWindow(mainController.getActiveProject());
			tlw.showAndWait();

			if (tlw.getSelectedTask() != null)
			{
				selectedTask = tlw.getSelectedTask();
			}
		}

		if (null != task || selectedTask != null)
		{
			if (selectedTask != null)
			{
				task = selectedTask;
			}
	
			EditTaskWindow etw = new EditTaskWindow(mainController.getActiveProject(), task);
			etw.showAndWait();

			try 
			{	
				if (etw.getTaskTitle() != null && etw.getDescription() != null && etw.getStartDate() != null && etw.getEndDate() != null)
				{
					Task editedTask = new Task(etw.getTaskTitle(), etw.getDescription(), etw.getStartDate(), etw.getEndDate());
					mainController.editTask(task, editedTask);
				}

			} catch (Exception e) {
				// Do nothing...
			}
		}
	}

	public void deleteTask(Task task) 
	{
		Task selectedTask = null;
		if (null == task)
		{
			TaskListWindow tlw = new TaskListWindow(mainController.getActiveProject());
			tlw.showAndWait();

			if (tlw.getSelectedTask() != null)
			{
				selectedTask = tlw.getSelectedTask();
			}
		}

		if (null != task || selectedTask != null)
		{
			if (selectedTask != null)
			{
				task = selectedTask;
			}
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Look, a Confirmation Dialog");
			alert.setContentText("Are you ok with this?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				mainController.deleteTask(task);
			} 
			else 
			{
				// Do nothing..
			}
		}
	}

	public void setLink(TaskViewInterface taskViewInterface) 
	{
		this.taskViewInterface = taskViewInterface;
	}
}