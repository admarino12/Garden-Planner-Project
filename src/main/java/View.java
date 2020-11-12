package src.main.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class View {
	
	public Controller control;
	private ToolBar toolbarpane;
	private ToolBar drawgardentoolbar;
	private Canvas drawgardencanvas;
	private GraphicsContext gc;
	private BorderPane drawgardenborderpane;
	private PlantSearchPane plantSearchPane;
	private BorderPane border;
	private ToggleButton erase = new ToggleButton("Erase");
	private ToggleButton draw = new ToggleButton("Draw");
	private Button done = new Button("Finished");
	
			

	final private int CANVASWIDTH = 1200;
	final private int CANVASHEIGHT = 780;
	
	final private int DRAWGARDENPANEWIDTH = 1000;
	final private int DRAWGARDENPANEHEIGHT = 700;

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

		
		//Toolbar
		toolbarpane = new ToolBar();

		ToggleButton save = new ToggleButton("Save");
		ToggleButton add = new ToggleButton("Add");
		ToggleButton move = new ToggleButton("Move");
		ToggleButton transform = new ToggleButton("Transform");
		ToggleButton remove = new ToggleButton ("Remove");
		toolbarpane.getItems().addAll(save, new Separator(), add, new Separator(), move, new Separator(), transform, new Separator(), remove);

		// DragnDropPane
		plantSearchPane = new PlantSearchPane(this);
		
		
		//DrawGardenPane
		drawgardencanvas = new Canvas(DRAWGARDENPANEWIDTH, DRAWGARDENPANEHEIGHT);
		drawgardencanvas.setStyle("-fx-background-color: #81EEA4;");
		drawgardencanvas.minWidth(DRAWGARDENPANEWIDTH);
		drawgardencanvas.minHeight(DRAWGARDENPANEHEIGHT);
		StackPane holder = new StackPane();
		holder.getChildren().add(drawgardencanvas);
		holder.setStyle("-fx-background-color: #81EEA4;");
		
		drawgardentoolbar = new ToolBar();
		
		//DrawGardenPane Buttons
		Button done = new Button("Finished");
		
		//Add buttons to DrawGardenPane
		HBox hb2 = new HBox();
		hb2.setPadding(new Insets(5, 10, 5, 730));
		hb2.getChildren().addAll(draw, new Separator(), erase , new Separator(), done);
		drawgardentoolbar.getItems().add(hb2);
		drawgardentoolbar.setOrientation(Orientation.HORIZONTAL);
		//Put toolbar on Right
		
		drawgardenborderpane = new BorderPane();
		drawgardenborderpane.setMinWidth(DRAWGARDENPANEWIDTH);
		drawgardenborderpane.setMinHeight(DRAWGARDENPANEHEIGHT);
		drawgardenborderpane.setCenter(holder);
		drawgardenborderpane.setTop(drawgardentoolbar);
		
		
		

		border = new BorderPane();
		root.getChildren().add(border);
		border.setLeft(plantSearchPane.getMainPane());
		border.setCenter(drawgardenborderpane);
		border.setTop(toolbarpane);
		
		gc = drawgardencanvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(5);

		
		theStage.show();
	}
	
	
	public PlantSearchPane getPlantSearchPane() {
		return plantSearchPane;
	}
	
	public void addPlants(Plant plant) {
		Image plantImage = null;
		try {
			plantImage = new Image(new FileInputStream("src/resources/images/"+plant.getName()+".png"));
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
			e.printStackTrace();
		}
		ImageView imgView = new ImageView();
		imgView.setImage(plantImage);
    	imgView.setPreserveRatio(true);
    	imgView.setFitHeight(100);
    	imgView.setX(plant.getxCor() - 50);
    	imgView.setY(plant.getyCor() - 50);
    	
    	System.out.println(imgView.getX());
    	System.out.println(imgView.getY());
  
    	
    	
    	border.getChildren().add(imgView);
	}
	
	public void movePlant() {
		
	}
	
	public void removePlant(Plant plant) {
		for (Plant p : control.garden.getPlantsInGarden()) {
			if (p.getxCor() == plant.getxCor() && p.getyCor() == plant.getyCor()) {
				border.getChildren().remove(control.garden.getPlantsInGarden().indexOf(p));
			}
		}
	}
	

	
	public Canvas getGardenCanvas() {
		return drawgardencanvas;
	}
	
	public GraphicsContext getgc() {
		return gc;
	}
	

	public ToggleButton getErase() {
		return erase;
	}


	public ToggleButton getDraw() {
		return draw;
	}


	public Button getDone() {
		return done;
	}


	public void setErase(ToggleButton erase) {
		this.erase = erase;
	}


	public void setDraw(ToggleButton draw) {
		this.draw = draw;
	}


	public void setDone(Button done) {
		this.done = done;
	}

	
	
}

