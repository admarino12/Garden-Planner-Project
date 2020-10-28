package src.main.java;

import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		View view = new View();
		Model model = new Model();
		
		
		Plant plant = new Plant("fake");

		System.out.println("ASCII Garden View");
		view.printLine();
		System.out.print("Enter garden length: ");
		int gardenWidth = scan.nextInt();
		System.out.print("Enter garden height: ");
		int gardenHeight = scan.nextInt();

		// Create the garden
		Garden garden = new Garden(gardenWidth, gardenHeight);

		// display garden dimensions
		System.out.println("Garden dimensions are: " + gardenWidth + " x " + gardenHeight);

		// menu
		boolean menuLoop = true;
		while (menuLoop) {

			int menuChoice = view.mainMenu(scan);

			switch (menuChoice) {
			case 1: // Load Plant

				System.out.println("Name of Plant:");
				String plantName = scan.next();
				// load plant data
				System.out.println("Load " + plantName + " from file? (Y/N): ");
				String userInput = scan.next();
				if (userInput.equals("Y")) {

					model.loadPlant(plantName);
				} else {
					break;
				}
				break;

			case 2: // Add Plant

				// TODO: Ask user for plant which plant they want to use.
				// System.out.println("Name of Plant to add: ");
				// String userInputString = scan.next();
				// model.getPlant("fake name");

				System.out.println("Please enter X-coordinate for plant: ");
				int xCord = scan.nextInt() - 1;
				while (xCord >= gardenWidth) {
					System.out.println("Number Cannot Exceed the Garden Width of: " + garden.getGardenWidth());
					view.printLine();
					System.out.println("Please enter X-coordinate for plant: ");
					xCord = scan.nextInt();
				}
				System.out.println("Please enter Y-coordinate for plant: ");
				int yCord = scan.nextInt() - 1;
				while (yCord >= gardenHeight) {
					System.out.println("Number Exceeds the Garden Height of: " + garden.getGardenHeight());
					view.printLine();
					System.out.println("Please enter Y-coordinate for plant: ");
					yCord = scan.nextInt();
				}
				garden.addPlant(plant, xCord, yCord);
				break;
			case 3: // Move Plant
				System.out.println("X-Coordinate of plant you wish to move: ");
				xCord = scan.nextInt();
				System.out.println("Y-Coordinate of plant you wish to move: ");
				yCord = scan.nextInt();
				if (garden.getGarden()[xCord][yCord] != 'Z') {
					garden.removePlant(xCord, yCord);
					System.out.println("X-Coordinate of where you wish to move plant: ");
					int movexCord = scan.nextInt();
					System.out.println("Y-Coordinate of where you wish to move plant: ");
					int moveyCord = scan.nextInt();
					garden.movePlant(plant, movexCord, moveyCord, movexCord, moveyCord);
				} 
				else {
					System.out.println("There is not plant here!/n");
				}
				break;
			case 4: // Remove Plant
				System.out.println("X-Coordinate of plant you wish to remove: ");
				xCord = scan.nextInt();
				System.out.println("Y-Coordinate of plant you  wish to remove: ");
				yCord = scan.nextInt();
				garden.removePlant(xCord, yCord);
				break;
			case 5:
				view.printGarden(garden.getGarden(), garden.getGardenWidth(), garden.getGardenHeight());

				System.out.println("Return to Menu? (Y)");

				userInput = scan.next();
				while (!userInput.contains("Y")) {
					userInput = scan.next();
				}
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
