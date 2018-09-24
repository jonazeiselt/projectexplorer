package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import project.Project;
import project.Task;

/**
 * 
 * @author Jonas Eiselt
 * @since 16 okt. 2016
 */
public class SQLite 
{
	private Connection c = null;
	private PreparedStatement stmt = null;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void connect() 
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:db");
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	public void disconnect() 
	{
		try 
		{
			stmt.close();
			c.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void createTable()
	{
		this.createProjectTable();
		this.createTaskTable();
	}

	private void createProjectTable() 
	{
		try 
		{
			String sql = "CREATE TABLE IF NOT EXISTS PROJECT (\n"
					+ "id integer PRIMARY KEY AUTOINCREMENT NOT NULL,\n" 
					+ "name VARCHAR(100) NOT NULL,\n" 
					+ "description VARCHAR(100) NOT NULL,\n"
					+ "startdate VARCHAR(100) NOT NULL,\n"
					+ "enddate VARCHAR(100) NOT NULL\n"
					+ ");"; 
			stmt = c.prepareStatement(sql);
			stmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			// Do nothing
		}
	}

	private void createTaskTable() 
	{
		try 
		{		
			String sql = "CREATE TABLE IF NOT EXISTS TASK (\n"
					+ "id integer PRIMARY KEY AUTOINCREMENT NOT NULL,\n" 
					+ "project_id integer NOT NULL,\n"
					+ "name VARCHAR(100) NOT NULL,\n" 
					+ "description VARCHAR(100) NOT NULL,\n"
					+ "startdate VARCHAR(100) NOT NULL,\n"
					+ "enddate VARCHAR(100) NOT NULL\n"
					+ ");"; 
			stmt = c.prepareStatement(sql);
			stmt.executeUpdate();

			stmt.close();
		} 
		catch (SQLException e) 
		{
			// Do nothing
		}
	}

	public int insertProject(Project project) 
	{
		int key = -1;
		try 
		{
			String query = "INSERT INTO PROJECT (name, description, startdate, enddate) values (?,?,?,?)";
			stmt = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, project.getName());
			stmt.setString(2, project.getDescription());
			stmt.setString(3, dateToString(project.getStartDate()));
			stmt.setString(4, dateToString(project.getEndDate()));
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) 
			{
				key = rs.getInt(1);
				System.out.println("Primary key: " + key);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		return key;
	}

	public int insertTask(Task task) 
	{
		int key = -1;
		try 
		{
			String query = "INSERT INTO TASK (project_id, name, description, startdate, enddate) values (?,?,?,?,?)";
			stmt = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, task.getProjectId());
			stmt.setString(2, task.getName());
			stmt.setString(3, task.getDescription());
			stmt.setString(4, dateToString(task.getStartDate()));
			stmt.setString(5, dateToString(task.getEndDate()));
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) 
			{
				key = rs.getInt(1);
				System.out.println("-- TASK ADDED --");
				System.out.println("Primary key: " + key);
				System.out.println("Project id: " + task.getProjectId() + "\nName:" + task.getName() + "\n----------------");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		return key;
	}

	public Project getProject(int id)
	{
		Project project = null;
		ArrayList<Task> tasks = null;

		try
		{
			String sql = "SELECT * FROM PROJECT WHERE ID='" + id + "'";
			stmt = c.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(); 
			rs.next();

			project = new Project(rs.getInt("ID"), rs.getString("NAME"), rs.getString("DESCRIPTION"), getDate(rs.getString("STARTDATE")), getDate(rs.getString("ENDDATE")), tasks);
			
			tasks = this.getTasks(id);
			project.setTasks(tasks);
			
			System.out.println("-- SEL. PROJECT --");
			System.out.println("Primary key: " + id + "\nName: " + project.getName() + "\n------------------");
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return project;
	}

	private ArrayList<Task> getTasks(int id) 
	{
		ArrayList<Task> tasks = new ArrayList<Task>();
		try
		{
			String sql = "SELECT * FROM TASK WHERE PROJECT_ID='" + id + "'";
			stmt = c.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(); 
			while (rs.next())
			{
				Task task = new Task(rs.getInt("ID"), rs.getString("NAME"), rs.getString("DESCRIPTION"), getDate(rs.getString("STARTDATE")), getDate(rs.getString("ENDDATE")));
				// task.setProjectId(id);
				tasks.add(task);
				
				System.out.println(task);
			}
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return tasks;
	}

//	public Task getTask(String id) 
//	{
//		Task task = null;
//
//		try
//		{
//			String sql = "SELECT * FROM TASK WHERE ID='" + id + "'";
//			stmt = c.prepareStatement(sql);
//
//			ResultSet rs = stmt.executeQuery(); 
//			rs.next();
//
//			task = new Task(rs.getInt("ID"), rs.getString("NAME"), rs.getString("DESCRIPTION"), getDate(rs.getString("STARTDATE")), getDate(rs.getString("ENDDATE")));
//		} 
//		catch(SQLException e) 
//		{
//			e.printStackTrace();
//		}
//		return task;
//	}

	public void updateProject(Project oldProject, Project newProject) 
	{

	}

	public void updateTask(Task newTask) 
	{
		try
		{
			System.out.println(newTask.getId() + ", " + newTask.getProjectId());
			String sql = "UPDATE TASK SET name='" + newTask.getName() + "', description='" + newTask.getDescription() + "', startdate='" + dateToString(newTask.getStartDate()) + "', enddate='" + dateToString(newTask.getEndDate()) + "' WHERE id='" + newTask.getId() + "'";
			// String sql = "UPDATE TASK SET project_id = ?, name = ?, description = ?, startdate = ?, enddate = ? WHERE id = ?";
			stmt = c.prepareStatement(sql);

			//			stmt.setInt(1, newTask.getProjectId());
			//			stmt.setString(2, newTask.getName());
			//			stmt.setString(3, newTask.getDescription());
			//			stmt.setString(4, dateToString(newTask.getStartDate()));
			//			stmt.setString(5, dateToString(newTask.getEndDate()));
			//			stmt.setInt(1, oldTask.getProjectId());
			//			
			stmt.executeUpdate();
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void deleteTask(Task task) 
	{
		try 
		{
			String sql = "DELETE FROM TASK WHERE id='" + task.getId() + "'";
			stmt = c.prepareStatement(sql);
			// stmt.setInt(1, task.getId());

			stmt.executeUpdate();
			System.out.println("Task w/ id " + task.getId() + " deleted...");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	private String dateToString(java.util.Date date) 
	{	
		String dateString;

		if (date == null)
		{
			dateString = null;
		}
		else 
		{
			dateString = sdf.format(date);
		}
		return dateString;
	}

	private Date getDate(String text) 
	{
		Date date = null;
		try 
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = format.parse(text);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return date;
	}

	/* ------- Table functions ------ */
	public void renameTable(String oldName, String newName)
	{		   
		try
		{  
			stmt = (PreparedStatement) c.createStatement();		 
			stmt.executeUpdate("ALTER TABLE '" + oldName + "' RENAME TO '" + newName + "'");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void deleteTable(String tableName)
	{		   
		try
		{  
			stmt = (PreparedStatement) c.createStatement();		 
			stmt.executeUpdate("DROP TABLE IF EXISTS " + tableName);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void cleanEntries()
	{		   
		try
		{  
			Statement stmt = c.createStatement();		 
			stmt.executeUpdate("DROP TABLE IF EXISTS PROJECT");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		try
		{  
			Statement stmt = c.createStatement();		 
			stmt.executeUpdate("DROP TABLE IF EXISTS TASK");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void showTables() 
	{
		try 
		{
			DatabaseMetaData meta = c.getMetaData();
			ResultSet res = meta.getTables(null, null, null, new String[] {"TABLE"});
			System.out.println("\nTables\n----------------"); 

			while(res.next()) 
			{
				System.out.println(res.getString("TABLE_NAME")); 
			}
			System.out.println();

			res.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}