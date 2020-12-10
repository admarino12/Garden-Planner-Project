package src.main.java;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;

/**
 * The ToolBarPane class contains all view elements for the ToolBarPane. This is
 * then linked to the main view.
 * 
 *
 *
 */
public class ToolBarPane {
	public ToolBar ratingToolBar;
	private Circle[] ratingCircles;
	final private int RATING_TOTAL = 4;

	final private int MIN_GARDEN_WIDTH = 10;
	final private int MAX_GARDEN_WIDTH = 150;
	final private int MIN_GARDEN_HEIGHT = 10;
	final private int MAX_GARDEN_HEIGHT = 150;

	final private String springColor = "rgba:(129, 238, 164";
	final private String summerColor = "rgba:(129, 238, 164";

	MenuButton fileButton;
	Button plantEncyclopedia;
	Button helpButton;
	Popup plantEncycPopUp;
	Popup newFilePopUp;
	Popup saveAsPopUp;
	Popup openPopUp;
	Popup helpPopUp;
	TextField gardenName;
	Spinner<Integer> heightSpinner;
	SpinnerValueFactory<Integer> heightValueFactory;
	Spinner<Integer> widthSpinner;
	SpinnerValueFactory<Integer> widthValueFactory;
	ListView<String> files;
	View mainView;

	/**
	 * Constructor for ToolBarPane. Initializes the ToolBarPane.
	 * 
	 * @param mainView View instance of view.
	 */
	public ToolBarPane(View mainView) {
		this.mainView = mainView;

		Label rating = new Label("Continuous Bloom:");
		rating.setPadding(new Insets(0, 0, 0, 834));
		ratingToolBar = new ToolBar();
		ratingToolBar.setPadding(new Insets(0, 0, 0, 5));

		createFileButton();
		createPlantEncyclopediaButton();
		createHelpButton();

		HBox hb4 = new HBox();
		ratingCircles = new Circle[RATING_TOTAL];
		for (int i = 0; i < ratingCircles.length; i++) {
			Circle circle = new Circle();
			circle.setCenterX(25.0f);
			circle.setCenterY(25.0f);
			circle.setRadius(10.0f);
			circle.setStroke(Color.BLACK);
			circle.setStrokeWidth(1);
			ratingCircles[i] = circle;
		}
		hb4.setPadding(new Insets(5, 10, 5, 0));
		hb4.setSpacing(5);
		hb4.getChildren().addAll(ratingCircles);
		ratingToolBar.getItems().addAll(fileButton, plantEncyclopedia, helpButton, rating, hb4);
		ratingToolBar.setStyle("-fx-background-color:#dbd0ab;-fx-border-color: #a8a083;-fx-border-width:1;-fx-border-radius:3;");
		ratingToolBar.setMinHeight(50);
	}

	/**
	 * Creates FileButton.
	 */
	public void createFileButton() {
		fileButton = new MenuButton("File");
		fileButton.setStyle("-fx-background-color:#D1EBDC;-fx-border-color: black;-fx-border-width:.5;-fx-border-radius:3;");

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

	/**
	 * Creates PlantEncyclopediaButton.
	 */
	public void createPlantEncyclopediaButton() {
		plantEncyclopedia = new Button("Plant Encyclopedia");
		mainView.control.setHandlerForPlantEncyclopediaClicked(plantEncyclopedia);
		plantEncyclopedia.setStyle("-fx-background-color:#D1EBDC;-fx-border-color: black;-fx-border-width:.5;-fx-border-radius:3;");
	}

	/**
	 * Creates helpButton.
	 */
	public void createHelpButton() {
		helpButton = new Button("Help");
		mainView.control.setHandlerForHelpButton(helpButton);
		helpButton.setStyle("-fx-background-color:#D1EBDC;-fx-border-color: black;-fx-border-width:.5;-fx-border-radius:3;");
		createHelpPopUp();
	}

	/**
	 * Creates NewFilePopUp window.
	 */
	public void createNewFilePopUp() {
		newFilePopUp = new Popup();
		newFilePopUp.setAutoHide(true);

		Label title = new Label("Create New Garden");
		title.setPadding(new Insets(5, 0, 15, 0));
		title.setFont(new Font(16));
		title.setAlignment(Pos.CENTER);

		VBox hb1 = new VBox();
		Label gardenNameLabel = new Label("Garden Name: ");
		gardenNameLabel.setPadding(new Insets(0, 5, 0, 0));
		gardenName = new TextField();
		gardenName.setMaxWidth(175);
		hb1.getChildren().addAll(gardenNameLabel, gardenName);
		hb1.setPadding(new Insets(0, 0, 20, 0));

		Label gardenDemLabel = new Label("Garden Dimensions: ");
		gardenDemLabel.setPadding(new Insets(0, 0, 0, 0));

		HBox hb2 = new HBox();
		Label widthLabel = new Label("Garden Width: ");
		widthLabel.setPadding(new Insets(0,4,0,0));
		widthSpinner = new Spinner<Integer>();
		widthValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_GARDEN_WIDTH, MAX_GARDEN_WIDTH, 1);
		widthSpinner.setValueFactory(widthValueFactory);
		widthSpinner.setMaxWidth(60);
		Label feetLabel2 = new Label("ft.");
		feetLabel2.setPadding(new Insets(0, 5, 0, 2));
		hb2.getChildren().addAll(widthLabel, widthSpinner, feetLabel2);
		hb2.setPadding(new Insets(0, 0, 0, 0));

		HBox hb3 = new HBox();
		Label heightLabel = new Label("Garden Height: ");
		heightSpinner = new Spinner<Integer>();
		heightValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_GARDEN_HEIGHT, MAX_GARDEN_HEIGHT, 1);
		heightSpinner.setValueFactory(heightValueFactory);
		heightSpinner.setMaxWidth(60);
		Label feetLabel1 = new Label("ft.");
		feetLabel1.setPadding(new Insets(0, 5, 0, 2));
		hb3.getChildren().addAll(heightLabel, heightSpinner, feetLabel1);
		hb3.setPadding(new Insets(0, 0, 20, 0));

