package orchard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.scene.input.DataFormat;
import javafx.scene.paint.Color;
import orchard.model.enums.FruitType;
import orchard.model.enums.JigsawPieceType;
import orchard.model.enums.OrchardColor;

class EnumTest {

	private static final Color BEIGE = Color.web("#EDF1D6");
	private static final Color LIGHT_GREEN = Color.web("#609966");
	private static final Color DARK_GREEN = Color.web("#40513B");
	
	private static final String APPLE_URL = "/apple.png";
	private static final String PEAR_URL = "/pear.png";
	private static final String PLUMS_URL = "/plums.png";
	private static final String CHERRY_URL = "/cherry.png";
	
	private static final String FRUIT_TYPE_DATA_FORMAT_CONTENT = "[model/fruit/FruitType]";
	private static final String JIGSAW_PIECE_DATA_FORMAT_CONTENT = "[model/jigsawPiece]";
	
	private static final String JIGSAW_PIECE_URL = "/jigsawPiece";
	private static final String JIGSAW_PIECE_EXTENSION = ".png";
	
	@Test
	void colorTest() {
		assertEquals(BEIGE, OrchardColor.BEIGE.color());
		assertEquals(LIGHT_GREEN, OrchardColor.LIGHT_GREEN.color());
		assertEquals(DARK_GREEN, OrchardColor.DARK_GREEN.color());
	}
	
	@Test
	void fruitTest() {
		assertEquals(APPLE_URL, FruitType.APPLE.getImageURL());
		assertEquals(PEAR_URL, FruitType.PEAR.getImageURL());
		assertEquals(PLUMS_URL, FruitType.PLUM.getImageURL());
		assertEquals(CHERRY_URL, FruitType.CHERRY.getImageURL());
		
		assertEquals(FRUIT_TYPE_DATA_FORMAT_CONTENT, FruitType.getDataFormat().toString());
		
		for (int i = 0; i < FruitType.values().length; i++) {
			assertEquals(i, FruitType.values()[i].getIndex());
		}
	}
	
	@Test
	void jigsawTest() {
		
		assertEquals(JIGSAW_PIECE_DATA_FORMAT_CONTENT, JigsawPieceType.getDataFormat().toString());
		for (int i = 0; i < JigsawPieceType.values().length; i++) {
			assertEquals(i, JigsawPieceType.values()[i].getIndex());
			assertEquals(JIGSAW_PIECE_URL + (i + 1) + JIGSAW_PIECE_EXTENSION, JigsawPieceType.values()[i].getImageURL());
		}
	}
}
