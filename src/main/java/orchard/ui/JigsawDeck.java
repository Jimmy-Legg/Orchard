package orchard.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import orchard.model.enums.JigsawPieceType;
import orchard.model.enums.OrchardColor;

public class JigsawDeck extends VBox {

	private static final double X_HIDED_POSITION = 200;
	private static final double Y_HIDED_POSITION = 0;

	private List<JigsawPiece> pieces = new ArrayList<>();

	public JigsawDeck() {
		super();

		this.setTranslateX(X_HIDED_POSITION);
		this.setTranslateY(Y_HIDED_POSITION);

		this.setPadding(new Insets(0, 10, 0, 10));
		this.setSpacing(5);

		this.setBackground(new Background(new BackgroundFill(OrchardColor.DARK_GREEN.color(), CornerRadii.EMPTY, Insets.EMPTY)));
		this.setAlignment(Pos.CENTER);

		
	}

	public void hide() {
		setDeckDraggable(false);

		TranslateTransition hideTranslation = new TranslateTransition();
		hideTranslation.setDuration(Duration.millis(1000));
		hideTranslation.setToX(X_HIDED_POSITION);
		hideTranslation.setToY(Y_HIDED_POSITION);
		hideTranslation.setCycleCount(0);
		hideTranslation.setNode(this);
		hideTranslation.play();
	}

	public void show() {
		TranslateTransition hideTranslation = new TranslateTransition();
		hideTranslation.setDuration(Duration.millis(1000));
		hideTranslation.setToX(0);
		hideTranslation.setToY(0);
		hideTranslation.setCycleCount(0);
		hideTranslation.setNode(this);
		hideTranslation.play();

		setDeckDraggable(true);
	}

	public void refill() {
		this.getChildren().clear();
		if (!pieces.isEmpty())
			pieces.clear();
		try {
			for (JigsawPieceType piece : JigsawPieceType.values()) {
				pieces.add(new JigsawPiece(piece));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		this.getChildren().addAll(pieces);
	}

	public void setDeckDraggable(Boolean isDeckDraggable) {
		for (JigsawPiece piece : pieces) {
			piece.setIsDraggable(isDeckDraggable);
		}
	}

	public void resize(Double width) {
		for (JigsawPiece piece : pieces) {
			piece.resize(width / (JigsawPieceType.values().length + 1));
		}
	}

	public List<JigsawPiece> getPieces() {
		return pieces;
	}

}
