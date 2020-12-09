package src.main.java;

/**
 * The Plant class contains all plant data. This includes name, characters,
 * traits, descriptions, x and y coordinates.
 * 
 * @author Elijah Haberman
 *
 */
public class Plant implements java.io.Serializable {

	private String plantName;
	private char plantChar;
	private String plantDescription;
	private String[] plantTraits;
	private double xCor;
	private double yCor;

	/**
	 * Constructor for Plant Object. Initializes the Plant Object.
	 * 
	 * @param plantName        String name of plant.
	 * @param plantDescription String description of plant.
	 * @param plantTraits      String[] traits of plant.
	 */
	public Plant(String plantName, String plantDescription, String[] plantTraits) {
		this.plantName = plantName;
		// Both of these use the static getData method from Model
		plantChar = plantName.charAt(0);
		this.plantDescription = plantDescription;
		this.plantTraits = plantTraits;
	}

	/**
	 * Getter for name of plant.
	 * 
	 * @return plantName
	 */
	public String getName() {
		return plantName;
	}

	/**
	 * Getter for plantChar.
	 * 
	 * @return plantChar
	 */
	public char getChar() {
		return plantChar;
	}

	/**
	 * Getter for plantDescription.
	 * 
	 * @return plantDescription
	 */
	public String getDescription() {
		return plantDescription;
	}

	/**
	 * Getter for plantTraits.
	 * 
	 * @return plantTraits
	 */
	public String[] getTraits() {
		return plantTraits;
	}

	/**
	 * Getter for xCor of plant.
	 * 
	 * @return xCor
	 */
	public double getxCor() {
		return xCor;
	}

	/**
	 * Setter for xCor of plant.
	 * 
	 * @param xCor x coordinate of plant.
	 */
	public void setxCor(double xCor) {
		this.xCor = xCor;
	}

	/**
	 * Getter for yCor of plant.
	 * 
	 * @return yCor y coordinate of plant.
	 */
	public double getyCor() {
		return yCor;
	}

	/**
	 * Setter for yCor of plant.
	 * 
	 * @param yCor y coordinate of plant.
	 */
	public void setyCor(double yCor) {
		this.yCor = yCor;
	}

}
