package orchard.ui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import orchard.model.Basket;

public class BasketUI extends StackPane {

	private static final String SCORE_INITIAL_TEXT = "0";
	private static final Font SCORE_TEXT_FONT = Font.font("Arial", FontWeight.BOLD, 55);

	private Basket basket;

	private Label scoreLabel;
	private BasketContent basketContent;

	private ImageView basketImage;

	
	
	public BasketUI(Basket basket, Double width) {
		this(basket);
		resize(width);
	}

	public BasketUI(Basket basket) {
		super();
		
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10, 0, 10, 0));

		this.scoreLabel = new Label(SCORE_INITIAL_TEXT);
		this.scoreLabel.setFont(SCORE_TEXT_FONT);
		
		this.basketContent = new BasketContent(basket);
		this.basketContent.setVisible(false);
		
		this.basketImage = new ImageView(new Image(Basket.getImageUrl()));

		this.getChildren().addAll(basketImage, scoreLabel, basketContent);
		
		this.basket = basket;
		this.basket.getFruitsAmount().addListener(new OnBasketContentChange());
	}

	public void resize(Double width) {
		this.basketImage.setFitHeight(width * 6 / 10);
		this.basketImage.setFitWidth(width * 9 / 10);
		this.basketContent.setSize(width * 9 / 10, width * 6 / 10);
		this.scoreLabel.setTranslateY(width / 8);
	}
	
	class OnBasketContentChange implements ChangeListener<Number> {
		
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			Platform.runLater(new UpdateScoreLabel(newValue));
		}
	}
	
	class UpdateScoreLabel implements Runnable {

		private Number newValue;
		
		public UpdateScoreLabel(Number newValue) {
			super();
			this.newValue = newValue;
		}

		@Override
		public void run() {
			scoreLabel.setText(newValue.toString());
		}
	}
	
	public Basket getBasket() {
		return basket;
	}
	
	public BasketContent getBasketContent() {
		return basketContent;
	}
}
