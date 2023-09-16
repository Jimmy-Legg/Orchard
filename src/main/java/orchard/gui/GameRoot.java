package orchard.gui;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import orchard.model.Basket;
import orchard.model.Player;
import orchard.model.enums.OrchardColor;
import orchard.model.tree.AppleTree;
import orchard.model.tree.CherryTree;
import orchard.model.tree.PearTree;
import orchard.model.tree.PlumTree;
import orchard.ui.BasketUI;
import orchard.ui.DiceUI;
import orchard.ui.GameOverPanel;
import orchard.ui.JigsawDeck;
import orchard.ui.OrchardBoard;
import orchard.ui.TreeUI;

public class GameRoot extends StackPane{

	private static final int GAME_OVER_PANEL_MARGING = 25;
	private static final Font TURN_FONT_TEXT = Font.font("Arial", FontWeight.BOLD, 20);
	
	private BorderPane gameInterface;
	
	private StackPane centerArea;
	private DiceUI diceUI;
	private OrchardBoard orchardBoard;
	
	private BasketUI[] basketsUI;
	
	private VBox leftBox;
	private Label turnText;

	private JigsawDeck piecesDeck;
	
	private GameOverPanel gameOverPanel;
	
	public GameRoot(List<Player> players) {
		super();
		
		this.gameInterface = new BorderPane();
		this.gameInterface.setBackground(new Background(new BackgroundFill(OrchardColor.BEIGE.color(), CornerRadii.EMPTY, Insets.EMPTY)));
		
		this.basketsUI = new BasketUI[] {
				new BasketUI(new Basket()),
				new BasketUI(new Basket()),
				new BasketUI(new Basket()),
				new BasketUI(new Basket())
		};
		
		this.orchardBoard = new OrchardBoard(new TreeUI[] {	
			new TreeUI(new AppleTree()),
			new TreeUI(new PlumTree()),
			new TreeUI(new PearTree()),
			new TreeUI(new CherryTree())
		});
		
		for(int i = 0; i < this.orchardBoard.getTreeUIs().length; i++) {
			this.orchardBoard.getTreeUIs()[i].getTree().setBasket(this.basketsUI[i].getBasket());
		}
		
		this.centerArea = new StackPane();
		this.centerArea.getChildren().add(this.orchardBoard);
		
		this.gameInterface.setCenter(this.centerArea);

		this.leftBox = new VBox();
		this.leftBox.setAlignment(Pos.CENTER);
		this.leftBox.setBackground(new Background(new BackgroundFill(OrchardColor.DARK_GREEN.color(), CornerRadii.EMPTY, Insets.EMPTY)));
		
		this.turnText = new Label();
		this.turnText.setFont(TURN_FONT_TEXT);
		this.turnText.setAlignment(Pos.CENTER);
		this.turnText.setTextAlignment(TextAlignment.CENTER);
		this.turnText.setPrefWidth(Double.MAX_VALUE);
		this.turnText.setPrefHeight(100);
		this.turnText.setTextFill(OrchardColor.BEIGE.color());
		this.setTurnText("Tour : 1", "A " + players.get(0).getPlayerData().getName() + " de jouer !");
		this.leftBox.getChildren().add(this.turnText);
		
		for(BasketUI gamePlayerProfil : this.basketsUI) {
			this.leftBox.getChildren().add(gamePlayerProfil);
		}
		this.gameInterface.setLeft(this.leftBox);
		
		this.piecesDeck = new JigsawDeck();
		this.piecesDeck.refill();
		
		this.gameInterface.setRight(this.piecesDeck);
		
		this.gameOverPanel = new GameOverPanel();
		this.gameOverPanel.setVisible(false);
		StackPane.setMargin(this.gameOverPanel, new Insets(GAME_OVER_PANEL_MARGING));
		
		this.getChildren().addAll(this.gameInterface, this.gameOverPanel);
		
		this.resizeLeftBox();
		this.resizeCenterArea();
		this.resizeJigsawDeck();
		
		this.widthProperty().addListener(new WidthChangedListener());
		this.heightProperty().addListener(new HeightChangedListener());
	}
	
	class WidthChangedListener implements ChangeListener<Number> {
		
		@Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
            if (newSceneWidth.floatValue()!=oldSceneWidth.floatValue()) {
            	resizeCenterArea();
            	resizeLeftBox();
            }
        }
	}
	
	class HeightChangedListener implements ChangeListener<Number> {
		
		@Override 
	    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
	    	if (newSceneHeight.floatValue()!=oldSceneHeight.floatValue()) {
	    		resizeCenterArea();
	    		resizeLeftBox();
	    		resizeJigsawDeck();
	    	}
	    }
	}
	
	public void setTurnText(String firstLineText, String secondLineText) {
		Platform.runLater(new ChangeTurnText(firstLineText, secondLineText));
	}
	
	class ChangeTurnText implements Runnable {
		
		private String firstLineText; 
		private String secondLineText;
		
		public ChangeTurnText(String firstLineText, String secondLineText) {
			super();
			this.firstLineText = firstLineText;
			this.secondLineText = secondLineText;
		}

		@Override
	    public void run() {
	    	turnText.setText(firstLineText + "\n" + secondLineText);	
	    }
	}
	
	public void resizeCenterArea() {
		this.orchardBoard.setSize((double) Math.round(Math.min(this.getWidth() * 3/4 - 100, this.getHeight() * 2/3)));
	}
	
	public void resizeLeftBox() {
		this.leftBox.setPrefWidth(this.getWidth() * 1/4);
		for(BasketUI basketUI : this.basketsUI) {
			basketUI.resize((double) Math.round(Math.min(this.getWidth()* 1/4L, (this.getHeight() - 200) * 2/5L)));
		}
	}
	
	public void resizeJigsawDeck() {
		this.piecesDeck.resize(this.getHeight());
	}
	
	public DiceUI getDiceUI() {
		return diceUI;
	}
	
	public GameOverPanel getGameOverPanel() {
		return gameOverPanel;
	}

	public void setDiceUI(DiceUI dice) {
		this.diceUI = dice;
		this.centerArea.getChildren().add(dice);
	}
	
	public JigsawDeck getDeckUI() {
		return piecesDeck;
	}
	
	public OrchardBoard getOrchardBoard() {
		return orchardBoard;
	}

	public BasketUI[] getBasketsUI() {
		return basketsUI;
	}
	
}
