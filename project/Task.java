package project;

import java.util.Date;

/**
 * 
 * @author Jonas Eiselt
 * @since 1 okt. 2016
 */
public class Task 
{
	private String name;
	private String description;
	
	private Date startDate;
	private Date endDate;
	
	private int id;
	private int projectId;
	
	public Task(String name, String description, Date startDate, Date endDate)
	{
		this.setName(name);
		this.setDescription(description);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}
	
	public Task(int id, String name, String description, Date startDate, Date endDate) 
	{
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Date getStartDate() 
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate) 
	{
		this.endDate = endDate;
	}
	
	public int getProjectId() 
	{
		return projectId;
	}
	
	public void setProjectId(int projectId)
	{
		this.projectId = projectId;
	}
	
	@Override
	public String toString() 
	{
		return "Task [name=" + name + ", description=" + description + ", startDate=" + startDate + ", endDate="
				+ endDate + ", id=" + id + "]";
	}
}