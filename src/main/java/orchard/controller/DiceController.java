package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import orchard.ui.DiceUI;

public class DiceController implements EventHandler<MouseEvent> {

	private DiceUI diceUI;

	public DiceController(DiceUI diceUI) {
		super();
		this.diceUI = diceUI;
	}

	@Override
	public void handle(MouseEvent event) {
		if (diceUI.getDice().isClickable()) {
			Thread rollThread = new Thread(new RollThread(diceUI));
			rollThread.start();
		}
	}
	
	private class RollThread implements Runnable {

		private DiceUI diceUI;
		
		public RollThread(DiceUI diceUI) {
			super();
			this.diceUI = diceUI;
		}

		@Override
		public void run() {
			
			diceUI.getDice().setClickable(false);

			try {
				diceUI.getLabel().setVisible(false);
				for(int i = 0; i < 20; i++) {
					diceUI.getDice().changeFace();
					Thread.sleep(100);
				}
				Thread.sleep(1000);
				diceUI.setVisible(false);
				diceUI.getDice().action();
			} catch (InterruptedException e) {
				 Thread.currentThread().interrupt();
			}
		}
	}
}
