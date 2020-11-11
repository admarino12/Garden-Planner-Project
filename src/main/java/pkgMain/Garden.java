package pkgMain;

import java.util.ArrayList;

class Garden {

	// The garden is a 2D array of Characters (each plant has its own character)
	private Plant[][] garden;
	private int width;
	private int height;
	private ArrayList<Plant> plantsInGarden = new ArrayList<Plant>();

	public Garden(int width, int height) {
		this.width = width;
		this.height = height;

		// Initialize garden based on input length and height
		garden = new Plant[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				garden[i][j] = null;
			}
		}
	}

	// Updated so that it needs a description variable.
	public void addPlant(int xCord, int yCord, String name, String description, String[] traits) {

		Plant plant = new Plant(name, description, traits);
		garden[yCord][xCord] = plant;
		plant.setxCor(xCord);
		plant.setyCor(yCord);
		plantsInGarden.add(plant);
	}

	public void movePlant(int movexCord, int moveyCord, int xCord, int yCord) {
		for (Plant plant1 : this.getPlants()) {
			if (plant1.getxCor() == xCord && plant1.getyCor() == yCord) {
				garden[moveyCord][movexCord] = plant1;
				plant1.setxCor(movexCord);
				plant1.setyCor(moveyCord);
				garden[yCord][xCord] = null;
			}
		}
	}

	public void removePlant(int xCord, int yCord) {
		Plant placeholder = null;
		for (Plant plant1 : this.plantsInGarden) {
			if (plant1.getxCor() == xCord && plant1.getyCor() == yCord) {
				garden[yCord][xCord] = null;
				placeholder = plant1;
				break;
			}
		}
		plantsInGarden.remove(placeholder);
	}

	public Plant[][] getGarden() {
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
