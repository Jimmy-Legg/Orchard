package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import orchard.model.GameHandler;
import orchard.model.enums.JigsawPieceType;
import orchard.ui.JigsawDeck;
import orchard.ui.JigsawPiece;

public class JigsawPieceDragAndDropController {

	public static void setupJigsawPieceDragAndDropController(JigsawPiece jigsawPiece, GameHandler gameHandler,
			JigsawDeck jigsawDeck) {
		jigsawPiece.setOnDragDetected(new JigsawPieceDragDetectedController());
		jigsawPiece.setOnDragDone(new JigsawPieceDragDoneController(jigsawDeck, gameHandler));
	}

	static class JigsawPieceDragDetectedController implements EventHandler<MouseEvent> {

		public void handle(MouseEvent event) {

			if (event.getSource() instanceof JigsawPiece) {

				JigsawPiece source = (JigsawPiece) event.getSource();
				if (source.isDraggable()) {

					Dragboard db = ((ImageView) event.getSource()).startDragAndDrop(TransferMode.ANY);
					ClipboardContent content = new ClipboardContent();

					content.putImage(((ImageView) event.getSource()).getImage());
					content.put(JigsawPieceType.getDataFormat(), source.getPieceType());

					db.setContent(content);
				}
				event.consume();
			}
		}
	}

	static class JigsawPieceDragDoneController implements EventHandler<DragEvent> {

		private JigsawDeck jigsawDeckUI;
		private GameHandler gameHandler;

		public JigsawPieceDragDoneController(JigsawDeck jigsawDeckUI, GameHandler gameHandler) {
			super();
			this.jigsawDeckUI = jigsawDeckUI;
			this.gameHandler = gameHandler;
		}

		public void handle(DragEvent event) {

			if (event.getSource() instanceof JigsawPiece) {

				JigsawPiece source = (JigsawPiece) event.getSource();

				if (event.getTransferMode() == TransferMode.MOVE) {

					jigsawDeckUI.getChildren().remove(source);

					Thread dragDoneThread = new Thread(new DragDoneRunnable());
					dragDoneThread.start();
				}
			}
			event.consume();
		}

		class DragDoneRunnable implements Runnable {

			@Override
			public void run() {
				try {
					Thread.sleep(500);
					jigsawDeckUI.hide();
					gameHandler.getPlayerToPlay().incrementRavenPieceAmount();
					if (!gameHandler.checkGameOver())
						gameHandler.nextPlayer();
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}
