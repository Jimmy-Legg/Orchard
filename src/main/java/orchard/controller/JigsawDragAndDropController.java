package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import orchard.model.Jigsaw;
import orchard.model.enums.JigsawPieceType;
import orchard.ui.JigsawUI;

public class JigsawDragAndDropController {

	public static void setupDragAndDropBehavior(JigsawUI jigsawUI) {
		jigsawUI.setOnDragOver(new JigsawDragOverController());
		jigsawUI.setOnDragDropped(new JigsawDragDroppedController(jigsawUI.getJigsaw()));
		jigsawUI.setOnDragExited(new JigsawDragExitedController(jigsawUI));
		jigsawUI.setOnDragEntered(new JigsawDragEnteredController(jigsawUI));
	}
	
	static class JigsawDragDroppedController implements EventHandler<DragEvent>{

		private Jigsaw jigsaw;
		
		public JigsawDragDroppedController(Jigsaw jigsaw) {
			super();
			this.jigsaw = jigsaw;
		}

		@Override
		public void handle(DragEvent event) {
			Boolean success = false;
	      
			try {
				if(event.getSource() instanceof JigsawUI && event.getDragboard().getContent(JigsawPieceType.getDataFormat()) != null) {
					JigsawPieceType jigsawPieceDropped = (JigsawPieceType)event.getDragboard().getContent(JigsawPieceType.getDataFormat());
					jigsaw.addPiece(jigsawPieceDropped.getIndex());
					success = true;
				}
	          
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	      
			event.setDropCompleted(success);
			event.consume();
		}
	}
	
	static class JigsawDragEnteredController implements EventHandler<DragEvent>{

		private JigsawUI jigsawUI;
		
		public JigsawDragEnteredController(JigsawUI jigsawUI) {
			super();
			this.jigsawUI = jigsawUI;
		}

		@Override
		public void handle(DragEvent event) {
			
			if(event.getDragboard().hasImage() && event.getDragboard().getContent(JigsawPieceType.getDataFormat()) != null) {
	        	JigsawPieceType jigsawPieceDropped = (JigsawPieceType)event.getDragboard().getContent(JigsawPieceType.getDataFormat());
	        	jigsawUI.showPiece(jigsawPieceDropped.getIndex());
	        }
	        
			event.consume();
		}
	}
	
	static class JigsawDragExitedController implements EventHandler<DragEvent>{

		private JigsawUI jigsawUI;
		
		public JigsawDragExitedController(JigsawUI jigsawUI) {
			super();
			this.jigsawUI = jigsawUI;
		}

		@Override
		public void handle(DragEvent event) {
			if(event.getDragboard().hasImage() && event.getDragboard().getContent(JigsawPieceType.getDataFormat()) != null) {
	        	JigsawPieceType jigsawPieceDropped = (JigsawPieceType)event.getDragboard().getContent(JigsawPieceType.getDataFormat());
	        	jigsawUI.unShowPiece(jigsawPieceDropped.getIndex());
	        }
	    	
	        event.consume();
		}
	}
	
	static class JigsawDragOverController  implements EventHandler<DragEvent>{

		@Override
		public void handle(DragEvent event) {
			if (event.getDragboard().hasImage() && event.getDragboard().getContent(JigsawPieceType.getDataFormat()) != null) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			event.consume();
		}
	}
}
