package src.main.java;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Controller extends Application{

	View view;
	Model model;
	transient Scanner scan;
	Garden garden;
	Canvas canvas; 
	boolean loadGarden;
	Garden loadedGarden;

	private final String PLANT_INFO_CSV = "src/resources/plants.csv";
	
	final int X_DRAW_OFFSET = 332;
	private final int Y_DRAW_OFFSET = 77;
	
	public static void main(String[] args) {
		//This initializes the JavaFX view
		launch(args);
	
	}
	
	
	@Override
	public void start(Stage theStage) {
		model = new Model(loadPlantList());
		view = new View(theStage, this, model.garden.getGardenWidth(), model.garden.getGardenHeight());
		scan = new Scanner(System.in);
		canvas = view.getDrawGardenPane().getDrawGardenCanvas();
		loadGarden = false;
		
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
				plantTraits = Arrays.copyOf(plantTraits,  plantTraits.length+1);
				plantTraits[plantTraits.length - 1] = " ";
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
			
			String plantTypeTrait = view.getPlantSearchPane().typesOfPlants.getSelectionModel().getSelectedItem();
			String seasonTrait = view.getPlantSearchPane().typesOfSeasons.getSelectionModel().getSelectedItem();
			String colorTrait = view.getPlantSearchPane().typesOfColors.getSelectionModel().getSelectedItem();
			System.out.println(plantTypeTrait+" "+seasonTrait+" "+colorTrait);
			ArrayList<String> names = new ArrayList<String>();
			
			if(plantTypeTrait!="All Plants" || seasonTrait!="All Seasons" || colorTrait!= "All Colors") {
				names = model.searchPlantListByTrait(plantTypeTrait, seasonTrait, colorTrait);
			}
			else {
				names.add("");
			}
			view.getPlantSearchPane().update(names);
		});
	}
	
	
		public void setHandlerForDragAndDrop(ImageView imgView) {
		imgView.setOnMousePressed (event -> {
			view.getScene().setCursor(Cursor.CLOSED_HAND);
		});
		imgView.setOnMouseReleased(event -> dragAndDrop(event, imgView.getImage()));
	}
	
	public void dragAndDrop(MouseEvent event, Image img) {
		view.getScene().setCursor(Cursor.HAND);
	    Node n = (Node)event.getSource();
	    Plant plant = model.Add(event.getSceneX(),event.getSceneY(), n.getId());
	   view.addPlants(plant);
	   garden.setSeasonRatings();
	   view.getToolBarPane().updateRating(garden.getSeasonRatings());
	   System.out.println("Rating Updated");
	 
	}
	
	public void setHandlerForDrawButton(ToggleButton drawButton) {
		drawButton.setOnAction(event -> {
			view.getScene().setCursor(Cursor.CROSSHAIR);
			view.getDrawGardenPane().getEraseButton().setSelected(false);
			
			if(drawButton.isSelected()) {

				view.getScene().setCursor(Cursor.CROSSHAIR);
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
				view.getScene().setCursor(Cursor.DEFAULT);
				view.getDrawGardenPane().getDrawGardenCanvas().setOnMousePressed(e -> {});
				view.getDrawGardenPane().getDrawGardenCanvas().setOnMouseDragged(e->{});
			}
		});
	}
	
	public void setHandlerForEraseButton(ToggleButton eraseButton) {
		eraseButton.setOnAction(event -> {
			view.getDrawGardenPane().getDrawButton().setSelected(false);
			
			if(eraseButton.isSelected()) {
				view.getScene().setCursor(Cursor.CROSSHAIR);
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
				view.getScene().setCursor(Cursor.DEFAULT);
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
		
		garden.setSeasonRatings();
		view.getToolBarPane().updateRating(garden.getSeasonRatings());
	}
	
	public void setHandlerForPlantDragged(ImageView imgView) {
		imgView.setOnMousePressed (event -> {
			view.getScene().setCursor(Cursor.CLOSED_HAND);
		});
		imgView.setOnMouseReleased(event -> movePlant(event));
	}
	public void movePlant(MouseEvent event) {
		view.getScene().setCursor(Cursor.HAND);
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
	
	public void setHandlerForSeasonComboBox(ComboBox<String> selectSeason) {
		selectSeason.setOnAction(event -> {
			Season season = Season.SPRING;
			switch(selectSeason.getSelectionModel().getSelectedItem()) {
			case "Spring":
				season = Season.SPRING;
				break;
			case "Summer":
				season = Season.SUMMER;
				break;
			case "Autumn":
				season = Season.AUTUMN;
				break;
			case "Winter":
				season = Season.WINTER;
				break;
			default:
				season = Season.ALL_SEASONS;
			}
			garden.setSeason(season);
			view.showPlantsInSeason(garden.getPlantsInSeason());
			view.getDrawGardenPane().setSeason(season);
		});
	}
	
	public void setHandlerForNewFilePopUpClicked(MenuItem newFile) {
		newFile.setOnAction(event -> {
			ToolBarPane tbp = view.getToolBarPane();
			tbp.gardenName.setText("");
			tbp.widthValueFactory.setValue(1);
			tbp.heightValueFactory.setValue(1);
			tbp.getNewFilePopUp().show(view.getStage());
		});
	}
	
	public void setHandlerForNewFilePopUpClicked(Button newFile) {
		newFile.setOnAction(event -> {
			view.getToolBarPane().getNewFilePopUp().show(view.getStage());
		});
	}
	
	public void setHandlerForNewFileClicked(Button start) {
		start.setOnAction(event -> {
			ToolBarPane tbp = view.getToolBarPane();
			createNewFile(tbp.gardenName.getText(), tbp.widthValueFactory.getValue(), tbp.heightValueFactory.getValue());
			view.getToolBarPane().getNewFilePopUp().hide();
			view.getToolBarPane().getHelpPopUp().show(view.getStage());
		});
	}
	
	public void createNewFile(String gardenName, int width, int height) {
		garden = new Garden(width, height);
		model.setGarden(garden);
		this.view.loadNewGarden(garden.getPlantsInGarden(), garden.getSeasonRatings(), garden.getSeason(), garden.getGardenWidth(), garden.getGardenHeight());
		save(gardenName);
	}
	
	public void setHandlerForSaveMenuItemClicked(MenuItem save) {
		save.setOnAction(event -> {
			save(model.savedData.getFileName());
		});
	}
	
	public void setHandlerForCancelNewFileClicked(Button cancel) {
		cancel.setOnAction(event -> {
			view.getToolBarPane().getNewFilePopUp().hide();
		});
	}
	
	public void setHandlerForSaveAsPopUpClicked(MenuItem saveAs) {
		saveAs.setOnAction(event -> {
			view.getToolBarPane().getSaveAsPopUp().show(view.getStage());
		});
	}
	
	public void setHandlerForSaveClicked(Button save, TextField fileName) {
		save.setOnAction(event -> {
			
			save(fileName.getText());
			fileName.setText("");
			view.getToolBarPane().getSaveAsPopUp().hide();
		});
	}
	
	public void save(String fileName) {
		model.savedData = new SavedData(fileName, model);
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("gardens/"+fileName + ".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(model.savedData);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in "+fileName+".ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public void setHandlerForCancelSaveAsClicked(Button cancel) {
		cancel.setOnAction(event -> {
			view.getToolBarPane().getSaveAsPopUp().hide();
		});
	}
	
	public void setHandlerForOpenPopUpClicked(MenuItem open) {
		open.setOnAction(event -> {
			view.getToolBarPane().updateOpenPopUp(getGardenFiles());
			view.getToolBarPane().getOpenPopUp().show(view.getStage());
		});
	}
	
	public void setHandlerForOpenPopUpClicked(Button open) {
		open.setOnAction(event -> {
			view.getToolBarPane().updateOpenPopUp(getGardenFiles());
			view.getToolBarPane().getOpenPopUp().show(view.getStage());
		});
	}
	
	public void setHandlerForCancelOpenPopUpClicked(Button cancel) {
		cancel.setOnAction(event -> {
			view.getToolBarPane().getOpenPopUp().hide();
		});
	}
	
	public void setHandlerForOpenClicked(Button open, ListView<String> files) {
		open.setOnAction(event -> {
			String fileName = (String)files.getSelectionModel().getSelectedItem();
			open(fileName);
			view.getToolBarPane().getOpenPopUp().hide();
		});
	}
	
	public void open(String fileName) {
		try {
	         FileInputStream fileIn = new FileInputStream("gardens/"+fileName);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         SavedData sd = (SavedData) in.readObject();
	         this.openNewFile(sd);
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("File not found");
	         c.printStackTrace();
	         return;
	      }
	}
	
	public void openNewFile(SavedData sd) {
		model.setGarden(sd.getGarden());
		model.savedData = sd;
		this.garden = model.getGarden();
		this.view.loadNewGarden(garden.getPlantsInGarden(), garden.getSeasonRatings(), garden.getSeason(), garden.getGardenWidth(), garden.getGardenHeight());
	}
	
	public ArrayList<String> getGardenFiles() {
		ArrayList<String> fileNames = new ArrayList<String>();
		
		File[] files = new File("gardens").listFiles();
		if(files!=null) {
			for(File file : files) {
				fileNames.add(file.getName());
			}
		}
		
		
		return fileNames;
	}
	
	public String getPlantDescription(String plantName) {
		return model.getPlantDescription(plantName);
	}
	
	public void setHandlerForPlantEncyclopediaClicked(Button b) {
		b.setOnAction(event -> {
			view.getToolBarPane().getPlantEncycPopUp().show(view.getStage());
		});
	}
	
	public void setHandlerForDonePlantEncycClicked(Button b) {
		b.setOnAction(event -> {
			view.getToolBarPane().getPlantEncycPopUp().hide();
		});
	}
	
	public void setHandlerForHelpButton(Button helpButton) {
		helpButton.setOnAction(event -> {
			view.getToolBarPane().getHelpPopUp().show(view.getStage());
		});
	}
	
	public void setHandlerForHelpButtonClose(Button b) {
		b.setOnAction(event -> {
			view.getToolBarPane().getHelpPopUp().hide();
		});
	}
	
	public boolean getLoadGarden() {
		return loadGarden; 
	}
	
	public Garden getLoadedGarden() {
		return loadedGarden;
	}
}

