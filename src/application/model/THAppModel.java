/*
 * Punsaporn Paula Sirisumpund sol203 lab8
 * Methods used to manipulate data in the THApp
 * */
package application.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

public class THAppModel {
	//Adds item to inventory returns confirmation
	public static String addItem( String item, String number ) throws IOException { 
        File file = new File( "data.properties" ); //Creates file to add to if doesn't exist
        file.createNewFile();
		Properties properties=new Properties(); 
		FileInputStream reader = new FileInputStream(file); //File reader
        properties.load(reader); //Reads file contents
        reader.close(); //close file
        
		if( properties.containsKey(item) ) { //If item is found add to it
			String temp = String.valueOf(Integer.parseInt((String)properties.get(item)) + Integer.parseInt(number)); //Adds to value
			properties.remove(item, number); //Removes old key
			properties.put(item, temp ); //Adds new updated key
		}//end if item found
		
		else if ( !properties.containsKey(item) ){ //Create item entry if item doesn't exist 
			properties.put(item, number);
		}//end else
		
		FileOutputStream writer = new FileOutputStream("data.properties");
		properties.store(writer, null);
		
		return item + " has been added."; //returns confirmation message
	}//End addItem
	
	//Obtains number of items in inventory, returns the number
	public static int getNumberOfItemsInInventory( String item ) throws IOException { 
        HashMap<Object, Object> inventory = new HashMap<Object,Object>(); //Creates HashMap
        File file=new File("data.properties"); //Opens file to search
        file.createNewFile(); //if file doesn't exist
        FileInputStream reader = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load( reader ); //Loads contents of file into properties
        reader.close(); //close file to search
        
        for( Object key: properties.stringPropertyNames() ){ //Populates hashmap 
        	inventory.put( key, properties.get(key).toString() );
        }//End for
        
        if( inventory.containsKey( item ) ){ //If item is found display its data
        	return Integer.parseInt((String)inventory.get( item ));
        }//End if
        else { //If item not found
        	return -1;
        }
	}//End getNumberOfItemsInInventory
	
	//Subtracts the needed item from inventory, returns error or confirmation
	public static String subtractItem(String item, String number ) throws IOException { 
        File file = new File( "data.properties" ); //Creates file to add to if doesn't exist
        file.createNewFile();
		Properties properties=new Properties(); 
		FileInputStream reader = new FileInputStream(file); //File reader
        properties.load(reader); //Reads file contents
        reader.close(); //close file
		
		if( properties.containsKey(item) ) { //If item is found
			int temp = Integer.parseInt((String)properties.get(item)) - Integer.parseInt(number); //Subtract from value
			if( temp < 0 ) { //If user asks for more than available 				
				return "Error: Not enough items";
			}//End if
			else if( temp == 0 ) { //If the user takes all that is available
				properties.remove(item,number);//removes item
				
				//Writes to file
				FileOutputStream writer = new FileOutputStream("data.properties"); 
				properties.store(writer, null);
				
				return item + " added to your cart.";
			} //End else if
			else { //Removes specified number
				properties.remove(item,number); //removes old key
				properties.put(item, String.valueOf(temp));//updates key
				
				//Writes to file
				FileOutputStream writer = new FileOutputStream("data.properties"); 
				properties.store(writer, null);
				
				return item + " added to your cart.";
			}//End else
		}//End if
		else { //Item not found
			return "Error: " + item + " not found.";
		}//End else
	}//End subTractItem
	
	//adds username to txt file, returns false if username did not match pattern
	public static boolean addUserName( String name ) throws IOException { 
		if( name.matches("[a-z]{3}[0-9]{3}") ) { //Check if id matches pattern
			File file = new File("users.txt"); //Opens file to be read
			file.createNewFile(); //if file doesn't exist
			Scanner scan = new Scanner(file); //Open file to be read
			String temp; //Temp to hold strings 
			
			while( scan.hasNextLine() ) { //Loops through file to check if user exists
				temp = scan.nextLine();
				if( temp.equals(name) ) { //If user already exists exit
					scan.close(); //close file being read
					return true; //Exit method user already exists
				}
			}//end while
			scan.close(); //close file being read
			
			//If method did not return no user was found, add user
			FileWriter printer = new FileWriter( file, true ); //Printer
			printer.write(name);
			printer.write("\n");
			printer.close();//close file being written
			return true;
		}//End if
		return false; //ID did not match pattern return false
	}//End addUserName
}
