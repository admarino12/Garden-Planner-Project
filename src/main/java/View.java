package src.main.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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
	//private ToggleButton save = new ToggleButton("Save");
	private ToggleButton add = new ToggleButton("Add");
	private ToggleButton move = new ToggleButton("Move");
	//private ToggleButton transform = new ToggleButton("Transform");
	private ToggleButton remove = new ToggleButton ("Remove");
	private int numChildrenInBorder;
			

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

		toolbarpane.getItems().addAll( add, new Separator(), move,  new Separator(), remove);

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

		numChildrenInBorder = border.getChildren().size();
		theStage.show();
	}
	
	
	public PlantSearchPane getPlantSearchPane() {
		return plantSearchPane;
	}
	
	public void addPlants(Plant plant) {
		ImageView plantIV = plantSearchPane.getPlantIV(plant.getName());
		
		ImageView imgView = new ImageView();
		imgView.setImage(plantIV.getImage());
    	imgView.setPreserveRatio(true);
    	imgView.setFitHeight(100);
    	imgView.setX(plant.getxCor() - 50);
    	imgView.setY(plant.getyCor() - 50);
    	imgView.setId(plant.getName() + plant.getxCor());
    	control.setHandlerForPlantClick(imgView);
    	control.setHandlerForPlantDragged(imgView);
 
  
    	
    	
    	border.getChildren().add(imgView);
	}
	
	public void movePlant(ImageView imgView, Plant p) {
		imgView.setId(p.getName() + p.getxCor());
		imgView.setX(p.getxCor() - 50);
		imgView.setY(p.getyCor() - 50);
		
	}
	
	public void removePlant(Plant plant) {
				border.getChildren().remove(control.garden.getPlantsInGarden().indexOf(plant) + numChildrenInBorder);
			
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

	public ToggleButton getAdd() {
		return add;
	}
	
	public ToggleButton getRemove() {
		return remove;
	}
	
	public ToggleButton getMove() {
		return move; 
	}
	
}

