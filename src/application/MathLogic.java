package application;

/**
 * Program to provide methods to retrieve String values
 * convert to Integers, perform calculations and return
 * results.
 */

import java.util.ArrayList;

import javafx.scene.control.Button;

public class MathLogic {
	
	private int number;
	private String numberAsString;
	private final String add = "\u002B";
	private final String subtract = "\u2212";
	private final String multiply = "\u00D7";
	private final String divide = "\u00F7";
	private StringBuilder sb;
	
	public MathLogic() {
		numberAsString = "";
		number =0;
	}
	
	/**
	 * Method to retrieve number from GUI
	 * @param nums ArrayList of number as String
	 * @return integer value of text entry
	 */
	public int getNumber(ArrayList<String> nums) {
		numberAsString = "";
		sb = new StringBuilder();
		//ensure ArrayList not empty
		if (nums.isEmpty()) {
			System.out.println("Empty number field");
		} else {
			//create String from ArrayList
			for (String s : nums) {
				sb.append(s);
				numberAsString = sb.toString();
			}
		}
		//add Integer value of String to return variable
		try {
			number = Integer.valueOf(numberAsString);
		} catch (NumberFormatException e) {
			System.out.println("Not a valid number");
			e.printStackTrace();
		}
		
		return number;
	}
	
	
	/**
	 * Method to perform calculations
	 * @param b operation Button 
	 * @param n1 first integer for calculation
	 * @param n2 second integer for calculation
	 * @return result of operation on number parameters
	 */
	public int calculate(Button b, int n1, int n2) {
		//hold return value
		int answer = 0;
		//switch to apply operation | 
		//no default only Button text can be entered
		switch(b.getText()) {
		case add:
			answer = n1 + n2;
			break;
		case subtract:
			answer = n1 - n2;
			break;
		case multiply:
			answer = n1 * n2;
			break;
		case divide:
			answer = n1 / n2;
			break;
		}
		return answer;
	}
}
