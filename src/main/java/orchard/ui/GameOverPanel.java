package orchard.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import orchard.model.Player;
import orchard.model.enums.OrchardColor;

public class GameOverPanel extends VBox{
	
	private static final String COPPERPLATE_GOTHIC_BOLD_FONT = "Copperplate Gothic Bold";
	
	private static final Background SIDE_BACKGROUND = new Background(new BackgroundFill(OrchardColor.LIGHT_GREEN.color(), new CornerRadii(25), Insets.EMPTY));
	private static final Background INNER_BACKGROUND = new Background(new BackgroundFill(OrchardColor.DARK_GREEN.color(), new CornerRadii(25), Insets.EMPTY));
	private static final Background RETURN_TO_MENU_BUTTON_BACKGROUND = new Background(new BackgroundFill(OrchardColor.BEIGE.color(), new CornerRadii(25), Insets.EMPTY));
	
	private static final String GAME_OVER_TITLE = "La partie est finie !";
	private static final String GAME_WIN_SUBTITLE = "Vous avez gagné, Bravo !";
	private static final String GAME_LOOSE_SUBTITLE = "Vous avez perdu ...";
	private static final String RETURN_TO_MENU_TEXT = "Retourner au menu";
	private static final String TURN_PLAYED_TEXT = "Tours joués : ";
	
	private static final Font TITLE_FONT = Font.font(COPPERPLATE_GOTHIC_BOLD_FONT, FontWeight.BOLD, 45);
	private static final Font SUBTITLE_FONT = Font.font(COPPERPLATE_GOTHIC_BOLD_FONT, FontWeight.BOLD, 30);
	private static final Font RETURN_TO_MENU_TEXT_FONT = Font.font(COPPERPLATE_GOTHIC_BOLD_FONT, FontWeight.BOLD, 42);
	
	private static final Integer RETURN_TO_MENU_BUTTON_SIZE = 700;
	private static final Integer BORDER = 12;
	private static final Integer SPACING = 20;
	
	private static final Insets BORDER_INSETS = new Insets(BORDER);
	private static final Insets RETURN_TO_MENU_TEXT_INSETS = new Insets(10);
	private static final Insets PADDING_INSETS = new Insets(15);
	
	private VBox innerBox;
	
	private Label gameOverTitle;
	private Label gameOverSubTitle;
	private Label turnLabel;
	
	private HBox returnToMenuButton;
	private Label returnToMenuText;
	
	private List<GameOverPlayerPanel> playerPanels;
	
	public GameOverPanel() {
		super();
		
		this.innerBox = new VBox();
		
		this.getChildren().add(innerBox);
		
		this.gameOverTitle = new Label(GAME_OVER_TITLE);
		this.gameOverTitle.setFont(TITLE_FONT);
		this.gameOverTitle.setPrefWidth(this.getPrefWidth());
		this.gameOverTitle.setTextFill(OrchardColor.BEIGE.color());
		
		this.gameOverSubTitle = new Label();
		this.gameOverSubTitle.setFont(SUBTITLE_FONT);
		this.gameOverSubTitle.setPrefWidth(this.getPrefWidth());
		
		this.turnLabel = new Label();
		this.turnLabel.setFont(SUBTITLE_FONT);
		this.turnLabel.setPrefWidth(this.getPrefWidth());
		this.turnLabel.setTextFill(OrchardColor.BEIGE.color());
		VBox.setMargin(turnLabel, new Insets(0, 0, 10, 0));
		
		this.returnToMenuText = new Label(RETURN_TO_MENU_TEXT);
		this.returnToMenuText.setFont(RETURN_TO_MENU_TEXT_FONT);
		this.returnToMenuText.setTextFill(OrchardColor.DARK_GREEN.color());
		
		this.returnToMenuButton = new HBox();
		this.returnToMenuButton.setBackground(RETURN_TO_MENU_BUTTON_BACKGROUND);
		
		this.returnToMenuButton.setAlignment(Pos.CENTER);
		this.returnToMenuButton.setMaxWidth(RETURN_TO_MENU_BUTTON_SIZE);
		this.returnToMenuButton.setPadding(RETURN_TO_MENU_TEXT_INSETS);
		this.returnToMenuButton.getChildren().add(returnToMenuText);
		
		this.playerPanels = new ArrayList<>();
		
		this.updateChildren();
		
		this.innerBox.setSpacing(SPACING);
		this.setAlignment(Pos.CENTER);
		this.innerBox.setAlignment(Pos.CENTER);
		this.setBackground(SIDE_BACKGROUND);
		this.innerBox.setBackground(INNER_BACKGROUND);
		this.setPadding(BORDER_INSETS);
		this.innerBox.setPadding(PADDING_INSETS);
		VBox.setVgrow(innerBox, Priority.ALWAYS);
	}
	
	public void setWin(Boolean win) {
		if(Boolean.TRUE.equals(win)) {
			this.gameOverSubTitle.setText(GAME_WIN_SUBTITLE);
			this.gameOverSubTitle.setTextFill(Color.GREEN);
		}
		else {
			this.gameOverSubTitle.setText(GAME_LOOSE_SUBTITLE);
			this.gameOverSubTitle.setTextFill(Color.RED);
		}
	}

	public void show(Boolean win, int turn, List<Player> players) {
		
		Platform.runLater(new ShowRunnable(win, turn, players));
		
	    this.setVisible(true);
	    
	    FadeTransition transition = new FadeTransition(Duration.millis(1500), this);
	    transition.setFromValue(0);
	    transition.setToValue(1.0);
	    transition.play();
	}

	private void updateChildren() {
		this.innerBox.getChildren().clear();
		this.innerBox.getChildren().addAll(gameOverTitle, gameOverSubTitle, turnLabel);
		this.innerBox.getChildren().addAll(playerPanels);
		this.innerBox.getChildren().add(returnToMenuButton);
	}

	public HBox getReturnToMenuButton() {
		return this.returnToMenuButton;
	}
	
	private class ShowRunnable implements Runnable{

		private Boolean win;
		private Integer turn;
		private List<Player> players;
		
		public ShowRunnable(Boolean win, Integer turn, List<Player> players) {
			super();
			this.win = win;
			this.turn = turn;
			this.players = players;
		}

		@Override
		public void run() {
			setWin(win);
			updatePlayers(players);
			updatePanel(turn);
			updateChildren();
		}
		
		void updatePanel(int turn) {
			turnLabel.setText(TURN_PLAYED_TEXT + turn);
		}
		
		void updatePlayers(List<Player> players) {
			
			List<Player> orderedPlayers = new ArrayList<>();
			Integer rank;
			
			orderedPlayers.addAll(players);
			Collections.sort(orderedPlayers);
			
			rank = 1;
			playerPanels.clear();
			for (int i = 0; i < orderedPlayers.size(); i++) {
				if(i >= 1 && orderedPlayers.get(i).compareTo(orderedPlayers.get(i - 1)) != 0)rank = i + 1;
				playerPanels.add(new GameOverPlayerPanel(orderedPlayers.get(i), rank));
			}
		}
		
	}
}
