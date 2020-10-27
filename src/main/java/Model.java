package src.main.java;

import java.util.ArrayList;

public class Model {

	ArrayList<Plant> plantList;

	public Model() {
		plantList = new ArrayList<Plant>();

	}

	public void loadPlant(String plantName) {
		plantList.add(new Plant(plantName));
	}

	public Plant getPlant(String plantName) {
		// Get desired plant from plantList

		Plant placeholder = new Plant("fake name");
		return placeholder;
	}

}

class Plant {

	private String plantName;
	private char plantChar;
	private int xCor;
	private int yCor;

	// Here we would search the CSV using the plantName to get the rest of the
	// information about the plant such as plantChar
	public Plant(String plantName) {
		this.plantName = plantName;

		// Default for now
		plantChar = 'F';
	}

	public String getName() {
		return plantName;
	}

	public char getChar() {
		return plantChar;
	}

}

class Garden {

	// The garden is a 2D array of Characters (each plant has its own character)
	private char[][] garden;
	private int width;
	private int height;

	public Garden(int width, int height) {
		this.width = width;
		this.height = height;

		// Initialize garden based on input length and height
		garden = new char[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				garden[i][j] = 'Z';
			}
		}
	}

	public void addPlant(Plant plant, int xCord, int yCord) {
		garden[yCord][xCord] = plant.getChar();
	}

	public void movePlant(Plant plant, int movexCord, int moveyCord, int xCord, int yCord) {
		garden[yCord][xCord] = 'Z';
		garden[moveyCord][movexCord] = plant.getChar();
	}

	public void removePlant(int xCord, int yCord) {
		garden[yCord][xCord] = 'Z';
	}

	public char[][] getGarden() {
		return garden;
	}

	public int getGardenWidth() {
		return width;
	}

	public int getGardenHeight() {
		return height;
	}
}