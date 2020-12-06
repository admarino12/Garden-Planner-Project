package src.main.java;

import java.awt.Paint;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PlantSearchPane  {
	
	ObservableList<String> plantOptions = 
			FXCollections.observableArrayList(
					"All Plants",
					"Perennials",
					"Trees",
					"Shrubs"
					);
	final ComboBox<String> typesOfPlants = new ComboBox<String>(plantOptions);
	ObservableList<String> seasonOptions = 
			FXCollections.observableArrayList(
					"All Seasons",
					"Spring",
					"Summer",
					"Autumn",
					"Winter"
					);
	final ComboBox<String>  typesOfSeasons = new ComboBox<String>(seasonOptions);
	ObservableList<String> colorOptions = 
			FXCollections.observableArrayList(
					"All Colors",
					"Yellow",
					"Green",
					"Red",
					"Purple",
					"Blue",
					"Orange",
					"White",
					"Pink"
					);
	final ComboBox<String>  typesOfColors = new ComboBox<String>(colorOptions);
	
	private TextField searcher;
	private View mainView;
	private VBox mainPane;
	private TilePane imageContainerPane;
	private ScrollPane scrollPane;
	
	final private int SCROLL_PANE_MAX_HEIGHT = 623;
	
	

	private ArrayList<String> allPlantNames;
	public Map<String, ImageView> plantList = new HashMap<String, ImageView>();
	
	
	public PlantSearchPane(View mainView)  {
		this.mainView = mainView;
		allPlantNames = mainView.control.getPlantNames();
		
		
		mainPane = new VBox(0);
		mainPane.setStyle("-fx-background-color: #E7DEBC;");
		
		//Hbox contains the search Bar
		Label searchlabel = new Label("Search:");
		searcher = new TextField();
		Button searchButton = new Button("Go");
		mainView.control.setHandlerForSearchBar(searchButton);
		
		
		HBox hb = new HBox();
		hb.getChildren().addAll(searchlabel, searcher,searchButton);
		hb.setSpacing(8);
		hb.setPadding(new Insets(5, 10, 5, 10));
		hb.setMaxHeight(50);
		hb.setAlignment(Pos.CENTER);
		
		//HBox contains the drop down selection box
		typesOfPlants.getSelectionModel().selectFirst();
		typesOfSeasons.getSelectionModel().selectFirst();
		typesOfColors.getSelectionModel().selectFirst();
		HBox hb1 = new HBox();
		mainView.control.setHandlerForSearchTab(typesOfPlants);
		mainView.control.setHandlerForSearchTab(typesOfSeasons);
		mainView.control.setHandlerForSearchTab(typesOfColors);
		hb1.getChildren().addAll(typesOfPlants, typesOfSeasons, typesOfColors);
		hb1.setPadding(new Insets(5, 10, 30, 10));
		hb1.setMaxHeight(50);
		hb1.setSpacing(5);
		hb1.setAlignment(Pos.BOTTOM_CENTER);
		
		//TilePane contains the image and labels
		imageContainerPane = new TilePane();
		imageContainerPane.setPrefColumns(2);
		imageContainerPane.setVgap(35);
		imageContainerPane.setHgap(35);
		imageContainerPane.setAlignment(Pos.CENTER);
		
		scrollPane = new ScrollPane();
		scrollPane.setPrefHeight(SCROLL_PANE_MAX_HEIGHT);
		scrollPane.setPadding(new Insets(0,0,0,0));
		scrollPane.setFitToWidth(true);
		scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; "); 
		
		
		createHashMap();
		update(allPlantNames);
		
		scrollPane.setContent(imageContainerPane);
		mainPane.getChildren().addAll(hb, hb1, scrollPane);
		
	}
	
	public void createHashMap() {
		for(String name : allPlantNames) {
			Image plantImage;
			try {
				plantImage = new Image(new FileInputStream("src/resources/images/"+name+".png"));
				ImageView plantImageView = new ImageView();
				plantImageView.setId(name);
				plantImageView.setImage(plantImage);
				plantImageView.setPreserveRatio(true);
		    	plantImageView.setFitHeight(100);
				mainView.control.setHandlerForDragAndDrop(plantImageView);
				plantList.put(name, plantImageView);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(ArrayList<String> names) {
		imageContainerPane.getChildren().clear();
		
		for(String inputName : names) {
			for(String generalName : allPlantNames) {
				if(generalName.toLowerCase().contains(inputName.toLowerCase())) {
					
					Label plantNameLabel = new Label(generalName.replace("_", " "));
					plantNameLabel.setTextFill(Color.BLACK);
					
					String plantDescription = mainView.control.getPlantDescription(generalName);
					
					VBox vb = new VBox();
					vb.setAlignment(Pos.CENTER);
					
					vb.getChildren().add(plantList.get(generalName));
					vb.getChildren().add(plantNameLabel);
					
					Tooltip toolTip = new Tooltip(plantDescription);
					Tooltip.install(vb, toolTip);
					
					imageContainerPane.getChildren().add(vb);
				}
			}
		}
		
	}
	
	public VBox getMainPane() {
		return mainPane;
	}
	
	public TextField getTextField() {
		return searcher;
	}
	
	public ImageView getPlantIV(String plantName) {
		return plantList.get(plantName);
	}
}
