package src.main.java;

import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlantSearchPane {
	
	ObservableList<String> options = 
			FXCollections.observableArrayList(
					"All",
					"Flowers",
					"Trees",
					"Bushes"
					);
	final ComboBox<String> typesofplants = new ComboBox<String>(options);
	
	private TextField searcher;
	private View mainView;
	private VBox mainPane;
	private TilePane imageContainerPane;

	private ArrayList<String> allPlantNames;
	private Map<String, ImageView> plantList = new HashMap<String, ImageView>();
	
	
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
		
		//HBox contains the drop down selection box
		HBox hb1 = new HBox();
		mainView.control.setHandlerForSearchTab(typesofplants);
		hb1.getChildren().add(typesofplants);
		hb1.setPadding(new Insets(5, 10, 30, 10));
		hb1.setMaxHeight(50);
		hb1.setAlignment(Pos.BOTTOM_CENTER);
		mainPane.getChildren().add(hb);
		mainPane.getChildren().add(hb1);
		
		//TilePane contains the image and labels
		imageContainerPane = new TilePane();
		imageContainerPane.setPrefColumns(2);
		imageContainerPane.setVgap(20);
		imageContainerPane.setAlignment(Pos.CENTER);
		
		createHashMap();
		update(allPlantNames);
		
		mainPane.getChildren().add(imageContainerPane);
		
	}
	
	public void createHashMap() {
		for(String name : allPlantNames) {
			//System.out.println(name);
			Image plantImage;
			try {
				plantImage = new Image(new FileInputStream("src/resources/images/"+name+".png"));
				ImageView plantImageView = new ImageView();
				plantImageView.setId(name);
				plantImageView.setImage(plantImage);
				plantImageView.setPreserveRatio(true);
		    	plantImageView.setFitHeight(65);
				mainView.control.setHandlerForDrag(plantImageView);
		    	//plantImageView.setOnDragDetected(mainView.control.getHandlerForDrag());
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
					VBox vb = new VBox();
					vb.setAlignment(Pos.CENTER);
					vb.getChildren().add(plantList.get(generalName));
					vb.getChildren().add(new Label(generalName.replace("_", " ")));
					
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
}
