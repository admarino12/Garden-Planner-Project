package pkgMain;


import java.util.ArrayList;

public class Model {

	ArrayList<Plant> plantList;
	int gardenWidth;
	int gardenHeight;
	int xCord;
	int yCord;
	String plantTraits = "Tree, FLower, Shrub, Yellow, Blue, Pink";
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

	public ArrayList<Plant> searchPlantListByTrait(String search) {
		ArrayList<Plant> results = new ArrayList<Plant>();
		for (Plant plant : plantList) {
			for (String trait : plant.getTraits()) {
				if (trait.equals(search)) {
					results.add(plant);
				}
			}
		}
		return results;
	}

}
