package orchard.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import orchard.model.enums.FruitType;

public class Basket {

	private IntegerProperty appleAmount;
	private IntegerProperty pearAmount;
	private IntegerProperty cherryAmount;
	private IntegerProperty plumAmount;
	private NumberBinding fruitsAmount;
	private static final String BASKET_IMAGE_URL = "/basket.png";
	
	public Basket() {
		super();
		this.appleAmount = new SimpleIntegerProperty();
		this.pearAmount = new SimpleIntegerProperty();
		this.cherryAmount = new SimpleIntegerProperty();
		this.plumAmount = new SimpleIntegerProperty();
		this.fruitsAmount = Bindings.add(this.appleAmount, Bindings.add(this.pearAmount, Bindings.add(this.cherryAmount, this.plumAmount)));
	}
	
	public IntegerProperty getAppleAmount() {
		return appleAmount;
	}

	public IntegerProperty getPearAmount() {
		return pearAmount;
	}

	public IntegerProperty getCherryAmount() {
		return cherryAmount;
	}

	public IntegerProperty getPlumAmount() {
		return plumAmount;
	}

	public NumberBinding getFruitsAmount() {
		return fruitsAmount;
	}
	
	public void addApple() {
		this.appleAmount.set(appleAmount.intValue() + 1);
	}

	public void addPear() {
		this.pearAmount.set(pearAmount.intValue() + 1);
	}
	
	public void addCherry() {
		this.cherryAmount.set(cherryAmount.intValue() + 1);
	}
	
	public void addPlum() {
		this.plumAmount.set(plumAmount.intValue() + 1);
	}
	
	public void addFruit(FruitType fruitType) {
		if(fruitType != null) {
			if(fruitType.equals(FruitType.APPLE))addApple();
			else if(fruitType.equals(FruitType.PEAR))addPear();
			else if(fruitType.equals(FruitType.PLUM))addPlum();
			else addCherry();
		}
	}
	
	public static String getImageUrl() {
		return BASKET_IMAGE_URL;
	}
}
