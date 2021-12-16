/*
 * Punsaporn Paula Sirisumpund sol203 lab8
 * Controls all the actions in the the Inventory.fxml
 * */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import application.model.THAppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InventoryController implements Initializable {
	@FXML
	private AnchorPane mainPane;
	@FXML 
	private TextField searchBar;
	@FXML
	private Label results;
    @FXML
    private ListView<String> list;
	
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
	public void checkInventory( ActionEvent event ) throws FileNotFoundException, IOException{ //checks list based on what user is looking for
		String item = searchBar.getText().toString(); //Reads in key to search
		int numItems = THAppModel.getNumberOfItemsInInventory( item ); //Obtains num items
		
		if( numItems == -1 )  //If Item not found
			results.setText("Item not found!"); 
		else //Print stock
			results.setText( numItems + " " + item + " in stock.");
		
        searchBar.clear(); //Clears searchbar
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { //Populates the listview of the inventory page
		try {
			ArrayList <String> inventory = new ArrayList<String>(); //Creates arraylist to hold file contents
			File file = new File("data.properties"); //Opens file to be read
			file.createNewFile(); //if file doesn't exist
			Scanner scan = new Scanner(file ); //Open file to be read
			String temp; //Temp string to hold and parse file lines
			while( scan.hasNextLine() ) { //Loops through file to populate arraylist
				temp = scan.nextLine();
				if( temp.charAt(0) != '#' ) {
					inventory.add( temp );
				}
			}//end while
			 list.getItems().addAll( inventory );
			 list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			scan.close(); //close file being read
		} catch ( IOException e ) {
			System.out.println(e);
		}
	}//End method initialize
}//End class
