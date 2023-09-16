package orchard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import orchard.model.Basket;
import orchard.model.enums.FruitType;

class BasketTest {

	private Basket basket;
	
	private static final String BASKET_IMAGE_URL = "/basket.png";
	
	@BeforeEach
	void initialize() {
		basket = new Basket();
	}
	
	@Test
	void canAddFruit() {
		
		assertEquals(0, basket.getAppleAmount().intValue());
		assertEquals(0, basket.getCherryAmount().intValue());
		assertEquals(0, basket.getPlumAmount().intValue());
		assertEquals(0, basket.getPearAmount().intValue());
		assertEquals(0, basket.getFruitsAmount().intValue());
		
		basket.addApple();
		assertEquals(1, basket.getAppleAmount().intValue());
		assertEquals(1, basket.getFruitsAmount().intValue());
		basket.addPear();
		assertEquals(1, basket.getPearAmount().intValue());
		assertEquals(2, basket.getFruitsAmount().intValue());
		basket.addCherry();
		assertEquals(1, basket.getCherryAmount().intValue());
		assertEquals(3, basket.getFruitsAmount().intValue());
		basket.addPlum();
		assertEquals(1, basket.getPlumAmount().intValue());
		assertEquals(4, basket.getFruitsAmount().intValue());
	}
	
	@Test
	void canGetUseAddFruit() {
		assertEquals(0, basket.getAppleAmount().intValue());
		assertEquals(0, basket.getCherryAmount().intValue());
		assertEquals(0, basket.getPlumAmount().intValue());
		assertEquals(0, basket.getPearAmount().intValue());
		assertEquals(0, basket.getFruitsAmount().intValue());
		
		basket.addFruit(FruitType.APPLE);
		assertEquals(1, basket.getAppleAmount().intValue());
		assertEquals(1, basket.getFruitsAmount().intValue());
		basket.addFruit(FruitType.PEAR);
		assertEquals(1, basket.getPearAmount().intValue());
		assertEquals(2, basket.getFruitsAmount().intValue());
		basket.addFruit(FruitType.CHERRY);
		assertEquals(1, basket.getCherryAmount().intValue());
		assertEquals(3, basket.getFruitsAmount().intValue());
		basket.addFruit(FruitType.PLUM);
		assertEquals(1, basket.getPlumAmount().intValue());
		assertEquals(4, basket.getFruitsAmount().intValue());
	}
	
	@Test
	void canGetImageURL() {
		assertEquals(BASKET_IMAGE_URL, Basket.getImageUrl());
	}
}
