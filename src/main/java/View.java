package src.main.java;

import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View {

	int canvasWidth = 1200;
	int canvasHeight = 700;
	
	// garden dimensions
	int gardenWidth, gardenHeight;
	Controller controller;
	
	
	public View(Stage theStage, Controller controller) {
		this.controller = controller;
		
		theStage.setTitle("Garden Builder");
		Group root = new Group();
		Scene theScene = new Scene(root, canvasWidth, canvasHeight);
		theStage.setScene(theScene);
		
		BorderPane borderPane = new BorderPane();
		root.getChildren().add(borderPane);
	}
	
	
	
	
	
	
	
	
	/*
	 * THIS IS LEFT OVERFROM ASCII
	 */
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
	
	
	/*
	 * THIS IS LEFT OVERFROM ASCII
	 */
	public void printPlantsinGarden(Garden garden) {
		for (Plant plant1 : garden.getPlants()) {
			System.out.println(plant1.getName() + " (" + (plant1.getxCor() + 1) + "," + (plant1.getyCor() + 1) + ")");
		}
	}

	/*
	 * THIS IS LEFT OVERFROM ASCII
	 */
	public void printLine() {
		System.out.println("******************************");
	}
}

