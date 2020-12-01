package src.main.java;

import javafx.scene.canvas.GraphicsContext;

public class SavedData implements java.io.Serializable {
	Garden garden;
	
	public SavedData(Model model) {
		this.garden = model.garden; 
		
	}
}
