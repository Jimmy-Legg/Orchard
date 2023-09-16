package orchard.scene;

import java.util.List;

import javafx.stage.Stage;
import orchard.model.Player;

public class SceneController {

	private Stage stage;
	
	public SceneController(Stage stage) {
		super();
		this.stage = stage;
	}
	public void showMenu() {
		stage.setScene(new MenuScene(this));
		stage.show();
	}
	
	public void showLobby() {
		stage.setScene(new LobbyScene(this));
		stage.show();
	}
	
	public void showGame(List<Player> players) {
		stage.setScene(new GameScene(players, this));
		stage.show();
	}

}
