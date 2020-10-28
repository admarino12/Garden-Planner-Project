package src.main.java;

import java.util.ArrayList;

class Garden {

	// The garden is a 2D array of Characters (each plant has its own character)
	private char[][] garden;
	private int width;
	private int height;
	private ArrayList<Plant> plantsInGarden = new ArrayList<Plant>();

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

	public void addPlant(int xCord, int yCord, String name) {
		Plant plant = new Plant(name);
		garden[yCord][xCord] = plant.getChar();
		plant.setxCor(xCord);
		plant.setyCor(yCord);
		plantsInGarden.add(plant);
	}

	public void movePlant(int movexCord, int moveyCord, int xCord, int yCord) {
		for (Plant plant1 : this.getPlants()) {
			if (plant1.getxCor() == xCord && plant1.getyCor() == yCord) {
				garden[moveyCord][movexCord] = plant1.getChar();
				plant1.setxCor(movexCord);
				plant1.setxCor(moveyCord);
				garden[yCord][xCord] = 'Z';
			}
		}
	}

	public void removePlant(int xCord, int yCord) {
		for (Plant plant1 : this.getPlants()) {
			if (plant1.getxCor() == xCord && plant1.getyCor() == yCord) {
				garden[yCord][xCord] = 'Z';
				plantsInGarden.remove(plant1);
			}
		}
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

	public ArrayList<Plant> getPlants() {
		return plantsInGarden;
	}
}
