package src.main.java;

import java.util.Scanner;

public class Controller {
	
	public static void main(String[] args) {
			
			String line = "******************************";
			Scanner scan = new Scanner(System.in);
			View view = new View();
			Model model = new Model();
			
			
			view.initializeGarden(scan);
			
			
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
					
					System.out.println("Name of Plant to add: ");
					String userInputString = scan.next();
					model.getPlant("fake name");
					view.addPlant();
					break;
				case 3:
					view.movePlant();
					break;
				case 4:
					view.removePlant(scan);
					break;
				case 5: 
					view.printGarden(scan);
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
