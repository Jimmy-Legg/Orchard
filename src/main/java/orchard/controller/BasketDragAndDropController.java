package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import orchard.model.Basket;
import orchard.model.GameHandler;
import orchard.model.enums.FruitType;
import orchard.model.tree.Tree;
import orchard.ui.BasketUI;

public class BasketDragAndDropController {
	
	public static void setupBasketDragAndDropBehavior(BasketUI basketUI) {
		basketUI.setOnDragDropped(new BasketDragDroppedController(basketUI.getBasket()));
		basketUI.setOnDragOver(new BasketDragOverController(basketUI.getBasket()));
	}
	
	static class BasketDragDroppedController implements EventHandler<DragEvent> {

		private Basket basket;

		public BasketDragDroppedController(Basket basket) {
			super();
			this.basket = basket;
		}

		@Override
		public void handle(DragEvent event) {

			Boolean success = false;
			try {

				FruitType fruitType = (FruitType) event.getDragboard().getContent(FruitType.getDataFormat());
				if (fruitType != null && basket.getFruitsAmount().intValue() < Tree.getFruitMaxAmount()) {
					basket.addFruit(fruitType);
					success = true;
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			event.setDropCompleted(success);
			event.consume();
		}
	}

	static class BasketDragOverController implements EventHandler<DragEvent> {

		Basket basket;

		public BasketDragOverController(Basket basket) {
			super();
			this.basket = basket;
		}

		public void handle(DragEvent event) {

			FruitType fruitType = (FruitType) event.getDragboard().getContent(FruitType.getDataFormat());

			if (event.getDragboard().hasImage() && fruitType != null
					&& basket.getFruitsAmount().intValue() < Tree.getFruitMaxAmount()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		}
	}
}
