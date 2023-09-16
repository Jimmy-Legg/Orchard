package orchard.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import orchard.model.Jigsaw;

public class JigsawUI extends StackPane {

	private List<ImageView> pieces;

	private Rectangle background;
	private ImageView backgroundImage;

	private Jigsaw jigsaw;

	private Integer[] pieceX = new Integer[] { 0, -1, 1, -2, 0, 2, -1, 1, 0 };

	private Integer[] pieceY = new Integer[] { -2, -1, -1, 0, 0, 0, 1, 1, 2 };

	private static final Color JIGSAW_BACKGROUND_COLOR = Color.CHOCOLATE;

	public JigsawUI(Jigsaw jigsaw, int pieceSize, int margin) {
		this(jigsaw);
		resize(pieceSize, margin);
	}

	public JigsawUI(Jigsaw jigsaw) {

		this.background = new Rectangle();
		this.background.setFill(JIGSAW_BACKGROUND_COLOR);
		this.background.setRotate(45);

		this.backgroundImage = new ImageView(new Image(getClass().getResourceAsStream("/jigsawBackground.png")));
		this.backgroundImage.setOpacity(0.4);

		try {
			setJigsaw(jigsaw);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		this.getChildren().addAll(background, backgroundImage);
		this.getChildren().addAll(pieces);

	}

	private class OnJigsawListChanged implements ListChangeListener<Integer> {

		@Override
		public void onChanged(Change<? extends Integer> changed) {
			while (changed.next()) {
				hideRemovedPieceID(changed);
				showAddedPieceID(changed);
			}
		}

		private void hideRemovedPieceID(Change<? extends Integer> changed) {
			if (changed.wasRemoved()) {
				for (Integer pieceIndex : changed.getRemoved()) {
					if (pieceIndex != null) {
						pieces.get(pieceIndex).setVisible(true);
						pieces.get(pieceIndex).setOpacity(1);
					}
				}
			}
		}

		private void showAddedPieceID(Change<? extends Integer> changed) {
			if (changed.wasAdded()) {
				for (Integer pieceIndex : changed.getAddedSubList()) {
					if (pieceIndex != null) {
						pieces.get(pieceIndex).setVisible(false);
					}
				}
			}
		}
	}

	public void showPiece(Integer pieceID) {
		if (jigsaw.getHidedPieces().contains(pieceID)) {
			pieces.get(pieceID).setVisible(true);
			pieces.get(pieceID).setOpacity(0.4);
		}
	}

	public void unShowPiece(Integer pieceID) {
		if (jigsaw.getHidedPieces().contains(pieceID)) {
			pieces.get(pieceID).setVisible(false);
		}
	}

	public void setSize(Double pieceSize, Double margin) {

		this.setMaxWidth(Double.MIN_VALUE);
		this.setMaxHeight(Double.MIN_VALUE);

		Double backgroundSize = pieceSize * 3 / Math.sqrt(2) + 2 * margin;
		Double backgroundImageSize = pieceSize * 3;

		background.setWidth(backgroundSize);
		background.setHeight(backgroundSize);

		backgroundImage.setFitWidth(backgroundImageSize);
		backgroundImage.setFitHeight(backgroundImageSize);

		for (int i = 0; i < pieces.size(); i++) {
			pieces.get(i).setFitHeight(pieceSize);
			pieces.get(i).setFitWidth(pieceSize);

			pieces.get(i).setTranslateX(pieceX[i] * pieceSize / 2);
			pieces.get(i).setTranslateY(pieceY[i] * pieceSize / 2);
		}
	}

	public void setJigsaw(Jigsaw jigsaw) throws IllegalArgumentException {
		if(jigsaw != null) {
			this.jigsaw = jigsaw;
			this.pieces = new ArrayList<>();
			
			for(String imageURL : jigsaw.getPiecesURL()) {
				pieces.add(new ImageView(new Image(imageURL)));
			}
			
			for(int i = 0; i < jigsaw.getPiecesURL().size(); i++) {
				if(jigsaw.getHidedPieces().contains(i))pieces.get(i).setVisible(false);
			}
			
			jigsaw.getHidedPieces().addListener(new OnJigsawListChanged());
		} else throw new IllegalArgumentException("L'argument jigsaw ne peut pas être null");
		
	}

	public Jigsaw getJigsaw() {
		return jigsaw;
	}
	
}
