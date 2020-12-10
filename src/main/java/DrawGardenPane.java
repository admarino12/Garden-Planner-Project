package src.main.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
/**
 * The DrawGardenPane class contains all JavaFx View elements for the Garden Area of the View.
 * @author Elijah Haberman
 *
 */
public class DrawGardenPane {

	// Season Styles
	final private String springStyle = "-fx-background-color: #81EEA4;";
	final private String summerStyle = "-fx-background-color: #FFF4B3;";
	final private String fallStyle = "-fx-background-color: #E8C696;";
	final private String winterStyle = "-fx-background-color: #E6FFFF;";
	final private String allSeasonStyle = "-fx-background-color: #bff5d0;";

	ObservableList<String> seasonOptions = FXCollections.observableArrayList("All Seasons", "Spring", "Summer",
			"Autumn", "Winter");
	final ComboBox<String> selectSeason = new ComboBox<String>(seasonOptions);

	// DrawGardenPane ToolBar
	private ToolBar drawGardenToolBar;
	private ToggleButton eraseButton = new ToggleButton("Erase");
	private ToggleButton drawButton = new ToggleButton("Draw");
	private ToggleButton paintPlantsButton = new ToggleButton("Paint");
		
	// DrawGardenPane MainPane
	private BorderPane holder;
	private BorderPane drawGardenBorder;
	private Canvas drawGardenCanvas;

	private Label gardenDimLabel;
	
	// DrawGardenPane Dimensions
	final private int DRAW_GARDENPANE_WIDTH = 900;
	final private int DRAW_GARDENPANE_HEIGHT = 900;
	
	Rectangle rect = new Rectangle();
	// Increment
	private int GRID_INCREMENT;
/**
 * Constructor for DrawGarden Pane.
 * Initializes UI elements for DrawGardenPane.
 * @param mainView View.
 * @param width Width of Garden set by User.
 * @param height Height of Gardens set by User.
 */
	public DrawGardenPane(View mainView, int width, int height) {

		drawGardenCanvas = new Canvas(DRAW_GARDENPANE_WIDTH, DRAW_GARDENPANE_HEIGHT);
		drawGardenCanvas.minWidth(DRAW_GARDENPANE_WIDTH);
		drawGardenCanvas.minHeight(DRAW_GARDENPANE_HEIGHT);

		holder = new BorderPane();


		//holder.getChildren().add(drawGardenCanvas);

		mainView.control.setHandlerForDrawButton(drawButton);
		mainView.control.setHandlerForEraseButton(eraseButton);
		mainView.control.setHandlerForSeasonComboBox(selectSeason);
		mainView.control.setHandlerForPaintPlantButton(paintPlantsButton);

		holder.setStyle(allSeasonStyle);
		drawGardenToolBar = new ToolBar();
		HBox hb3 = new HBox();
		HBox hb2 = new HBox();
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		selectSeason.setMaxWidth(400);
		hb2.setPadding(new Insets(5, 5, 5, 5));
		hb2.setSpacing(4);
		hb2.getChildren().addAll(drawButton, eraseButton, paintPlantsButton);
		hb3.setPadding(new Insets(5, 5, 5, 0));
		hb3.getChildren().addAll(selectSeason);
		
		gardenDimLabel = new Label();
		gardenDimLabel.setLayoutX(0);
		gardenDimLabel.setLayoutY(0);
		gardenDimLabel.setFont(new Font(14));
		gardenDimLabel.setPadding(new Insets(0, 0, 0, 480));
		drawGardenToolBar.getItems().addAll(hb3, hb2, gardenDimLabel);
				
		drawGardenBorder = new BorderPane();
		drawGardenBorder.setMinWidth(DRAW_GARDENPANE_WIDTH);
		drawGardenBorder.setMinHeight(DRAW_GARDENPANE_HEIGHT);
		drawGardenBorder.setCenter(holder);
		drawGardenBorder.setTop(drawGardenToolBar);
	}
/**
 * Setter for holder(BorderPane) background based on season.
 * @param season Enum for season.
 */
	public void setSeason(Season season) {
		switch (season) {
		case SPRING:
			holder.setStyle(springStyle);
			break;
		case SUMMER:
			holder.setStyle(summerStyle);
			break;
		case AUTUMN:
			holder.setStyle(fallStyle);
			break;
		case WINTER:
			holder.setStyle(winterStyle);
			break;
		default:
			holder.setStyle(allSeasonStyle);
			break;
		}
	}
/**
 * Setter for ComboBox based on season.
 * @param season Enum for season.
 */
	public void setSeasonComboBox(Season season) {
		switch (season) {
		case SPRING:
			selectSeason.getSelectionModel().select("Spring");
			break;
		case SUMMER:
			selectSeason.getSelectionModel().select("Summer");
			break;
		case AUTUMN:
			selectSeason.getSelectionModel().select("Autumn");
			break;
		case WINTER:
			selectSeason.getSelectionModel().select("Winter");
			break;
		default:
			selectSeason.getSelectionModel().select("All Seasons");
			break;
		}
	}
/**
 * Sets the scale of the grid based on width set by user.
 * @param width Width set by user.
 */
	public void setLines(int width, int height) {
		holder.getChildren().clear();
		holder.getChildren().add(drawGardenCanvas);
		if (width > height) {
		GRID_INCREMENT = DRAW_GARDENPANE_WIDTH / width;
		}
		else {
			GRID_INCREMENT = DRAW_GARDENPANE_HEIGHT / height;
		}
		for (int i = 0; i < DRAW_GARDENPANE_WIDTH; i += GRID_INCREMENT) {
			Line vertical = new Line(i, 0, i, DRAW_GARDENPANE_WIDTH);
			vertical.setStroke(Color.DARKGRAY);
			vertical.setStrokeWidth(0.8);
			Line horizontal = new Line(0, i, DRAW_GARDENPANE_HEIGHT, i);
			horizontal.setStroke(Color.DARKGRAY);
			horizontal.setStrokeWidth(0.8);
			holder.getChildren().add(vertical);
			holder.getChildren().add(horizontal);
		}
	}
	
/**
 * Sets the GardenDimensions Label.
 * @param width Width set by user.
 * @param height Height set by user.
 */
	public void setGardenDim(int width) {
		gardenDimLabel.setText("Dimensions: " + width + "ft x " + width + "ft");
	}
/**
 * Getter for drawGardenBorder.
 * @return drawGardenBorder
 */
	public BorderPane getDrawGardenBorder() {
		return drawGardenBorder;
	}
/**
 * Getter for drawGardenCanvas.
 * @return drawGardenCanvas
 */
	public Canvas getDrawGardenCanvas() {
		return drawGardenCanvas;
	}
/**
 * Getter for eraseButton.
 * @return eraseButton
 */
	public ToggleButton getEraseButton() {
		return eraseButton;
	}
/**
 * Getter for drawButton.
 * @return drawButton
 */
	public ToggleButton getDrawButton() {
		return drawButton;
	}

/**
 * Getter for the holder BorderPane.
 * @return holder
 */
	public BorderPane getHolder() {
		return holder;
	}
	
	public int getGardenPaneWidth() {
		return DRAW_GARDENPANE_WIDTH;
	}
	
	public int getGardenPaneHeight() {
		return DRAW_GARDENPANE_HEIGHT;
	}
}
