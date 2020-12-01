package src.main.java;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Controller extends Application implements java.io.Serializable {

	View view;
	Model model;
	transient Scanner scan;
	Garden garden;
	Canvas canvas; 
	
	final int X_DRAW_OFFSET = 258;
	private final int Y_DRAW_OFFSET = 82;
	
	public static void main(String[] args) {
		//This initializes the JavaFX view
		launch(args);
	
	}
	
	
	@Override
	public void start(Stage theStage) {
		model = new Model(loadPlantList());
		view = new View(theStage, this);
		scan = new Scanner(System.in);
		canvas = view.getDrawGardenPane().getDrawGardenCanvas();
		
		this.garden = model.garden;
        
		//Draw start
		view.getDrawGardenPane().getDrawGardenCanvas().setOnMousePressed(e->{
			if(view.getDrawGardenPane().getDrawButton().isSelected() && !view.getDrawGardenPane().getEraseButton().isSelected()) {
			view.getgc().beginPath();
			view.getgc().lineTo(e.getSceneX()-X_DRAW_OFFSET, e.getSceneY()-Y_DRAW_OFFSET);
			view.getgc().stroke();
			}
		//Erase start
			else if(!view.getDrawGardenPane().getDrawButton().isSelected() && view.getDrawGardenPane().getEraseButton().isSelected()) {
				double lineWidth = view.getgc().getLineWidth()*4;
				view.getgc().clearRect(e.getSceneX()-X_DRAW_OFFSET - lineWidth , e.getSceneY()-Y_DRAW_OFFSET - lineWidth,
						lineWidth, lineWidth);
			}
			
			});	
		
		//Draw line
		view.getDrawGardenPane().getDrawGardenCanvas().setOnMouseDragged(e->{
			if(view.getDrawGardenPane().getDrawButton().isSelected() && !view.getDrawGardenPane().getEraseButton().isSelected()) {
			view.getgc().lineTo(e.getSceneX()-X_DRAW_OFFSET, e.getSceneY()-Y_DRAW_OFFSET);
			view.getgc().stroke();
			}
		//Erase line
			else if(!view.getDrawGardenPane().getDrawButton().isSelected() && view.getDrawGardenPane().getEraseButton().isSelected()) {
				double lineWidth = view.getgc().getLineWidth()*4;
				view.getgc().clearRect(e.getSceneX()-X_DRAW_OFFSET - lineWidth , e.getSceneY()-Y_DRAW_OFFSET - lineWidth,
						lineWidth, lineWidth);
			}
			});
		
		//Change Seasons
		//Spring
		String springstyle = "-fx-background-color: #81EEA4, linear-gradient(from 0.5px 0px to 20.5px 0px, repeat, gray 1%, transparent 2%), linear-gradient(from 0px 0.5px to 0px 20.5px, repeat, gray 1%, transparent 2%);";
		view.getDrawGardenPane().getSpringButton().setOnAction(e -> 
			view.getDrawGardenPane().getHolder().setStyle(springstyle)
		);
		
		//Summer
		String summerstyle = "-fx-background-color: #FFF4B3, linear-gradient(from 0.5px 0px to 20.5px 0px, repeat, gray 1%, transparent 2%), linear-gradient(from 0px 0.5px to 0px 20.5px, repeat, gray 1%, transparent 2%);";
		view.getDrawGardenPane().getSummerButton().setOnAction(e -> 
		view.getDrawGardenPane().getHolder().setStyle(summerstyle)
	);
		
		//Fall
		String fallstyle = "-fx-background-color: #E8C696, linear-gradient(from 0.5px 0px to 20.5px 0px, repeat, gray 1%, transparent 2%), linear-gradient(from 0px 0.5px to 0px 20.5px, repeat, gray 1%, transparent 2%);";
		view.getDrawGardenPane().getFallButton().setOnAction(e -> 
		view.getDrawGardenPane().getHolder().setStyle(fallstyle)
	);
		
		//Winter
		String winterstyle = "-fx-background-color: #E6FFFF, linear-gradient(from 0.5px 0px to 20.5px 0px, repeat, gray 1%, transparent 2%), linear-gradient(from 0px 0.5px to 0px 20.5px, repeat, gray 1%, transparent 2%);";
		view.getDrawGardenPane().getWinterButton().setOnAction(e -> 
		view.getDrawGardenPane().getHolder().setStyle(winterstyle)
	);
		
		
    }
	
	private final String plantResources = "src/resources/plants.csv";
	
	public ArrayList<Plant> loadPlantList(){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		String line = "";
		
		//Read each line of the CSV file and pull the plant name, description, and String array of plant traits
		try {
			FileReader file = new FileReader(plantResources);
			BufferedReader csvFile = new BufferedReader(file);
			
			while((line = csvFile.readLine()) != null) {
				
				String[] plant = line.split(",");
				String[] plantTraits = plant[2].split("-");
				plantList.add(  new Plant(plant[0], plant[1], plantTraits) );
				
			}			
			csvFile.close();
		
		}
		catch (IOException e) { e.printStackTrace(); }
		
		return plantList;
	}
	
	public ArrayList<String> getPlantNames() {
		ArrayList<String> plantNames = new ArrayList<String>();
		
		for(Plant plant : model.getPlantList()) {
			plantNames.add(plant.getName());
		}
		return plantNames;
	}
	
	
	
	public void setHandlerForSearchBar(Button searchButton) {
		searchButton.setOnAction(event -> {
			PlantSearchPane pane = view.getPlantSearchPane();
			System.out.println(pane.getTextField().getText());
			ArrayList<String> names = new ArrayList<String>();
			names.add(pane.getTextField().getText());
			pane.update(names);
		});
	}
	
	public void setHandlerForSearchTab(ComboBox<String> options) {
		options.setOnAction(event -> {
			view.getPlantSearchPane().getTextField().setText("");
			String trait = options.getSelectionModel().getSelectedItem();
			ArrayList<String> names = new ArrayList<String>();
			if(trait!="All") {
				names = model.searchPlantListByTrait(trait);
			}
			else {
				names.add("");
			}
			view.getPlantSearchPane().update(names);
		});
	}
	
	
		public void setHandlerForDragAndDrop(ImageView imgView) {
		imgView.setOnMouseReleased(event -> dragAndDrop(event));	
	}
	
	public void dragAndDrop(MouseEvent event) {
	    Node n = (Node)event.getSource();
	    Plant plant = model.Add(event.getSceneX(),event.getSceneY(), n.getId());
	   view.addPlants(plant);
	 
	}
	
	public void setHandlerForRemoveClick(ImageView imgView) {
		imgView.setOnMouseClicked(event -> removeClick(event));
	}
	
	public void removeClick(MouseEvent event) {
		Node n = (Node)event.getSource();
		Plant plantRemoved = null;
		for (Plant p : garden.getPlantsInGarden()) {
			if (n.getId().equals(p.getName() + p.getxCor() + p.getyCor())) {
					plantRemoved = p; 
			}
		}
		view.removePlant(plantRemoved);
		model.remove(plantRemoved);
	}
	
	public void setHandlerForPlantDragged(ImageView imgView) {
		imgView.setOnMouseReleased(event -> movePlant(event));
	}
	public void movePlant(MouseEvent event) {
		ImageView imgView = (ImageView) event.getSource();
		Node n = (Node)event.getSource();
		Plant plantMoved = null;
		for (Plant p : garden.getPlantsInGarden()) {
			if (n.getId().equals(p.getName() + p.getxCor() + p.getyCor())) {
				plantMoved = p; 
			}
			
		}
		model.move(event.getSceneX(),event.getSceneY(),plantMoved);
		view.movePlant(imgView, plantMoved);
	
	}
	
//	public void SetSeason() {
//		view.getDrawGardenPane().getSpringButton()
//		.setOnAction(e-> view.getDrawGardenPane().getHolder().setStyle(	"-fx-background-color: #81EEA4, linear-gradient(from 0.5px 0px to 20.5px 0px, repeat, gray 1%, transparent 2%),"
//				+ "linear-gradient(from 0px 0.5px to 0px 20.5px, "
//				+ "repeat, gray 1%, transparent 2%);"));
//	}
//	
	public void setHandlerForSaveClicked(Button b) {
		b.setOnAction(event -> save(event));
	}
	
	public void save(ActionEvent event) {
		 model.save();
	}
	
	public void setHandlerForOpenClicked(Button b) {
		b.setOnAction(event -> open(event));
	}
	
	public void open(ActionEvent event) {
		model.open(this);
	}
	
	public void openNewFile(SavedData sd) {
		this.garden = sd.garden;
		this.view.updatePlants();
	}
}

