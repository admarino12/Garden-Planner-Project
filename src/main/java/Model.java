package src.main.java;

import java.util.ArrayList;

public class Model implements java.io.Serializable {
	
	ArrayList<Plant> plantList;
	int gardenWidth;
	int gardenHeight;
	String plantTraits = "Trees, FLowers, Bushes, Yellow, Blue, Pink";
	Garden garden;
	int rating;

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
		boolean spring = false;
		boolean summer = false;
		boolean fall = false;
		boolean winter = false;
		String name;
		
		for(Plant plant : garden.getPlantsInGarden()) {
			name = plant.getName();
			switch (name) {
				case "Blueflag_Iris":
					spring = true;
				case "Canada_Wild_Ginger":
					spring = true;
				case "Evening_Primrose":
					summer = true;
				case "Pink_Azalea":
					spring = true;
				case "Swamp_Rose":
					summer = true;
				case "Virginia_Bluebells":
					spring = true;
				case "Yellow_Thistle":
					spring = true;
			}
		}
		if(spring = true)
			rating++;
		if(summer = true)
			rating++;
		if(fall = true)
			rating++;
		if(winter = true)
			rating++;
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






