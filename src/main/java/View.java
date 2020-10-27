package src.main.java;

import java.util.Scanner;

public class View {


	// garden dimensions
	int gardenWidth, gardenHeight;

	public int mainMenu(Scanner scan) {
		printLine();
		System.out.println("\nMenu: ");
		System.out.println("1. Load Plants\n2. Add Plant" + "\n3. Move Plant\n4. Remove Plant\n5. Print Garden"
				+ "\n6. Exit\nChoose a number: ");

		return scan.nextInt();

	}

	public void movePlant() {

	}

	public void printGarden( char[][] garden, int gardenWidth, int gardenHeight) {
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
	}

	
	public void printLine() {
		System.out.println("******************************");
	}
}
