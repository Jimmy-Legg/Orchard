package orchard.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import orchard.model.Dice;
import orchard.model.dice_face.DiceFace;

public class DiceUI extends StackPane{

	private List<Image> images;

	private ImageView imageView;
	private Label clickOnMeText;
	
	private Dice dice;
	
	private static final String CLICK_ON_ME_TEXT = "Clique pour lancer !";
	private static final Font CLICK_ON_ME_FONT = Font.font("Arial", FontWeight.BOLD, 35);
	
	public DiceUI(Dice dice) {
		super();
		
		this.imageView = new ImageView();
		this.images = new ArrayList<>();
		
		try {
			setDice(dice);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		this.clickOnMeText = new Label(CLICK_ON_ME_TEXT);
		this.clickOnMeText.setAlignment(Pos.BOTTOM_CENTER);
		this.clickOnMeText.setFont(CLICK_ON_ME_FONT);
		
		this.getChildren().addAll(imageView, clickOnMeText);
		
		this.setSize(500);
	}
	
	public class OnFaceChange implements ChangeListener<Number>{
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldFace, Number newFace) {
			imageView.setImage(images.get((int) newFace));
		}
	}
	
	public ImageView getImageView() {
		return this.imageView;
	}
	
	public Label getLabel() {
		return this.clickOnMeText;
	}
	
	public void setSize(int size) {
		this.imageView.setFitHeight(size);
		this.imageView.setFitWidth(size);
		this.clickOnMeText.setPrefHeight(size * 0.75f);
	}

	public Dice getDice() {
		return dice;
	}
	
	public void setDice(Dice dice) throws IllegalArgumentException{
		if(dice != null) {
			this.dice = dice;
			for(DiceFace diceFace : dice.getFaces()) {
				images.add(new Image(diceFace.getImageURL()));
			}
			this.dice.getActualFaceProperty().addListener(new OnFaceChange());
			
			if(!images.isEmpty())this.imageView.setImage(images.get(0));
			else this.imageView.setImage(null);
		} else throw new IllegalArgumentException("Dice cannot be null");
		
	}
	
}
