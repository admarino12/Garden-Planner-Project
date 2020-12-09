package src.main.java;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class EncyclopediaPane {
	
	// EncyclopediaPane Dimensions
	final private int EncyclopediaPane_WIDTH = 800;
	final private int EncyclopediaPane_HEIGHT = 770 ;
	final View mainView;
	
	private TilePane imgContainer;
	private BorderPane borderPane; 

	public EncyclopediaPane(View mainView) {
		VBox vb = new VBox();
		
		this.mainView = mainView; 
		
		Text label = new Text("Plant Encyclopedia");
		label.setStyle("-fx-font-weight: bold");
		
		Text Info = new Text ("To use the Plant Encyclopedia, you can search using the left side search tool and "
				+ "click on the image to bring up the plant's page.");
		Text Info2 = new Text (" To go back to your garden click done. ");
		
		Button done = new Button ("Done");
		mainView.control.setHandlerForDonePlantEncycClicked(done);
		
		vb.getChildren().addAll(label, Info, Info2);
		vb.setAlignment(Pos.CENTER);
		
		borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color:#D1EBDC;-fx-border-color: black;-fx-border-width:2;-fx-border-radius:3;");
		borderPane.setCenter(vb);
		borderPane.setBottom(done);
		borderPane.setMinWidth(EncyclopediaPane_WIDTH);
		borderPane.setMinHeight(EncyclopediaPane_HEIGHT);
		borderPane.setMaxHeight(EncyclopediaPane_HEIGHT);
		borderPane.setAlignment(borderPane,Pos.TOP_CENTER);
		borderPane.setAlignment(done,Pos.BASELINE_CENTER);
		
		
	}
	
	public BorderPane getPane() {
		return borderPane; 
	}
	
	public void buildPlantPage(String name) {
		
		borderPane = new BorderPane();
		borderPane.setMinWidth(EncyclopediaPane_WIDTH);
		borderPane.setMinHeight(EncyclopediaPane_HEIGHT);
		imgContainer = new TilePane();
		
		//Create title for the page
		Label title = new Label("Encyclopedia");
		title.setStyle("-fx-font-weight: bold");
		title.setAlignment(Pos.CENTER);
		borderPane.setTop(title);
		
		
		//get plant information
		ImageView img = new ImageView();
		img = mainView.getPlantList().get(name);
		imgContainer.getChildren().add(img);
		
		
		Text commonName = new Text(name);
		
		Text description = new Text(mainView.control.getPlantDescription(name));
		
		//Create Vbox
				VBox vb = new VBox();
				vb.getChildren().addAll(imgContainer,commonName,description);
		
		for (String trait: mainView.control.getPlantTraits(name) ) {
			Text traits = new Text(trait);
			vb.getChildren().add(traits);
		}
		Button done = new Button ("Done");
		mainView.control.setHandlerForDonePlantEncycClicked(done);
		done.setAlignment(Pos.BOTTOM_RIGHT);
		borderPane.setBottom(done);
		
		borderPane.setCenter(vb);
		
		
	}
}
