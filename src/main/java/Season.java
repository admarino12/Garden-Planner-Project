package src.main.java;

public enum Season {
	
	SPRING("Spring"), SUMMER("Summer"),	AUTUMN("Autumn"), WINTER("Winter");
	
	private String name = null;
	
	private Season(String s) {
		name = s;
	}
	
	private String getSeason() {
		return name;
	}
}
