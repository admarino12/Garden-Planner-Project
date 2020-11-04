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
		//Creates the plantList where all data is taken from, this is all the available plants
		try {
			File file = new File("src/resources/plants.txt");
		    Scanner reader = new Scanner(file); 
		    //Loops through and creates Plants
		    while (reader.hasNextLine()) {
		    		 plantList.add(new Plant(reader.nextLine(),reader.nextLine()));
		    		  
		    }
		      reader.close();
			} catch (FileNotFoundException e) {
			      System.out.println("Problem with reading file");
			      e.printStackTrace();
			}

	}

	public ArrayList<Plant> getPlantList() {
		return plantList;
	}
	
	
	
	public void addPlant() {
		System.out.println("***List of Native Plants***");
		for (Plant plant1 : getPlantList()) {
			System.out.println(plant1.getName());
		}
		
		System.out.println("\nName of Plant to add: ");
		String name = scan.next();
		
		
		System.out.println("Please enter X-coordinate for plant: ");
		int xCord = scan.nextInt() - 1;
		while (xCord > garden.getGardenWidth()) {
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
		if	(garden.getGarden()[yCord][xCord] != null) {
			System.out.println("There is already a plant here!");
		}
		else {
			//Updated so that we are just copying plants over from the available plant list. 
			for (Plant plant1 : getPlantList()) {
				if (plant1.getName().equals(name)) {
					garden.addPlant(xCord, yCord, plant1.getName(),plant1.getDescription() );
				}
			}
		}
	}
	
	public void movePlant() {
		System.out.println("Available plants to move: ");
		for (Plant plant1 : garden.getPlants()) {
			System.out.println(plant1.getName() + " (" + (plant1.getxCor() + 1) + "," + (plant1.getyCor() + 1) + ")");
		}
		printLine();
		System.out.println("X-Coordinate of plant you wish to move: ");
		xCord = scan.nextInt() - 1;
		System.out.println("Y-Coordinate of plant you wish to move: ");
		yCord = scan.nextInt() - 1;
		if (garden.getGarden()[yCord][xCord] != null) {
			System.out.println("X-Coordinate of where you wish to move plant: ");
			int movexCord = scan.nextInt() - 1;
			System.out.println("Y-Coordinate of where you wish to move plant: ");
			int moveyCord = scan.nextInt() - 1;	
			if	(garden.getGarden()[moveyCord][movexCord] == null) {
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
		System.out.println("Available plants to remove: ");
		for (Plant plant1 : garden.getPlants()) {
			System.out.println(plant1.getName() + " (" + (plant1.getxCor() + 1) + "," + (plant1.getyCor() + 1) + ")");
		}
		printLine();
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






