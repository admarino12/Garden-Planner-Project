package src.main.java;

import java.util.ArrayList;

class Garden implements java.io.Serializable {
	private double width;
	private double height;
	private ArrayList<Plant> plantsInGarden = new ArrayList<Plant>();

	public Garden(int width, int height) {
		this.width = width;
		this.height = height;
	}

	//Updated so that it needs a description variable. 
	public void addPlant(double xCord, double yCord, String name, String description, String[] traits) {
		Plant plant = new Plant(name,description, traits);
		plant.setxCor(xCord);
		plant.setyCor(yCord);
		getPlantsInGarden().add(plant);
	}



	public double getGardenWidth() {
		return width;
	}

	public double getGardenHeight() {
		return height;
	}

	public ArrayList<Plant> getPlants() {
		return plantsInGarden;
	}
	
	public ArrayList<Plant> getPlantsInGarden() {
		return this.plantsInGarden;
	}
	
}
