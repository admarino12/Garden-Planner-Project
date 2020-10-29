package src.main.java;

import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		View view = new View();
		Model model = new Model();
		String userInput;
		

		System.out.println("ASCII Garden Planner");
		view.printLine();
		System.out.print("Enter garden length: ");
		int gardenWidth = scan.nextInt();
		System.out.print("Enter garden height: ");
		int gardenHeight = scan.nextInt();

		// Create the garden
		Garden garden = new Garden(gardenWidth, gardenHeight);
		model.garden = garden;

		// display garden dimensions
		System.out.println("Garden dimensions are: " + gardenWidth + " x " + gardenHeight);

		// menu
		boolean menuLoop = true;
		while (menuLoop) {

			int menuChoice = view.mainMenu(scan);

			switch (menuChoice) {
			case 1: // Add Plant
				model.addPlant();
				break;
			case 2: // Move Plant
				model.movePlant();
				break;
			case 3: // Remove Plant
				model.removePlant();
				break;
			case 4:
				view.printGarden(garden.getGarden(), garden.getGardenWidth(), garden.getGardenHeight());

				System.out.println("Return to Menu? (Y)");

				userInput = scan.next();
				while (!userInput.contains("Y")) {
					userInput = scan.next();
				}
				break;
			case 5: //Print Plants in Garden
				model.printPlantsinGarden();
				break;
			case 6: // Exit
				menuLoop = false;
				System.out.println("Exiting garden planner.");
				break;
			default:
				System.out.println("Error: Please choose from 1-6.");
			}
		}
	}
}
