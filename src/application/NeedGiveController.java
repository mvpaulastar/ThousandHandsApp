/*
 * Punsaporn Paula Sirisumpund sol203 lab8
 * Controls all the actions in the the NeedGive.fxml
 * */
package application;

import java.io.FileNotFoundException;
import application.model.THAppModel;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NeedGiveController{	
	@FXML
	private AnchorPane mainPane;
	@FXML
	private TextField itemToAdd;
	@FXML
	private TextField userToAdd;
	@FXML
	private TextField numToAdd;
	@FXML
	private Label myMessage;
	@FXML 
	private Label toggler;
	
	public String getMyMessageText() { //Returns the string in the myMessage label
		return myMessage.getText();
	}
	
	public void setToggler( String value ) { //Sets the NeedGive.fxml to add or subtract depending on which button the user pressed
		toggler.setText(value);
	}
	
	@FXML
	public void goHome( ActionEvent event ) throws IOException {//Returns user back to Main.fxml
		mainPane = FXMLLoader.load(getClass().getResource("Main.fxml")); //Goes to home pane
		Scene scene = new Scene(mainPane); //Pane you are going to show
		Node eventStage = (Node) event.getSource(); //Gets object where button was pressed
		Stage window = (Stage)eventStage.getScene().getWindow(); //Gets primaryStage Window
		window.setScene( scene );
		window.show();
	}//End method goHome
	
	@FXML
	public void processItems( ActionEvent event ) throws IOException, FileNotFoundException{ //processes whether to add or subtract an item
		String item = itemToAdd.getText().toString(); //Reads in item to add/subtract
		String number = numToAdd.getText().toString(); //Reads in number of item to add/subtract
		String user = userToAdd.getText().toString(); //Reads in user id
	
		Boolean checker = THAppModel.addUserName(user); //Adds user
		if( checker == false ){//If user did not match abc123 format print errors
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Invalid ID not in the abc123 format, no action taken!");
			a.show(); //show error
			myMessage.setText("ERROR: Invalid ID");
			return;
		}//end if
		
		if( toggler.getText() == "Give") { //If give
			String result = THAppModel.addItem(item, number); //Adds item	
			
			//Creates and sends a confirmation alert
			Alert a = new Alert(AlertType.NONE);
			a.setAlertType(AlertType.CONFIRMATION);// set alert type
			a.setContentText("Given: " + item + "\nQuantity: " + number);
			a.show();// show the dialog	
			
			myMessage.setText(result); //prints message under add button
		}//End if
		else if( toggler.getText() == "Need" ) { //If need
			String result = THAppModel.subtractItem(item, number); //Subtracts Item
			
			if( result.matches(item + " added to your cart.")) {
				//Creates and sends a confirmation alert
				Alert a = new Alert(AlertType.NONE);
				a.setAlertType(AlertType.CONFIRMATION);// set alert type
				a.setContentText("Taken: " + item + "\nQuantity: " + number);
				a.show();// show the dialog
			}
			else { //Otherwise there was an error
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("There was an error with your request. No action taken!"); //Give prompt and don't subtract
				a.show(); //show dialog
			}
			
			myMessage.setText(result); //prints message under subtract button
		}//End else if
		
		//Clears all the text fields
		itemToAdd.clear();
		userToAdd.clear();
		numToAdd.clear();
	}//End processItems
}//End class
