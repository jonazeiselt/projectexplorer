package app.util;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 
 * @author Jonas Eiselt
 * @since 6 okt. 2016
 */
public class LayoutSettings 
{
	private static double screenWidth;
	private static double screenHeight;

	private static int dateWidth = 50;
	private static int dateHeight = 40;
	
	private static int descriptionWidth = 200;
	
	public static void init() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		setScreenWidth(screenSize.getWidth());
		setScreenHeight(screenSize.getHeight());
	}

	public static int getScreenWidth() 
	{
		return (int) screenWidth;
	}

	public static void setScreenWidth(double screenWidth) 
	{
		LayoutSettings.screenWidth = screenWidth;
	}

	public static int getScreenHeight() 
	{
		return (int) screenHeight;
	}

	public static void setScreenHeight(double screenHeight) 
	{
		LayoutSettings.screenHeight = screenHeight;
	}
	
	public static int getDateWidth()
	{
		return dateWidth;
	}
	
	public static int getDateHeight()
	{
		return dateHeight;
	}
	
	public static int getDescriptionWidth()
	{
		return descriptionWidth;
	}
}