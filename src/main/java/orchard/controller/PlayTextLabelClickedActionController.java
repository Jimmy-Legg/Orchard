package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import orchard.scene.SceneController;

public class PlayTextLabelClickedActionController implements EventHandler<MouseEvent>{

	private SceneController sceneController;
	
	@Override
	public void handle(MouseEvent event) {
		sceneController.showLobby();
	}

	public PlayTextLabelClickedActionController(SceneController sceneController) {
		super();
		this.sceneController = sceneController;
	}
}
