package src.main.java;


public class SavedData implements java.io.Serializable {
	Garden garden;
	
	public SavedData(Model model) {
		this.garden = model.garden; 
		
	}
}
