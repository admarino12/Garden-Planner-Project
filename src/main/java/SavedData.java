package src.main.java;


public class SavedData implements java.io.Serializable {
	Garden garden;
	String fileName;
	
	public SavedData(String fileName, Model model) {
		this.fileName = fileName;
		this.garden = model.garden; 
		
	}
	
	public Garden getGarden() {
		return garden;
	}
	
	public String getFileName() {
		return fileName;
	}
}
