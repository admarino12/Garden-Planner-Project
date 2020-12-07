package src.main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Garden implements java.io.Serializable {
	private double width;
	private double height;
	private ArrayList<Plant> plantsInGarden = new ArrayList<Plant>();
	
	private Season season;
	private ArrayList<Integer> ratings;

	public Garden(int width, int height) {
		this.width = width;
		this.height = height;
		this.season = Season.ALL_SEASONS;
		setSeasonRatings();
	}

	//Updated so that it needs a description variable. 
	public void addPlant(double xCord, double yCord, String name, String description, String[] traits) {
		Plant plant = new Plant(name,description, traits);
		plant.setxCor(xCord);
		plant.setyCor(yCord);
		getPlantsInGarden().add(plant);
	}
	
	public ArrayList<Plant> getPlantsInSeason() {
		ArrayList<Plant> plantsInSeason = new ArrayList<Plant>();
		if(season == Season.ALL_SEASONS) {
			return plantsInGarden;
		}
		else {
			for(Plant plant : plantsInGarden) {
				List<String> plantTraits = Arrays.asList(plant.getTraits());
				if(plantTraits.contains(season.getSeason())) {
					plantsInSeason.add(plant);
				}
			}
		}
		return plantsInSeason;
	}
	
	public void setSeasonRatings() {
		ArrayList<Integer> seasons = new ArrayList<Integer>();
		seasons.add(0);
		seasons.add(0);
		seasons.add(0);
		seasons.add(0);
		for(Plant plant : plantsInGarden) {
			List<String> plantTraits = Arrays.asList(plant.getTraits());
			if(plantTraits.contains(Season.SPRING.getSeason())) {
				seasons.set(0, seasons.get(0) + 1);
			}
			if(plantTraits.contains(Season.SUMMER.getSeason())) {
				seasons.set(1, seasons.get(1) + 1);
			}
			if(plantTraits.contains(Season.AUTUMN.getSeason())) {
				seasons.set(2, seasons.get(2) + 1);
			}
			if(plantTraits.contains(Season.WINTER.getSeason())) {
				seasons.set(3, seasons.get(3) + 1);
			}
		}
		ratings = seasons;
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
	
	public ArrayList<Integer> getSeasonRatings() {
		return ratings;
	}
	
}
