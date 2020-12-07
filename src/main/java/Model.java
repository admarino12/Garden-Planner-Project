package src.main.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javafx.scene.image.Image;

public class Model implements java.io.Serializable {
	
	ArrayList<Plant> plantList;
	int gardenWidth;
	int gardenHeight;
	String plantTraits = "Trees, Flowers, Bushes, Yellow, Blue, Pink";
	Garden garden;
	int rating;
	SavedData savedData; 

	public Model(ArrayList<Plant> plantList) {
		this.plantList = plantList;
		this.garden = new Garden(gardenWidth, gardenHeight);

	}

	public ArrayList<Plant> getPlantList() {
		return plantList;
	}
	
	public String getPlantTraits() {
		return plantTraits;
	}
	
	//updates the rating integer based off of the current plants in garden. Can be max of 4.
	public void updateRating() {
		rating = 0;
		HashSet<String> seasons = new HashSet<String>();
		
		for(Plant plant : garden.getPlantsInGarden()) {
			String description = plant.getDescription();
			String[] splitDescription = description.split(":");
			switch(splitDescription[0]) {
			case "Spring":
				seasons.add("Spring");
				break;
			case "Summer":
				seasons.add("Summer");
				break;
			case "Fall":
				seasons.add("Fall");
				break;
			case "Winter":
				seasons.add("Winter");
				break;
			}
		}
		rating = seasons.size();
	}
	
	public int getRating() {
		return rating;
	}
	
	public Plant Add(double getX, double getY, String name, Image image) {
		Plant plantReturned = null; 
		for(Plant plant : plantList) {
			if (plant.getName().equals(name)){
				plant.setxCor(getX);
				plant.setyCor(getY);
				garden.addPlant(getX, getY, name, plant.getDescription(), plant.getTraits(), image) ;
				
				plantReturned = plant; 
				
			}
		}
		System.out.println(garden.getPlantsInGarden());
		return plantReturned; 
    }
	
	public void remove(Plant p) {
		garden.getPlantsInGarden().remove(p);
	}
	
	public void move(double x, double y, Plant p) {
		p.setxCor(x);
		p.setyCor(y);
	}
	
	public ArrayList<String> searchPlantListByTrait(String plantTypeTrait, String seasonTrait, String colorTrait){
		if(plantTypeTrait=="All Plants") {
			plantTypeTrait = " ";
		}
		if(seasonTrait=="All Seasons") {
			seasonTrait = " ";
		}
		if(colorTrait=="All Colors") {
			colorTrait = " ";
		}
		ArrayList<String> results = new ArrayList<String>();
		for(Plant plant : plantList) {
			List<String> plantsTraits = Arrays.asList(plant.getTraits());
			if(plantsTraits.contains(plantTypeTrait) && plantsTraits.contains(seasonTrait) && plantsTraits.contains(colorTrait)) {
				results.add(plant.getName());
			}
		}
		return results;
	}
	
	public String getPlantDescription(String plantName) {
		String plantDescription = null;
		for(Plant plant : plantList) {
			if(plant.getName().equals(plantName)) {
				plantDescription = plant.getDescription();
			}
		}
		return plantDescription;
	}
	
	public ArrayList<Plant> getPlantsInSeason() {
		ArrayList<Plant> plantsInSeason = new ArrayList<Plant>();
		if(garden.getSeason() == Season.ALL_SEASONS) {
			return garden.getPlantsInGarden();
		}
		else {
			for(Plant plant : garden.getPlantsInGarden()) {
				List<String> plantTraits = Arrays.asList(plant.getTraits());
				if(plantTraits.contains(garden.getSeason().getSeason())) {
					plantsInSeason.add(plant);
				}
			}
		}
		return plantsInSeason;
	}
	
	public Garden getGarden() {
		return garden;
	}
	
	public void setGarden(Garden garden) {
		this.garden = garden;
	}

}






