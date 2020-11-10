package src.main.java;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DrawGardenView {

	int canvasWidth = 1200;
	int canvasHeight = 700;
	
	Controller controller;
	
	
	public DrawGardenView(Stage theStage, Controller controller) {
		this.controller = controller;
		
		Group root = new Group();
		
		BorderPane borderPane = new BorderPane();
		ToolBar toolBar = new ToolBar();
		
		//add Buttons too the toolbar
		Button erase = new Button("erase");
		Button draw = new Button("draw");
		Button done = new Button("done");
		
		toolBar.getItems().addAll(erase,draw,done);
		
		//Add other panes to borderPane
		borderPane.setTop(toolBar);
		
		root.getChildren().add(borderPane);
		
		theStage.setTitle("Draw Garden");
		Scene theScene = new Scene(root, canvasWidth, canvasHeight);
		theStage.setScene(theScene);
		theStage.show();
	}
}
	