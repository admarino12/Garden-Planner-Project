package src.main.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class StartPageView {
	private Scene introScene;
	private View mainView;
	
	private String FRONT_PAGE_IMG = "src/resources/images/Front_Page.png";
	
	public StartPageView(View view) {
			mainView = view;	
			//Front Page View
			VBox layout = new VBox(10);
			      
			//New Garden button to get to garden view
			Button startBtn = new Button("Start New Garden"); 
			startBtn.setMaxSize(200, 200);
			startBtn.setStyle("-fx-text-alignment: center");
			startBtn.setStyle("-fx-background-color: #D1EBDC");
			mainView.control.setHandlerForNewFilePopUpClicked(startBtn);
			
			//Upload Garden button
			Button uploadBtn = new Button ("Upload Garden");
			uploadBtn.setMaxSize(200, 200);
			uploadBtn.setStyle("-fx-text-alignment: center");
			uploadBtn.setStyle("-fx-background-color: #D1EBDC");
			view.control.setHandlerForOpenPopUpClicked(uploadBtn); //close pop up on click
			
			//import background image
			Image image;
			try {
				image = new Image(new FileInputStream(FRONT_PAGE_IMG));
				BackgroundImage bg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null);
				Background background = new Background(bg);
				
				layout.setBackground(background);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			//setting up layout of Front Page
			layout.setPadding(new Insets(220,10,10,10));
			layout.getChildren().addAll(startBtn, uploadBtn);
			layout.setAlignment(Pos.CENTER);
			
			//set Front Page scene
			introScene= new Scene(layout,view.getROOT_WIDTH(), view.getROOT_HEIGHT()); 
			introScene.setCursor(Cursor.HAND);
			view.getStage().setScene(introScene);
	}
	
	public Scene getScene() {
		return introScene;
	}
	
}
