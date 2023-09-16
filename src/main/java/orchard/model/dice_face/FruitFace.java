package orchard.model.dice_face;

import java.util.Arrays;

import orchard.model.GameHandler;
import orchard.model.tree.Tree;

public class FruitFace extends DiceFace{
	
	private Tree tree;
	private GameHandler gameHandler;
	
	public FruitFace(Tree tree, String imageURL, GameHandler gameHandler) {
		super(imageURL);
		this.tree = tree;
		this.gameHandler = gameHandler;
	}
	
	@Override
	public void action() {
		if(!tree.isEmpty()) {
			gameHandler.setCollectState(Arrays.asList(tree.getFruitType()), 1);
		} else {
			Thread nextPlayerThread = new Thread(new NextPlayerThread());
			nextPlayerThread.start();
		}
	}
	
	private class NextPlayerThread implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			gameHandler.nextPlayer();
		}
	}
}
