/*
 * Punsaporn Paula Sirisumpund sol203 lab8
 * Controls all the actions in the the Main.fxml
 * */
package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private AnchorPane mainPane;
	@FXML
	private Button need;
	@FXML
	private Button give;
	
	@FXML
	public void goInventory( ActionEvent event ) throws IOException {//Changes the scene to Inventory.fxml
		mainPane = FXMLLoader.load(getClass().getResource("Inventory.fxml")); //Goes to inventory pane
		Scene scene = new Scene(mainPane); //Pane you are going to show
		Node eventStage = (Node) event.getSource();//Gets object where button was pressed
		Stage window = (Stage)eventStage.getScene().getWindow();//Gets primaryStage Window
		window.setScene( scene );
		window.show();
	}//End method goInventory
	
	@FXML
	public void goNeed( ActionEvent event ) throws IOException { //Changes the scene to NeedGive.fxml from need button
		FXMLLoader load = new FXMLLoader(getClass().getResource("NeedGive.fxml") ); //Grabs NeedGive pane
		mainPane = load.load(); //Loads the pane into mainPane
		NeedGiveController needControl = load.getController(); //Obtains the NeedGiveController
		needControl.setToggler("Need"); //Sets the mode to Need
		Stage window = (Stage)need.getScene().getWindow(); //Obtains the window the button was pressed on
		Scene scene = new Scene(mainPane); //Creates the new scene to hold the new page
		window.setScene( scene ); //Changes the scene
		window.show();
	}//End NeedGive
	
	@FXML
	public void goGive( ActionEvent event ) throws IOException { //Changes the scene to NeedGive.fxml from give button
		FXMLLoader load = new FXMLLoader(getClass().getResource("NeedGive.fxml") ); //Grabs NeedGive pane
		mainPane = load.load(); //Loads the pane into mainPane
		NeedGiveController giveControl = load.getController(); //Obtains the NeedGiveController
		giveControl.setToggler("Give"); //Sets the mode to Need
		Stage window = (Stage)give.getScene().getWindow(); //Obtains the window the button was pressed on
		Scene scene = new Scene(mainPane); //Creates the new scene to hold the new page
		window.setScene( scene ); //Changes the scene
		window.show();
	}//End NeedGive
}
