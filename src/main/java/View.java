package src.main.java;

import java.util.Scanner;

public class View {

	String line = "******************************";

	// garden dimensions
	int gardenWidth, gardenHeight;

	// The garden is a 2D array of Characters (each plant has its own character)
	char[][] garden;

	public void initializeGarden(Scanner scan) {

		System.out.println("ASCII Garden View");
		System.out.println(line);
		System.out.print("Enter garden length: ");
		gardenWidth = scan.nextInt();
		System.out.print("Enter garden height: ");
		gardenHeight = scan.nextInt();

		// Initialize garden based on input length and height
		garden = new char[gardenHeight][gardenWidth];
		for (int i = 0; i < gardenHeight; i++) {
			for (int j = 0; j < gardenWidth; j++) {
				garden[i][j] = 'Z';
			}
		}

		// display garden dimensions
		System.out.println("Garden dimensions are: " + gardenWidth + " x " + gardenHeight);
	}

	public int mainMenu(Scanner scan) {

		System.out.println(line + "\nMenu: ");
		System.out.println("1. Load Plants\n2. Add Plant" + "\n3. Move Plant\n4. Remove Plant\n5. Print Garden"
				+ "\n6. Exit\nChoose a number: ");

		return scan.nextInt();

	}

	public void addPlant(Scanner scan) {
		char plant = 'X';
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
		garden[xCord][yCord] = plant;
	}

	public void movePlant() {

	}

	public void removePlant(Scanner scan) {
		System.out.println("X-Coordinate of plant you wish to remove: ");
		int xCord = scan.nextInt();
		System.out.println("Y-Coordinate of plant you  wish to remove: ");
		int yCord = scan.nextInt();
		garden[yCord][xCord] = 'Z';

	}

	public void printGarden(Scanner scan) {
		System.out.print(" ");
		for (int j = 0; j < gardenWidth; j++) {
			System.out.print("__");
		}
		System.out.println();
		for (int i = 0; i < gardenHeight; i++) {
			System.out.print("|");
			for (int j = 0; j < gardenWidth; j++) {
				//
				// Display contents of the 2D garden character array here
				//
				if (garden[i][j] == 'Z') {
					System.out.print("  ");
				} else
					System.out.print(garden[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.print("|");
		for (int j = 0; j < gardenWidth; j++) {
			System.out.print("__");
		}
		System.out.println("|");
		System.out.println();

		System.out.println("Return to Menu? (Y)");

		String userInput = scan.next();
		while (!userInput.contains("Y")) {
			userInput = scan.next();
		}

	}
}
