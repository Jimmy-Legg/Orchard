package orchard.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import orchard.model.enums.JigsawPieceType;

public class JigsawPiece extends ImageView {
	
	private Boolean isDraggable = false;
	private final JigsawPieceType pieceType;
	
	public JigsawPiece(JigsawPieceType pieceType) throws IllegalArgumentException{
		if(pieceType != null) {
			this.pieceType = pieceType;
			this.setImage(new Image(pieceType.getImageURL(), 100, 100, true, true));
		} else throw new IllegalArgumentException("Piece type cannot be null");
	}
	
	public void setIsDraggable(Boolean isDeckDraggable) {
		this.isDraggable = isDeckDraggable;
	}
	
	public Boolean isDraggable() {
		return isDraggable;
	}

	public JigsawPieceType getPieceType() {
		return pieceType;
	}
	
	public void resize(Double width) {
		this.setFitHeight(width);
		this.setFitWidth(width);
	}
	
	
}
