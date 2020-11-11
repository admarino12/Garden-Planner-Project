package src.main.java;

import java.util.ArrayList;

public class Model {
	
	ArrayList<Plant> plantList;
	int gardenWidth;
	int gardenHeight;
	int xCord;
	int yCord;
	String plantTraits = "Trees, FLowers, Bushes, Yellow, Blue, Pink";
	Garden garden;

	public Model(ArrayList<Plant> plantList) {
		this.plantList = plantList;

	}

	public ArrayList<Plant> getPlantList() {
		return plantList;
	}
	
	public String getPlantTraits() {
		return plantTraits;
	}
	
	public ArrayList<String> searchPlantListByTrait(String search){
		ArrayList<String> results = new ArrayList<String>();
		for(Plant plant : plantList) {
			for(String trait : plant.getTraits()) {
				if(trait.equals(search)) {
					results.add(plant.getName());
				}
			}
		}
		return results;
	}
	

}






