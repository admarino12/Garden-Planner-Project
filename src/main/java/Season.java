package src.main.java;

public enum Season {
	
	SPRING("Spring"), SUMMER("Summer"),	AUTUMN("Autumn"), WINTER("Winter"), ALL_SEASONS("All Seasons");
	
	private String name;
	
	private Season(String s) {
		name = s;
	}
	
	public String getSeason() {
		return name;
	}
}
