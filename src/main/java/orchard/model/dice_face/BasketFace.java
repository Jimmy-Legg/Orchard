package orchard.model.dice_face;

import java.util.Arrays;

import orchard.model.GameHandler;
import orchard.model.enums.FruitType;
import orchard.model.tree.Tree;

public class BasketFace extends DiceFace{
	
	private GameHandler gameHandler;
	
	public BasketFace( GameHandler gameHandler) {
		super("/dice5.png");
		this.gameHandler = gameHandler;
	}
	
	@Override
	public void action() {
		gameHandler.setCollectState(Arrays.asList(FruitType.values()), 2);
	}
}