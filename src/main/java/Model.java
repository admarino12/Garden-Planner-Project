package src.main.java;

import java.util.ArrayList;

public class Model {
	
	ArrayList<Plant> plantList;
	int gardenWidth;
	int gardenHeight;
	String plantTraits = "Trees, FLowers, Bushes, Yellow, Blue, Pink";
	Garden garden;

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
	
	public Plant Add(double getX, double getY, String name) {
		Plant plantReturned = null; 
		for(Plant plant : plantList) {
			if (plant.getName().equals(name)){
				plant.setxCor(getX);
				plant.setyCor(getY);
				garden.addPlant(getX, getY, name, plant.getDescription(), plant.getTraits());
				
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






