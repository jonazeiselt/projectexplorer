package app.controller;

import app.interfaces.TaskViewInterface;
import app.util.LayoutSettings;
import database.SQLite;
import project.Project;
import project.Task;

public class PEAppController 
{
	private Project project;
	private TaskViewInterface taskViewInterface;

	private SQLite sql;

	public PEAppController() 
	{
		LayoutSettings.init(); // Change to non-static class?

		sql = new SQLite();
		sql.connect();

		// sql.cleanEntries();
		sql.createTable();

		sql.showTables();
	}	

	public Project getActiveProject()
	{
		return project;
	}

	public void setActiveProject(Project project)
	{
		this.project = project;
	}

	public void setActiveProject(int id)
	{
		this.project = sql.getProject(id);
	}
	
	public void addProject(Project project) 
	{
		int key = sql.insertProject(project);
		project.setId(key);

		System.out.println(project + " added..");

		this.setActiveProject(project);
		taskViewInterface.refresh();
	}

	public void addTask(Task task) 
	{
		try 
		{
			task.setProjectId(project.getId());
			project.addTask(task);
			
			int key = sql.insertTask(task);
			task.setId(key);

			System.out.println(task + " added..");
			taskViewInterface.refresh();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void setLink(TaskViewInterface taskViewInterface) 
	{
		this.taskViewInterface = taskViewInterface;
	}

	public void editTask(Task oldTask, Task editedTask) 
	{
		try 
		{
			editedTask.setId(oldTask.getId());
			editedTask.setProjectId(project.getId());
			
			System.out.println("ID: " + editedTask.getId() + "\nProject ID: " + editedTask.getProjectId());
			
			project.editTask(oldTask, editedTask);
			sql.updateTask(editedTask);
			
			System.out.println(oldTask + " edited..");
			taskViewInterface.refresh();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void deleteTask(Task task) 
	{
		try 
		{
			project.deleteTask(task);
			sql.deleteTask(task);

			System.out.println(task + " deleted..");
			taskViewInterface.refresh();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}