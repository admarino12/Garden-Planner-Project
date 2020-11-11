package src.main.java;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class DrawGardenPane {
	
	BorderPane gardenborder = new BorderPane();
	ToolBar gardentoolbar = new ToolBar();
	
	//Buttons
	Button delete = new Button("Delete");
	Button draw = new Button("Draw");
	Button done = new Button("Done");
	
	
	public BorderPane getGardenborder() {
		return gardenborder;
	}
	public ToolBar getGardentoolbar() {
		return gardentoolbar;
	}
	public Button getDelete() {
		return delete;
	}
	public Button getDraw() {
		return draw;
	}
	public Button getDone() {
		return done;
	}
	public void setGardenborder(BorderPane gardenborder) {
		this.gardenborder = gardenborder;
	}
	public void setGardentoolbar(ToolBar gardentoolbar) {
		this.gardentoolbar = gardentoolbar;
	}
	public void setDelete(Button delete) {
		this.delete = delete;
	}
	public void setDraw(Button draw) {
		this.draw = draw;
	}
	public void setDone(Button done) {
		this.done = done;
	}
}
//
//	int canvasWidth = 1200;
//	int canvasHeight = 700;
//	
//	Controller controller;
//	
//	
//	public DrawGardenPane(Stage theStage, Controller controller) {
//		this.controller = controller;
//		
//		Group root = new Group();
//		
//		BorderPane borderPane = new BorderPane();
//		ToolBar toolBar = new ToolBar();
//		
//		//add Buttons too the toolbar
//		Button erase = new Button("erase");
//		Button draw = new Button("draw");
//		Button done = new Button("done");
//		
//		toolBar.getItems().addAll(erase,draw,done);
//		
//		//Add other panes to borderPane
//		borderPane.setTop(toolBar);
//		
//		root.getChildren().add(borderPane);
//		
//		theStage.setTitle("Draw Garden");
//		Scene theScene = new Scene(root, canvasWidth, canvasHeight);
//		theStage.setScene(theScene);
//		theStage.show();
//	}
//}