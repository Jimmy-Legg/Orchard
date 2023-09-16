package orchard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import orchard.exceptions.TreeException;
import orchard.model.enums.FruitType;
import orchard.model.tree.AppleTree;
import orchard.model.tree.CherryTree;
import orchard.model.tree.PearTree;
import orchard.model.tree.PlumTree;
import orchard.model.tree.Tree;

class TreeTest {

	private static final String TREE_IMAGE_URL = "/arbre.png";
	private static final String TREE_ERROR_MESSAGE = "Tree cannot refill without a Fruit Type";
	
	
	private Tree appleTree;
	private Tree pearTree;
	private Tree plumTree;
	private Tree cherryTree;

	@BeforeEach
	public void init() {
		appleTree = new AppleTree();
		pearTree = new PearTree();
		plumTree = new PlumTree();
		cherryTree = new CherryTree();
	}

	@Test
	void should_be_empty_after_initializing_trees() {
		
		appleTree = new AppleTree();
		
		assertTrue(appleTree.isEmpty());
		assertTrue(pearTree.isEmpty());
		assertTrue(plumTree.isEmpty());
		assertTrue(cherryTree.isEmpty());
	}
	
	@Test
	void should_be_empty_after_picking_all_the_fruits_by_collect() {
		
		for(int i = 0; i < Tree.getFruitMaxAmount(); i++) {
			appleTree.removeAFruit();
			pearTree.removeAFruit();
			plumTree.removeAFruit();
			cherryTree.removeAFruit();
		}
		
		assertTrue(appleTree.isEmpty());
		assertTrue(pearTree.isEmpty());
		assertTrue(plumTree.isEmpty());
		assertTrue(cherryTree.isEmpty());
	}
	
	@Test
	void should_not_pick_more_than_the_max_value_of_the_tree() {
		
		for(int i = 0; i < Tree.getFruitMaxAmount() * 2; i++) {
			appleTree.removeAFruit();
			pearTree.removeAFruit();
			plumTree.removeAFruit();
			cherryTree.removeAFruit();
		}
		
		assertTrue(appleTree.isEmpty());
		assertTrue(pearTree.isEmpty());
		assertTrue(plumTree.isEmpty());
		assertTrue(cherryTree.isEmpty());
	}
	
	@Test
	void should_return_the_good_image() {
		
		assertEquals(TREE_IMAGE_URL, appleTree.getImageURL());
		assertEquals(TREE_IMAGE_URL, pearTree.getImageURL());
		assertEquals(TREE_IMAGE_URL, plumTree.getImageURL());
		assertEquals(TREE_IMAGE_URL, cherryTree.getImageURL());
	}
	
	@Test
	void should_return_the_good_fruitType() {

		assertEquals(FruitType.APPLE, appleTree.getFruitType());
		assertEquals(FruitType.PEAR, pearTree.getFruitType());
		assertEquals(FruitType.PLUM, plumTree.getFruitType());
		assertEquals(FruitType.CHERRY, cherryTree.getFruitType());
		
	}
	
	@Test
	void should_return_the_good_fruitList() {
		
		assertEquals(TREE_IMAGE_URL, appleTree.getImageURL());
		assertEquals(TREE_IMAGE_URL, pearTree.getImageURL());
		assertEquals(TREE_IMAGE_URL, plumTree.getImageURL());
		assertEquals(TREE_IMAGE_URL, cherryTree.getImageURL());
	}
	
	
	@Test
	void canRefill() {
		
		try {
			appleTree.refill();
			pearTree.refill();
			plumTree.refill();
			cherryTree.refill();
		} catch (TreeException e) {
			e.printStackTrace();
		}
		
		assertEquals(Tree.getFruitMaxAmount(), appleTree.getFruitsAmount());
		assertEquals(Tree.getFruitMaxAmount(), pearTree.getFruitsAmount());
		assertEquals(Tree.getFruitMaxAmount(), plumTree.getFruitsAmount());
		assertEquals(Tree.getFruitMaxAmount(), cherryTree.getFruitsAmount());
	}
	
	@Test
	void canTreeLaunchExceptions() {
		VoidTree voidTree = new VoidTree();
		
		assertThrows(
			TreeException.class,
		    () -> voidTree.refill(),
		    TREE_ERROR_MESSAGE
		);
	}
	
	class VoidTree extends Tree {
		protected VoidTree() {
			super(null);
		}	
	}
	
	@Test
	void canGetTheFruitList() {
		appleTree = new AppleTree();
		assertEquals(Tree.getFruitMaxAmount(), appleTree.getFruits().size());
	}
	
}
