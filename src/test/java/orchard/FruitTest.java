package orchard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import orchard.model.enums.FruitType;
import orchard.model.fruit.Apple;
import orchard.model.fruit.Cherry;
import orchard.model.fruit.Fruit;
import orchard.model.fruit.Pear;
import orchard.model.fruit.Plum;

class FruitTest {

	private Fruit apple;
	private Fruit pear;
	private Fruit plum;
	private Fruit cherry;
	
	@BeforeEach
	void initialize() {
		apple = new Apple(0);
		pear = new Pear(1);
		plum = new Plum(2);
		cherry = new Cherry(3);	
	}
	
	@Test
	void CanDoAToString() { 
		assertEquals("Apple", apple.toString());
		assertEquals("Pear", pear.toString());
		assertEquals("Plums", plum.toString());
		assertEquals("Cherry", cherry.toString());
	}
	
	@Test
	void CanGetFruitType() { 
		assertEquals(FruitType.APPLE, apple.getFruitType());
		assertEquals(FruitType.PEAR, pear.getFruitType());
		assertEquals(FruitType.PLUM, plum.getFruitType());
		assertEquals(FruitType.CHERRY, cherry.getFruitType());
	}
	
	@Test
	void CanGetIndex() { 
		assertEquals(0, apple.getIndex());
		assertEquals(1, pear.getIndex());
		assertEquals(2, plum.getIndex());
		assertEquals(3, cherry.getIndex());
	}
	
}
