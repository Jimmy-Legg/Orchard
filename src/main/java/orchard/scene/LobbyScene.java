package orchard.scene;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import orchard.controller.LobbyAddPlayerButton;
import orchard.controller.LobbyEnterKeyPressed;
import orchard.gui.LobbyRoot;
import orchard.model.Player;
import orchard.model.PlayerData;

public class LobbyScene extends Scene {

	private LobbyRoot root;
	private List<Player> players;

	public LobbyScene(SceneController sceneController) {
		super(new LobbyRoot(), 1800, 900);

		this.root = (LobbyRoot) this.getRoot();
		this.players = new ArrayList<>();

		this.root.getPlayTextLbl().setOnMouseClicked(new PlayTextEventHandler(sceneController));

		LobbyEnterKeyPressed lobbyEnterKeyPressed = new LobbyEnterKeyPressed(this, root.getPlayerNameField(), root.getErrorLabel());
		setOnKeyPressed(lobbyEnterKeyPressed);

		LobbyAddPlayerButton lobbyAddPlayerButton = new LobbyAddPlayerButton(this, root.getPlayerNameField(), root.getErrorLabel());
		this.root.getValidateNewPlayerButton().setOnAction(lobbyAddPlayerButton);
	}

	public void addPlayer(String name) {
		Player player = new Player(new PlayerData(name));
		Integer index = getPlayers().size();

		this.players.add(player);
		this.root.addPlayerProfil(player, index, this);
	}

	public void deletePlayer(int index) {
		this.players.remove(index);
		this.root.updatePlayerProfils(players, this);
	}

	private class PlayTextEventHandler implements EventHandler<MouseEvent> {
		
		private SceneController sceneController;
		
		public PlayTextEventHandler(SceneController sceneController) {
			super();
			this.sceneController = sceneController;
		}
		
		@Override
		public void handle(MouseEvent event) {
			if (!getPlayers().isEmpty())
				this.sceneController.showGame(getPlayers());
		}
	}
	
	public List<Player> getPlayers() {
		return players;
	}

}
