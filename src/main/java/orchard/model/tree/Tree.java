package orchard.model.tree;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import orchard.exceptions.TreeException;
import orchard.model.Basket;
import orchard.model.enums.FruitType;
import orchard.model.fruit.Apple;
import orchard.model.fruit.Cherry;
import orchard.model.fruit.Fruit;
import orchard.model.fruit.Pear;
import orchard.model.fruit.Plum;

public abstract class Tree{
	
	private final FruitType fruitType;
	private final String imageURL;
	
	private Basket basket;
	
	private static final Integer FRUIT_MAX_AMOUNT = 10;
	
	private ObservableList<Fruit> fruits;
	
	protected Tree(FruitType fruitType, String imageUrl) {
		super();
		
		this.fruitType = fruitType;
		
		this.imageURL = imageUrl;
		this.fruits = FXCollections.observableList(Arrays.asList(new Fruit[FRUIT_MAX_AMOUNT]));
	}
	
	protected Tree(FruitType fruitType) {
		this(
			fruitType,
			"/arbre.png"
		);
	}
	
	public void refill() throws TreeException {
		if(fruitType != null) {
			for (int i = 0; i<FRUIT_MAX_AMOUNT ; i++) {
				if(fruitType.equals(FruitType.APPLE))fruits.set(i, new Apple(i)); 
				else if(fruitType.equals(FruitType.PEAR))fruits.set(i, new Pear(i));
				else if(fruitType.equals(FruitType.CHERRY))fruits.set(i, new Cherry(i));
				else fruits.set(i, new Plum(i));
			}
		}
		else {
			throw new TreeException("Tree cannot refill without a Fruit Type");
		}
	}
	
	public void removeAFruit(){
		int i=0;
		while(i<FRUIT_MAX_AMOUNT && fruits.get(i) == null) {
			i++;
		}
		if(i<FRUIT_MAX_AMOUNT) fruits.set(i, null);
	}
	
	public Integer getFruitsAmount() {
		Integer amount = 0;
		for (int i = 0; i<FRUIT_MAX_AMOUNT ; i++) {
			if(this.fruits.get(i) != null)amount++;
		}
		return amount;
	}
	
	public Boolean isEmpty() {
		
		boolean isEmpty = true;
		
		for(Fruit f : fruits) {
			if(f!=null)isEmpty = false;
		}
		return isEmpty;
		
	}
	
	public String getImageURL() {
		return imageURL;
	}
	
	public ObservableList<Fruit> getFruits() {
		return fruits;
	}

	public FruitType getFruitType() {
		return fruitType;
	}

	public static Integer getFruitMaxAmount() {
		return FRUIT_MAX_AMOUNT;
	}
	
	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	
}


