package pkgMain;


import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class View {

	Controller control;
	private ToolBar toolbarpane;
	private ToolBar drawgardentoolbar;
	private FlowPane drawgardenpane;
	private BorderPane drawgardenborderpane;
	private TilePane dragndroppane;
	private BorderPane border;
	
	ObservableList<String> options = 
			FXCollections.observableArrayList(
					"Flowers",
					"Trees",
					"Bushes",
					"Misc"
					);
	final ComboBox<String> typesofplants = new ComboBox<String>(options);
			

	final private int CANVASWIDTH = 1000;
	final private int CANVASHEIGHT = 600;
	
	final private int DRAWGARDENPANEWIDTH = 400;
	final private int DRAWGARDENPANEHEIGHT = 700;
	
	final private int DRAGNDROPPANEWIDTH=200;
	final private int DRAGNDROPPANEHEIGHT= 800;

	// garden dimensions
	int gardenWidth, gardenHeight;

	public View(Stage theStage, Controller control) {
		this.control = control;
		
		theStage.setTitle("Garden Builder");
		Group root = new Group();
		Scene theScene = new Scene(root, CANVASWIDTH, CANVASHEIGHT);
		//String styleSheet = getClass().getResource("style.css").toExternalForm();
		//theScene.getStylesheets().add(styleSheet);
		theStage.setScene(theScene);

//		// Buttons
//		Button b = new Button("Test");
		
		//Toolbar
		toolbarpane = new ToolBar();
		Button save = new Button("Save");
		Button add = new Button("Add");
		Button move = new Button("Move");
		Button transform = new Button("Transform");
		toolbarpane.getItems().addAll(save, new Separator(), add, new Separator(), move, new Separator(), transform);

		// DragnDropPane
		dragndroppane = new TilePane();
		dragndroppane.setStyle("-fx-background-color: #E7DEBC;");
		
		Label searchlabel = new Label("Search:");
		TextField searcher = new TextField();
		Button searchbutton = new Button("Go");
		HBox hb = new HBox();
		hb.getChildren().addAll(searchlabel, searcher,searchbutton);
		hb.setSpacing(8);
		hb.setPadding(new Insets(5, 10, 5, 10));
		HBox hb1 = new HBox();
		hb1.getChildren().add(typesofplants);
		hb1.setPadding(new Insets(5, 10, 5, 10));
		hb1.setAlignment(Pos.BOTTOM_CENTER);
		dragndroppane.getChildren().add(hb);
		dragndroppane.getChildren().add(hb1);
		
		
		//DrawGardenPane
		drawgardenpane = new FlowPane();
		drawgardenpane.setStyle("-fx-background-color: #81EEA4;");
		drawgardenpane.setMinWidth(DRAWGARDENPANEWIDTH);
		drawgardenpane.setMinHeight(DRAWGARDENPANEHEIGHT);
		
		drawgardentoolbar = new ToolBar();
		
		//DrawGardenPane Buttons
		Button delete = new Button("Delete");
		Button draw = new Button("Draw");
		Button done = new Button("Finished");
		
		//Add buttons to DrawGardenPane
		HBox hb2 = new HBox();
		hb2.setPadding(new Insets(5, 10, 5, 555));
		hb2.getChildren().addAll(delete, new Separator(), draw , new Separator(), done);
		drawgardentoolbar.getItems().add(hb2);
		drawgardentoolbar.setOrientation(Orientation.HORIZONTAL);
		//Put toolbar on Right
		
		drawgardenborderpane = new BorderPane();
		drawgardenborderpane.setMinWidth(DRAWGARDENPANEWIDTH);
		drawgardenborderpane.setMinHeight(DRAWGARDENPANEHEIGHT);
		drawgardenborderpane.setCenter(drawgardenpane);
		drawgardenborderpane.setTop(drawgardentoolbar);
		
		
		

		border = new BorderPane();
		root.getChildren().add(border);
		border.setLeft(dragndroppane);
		border.setCenter(drawgardenborderpane);
		border.setTop(toolbarpane);


	
		theStage.show();
	}

	/*
	 * THIS IS LEFT OVERFROM ASCII
	 */
	public void printGarden(Plant[][] garden, int gardenWidth, int gardenHeight) {
		System.out.print(" ");
		for (int j = 0; j < gardenWidth; j++) {
			System.out.print("__");
		}
		System.out.println();
		for (int i = 0; i < gardenHeight; i++) {
			System.out.print("|");
			for (int j = 0; j < gardenWidth; j++) {
				//
				// Display contents of the 2D garden character array here
				//
				if (garden[i][j] == null) {
					System.out.print("  ");
				} else
					System.out.print(garden[i][j].getChar() + " ");
			}
			System.out.println("|");
		}
		System.out.print("|");
		for (int j = 0; j < gardenWidth; j++) {
			System.out.print("__");
		}
		System.out.println("|");
		System.out.println();
	}

	/*
	 * THIS IS LEFT OVERFROM ASCII
	 */
	public void printPlantsinGarden(Garden garden) {
		for (Plant plant1 : garden.getPlants()) {
			System.out.println(plant1.getName() + " (" + (plant1.getxCor() + 1) + "," + (plant1.getyCor() + 1) + ")");
		}
	}

	/*
	 * THIS IS LEFT OVERFROM ASCII
	 */
	public void printLine() {
		System.out.println("******************************");
	}

}
