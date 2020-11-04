package src.main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

	View view;
	Model model;
	Scanner scan;
	Garden garden;
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.menuChoiceLoop();
	}
	
	public Controller() {
		view = new View();
		model = new Model(loadPlantList());
		scan = new Scanner(System.in);
		
		System.out.println("ASCII Garden Planner");
		view.printLine();
		System.out.print("Enter garden length: ");
		int gardenWidth = scan.nextInt();
		System.out.print("Enter garden height: ");
		int gardenHeight = scan.nextInt();

		// Create the garden
		garden = new Garden(gardenWidth, gardenHeight);
		model.garden = garden;

		// display garden dimensions
		System.out.println("Garden dimensions are: " + gardenWidth + " x " + gardenHeight);
	}
	
	public void menuChoiceLoop() {
		// menu
		String userInput;
		boolean menuLoop = true;
		
		while (menuLoop) {
			int menuChoice = view.mainMenu(scan);

			switch (menuChoice) {
			case 1: // Add Plant
				addPlantLogic();
				break;
			case 2: // Move Plant
				movePlantLogic();
				break;
			case 3: // Remove Plant
				removePlantLogic();
				break;
			case 4: //Print UI of Garden
				view.printGarden(garden.getGarden(), garden.getGardenWidth(), garden.getGardenHeight());

				System.out.println("Return to Menu? (Y)");

				userInput = scan.next();
				while (!userInput.contains("Y")) {
					userInput = scan.next();
				}
				break;
			case 5: //Print List of Plants in Garden
				view.printPlantsinGarden(garden);
				break;
			case 6:
				searchPlantByTraitLogic();
				break;
			case 7: // Exit
				menuLoop = false;
				System.out.println("Exiting garden planner.");
				scan.close();
				break;
			default:
				System.out.println("Error: Please choose from 1-6.");
			}
		}
	}
	
	public void addPlantLogic() {
		System.out.println("***List of Native Plants***");
		for (Plant plant1 : model.getPlantList()) {
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
			for (Plant plant1 : model.getPlantList()) {
				if (name.equals(plant1.getName())) {
					garden.addPlant(xCord, yCord, plant1.getName(),plant1.getDescription(), plant1.getTraits() );
				}
			}
		}
	}
	
	public void movePlantLogic() {
		System.out.println("Available plants to move: ");
		for (Plant plant1 : garden.getPlants()) {
			System.out.println(plant1.getName() + " (" + (plant1.getxCor() + 1) + "," + (plant1.getyCor() + 1) + ")");
		}
		printLine();
		System.out.println("X-Coordinate of plant you wish to move: ");
		int xCord = scan.nextInt() - 1;
		System.out.println("Y-Coordinate of plant you wish to move: ");
		int yCord = scan.nextInt() - 1;
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
	
	public void removePlantLogic() {
		System.out.println("Available plants to remove: ");
		for (Plant plant1 : garden.getPlants()) {
			System.out.println(plant1.getName() + " (" + (plant1.getxCor() + 1) + "," + (plant1.getyCor() + 1) + ")");
		}
		printLine();
		System.out.println("X-Coordinate of plant you wish to remove: ");
		int xCord = scan.nextInt() - 1;
		System.out.println("Y-Coordinate of plant you  wish to remove: ");
		int yCord = scan.nextInt() - 1;
		garden.removePlant(xCord, yCord);
	}
	
	public void searchPlantByTraitLogic() {
		System.out.println("Possible Characteristics to Search By:");
		String[] traits = model.getPlantTraits().split(", ");
		for(String trait : traits) {
			System.out.println(trait);
		}
		
		System.out.println("Search plant by Characteristic: ");
		String search = scan.next();
		ArrayList<Plant> searchResults = model.searchPlantListByTrait(search);
		for( Plant plant : searchResults) {
			System.out.println(plant.getName());
		}
		
	}
	
	public ArrayList<Plant> loadPlantList(){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		String line = "";
		
		//Read each line of the CSV file and pull the plant name, description, and String array of plant traits
		try {
			FileReader file = new FileReader("src/resources/plants.csv");
			BufferedReader csvFile = new BufferedReader(file);
			
			while((line = csvFile.readLine()) != null) {
				
				String[] plant = line.split(",");
				String[] plantTraits = plant[2].split("-");
				plantList.add(  new Plant(plant[0], plant[1], plantTraits) );
				
			}			
			csvFile.close();
		
		}
		catch (IOException e) { e.printStackTrace(); }
		
		return plantList;
	}
	
	public static void printLine() {
		System.out.println("******************************");
	}
}
