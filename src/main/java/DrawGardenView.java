package src.main.java;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DrawGardenView {

	int canvasWidth = 1200;
	int canvasHeight = 700;
	
	Controller controller;
	
	
	public DrawGardenView(Stage theStage, Controller controller) {
		this.controller = controller;
		
		theStage.setTitle("Draw Garden");
		Group root = new Group();
		Scene theScene = new Scene(root, canvasWidth, canvasHeight);
		theStage.setScene(theScene);
		
		BorderPane borderPane = new BorderPane();
		root.getChildren().add(borderPane);
		theStage.setScene(theScene);
		theStage.show();
	}
}
	