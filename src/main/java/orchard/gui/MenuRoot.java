package orchard.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuRoot extends StackPane{
	
	private GridPane gpane;
	
	private Label playTextLbl;
	private Label quitTextLbl;
	
	private Image image;
	
	private ImageView playArrowImageView;
	private ImageView quitArrowImageView;
	
	private VBox playBox;
	private VBox quitBox;
	
	private static final Font FONT = new Font("Copperplate Gothic Bold", 60);
	private static final Color COLOR_BLACK = Color.web("#000000");
	
	private static final String PLAY_TEXT = "Play";
	private static final String QUIT_TEXT = "Quit";
	
	public MenuRoot() {
		super();
		
		this.setBackground(new Background(new BackgroundFill(Color.web("#9DC08B"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		this.playTextLbl = new Label(PLAY_TEXT);
		this.quitTextLbl = new Label(QUIT_TEXT);
        
		this.image = new Image(getClass().getResourceAsStream("/arrow.png"));
		
		this.playArrowImageView = new ImageView(image);
		this.quitArrowImageView = new ImageView(image);
        
		this.playBox = new VBox();
		this.quitBox = new VBox();
		
		this.playBox.getChildren().add(playArrowImageView);
		this.quitBox.getChildren().add(quitArrowImageView);
		
		this.gpane = new GridPane();
		this.gpane.setAlignment(Pos.CENTER_LEFT);
        this.gpane.add(playBox, 2, 2);
        this.gpane.add(quitBox, 2, 4);
        this.gpane.add(playArrowImageView, 0,2);
        this.gpane.add(quitArrowImageView, 0, 4);
        
        this.playArrowImageView.setX(40);
        this.playArrowImageView.setY(40);
        this.playArrowImageView.setFitWidth(50);
        this.playArrowImageView.setFitHeight(50);
        this.playArrowImageView.setImage(null);
        
        this.quitArrowImageView.setX(40);
        this.quitArrowImageView.setY(40);
        this.quitArrowImageView.setFitWidth(50);
        this.quitArrowImageView.setFitHeight(50);
        this.quitArrowImageView.setImage(null);
        
        this.playBox.getChildren().add(playTextLbl);
        this.quitBox.getChildren().add(quitTextLbl);
        
        this.playBox.setAlignment(Pos.CENTER_LEFT);
        this.quitBox.setAlignment(Pos.CENTER_LEFT);
        
        this.playTextLbl.setFont(FONT);
        this.playTextLbl.setTextFill(COLOR_BLACK);
        this.quitTextLbl.setFont(FONT);
        this.quitTextLbl.setTextFill(COLOR_BLACK);
        
        this.getChildren().add(gpane);
        
	}
	public Label getPlayTextLbl() {
		return playTextLbl;
	}
	public Label getQuitTextLbl() {
		return quitTextLbl;
	}
	public ImageView getImageView() {
		return playArrowImageView;
	}
	public ImageView getImageView2() {
		return quitArrowImageView;
	}
	public Image getImage() {
		return image;
	}
}
