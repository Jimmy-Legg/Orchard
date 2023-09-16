package orchard.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import orchard.model.Player;
import orchard.model.enums.OrchardColor;

public class LobbyPlayerProfil extends VBox {
	
	private Label nameLabel;
	private ImageView redCross;
	private VBox topBox;
	private VBox topBox1;
	private BorderPane borderPane;
	
	private static final Font NAME_LABEL_FONT = new Font("Arial", 30);
	
	public LobbyPlayerProfil(Player player) {
		super();
		
		this.setBackground(new Background(new BackgroundFill(OrchardColor.BEIGE.color(), new CornerRadii(10), Insets.EMPTY)));

		this.setPrefWidth(100);
		this.setPrefHeight(150);
		
		this.borderPane = new BorderPane();
		this.topBox = new VBox();
		this.topBox1 = new VBox();
		
		this.nameLabel = new Label(player.getPlayerData().getName());
		this.nameLabel.setFont(NAME_LABEL_FONT);
		this.nameLabel.setTextFill(OrchardColor.LIGHT_GREEN.color());
		this.nameLabel.setPadding(new Insets(0,0,0,50));
		this.redCross = new ImageView(new Image(getClass().getResourceAsStream("/redCrossIcon.png")));
		this.redCross.setFitWidth(50);
		this.redCross.setFitHeight(50);
		
		this.topBox.getChildren().add(nameLabel);
		this.topBox1.getChildren().add(redCross);

		this.topBox.setAlignment(Pos.TOP_LEFT);
		this.topBox1.setAlignment(Pos.TOP_RIGHT);
	   
		this.borderPane.setRight(topBox1);
		this.borderPane.setLeft(topBox);
	    this.borderPane.setPadding(new Insets(5,5,0,0));

		this.getChildren().addAll(borderPane);
	}

	public ImageView getRedCross() {
		return this.redCross;
	}
}
