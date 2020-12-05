package src.main.java;

import java.util.ArrayList;

import javafx.scene.image.Image;

class Garden implements java.io.Serializable {
	private double width;
	private double height;
	private ArrayList<Plant> plantsInGarden = new ArrayList<Plant>();
	
	private Season season;
	private int rating;

	public Garden(int width, int height) {
		this.width = width;
		this.height = height;
		this.season = Season.SPRING;
		this.rating = 0;
	}

	//Updated so that it needs a description variable. 
	public void addPlant(double xCord, double yCord, String name, String description, String[] traits, Image image) {
		Plant plant = new Plant(name,description, traits);
		plant.setImage(image);
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
	
	public Season getSeason() {
		return season;
	}
	
	public void setSeason(Season season) {
		this.season = season;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
