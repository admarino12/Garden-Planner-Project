package src.main.java;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class Controller extends Application{

	View view;
	Model model;
	transient Scanner scan;
	Garden garden;
	Canvas canvas; 

	private final String PLANT_INFO_CSV = "src/resources/plants.csv";
	
	final int X_DRAW_OFFSET = 258;
	private final int Y_DRAW_OFFSET = 184;
	
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
        
    }
	
	public ArrayList<Plant> loadPlantList(){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		String line = "";
		
		//Read each line of the CSV file and pull the plant name, description, and String array of plant traits
		try {
			FileReader file = new FileReader(PLANT_INFO_CSV);
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
	   model.updateRating();
	   view.getToolBarPane().updateRating(model.getRating());
	   System.out.println(model.rating + " stars.");
	 
	}
	
	public void setHandlerForDrawButton(ToggleButton drawButton) {
		drawButton.setOnAction(event -> {
			view.getDrawGardenPane().getEraseButton().setSelected(false);
			
			if(drawButton.isSelected()) {
				//Draw Start
				view.getDrawGardenPane().getDrawGardenCanvas().setOnMousePressed(e->{
					view.getgc().beginPath();
					view.getgc().lineTo(e.getSceneX()-X_DRAW_OFFSET, e.getSceneY()-Y_DRAW_OFFSET);
					view.getgc().stroke();
				});
				
				//Draw Line
				view.getDrawGardenPane().getDrawGardenCanvas().setOnMouseDragged(e->{
					view.getgc().lineTo(e.getSceneX()-X_DRAW_OFFSET, e.getSceneY()-Y_DRAW_OFFSET);
					view.getgc().stroke();
				});
			}
			else { 
				view.getDrawGardenPane().getDrawGardenCanvas().setOnMousePressed(e -> {});
				view.getDrawGardenPane().getDrawGardenCanvas().setOnMouseDragged(e->{});
			}
		});
	}
	
	public void setHandlerForEraseButton(ToggleButton eraseButton) {
		eraseButton.setOnAction(event -> {
			view.getDrawGardenPane().getDrawButton().setSelected(false);
			
			if(eraseButton.isSelected()) {
				//Erase Start
				view.getDrawGardenPane().getDrawGardenCanvas().setOnMousePressed(e->{
					double lineWidth = view.getgc().getLineWidth()*4;
					view.getgc().clearRect(e.getSceneX()-X_DRAW_OFFSET - lineWidth , e.getSceneY()-Y_DRAW_OFFSET - lineWidth,
							lineWidth, lineWidth);
				});
				
				//Erase Line
				view.getDrawGardenPane().getDrawGardenCanvas().setOnMouseDragged(e->{
					double lineWidth = view.getgc().getLineWidth()*4;
					view.getgc().clearRect(e.getSceneX()-X_DRAW_OFFSET - lineWidth , e.getSceneY()-Y_DRAW_OFFSET - lineWidth,
							lineWidth, lineWidth);
				});
			}
			else { 
				view.getDrawGardenPane().getDrawGardenCanvas().setOnMousePressed(e -> {});
				view.getDrawGardenPane().getDrawGardenCanvas().setOnMouseDragged(e->{});	
			}
		});
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
	
	public void setHandlerForSeasonButton(Button seasonButton) {
		seasonButton.setOnAction(event -> {
			view.getDrawGardenPane().setSeason(seasonButton.getText());
		});
	}
	
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
	
	public String getPlantDescription(String plantName) {
		return model.getPlantDescription(plantName);
	}
}

