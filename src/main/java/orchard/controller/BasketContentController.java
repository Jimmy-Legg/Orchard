package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import orchard.ui.BasketUI;

public class BasketContentController {

	public static void setupBasketContentBehavior(BasketUI basketUI) {
		basketUI.setOnMouseClicked(new BasketClickedController(basketUI));
		basketUI.setOnMouseExited(new BasketMouseExitedController(basketUI));
	}
	
	static class BasketClickedController implements EventHandler<MouseEvent>{
		private BasketUI basketUI;
		public BasketClickedController(BasketUI basketUI) {
			super();
			this.basketUI = basketUI;
		}

		@Override
		public void handle(MouseEvent event) {
			basketUI.getBasketContent().setVisible(!basketUI.getBasketContent().isVisible());
		}
	}
	
	static class BasketMouseExitedController implements EventHandler<MouseEvent> {

		private BasketUI basketUI;
		
		public BasketMouseExitedController(BasketUI basketUI) {
			super();
			this.basketUI = basketUI;
		}

		@Override
		public void handle(MouseEvent event) {
			if(basketUI.getBasketContent().isVisible())basketUI.getBasketContent().setVisible(false);
		}
	}
}
