package orchard.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import orchard.model.Player;
import orchard.scene.LobbyScene;

public class LobbyNewPlayerErrorMessageHandler {

	private static final String PLAYER_EXIST_TEXT = "Le joueur existe déjà";
	private static final String NAME_TOO_SHORT_TEXT = "Nom de joueur trop court";
	private static final String NAME_TOO_LONG_TEXT = "Nom de joueur trop long";
	private static final String PLAYER_LIST_FULL = "La liste des joueurs est pleine";

	private static final Integer MAX_PLAYER = 3;

	private LobbyScene lobbyScene;
	private TextField namePlayerTextField;
	private Label errorLabel;
	
	public LobbyNewPlayerErrorMessageHandler(LobbyScene lobbyScene, TextField namePlayerTextField, Label errorLabel) {
		super();
		this.lobbyScene = lobbyScene;
		this.namePlayerTextField = namePlayerTextField;
		this.errorLabel = errorLabel;
	}

	public void handle() {
		if (!isPlayerListFull() && isTextValid() && !isPlayerExisting()) {
			lobbyScene.addPlayer(namePlayerTextField.getText());
			namePlayerTextField.setText("");
		}
	}

	private Boolean isPlayerExisting() {

		Boolean playerExist = false;

		for (Player player : lobbyScene.getPlayers()) {
			if (player.getPlayerData().getName().equals(namePlayerTextField.getText()))
				playerExist = true;
		}

		if (playerExist) {
			showErrorMessage(PLAYER_EXIST_TEXT);
			return true;
		} else {
			return false;
		}
	}

	private Boolean isPlayerListFull() {
		if (lobbyScene.getPlayers().size() >= MAX_PLAYER) {
			showErrorMessage(PLAYER_LIST_FULL);
			return true;
		} else {
			return false;
		}
	}

	private Boolean isTextValid() {

		if (namePlayerTextField.getText().length() <= 15) {
			if (namePlayerTextField.getText().length() >= 3) {
				return true;
			} else
				showErrorMessage(NAME_TOO_SHORT_TEXT);
			return false;
		} else
			showErrorMessage(NAME_TOO_LONG_TEXT);
		return false;
	}

	private void showErrorMessage(String text) {
		errorLabel.setText(text);
		errorLabel.setOpacity(1);
		errorLabel.setVisible(true);

		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(errorLabel.opacityProperty(), 1)),
				new KeyFrame(Duration.seconds(1), new KeyValue(errorLabel.opacityProperty(), 0)));
		timeline.setOnFinished(e -> errorLabel.setVisible(false));
		timeline.play();
	}
}
