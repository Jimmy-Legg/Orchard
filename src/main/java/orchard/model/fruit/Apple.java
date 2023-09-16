package orchard.model.fruit;

import orchard.model.enums.FruitType;

public class Apple extends Fruit{
	
	public Apple(Integer index) {
		super(FruitType.APPLE, index);
	}
	
	@Override
	public String toString() {
		return "Apple";
	}
}
