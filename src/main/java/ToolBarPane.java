package src.main.java;


import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class ToolBarPane {
	private ToolBar ratingToolBar;
	private Circle[] ratingCircles;
	final private int RATING_TOTAL = 4;
	
	MenuButton fileButton;
	Button plantEncyclopedia;
	Button helpButton; 
	Popup plantEncycPopUp; 
	Popup newFilePopUp;
	Popup saveAsPopUp;
	Popup openPopUp;
	Popup helpPopUp;
	TextField gardenName;
	TextField widthText;
	TextField heightText;
	ListView<String> files;
	View mainView;
	
	
	public ToolBarPane(View mainView) {
		this.mainView = mainView;
		
		Label rating = new Label("Rating:");
		rating.setPadding(new Insets(0,0,0,900));
		ratingToolBar = new ToolBar();
		ratingToolBar.setPadding(new Insets(0,0,0,5));
		
		createFileButton();
		createPlantEncyclopediaButton();
		createHelpButton();
		
		HBox hb4 = new HBox();
		ratingCircles = new Circle[RATING_TOTAL];
		for(int i=0;i<ratingCircles.length;i++) {
			Circle circle = new Circle();
			circle.setCenterX(25.0f);
			circle.setCenterY(25.0f);
			circle.setRadius(10.0f);
			circle.setFill(Color.WHITE);
			circle.setStroke(Color.BLACK);
			circle.setStrokeWidth(1);
			ratingCircles[i] = circle;
		}
		hb4.setPadding(new Insets(5, 10, 5, 0));
		hb4.setSpacing(5);
		hb4.getChildren().addAll(ratingCircles);
		ratingToolBar.getItems().addAll( fileButton, plantEncyclopedia,helpButton, rating, hb4);
	}
	
	public void createFileButton() {
		fileButton = new MenuButton("File");
		
		MenuItem newFile = new MenuItem("New File...");
		mainView.control.setHandlerForNewFilePopUpClicked(newFile);
		createNewFilePopUp();
		
		MenuItem save = new MenuItem("Save");
		mainView.control.setHandlerForSaveMenuItemClicked(save);
		
		MenuItem saveAs = new MenuItem("Save As...");
		mainView.control.setHandlerForSaveAsPopUpClicked(saveAs);
		createSaveAsPopUp();
		MenuItem open = new MenuItem("Open...");
		mainView.control.setHandlerForOpenPopUpClicked(open);
		createOpenPopUp();
		fileButton.getItems().addAll(newFile, save, saveAs, open);
		
	}
	
	public void createPlantEncyclopediaButton() {
		plantEncyclopedia = new Button ("Plant Encyclopedia");
		mainView.control.setHandlerForPlantEncyclopediaClicked(plantEncyclopedia);
		createPlantEncyclopediaPopUp();
	}
	
	public void createHelpButton() {
		helpButton = new Button ("Help");
		mainView.control.setHandlerForHelpButton(helpButton);
		createHelpPopUp();
	}
	
	public void createNewFilePopUp() {
		newFilePopUp = new Popup();
		newFilePopUp.setAutoHide(true);
		
		Label title = new Label("Create New Garden");
		title.setPadding(new Insets(5,0,15,0));
		title.setFont(new Font(16));
		title.setAlignment(Pos.CENTER);
		
		VBox hb1 = new VBox();
		Label gardenNameLabel = new Label("Garden Name");
		gardenNameLabel.setPadding(new Insets(0,5,0,0));
		gardenName = new TextField();
		gardenName.setMaxWidth(175);
		hb1.getChildren().addAll(gardenNameLabel, gardenName);
		hb1.setPadding(new Insets(0,0,20,0));
		
		Label gardenDemLabel = new Label("Garden Dimensions");
		gardenDemLabel.setPadding(new Insets(0,0,0,0));
		HBox hb2 = new HBox();
		Label heightLabel = new Label("Height: ");
		heightText = new TextField();
		heightText.setMaxWidth(60);
		Label feetLabel1 = new Label("ft.");
		feetLabel1.setPadding(new Insets(0,5,0,2));
		hb2.getChildren().addAll(heightLabel, heightText, feetLabel1);
		hb2.setPadding(new Insets(0,0,0,0));
		
		HBox hb3 = new HBox();
		Label widthLabel = new Label("Width:  ");
		widthText = new TextField();
		widthText.setMaxWidth(60);
		Label feetLabel2 = new Label("ft.");
		feetLabel2.setPadding(new Insets(0,5,0,2));
		hb3.getChildren().addAll(widthLabel, widthText, feetLabel2);
		hb3.setPadding(new Insets(0,0,20,0));

		HBox hb4 = new HBox();
		Button start = new Button("Start");
		mainView.control.setHandlerForNewFileClicked(start);
		Button cancel = new Button("Cancel");
		hb4.getChildren().addAll(start, cancel);
		hb4.setAlignment(Pos.CENTER);
		hb4.setSpacing(20);
		
		
		
		VBox vb = new VBox();
		vb.setStyle("-fx-background-color:#D1EBDC;-fx-border-color: black;-fx-border-width:2;-fx-border-radius:3;-fx-hgap:3;-fx-vgap:5;");
		vb.setPadding(new Insets(10,20,10,20));
		//vb.setMinWidth(450);
		//vb.setMinHeight(300);
		vb.getChildren().addAll(title, hb1, gardenDemLabel,  hb2, hb3, hb4);
		
		newFilePopUp.getContent().add(vb);
	}
	
	public void createSaveAsPopUp() {
		saveAsPopUp = new Popup();
		saveAsPopUp.setAutoHide(true);
		
		//First Row of PopUp. Contains: Label and TextField
		Label label = new Label("Save as: ");
		TextField fileName = new TextField();
		HBox hb1 = new HBox();
		hb1.setPadding(new Insets(0,0,10,0));
		hb1.getChildren().addAll(label, fileName);
		
		//Second Row of PupUp. Contains: Cancel Button and Save Button
		Button save = new Button("Save");
		mainView.control.setHandlerForSaveClicked(save, fileName);
		Button cancel = new Button("Cancel");
		mainView.control.setHandlerForCancelSaveAsClicked(cancel);
		HBox hb2 = new HBox();
		hb2.setAlignment(Pos.CENTER);
		hb2.setSpacing(12);
		hb2.getChildren().addAll(save, cancel);
		
		//ViewBox contains the two Rows above
		VBox vb = new VBox();
		vb.setStyle("-fx-background-color:#D1EBDC;-fx-border-color: black;-fx-border-width:2;-fx-border-radius:3;-fx-hgap:3;-fx-vgap:5;");
		vb.setPadding(new Insets(10,10,5,10));
		vb.getChildren().addAll(hb1, hb2);
		
		saveAsPopUp.getContent().add(vb);
	}
	
	public void createOpenPopUp() {
		openPopUp = new Popup();
		openPopUp.setAutoHide(true);
		
		Label label = new Label("Select a File:");
		files = new ListView<String>();
		files.setStyle("-fx-background-color:white;-fx-border-color: grey;-fx-border-width:1;-fx-border-radius:3;");
		files.setMinHeight(300);
		files.setMinWidth(200);
		
		Button open = new Button("Open");
		mainView.control.setHandlerForOpenClicked(open, files);
		Button cancel = new Button("Cancel");
		mainView.control.setHandlerForCancelOpenPopUpClicked(cancel);
		HBox hb = new HBox();
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(12);
		hb.setPadding(new Insets(5,0,0,0));
		hb.getChildren().addAll(open, cancel);
		
		VBox vb = new VBox();
		vb.setStyle("-fx-background-color:#D1EBDC;-fx-border-color: black;-fx-border-width:2;-fx-border-radius:3;-fx-hgap:3;-fx-vgap:5;");
		vb.setPadding(new Insets(10,10,5,10));
		vb.getChildren().addAll(label, files, hb);
		
		openPopUp.getContent().add(vb);
	}
	
	public void updateOpenPopUp(ArrayList<String> fileNames) {
		files.getItems().clear();
		for(String name : fileNames) {
			files.getItems().add(name);
		}
	}
	
	public void createPlantEncyclopediaPopUp() {
		plantEncycPopUp = new Popup(); 
		plantEncycPopUp.setAutoHide(true);
		
		Label label = new Label("Plant Encyclopedia");
		label.setStyle("-fx-font-weight: bold");
		
		Button done = new Button ("Done");
		mainView.control.setHandlerForDonePlantEncycClicked(done);
		HBox hb = new HBox();
		hb.setAlignment(Pos.CENTER);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(12);
		hb.setPadding(new Insets(10,5,5,10));
		hb.getChildren().addAll(done);
		
		Text plantData[] = new Text [mainView.control.getPlantNames().size()];
		int counter = 0;
		for (String name: mainView.control.getPlantNames()) {
			plantData[counter] = new Text( name.replace("_", " ") + ": " + mainView.control.getPlantDescription(name));
			counter++;
		}
		
		VBox vb = new VBox(8);
		vb.setStyle("-fx-background-color:#E7DEBC;-fx-border-color: black;-fx-border-width:2;-fx-border-radius:3;-fx-hgap:4;-fx-vgap:6;");
		vb.setPadding(new Insets(10,5,5,10));
		vb.getChildren().add(label);
		
		for (Text data: plantData) {
			vb.getChildren().add(data);
		}
		
		vb.getChildren().addAll(hb);
		
		plantEncycPopUp.getContent().add(vb);
		
		
	}
	
	public void createHelpPopUp() {
		helpPopUp = new Popup();
		helpPopUp.setAutoHide(true);
		
		Label label = new Label("Help");
		label.setStyle("-fx-font-weight: bold");
		
		Button done = new Button ("Done");
		mainView.control.setHandlerForHelpButtonClose(done);
		HBox hb = new HBox();
		hb.setAlignment(Pos.BOTTOM_CENTER);
		hb.setSpacing(12);
		hb.setPadding(new Insets(10,5,5,10));
		hb.getChildren().addAll(done);
		
		VBox vb = new VBox(8);
		vb.setStyle("-fx-background-color:#E7DEBC;-fx-border-color: black;-fx-border-width:2;-fx-border-radius:3;-fx-hgap:4;-fx-vgap:6;");
		vb.setPadding(new Insets(10,5,5,10));
		vb.getChildren().add(label);
		vb.setMinSize(250, 250);
		vb.getChildren().addAll(hb);
		
		helpPopUp.getContent().add(vb);
		
	}
	
	public void updateRating(int rating) {
		for(int i=0;i<ratingCircles.length;i++) {
			if(i<rating) {
				ratingCircles[i].setFill(Color.GOLD);
			}
			else ratingCircles[i].setFill(Color.WHITE);
		}
	}
	
	public ToolBar getRatingToolBar() {
		return ratingToolBar;
	}
	
	public Popup getNewFilePopUp() {
		return newFilePopUp;
	}
	
	public Popup getSaveAsPopUp() {
		return saveAsPopUp;
	}
	
	public Popup getOpenPopUp() {
		return openPopUp;
	}
	
	public Popup getPlantEncycPopUp() {
		return plantEncycPopUp; 
	}
	
	public Popup getHelpPopUp() {
		return helpPopUp; 
	}
	
}
