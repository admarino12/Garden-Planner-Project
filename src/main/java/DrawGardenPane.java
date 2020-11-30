package src.main.java;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class DrawGardenPane {
	
	//DrawGardenPane ToolBar
	private ToolBar drawGardenToolBar;
	private ToggleButton eraseButton = new ToggleButton("Erase");
	private ToggleButton drawButton = new ToggleButton("Draw");
	
	//DrawGardenPane MainPane
	private StackPane holder;
	private BorderPane drawGardenBorder;
	private Canvas drawGardenCanvas;
	
	//DrawGardenPane Dimensions
	final private int DRAW_GARDENPANE_WIDTH = 1000;
	final private int DRAW_GARDENPANE_HEIGHT = 750;
	
	
	public DrawGardenPane(View mainView) {
		
		drawGardenCanvas = new Canvas(DRAW_GARDENPANE_WIDTH, DRAW_GARDENPANE_HEIGHT);
		drawGardenCanvas.minWidth(DRAW_GARDENPANE_WIDTH);
		drawGardenCanvas.minHeight(DRAW_GARDENPANE_HEIGHT);
		
		holder = new StackPane();
		holder.getChildren().add(drawGardenCanvas);
		holder.setStyle("-fx-background-color: #81EEA4;");
		
		drawGardenToolBar = new ToolBar();
		HBox hb2 = new HBox();
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		hb2.setPadding(new Insets(5, 10, 5, 830));
		hb2.getChildren().addAll(drawButton, separator, eraseButton);
		drawGardenToolBar.getItems().add(hb2);
		
		drawGardenBorder = new BorderPane();
		drawGardenBorder.setMinWidth(DRAW_GARDENPANE_WIDTH);
		drawGardenBorder.setMinHeight(DRAW_GARDENPANE_HEIGHT);
		drawGardenBorder.setCenter(holder);
		drawGardenBorder.setTop(drawGardenToolBar);
		
}
	
	public BorderPane getDrawGardenBorder() {
		return drawGardenBorder;
	}
	
	public Canvas getDrawGardenCanvas() {
		return drawGardenCanvas;
	}
	
	public ToggleButton getEraseButton() {
		return eraseButton;
	}
	
	public ToggleButton getDrawButton() {
		return drawButton;
	}
}
