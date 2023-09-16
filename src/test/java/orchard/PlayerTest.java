package orchard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import orchard.model.Player;
import orchard.model.PlayerData;
import orchard.model.enums.FruitType;

class PlayerTest {

	private static final String PLAYER_NAME = "player1";
	private static final String BASKET_IMAGE = "/basket.png";
	
	private PlayerData pData;
	private Player p1;
	
	@BeforeEach
	void initialize() {
		pData = new PlayerData(PLAYER_NAME);
		p1 = new Player(pData);
	}
	
	@Test
	void playerDataGetImageAndPlayerName() {
		assertEquals(BASKET_IMAGE, pData.getBasketImageUrl());
		assertEquals(PLAYER_NAME, pData.getName());
	}
	
	@Test
	void playerDataMoney() {
		assertEquals(0, pData.getMoney());
		pData.setMoney(10);
		assertEquals(10, pData.getMoney());
	}
	@Test
	void playerDataVictory() {
		assertEquals(0, pData.getVictory());
		pData.setVictory(10);
		assertEquals(10, pData.getVictory());
	}
	
	@Test
	void playerDataLoose() {
		assertEquals(0, pData.getLoose());
		pData.setLoose(10);
		assertEquals(10, pData.getLoose());
	}
	
	@Test
	void playerCanAddFruits() {
		assertEquals(0, p1.getAppleAmount().intValue());
		assertEquals(0, p1.getPearAmount().intValue());
		assertEquals(0, p1.getPlumAmount().intValue());
		assertEquals(0, p1.getCherryAmount().intValue());
		assertEquals(0, p1.getFruitAmount().intValue());
		
		p1.addApple();
		p1.addCherry();
		p1.addPear();
		p1.addPlum();
		
		assertEquals(1, p1.getAppleAmount().intValue());
		assertEquals(1, p1.getPearAmount().intValue());
		assertEquals(1, p1.getPlumAmount().intValue());
		assertEquals(1, p1.getCherryAmount().intValue());
		assertEquals(4, p1.getFruitAmount().intValue());
		
		p1.setAppleAmount(2);
		p1.setPearAmount(2);
		p1.setPlumAmount(2);
		p1.setCherryAmount(2);
		
		assertEquals(2, p1.getAppleAmount().intValue());
		assertEquals(2, p1.getPearAmount().intValue());
		assertEquals(2, p1.getPlumAmount().intValue());
		assertEquals(2, p1.getCherryAmount().intValue());
		assertEquals(8, p1.getFruitAmount().intValue());
	}
	
	@Test
	void playerCanAddFruitsWithOtherMethod() {
		assertEquals(0, p1.getAppleAmount().intValue());
		assertEquals(0, p1.getPearAmount().intValue());
		assertEquals(0, p1.getPlumAmount().intValue());
		assertEquals(0, p1.getCherryAmount().intValue());
		assertEquals(0, p1.getFruitAmount().intValue());
		
		p1.addFruit(FruitType.APPLE);
		p1.addFruit(FruitType.PEAR);
		p1.addFruit(FruitType.PLUM);
		p1.addFruit(FruitType.CHERRY);
		
		assertEquals(1, p1.getAppleAmount().intValue());
		assertEquals(1, p1.getPearAmount().intValue());
		assertEquals(1, p1.getPlumAmount().intValue());
		assertEquals(1, p1.getCherryAmount().intValue());
		assertEquals(4, p1.getFruitAmount().intValue());
	}
	
	@Test
	void playerCanGetPlayerData() {
		assertEquals(p1.getPlayerData(), pData);
	}
	
	@Test
	void playerToString() {
		assertEquals("player1 | score : 0 | raven : 0", p1.toString());
		p1.addApple();
		assertEquals("player1 | score : 1 | raven : 0", p1.toString());
		p1.addPlum();
		assertEquals("player1 | score : 2 | raven : 0", p1.toString());
		p1.addPear();
		assertEquals("player1 | score : 3 | raven : 0", p1.toString());
		p1.addCherry();
		assertEquals("player1 | score : 4 | raven : 0", p1.toString());
		p1.incrementRavenPieceAmount();
		assertEquals("player1 | score : 4 | raven : 1", p1.toString());
	}
	
	@Test
	void playerAddRavenJigsawPiece() {
		assertEquals(0, p1.getRavenPieceAmount());
		p1.incrementRavenPieceAmount();
		assertEquals(1, p1.getRavenPieceAmount());
	}
	
	@Test
	void PlayerCanCompareTo() {
		Player p2 = new Player(new PlayerData("p2"));
		assertEquals(0, p1.compareTo(p2));
		
		p2.addApple();
		assertTrue(p1.compareTo(p2) > 0);
		assertTrue(p2.compareTo(p1) < 0);
		
		p1.addApple();
		assertEquals(0, p1.compareTo(p2));
		
		p2.incrementRavenPieceAmount();
		assertTrue(p1.compareTo(p2) < 0);
		assertTrue(p2.compareTo(p1) > 0);
	}
}
