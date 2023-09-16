package orchard.gui;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import orchard.controller.LobbyDeletePlayerController;
import orchard.model.Player;
import orchard.scene.LobbyScene;
import orchard.ui.LobbyPlayerProfil;

public class LobbyRoot extends BorderPane {

	private StackPane stackPane;
	private VBox lobbyPlayerProfilsBox;
	private HBox topBar;
	private HBox bottomBar;
	private VBox centerBox;
 
	private TextField playerNameField;
	private Button validateNewPlayerButton;
	private Label errorLabel;
	private Label playTextLbl;

	private static final int MARGING = 15;

	private static final Font FONT = new Font("Copperplate Gothic Bold", 60);
	private static final Font FONT1 = new Font("Bauhaus 93", 60);
	
	private static final Color COLOR_BACK_GROUND = Color.web("#9DC08B");
	private static final Color COLOR_PLAY = Color.web("#40513B");
	private static final Color COLOR_RED = Color.web("#910a00");

	private static final String PLAY_TEXT = "Jouer !";
	private static final String ADD_PLAYER_TEXT = "Ajouter un joueur";
	private static final String PLAYER_NAME_TEXTFIELD_HINT_TEXT = "Entrez un nom de joueur ...";

	public LobbyRoot() {
		super();

		this.setBackground(new Background(new BackgroundFill(COLOR_BACK_GROUND, CornerRadii.EMPTY, Insets.EMPTY)));

		this.playTextLbl = new Label(PLAY_TEXT);
		this.playTextLbl.setFont(FONT);
		this.playTextLbl.setTextFill(COLOR_PLAY);
		this.playTextLbl.setAlignment(Pos.CENTER);
		this.playTextLbl.setPadding(new Insets(30));

		this.bottomBar = new HBox();
		this.bottomBar.setAlignment(Pos.CENTER);
		this.bottomBar.getChildren().add(playTextLbl);
		this.bottomBar.setPadding(new Insets(0, 0, 0, 0));
		this.setBottom(bottomBar);

		this.playerNameField = new TextField();
		this.playerNameField.setPrefHeight(35);
		this.playerNameField.setPromptText(PLAYER_NAME_TEXTFIELD_HINT_TEXT);

		this.validateNewPlayerButton = new Button(ADD_PLAYER_TEXT);
		this.validateNewPlayerButton.setPrefHeight(35);
		this.validateNewPlayerButton.setPrefWidth(35);

		this.topBar = new HBox();
		this.topBar.getChildren().addAll(playerNameField, validateNewPlayerButton);
		this.topBar.setPadding(new Insets(50, 50, 0, 50));
		this.setTop(topBar);

		this.centerBox = new VBox();
		this.centerBox.setAlignment(Pos.CENTER);
		this.errorLabel = new Label();
		this.errorLabel.setFont(FONT1);
		this.errorLabel.setTextFill(COLOR_RED);
		this.errorLabel.setVisible(false);

		this.lobbyPlayerProfilsBox = new VBox();
		this.lobbyPlayerProfilsBox.setAlignment(Pos.CENTER);
		this.lobbyPlayerProfilsBox.setPrefHeight(500);
		this.lobbyPlayerProfilsBox.setPrefWidth(800);
		this.lobbyPlayerProfilsBox.setMaxHeight(500);
		this.lobbyPlayerProfilsBox.setMaxWidth(1000);
		this.lobbyPlayerProfilsBox.setSpacing(5);

		this.stackPane = new StackPane();
		this.stackPane.getChildren().add(lobbyPlayerProfilsBox);
		this.stackPane.getChildren().add(errorLabel);
		BorderPane.setAlignment(stackPane, Pos.CENTER);
		
		this.setCenter(stackPane);
		
		this.widthProperty().addListener(new ChangeWidthListener());
		this.resize();
	}

	class ChangeWidthListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			resize();
		}
	}

	
	public void resize() {
		playerNameField.setPrefWidth(this.getWidth() * 70 / 100 - MARGING);
		validateNewPlayerButton.setPrefWidth(this.getWidth() * 30 / 100 - MARGING);
		playTextLbl.setPrefWidth(this.getWidth());
	}
	
	public void addPlayerProfil(Player player, int index, LobbyScene lobbyScene) {
		if (lobbyPlayerProfilsBox.getChildren().size() <= index) {
			
			LobbyPlayerProfil lobbyPlayerProfil = new LobbyPlayerProfil(player);
			
			if (lobbyPlayerProfilsBox.getChildren().size() < index)
				lobbyPlayerProfilsBox.getChildren().set(index, lobbyPlayerProfil);
			else
				lobbyPlayerProfilsBox.getChildren().add(lobbyPlayerProfil);
			
			LobbyDeletePlayerController lobbyDeletPlayerController = new LobbyDeletePlayerController(lobbyScene, index);
			lobbyPlayerProfil.getRedCross().setOnMouseClicked(lobbyDeletPlayerController);
		}
	}
	
	public void updatePlayerProfils(List<Player> players, LobbyScene lobbyScene) {
		
		lobbyPlayerProfilsBox.getChildren().clear();
		
		for (int i = 0; i < players.size(); i++) {
			
			LobbyPlayerProfil lobbyPlayerProfil = new LobbyPlayerProfil(players.get(i));
			lobbyPlayerProfilsBox.getChildren().add(lobbyPlayerProfil);
			
			LobbyDeletePlayerController lobbyDeletPlayerController = new LobbyDeletePlayerController(lobbyScene, i);
			lobbyPlayerProfil.getRedCross().setOnMouseClicked(lobbyDeletPlayerController);
		}
	}
	
	public Label getErrorLabel() {
		return errorLabel;
	}

	public Label getPlayTextLbl() {
		return playTextLbl;
	}

	public TextField getPlayerNameField() {
		return playerNameField;
	}

	public Button getValidateNewPlayerButton() {
		return validateNewPlayerButton;
	}
	
	public VBox getLobbyPlayerProfilsPane() {
		return lobbyPlayerProfilsBox;
	}
}
