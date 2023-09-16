package orchard.scene;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import orchard.controller.BasketContentController;
import orchard.controller.BasketDragAndDropController;
import orchard.controller.DiceController;
import orchard.controller.FruitDragAndDropController;
import orchard.controller.JigsawDragAndDropController;
import orchard.controller.JigsawPieceDragAndDropController;
import orchard.controller.ReturnToMenuButtonOnClickController;
import orchard.exceptions.TreeException;
import orchard.gui.GameRoot;
import orchard.model.Dice;
import orchard.model.GameHandler;
import orchard.model.Player;
import orchard.model.dice_face.BasketFace;
import orchard.model.dice_face.DiceFace;
import orchard.model.dice_face.FruitFace;
import orchard.model.dice_face.RavenFace;
import orchard.ui.BasketUI;
import orchard.ui.DiceUI;
import orchard.ui.FruitUI;
import orchard.ui.JigsawPiece;
import orchard.ui.TreeUI;

public class GameScene extends Scene{
	
	public GameScene(List<Player> players, SceneController sceneController) {
		super(new GameRoot(players));
		
		GameRoot root = (GameRoot) this.getRoot();
		
		GameHandler gameHandler = new GameHandler(players, root);
		
		setupDice(root, gameHandler);
		refillTrees(root);
		setupControllers(root, gameHandler);
		setupReturnToMenuButtonController(sceneController, root);
	}

	private void setupControllers(GameRoot root, GameHandler gameHandler) {
		setupFruitsControlers(root, gameHandler);
		setupBasketsControllers(root);
		setupJigsawPiecesControllers(root, gameHandler);
		setupJigsawController(root);
	}

	private void setupReturnToMenuButtonController(SceneController sceneController, GameRoot root) {
		ReturnToMenuButtonOnClickController returnToMenuButtonOnClickController = new ReturnToMenuButtonOnClickController(sceneController);
		root.getGameOverPanel().getReturnToMenuButton().setOnMouseClicked(returnToMenuButtonOnClickController);
	}

	private void setupJigsawController(GameRoot root) {
		JigsawDragAndDropController.setupDragAndDropBehavior(root.getOrchardBoard().getJigsawUI());
	}

	private void setupJigsawPiecesControllers(GameRoot root, GameHandler gameHandler) {
		for(JigsawPiece piece : root.getDeckUI().getPieces()) {
			JigsawPieceDragAndDropController.setupJigsawPieceDragAndDropController(piece, gameHandler, root.getDeckUI());
		}
	}

	private void setupBasketsControllers(GameRoot root) {
		for(BasketUI basketUI : root.getBasketsUI()) {
			BasketContentController.setupBasketContentBehavior(basketUI);
			BasketDragAndDropController.setupBasketDragAndDropBehavior(basketUI);
		}
	}

	private void setupFruitsControlers(GameRoot root, GameHandler gameHandler) {
		for(TreeUI treeUI : root.getOrchardBoard().getTreeUIs()) {
			for(FruitUI fruitUI : treeUI.getFruitUIs()) {
				FruitDragAndDropController.setupFruitDragAndDropController(fruitUI, treeUI.getTree(), gameHandler);
			}
		}
	}

	private void refillTrees(GameRoot root) {
		for(int i = 0; i < root.getOrchardBoard().getTreeUIs().length; i++) {
			try {
				root.getOrchardBoard().getTreeUIs()[i].getTree().refill();
			} catch (TreeException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setupDice(GameRoot root, GameHandler gameHandler) {
		List<DiceFace> diceFaces = new ArrayList<>();
		for(int i = 0; i < 4; i++) {
			diceFaces.add(new FruitFace(root.getOrchardBoard().getTreeUIs()[i].getTree(), "/dice" + i + ".png", gameHandler));
		}
		diceFaces.add(new RavenFace(root.getDeckUI()));
		diceFaces.add(new BasketFace(gameHandler));
		
		Dice dice = new Dice(diceFaces);
		root.setDiceUI(new DiceUI(dice));
		
		DiceController diceController = new DiceController(root.getDiceUI());
		root.getDiceUI().setOnMouseClicked(diceController);
	}
}
