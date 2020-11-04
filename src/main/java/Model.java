package src.main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {
	
	Scanner scan = new Scanner(System.in);
	ArrayList<Plant> plantList;
	int gardenWidth;
	int gardenHeight;
	int xCord;
	int yCord;
	Garden garden;

	public Model(ArrayList<Plant> plantList) {
		this.plantList = plantList;

	}

	public ArrayList<Plant> getPlantList() {
		return plantList;
	}
	

}






