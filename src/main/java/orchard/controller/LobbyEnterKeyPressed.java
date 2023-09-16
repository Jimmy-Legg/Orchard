package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import orchard.scene.LobbyScene;

public class LobbyEnterKeyPressed implements EventHandler<KeyEvent> {
	
	private LobbyNewPlayerErrorMessageHandler lobbyNewPlayerErrorMessageHandler;
	
	public LobbyEnterKeyPressed(LobbyScene lobbyScene, TextField namePlayerTextField, Label errorLabel) {
		super();
		this.lobbyNewPlayerErrorMessageHandler = new LobbyNewPlayerErrorMessageHandler(lobbyScene, namePlayerTextField, errorLabel);
	}

	@Override
	public void handle(KeyEvent ke) {
		if (ke.getCode().equals(KeyCode.ENTER)) {
			lobbyNewPlayerErrorMessageHandler.handle();
		}
	}
}
