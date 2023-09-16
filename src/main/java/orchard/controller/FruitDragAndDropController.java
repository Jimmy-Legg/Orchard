package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import orchard.model.GameHandler;
import orchard.model.enums.FruitType;
import orchard.model.tree.Tree;
import orchard.ui.FruitUI;

public class FruitDragAndDropController {

	public static void setupFruitDragAndDropController(FruitUI fruitUI, Tree tree, GameHandler gameHandler) {
		fruitUI.setOnDragDetected(new FruitDragDetectedController(gameHandler));
		fruitUI.setOnDragDone(new FruitDragDoneController(tree, gameHandler));
	}
	
	static class FruitDragDetectedController implements EventHandler<MouseEvent> {

		private GameHandler gameHandler;

		public FruitDragDetectedController(GameHandler gameHandler) {
			super();
			this.gameHandler = gameHandler;
		}

		@Override
		public void handle(MouseEvent event) {

			FruitUI source;

			if (event.getSource() instanceof FruitUI) {

				source = (FruitUI) event.getSource();
				
				if (gameHandler.getFruitsToTakeTypes() != null
						&& gameHandler.getFruitsToTakeTypes().contains(source.getFruit().getFruitType())
						&& gameHandler.getFruitToTakeAmount() > 0) {

					source.setVisible(false);

					Dragboard db = source.startDragAndDrop(TransferMode.ANY);
					ClipboardContent content = new ClipboardContent();

					content.putImage(new Image(source.getFruit().getFruitType().getImageURL(), 100, 100, false, false));
					content.put(FruitType.getDataFormat(), source.getFruit().getFruitType());

					db.setContent(content);
				}
				event.consume();
			}
		}
	}
	
	static class FruitDragDoneController implements EventHandler<DragEvent> {

		private Tree tree;
		private GameHandler gameHandler;

		public FruitDragDoneController(Tree tree, GameHandler gameHandler) {
			super();
			this.tree = tree;
			this.gameHandler = gameHandler;
		}
		
		public void handle(DragEvent event) {

			FruitUI source;

			if (event.getSource() instanceof FruitUI) {
				source = (FruitUI) event.getSource();

				if(event.getTransferMode() == TransferMode.MOVE) {
					tree.getFruits().set(source.getFruit().getIndex(), null);
					gameHandler.getPlayerToPlay().addFruit(source.getFruit().getFruitType());
					gameHandler.fruitCollected();
				}
				else {
					source.setVisible(true);
				}
				event.consume();
			}
		}
	}
}
