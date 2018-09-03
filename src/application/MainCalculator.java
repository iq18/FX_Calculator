package application;

/**
 * Program to create simplified calculator.
 * Calculator performs basic operations on two
 * integers only. (add, subtract, multiply and divide)
 * 
 * @author Ivan
 * @version 1
 */
	
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class MainCalculator extends Application {
	
	private ArrayList<String> numbersAsString;
	private ArrayList<Button> buttons;
	private ArrayList<Button> operations;
	private MathLogic mathLogic;
	private int one;
	private int two;
	private int result;
	private Button operation;
	private String numsAsString;
	private TextField tf2;
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {

			buttons = new ArrayList<Button>();
			operations = new ArrayList<Button>();
			mathLogic = new MathLogic();
			numbersAsString = new ArrayList<String>();

			BorderPane root = new BorderPane();
			FlowPane fPane = new FlowPane();
			fPane.setHgap(5);
			fPane.setVgap(5);

			TextField tf = new TextField();
			tf.setMinSize(450, 70);
			tf.setLayoutX(0);
			tf.setLayoutY(0);
			BorderPane.setMargin(tf, new Insets(0, 0, 5, 0));
			tf.getStyleClass().add("text-fields");

			tf2 = new TextField();
			tf2.setMinSize(450, 70);
			tf2.setLayoutX(0);
			tf2.setLayoutY(0);
			BorderPane.setMargin(tf2, new Insets(0, 0, 5, 0));
			tf2.getStyleClass().add("text-fields");

			// number Buttons
			Button b0 = new Button();
			buttons.add(b0);
			Button b1 = new Button();
			buttons.add(b1);
			Button b2 = new Button();
			buttons.add(b2);
			Button b3 = new Button();
			buttons.add(b3);
			Button b4 = new Button();
			buttons.add(b4);
			Button b5 = new Button();
			buttons.add(b5);
			Button b6 = new Button();
			buttons.add(b6);
			Button b7 = new Button();
			buttons.add(b7);
			Button b8 = new Button();
			buttons.add(b8);
			Button b9 = new Button();
			buttons.add(b9);

			// set Button properties
			for (int x = 0; x <= 9; x++) {
				// set text(value) of button
				buttons.get(x).setText(String.valueOf(x));
				buttons.get(x).setMinHeight(80);
				buttons.get(x).setMinWidth(100);
				buttons.get(x).getStyleClass().add("num-buttons");
				// add buttons to FlowPane
				fPane.getChildren().add(buttons.get(x));
			}
			// EventListeners for Buttons
			for (Button b : buttons) {
				b.setOnAction(e -> {
					numbersAsString.add(b.getText());
					// regex to remove commas, square brackets and spaces from ArrayList toString
					// output
					numsAsString = "" + numbersAsString;
					numsAsString = numsAsString.replaceAll("\\W", "");
					if (root.getTop() == tf) {
						tf.setText(numsAsString);
					} else if (root.getTop() == tf2) {
						tf2.setText(numsAsString);
					}
				});
			}

			// Button to trigger calculate function
			Button calculate = new Button("Calculate");
			calculate.setMinSize(200, 80);
			calculate.getStyleClass().add("num-buttons");
			fPane.getChildren().add(calculate);

			// align larger Button | margin to account for HGap between smaller buttons
			FlowPane.setMargin(calculate, new Insets(0, 0, 0, 5));
			// set margin for leftmost Buttons
			for (int x = 0; x < 9; x += 4) {
				FlowPane.setMargin(buttons.get(x), new Insets(0, 0, 0, 15));
			}

			// operation Buttons
			Button add = new Button("\u002B");
			operations.add(add);
			Button subtract = new Button("\u2212");
			operations.add(subtract);
			Button multiply = new Button("\u00D7");
			operations.add(multiply);
			Button divide = new Button("\u00F7");
			operations.add(divide);
			// add properties to operation Buttons
			for (Button b : operations) {
				b.setMinHeight(80);
				b.setMinWidth(100);
				b.getStyleClass().add("ops");
				fPane.getChildren().add(b);
				// set operation Button actions
				b.setOnAction(e -> {
					// clear first number from text field
					tf.clear();
					// pass first number to Logic method
					one = mathLogic.getNumber(numbersAsString);
					// clear List
					numbersAsString.clear();
					// replace text field
					root.setTop(tf2);
					// store operation Button to pass to calculate method
					operation = b;

				});
			}
			// align operation Buttons
			FlowPane.setMargin(add, new Insets(0, 0, 0, 15));

			// get second number and pass numbers and operation to calculate
			calculate.setOnAction(e -> {
				two = mathLogic.getNumber(numbersAsString);
				numbersAsString.clear();
				tf2.clear();
				result = mathLogic.calculate(operation, one, two);
				tf2.setText(String.valueOf(result));
				reset();

			});

			// set display
			root.setTop(tf);
			root.setCenter(fPane);
			Scene scene = new Scene(root, 450, 420);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to reset to allow multiple calculations
	 */
	private void reset() {
		mathLogic = new MathLogic();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
