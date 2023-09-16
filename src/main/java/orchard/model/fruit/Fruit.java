package orchard.model.fruit;

import orchard.model.enums.FruitType;

public abstract class Fruit {

	private FruitType fruitType;
	private Integer index;
	
	protected Fruit(FruitType fruitType, Integer index) {
		this.fruitType = fruitType;
		this.index = index;
	}

	public Integer getIndex() {
		return index;
	}

	public FruitType getFruitType() {
		return fruitType;
	}
	
}
