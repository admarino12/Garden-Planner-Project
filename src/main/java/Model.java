package src.main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {
	
	Scanner scan = new Scanner(System.in);
	ArrayList<Plant> plantList;
	int gardenWidth;
	int gardenHeight;
	int xCord;
	int yCord;
	Garden garden;

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
	
	public void addPlant() {
		System.out.println("***List of Native Plants***");
		for (Plant plant1 : getPlantList()) {
			System.out.println(plant1.getName());
		}
		
		System.out.println("\nName of Plant to add (Use first word): ");
		String name = scan.next();
		
		System.out.println("Please enter X-coordinate for plant: ");
		int xCord = scan.nextInt() - 1;
		while (xCord >= garden.getGardenWidth()) {
			System.out.println("Number Cannot Exceed the Garden Width of: " + garden.getGardenWidth());
			printLine();
			System.out.println("Please enter X-coordinate for plant: ");
			xCord = scan.nextInt() - 1;
		}
		System.out.println("Please enter Y-coordinate for plant: ");
		int yCord = scan.nextInt() - 1;
		while (yCord >= garden.getGardenHeight()) {
			System.out.println("Number Exceeds the Garden Height of: " + garden.getGardenHeight());
			printLine();
			System.out.println("Please enter Y-coordinate for plant: ");
			yCord = scan.nextInt() - 1;
		}
		if	(garden.getGarden()[xCord][yCord] != 'Z') {
			System.out.println("There is already a plant here!");
		}
		else {
		garden.addPlant(xCord, yCord, name);
		}
	}
	
	public void movePlant() {
		
		System.out.println("X-Coordinate of plant you wish to move: ");
		xCord = scan.nextInt() - 1;
		System.out.println("Y-Coordinate of plant you wish to move: ");
		yCord = scan.nextInt() - 1;
		if (garden.getGarden()[xCord][yCord] != 'Z') {
			System.out.println("X-Coordinate of where you wish to move plant: ");
			int movexCord = scan.nextInt() - 1;
			System.out.println("Y-Coordinate of where you wish to move plant: ");
			int moveyCord = scan.nextInt() - 1;	
			if	(garden.getGarden()[movexCord][moveyCord] != 'Z') {
				garden.movePlant(movexCord, moveyCord, xCord, yCord);
			}
			else {
				System.out.println("There is already a plant here!");
			}
		}
		else {
			System.out.println("There is no plant here!");
		}
	}
	
	public void removePlant() {
		System.out.println("X-Coordinate of plant you wish to remove: ");
		xCord = scan.nextInt() - 1;
		System.out.println("Y-Coordinate of plant you  wish to remove: ");
		yCord = scan.nextInt() - 1;
		garden.removePlant(xCord, yCord);
	}
	
	public void printPlantsinGarden() {
		for (Plant plant1 : garden.getPlants()) {
			System.out.println(plant1.getName() + " (" + (plant1.getxCor() + 1) + "," + (plant1.getyCor() + 1) + ")");
		}
	}
	public void printLine() {
		System.out.println("******************************");
	}

}






