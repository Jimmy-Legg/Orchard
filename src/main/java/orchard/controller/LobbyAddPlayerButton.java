package orchard.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import orchard.scene.LobbyScene;

public class LobbyAddPlayerButton implements EventHandler<ActionEvent> {
	
	private LobbyNewPlayerErrorMessageHandler lobbyNewPlayerErrorMessageHandler;
	
	public LobbyAddPlayerButton(LobbyScene lobbyScene, TextField namePlayerTextField, Label errorLabel) {
		super();
		this.lobbyNewPlayerErrorMessageHandler = new LobbyNewPlayerErrorMessageHandler(lobbyScene, namePlayerTextField, errorLabel);
	}

	@Override
	public void handle(ActionEvent event) {
		lobbyNewPlayerErrorMessageHandler.handle();
	}
}
