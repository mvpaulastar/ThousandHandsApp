/*
 * Punsaporn Paula Sirisumpund sol203 lab8
 * JUnit testing of all different methods
 * */
package application.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import application.model.THAppModel;

public class THAppTest {
	HashMap<Object, Object> inventory = new HashMap<Object,Object>(); //Creates HashMap
	String username = "abc123";
	String item = "paper";
	String number = "5";
	
	@Before
	public void setUp() throws Exception {
        File file=new File("data.properties"); //Opens file to search
        file.createNewFile(); //if file doesn't exist
        FileInputStream reader = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load( reader ); //Loads contents of file into properties
        reader.close(); //close file to search
        
        for( Object key: properties.stringPropertyNames() ){ //Populates hashmap 
        	inventory.put( key, properties.get(key).toString() );
        }//End for      
	}

	@Test
	public void testAddItem() throws IOException{ //Test if addItem is working
		assertEquals( item + " has been added." , THAppModel.addItem( item, number ) ); //will see if the right confirmation message was given
	}
	
	@Test
	public void testGetNumberOfItemsInInventory() throws IOException {
		assertEquals( Integer.parseInt((String)inventory.get( item )) , THAppModel.getNumberOfItemsInInventory(item) ); //checks if inventory contains the correct number of items
	}
	
	@Test
	public void testSubtractItem() throws IOException {
		assertEquals( item + " added to your cart." , THAppModel.subtractItem( item, number ) ); //check if no error message was given
	}
	
	@Test
	public void testAddUserName() throws IOException {
		assertTrue( THAppModel.addUserName(username) ); //checks if the method returned true
	}

}
