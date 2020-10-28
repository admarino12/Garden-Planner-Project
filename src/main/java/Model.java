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

	public Plant getPlant(String plantName) {
		// Get desired plant from plantList

		Plant placeholder = new Plant("fake name");
		return placeholder;
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


class Plant {

	private String plantName;
	private char plantChar;
	private String plantDescription;
	private int xCor;
	private int yCor;

	// Here we would search the CSV using the plantName to get the rest of the
	// information about the plant such as plantChar
	public Plant(String plantName) {
		this.plantName = plantName;

		// Both of these use the static getData method from Model
		plantChar = Model.getData(2,plantName).charAt(0);
		plantDescription = Model.getData(3,plantName);
	}

	public String getName() {
		return plantName;
	}

	public char getChar() {
		return plantChar;
	}

}

class Garden {

	// The garden is a 2D array of Characters (each plant has its own character)
	private char[][] garden;
	private int width;
	private int height;

	public Garden(int width, int height) {
		this.width = width;
		this.height = height;

		// Initialize garden based on input length and height
		garden = new char[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				garden[i][j] = 'Z';
			}
		}
	}

	public void addPlant(Plant plant, int xCord, int yCord) {
		garden[yCord][xCord] = plant.getChar();
	}

	public void movePlant(Plant plant, int movexCord, int moveyCord, int xCord, int yCord) {
		garden[yCord][xCord] = 'Z';
		garden[moveyCord][movexCord] = plant.getChar();
	}

	public void removePlant(int xCord, int yCord) {
		garden[yCord][xCord] = 'Z';
	}

	public char[][] getGarden() {
		return garden;
	}

	public int getGardenWidth() {
		return width;
	}

	public int getGardenHeight() {
		return height;
	}
}

