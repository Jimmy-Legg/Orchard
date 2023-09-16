package orchard.model;

import java.util.ArrayList;
import java.util.List;

import orchard.gui.GameRoot;
import orchard.model.enums.FruitType;

public class GameHandler {

	private int turn;
	private int playerToPlayIndex;

	private List<Player> players;

	private int fruitToTakeAmount;
	private List<FruitType> fruitToTakeType;
	
	private GameRoot gameRoot;

	public GameHandler(List<Player> players, GameRoot gameRoot) {
		this(players, gameRoot, 1, 0);
	}

	public GameHandler(List<Player> players, GameRoot gameRoot, int turn, int playerToPlayIndex) {
		super();
		this.players = players;
		this.gameRoot = gameRoot;
		this.turn = turn;
		this.playerToPlayIndex = playerToPlayIndex;
		this.fruitToTakeAmount = 0;
		this.fruitToTakeType = new ArrayList<>();
	}

	public void nextTurn() {
		this.turn++;
	}

	public void nextPlayer() {

		if (playerToPlayIndex < players.size() - 1)
			this.playerToPlayIndex++;
		else {
			this.playerToPlayIndex = 0;
			this.nextTurn();
		}

		gameRoot.getDiceUI().setVisible(true);
		gameRoot.getDiceUI().getDice().setClickable(true);

		gameRoot.setTurnText("Tour : " + turn,
				"A " + players.get(playerToPlayIndex).getPlayerData().getName() + " de jouer !");
	}

	public Boolean checkGameOver() {
		if (gameRoot.getOrchardBoard().areTreesEmpty() || gameRoot.getOrchardBoard().getJigsawUI().getJigsaw().isFull()) {
			gameRoot.getGameOverPanel().show(checkWin(), turn, players);
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean checkWin() {
		return gameRoot.getOrchardBoard().areTreesEmpty();
	}
	
	public void fruitCollected() {
		this.fruitToTakeAmount--;
		Boolean isGameOver = checkGameOver();
		if(this.fruitToTakeAmount <= 0) {
			this.fruitToTakeAmount = 0;
			this.fruitToTakeType = new ArrayList<>();
			if(!isGameOver) {
				nextPlayer();
			}
		}
	}
	
	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void setCollectState(List<FruitType> fruitType, int amount) {
		this.fruitToTakeType = fruitType;
		this.fruitToTakeAmount = amount;
	}
	
	public int getFruitToTakeAmount() {
		return fruitToTakeAmount;
	}

	public List<FruitType> getFruitsToTakeTypes() {
		return fruitToTakeType;
	}

	public Integer getPlayerToPlayIndex() {
		return playerToPlayIndex;
	}

	public Player getPlayerToPlay() {
		return players.get(playerToPlayIndex);
	}

}
