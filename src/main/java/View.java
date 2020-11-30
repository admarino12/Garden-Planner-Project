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

public class View implements java.io.Serializable {
	
	public Controller control;
	private ToolBar toolbarpane;
	private GraphicsContext gc;
	private DrawGardenPane drawGardenPane;
	private PlantSearchPane plantSearchPane;
	private BorderPane border;
	private int numChildrenInBorder;
			

	final private int ROOT_WIDTH = 1200;
	final private int ROOT_HEIGHT = 780;

	// garden dimensions
	int gardenWidth, gardenHeight;

	public View(Stage theStage, Controller control) {
		this.control = control;
		
		theStage.setTitle("Garden Builder");
		Group root = new Group();
		Scene theScene = new Scene(root, ROOT_WIDTH, ROOT_HEIGHT);
		theStage.setScene(theScene);

		
		//Toolbar
		toolbarpane = new ToolBar();


		// DragnDropPane
		plantSearchPane = new PlantSearchPane(this);
		
		
		//DrawGardenPane
		drawGardenPane = new DrawGardenPane(this);
		
		
		border = new BorderPane();
		root.getChildren().add(border);
		border.setLeft(plantSearchPane.getMainPane());
		border.setCenter(drawGardenPane.getDrawGardenBorder());
		border.setTop(toolbarpane);
		
		gc = drawGardenPane.getDrawGardenCanvas().getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(5);

		numChildrenInBorder = border.getChildren().size();
		theStage.show();
	}
	
	
	public PlantSearchPane getPlantSearchPane() {
		return plantSearchPane;
	}
	
	public DrawGardenPane getDrawGardenPane() {
		return drawGardenPane;
	}
	
	
	public void addPlants(Plant plant) {
		ImageView plantIV = plantSearchPane.getPlantIV(plant.getName());
		
		ImageView imgView = new ImageView();
		imgView.setImage(plantIV.getImage());
    	imgView.setPreserveRatio(true);
    	imgView.setFitHeight(100);
    	imgView.setFitWidth(100);
    	imgView.setX(plant.getxCor() - imgView.getFitWidth()/2);
    	imgView.setY(plant.getyCor() - imgView.getFitHeight()/2);
    	imgView.setId(plant.getName() + plant.getxCor() + plant.getyCor());
    	control.setHandlerForRemoveClick(imgView);
    	control.setHandlerForPlantDragged(imgView);
 
  
    	
    	
    	border.getChildren().add(imgView);
	}
	
	public void movePlant(ImageView imgView, Plant p) {
		imgView.setId(p.getName() + p.getxCor() + p.getyCor());
		imgView.setX(p.getxCor() - imgView.getFitWidth()/2);
		imgView.setY(p.getyCor() - imgView.getFitHeight()/2);
		
	}
	
	public void removePlant(Plant plant) {
				border.getChildren().remove(control.garden.getPlantsInGarden().indexOf(plant) + numChildrenInBorder);
			
	}
	
	
	public GraphicsContext getgc() {
		return gc;
	}


}

