package orchard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import orchard.model.Jigsaw;
import orchard.model.enums.JigsawPieceType;

class JigsawTest {
	
	private Jigsaw jigsaw;
	
	@BeforeEach
	void initialize() {
		jigsaw = new Jigsaw();
	}
	
	@Test
	void getPieceHided() {
		assertEquals(9 ,jigsaw.getHidedPieces().size());
	}
	
	@Test
	void getPieceURL() {
		
		for(JigsawPieceType piece : JigsawPieceType.values()) {
			assertEquals(jigsaw.getPiecesURL().get(piece.getIndex()), piece.getImageURL());
		}
		
	}
	
	@Test
	void addRandomPiece() {
		jigsaw.addRandomPiece();
		assertEquals(8, jigsaw.getHidedPieces().size());
		jigsaw.addRandomPiece();
		assertEquals(7, jigsaw.getHidedPieces().size());
	}
	
	@Test
	void addPiece() {
		assertTrue(jigsaw.getHidedPieces().contains(1));
		jigsaw.addPiece(1);
		assertFalse(jigsaw.getHidedPieces().contains(1));
		assertTrue(jigsaw.getHidedPieces().contains(2));
		jigsaw.addPiece(2);
		assertFalse(jigsaw.getHidedPieces().contains(2));
		assertTrue(jigsaw.getHidedPieces().contains(3));
		jigsaw.addPiece(3);
		assertFalse(jigsaw.getHidedPieces().contains(3));
		
	}
	
	@Test
	void isFullShouldWork() {
		while(jigsaw.getHidedPieces().size() > 0) {
			assertFalse(jigsaw.isFull());
			jigsaw.addRandomPiece();
		}
		assertTrue(jigsaw.isFull());
	}
}
