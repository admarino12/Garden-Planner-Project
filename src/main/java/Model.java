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
		//Get desired plant from plantList
		
		Plant placeholder = new Plant("fake name");
		return placeholder;
	}
	
	
}

class Plant {
	
	String plantName;
	
	//Here we would search the CSV using the plantName to get the rest of the information about the plant
	public Plant(String plantName) {
		this.plantName = plantName;
	}
	
	public String getName() {
		return plantName;
	}
	
}