		HBox hb4 = new HBox();
		Button start = new Button("Start");
		mainView.control.setHandlerForNewFileClicked(start);
		Button cancel = new Button("Cancel");
		mainView.control.setHandlerForCancelNewFileClicked(cancel);
		hb4.getChildren().addAll(start, cancel);
		hb4.setAlignment(Pos.CENTER);
		hb4.setSpacing(20);
		hb4.setPadding(new Insets(10,10,10,10));

		VBox vb = new VBox();
		vb.setStyle(
				"-fx-background-color:#D1EBDC;-fx-border-color: black;-fx-border-width:2;-fx-border-radius:3;-fx-hgap:3;-fx-vgap:5;");
		vb.setPadding(new Insets(10, 20, 10, 20));
		// vb.setMinWidth(450);
		// vb.setMinHeight(300);
		vb.getChildren().addAll(title, hb1, gardenDemLabel, hb2 , hb3 , hb4);

		newFilePopUp.getContent().add(vb);
	}

	/**
	 * Creates SaveAsPopUp window.
	 */
	public void createSaveAsPopUp() {
		saveAsPopUp = new Popup();
		saveAsPopUp.setAutoHide(true);

		// First Row of PopUp. Contains: Label and TextField
		Label label = new Label("Save as: ");
		TextField fileName = new TextField();
		HBox hb1 = new HBox();
		hb1.setPadding(new Insets(0, 0, 10, 0));
		hb1.getChildren().addAll(label, fileName);

		// Second Row of PupUp. Contains: Cancel Button and Save Button
		Button save = new Button("Save");
		mainView.control.setHandlerForSaveClicked(save, fileName);
		Button cancel = new Button("Cancel");
		mainView.control.setHandlerForCancelSaveAsClicked(cancel);
		HBox hb2 = new HBox();
		hb2.setAlignment(Pos.CENTER);
		hb2.setSpacing(12);
		hb2.getChildren().addAll(save, cancel);

		// ViewBox contains the two Rows above
		VBox vb = new VBox();
		vb.setStyle(
				"-fx-background-color:#D1EBDC;-fx-border-color: black;-fx-border-width:2;-fx-border-radius:3;-fx-hgap:3;-fx-vgap:5;");
		vb.setPadding(new Insets(10, 10, 5, 10));
		vb.getChildren().addAll(hb1, hb2);

		saveAsPopUp.getContent().add(vb);
	}

	/**
	 * Creates OpenPopUp window.
	 */
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
		hb.setPadding(new Insets(5, 0, 0, 0));
		hb.getChildren().addAll(open, cancel);

		VBox vb = new VBox();
		vb.setStyle(
				"-fx-background-color:#D1EBDC;-fx-border-color: black;-fx-border-width:2;-fx-border-radius:3;-fx-hgap:3;-fx-vgap:5;");
		vb.setPadding(new Insets(10, 10, 5, 10));
		vb.getChildren().addAll(label, files, hb);

		openPopUp.getContent().add(vb);
	}

	/**
	 * Updates the PopUp with correct saved fileNames.
	 * 
	 * @param fileNames ArrayList of Strings for names of files.
	 */
	public void updateOpenPopUp(ArrayList<String> fileNames) {
		files.getItems().clear();
		for (String name : fileNames) {
			files.getItems().add(name);
		}
	}

	/**
	 * Creates HelpPopUp Window.
	 */
	
	public void createHelpPopUp() {
		helpPopUp = new Popup();
		helpPopUp.setAutoHide(true);

		Label label = new Label("Help");
		label.setStyle("-fx-font-weight: bold;-fx-font: 24 Papyrus");

		Button done = new Button("Done");
		done.setStyle("-fx-background-color:#E7DEBC;-fx-border-color: black;-fx-border-width:.5;-fx-border-radius:3;-fx-font: 18 Garamond");
		mainView.control.setHandlerForHelpButtonClose(done);
		HBox hb = new HBox();
		hb.setAlignment(Pos.BOTTOM_CENTER);
		hb.setSpacing(12);
		hb.setPadding(new Insets(10, 5, 5, 10));
		hb.getChildren().addAll(done);

		Text HelpInfo[] = new Text[mainView.control.getHelpText().length];
		int counter = 0;
		String[] helpText = mainView.control.getHelpText();
		for (String name : helpText) {
			HelpInfo[counter] = new Text(name);
			counter++;
		}

		VBox vb = new VBox(8);
		vb.setStyle(
				"-fx-background-color:##dbd0ab;-fx-border-color: black;-fx-border-width:.5;-fx-border-radius:3;-fx-hgap:4;-fx-vgap:6;");
		vb.setPadding(new Insets(10, 5, 5, 10));
		vb.getChildren().add(label);
		vb.setMinSize(250, 250);

		for (Text data : HelpInfo) {
			data.setStyle("-fx-font: 18 garamond");
			vb.getChildren().add(data);
		}
		vb.getChildren().addAll(hb);
		vb.setStyle("-fx-background-color:#D1EBDC;-fx-border-color: black;-fx-border-width:2;-fx-border-radius:3;");
		helpPopUp.getContent().add(vb);

	}

	/**
	 * Updates the rating system based on plants. Based on the plants that bloom in
	 * the season fill the circle with the appropriate color based on the season the
	 * plant blooms.
	 * 
	 * @param ratings Integer for rating.
	 */
	public void updateRating(ArrayList<Integer> ratings) {
		double opacity = 0;
		for (int i = 0; i < ratingCircles.length; i++) {
			if (ratings.get(i) > 10) {
				opacity = 1;
			} else
				opacity = (double) ((double) (ratings.get(i)) / 10.0);
			switch (i) {
			case 0:
				ratingCircles[i].setFill(Color.rgb(129, 238, 164, opacity));
				break;
			case 1:
				ratingCircles[i].setFill(Color.rgb(255, 244, 179, opacity));
				break;
			case 2:
				ratingCircles[i].setFill(Color.rgb(232, 198, 150, opacity));
				break;
			case 3:
				ratingCircles[i].setFill(Color.rgb(230, 255, 255, opacity));
				break;
			}
		}

	}

	/**
	 * Getter for ratingToolBar.
	 * 
	 * @return ratingToolBar ToolBar.
	 */
	public ToolBar getRatingToolBar() {
		return ratingToolBar;
	}

	/**
	 * Getter for newFilePopUp
	 * 
	 * @return newFilePopUp PopUp.
	 */
	public Popup getNewFilePopUp() {
		return newFilePopUp;
	}

	/**
	 * Getter for saveAsPopUp.
	 * 
	 * @return saveAsPopUp PopUp.
	 */
	public Popup getSaveAsPopUp() {
		return saveAsPopUp;
	}

	/**
	 * Getter for openPopUp.
	 * 
	 * @return openPopUp PopUp.
	 */
	public Popup getOpenPopUp() {
		return openPopUp;
	}

	/**
	 * Getter for plantEncycPopUp.
	 * 
	 * @return plantEncycPopUp PopUp.
	 */
	public Popup getPlantEncycPopUp() {
		return plantEncycPopUp;
	}

	/**
	 * Getter for helpPopUp
	 * 
	 * @return helpPopUp PopUp.
	 */
	public Popup getHelpPopUp() {
		return helpPopUp;
	}

}
