package src.main.java;

import java.util.Scanner;

public class View {


	// garden dimensions
	int gardenWidth, gardenHeight;

	public int mainMenu(Scanner scan) {
		
		printLine();
		System.out.println("\nMenu: ");
		System.out.println("1. Add Plant" + "\n2. Move Plant\n3. Remove Plant\n4. Print Garden"
				+ "\n5. Print List of Plants\n" + "6. Exit\nChoose a number: ");

		return scan.nextInt();

	}

	public void printGarden( Plant[][] garden, int gardenWidth, int gardenHeight) {
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
				if (garden[i][j] == null) {
					System.out.print("  ");
				} else
					System.out.print(garden[i][j].getChar() + " ");
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
