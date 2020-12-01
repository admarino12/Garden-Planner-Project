package src.main.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

			

	final private int ROOT_WIDTH = 1225;
	final private int ROOT_HEIGHT = 760;

	// garden dimensions
	int gardenWidth, gardenHeight;

	public View(Stage theStage, Controller control) {
		this.control = control;
		this.theStage = theStage;
		
		//Front Page View
		Stage popupwindow = new Stage();
	      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Front Page");
		      
		//start button to get to garden view
		Button startBtn = new Button("Start New Garden"); 
		startBtn.setMaxSize(200, 200);
		startBtn.setStyle("-fx-text-alignment: center");
		startBtn.setStyle("-fx-background-color: #D1EBDC");
		startBtn.setOnAction(e -> popupwindow.close()); //close pop up on click
		VBox layout = new VBox(10);
		
		//import background image
		Image image;
		try {
			image = new Image(new FileInputStream("src/resources/images/Front_Page.png"));
			BackgroundImage bg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null);
			Background background = new Background(bg);
			layout.setBackground(background);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//setting up layout of Front Page
		layout.setPadding(new Insets(220,10,10,10));
		layout.getChildren().addAll(startBtn);
		layout.setAlignment(Pos.CENTER);
		
		//set Front Page scene
		Scene scene1= new Scene(layout, 1225, 760); 
		popupwindow.setScene(scene1);
		popupwindow.showAndWait(); //once pop up is closed, Garden Builder View is shown
		
		
		//Garden Builder View
		theStage.setTitle("Garden Builder");
		Group root = new Group();
		Scene theScene = new Scene(root, ROOT_WIDTH, getROOT_HEIGHT());
		theStage.setScene(theScene);

		
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
	
	public void removePlant(Plant plant) {
				border.getChildren().remove(control.garden.getPlantsInGarden().indexOf(plant) + numChildrenInBorder);
			
	}
	public void loadNewGarden(ArrayList<Plant> plants, int rating, Season season ){
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

