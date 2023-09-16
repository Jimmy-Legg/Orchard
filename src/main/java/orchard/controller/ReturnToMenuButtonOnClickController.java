package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import orchard.scene.SceneController;

public class ReturnToMenuButtonOnClickController implements EventHandler<MouseEvent>{

	private SceneController sceneController;

	public ReturnToMenuButtonOnClickController(SceneController sceneController) {
		super();
		this.sceneController = sceneController;
	}

	@Override
	public void handle(MouseEvent event) {
		sceneController.showMenu();
	}
}
