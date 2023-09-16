package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import orchard.scene.LobbyScene;

public class LobbyDeletePlayerController implements EventHandler<MouseEvent> {
	
	private LobbyScene lobbyScene;
	private int index;
	
	@Override
	public void handle(MouseEvent event) {
		lobbyScene.deletePlayer(index);
	}

	public LobbyDeletePlayerController(LobbyScene lobbyScene, int index) {
		super();
		this.lobbyScene = lobbyScene;
		this.index = index;
	}
}
