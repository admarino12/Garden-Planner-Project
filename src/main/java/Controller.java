package src.main.java;

import java.util.Scanner;

public class Controller {
	
	public static void main(String[] args) {
			
			String line = "******************************";
			Scanner scan = new Scanner(System.in);
			View view = new View();
			Model model = new Model();
			

			System.out.println("ASCII Garden View");
			System.out.println(line);
			System.out.print("Enter garden length: ");
			int gardenWidth = scan.nextInt();
			System.out.print("Enter garden height: ");
			int gardenHeight = scan.nextInt();
			
			//Create the garden
			Garden garden = new Garden(gardenWidth, gardenHeight);
			
			// display garden dimensions
			System.out.println("Garden dimensions are: " + gardenWidth + " x " + gardenHeight);
			
			//menu
			boolean menuLoop = true;
			while(menuLoop) {
				
				int menuChoice = view.mainMenu(scan);
				
				switch(menuChoice) {
				case 1:
					
					System.out.println("Name of Plant:");
					String plantName = scan.next();
					//load plant data
					System.out.println("Load "+plantName+" from file? (Y/N): ");
					String userInput = scan.next();
						if (userInput.equals("Y")) {
							
							model.loadPlant(plantName);
						}
						else {
							break;
						}
					break;
					
				case 2:
					
					//System.out.println("Name of Plant to add: ");
					//String userInputString = scan.next();
					//model.getPlant("fake name");
					Plant plant = new Plant("fake");
					System.out.println("Please enter X-coordinate for plant: ");
					int xCord = scan.nextInt();
					while (xCord >= gardenWidth) {
						xCord = scan.nextInt();
						System.out.println("Please enter X-coordinate for plant: ");
					}
					System.out.println("Please enter Y-coordinate for plant: ");
					int yCord = scan.nextInt();
					while (yCord >= gardenHeight) {
						yCord = scan.nextInt();
						System.out.println("Please enter Y-coordinate for plant: ");
					}
					garden.addPlant(plant, xCord, yCord);
					break;
				case 3:
					view.movePlant();
					break;
				case 4: //Remove Plant
					System.out.println("X-Coordinate of plant you wish to remove: ");
					xCord = scan.nextInt();
					System.out.println("Y-Coordinate of plant you  wish to remove: ");
					yCord = scan.nextInt();
					garden.removePlant(xCord, yCord);
					break;
				case 5: 
					view.printGarden(scan, garden.getGarden(), garden.getGardenWidth(), garden.getGardenHeight());
					break;
				case 6: 
					//exit
					menuLoop = false;
					System.out.println("Exiting garden planner.");
					break;
				default:
					System.out.println("Error: Please choose from 1-6.");
			}
		}
	}
}
