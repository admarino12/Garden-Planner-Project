package src.main.java;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Controller extends Application {

	View view;
	Model model;
	Scanner scan;
	Garden garden;
	
	
	public static void main(String[] args) {
		//This initializes the JavaFX view
		launch(args);
	
	}
	
	
	@Override
	public void start(Stage theStage) {
		model = new Model(loadPlantList());
		view = new View(theStage, this);
		scan = new Scanner(System.in);
		
		this.garden = model.garden;
        
		//Draw start
		view.getGardenCanvas().setOnMousePressed(e->{
			if(view.getDraw().isSelected() && !view.getErase().isSelected()) {
			view.getgc().beginPath();
			view.getgc().lineTo(e.getSceneX()-258, e.getSceneY()-84);
			view.getgc().stroke();
			}
		//Erase start
			else if(!view.getDraw().isSelected() && view.getErase().isSelected()) {
				double lineWidth = view.getgc().getLineWidth()*4;
				view.getgc().clearRect(e.getSceneX()-258 - lineWidth , e.getSceneY()-84 - lineWidth,
						lineWidth, lineWidth);
			}
			
			});	
		
		//Draw line
		view.getGardenCanvas().setOnMouseDragged(e->{
			if(view.getDraw().isSelected() && !view.getErase().isSelected()) {
			view.getgc().lineTo(e.getSceneX()-258, e.getSceneY()-84);
			view.getgc().stroke();
			}
		//Erase line
			else if(!view.getDraw().isSelected() && view.getErase().isSelected()) {
				double lineWidth = view.getgc().getLineWidth()*4;
				view.getgc().clearRect(e.getSceneX()-258 - lineWidth , e.getSceneY()-84 - lineWidth,
						lineWidth, lineWidth);
			}
			});
		
		
    }
	
	public ArrayList<Plant> loadPlantList(){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		String line = "";
		
		//Read each line of the CSV file and pull the plant name, description, and String array of plant traits
		try {
			FileReader file = new FileReader("src/resources/plants.csv");
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
	
	
		public void setHandlerForDrag(ImageView imgView) {
		//imgView.setOnMouseDragged();	
		imgView.setOnMouseReleased(event -> drag(event));	
	}
	
	public void drag(MouseEvent event) {
	    Node n = (Node)event.getSource();

	    //System.out.println(n);
	 if (view.getAdd().isSelected()) {

	   Plant plant = model.Add(event.getSceneX(),event.getSceneY(), n.getId());
	   view.addPlants(plant);
	 }
	}
	
	public void setHandlerForPlantClick(ImageView imgView) {
		imgView.setOnMouseClicked(event -> click(event));
	}
	public void click(MouseEvent event) {
		Node n = (Node)event.getSource();
		
		if (view.getRemove().isSelected()) {
			Plant plantRemoved = null;
			for (Plant p : garden.getPlantsInGarden()) {
				if (n.getId().equals(p.getName() + p.getxCor())) {
					plantRemoved = p; 
				}
			}
			view.removePlant(plantRemoved);
			model.remove(plantRemoved);
		}
	}
	
	public void setHandlerForPlantDragged(ImageView imgView) {
		imgView.setOnMouseReleased(event -> move(event));
	}
	public void move(MouseEvent event) {
		ImageView imgView = (ImageView) event.getSource();
		Node n = (Node)event.getSource();
		
		if (view.getMove().isSelected()) {
			Plant plantMoved = null;
			for (Plant p : garden.getPlantsInGarden()) {
				if (n.getId().equals(p.getName() + p.getxCor())) {
					plantMoved = p; 
				}
			}
			model.move(event.getSceneX(),event.getSceneY(),plantMoved);
			view.movePlant(imgView, plantMoved);
			
	}
	
	}
}

