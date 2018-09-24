package app.util;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * 
 * @author Jonas Eiselt
 * @since 2 okt. 2016
 */
public class ProgressIndicator extends Group 
{
	private Text t;

	public ProgressIndicator(int x, int y, double width, double height, int progress)
	{
		if (progress != -99)
		{
			t = new Text(progress + "%");
	        t.setX(x+(width/2.2));
	        t.setY(y+(height/1.5));
	        t.setFill(Color.BLACK);
	        t.setFont(Font.font(null, FontWeight.BOLD, 15));
		}
		else 
		{
			t = new Text("");
		}
		
		Rectangle r1 = new Rectangle(x, y, width, height);
		r1.setFill(Color.AQUAMARINE);
		r1.setArcHeight(15);
	    r1.setArcWidth(15);
	    
		Rectangle r2 = new Rectangle(x+(width/100), y+(height/10), width-(width/50), height-(height/5));
		r2.setFill(Color.WHITE);
		r2.setArcHeight(15);
	    r2.setArcWidth(15);
	    
	    // r2.setStroke(Color.WHEAT);
		
	    this.getChildren().addAll(r1, r2, t);
	}
	
	public void setProgress(int progress)
	{
		t.setText(progress + "%");
	}
}
