package orchard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import orchard.exceptions.TreeException;
import orchard.model.Dice;
import orchard.model.GameHandler;
import orchard.model.dice_face.BasketFace;
import orchard.model.dice_face.DiceFace;
import orchard.model.dice_face.FruitFace;
import orchard.model.dice_face.RavenFace;
import orchard.model.enums.FruitType;
import orchard.model.tree.AppleTree;
import orchard.model.tree.CherryTree;
import orchard.model.tree.PearTree;
import orchard.model.tree.PlumTree;
import orchard.model.tree.Tree;
import orchard.ui.JigsawDeck;

class DiceTest {

	private Dice dice;
	
	private GameHandler gameHandler;
	
	private Tree appleTree;
	private Tree pearTree;
	private Tree plumTree;
	private Tree cherryTree;
	
	private List<DiceFace> diceFaces;
	
	@BeforeEach
	public void init() {
		appleTree = new AppleTree();
		pearTree = new PearTree();
		plumTree = new PlumTree();
		cherryTree = new CherryTree();
		
		diceFaces = new ArrayList<>();
		gameHandler = new GameHandler(null, null);
		
		diceFaces.add(new FruitFace(appleTree, "/dice0.png", gameHandler));
		diceFaces.add(new FruitFace(pearTree, "/dice1.png", gameHandler));
		diceFaces.add(new FruitFace(plumTree, "/dice2.png", gameHandler));
		diceFaces.add(new FruitFace(cherryTree, "/dice3.png", gameHandler));
		diceFaces.add(new RavenFace(new JigsawDeck()));
		diceFaces.add(new BasketFace(gameHandler));
		
		dice = new Dice(diceFaces);
	}

	@Test
	void facesHaveImageAndName() {
		
		for (int i = 0; i < 4; i++) {
			assertEquals(diceFaces.get(i).getImageURL(), "/dice" + i + ".png");
		}
	}
	
	@Test
	void shouldChangeFaceToADifferentRandomFace() {
		
		for (int i = 0; i < dice.getFaces().size() * 2; i++) {
			Integer oldValue = dice.getActualFaceProperty().intValue();
			dice.changeFace();
			
			Integer newValue = dice.getActualFaceProperty().intValue();
			assertNotEquals(oldValue, newValue);
			assertEquals(dice.getFace(), diceFaces.get(dice.getActualFaceProperty().intValue()));
		}
	}
	
	@Test
	void shouldBeClickableFromTheBeginingAndCanChange() {
		assertTrue(dice.isClickable());
		dice.setClickable(false);
		assertFalse(dice.isClickable());
	}
	
	@Test
	void shouldSetFruitToTakeAndFruitTypeWhenBasketAction() {
		assertEquals(0, gameHandler.getFruitsToTakeTypes().size());
		dice.getFaces().get(5).action();
		assertEquals(4, gameHandler.getFruitsToTakeTypes().size());
		assertTrue(gameHandler.getFruitsToTakeTypes().contains(FruitType.APPLE));
		assertTrue(gameHandler.getFruitsToTakeTypes().contains(FruitType.PEAR));
		assertTrue(gameHandler.getFruitsToTakeTypes().contains(FruitType.PLUM));
		assertTrue(gameHandler.getFruitsToTakeTypes().contains(FruitType.CHERRY));	
	}
	
	@Test
	void shouldSetFruitToTakeAndFruitTypeWhenFruitAction() {
		
		try {
			appleTree.refill();
			pearTree.refill();
			plumTree.refill();
			cherryTree.refill();
		} catch (TreeException e) {
			Thread.currentThread().interrupt();
		}
		
		assertEquals(0, gameHandler.getFruitsToTakeTypes().size());
		dice.getFaces().get(0).action();
		assertEquals(1, gameHandler.getFruitsToTakeTypes().size());
		assertTrue(gameHandler.getFruitsToTakeTypes().contains(FruitType.APPLE));
		
		dice.getFaces().get(1).action();
		assertEquals(1, gameHandler.getFruitsToTakeTypes().size());
		assertTrue(gameHandler.getFruitsToTakeTypes().contains(FruitType.PEAR));
		
		dice.getFaces().get(2).action();
		assertEquals(1, gameHandler.getFruitsToTakeTypes().size());
		assertTrue(gameHandler.getFruitsToTakeTypes().contains(FruitType.PLUM));
		
		dice.getFaces().get(3).action();
		assertEquals(1, gameHandler.getFruitsToTakeTypes().size());
		assertTrue(gameHandler.getFruitsToTakeTypes().contains(FruitType.CHERRY));	
	}
	
	@Test
	void shouldDoNothingActionAndTreeEmpty() {
		
		assertEquals(0, gameHandler.getFruitsToTakeTypes().size());
		dice.getFaces().get(0).action();
		assertEquals(0, gameHandler.getFruitsToTakeTypes().size());
		
		dice.getFaces().get(1).action();
		assertEquals(0, gameHandler.getFruitsToTakeTypes().size());
		
		dice.getFaces().get(2).action();
		assertEquals(0, gameHandler.getFruitsToTakeTypes().size());
		
		dice.getFaces().get(3).action();
		assertEquals(0, gameHandler.getFruitsToTakeTypes().size());
	}
}
