package orchard.ui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import orchard.model.Basket;
import orchard.model.enums.FruitType;

public class BasketContent extends StackPane{
	
	private Basket basket;
	
	private GridPane gridPane;
	
	private ImageView appleImage;
	private ImageView pearImage;
	private ImageView cherryImage;
	private ImageView plumImage;
	
	private Label appleLabel;
	private Label pearLabel;
	private Label cherryLabel;
	private Label plumLabel;
	
	private Canvas canvas;
	
	public BasketContent(Basket basket) {
		super();
		this.basket = basket;
		
		this.gridPane = new GridPane();
		this.gridPane.setAlignment(Pos.CENTER);
		this.gridPane.setHgap(5);
		
		this.appleImage = new ImageView(new Image(FruitType.APPLE.getImageURL(), 50, 50, false, false));
		this.pearImage = new ImageView(new Image(FruitType.PEAR.getImageURL(), 50, 50, false, false));
		this.cherryImage = new ImageView(new Image(FruitType.CHERRY.getImageURL(), 50, 50, false, false));
		this.plumImage = new ImageView(new Image(FruitType.PLUM.getImageURL(), 50, 50, false, false));
		
		this.gridPane.add(appleImage, 0, 0);
		this.gridPane.add(pearImage, 2, 0);
		this.gridPane.add(cherryImage, 0, 1);
		this.gridPane.add(plumImage, 2, 1);
		
		this.appleLabel = new Label("0");
		this.pearLabel = new Label("0");
		this.cherryLabel = new Label("0");
		this.plumLabel = new Label("0");
		
		this.canvas = new Canvas();
		this.canvas.getGraphicsContext2D().setFill(Color.WHITE);
		this.canvas.getGraphicsContext2D().setStroke(Color.rgb(50, 205, 50));
		this.canvas.getGraphicsContext2D().setLineWidth(Double.MIN_VALUE);
		this.canvas.getGraphicsContext2D().setGlobalAlpha(0.8);
		
		this.gridPane.add(appleLabel, 1, 0);
		this.gridPane.add(pearLabel, 3, 0);
		this.gridPane.add(cherryLabel, 1, 1);
		this.gridPane.add(plumLabel, 3, 1);
		
		this.basket.getAppleAmount().addListener(new OnAppleChangeListener());
		this.basket.getPearAmount().addListener(new OnPearChangeListener());
		this.basket.getPlumAmount().addListener(new OnPlumChangeListener());
		this.basket.getCherryAmount().addListener(new OnCherryChangeListener());
		
		this.getChildren().addAll(canvas, gridPane);
		this.setAlignment(Pos.CENTER);
	}
	
	private void drawCanvas() {
		
		Double width = this.canvas.getWidth();
		Double height = this.canvas.getHeight();
		
		this.canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		this.canvas.getGraphicsContext2D().setFill(Color.WHITE);
		this.canvas.getGraphicsContext2D().fillRoundRect(width * 1 / 5 / 2L, height * 1/10,
				width * 4 / 5L, height * 4/5, 30, 30);
	}
	
	public void setSize(Double width, Double height) {
		
		this.setMinWidth(width);
		this.setMinHeight(height);
		
		this.canvas.setHeight(height);
		this.canvas.setWidth(width);
		this.canvas.setLayoutY(0);
		this.canvas.setLayoutX(0);
		
		this.drawCanvas();
		this.setTranslateY(height/6);
	}
	
	class OnAppleChangeListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			Platform.runLater(new ChangeAppleTextRunnable(newValue.toString()));
		}
	}
	
	class OnPearChangeListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			Platform.runLater(new ChangePearTextRunnable(newValue.toString()));
		}
	}
	
	class OnPlumChangeListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			Platform.runLater(new ChangePlumTextRunnable(newValue.toString()));
		}
	}
	
	class OnCherryChangeListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			Platform.runLater(new ChangeCherryTextRunnable(newValue.toString()));
		}
	}
	
	class ChangeAppleTextRunnable implements Runnable {
		private String value;
		public ChangeAppleTextRunnable(String value) {
			super();
			this.value = value;
		}
		@Override
		public void run() {
			appleLabel.setText(value);
		}
	}
	
	class ChangePearTextRunnable implements Runnable {
		private String value;
		public ChangePearTextRunnable(String value) {
			super();
			this.value = value;
		}
		@Override
		public void run() {
			pearLabel.setText(value);
		}
	}
	
	class ChangeCherryTextRunnable implements Runnable {
		private String value;
		public ChangeCherryTextRunnable(String value) {
			super();
			this.value = value;
		}
		@Override
		public void run() {
			cherryLabel.setText(value);
		}
	}
	
	class ChangePlumTextRunnable implements Runnable {
		private String value;
		public ChangePlumTextRunnable(String value) {
			super();
			this.value = value;
		}
		@Override
		public void run() {
			plumLabel.setText(value);
		}
	}
}
