package src.main.java;

public class Plant {

	private String plantName;
	private char plantChar;
	private String plantDescription;
	private int xCor;
	private int yCor;

	// Here we would search the CSV using the plantName to get the rest of the
	// information about the plant such as plantChar
	public Plant(String plantName, String plantDescription) {
		this.plantName = plantName;
		// Both of these use the static getData method from Model
		plantChar = plantName.charAt(0);
		this.plantDescription = plantDescription; 
	}

	public String getName() {
		return plantName;
	}

	public char getChar() {
		return plantChar;
	}
	
	public String getDescription() {
		return plantDescription;
	}

	public int getxCor() {
		return xCor;
	}

	public void setxCor(int xCor) {
		this.xCor = xCor;
	}

	public int getyCor() {
		return yCor;
	}

	public void setyCor(int yCor) {
		this.yCor = yCor;
	}
	
	
}
