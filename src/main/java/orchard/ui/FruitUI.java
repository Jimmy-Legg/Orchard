package orchard.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import orchard.model.fruit.Fruit;

public class FruitUI extends ImageView{

	private Fruit fruit;
	
	public FruitUI(Fruit fruit) {
		super();
		this.fruit = fruit;
		if(fruit != null)this.setImage(new Image(fruit.getFruitType().getImageURL()));
	}
	
	public FruitUI(Double size, Double translateX, Double translateY, Fruit fruit) {
		this(fruit);
		this.resize(size, translateX, translateY);
	}
	
	public void resize(Double size, Double translateX, Double translateY) {
		this.setFitHeight(size);
		this.setFitWidth(size);
		
		this.setTranslateX(translateX);
		this.setTranslateY(translateY);
	}

	public Fruit getFruit() {
		return this.fruit;
	}
}

