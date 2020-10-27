package src.main.java;

import java.util.Scanner;

public class Controller {
	
	public static void main(String[] args) {
			
			String line = "******************************";
			Scanner scan = new Scanner(System.in);
			
			//garden dimensions
			int gardenLength, gardenHeight;
			
			System.out.println("ASCII Garden View");
			System.out.println(line);
			System.out.print("Enter garden length: ");
			gardenLength = scan.nextInt();
			System.out.print("Enter garden height: ");
			gardenHeight = scan.nextInt();
			
			//display garden dimensions
			System.out.println("Garden dimensions are: " 
			+ gardenLength +  " x " + gardenHeight);
			
			//menu
			boolean menuLoop = true;
			while(menuLoop) {
				System.out.println(line + "\nMenu: ");
				System.out.println("1. Load Plants\n2. Add Plant"
						+ "\n3. Move Plant\n4. Remove Plant\n5. Print Garden"
						+ "\n6. Exit\nChoose a number: ");
				
				int menuChoice = scan.nextInt();
				
				switch(menuChoice) {
				case 1:
					//load plant data
					System.out.println("Load Plant from file? (Y/N): ");
					String userInput = scan.next();
						if (userInput.equals("Y")) {
							//read in from file
							//loadPlants();
						}
						else {
							break;
						}
					break;
				case 2:
					//add plant to garden view
					//addPlant();
					break;
				case 3:
					//move plant
					//movePlant();
					break;
				case 4:
					//remove plant
					//removePlant();
					break;
				case 5: 
					//print garden
					//printGarden();
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
