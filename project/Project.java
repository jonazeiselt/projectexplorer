package project;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Jonas Eiselt
 * @since 1 okt. 2016
 */
public class Project extends Task
{
	private ArrayList<Task> tasks;
	
	public Project(String name, String description, Date startDate, Date endDate) 
	{
		super(name, description, startDate, endDate);
	}
	
	public Project(String name, String description, Date startDate, Date endDate, ArrayList<Task> tasks) 
	{
		super(name, description, startDate, endDate);
		this.setTasks(tasks);
	}
	
	public Project(int id, String name, String description, Date startDate, Date endDate, ArrayList<Task> tasks) 
	{
		super(id, name, description, startDate, endDate);
		this.setTasks(tasks);
	}
	
	public ArrayList<Task> getTasks() 
	{
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) 
	{
		this.tasks = tasks;
	}
	
	public void addTask(Task task)
	{
		this.tasks.add(task);
	}
	
	public void editTask(Task oldTask, Task newTask)
	{
//		newTask.setId(oldTask.getId());
//		newTask.setProjectId(oldTask.getProjectId());
		
		int index = tasks.indexOf(oldTask);
		tasks.remove(index);
		tasks.add(index, newTask);
	}
	
	public void deleteTask(Task task) 
	{
		tasks.remove(task);
	}
	
	@Override
	public String toString() 
	{
		return "Project [" + super.getName() + ", Start date: " + super.getStartDate() + ", End date: "
				+ super.getEndDate() + "]";
	}
}