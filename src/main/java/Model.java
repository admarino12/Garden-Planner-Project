package src.main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {

	ArrayList<Plant> plantList;

	public Model() {
		plantList = new ArrayList<Plant>();
		//Creates the plantList
		try {
			File file = new File("src/resources/plants.txt");
		    Scanner reader = new Scanner(file); 
		    int tracker = 3;
		    //Loops through and creates Plants
		    while (reader.hasNextLine()) {
		    	if (tracker % 3 == 0 ) {
		    		 tracker++;
		    		 plantList.add(new Plant(reader.nextLine()));
		    	}else {
		    		  tracker++;
		    		  reader.nextLine();
		    		  
		    	}
		    }
		      reader.close();
			} catch (FileNotFoundException e) {
			      System.out.println("Problem with reading file");
			      e.printStackTrace();
			}

	}

	public void loadPlant(String plantName) {
		plantList.add(new Plant(plantName));
	}

	public ArrayList<Plant> getPlantList() {
		return plantList;
	}
	
	/**
	 * This method imports the dataWanted from the plants.txt file. 
	 * 1 corresponds to Name
	 * 2 corresponds to Icon/Char
	 * 3 corresponds to Description
	 * find is the name of the plant you would like to find.
	 * @param dataWanted
	 * @param find
	 * @return
	 */
	public static String getData(int dataWanted, String find) {
		try {
		      File file = new File("src/resources/plants.txt");
		      Scanner reader = new Scanner(file); 
		      while (reader.hasNextLine()) {
		    	  String current = reader.nextLine();
		    	  if (find.equals(current)) {
		    		  switch(dataWanted) {
		    			  case 1: 
		    				  return current;
		    			  case 2: 
		    				  current = reader.nextLine();
		    				  return current;
		    			  case 3:
		    				  current = reader.nextLine();
		    				  current = reader.nextLine();
		    				  return current;
		    		  }
		    				  
		    	  }else {
		    		  reader.nextLine();
		    	  }
		      	}
		      reader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("Problem with reading file");
		      e.printStackTrace();
		    }
		return "Error";
	}
	
	

}






