package app.util.timepicker;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

/**
 * This is a custom class that extends the Spinner class.
 * 
 * @author Project Group 3
 * @since 2015-05-26
 */
public class MinuteSpinner extends Spinner<Integer> 
{
	public MinuteSpinner() 
	{
		SpinnerValueFactory<Integer> svf = new
				SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0, 1); 
		this.setValueFactory(svf);
		
		this.setPrefWidth(70);
		this.setEditable(true);
	}

	/**
	 * This is a public method that will return the selected minute.
	 * @return minute
	 */
	public int getMinute()
	{
		return getValue();
	}
}