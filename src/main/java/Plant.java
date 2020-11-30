package src.main.java;

public class Plant implements java.io.Serializable{

	private String plantName;
	private char plantChar;
	private String plantDescription;
	private String[] plantTraits;
	private double xCor;
	private double yCor;

	public Plant(String plantName, String plantDescription, String[] plantTraits) {
		this.plantName = plantName;
		// Both of these use the static getData method from Model
		plantChar = plantName.charAt(0);
		this.plantDescription = plantDescription; 
		this.plantTraits = plantTraits;
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
	
	public String[] getTraits() {
		return plantTraits;
	}

	public double getxCor() {
		return xCor;
	}

	public void setxCor(double xCor) {
		this.xCor = xCor;
	}

	public double getyCor() {
		return yCor;
	}

	public void setyCor(double yCor) {
		this.yCor = yCor;
	}
	
	
}
