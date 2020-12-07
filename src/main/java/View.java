package src.main.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class View {
	
	public Controller control;
	private GraphicsContext gc;
	private DrawGardenPane drawGardenPane;
	private PlantSearchPane plantSearchPane;
	private ToolBarPane ratingToolBar;
	private BorderPane border;
	private Stage theStage;
	private int numChildrenInBorder;
	private StartPageView startPage;
	private Scene theScene;

			

	final private int ROOT_WIDTH = 1275;
	final private int ROOT_HEIGHT = 760;

	// garden dimensions
	int gardenWidth, gardenHeight;

	public View(Stage theStage, Controller control) {
		this.control = control;
		this.theStage = theStage;
		
		theStage.setTitle("Garden Builder");
		startPage = new StartPageView(this);

		
		
		//Garden Builder View
		Group root = new Group();
		theScene = new Scene(root, getROOT_WIDTH(), getROOT_HEIGHT());
		theScene.setCursor(Cursor.HAND);
		
		//ToolBar
		ratingToolBar = new ToolBarPane(this);

		// DragnDropPane
		plantSearchPane = new PlantSearchPane(this);
		
		//DrawGardenPane
		drawGardenPane = new DrawGardenPane(this);
		
		
		border = new BorderPane();
		root.getChildren().add(border);
		border.setLeft(plantSearchPane.getMainPane());
		border.setCenter(drawGardenPane.getDrawGardenBorder());
		border.setTop(ratingToolBar.getRatingToolBar());
		
		gc = drawGardenPane.getDrawGardenCanvas().getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(5);

		numChildrenInBorder = border.getChildren().size();
		theStage.show();
		
		if (control.getLoadGarden()) {
			Garden newGarden = control.getLoadedGarden();
			loadNewGarden(newGarden.getPlantsInGarden(), newGarden.getRating(), newGarden.getSeason());
		}
	}
	
	public void setMainScene() {
		theStage.setScene(theScene);
	}
	
	public Scene getScene() {
		return theScene; 
	}
	
	public PlantSearchPane getPlantSearchPane() {
		return plantSearchPane;
	}
	
	public DrawGardenPane getDrawGardenPane() {
		return drawGardenPane;
	}
	
	public ToolBarPane getToolBarPane() {
		return ratingToolBar;
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
	
	public void showPlantsInSeason(ArrayList<Plant> plantsInSeason) {
		//Remove plants
		for (int i = border.getChildren().size() - 1; i >=3; i--){
			border.getChildren().remove(i);
		}
		//Add Plants
		for (Plant p: plantsInSeason) {
			addPlants(p);
		}
	}
	
	public void removePlant(Plant plant) {
				border.getChildren().remove(control.garden.getPlantsInGarden().indexOf(plant) + numChildrenInBorder);
			
	}
	public void loadNewGarden(ArrayList<Plant> plants, int rating, Season season ){
		
		theStage.setScene(theScene);
		//Remove plants
		for (int i = border.getChildren().size() - 1; i >=3; i--){
			border.getChildren().remove(i);
		}
		//Add Plants
		for (Plant p: plants) {
			addPlants(p);
		}
		
		gc.clearRect(0, 0, drawGardenPane.getDrawGardenCanvas().getWidth(), drawGardenPane.getDrawGardenCanvas().getHeight());
		
		
		//Update Rating
		ratingToolBar.updateRating(rating);
		
		drawGardenPane.setSeason(season);
		drawGardenPane.setSeasonComboBox(season);
		
	}
	
	public GraphicsContext getgc() {
		return gc;
	}
	
	public Stage getStage() {
		return theStage;
	}


	public int getROOT_HEIGHT() {
		return ROOT_HEIGHT;
	}
	
	public int getROOT_WIDTH() {
		return ROOT_WIDTH;
	}
	
	

}

