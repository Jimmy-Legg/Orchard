package orchard.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import orchard.model.enums.JigsawPieceType;

public class Jigsaw extends StackPane{
	
	private ObservableList<Integer> hidedPieces;
	private List<String> piecesURL;
	
	public Jigsaw() {
		
		this.piecesURL = new ArrayList<>();
		this.hidedPieces = FXCollections.observableArrayList();
		
		for(JigsawPieceType piece : JigsawPieceType.values()) {
			piecesURL.add(piece.getImageURL());
			hidedPieces.add(piece.getIndex());
		}
		
		Collections.shuffle(hidedPieces);
	}

	public ObservableList<Integer> getHidedPieces() {
		return hidedPieces;
	}

	public List<String> getPiecesURL() {
		return piecesURL;
	}
	
	public void addRandomPiece() {
		if(!hidedPieces.isEmpty()) {
			hidedPieces.remove(0);
		}
	}
	
	public void addPiece(Integer pieceID) {
		if(hidedPieces.contains(pieceID)) {
			hidedPieces.remove(hidedPieces.indexOf(pieceID));
		}
	}
	
	public Boolean isFull() {
		return hidedPieces.isEmpty();
	}
}
